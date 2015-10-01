package com.lbschina.srt.util;

import android.util.Log;

public class LogUtils {
	private static final String TAG = "LogUtils";

	public static final void thread() {
		LogUtils.thread(null);
	}

	public static final void thread(String msg) {
		Thread t = Thread.currentThread();
		Log.d(TAG,"<" + t.getName() + ">id: " + t.getId() + ", Priority: "	+ t.getPriority() + ", Group: "
						+ t.getThreadGroup().getName()
						+ (msg != null ? ",Msg:" + msg : ""));
	}

	public static final void d(String msg) {
		Log.d(TAG, msg);
	}

}
