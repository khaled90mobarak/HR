package fortopapps.hr.checkinout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.attendance.Attendance;
import fortopapps.hr.connection.Connection;

public class checkinout extends AppCompatActivity {


//    String url_check = "http://104.236.218.198/api/check";
//
//    String last_activity = "http://104.236.218.198/api/last-activity";


    String url_check = "http://maridive.lis-app.com/api/check";

    String last_activity = "http://maridive.lis-app.com/api/last-activity";


    Connection Con;

    CheckInOutDataClass network_data = new CheckInOutDataClass();



    TextView tvCheckInout_type , tvCheckInout_location , tvCheckInout_date , tvCheckInout_time ,nextstatus;
    ImageView ImgCheckinout;
    ImageButton btnCheck ;
    ImageButton btnAttendanceHistory ;



    ArrayList<CheckInOutDataClass> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkinout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Check In/Out");


        tvCheckInout_location = (TextView)  findViewById(R.id.tvCheckinout_location);
        tvCheckInout_type = (TextView)  findViewById(R.id.tvCheckinout_checkingStatus);
        tvCheckInout_time = (TextView)  findViewById(R.id.tvCheckinout_time);
        tvCheckInout_date = (TextView)  findViewById(R.id.tvCheckinginout_date);
        nextstatus = (TextView)  findViewById(R.id.nextstatus);
        ImgCheckinout = (ImageView) findViewById(R.id.img_checkInOut_checkIn);
        btnAttendanceHistory = (ImageButton)  findViewById(R.id.imgbtn_checkinOut_attendance);
        btnCheck = (ImageButton)  findViewById(R.id.imgbtn_checkinout);
        btnCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                check();

            }
        });

        btnAttendanceHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(checkinout.this,Attendance.class);
                startActivity(i);


            }
        });

        GetlastStatus();
    }

    // ======================================================================

    private void check()
    {
        NetworkInfo  network = NetworkInfo.getNetworkInfo(this);
        Con = new Connection(this,url_check);
        Con.UseCash =false;
        Con.AddParmmter("ip", network.RouterIp);
        Con.AddParmmter("network_name", network.NetWorkname);
        Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));

        Con.Connect(new Connection.Result() {
            public void data(String str) {

                try {

                    JSONObject jobj = new JSONObject(str);

                    datalist = network_data.parseJsonCheck(str);

                    ReadFeedBack();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }
private  void  GetlastStatus()
 {
     NetworkInfo  network = NetworkInfo.getNetworkInfo(this);

     Con = new Connection(this,last_activity);
     Con.UseCash =false;
     Con.AddParmmter("ip", network.RouterIp);
     Con.AddParmmter("network_name", network.NetWorkname);
     Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));

     Con.Connect(new Connection.Result() {
         public void data(String str) {

             try {
                 JSONObject jobj = new JSONObject(str);

                 datalist = network_data.parseJsonCheck(str);

                 ReadFeedBack();


             } catch (JSONException e) {
                 e.printStackTrace();
             }


         }

     });

 }




private void ReadFeedBack()
{

    if (datalist.get(0).getJsonStatus().equals("success"))
    {


        if (datalist.get(0).getLastAttendance_type()!=null )
        {
            tvCheckInout_type.setText(datalist.get(0).getLastAttendance_type());
        }

        if (datalist.get(0).getLastAttendance_location()!=null)
        {
            tvCheckInout_location.setText(datalist.get(0).getLastAttendance_location());
        }

        if (datalist.get(0).getLastAttendance_date()!=null)
        {
            tvCheckInout_date.setText(datalist.get(0).getLastAttendance_date());
        }

        if (datalist.get(0).getLastAttendance_time()!=null)
        {
            tvCheckInout_time.setText(datalist.get(0).getLastAttendance_time());
        }

        if (datalist.get(0).getLastAttendance_next()!=null)
        {
            nextstatus.setText(datalist.get(0).getLastAttendance_next());
        }

        if (datalist.get(0).getLastAttendance_status()!=null)
        {
            String status = datalist.get(0).getLastAttendance_status();
          if (status.equals("Pending"))
            {

                ImgCheckinout.setImageResource(R.drawable.img_checkinout_checkin);
            }
            else if(status.equals("Approved"))
          {

              ImgCheckinout.setImageResource(R.drawable.img_checkinout_checkin_done);
          }
          else
          {

              ImgCheckinout.setImageResource(R.drawable.img_checkinout_checkin_reject);
          }

        }

        if ((datalist.get(0).getReasonId_array()!=null))
        {

            ArrayList<String> myReasonId_array ;
            ArrayList<String> myReasonTitle_array ;

            myReasonId_array = new ArrayList<String>();
            myReasonId_array.addAll(datalist.get(0).getReasonId_array());

            myReasonTitle_array = new ArrayList<String>() ;
            myReasonTitle_array.addAll(datalist.get(0).getReasonTitle_array());

            Intent y = new Intent(checkinout.this,SendingExceptionActivity.class) ;
            y.putStringArrayListExtra("reasonId_array", myReasonId_array);
            y.putStringArrayListExtra("reasonTitle_array", myReasonTitle_array);
            y.putExtra("exception_id", datalist.get(0).getCheck_status_exceptionId());
            startActivity(y);

        }



    }

    else
        Toast.makeText(checkinout.this, datalist.get(0).getJsonStatus() + "\n" + datalist.get(0).getJsonMessage(),
                Toast.LENGTH_SHORT).show();
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
//        checkinout.this.finish();
//    }




}
