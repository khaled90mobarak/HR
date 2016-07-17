package fortopapps.hr.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import fortopapps.hr.R;
import fortopapps.hr.attendance.Attendance;
import fortopapps.hr.checkinout.checkinout;
import fortopapps.hr.connection.Connection;
import fortopapps.hr.inquiries.EndlessScrollListener;
import fortopapps.hr.inquiries.InquiriesActivity;
import fortopapps.hr.sallary.sallary;

public class Side_menuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,  SwipeRefreshLayout.OnRefreshListener {


    // ======================================================================

//    String url = "http://104.236.218.198/api/news";

    private final int AUTOLOAD_THRESHOLD = 4;
    private final int MAXIMUM_ITEMS = 52;
    private boolean mIsLoading = false;
    private boolean mMoreDataAvailable = true;
    private boolean mWasLoading = false;


    String url = "http://maridive.lis-app.com/api/news";


    private SwipeRefreshLayout swipeRefreshLayout;

    ListView lvData ;
    ArrayList<NewsDataClass> newsList;
    NewsDataClass clsNewsData = new NewsDataClass();

    // handling the received data after parsing it
    NewsDataClass theData ;
    ProgressBar footer ;



    int pageCount = 1 ;
    boolean stoppage  ;

    boolean flag = true ;


    Connection Con;

    NewsAdapterClass myAdapter ;

    NewsAdapterClass refreshAdapter ;
    boolean flag_adapter = true  ;
    // ======================================================================

    TextView username ;
    TextView email ;
    ImageView userphoto;
    int total ;



    ProgressBar progressbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_navigation);



        Toolbar  mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(getResources().getText(R.string.title_activity_news_list));

        final Handler handler = new Handler();

        newsList = new ArrayList<NewsDataClass>();


      //  setTitle(getResources().getText(R.string.title_activity_news_list));
        lvData = (ListView)  findViewById(R.id.lvData);
        setupmenu();
        //setuploading();

        progressbar = (ProgressBar) findViewById(R.id.progressBar_newslist);

        // Load News Data
        Con = new Connection(this,url);

//        Con.UseCash = true;     recently hashed
          Con.ClearCash();   // recently added

   getnews("1");
//        ++pageCount;

        loadtable();
//                     total = Integer.parseInt(newsList.get(0).getTotal());
//        setupmore();
        cellClicked();


        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);


//        lvData.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        lvData.setVerticalScrollBarEnabled(false);
//        lvData.setEnabled(false);


//        lvData.setHasMoreItems(true);

            


        lvData.setOnScrollListener(new AbsListView.OnScrollListener() {




            @Override
            public void onScrollStateChanged(AbsListView view,
                                             int scrollState) { // TODO Auto-generated method stub



                int threshold = 1;
                int count = lvData.getCount();


                if (scrollState == SCROLL_STATE_IDLE ) {
                    if (lvData.getLastVisiblePosition() >= count
                            - threshold&& flag==true) {
                        // Execute LoadMoreDataTask AsyncTask
//                        new LoadMoreDataTask().execute();


//                        ++pageCount ;
                      int total =   Integer.parseInt(newsList.get(0).getTotal());

//                        if (count==total) {
//
//                            flag = false;
//                        }
//Log.i("wezza", String.valueOf(pageCount+1));
                            getnews(String.valueOf(++pageCount));

                        if (flag_adapter==true)
                        myAdapter.notifyDataSetChanged();
                        else {

                            refreshAdapter.notifyDataSetChanged();
                            lvData.setAdapter(refreshAdapter);


                        }

                    }


                }



            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount  ) {
                // TODO Auto-generated method stub


//                if (!mIsLoading && mMoreDataAvailable) {
//                    if (totalItemCount >= MAXIMUM_ITEMS) {
//                        mMoreDataAvailable = false;
//
//                    } else if (totalItemCount - AUTOLOAD_THRESHOLD <= firstVisibleItem + visibleItemCount) {
//                        mIsLoading = true;
//                        getnews(String.valueOf(++pageCount));
//                        myAdapter.notifyDataSetChanged();
//
//                    }
//
//
//                }




////                int lastInScreen = firstVisibleItem + visibleItemCount;
////                if((lastInScreen == totalItemCount)){
//                int AUTOLOAD_THRESHOLD = 4 ;
//
//
////                if (!flag && mMoreDataAvailable) {
////                    if (totalItemCount >= MAXIMUM_ITEMS) {
////                        mMoreDataAvailable = false;
//
////                    }
//
//             if (totalItemCount - AUTOLOAD_THRESHOLD <= firstVisibleItem + visibleItemCount) {
////                        mIsLoading = true;
////                 if (total == totalItems) {
//
//
////                 final int count = lvData.getCount();
////                 final int total = Integer.parseInt(newsList.get(0).getTotal());
//                     // Execute some code after 15 seconds have passed
//
//                 handler.post
//                         (new Runnable() {
//                             public void run() {
//                                 getnews(String.valueOf(++pageCount));
//
//                                 myAdapter.notifyDataSetChanged();
//
//                             }
//
//
//
//                     });
//
////                 }
//                    }
//
//
//
//
//
//
////                        }
////                    }
////                }


//                if(firstVisibleItem+visibleItemCount == totalItemCount && totalItemCount!=0) {
//                     getnews(String.valueOf(++pageCount));
//                    Toast.makeText(Side_menuActivity.this,"ok",Toast.LENGTH_SHORT).show();
//                    myAdapter.notifyDataSetChanged();
//
//                     }
//
//                myAdapter.notifyDataSetChanged();


//                int count = lvData.getCount();
//                int lastIndexInScreen = visibleItemCount + firstVisibleItem;
//                if (lastIndexInScreen>= totalItemCount&& flag==true ) {
//
//                    // It is time to load more items
//
////                    flag = true;
//
////                    int total =   Integer.parseInt(newsList.get(0).getTotal());
////
//                        if (count==Integer.parseInt(newsList.get(1).getTotal())) {
//
//                            flag = false;
//                        }
//
//                    getnews(String.valueOf(++pageCount));
//                    myAdapter.notifyDataSetChanged();
//
//                }


            }

        });





        // new loadmore




//        lvData.setOnScrollListener(new EndlessScrollListener() {
//
//
//            @Override
//            public boolean onLoadMore(int page, int totalItemsCount) {
//                // Triggered only when new data needs to be appended to the list
//                // Add whatever code is needed to append new items to your AdapterView
//
////                getnews(String.valueOf(page));
//
//                Con.Reset();
//                Con.ClearCash();
//
//                Con.AddParmmter("page", "1");
//                Con.Connect(new Connection.Result() {
//                    public void data(String str) {
//
//
////                                                newsList.clear();
//
//                        newsList.addAll(clsNewsData.parseJson(str));
//
//                    }
//                });
//                        // or customLoadMoreDataFromApi(totalItemsCount);
//                return true; // ONLY if more data is actually being loaded; false otherwise.
//            }
//        });
    }







    // Append more data into the adapter
    public void customLoadMoreDataFromApi(int offset) {
        // This method probably sends out a network request and appends new data items to your adapter.
        // Use the offset value and add it as a parameter to your API request to retrieve paginated data.
        // Deserialize API response and then construct new objects to append to the adapter
    }






    @Override
    public void onRefresh() {
//        getnews();
//        newsList.clear();

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {


                                        newsList.clear();
                                        swipeRefreshLayout.setRefreshing(true);


//                                        clsNewsData = new NewsDataClass();

//                                        newsList.clear();
//                                        newsList = new ArrayList<NewsDataClass>();


                                        Con.Reset();

                                        Con.ClearCash();


//                                        newsList = new ArrayList<NewsDataClass>();

//                                        getnews("1");
                                        Con.AddParmmter("page", "1");
                                        Con.Connect(new Connection.Result() {
                                            public void data(String str) {


//                                                newsList.clear();

                                                newsList.addAll(clsNewsData.parseJson(str));     // to append the new items to the listview


                                                refreshAdapter = new NewsAdapterClass(Side_menuActivity.this, newsList);
                                                lvData.setAdapter(refreshAdapter);
                                                refreshAdapter.notifyDataSetChanged();
                                                flag_adapter= false ;


//                                                myAdapter.notifyDataSetChanged();
//                                                lvData.setAdapter(myAdapter);

                                                /////
//                                                 myAdapter = new NewsAdapterClass(Side_menuActivity.this, newsList);
//                                                lvData.setAdapter(myAdapter);
//                                                lvData.invalidateViews();
                                                /////////////


                                                swipeRefreshLayout.setRefreshing(false);
                                                progressbar.setVisibility(View.GONE);


                                            }
                                        });

//                                        loadtable();

//                                        lvData.setAdapter(myAdapter);
//                                        myAdapter.notifyDataSetChanged();







                                    }
                                }
        );



    }

    // Setup menu
    // ======================================================================
public void setupmenu()
 {
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     // setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        username = (TextView) header.findViewById(R.id.username);
        email = (TextView) header.findViewById(R.id.email);
        userphoto = (ImageView) header.findViewById(R.id.userphoto);

        SharedPreferences pref = getSharedPreferences("prefs_credentials", MODE_PRIVATE);
        username.setText(pref.getString("username", "") );
        email.setText(pref.getString("email", ""));
        String avatar = pref.getString("avatar", "");
    //    String avtar_path = pref.getString("avtar_path", "");
      //  avatar = avtar_path + avatar;

//        Picasso.with(this).load(avatar).into(userphoto);    // recently hashed
          userphoto.setImageResource(R.drawable.user_temp);



 }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

        //  super.onBackPressed();

          //  System.exit(0);

            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_menu, menu);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.newsfeeds) {

            getnews("1");

        } else if (id == R.id.inquiries) {
            Intent i = new Intent(Side_menuActivity.this,InquiriesActivity.class);
            startActivity(i);


        } else if (id == R.id.checkinout) {
            Intent i = new Intent(Side_menuActivity.this,checkinout.class);
            startActivity(i);

        }
//        else if (id == R.id.chat) {
//
//            Intent i = new Intent(Side_menuActivity.this,ChatActivity.class);
//            startActivity(i);
//
//        }
        else if (id == R.id.attendance_history) {

            Intent i = new Intent(Side_menuActivity.this,Attendance.class);
               startActivity(i);



        } else if (id == R.id.salary_history) {
            Intent i = new Intent(Side_menuActivity.this,sallary.class);
            startActivity(i);
        }
//        else if (id == R.id.settings) {
//
//            Intent i = new Intent(Side_menuActivity.this,profile.class);
//            startActivity(i);
//
//        }
        else if (id == R.id.sign_out) {

            SharedPreferences prefs = getSharedPreferences("prefs_credentials", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("username", "");
            editor.putString("password", "");


            editor.commit();

            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // News
    // ======================================================================

    public void getnews(String currentPage)
    {



        stoppage = false;
        Con.Reset();

        Con.AddParmmter("page", currentPage);
        Con.Connect(new Connection.Result() {
            public void data(String str) {

//                newsList.removeAll();    // u should re intialize this array for it doesnt repeat data
//                newsList.clear();
//                newsList = new ArrayList<NewsDataClass>();


//                 System.out.println(str);





//                newsList = clsNewsData.parseJson(str);
                newsList.addAll(clsNewsData.parseJson(str));     // to append the new items to the listview

//                myAdapter = new NewsAdapterClass(Side_menuActivity.this, newsList);
//                lvData.setAdapter(myAdapter);
//                myAdapter.notifyDataSetChanged();


//                loadtable();



                swipeRefreshLayout.setRefreshing(false);
                progressbar.setVisibility(View.GONE);


            }
        });



    }

    private void setupmore ()
    {

        lvData.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView

                getnews(String.valueOf(page));
                myAdapter.notifyDataSetChanged();
//                lvData.setAdapter(myAdapter);
                // or customLoadMoreDataFromApi(totalItemsCount);
                return true; // ONLY if more data is actually being loaded; false otherwise.
            }
        });

    }

//    private void setupmore()
//    {
//
//        lvData.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//
//            @Override
//            public void onScrollStateChanged(AbsListView arg0, int arg1) {
//                // TODO Auto-generated method stub
//
//
//                flag = true;
//
//                if (arg1 == 5) {
//                    flag = false;
//
//                }
//
//
//            }
//
//            @Override
//            public void onScroll(AbsListView arg0, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                // TODO Auto-generated method stub
//
//                int modrow = -1;
//                if (firstVisibleItem >= 7)
//                {
//                    modrow =  firstVisibleItem % 7 ;
//                }
//                if (modrow == 0 && flag == true && stoppage == false) {
//
//
//                    flag = false;
//
//                    pageCount++;
//
//                    String page =String.valueOf(pageCount);
//
//
//                    Con.Reset();
//                    Con.AddParmmter("page",page);
//                    Con.Connect(new Connection.Result() {
//                        public void data(String str) {
//
//
//                            ArrayList<NewsDataClass> pagedata = new ArrayList<NewsDataClass>();
//                            pagedata = clsNewsData.parseJson(str);
//
//                            if (pagedata.size() > 0) {
//                                newsList.addAll(pagedata);
//                            } else {
//                                pageCount--;
//                                stoppage = true;
//                            }
//
//                            myAdapter.notifyDataSetChanged();
//
//                           // lvData.smoothScrollToPosition((pageCount * 10) + 1);
//
//                        }
//                    });
//
//                }
//
//
//
//
//            }
//
//
//        });
//
//
//
//
//
//}



    private void loadtable()
    {

        myAdapter = new NewsAdapterClass(Side_menuActivity.this, newsList);
        myAdapter.notifyDataSetChanged();
        lvData.setAdapter(myAdapter);



    }

private void cellClicked()
    {

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            int positionItem;

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                theData = newsList.get(position);

                Intent i = new Intent(Side_menuActivity.this, NewsDetails.class);
                i.putExtra("obj", theData);
                startActivity(i);

            }
        });
    }




    // End class
}
