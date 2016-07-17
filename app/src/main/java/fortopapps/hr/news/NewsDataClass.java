package fortopapps.hr.news;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NewsDataClass implements Parcelable { // i used parcelable for be able to transfer data from activity to another

	private String total  ; 
	private String per_page ; 
	private String current_page ; 
	private String last_page ; 
	private String next_page_url ; 
	private String prev_page_url ; 
	private String from ; 
	private String to ; 
	             
	
	private String id ; 
	private String name ; 
	private String description ; 
	private String image ; 
	private String publish ; 
	private String entry_user ;
	private String created_at ;


	private String end_date ;
	private String category_id ; 



	private String deleted_at ;

	private String start_date ;

	private String category ; 
	private String description_no_tags ; 
	private String image_url ; 
	
	
	private Bitmap myImage ; 
//	private byte[] myArray ;
	
	public NewsDataClass ()
	{
		
	}
	
	
	
	 public NewsDataClass(Parcel in) {
			// TODO Auto-generated constructor stub
			 
		         total = in.readString();


		 per_page = in.readString();
		 current_page = in.readString() ;
		 last_page = in.readString();
		 next_page_url = in.readString();
		 prev_page_url = in.readString();
		 from = in.readString() ;
		 to = in.readString() ;


		 id = in.readString();
		 name = in.readString();
		 description = in.readString();
		 image = in.readString();
		 publish = in.readString();
		 entry_user = in.readString();
		 created_at = in.readString();
		 category_id = in.readString() ;
		 start_date = in.readString() ;
		    	 end_date = in.readString() ;

		 deleted_at = in.readString();

		 myImage = in.readParcelable(null);

//		    	 myArray = new byte[in.readInt()];
//		    	 in.readByteArray(myArray);

		 category = in.readString();
		 description_no_tags = in.readString();
		 image_url = in.readString();
		    	 
		    	
		    	 

		}
	 
	 
	 public static final Parcelable.Creator<NewsDataClass> CREATOR = 
	            new Parcelable.Creator<NewsDataClass>() {
	        public NewsDataClass createFromParcel(Parcel in) {
	            return new NewsDataClass(in);
	        }

	        public NewsDataClass[] newArray(int size) {
	            return new NewsDataClass[size];
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
		 out.writeString(current_page) ;
		 out.writeString(last_page);
		 out.writeString(next_page_url);
		 out.writeString(prev_page_url);
		 out.writeString(from) ;
		 out.writeString(to) ;


		out.writeString(id);
		 out.writeString(name);
		 out.writeString(description);
		 out.writeString(image);
		 out.writeString(publish);
		 out.writeString(entry_user);
		 out.writeString(created_at);
		 out.writeString(category_id) ;
		 out.writeString(start_date) ;
		 out.writeString(end_date) ;

		 out.writeString(deleted_at);

		out.writeParcelable(myImage, flags);

//		    	 myArray = new byte[in.readInt()];
//		    	 in.readByteArray(myArray);

		 out.writeString(category);
		 out.writeString(description_no_tags);
		 out.writeString(image_url);

		
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



	public String getFrom() {
		return from;
	}



	public void setFrom(String from) {
		this.from = from;
	}



	public String getTo() {
		return to;
	}



	public void setTo(String to) {
		this.to = to;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getPublish() {
		return publish;
	}



	public void setPublish(String publish) {
		this.publish = publish;
	}



	public String getEntry_user() {
		return entry_user;
	}



	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}



	



	public String getStart_date() {
		return start_date;
	}



	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}



	public String getEnd_date() {
		return end_date;
	}



	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}

	
	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getDescription_no_tags() {
		return description_no_tags;
	}



	public void setDescription_no_tags(String description_no_tags) {
		this.description_no_tags = description_no_tags;
	}



	public String getImage_url() {
		return image_url;
	}



	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}



	public String getDeleted_at() {
		return deleted_at;
	}



	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}



	public Bitmap getMyImage() {
		return myImage;
	}



	public void setMyImage(Bitmap myImage) {
		this.myImage = myImage;
	}



//	public byte[] getMyArray() {
//		return myArray;
//	}



//	public void setMyArray(byte[] myArray) {
//		this.myArray = myArray;
//	}



	public ArrayList<NewsDataClass> parseJson(String jsonParsed) {


		ArrayList<NewsDataClass> myData = new ArrayList<NewsDataClass>() ;
		NewsDataClass d ;

		try {

			JSONObject jobj = new JSONObject(jsonParsed) ;

			String per_page = jobj.getString("per_page");
			String total = jobj.getString("total");
			String current_page = jobj.getString("current_page");
			String last_page = jobj.getString("last_page");
			String next_page_url = jobj.getString("next_page_url");
			String prev_page_url = jobj.getString("prev_page_url");
			String from = jobj.getString("from");
			String to = jobj.getString("to");


			JSONArray jData = jobj.getJSONArray("data");
			JSONObject jParameters ;

			for (int i = 0 ; i<  jData.length() ; i++)
			{

				d = new NewsDataClass();
				jParameters = jData.getJSONObject(i);


				String id = jParameters.getString("id");
				String name = jParameters.getString("name");
				String description = jParameters.getString("description");
				String image = jParameters.getString("image");
				String image_fullurl = jParameters.getString("image_url");

//				if (image != null)
//				{
//					image = image_fullurl + image;
//
//				}

				String publish = jParameters.getString("publish");
				String entry_user = jParameters.getString("entry_user");
				String created_at = jParameters.getString("created_at");
				String start_date = jParameters.getString("start_date");
				String end_date = jParameters.getString("end_date");
				String category_id = jParameters.getString("category_id");
				String deleted_at = jParameters.getString("deleted_at");
				String category = jParameters.getString("category");
				String description_no_tags = jParameters.getString("desc_notags");






				d.setPer_page(per_page);
				d.setTotal(total);
				d.setCurrent_page(current_page);
				d.setLast_page(last_page);
				d.setNext_page_url(next_page_url);
				d.setPrev_page_url(prev_page_url);
				d.setFrom(from);
				d.setTo(to);


				d.setId(id);
				d.setName(name);
				d.setDescription(description);
				d.setImage(image);
				d.setPublish(publish);
				d.setEntry_user(entry_user);
				d.setCreated_at(created_at);
				d.setStart_date(start_date);
				d.setEnd_date(end_date);
				d.setCategory_id(category_id);

				d.setDeleted_at(deleted_at);

				d.setCategory(category);
				d.setDescription_no_tags(description_no_tags);
				d.setImage_url(image_fullurl);



				myData.add(d);
			}

		}

		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		return myData ;
	}
}
