<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>

<%@ page import="com.kmw0628.bbs.alert.alertInfo" %>
<%@ page import="com.kmw0628.bbs.alert.alertDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	int Id=0;
	if (request.getParameter("Id") != null){
		Id = Integer.parseInt(request.getParameter("Id"));
	}
	if (Id == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글입니다.')");
		script.println("location.href = 'alertLog.jsp'");
		script.println("</script>");
	}
	alertInfo SMSInfo = new alertDAO().getAlert(Id);
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li><a href="bbs.jsp">게시판</a></li>
				<li class="active"><a href="alertLog.jsp">알람서비스</a></li>
			</ul>				
			<%
				if(userID == null){
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<table class="table table-stciped" style="text-align: center; border: ipx solid #dddddd">
				<thead>
					<tr>
						<th colspan="3" style="background-color: #eeeeee; text-align: center;">게시판 글 보기 양식</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>번호</td>
						<td colspan="2"><%= SMSInfo.getId() %></td>
					</tr>
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2" style="text-align: center;">
						[<%= SMSInfo.getService_name()%>
						<%= SMSInfo.getServer_name()%>]
						<%= SMSInfo.getResource_name()%> : 
						<%= SMSInfo.getAlert_value()%>  
						</td>
					</tr>

					<tr>
						<td>기록시간</td>
						<td colspan="2"><%= SMSInfo.getTime() %></td>

					</tr>
					<tr>
						<td>서버시간</td>
						<td colspan="2"><%= SMSInfo.getServer_time() %></td>
					</tr>
					<tr>
						<td>상세내용</td>
						<td colspan="1" style="min-height: 100px; width: 100px; text-align: left;" >
							기록타입<br>
							서버타입<br>
							서버이름<br>
							서버ID<br>
							알람종류<br>
							알람명<br>
							알람값<br>
							알람레벨<br>
							알람설명<br>
							서버주소<br>
							호스트명<br>
							서비스명<br>
							오브젝트ID<br>
							그룹명<br>
							알람그룹명<br>
							커스텀값<br>
							업무명<br>
							응답코드<br>
						</td>
						<td colspan="1" style="min-height: 200px; text-align: left;" >
							 : <%=SMSInfo.getType()%><br>
							 : <%=SMSInfo.getServer_type()%><br>
							 : <%=SMSInfo.getServer_name()%><br>
							 : <%=SMSInfo.getServer_id()%><br>
							 : <%=SMSInfo.getAlert_type()%><br>
							 : <%=SMSInfo.getResource_name()%><br>
							 : <%=SMSInfo.getAlert_value()%><br>
							 : <%=SMSInfo.getAlert_level()%><br>
							 : <%=SMSInfo.getDescription()%><br>
							 : <%=SMSInfo.getHost_ip()%><br>
							 : <%=SMSInfo.getHost_name()%><br>
							 : <%=SMSInfo.getService_name()%><br>
							 : <%=SMSInfo.getObject_id()%><br>
							 : <%=SMSInfo.getGroup_name()%><br>
							 : <%=SMSInfo.getAlert_group_name()%><br>
							 : <%=SMSInfo.getCustom()%><br>
							 : <%=SMSInfo.getBiz_name()%><br>
							 : <%=SMSInfo.getResponse_code()%><br>
						</td>
					</tr>
				</tbody>
			</table>
			<a href="alertLog.jsp" class="btn btn-primary">목록</a>
			<input type="submit" class="btn btn-primary pull-right" value="글쓰기">
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>