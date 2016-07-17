package fortopapps.hr.inquiries;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;
import fortopapps.hr.connection.parsejson;

public class InquiriesDetailsActivity extends AppCompatActivity {


    // ======================================================================

    TextView tvInquiryDetails_from ;
    TextView tvInquiryDetails_department ;
    TextView tvInquiryDetails_to ;

    TextView tvInquiryMessage ;
    TextView tvInquiryDetails_date ;
    TextView tvInquiryDetails_repliesCount ;
    TextView tvInquiryDetails_title;

    TextView TvInquiryDetails_replyEmp;
    TextView tvInquiryDetails_replyMessage;

    EditText etInquiryDetails_sendInquiry ;

    ListView lvReplies ;

    ImageButton btnSendAnswers ;



    ArrayList<InquiriesDataClass> first ;

    ArrayList<String> myAnswersParsed ;

    String idInquiry ;

//    String url_viewReplies = "http://104.236.218.198/api/inquiryView";
//    String url_myAnswers = "http://104.236.218.198/api/replay";

    String url_viewReplies = "http://maridive.lis-app.com/api/inquiryView";
    String url_myAnswers = "http://maridive.lis-app.com/api/replay";


    Connection ConReplies  ;
    Connection ConAnswers ;

    // ======================================================================


    InquiriesDataClass clsdata =  new InquiriesDataClass();
    ArrayList<InquiriesDataClass> dataList;
    InquiriesRepliesAdapterClass adapter;
    boolean flag  ;
    int pageCount = 1 ;
    boolean stoppage  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquiries_details);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Inquiry Details");

          ConReplies = new Connection(this,url_viewReplies);
          ConAnswers = new Connection(this,url_myAnswers);


        Bundle b = getIntent().getExtras() ;
        InquiriesDataClass d = b.getParcelable("obj");
        idInquiry = d.getId();

        tvInquiryDetails_from = (TextView) findViewById(R.id.tvInquiryDetails_from);
        tvInquiryDetails_department = (TextView) findViewById(R.id.tvInquiryDetails_department);
        tvInquiryDetails_to = (TextView) findViewById(R.id.tvInquiryDetails_to);

        tvInquiryMessage = (TextView) findViewById(R.id.tvInquiryMessage);
        tvInquiryDetails_title = (TextView) findViewById(R.id.tvInquiryDetails_title);
        tvInquiryDetails_date = (TextView) findViewById(R.id.tvInquiryDetails_date);
        tvInquiryDetails_repliesCount = (TextView) findViewById(R.id.tvInquiryDetails_repliesCount);
        ImageView imgStatus = (ImageView) findViewById(R.id.imgInquiryStatus2);

        lvReplies = (ListView) findViewById(R.id.lvInquiries_replies);
        dataList = new ArrayList<InquiriesDataClass>();
       // dataList.add(d);

        adapter = new InquiriesRepliesAdapterClass(this,dataList);
        adapter.notifyDataSetChanged();
        lvReplies.setAdapter(adapter);


        tvInquiryDetails_from.setText(d.getEmpFrom());
        tvInquiryDetails_department.setText(d.getDepartmentname());
        tvInquiryDetails_to.setText(d.getEmpTo());

        tvInquiryMessage.setText(d.getMessage());
        tvInquiryDetails_title.setText(d.getTitle());
        tvInquiryDetails_date.setText(d.getEntryDate());
        tvInquiryDetails_repliesCount.setText(d.getReplay_count());

        if (d.getStatus().equals("1"))
        {
            imgStatus.setImageResource(R.drawable.img_inquiry_pending);
        }

        else if (d.getStatus().equals("2"))
        {
            imgStatus.setImageResource(R.drawable.img_inquiry_open);
        }

        else if (d.getStatus().equals("3"))
        {
            imgStatus.setImageResource(R.drawable.img_inquiry_closed);
        }


        getData(true);

        btnsend();





    }


    private void  btnsend()
    {
        btnSendAnswers = (ImageButton) findViewById(R.id.img_button_sendInquiry1);
        etInquiryDetails_sendInquiry  = (EditText) findViewById(R.id.etInquiryDetails_sendInquiry);

        btnSendAnswers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//				 TODO Auto-generated method stub


                ConAnswers.Reset();
                ConAnswers.AddParmmter("parent", idInquiry);
                ConAnswers.AddParmmter("message", etInquiryDetails_sendInquiry.getText().toString());
                ConAnswers.AddParmmter("mobile_token", notification.GetSavedDeviceToken(InquiriesDetailsActivity.this));
                ConAnswers.Connect(new Connection.Result() {
                    public void data(String str) {


                        try {

                            JSONObject objData = new JSONObject(str);
                            String status = objData.getString("status");

                            if (status.equals("Success")) {
                                etInquiryDetails_sendInquiry.setText("");
                                getData(false);
                                // Toast.makeText(InquiriesDetailsActivity.this, "Inquiry created .", Toast.LENGTH_LONG).show();
                            } else {

//                                       String message = objData.getString("message");
//
//                                        Toast.makeText(InquiriesDetailsActivity.this, message, Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }


                    }
                });

            }
        });

    }


    public void getData(Boolean UseCash)
    {
        lvReplies = (ListView)  findViewById(R.id.lvInquiries_replies);

        ConReplies.Reset();
        ConReplies.UseCash = UseCash;
        ConReplies.AddParmmter("id", idInquiry);
        ConReplies.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
        ConReplies.Connect(new Connection.Result() {
            public void data(String str) {

                parsejson json = new parsejson();
                Map obj = json.GetDic(str);
                String inquiry_str = obj.get("inquiry").toString();
                ArrayList arr = json.GetArray(inquiry_str);
                if (arr.size() > 0) {
                    String info = arr.get(0).toString();
                    Map inq = json.GetDic(info);

                    tvInquiryDetails_department.setText(inq.get("name").toString());

                }
                dataList.addAll(clsdata.parseJsonreplies(str));
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void setupmore() {

        lvReplies.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                // TODO Auto-generated method stub


                flag = true;

                if (arg1 == 5) {
                    flag = false;

                }


            }

            @Override
            public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // TODO Auto-generated method stub
                int modrow = -1;
                if (firstVisibleItem >= 7) {
                    modrow = firstVisibleItem % 7;
                }

                if (modrow == 0 && flag == true && stoppage == false) {


                    flag = false;

                    pageCount++;


                    ConReplies.Reset();
                    ConReplies.AddParmmter("page", String.valueOf(pageCount));
                    ConReplies.AddParmmter("id", idInquiry);
                    ConReplies.AddParmmter("mobile_token", notification.GetSavedDeviceToken(InquiriesDetailsActivity.this));
                    ConReplies.Connect(new Connection.Result() {
                        public void data(String str) {

                            ArrayList<InquiriesDataClass> pagedata = new ArrayList<InquiriesDataClass>();
                            pagedata = clsdata.parseJson(str);

                            if (pagedata.size() > 0) {
                                dataList.addAll(pagedata);
                            } else {
                                pageCount--;
                                stoppage = true;
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });


                }


            }


        });



}



private void cellClicked()
    {


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
//        InquiriesDetailsActivity.this.finish();
//    }




     // End class
}
