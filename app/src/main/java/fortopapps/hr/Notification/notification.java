package fortopapps.hr.Notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import fortopapps.hr.connection.ConnectionDetector;

/**
 * Created by khaled on 01/01/2016.
 */
public class notification {

    // Resgistration Id from GCM
    private static final String PREF_GCM_REG_ID = "PREF_GCM_REG_ID";
    private SharedPreferences prefs;
    // Your project number and web server url.
    private static final String GCM_SENDER_ID = "86902214460";

    GoogleCloudMessaging gcm;

    private static final int ACTION_PLAY_SERVICES_DIALOG = 100;
    protected static final int MSG_REGISTER_WITH_GCM = 101;

    private String gcmRegId;


  // ===================== Constarctor ==============================================

     private AppCompatActivity _app;

    public notification( AppCompatActivity  app )
    {

        _app  = app;
    }

    // ===================== Deleage Method ==============================================
    public interface Result {
        public void token(String devicetoken);
    }

    private Result DelegateMethod;


    public void GetToken(Result dlg){
        DelegateMethod = dlg;

// Check device for Play Services APK.
        if (isGoogelPlayInstalled()) {
            gcm = GoogleCloudMessaging.getInstance(_app);

            // Read saved registration id from shared preferences.
            gcmRegId = getSharedPreferences().getString(PREF_GCM_REG_ID, "");

            if (TextUtils.isEmpty(gcmRegId)) {
                handler.sendEmptyMessage(MSG_REGISTER_WITH_GCM);
            }else{

                executeNow(gcmRegId);
                //Toast.makeText(_context, "Already registered with GCM", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void executeNow(String devicetoken){
        DelegateMethod.token(devicetoken);
    }
// ===================== End Deleage Method ==============================================

    private boolean isGoogelPlayInstalled() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(_app);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, _app,
                        ACTION_PLAY_SERVICES_DIALOG).show();
            } else {
                Toast.makeText(_app,
                        "Google Play Service is not installed",
                        Toast.LENGTH_SHORT).show();

            }
            return false;
        }
        return true;

    }


    private SharedPreferences getSharedPreferences() {
        if (prefs == null) {
            prefs = _app.getSharedPreferences(
                    "DeviceToken", Context.MODE_PRIVATE);
        }
        return prefs;
    }

    public static String GetSavedDeviceToken(Context context )
    {
        SharedPreferences prefs = context.getSharedPreferences("DeviceToken", Context.MODE_PRIVATE);
      //  return  prefs.getString(PREF_GCM_REG_ID, "");
        return "358021058398292";
    }

    public void saveInSharedPref(String result) {
        // TODO Auto-generated method stub
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(PREF_GCM_REG_ID, result);
        editor.commit();
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_WITH_GCM:
                    new GCMRegistrationTask().execute();
                    break;
            }
        };
    };


    private class GCMRegistrationTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            if (gcm == null && isGoogelPlayInstalled()) {
                gcm = GoogleCloudMessaging.getInstance(_app);
            }
            try {
                gcmRegId = gcm.register(GCM_SENDER_ID);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return gcmRegId;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
              //  Toast.makeText(_context, "registered with GCM",Toast.LENGTH_LONG).show();
                saveInSharedPref(result);
                executeNow(gcmRegId);
            }
            else
            {
                executeNow("");
            }
        }

    }
}
