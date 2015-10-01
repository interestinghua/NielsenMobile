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

public class HHTCallPointHelp {
	
	SQLiteDatabase mHHTDB=null;
	public static final String TABLE_SALESPOINT = "STOREHISTORY";
	
	public void init(Context context) {
		try {			
			/**
			 * /storage/sdcard0/mobilemap/db
			 */
			
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				String databasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mobilemap/db";			
				String databaseFilename = databasePath + "/"+ MapConstants.POINTS_DATABASE_FILENAME;			
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
