package com.lbschina.srt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.core.GeoPoint;

public class GpsService extends Service implements LocationListener {

	LocationManager locManager;
	private String TAG;
	public GeoPoint gpGeoPoint;
	private LocationManagerProxy locationManager = null;
	private Thread thread;
	private Location location;
	private Double geoLat;
	private Double geoLng;
	protected Message msg;
	private SharedPreferences sp;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == MapConstants.FIRST_LOCATION) {
				instal();
			}
		}
	};

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	public void instal() {
		locationManager = LocationManagerProxy.getInstance(this);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		thread.suspend();
		super.onDestroy();
	}

	@Override
	public void onCreate() {
		
		locationManager = LocationManagerProxy.getInstance(this);
		sp = this.getSharedPreferences("SRT", Context.MODE_WORLD_READABLE);
		final String user = sp.getString("USER_NAME", "");

		Runnable insertRunnable = new Runnable() {
			@Override
			public void run() {
				Looper.prepare();
				while (true) {
					Log.i(TAG, "Service onCreate--->");
					try {

						Thread.sleep(1800000);

						String bestprovider = enableMyLocation();
						Location bestLocation = null;
						if (bestprovider != null) {
							bestLocation = locationManager
									.getLastKnownLocation(bestprovider);
						}
						if (bestLocation != null) {

							Double x1 = bestLocation.getLongitude();
							Double y1 = bestLocation.getLatitude();
							// ----------------------上传用户GPS位置-------------
							InsertGps insert = new InsertGps();
							insert.InsertGPS(user, x1, y1);

						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(null, insertRunnable, "MagentoBackground");
		thread.start();
		super.onCreate();

	}

	@Override
	public void onLocationChanged(Location arg0) {
		if (location != null) {
			geoLat = location.getLatitude();
			geoLng = location.getLongitude();
			String str = ("定位成功:(" + geoLng + "," + geoLat + ")");
			Message msg = new Message();
			msg.obj = str;
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	}

	public String enableMyLocation() {
		try {
			locationManager = LocationManagerProxy.getInstance(this);
			Criteria cri = new Criteria();
			cri.setAccuracy(Criteria.ACCURACY_COARSE);
			cri.setAltitudeRequired(false);
			cri.setBearingRequired(false);
			cri.setCostAllowed(false);
			String bestProvider = locationManager.getBestProvider(cri, true);
			return bestProvider;
		} catch (Exception ex) {
			Log.i(TAG, ex.toString());
			return null;
		}
	}
}
