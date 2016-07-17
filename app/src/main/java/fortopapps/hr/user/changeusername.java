package fortopapps.hr.user;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;

public class changeusername extends AppCompatActivity {

    EditText username;
    Button btnchange;
    TextView msg;

//    String urlFull = "http://104.236.218.198/api/username";

    String urlFull = "http://maridive.lis-app.com/api/username";


    Connection Con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_changeusername);
        setTitle("Change username");


        username = (EditText) findViewById(R.id.c_username);
        msg = (TextView) findViewById(R.id.c_msg);
        btnchange = (Button) findViewById(R.id.c_btnchange);
        btnchange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (username.getText().toString().equals("")) {
                    msg.setTextColor(Color.RED);
                    msg.setText("Please enter new username .");

                } else {
                    postLoginData();
                }

            }
        });

    }


    private void postLoginData()
    {

        Con = new Connection(this,urlFull);
        Con.UseCash =false;
        Con.AddParmmter("username", username.getText().toString());
        Con.AddParmmter("mobile_token", notification.GetSavedDeviceToken(this));
        Con.Connect(new Connection.Result() {
            public void data(String str) {


                try {

                    JSONObject  jobj = new JSONObject(str);

                    String status = jobj.getString("status");


                    if (status.equals("success"))
                    {

                        msg.setText("Username changed successfully .");
                        msg.setTextColor(Color.GREEN  );

                        SharedPreferences prefs = getSharedPreferences("prefs_credentials", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("username", username.getText().toString());
                        editor.commit();
                        // finish();
                    }
                    else if (status.equals("failed"))
                    {
                        String message = jobj.getString("message");
                        msg.setText(message);
                        msg.setTextColor(Color.RED);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        });


    }



}
