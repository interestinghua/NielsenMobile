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

import com.lbschina.srt.MapConstants;
import com.lbschina.srt.NewPoint;
import com.lbschina.srt.R;

public class HHTCallNewStoreHelp {
	
	SQLiteDatabase mNEWSTORE=null;
	public static final String TABLE = "NEWSTORE";

	// initialization ,need to be called right after constructor;
	public void init(Context context) {
		try {			
			/**
			 * /storage/sdcard0/mobilemap/db
			 */
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
				String databasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mobilemap/db";			
				String databaseFilename = databasePath + "/"+ MapConstants.NEWSTORE_DATABASE_FILENAME;			
				File dir = new File(databasePath);
				
				//若路径不存在，则创建该路径
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				//若文件不存在，则读取该文件
				if (!(new File(databaseFilename)).exists()) {
					InputStream is = context.getResources().openRawResource(R.raw.newstore);
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
				
				mNEWSTORE = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//插入新获取的点
	public long NewPointInsert(String id,float gpsx,float gpsy,float x,float y,float gpsx1,float gpsy1,float accu,String reason){
		
		String sql = "insert into " + TABLE	+ " (STORE_ID,GPSX,GPSY,X,Y,GPSX1,GPSY1,ACCUR,REASON) values ('" + id + "','" + gpsx + "','" + gpsy + "','" + x
				+ "','" + y + "','" + gpsx1 +"','"+gpsy1+"','"+accu+ "','"+reason+"')";
		Log.i("SQL", sql);
		
		if(mNEWSTORE!=null){
			mNEWSTORE.execSQL(sql);
			return 1;
		}		
		return 0;
	}
	
	//update新获取的点
	public long NewPointUpdate(String id,float x,float y,String reason){
		
		String UpdateXSql = "update " + TABLE + " set X = '" + x + "'  WHERE STORE_ID = '" + id + "'";
		String UpdateYSql = "update " + TABLE + " set Y = '" + y + "'  WHERE STORE_ID = '" + id + "'";
		String UpdateReasonSql = "update " + TABLE + " set REASON = '" + reason + "'  WHERE STORE_ID = '" + id + "'";
		
		Log.i("SQL", UpdateXSql);
		Log.i("SQL", UpdateYSql);
		Log.i("SQL", UpdateReasonSql);
		
		if(mNEWSTORE!=null){
			mNEWSTORE.execSQL(UpdateXSql);
			mNEWSTORE.execSQL(UpdateYSql);
			mNEWSTORE.execSQL(UpdateReasonSql);
			return 1;
		}		
		return 0;
	}

	/**
	 * 返回指定ID的售点
	 * @param PID
	 * @return
	 */
	public ArrayList<NewPoint> getPoint(String PID) {
		
		ArrayList<NewPoint> newpoint_list = new ArrayList<NewPoint>();
		String sql = "select STORE_ID,GPSX,GPSY,X,Y,GPSX1,GPSY1,ACCUR,REASON from " + TABLE + " where STORE_ID=" + "'"+PID+"'";
		
		if(mNEWSTORE!=null){
			
			Cursor cursor = mNEWSTORE.rawQuery(sql, null);
			
			while (cursor.moveToNext()) {
				String SID = cursor.getString(0);			
				float GPSX = cursor.getFloat(1);
				float GPSY = cursor.getFloat(2);
				float X = cursor.getFloat(3);
				float Y = cursor.getFloat(4);
				float GPSX1=cursor.getFloat(5);
				float GPSY1=cursor.getFloat(6);
				float ACCUR=cursor.getFloat(7);
				String REASON=cursor.getString(8);

				NewPoint spoint = new NewPoint();
				spoint.ID =SID;			
				spoint.X = String.valueOf(X);
				spoint.Y = String.valueOf(Y);
				spoint.GPSX1=String.valueOf(GPSX1);
				spoint.GPSY1=String.valueOf(GPSY1);
				spoint.ACCU=ACCUR;
				spoint.GPSX = String.valueOf(GPSX);
				spoint.GPSY = String.valueOf(GPSY);
				spoint.reason=REASON;
				
				newpoint_list.add(spoint);
			}
			cursor.close();
		}
		return newpoint_list;
	}

	public void dispose() {
		if(mNEWSTORE!=null){
			mNEWSTORE.close();
		}		
	}

}
