package com.kmw0628.bbs.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kmw0628.bbs.loader.XmlLoader;
import com.kmw0628.bbs.loader.DbConn;

public class UsersDAO {
	private static Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DbConn Dinfo = new DbConn();
	private XmlLoader XLoad = new XmlLoader();
	private String url;
	private String driver;
	private String id;
	private String pw;

	public UsersDAO() {
		try {
			XLoad.XLoadDB();
			url = Dinfo.getUrl();
			driver = Dinfo.getDriver();
			id = Dinfo.getId();
			pw = Dinfo.getPw();
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	  public int login(String userID, String userPassword) {
		    String SQL = "SELECT userPassword FROM BBS.USERS WHERE userID LIKE ?";
		    try {
		      this.pstmt = conn.prepareStatement(SQL);
		      this.pstmt.setString(1, userID);
		      this.rs = this.pstmt.executeQuery();
		      if (this.rs.next()) {
		        if (this.rs.getString(1).equals(userPassword))
		          return 1; 
		        return 0;
		      } 
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		    return -2;
		  }
		  
		  public int join(Users users) {
		    String SQL = "INSERT INTO BBS.USERS (userid, UserPASSWORD, UserNAME, UserGENDER, UserMAIL) VALUES (?, ?, ?, ?, ?)";
		    try {
		      this.pstmt = conn.prepareStatement(SQL);
		      this.pstmt.setString(1, users.getUserID());
		      this.pstmt.setString(2, users.getUserPassword());
		      this.pstmt.setString(3, users.getUserName());
		      this.pstmt.setString(4, users.getUserGender());
		      this.pstmt.setString(5, users.getUserEmail());
		      return this.pstmt.executeUpdate();
		    } catch (Exception e) {
		      e.printStackTrace();
		      return -1;
		    } 
		  }
		  
			public void closeAll() {
				  System.out.print("[bbs.users.UsersDAO.closeAll]");
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
					  if (conn != null) {
						  System.out.println("conn-[close]");
					  conn.close();
					  } else {
				    	  System.out.println("conn-[NULL]");
					  }
				  } catch (Exception e) {
					  e.printStackTrace();
				  } 
				}
		}