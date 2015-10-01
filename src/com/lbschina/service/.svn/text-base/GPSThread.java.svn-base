package com.lbschina.service;

import android.content.Context;
import android.content.Intent;

public class GPSThread extends Thread {
	
	Context contxt;
	
	public GPSThread(Context contxt) {
		super();
		this.contxt = contxt;
	}

	public void run() {
		Intent intentGPS = new Intent(contxt, GPSService.class);
		contxt.stopService(intentGPS);
	}

}
