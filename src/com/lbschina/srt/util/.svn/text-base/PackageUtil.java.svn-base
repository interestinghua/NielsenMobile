package com.lbschina.srt.util;

import java.util.List;

import android.app.ActivityManager;
import android.content.Context;

public class PackageUtil {
	
	public static boolean isServiceStarted(Context context, String PackageName) {
		boolean isStarted = false;
		try {
			ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			//int intGetTastCounter = 1000;
			int intGetTastCounter = 30;
			List<ActivityManager.RunningServiceInfo> mRunningService = mActivityManager.getRunningServices(intGetTastCounter);
			for (ActivityManager.RunningServiceInfo amService : mRunningService) {
				if (0 == amService.service.getPackageName().compareTo(PackageName)) {
					isStarted = true;
					break;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return isStarted;
	}
}
