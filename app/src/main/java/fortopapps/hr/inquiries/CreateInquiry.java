package fortopapps.hr.inquiries;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.checkinout.DepartmentSpinnerDataClass;
import fortopapps.hr.checkinout.EmployeeSpinnerDataClass;
import fortopapps.hr.connection.Connection;

public class CreateInquiry extends AppCompatActivity {

    Spinner sp_department ;
    Spinner sp_employee ;

//    String urlDepartments = "http://104.236.218.198/api/departments" ;
//    String urlEmployees = "http://104.236.218.198/api/employees" ;
//    String urlSend = "http://104.236.218.198/api/inquiryCreate";

    String urlDepartments = "http://maridive.lis-app.com/api/departments" ;
    String urlEmployees = "http://maridive.lis-app.com/api/employees" ;
    String urlSend = "http://maridive.lis-app.com/api/inquiryCreate";



    Connection Con;

    DepartmentSpinnerDataClass clsDepart = new DepartmentSpinnerDataClass();
    EmployeeSpinnerDataClass clsemp = new EmployeeSpinnerDataClass();

    Spinner mySpinnerDepartments ;
    Spinner mySpinnerEmployees ;


    ImageButton btnSendInquiry ;

    EditText etTitle , etMessage;

    ArrayList<String> departmentPositionId ;
    String departmentId ;


    ArrayList<String> employeePositionId ;
    String employeeId ;


    ProgressDialog mProgressDialog;

    ProgressBar progressbar ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inquiry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Create Inquiry");

        progressbar = (ProgressBar) findViewById(R.id.progressbar_create_inquiry);

        mySpinnerDepartments = (Spinner) findViewById(R.id.spinner_createInquiry_department);
        mySpinnerEmployees = (Spinner) findViewById(R.id.spinner_createInquiry_employee);

        etTitle = (EditText) findViewById(R.id.etInquiryTitle_createInquiry);
        etMessage = (EditText) findViewById(R.id.etInquiryMessage_createInquiry);
        btnSendInquiry = (ImageButton) findViewById(R.id.imgbtn_sendInquiry);


        Departments();
        Employees();

        LoadDepartments();

        btncreate();

    }

  private void LoadDepartments()
    {
        Con = new Connection(this,urlDepartments);
        Con.UseCash =false;

        Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));

        Con.Connect(new Connection.Result() {
            public void data(String str) {
                final ArrayList<DepartmentSpinnerDataClass> result = clsDepart.parseJsonGetDepartment(str);


                ArrayList<String> departmentSpinnerName = new ArrayList<String>();

                ArrayList<DepartmentSpinnerDataClass> departmentListData = result;

                for (DepartmentSpinnerDataClass departmentSpinnerData : departmentListData) {

                    departmentSpinnerName.add(departmentSpinnerData.getName());

                }


                departmentPositionId = new ArrayList<String>();
                for (DepartmentSpinnerDataClass departmentSpinnerData : departmentListData) {


                    departmentPositionId.add(departmentSpinnerData.getId());

                }


                mySpinnerDepartments
                        .setAdapter(new ArrayAdapter<String>(CreateInquiry.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                departmentSpinnerName));


            }


        });




 }

    private void LoadEmployees()
    {
        Con = new Connection(this,urlEmployees);
        Con.UseCash =false;
        Con.AddParmmter("id", departmentId);
        Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));

        Con.Connect(new Connection.Result() {
            public void data(String str) {

                final ArrayList<EmployeeSpinnerDataClass> result = clsemp.parseJsonGetEmployee(str);

                ArrayList<String> employeeSpinner = new ArrayList<String>();

                ArrayList<EmployeeSpinnerDataClass> employeeListData = result;

                for (EmployeeSpinnerDataClass employeeSpinnerData : employeeListData) {

                    employeeSpinner.add(employeeSpinnerData.getFname() + "  " + employeeSpinnerData.getLname());

                }


                employeePositionId = new ArrayList<String>();
                for (EmployeeSpinnerDataClass employeeSpinnerData : employeeListData) {

                    employeePositionId.add(employeeSpinnerData.getId());

                }


                mySpinnerEmployees
                        .setAdapter(new ArrayAdapter<String>(CreateInquiry.this,
                                android.R.layout.simple_spinner_dropdown_item,
                                employeeSpinner));
            }


        });


        progressbar.setVisibility(View.GONE);


    }


    private void Departments()
{
    mySpinnerDepartments
            .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0,
                                           View arg1, int position, long arg3) {
                    // TODO Auto-generated method stub
                    // Locate the textviews in activity_main.xml

                    departmentId = departmentPositionId.get(position);
                    LoadEmployees();

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });

}

private  void Employees()
{

    mySpinnerEmployees.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int position, long arg3) {
            // TODO Auto-generated method stub

            employeeId = employeePositionId.get(position);
//							Toast.makeText(CreateInquiry.this, employeeId, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }

    });
}




    private  void btncreate()
    {
        // ==

        btnSendInquiry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Con = new Connection(CreateInquiry.this, urlSend);
                Con.UseCash = false;
                Con.AddParmmter("title", etTitle.getText().toString());
                Con.AddParmmter("message", etMessage.getText().toString());
                Con.AddParmmter("department_id", departmentId);
                Con.AddParmmter("to", employeeId);
                Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(CreateInquiry.this));

                Con.Connect(new Connection.Result() {
                    public void data(String str) {

                        try {

                            JSONObject objData = new JSONObject(str);
                            String status = objData.getString("status");

                            if (status.equals("Success")) {
                                etTitle.setText("");
                                etMessage.setText("");
                                Toast.makeText(CreateInquiry.this, "Inquiry created .", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(CreateInquiry.this, "Inquiry not created .", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }

                });

            }
        });

        //==

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);

        }

        return true;
    }


//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//        CreateInquiry.this.finish();
//    }



}
