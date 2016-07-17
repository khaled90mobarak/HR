package fortopapps.hr.checkinout;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DepartmentSpinnerDataClass implements Parcelable{  // department Data Class for sending exception

	
	private String id  ; 
	private String name ; 
	
	
	
	
	public DepartmentSpinnerDataClass ()
	{
		
	}
	

	
	
	
	
	 public DepartmentSpinnerDataClass(Parcel in) {
			// TODO Auto-generated constructor stub
			 
		         id = in.readString(); 
			 name = in.readString();

		}
	 
	 
	 public static final Parcelable.Creator<DepartmentSpinnerDataClass> CREATOR = 
	            new Parcelable.Creator<DepartmentSpinnerDataClass>() {
	        public DepartmentSpinnerDataClass createFromParcel(Parcel in) {
	            return new DepartmentSpinnerDataClass(in);
	        }

	        public DepartmentSpinnerDataClass[] newArray(int size) {
	            return new DepartmentSpinnerDataClass[size];
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
		out.writeString(id);
		out.writeString(name);
		
		
	}


	// getters and setters

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



	public ArrayList<DepartmentSpinnerDataClass> parseJsonGetDepartment(String jsonParsed) {

		String message = "";


//		ArrayList<String> credentials = new ArrayList<String>();
		ArrayList<DepartmentSpinnerDataClass> departmentList = new ArrayList<DepartmentSpinnerDataClass>();

		try {


			JSONObject objData = new JSONObject(jsonParsed);
//			String status = objData.getString("status");
//			credentials.add(status);

//			if (objData.getString("message").equals(null))
			if (objData.getString("status").equals("success"))
//				message = "" ;

			{
				JSONArray jarrDepartment = objData.getJSONArray("departments");

				for (int i = 0 ; i<jarrDepartment.length(); i++)
				{
					JSONObject objArrItem = jarrDepartment.getJSONObject(i);

					DepartmentSpinnerDataClass d = new DepartmentSpinnerDataClass() ;


					d.setId(objArrItem.getString("id"));
					d.setName(objArrItem.getString("name"));


					departmentList.add(d);

				}




			}


			else
			{
				DepartmentSpinnerDataClass data = new DepartmentSpinnerDataClass ();
//			 message = objData.getString("message");
				data.setName("message");
				departmentList.add(data);
			}

		}

		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return departmentList;

	}
	
}
