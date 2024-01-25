package com.kmw0628.bbs.dbManager;

public class dbInfo {
		public static String url;
		public static String id;
		public static String pw;
		public static String driver;
		public static String type;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			dbInfo.url = url;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			dbInfo.id = id;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			dbInfo.pw = pw;
		}
		public String getDriver() {
			return driver;
		}
		public void setDriver(String driver) {
			dbInfo.driver = driver;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			dbInfo.type = type;
		}
}
