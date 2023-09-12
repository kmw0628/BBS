<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kmw0628.bbs.alert.alertInfo" %>
<%@ page import="com.kmw0628.bbs.alert.alertDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
	String Token = request.getParameter("Token");
	String Key = "Exem12";
	if (Token != null){
		if(Token.equals(Key)){
			alertInfo SMSInfo = new alertInfo();
			SMSInfo.setServer_time(request.getParameter("p1"));
			SMSInfo.setType(request.getParameter("p2"));
			SMSInfo.setServer_type(request.getParameter("p3"));
			SMSInfo.setServer_name(request.getParameter("p4"));
			SMSInfo.setServer_id(request.getParameter("p5"));
			SMSInfo.setAlert_type(request.getParameter("p6"));
			SMSInfo.setAlert_value(request.getParameter("p7"));
			SMSInfo.setAlert_level(request.getParameter("p8"));
			SMSInfo.setResource_name(request.getParameter("p9"));
			SMSInfo.setDescription(request.getParameter("p10"));
			SMSInfo.setHost_ip(request.getParameter("p11"));
			SMSInfo.setHost_name(request.getParameter("p12"));
			SMSInfo.setService_name(request.getParameter("p13"));
			SMSInfo.setObject_id(request.getParameter("p14"));
			SMSInfo.setGroup_name(request.getParameter("p15"));
			SMSInfo.setAlert_group_name(request.getParameter("p16"));
			SMSInfo.setCustom(request.getParameter("p17"));
			SMSInfo.setBiz_name(request.getParameter("p18"));
			SMSInfo.setResponse_code(request.getParameter("p19"));
			new alertDAO().write(SMSInfo);
		}
	}
	%>
</body>
</html>