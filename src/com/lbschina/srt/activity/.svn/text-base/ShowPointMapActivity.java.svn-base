package com.lbschina.srt.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.lbschina.srt.App;
import com.lbschina.srt.NewPoint;
import com.lbschina.srt.R;
import com.lbschina.srt.util.AMapUtil;

public class ShowPointMapActivity extends FragmentActivity implements 
				LocationSource, AMapLocationListener,OnMarkerClickListener,
				OnInfoWindowClickListener, OnMarkerDragListener,InfoWindowAdapter{
	
	private AMap aMap=null;
	private UiSettings mUiSettings=null;

	private TextView navigateBack;
	ArrayList<NewPoint> noteList = new ArrayList<NewPoint>();
	
	//定位相关
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;	
	
	//全局变量存储位置
	private App MyApp;
	
	private View mWindow;
	private View mContents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		HideTitle();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_point_map);		
		init();
		initGPS();
		
		MyApp=(App) this.getApplication();		

		navigateBack = (TextView) findViewById(R.id.NavigateBack);
		navigateBack.setClickable(true);

		navigateBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ReturnMainPage();
			}
		});
	}
	
	private void initGPS(){	
		
		LocationManager locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);       
        boolean isEnabled= locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER); 
        
        if(!isEnabled){  
            //这段代码可以实现GPS开关状态的切换  
            Intent GPSIntent = new Intent();  
            GPSIntent.setClassName("com.android.settings",
            						"com.android.settings.widget.SettingsAppWidgetProvider");  
            GPSIntent.addCategory("android.intent.category.ALTERNATIVE");  
            GPSIntent.setData(Uri.parse("custom:3")); 
            
            try {  
                PendingIntent.getBroadcast(ShowPointMapActivity.this, 0, GPSIntent, 0).send();  
            } catch (CanceledException e) {  
                e.printStackTrace();  
            } 
        }	
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		
		super.onResume();
		// 接受传入的网点
		Intent startIntent = this.getIntent();
		String noteId = startIntent.getStringExtra("StrID");

		if (noteId != null && noteId !="") {
			
			double x = Double.valueOf(startIntent.getStringExtra("X"));
			double y = Double.valueOf(startIntent.getStringExtra("Y"));

			NewPoint spointPoint = new NewPoint();
			spointPoint.X = String.valueOf(x);
			spointPoint.Y = String.valueOf(y);
			spointPoint.ID = noteId;
			
			noteList.add(spointPoint);
			MyApp.setSpointPoint(spointPoint);	
			
		} else {			
			noteList = (ArrayList<NewPoint>) startIntent.getSerializableExtra("NoteList");
			
			if (noteList !=null && noteList.size() > 0) {
				MyApp.setNoteList(noteList);
			}
		}
		
		//---------------------------------------
		
		NewPoint spointPoint = new NewPoint();
		List<NewPoint> noteList = new ArrayList<NewPoint>();
		
		double x=0.0;
		double y=0.0;
		double aver_x=0.0;
		double aver_y=0.0;
		
		if(MyApp.getShowStr().equals("SHOWONE") && MyApp.getSpointPoint()!=null 
			&& MyApp.getSpointPoint().ID!=null && MyApp.getSpointPoint().X!=null 
			&& MyApp.getSpointPoint().Y!=null){
			
			spointPoint=MyApp.getSpointPoint();				
			aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(spointPoint.Y),Double.valueOf(spointPoint.X)))
					.snippet("ID:"+spointPoint.ID)
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.poingloc2)));
			
			aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(spointPoint.Y), 
																		 Double.valueOf(spointPoint.X)), 15));
		}else{
			if(MyApp.getShowStr().equals("SHOWALL") && MyApp.getNoteList()!=null && MyApp.getNoteList().size()>0){
				
				noteList=MyApp.getNoteList();				
				for(NewPoint np: noteList){					
					x+=Float.valueOf(np.X);
					y+=Float.valueOf(np.Y);					
					aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(np.Y),Double.valueOf(np.X)))
							.snippet("ID:"+np.ID).icon(BitmapDescriptorFactory.fromResource(R.drawable.poingloc2)));				
				}				
				aver_x=x/noteList.size();
				aver_y=y/noteList.size();				
				aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aver_y, aver_x), 11));
			}
		}
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {                
		if (aMap == null) {			
			aMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentMap)).getMap();		
			if (AMapUtil.checkReady(this, aMap)) {
				setUpMap();
			}
		}
		
		mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
		mContents = getLayoutInflater().inflate(R.layout.custom_info_contents,null);
	}
	
	private void setUpMap() {
		
		//我的位置功能
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		aMap.setMyLocationStyle(myLocationStyle);
		mAMapLocationManager = LocationManagerProxy.getInstance(ShowPointMapActivity.this);
		aMap.setLocationSource(this);
		aMap.setMyLocationEnabled(false);//设置为true表示系统定位按钮显示并响应点击，false表示隐藏，默认是false
		
		//设置地图UI
		aMap.setMapType(AMap.MAP_TYPE_NORMAL);	
		mUiSettings=aMap.getUiSettings();
		mUiSettings.setRotateGesturesEnabled(false);
		mUiSettings.setTiltGesturesEnabled(false);
		mUiSettings.setZoomControlsEnabled(true);
		mUiSettings.setZoomGesturesEnabled(true);		
		
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
		aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ReturnMainPage();
		}
		return super.onKeyDown(keyCode, event);
	}

	private void ReturnMainPage() {
		setResult(RESULT_OK);
		finish();
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
	}

	private void HideTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	public class CenterPointView extends View{
	      
	    public CenterPointView(Context context) {  
	        super(context);
	    }  
	    public CenterPointView(Context context , AttributeSet attrs){  
	        super(context,attrs);  
	    }

	    @Override  
	    public void draw(Canvas canvas) {	    	
	        super.draw(canvas); 
	        DisplayMetrics metric = new DisplayMetrics();  
		    getWindowManager().getDefaultDisplay().getMetrics(metric);			
	    }  
	}

	@Override
	public void onLocationChanged(Location location) {
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}

	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null) {
			mListener.onLocationChanged(aLocation);
		}		
	}

	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			
			mAMapLocationManager = LocationManagerProxy.getInstance(this);
			//设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true			
			mAMapLocationManager.setGpsEnable(true);
		}
		
		// Location API定位采用GPS和网络混合定位方式，时间最短是5000毫秒
		/**
		 *provider - 注册监听的provider名称 
		  minTime- 位置变化的通知时间，单位为毫秒，实际时间有可能长于或短于设定值 
		  minDistance- 位置变化通知距离，单位为米 
		  listener- 监听listener 
		 */
		
		mAMapLocationManager.requestLocationUpdates(LocationProviderProxy.AMapNetwork, 1000, 10, this);			
	}

	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destory();
		}
		mAMapLocationManager = null;		
	}

	@Override
	protected void onPause() {
		super.onPause();
		deactivate();
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		return false;
	}

	@Override
	public void onMarkerDrag(Marker marker) {
		
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		
	}

	@Override
	public View getInfoContents(Marker marker) {
		render(marker, mContents);
		return mContents;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		render(marker, mWindow);
		return mWindow;
	} 
	
	public void render(Marker marker, View view) {
		String snippet = marker.getSnippet();
		TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
		if (snippet != null) {
			SpannableString snippetText = new SpannableString(snippet);
			snippetUi.setText(snippetText);
		} else {
			snippetUi.setText("");
		}
	}	
}
