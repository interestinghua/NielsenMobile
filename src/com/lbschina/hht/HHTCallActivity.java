package com.lbschina.hht;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.VisibleRegion;
import com.lbschina.hht.entity.NieBlock;
import com.lbschina.hht.entity.NieHisPoint;
import com.lbschina.hht.entity.ReturnEntity;
import com.lbschina.service.GPSService;
import com.lbschina.service.GPSThread;
import com.lbschina.srt.App;
import com.lbschina.srt.MapConstants;
import com.lbschina.srt.NewPoint;
import com.lbschina.srt.R;
import com.lbschina.srt.activity.BlockQueryActivity;
import com.lbschina.srt.activity.ValidationActivity;
import com.lbschina.srt.util.AMapUtil;
import com.lbschina.srt.util.LonLat2Distance;
import com.lbschina.srt.util.XmlCreateUtil;
import com.lbschina.validate.SoftKey;

public class HHTCallActivity extends FragmentActivity implements
		LocationSource, AMapLocationListener,OnMarkerClickListener,
		OnInfoWindowClickListener, OnMarkerDragListener,InfoWindowAdapter {
	
	private AMap aMap=null;
	private TextView BlockIDTxt=null;
	private TextView SalesIDTxt=null;	
	private Button saveBtn=null;
	private UiSettings mUiSettings=null;
	private TextView marquee_txt=null;
	
	//定位相关
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;	
	
	//全局变量存储位置
	private App MyApp;
	
	private View mWindow;
	private View mContents;
	
	//数据库操作
	HHTCallPointHelp mHHTCallPointHelp = null;
	HHTCallBlockHelp mHHTCallBlockHelp = null;
	HHTCallNewStoreHelp mHHTCallNewStoreHelp=null;
	
	Intent mGetIntent=null;
	private Boolean flag;
	private Boolean isSDCard=false;
	private Boolean isGPSShowCenter=true;
	
	DecimalFormat df = new DecimalFormat("#.000000");
	DecimalFormat df2 = new DecimalFormat("#.00");
	Random mRand = new Random();
	
	//存储验证变量
	private SharedPreferences sp;
	XmlCreateUtil mXmlCreateUtil=null;
	String sdpath = null;
	String mSavePath = null;
	
	private Button gotoBtn=null;
	private double mlon=0.0;
	private double mlat=0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		HideTitle();
		setContentView(R.layout.hhtcall);
		
		MyApp=(App)this.getApplication();
		
		MyApp.setmCityID(null);
		MyApp.setmBlockID(null);
		MyApp.setmStoreAddr(null);
		MyApp.setmStoreFlag(null);
		MyApp.setmStoreName(null);
		
		sp = this.getSharedPreferences("LMM", Context.MODE_PRIVATE);
		
		//启动GPS后台服务
		Intent intentGPS = new Intent(HHTCallActivity.this, GPSService.class);
		startService(intentGPS);
		
		//通过线程启动服务
		GPSThread thdGPS=new GPSThread(HHTCallActivity.this);
		thdGPS.start();
		
		flag=false;
		isSDCard=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(getApplicationContext(), R.string.sdcarderror ,Toast.LENGTH_LONG).show();
		}
		
		init();
		initGPS();
		this.addContentView(new CenterPointView(this), 
							new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		marquee_txt=(TextView) findViewById(R.id.marquee_txt);
		marquee_txt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(HHTCallActivity.this,ShowInfoActivity.class);
				startActivity(intent);				
			}
		});
		
		BlockIDTxt = (TextView) findViewById(R.id.BlockIDTxt);
		SalesIDTxt = (TextView) findViewById(R.id.SalesIDTxt);
		
		BlockIDTxt.setOnClickListener(mBlockIDTxtOnclick);
		SalesIDTxt.setOnClickListener(mSalesIDTxtOnclick);
		
		saveBtn = (Button) findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(mSaveBtnOnclick);
		
		gotoBtn=(Button) findViewById(R.id.gotoBtn);		
		gotoBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
			}
		});
	}
	
	OnClickListener mSalesIDTxtOnclick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
				 String mPid=SalesIDTxt.getText().toString();
				 
				 if(mPid!=null && mPid.length()>0 && mPid.startsWith("L")){
					 
					 List<NewPoint> NieHisPoint_list=new ArrayList<NewPoint>();
					 NieHisPoint_list=mHHTCallNewStoreHelp.getPoint(mPid);
					 
					 if(NieHisPoint_list!=null && NieHisPoint_list.size()>0){
						 DrawNewSalesPoint(NieHisPoint_list);
						 NewPoint np=NieHisPoint_list.get(0);
						 
						 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(np.Y),Double.valueOf(np.X)), 16));
					 }else{
						 if(!isSDCard){
							 Toast.makeText(getApplicationContext(), "请检查SD卡，获取售点数据失败！",Toast.LENGTH_LONG).show();
						 }else{
							 Toast.makeText(getApplicationContext(), "数据库中无此售点信息！",Toast.LENGTH_LONG).show();
						 }						 
					 }
				 }else if(mPid!=null && mPid.startsWith("qc")){

					String mQCX=MyApp.getmQCX();
					String mQCY=MyApp.getmQCY();
						
					if(mQCX!=null && mQCY!=null){
						aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(mQCY), Double.valueOf(mQCX)), 16));
					}
					 
				 }else{
					 List<NieHisPoint> NieHisPoint_list=new ArrayList<NieHisPoint>();
					 List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
					 
					 NieHisPoint_list=mHHTCallPointHelp.getSalesPointID(mPid);
					 NewPoint_list=mHHTCallNewStoreHelp.getPoint(mPid);
					 
					 if(NewPoint_list!=null && NewPoint_list.size()>0){
						 
						 DrawNewSalesPoint(NewPoint_list);
						 NewPoint np=NewPoint_list.get(0);
						 
						 float px=Float.valueOf(np.X);
						 float py=Float.valueOf(np.Y);
						 
						 if(px!=0 && py!=0){
							 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(py,px), 17));
						 }else{
							 Toast.makeText(getApplicationContext(), "该售点无坐标信息！",Toast.LENGTH_LONG).show();
						 }						 
					 }else if(NieHisPoint_list!=null && NieHisPoint_list.size()>0){
						 
						 DrawSalesPoint(NieHisPoint_list);						 
						 NieHisPoint np=NieHisPoint_list.get(0);
						 
						 float px=np.getPX();
						 float py=np.getPY();
						 
						 if(px!=0 && py!=0){
							 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(np.getPY(),np.getPX()), 16));
						 }else{
							 Toast.makeText(getApplicationContext(), "该售点无坐标信息！",Toast.LENGTH_LONG).show();
						 }
						 
					 }else{
						 if(!isSDCard){
							 Toast.makeText(getApplicationContext(), "请检查SD卡，获取售点数据失败！",Toast.LENGTH_LONG).show();
						 }else{
							 Toast.makeText(getApplicationContext(), "数据库中无此售点信息！",Toast.LENGTH_LONG).show();
						 }							 
					 }
				 }
			 } 			 
	};
	
	
	OnClickListener mBlockIDTxtOnclick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			 float vx=0.0f;
			 float vy=0.0f;
			 
			 if(MyApp!=null){
				 vx=MyApp.getmBlockCenterX();
				 vy=MyApp.getmBlockCenterY();
			 }
			 
			 if(vx!=0 && vy!=0){
				 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(vy,vx), 16));	
			 }			 				 
		}
	};
	
	OnClickListener mSaveBtnOnclick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			aMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			
			double distance=0.0;	//当前GPS位置与中心点GPS位置距离
			double cpGPSx=0.0;		//中心点GPS经度
			double cpGPSy=0.0;		//中心点GPS纬度
			double gpspx=0.0;		//当前位置GPS经度
			double gpspy=0.0;		//当前位置GPS纬度
			double cpX2ll=0.0;		//中心点GPS经度
			double cpY2ll=0.0;		//中心点GPS纬度
			
			String mSalesid=SalesIDTxt.getText().toString();
			
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
			
			cpX2ll=Double.valueOf(df.format(cpX2ll));
			cpY2ll=Double.valueOf(df.format(cpY2ll));
			
			if(mSalesid.startsWith("L")){
				//新增售点
				List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
				
				NewPoint_list=mHHTCallNewStoreHelp.getPoint(mSalesid);
				
				if(NewPoint_list!=null && NewPoint_list.size()>0){
					NewPoint np=NewPoint_list.get(0);
					gpspx=Double.valueOf(np.X);
					gpspy=Double.valueOf(np.Y);
				}else{									
					gpspx=0.0f;
					gpspy=0.0f;
				}				
			}else if(mSalesid.startsWith("qc")){
				//QC检查
				gpspx=Double.valueOf(MyApp.getmQCX());
				gpspy=Double.valueOf(MyApp.getmQCY());
			}else{
				//历史售点
				List<NieHisPoint> NieHisPoint_list=new ArrayList<NieHisPoint>();
				List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
				
				NewPoint_list=mHHTCallNewStoreHelp.getPoint(mSalesid);				
				NieHisPoint_list=mHHTCallPointHelp.getSalesPointID(mSalesid);
				
				if(NewPoint_list!=null && NewPoint_list.size()>0){
					
					NewPoint nhp=NewPoint_list.get(0);
					gpspx=Double.valueOf(nhp.X);
					gpspy=Double.valueOf(nhp.Y);
					
				}else if(NieHisPoint_list!=null && NieHisPoint_list.size()>0){
					
					NieHisPoint nhp=NieHisPoint_list.get(0);
					gpspx=Double.valueOf(nhp.getPX());
					gpspy=Double.valueOf(nhp.getPY());					
				}				
			}
			
			if(MyApp!=null){
				
				MyApp.setmX(String.valueOf(cpX2ll));
				MyApp.setmY(String.valueOf(cpY2ll));
				
				LonLat2Distance ll2d=new LonLat2Distance();
				
				if(MyApp.getmX()!=null && MyApp.getmY()!=null){
					cpGPSx=Double.valueOf(MyApp.getmX());
					cpGPSy=Double.valueOf(MyApp.getmY());
				}
				
				if(gpspx!=0 && gpspy!=0){
					//与历史坐标计算距离
					distance=ll2d.DistanceOfTwoPoints(gpspx, gpspy, cpGPSx, cpGPSy);
				}else{
					distance=0.0f;
				}
					
				MyApp.setmDistance(String.valueOf(distance));
			}
				
			AlertDialog.Builder builder = new AlertDialog.Builder(HHTCallActivity.this);
			int DisStandard=0;
			if(mSalesid.startsWith("qc")){
				DisStandard=MapConstants.QC_OFF_SET;
			}else{
				DisStandard=MapConstants.OFF_SET;
			}
			if(Math.abs(distance)< DisStandard){
				//符合精度
				builder.setTitle("是否保存？")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setNegativeButton("取消", null);
				
				builder.setPositiveButton("保存",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int which){
								
								Intent RsltIntent = new Intent();
								
								String mPid=SalesIDTxt.getText().toString();
								if(mPid.startsWith("qc")){
									mPid=mPid.substring(2);
								}
								
								if(MyApp!=null){
									
									List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
									NewPoint_list=mHHTCallNewStoreHelp.getPoint(mPid);
									
									//手动绘制坐标
									String X=MyApp.getmX();
									String Y=MyApp.getmY();
									
									//GPS定位原始坐标
									String GPSX="";
									String GPSY="";
									String ACCUR="";
									
									if(MyApp.getmGPSLon()!=null && MyApp.getmGPSLat()!=null && MyApp.getmGPSAccu()!=null){
										GPSX = df.format(Double.valueOf(MyApp.getmGPSLon()));								
										GPSY = df.format(Double.valueOf(MyApp.getmGPSLat()));		
										ACCUR= df2.format(Double.valueOf(MyApp.getmGPSAccu()));
										Toast.makeText(getApplicationContext(), "GPS信息已获取",Toast.LENGTH_LONG).show();
									}else{
										GPSX = 0+"";								
										GPSY = 0+"";		
										ACCUR= 0+"";
										Toast.makeText(getApplicationContext(), "未获取GPS信息",Toast.LENGTH_LONG).show();
									}
									
									//纠偏后GPS坐标
									String GPSX1="";
									String GPSY1="";
									
									if(MyApp.getmGPSX()!=null && MyApp.getmGPSY()!=null){
										GPSX1=df.format(Double.valueOf(MyApp.getmGPSX()));
										GPSY1=df.format(Double.valueOf(MyApp.getmGPSY()));	
									}else{
										GPSX1=0+"";
										GPSY1=0+"";	
									}
									
									
									if(NewPoint_list!=null && NewPoint_list.size()>0){
										//说明该售点之前已经传入过，此时应该更新表中手动描点坐标信息
										long result=mHHTCallNewStoreHelp.NewPointUpdate(mPid,Float.valueOf(X),Float.valueOf(Y),"无");
										
										if(1==result){
											Toast.makeText(getApplicationContext(), "已更新",Toast.LENGTH_LONG).show();	
										}else if(0==result){
											Toast.makeText(getApplicationContext(), "请检查SD卡是否能正常读取，更新失败！",Toast.LENGTH_LONG).show();	
										}									
									}else{
										//若查不到该点信息则将该点插入该数据库
										long result=mHHTCallNewStoreHelp.NewPointInsert(mPid,
																			Float.valueOf(GPSX), 
																			Float.valueOf(GPSY), 
																			Float.valueOf(X), 
																			Float.valueOf(Y), 																
																			Float.valueOf(GPSX1), 
																			Float.valueOf(GPSY1), 
																			Float.valueOf(ACCUR),
																			"无" 
																			);									
										if(1==result){
											Toast.makeText(getApplicationContext(), "已保存",Toast.LENGTH_LONG).show();	
										}else if(0==result){
											Toast.makeText(getApplicationContext(), "请检查SD卡是否能正常读取，保存失败！",Toast.LENGTH_LONG).show();	
										}	
									}
									
									
									RsltIntent.putExtra("PID", mPid);
									
									//手动描点坐标
									RsltIntent.putExtra("X", X);
									RsltIntent.putExtra("Y", Y);
									
									//纠偏后坐标
									RsltIntent.putExtra("GPSX1", GPSX1);
									RsltIntent.putExtra("GPSY1", GPSY1);						
									
									//原始坐标和精度
									RsltIntent.putExtra("GPSX", GPSX);								
									RsltIntent.putExtra("GPSY", GPSY);		
									RsltIntent.putExtra("ACCUR", ACCUR);
									
									RsltIntent.putExtra("REASON", "无");
								}			
								HHTCallActivity.this.setResult(RESULT_OK, RsltIntent);
					            HHTCallActivity.this.finish(); 
							}
					});					
			}else{
				//精度不符合要求
				builder.setTitle("精度不符合要求，是否重新采集？")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setNegativeButton("否", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
									AlertDialog.Builder NoBuilder = new AlertDialog.Builder(HHTCallActivity.this);	
									LayoutInflater mInflater = LayoutInflater.from(HHTCallActivity.this);
									final View dialogView = mInflater.inflate(R.layout.hhtreason,null); 
									
									NoBuilder.setTitle("请输入网点编号和原因")
											 .setIcon(android.R.drawable.ic_dialog_info)
											 .setView(dialogView)
											 .setNegativeButton("取消", null);
					
									NoBuilder.setPositiveButton("保存",
											new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog,int which){
													
													EditText ptReason=(EditText) dialogView.findViewById(R.id.pointReason);
													String reason=ptReason.getText().toString();
													String mSalesID=SalesIDTxt.getText().toString();
													if(mSalesID.startsWith("qc")){
														mSalesID=mSalesID.substring(2);
													}
													Intent RsltIntent = new Intent();
													MyApp.setmReason(reason);
													
													if(MyApp!=null){
														
														List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
														NewPoint_list=mHHTCallNewStoreHelp.getPoint(mSalesID);
														
														//手动绘制坐标
														String X=MyApp.getmX();
														String Y=MyApp.getmY();
														
														//GPS定位原始坐标
														String GPSX="";
														String GPSY="";
														String ACCUR="";
														
														if(MyApp.getmGPSLon()!=null && MyApp.getmGPSLat()!=null && MyApp.getmGPSAccu()!=null){
															GPSX = df.format(Double.valueOf(MyApp.getmGPSLon()));								
															GPSY = df.format(Double.valueOf(MyApp.getmGPSLat()));		
															ACCUR= df2.format(Double.valueOf(MyApp.getmGPSAccu()));
															
															Toast.makeText(getApplicationContext(), "GPS信息已获取",Toast.LENGTH_LONG).show();
														}else{
															GPSX = 0+"";								
															GPSY = 0+"";		
															ACCUR= 0+"";
															Toast.makeText(getApplicationContext(), "未获取GPS信息",Toast.LENGTH_LONG).show();
														}
														
														//纠偏后GPS坐标
														String GPSX1="";
														String GPSY1="";
														
														if(MyApp.getmGPSX()!=null && MyApp.getmGPSY()!=null){
															GPSX1=df.format(Double.valueOf(MyApp.getmGPSX()));
															GPSY1=df.format(Double.valueOf(MyApp.getmGPSY()));	
														}else{
															GPSX1=0+"";
															GPSY1=0+"";	
														}
														
														String REASON="";
														
														if(MyApp.getmReason()!=null){
															REASON=MyApp.getmReason();
														}else{
															REASON="无";
														}
														
														if(NewPoint_list!=null && NewPoint_list.size()>0){
															//说明该售点之前已经传入过，此时应该更新表中手动描点坐标信息
															long result=mHHTCallNewStoreHelp.NewPointUpdate(mSalesID,Float.valueOf(X),Float.valueOf(Y),REASON);
															
															if(1==result){
																Toast.makeText(getApplicationContext(), "已更新",Toast.LENGTH_LONG).show();	
															}else if(0==result){
																Toast.makeText(getApplicationContext(), "请检查SD卡是否能正常读取，更新失败！",Toast.LENGTH_LONG).show();	
															}	
	
														}else{
															long result=mHHTCallNewStoreHelp.NewPointInsert(mSalesID,
																								Float.valueOf(GPSX), 
																								Float.valueOf(GPSY), 
																								Float.valueOf(X), 
																								Float.valueOf(Y), 																
																								Float.valueOf(GPSX1), 
																								Float.valueOf(GPSY1), 
																								Float.valueOf(ACCUR),
																								REASON );									
															if(1==result){
																Toast.makeText(getApplicationContext(), "已保存",Toast.LENGTH_LONG).show();	
															}else if(0==result){
																Toast.makeText(getApplicationContext(), "请检查SD卡是否能正常读取，保存失败！",Toast.LENGTH_LONG).show();	
															}	
														}
														
														RsltIntent.putExtra("PID", mSalesID);
														RsltIntent.putExtra("X", X);
														RsltIntent.putExtra("Y", Y);
														
														RsltIntent.putExtra("GPSX1", GPSX1);
														RsltIntent.putExtra("GPSY1", GPSY1);														
														
														RsltIntent.putExtra("GPSX", GPSX);								
														RsltIntent.putExtra("GPSY", GPSY);		
														RsltIntent.putExtra("ACCUR",ACCUR);

														RsltIntent.putExtra("REASON", REASON);															
													}
													
													HHTCallActivity.this.setResult(RESULT_OK, RsltIntent);
										            HHTCallActivity.this.finish(); 														
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
		}};
	
	
	 
	 public List<LatLng> Str2LatLng(String coord){
		 
		 String mCoord=coord;
		 String[] StrArr=mCoord.split(",");
		 List<LatLng> latng_list=new ArrayList<LatLng>();
		 
		 for(int i=0;i<StrArr.length;i++){
			 
			 String lat=StrArr[i].trim().split(" ")[1];
			 if(lat.startsWith("(")){
				 lat=lat.substring(1);
			 }
			 if(lat.endsWith(")")){
				 lat=lat.substring(0, lat.length()-1);
			 }
			 
			 String lng=StrArr[i].trim().split(" ")[0];
			 if(lng.startsWith("(")){
				 lng=lng.substring(1);
			 }
			 if(lng.endsWith(")")){
				 lng=lng.substring(0, lng.length()-1);
			 }			 
			 
			 LatLng latng=new LatLng(Double.valueOf(lat), 
					 				 Double.valueOf(lng)); 
			 latng_list.add(latng);
		 }	
		 
		 return latng_list;		 
	 }
	
	
	private void setUpMap() {
		
		//我的位置功能
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker));
		myLocationStyle.strokeColor(Color.RED);
		myLocationStyle.strokeWidth(1);
		aMap.setMyLocationStyle(myLocationStyle);
		
		mAMapLocationManager = LocationManagerProxy.getInstance(HHTCallActivity.this);
		aMap.setLocationSource(this);
		aMap.setMyLocationEnabled(true);//设置为true表示系统定位按钮显示并响应点击，false表示隐藏，默认是false
		
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
		
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
		aMap.setOnMarkerDragListener(this);// 设置marker可拖拽事件监听器
		
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapConstants.SHANGHAI, 20));
	}
	
	
	/**
	 * 初始化AMap对象
	 */
	private void init() {
		
		if (aMap == null) {			
			aMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();	
//			aMap.getUiSettings().
			if (AMapUtil.checkReady(this, aMap)) {
				setUpMap();
			}
		}
		
		mHHTCallPointHelp= new HHTCallPointHelp();
		mHHTCallPointHelp.init(HHTCallActivity.this);
		
		mHHTCallBlockHelp= new HHTCallBlockHelp();
		mHHTCallBlockHelp.init(HHTCallActivity.this);
		
		mHHTCallNewStoreHelp=new HHTCallNewStoreHelp();
		mHHTCallNewStoreHelp.init(HHTCallActivity.this);
		
		mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
		mContents = getLayoutInflater().inflate(R.layout.custom_info_contents,null);
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
                PendingIntent.getBroadcast(HHTCallActivity.this, 0, GPSIntent, 0).send();  
            } catch (CanceledException e) {  
                e.printStackTrace();  
            } 
        }		
	}	

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		//PID、CITYID、STOREID
		/**
		 * 售点编号PID: Nlinx code
		        城市&地图块编号CITYID: 城市编号$地图块编号
		        店铺信息STOREID:   店铺名称$店铺地址$附近标志
		*/
		
		String mPid=null;
		String mCityId_BlockID=null;
		String mStoreID=null;
		
		String mBlockID="";
		String mCityID="";
		String mStoreName="";
		String mStoreAddr="";
		String mStoreFlag="";
		
		TelephonyManager phoneMgr=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
		
		String mMillis=String.valueOf(System.currentTimeMillis());		
		mGetIntent=this.getIntent();
		
//		String mSRC=mGetIntent.getStringExtra("SRC");
//		Boolean isFromHHT=false;
//		if(mSRC==null){
//			isFromHHT=true;
//		}
		
		if(mGetIntent!=null){
			
			mPid=mGetIntent.getStringExtra("PID");
			if(mPid!=null){
				if(mPid.startsWith("show")){
					MyApp.setPID(mPid.substring(4));					
					MyApp.setmShowMode(mPid);
				}else if(mPid.startsWith("qc")){
					MyApp.setPID(mPid);
					MyApp.setmQCSTR(mPid);
				}else{
					MyApp.setPID(mPid);
				}				
			}
			
			mCityId_BlockID=mGetIntent.getStringExtra("CITYID");
			if(mCityId_BlockID!=null){
				MyApp.setmCITYID(mCityId_BlockID);			
			}
			
			mStoreID=mGetIntent.getStringExtra("STOREID");
			if(mStoreID!=null){
				MyApp.setmSTOREID(mStoreID);
			}			
		}
		
		String validation_key=sp.getString("RSLT_KEY", null);		
		
		if(validation_key!=null && validation_key!=""){
			
			String RSLT_KEY=sp.getString("RSLT_KEY", null);
			String SERIAL_KEY=sp.getString("SERIAL_KEY", null);
			
			String deviceNo=phoneMgr.getDeviceId();
			
			String demo_validation=null;
			
			if(SERIAL_KEY!=null && deviceNo!=null){
				demo_validation=SoftKey.GetEncrypKey(SERIAL_KEY, deviceNo);
			}
			
			if(RSLT_KEY!=null && demo_validation!=null && demo_validation.equals(RSLT_KEY)){
				
				String strShowMode=MyApp.getmShowMode();
				
				if(strShowMode!=null && strShowMode.startsWith("show")){
					saveBtn.setClickable(false);
					saveBtn.setVisibility(View.INVISIBLE);
					MyApp.setmShowMode(null);
				}else{
					saveBtn.setClickable(true);
					saveBtn.setVisibility(View.VISIBLE);
				}
				
				mPid=MyApp.getPID();
				
				if(mPid!=null && mPid.length()>0 && mPid.equals("NewStore")){
					
					mPid="L"+deviceNo.substring(3, 5)+mMillis.substring(10)+mRand.nextInt(100);
					MyApp.setmNewStoreID(mPid);
					SalesIDTxt.setText(mPid);
					saveBtn.setClickable(true);
					isGPSShowCenter=true;
					
				}else if(mPid!=null && mPid.length()>0 && mPid.startsWith("L")){
					MyApp.setmNewStoreID(mPid);
					SalesIDTxt.setText(mPid);
					saveBtn.setClickable(true);
					
				}else if(mPid!=null && mPid.length()>0 && mPid.startsWith("qc")){
					
					String mQCID=null;
					String mQCX=null;
					String mQCY=null;
					
					if(mPid.contains("$") && mPid.split("\\$").length==3){
						
						mQCID=mPid.split("\\$")[0];
						MyApp.setmQCID(mQCID);
						
						mQCX=mPid.split("\\$")[1];
						MyApp.setmQCX(mQCX);
						
						mQCY=mPid.split("\\$")[2];
						MyApp.setmQCY(mQCY);
						
						SalesIDTxt.setText(mQCID);
						
						if(mQCX!=null && mQCY!=null){
							aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(mQCY),Double.valueOf(mQCX)))
									.snippet(mQCID).icon(BitmapDescriptorFactory.fromResource(R.drawable.poingloc2)));
							aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(mQCY), Double.valueOf(mQCX)), 16));
						}						
					}					
					
				}else{
					if(mPid!=null && mPid.length()>0){
						SalesIDTxt.setText(mPid);
						MyApp.setPID(mPid);
					}else{
						SalesIDTxt.setClickable(false);
					}
				}
				
				mCityId_BlockID=MyApp.getmCITYID();
				
				if(mCityId_BlockID!=null && mCityId_BlockID.contains("$") && mCityId_BlockID.split("\\$").length==2){
					mCityID=mCityId_BlockID.split("\\$")[0];
					MyApp.setmCityID(mCityID);
					
					mBlockID=mCityId_BlockID.split("\\$")[1];
					if(mBlockID!=null && mBlockID.length()>0){
						MyApp.setmBlockID(mBlockID);
						BlockIDTxt.setText(mBlockID);
					}					
				}else{
					BlockIDTxt.setClickable(false);
				}
				
				mStoreID=MyApp.getmSTOREID();
				
				if(mStoreID!=null && mStoreID.contains("$") && mStoreID.split("\\$").length==3){
					
					mStoreName=mStoreID.split("\\$")[0];
					MyApp.setmStoreName(mStoreName);
					
					mStoreAddr=mStoreID.split("\\$")[1];
					MyApp.setmStoreAddr(mStoreAddr);
					
					mStoreFlag=mStoreID.split("\\$")[2];
					MyApp.setmStoreFlag(mStoreFlag);						
					
					marquee_txt.setText("店铺名称："+mStoreName+"   店铺地址："+mStoreAddr+"   附近标志："+mStoreFlag);
				}
				
				 List<NieBlock> NieBlock_list=new ArrayList<NieBlock>();
				 List<NieHisPoint> NieHisPoint_list=new ArrayList<NieHisPoint>();
				 List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();
				 
				 if(mHHTCallBlockHelp!=null){
					 
					 if(mBlockID!=null && mBlockID.length()>0){
						 BlockIDTxt.setText(mBlockID);
						 NieBlock_list=mHHTCallBlockHelp.getNieBlock(mBlockID, mCityID);
						 
						 if(NieBlock_list!=null && NieBlock_list.size()>0){
							 if(MyApp!=null){
								 MyApp.setNieBlock_list(NieBlock_list);
							 }
							 
							 DrawBlock(NieBlock_list);
						 }else{
							 if(!isSDCard){
								 Toast.makeText(getApplicationContext(), "请检查SD卡，获取地图块数据失败！",Toast.LENGTH_LONG).show();
							 }else{
								 Toast.makeText(getApplicationContext(), "数据库中没有对应地图块信息！",Toast.LENGTH_LONG).show();
								 /**
								  * mlon=lon;
									mlat=lat;
								  */
								 SharedPreferences sp=getSharedPreferences("LONLAT", 0);
								 String lonStr=sp.getString("LON", null);
								 String latStr=sp.getString("LAT", null);								 
								 if(latStr!=null && lonStr!=null){
									 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(latStr), Double.valueOf(lonStr)), 20));
								 }
								 
							 }
						 }			
					 }			
				 }
				 
				 if(mHHTCallPointHelp!=null){
					 //传入历史售点，需要去两个数据库去查询数据				 
					 if(mPid!=null && mPid.length()>0 && !mPid.startsWith("L") && !mPid.startsWith("qc")){
						 NieHisPoint_list=mHHTCallPointHelp.getSalesPointID(mPid);
						 NewPoint_list=mHHTCallNewStoreHelp.getPoint(mPid);
					 }			 
					 
					 if(NewPoint_list!=null && NewPoint_list.size()>0){
						 if(MyApp!=null){
							 MyApp.setNewPoint_list(NewPoint_list);
						 }
						 
						 NewPoint np=NewPoint_list.get(0);
						 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(np.Y), Double.valueOf(np.X)), 16));
						 DrawNewSalesPoint(NewPoint_list);
						 
					 }else if(NieHisPoint_list!=null && NieHisPoint_list.size()>0){
						 
						 if(MyApp!=null){
							 MyApp.setNieHisPoint_list(NieHisPoint_list);
						 }
						 
						 NieHisPoint nhp=NieHisPoint_list.get(0);
						 
						 float py=nhp.getPY();
						 float px=nhp.getPX();
						 if(py!=0 && px!=0){
							 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(py, px), 16)); 
						 }else{
							 Toast.makeText(getApplicationContext(), "该售点无坐标信息！",Toast.LENGTH_LONG).show();
							 //定位到当前GPS位置
//							GPSX1=df.format(Double.valueOf(MyApp.getmGPSX()));
//							GPSY1=df.format(Double.valueOf(MyApp.getmGPSY()));	
							 
							 if(MyApp.getmGPSY()!=null && MyApp.getmGPSX()!=null){
								 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(MyApp.getmGPSY()), 
										  Double.valueOf(MyApp.getmGPSX())), 16)); 								 
							 }							 
						 }	
						 
						 DrawSalesPoint(NieHisPoint_list);
					 }else{
						 if(!isSDCard){
							 Toast.makeText(getApplicationContext(), "请检查SD卡，获取售点数据失败！",Toast.LENGTH_LONG).show(); 
						 }else{
							 if(!mPid.startsWith("qc")){								 
								 SharedPreferences sp=getSharedPreferences("LONLAT", 0);
								 String lonStr=sp.getString("LON", null);
								 String latStr=sp.getString("LAT", null);								 
								 if(latStr!=null && lonStr!=null){
									 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(latStr), Double.valueOf(lonStr)), 20));
								 }
								Toast.makeText(getApplicationContext(), "数据库中没有对应售点信息！",Toast.LENGTH_LONG).show();
							 }							 
						 }		
					 }
					 NewPoint_list.clear();		 
				 }
				 
				 String newStoreID=MyApp.getmNewStoreID();
				 
				 if(mHHTCallNewStoreHelp!=null && newStoreID!=null){
					 
					 if(mPid!=null && mPid.length()>0 && mPid.startsWith("L")){
						 NewPoint_list=mHHTCallNewStoreHelp.getPoint(mPid);
					 }			 
					 
					 if(NewPoint_list!=null && NewPoint_list.size()>0){
						 
						 if(MyApp!=null){
							 MyApp.setNewPoint_list(NewPoint_list);
						 }
						 
						 NewPoint np=NewPoint_list.get(0);
						 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(np.Y), Double.valueOf(np.X)), 16));
						 DrawNewSalesPoint(NewPoint_list);
						 
					 }else{
						 if(!isSDCard){
							 Toast.makeText(getApplicationContext(), "请检查SD卡，获取售点数据失败！",Toast.LENGTH_LONG).show(); 
						 }					 
					 }				 
					 NewPoint_list.clear();
				 }
				
				String mBlockQryFlag=MyApp.getmBlockQryFlag();
				String mBlockQryCoord=MyApp.getmBlockQryCoord();
				
				if(mBlockQryCoord!=null && mBlockQryCoord!="" && mBlockQryFlag.equals("ReturnFromBlockQry")){
					DrawBlockByCoord(mBlockQryCoord);
				}
				
				MyApp.setPID(null);
			}			
		}else{
			Intent intent = new Intent(HHTCallActivity.this,ValidationActivity.class);
			intent.putExtra("SRC", "HHTCallActivity");
			startActivity(intent);
			HHTCallActivity.this.finish();
		}
	}
	
	public void DrawNewSalesPoint(List<NewPoint> NewPoint_list){
		Log.i("TAG", "DrawNewSalesPoint");
		if(NewPoint_list!=null && NewPoint_list.size()>0){
			for(NewPoint np:NewPoint_list){
				aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(np.Y),Double.valueOf(np.X)))
						.snippet(np.ID).icon(BitmapDescriptorFactory.fromResource(R.drawable.poingloc2)));
				
			}			
		}		
	}
	
	
	public void DrawSalesPoint(List<NieHisPoint> NieHisPoint_list){
		Log.i("TAG", "DrawSalesPoint");
		if(NieHisPoint_list!=null && NieHisPoint_list.size()>0){
			for(NieHisPoint np:NieHisPoint_list){
				aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(np.getPY()),Double.valueOf(np.getPX())))
						.snippet(np.getPID()).icon(BitmapDescriptorFactory.fromResource(R.drawable.poingloc2)));
				
			}			
		}		
	}
	
	public void DrawSalesPointHelp(List<NieHisPoint> NieHisPoint_list){
		Log.i("TAG", "DrawSalesPointHelp");
		if(NieHisPoint_list!=null && NieHisPoint_list.size()>0){
			for(NieHisPoint np:NieHisPoint_list){
				aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(np.getPY()),Double.valueOf(np.getPX())))
						.snippet(np.getPID()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_location)));
			}			
		}		
	}

	
	public void DrawBlock(List<NieBlock> NieBlock_list){
		Log.i("TAG", "DrawBlock");
		
		 if(NieBlock_list!=null && NieBlock_list.size()>0){
			 
			 List<LatLng> latng_list=new ArrayList<LatLng>();
			 
			 for(NieBlock re:NieBlock_list){
				 
				 String blockCoord=re.getBlockCoord().trim();
				 
				 if(blockCoord.startsWith("POLYGON")){
					 blockCoord=blockCoord.replace("POLYGON ((", "").replace("))", "").trim();
					 Log.i("SQL",blockCoord);
					 latng_list=Str2LatLng(blockCoord);
				 }else if(blockCoord.startsWith("GEOMETRYCOLLECTION")){
					 blockCoord=blockCoord.substring(blockCoord.indexOf("POLYGON (("), blockCoord.indexOf("))")).replace("POLYGON ((", "");
					 Log.i("SQL",blockCoord);
					 latng_list=Str2LatLng(blockCoord);
				 }
				 
				 if(latng_list!=null && latng_list.size()>0){
					 
					 float distance=0.0f;
					 
					 Log.i("PRELENGTH", String.valueOf(latng_list.size()));
					 for(int j=0;j<latng_list.size()-1;j++){						 
						 distance = AMapUtils.calculateLineDistance(latng_list.get(j),latng_list.get(j+1));
						 Log.i("SQL", String.valueOf(distance));
						 if(distance<15){
							 latng_list.remove(j+1);
						 }						  
					 }
					 
					 Log.i("AFTERLENGTH", String.valueOf(latng_list.size()));
					 for(int j=0;j<latng_list.size()-1;j++){
						 aMap.addPolyline((new PolylineOptions())
									.add(latng_list.get(j),latng_list.get(j+1))
									.color(Color.RED)
									.width(8));
					 }
					
				 }
				 
				 if(MyApp!=null){
					 MyApp.setmBlockCenterX(Float.valueOf(re.getCenterX()));
					 MyApp.setmBlockCenterY(Float.valueOf(re.getCenterY()));
				 }
				 
				 if(MyApp.getmQCSTR()==null){
					 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.valueOf(MyApp.getmBlockCenterY()),
							  Double.valueOf(MyApp.getmBlockCenterX())), 15));
				 }
			 }
		 }	
		
	}
	
	public void DrawBlockSalesPoint(List<ReturnEntity> ReturnEntity_list){
		Log.i("TAG", "DrawBlockSalesPoint");
		 if(ReturnEntity_list!=null && ReturnEntity_list.size()>0){
			 
			 List<LatLng> latng_list=new ArrayList<LatLng>();
			 
			 for(ReturnEntity re:ReturnEntity_list){
				 
				 BlockIDTxt.setText(re.getBlockID());
				 SalesIDTxt.setText(re.getPID());
				 
				 aMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(re.getPY()),Double.valueOf(re.getPX())))
							.snippet(re.getPID()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_location)));
				 
				 String blockCoord=re.getBlockCoord().trim();
				 
				 if(blockCoord.startsWith("POLYGON")){
					 blockCoord=blockCoord.replace("POLYGON ((", "").replace("))", "").trim();
					 Log.i("SQL",blockCoord);
					 latng_list=Str2LatLng(blockCoord);
				 }else if(blockCoord.startsWith("GEOMETRYCOLLECTION")){
					 blockCoord=blockCoord.substring(blockCoord.indexOf("POLYGON (("), blockCoord.indexOf("))")).replace("POLYGON ((", "");
					 Log.i("SQL",blockCoord);
					 latng_list=Str2LatLng(blockCoord);
				 }
			 }
			 
			 float vx=0.0f;
			 float vy=0.0f;
			 
			 for(LatLng ll:latng_list){
				 vx+=ll.longitude;
				 vy+=ll.latitude;					 
			 }
			 
			 vx=vx/latng_list.size();
			 vy=vy/latng_list.size();				 
			 
			 if(latng_list!=null && latng_list.size()>0){
				 
				 float distance=0.0f;
				 Log.i("PRELENGTH", String.valueOf(latng_list.size()));
				 for(int j=0;j<latng_list.size()-1;j++){						 
					 distance = AMapUtils.calculateLineDistance(latng_list.get(j),latng_list.get(j+1));
					 Log.i("SQL", String.valueOf(distance));
					 if(distance<15){
						 latng_list.remove(j+1);
					 }						  
				 }
				 Log.i("AFTERLENGTH", String.valueOf(latng_list.size()));
				 for(int j=0;j<latng_list.size()-1;j++){
					 aMap.addPolyline((new PolylineOptions())
								.add(latng_list.get(j),latng_list.get(j+1))
								.color(Color.RED)
								.width(8));
				 }
				
			 }	
			 
			 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(vy,vx), 16));
		 }	
		
	}
	
	public void DrawBlockByCoord(String blockCoord){
		Log.i("TAG", "DrawBlockByCoord");
		 List<LatLng> latng_list=new ArrayList<LatLng>();
		
		 if(blockCoord.startsWith("POLYGON")){
			 blockCoord=blockCoord.replace("POLYGON ((", "").replace("))", "").trim();
			 Log.i("SQL",blockCoord);
			 latng_list=Str2LatLng(blockCoord);
		 }else if(blockCoord.startsWith("GEOMETRYCOLLECTION")){
			 blockCoord=blockCoord.substring(blockCoord.indexOf("POLYGON (("), blockCoord.indexOf("))")).replace("POLYGON ((", "");
			 Log.i("SQL",blockCoord);
			 latng_list=Str2LatLng(blockCoord);
		 } 
		 
		 float vx=0.0f;
		 float vy=0.0f;
		 
		 for(LatLng ll:latng_list){
			 vx+=ll.longitude;
			 vy+=ll.latitude;					 
		 }
		 
		 vx=vx/latng_list.size();
		 vy=vy/latng_list.size();	
		 
		 aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(vy,vx), 16));	
		 
		 if(latng_list!=null && latng_list.size()>0){
			 
			 float distance=0.0f;
			 Log.i("PRELENGTH", String.valueOf(latng_list.size()));
			 for(int j=0;j<latng_list.size()-1;j++){						 
				 distance = AMapUtils.calculateLineDistance(latng_list.get(j),latng_list.get(j+1));
				 Log.i("SQL", String.valueOf(distance));
				 if(distance<15){
					 latng_list.remove(j+1);
				 }						  
			 }
			 Log.i("AFTERLENGTH", String.valueOf(latng_list.size()));
			 for(int j=0;j<latng_list.size()-1;j++){
				 aMap.addPolyline((new PolylineOptions())
							.add(latng_list.get(j),latng_list.get(j+1))
							.color(Color.RED)
							.width(8));
			 }
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
			
			double lat=aLocation.getLatitude();
			double lon=aLocation.getLongitude();
			
			lat=Double.valueOf(df.format(lat));
			lon=Double.valueOf(df.format(lon));
			
			mlon=lon;
			mlat=lat;
			
			SharedPreferences sp=getSharedPreferences("LONLAT",0);
			Editor spEditor=sp.edit();
			spEditor.putString("LON", lon+"");
			spEditor.putString("LAT", lat+"");
			spEditor.commit();
			
//			if(flag==false){				
//				aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 16));				
//				flag=true;				
//			}
			
//			if(isGPSShowCenter){				
//				aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 20));				
//				isGPSShowCenter=false;				
//			}
			
			if(MyApp!=null){
				MyApp.setmGPSX(String.valueOf(lon));
				MyApp.setmGPSY(String.valueOf(lat));
			}
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
		    int width = metric.widthPixels;     // 屏幕宽度（像素）  
		    int height = metric.heightPixels;   // 屏幕高度（像素）
		    
			Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.indoor_loc_suc);
			canvas.drawBitmap(bmp, width/2-14, height/2+14, null) ; 
	    }  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		menu.add(Menu.NONE, Menu.FIRST + 1, 1, "辅助网点").setIcon(android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "500m POI").setIcon(android.R.drawable.ic_dialog_info);
		menu.add(Menu.NONE, Menu.FIRST + 3, 3, "地图块查询").setIcon(android.R.drawable.ic_menu_compass);
		
        return true;
	}

	Boolean isDel=false;	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case Menu.FIRST + 1: 
	        	
	        	List<NieHisPoint> NieHisPoint_list=new ArrayList<NieHisPoint>();
	        	String mBlockID=null;
	        	String mPID=null;
	        	
	        	 if(mHHTCallPointHelp!=null){ 
	        		 
	    	       	 if(MyApp!=null){
		    			 mBlockID=MyApp.getmBlockID();
		    			 //mPID=MyApp.getPID();		    			 
		    		 }
	    	       	 
	    	       	 mPID=SalesIDTxt.getText().toString();
	        		 
	        		 if(mBlockID!=null && mPID!=null){
	        			 NieHisPoint_list=mHHTCallPointHelp.getSalesPointIDInBlock(mBlockID,mPID);
	        		 }
	        		 
	        		 if(NieHisPoint_list!=null && NieHisPoint_list.size()>0){
	        	   		 if(!isDel){
		        			 DrawSalesPointHelp(NieHisPoint_list);	
		        			 isDel=true;
		        		 }else{
		        			 aMap.clear();
		        			 if(MyApp!=null){
		        				 DrawBlock(MyApp.getNieBlock_list());
		        				 DrawSalesPoint(MyApp.getNieHisPoint_list());
		        			 }
		        			 isDel=false;
		        		 }
	        		 }else{
	        			 if(!isSDCard){
	        				 Toast.makeText(getApplicationContext(), "请检查SD卡，获取售点数据失败！",Toast.LENGTH_LONG).show();
	        			 }else{
	        				 Toast.makeText(getApplicationContext(), "无辅助售点！",Toast.LENGTH_LONG).show();
	        			 }	        			
	        		 }	     	        		 		
				 }	        	 
	            break;
	        case Menu.FIRST + 2:
				break;

	        case Menu.FIRST + 3:	        	
	        	Intent intent = new Intent(HHTCallActivity.this, BlockQueryActivity.class);
	        	startActivityForResult(intent, 2);				
				break;
        }
        return false;
	}
	
	


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		switch (resultCode) { 
			case RESULT_OK:
				String blockCoord=data.getStringExtra("BLOCKCOORD");
				//String blockID=data.getStringExtra("BLOCKID");
				
				if(blockCoord!=null && blockCoord!=""){
					MyApp.setmBlockQryCoord(blockCoord);
					MyApp.setmBlockQryFlag("ReturnFromBlockQry");
					//DrawBlockByCoord(blockCoord);
				}
				
				break;				
			default:
				break;
		}
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
	public boolean onMarkerClick(Marker marker) {
		return false;
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
