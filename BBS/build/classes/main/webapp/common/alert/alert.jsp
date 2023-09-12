<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.kmw0628.bbs.alert.alertDAO" %>
<%@ page import="com.kmw0628.bbs.alert.alertInfo" %>
<%@ page import="com.kmw0628.bbs.loader.DbConn" %>
<%@ page import="java.util.ArrayList" %>
<style type="text"/css">
				a, a:hover{
					collor: #000000;
					text-decoratopn: none;
				}
	</style>
		<div class="container">
		<div class="row">
			<table class="table table-stciped" style="text-align: center; border: ipx solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; width: 50px; text-align: center;">순서</th>
						<th style="background-color: #eeeeee; width: 100px; text-align: center;">알람</th>
						<th style="background-color: #eeeeee; text-align: center;">알람내용</th>
						<th style="background-color: #eeeeee; width: 200px; text-align: center;">기록시간</th>
						<th style="background-color: #eeeeee; width: 200px; text-align: center;">서버시간</th>
					</tr>
				</thead>
				<thead>
				<%
					int alertPageNumber = 1;
					if (request.getParameter("alertPageNumber") != null){
						alertPageNumber = Integer.parseInt(request.getParameter("alertPageNumber"));
					}
					alertDAO alertDAO = new alertDAO();
					ArrayList<alertInfo> alertList = alertDAO.getList(alertPageNumber);
					for(int i = 0; i < alertList.size(); i++){
				%>
					<tr>
						<td><%= alertList.get(i).getId()%></td>
						<td><%= alertList.get(i).getAlert_level()%></td>
						<td style=" text-align: center;"><a href="alertView.jsp?Id=<%= alertList.get(i).getId() %>">
						[<%= alertList.get(i).getService_name()%>
						<%= alertList.get(i).getServer_name()%>]
						<%= alertList.get(i).getResource_name()%> : 
						<%= alertList.get(i).getAlert_value()%>  
						</a></td>
						<td><%= alertList.get(i).getTime()%></td>
						<td><%= alertList.get(i).getServer_time()%></td>
					</tr>
				<%
					} 
				%>
				</thead>
			</table>
			<% 
				if(alertPageNumber != 1){	
			%>
				<a href="main.jsp?alertPageNumber=<%=alertPageNumber - 1 %>" class="btn btn-success btn-arraw-left">이전</a>
			<% 
				}if (alertDAO.nextPage(alertPageNumber + 1)){
			%>
				<a href="main.jsp?alertPageNumber=<%=alertPageNumber + 1 %>" class="btn btn-success btn-arraw-left">다음</a>
			<%
				}
			%>
		</div>
	</div>