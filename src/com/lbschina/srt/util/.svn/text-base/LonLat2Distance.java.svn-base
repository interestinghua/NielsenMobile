package com.lbschina.srt.util;

public class LonLat2Distance {
	
	public double Rad(double d) {
		return ((d * 3.1415926535897931) / 180.0);
	}

	public double DistanceOfTwoPoints(double lng1, double lat1,
									  double lng2, double lat2) {
		double d = this.Rad(lat1);
		double num2 = this.Rad(lat2);
		double num3 = d - num2;
		double num4 = this.Rad(lng1) - this.Rad(lng2);
		double num5 = 2.0 * Math.asin(Math.sqrt(Math.pow(Math.sin(num3 / 2.0),
				2.0)
				+ ((Math.cos(d) * Math.cos(num2)) * Math.pow(
						Math.sin(num4 / 2.0), 2.0))));
		num5 *= 6378137.0;
		return (Math.round((double) (num5 * 10000.0)) / 10000.0);
	}
}
