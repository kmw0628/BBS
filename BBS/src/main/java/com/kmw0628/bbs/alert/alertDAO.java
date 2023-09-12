package com.kmw0628.bbs.alert;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kmw0628.bbs.loader.DbConn;

public class alertDAO {
	private DbConn DbConn = new DbConn();
	public int getNext() {
		String SQL = "SELECT id FROM BBS.alert_history ORDER BY id DESC";
		    System.out.println(SQL);
		    int i = 1;
		    try {
		    	DbConn DbConn = new DbConn();
		    	DbConn.BbsConn();
		    	DbConn.excute(SQL);
		    	if (DbConn.getRs().next()) {
		    		i = (DbConn.getRs().getInt(1)) + 1;
		    		}
		    	DbConn.close();
		    	return i;
		    } catch (SQLException | ClassNotFoundException e) {
		    	e.printStackTrace();
		    	return -1;
		    } finally { 
		    }
		  }
		  
		  public int write(alertInfo SMSData) {
		    String SQL = "INSERT INTO bbs.alert_history(\r\n"
		    		+ "	server_time, type, server_type, server_name, server_id, alert_type, alert_value, alert_level, resource_name, description, host_ip, host_name, service_name, object_id, group_name, alert_group_name, custom, biz_name, response_code)\r\n"
		    		+ "	VALUES (?::timestamp, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		    System.out.println(SQL);
		    int i = 1;
		    try {
		    	DbConn.BbsConn();
		    	DbConn.setPstmt(SQL); 
		    	DbConn.getPstmt().setString(1, SMSData.getServer_time());
		    	DbConn.getPstmt().setString(2, SMSData.getType());
		    	DbConn.getPstmt().setString(3, SMSData.getServer_type());
		    	DbConn.getPstmt().setString(4, SMSData.getServer_name());
		    	DbConn.getPstmt().setString(5, SMSData.getServer_id());
		    	DbConn.getPstmt().setString(6, SMSData.getAlert_type());
		    	DbConn.getPstmt().setString(7, SMSData.getAlert_value());
		    	DbConn.getPstmt().setString(8, SMSData.getAlert_level());
		    	DbConn.getPstmt().setString(9, SMSData.getResource_name());
		    	DbConn.getPstmt().setString(10, SMSData.getDescription());
		    	DbConn.getPstmt().setString(11, SMSData.getHost_ip());
		    	DbConn.getPstmt().setString(12, SMSData.getHost_name());
		    	DbConn.getPstmt().setString(13, SMSData.getService_name());
		    	DbConn.getPstmt().setString(14, SMSData.getObject_id());
		    	DbConn.getPstmt().setString(15, SMSData.getGroup_name());
		    	DbConn.getPstmt().setString(16, SMSData.getAlert_group_name());
		    	DbConn.getPstmt().setString(17, SMSData.getCustom());
		    	DbConn.getPstmt().setString(18, SMSData.getBiz_name());
		    	DbConn.getPstmt().setString(19, SMSData.getResponse_code());
		    	i = DbConn.executeUpdate();
		    	DbConn.close();
		    	return i;
		    } catch (SQLException | ClassNotFoundException e) {
		    	e.printStackTrace();
		    	return -1;
		    } finally { 
		    }
		  }

		  public ArrayList<alertInfo> getList(int pageNumber) {
			String SQL = "SELECT id, type, server_name, resource_name, alert_value, alert_level, TO_CHAR(time, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(server_time, 'YYYY-MM-DD HH24:MI:SS')  FROM bbs.alert_history where id < ? ORDER BY id DESC limit 10";
			System.out.println(SQL);
		    ArrayList<alertInfo> list = new ArrayList<>();
		    try {
		    	int next = getNext();
		    	DbConn DbConn = new DbConn();
		    	DbConn.BbsConn();
		    	DbConn.setPstmt(SQL);
		    	DbConn.getPstmt().setInt(1, next - (pageNumber - 1) * 10);
		    	DbConn.excute();
		    	while (DbConn.getRs().next()) {
		    		alertInfo SMSData = new alertInfo();
		    		SMSData.setId(DbConn.getRs().getString(1));
		    		SMSData.setType(DbConn.getRs().getString(2));
		    		SMSData.setServer_name(DbConn.getRs().getString(3));
		    		SMSData.setResource_name(DbConn.getRs().getString(4));
		    		SMSData.setAlert_value(DbConn.getRs().getString(5));
		    		SMSData.setAlert_level(DbConn.getRs().getString(6));
		    		SMSData.setTime(DbConn.getRs().getString(7));
		    		SMSData.setServer_time(DbConn.getRs().getString(8));
		    		list.add(SMSData);
		    	} 
		    	DbConn.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally { 
		    }
		    return list;
		  }

		  public boolean nextPage(int pageNumber) {
		    String SQL = "SELECT id FROM BBS.alert_history where id < ?";
		    System.out.println(SQL);
		    boolean i = false;
		    try {
		    	int next = getNext();
		    	DbConn DbConn = new DbConn();
		    	DbConn.BbsConn();
		    	DbConn.setPstmt(SQL);
		    	DbConn.getPstmt().setInt(1, next - (pageNumber - 1) * 10);
		    	DbConn.excute();
		      if (DbConn.getRs().next()) {
		    	  i = true;
		      } 
		      DbConn.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally { 
		    }
		    return i;
		  }
		  
		  public alertInfo getAlert(int Id) {
		    String SQL = "SELECT * FROM BBS.alert_history WHERE Id = ?";
		    System.out.println(SQL);
		    try {
		    	DbConn DbConn = new DbConn();
		    	DbConn.BbsConn();
		    	DbConn.setPstmt(SQL);
		    	DbConn.getPstmt().setInt(1, Id);
		    	DbConn.excute();
		    	if (DbConn.getRs().next()) {
		    		alertInfo SMSInfo = new alertInfo();
		    		SMSInfo.setId(DbConn.getRs().getString(1));
		    		SMSInfo.setTime(DbConn.getRs().getString(2));
		    		SMSInfo.setServer_time(DbConn.getRs().getString(3));
		    		SMSInfo.setType(DbConn.getRs().getString(4));
		    		SMSInfo.setServer_type(DbConn.getRs().getString(5));
		    		SMSInfo.setServer_name(DbConn.getRs().getString(6));
		    		SMSInfo.setServer_id(DbConn.getRs().getString(7));
		    		SMSInfo.setAlert_type(DbConn.getRs().getString(8));
		    		SMSInfo.setAlert_value(DbConn.getRs().getString(9));
		    		SMSInfo.setAlert_level(DbConn.getRs().getString(10));
		    		SMSInfo.setResource_name(DbConn.getRs().getString(11));
		    		SMSInfo.setDescription(DbConn.getRs().getString(12));
		    		SMSInfo.setHost_ip(DbConn.getRs().getString(13));
		    		SMSInfo.setHost_name(DbConn.getRs().getString(14));
		    		SMSInfo.setService_name(DbConn.getRs().getString(15));
		    		SMSInfo.setObject_id(DbConn.getRs().getString(16));
		    		SMSInfo.setGroup_name(DbConn.getRs().getString(17));
		    		SMSInfo.setAlert_group_name(DbConn.getRs().getString(18));
		    		SMSInfo.setCustom(DbConn.getRs().getString(19));
		    		SMSInfo.setBiz_name(DbConn.getRs().getString(20));
		    		SMSInfo.setResponse_code(DbConn.getRs().getString(21));
		        DbConn.close();
		        return SMSInfo;
		      } 
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally { 
		    }
		    return null;
		  }
		  
		  public int update(int Id, int stat) {
		    String SQL = "UPDATE BBS.alert_history SET stat = ? WHERE id = ?";
		    System.out.println(SQL);
		    int i;
		    try {
		    	DbConn DbConn = new DbConn();
		    	DbConn.BbsConn();
		    	DbConn.setPstmt(SQL);
		    	DbConn.getPstmt().setInt(1, stat);
		    	DbConn.getPstmt().setInt(2, Id);
		    	i = DbConn.executeUpdate();
		    	DbConn.close();
		    	return i ;
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally { 
		    }
		    return -1;
		  }
		  
		  public int delete(int id) {
		    String SQL = "UPDATE BBS.alert_history SET Available = 0 WHERE id = ?";
		    System.out.println(SQL);
		    int i;
		    try {
		    	DbConn DbConn = new DbConn();
		    	DbConn.setPstmt(SQL);
		    	DbConn.getPstmt().setInt(1, id);
		    	i = DbConn.executeUpdate();
		    	DbConn.close();
		      return i;
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally { 
		    }
		    return -1;
		  }
		}
