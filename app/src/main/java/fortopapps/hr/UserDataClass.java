package fortopapps.hr;

import android.os.Parcel;           
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UserDataClass implements Parcelable {
	
	private String status ; 
	private String id ; 
	private String fname ; 
	private String lname ; 
	private String phone ; 

	private String job ; 
	private String date_of_birth ; 
	private String avatar ; 
	private String avtar_path ;
    private String errorMessage ; 
	
	
	



	public UserDataClass ()
	{
		
	}
	
	
	
	 public UserDataClass(Parcel in) {
		 
			// TODO Auto-generated constructor stub
			 
		 status = in.readString();
	id = in.readString();
	fname = in.readString();
	lname = in.readString();
	phone = in.readString();
	errorMessage = in.readString();
	job = in.readString();
	date_of_birth = in.readString();
	email = in.readString();
	avatar = in.readString();
	avtar_path = in.readString();

		}
	 
	 
	 
	 public static final Parcelable.Creator<UserDataClass> CREATOR = 
	            new Parcelable.Creator<UserDataClass>() {
	        public UserDataClass createFromParcel(Parcel in) {
	            return new UserDataClass(in);
	        }

	        public UserDataClass[] newArray(int size) {
	            return new UserDataClass[size];
	        }
	    };

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		
		out.writeString(status);
		out.writeString(id);
		out.writeString(fname);
		out.writeString(lname);
		out.writeString(phone);
		out.writeString(errorMessage);
		out.writeString(job);
		out.writeString(date_of_birth);
		out.writeString(email);
		out.writeString(avatar);
		out.writeString(avtar_path);
		
	}
	
	
	
	// getters and setters
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getJob() {
		return job;
	}



	public void setJob(String job) {
		this.job = job;
	}



	public String getDate_of_birth() {
		return date_of_birth;
	}



	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	 
	private String email ; 
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getAvatar() {
		return avatar;
	}



	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	public String getAvtar_path() {
		return avtar_path;
	}



	public void setAvtar_path(String avtar_path) {
		this.avtar_path = avtar_path;
	}

	
	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}




 UserDataClass  parseJson(String jsonParsed)
	{


		UserDataClass d = new UserDataClass();

		String message = "";



		try {


			JSONObject objData = new JSONObject(jsonParsed);

			String status = objData.getString("status");



			if (objData.getString("status").equals("success"))
			{
				JSONObject objUser = objData.getJSONObject("user");
				message = "";

				d.setStatus(status);
				d.setId(objUser.getString("id")) ;
				d.setFname(objUser.getString("fname"));
				d.setLname(objUser.getString("lname"));
				d.setPhone(objUser.getString("phone"));
				d.setJob(objUser.getString("job"));
				d.setDate_of_birth(objUser.getString("date_of_birth"));
				d.setEmail(objUser.getString("email"));
				d.setAvatar(objUser.getString("avatar"));
				d.setAvtar_path(objData.getString("avtar_path"));



			}

			else if (objData.getString("status").equals("failed"))

			{

				message = objData.getString("message");
				d.setStatus(status);
				d.setErrorMessage(message);


			}


		}

		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return d;

	}



}
