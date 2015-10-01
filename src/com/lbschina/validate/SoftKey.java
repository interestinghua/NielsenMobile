package com.lbschina.validate;

public class SoftKey {

	public static String GetEncrypKey(String strKey, String strID) {
		
		String result = "";		
		// 第一次加密
		result = MD5.GetMD5Code(strKey);
		// 为第二次加密准备数据
		String tmpStr = result.toLowerCase();
		String str2 = tmpStr + strID;
		result = MD5.GetMD5Code(str2);
		return result.toLowerCase();
	}
}
