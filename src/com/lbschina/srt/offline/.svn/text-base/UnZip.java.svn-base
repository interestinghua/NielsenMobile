package com.lbschina.srt.offline;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import android.os.Environment;
import android.util.Log;

public class UnZip {
	    
		static final int BUFFER = 4096;		

		public static long upZipFile(String filename, String pathname) {
			
			//解压前删除之前解压的文件
			String rootDir = Environment.getExternalStorageDirectory().toString();
			
			try {
				ZipFile zipFile = new ZipFile(filename);
				Enumeration enu = zipFile.entries();
				
				String name=filename.substring(filename.lastIndexOf("/")+1);
				
				if(name.startsWith("store")){
					File storeFile=new File(rootDir+"/mobilemap/db/","salespointinfo");
					if(storeFile.exists()){
						storeFile.delete();
					}					
				}else if(name.startsWith("blockinfo")){
					File blockFile=new File(rootDir+"/mobilemap/db/","blockinfo");
					if(blockFile.exists()){
						blockFile.delete();
					}					
				}
				
				int nameLen=name.length();
				int off=nameLen;
				
				while (enu.hasMoreElements()) {
					
					ZipEntry zipEntry = (ZipEntry) enu.nextElement();
					
					if (zipEntry.isDirectory()) {
						new File(filename.substring(0,filename.lastIndexOf("/")-off)+zipEntry.getName()).mkdirs();
						Log.i("FILE", filename.substring(0,filename.lastIndexOf("/")-off));
						continue;
					}
					
					BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(zipEntry));
					File file = new File(filename.substring(0,filename.lastIndexOf("/")-off)+zipEntry.getName());
					File parent = file.getParentFile();
					
					if (parent != null && !parent.exists()) {
						parent.mkdirs();
					}
					
					FileOutputStream fos = new FileOutputStream(file);
					BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);

					int count=0;
					byte[] array = new byte[BUFFER];
					while ((count = bis.read(array, 0, BUFFER)) != -1) {
						bos.write(array, 0, BUFFER);
					}

					bos.flush();
					bos.close();
					bis.close();
				}
				
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
}
