package com.lbschina.srt;

import java.util.Calendar;
import java.util.Date;

import com.amap.api.maps.model.LatLng;

public class MapConstants {
	
	public static double MapCenterX =121.517601;
	public static double MapCenterY = 31.215738;
	
	public static int perm = 0;
	public static int Zoom = 13;
	public static int PID = 1;
	public static String UserName;
	public final static String DATABASE_FILENAME = "newpoint";
	public final static String HHT_DATABASE_FILENAME = "point";
	public final static String BLOCK_DATABASE_FILENAME = "blockinfo";
	public final static String POINTS_DATABASE_FILENAME = "salespointinfo";
	public final static String NEWSTORE_DATABASE_FILENAME = "newstore";
	
	//售点
	public final static String POINTS_DB_YUNNAN = "spyunnan";
	public final static String POINTS_DB_HENAN = "sphenan";
	public final static String POINTS_DB_GUIZHOU = "spguizhou";	
	public final static String POINTS_DB_HAINAN = "sphainan";
	public final static String POINTS_DB_XINJIANG = "spxinjiang";
	public final static String POINTS_DB_HEILONGJIANG = "spheilongjiang";
	public final static String POINTS_DB_JIANGXI = "spjiangxi";
	public final static String POINTS_DB_NEIMENGGU = "spnengmenggu";
	public final static String POINTS_DB_NINGXIA = "spningxia";
	public final static String POINTS_DB_SICHUAN = "spsichuan";
	public final static String POINTS_DB_HUNAN = "sphunan";
	public final static String POINTS_DB_ANHUI = "spanhui";
	public final static String POINTS_DB_TIANJING = "sptianjing";
	public final static String POINTS_DB_SHAANXI = "spshaanxi";	
	public final static String POINTS_DB_HUBEI = "sphubei";
	public final static String POINTS_DB_JILIN = "spjilin";
	public final static String POINTS_DB_JIANGSU = "spjiangsu";	
	public final static String POINTS_DB_SHANGHAI = "spshanghai";
	public final static String POINTS_DB_BEIJING = "spbeijing";
	public final static String POINTS_DB_SHANDONG = "spshandong";
	public final static String POINTS_DB_GUANGXI = "spguangxi";
	public final static String POINTS_DB_LIAONING = "spliaoning";
	public final static String POINTS_DB_ZHEJIANG = "spzhejiang";
	public final static String POINTS_DB_FUJIAN = "spfujian";
	public final static String POINTS_DB_GUANGDONG = "spguangdong";
	public final static String POINTS_DB_SHANXI = "spshanxi";
	public final static String POINTS_DB_HEBEI = "sphebei";
	public final static String POINTS_DB_GANSU = "spgansu";
	public final static String POINTS_DB_CHONGQING = "spchongqing";
	public final static String POINTS_DB_QINHAI = "spqinhai";	
	
	public static final LatLng SHANGHAI = new LatLng(31.239879, 121.499674);// 上海市经纬度
	public static final LatLng FANGHENG = new LatLng(39.989614, 116.481763);// 方恒国际中心经纬度
	
	public static final int POISEARCH = 1000;
	public static final int ERROR = 1001;
	public static final int FIRST_LOCATION = 1002;

	public static final int ROUTE_START_SEARCH = 2000;	// 路径规划起点搜索
	public static final int ROUTE_END_SEARCH = 2001;	// 路径规划终点搜索
	public static final int ROUTE_SEARCH_RESULT = 2002;	// 路径规划结果
	public static final int ROUTE_SEARCH_ERROR = 2004;	// 路径规划起起始点搜索异常

	public static final int GEOCODER_RESULT = 3000;		// 地理编码结果
	public static final int DIALOG_LAYER = 4000;
	
	public static final int OFF_SET=40; //偏移标准
	public static final int QC_OFF_SET=20; //QC偏移标准

	public static int displayWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_num = c.get(Calendar.WEEK_OF_YEAR);

		if (week_num % 2 == 0)
			return 2;
		else {
			return 1;
		}
	}

	public static int displayWeek1(Date date) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		int week_num = c.get(Calendar.WEEK_OF_YEAR);

		if (week_num % 2 == 0)
			return 2;
		else {
			return 1;
		}
	}

	public static int displayDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}

	public static int displayDay1(Date date) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	//城市离线地图包大小
	public static long ANHUI = 146837504;
	public static long FUJIAN =78493696;
	
}
