package com.lbschina.srt;

import java.util.ArrayList;
import java.util.List;

import com.lbschina.hht.entity.NieBlock;
import com.lbschina.hht.entity.NieHisPoint;

import android.app.Application;

public class App extends Application {
	
	//GPS获取坐标
	private String Lon;
	private String Lat;
	
	//屏幕中心点的经纬度坐标
	private String centerGPSX;
	private String centerGPSY;
	
	//GPS定位点与屏幕中心点距离
	private String distance;
	
	//存储HHT传送过来的参数
	private String PID;
	private String mX;
	private String mY;
	private String mGPSX;
	private String mGPSY;
	private String mReason;	
	private String mDistance;
	
	private String mCityID;
	private String mBlockID;
	private String mStoreName;
	private String mStoreAddr;
	private String mStoreFlag;
	
	private String mCITYID;
	private String mSTOREID;
	
	//QC相关变量
	private String mQCID;
	private String mQCX;
	private String mQCY;
	private String mQCSTR;

	public String getmQCSTR() {
		return mQCSTR;
	}

	public void setmQCSTR(String mQCSTR) {
		this.mQCSTR = mQCSTR;
	}

	public String getmQCID() {
		return mQCID;
	}

	public void setmQCID(String mQCID) {
		this.mQCID = mQCID;
	}

	public String getmQCX() {
		return mQCX;
	}

	public void setmQCX(String mQCX) {
		this.mQCX = mQCX;
	}

	public String getmQCY() {
		return mQCY;
	}

	public void setmQCY(String mQCY) {
		this.mQCY = mQCY;
	}

	public String getmCITYID() {
		return mCITYID;
	}

	public void setmCITYID(String mCITYID) {
		this.mCITYID = mCITYID;
	}

	public String getmSTOREID() {
		return mSTOREID;
	}

	public void setmSTOREID(String mSTOREID) {
		this.mSTOREID = mSTOREID;
	}

	private float mBlockCenterX;
	private float mBlockCenterY;
	
	private List<NieBlock> NieBlock_list=new ArrayList<NieBlock>();
	private List<NieHisPoint> NieHisPoint_list=new ArrayList<NieHisPoint>();
	private List<NewPoint> NewPoint_list=new ArrayList<NewPoint>();

	//存储手机相关信息
	private String hardwareNo;
	
	//存储验证字符串
	private String mValidationRslt;	
	
	//原始GPS信息
	private String mGPSLat;
	private String mGPSLon;
	private String mGPSAccu;
	
	//block查询相关参数
	private String mBlockQryCoord;
	private String mBlockQryFlag;
	private String mSecQryBlock;
	
	//新店铺相关变量
	private String mNewStoreID;
	
	//浏览模式变量
	private String mShowMode;
	
	public String getmShowMode() {
		return mShowMode;
	}

	public void setmShowMode(String mShowMode) {
		this.mShowMode = mShowMode;
	}

	public List<NewPoint> getNewPoint_list() {
		return NewPoint_list;
	}

	public void setNewPoint_list(List<NewPoint> newPoint_list) {
		NewPoint_list = newPoint_list;
	}
	
	public String getmNewStoreID() {
		return mNewStoreID;
	}

	public void setmNewStoreID(String mNewStoreID) {
		this.mNewStoreID = mNewStoreID;
	}

	public String getmSecQryBlock() {
		return mSecQryBlock;
	}

	public void setmSecQryBlock(String mSecQryBlock) {
		this.mSecQryBlock = mSecQryBlock;
	}

	public String getmBlockQryCoord() {
		return mBlockQryCoord;
	}

	public void setmBlockQryCoord(String mBlockQryCoord) {
		this.mBlockQryCoord = mBlockQryCoord;
	}

	public String getmBlockQryFlag() {
		return mBlockQryFlag;
	}

	public void setmBlockQryFlag(String mBlockQryFlag) {
		this.mBlockQryFlag = mBlockQryFlag;
	}

	public String getmGPSLat() {
		return mGPSLat;
	}

	public void setmGPSLat(String mGPSLat) {
		this.mGPSLat = mGPSLat;
	}

	public String getmGPSLon() {
		return mGPSLon;
	}

	public void setmGPSLon(String mGPSLon) {
		this.mGPSLon = mGPSLon;
	}

	public String getmGPSAccu() {
		return mGPSAccu;
	}

	public void setmGPSAccu(String mGPSAccu) {
		this.mGPSAccu = mGPSAccu;
	}

	public String getmValidationRslt() {
		return mValidationRslt;
	}

	public void setmValidationRslt(String mValidationRslt) {
		this.mValidationRslt = mValidationRslt;
	}

	public String getHardwareNo() {
		return hardwareNo;
	}

	public void setHardwareNo(String hardwareNo) {
		this.hardwareNo = hardwareNo;
	}

	public List<NieBlock> getNieBlock_list() {
		return NieBlock_list;
	}

	public void setNieBlock_list(List<NieBlock> nieBlock_list) {
		NieBlock_list = nieBlock_list;
	}

	public List<NieHisPoint> getNieHisPoint_list() {
		return NieHisPoint_list;
	}

	public void setNieHisPoint_list(List<NieHisPoint> nieHisPoint_list) {
		NieHisPoint_list = nieHisPoint_list;
	}

	public float getmBlockCenterX() {
		return mBlockCenterX;
	}

	public void setmBlockCenterX(float mBlockCenterX) {
		this.mBlockCenterX = mBlockCenterX;
	}

	public float getmBlockCenterY() {
		return mBlockCenterY;
	}

	public void setmBlockCenterY(float mBlockCenterY) {
		this.mBlockCenterY = mBlockCenterY;
	}

	public String getmBlockID() {
		return mBlockID;
	}

	public void setmBlockID(String mBlockID) {
		this.mBlockID = mBlockID;
	}

	public String getmStoreName() {
		return mStoreName;
	}

	public void setmStoreName(String mStoreName) {
		this.mStoreName = mStoreName;
	}

	public String getmStoreAddr() {
		return mStoreAddr;
	}

	public void setmStoreAddr(String mStoreAddr) {
		this.mStoreAddr = mStoreAddr;
	}

	public String getmStoreFlag() {
		return mStoreFlag;
	}

	public void setmStoreFlag(String mStoreFlag) {
		this.mStoreFlag = mStoreFlag;
	}

	private NewPoint spointPoint = new NewPoint();
	
	private List<NewPoint> noteList = new ArrayList<NewPoint>();
	
	private String showStr="SHOWALL";
	
	public String getmCityID() {
		return mCityID;
	}

	public void setmCityID(String mCityID) {
		this.mCityID = mCityID;
	}

	public String getmDistance() {
		return mDistance;
	}

	public void setmDistance(String mDistance) {
		this.mDistance = mDistance;
	}
	
	public String getmX() {
		return mX;
	}

	public void setmX(String mX) {
		this.mX = mX;
	}

	public String getmY() {
		return mY;
	}

	public void setmY(String mY) {
		this.mY = mY;
	}

	public String getmGPSX() {
		return mGPSX;
	}

	public void setmGPSX(String mGPSX) {
		this.mGPSX = mGPSX;
	}

	public String getmGPSY() {
		return mGPSY;
	}

	public void setmGPSY(String mGPSY) {
		this.mGPSY = mGPSY;
	}

	public String getmReason() {
		return mReason;
	}

	public void setmReason(String mReason) {
		this.mReason = mReason;
	}

	public String getShowStr() {
		return showStr;
	}

	public void setShowStr(String showStr) {
		this.showStr = showStr;
	}

	public List<NewPoint> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<NewPoint> noteList) {
		this.noteList = noteList;
	}

	public NewPoint getSpointPoint() {
		return spointPoint;
	}

	public void setSpointPoint(NewPoint spointPoint) {
		this.spointPoint = spointPoint;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getCenterGPSX() {
		return centerGPSX;
	}

	public void setCenterGPSX(String centerGPSX) {
		this.centerGPSX = centerGPSX;
	}

	public String getCenterGPSY() {
		return centerGPSY;
	}

	public void setCenterGPSY(String centerGPSY) {
		this.centerGPSY = centerGPSY;
	}

	public String getLon() {
		return Lon;
	}

	public void setLon(String lon) {
		Lon = lon;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}
	
	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public App() {}

	public static void main(String[] args) {}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
}
