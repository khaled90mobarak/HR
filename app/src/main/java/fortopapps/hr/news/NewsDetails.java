package fortopapps.hr.news;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import fortopapps.hr.R;
import it.sephiroth.android.library.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {

    WebView tvNewsDetails_description;
    TextView tvNewsDetails_name;
    TextView tvNewsDetails_date;
    TextView lblcat;
    ImageView imgNewsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("News Details");

        tvNewsDetails_description = (WebView) findViewById(R.id.webView);
        tvNewsDetails_name = (TextView) findViewById(R.id.tvnewsDetails_name);
        tvNewsDetails_date = (TextView) findViewById(R.id.tvNewsDetails_date);
        lblcat = (TextView) findViewById(R.id.lblcat);

        imgNewsDetails = (ImageView) findViewById(R.id.img_default_newsDetails);

        Bundle b = getIntent().getExtras();
        NewsDataClass d = b.getParcelable("obj");


        lblcat.setText(d.getCategory());
        tvNewsDetails_name.setText(d.getName());
        tvNewsDetails_description.loadData(d.getDescription(), "text/html; charset=UTF-8", null);
        tvNewsDetails_date.setText(d.getStart_date());

        String photo = d.getImage();
        if (!photo.equals("null")) {
            String image = d.getImage_url() + d.getImage();
            Picasso.with(NewsDetails.this).load(image).into(imgNewsDetails);

            imgNewsDetails.setVisibility(View.VISIBLE);
        } else {
//            android.view.ViewGroup.LayoutParams layoutParams = imgNewsDetails.getLayoutParams();
//            layoutParams.width =  0;
//            layoutParams.height = 0;
//            imgNewsDetails.setLayoutParams(layoutParams);

            imgNewsDetails.setVisibility(View.GONE);
        }


    }



        public boolean onOptionsItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case android.R.id.home :
                    NavUtils.navigateUpFromSameTask(this);
            }

            return true ;
        }


//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//        NewsDetails.this.finish();
//    }


}