<%@page import="dao.PostDAO"%>
<%@page import="beans.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%
		String str = request.getParameter("id");
		if(str == null || str.equals("")){
			str = "0";
		}
		int id = Integer.parseInt(str);
		Post post;
		if(id == 0){
			post = new Post();
		}else{
			post = new PostDAO().selectById(id);
		}
	%>
		<title>部署データの<%=id == 0 ? "新規作成" : "編集"%></title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<h1>部署データを<%=id == 0 ? "新規作成" : "編集"%>します</h1>
	<%
		if(request.getAttribute("insertSucceed") != null){
			if(request.getAttribute("insertSucceed").equals("Success")){
	%>
				<p class = "success">部署データを追加しました。</p>
	<%
			}else{
	%>
				<p class = "error">部署データの追加に失敗しました。</p>
	<%
			}
		}
	%>
		<div class="contents">
			<form method="post" action="CommitPost">
				<p>名前：<input type="text" name="postName" value="<%=post.getName()%>"></p>
			<%
				if(request.getAttribute("errorName") != null){
			%>
					<p class="error"><%=request.getAttribute("errorName") %></p>
			<%
				}
			%>
				<input type="hidden" name="id" value="<%=post.getId() %>">
				<input class="confing" type="submit" value="設定">
			</form>
			<form method="post" action="PostList.jsp">
				<input class="cancel" type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>