package com.lbschina.srt.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.lbschina.dpn.ServiceManager;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.VisibleRegion;
import com.lbschina.srt.App;
import com.lbschina.srt.MapConstants;
import com.lbschina.srt.NewPoint;
import com.lbschina.srt.R;
import com.lbschina.srt.db.SalesPointHelp;
import com.lbschina.srt.util.AMapUtil;
import com.lbschina.srt.util.Constants;
import com.lbschina.srt.util.LonLat2Distance;

public class ShowMapActivity extends FragmentActivity implements LocationSource, AMapLocationListener  {

	private AMap aMap=null;
	private Button navigateBack=null;
	private Button saveBtn=null;
	private UiSettings mUiSettings=null;
	private String ly_time ="";
	private Boolean flag;
	
	//定位相关
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;	
	
	//全局变量存储位置
	private App MyApp;
	
	//数据库操作
	SalesPointHelp pointHelper = null;
	
	static final CameraPosition LUJIAZUI = new CameraPosition.Builder().target(Constants.SHANGHAI).zoom(14).bearing(0).tilt(0).build();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		HideTitle();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_show);
		
		//添加中心点图标
		this.addContentView(new CenterPointView(this), new LayoutParams(LayoutParams.FILL_PARENT, 
				LayoutParams.FILL_PARENT));
		initGPS();
		init();
		  // Start the service
        ServiceManager serviceManager = new ServiceManager(this);
        serviceManager.setNotificationIcon(R.drawable.list_citymap_load);
        serviceManager.startService();
        
		flag=false;
		MyApp=(App) this.getApplication();
		
		navigateBack = (Button) findViewById(R.id.NavigateBack);
		navigateBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ReturnMainPage();
			}
		});
		
		saveBtn=(Button) findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				aMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
				
				double distance=0.0;//当前GPS位置与中心点GPS位置距离
				double cpGPSx=0.0;//中心点GPS经度
				double cpGPSy=0.0;//中心点GPS纬度
				double gpspx=0.0;//当前位置GPS经度
				double gpspy=0.0;//当前位置GPS纬度
				double cpX2ll=0.0;//中心点GPS经度
				double cpY2ll=0.0;//中心点GPS纬度
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ly_time = sdf.format(new java.util.Date());
				
				VisibleRegion visiblereg=null;
				LatLngBounds latlngbounds=null;
				
				if(aMap!=null){
					visiblereg=aMap.getProjection().getVisibleRegion();
					latlngbounds=visiblereg.latLngBounds;
				}			
				
				LatLng NE=latlngbounds.northeast; //东北角
				LatLng WS=latlngbounds.southwest; //西南角
				
				cpX2ll=(NE.longitude+WS.longitude)/2;
				cpY2ll=(NE.latitude+WS.latitude)/2;
				
				Log.i("cpX2ll", String.valueOf(cpX2ll));
				Log.i("cpY2ll", String.valueOf(cpY2ll));
				
				if(MyApp!=null){
					
					MyApp.setCenterGPSX(String.valueOf(cpX2ll));
					MyApp.setCenterGPSY(String.valueOf(cpY2ll));
					
					LonLat2Distance ll2d=new LonLat2Distance();
					
					if(MyApp.getCenterGPSX()!=null && MyApp.getCenterGPSY()!=null){
						cpGPSx=Double.valueOf(MyApp.getCenterGPSX());
						cpGPSy=Double.valueOf(MyApp.getCenterGPSY());
					}
					
					if(MyApp.getLon()!=null && MyApp.getLat()!=null){
						gpspx=Double.valueOf(MyApp.getLon());
						gpspy=Double.valueOf(MyApp.getLat());						
					}
					
					distance=ll2d.DistanceOfTwoPoints(gpspx, gpspy, cpGPSx, cpGPSy);	
					MyApp.setDistance(String.valueOf(distance));
				}
				
				pointHelper = new SalesPointHelp();
				pointHelper.init(ShowMapActivity.this);
				
				final EditText inputServer = new EditText(ShowMapActivity.this);
				
				AlertDialog.Builder builder = new AlertDialog.Builder(ShowMapActivity.this);
				
				if(Math.abs(distance)< MapConstants.OFF_SET ){
					//符合精度
					builder.setTitle("请输入网点编号")
							.setIcon(android.R.drawable.ic_dialog_info)
							.setView(inputServer).setNegativeButton("取消", null);
					
					builder.setPositiveButton("保存",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which){
									
									String strID=inputServer.getText().toString();									
									NewPoint spoint = new NewPoint();									
									spoint.ID = strID;
									spoint.createTime=ly_time;
									
									if(MyApp!=null){
										spoint.X =MyApp.getCenterGPSX();
										spoint.Y = MyApp.getCenterGPSY();	
										spoint.distance=MyApp.getDistance();
										
										if(MyApp.getLon()!=null && MyApp.getLat()!=null){
											spoint.GPSX=MyApp.getLon();
											spoint.GPSY=MyApp.getLat();
										}else{
											spoint.GPSX=0+"";
											spoint.GPSY=0+"";
										}
									}
									
									spoint.isOver=0;
									
									List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
									NewPoint_list=pointHelper.getPoint(strID);
									
									if(NewPoint_list!=null && NewPoint_list.size()>0){
										Toast.makeText(getApplicationContext(), "ID号已存在，请重新输入!",0).show();	
									}else{
										long result=pointHelper.NewPointInsert(spoint.ID,
																			Float.valueOf(spoint.GPSX), 
																			Float.valueOf(spoint.GPSY), 
																			Float.valueOf(spoint.X), 
																			Float.valueOf(spoint.Y), 																
																			Float.valueOf(spoint.distance), 
																			spoint.reason, 
																			Integer.valueOf(spoint.isOver),
																			spoint.createTime);									
										if(1==result){
											Toast.makeText(getApplicationContext(), "已保存",Toast.LENGTH_LONG).show();	
										}else if(0==result){
											Toast.makeText(getApplicationContext(), "请检查SD卡是否能正常读取，保存失败！",Toast.LENGTH_LONG).show();	
										}	
									}																				
								}								
						});					
				}else{
					//精度不符合要求
					//NewPoint spoint = new NewPoint();
					builder.setTitle("精度不符合要求，是否重新采集？")
							.setIcon(android.R.drawable.ic_dialog_info)
							.setNegativeButton("否", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
										AlertDialog.Builder NoBuilder = new AlertDialog.Builder(ShowMapActivity.this);	
										LayoutInflater mInflater = LayoutInflater.from(ShowMapActivity.this);
										final View dialogView = mInflater.inflate(R.layout.reasondialog,null); 
										
										NoBuilder.setTitle("请输入网点编号和原因")
												 .setIcon(android.R.drawable.ic_dialog_info)
												 .setView(dialogView)
												 .setNegativeButton("取消", null);
						
										NoBuilder.setPositiveButton("保存",
												new DialogInterface.OnClickListener() {
													public void onClick(DialogInterface dialog,int which){
														
														EditText ptId=(EditText) dialogView.findViewById(R.id.pointId);
														String strID=ptId.getText().toString();
														
														EditText ptReason=(EditText) dialogView.findViewById(R.id.pointReason);
														String reason=ptReason.getText().toString();
														
														NewPoint spoint = new NewPoint();
														spoint.ID = strID;
														
														if(MyApp!=null){
															spoint.X =MyApp.getCenterGPSX();
															spoint.Y = MyApp.getCenterGPSY();
															
															if(MyApp.getLon()!=null && MyApp.getLat()!=null){
																spoint.GPSX=MyApp.getLon();
																spoint.GPSY=MyApp.getLat();
															}else{
																spoint.GPSX=0+"";
																spoint.GPSY=0+"";
															}
															
															spoint.distance=MyApp.getDistance();
															spoint.createTime=ly_time;
														}
														spoint.isOver=1;
														spoint.reason=reason;
														
														List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
														NewPoint_list=pointHelper.getPoint(strID);
														
														if(NewPoint_list!=null && NewPoint_list.size()>0){
															Toast.makeText(getApplicationContext(), "ID号已存在，请重新输入!",Toast.LENGTH_LONG).show();	
														}else{
															long result=pointHelper.NewPointInsert(spoint.ID,
																					Float.valueOf(spoint.GPSX), 
																					Float.valueOf(spoint.GPSY), 
																					Float.valueOf(spoint.X), 
																					Float.valueOf(spoint.Y),																					
																					Float.valueOf(spoint.distance), 
																					spoint.reason, 
																					Integer.valueOf(spoint.isOver),
																					spoint.createTime);									
															if(1==result){
																Toast.makeText(getApplicationContext(), "已保存",Toast.LENGTH_LONG).show();	
															}else if(0==result){
																Toast.makeText(getApplicationContext(), "请检查SD卡是否能正常读取，保存失败！",Toast.LENGTH_LONG).show();	
															}		
														}								
													}								
											    });									
										NoBuilder.show();
								}
							});
			
					builder.setPositiveButton("是",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which){
								dialog.dismiss();
							}								
						});				
				}				
				builder.show();			
			}
		});		
	}
	
	/**
	 * 初始化AMap对象
	 */
	private void init() {                
		if (aMap == null) {			
			aMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();		
			if (AMapUtil.checkReady(this, aMap)) {
				setUpMap();
			}
		}
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
                PendingIntent.getBroadcast(ShowMapActivity.this, 0, GPSIntent, 0).send();  
            } catch (CanceledException e) {  
                e.printStackTrace();  
            } 
        }	
		
	}
	
	private void setUpMap() {
		
		//我的位置功能
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
		myLocationStyle.strokeColor(Color.RED);
		myLocationStyle.strokeWidth(1);
		aMap.setMyLocationStyle(myLocationStyle);
		mAMapLocationManager = LocationManagerProxy.getInstance(ShowMapActivity.this);
		aMap.setLocationSource(this);
		aMap.setMyLocationEnabled(true);//设置为true表示系统定位按钮显示并响应点击，false表示隐藏，默认是false
		
		//改变视图
		//aMap.moveCamera(CameraUpdateFactory.newCameraPosition(LUJIAZUI));		
		
		//设置地图UI
		aMap.setMapType(AMap.MAP_TYPE_NORMAL);	
		mUiSettings=aMap.getUiSettings();
		mUiSettings.setCompassEnabled(true);
		mUiSettings.setRotateGesturesEnabled(false);
		mUiSettings.setTiltGesturesEnabled(false);
		mUiSettings.setCompassEnabled(true);
		mUiSettings.setMyLocationButtonEnabled(true);
		mUiSettings.setZoomControlsEnabled(true);
		mUiSettings.setZoomGesturesEnabled(true);	
		mUiSettings.setScaleControlsEnabled(true);
		mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);		
	}

	private void ReturnMainPage() {
		Intent myIntent = new Intent();
		myIntent = new Intent(ShowMapActivity.this, MainActivity.class);
		startActivity(myIntent);
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		this.finish();
	}

	private void HideTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		deactivate();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	public void onBackPressed() {		
		super.onBackPressed();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ReturnMainPage();
		}		
		return super.onKeyDown(keyCode, event);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == MapConstants.FIRST_LOCATION) {
			}
		}
	};	

	
	//显示我的位置覆写函数
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
			
			double lat=aLocation.getLatitude();
			double lon=aLocation.getLongitude();
			
			float accuracy=aLocation.getAccuracy();
			Log.i("ACC", String.valueOf(accuracy));
			
			//Toast.makeText(getApplicationContext(), String.valueOf(lat)+"  "+String.valueOf(lon)
			//+" "+String.valueOf(accuracy),0).show();
			
			if(flag==false){
				aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 14));
				flag=true;
			}			
			
			if(MyApp!=null){
				MyApp.setLon(String.valueOf(lon));
				MyApp.setLat(String.valueOf(lat));
			}
			
			Log.i("Lat", String.valueOf(lat));
			Log.i("Lon", String.valueOf(lon));
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
		 *     	provider - 注册监听的provider名称 
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
		    int width = metric.widthPixels;     // 屏幕宽度（像素）  
		    int height = metric.heightPixels;   // 屏幕高度（像素）
		    
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.indoor_loc_suc);		 
			canvas.drawBitmap(bmp, width/2-14, height/2, null) ;   
	    }  
	} 

}
