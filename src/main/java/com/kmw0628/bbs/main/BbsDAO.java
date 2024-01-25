package com.kmw0628.bbs.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.kmw0628.bbs.dbManager.dbInfo;
import com.kmw0628.bbs.loader.XmlLoader;

public class BbsDAO {
	private static Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private dbInfo Dinfo = new dbInfo();
	private XmlLoader XLoad = new XmlLoader();
	private String url;
	private String driver;
	private String id;
	private String pw;
	private static LocalDate localDate;
	private static String type;
	public BbsDAO() {
		try {
			XLoad.XLoadDB();
			url = Dinfo.getUrl();
			driver = Dinfo.getDriver();
			id = Dinfo.getId();
			pw = Dinfo.getPw();
			type = Dinfo.getType();
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	  public String getDate() {
		String SQL = "";
		switch(type) {
			case "oracle" : 
				SQL = "SELECT sysdate FROM dual";
				System.out.println("SQL : " + SQL);
				break;
			case "postgres" :
				SQL = "SELECT NOW()";
				System.out.println("SQL : " + SQL);
				break;
			default :
				System.out.println("SQL : " + SQL);
				break;
		}
	    try {
	      PreparedStatement pstmt = this.conn.prepareStatement(SQL);
	      this.rs = pstmt.executeQuery();
	      if (this.rs.next()) {
	        return this.rs.getString(1);
	      } 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return "";
	  }
	  
	  public int getNext() {
	    String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";
	    try {
	      PreparedStatement pstmt = this.conn.prepareStatement(SQL);
	      this.rs = pstmt.executeQuery();
	      if (this.rs.next()) {
	        return this.rs.getInt(1) + 1;
	      } 
	      return 1;
	    } catch (Exception e) {
	      e.printStackTrace();
	      return -1;
	    } 
	  }
	  
	  public int write(String bbsTile, String userID, String bbsContent) {
	    String SQL = "INSERT INTO BBS(BBSID, BBSTITLE, USERID, BBSDATE, BBSCONTENT, BBSAVAILABLE) VALUES(?, ?, ?, TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS'), ?, ?)";
	    try {
	      PreparedStatement pstmt = conn.prepareStatement(SQL);
	      pstmt.setInt(1, getNext());
	      pstmt.setString(2, bbsTile);
	      pstmt.setString(3, userID);
	      pstmt.setString(4, getDate());
	      pstmt.setString(5, bbsContent);
	      pstmt.setInt(6, 1);
	      return pstmt.executeUpdate();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return -1;
	  }

	  public ArrayList<Bbs> getList(int pageNumber) {
	    //String SQL = "SELECT * FROM BBS where bbsID < ? AND bbsAvailable = 1 AND rownum <= 10 ORDER BY bbsID DESC";
		String SQL = "SELECT * FROM BBS where bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC limit 10";
	    ArrayList<Bbs> list = new ArrayList<>();
	    try {
	      PreparedStatement pstmt = conn.prepareStatement(SQL);
	      pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
	      this.rs = pstmt.executeQuery();
	      while (this.rs.next()) {
	        Bbs bbs = new Bbs();
	        bbs.setBbsID(this.rs.getInt(1));
	        bbs.setBbsTitle(this.rs.getString(2));
	        bbs.setUserID(this.rs.getString(3));
	        bbs.setBbsDate(this.rs.getString(4));
	        bbs.setBbsContent(this.rs.getString(5));
	        bbs.setBbsAvailable(this.rs.getInt(6));
	        list.add(bbs);
	      } 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return list;
	  }

	  public boolean nextPage(int pageNumber) {
	    String SQL = "SELECT * FROM BBS where bbsID < ? AND bbsAvailable = 1";
	    ArrayList<Bbs> list = new ArrayList<>();
	    try {
	      PreparedStatement pstmt = this.conn.prepareStatement(SQL);
	      pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
	      this.rs = pstmt.executeQuery();
	      if (this.rs.next()) {
	        return true;
	      } 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return false;
	  }
	  
	  public Bbs getBbs(int bbsID) {
	    String SQL = "SELECT * FROM BBS WHERE bbsID = ?";
	    try {
	      PreparedStatement pstmt = this.conn.prepareStatement(SQL);
	      pstmt.setInt(1, bbsID);
	      this.rs = pstmt.executeQuery();
	      if (this.rs.next()) {
	        Bbs bbs = new Bbs();
	        bbs.setBbsID(this.rs.getInt(1));
	        bbs.setBbsTitle(this.rs.getString(2));
	        bbs.setUserID(this.rs.getString(3));
	        bbs.setBbsDate(this.rs.getString(4));
	        bbs.setBbsContent(this.rs.getString(5));
	        bbs.setBbsAvailable(this.rs.getInt(6));
	        return bbs;
	      } 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return null;
	  }
	  
	  public int update(int bbsID, String bbsTitle, String bbsContent) {
	    String SQL = "UPDATE BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
	    try {
	      PreparedStatement pstmt = this.conn.prepareStatement(SQL);
	      pstmt.setString(1, bbsTitle);
	      pstmt.setString(2, bbsContent);
	      pstmt.setInt(3, bbsID);
	      return pstmt.executeUpdate();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return -1;
	  }
	  
	  public int delete(int bbsID) {
	    String SQL = "UPDATE BBS SET bbsAvailable = 0 WHERE bbsID = ?";
	    try {
	      PreparedStatement pstmt = this.conn.prepareStatement(SQL);
	      pstmt.setInt(1, bbsID);
	      return pstmt.executeUpdate();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    System.out.println("delete()");
	    return -1;
	  }
	  
	public void closeAll() {
		  System.out.print("[BBS.BbsDAO.closeAll]");
		  try {
			  if (this.rs != null) {
				  System.out.print("rs-[close]");
				  this.rs.close();
			  } else {
				  System.out.print("rs-[NULL]");
			  }
			  if (this.pstmt != null) {
				  System.out.print(" ptmt-[close] ");
				  this.pstmt.close();
			  } else {
				  System.out.print(" ptmt-[NULL] ");
			  }
			  if (this.conn != null) {
				  System.out.println("conn-[close]");
			  this.conn.close();
			  } else {
		    	  System.out.println("conn-[NULL]");
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
		  } 
		}
	}
