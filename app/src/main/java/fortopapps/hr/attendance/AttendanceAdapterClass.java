package fortopapps.hr.attendance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import android.app.Activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fortopapps.hr.R;
import fortopapps.hr.connection.parsejson;

public class AttendanceAdapterClass extends BaseAdapter  {

	public final Map<String, Adapter> sections = new LinkedHashMap<String, Adapter>();
	public final ArrayAdapter<String> headers;
	public final static int TYPE_SECTION_HEADER = 0;
	Context mContext ;

	public AttendanceAdapterClass(Context context)
	{
		  mContext = context ;
		headers = new ArrayAdapter<String>(context, R.layout.activity_attendance_cell_header);
	}

	public void addSection(String section, Adapter adapter)
	{
		this.headers.add(section);
		this.sections.put(section, adapter);
	}

	public Object getItem(int position)
	{
		for (Object section : this.sections.keySet())
		{
			Adapter adapter = sections.get(section);
			int size = adapter.getCount() + 1;

			// check if position inside this section
			if (position == 0) return section;
			if (position < size) return adapter.getItem(position - 1);

			// otherwise jump into next section
			position -= size;
		}
		return null;
	}

	public int getCount()
	{
		// total together all sections, plus one for each section header
		int total = 0;
		for (Adapter adapter : this.sections.values())
			total += adapter.getCount() + 1;
		return total;
	}

	@Override
	public int getViewTypeCount()
	{
		// assume that headers count as one, then total all sections
		int total = 1;
		for (Adapter adapter : this.sections.values())
			total += adapter.getViewTypeCount();
		return total;
	}

	@Override
	public int getItemViewType(int position)
	{
		int type = 1;
		for (Object section : this.sections.keySet())
		{
			Adapter adapter = sections.get(section);
			int size = adapter.getCount() + 1;

			// check if position inside this section
			if (position == 0) return TYPE_SECTION_HEADER;
			if (position < size) return type + adapter.getItemViewType(position - 1);

			// otherwise jump into next section
			position -= size;
			type += adapter.getViewTypeCount();
		}
		return -1;
	}

	public boolean areAllItemsSelectable()
	{
		return false;
	}

	@Override
	public boolean isEnabled(int position)
	{
		return (getItemViewType(position) != TYPE_SECTION_HEADER);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		int sectionnum = 0;
		for (Object section : this.sections.keySet())
		{
			Adapter adapter = sections.get(section);
			int size = adapter.getCount() + 1;

			// check if position inside this section
			if (position == 0) {
			 //	return headers.getView(sectionnum, convertView, parent) ;
				return getViewHeader(headers,sectionnum,convertView, parent);
			}
			if (position < size)
			 {
					 //return adapter.getView(position - 1, convertView, parent);
			  return getViewCellcutome(adapter,position - 1, convertView, parent);
			}

			// otherwise jump into next section
			position -= size;
			sectionnum++;
		}
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

/*
	ArrayList  myList ;

	Context mContext ; 
	
	public AttendanceAdapterClass(Context context, ArrayList listData) {
		super(context,0, listData);
		
		mContext = context ; 
		myList =(ArrayList ) listData ;
		// TODO Auto-generated constructor stub
	}
	
	@SuppressLint("ViewHolder")
	@Override
		*/
	public View getViewCellcutome(Adapter adapter,int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub


		Activity activity = (Activity)mContext ;
		convertView = activity.getLayoutInflater().inflate(R.layout.activity_attendance_cell,parent,false);

		TextView txtdate = (TextView) convertView.findViewById(R.id.txtdate);
		TextView txttime = (TextView) convertView.findViewById(R.id.txttime);
		TextView txtlocation = (TextView) convertView.findViewById(R.id.txtlocation);
    	TextView txtstatus = (TextView) convertView.findViewById(R.id.txtstatus);
		ImageView cell_attendance = (ImageView) convertView.findViewById(R.id.cell_attendance);

		parsejson obj = new parsejson();
		String jsonrow1 =   adapter.getItem(position).toString();
		Map OneRow = obj.GetDic(jsonrow1);

		String Orgtime = OneRow.get("time").toString();
		String activity_status = OneRow.get("activity_status").toString();
		String location = OneRow.get("location").toString();
		String date = "";
		String time = "";

		SimpleDateFormat  Orgformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt = Orgformat.parse(Orgtime);

			SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat  TimeFormat = new SimpleDateFormat("HH:mm:ss");

			date = dateFormat.format(dt).toString();
			time = TimeFormat.format(dt).toString();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		txttime.setText(time);
		txtdate.setText(date);
		txtlocation.setText(location);
		txtstatus.setText(activity_status);


		if (position %2 ==0)
		{
			cell_attendance.setImageResource(R.drawable.cell2);
		}
		return convertView;
	}


	public View getViewHeader(Adapter adapter,int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub


		Activity activity = (Activity)mContext ;
		convertView = activity.getLayoutInflater().inflate(R.layout.activity_attendance_cell_header,parent,false);

		TextView txtmonth = (TextView) convertView.findViewById(R.id.txtmonth);

		String month =   adapter.getItem(position).toString();
		txtmonth.setText(month);


		return convertView;
	}

}
