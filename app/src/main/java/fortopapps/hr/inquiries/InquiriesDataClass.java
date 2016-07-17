package fortopapps.hr.inquiries;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InquiriesDataClass implements Parcelable{   // i used parcelable for be able to transfer data from activity to another

	private String total  ; 
	private String per_page ; 
	private String current_page ; 
	private String last_page ; 
	private String next_page_url ; 
	private String prev_page_url ; 
	private String from_offset ; 
	private String to_offset ; 
	
	

	private String id ; 
	private String title ; 
	private String message ; 
	private String entry_date ; 
	private String id_from; 
	private String emp_from ;
	private String id_to ;
	private String emp_to ; 
	private String status ; 
	private String parent ;
	private String department_id ;
	private String department_name ;
	private String replay_count ;
	
	
	
	public InquiriesDataClass ()
	{
		
	}
	

	
	
	 public InquiriesDataClass(Parcel in) {
			// TODO Auto-generated constructor stub
			 
		         total = in.readString(); 
		    	 

		    	 per_page = in.readString();
		    	 current_page = in.readString() ; 
		    	 last_page = in.readString(); 
		    	 next_page_url = in.readString();
		    	 prev_page_url = in.readString();
		    	 from_offset = in.readString() ;
		    	 to_offset = in.readString() ; 
		    	 

		    	 
		    	  id = in.readString() ;
					title = in.readString() ;
					 message = in.readString() ;
					entry_date =   in.readString() ;
					id_from = in.readString();
					id_to = in.readString();
					emp_from = in.readString() ;
					emp_to= in.readString() ;
					status = in.readString() ;
					parent = in.readString(); 
					department_id = in.readString() ;
					replay_count = in.readString();
		    	 

		}
	 
	 
	 public static final Parcelable.Creator<InquiriesDataClass> CREATOR = 
	            new Parcelable.Creator<InquiriesDataClass>() {
	        public InquiriesDataClass createFromParcel(Parcel in) {
	            return new InquiriesDataClass(in);
	        }

	        public InquiriesDataClass[] newArray(int size) {
	            return new InquiriesDataClass[size];
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
		out.writeString(total);
		out.writeString(per_page);
		out.writeString(current_page);
		out.writeString(last_page);
		out.writeString(next_page_url);
		out.writeString(prev_page_url);
		out.writeString(id_from);
		out.writeString(to_offset);
		

		
		
		out.writeString(id);
		out.writeString(title);
		out.writeString(message);
		out.writeString(entry_date);
		out.writeString(id_from);
		out.writeString(id_to);
		out.writeString(emp_from);
		out.writeString(emp_to);
		out.writeString(status);
		out.writeString(parent);
		out.writeString(department_id);
		out.writeString(replay_count);
		
	}


	// getters and setters

	public String getTotal() {
		return total;
	}



	public void setTotal(String total) {
		this.total = total;
	}



	public String getPer_page() {
		return per_page;
	}



	public void setPer_page(String per_page) {
		this.per_page = per_page;
	}



	public String getCurrent_page() {
		return current_page;
	}



	public void setCurrent_page(String current_page) {
		this.current_page = current_page;
	}



	public String getLast_page() {
		return last_page;
	}



	public void setLast_page(String last_page) {
		this.last_page = last_page;
	}



	public String getNext_page_url() {
		return next_page_url;
	}



	public void setNext_page_url(String next_page_url) {
		this.next_page_url = next_page_url;
	}



	public String getPrev_page_url() {
		return prev_page_url;
	}



	public void setPrev_page_url(String prev_page_url) {
		this.prev_page_url = prev_page_url;
	}



	public String getFromOffset() {
		return from_offset;
	}



	public void setFromOffset(String from_offset) {
		this.from_offset = from_offset;
	}



	public String getToOffset() {
		return to_offset;
	}



	public void setToOffset(String to) {
		this.to_offset = to;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getEntryDate() {
		return entry_date;
	}



	public void setEntryDate(String entry_date) {
		this.entry_date = entry_date;
	}



	public String getIdFrom() {
		return id_from;
	}



	public void setIdFrom(String id_from) {
		this.id_from = id_from;
	}



	public String getEmpFrom() {
		return emp_from;
	}



	public void setEmpFrom(String emp_from) {
		this.emp_from = emp_from;
	}



	



	public String getEmpTo() {
		return emp_to;
	}



	public void setEmpTo(String emp_to) {
		this.emp_to = emp_to;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdTo() {
		return id_to;
	}

	public void setIdTo(String id_to) {
		this.id_to = id_to;
	}

	public String getDepartmentId() {
		return department_id;
	}

	public String getDepartmentname() {
		return department_name;
	}
	public void setDepartmentId(String department_id) {
		this.department_id = department_id;
	}
	public void setDepartmentname(String department_name) {
		this.department_name = department_name;
	}
//	public ArrayList<String> getDetails() {
//		return details;
//	}
//
//	public void setDetails(ArrayList<String> details) {
//		this.details = details;
//	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getReplay_count() {
		return replay_count;
	}

	public void setReplay_count(String replay_count) {
		this.replay_count = replay_count;
	}



	public ArrayList<InquiriesDataClass> parseJson(String jsonParsed) {


		ArrayList<InquiriesDataClass> myData = new ArrayList<InquiriesDataClass>() ;


		try {



			JSONObject jobj = new JSONObject(jsonParsed) ;
			String total = jobj.getString("total");
			String current_page = jobj.getString("current_page");
			String last_page = jobj.getString("last_page");
			String next_page_url = jobj.getString("next_page_url");
			String prev_page_url = jobj.getString("prev_page_url");
			String from_offset = jobj.getString("from");
			String to_offset = jobj.getString("to");


			JSONArray jData = jobj.getJSONArray("data");


			String per_page = jobj.getString("per_page");
			Integer inquiries_per_page = Integer.parseInt(per_page);

			JSONObject jParameters ;

			for (int i = 0 ; i<inquiries_per_page ; i++)
			{

				InquiriesDataClass d = new InquiriesDataClass() ;

				jParameters = jData.getJSONObject(i);



				String id = jParameters.getString("id");
				String title = jParameters.getString("title");
				String message = jParameters.getString("message");
				String entry_date = jParameters.getString("entry_date");
				String id_from = jParameters.getString("from");
				String emp_from = jParameters.getString("emp_from");
				String id_to = jParameters.getString("to");
				String emp_to = jParameters.getString("emp_to");
				String status = jParameters.getString("status");
				String parent = jParameters.getString("parent");
				String department_id = jParameters.getString("department_id");

				String replay_count = jParameters.getString("replay_count");


				d.setId(id);
				d.setTitle(title);
				d.setMessage(message);
				d.setEntryDate(entry_date);
				d.setIdFrom(id_from);
				d.setEmpFrom(emp_from);
				d.setIdTo(id_to);
				d.setEmpTo(emp_to);
				d.setStatus(status);
				d.setParent(parent);
				d.setDepartmentId(department_id);

				d.setReplay_count(replay_count);


				//    paging information
				d.setPer_page(per_page);
				d.setTotal(total);
				d.setCurrent_page(current_page);
				d.setLast_page(last_page);
				d.setNext_page_url(next_page_url);
				d.setPrev_page_url(prev_page_url);
				d.setFromOffset(from_offset);
				d.setToOffset(to_offset);




				myData.add(d);
			}

		}

		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return myData ;
	}


	// parsing replies of admin
	public ArrayList<InquiriesDataClass> parseJsonreplies(String jsonParsed) {



		ArrayList<InquiriesDataClass> myDataReplies = new ArrayList<InquiriesDataClass>() ;



		try {

			JSONObject jobj = new JSONObject(jsonParsed) ;


			JSONArray jReplies = jobj.getJSONArray("replies");
			JSONObject jRepliesParameters ;

			for (int i=0; i<jReplies.length() ;i++)
			{

				InquiriesDataClass dReplies = new InquiriesDataClass() ;
				jRepliesParameters = jReplies.getJSONObject(i);



				String idReplies = jRepliesParameters.getString("id");
				String titleReplies = jRepliesParameters.getString("title");
				String messageReplies = jRepliesParameters.getString("message");
				String entry_dateReplies = jRepliesParameters.getString("entry_date");
				String id_from = jRepliesParameters.getString("from");
				String emp_fromReplies = jRepliesParameters.getString("emp_from");
				String id_to = jRepliesParameters.getString("to");
				String emp_toReplies = jRepliesParameters.getString("emp_to");
				String statusReplies = jRepliesParameters.getString("status");
				String parentReplies = jRepliesParameters.getString("parent");
				String department_idReplies = jRepliesParameters.getString("department_id");

				String replay_countReplies = jRepliesParameters.getString("replay_count");



				dReplies.setId(idReplies);
				dReplies.setTitle(titleReplies);
				dReplies.setMessage(messageReplies);
				dReplies.setEntryDate(entry_dateReplies);
				dReplies.setIdFrom(id_from);
				dReplies.setEmpFrom(emp_fromReplies);
				dReplies.setIdTo(id_to);
				dReplies.setEmpTo(emp_toReplies);
				dReplies.setStatus(statusReplies);
				dReplies.setParent(parentReplies);
				dReplies.setDepartmentId(department_idReplies);

				dReplies.setReplay_count(replay_countReplies);


				myDataReplies.add(dReplies);

			}


		}

		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return myDataReplies ;
	}

}
