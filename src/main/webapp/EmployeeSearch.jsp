<%@page import="beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="util.Gender"%>
<%@page import="util.Prefecture"%>
<%@page import="dao.PostDAO"%>
<%@page import="dao.EmployeeDAO"%>
<%@page import="beans.Employee"%>
<%@page import="beans.Post"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>社員データベース検索ページ</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<h1>条件を指定して社員情報を検索します。</h1>
		<div class="contents">
			<%
				if(request.getAttribute("errorAllEmpty") != null){
			%>
					<p class="error"><%=request.getAttribute("errorAllEmpty") %></p>
			<%
				}
			%>
			<form method="post" action="SearchEmployee">
				<p>所属部署：<select name="postId">
					<option value="0" selected>選択してください</option>
				<%
					ArrayList<Post> posts = new PostDAO().selectAll();
					for(Post p : posts){
				%>
						<option value="<%=p.getId()%>"><%=p.getName() %></option>
				<%
					}
				%>
				</select></p>
				<p>社員ID：<input type="text" name="empId" placeholder="例)emp0000"></p>
				<%
					if(request.getAttribute("errorEmpId") != null){
				%>
						<p class="error"><%=request.getAttribute("errorEmpId") %></p>
				<%
					}
				%>
				<p>名前に含む文字：<input type="text" name="name"></p>
				<%
					if(request.getAttribute("errorName") != null){
				%>
						<p class="error"><%=request.getAttribute("errorName") %></p>
				<%
					}
				%>
				<input class="confing" type="submit" value="検索">
			</form>
			<form method="post" action="EmployeeList.jsp">
				<input class="cancel" type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>