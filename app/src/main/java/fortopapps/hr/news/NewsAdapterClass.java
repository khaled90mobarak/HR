package fortopapps.hr.news;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fortopapps.hr.R;
import it.sephiroth.android.library.picasso.Picasso;


public class NewsAdapterClass extends ArrayAdapter<NewsDataClass> {

	ArrayList<NewsDataClass> myList ; 
	Context mContext ; 
	
	String imageUrl ; 
	String imageName ; 
	
	public NewsAdapterClass(Context context, ArrayList<NewsDataClass> listData) {
		super(context,0, listData);
		
		mContext = context ; 
		myList =(ArrayList<NewsDataClass>) listData ; 
		// TODO Auto-generated constructor stub
	}
	
	// the interface of the show more button
//	customButtonListener customListner;
//
//		public interface customButtonListener {
//			public void onButtonClickListner(int position);
//		}
//
//	public void setCustomButtonListner(customButtonListener listener) {
//		this.customListner = listener;
//    }

	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		Activity activity = (Activity) mContext;
		convertView = activity.getLayoutInflater().inflate(R.layout.news_cell_data, parent, false);

		TextView title = (TextView) convertView.findViewById(R.id.tv_cell_title);
		TextView Description = (TextView) convertView.findViewById(R.id.tv_newsCell_description);
		TextView lblcat = (TextView) convertView.findViewById(R.id.lblcat);

		ImageView img_news = (ImageView) convertView.findViewById(R.id.img_newsCell);



		NewsDataClass d = myList.get(position);


//		d = getItem(position);


		title.setText(d.getName());
		lblcat.setText(d.getCategory());
		Description.setText(d.getDescription_no_tags() + "....");

		String photo = d.getImage();
		if (photo != null) {
			String	image = d.getImage_url() + d.getImage();
	 	Picasso.with(mContext).load(image).into(img_news);

			img_news.setVisibility(View.VISIBLE);
		}
		else
		{
			 img_news.setVisibility(View.INVISIBLE);
		}


		return convertView;
	}



	
}
