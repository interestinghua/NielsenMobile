package com.lbschina.srt.db;

import java.util.ArrayList;

import com.amap.api.location.core.GeoPoint;
import com.lbschina.srt.NewPoint;

public class Route {
	public int RID;
	public int Day;
	public int Week;
	public String GEOPOINTSSTRING;

	public String getRouteName() {

		return Day + "_" + Week;
	}

	public ArrayList<GeoPoint> GetGeoPts() {
		ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();

		if (GEOPOINTSSTRING.contains(",")) {
			String[] geoStrings = GEOPOINTSSTRING.split(",");
			for (int i = 0; i < geoStrings.length; i++) {

				Double x = Double.parseDouble(geoStrings[i]);
				Double y = Double.parseDouble(geoStrings[i + 1]);
				GeoPoint geoPoint = new GeoPoint((int) (y * 1E6),
						(int) (x * 1E6));
				points.add(geoPoint);
				i++;
			}
		}
		return points;
	}

	public ArrayList<NewPoint> salesPoints = new ArrayList<NewPoint>();


}
