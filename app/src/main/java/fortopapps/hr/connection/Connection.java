package fortopapps.hr.connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Connection {



    Handler mainHandler ;

    public static final MediaType JSON  = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    FormEncodingBuilder formBody ;
    Request requesturl;

    ProgressDialog loading ;
    private Context _context;
    private String url;
    private String Cashurl;

    public Boolean UseCash = true;



    public Connection(Context context ,String URL)
    {
        url = URL;
        this._context = context;
        mainHandler = new Handler(_context.getMainLooper());
        setuploading();
        Reset();
    }

// ===================== Deleage Method ==============================================
    public interface Result {
        public void data(String str);
    }

    private Result DelegateMethod;


    public void Connect(Result dlg){
        DelegateMethod = dlg;

        ConnectionDetector testConnection = new ConnectionDetector(_context);
        if (testConnection.isConnectingToInternet())
        {
//            ReadyToConnect();
            ReadFromServer();

        }
        else
        {
            Toast.makeText(_context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }


    }

    void ReadyToConnect()
    {
        Boolean notuseCash =false;

        notuseCash = isTimeToUpdate();



        if (notuseCash == true) {
            ReadFromServer();
        }
        else
        {
            String json=   ReadFromLocal();

            if (json.equals(""))
            {
                ReadFromServer();
            }
            else {
                executeNow(json);
            }

        }
    }

    public void executeNow(String str){
        DelegateMethod.data(str);
    }
// ===================== End Deleage Method ==============================================


    private void  setuploading()
    {
        loading = new ProgressDialog(_context);


        loading.setMessage("loading ...");


    }

    public void Reset()
    {
        formBody = new FormEncodingBuilder();
        Cashurl = url;
    }
        public void AddParmmter(String key, String value) {

        Cashurl = Cashurl + "&" + key + "&" + value;

        formBody.add(key, value);

    }

    private Boolean isTimeToUpdate()
    {


        if (UseCash == false)
        {
            return true;
        }
        else
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date dtNow = new Date();
            String currentDateandTime = dateFormat.format(dtNow);
            String LastTime = GetLastTime();
            int cashtime =  GetCashTime()   ;
            if (LastTime.equals(""))
            {

                SetLastTime(currentDateandTime);
                return true;
            }
            else
            {

                Date LastDate = null;
                try {
                    LastDate = dateFormat.parse(LastTime);
                    long minutes = getTimeDifference(dtNow ,LastDate );

                    if (minutes > cashtime)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();

                    SetLastTime(currentDateandTime);
                    return true;

                }

            }


        }

    }

    private void ReadFromServer()
    {

        loading.show();

        RequestBody body = formBody.build();
        requesturl = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(requesturl).enqueue(new Callback() {


            String json;

            @Override
            public void onFailure(Request request, IOException e) {
                loading.dismiss();


                    String json=   ReadFromLocal( );
                    executeNow(json);


            }

            @Override
            public void onResponse(Response response) throws IOException {
                loading.dismiss();

                json = response.body().string();
                SaveForCash(json);

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        executeNow(json);
                    }
                });


            }
        });


    }


    public void uploadImage(Result dlg  , String sourceImageFile,String mobile_token)
    {
        ConnectionDetector testConnection = new ConnectionDetector(_context);
        if (!testConnection.isConnectingToInternet())
        {
            Toast.makeText(_context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
        else
        {

        DelegateMethod = dlg;

        loading.show();


        File sourceFile = new File(sourceImageFile);
        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");



        RequestBody body = new MultipartBuilder()
                .type(MultipartBuilder.FORM)
                .addFormDataPart("mobile_token", mobile_token)
                .addFormDataPart("image_ext", "png")
                .addFormDataPart("avatar", "avatar.png", RequestBody.create(MEDIA_TYPE_PNG, sourceFile))
                .build();


        requesturl = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        client.newCall(requesturl).enqueue(new Callback() {


            String json;

            @Override
            public void onFailure(Request request, IOException e) {
                loading.dismiss();

                executeNow(json);


            }

            @Override
            public void onResponse(Response response) throws IOException {
                loading.dismiss();

                json = response.body().string();

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        executeNow(json);
                    }
                });


            }
        });


        }

  }


    private String ReadFromLocal( )
    {
        if (UseCash == true) {
            SharedPreferences pref = _context.getSharedPreferences("Cash", _context.MODE_PRIVATE);
            return pref.getString(Cashurl, "");
        }
        else
        {
            return "";
        }
    }

    private String GetLastTime( )
    {
        SharedPreferences pref = _context.getSharedPreferences("Cash", _context.MODE_PRIVATE);
        return   pref.getString("LastTime", "")  ;
    }
    private void SetLastTime(String dt )
    {
        SharedPreferences pref = _context.getSharedPreferences("Cash", _context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("LastTime", dt);
        editor.commit();
    }

    public int GetCashTime( )
    {
        SharedPreferences pref = _context.getSharedPreferences("Cash", _context.MODE_PRIVATE);
        return   pref.getInt("CashTime", 0)  ;
    }
    public   void SetCashTime(int minute )
    {
        SharedPreferences pref = _context.getSharedPreferences("Cash", _context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("CashTime", minute);
        editor.commit();
    }


    private void SaveForCash( String json)
    {
        if (UseCash == true) {
            SharedPreferences pref = _context.getSharedPreferences("Cash", _context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(Cashurl, json);
            editor.commit();
        }

    }

    public void ClearCash()
    {
        SharedPreferences settings = _context.getSharedPreferences("Cash", _context.MODE_PRIVATE);
        settings.edit().clear().commit();
    }

    public static long getTimeDifference(Date d2,Date d1 ) {


        //milliseconds
        long different = d2.getTime() - d1.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        /*
        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;
*/

        long elapsedMinutes = different / minutesInMilli;
      //  different = different % minutesInMilli;

        // long elapsedSeconds = different / secondsInMilli;



         return elapsedMinutes;

    }

    /*
    public String postData(String url ) throws IOException {


       loading.show() ;

        formBody.add("page", "1");
        RequestBody body = formBody.build();
        requesturl = new Request.Builder()
                .url(url)
                .post(body)
                .build();



        Response response = null;
        try {
            response = client.newCall(requesturl).execute();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        return response.body().string();


    }

    String post(String url) throws IOException {
        // RequestBody body = RequestBody.create(JSON, json);
        RequestBody body = formBody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
       Response response = client.newCall(request).execute();


        return response.body().string();
    }
*/






}
