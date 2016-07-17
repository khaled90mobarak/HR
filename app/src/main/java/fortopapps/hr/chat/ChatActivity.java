package fortopapps.hr.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import fortopapps.hr.R;

public class ChatActivity extends AppCompatActivity {

	String url = "http://104.236.218.198/api/chat-login";
	private WebView wv1;

	String mobile_token = "35802105839829222";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_fragment);
		setTitle("Chat");

		wv1 = (WebView) findViewById(R.id.webView_chat);
		wv1.setWebViewClient(new MyBrowser());


		wv1.getSettings().setLoadsImagesAutomatically(true);
		wv1.getSettings().setJavaScriptEnabled(true);
		wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		wv1.loadUrl(url + "?mobile_token=" + mobile_token);


	}

	private class MyBrowser extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url+"?mobile_token="+mobile_token);


			return true;
		}





	}
}