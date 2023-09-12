<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>JSP/JAVA TEST SITE</title>
</head>
<body>
	<% String page_sw = "alert"; %>
	<%@ include file="common/common-nav.jsp" %>
	<%
	switch (page_sw) {
		case "bbs" : %>
			<%@ include file="common/bbs/bbs.jsp" %>
			<% break; 
		case "alert" : %>
			<%@ include file="common/alert/alert.jsp" %>
			<% break; 
		default : %> 
			<%@ include file="common/common-Welcome.jsp" %>
			<%@ include file="common/common-gallery.jsp" %> 
			<% break; 
	}%>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>