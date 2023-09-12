<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.kmw0628.bbs.main.BbsDAO" %>
<%@ page import="com.kmw0628.bbs.main.Bbs" %>
<%@ page import="com.kmw0628.bbs.loader.DbConn" %>
<%@ page import="java.util.ArrayList" %>
	<%
	int bbsPageNumber = 1;
	if (request.getParameter("bbsPageNumber") != null){
		bbsPageNumber = Integer.parseInt(request.getParameter("bbsPageNumber"));
	}
	%>
	<div class="container">
		<div class="row">
			<table class="table table-stciped" style="text-align: center; border: ipx solid #dddddd">
				<thead>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
				<thead>
				<%
				BbsDAO bbsDAO = new BbsDAO();
					ArrayList<Bbs> bbsList = bbsDAO.getList(bbsPageNumber);
					for(int i = 0; i < bbsList.size(); i++){
				%>
					<tr>
						<td><%= bbsList.get(i).getBbsID()%></td>
						<td><a href="view.jsp?bbsID=<%= bbsList.get(i).getBbsID() %>"><%= bbsList.get(i).getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></a></td>
						<td><%= bbsList.get(i).getUserID()%></td>
						<td><%= bbsList.get(i).getBbsDate()%></td>
					</tr>
				<%
					} 
				%>
				</thead>
			</table>
			<% 
				if(bbsPageNumber != 1){	
			%>
				<a href="main.jsp?bbsPageNumber=<%=bbsPageNumber - 1 %>" class="btn btn-success btn-arraw-left">이전</a>
			<% 
				}if (bbsDAO.nextPage(bbsPageNumber + 1)){
			%>
				<a href="main.jsp?bbsPageNumber=<%=bbsPageNumber + 1 %>" class="btn btn-success btn-arraw-left">다음</a>
			<%
				}
			%>
			<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		</div>
	</div>