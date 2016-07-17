package fortopapps.hr.inquiries;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fortopapps.hr.R;

public class InquiriesAdapterClass extends ArrayAdapter<InquiriesDataClass> {


	ArrayList<InquiriesDataClass> myList ; 

	Context mContext ; 
	
	public InquiriesAdapterClass(Context context, ArrayList<InquiriesDataClass> listData) {
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
		convertView = activity.getLayoutInflater().inflate(R.layout.inquiries_cell_data,parent,false);
		
		TextView tvEmpFrom = (TextView) convertView.findViewById(R.id.tvInquiryEmpFrom);
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvInquiryTitle);	
		TextView tvMessage = (TextView) convertView.findViewById(R.id.tvInquiryMessage);
    	TextView tvRepliesCount = (TextView) convertView.findViewById(R.id.tvInquiryRepliesCount);
		TextView tvDate = (TextView) convertView.findViewById(R.id.tvInquiryDate);
		
		ImageView imgStatus = (ImageView) convertView.findViewById(R.id.imgInquiryStatus);


		
InquiriesDataClass d = myList.get(position);



   
if (d.getEmpFrom()!=null)
tvEmpFrom.setText(d.getEmpFrom()) ;
if (d.getTitle()!=null)
tvTitle.setText(d.getTitle());
if (d.getMessage()!=null)
tvMessage.setText(d.getMessage());
if (d.getReplay_count()!=null)
tvRepliesCount.setText(d.getReplay_count());

if (d.getEntryDate()!=null)
tvDate.setText(d.getEntryDate());

if (d.getStatus().equals("1"))
{
	imgStatus.setImageResource(R.drawable.img_inquiry_pending);
}

else if (d.getStatus().equals("2"))
{
	imgStatus.setImageResource(R.drawable.img_inquiry_open);
}

else if (d.getStatus().equals("3"))
{
	imgStatus.setImageResource(R.drawable.img_inquiry_closed); 
}
		
		return convertView;
	}
	
	
}
