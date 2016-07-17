package fortopapps.hr.sallary;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;
import fortopapps.hr.connection.parsejson;

public class sallary extends AppCompatActivity {
	ArrayList Arrpayrolls;
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<Map> listDataHeader;
	HashMap<Map, List<Map>> listDataChild;

//	String url = "http://104.236.218.198/api/salary";

	String url = "http://maridive.lis-app.com/api/salary";


	Connection Con;



	TextView s_name;
	TextView s_dempartment;
	TextView s_numbers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sallary);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setTitle("Salary");

		Con = new Connection(this,url);


		  s_name = (TextView) findViewById(R.id.s_name);
		  s_dempartment = (TextView)  findViewById(R.id.s_dempartment);
		  s_numbers = (TextView)  findViewById(R.id.s_numbers);



      checkpassword();
	}

	private void  checkpassword()
	{

		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog.setContentView(R.layout.checkpassword);
		//dialog.setTitle("");

		final EditText txtps_chk = (EditText) dialog.findViewById(R.id.editText);
		Button chkps          = (Button) dialog.findViewById(R.id.chkps);

		dialog.show();


		chkps.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				SharedPreferences pref = getSharedPreferences("prefs_credentials", MODE_PRIVATE);
				String mPassword = pref.getString("password", "");
				String typeps = txtps_chk.getText().toString();
				if (typeps.equals(mPassword)) {
					dialog.hide();
					getsallary();
				} else {
					Toast.makeText(getApplicationContext(),
							"Invalid password .",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

private void  setupExpand()
	{
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		expListView.setGroupIndicator(null);

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
										int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();




				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
//				Toast.makeText(getApplicationContext(),
//						listDataHeader.get(groupPosition) + " Expanded",
//						Toast.LENGTH_SHORT).show();




			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
//				Toast.makeText(getApplicationContext(),
//						listDataHeader.get(groupPosition) + " Collapsed",
//						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(
//						getApplicationContext(),
//						listDataHeader.get(groupPosition)
//								+ " : "
//								+ listDataChild.get(
//										listDataHeader.get(groupPosition)).get(
//										childPosition), Toast.LENGTH_SHORT)
//						.show();
				return false;
			}
		});

	}
	// ======================================================================

	public void getsallary()
	{

		Con.Reset();
		Con.AddParmmter("page","1");
		Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
		Con.Connect(new Connection.Result() {
			public void data(String str) {

				parsejson obj = new parsejson();

				Map dic = obj.GetDic(str);

				JSONArray payrolls = (JSONArray) dic.get("payrolls");

				Arrpayrolls = obj.GetArray(payrolls);
				prepareListData();
			}
		});


	}



	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<Map>();
		listDataChild = new HashMap<Map, List<Map>>();
		parsejson obj = new parsejson();

		String row_id = "";
		String employee_fname = "";
		String employee_lname = "";
		String department_name = "";


		for (int i = 0; i < Arrpayrolls.size(); i++) {

			JSONObject rr = (JSONObject) Arrpayrolls.get(i);
			Map Row = obj.GetDic(rr);

			String month = Row.get("month").toString();
			String year = Row.get("year").toString();
			String basic_salary = Row.get("basic_salary").toString();
			String nature_allowance = Row.get("nature_allowance").toString();
			String representation_allowance = Row.get("representation_allowance").toString();
			String sea_allowance = Row.get("sea_allowance").toString();
			String allowance_the_time = Row.get("allowance_the_time").toString();
			String addition_social = Row.get("addition_social").toString();
			String in_addition_a_private_social = Row.get("in_addition_a_private_social").toString();
			String benifits_subject_to_taxation = Row.get("benifits_subject_to_taxation").toString();
			String total_benefits = Row.get("total_benefits").toString();
			String net_dollar = Row.get("net_dollar").toString();
			String net_pounds = Row.get("net_pounds").toString();
			String tax = Row.get("tax").toString();
			String employee_insurance = Row.get("employee_insurance").toString();
			String insurance_premium_car = Row.get("insurance_premium_car").toString();
			String buy_a_precedent = Row.get("buy_a_precedent").toString();
			String vodafone_pounds_factor = Row.get("vodafone_pounds_factor").toString();
			String total_deductions = Row.get("total_deductions").toString();
			String insurance_company = Row.get("insurance_company").toString();
			String remaining_car_insurance = Row.get("remaining_car_insurance").toString();
			String total_deductions_after_insurance = Row.get("total_deductions_after_insurance").toString();
			String month_char = Row.get("month_char").toString();

 if (i==0)
 {
	 employee_fname = Row.get("employee_fname").toString();
	 employee_lname = Row.get("employee_lname").toString();
	 department_name = Row.get("department_name").toString();
	 row_id = Row.get("id").toString();
 }


			// Adding child data
			Map<String, String> map = new HashMap<String, String>();
			map.put("basic_salary", basic_salary);
			map.put("total_benefits", basic_salary);
			map.put("total_deductions", basic_salary);
			map.put("net_pounds", basic_salary);
			map.put("month_char", month_char + " " + year);
			listDataHeader.add(map);


			// Adding child data
			List<Map> top250 = new ArrayList<Map>();

			Map<String, String> item1 = new HashMap<String, String>();
			map.put("basic_salary", basic_salary);
			map.put("nature_allowance", nature_allowance);
			map.put("representation_allowance", representation_allowance);
			map.put("sea_allowance", sea_allowance);
			map.put("allowance_the_time", allowance_the_time);
			map.put("addition_social", addition_social);
			map.put("in_addition_a_private_social", in_addition_a_private_social);
			map.put("benifits_subject_to_taxation", benifits_subject_to_taxation);
			map.put("total_benefits", total_benefits);
			map.put("net_dollar", net_dollar);
			map.put("net_pounds", net_pounds);
			map.put("tax", tax);
			map.put("employee_insurance", employee_insurance);
			map.put("insurance_premium_car", insurance_premium_car);
			map.put("buy_a_precedent", buy_a_precedent);
			map.put("vodafone_pounds_factor", vodafone_pounds_factor);
			map.put("total_deductions", total_deductions);
			map.put("insurance_company", insurance_company);
			map.put("remaining_car_insurance", remaining_car_insurance);
			map.put("total_deductions_after_insurance", total_deductions_after_insurance);



			top250.add(map);

			listDataChild.put(listDataHeader.get(0), top250); // Header, Child data

		}

     s_name.setText(employee_fname + " " + employee_lname);
s_dempartment.setText(department_name);
		s_numbers.setText(row_id);
		setupExpand();
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);

		}

		return true;
	}


//	@Override
//	public void onBackPressed() {
//		moveTaskToBack(true);
//		sallary.this.finish();
//	}




}
