package com.iiitb;

public class ReportingDate {
	private String TIMEKEY="";
	private String Date="";
	private String DAY="";
	private int MONTH=0;
	private int YEAR=0;
	private int WEEKDAY=0;
	public String getTIMEKEY() {
		return TIMEKEY;
	}
	public void setTIMEKEY(String tIMEKEY) {
		TIMEKEY = tIMEKEY;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getDAY() {
		return DAY;
	}
	public void setDAY(String dAY) {
		DAY = dAY;
	}
	public int getMONTH() {
		return MONTH;
	}
	public void setMONTH(int mONTH) {
		MONTH = mONTH;
	}
	public int getYEAR() {
		return YEAR;
	}
	public void setYEAR(int yEAR) {
		YEAR = yEAR;
	}
	public int getWEEKDAY() {
		return WEEKDAY;
	}
	public void setWEEKDAY(int wEEKDAY) {
		WEEKDAY = wEEKDAY;
	}
	
}
