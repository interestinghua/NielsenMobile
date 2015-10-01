package com.lbschina.srt.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.lbschina.srt.MapConstants;
import com.lbschina.srt.R;
import com.lbschina.srt.NewPoint;

/**
 * @author Rainny 
 */

public class SalesPointHelp {

	SQLiteDatabase mSRTDB=null;
	public static final String TABLE = "newpoint";
	String tableroute = "ROUTE";
	String tablerouteAss = "ROUTE_ASSIGN_POINT";

	// initialization ,need to be called right after constructor;
	public void init(Context context) {
		try {			
			/**
			 * /storage/sdcard0/mobilemap/db
			 */
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
				String databasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mobilemap/db";			
				String databaseFilename = databasePath + "/"+ MapConstants.DATABASE_FILENAME;			
				File dir = new File(databasePath);
				
				//若路径不存在，则创建该路径
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				//若文件不存在，则读取该文件
				if (!(new File(databaseFilename)).exists()) {
					InputStream is = context.getResources().openRawResource(R.raw.newpoint);
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
				
				mSRTDB = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//插入新获取的点
	public long NewPointInsert(String id,float gpsx,float gpsy,float x,float y,float distance,String reason,int isover,String createtime){
		
		String sql = "insert into " + TABLE	+ " (id,gpsx,gpsy,x,y,distance,reason,isover,createtime) values ('" + id + "','" + gpsx + "','" + gpsy + "','" + x
				+ "','" + y + "','" + distance +"','"+reason+"','"+isover+ "','"+createtime+"')";
		Log.i("SQL", sql);
		
		if(mSRTDB!=null){
			mSRTDB.execSQL(sql);
			return 1;
		}		
		return 0;
	}
	

	/**
	 * 返回所有售点
	 */
	public ArrayList<NewPoint> getAllPoint() {

		ArrayList<NewPoint> newpoint_list = new ArrayList<NewPoint>();
		
		String sql = "select id,x,y,createtime,reason from  " + TABLE;
		
		Log.i("SQL",sql);
		
		if(mSRTDB!=null){
			Cursor cursor = mSRTDB.rawQuery(sql, null);
			
			while (cursor.moveToNext()) {
				String SID = cursor.getString(0);
				float X = cursor.getFloat(1);
				float Y = cursor.getFloat(2);
				String createtime=cursor.getString(3);

				NewPoint spoint = new NewPoint();
				
				spoint.ID =SID;			
				spoint.X = String.valueOf(X);
				spoint.Y = String.valueOf(Y);
				spoint.createTime=createtime;
				
				newpoint_list.add(spoint);
			}
			cursor.close();
		}

		return newpoint_list;
	}

	/**
	 * 返回指定ID的售点
	 * @param PID
	 * @return
	 */
	public ArrayList<NewPoint> getPoint(String PID) {
		
		ArrayList<NewPoint> newpoint_list = new ArrayList<NewPoint>();
		String sql = "select id,x,y,createtime,reason,gpsx,gpsy from  " + TABLE + " where id=" + "'"+PID+"'";
		
		if(mSRTDB!=null){
			Cursor cursor = mSRTDB.rawQuery(sql, null);
			while (cursor.moveToNext()) {
				int SID = cursor.getInt(0);			
				int X = cursor.getInt(1);
				int Y = cursor.getInt(2);	
				String createtime=cursor.getString(3);
				String mReason=cursor.getString(4);
				float gpsx=cursor.getFloat(5);
				float gpsy=cursor.getFloat(6);

				NewPoint spoint = new NewPoint();
				spoint.ID =String.valueOf(SID);			
				spoint.X = String.valueOf(X);
				spoint.Y = String.valueOf(Y);
				spoint.GPSX = String.valueOf(gpsx);
				spoint.GPSY = String.valueOf(gpsy);
				spoint.createTime=createtime;
				spoint.reason=mReason;
				
				newpoint_list.add(spoint);
			}
			cursor.close();
		}

		return newpoint_list;
	}
	

	/**
	 * 删除指定ID点
	 * @param SID
	 */
	public long delPoint(String SID) {
		String sql = "DELETE FROM " + TABLE + " WHERE id = " + "'"+SID+"'";
		mSRTDB.execSQL(sql);
		return 1;
	}

	public Cursor Select(String sql) {
		if (mSRTDB == null) {
			System.out.println("null");
			return null;
		}
		Cursor cursor = mSRTDB.rawQuery(sql, null);

		return cursor;
	}

	public long Insert(ContentValues values) {

		long returnValue = -1;
		if (mSRTDB == null) {
			System.out.println("null");
			return returnValue;
		}
		returnValue = mSRTDB.insert(TABLE, null, values);

		return returnValue;
	}

	//更新routeassign
	public long addPointrouteass(int PID, int WEEK, int DAY,
			String GEOPOINTSSTRING) {

		String sql = "insert into " + tablerouteAss
				+ " (PID,WEEK,DAY,GEOPOINTSSTRING) values (" + PID + "," + WEEK
				+ "," + DAY + "," + GEOPOINTSSTRING + ")";
		mSRTDB.execSQL(sql);

		return 1;
	}

	//更新route
	public long addPointroute(int PID, int WEEK, int DAY, String GEOPOINTSSTRING) {

		String sql = "insert into " + tableroute
				+ " (PID,WEEK,DAY,GEOPOINTSSTRING) values (" + PID + "," + WEEK
				+ "," + DAY + "," + GEOPOINTSSTRING + ")";
		mSRTDB.execSQL(sql);

		return 1;
	}

	// 添加到本地SQL
	public long addPoint(String StrID, int X, int Y, int per) {

		String sql = "insert into " + TABLE
				+ " (StrID,PID,X,Y,CreateTime,upload) values (" + StrID + ","
				+ MapConstants.PID + "," + X + "," + Y + ","
				+ "datetime('now','localtime')," + 0 + ")";
		mSRTDB.execSQL(sql);

		return 1;
	}

	public long addPoint2(String PointName, String StrID, int X, int Y, int per) {

		String sql = "insert into "
				+ TABLE
				+ " (PointName,StrID,PID,X,Y,CreateTime,upload,NoLoad) values ( '"
				+ PointName + "'," + StrID + "," + MapConstants.PID + "," + X
				+ "," + Y + "," + "datetime('now','localtime')," + 0 + "," + 0
				+ ")";
		mSRTDB.execSQL(sql);

		return 1;
	}

	public long addroute(String GEOPOINTSSTRING, String WEEK, String DAY,
			String PID) {

		String sql = "insert into " + tableroute
				+ " (GEOPOINTSSTRING,WEEK,DAY,PID) values ( " + "'"
				+ GEOPOINTSSTRING + "'," + DAY + "," + WEEK + " ," + PID + " )";
		mSRTDB.execSQL(sql);

		return 1;
	}

	public long addassign(String RID, String SID, String pointorder,
			String day, String week) {

		String sql = "insert into " + tablerouteAss
				+ " (RID,SID,pointorder,ASSIGN,Day,Week) values ( " + RID + ","
				+ SID + "," + pointorder + " ," + 0 + ",'" + day + "','" + week
				+ "' )";
		mSRTDB.execSQL(sql);

		return 1;
	}

	// 上传到服务器SQL
	public long addPoint1(String StrID, int X, int Y, int per) {

		String sql = "insert into " + TABLE
				+ " (StrID,PID,X,Y,CreateTime,upload) values (" + StrID
				+ "," + MapConstants.PID + "," + X + "," + Y + ","
				+ "datetime('now','localtime')," + 1 + "," + 1 + ")";
		Log.i("ADDSQL", sql);
		mSRTDB.execSQL(sql);

		return 1;
	}

	// 上传到服务器SQL
	public long addPointnet(String StrID, int X, int Y) {

		String sql = "insert into " + TABLE
				+ " (StrID,PID,X,Y,CreateTime) values (" + StrID + ","
				+ MapConstants.PID + "," + X + "," + Y + ","
				+ "datetime('now','localtime'))";
		mSRTDB.execSQL(sql);

		return 1;
	}


	public void upPoint(int SID) {
		String sql = "update " + TABLE + " set upload = " + 0
				+ "  WHERE SID = " + SID + "";
		mSRTDB.execSQL(sql);
	}

	// -------------签到之后，更新本地数据库--------------
	public void upPointROOTSA() {
		String sql = "update " + tablerouteAss + " set ASSIGN = " + 0;
		mSRTDB.execSQL(sql);
	}

	public void upPointrouteassign(int RID, int pointorder) {
		String sql = "update " + tablerouteAss + " set ASSIGN = 1 WHERE RID = "
				+ RID + " and POINTORDER = " + pointorder;
		mSRTDB.execSQL(sql);

	}

	public long Update(ContentValues values, String whereClause,
			String[] whereArgs) {

		long returnValue = -1;
		if (mSRTDB == null) {
			System.out.println("null");
			return returnValue;
		}
		returnValue = mSRTDB.update(TABLE, values, whereClause, whereArgs);

		return returnValue;

	}

	// 删除数据
	public long Delete(String whereClause, String[] whereArgs) {

		long returnValue = -1;
		if (mSRTDB == null) {
			System.out.println("null");
			return returnValue;
		}
		returnValue = mSRTDB.delete(TABLE, whereClause, whereArgs);

		return returnValue;

	}

	// 下载数据时清空表
	public long Delete1(String Table, String whereClause, String[] whereArgs) {

		long returnValue = -1;
		if (mSRTDB == null) {
			System.out.println("null");
			return returnValue;
		}
		returnValue = mSRTDB.delete(Table, whereClause, whereArgs);

		return returnValue;

	}

	public void dispose() {
		mSRTDB.close();
	}
}
