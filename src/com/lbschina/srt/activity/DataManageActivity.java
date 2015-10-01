package com.lbschina.srt.activity;

import java.io.File;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lbschina.srt.R;
import com.lbschina.srt.download.DownloadProgressListener;
import com.lbschina.srt.download.Downloader;
import com.lbschina.srt.offline.UnZip;
import com.lbschina.srt.util.NetUtils;

public class DataManageActivity extends Activity {
	
	Button SalesPointDown=null;
	TextView SalesPointTextView=null;
	ProgressBar SalesPointProgressbar=null;
	TextView SalesPointTimeTextView=null;
	String mSalesPointTime=null;
	
	Button BlockDown=null;
	TextView BlockTextView=null;
	ProgressBar BlockProgressbar=null;
	TextView BlockTimeTextView=null;
	String mBlockTime=null;
	
	SimpleDateFormat sdf ;
	private SharedPreferences mTimeSP;	
	
	File rootDir = Environment.getExternalStorageDirectory();
	Boolean isWiFi=false;
	
	
	private TextView NavigateBack=null;
	
	//断点下载
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {			
			case 1:				
				if(msg.obj.equals("salespointinfo.zip")){					
					int size = msg.getData().getInt("size");
					SalesPointProgressbar.setProgress(size);
					float result = (float) SalesPointProgressbar.getProgress()/ (float) SalesPointProgressbar.getMax();
					int p = (int) (result * 100);
					SalesPointTextView.setText(p + "%");
					
					if (SalesPointProgressbar.getProgress() == SalesPointProgressbar.getMax()){
						Toast.makeText(DataManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						SalesPointDown.setClickable(true);
						
						final String zipFile=rootDir+"/mobilemap/db/salespointinfo.zip/salespointinfo.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
			            
						
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
			            
						SalesPointProgressbar.setVisibility(View.INVISIBLE);
						SalesPointTextView.setVisibility(View.INVISIBLE);
						SalesPointDown.setBackgroundDrawable(null);
						SalesPointDown.setText("已下载");
						
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}					
				}else if(msg.obj.equals("blockinfo.zip")){
					
					int size = msg.getData().getInt("size");
					BlockProgressbar.setProgress(size);
					float result = (float) BlockProgressbar.getProgress()/ (float) BlockProgressbar.getMax();
					int p = (int) (result * 100);
					BlockTextView.setText(p + "%");
					
					if (BlockProgressbar.getProgress() == BlockProgressbar.getMax()){
						Toast.makeText(DataManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						BlockDown.setClickable(true);
						
						final String zipFile=rootDir+"/mobilemap/db/blockinfo.zip/blockinfo.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
		
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();		
		
						BlockProgressbar.setVisibility(View.INVISIBLE);
						BlockTextView.setVisibility(View.INVISIBLE);
						BlockDown.setBackgroundDrawable(null);
						BlockDown.setText("已下载");
						
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}
				break;
				
			case -1:
				Toast.makeText(DataManageActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datamanage);
		
		isWiFi=NetUtils.isWifiConnected(DataManageActivity.this);
		
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		mTimeSP = this.getSharedPreferences("SPTIME", Context.MODE_PRIVATE);
		

		
		
		NavigateBack=(TextView)findViewById(R.id.NavigateBack);
		NavigateBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ReturnMainPage();				
			}
		});
		
		SalesPointProgressbar=(ProgressBar)findViewById(R.id.SalesPointProgressbar);
		SalesPointTextView=(TextView)findViewById(R.id.SalesPointTextView);
		SalesPointDown=(Button)findViewById(R.id.SalesPointDown);
		SalesPointTimeTextView=(TextView)findViewById(R.id.SalesPointTimeTextView);
		
		SalesPointDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SalesPointDown.setClickable(false);
				SalesPointProgressbar.setVisibility(View.VISIBLE);
				SalesPointTextView.setVisibility(View.VISIBLE);
				
				mSalesPointTime = sdf.format(new java.util.Date());
				//存入数据
				Editor editor = mTimeSP.edit();
				editor.putString("SALESTIME", mSalesPointTime);
				editor.commit();
				
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					String path ="http://server.lbschina.com.cn/MobileMap/DB/salespointinfo.zip";
					File dir =new File(rootDir + "/mobilemap/db","salespointinfo.zip");
					download(path, dir,"salespointinfo.zip");
				} else {
					if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
						Toast.makeText(DataManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
					}
				}	
				
			}
		});
		
		BlockProgressbar=(ProgressBar)findViewById(R.id.BlockProgressbar);
		BlockTextView=(TextView)findViewById(R.id.BlockTextView);
		BlockDown=(Button)findViewById(R.id.BlockDown);
		BlockTimeTextView=(TextView)findViewById(R.id.BlockTimeTextView);
		
		BlockDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BlockDown.setClickable(false);
				BlockProgressbar.setVisibility(View.VISIBLE);
				BlockTextView.setVisibility(View.VISIBLE);
				
				mBlockTime = sdf.format(new java.util.Date());
				//存入数据
				Editor editor = mTimeSP.edit();
				editor.putString("BLOCKTIME", mBlockTime);
				editor.commit();
				
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
					String path ="http://server.lbschina.com.cn/MobileMap/DB/blockinfo.zip";
					File dir =new File(rootDir + "/mobilemap/db","blockinfo.zip");
					download(path, dir,"blockinfo.zip");
				} else {
					if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
						Toast.makeText(DataManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
					}
					if(!isWiFi){
						Toast.makeText(DataManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
					}	
				}	
				
			}
		});
		
		
	}
	
	private void ReturnMainPage() {
		Intent myIntent = new Intent();
		myIntent = new Intent(DataManageActivity.this, MainActivity.class);
		startActivity(myIntent);
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		this.finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		String SALESTIME=mTimeSP.getString("SALESTIME", null);
		if(SALESTIME!=null){
			SalesPointTimeTextView.setText("上次更新时间:"+"\n"+SALESTIME);
		}
		
		String BLOCKTIME=mTimeSP.getString("BLOCKTIME", null);
		if(BLOCKTIME!=null){
			BlockTimeTextView.setText("上次更新时间:"+"\n"+BLOCKTIME);
		}
		
		File AnHuiShengfile=new File(rootDir+"/mobilemap/db/salespointinfo");    
		if(AnHuiShengfile.exists()) { 
			SalesPointDown.setClickable(true);
			SalesPointProgressbar.setVisibility(View.INVISIBLE);
			SalesPointTextView.setVisibility(View.INVISIBLE);
			SalesPointDown.setBackgroundDrawable(null);
			SalesPointDown.setText("已更新");	
			
		}
		
		File beijingfile=new File(rootDir+"/mobilemap/db/blockinfo");    
		if(beijingfile.exists()) { 
			BlockDown.setClickable(true);
			BlockProgressbar.setVisibility(View.INVISIBLE);
			BlockTextView.setVisibility(View.INVISIBLE);
			BlockDown.setBackgroundDrawable(null);
			BlockDown.setText("已更新");
		}
	}
	
	// 对于UI控件的更新只能由主线程(UI线程)负责，如果在非UI线程更新UI控件，更新的结果不会反映在屏幕上，某些控件还会出错
	private void download(final String path, final File dir,String msgFileName) {
		
		final String msgName = msgFileName;
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					Downloader loader = new Downloader(DataManageActivity.this,path, dir, 1);
					
					int length = loader.getFileSize();// 获取文件的长度
					
					if(msgName.equals("salespointinfo.zip")){
						SalesPointProgressbar.setMax(length);
					}else if(msgName.equals("blockinfo.zip")){
						BlockProgressbar.setMax(length);
					}
					
					loader.download(new DownloadProgressListener() {
						
						public void onDownloadSize(int size) {// 可以实时得到文件下载的长度
							
							Message msg = new Message();
							
							if(msgName.equals("salespointinfo.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("blockinfo.zip")){
								msg.obj=msgName;
							}
							
							msg.what = 1;
							msg.getData().putInt("size", size);
							handler.sendMessage(msg);
						}
					});
				} catch (Exception e) {
					Message msg = new Message();
					msg.what = -1;
					msg.getData().putString("error", "下载失败");
					handler.sendMessage(msg);
				}
			}
		}).start();

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ReturnMainPage();	
	}
	
	
	

}
