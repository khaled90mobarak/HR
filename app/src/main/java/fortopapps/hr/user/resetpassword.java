package fortopapps.hr.user;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;

public class resetpassword extends AppCompatActivity {
    EditText email;
    Button btnchange;
    TextView msg;




//    String urlFull = "http://104.236.218.198/api/password/email";

    String urlFull = "http://maridive.lis-app.com/api/password/email";
    Connection Con;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_resetpassword);


        getSupportActionBar().setTitle("Reset my password");
//        getSupportActionBar().hide();

        email = (EditText) findViewById(R.id.r_email);
        msg = (TextView) findViewById(R.id.r_msg);
        btnchange = (Button) findViewById(R.id.r_resetpassword);
        btnchange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (email.getText().toString().equals("")) {
                    msg.setTextColor(Color.RED);
                    msg.setText("Please enter your email .");

                } else {
                    postLoginData();
                }

            }
        });


        // khaled mobarak
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    // khaled mobarak
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);

                this.finish();

                return true;
        }
        return true;
    }


    //  khaled mobarak
//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//       resetpassword.this.finish();
//    }


    private void postLoginData()
    {


        Con = new Connection(this,urlFull);
        Con.UseCash =false;
        Con.AddParmmter("email", email.getText().toString());
        Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
        Con.Connect(new Connection.Result() {
            public void data(String str) {

                try {

                    JSONObject jobj = new JSONObject(str);

                    String status = jobj.getString("status");


                    if (status.equals("fail")) {


                        String message = jobj.getString("message");
                        msg.setText(message);
                        msg.setTextColor(Color.RED);
                    } else {

                        String message = jobj.getString("message");
                        msg.setText(message);
                        msg.setTextColor(Color.GREEN);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }




}


