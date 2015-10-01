package com.lbschina.srt.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lbschina.hht.HHTCallActivity;
import com.lbschina.srt.App;
import com.lbschina.srt.R;
import com.lbschina.srt.util.NetUtils;
import com.lbschina.srt.util.XmlCreateUtil;
import com.lbschina.validate.KsoapValidateHttp;
import com.lbschina.validate.SoftKey;

public class ValidationActivity extends Activity {
	
	private EditText validationId=null;
	private Button validationBtn=null;
	String validationStr="";
	
	//全局变量存储位置
	private App MyApp;
	//存储验证变量
	private SharedPreferences sp;	
	String mReturn=null;
	
	XmlCreateUtil mXmlCreateUtil=null;
	String sdpath = null;
	String mSavePath = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HideTitle();
		setContentView(R.layout.validationxml);
		
		MyApp=(App) this.getApplication();
		loadPhoneStatus();
		
		sp = this.getSharedPreferences("LMM", Context.MODE_PRIVATE);
		
		WifiManager wifiManager = (WifiManager)ValidationActivity.this.getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(true);
		
//		if(!NetUtils.isNetworkConnected(ValidationActivity.this)){			
//			Toast.makeText(ValidationActivity.this, "当前无网络，不可激活！", Toast.LENGTH_SHORT).show();
//			wifiManager.setWifiEnabled(true);
//		}
//		if(!NetUtils.isWifiConnected(ValidationActivity.this)){
//			Toast.makeText(ValidationActivity.this, "当前无网络，不可激活！", Toast.LENGTH_SHORT).show();
//			wifiManager.setWifiEnabled(true);
//		}
		
		validationId=(EditText) findViewById(R.id.validationId);
		
		validationBtn=(Button) findViewById(R.id.validationBtn);
		validationBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String hardwareNo="";
				String mRslt="";
				validationStr=validationId.getText().toString();
				
				if(MyApp!=null){
					hardwareNo= MyApp.getHardwareNo();
				}
				
				KsoapValidateHttp mValidate=new KsoapValidateHttp();
				
				try {
					mRslt=mValidate.GetValidateStrDEMO(validationStr,hardwareNo);
					
					if(mRslt!=null && !mRslt.equals("-1") && !mRslt.equals("-2")){
						
						MyApp.setmValidationRslt(mRslt);
						Log.i("VALIDATION_KEY_SAVE", mRslt);
						
						//存入数据
						Editor editor = sp.edit();
						editor.putString("RSLT_KEY", mRslt);
						editor.putString("SERIAL_KEY", validationStr);
						editor.commit();
						
						if(mReturn!=null && mReturn.equals("HHTCallActivity")){
							Intent intent = new Intent(ValidationActivity.this,HHTCallActivity.class);
							intent.putExtra("SRC", "ValidationActivity");
							startActivity(intent);
							ValidationActivity.this.finish();
						}else{
							Intent intent = new Intent(ValidationActivity.this,MainActivity.class);
							startActivity(intent);
							ValidationActivity.this.finish();
						}					
					}
					if(mRslt == null && (!NetUtils.isNetworkConnected(ValidationActivity.this) || !NetUtils.isWifiConnected(ValidationActivity.this))){
						Toast.makeText(ValidationActivity.this, "当前无网络，不可激活！", Toast.LENGTH_SHORT).show();
					}
					
					if(mRslt!=null && mRslt.equals("-1")){
						Toast.makeText(ValidationActivity.this, "激活码无效", Toast.LENGTH_SHORT).show();
					}
					
					if(mRslt!=null && mRslt.equals("-2")){
						Toast.makeText(ValidationActivity.this, "该激活码已使用过，请更换！", Toast.LENGTH_SHORT).show();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void loadPhoneStatus(){		
		   TelephonyManager phoneMgr=(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);		   
		   String deviceNo=phoneMgr.getDeviceId();
		   if(MyApp!=null){
			   MyApp.setHardwareNo(deviceNo);
		   }
		   Log.i("deviceNo", deviceNo);
	}

	@Override
	protected void onResume() {
		
		super.onResume();	
		
		Intent mIntent=this.getIntent();
		if(mIntent!=null){
			mReturn=mIntent.getStringExtra("SRC");
		}
		Boolean isFromHHT=false;
		
		if(mReturn!=null && mReturn.equals("HHTCallActivity")){
			isFromHHT=true;
		}		
		if(!isFromHHT){

			String validation_key=sp.getString("RSLT_KEY", null);
			
			if(validation_key!=null && validation_key!=""){
				
				String RSLT_KEY=sp.getString("RSLT_KEY", null); 
				String SERIAL_KEY=sp.getString("SERIAL_KEY", null);
				String deviceNo=MyApp.getHardwareNo();
				String demo_validation=null;
				
				if(SERIAL_KEY!=null && deviceNo!=null){
					demo_validation=SoftKey.GetEncrypKey(SERIAL_KEY, deviceNo);
				}				
				if(demo_validation!=null && RSLT_KEY!=null && demo_validation.equals(RSLT_KEY)){
					Intent intent = new Intent(ValidationActivity.this,MainActivity.class);
					startActivity(intent);
					ValidationActivity.this.finish();
				}			
			}
		}
	}
	
	private void HideTitle() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
}
