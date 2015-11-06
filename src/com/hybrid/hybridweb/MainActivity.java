package com.hybrid.hybridweb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btnWeb80;
	Button btnWeb8080;
	WebView myweb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnWeb80 = (Button) findViewById(R.id.btnWeb80);
		btnWeb8080 = (Button) findViewById(R.id.btnWeb8080);
		
		btnWeb80.setOnClickListener(this);
		btnWeb8080.setOnClickListener(this);
		
		myweb = (WebView) findViewById(R.id.myweb);
		
		WebSettings settings = myweb.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);
		
		myweb.addJavascriptInterface(new MyjavascriptInterface(), "android");	
		// 자바스크립트에서 자바를 호출 할수있도록 하는 인터페이스 역활
		myweb.setWebViewClient(new MyWebViewClient());					
		// a hred
		 myweb.setWebChromeClient(new WebChromeClient()); 
		//alert()
	
		myweb.loadUrl("http://192.168.10.17:8080/web/index.jsp");
		/*myweb.loadUrl("http://soen.kr");*/
		/*myweb.loadUrl("http://192.168.10.17");*/
	}
	class MyjavascriptInterface {
		
		@JavascriptInterface
		public void showToast(String msg) {
			Log.i("###", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
			
		}
	}
	
	class MyWebViewClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.i("###", "url = " + url);
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnWeb80:
			myweb.loadUrl("http://192.168.10.17");
			break;
		
		case R.id.btnWeb8080:
			myweb.loadUrl("http://192.168.10.17:8080/web");
			break;

		default:
			break;
		}
		
	}
}
