package com.lbschina.hht;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.lbschina.hht.entity.NieBlock;
import com.lbschina.srt.MapConstants;
import com.lbschina.srt.R;

public class HHTCallBlockHelp {
	
	SQLiteDatabase mHHTDB=null;
	public static final String TABLE_BLOCK = "BLOCK";
	
	public void init(Context context) {
		try {			
			/**
			 * /storage/sdcard0/mobilemap/db
			 */
			
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				
				String databasePath = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/mobilemap/db";			
				String databaseFilename = databasePath + "/"+ MapConstants.BLOCK_DATABASE_FILENAME;			
				File dir = new File(databasePath);
				
				//若路径不存在，则创建该路径
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				//若文件不存在，则读取该文件
				if (!(new File(databaseFilename)).exists()) {
					InputStream is = context.getResources().openRawResource(R.raw.blockinfo);
					File dbName = new File(databaseFilename);
					FileOutputStream fos = new FileOutputStream(dbName);
					
					byte[] buffer = new byte[1024];
					int count = 0;
					
					while ((count = is.read(buffer)) > 0) {
						fos.write(buffer, 0, count);
					}
					
					fos.flush();
					fos.close();
					is.close();
				}
				
				mHHTDB = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<NieBlock> getNieBlock(String BlockID,String CITYID) {

		ArrayList<NieBlock> NieHisPoint_list = null;
		
		String sql = "select distinct centerX,centerY,BlockCoord,BlockID from " + TABLE_BLOCK + " where BlockID = '"+BlockID.trim()+"' and CityID = '"+CITYID.trim()+"'";
		//String sql = "select distinct centerX,centerY,BlockCoord,BlockID from " + TABLE_BLOCK + " where BlockID = '"+BlockID.trim()+" "+"' and CityID = '"+CITYID.trim()+" "+"'";
		
		Log.i("SQL",sql);
		
		if(mHHTDB!=null){
			NieHisPoint_list = new ArrayList<NieBlock>();
			Cursor cursor = mHHTDB.rawQuery(sql, null);
			
			while (cursor.moveToNext()) {
				
				String mPX = String.valueOf(cursor.getFloat(0));
				String mPY = String.valueOf(cursor.getFloat(1));
				byte[] BlockCoord_byte=cursor.getBlob(2);
				String mBlockCoord="";
				
	            InputStream in = new ByteArrayInputStream(BlockCoord_byte);
	            byte[] buff=new byte[(int) BlockCoord_byte.length];
	            
		        try {
					for(int i=0;(i=in.read(buff))>0;){  
						mBlockCoord=mBlockCoord+new String(buff);  
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		        String mBlockID = cursor.getString(3);

				NieBlock mNieHisPoint = new NieBlock();
				mNieHisPoint.setCenterX(mPX);
				mNieHisPoint.setCenterY(mPY);
				mNieHisPoint.setBlockCoord(mBlockCoord);
				mNieHisPoint.setBlockID(mBlockID);
				
				NieHisPoint_list.add(mNieHisPoint);
			}
			cursor.close();
		}

		return NieHisPoint_list;
	}
	
	public ArrayList<NieBlock> getNieBlockByCityId(String CITYID) {

		ArrayList<NieBlock> NieHisPoint_list = null;
		
		//String sql = "select centerX,centerY,BlockCoord,BlockID from " + TABLE_BLOCK + " where CityID = '"+CITYID.trim()+" "+"'";
		String sql = "select centerX,centerY,BlockCoord,BlockID from " + TABLE_BLOCK + " where CityID = '"+CITYID.trim()+"'";
		
		Log.i("SQL",sql);
		
		if(mHHTDB!=null){
			
			NieHisPoint_list = new ArrayList<NieBlock>();
			
			Cursor cursor = mHHTDB.rawQuery(sql, null);
			
			while (cursor.moveToNext()) {
				
				String mPX = String.valueOf(cursor.getFloat(0));
				String mPY = String.valueOf(cursor.getFloat(1));
				byte[] BlockCoord_byte=cursor.getBlob(2);
				String mBlockCoord="";
				
	            InputStream in = new ByteArrayInputStream(BlockCoord_byte);
	            byte[] buff=new byte[(int) BlockCoord_byte.length];
	            
		        try {
					for(int i=0;(i=in.read(buff))>0;){  
						mBlockCoord=mBlockCoord+new String(buff);  
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		        String mblockId=cursor.getString(3);

				NieBlock mNieHisPoint = new NieBlock();
				mNieHisPoint.setBlockID(mblockId);
				mNieHisPoint.setCenterX(mPX);
				mNieHisPoint.setCenterY(mPY);
				mNieHisPoint.setBlockCoord(mBlockCoord);
				
				NieHisPoint_list.add(mNieHisPoint);
			}
			cursor.close();
		}

		return NieHisPoint_list;
	}
}
