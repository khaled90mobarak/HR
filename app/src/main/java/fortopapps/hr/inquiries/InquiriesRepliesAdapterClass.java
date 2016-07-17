package fortopapps.hr.inquiries;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import fortopapps.hr.R;

public class InquiriesRepliesAdapterClass extends ArrayAdapter<InquiriesDataClass> {

	ArrayList<InquiriesDataClass> myList ; 
	Context mContext ; 
	
	public InquiriesRepliesAdapterClass(Context context, ArrayList<InquiriesDataClass> listData) {
		super(context,0, listData);
		
		mContext = context ; 
		myList =(ArrayList<InquiriesDataClass>) listData ; 
		// TODO Auto-generated constructor stub
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		Activity activity = (Activity)mContext ; 
		convertView = activity.getLayoutInflater().inflate(R.layout.inquiry_replies_cell,parent,false);
		
		TextView tvEmpTo = (TextView) convertView.findViewById(R.id.tvInquiryEmpTo_repliesList);
//		
		TextView tvMessage = (TextView) convertView.findViewById(R.id.tvInquiryMessage_repliesList);
//		
		TextView tvDate = (TextView) convertView.findViewById(R.id.tvInquiryDate_repliesList);
		
//		


		
InquiriesDataClass d = myList.get(position);
		
		


 
if (d.getEmpTo()!=null)
{

tvEmpTo.setText(d.getEmpFrom()) ;
}


if (d.getTitle()!=null)

tvMessage.setText(d.getMessage().toString());

if (d.getEntryDate()!=null)
tvDate.setText(d.getEntryDate().toString());


		
		return convertView;
	}
	
	
}
