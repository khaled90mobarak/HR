package fortopapps.hr.user;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import fortopapps.hr.BitmapProcessor;
import fortopapps.hr.Notification.notification;
import fortopapps.hr.R;
import fortopapps.hr.connection.Connection;
import fortopapps.hr.connection.parsejson;
import it.sephiroth.android.library.picasso.Picasso;

public class profile extends AppCompatActivity {
    TextView fullname,email,mobile;
    ImageButton  imguser,btncash;
    Button btnchangeusername,btnresetps , btnclearcash;

    Connection con  ;

 //#############################
    // Select Photo
    private static final int IMAGE_PICK 	= 1;
    private static final int IMAGE_CAPTURE 	= 2;
    private Uri mCapturedImageURI;
    String imgpath;
    Bitmap imgselected;
 //#############################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        setTitle("Profile");

           fullname = (TextView) findViewById(R.id.p_txtfullname);
            email = (TextView) findViewById(R.id.p_txtemail);
            mobile = (TextView) findViewById(R.id.p_txtmobile);
           imguser = (ImageButton) findViewById(R.id.p_btnimguser);
        imguser.setOnClickListener(selectimg);



//        con = new Connection(this,"http://104.236.218.198/api/avatar");

        con = new Connection(this,"http://maridive.lis-app.com/api/avatar");
        Loaduserinfo();
        changeusername();
        Resetps();
        ClearCash();
        useCash();


    }

    private OnClickListener selectimg = new OnClickListener() {

        @Override
        public void onClick(final View arg0) {

            // TODO Auto-generated method stub
            final AlertDialog  alertDialog = new AlertDialog.Builder(arg0.getContext()).create();

            alertDialog.setTitle("صورة");

            alertDialog.setMessage("التقاط صورة من");

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "من الالبوم", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {

                    ImagePickListener x = new ImagePickListener();
                    x.onClick(arg0 );
                    //...




                } });

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "من الكاميرا", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {

                    TakePictureListener x = new TakePictureListener();
                    x.onClick(arg0 );
                    //...

                }});

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "الغاء" , new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int id) {

                    alertDialog.dismiss();
                    //...

                }});

            alertDialog.show();



        }



    };

    private void changeusername()
    {
        btnchangeusername = (Button) findViewById(R.id.p_btnchangeusername);
        btnchangeusername.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(profile.this, changeusername.class);
                startActivity(i);

            }
        });
    }

    private void Resetps()
    {
        btnresetps = (Button) findViewById(R.id.p_btnresestps);
        btnresetps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(profile.this, resetpassword.class);
                startActivity(i);

            }
        });

    }

    private void ClearCash()
    {
        btnclearcash = (Button) findViewById(R.id.p_btnclearcash);
        btnclearcash.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ClearCash();
                Toast.makeText(profile.this, "done", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void useCash()
    {
        btncash = (ImageButton) findViewById(R.id.btncash);


        int time =  con.GetCashTime();

        if (time == -1)  // Disable
        {
            btncash.setImageResource(R.drawable.cashoff);
        }
        else     // Enable
        {
            btncash.setImageResource(R.drawable.cashon);
        }

        btncash.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                int time = con.GetCashTime();

                if (time == -1)  // Set To Eanble
                {
                    btncash.setImageResource(R.drawable.cashon);
                    con.SetCashTime(24 * 60);
                } else     // Set To disable
                {
                    btncash.setImageResource(R.drawable.cashoff);
                    con.SetCashTime(-1);
                }

            }
        });


    }


    private void Loaduserinfo() {


        SharedPreferences pref = getSharedPreferences("prefs_credentials", MODE_PRIVATE);

        email.setText(pref.getString("email", ""));
        mobile.setText(pref.getString("phone", ""));
        fullname.setText(pref.getString("fname", "") + " " + pref.getString("lname", ""));

        String avatar = pref.getString("avatar", "");

        avatar =  avatar;

        Picasso.with(this).load(avatar).into(imguser);
    }

    // ############################################################################
    /**
     * Receive the result from the startActivity
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_PICK:
                    this.imageFromGallery(resultCode, data);
                    break;
                case IMAGE_CAPTURE:



                    this.imageFromCamera(resultCode, data);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Update the imageView with new bitmap
     * @param newImage
     */
    private void updateImageView(Bitmap newImage) {


            imgselected = newImage;

        BitmapProcessor bitmapProcessor = new BitmapProcessor(newImage, 50, 50, 0);

       	imguser.setImageBitmap(bitmapProcessor.getBitmap());



        con.uploadImage(new Connection.Result() {
            public void data(String str) {



                    parsejson json = new parsejson();
                    Map reslut = json.GetDic(str);

                   // JSONObject  jobj = new JSONObject(str);

                    String status = reslut.get("status").toString();


                    if (status.equals("success")) {

                     String responsestr =  reslut.get("response").toString();
                      Map response = json.GetDic(responsestr);


                        String filePath = response.get("filePath").toString();

                        SharedPreferences prefs = getSharedPreferences("prefs_credentials", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.putString("avatar", filePath);



                        editor.commit();


                    }  else  {

                        String message = reslut.get("message").toString();

                    }


            }
        }, imgpath,  notification.GetSavedDeviceToken(this) );


    }

    /**
     * Image result from camera
     * @param resultCode
     * @param data
     */
    private void imageFromCamera(int resultCode, Intent data) {


            imgpath = getRealPathFromURI(mCapturedImageURI);



        ContentResolver cr = getContentResolver();
        Bitmap bitmap;
        try {
            bitmap = android.provider.MediaStore.Images.Media
                    .getBitmap(cr, mCapturedImageURI);



            // imageView.setImageBitmap(bitmap);

            this.updateImageView(bitmap);

        } catch (Exception e) {


        }
        //this.updateImageView((Bitmap) data.getExtras().get("data"));
    }

    /**
     * Image result from gallery
     * @param resultCode
     * @param data
     */
    private void imageFromGallery(int resultCode, Intent data) {
        Uri selectedImage = data.getData();
        String [] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();


            imgpath = filePath ;



        this.updateImageView(BitmapFactory.decodeFile(filePath));
    }


    /**
     * Click Listener for selecting images from phone gallery
     * @author tscolari
     *
     */
    class ImagePickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Escolha uma Foto"), IMAGE_PICK);

        }
    }

    /**
     * Click listener for taking new picture
     * @author tscolari
     *
     */
    class TakePictureListener implements OnClickListener {
        static final int CAMERA_PIC_REQUEST = 1337;
        @Override
        public void onClick(View v) {
// 			Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
// 		  startActivityForResult(intent, IMAGE_CAPTURE);

            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "t");
            mCapturedImageURI = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            Intent intentPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intentPicture.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
            startActivityForResult(intentPicture,IMAGE_CAPTURE);


        }
    }

    //----------------------------------------
    /**
     * This method is used to get real path of file from from uri
     *
     * @param contentUri
     * @return String
     */
    //----------------------------------------
    public String getRealPathFromURI(Uri contentUri)
    {
        try
        {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        catch (Exception e)
        {
            return contentUri.getPath();
        }
    }

    ///////////////////////////// testing image

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }



    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

///////////////////////////////////



    // other way to resize
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

}
