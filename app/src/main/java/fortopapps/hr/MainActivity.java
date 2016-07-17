package fortopapps.hr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.connection.Connection;
import fortopapps.hr.news.Side_menuActivity;
import fortopapps.hr.user.resetpassword;

//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;

public class MainActivity extends AppCompatActivity {

    private ImageButton  btnLogin , btnForgetPassword  ;

    private EditText etUsername, etPassword ;

    TextView tvMessage ;
    Connection con;

    String   mUsername ;
    String  mPassword ;
    String  mMobile_token ;

    String  status ;


    UserDataClass clsuer ;


//    String urlFull = "http://104.236.218.198/api/auth/login";

    String urlFull = "http://maridive.lis-app.com/api/auth/login";

    Connection Con  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);


        // khaled mobarak
        getSupportActionBar().hide();


        setContentView(R.layout.activity_main);





//        notification notif = new notification(this);
//        notif.GetToken(new notification.Result() {
//            public void token(String devicetoken) {
//
//            }
//        });


        // Cash Setting
        Con = new Connection(this,urlFull);
        if (Con.GetCashTime() == 0)
        {
            Con.SetCashTime(24 *60);
        }



        btnLogin = (ImageButton) findViewById(R.id.imgbtn_logIn);


        btnForgetPassword = (ImageButton) findViewById(R.id.imgbtn_forgetPassword);


        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);



       getSharedPreferences();
        if ((mUsername.length()>0) & (mPassword.length()>0))
        {
            Intent i = new Intent(MainActivity.this, Side_menuActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);

        }


        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                mUsername = etUsername.getText().toString();
                mPassword = etPassword.getText().toString();




                if (!(etUsername.getText().toString().trim().equals(""))
                        &&!(etPassword.getText().toString().trim().equals("")))

                {



                       postLoginData();

                }



                else if (etUsername.getText().toString().equals("")||etPassword.getText().toString().equals(""))

                {
                    Toast.makeText(MainActivity.this, "please enter all data !", Toast.LENGTH_SHORT).show();
                }


                else
                {
                    Toast.makeText(MainActivity.this, "Please enter valid data !", Toast.LENGTH_SHORT).show();
                }

            }

        });







        btnForgetPassword.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Intent i = new Intent(MainActivity.this, resetpassword.class);
                startActivity(i);


            }
        });


        btnLogin.setOnTouchListener(new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color


                    v.getBackground().setColorFilter(Color.parseColor("#DF0101"), PorterDuff.Mode.MULTIPLY);

                    v.invalidate();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    // set to normal color

                    v.getBackground().clearColorFilter();
                    v.invalidate();
                }

                return false;
            }
        });


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    // to save the username , password and user data  to use them in profile screen and use it for remember me
    public void saveToSharedPreference ()
    {

        SharedPreferences prefs = getSharedPreferences("prefs_credentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("username", mUsername);
        editor.putString("password", mPassword);
        editor.putString("mobile_token", mMobile_token);

        editor.putString("id", clsuer.getId().toString());
        editor.putString("fname", clsuer.getFname().toString());
        editor.putString("lname", clsuer.getLname().toString());
        editor.putString("phone",clsuer.getPhone().toString());
        editor.putString("job", clsuer.getJob().toString());
        editor.putString("date_of_birth", clsuer.getDate_of_birth().toString());
        editor.putString("email", clsuer.getEmail().toString());

        String avatar =  clsuer.getAvtar_path().toString() +  clsuer.getAvatar().toString();
        editor.putString("avatar", avatar);
      //  editor.putString("avtar_path", clsuer.getAvtar_path().toString());


        editor.commit();

    }



    public void getSharedPreferences ()
    {
        SharedPreferences pref = getSharedPreferences("prefs_credentials", MODE_PRIVATE);
        mUsername = pref.getString("username", "");
        mPassword = pref.getString("password", "");

    }




    private void postLoginData()
    {

        Con = new Connection(this,urlFull);
        Con.UseCash =false;
        Con.AddParmmter("username", etUsername.getText().toString());
        Con.AddParmmter("password", etPassword.getText().toString());
        Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
        Con.AddParmmter("mobile_type", "android");
        Con.Connect(new Connection.Result() {
            public void data(String str) {

                try {

                    JSONObject jobj = new JSONObject(str);

                    String status = jobj.getString("status");


                    if (status.equals("success")) {
                        clsuer = new UserDataClass();

                        clsuer = clsuer.parseJson(str);

                        saveToSharedPreference();

                        Intent i = new Intent(MainActivity.this, Side_menuActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);

                        // finish();
                    } else if (status.equals("failed")) {
                        String message = jobj.getString("message");

                        Toast.makeText(MainActivity.this, status + "\n" + message, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



    }


}

