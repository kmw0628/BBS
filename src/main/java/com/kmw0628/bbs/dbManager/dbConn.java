package com.kmw0628.bbs.dbManager;

import java.sql.Connection;
import java.sql.DriverManager;

import com.kmw0628.bbs.loader.XmlLoader;

public class dbConn {
	private static Connection conn;
	private static dbInfo Dinfo = new dbInfo();
	private static XmlLoader XLoad = new XmlLoader();
	private static String url;
	private static String id;
	private static String pw;
	public static Connection getDbConn() {
		try {
			XLoad.XLoadDB();
			url = Dinfo.getUrl();
			id = Dinfo.getId();
			pw = Dinfo.getPw();
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static String getDbDriver() {
		
		return "";
	}
}
