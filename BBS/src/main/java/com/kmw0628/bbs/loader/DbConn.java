package com.kmw0628.bbs.loader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConn extends DbInfo{
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public DbInfo DbInfo;
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	public void setPstmt(String SQL) {
		try {
			this.pstmt = getConn().prepareStatement(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public void BbsConn() throws SQLException, ClassNotFoundException {
		try {
			XmlLoader xmlLoader = new XmlLoader();
			xmlLoader.XLoadDB();
			Class.forName(getDriver());
			Connection conn = DriverManager.getConnection(getUrl(), getId(), getPw());
			setConn(conn);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	public ResultSet excute(String SQL) throws SQLException {
		try {
			setPstmt(SQL);
			setRs(getPstmt().executeQuery());
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return getRs();
	}
	public ResultSet excute() throws SQLException {
		try {
			setRs(getPstmt().executeQuery());
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return getRs();
	}	
	public int executeUpdate() throws SQLException {
		try {
			return getPstmt().executeUpdate();
		} catch (SQLException e) { 
			e.printStackTrace();
			return -1;
		}
	}
	public void close() throws SQLException {
		  try {
			  if (rs != null) {
				  rs.close();
				  System.out.println("Close RS");
			  } 
			  if (pstmt != null) {
				  pstmt.close();
				  System.out.println("Close Stmt");
			  } 
			  if (conn != null) { 
				  conn.close();
				  System.out.println("Close Conn");
			  } 
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } 
		}
}
