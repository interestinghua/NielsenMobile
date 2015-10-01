package com.lbschina.hht;

import com.lbschina.srt.App;
import com.lbschina.srt.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class ShowInfoActivity extends Activity {
	
	TextView StoreNameText;
	TextView StoreAddrText;
	TextView StoreFlagText;
	//全局变量存储位置
	private App MyApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.showinfo);
		
		MyApp=(App) this.getApplication();
		
		StoreNameText = (TextView) findViewById(R.id.StoreNameText);
		StoreAddrText = (TextView) findViewById(R.id.StoreAddrText);
		StoreFlagText = (TextView) findViewById(R.id.StoreFlagText);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		StoreNameText.setText(MyApp.getmStoreName());
		StoreAddrText.setText(MyApp.getmStoreAddr());
		StoreFlagText.setText(MyApp.getmStoreFlag());			
	}
}
