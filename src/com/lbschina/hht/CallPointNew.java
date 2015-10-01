package com.lbschina.hht;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.lbschina.hht.entity.NieHisPoint;
import com.lbschina.srt.MapConstants;
import com.lbschina.srt.R;

public class CallPointNew {
	
	SQLiteDatabase mHHTDB=null;
	public static final String TABLE_SALESPOINT = "STOREHISTORY";
	
	public String mCityID=null;
	String databasePath=null;
	String databaseFilename=null;
	
	String mDbProvince=null;
	SQLiteDatabase mProDB=null;
	String mProName=null;
	
	public void init(Context context,String mCityID) {
		try {			
			/**
			 * /storage/sdcard0/mobilemap/db
			 */
			
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
				databasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mobilemap/db";
				mProName=this.getProvince(mCityID);
				if(mProName.equals("云南省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_YUNNAN;
				}else if(mProName.equals("河南省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_HENAN;
				}else if(mProName.equals("贵州省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_GUIZHOU;
				}else if(mProName.equals("海南省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_HAINAN;
				}else if(mProName.equals("新疆自治区")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_XINJIANG;
				}else if(mProName.equals("黑龙江省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_HEILONGJIANG;
				}else if(mProName.equals("江西省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_JIANGXI;
				}else if(mProName.equals("内蒙古自治区")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_NEIMENGGU;
				}else if(mProName.equals("宁夏自治区")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_NINGXIA;
				}else if(mProName.equals("四川省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_SICHUAN;
				}else if(mProName.equals("湖南省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_HUNAN;
				}else if(mProName.equals("安徽省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_ANHUI;
				}else if(mProName.equals("天津市")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_TIANJING;
				}else if(mProName.equals("陕西省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_SHAANXI;
				}else if(mProName.equals("湖北省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_HUBEI;
				}else if(mProName.equals("吉林省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_JILIN;
				}else if(mProName.equals("江苏省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_JIANGSU;
				}else if(mProName.equals("上海市")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_SHANGHAI;
				}else if(mProName.equals("北京市")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_BEIJING;
				}else if(mProName.equals("山东省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_SHANDONG;
				}else if(mProName.equals("广西省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_GUANGXI;
				}else if(mProName.equals("辽宁省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_LIAONING;
				}else if(mProName.equals("浙江省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_ZHEJIANG;
				}else if(mProName.equals("福建省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_FUJIAN;
				}else if(mProName.equals("广东省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_GUANGDONG;
				}else if(mProName.equals("山西省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_SHANXI;
				}else if(mProName.equals("河北省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_HEBEI;
				}else if(mProName.equals("甘肃省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_GANSU;
				}else if(mProName.equals("重庆市")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_CHONGQING;
				}else if(mProName.equals("青海省")){
					databaseFilename = databasePath + "/"+ MapConstants.POINTS_DB_QINHAI;
				}
				
				File dir = new File(databasePath);
				
				//若路径不存在，则创建该路径
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				//若文件不存在，则读取该文件
				if (!(new File(databaseFilename)).exists()) {
					InputStream is = context.getResources().openRawResource(R.raw.salespointinfo);
					File dbName = new File(databaseFilename);
					FileOutputStream fos = new FileOutputStream(dbName);
					
					byte[] buffer = new byte[1024];
					int count = 0;
					
					while ((count = is.read(buffer)) > 0) {
						fos.write(buffer, 0, count);
					}
					
					fos.flush();
					fos.close();
					is.close();
				}
				
				mHHTDB = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initProDB(Context context) {
		try {			
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
				databasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mobilemap/db";
				mDbProvince= databasePath + "/cityid";
				
				File dir = new File(databasePath);
				
				//若路径不存在，则创建该路径
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				//若文件不存在，则读取该文件				
				if (!(new File(mDbProvince)).exists()) {
					InputStream is = context.getResources().openRawResource(R.raw.cityid);
					File dbName = new File(mDbProvince);
					FileOutputStream fos = new FileOutputStream(dbName);
					
					byte[] buffer = new byte[1024];
					int count = 0;
					
					while ((count = is.read(buffer)) > 0) {
						fos.write(buffer, 0, count);
					}
					
					fos.flush();
					fos.close();
					is.close();
				}
				
				mProDB = SQLiteDatabase.openOrCreateDatabase(mDbProvince, null);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getProvince(String cityID){
		
		String sql = "select PROVINCE from CITY_ID where CITY='"+cityID+"'";
		String mProvince=null;
		
		if(mProDB!=null){
			Cursor cursor = mHHTDB.rawQuery(sql,null);
			
			while (cursor.moveToNext()) {
				mProvince = cursor.getString(0);				
			}			
			cursor.close();
			return mProvince.trim();
		}
		
		return null;		
	}
	
	public ArrayList<NieHisPoint> getSalesPointID(String PID) {

		ArrayList<NieHisPoint> NieHisPoint_list = null;
		
		String sql = "select Block_ID,Y,X,CITY_ID,CITY,STORE_ID from " + TABLE_SALESPOINT + " where STORE_ID='"+PID+"'";
		
		Log.i("SQL",sql);
		
		if(mHHTDB!=null){
			
			NieHisPoint_list = new ArrayList<NieHisPoint>();
			Cursor cursor = mHHTDB.rawQuery(sql,null);
			
			while (cursor.moveToNext()) {
				String mPID = cursor.getString(5);			
				String CITYNAME = cursor.getString(4);
				String CityID = cursor.getString(3);
				float X = cursor.getFloat(2);
				float Y = cursor.getFloat(1);
				String Block_ID=cursor.getString(0);

				NieHisPoint mNieHisPoint = new NieHisPoint();
				
				mNieHisPoint.setPID(mPID);
				mNieHisPoint.setCITYID(CityID);
				mNieHisPoint.setCITYNAME(CITYNAME);
				mNieHisPoint.setPX(X);
				mNieHisPoint.setPY(Y);
				mNieHisPoint.setPointInBLOCKID(Block_ID);
				
				NieHisPoint_list.add(mNieHisPoint);
			}
			cursor.close();
		}

		return NieHisPoint_list;
	}
	
	
	public ArrayList<NieHisPoint> getSalesPointIDInBlock(String blockID,String pID) {

		ArrayList<NieHisPoint> NieHisPoint_list = null;
		
		String sql = "select Block_ID,Y,X,CITY_ID,CITY,STORE_ID from " + TABLE_SALESPOINT + " where Block_ID='"+blockID+"' and STORE_ID <> '"+pID+"'";
		
		Log.i("SQL",sql);
		
		if(mHHTDB!=null){
			
			NieHisPoint_list = new ArrayList<NieHisPoint>();
			
			Cursor cursor = mHHTDB.rawQuery(sql, null);
			
			while (cursor.moveToNext()) {
				String mPID = cursor.getString(5);			
				String CITYNAME = cursor.getString(4);
				String CityID = cursor.getString(3);
				float X = cursor.getFloat(2);
				float Y = cursor.getFloat(1);
				String Block_ID=cursor.getString(0);

				NieHisPoint mNieHisPoint = new NieHisPoint();
				
				mNieHisPoint.setPID(mPID);
				mNieHisPoint.setCITYID(CityID);
				mNieHisPoint.setCITYNAME(CITYNAME);
				mNieHisPoint.setPX(X);
				mNieHisPoint.setPY(Y);
				mNieHisPoint.setPointInBLOCKID(Block_ID);
				
				NieHisPoint_list.add(mNieHisPoint);
			}
			cursor.close();
		}

		return NieHisPoint_list;
	}

}
