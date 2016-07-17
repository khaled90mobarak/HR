package fortopapps.hr.checkinout;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckInOutDataClass implements Parcelable {

	private String jsonStatus ; 
	private String jsonMessage ;

	private String lastAttendance_next ;
	private String lastAttendance_type  ; 
	private String lastAttendance_time ; 
	private String lastAttendance_status ; 
	private String lastAttendance_date ;
	private String lastAttendance_location ;
	
	
	
	private String check_status ; 
	private String check_status_type ; 
	private String check_status_reasons ; 
	
	
	
	private String check_status_reasons_id ; 
	private String check_status_reasons_title ; 
	
	private String check_status_exceptionId ;
	
	private ArrayList<String> reasonId_array;
	private ArrayList<String> reasonTitle_array;
	
	
	private String ip ;
	private String network_name ;
	
	



	public CheckInOutDataClass ()
	{
		
	}
	
	
	 public CheckInOutDataClass(Parcel in) {
			// TODO Auto-generated constructor stub
			 
		 
		 jsonStatus = in.readString();
		 jsonMessage = in.readString();

		 lastAttendance_next = in.readString();
		 lastAttendance_type = in.readString();
		    	 lastAttendance_time = in.readString();
		    	 lastAttendance_status = in.readString() ; 
		    	 lastAttendance_date = in.readString();
		    	 lastAttendance_location = in.readString();
		    	 
		    	 check_status = in.readString(); 
		    	 check_status_type = in.readString();
		    	 check_status_reasons = in.readString();
		    	 check_status_reasons_id = in.readString() ;
		    	 check_status_reasons_title = in.readString() ; 
		    	 
		    	 reasonId_array =(ArrayList<String>) in.readSerializable();
		    	 reasonTitle_array = (ArrayList<String>) in.readSerializable();
		    	 
		    	 ip = in.readString();
		    	 network_name = in.readString();
		    	 
		    	 
		    	  check_status_exceptionId = in.readString() ;
					
		    	 

		}
	 
	 
	 public static final Parcelable.Creator<CheckInOutDataClass> CREATOR = 
	            new Parcelable.Creator<CheckInOutDataClass>() {
	        public CheckInOutDataClass createFromParcel(Parcel in) {
	            return new CheckInOutDataClass(in);
	        }

	        public CheckInOutDataClass[] newArray(int size) {
	            return new CheckInOutDataClass[size];
	        }
	    };

	public String getLastAttendance_next() {
		return lastAttendance_next;
	}

	public void setLastAttendance_next(String lastAttendance_next) {
		this.lastAttendance_next= lastAttendance_next;
	}

	public String getLastAttendance_type() {
		return lastAttendance_type;
	}

	public void setLastAttendance_type(String lastAttendance_type) {
		this.lastAttendance_type = lastAttendance_type;
	}

	public String getLastAttendance_time() {
		return lastAttendance_time;
	}

	public void setLastAttendance_time(String lastAttendance_time) {
		this.lastAttendance_time = lastAttendance_time;
	}

	public String getLastAttendance_status() {
		return lastAttendance_status;
	}

	public void setLastAttendance_status(String lastAttendance_status) {
		this.lastAttendance_status = lastAttendance_status;
	}

	
	
	public String getLastAttendance_date() {
		return lastAttendance_date;
	}


	public void setLastAttendance_date(String lastAttendance_date) {
		this.lastAttendance_date = lastAttendance_date;
	}


	public String getLastAttendance_location() {
		return lastAttendance_location;
	}


	public void setLastAttendance_location(String lastAttendance_location) {
		this.lastAttendance_location = lastAttendance_location;
	}
	
	
	
	public String getCheck_status() {
		return check_status;
	}

	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}

	public String getCheck_status_type() {
		return check_status_type;
	}

	public void setCheck_status_type(String check_status_type) {
		this.check_status_type = check_status_type;
	}

	public String getCheck_status_reasons() {
		return check_status_reasons;
	}

	public void setCheck_status_reasons(String check_status_reasons) {
		this.check_status_reasons = check_status_reasons;
	}

	public String getCheck_status_reasons_id() {
		return check_status_reasons_id;
	}

	public void setCheck_status_reasons_id(String check_status_reasons_id) {
		this.check_status_reasons_id = check_status_reasons_id;
	}

	public String getCheck_status_reasons_title() {
		return check_status_reasons_title;
	}

	public void setCheck_status_reasons_title(String check_status_reasons_title) {
		this.check_status_reasons_title = check_status_reasons_title;
	}

	public String getCheck_status_exceptionId() {
		return check_status_exceptionId;
	}

	public void setCheck_status_exceptionId(String check_status_exceptionId) {
		this.check_status_exceptionId = check_status_exceptionId;
	}
	
	
	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getNetwork_name() {
		return network_name;
	}


	public void setNetwork_name(String network_name) {
		this.network_name = network_name;
	}

	


	public ArrayList<String> getReasonId_array() {
		return reasonId_array;
	}


	public void setReasonId_array(ArrayList<String> reasonId_array) {
		this.reasonId_array = reasonId_array;
	}


	public ArrayList<String> getReasonTitle_array() {
		return reasonTitle_array;
	}


	public void setReasonTitle_array(ArrayList<String> reasonTitle_array) {
		this.reasonTitle_array = reasonTitle_array;
	}
	
	
	public String getJsonStatus() {
		return jsonStatus;
	}


	public void setJsonStatus(String jsonStatus) {
		this.jsonStatus = jsonStatus;
	}


	public String getJsonMessage() {
		return jsonMessage;
	}


	public void setJsonMessage(String jsonMessage) {
		this.jsonMessage = jsonMessage;
	}

	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		// TODO Auto-generated method stub
		
		out.writeString(jsonStatus);
		out.writeString(jsonMessage);
		out.writeString(lastAttendance_next);
		out.writeString(lastAttendance_type);
		out.writeString(lastAttendance_time);
		out.writeString(lastAttendance_status);
		out.writeString(lastAttendance_date);
		out.writeString(lastAttendance_location);
		
		out.writeString(check_status);
		out.writeString(check_status_type);
		out.writeString(check_status_reasons);
		out.writeString(check_status_reasons_id);
		out.writeString(check_status_reasons_title);
		out.writeString(ip);
		out.writeString(network_name);
		out.writeString(check_status_exceptionId);
		
		out.writeSerializable(reasonId_array);
		out.writeSerializable(reasonTitle_array);
	}



	// parsing the api result data
	public ArrayList<CheckInOutDataClass> parseJsonCheck(String jsonParsed) {

		String lastAttendance_next = null ;
		String lastAttendance_type = null ;
		String lastAttendance_time = null ;
		String lastAttendance_date = null ;
		String lastAttendance_status = null ;
		String lastAttendance_location = null ;
		String checkStatus_status = null ;
		String checkStatus_type = null ;

		String checkStatus_exceptionId = null ;


		ArrayList<CheckInOutDataClass> myFile = new ArrayList<CheckInOutDataClass>() ;
		CheckInOutDataClass d = new CheckInOutDataClass();


		JSONArray arrReasons ;

		ArrayList<String> arrReasons_id = null ;
		ArrayList<String> arrReasons_title = null ;


		try {



			JSONObject jobj = new JSONObject(jsonParsed) ;
//	String major_status = jobj.getString("status");

			String jsonStatus = jobj.getString("status");
			d.setJsonStatus(jsonStatus);

			if (jsonStatus.equals("success"))

			{

				if ((jobj.has("lastAttendance") ))
				{
					if ((jobj.getJSONObject("lastAttendance").getString("type") != "null")) {
						lastAttendance_type = jobj.getJSONObject("lastAttendance").getString("type");
						d.setLastAttendance_type(lastAttendance_type);
					}
					if ((jobj.getJSONObject("lastAttendance").getString("next") != "null")) {
						lastAttendance_next = jobj.getJSONObject("lastAttendance").getString("next");
						d.setLastAttendance_next(lastAttendance_next);
					}

					if ((jobj.getJSONObject("lastAttendance").getString("date") != "null")) {
						lastAttendance_date = jobj.getJSONObject("lastAttendance").getString("date");
						d.setLastAttendance_date(lastAttendance_date);
					}


					if ((jobj.getJSONObject("lastAttendance").getString("time") != "null")) {
						lastAttendance_time = jobj.getJSONObject("lastAttendance").getString("time");
						d.setLastAttendance_time(lastAttendance_time);
					}

					if ((jobj.getJSONObject("lastAttendance").getString("status") != "null")) {
						lastAttendance_status = jobj.getJSONObject("lastAttendance").getString("status");
						d.setLastAttendance_status(lastAttendance_status);
					}


					if ((jobj.getJSONObject("lastAttendance").getString("location") != "null")) {
						lastAttendance_location = jobj.getJSONObject("lastAttendance").getString("location");
						d.setLastAttendance_location(lastAttendance_location);
					}
				}


				if ((jobj.has("check_status") )) {
					if ((jobj.getJSONObject("check_status").getString("status") != "null")) {
						checkStatus_status = jobj.getJSONObject("check_status").getString("status");
						d.setCheck_status(checkStatus_status);
					}


					if (!(jobj.getJSONObject("check_status").getString("type").equals("null"))) {
						checkStatus_type = jobj.getJSONObject("check_status").getString("type");
						d.setCheck_status_type(checkStatus_type);
					}


					if ((jobj.getJSONObject("check_status").getString("exceptionId") != "null")) {
						checkStatus_exceptionId = jobj.getJSONObject("check_status").getString("exceptionId");
						d.setCheck_status_exceptionId(checkStatus_exceptionId);
					}


					if (!(jobj.getJSONObject("check_status").getJSONArray("reasons").getJSONObject(0).getString("title").equals("null"))) {
						arrReasons = jobj.getJSONObject("check_status").getJSONArray("reasons");

						for (int i = 0; i < arrReasons.length(); i++) {

							arrReasons_id = new ArrayList<String>();
							arrReasons_title = new ArrayList<String>();


							String check_status_reasons_id = jobj.getJSONObject("check_status").getJSONArray("reasons").getJSONObject(i).getString("id");


							String check_status_reasons_title = jobj.getJSONObject("check_status").getJSONArray("reasons").getJSONObject(i).getString("title");


							arrReasons_id.add(check_status_reasons_id);
							arrReasons_title.add(check_status_reasons_title);


						}

						d.setReasonId_array(arrReasons_id);
						d.setReasonTitle_array(arrReasons_title);

						d.setJsonStatus(jsonStatus);
					}
				}

				myFile.add(d);

			}

			else if (jsonStatus.equals("failed"))
			{
				d.setJsonStatus(jsonStatus);

				String jsonMessage = jobj.getString("message");
				d.setJsonMessage(jsonMessage);
				myFile.add(d);
			}

		}

		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return myFile ;
	}



}
