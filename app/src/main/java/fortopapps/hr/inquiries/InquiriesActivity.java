package fortopapps.hr.inquiries;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.paginate.abslistview.LoadingListItemCreator;

import java.util.ArrayList;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;

public class InquiriesActivity extends AppCompatActivity implements  SwipeRefreshLayout.OnRefreshListener{


    // ======================================================================

//    String url = "http://maridive.lis-app.com/api/inquiriesList";

    String url = "http://104.236.218.198/api/inquiriesList";
    private SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar ;

    ListView lvData ;
    ArrayList<InquiriesDataClass> inquiriesList;
    InquiriesDataClass clsInquiriesData = new InquiriesDataClass();

    // handling the received data after parsing it
    InquiriesDataClass theData ;
    ProgressBar footer ;


    boolean flag  ;
    int pageCount = 1 ;
    boolean stoppage  ;

    Connection Con ;


    InquiriesAdapterClass myAdapter ;

    // ======================================================================

    TextView username ;
    TextView email ;
    ImageView userphoto;
    ImageButton btncreatinq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquirieslist_fragment);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Inquiries");

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_inquiries);
        swipeRefreshLayout.setOnRefreshListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar_inquiries_list);

        Con  = new Connection(this,url);
//        Con.UseCash = true;
          Con.ClearCash();

        btncreatinq = (ImageButton)  findViewById(R.id.imgbtn_createInquiry);

        btncreatinq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Intent i = new Intent(InquiriesActivity.this, CreateInquiry.class);
                startActivity(i);

            }
        });


//        setuploading();

        // Load News Data
   getnews("1");




   setupmore();
        cellClicked();






//        swipeRefreshLayout.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        swipeRefreshLayout.setRefreshing(true);
//
//                                        getnews();
//                                    }
//                                }
//        );






    }


    // ======================================================================



    @Override
    public void onRefresh() {
//        getnews();

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        getnews("1");
                                    }
                                }
        );
    }


    public void getnews(String currentpage)
    {
        lvData = (ListView)  findViewById(R.id.lvInquiries);

        Con.Reset();

        Con.AddParmmter("page",currentpage);
            Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
//        Con.AddParmmter("mobile_token", "358021058398292");
        System.out.println(Con.toString());
        Con.Connect(new Connection.Result() {
            public void data(String str) {

                System.out.println(str);
                inquiriesList = clsInquiriesData.parseJson(str);
                loadtable();

                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }
        });

    }


//    private void setupmore ()
//    {
//
//      lvData.setOnScrollListener(new EndlessScrollListener() {
//@Override
//public boolean onLoadMore(int page, int totalItemsCount) {
//    // Triggered only when new data needs to be appended to the list
//    // Add whatever code is needed to append new items to your AdapterView
//
//    getnews(String.valueOf(page));
//    myAdapter.notifyDataSetChanged();
//    // or customLoadMoreDataFromApi(totalItemsCount);
//    return true; // ONLY if more data is actually being loaded; false otherwise.
//}
//});
//
//    }




    private void setupmore() {

        lvData.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {
                // TODO Auto-generated method stub


                flag = true;

                if (arg1 == 10) {
                    flag = false;

                }


            }

            @Override
            public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //TODO Auto-generated method stub

                int modrow = -1;
                if (firstVisibleItem >= 7) {
                    modrow = firstVisibleItem % 7;
                }
                if (modrow == 0 && flag == true && stoppage == false) {


                    flag = false;

                    pageCount++;

                    String page = String.valueOf(pageCount);

                    Con.Reset();
                    Con.AddParmmter("page", page);
                    Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(InquiriesActivity.this));
//                    Con.AddParmmter("mobile_token","358021058398292" );


                    Con.Connect(new Connection.Result() {
                        public void data(String str) {

                            ArrayList<InquiriesDataClass> pagedata = new ArrayList<InquiriesDataClass>();
                            pagedata = clsInquiriesData.parseJson(str);

                            if (pagedata.size() > 0) {
                                inquiriesList.addAll(pagedata);
                            } else {
                                pageCount--;
                                stoppage = true;
                            }
                            myAdapter.notifyDataSetChanged();

                        }
                    });

                }


            }


        });






}

    private void loadtable()
    {

        myAdapter = new InquiriesAdapterClass(InquiriesActivity.this, inquiriesList);

        myAdapter.notifyDataSetChanged();
        lvData.setAdapter(myAdapter);



    }

private void cellClicked()
    {

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            int positionItem;

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                theData = inquiriesList.get(position);

                Intent i = new Intent(InquiriesActivity.this, InquiriesDetailsActivity.class);
                i.putExtra("obj", theData);
                startActivity(i);


            }
        });
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
//        InquiriesActivity.this.finish();
//    }


    private class CustomLoadingListItemCreator implements LoadingListItemCreator {
        @Override
        public View newView(int position, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.inquiries_cell_data, parent, false);
//            view.setTag(new VH(view));
            return view;
        }

        @Override
        public void bindView(int position, View view) {
            // Bind custom loading row if needed
        }
    }


     // End class
}
