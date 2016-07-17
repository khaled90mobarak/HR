package fortopapps.hr.sallary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fortopapps.hr.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context _context;
	private List<Map> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<Map, List<Map>> _listDataChild;

	public ExpandableListAdapter(Context context, List<Map> listDataHeader,
								 HashMap<Map, List<Map>> listChildData) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
							 boolean isLastChild, View convertView, ViewGroup parent) {

		Map dic = (Map)  getChild(groupPosition, childPosition);

		//final String childText = dic.get("tit").toString(); // (String) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.activity_sallary_list_item, null);
		}

		TextView basic_salary = (TextView) convertView.findViewById(R.id.basic_salary);
		TextView nature_allowance = (TextView) convertView.findViewById(R.id.nature_allowance);
		TextView representation_allowance = (TextView) convertView.findViewById(R.id.representation_allowance);
		TextView sea_allowance = (TextView) convertView.findViewById(R.id.sea_allowance);
		TextView allowance_the_time = (TextView) convertView.findViewById(R.id.allowance_the_time);
		TextView addition_social = (TextView) convertView.findViewById(R.id.addition_social);
		TextView in_addition_a_private_social = (TextView) convertView.findViewById(R.id.in_addition_a_private_social);
		TextView benifits_subject_to_taxation = (TextView) convertView.findViewById(R.id.benifits_subject_to_taxation);
		TextView total_benefits = (TextView) convertView.findViewById(R.id.total_benefits);
		TextView net_dollar = (TextView) convertView.findViewById(R.id.net_dollar);
		TextView net_pounds = (TextView) convertView.findViewById(R.id.net_pounds);
		TextView tax = (TextView) convertView.findViewById(R.id.tax);
		TextView employee_insurance = (TextView) convertView.findViewById(R.id.employee_insurance);
		TextView insurance_premium_car = (TextView) convertView.findViewById(R.id.insurance_premium_car);
		TextView buy_a_precedent = (TextView) convertView.findViewById(R.id.buy_a_precedent);
		TextView vodafone_pounds_factor = (TextView) convertView.findViewById(R.id.vodafone_pounds_factor);
		TextView total_deductions = (TextView) convertView.findViewById(R.id.total_deductions);
		TextView insurance_company = (TextView) convertView.findViewById(R.id.insurance_company);
		TextView remaining_car_insurance = (TextView) convertView.findViewById(R.id.remaining_car_insurance);
		TextView total_deductions_after_insurance = (TextView) convertView.findViewById(R.id.total_deductions_after_insurance);


		basic_salary.setText(dic.get("basic_salary").toString());
		nature_allowance.setText(dic.get("nature_allowance").toString());
		representation_allowance.setText(dic.get("representation_allowance").toString());
		sea_allowance.setText(dic.get("sea_allowance").toString());
		allowance_the_time.setText(dic.get("allowance_the_time").toString());
		addition_social.setText(dic.get("addition_social").toString());
		in_addition_a_private_social.setText(dic.get("in_addition_a_private_social").toString());
		benifits_subject_to_taxation.setText(dic.get("benifits_subject_to_taxation").toString());
		total_benefits.setText(dic.get("total_benefits").toString());
		net_dollar.setText(dic.get("net_dollar").toString());
		net_pounds.setText(dic.get("net_pounds").toString());
		tax.setText(dic.get("tax").toString());
		employee_insurance.setText(dic.get("employee_insurance").toString());
		insurance_premium_car.setText(dic.get("insurance_premium_car").toString());
		buy_a_precedent.setText(dic.get("buy_a_precedent").toString());
		vodafone_pounds_factor.setText(dic.get("vodafone_pounds_factor").toString());
		total_deductions.setText(dic.get("total_deductions").toString());
		insurance_company.setText(dic.get("insurance_company").toString());
		remaining_car_insurance.setText(dic.get("remaining_car_insurance").toString());
		total_deductions_after_insurance.setText(dic.get("total_deductions_after_insurance").toString());


		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
							 View convertView, ViewGroup parent) {

		Map dic = (Map) getGroup(groupPosition);
		String month_char = dic.get("month_char").toString() ; //(String) getGroup(groupPosition);
		String basic_salary = dic.get("basic_salary").toString() ;
		String total_benefits = dic.get("total_benefits").toString() ;
		String total_deductions = dic.get("total_deductions").toString() ;
		String net_pounds = dic.get("net_pounds").toString() ;

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.activity_sallary_list_group, null);
		}

		TextView txtmonthd = (TextView) convertView.findViewById(R.id.txtmonth);
		TextView txtbasic = (TextView) convertView.findViewById(R.id.txtbasic);
		TextView txtbenfits = (TextView) convertView.findViewById(R.id.txtbenfits);
		TextView txtdeduct = (TextView) convertView.findViewById(R.id.txtdeduct);
		TextView txttotal = (TextView) convertView.findViewById(R.id.txttotal);



		txtmonthd.setText(month_char);
		txtbasic.setText(basic_salary);
		txtbenfits.setText(total_benefits);
		txtdeduct.setText(total_deductions);
		txttotal.setText(net_pounds);


		ImageView img_selection=(ImageView) convertView.findViewById(R.id.imgarrow);
		int imageResourceId = isExpanded ? R.drawable.imgup
				: R.drawable.imgdown;
		img_selection.setImageResource(imageResourceId);


		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
