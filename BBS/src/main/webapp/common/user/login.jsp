<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kmw0628.bbs.users.UsersDAO" %>
<%@ page import="java.io.PrintWriter" %>
<jsp:useBean id = "users" class="com.kmw0628.bbs.users.Users" scope="page"/>
<jsp:setProperty name="users" property="userID"/>
<jsp:setProperty name="users" property="userPassword"/>
<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	if(userID != null){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 로그인 되어 있습니다.')");
		script.println("location.href = 'main.jsp'");
		script.println("</script>");
	}
	UsersDAO usersDAO = new UsersDAO();
	int result = usersDAO.login(users.getUserID(), users.getUserPassword());
	if (result == 1){
		session.setAttribute("userID", users.getUserID());
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'main.jsp'");
		script.println("</script>");
	}
	else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인에 실패 하였 습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
%>