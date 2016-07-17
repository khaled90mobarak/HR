package fortopapps.hr.checkinout;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmployeeSpinnerDataClass implements Parcelable {   // employee data class for sending exception

	
	private String id  ; 
	private String lname ; 
	private String fname ; 
	
	
	
	
	public EmployeeSpinnerDataClass ()
	{
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
	return	getId() + "\n" + getFname() + "\n" + getLname() ; 
	}
	
	
	public EmployeeSpinnerDataClass(Parcel in) {
		// TODO Auto-generated constructor stub
		 
	         id = in.readString(); 
		 fname = in.readString();
		 lname = in.readString();

	}
 
 
 public static final Parcelable.Creator<EmployeeSpinnerDataClass> CREATOR = 
            new Parcelable.Creator<EmployeeSpinnerDataClass>() {
        public EmployeeSpinnerDataClass createFromParcel(Parcel in) {
            return new EmployeeSpinnerDataClass(in);
        }

        public EmployeeSpinnerDataClass[] newArray(int size) {
            return new EmployeeSpinnerDataClass[size];
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
	out.writeString(fname);
	out.writeString(lname);
	
	
}


// getters and setters

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getLname() {
	return lname;
}

public void setLname(String lname) {
	this.lname = lname;
}

public String getFname() {
	return fname;
}

public void setFname(String fname) {
	this.fname = fname;
}


	public ArrayList<EmployeeSpinnerDataClass> parseJsonGetEmployee(String jsonParsed) {

		String message = "";


		ArrayList<EmployeeSpinnerDataClass> employeeList = new ArrayList<EmployeeSpinnerDataClass>();

		try {


			JSONObject objData = new JSONObject(jsonParsed);


			EmployeeSpinnerDataClass dataZero = new EmployeeSpinnerDataClass();
			dataZero.setId("0");
			dataZero.setFname("All");
			dataZero.setLname("");
			employeeList.add(0, dataZero);


			if (objData.getString("status").equals("success"))


			{
				JSONArray jarrEmployees = objData.getJSONArray("employees");

				for (int i = 0 ; i<jarrEmployees.length(); i++)
				{
					JSONObject objArrItem = jarrEmployees.getJSONObject(i);

					EmployeeSpinnerDataClass d = new EmployeeSpinnerDataClass() ;


					d.setId(objArrItem.getString("id"));
					d.setFname(objArrItem.getString("fname"));
					d.setLname(objArrItem.getString("lname"));


					employeeList.add(d);

				}




			}


			else
			{
				EmployeeSpinnerDataClass data = new EmployeeSpinnerDataClass ();

				data.setFname("message");
				employeeList.add(data);
			}

		}

		catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return employeeList;

	}



}
