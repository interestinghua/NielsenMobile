package com.lbschina.srt.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lbschina.srt.R;
import com.lbschina.srt.update.ParseXmlService;
import com.lbschina.srt.util.FileDownload;

public class AutoUpdateActivity extends Activity {
	
	private TextView NavigateBack=null;
	private TextView AutoUpdateTextView=null;
	private Button AutoUpdateDown=null;
	HashMap<String, String> mHashMap;
	
	private ProgressBar mProgress;
	private Dialog mDownloadDialog;
	SimpleDateFormat sdf ;
	String mUpdateTime=null;
	private SharedPreferences mTimeSP;	
	
	private boolean cancelUpdate = false;
	private String mSavePath;
	private int progress;
	
	private static final int DOWNLOAD = 1;
	private static final int DOWNLOAD_FINISH = 2;
	
	File rootDir = Environment.getExternalStorageDirectory();
	
	private Handler mHandler = new Handler(){
		
		public void handleMessage(Message msg){
			switch (msg.what){
				case DOWNLOAD:
					mProgress.setProgress(progress);
					break;
				case DOWNLOAD_FINISH:
					installApk();
					break;
				default:
					break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.autoupdate);
		
		AutoUpdateTextView=(TextView)findViewById(R.id.AutoUpdateTextView);
		mTimeSP= this.getSharedPreferences("SPTIME", Context.MODE_PRIVATE);
		
		NavigateBack=(TextView)findViewById(R.id.NavigateBack);
		NavigateBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ReturnMainPage();				
			}
		});
		
		AutoUpdateDown=(Button)findViewById(R.id.AutoUpdateDown);		
		AutoUpdateDown.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Boolean isUpdate=isUpdate();
				if(isUpdate){
					showNoticeDialog();					
				}else{
					Toast.makeText(AutoUpdateActivity.this, R.string.soft_update_no, Toast.LENGTH_LONG).show();
				}				
			}
		});
		
	}
	
	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog(){
		AlertDialog.Builder builder = new Builder(AutoUpdateActivity.this);
		builder.setTitle(R.string.soft_update_title);
		builder.setMessage(R.string.soft_update_info);
		builder.setCancelable(false);
		builder.setPositiveButton(R.string.soft_update_updatebtn, new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
				showDownloadDialog();
			}
		});
		builder.setNegativeButton(R.string.soft_update_later, new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
			}
		});
		Dialog noticeDialog = builder.create();
		noticeDialog.show();
	}
	
	/**
	 * 显示软件下载对话框
	 */
	private void showDownloadDialog(){
		AlertDialog.Builder builder = new Builder(AutoUpdateActivity.this);
		builder.setTitle(R.string.soft_updating);
		builder.setCancelable(false);
		final LayoutInflater inflater = LayoutInflater.from(AutoUpdateActivity.this);
		View v = inflater.inflate(R.layout.softupdate_progress, null);
		mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
		builder.setView(v);
		builder.setNegativeButton(R.string.soft_update_cancel, new OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				dialog.dismiss();
				cancelUpdate = true;
			}
		});
		mDownloadDialog = builder.create();
		mDownloadDialog.show();
		downloadApk();
	}
	
	/**
	 * 下载apk文件
	 */
	private void downloadApk(){
		new downloadApkThread().start();
	}
	
	/**
	 * 下载文件线程
	 */
	private class downloadApkThread extends Thread{
		@Override
		public void run(){
			
			try{
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					
					// 获得存储卡的路径
					String sdpath = Environment.getExternalStorageDirectory() + "/";
					mSavePath = sdpath + "mobilemap";
					URL url = new URL(mHashMap.get("url"));
					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()){
						file.mkdir();
					}
					File apkFile = new File(mSavePath, mHashMap.get("name"));
					FileOutputStream fos = new FileOutputStream(apkFile);
					int count = 0;
					// 缓存
					byte buf[] = new byte[256];
					// 写入到文件中
					do{
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
						// 更新进度
						mHandler.sendEmptyMessage(DOWNLOAD);
						if (numread <= 0){
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							mUpdateTime = sdf.format(new java.util.Date());
							
							//存入数据
							Editor editor = mTimeSP.edit();
							editor.putString("SOFTUPDATETIME", mUpdateTime);
							editor.commit();
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					fos.close();
					is.close();
				}
			}catch (MalformedURLException e){
				e.printStackTrace();
			}catch (IOException e){
				e.printStackTrace();
			}
			// 取消下载对话框显示
			mDownloadDialog.dismiss();
		}
	};
	
	/**
	 * 安装APK文件
	 */
	private void installApk(){
		File apkfile = new File(mSavePath, mHashMap.get("name"));
		if(!apkfile.exists()){
			return;
		}
		// 通过Intent安装APK文件
		Intent mInstallIntent = new Intent(Intent.ACTION_VIEW);
		mInstallIntent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		AutoUpdateActivity.this.startActivity(mInstallIntent);
		
//		String str_soft_path = "storage/sdcard0/mobilemap";
//        File file = new File(str_soft_path + "/NielsenMobileMap.apk");
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(file),
//                        "application/vnd.android.package-archive");
//        startActivity(intent);
	}
	
	private void ReturnMainPage() {
		Intent myIntent = new Intent();
		myIntent = new Intent(AutoUpdateActivity.this, MainActivity.class);
		startActivity(myIntent);
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		this.finish();
	}
	
	/**
	 * 获取软件版本号
	 */
	private int getVersionCode(Context context){
		int versionCode = 0;
		try{
			//获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = context.getPackageManager().getPackageInfo("com.lbschina.srt", 0).versionCode;
		}catch (NameNotFoundException e){
			e.printStackTrace();
		}
		return versionCode;
	}

	
	/**
	 * 检查软件是否有更新版本
	 */
	private boolean isUpdate(){
		int versionCode = getVersionCode(AutoUpdateActivity.this);
		
		String mUrl="http://server.lbschina.com.cn/MobileMap/Apk/update.xml"; 
		String mRslt=FileDownload.DownFile(mUrl);
		
		File UpdateXmlFile=new File(rootDir+"/mobilemap/update.xml");		
		ParseXmlService service = new ParseXmlService();
		
		try{
			if("1"==mRslt){
				mHashMap = service.parseXml(UpdateXmlFile);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		if (null != mHashMap){
			int serviceCode = Integer.valueOf(mHashMap.get("version"));
			// 版本判断
			if (serviceCode > versionCode){
				return true;
			}
		}
		return false;
	}
	

	@Override
	protected void onResume() {
		super.onResume();
		
		String SOFTUPDATETIME=mTimeSP.getString("SOFTUPDATETIME", null);
		if(SOFTUPDATETIME!=null){
			AutoUpdateTextView.setText("上次更新时间:"+"\n"+SOFTUPDATETIME);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		ReturnMainPage();	
	}
}
