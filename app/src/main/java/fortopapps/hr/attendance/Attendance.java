package fortopapps.hr.attendance;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;
import fortopapps.hr.connection.parsejson;

public class Attendance extends AppCompatActivity {

    ListView lvData ;
    AttendanceAdapterClass myAdapter ;

//    String url = "http://104.236.218.198/api/attendance";

    String url = "http://maridive.lis-app.com/api/attendance";
    Connection Con;



 /// More Page
    boolean flag  ;
    int pageCount = 1 ;
    boolean stoppage  ;


    ArrayList arractivities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Attendance");



        getattendance();

    }


    // ======================================================================

    public void getattendance()
    {
        Con = new Connection(this,url);
        Con.Reset();
        Con.AddParmmter("page", "1");
        Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
        Con.Connect(new Connection.Result() {
            public void data(String str) {

                parsejson obj = new parsejson();

                Map dic = obj.GetDic(str);

                JSONArray activities = (JSONArray) dic.get("activities");

                arractivities = obj.GetArray(activities);
                loadtable();
            }
        });

    }


    private void loadtable()
    {
        lvData = (ListView)  findViewById(R.id.lvattendace);


        myAdapter = new AttendanceAdapterClass(Attendance.this);

        parsejson obj = new parsejson();

        for (int i = 0; i < arractivities.size(); i++) {

        JSONObject rr = (JSONObject) arractivities.get(i);
        Map Row = obj.GetDic(rr);
        String month = Row.get("month").toString();
        JSONArray data = (JSONArray) Row.get("data");
        ArrayList myList = obj.GetArray(data);

        ArrayAdapter<String> listadapter = new ArrayAdapter<String>(this, R.layout.activity_attendance_cell, myList);
        myAdapter.addSection(month, listadapter);
     }

        lvData.setAdapter(myAdapter);

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
//        Attendance.this.finish();
//    }

}
