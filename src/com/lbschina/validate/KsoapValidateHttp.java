package com.lbschina.validate;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class KsoapValidateHttp {
	
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String METHOD_NAME = "GetLics";
	// WebServiceµÿ÷∑
	private static String URL = "http://server.lbschina.com.cn/MobileMap/Key/4.0/AutoServices/Service.asmx";
	private static String SOAP_ACTION = "http://tempuri.org/GetLics";
	private SoapPrimitive detail;
	
	public String GetValidateStrDEMO(String serialNo, String hardwareNo) throws IOException {
		
		String rsltStr="";
		
		try {
			SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
			rpc.addProperty("Serial", serialNo);
			rpc.addProperty("hardID", hardwareNo);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);

			HttpTransportSE ht = new HttpTransportSE(URL);
			
			ht.debug = true;

			ht.call(SOAP_ACTION, envelope);

			detail = (SoapPrimitive) envelope.getResponse();

			if (detail != null) {
				rsltStr=detail.toString();
				return rsltStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
}
