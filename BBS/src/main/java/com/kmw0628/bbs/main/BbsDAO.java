package com.kmw0628.bbs.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.kmw0628.bbs.loader.DbConn;
import com.kmw0628.bbs.loader.DbInfo;

public class BbsDAO extends DbInfo{
	private	DbConn bbsConn = new DbConn();
	public String getNow() {
	    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	  
	public int getNext() {
	    String SQL = "SELECT bbsID FROM BBS.BBS ORDER BY bbsID DESC";
	    System.out.println(SQL);
	    int i = 1;
	    try {
	    	bbsConn.BbsConn();
	    	bbsConn.excute(SQL);
	    	if (bbsConn.getRs().next()) {
	    		i = (bbsConn.getRs().getInt(1)) + 1;
	    		}
	    	bbsConn.close();
	    	return i;
	    } catch (SQLException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    	return -1;
	    } finally { 
	    }
	  }
	  
	  public int write(String bbsTile, String userID, String bbsContent) {
	    String SQL = "INSERT INTO BBS.BBS(BBSID, BBSTITLE, USERID, BBSDATE, BBSCONTENT, BBSAVAILABLE) VALUES(?, ?, ?, TO_TIMESTAMP(?,'YYYY-MM-DD HH24:MI:SS'), ?, ?)";
	    System.out.println(SQL);
	    int i = 1;
	    try {
	    	int next = getNext();
	    	bbsConn.BbsConn();
	    	bbsConn.setPstmt(SQL);
	    	bbsConn.getPstmt().setInt(1, next);
	    	bbsConn.getPstmt().setString(2, bbsTile);
	    	bbsConn.getPstmt().setString(3, userID);
	    	bbsConn.getPstmt().setString(4, getNow());
	    	bbsConn.getPstmt().setString(5, bbsContent);
	    	bbsConn.getPstmt().setInt(6, 1);
	    	i = bbsConn.executeUpdate();
	    	bbsConn.close();
	    	return i;
	    } catch (SQLException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    	return -1;
	    } finally { 
	    }
	  }

	  public ArrayList<Bbs> getList(int pageNumber) {
		String SQL = "SELECT * FROM BBS.BBS where bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC limit 10";
		System.out.println(SQL);
	    ArrayList<Bbs> list = new ArrayList<>();
	    try {
	    	int next = getNext();
	    	bbsConn.BbsConn();
	    	bbsConn.setPstmt(SQL);
	    	bbsConn.getPstmt().setInt(1, next - (pageNumber - 1) * 10);
	    	bbsConn.excute();
	    	while (bbsConn.getRs().next()) {
	    		Bbs bbs = new Bbs();
	    		bbs.setBbsID(bbsConn.getRs().getInt(1));
	    		bbs.setBbsTitle(bbsConn.getRs().getString(2));
	    		bbs.setUserID(bbsConn.getRs().getString(3));
	    		bbs.setBbsDate(bbsConn.getRs().getString(4));
	    		bbs.setBbsContent(bbsConn.getRs().getString(5));
	    		bbs.setBbsAvailable(bbsConn.getRs().getInt(6));
	    		list.add(bbs);
	    	} 
	    	bbsConn.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally { 
	    }
	    return list;
	  }

	  public boolean nextPage(int pageNumber) {
	    String SQL = "SELECT * FROM BBS.BBS where bbsID < ? AND bbsAvailable = 1";
	    System.out.println(SQL);
	    boolean i = false;
	    try {
	    	int next = getNext();
	    	bbsConn.BbsConn();
	    	bbsConn.setPstmt(SQL);
	    	bbsConn.getPstmt().setInt(1, next - (pageNumber - 1) * 10);
	    	bbsConn.excute();
	      if (bbsConn.getRs().next()) {
	    	  i = true;
	      } 
	      bbsConn.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally { 
	    }
	    return i;
	  }
	  
	  public Bbs getBbs(int bbsID) {
	    String SQL = "SELECT * FROM BBS.BBS WHERE bbsID = ?";
	    System.out.println(SQL);
	    try {
	    	bbsConn.BbsConn();
	    	bbsConn.setPstmt(SQL);
	    	bbsConn.getPstmt().setInt(1, bbsID);
	    	bbsConn.excute();
	    	if (bbsConn.getRs().next()) {
	    		Bbs bbs = new Bbs();
	        bbs.setBbsID(bbsConn.getRs().getInt(1));
	        bbs.setBbsTitle(bbsConn.getRs().getString(2));
	        bbs.setUserID(bbsConn.getRs().getString(3));
	        bbs.setBbsDate(bbsConn.getRs().getString(4));
	        bbs.setBbsContent(bbsConn.getRs().getString(5));
	        bbs.setBbsAvailable(bbsConn.getRs().getInt(6));
	        bbsConn.close();
	        return bbs;
	      } 
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally { 
	    }
	    return null;
	  }
	  
	  public int update(int bbsID, String bbsTitle, String bbsContent) {
	    String SQL = "UPDATE BBS.BBS SET bbsTitle = ?, bbsContent = ? WHERE bbsID = ?";
	    System.out.println(SQL);
	    int i;
	    try {
	    	bbsConn.BbsConn();
	    	bbsConn.setPstmt(SQL);
	    	bbsConn.getPstmt().setString(1, bbsTitle);
	    	bbsConn.getPstmt().setString(2, bbsContent);
	    	bbsConn.getPstmt().setInt(3, bbsID);
	    	i = bbsConn.executeUpdate();
	    	bbsConn.close();
	    	return i ;
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally { 
	    }
	    return -1;
	  }
	  
	  public int delete(int bbsID) {
	    String SQL = "UPDATE BBS.BBS SET bbsAvailable = 0 WHERE bbsID = ?";
	    System.out.println(SQL);
	    int i;
	    try {
	    	bbsConn.setPstmt(SQL);
	    	bbsConn.getPstmt().setInt(1, bbsID);
	    	i = bbsConn.executeUpdate();
	    	bbsConn.close();
	      return i;
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally { 
	    }
	    return -1;
	  }
	}
