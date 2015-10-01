package com.lbschina.srt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Environment;

public class FileDownload {
	
	public static String DownFile(String mUrl){
		try{
			if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				
				String sdpath = Environment.getExternalStorageDirectory() + "/";
				String mSavePath = sdpath + "mobilemap";
				
				URL url = new URL(mUrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.connect();
				InputStream is = conn.getInputStream();

				File file = new File(mSavePath,"update.xml");
				
				if (!file.exists()){
					file.createNewFile();
				}
		
				FileOutputStream fos = new FileOutputStream(file);
				byte buf[] = new byte[2048];
				int numread = -1;
				while ((numread = is.read(buf)) != -1) {
					fos.write(buf, 0, numread);
				}
				
				fos.close();
				is.close();
				return 1+"";
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
			return 0+"";
		}catch (IOException e){
			e.printStackTrace();
			return 0+"";
		}
		return null;
	}
}
