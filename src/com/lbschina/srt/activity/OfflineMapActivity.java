package com.lbschina.srt.activity;

import java.io.File;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lbschina.srt.R;
import com.lbschina.srt.download.DownloadProgressListener;
import com.lbschina.srt.download.Downloader;
import com.lbschina.srt.offline.UnZip;
import com.lbschina.srt.util.NetUtils;

public class OfflineMapActivity extends FragmentActivity { 
	
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
	
	Button GangAodown_btn=null;
	ProgressBar GangAoPB=null;	
	TextView cityGangAoTextView=null;
	
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
	
	private TextView NavigateBack=null;
	
	//断点下载
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {			
			case 1:				
				if(msg.obj.equals("ShangHai.zip")){					
					int size = msg.getData().getInt("size");
					shanghaiPB.setProgress(size);
					float result = (float) shanghaiPB.getProgress()/ (float) shanghaiPB.getMax();
					int p = (int) (result * 100);
					citySHTextView.setText(p + "%");
					
					if (shanghaiPB.getProgress() == shanghaiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						Shanghaidown_btn.setClickable(false);		
						final String zipFile=rootDir+"/mobilemap/map/vmap/ShangHai.zip/ShangHai.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
						
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();		
	
						shanghaiPB.setVisibility(View.INVISIBLE);
						citySHTextView.setVisibility(View.INVISIBLE);
						Shanghaidown_btn.setBackgroundDrawable(null);
						Shanghaidown_btn.setText("已下载");
						
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
					}					
				}else if(msg.obj.equals("BeiJing.zip")){
					
					int size = msg.getData().getInt("size");
					beijingPB.setProgress(size);
					float result = (float) beijingPB.getProgress()/ (float) beijingPB.getMax();
					int p = (int) (result * 100);
					cityBJTextView.setText(p + "%");
					
					if (beijingPB.getProgress() == beijingPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						BeiJingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/BeiJing.zip/BeiJing.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("AnHuiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					AnHuiPB.setProgress(size);
					float result = (float) AnHuiPB.getProgress()/ (float) AnHuiPB.getMax();
					int p = (int) (result * 100);
					cityAnHuiTextView.setText(p + "%");
					
					if (AnHuiPB.getProgress() == AnHuiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						AnHuidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/AnHuiSheng.zip/AnHuiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("ChongQing.zip")){
					
					int size = msg.getData().getInt("size");
					ChongQingPB.setProgress(size);
					float result = (float) ChongQingPB.getProgress()/ (float) ChongQingPB.getMax();
					int p = (int) (result * 100);
					cityChongQingTextView.setText(p + "%");
					
					if (ChongQingPB.getProgress() == ChongQingPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ChongQingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/ChongQing.zip/ChongQing.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("FuJianSheng.zip")){
					
					int size = msg.getData().getInt("size");
					FuJianPB.setProgress(size);
					float result = (float) FuJianPB.getProgress()/ (float) FuJianPB.getMax();
					int p = (int) (result * 100);
					cityFuJianTextView.setText(p + "%");
					
					if (FuJianPB.getProgress() == FuJianPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						FuJiandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/FuJianSheng.zip/FuJianSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("China_largescale.zip")){
					
					int size = msg.getData().getInt("size");
					GangAoPB.setProgress(size);
					float result = (float) GangAoPB.getProgress()/ (float) GangAoPB.getMax();
					int p = (int) (result * 100);
					cityGangAoTextView.setText(p + "%");
					
					if (GangAoPB.getProgress() == GangAoPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GangAodown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/China_largescale.zip/China_largescale.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						GangAodown_btn.setBackgroundDrawable(null);
						GangAodown_btn.setText("已下载");
						
						GangAoPB.setVisibility(1);
						cityGangAoTextView.setVisibility(1);
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
					
				}else if(msg.obj.equals("GanSuSheng.zip")){
					
					int size = msg.getData().getInt("size");
					GanSuPB.setProgress(size);
					float result = (float) GanSuPB.getProgress()/ (float) GanSuPB.getMax();
					int p = (int) (result * 100);
					cityGanSuTextView.setText(p + "%");
					
					if (GanSuPB.getProgress() == GanSuPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GanSudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/GanSuSheng.zip/GanSuSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("GuangDongSheng.zip")){
					
					int size = msg.getData().getInt("size");
					GuangDongPB.setProgress(size);
					float result = (float) GuangDongPB.getProgress()/ (float) GuangDongPB.getMax();
					int p = (int) (result * 100);
					cityGuangDongTextView.setText(p + "%");
					
					if (GuangDongPB.getProgress() == GuangDongPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GuangDongdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/GuangDongSheng.zip/GuangDongSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("GuangXiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					GuangXiPB.setProgress(size);
					float result = (float) GuangXiPB.getProgress()/ (float) GuangXiPB.getMax();
					int p = (int) (result * 100);
					cityGuangXiTextView.setText(p + "%");
					
					if (GuangXiPB.getProgress() == GuangXiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GuangXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/GuangXiSheng.zip/GuangXiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("GuiZhouSheng.zip")){
					
					int size = msg.getData().getInt("size");
					GuiZhouPB.setProgress(size);
					float result = (float) GuiZhouPB.getProgress()/ (float) GuiZhouPB.getMax();
					int p = (int) (result * 100);
					cityGuiZhouTextView.setText(p + "%");
					
					if (GuiZhouPB.getProgress() == GuiZhouPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						GuiZhoudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/GuiZhouSheng.zip/GuiZhouSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("HaiNanSheng.zip")){
					
					int size = msg.getData().getInt("size");
					HaiNanPB.setProgress(size);
					float result = (float) HaiNanPB.getProgress()/ (float) HaiNanPB.getMax();
					int p = (int) (result * 100);
					cityHaiNanTextView.setText(p + "%");
					
					if (HaiNanPB.getProgress() == HaiNanPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HaiNandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/HaiNanSheng.zip/HaiNanSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("HeBeiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					HeBeiPB.setProgress(size);
					float result = (float) HeBeiPB.getProgress()/ (float) HeBeiPB.getMax();
					int p = (int) (result * 100);
					cityHeBeiTextView.setText(p + "%");
					
					if (HeBeiPB.getProgress() == HeBeiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HeBeidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/HeBeiSheng.zip/HeBeiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("HeiLongJiang.zip")){
					
					int size = msg.getData().getInt("size");
					HeiLongJiangPB.setProgress(size);
					float result = (float) HeiLongJiangPB.getProgress()/ (float) HeiLongJiangPB.getMax();
					int p = (int) (result * 100);
					cityHeiLongJiangTextView.setText(p + "%");
					
					if (HeiLongJiangPB.getProgress() == HeiLongJiangPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HeiLongJiangdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/HeiLongJiang.zip/HeiLongJiang.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("HeNanSheng.zip")){
					
					int size = msg.getData().getInt("size");
					HeNanPB.setProgress(size);
					float result = (float) HeNanPB.getProgress()/ (float) HeNanPB.getMax();
					int p = (int) (result * 100);
					cityHeNanTextView.setText(p + "%");
					
					if (HeNanPB.getProgress() == HeNanPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HeNandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/HeNanSheng.zip/HeNanSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("HuBeiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					HuBeiPB.setProgress(size);
					float result = (float) HuBeiPB.getProgress()/ (float) HuBeiPB.getMax();
					int p = (int) (result * 100);
					cityHuBeiTextView.setText(p + "%");
					
					if (HuBeiPB.getProgress() == HuBeiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HuBeidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/HuBeiSheng.zip/HuBeiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("HuNanSheng.zip")){
					
					int size = msg.getData().getInt("size");
					HuNanPB.setProgress(size);
					float result = (float) HuNanPB.getProgress()/ (float) HuNanPB.getMax();
					int p = (int) (result * 100);
					cityHuNanTextView.setText(p + "%");
					
					if (HuNanPB.getProgress() == HuNanPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						HuNandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/HuNanSheng.zip/HuNanSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
						
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
				}else if(msg.obj.equals("JiangSuSheng.zip")){
					
					int size = msg.getData().getInt("size");
					JiangSuPB.setProgress(size);
					float result = (float) JiangSuPB.getProgress()/ (float) JiangSuPB.getMax();
					int p = (int) (result * 100);
					cityJiangSuTextView.setText(p + "%");
					
					if (JiangSuPB.getProgress() == JiangSuPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						JiangSudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/JiangSuSheng.zip/JiangSuSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("JiangXiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					JiangXiPB.setProgress(size);
					float result = (float) JiangXiPB.getProgress()/ (float) JiangXiPB.getMax();
					int p = (int) (result * 100);
					cityJiangXiTextView.setText(p + "%");
					
					if (JiangXiPB.getProgress() == JiangXiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
							
						JiangXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/JiangXiSheng.zip/JiangXiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("JiLinSheng.zip")){
					
					int size = msg.getData().getInt("size");
					JiLingPB.setProgress(size);
					float result = (float) JiLingPB.getProgress()/ (float) JiLingPB.getMax();
					int p = (int) (result * 100);
					cityJiLingTextView.setText(p + "%");
					
					if (JiLingPB.getProgress() == JiLingPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						JiLingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/JiLinSheng.zip/JiLinSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("LiaoNingSheng.zip")){
					
					int size = msg.getData().getInt("size");
					LiaoNingPB.setProgress(size);
					float result = (float) LiaoNingPB.getProgress()/ (float) LiaoNingPB.getMax();
					int p = (int) (result * 100);
					cityLiaoNingTextView.setText(p + "%");
					
					if (LiaoNingPB.getProgress() == LiaoNingPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						LiaoNingdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/LiaoNingSheng.zip/LiaoNingSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("NeiMengGu.zip")){
					
					int size = msg.getData().getInt("size");
					NeiMengGuPB.setProgress(size);
					float result = (float) NeiMengGuPB.getProgress()/ (float) NeiMengGuPB.getMax();
					int p = (int) (result * 100);
					cityNeiMengGuTextView.setText(p + "%");
					
					if (NeiMengGuPB.getProgress() == NeiMengGuPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						NeiMengGudown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/NeiMengGu.zip/NeiMengGu.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("NingXia.zip")){
					
					int size = msg.getData().getInt("size");
					NingXiaPB.setProgress(size);
					float result = (float) NingXiaPB.getProgress()/ (float) NingXiaPB.getMax();
					int p = (int) (result * 100);
					cityNingXiaTextView.setText(p + "%");
					
					if (NingXiaPB.getProgress() == NingXiaPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						NingXiadown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/NingXia.zip/NingXia.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
						
						NingXiaPB.setVisibility(View.INVISIBLE);
						cityNingXiaTextView.setVisibility(View.INVISIBLE);
						NingXiadown_btn.setBackgroundDrawable(null);
						NingXiadown_btn.setText("已下载");
						//new File(zipFile).delete();
						//new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("QingHaiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					QingHaiPB.setProgress(size);
					float result = (float) QingHaiPB.getProgress()/ (float) QingHaiPB.getMax();
					int p = (int) (result * 100);
					cityQingHaiTextView.setText(p + "%");
					
					if (QingHaiPB.getProgress() == QingHaiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						QingHaidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/QingHaiSheng.zip/QingHaiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("ShaanXiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					ShaanXiPB.setProgress(size);
					float result = (float) ShaanXiPB.getProgress()/ (float) ShaanXiPB.getMax();
					int p = (int) (result * 100);
					cityShaanXiTextView.setText(p + "%");
					
					if (ShaanXiPB.getProgress() == ShaanXiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ShaanXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/ShaanXiSheng.zip/ShaanXiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("ShanDongSheng.zip")){
					
					int size = msg.getData().getInt("size");
					ShanDongPB.setProgress(size);
					float result = (float) ShanDongPB.getProgress()/ (float) ShanDongPB.getMax();
					int p = (int) (result * 100);
					cityShanDongTextView.setText(p + "%");
					
					if (ShanDongPB.getProgress() == ShanDongPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ShanDongdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/ShanDongSheng.zip/ShanDongSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
						
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
				}else if(msg.obj.equals("ShanXiSheng.zip")){
					
					int size = msg.getData().getInt("size");
					ShanXiPB.setProgress(size);
					float result = (float) ShanXiPB.getProgress()/ (float) ShanXiPB.getMax();
					int p = (int) (result * 100);
					cityShanXiTextView.setText(p + "%");
					
					if (ShanXiPB.getProgress() == ShanXiPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ShanXidown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/ShanXiSheng.zip/ShanXiSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
						
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
				}else if(msg.obj.equals("SiChuanSheng.zip")){
					
					int size = msg.getData().getInt("size");
					SiChuanPB.setProgress(size);
					float result = (float) SiChuanPB.getProgress()/ (float) SiChuanPB.getMax();
					int p = (int) (result * 100);
					citySiChuanTextView.setText(p + "%");
					
					if (SiChuanPB.getProgress() == SiChuanPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						SiChuandown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/SiChuanSheng.zip/SiChuanSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";

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
				}else if(msg.obj.equals("TianJin.zip")){
					
					int size = msg.getData().getInt("size");
					TianJinPB.setProgress(size);
					float result = (float) TianJinPB.getProgress()/ (float) TianJinPB.getMax();
					int p = (int) (result * 100);
					cityTianJinTextView.setText(p + "%");
					
					if (TianJinPB.getProgress() == TianJinPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						TianJindown_btn.setClickable(false);

						final String zipFile=rootDir+"/mobilemap/map/vmap/TianJin.zip/TianJin.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
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
				}else if(msg.obj.equals("XinJiang.zip")){
					
					int size = msg.getData().getInt("size");
					XinJiangPB.setProgress(size);
					float result = (float) XinJiangPB.getProgress()/ (float) XinJiangPB.getMax();
					int p = (int) (result * 100);
					cityXinJiangTextView.setText(p + "%");
					
					if (XinJiangPB.getProgress() == XinJiangPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						XinJiangdown_btn.setClickable(false);
						
						final String zipFile=rootDir+"/mobilemap/map/vmap/XinJiang.zip/XinJiang.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
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
				}else if(msg.obj.equals("XiZang.zip")){
					
					int size = msg.getData().getInt("size");
					XiZangPB.setProgress(size);
					float result = (float) XiZangPB.getProgress()/ (float) XiZangPB.getMax();
					int p = (int) (result * 100);
					cityXiZangTextView.setText(p + "%");
					
					if (XiZangPB.getProgress() == XiZangPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						XiZangdown_btn.setClickable(false);
						final String zipFile=rootDir+"/mobilemap/map/vmap/XiZang.zip/XiZang.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
						Thread t1 = new Thread(){           
							public void run() {
								UnZip.upZipFile(zipFile, targetDir);
			               }};
			               
			            t1.start();
		
						
						XiZangPB.setVisibility(View.INVISIBLE);
						cityXiZangTextView.setVisibility(View.INVISIBLE);
						XiZangdown_btn.setBackgroundDrawable(null);
						XiZangdown_btn.setText("已下载");
//						new File(zipFile).delete();
//						new File(zipFile).getParentFile().delete();
						
					}
				}else if(msg.obj.equals("YunNanSheng.zip")){
					
					int size = msg.getData().getInt("size");
					YunNanPB.setProgress(size);
					float result = (float) YunNanPB.getProgress()/ (float) YunNanPB.getMax();
					int p = (int) (result * 100);
					cityYunNanTextView.setText(p + "%");
					
					if (YunNanPB.getProgress() == YunNanPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						YunNandown_btn.setClickable(false);
						final String zipFile=rootDir+"/mobilemap/map/vmap/YunNanSheng.zip/YunNanSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
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
				}else if(msg.obj.equals("ZheJiangSheng.zip")){
					
					int size = msg.getData().getInt("size");
					ZheJiangPB.setProgress(size);
					float result = (float) ZheJiangPB.getProgress()/ (float) ZheJiangPB.getMax();
					int p = (int) (result * 100);
					cityZheJiangTextView.setText(p + "%");
					
					if (ZheJiangPB.getProgress() == ZheJiangPB.getMax()){
						Toast.makeText(OfflineMapActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
						
						ZheJiangdown_btn.setClickable(false);
						final String zipFile=rootDir+"/mobilemap/map/vmap/ZheJiangSheng.zip/ZheJiangSheng.zip";
						final String targetDir=rootDir+"/mobilemap/map/vmap/";
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
				Toast.makeText(OfflineMapActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	
	Boolean isWiFi=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.offlinemap);
		
		checkAndCreateDirectory("/mobilemap/map/vmap/");
		

		
		
		NavigateBack=(TextView)findViewById(R.id.NavigateBack);
		NavigateBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ReturnMainPage();				
			}
		});
		
		isWiFi=NetUtils.isWifiConnected(OfflineMapActivity.this);
		//Boolean isNetWork=NetUtils.isNetworkConnected(OfflineMapActivity.this);
		
				
		shanghaiPB=(ProgressBar)findViewById(R.id.citySHProgressbar);
		citySHTextView=(TextView)findViewById(R.id.citySHTextView);
		Shanghaidown_btn=(Button)findViewById(R.id.citySHDown);		
		Shanghaidown_btn.setOnClickListener(new OnClickListener() {			
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载上海市离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//Shanghaidown_btn.setClickable(false);
						shanghaiPB.setVisibility(View.VISIBLE);
						citySHTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/ShangHai.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","ShangHai.zip");
							download(path, dir,"ShangHai.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载北京市离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//BeiJingdown_btn.setClickable(false);
						beijingPB.setVisibility(View.VISIBLE);
						cityBJTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/BeiJing.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","BeiJing.zip");
							download(path, dir,"BeiJing.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载天津市离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//TianJindown_btn.setClickable(false);
						TianJinPB.setVisibility(View.VISIBLE);
						cityTianJinTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/TianJin.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","TianJin.zip");
							download(path, dir,"TianJin.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载重庆市离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ChongQingdown_btn.setClickable(false);
						ChongQingPB.setVisibility(View.VISIBLE);
						cityChongQingTextView.setVisibility(View.VISIBLE);
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/ChongQing.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","ChongQing.zip");
							download(path, dir,"ChongQing.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载安徽省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//AnHuidown_btn.setClickable(false);
						AnHuiPB.setVisibility(View.VISIBLE);
						cityAnHuiTextView.setVisibility(View.VISIBLE);
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/AnHuiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","AnHuiSheng.zip");
							download(path, dir,"AnHuiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载福建省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//FuJiandown_btn.setClickable(false);
						FuJianPB.setVisibility(View.VISIBLE);
						cityFuJianTextView.setVisibility(View.VISIBLE);	
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/FuJianSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","FuJianSheng.zip");
							download(path, dir,"FuJianSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载甘肃省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GanSudown_btn.setClickable(false);
						GanSuPB.setVisibility(View.VISIBLE);
						cityGanSuTextView.setVisibility(View.VISIBLE);	
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/GanSuSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","GanSuSheng.zip");
							download(path, dir,"GanSuSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
		
		GangAodown_btn=(Button)findViewById(R.id.cityQuanGuoJiChuDown);	
		GangAoPB=(ProgressBar)findViewById(R.id.cityQuanGuoJiChuProgressbar);
		cityGangAoTextView=(TextView)findViewById(R.id.cityQuanGuoJiChuTextView);
		GangAodown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载全国基础离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GangAodown_btn.setClickable(false);
						GangAoPB.setVisibility(View.VISIBLE);
						cityGangAoTextView.setVisibility(View.VISIBLE);
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/China_largescale.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","China_largescale.zip");
							download(path, dir,"China_largescale.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载广东省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GuangDongdown_btn.setClickable(false);
						GuangDongPB.setVisibility(View.VISIBLE);
						cityGuangDongTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/GuangDongSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","GuangDongSheng.zip");
							download(path, dir,"GuangDongSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载广西省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GuangXidown_btn.setClickable(false);
						GuangXiPB.setVisibility(View.VISIBLE);
						cityGuangXiTextView.setVisibility(View.VISIBLE);
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/GuangXiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","GuangXiSheng.zip");
							download(path, dir,"GuangXiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载贵州省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//GuiZhoudown_btn.setClickable(false);
						GuiZhouPB.setVisibility(View.VISIBLE);
						cityGuiZhouTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/GuiZhouSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","GuiZhouSheng.zip");
							download(path, dir,"GuiZhouSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载海南省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HaiNandown_btn.setClickable(false);
						HaiNanPB.setVisibility(View.VISIBLE);
						cityHaiNanTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/HaiNanSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","HaiNanSheng.zip");
							download(path, dir,"HaiNanSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载河北省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HeBeidown_btn.setClickable(false);
						HeBeiPB.setVisibility(View.VISIBLE);
						cityHeBeiTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/HeBeiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","HeBeiSheng.zip");
							download(path, dir,"HeBeiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载河南省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HeNandown_btn.setClickable(false);
						HeNanPB.setVisibility(View.VISIBLE);
						cityHeNanTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/HeNanSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","HeNanSheng.zip");
							download(path, dir,"HeNanSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载黑龙江省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HeiLongJiangdown_btn.setClickable(false);
						HeiLongJiangPB.setVisibility(View.VISIBLE);
						cityHeiLongJiangTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/HeiLongJiang.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","HeiLongJiang.zip");
							download(path, dir,"HeiLongJiang.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载湖北省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HuBeidown_btn.setClickable(false);
						HuBeiPB.setVisibility(View.VISIBLE);
						cityHuBeiTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/HuBeiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","HuBeiSheng.zip");
							download(path, dir,"HuBeiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载湖南省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//HuNandown_btn.setClickable(false);
						HuNanPB.setVisibility(View.VISIBLE);
						cityHuNanTextView.setVisibility(View.VISIBLE);
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/HuNanSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","HuNanSheng.zip");
							download(path, dir,"HuNanSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载吉林省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//JiLingdown_btn.setClickable(false);
						JiLingPB.setVisibility(View.VISIBLE);
						cityJiLingTextView.setVisibility(View.VISIBLE);
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/JiLinSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","JiLinSheng.zip");
							download(path, dir,"JiLinSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载江苏省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//JiangSudown_btn.setClickable(false);
						JiangSuPB.setVisibility(View.VISIBLE);
						cityJiangSuTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/JiangSuSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","JiangSuSheng.zip");
							download(path, dir,"JiangSuSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载江西省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){	
						//JiangXidown_btn.setClickable(false);
						JiangXiPB.setVisibility(View.VISIBLE);
						cityJiangXiTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/JiangXiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","JiangXiSheng.zip");
							download(path, dir,"JiangXiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载辽宁省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//LiaoNingdown_btn.setClickable(false);
						LiaoNingPB.setVisibility(View.VISIBLE);
						cityLiaoNingTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/LiaoNingSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","LiaoNingSheng.zip");
							download(path, dir,"LiaoNingSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载内蒙古离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//NeiMengGudown_btn.setClickable(false);
						NeiMengGuPB.setVisibility(View.VISIBLE);
						cityNeiMengGuTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/NeiMengGu.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","NeiMengGu.zip");
							download(path, dir,"NeiMengGu.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载宁夏离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//NingXiadown_btn.setClickable(false);
						NingXiaPB.setVisibility(View.VISIBLE);
						cityNingXiaTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/NingXia.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","NingXia.zip");
							download(path, dir,"NingXia.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载青海省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//QingHaidown_btn.setClickable(false);
						QingHaiPB.setVisibility(View.VISIBLE);
						cityQingHaiTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/QingHaiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","QingHaiSheng.zip");
							download(path, dir,"QingHaiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载山东省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ShanDongdown_btn.setClickable(false);
						ShanDongPB.setVisibility(View.VISIBLE);
						cityShanDongTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/ShanDongSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","ShanDongSheng.zip");
							download(path, dir,"ShanDongSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载山西省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ShanXidown_btn.setClickable(false);
						ShanXiPB.setVisibility(View.VISIBLE);
						cityShanXiTextView.setVisibility(View.VISIBLE);	
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/ShanXiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","ShanXiSheng.zip");
							download(path, dir,"ShanXiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载陕西省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ShaanXidown_btn.setClickable(false);
						ShaanXiPB.setVisibility(View.VISIBLE);
						cityShaanXiTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/ShaanXiSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","ShaanXiSheng.zip");
							download(path, dir,"ShaanXiSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载四川省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//SiChuandown_btn.setClickable(false);
						SiChuanPB.setVisibility(View.VISIBLE);
						citySiChuanTextView.setVisibility(View.VISIBLE);	
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/SiChuanSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","SiChuanSheng.zip");
							download(path, dir,"SiChuanSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
		
		XiZangdown_btn=(Button)findViewById(R.id.cityXiZangDown);	
		XiZangPB=(ProgressBar)findViewById(R.id.cityXiZangProgressbar);
		cityXiZangTextView=(TextView)findViewById(R.id.cityXiZangTextView);
		XiZangdown_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载西藏离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//XiZangdown_btn.setClickable(false);
						XiZangPB.setVisibility(View.VISIBLE);
						cityXiZangTextView.setVisibility(View.VISIBLE);	
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/XiZang.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","XiZang.zip");
							download(path, dir,"XiZang.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载新疆离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						
						//XinJiangdown_btn.setClickable(false);
						XinJiangPB.setVisibility(View.VISIBLE);
						cityXinJiangTextView.setVisibility(View.VISIBLE);
						
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/XinJiang.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","XinJiang.zip");
							download(path, dir,"XinJiang.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载云南省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//YunNandown_btn.setClickable(false);
						YunNanPB.setVisibility(View.VISIBLE);
						cityYunNanTextView.setVisibility(View.VISIBLE);	
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/YunNanSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","YunNanSheng.zip");
							download(path, dir,"YunNanSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
				AlertDialog.Builder builder = new Builder(OfflineMapActivity.this);
				builder.setTitle(R.string.map_download);
				builder.setMessage("确定下载浙江省离线地图？");
				builder.setCancelable(false);
				builder.setPositiveButton(R.string.map_download_ok, new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which){
						//ZheJiangdown_btn.setClickable(false);
						ZheJiangPB.setVisibility(View.VISIBLE);
						cityZheJiangTextView.setVisibility(View.VISIBLE);	
						if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && isWiFi) {
							String path ="http://server.lbschina.com.cn/MobileMap/Map/ZheJiangSheng.zip";
							File dir =new File(rootDir + "/mobilemap/map/vmap","ZheJiangSheng.zip");
							download(path, dir,"ZheJiangSheng.zip");
						} else {
							if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
								Toast.makeText(OfflineMapActivity.this, R.string.sdcarderror,Toast.LENGTH_SHORT).show();
							}
							if(!isWiFi){
								Toast.makeText(OfflineMapActivity.this, "当前无网络",Toast.LENGTH_SHORT).show();
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
					Downloader loader = new Downloader(OfflineMapActivity.this,path, dir, 1);
					
					int length = loader.getFileSize();// 获取文件的长度
					
					if(msgName.equals("ShangHai.zip")){
						shanghaiPB.setMax(length);
					}else if(msgName.equals("BeiJing.zip")){
						beijingPB.setMax(length);
					}else if(msgName.equals("AnHuiSheng.zip")){
						AnHuiPB.setMax(length);
					}else if(msgName.equals("ChongQing.zip")){
						ChongQingPB.setMax(length);
					}else if(msgName.equals("FuJianSheng.zip")){
						FuJianPB.setMax(length);
					}else if(msgName.equals("China_largescale.zip")){
						GangAoPB.setMax(length);
					}else if(msgName.equals("GanSuSheng.zip")){
						GanSuPB.setMax(length);
					}else if(msgName.equals("GuangDongSheng.zip")){
						GuangDongPB.setMax(length);
					}else if(msgName.equals("GuangXiSheng.zip")){
						GuangXiPB.setMax(length);
					}else if(msgName.equals("GuiZhouSheng.zip")){
						GuiZhouPB.setMax(length);
					}else if(msgName.equals("HaiNanSheng.zip")){
						HaiNanPB.setMax(length);
					}else if(msgName.equals("HeBeiSheng.zip")){
						HeBeiPB.setMax(length);
					}else if(msgName.equals("HeiLongJiang.zip")){
						HeiLongJiangPB.setMax(length);
					}else if(msgName.equals("HeNanSheng.zip")){
						HeNanPB.setMax(length);
					}else if(msgName.equals("HuBeiSheng.zip")){
						HuBeiPB.setMax(length);
					}else if(msgName.equals("HuNanSheng.zip")){
						HuNanPB.setMax(length);
					}else if(msgName.equals("JiangSuSheng.zip")){
						JiangSuPB.setMax(length);
					}else if(msgName.equals("JiangXiSheng.zip")){
						JiangXiPB.setMax(length);
					}else if(msgName.equals("JiLinSheng.zip")){
						JiLingPB.setMax(length);
					}else if(msgName.equals("LiaoNingSheng.zip")){
						LiaoNingPB.setMax(length);
					}else if(msgName.equals("NeiMengGu.zip")){
						NeiMengGuPB.setMax(length);
					}else if(msgName.equals("NingXia.zip")){
						NingXiaPB.setMax(length);
					}else if(msgName.equals("QingHaiSheng.zip")){
						QingHaiPB.setMax(length);
					}else if(msgName.equals("ShaanXiSheng.zip")){
						ShaanXiPB.setMax(length);
					}else if(msgName.equals("ShanDongSheng.zip")){
						ShanDongPB.setMax(length);
					}else if(msgName.equals("ShanXiSheng.zip")){
						ShanXiPB.setMax(length);
					}else if(msgName.equals("SiChuanSheng.zip")){
						SiChuanPB.setMax(length);
					}else if(msgName.equals("TianJin.zip")){
						TianJinPB.setMax(length);
					}else if(msgName.equals("XinJiang.zip")){
						XinJiangPB.setMax(length);
					}else if(msgName.equals("XiZang.zip")){
						XiZangPB.setMax(length);
					}else if(msgName.equals("YunNanSheng.zip")){
						YunNanPB.setMax(length);
					}else if(msgName.equals("ZheJiangSheng.zip")){
						ZheJiangPB.setMax(length);
					}else if(msgName.equals("ShangHai.zip")){
						shanghaiPB.setMax(length);
					}
					
					loader.download(new DownloadProgressListener() {
						
						public void onDownloadSize(int size) {// 可以实时得到文件下载的长度
							
							Message msg = new Message();
							
							if(msgName.equals("ShangHai.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("BeiJing.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("AnHuiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("ChongQing.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("FuJianSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("China_largescale.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("GanSuSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("GuangDongSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("GuangXiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("GuiZhouSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("HaiNanSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("HeBeiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("HeiLongJiang.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("HeNanSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("HuBeiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("HuNanSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("JiangSuSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("JiangXiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("JiLinSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("LiaoNingSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("NeiMengGu.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("NingXia.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("QingHaiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("ShaanXiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("ShanDongSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("ShanXiSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("SiChuanSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("TianJin.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("XinJiang.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("XiZang.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("YunNanSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("ZheJiangSheng.zip")){
								msg.obj=msgName;
							}else if(msgName.equals("ShangHai.zip")){
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
	
	public void checkAndCreateDirectory(String dirName){   
        File new_dir = new File(rootDir + dirName);   
        if(!new_dir.exists()){
            new_dir.mkdirs();   
        }   
	}   
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		File AnHuiShengfile=new File(rootDir+"/mobilemap/map/vmap/AnHuiSheng.aom");    
		if(AnHuiShengfile.exists()) { 
			//AnHuidown_btn.setClickable(false);
			AnHuiPB.setVisibility(View.INVISIBLE);
			cityAnHuiTextView.setVisibility(View.INVISIBLE);
			AnHuidown_btn.setBackgroundDrawable(null);
			AnHuidown_btn.setText("已下载");	
			
		}
		
		File beijingfile=new File(rootDir+"/mobilemap/map/vmap/beijing.aom");    
		if(beijingfile.exists()) { 
			//BeiJingdown_btn.setClickable(false);
			beijingPB.setVisibility(View.INVISIBLE);
			cityBJTextView.setVisibility(View.INVISIBLE);
			BeiJingdown_btn.setBackgroundDrawable(null);
			BeiJingdown_btn.setText("已下载");
		}
		
		File chongqingfile=new File(rootDir+"/mobilemap/map/vmap/chongqing.aom");    
		if(chongqingfile.exists()) { 
			//ChongQingdown_btn.setClickable(false);
			ChongQingPB.setVisibility(View.INVISIBLE);
			cityChongQingTextView.setVisibility(View.INVISIBLE);
			
			ChongQingdown_btn.setBackgroundDrawable(null);
			ChongQingdown_btn.setText("已下载");
		   
		} 
		
		File FuJianShengfile=new File(rootDir+"/mobilemap/map/vmap/FuJianSheng.aom");    
		if(FuJianShengfile.exists()) { 
			FuJianPB.setVisibility(View.INVISIBLE);
			cityFuJianTextView.setVisibility(View.INVISIBLE);
			FuJiandown_btn.setBackgroundDrawable(null);
			FuJiandown_btn.setText("已下载");
			//FuJiandown_btn.setClickable(false);
		   
		} 
		
		File xianggangfile=new File(rootDir+"/mobilemap/map/vmap/China_largescale.aom");    
		if(xianggangfile.exists()) {    
			GangAodown_btn.setBackgroundDrawable(null);
			GangAodown_btn.setText("已下载");			
			GangAoPB.setVisibility(View.INVISIBLE);
			cityGangAoTextView.setVisibility(View.INVISIBLE);
			//GangAodown_btn.setClickable(false);
		} 
		
		File GanSuShengfile=new File(rootDir+"/mobilemap/map/vmap/GanSuSheng.aom");    
		if(GanSuShengfile.exists()) { 
			GanSuPB.setVisibility(View.INVISIBLE);
			cityGanSuTextView.setVisibility(View.INVISIBLE);
			GanSudown_btn.setBackgroundDrawable(null);
			GanSudown_btn.setText("已下载");
			//GanSudown_btn.setClickable(false);
		   
		}
		
		File GuangDongShengfile=new File(rootDir+"/mobilemap/map/vmap/GuangDongSheng.aom");    
		if(GuangDongShengfile.exists()) { 
			GuangDongPB.setVisibility(View.INVISIBLE);
			cityGuangDongTextView.setVisibility(View.INVISIBLE);
			GuangDongdown_btn.setBackgroundDrawable(null);
			GuangDongdown_btn.setText("已下载");
			//GuangDongdown_btn.setClickable(false);
		} 	
		File GuangXiShengfile=new File(rootDir+"/mobilemap/map/vmap/GuangXiSheng.aom");    
		if(GuangXiShengfile.exists()) {
			GuangXiPB.setVisibility(View.INVISIBLE);
			cityGuangXiTextView.setVisibility(View.INVISIBLE);
			GuangXidown_btn.setBackgroundDrawable(null);
			GuangXidown_btn.setText("已下载");
			//GuangXidown_btn.setClickable(false);
		} 
		File GuiZhouShengfile=new File(rootDir+"/mobilemap/map/vmap/GuiZhouSheng.aom");    
		if(GuiZhouShengfile.exists()) {  
			GuiZhouPB.setVisibility(View.INVISIBLE);
			cityGuiZhouTextView.setVisibility(View.INVISIBLE);
			GuiZhoudown_btn.setBackgroundDrawable(null);
			GuiZhoudown_btn.setText("已下载");
			//GuiZhoudown_btn.setClickable(false);
		}
		
		File HaiNanShengfile=new File(rootDir+"/mobilemap/map/vmap/HaiNanSheng.aom");    
		if(HaiNanShengfile.exists()) { 
			HaiNanPB.setVisibility(View.INVISIBLE);
			cityHaiNanTextView.setVisibility(View.INVISIBLE);
			HaiNandown_btn.setBackgroundDrawable(null);
			HaiNandown_btn.setText("已下载");
			//HaiNandown_btn.setClickable(false);
		} 
		File HeBeiShengfile=new File(rootDir+"/mobilemap/map/vmap/HeBeiSheng.aom");    
		if(HeBeiShengfile.exists()) {  
			HeBeiPB.setVisibility(View.INVISIBLE);
			cityHeBeiTextView.setVisibility(View.INVISIBLE);
			HeBeidown_btn.setBackgroundDrawable(null);
			HeBeidown_btn.setText("已下载");
			//HeBeidown_btn.setClickable(false);
		} 
		File HeiLongJiangfile=new File(rootDir+"/mobilemap/map/vmap/HeiLongJiang.aom");    
		if(HeiLongJiangfile.exists()) {  
			HeiLongJiangPB.setVisibility(View.INVISIBLE);
			cityHeiLongJiangTextView.setVisibility(View.INVISIBLE);
			HeiLongJiangdown_btn.setBackgroundDrawable(null);
			HeiLongJiangdown_btn.setText("已下载");
			//HeiLongJiangdown_btn.setClickable(false);
		} 
		File HeNanShengfile=new File(rootDir+"/mobilemap/map/vmap/HeNanSheng.aom");    
		if(HeNanShengfile.exists()) { 
			HeNanPB.setVisibility(View.INVISIBLE);
			cityHeNanTextView.setVisibility(View.INVISIBLE);
			HeNandown_btn.setBackgroundDrawable(null);
			HeNandown_btn.setText("已下载");
			//HeNandown_btn.setClickable(false);
		} 
		
		File HuBeiShengfile=new File(rootDir+"/mobilemap/map/vmap/HuBeiSheng.aom");    
		if(HuBeiShengfile.exists()) {    
			HuBeiPB.setVisibility(View.INVISIBLE);
			cityHuBeiTextView.setVisibility(View.INVISIBLE);
			HuBeidown_btn.setBackgroundDrawable(null);
			HuBeidown_btn.setText("已下载");
			//HuBeidown_btn.setClickable(false);
		} 
		
		File HuNanShengfile=new File(rootDir+"/mobilemap/map/vmap/HuNanSheng.aom");    
		if(HuNanShengfile.exists()) {   
			HuNanPB.setVisibility(View.INVISIBLE);
			cityHuNanTextView.setVisibility(View.INVISIBLE);
			HuNandown_btn.setBackgroundDrawable(null);
			HuNandown_btn.setText("已下载");
			//HuNandown_btn.setClickable(false);
		}
		
		File JiangSuShengfile=new File(rootDir+"/mobilemap/map/vmap/JiangSuSheng.aom");    
		if(JiangSuShengfile.exists()) {
			JiangSuPB.setVisibility(View.INVISIBLE);
			cityJiangSuTextView.setVisibility(View.INVISIBLE);
			JiangSudown_btn.setBackgroundDrawable(null);
			JiangSudown_btn.setText("已下载");
			//JiangSudown_btn.setClickable(false);
		}
		
		File JiangXiShengfile=new File(rootDir+"/mobilemap/map/vmap/JiangXiSheng.aom");    
		if(JiangXiShengfile.exists()) { 
			JiangXiPB.setVisibility(View.INVISIBLE);
			cityJiangXiTextView.setVisibility(View.INVISIBLE);
			JiangXidown_btn.setBackgroundDrawable(null);
			JiangXidown_btn.setText("已下载");
			//JiangXidown_btn.setClickable(false);
		}
		
		File JiLinShengfile=new File(rootDir+"/mobilemap/map/vmap/JiLinSheng.aom");    
		if(JiLinShengfile.exists()) { 
			JiLingPB.setVisibility(View.INVISIBLE);
			cityJiLingTextView.setVisibility(View.INVISIBLE);
			JiLingdown_btn.setBackgroundDrawable(null);
			JiLingdown_btn.setText("已下载");
			//JiLingdown_btn.setClickable(false);
		} 
		
		File LiaoNingShengfile=new File(rootDir+"/mobilemap/map/vmap/LiaoNingSheng.aom");    
		if(LiaoNingShengfile.exists()) { 
			LiaoNingPB.setVisibility(View.INVISIBLE);
			cityLiaoNingTextView.setVisibility(View.INVISIBLE);
			LiaoNingdown_btn.setBackgroundDrawable(null);
			LiaoNingdown_btn.setText("已下载");
			//LiaoNingdown_btn.setClickable(false);
		} 
		
		File NeiMengGufile=new File(rootDir+"/mobilemap/map/vmap/NeiMengGu.aom");    
		if(NeiMengGufile.exists()) { 
			NeiMengGuPB.setVisibility(View.INVISIBLE);
			cityNeiMengGuTextView.setVisibility(View.INVISIBLE);
			NeiMengGudown_btn.setBackgroundDrawable(null);
			NeiMengGudown_btn.setText("已下载");
			//NeiMengGudown_btn.setClickable(false);
		}
		
		File NingXiafile=new File(rootDir+"/mobilemap/map/vmap/NingXia.aom");    
		if(NingXiafile.exists()) { 
			NingXiaPB.setVisibility(View.INVISIBLE);
			cityNingXiaTextView.setVisibility(View.INVISIBLE);
			NingXiadown_btn.setBackgroundDrawable(null);
			NingXiadown_btn.setText("已下载");
			//NingXiadown_btn.setClickable(false);
		} 
		File QingHaiShengfile=new File(rootDir+"/mobilemap/map/vmap/QingHaiSheng.aom");    
		if(QingHaiShengfile.exists()) { 
			QingHaiPB.setVisibility(View.INVISIBLE);
			cityQingHaiTextView.setVisibility(View.INVISIBLE);
			QingHaidown_btn.setBackgroundDrawable(null);
			QingHaidown_btn.setText("已下载");
			//QingHaidown_btn.setClickable(false);
		} 
		File ShaanXiShengfile=new File(rootDir+"/mobilemap/map/vmap/ShaanXiSheng.aom");    
		if(ShaanXiShengfile.exists()) { 
			ShaanXiPB.setVisibility(View.INVISIBLE);
			cityShaanXiTextView.setVisibility(View.INVISIBLE);
			ShaanXidown_btn.setBackgroundDrawable(null);
			ShaanXidown_btn.setText("已下载");	
			//ShaanXidown_btn.setClickable(false);
		} 
		
		File ShanDongShengfile=new File(rootDir+"/mobilemap/map/vmap/ShanDongSheng.aom");    
		if(ShanDongShengfile.exists()) { 
			ShanDongPB.setVisibility(View.INVISIBLE);
			cityShanDongTextView.setVisibility(View.INVISIBLE);
			ShanDongdown_btn.setBackgroundDrawable(null);
			ShanDongdown_btn.setText("已下载");
			//ShanDongdown_btn.setClickable(false);
		} 
		
		File shanghaifile=new File(rootDir+"/mobilemap/map/vmap/shanghai.aom");    
		if(shanghaifile.exists()) { 
			shanghaiPB.setVisibility(View.INVISIBLE);
			citySHTextView.setVisibility(View.INVISIBLE);
			Shanghaidown_btn.setBackgroundDrawable(null);
			Shanghaidown_btn.setText("已下载");	
			//Shanghaidown_btn.setClickable(false);
		} 
		File ShanXiShengfile=new File(rootDir+"/mobilemap/map/vmap/ShanXiSheng.aom");    
		if(ShanXiShengfile.exists()) { 
			ShanXiPB.setVisibility(View.INVISIBLE);
			cityShanXiTextView.setVisibility(View.INVISIBLE);
			ShanXidown_btn.setBackgroundDrawable(null);
			ShanXidown_btn.setText("已下载");
			//ShanXidown_btn.setClickable(false);
		} 
		
		File SiChuanShengfile=new File(rootDir+"/mobilemap/map/vmap/SiChuanSheng.aom");    
		if(SiChuanShengfile.exists()) { 
			SiChuanPB.setVisibility(View.INVISIBLE);
			citySiChuanTextView.setVisibility(View.INVISIBLE);
			SiChuandown_btn.setBackgroundDrawable(null);
			SiChuandown_btn.setText("已下载");
			//SiChuandown_btn.setClickable(false);
		} 
		
		File tianjinfile=new File(rootDir+"/mobilemap/map/vmap/tianjin.aom");    
		if(tianjinfile.exists()) { 
			TianJinPB.setVisibility(View.INVISIBLE);
			cityTianJinTextView.setVisibility(View.INVISIBLE);
			TianJindown_btn.setBackgroundDrawable(null);
			TianJindown_btn.setText("已下载");
			//TianJindown_btn.setClickable(false);
		} 
		File XinJiangfile=new File(rootDir+"/mobilemap/map/vmap/XinJiang.aom");    
		if(XinJiangfile.exists()) { 
			XinJiangPB.setVisibility(View.INVISIBLE);
			cityXinJiangTextView.setVisibility(View.INVISIBLE);
			XinJiangdown_btn.setBackgroundDrawable(null);
			XinJiangdown_btn.setText("已下载");
			//XinJiangdown_btn.setClickable(false);
		} 
		File XiZangfile=new File(rootDir+"/mobilemap/map/vmap/XiZang.aom");    
		if(XiZangfile.exists()) { 
			XiZangPB.setVisibility(View.INVISIBLE);
			cityXiZangTextView.setVisibility(View.INVISIBLE);
			XiZangdown_btn.setBackgroundDrawable(null);
			XiZangdown_btn.setText("已下载");
			//XiZangdown_btn.setClickable(false);
		} 
		File YunNanShengfile=new File(rootDir+"/mobilemap/map/vmap/YunNanSheng.aom");    
		if(YunNanShengfile.exists()) { 
			YunNanPB.setVisibility(View.INVISIBLE);
			cityYunNanTextView.setVisibility(View.INVISIBLE);
			YunNandown_btn.setBackgroundDrawable(null);
			YunNandown_btn.setText("已下载");
			//YunNandown_btn.setClickable(false);
		} 
		File ZheJiangShengfile=new File(rootDir+"/mobilemap/map/vmap/ZheJiangSheng.aom");    
		if(ZheJiangShengfile.exists()) { 
			ZheJiangPB.setVisibility(View.INVISIBLE);
			cityZheJiangTextView.setVisibility(View.INVISIBLE);
			ZheJiangdown_btn.setBackgroundDrawable(null);
			ZheJiangdown_btn.setText("已下载");
			//ZheJiangdown_btn.setClickable(false);
		} 
		
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	private void ReturnMainPage() {
		Intent myIntent = new Intent();
		myIntent = new Intent(OfflineMapActivity.this, MainActivity.class);
		startActivity(myIntent);
		overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
		this.finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ReturnMainPage();
		}		
		return super.onKeyDown(keyCode, event);
	}
}
