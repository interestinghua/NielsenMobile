package com.lbschina.srt.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lbschina.service.GPSService;
import com.lbschina.service.GPSThread;
import com.lbschina.srt.App;
import com.lbschina.srt.R;

@SuppressLint({ "ShowToast", "ParserError" })
public class MainActivity extends Activity {

	private Integer[] images = {
			// 九宫格图片的设置
			R.drawable.map,  R.drawable.showpoint,R.drawable.addpoint,R.drawable.showroute,R.drawable.job,R.drawable.set};

	private String[] texts = {
			// 九宫格图片下方文字的设置
			"售点采集",  "网点浏览","地图管理","地图块管理","售点管理","软件更新"};

	// 再按一次退出程序
	private boolean isExits = false;
	private Timer timer;
	private TimerTask timerTask;
	
	private TextView VersionTitle;
	
	//全局变量存储位置
	private App MyApp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HideTitle();
		setContentView(R.layout.activity_main);
		
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(getApplicationContext(), R.string.sdcarderror ,Toast.LENGTH_LONG).show();
		}
	
		//启动GPS后台服务
		Intent intentGPS = new Intent(MainActivity.this, GPSService.class);
		startService(intentGPS);
		
		GPSThread thdGPS=new GPSThread(MainActivity.this);
		thdGPS.start();
		
		String versionCode = this.getVersionCode(MainActivity.this);
		VersionTitle=(TextView) this.findViewById(R.id.VersionTitle);
		VersionTitle.setText("Version:"+versionCode);
		
		
		MyApp=(App) this.getApplication();
		InitMainPage();		
	}
	
	/**
	 * 获取软件版本号
	 */
	private String getVersionCode(Context context){
		String versionCode=null;
		try{
			//获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = context.getPackageManager().getPackageInfo("com.lbschina.srt", 0).versionName;
			return versionCode;
		}catch (NameNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	private void InitMainPage() {

		final GridView gridview = (GridView) findViewById(R.id.GridView);
		ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < images.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", images[i]);
			map.put("ItemText", texts[i]);
			meumList.add(map);
		}

		SimpleAdapter saMenuItem = new SimpleAdapter(this, meumList,
				R.layout.meun_item, new String[] { "ItemImage", "ItemText" }, // 对应map的Key
				new int[] { R.id.ItemImage, R.id.ItemText }); // 对应R的Id
		// 添加Item到网格中
		gridview.setAdapter(saMenuItem);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			@SuppressLint("ShowToast")
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Object obj = gridview.getAdapter().getItem(arg2);
				HashMap<String, Object> map = (HashMap<String, Object>) obj;
				String str = (String) map.get("ItemText");
				jumpPage(str);
			}
		});
	}

	@TargetApi(5)
	@SuppressLint("NewApi")
	private void jumpPage(String str) {
		if (str == texts[0]) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ShowMapActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			MainActivity.this.finish();

		} else if (str == texts[1]) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, ShowPointGridActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			MainActivity.this.finish();
			
		}else if (str == texts[2]) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, OfflineMapActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			MainActivity.this.finish();
			
		}else if (str == texts[3]) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, BlockManageActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			MainActivity.this.finish();
		}else if (str == texts[4]) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, SalesPointManageActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			MainActivity.this.finish();
		}else if (str == texts[5]) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, AutoUpdateActivity.class);
			startActivity(intent);
			overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			MainActivity.this.finish();
		}   
	}

	@SuppressLint({ "ShowToast", "ShowToast" })
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (!isExits) {
				Toast.makeText(this, "再按一次退出", 1000).show();
				isExits = true;
				timer = new Timer();
				timerTask = new TimerTask() {
					@Override
					public void run() {
						if (isExits) {
							isExits = false;
						}
					}
				};
				timer.schedule(timerTask, 3000);
			} else {
				//退出前停止服务
//				Intent intentGPS = new Intent(MainActivity.this, GPSService.class);
//				MainActivity.this.stopService(intentGPS);				
				finish();
			}
		}

		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case 0:				
				break;
	
			case 1:	
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);	
				builder.setMessage("退出软件").setCancelable(true)
						.setPositiveButton("确定", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								//退出前停止服务
//								Intent intentGPS = new Intent(MainActivity.this, GPSService.class);
//								MainActivity.this.stopService(intentGPS);
								finish();
							}
						})
						.setNegativeButton("取消", new OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
							}
						});
				builder.show();
		}
		return true;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 1, "退出").setIcon(android.R.drawable.ic_delete);
		return true;
	}

	private void HideTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}	
}
