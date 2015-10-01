package com.lbschina.srt;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class InsertGps {
	public String guestID;
	public Double x;
	public Double y;
	private static final String NAMESPACE = "http://tempuri.org/";

	// WebServiceµÿ÷∑
	private static String URL = "http://server.lbschina.com.cn/srt/srtwebservice.asmx";
	private static final String METHOD_NAME = "InsertUserPoint";
	private static String SOAP_ACTION = "http://tempuri.org/InsertUserPoint";

	public Boolean InsertGPS(String guestID, Double x, Double y) {
		try {

			SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
			rpc.addProperty("UserName", MapConstants.UserName);
			rpc.addProperty("NumberID", guestID);
			rpc.addProperty("X", x.toString());
			rpc.addProperty("Y", y.toString());
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);

			HttpTransportSE ht = new HttpTransportSE(URL);
			ht.debug = true;
			ht.call(SOAP_ACTION, envelope);
			SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();

			if (soapPrimitive != null) {
				try {
					Boolean.valueOf(soapPrimitive.toString());
				} catch (Exception e) {
				}
				return false;

			}
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
		return null;
	}

}
