package fortopapps.hr.checkinout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;

public class SendingExceptionActivity extends AppCompatActivity {
	
	
	Spinner spReasons_check ; 
	EditText etNotes_check ; 
	ImageButton btnSubmit_checkException;
	
	
	String exceptionId ; 
	ArrayList<String> reasonId ; 
	ArrayList<String> reasonTitle ; 
	String reasonPosition ; 
	
	
//	String url = "http://104.236.218.198/api/exception";

	String url = "http://maridive.lis-app.com/api/exception";

	Connection Con;


	@Override
	protected void onCreate(Bundle savedInstanceState) {    
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sending_exception);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setTitle("Exception");
		
		
		// getting data from the check in/out activity to fill in the spinner
		Bundle b = getIntent().getExtras() ;		
		exceptionId = b.getString("exception_id");
		reasonId = b.getStringArrayList("reasonId_array");
		reasonTitle = b.getStringArrayList("reasonTitle_array");
		
		
		etNotes_check = (EditText) findViewById(R.id.etNotes_check);
		spReasons_check = (Spinner) findViewById(R.id.spinner_reasons_check);
		btnSubmit_checkException = (ImageButton) findViewById(R.id.btnSubmit_checkException);
		
		spReasons_check
		.setAdapter(new ArrayAdapter<String>(SendingExceptionActivity.this,
				android.R.layout.simple_spinner_dropdown_item,
				reasonTitle));
		
		spReasons_check.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
									   int position, long arg3) {
				// TODO Auto-generated method stub


				reasonPosition = reasonId.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		
		btnSubmit_checkException.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SendExp();

			}
		});



		
	}


	// for the back button
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		   switch (id) {
	        case android.R.id.home:
	            // app icon in action bar clicked; goto parent activity.
	            this.finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	
	}


	// ======================================================================

	private void SendExp()
	{

		Con = new Connection(this,url);
		Con.UseCash =false;
		Con.AddParmmter("exception_id", exceptionId);
		Con.AddParmmter("reason_id", reasonPosition);
		Con.AddParmmter("employee_notes", etNotes_check.getText().toString());
		Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
		Con.Connect(new Connection.Result() {
			public void data(String str) {

				try {

					JSONObject jobj = new JSONObject(str);

					String status = jobj.getString("status");

					if (status.equals("success"))
					{
						Toast.makeText(SendingExceptionActivity.this, "Sent", Toast.LENGTH_SHORT).show();
						finish();
					}
					else if (status.equals("failed"))
					{
						String message = jobj.getString("message");
						Toast.makeText(SendingExceptionActivity.this,message, Toast.LENGTH_SHORT).show();
					}


				} catch (JSONException e) {
					e.printStackTrace();
				}


			}

		});




	}






	 
}
