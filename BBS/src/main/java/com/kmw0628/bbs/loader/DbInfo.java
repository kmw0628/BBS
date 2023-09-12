package com.kmw0628.bbs.loader;

public class DbInfo {
	private static String url;
	private static String id;
	private static String pw;
	private static String driver;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		DbInfo.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		DbInfo.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		DbInfo.pw = pw;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		DbInfo.driver = driver;
	}
}
 