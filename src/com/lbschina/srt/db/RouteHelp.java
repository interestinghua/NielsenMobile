package com.lbschina.srt.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.lbschina.srt.MapConstants;
import com.lbschina.srt.R;
import com.lbschina.srt.NewPoint;

/**
 * @author Rainny
 * 
 */

public class RouteHelp {

	SQLiteDatabase mSRTDB;
	String table = "Route";

	// initialization ,need to be called right after constructor;
	public void init(Context context) {
		try {
			String databasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SRT";
			String databaseFilename = databasePath + "/" + MapConstants.DATABASE_FILENAME;
			File dir = new File(databasePath);
			
			if (!dir.exists()) {
				dir.mkdir();
			}
			if (!(new File(databaseFilename)).exists()) {
				InputStream is = context.getResources().openRawResource(R.raw.newpoint);
				FileOutputStream fos = new FileOutputStream(databaseFilename);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Route getRoute1(Date date, int PID) {

		Route route = null;
		int week = MapConstants.displayWeek1(date);
		int day = MapConstants.displayDay1(date);

		String sql = "select RID, GEOPOINTSSTRING from  " + table + " where Week=" + week + " and Day=" + day;
		Cursor cursor = mSRTDB.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			route = new Route();
			int RID = cursor.getInt(0);
			String GEOPOINTSSTRING = cursor.getString(1);

			route.RID = RID;
			route.Day = day;
			route.Week = week;
			route.GEOPOINTSSTRING = GEOPOINTSSTRING;
			String day1 = "Day" + String.valueOf(day);
			String week1 = "Week" + String.valueOf(week);
			route.salesPoints = getRouteAllPoint1(day1, week1);
		}
		cursor.close();
		return route;
	}

	public Route getRoute(Date date, int PID) {

		Route route = null;
		int week = MapConstants.displayWeek(date);
		int day = MapConstants.displayDay(date);

		String sql = "select RID, GEOPOINTSSTRING from  " + table + " where Week=" + week + " and Day=" + day;
		Cursor cursor = mSRTDB.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			route = new Route();
			int RID = cursor.getInt(0);
			String GEOPOINTSSTRING = cursor.getString(1);

			route.RID = RID;
			route.Day = day;
			route.Week = week;
			route.GEOPOINTSSTRING = GEOPOINTSSTRING;
			String day1 = "Day" + String.valueOf(day);
			String week1 = "Week" + String.valueOf(week);
			route.salesPoints = getRouteAllPoint(day1, week1);
		}
		cursor.close();
		return route;
	}

	public ArrayList<NewPoint> getRouteAllPoint1(String day, String week) {

		ArrayList<NewPoint> al = new ArrayList<NewPoint>();
		String sql = "select sales_point.SID,sales_point.StrID,sales_point.POINTNAME,sales_point.X,sales_point.Y,sales_point.CreateTime,ROUTE_ASSIGN_POINT.POINTORDER "
				+ "from  sales_point,ROUTE_ASSIGN_POINT where sales_point.StrID =ROUTE_ASSIGN_POINT.SID  and  ROUTE_ASSIGN_POINT.Day= '"
				+ day
				+ "' and  ROUTE_ASSIGN_POINT.week='"
				+ week
				+ "' and ROUTE_ASSIGN_POINT.ASSIGN=  0 "
				+ " order by route_assign_point.pointorder";
		Cursor cursor = mSRTDB.rawQuery(sql, null);
		while (cursor.moveToNext()) {

			
			int X = cursor.getInt(3);
			int Y = cursor.getInt(4);
			
			NewPoint spoint = new NewPoint();
			
			spoint.X = String.valueOf(X);
			spoint.Y = String.valueOf(Y);
			
			al.add(spoint);
		}
		cursor.close();
		return al;
	}

	/**
	 * 返回该ROute所有售点
	 * 
	 * @param PID
	 * @return
	 */
	public ArrayList<NewPoint> getRouteAllPoint(String day, String week) {

		ArrayList<NewPoint> al = new ArrayList<NewPoint>();
		// String sql =
		// "select sales_point.SID,sales_point.StrID,sales_point.POINTNAME,sales_point.X,sales_point.Y,sales_point.CreateTime,ROUTE_ASSIGN_POINT.POINTORDER "
		// +
		// "from  sales_point,ROUTE_ASSIGN_POINT where sales_point.StrID =ROUTE_ASSIGN_POINT.SID  and ROUTE_ASSIGN_POINT.RID="
		// + RID + " order by route_assign_point.pointorder";

		String sql = "select sales_point.SID,sales_point.StrID,sales_point.POINTNAME,sales_point.X,sales_point.Y,sales_point.CreateTime,ROUTE_ASSIGN_POINT.POINTORDER "
				+ "from  sales_point,ROUTE_ASSIGN_POINT where sales_point.StrID =ROUTE_ASSIGN_POINT.SID  and ROUTE_ASSIGN_POINT.Day= '"
				+ day
				+ "' and  ROUTE_ASSIGN_POINT.week='"
				+ week
				+ "'  order by route_assign_point.pointorder";

		Cursor cursor = mSRTDB.rawQuery(sql, null);
		while (cursor.moveToNext()) {

			
			int X = cursor.getInt(3);
			int Y = cursor.getInt(4);
			
			NewPoint spoint = new NewPoint();
			
			spoint.X = String.valueOf(X);
			spoint.Y = String.valueOf(Y);
			
			al.add(spoint);
		}
		cursor.close();
		return al;
	}

	public long addPoint(String StrID, int X, int Y) {

		String sql = "insert into " + table
				+ " (StrID,PID,X,Y,CreateTime) values (" + StrID + ","
				+ MapConstants.PID + "," + X + "," + Y + ","
				+ "datetime('now','localtime'))";
		mSRTDB.execSQL(sql);

		return 1;
	}

	public void delPoint(int SID) {

		String sql = "DELETE FROM " + table + " WHERE SID = " + SID + "";
		mSRTDB.execSQL(sql);

	}

	// ContentValues values = new ContentValues();
	// values.put("name", "批量更新后的名字");
	// db.update(TABLE_NAME, values, "id<?", new String[] { "3" });

	public long Update(ContentValues values, String whereClause,
			String[] whereArgs) {

		long returnValue = -1;
		if (mSRTDB == null) {
			System.out.println("null");
			return returnValue;
		}
		returnValue = mSRTDB.update(table, values, whereClause, whereArgs);

		return returnValue;

	}

	// 删除数据
	// db.delete(TABLE_NAME, " id < 999999", null);

	public long Delete(String whereClause, String[] whereArgs) {

		long returnValue = -1;
		if (mSRTDB == null) {
			System.out.println("null");
			return returnValue;
		}
		returnValue = mSRTDB.delete(table, whereClause, whereArgs);

		return returnValue;

	}

	public void dispose() {
		mSRTDB.close();
	};

}
