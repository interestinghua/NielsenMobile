package com.lbschina.srt.activity;

import java.io.File;
import java.text.SimpleDateFormat;

import com.lbschina.srt.R;
import com.lbschina.srt.download.DownloadProgressListener;
import com.lbschina.srt.download.Downloader;
import com.lbschina.srt.offline.UnZip;
import com.lbschina.srt.util.NetUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SalesPointManageActivity extends Activity {
	private TextView NavigateBack=null;
	
	File rootDir = Environment.getExternalStorageDirectory();
	
	//下载按钮
	Button Shanghaidown_btn=null;
	ProgressBar shanghaiPB=null;
	TextView citySHTextView=null;
	
	Button BeiJingdown_btn=null;
	ProgressBar beijingPB=null;	
	TextView cityBJTextView=null;
	
	Button TianJindown_btn=null;
	ProgressBar TianJinPB=null;	
	TextView cityTianJinTextView=null;
	
	Button ChongQingdown_btn=null;
	ProgressBar ChongQingPB=null;	
	TextView cityChongQingTextView=null;
	
	Button AnHuidown_btn=null;
	ProgressBar AnHuiPB=null;	
	TextView cityAnHuiTextView=null;
	
	Button FuJiandown_btn=null;
	ProgressBar FuJianPB=null;	
	TextView cityFuJianTextView=null;
	
	Button GanSudown_btn=null;
	ProgressBar GanSuPB=null;	
	TextView cityGanSuTextView=null;
	
	Button GuangDongdown_btn=null;
	ProgressBar GuangDongPB=null;	
	TextView cityGuangDongTextView=null;
	
	Button GuangXidown_btn=null;
	ProgressBar GuangXiPB=null;	
	TextView cityGuangXiTextView=null;
	
	Button GuiZhoudown_btn=null;
	ProgressBar GuiZhouPB=null;	
	TextView cityGuiZhouTextView=null;
	
	Button HaiNandown_btn=null;
	ProgressBar HaiNanPB=null;	
	TextView cityHaiNanTextView=null;
	
	Button HeBeidown_btn=null;
	ProgressBar HeBeiPB=null;	
	TextView cityHeBeiTextView=null;	
	
	Button HeNandown_btn=null;
	ProgressBar HeNanPB=null;	
	TextView cityHeNanTextView=null;
	
	Button HeiLongJiangdown_btn=null;
	ProgressBar HeiLongJiangPB=null;	
	TextView cityHeiLongJiangTextView=null;
	
	Button HuBeidown_btn=null;
	ProgressBar HuBeiPB=null;	
	TextView cityHuBeiTextView=null;
	
	Button HuNandown_btn=null;
	ProgressBar HuNanPB=null;	
	TextView cityHuNanTextView=null;
	
	Button JiLingdown_btn=null;
	ProgressBar JiLingPB=null;	
	TextView cityJiLingTextView=null;
	
	Button JiangSudown_btn=null;
	ProgressBar JiangSuPB=null;	
	TextView cityJiangSuTextView=null;
	
	Button JiangXidown_btn=null;
	ProgressBar JiangXiPB=null;	
	TextView cityJiangXiTextView=null;
	
	Button LiaoNingdown_btn=null;
	ProgressBar LiaoNingPB=null;	
	TextView cityLiaoNingTextView=null;
	
	Button NeiMengGudown_btn=null;
	ProgressBar NeiMengGuPB=null;	
	TextView cityNeiMengGuTextView=null;
	
	Button NingXiadown_btn=null;
	ProgressBar NingXiaPB=null;	
	TextView cityNingXiaTextView=null;
	
	Button QingHaidown_btn=null;
	ProgressBar QingHaiPB=null;	
	TextView cityQingHaiTextView=null;
	
	Button ShanDongdown_btn=null;
	ProgressBar ShanDongPB=null;	
	TextView cityShanDongTextView=null;
	
	Button ShanXidown_btn=null;
	ProgressBar ShanXiPB=null;	
	TextView cityShanXiTextView=null;
	
	Button ShaanXidown_btn=null;
	ProgressBar ShaanXiPB=null;	
	TextView cityShaanXiTextView=null;
	
	Button SiChuandown_btn=null;
	ProgressBar SiChuanPB=null;	
	TextView citySiChuanTextView=null;
	
	Button XiZangdown_btn=null;
	ProgressBar XiZangPB=null;	
	TextView cityXiZangTextView=null;
	
	Button XinJiangdown_btn=null;
	ProgressBar XinJiangPB=null;	
	TextView cityXinJiangTextView=null;
	
	Button YunNandown_btn=null;
	ProgressBar YunNanPB=null;	
	TextView cityYunNanTextView=null;
	
	Button ZheJiangdown_btn=null;
	ProgressBar ZheJiangPB=null;	
	TextView cityZheJiangTextView=null;
	
	TextView SHANGHAITimeTextView=null;
	TextView YUNNANTimeTextView=null;
	TextView HENANTimeTextView=null;
	TextView GUIZHOUTimeTextView=null;
	TextView HAINANTimeTextView=null;
	TextView XINJIANGTimeTextView=null;
	TextView HEILONGJIANGTimeTextView=null;
	TextView JIANGXITimeTextView=null;
	TextView NEIMENGGUTimeTextView=null;
	TextView NINGXIATimeTextView=null;
	TextView SICHUANTimeTextView=null;
	TextView HUNANTimeTextView=null;
	TextView ANHUITimeTextView=null;
	TextView TIANJINGTimeTextView=null;
	TextView SHAANXITimeTextView=null;
	TextView HUBEITimeTextView=null;
	TextView JILINGTimeTextView=null;
	TextView JIANGSUTimeTextView=null;
	TextView BEIJINGTimeTextView=null;
	TextView SHANDONGTimeTextView=null;
	TextView GUANGXITimeTextView=null;
	TextView LIAONINGTimeTextView=null;
	TextView ZHEJIANGTimeTextView=null;
	TextView FUJIANTimeTextView=null;
	TextView GUANGDONGTimeTextView=null;
	TextView SHANXITimeTextView=null;
	TextView HEBEITimeTextView=null;
	TextView GANSUTimeTextView=null;
	TextView CHONGQINGTimeTextView=null;
	TextView QINGHAITimeTextView=null;
	
	
	//断点下载
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {			
			case 1:				
				if(msg.obj.equals("store_shanghai.zip")){					
					int size = msg.getData().getInt("size");
					shanghaiPB.setProgress(size);
					float result = (float) shanghaiPB.getProgress()/ (float) shanghaiPB.getMax();
					int p = (int) (result * 100);
					citySHTextView.setText(p + "%");
					
					if (shanghaiPB.getProgress() == shanghaiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						Shanghaidown_btn.setClickable(false);		
						final String zipFile=rootDir+"/mobilemap/db/store_shanghai.zip/store_shanghai.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();		
	
						shanghaiPB.setVisibility(View.INVISIBLE);
						citySHTextView.setVisibility(View.INVISIBLE);
						Shanghaidown_btn.setBackgroundDrawable(null);
						Shanghaidown_btn.setText("已下载");
//						BeiJingdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						TianJindown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						ChongQingdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						AnHuidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						FuJiandown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						GanSudown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						GuangDongdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						GuangXidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						GuiZhoudown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						HaiNandown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						HeBeidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						HeNandown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						HeiLongJiangdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						HuBeidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						HuNandown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						JiLingdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						JiangSudown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						JiangXidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						LiaoNingdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						NeiMengGudown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						NingXiadown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						QingHaidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						ShanDongdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						ShanXidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						ShaanXidown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						SiChuandown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						XinJiangdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						YunNandown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
//						ZheJiangdown_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.list_citymap_load));
						
						
						//new File(zipFile).delete();
						//new File(zipFile).getParentFile().delete();
					}					
				}else if(msg.obj.equals("store_beijing.zip")){
					
					int size = msg.getData().getInt("size");
					beijingPB.setProgress(size);
					float result = (float) beijingPB.getProgress()/ (float) beijingPB.getMax();
					int p = (int) (result * 100);
					cityBJTextView.setText(p + "%");
					
					if (beijingPB.getProgress() == beijingPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						BeiJingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_beijing.zip/store_beijing.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();		
		
						beijingPB.setVisibility(View.INVISIBLE);
						cityBJTextView.setVisibility(View.INVISIBLE);
						BeiJingdown_btn.setBackgroundDrawable(null);
						BeiJingdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_anhui.zip")){
					
					int size = msg.getData().getInt("size");
					AnHuiPB.setProgress(size);
					float result = (float) AnHuiPB.getProgress()/ (float) AnHuiPB.getMax();
					int p = (int) (result * 100);
					cityAnHuiTextView.setText(p + "%");
					
					if (AnHuiPB.getProgress() == AnHuiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						AnHuidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_anhui.zip/store_anhui.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();	
			
						AnHuiPB.setVisibility(View.INVISIBLE);
						cityAnHuiTextView.setVisibility(View.INVISIBLE);
						AnHuidown_btn.setBackgroundDrawable(null);
						AnHuidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_chongqing.zip")){
					
					int size = msg.getData().getInt("size");
					ChongQingPB.setProgress(size);
					float result = (float) ChongQingPB.getProgress()/ (float) ChongQingPB.getMax();
					int p = (int) (result * 100);
					cityChongQingTextView.setText(p + "%");
					
					if (ChongQingPB.getProgress() == ChongQingPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ChongQingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_chongqing.zip/store_chongqing.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();	

						ChongQingPB.setVisibility(View.INVISIBLE);
						cityChongQingTextView.setVisibility(View.INVISIBLE);
						
						ChongQingdown_btn.setBackgroundDrawable(null);
						ChongQingdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_fujian.zip")){
					
					int size = msg.getData().getInt("size");
					FuJianPB.setProgress(size);
					float result = (float) FuJianPB.getProgress()/ (float) FuJianPB.getMax();
					int p = (int) (result * 100);
					cityFuJianTextView.setText(p + "%");
					
					if (FuJianPB.getProgress() == FuJianPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						FuJiandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_fujian.zip/store_fujian.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						FuJianPB.setVisibility(View.INVISIBLE);
						cityFuJianTextView.setVisibility(View.INVISIBLE);
						FuJiandown_btn.setBackgroundDrawable(null);
						FuJiandown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_gansu.zip")){
					
					int size = msg.getData().getInt("size");
					GanSuPB.setProgress(size);
					float result = (float) GanSuPB.getProgress()/ (float) GanSuPB.getMax();
					int p = (int) (result * 100);
					cityGanSuTextView.setText(p + "%");
					
					if (GanSuPB.getProgress() == GanSuPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GanSudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_gansu.zip/store_gansu.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
	
						GanSuPB.setVisibility(View.INVISIBLE);
						cityGanSuTextView.setVisibility(View.INVISIBLE);
						GanSudown_btn.setBackgroundDrawable(null);
						GanSudown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("store_guangdong.zip")){
					
					int size = msg.getData().getInt("size");
					GuangDongPB.setProgress(size);
					float result = (float) GuangDongPB.getProgress()/ (float) GuangDongPB.getMax();
					int p = (int) (result * 100);
					cityGuangDongTextView.setText(p + "%");
					
					if (GuangDongPB.getProgress() == GuangDongPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GuangDongdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_guangdong.zip/store_guangdong.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
	
						GuangDongPB.setVisibility(View.INVISIBLE);
						cityGuangDongTextView.setVisibility(View.INVISIBLE);
						GuangDongdown_btn.setBackgroundDrawable(null);
						GuangDongdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_guangxi.zip")){
					
					int size = msg.getData().getInt("size");
					GuangXiPB.setProgress(size);
					float result = (float) GuangXiPB.getProgress()/ (float) GuangXiPB.getMax();
					int p = (int) (result * 100);
					cityGuangXiTextView.setText(p + "%");
					
					if (GuangXiPB.getProgress() == GuangXiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GuangXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_guangxi.zip/store_guangxi.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
		
						GuangXiPB.setVisibility(View.INVISIBLE);
						cityGuangXiTextView.setVisibility(View.INVISIBLE);
						GuangXidown_btn.setBackgroundDrawable(null);
						GuangXidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_guizhou.zip")){
					
					int size = msg.getData().getInt("size");
					GuiZhouPB.setProgress(size);
					float result = (float) GuiZhouPB.getProgress()/ (float) GuiZhouPB.getMax();
					int p = (int) (result * 100);
					cityGuiZhouTextView.setText(p + "%");
					
					if (GuiZhouPB.getProgress() == GuiZhouPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GuiZhoudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_guizhou.zip/store_guizhou.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
		
						GuiZhouPB.setVisibility(View.INVISIBLE);
						cityGuiZhouTextView.setVisibility(View.INVISIBLE);
						GuiZhoudown_btn.setBackgroundDrawable(null);
						GuiZhoudown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("store_hainan.zip")){
					
					int size = msg.getData().getInt("size");
					HaiNanPB.setProgress(size);
					float result = (float) HaiNanPB.getProgress()/ (float) HaiNanPB.getMax();
					int p = (int) (result * 100);
					cityHaiNanTextView.setText(p + "%");
					
					if (HaiNanPB.getProgress() == HaiNanPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HaiNandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_hainan.zip/store_hainan.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						HaiNanPB.setVisibility(View.INVISIBLE);
						cityHaiNanTextView.setVisibility(View.INVISIBLE);
						HaiNandown_btn.setBackgroundDrawable(null);
						HaiNandown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_hebei.zip")){
					
					int size = msg.getData().getInt("size");
					HeBeiPB.setProgress(size);
					float result = (float) HeBeiPB.getProgress()/ (float) HeBeiPB.getMax();
					int p = (int) (result * 100);
					cityHeBeiTextView.setText(p + "%");
					
					if (HeBeiPB.getProgress() == HeBeiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HeBeidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_hebei.zip/store_hebei.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
	
						HeBeiPB.setVisibility(View.INVISIBLE);
						cityHeBeiTextView.setVisibility(View.INVISIBLE);
						HeBeidown_btn.setBackgroundDrawable(null);
						HeBeidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_heilongjiang.zip")){
					
					int size = msg.getData().getInt("size");
					HeiLongJiangPB.setProgress(size);
					float result = (float) HeiLongJiangPB.getProgress()/ (float) HeiLongJiangPB.getMax();
					int p = (int) (result * 100);
					cityHeiLongJiangTextView.setText(p + "%");
					
					if (HeiLongJiangPB.getProgress() == HeiLongJiangPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HeiLongJiangdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_heilongjiang.zip/store_heilongjiang.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						HeiLongJiangPB.setVisibility(View.INVISIBLE);
						cityHeiLongJiangTextView.setVisibility(View.INVISIBLE);
						HeiLongJiangdown_btn.setBackgroundDrawable(null);
						HeiLongJiangdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("store_henan.zip")){
					
					int size = msg.getData().getInt("size");
					HeNanPB.setProgress(size);
					float result = (float) HeNanPB.getProgress()/ (float) HeNanPB.getMax();
					int p = (int) (result * 100);
					cityHeNanTextView.setText(p + "%");
					
					if (HeNanPB.getProgress() == HeNanPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HeNandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_henan.zip/store_henan.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
	
						HeNanPB.setVisibility(View.INVISIBLE);
						cityHeNanTextView.setVisibility(View.INVISIBLE);
						HeNandown_btn.setBackgroundDrawable(null);
						HeNandown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_hubei.zip")){
					
					int size = msg.getData().getInt("size");
					HuBeiPB.setProgress(size);
					float result = (float) HuBeiPB.getProgress()/ (float) HuBeiPB.getMax();
					int p = (int) (result * 100);
					cityHuBeiTextView.setText(p + "%");
					
					if (HuBeiPB.getProgress() == HuBeiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HuBeidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_hubei.zip/store_hubei.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						HuBeiPB.setVisibility(View.INVISIBLE);
						cityHuBeiTextView.setVisibility(View.INVISIBLE);
						HuBeidown_btn.setBackgroundDrawable(null);
						HuBeidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_hunan.zip")){
					
					int size = msg.getData().getInt("size");
					HuNanPB.setProgress(size);
					float result = (float) HuNanPB.getProgress()/ (float) HuNanPB.getMax();
					int p = (int) (result * 100);
					cityHuNanTextView.setText(p + "%");
					
					if (HuNanPB.getProgress() == HuNanPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HuNandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_hunan.zip/store_hunan.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						HuNanPB.setVisibility(View.INVISIBLE);
						cityHuNanTextView.setVisibility(View.INVISIBLE);
						HuNandown_btn.setBackgroundDrawable(null);
						HuNandown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("store_jiangsu.zip")){
					
					int size = msg.getData().getInt("size");
					JiangSuPB.setProgress(size);
					float result = (float) JiangSuPB.getProgress()/ (float) JiangSuPB.getMax();
					int p = (int) (result * 100);
					cityJiangSuTextView.setText(p + "%");
					
					if (JiangSuPB.getProgress() == JiangSuPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						JiangSudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_jiangsu.zip/store_jiangsu.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						JiangSuPB.setVisibility(View.INVISIBLE);
						cityJiangSuTextView.setVisibility(View.INVISIBLE);
						JiangSudown_btn.setBackgroundDrawable(null);
						JiangSudown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
						
					}
				}else if(msg.obj.equals("store_jiangxi.zip")){
					
					int size = msg.getData().getInt("size");
					JiangXiPB.setProgress(size);
					float result = (float) JiangXiPB.getProgress()/ (float) JiangXiPB.getMax();
					int p = (int) (result * 100);
					cityJiangXiTextView.setText(p + "%");
					
					if (JiangXiPB.getProgress() == JiangXiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
							
						JiangXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_jiangxi.zip/store_jiangxi.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						JiangXiPB.setVisibility(View.INVISIBLE);
						cityJiangXiTextView.setVisibility(View.INVISIBLE);
						JiangXidown_btn.setBackgroundDrawable(null);
						JiangXidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("store_jiling.zip")){
					
					int size = msg.getData().getInt("size");
					JiLingPB.setProgress(size);
					float result = (float) JiLingPB.getProgress()/ (float) JiLingPB.getMax();
					int p = (int) (result * 100);
					cityJiLingTextView.setText(p + "%");
					
					if (JiLingPB.getProgress() == JiLingPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						JiLingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_jiling.zip/store_jiling.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						JiLingPB.setVisibility(View.INVISIBLE);
						cityJiLingTextView.setVisibility(View.INVISIBLE);
						JiLingdown_btn.setBackgroundDrawable(null);
						JiLingdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_liaoning.zip")){
					
					int size = msg.getData().getInt("size");
					LiaoNingPB.setProgress(size);
					float result = (float) LiaoNingPB.getProgress()/ (float) LiaoNingPB.getMax();
					int p = (int) (result * 100);
					cityLiaoNingTextView.setText(p + "%");
					
					if (LiaoNingPB.getProgress() == LiaoNingPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						LiaoNingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_liaoning.zip/store_liaoning.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						LiaoNingPB.setVisibility(View.INVISIBLE);
						cityLiaoNingTextView.setVisibility(View.INVISIBLE);
						LiaoNingdown_btn.setBackgroundDrawable(null);
						LiaoNingdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_neimenggu.zip")){
					
					int size = msg.getData().getInt("size");
					NeiMengGuPB.setProgress(size);
					float result = (float) NeiMengGuPB.getProgress()/ (float) NeiMengGuPB.getMax();
					int p = (int) (result * 100);
					cityNeiMengGuTextView.setText(p + "%");
					
					if (NeiMengGuPB.getProgress() == NeiMengGuPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						NeiMengGudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_neimenggu.zip/store_neimenggu.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						NeiMengGuPB.setVisibility(View.INVISIBLE);
						cityNeiMengGuTextView.setVisibility(View.INVISIBLE);
						NeiMengGudown_btn.setBackgroundDrawable(null);
						NeiMengGudown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_ningxia.zip")){
					
					int size = msg.getData().getInt("size");
					NingXiaPB.setProgress(size);
					float result = (float) NingXiaPB.getProgress()/ (float) NingXiaPB.getMax();
					int p = (int) (result * 100);
					cityNingXiaTextView.setText(p + "%");
					
					if (NingXiaPB.getProgress() == NingXiaPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						NingXiadown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_ningxia.zip/store_ningxia.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						NingXiaPB.setVisibility(View.INVISIBLE);
						cityNingXiaTextView.setVisibility(View.INVISIBLE);
						NingXiadown_btn.setBackgroundDrawable(null);
						NingXiadown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("store_qinghai.zip")){
					
					int size = msg.getData().getInt("size");
					QingHaiPB.setProgress(size);
					float result = (float) QingHaiPB.getProgress()/ (float) QingHaiPB.getMax();
					int p = (int) (result * 100);
					cityQingHaiTextView.setText(p + "%");
					
					if (QingHaiPB.getProgress() == QingHaiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						QingHaidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_qinghai.zip/store_qinghai.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();

						QingHaiPB.setVisibility(View.INVISIBLE);
						cityQingHaiTextView.setVisibility(View.INVISIBLE);
						QingHaidown_btn.setBackgroundDrawable(null);
						QingHaidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("store_shaanxi.zip")){
					
					int size = msg.getData().getInt("size");
					ShaanXiPB.setProgress(size);
					float result = (float) ShaanXiPB.getProgress()/ (float) ShaanXiPB.getMax();
					int p = (int) (result * 100);
					cityShaanXiTextView.setText(p + "%");
					
					if (ShaanXiPB.getProgress() == ShaanXiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ShaanXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_shaanxi.zip/store_shaanxi.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
			
						ShaanXiPB.setVisibility(View.INVISIBLE);
						cityShaanXiTextView.setVisibility(View.INVISIBLE);
						ShaanXidown_btn.setBackgroundDrawable(null);
						ShaanXidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_shandong.zip")){
					
					int size = msg.getData().getInt("size");
					ShanDongPB.setProgress(size);
					float result = (float) ShanDongPB.getProgress()/ (float) ShanDongPB.getMax();
					int p = (int) (result * 100);
					cityShanDongTextView.setText(p + "%");
					
					if (ShanDongPB.getProgress() == ShanDongPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ShanDongdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_shandong.zip/store_shandong.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
			
						ShanDongPB.setVisibility(View.INVISIBLE);
						cityShanDongTextView.setVisibility(View.INVISIBLE);
						ShanDongdown_btn.setBackgroundDrawable(null);
						ShanDongdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_shanxi.zip")){
					
					int size = msg.getData().getInt("size");
					ShanXiPB.setProgress(size);
					float result = (float) ShanXiPB.getProgress()/ (float) ShanXiPB.getMax();
					int p = (int) (result * 100);
					cityShanXiTextView.setText(p + "%");
					
					if (ShanXiPB.getProgress() == ShanXiPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ShanXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_shanxi.zip/store_shanxi.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						ShanXiPB.setVisibility(View.INVISIBLE);
						cityShanXiTextView.setVisibility(View.INVISIBLE);
						ShanXidown_btn.setBackgroundDrawable(null);
						ShanXidown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_sichuan.zip")){
					
					int size = msg.getData().getInt("size");
					SiChuanPB.setProgress(size);
					float result = (float) SiChuanPB.getProgress()/ (float) SiChuanPB.getMax();
					int p = (int) (result * 100);
					citySiChuanTextView.setText(p + "%");
					
					if (SiChuanPB.getProgress() == SiChuanPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						SiChuandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_sichuan.zip/store_sichuan.zip";
						final String targetDir=rootDir+"/mobilemap/db/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						SiChuanPB.setVisibility(View.INVISIBLE);
						citySiChuanTextView.setVisibility(View.INVISIBLE);
						SiChuandown_btn.setBackgroundDrawable(null);
						SiChuandown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_tianjing.zip")){
					
					int size = msg.getData().getInt("size");
					TianJinPB.setProgress(size);
					float result = (float) TianJinPB.getProgress()/ (float) TianJinPB.getMax();
					int p = (int) (result * 100);
					cityTianJinTextView.setText(p + "%");
					
					if (TianJinPB.getProgress() == TianJinPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						TianJindown_btn.setClickable(false);

						final String zipFile=rootDir+"/mobilemap/db/store_tianjing.zip/store_tianjing.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						TianJinPB.setVisibility(View.INVISIBLE);
						cityTianJinTextView.setVisibility(View.INVISIBLE);
						TianJindown_btn.setBackgroundDrawable(null);
						TianJindown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_xinjiang.zip")){
					
					int size = msg.getData().getInt("size");
					XinJiangPB.setProgress(size);
					float result = (float) XinJiangPB.getProgress()/ (float) XinJiangPB.getMax();
					int p = (int) (result * 100);
					cityXinJiangTextView.setText(p + "%");
					
					if (XinJiangPB.getProgress() == XinJiangPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						XinJiangdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/db/store_xinjiang.zip/store_xinjiang.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						XinJiangPB.setVisibility(View.INVISIBLE);
						cityXinJiangTextView.setVisibility(View.INVISIBLE);
						XinJiangdown_btn.setBackgroundDrawable(null);
						XinJiangdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_yunnan.zip")){
					
					int size = msg.getData().getInt("size");
					YunNanPB.setProgress(size);
					float result = (float) YunNanPB.getProgress()/ (float) YunNanPB.getMax();
					int p = (int) (result * 100);
					cityYunNanTextView.setText(p + "%");
					
					if (YunNanPB.getProgress() == YunNanPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						YunNandown_btn.setClickable(false);
						final String zipFile=rootDir+"/mobilemap/db/store_yunnan.zip/store_yunnan.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						YunNanPB.setVisibility(View.INVISIBLE);
						cityYunNanTextView.setVisibility(View.INVISIBLE);
						YunNandown_btn.setBackgroundDrawable(null);
						YunNandown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}else if(msg.obj.equals("store_zhejiang.zip")){
					
					int size = msg.getData().getInt("size");
					ZheJiangPB.setProgress(size);
					float result = (float) ZheJiangPB.getProgress()/ (float) ZheJiangPB.getMax();
					int p = (int) (result * 100);
					cityZheJiangTextView.setText(p + "%");
					
					if (ZheJiangPB.getProgress() == ZheJiangPB.getMax()){
						Toast.makeText(SalesPointManageActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ZheJiangdown_btn.setClickable(false);
						final String zipFile=rootDir+"/mobilemap/db/store_zhejiang.zip/store_zhejiang.zip";
						final String targetDir=rootDir+"/mobilemap/db/";
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
					
						ZheJiangPB.setVisibility(View.INVISIBLE);
						cityZheJiangTextView.setVisibility(View.INVISIBLE);
						ZheJiangdown_btn.setBackgroundDrawable(null);
						ZheJiangdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}
				}
				break;
				
			case -1:
				Toast.makeText(SalesPointManageActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	
	private Boolean isWiFi=false;
	String mSalesPointTime=null;
	SimpleDateFormat sdf ;
	private SharedPreferences mTimeSP;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salesmanage);
		checkAndCreateDirectory("/mobilemap/db/");
		
		
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		mTimeSP = this.getSharedPreferences("SALESPOINTTIME", Context.MODE_PRIVATE);
		

		
		
		NavigateBack=(TextView)findViewById(R.id.NavigateBack);
		NavigateBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ReturnMainPage();				
			}
		});
		
		 SHANGHAITimeTextView=(TextView)findViewById(R.id.SHANGHAITimeTextView);
		 YUNNANTimeTextView=(TextView)findViewById(R.id.YUNNANTimeTextView);
		 HENANTimeTextView=(TextView)findViewById(R.id.HENANTimeTextView);
		 GUIZHOUTimeTextView=(TextView)findViewById(R.id.GUIZHOUTimeTextView);
		 HAINANTimeTextView=(TextView)findViewById(R.id.HAINANTimeTextView);
		 XINJIANGTimeTextView=(TextView)findViewById(R.id.XINJIANGTimeTextView);
		 HEILONGJIANGTimeTextView=(TextView)findViewById(R.id.HEILONGJIANGTimeTextView);
		 JIANGXITimeTextView=(TextView)findViewById(R.id.JIANGXITimeTextView);
		 NEIMENGGUTimeTextView=(TextView)findViewById(R.id.NEIMENGGUTimeTextView);
		 NINGXIATimeTextView=(TextView)findViewById(R.id.NINGXIATimeTextView);
		 SICHUANTimeTextView=(TextView)findViewById(R.id.SICHUANTimeTextView);
		 HUNANTimeTextView=(TextView)findViewById(R.id.HUNANTimeTextView);
		 ANHUITimeTextView=(TextView)findViewById(R.id.ANHUITimeTextView);
		 TIANJINGTimeTextView=(TextView)findViewById(R.id.TIANJINGTimeTextView);
		 SHAANXITimeTextView=(TextView)findViewById(R.id.SHAANXITimeTextView);
		 HUBEITimeTextView=(TextView)findViewById(R.id.HUBEITimeTextView);
		 JILINGTimeTextView=(TextView)findViewById(R.id.JILINGTimeTextView);
		 JIANGSUTimeTextView=(TextView)findViewById(R.id.JIANGSUTimeTextView);
		 BEIJINGTimeTextView=(TextView)findViewById(R.id.BEIJINGTimeTextView);
		 SHANDONGTimeTextView=(TextView)findViewById(R.id.SHANDONGTimeTextView);
		 GUANGXITimeTextView=(TextView)findViewById(R.id.GUANGXITimeTextView);
		 LIAONINGTimeTextView=(TextView)findViewById(R.id.LIAONINGTimeTextView);
		 ZHEJIANGTimeTextView=(TextView)findViewById(R.id.ZHEJIANGTimeTextView);
		 FUJIANTimeTextView=(TextView)findViewById(R.id.FUJIANTimeTextView);
		 GUANGDONGTimeTextView=(TextView)findViewById(R.id.GUANGDONGTimeTextView);
		 SHANXITimeTextView=(TextView)findViewById(R.id.SHANXITimeTextView);
		 HEBEITimeTextView=(TextView)findViewById(R.id.HEBEITimeTextView);
		 GANSUTimeTextView=(TextView)findViewById(R.id.GANSUTimeTextView);
		 CHONGQINGTimeTextView=(TextView)findViewById(R.id.CHONGQINGTimeTextView);
		 QINGHAITimeTextView=(TextView)findViewById(R.id.QINGHAITimeTextView);
		
		isWiFi=NetUtils.isWifiConnected(SalesPointManageActivity.this);		
				
		shanghaiPB=(ProgressBar)findViewById(R.id.citySHProgressbar);
		citySHTextView=(TextView)findViewById(R.id.citySHTextView);
		Shanghaidown_btn=(Button)findViewById(R.id.citySHDown);		
		Shanghaidown_btn.setOnClickListener(new OnClickListener() {			
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载上海市售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//Shanghaidown_btn.setClickable(false);
						shanghaiPB.setVisibility(View.VISIBLE);
						citySHTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("SHANGHAISPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_shanghai.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_shanghai.zip");
							download(path, dir,"store_shanghai.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}	
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();			

			}
		});
			
		beijingPB=(ProgressBar)findViewById(R.id.cityBeiJingProgressbar);
		cityBJTextView=(TextView)findViewById(R.id.cityBJTextView);
		BeiJingdown_btn=(Button)findViewById(R.id.cityBeiJingDown);		
		BeiJingdown_btn.setOnClickListener(new View.OnClickListener() {			
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载北京市售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//BeiJingdown_btn.setClickable(false);
						beijingPB.setVisibility(View.VISIBLE);
						cityBJTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("BEIJINGSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_beijing.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_beijing.zip");
							download(path, dir,"store_beijing.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
		
			}
		});	
		
		TianJindown_btn=(Button)findViewById(R.id.cityTianJinDown);	
		TianJinPB=(ProgressBar)findViewById(R.id.cityTianJinProgressbar);
		cityTianJinTextView=(TextView)findViewById(R.id.cityTianJinTextView);
		TianJindown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载天津市售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//TianJindown_btn.setClickable(false);
						TianJinPB.setVisibility(View.VISIBLE);
						cityTianJinTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("TIANJINGSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_tianjing.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_tianjing.zip");
							download(path, dir,"store_tianjing.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}	
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
				
			}
		});
		
		ChongQingdown_btn=(Button)findViewById(R.id.cityChongQingDown);	
		ChongQingPB=(ProgressBar)findViewById(R.id.cityChongQingProgressbar);
		cityChongQingTextView=(TextView)findViewById(R.id.cityChongQingTextView);
		ChongQingdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载重庆市售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ChongQingdown_btn.setClickable(false);
						ChongQingPB.setVisibility(View.VISIBLE);
						cityChongQingTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("CHONGQINGSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_chongqing.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_chongqing.zip");
							download(path, dir,"store_chongqing.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}					
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();				
			}
		});
		
		
		AnHuidown_btn=(Button)findViewById(R.id.cityAnHuiDown);	
		AnHuiPB=(ProgressBar)findViewById(R.id.cityAnHuiProgressbar);
		cityAnHuiTextView=(TextView)findViewById(R.id.cityAnHuiTextView);
		AnHuidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载安徽省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//AnHuidown_btn.setClickable(false);
						AnHuiPB.setVisibility(View.VISIBLE);
						cityAnHuiTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("ANHUISPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_anhui.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_anhui.zip");
							download(path, dir,"store_anhui.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();			
			}
		});
		
		
		FuJiandown_btn=(Button)findViewById(R.id.cityFuJianDown);	
		FuJianPB=(ProgressBar)findViewById(R.id.cityFuJianProgressbar);
		cityFuJianTextView=(TextView)findViewById(R.id.cityFuJianTextView);
		FuJiandown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载福建省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//FuJiandown_btn.setClickable(false);
						FuJianPB.setVisibility(View.VISIBLE);
						cityFuJianTextView.setVisibility(View.VISIBLE);	
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("FUJIANSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_fujian.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_fujian.zip");
							download(path, dir,"store_fujian.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
			}
		});

		
		GanSudown_btn=(Button)findViewById(R.id.cityGanSuDown);	
		GanSuPB=(ProgressBar)findViewById(R.id.cityGanSuProgressbar);
		cityGanSuTextView=(TextView)findViewById(R.id.cityGanSuTextView);
		GanSudown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载甘肃省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GanSudown_btn.setClickable(false);
						GanSuPB.setVisibility(View.VISIBLE);
						cityGanSuTextView.setVisibility(View.VISIBLE);	
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("GANSUSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_gansu.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_gansu.zip");
							download(path, dir,"store_gansu.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
			}
		});
		
		GuangDongdown_btn=(Button)findViewById(R.id.cityGuangDongDown);	
		GuangDongPB=(ProgressBar)findViewById(R.id.cityGuangDongProgressbar);
		cityGuangDongTextView=(TextView)findViewById(R.id.cityGuangDongTextView);
		GuangDongdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载广东省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GuangDongdown_btn.setClickable(false);
						GuangDongPB.setVisibility(View.VISIBLE);
						cityGuangDongTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("GUANGDONGSPTIME", mSalesPointTime);
						editor.commit();
						
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_guangdong.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_guangdong.zip");
							download(path, dir,"store_guangdong.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
			}
		});
		
		GuangXidown_btn=(Button)findViewById(R.id.cityGuangXiDown);	
		GuangXiPB=(ProgressBar)findViewById(R.id.cityGuangXiProgressbar);
		cityGuangXiTextView=(TextView)findViewById(R.id.cityGuangXiTextView);
		GuangXidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载广西省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GuangXidown_btn.setClickable(false);
						GuangXiPB.setVisibility(View.VISIBLE);
						cityGuangXiTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("GUANGXISPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_guangxi.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_guangxi.zip");
							download(path, dir,"store_guangxi.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
			}
		});
		
		GuiZhoudown_btn=(Button)findViewById(R.id.cityGuiZhouDown);	
		GuiZhouPB=(ProgressBar)findViewById(R.id.cityGuiZhouProgressbar);
		cityGuiZhouTextView=(TextView)findViewById(R.id.cityGuiZhouTextView);
		GuiZhoudown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载贵州省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GuiZhoudown_btn.setClickable(false);
						GuiZhouPB.setVisibility(View.VISIBLE);
						cityGuiZhouTextView.setVisibility(View.VISIBLE);
						
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("GUIZHOUSPTIME", mSalesPointTime);
						editor.commit();
						
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_guizhou.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_guizhou.zip");
							download(path, dir,"store_guizhou.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
			}
		});
		
		HaiNandown_btn=(Button)findViewById(R.id.cityHaiNanDown);	
		HaiNanPB=(ProgressBar)findViewById(R.id.cityHaiNanProgressbar);
		cityHaiNanTextView=(TextView)findViewById(R.id.cityHaiNanTextView);
		HaiNandown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载海南省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HaiNandown_btn.setClickable(false);
						HaiNanPB.setVisibility(View.VISIBLE);
						cityHaiNanTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("HAINANSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_hainan.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_hainan.zip");
							download(path, dir,"store_hainan.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
			}
		});
		
		HeBeidown_btn=(Button)findViewById(R.id.cityHeBeiDown);	
		HeBeiPB=(ProgressBar)findViewById(R.id.cityHeBeiProgressbar);
		cityHeBeiTextView=(TextView)findViewById(R.id.cityHeBeiTextView);
		HeBeidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载河北省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HeBeidown_btn.setClickable(false);
						HeBeiPB.setVisibility(View.VISIBLE);
						cityHeBeiTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("HEBEISPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_hebei.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_hebei.zip");
							download(path, dir,"store_hebei.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
			}
		});
		
		HeNandown_btn=(Button)findViewById(R.id.cityHeNanDown);	
		HeNanPB=(ProgressBar)findViewById(R.id.cityHeNanProgressbar);
		cityHeNanTextView=(TextView)findViewById(R.id.cityHeNanTextView);
		HeNandown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载河南省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HeNandown_btn.setClickable(false);
						HeNanPB.setVisibility(View.VISIBLE);
						cityHeNanTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("HENANSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_henan.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_henan.zip");
							download(path, dir,"store_henan.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
			}
		});
		
		HeiLongJiangdown_btn=(Button)findViewById(R.id.cityHeiLongJiangDown);	
		HeiLongJiangPB=(ProgressBar)findViewById(R.id.cityHeiLongJiangProgressbar);
		cityHeiLongJiangTextView=(TextView)findViewById(R.id.cityHeiLongJiangTextView);
		HeiLongJiangdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载黑龙江省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HeiLongJiangdown_btn.setClickable(false);
						HeiLongJiangPB.setVisibility(View.VISIBLE);
						cityHeiLongJiangTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("HEILONGJIANGSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_heilongjiang.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_heilongjiang.zip");
							download(path, dir,"store_heilongjiang.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
			}
		});
		
		HuBeidown_btn=(Button)findViewById(R.id.cityHuBeiDown);	
		HuBeiPB=(ProgressBar)findViewById(R.id.cityHuBeiProgressbar);
		cityHuBeiTextView=(TextView)findViewById(R.id.cityHuBeiTextView);
		HuBeidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载湖北省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HuBeidown_btn.setClickable(false);
						HuBeiPB.setVisibility(View.VISIBLE);
						cityHuBeiTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("HUBEISPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_hubei.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_hubei.zip");
							download(path, dir,"store_hubei.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
			}
		});
		
		HuNandown_btn=(Button)findViewById(R.id.cityHuNanDown);	
		HuNanPB=(ProgressBar)findViewById(R.id.cityHuNanProgressbar);
		cityHuNanTextView=(TextView)findViewById(R.id.cityHuNanTextView);
		HuNandown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载湖南省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HuNandown_btn.setClickable(false);
						HuNanPB.setVisibility(View.VISIBLE);
						cityHuNanTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("HUNANSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_hunan.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_hunan.zip");
							download(path, dir,"store_hunan.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
			}
		});
		
		JiLingdown_btn=(Button)findViewById(R.id.cityJiLingDown);	
		JiLingPB=(ProgressBar)findViewById(R.id.cityJiLingProgressbar);
		cityJiLingTextView=(TextView)findViewById(R.id.cityJiLingTextView);
		JiLingdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载吉林省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//JiLingdown_btn.setClickable(false);
						JiLingPB.setVisibility(View.VISIBLE);
						cityJiLingTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("JILINGSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_jiling.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_jiling.zip");
							download(path, dir,"store_jiling.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
			}
		});
		
		JiangSudown_btn=(Button)findViewById(R.id.cityJiangSuDown);	
		JiangSuPB=(ProgressBar)findViewById(R.id.cityJiangSuProgressbar);
		cityJiangSuTextView=(TextView)findViewById(R.id.cityJiangSuTextView);
		JiangSudown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载江苏省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//JiangSudown_btn.setClickable(false);
						JiangSuPB.setVisibility(View.VISIBLE);
						cityJiangSuTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("JIANGSUSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_jiangsu.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_jiangsu.zip");
							download(path, dir,"store_jiangsu.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();
				
			}
		});
		
		JiangXidown_btn=(Button)findViewById(R.id.cityJiangXiDown);	
		JiangXiPB=(ProgressBar)findViewById(R.id.cityJiangXiProgressbar);
		cityJiangXiTextView=(TextView)findViewById(R.id.cityJiangXiTextView);
		JiangXidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载江西省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){	
						//JiangXidown_btn.setClickable(false);
						JiangXiPB.setVisibility(View.VISIBLE);
						cityJiangXiTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("JIANGXISPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_jiangxi.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_jiangxi.zip");
							download(path, dir,"store_jiangxi.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});
		
		LiaoNingdown_btn=(Button)findViewById(R.id.cityLiaoNingDown);	
		LiaoNingPB=(ProgressBar)findViewById(R.id.cityLiaoNingProgressbar);
		cityLiaoNingTextView=(TextView)findViewById(R.id.cityLiaoNingTextView);
		LiaoNingdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载辽宁省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//LiaoNingdown_btn.setClickable(false);
						LiaoNingPB.setVisibility(View.VISIBLE);
						cityLiaoNingTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("LIAONINGSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_liaoning.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_liaoning.zip");
							download(path, dir,"store_liaoning.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});
		
		NeiMengGudown_btn=(Button)findViewById(R.id.cityNeiMengGuDown);	
		NeiMengGuPB=(ProgressBar)findViewById(R.id.cityNeiMengGuProgressbar);
		cityNeiMengGuTextView=(TextView)findViewById(R.id.cityNeiMengGuTextView);
		NeiMengGudown_btn.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载内蒙古售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//NeiMengGudown_btn.setClickable(false);
						NeiMengGuPB.setVisibility(View.VISIBLE);
						cityNeiMengGuTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("NEIMENGGUSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_neimenggu.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_neimenggu.zip");
							download(path, dir,"store_neimenggu.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				

				
			}
		});
		
		NingXiadown_btn=(Button)findViewById(R.id.cityNingXiaDown);	
		NingXiaPB=(ProgressBar)findViewById(R.id.cityNingXiaProgressbar);
		cityNingXiaTextView=(TextView)findViewById(R.id.cityNingXiaTextView);
		NingXiadown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载宁夏售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//NingXiadown_btn.setClickable(false);
						NingXiaPB.setVisibility(View.VISIBLE);
						cityNingXiaTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("NINGXIASPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_ningxia.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_ningxia.zip");
							download(path, dir,"store_ningxia.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
			}
		});
		
		QingHaidown_btn=(Button)findViewById(R.id.cityQingHaiDown);	
		QingHaiPB=(ProgressBar)findViewById(R.id.cityQingHaiProgressbar);
		cityQingHaiTextView=(TextView)findViewById(R.id.cityQingHaiTextView);
		QingHaidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载青海省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//QingHaidown_btn.setClickable(false);
						QingHaiPB.setVisibility(View.VISIBLE);
						cityQingHaiTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("QINGHAISPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_qinghai.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_qinghai.zip");
							download(path, dir,"store_qinghai.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
			}
		});
		
		ShanDongdown_btn=(Button)findViewById(R.id.cityShanDongDown);	
		ShanDongPB=(ProgressBar)findViewById(R.id.cityShanDongProgressbar);
		cityShanDongTextView=(TextView)findViewById(R.id.cityShanDongTextView);
		ShanDongdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载山东省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ShanDongdown_btn.setClickable(false);
						ShanDongPB.setVisibility(View.VISIBLE);
						cityShanDongTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("SHANDONGSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_shandong.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_shandong.zip");
							download(path, dir,"store_shandong.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});
		
		ShanXidown_btn=(Button)findViewById(R.id.cityShanXiDown);	
		ShanXiPB=(ProgressBar)findViewById(R.id.cityShanXiProgressbar);
		cityShanXiTextView=(TextView)findViewById(R.id.cityShanXiTextView);
		ShanXidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载山西省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ShanXidown_btn.setClickable(false);
						ShanXiPB.setVisibility(View.VISIBLE);
						cityShanXiTextView.setVisibility(View.VISIBLE);	
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("SHANXISPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_shanxi.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_shanxi.zip");
							download(path, dir,"store_shanxi.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});
		
		ShaanXidown_btn=(Button)findViewById(R.id.cityShaanXiDown);	
		ShaanXiPB=(ProgressBar)findViewById(R.id.cityShaanXiProgressbar);
		cityShaanXiTextView=(TextView)findViewById(R.id.cityShaanXiTextView);
		ShaanXidown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载陕西省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ShaanXidown_btn.setClickable(false);
						ShaanXiPB.setVisibility(View.VISIBLE);
						cityShaanXiTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("SHAANXISPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_shaanxi.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_shaanxi.zip");
							download(path, dir,"store_shaanxi.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});
		
		SiChuandown_btn=(Button)findViewById(R.id.citySiChuanDown);	
		SiChuanPB=(ProgressBar)findViewById(R.id.citySiChuanProgressbar);
		citySiChuanTextView=(TextView)findViewById(R.id.citySiChuanTextView);
		SiChuandown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载四川省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//SiChuandown_btn.setClickable(false);
						SiChuanPB.setVisibility(View.VISIBLE);
						citySiChuanTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("SICHUANSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_sichuan.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_sichuan.zip");
							download(path, dir,"store_sichuan.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});

		
		XinJiangdown_btn=(Button)findViewById(R.id.cityXinJiangDown);	
		XinJiangPB=(ProgressBar)findViewById(R.id.cityXinJiangProgressbar);
		cityXinJiangTextView=(TextView)findViewById(R.id.cityXinJiangTextView);
		XinJiangdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载新疆售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						
						//XinJiangdown_btn.setClickable(false);
						XinJiangPB.setVisibility(View.VISIBLE);
						cityXinJiangTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("XINJIANGSPTIME", mSalesPointTime);
						editor.commit();
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_xinjiang.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_xinjiang.zip");
							download(path, dir,"store_xinjiang.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});
		
		YunNandown_btn=(Button)findViewById(R.id.cityYunNanDown);	
		YunNanPB=(ProgressBar)findViewById(R.id.cityYunNanProgressbar);
		cityYunNanTextView=(TextView)findViewById(R.id.cityYunNanTextView);
		YunNandown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载云南省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//YunNandown_btn.setClickable(false);
						YunNanPB.setVisibility(View.VISIBLE);
						cityYunNanTextView.setVisibility(View.VISIBLE);	
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("YUNNANSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_yunnan.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_yunnan.zip");
							download(path, dir,"store_yunnan.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
				
			}
		});
		
		ZheJiangdown_btn=(Button)findViewById(R.id.cityZheJiangDown);	
		ZheJiangPB=(ProgressBar)findViewById(R.id.cityZheJiangProgressbar);
		cityZheJiangTextView=(TextView)findViewById(R.id.cityZheJiangTextView);
		ZheJiangdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new Builder(SalesPointManageActivity.this);
				builder.setTitle(R.string.salespoint_download);
				builder.setMessage("确定下载浙江省售点？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ZheJiangdown_btn.setClickable(false);
						ZheJiangPB.setVisibility(View.VISIBLE);
						cityZheJiangTextView.setVisibility(View.VISIBLE);
						mSalesPointTime = sdf.format(new java.util.Date());
						Editor editor = mTimeSP.edit();
						editor.putString("ZHEJIANGSPTIME", mSalesPointTime);
						editor.commit();
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ) {
							String path ="http://server.lbschina.com.cn/MobileMap/DB/store_zhejiang.zip";
							File dir =new File(rootDir + "/mobilemap/db","store_zhejiang.zip");
							download(path, dir,"store_zhejiang.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(SalesPointManageActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(SalesPointManageActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
							}		
						}
					}
				});
				builder.setNegativeButton(R.string.map_download_no, new  DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						dialog.dismiss();
					}
				});
				Dialog noticeDialog = builder.create();
				noticeDialog.show();	
				
				
				
			}
		});
	}
	
	// 对于UI控件的更新只能由主线程(UI线程)负责，如果在非UI线程更新UI控件，更新的结果不会反映在屏幕上，某些控件还会出错
	private void download(final String path, final File dir,String msgFileName) {
		
		final String msgName = msgFileName;
		
		new Thread(new Runnable() {
			
			public void run() {
				try {
					Downloader loader = new Downloader(SalesPointManageActivity.this,path, dir, 1);
					
					int length = loader.getFileSize();// 获取文件的长度
					
					if(msgName.equals("store_shanghai.zip")){
						shanghaiPB.setMax(length);
					}else if(msgName.equals("store_beijing.zip")){
						beijingPB.setMax(length);
					}else if(msgName.equals("store_anhui.zip")){
						AnHuiPB.setMax(length);
					}else if(msgName.equals("store_chongqing.zip")){
						ChongQingPB.setMax(length);
					}else if(msgName.equals("store_fujian.zip")){
						FuJianPB.setMax(length);
					}else if(msgName.equals("store_gansu.zip")){
						GanSuPB.setMax(length);
					}else if(msgName.equals("store_guangdong.zip")){
						GuangDongPB.setMax(length);
					}else if(msgName.equals("store_guangxi.zip")){
						GuangXiPB.setMax(length);
					}else if(msgName.equals("store_guizhou.zip")){
						GuiZhouPB.setMax(length);
					}else if(msgName.equals("store_hainan.zip")){
						HaiNanPB.setMax(length);
					}else if(msgName.equals("store_hebei.zip")){
						HeBeiPB.setMax(length);
					}else if(msgName.equals("store_heilongjiang.zip")){
						HeiLongJiangPB.setMax(length);
					}else if(msgName.equals("store_henan.zip")){
						HeNanPB.setMax(length);
					}else if(msgName.equals("store_hubei.zip")){
						HuBeiPB.setMax(length);
					}else if(msgName.equals("store_hunan.zip")){
						HuNanPB.setMax(length);
					}else if(msgName.equals("store_jiangsu.zip")){
						JiangSuPB.setMax(length);
					}else if(msgName.equals("store_jiangxi.zip")){
						JiangXiPB.setMax(length);
					}else if(msgName.equals("store_jiling.zip")){
						JiLingPB.setMax(length);
					}else if(msgName.equals("store_liaoning.zip")){
						LiaoNingPB.setMax(length);
					}else if(msgName.equals("store_neimenggu.zip")){
						NeiMengGuPB.setMax(length);
					}else if(msgName.equals("store_ningxia.zip")){
						NingXiaPB.setMax(length);
					}else if(msgName.equals("store_qinghai.zip")){
						QingHaiPB.setMax(length);
					}else if(msgName.equals("store_shaanxi.zip")){
						ShaanXiPB.setMax(length);
					}else if(msgName.equals("store_shandong.zip")){
						ShanDongPB.setMax(length);
					}else if(msgName.equals("store_shanxi.zip")){
						ShanXiPB.setMax(length);
					}else if(msgName.equals("store_sichuan.zip")){
						SiChuanPB.setMax(length);
					}else if(msgName.equals("store_tianjing.zip")){
						TianJinPB.setMax(length);
					}else if(msgName.equals("store_xinjiang.zip")){
						XinJiangPB.setMax(length);
					}else if(msgName.equals("store_xizang.zip")){
						XiZangPB.setMax(length);
					}else if(msgName.equals("store_yunnan.zip")){
						YunNanPB.setMax(length);
					}else if(msgName.equals("store_zhejiang.zip")){
						ZheJiangPB.setMax(length);
					}
					
					loader.download(new DownloadProgressListener() {
						
						public void onDownloadSize(int size) {// 可以实时得到文件下载的长度
							
							Message msg = new Message();
							
							if(msgName.equals("store_shanghai.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_beijing.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_anhui.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_chongqing.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_fujian.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_gansu.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_guangdong.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_guangxi.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_guizhou.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_hainan.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_hebei.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_heilongjiang.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_henan.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_hubei.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_hunan.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_jiangsu.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_jiangxi.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_jiling.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_liaoning.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_neimenggu.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_ningxia.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_qinghai.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_shaanxi.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_shandong.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_shanxi.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_sichuan.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_tianjing.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_xinjiang.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_xizang.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_yunnan.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("store_zhejiang.zip")){
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
	
	private void ReturnMainPage() {
		Intent myIntent = new Intent();
		myIntent = new Intent(SalesPointManageActivity.this, MainActivity.class);
		startActivity(myIntent);
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		this.finish();
	}	
	public void checkAndCreateDirectory(String dirName){   
        File new_dir = new File(rootDir + dirName);   
        if(!new_dir.exists()){
            new_dir.mkdirs();   
        }   
	}  

	@Override
	protected void onResume() {
		super.onResume();
		
		File ShangHaifile=new File(rootDir+"/mobilemap/db/store_shanghai.zip");    
		if(ShangHaifile.exists()) {
			Shanghaidown_btn.setClickable(true);
			shanghaiPB.setVisibility(View.INVISIBLE);
			citySHTextView.setVisibility(View.INVISIBLE);
			Shanghaidown_btn.setBackgroundDrawable(null);
			Shanghaidown_btn.setText("已更新");
			
			String SHANGHAISPTIME=mTimeSP.getString("SHANGHAISPTIME", null);
			if(SHANGHAISPTIME!=null){
				SHANGHAITimeTextView.setText("上次更新时间:"+"\n"+SHANGHAISPTIME);
			}
		}
		
		File BeiJingfile=new File(rootDir+"/mobilemap/db/store_beijing.zip");    
		if(BeiJingfile.exists()) {
			BeiJingdown_btn.setClickable(true);
			beijingPB.setVisibility(View.INVISIBLE);
			cityBJTextView.setVisibility(View.INVISIBLE);
			BeiJingdown_btn.setBackgroundDrawable(null);
			BeiJingdown_btn.setText("已更新");
			
			String BEIJINGSPTIME=mTimeSP.getString("BEIJINGSPTIME", null);
			if(BEIJINGSPTIME!=null){
				BEIJINGTimeTextView.setText("上次更新时间:"+"\n"+BEIJINGSPTIME);
			}
		}
		
		File TianJingfile=new File(rootDir+"/mobilemap/db/store_tianjing.zip");    
		if(TianJingfile.exists()) {
			TianJindown_btn.setClickable(true);
			TianJinPB.setVisibility(View.INVISIBLE);
			cityTianJinTextView.setVisibility(View.INVISIBLE);
			TianJindown_btn.setBackgroundDrawable(null);
			TianJindown_btn.setText("已更新");
			
			String TIANJINGTIME=mTimeSP.getString("TIANJINGSPTIME", null);
			if(TIANJINGTIME!=null){
				TIANJINGTimeTextView.setText("上次更新时间:"+"\n"+TIANJINGTIME);
			}
		}
		
		File ChongQingfile=new File(rootDir+"/mobilemap/db/store_chongqing.zip");    
		if(ChongQingfile.exists()) {
			ChongQingdown_btn.setClickable(true);
			ChongQingPB.setVisibility(View.INVISIBLE);
			cityChongQingTextView.setVisibility(View.INVISIBLE);
			ChongQingdown_btn.setBackgroundDrawable(null);
			ChongQingdown_btn.setText("已更新");	
			
			String CHONGQINGSPTIME=mTimeSP.getString("CHONGQINGSPTIME", null);
			if(CHONGQINGSPTIME!=null){
				CHONGQINGTimeTextView.setText("上次更新时间:"+"\n"+CHONGQINGSPTIME);
			}
		}
		
		File AnHuifile=new File(rootDir+"/mobilemap/db/store_anhui.zip");    
		if(AnHuifile.exists()) {
			AnHuidown_btn.setClickable(true);
			AnHuiPB.setVisibility(View.INVISIBLE);
			cityAnHuiTextView.setVisibility(View.INVISIBLE);
			AnHuidown_btn.setBackgroundDrawable(null);
			AnHuidown_btn.setText("已更新");	
			
			String ANHUISPTIME=mTimeSP.getString("ANHUISPTIME", null);
			if(ANHUISPTIME!=null){
				ANHUITimeTextView.setText("上次更新时间:"+"\n"+ANHUISPTIME);
			}
		}
		
		File FuJianfile=new File(rootDir+"/mobilemap/db/store_fujian.zip");    
		if(FuJianfile.exists()) {
			FuJiandown_btn.setClickable(true);
			FuJianPB.setVisibility(View.INVISIBLE);
			cityFuJianTextView.setVisibility(View.INVISIBLE);
			FuJiandown_btn.setBackgroundDrawable(null);
			FuJiandown_btn.setText("已更新");
			
			String FUJIANSPTIME=mTimeSP.getString("FUJIANSPTIME", null);
			if(FUJIANSPTIME!=null){
				FUJIANTimeTextView.setText("上次更新时间:"+"\n"+FUJIANSPTIME);
			}
		}
		
		File GanSufile=new File(rootDir+"/mobilemap/db/store_gansu.zip");    
		if(GanSufile.exists()) {
			GanSudown_btn.setClickable(true);
			GanSuPB.setVisibility(View.INVISIBLE);
			cityGanSuTextView.setVisibility(View.INVISIBLE);
			GanSudown_btn.setBackgroundDrawable(null);
			GanSudown_btn.setText("已更新");	
			
			String GANSUSPTIME=mTimeSP.getString("GANSUSPTIME", null);
			if(GANSUSPTIME!=null){
				GANSUTimeTextView.setText("上次更新时间:"+"\n"+GANSUSPTIME);
			}
		}
		
		File GuangDongfile=new File(rootDir+"/mobilemap/db/store_guangdong.zip");    
		if(GuangDongfile.exists()) {
			GuangDongdown_btn.setClickable(true);
			GuangDongPB.setVisibility(View.INVISIBLE);
			cityGuangDongTextView.setVisibility(View.INVISIBLE);
			GuangDongdown_btn.setBackgroundDrawable(null);
			GuangDongdown_btn.setText("已更新");	
			
			String GUANGDONGTIME=mTimeSP.getString("GUANGDONGSPTIME", null);
			if(GUANGDONGTIME!=null){
				GUANGDONGTimeTextView.setText("上次更新时间:"+"\n"+GUANGDONGTIME);
			}
		}
		
		File GuangXifile=new File(rootDir+"/mobilemap/db/store_guangxi.zip");    
		if(GuangXifile.exists()) {
			GuangXidown_btn.setClickable(true);
			GuangXiPB.setVisibility(View.INVISIBLE);
			cityGuangXiTextView.setVisibility(View.INVISIBLE);
			GuangXidown_btn.setBackgroundDrawable(null);
			GuangXidown_btn.setText("已更新");	
			
			String GUANGXISPTIME=mTimeSP.getString("GUANGXISPTIME", null);
			if(GUANGXISPTIME!=null){
				GUANGXITimeTextView.setText("上次更新时间:"+"\n"+GUANGXISPTIME);
			}
		}
		
		File GuiZhoufile=new File(rootDir+"/mobilemap/db/store_guizhou.zip");    
		if(GuiZhoufile.exists()) {
			GuiZhoudown_btn.setClickable(true);
			GuiZhouPB.setVisibility(View.INVISIBLE);
			cityGuiZhouTextView.setVisibility(View.INVISIBLE);
			GuiZhoudown_btn.setBackgroundDrawable(null);
			GuiZhoudown_btn.setText("已更新");
			
			String GUIZHOUTIME=mTimeSP.getString("GUIZHOUSPTIME", null);
			if(GUIZHOUTIME!=null){
				GUIZHOUTimeTextView.setText("上次更新时间:"+"\n"+GUIZHOUTIME);
			}
		}
		
		File HaiNanfile=new File(rootDir+"/mobilemap/db/store_hainan.zip");    
		if(HaiNanfile.exists()) {
			HaiNandown_btn.setClickable(true);
			HaiNanPB.setVisibility(View.INVISIBLE);
			cityHaiNanTextView.setVisibility(View.INVISIBLE);
			HaiNandown_btn.setBackgroundDrawable(null);
			HaiNandown_btn.setText("已更新");
			
			String HAINANTIME=mTimeSP.getString("HAINANSPTIME", null);
			if(HAINANTIME!=null){
				HAINANTimeTextView.setText("上次更新时间:"+"\n"+HAINANTIME);
			}
		}
		
		File HeBeifile=new File(rootDir+"/mobilemap/db/store_hebei.zip");    
		if(HeBeifile.exists()) {
			HeBeidown_btn.setClickable(true);
			HeBeiPB.setVisibility(View.INVISIBLE);
			cityHeBeiTextView.setVisibility(View.INVISIBLE);
			HeBeidown_btn.setBackgroundDrawable(null);
			HeBeidown_btn.setText("已更新");	
			
			String HEBEISPTIME=mTimeSP.getString("HEBEISPTIME", null);
			if(HEBEISPTIME!=null){
				HEBEITimeTextView.setText("上次更新时间:"+"\n"+HEBEISPTIME);
			}
		}
		
		File HeNanfile=new File(rootDir+"/mobilemap/db/store_henan.zip");    
		if(HeNanfile.exists()) {
			HeNandown_btn.setClickable(true);
			HeNanPB.setVisibility(View.INVISIBLE);
			cityHeNanTextView.setVisibility(View.INVISIBLE);
			HeNandown_btn.setBackgroundDrawable(null);
			HeNandown_btn.setText("已更新");	
			
			String HENANTIME=mTimeSP.getString("HENANSPTIME", null);
			if(HENANTIME!=null){
				HENANTimeTextView.setText("上次更新时间:"+"\n"+HENANTIME);
			}
		}
		
		File HeiLongJiangfile=new File(rootDir+"/mobilemap/db/store_heilongjiang.zip");    
		if(HeiLongJiangfile.exists()) {
			HeiLongJiangdown_btn.setClickable(true);
			HeiLongJiangPB.setVisibility(View.INVISIBLE);
			cityHeiLongJiangTextView.setVisibility(View.INVISIBLE);
			HeiLongJiangdown_btn.setBackgroundDrawable(null);
			HeiLongJiangdown_btn.setText("已更新");	
			
			String HEILONGJIANGTIME=mTimeSP.getString("HEILONGJIANGSPTIME", null);
			if(HEILONGJIANGTIME!=null){
				HEILONGJIANGTimeTextView.setText("上次更新时间:"+"\n"+HEILONGJIANGTIME);
			}
		}
		
		File HuBeifile=new File(rootDir+"/mobilemap/db/store_hubei.zip");    
		if(HuBeifile.exists()) {
			HuBeidown_btn.setClickable(true);
			HuBeiPB.setVisibility(View.INVISIBLE);
			cityHuBeiTextView.setVisibility(View.INVISIBLE);
			HuBeidown_btn.setBackgroundDrawable(null);
			HuBeidown_btn.setText("已更新");	
			
			String HUBEISPTIME=mTimeSP.getString("HUBEISPTIME", null);
			if(HUBEISPTIME!=null){
				HUBEITimeTextView.setText("上次更新时间:"+"\n"+HUBEISPTIME);
			}
		}
		
		File HuNanfile=new File(rootDir+"/mobilemap/db/store_hunan.zip");    
		if(HuNanfile.exists()) {
			HuNandown_btn.setClickable(true);
			HuNanPB.setVisibility(View.INVISIBLE);
			cityHuNanTextView.setVisibility(View.INVISIBLE);
			HuNandown_btn.setBackgroundDrawable(null);
			HuNandown_btn.setText("已更新");	
			
			String HUNANSPTIME=mTimeSP.getString("HUNANSPTIME", null);
			if(HUNANSPTIME!=null){
				HUNANTimeTextView.setText("上次更新时间:"+"\n"+HUNANSPTIME);
			}
		}
		
		File JiLingfile=new File(rootDir+"/mobilemap/db/store_jiling.zip");    
		if(JiLingfile.exists()) {
			JiLingdown_btn.setClickable(true);
			JiLingPB.setVisibility(View.INVISIBLE);
			cityJiLingTextView.setVisibility(View.INVISIBLE);
			JiLingdown_btn.setBackgroundDrawable(null);
			JiLingdown_btn.setText("已更新");
			
			String JILINGSPTIME=mTimeSP.getString("JILINGSPTIME", null);
			if(JILINGSPTIME!=null){
				JILINGTimeTextView.setText("上次更新时间:"+"\n"+JILINGSPTIME);
			}
		}
		
		File JiangSufile=new File(rootDir+"/mobilemap/db/store_jiangsu.zip");    
		if(JiangSufile.exists()) {
			JiangSudown_btn.setClickable(true);
			JiangSuPB.setVisibility(View.INVISIBLE);
			cityJiangSuTextView.setVisibility(View.INVISIBLE);
			JiangSudown_btn.setBackgroundDrawable(null);
			JiangSudown_btn.setText("已更新");
			
			String JIANGSUSPTIME=mTimeSP.getString("JIANGSUSPTIME", null);
			if(JIANGSUSPTIME!=null){
				JIANGSUTimeTextView.setText("上次更新时间:"+"\n"+JIANGSUSPTIME);
			}
		}
		
		File JiangXifile=new File(rootDir+"/mobilemap/db/store_jiangxi.zip");    
		if(JiangXifile.exists()) {
			JiangXidown_btn.setClickable(true);
			JiangXiPB.setVisibility(View.INVISIBLE);
			cityJiangXiTextView.setVisibility(View.INVISIBLE);
			JiangXidown_btn.setBackgroundDrawable(null);
			JiangXidown_btn.setText("已更新");
			
			String JIANGXITIME=mTimeSP.getString("JIANGXISPTIME", null);
			if(JIANGXITIME!=null){
				JIANGXITimeTextView.setText("上次更新时间:"+"\n"+JIANGXITIME);
			}
		}
		
		File LiaoNingfile=new File(rootDir+"/mobilemap/db/store_liaoning.zip");    
		if(LiaoNingfile.exists()) {
			LiaoNingdown_btn.setClickable(true);
			LiaoNingPB.setVisibility(View.INVISIBLE);
			cityLiaoNingTextView.setVisibility(View.INVISIBLE);
			LiaoNingdown_btn.setBackgroundDrawable(null);
			LiaoNingdown_btn.setText("已更新");	
			
			String LIAONINGSPTIME=mTimeSP.getString("LIAONINGSPTIME", null);
			if(LIAONINGSPTIME!=null){
				LIAONINGTimeTextView.setText("上次更新时间:"+"\n"+LIAONINGSPTIME);
			}
		}
		
		File NeiMengGufile=new File(rootDir+"/mobilemap/db/store_neimenggu.zip");    
		if(NeiMengGufile.exists()) {
			NeiMengGudown_btn.setClickable(true);
			NeiMengGuPB.setVisibility(View.INVISIBLE);
			cityNeiMengGuTextView.setVisibility(View.INVISIBLE);
			NeiMengGudown_btn.setBackgroundDrawable(null);
			NeiMengGudown_btn.setText("已更新");	
			
			String NEIMENGGUSPTIME=mTimeSP.getString("NEIMENGGUSPTIME", null);
			if(NEIMENGGUSPTIME!=null){
				NEIMENGGUTimeTextView.setText("上次更新时间:"+"\n"+NEIMENGGUSPTIME);
			}
		}
		
		File NingXiafile=new File(rootDir+"/mobilemap/db/store_ningxia.zip");    
		if(NingXiafile.exists()) {
			NingXiadown_btn.setClickable(true);
			NingXiaPB.setVisibility(View.INVISIBLE);
			cityNingXiaTextView.setVisibility(View.INVISIBLE);
			NingXiadown_btn.setBackgroundDrawable(null);
			NingXiadown_btn.setText("已更新");	
			
			String NINGXIASPTIME=mTimeSP.getString("NINGXIASPTIME", null);
			if(NINGXIASPTIME!=null){
				NINGXIATimeTextView.setText("上次更新时间:"+"\n"+NINGXIASPTIME);
			}
		}
		
		File QingHaifile=new File(rootDir+"/mobilemap/db/store_qinghai.zip");    
		if(QingHaifile.exists()) {
			QingHaidown_btn.setClickable(true);
			QingHaiPB.setVisibility(View.INVISIBLE);
			cityQingHaiTextView.setVisibility(View.INVISIBLE);
			QingHaidown_btn.setBackgroundDrawable(null);
			QingHaidown_btn.setText("已更新");	
			
			String QINGHAISPTIME=mTimeSP.getString("QINGHAISPTIME", null);
			if(QINGHAISPTIME!=null){
				QINGHAITimeTextView.setText("上次更新时间:"+"\n"+QINGHAISPTIME);
			}		
		}
		
		File ShanDongfile=new File(rootDir+"/mobilemap/db/store_shandong.zip");    
		if(ShanDongfile.exists()) {
			ShanDongdown_btn.setClickable(true);
			ShanDongPB.setVisibility(View.INVISIBLE);
			cityShanDongTextView.setVisibility(View.INVISIBLE);
			ShanDongdown_btn.setBackgroundDrawable(null);
			ShanDongdown_btn.setText("已更新");	
			
			String SHANDONGSPTIME=mTimeSP.getString("SHANDONGSPTIME", null);
			if(SHANDONGSPTIME!=null){
				SHANDONGTimeTextView.setText("上次更新时间:"+"\n"+SHANDONGSPTIME);
			}
		}
		
		File ShanXifile=new File(rootDir+"/mobilemap/db/store_shanxi.zip");    
		if(ShanXifile.exists()) {
			ShanXidown_btn.setClickable(true);
			ShanXiPB.setVisibility(View.INVISIBLE);
			cityShanXiTextView.setVisibility(View.INVISIBLE);
			ShanXidown_btn.setBackgroundDrawable(null);
			ShanXidown_btn.setText("已更新");
			
			String SHANXITIME=mTimeSP.getString("SHANXISPTIME", null);
			if(SHANXITIME!=null){
				SHANXITimeTextView.setText("上次更新时间:"+"\n"+SHANXITIME);
			}
		}
		
		File ShaanXifile=new File(rootDir+"/mobilemap/db/store_shaanxi.zip");    
		if(ShaanXifile.exists()) {
			ShaanXidown_btn.setClickable(true);
			ShaanXiPB.setVisibility(View.INVISIBLE);
			cityShaanXiTextView.setVisibility(View.INVISIBLE);
			ShaanXidown_btn.setBackgroundDrawable(null);
			ShaanXidown_btn.setText("已更新");
			
			String SHAANXISPTIME=mTimeSP.getString("SHAANXISPTIME", null);
			if(SHAANXISPTIME!=null){
				SHAANXITimeTextView.setText("上次更新时间:"+"\n"+SHAANXISPTIME);
			}
		}
		
		File SiChuanfile=new File(rootDir+"/mobilemap/db/store_sichuan.zip");    
		if(SiChuanfile.exists()) {
			SiChuandown_btn.setClickable(true);
			SiChuanPB.setVisibility(View.INVISIBLE);
			citySiChuanTextView.setVisibility(View.INVISIBLE);
			SiChuandown_btn.setBackgroundDrawable(null);
			SiChuandown_btn.setText("已更新");	
			
			String SICHUANSPTIME=mTimeSP.getString("SICHUANSPTIME", null);
			if(SICHUANSPTIME!=null){
				SICHUANTimeTextView.setText("上次更新时间:"+"\n"+SICHUANSPTIME);
			}
		}
		
		File XinJiangfile=new File(rootDir+"/mobilemap/db/store_xinjiang.zip");  
		if(XinJiangfile.exists()) {
			XinJiangdown_btn.setClickable(true);
			XinJiangPB.setVisibility(View.INVISIBLE);
			cityXinJiangTextView.setVisibility(View.INVISIBLE);
			XinJiangdown_btn.setBackgroundDrawable(null);
			XinJiangdown_btn.setText("已更新");	
			
			String XINJIANGTIME=mTimeSP.getString("XINJIANGSPTIME", null);
			if(XINJIANGTIME!=null){
				XINJIANGTimeTextView.setText("上次更新时间:"+"\n"+XINJIANGTIME);
			}
		}
		
		File YunNanfile=new File(rootDir+"/mobilemap/db/store_yunnan.zip");    
		if(YunNanfile.exists()) {
			YunNandown_btn.setClickable(true);
			YunNanPB.setVisibility(View.INVISIBLE);
			cityYunNanTextView.setVisibility(View.INVISIBLE);
			YunNandown_btn.setBackgroundDrawable(null);
			YunNandown_btn.setText("已更新");	
			
			String YUNNANSPTIME=mTimeSP.getString("YUNNANSPTIME", null);
			if(YUNNANSPTIME!=null){
				YUNNANTimeTextView.setText("上次更新时间:"+"\n"+YUNNANSPTIME);
			}
		}
		
		File ZheJiangfile=new File(rootDir+"/mobilemap/db/store_zhejiang.zip");    
		if(ZheJiangfile.exists()) {
			ZheJiangdown_btn.setClickable(true);
			ZheJiangPB.setVisibility(View.INVISIBLE);
			cityZheJiangTextView.setVisibility(View.INVISIBLE);
			ZheJiangdown_btn.setBackgroundDrawable(null);
			ZheJiangdown_btn.setText("已更新");	
			
			String ZHEJIANGSPTIME=mTimeSP.getString("ZHEJIANGSPTIME", null);
			if(ZHEJIANGSPTIME!=null){
				ZHEJIANGTimeTextView.setText("上次更新时间:"+"\n"+ZHEJIANGSPTIME);
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		ReturnMainPage();	
	}
}
