<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.Post"%>
<%@page import="dao.PostDAO"%>

<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>部署データベース管理ページ</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<%
		ArrayList<Post> posts = new PostDAO().selectAll();
	%>
	<body>
		<p>部署一覧：</p>
	<%
		if(request.getAttribute("deleteSucceed") != null){
			if(request.getAttribute("deleteSucceed").equals("Success")){
	%>
				<p class="success">削除しました。</p>
	<%
			}else{
	%>
				<p class="error">削除に失敗しました。</p>
	<%
			}
		}
	%>
		<div class="contents">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>部署名</th>
					<th></th>
					<th></th>
				</tr>
				<%
					for (Post p : posts) {
				%>
				<tr>
					<td><%=p.getId()%></td>
					<td><%=p.getName()%></td>
					<td>
						<form method="post" action="PostEditor.jsp">
							<input type="hidden" name="id" value="<%=p.getId()%>">
							<input class="edit" type="submit" value="編集">
						</form>
					</td>
					<td>
						<form method="post" action="DeletePost">
							<input type="hidden" name="id" value="<%=p.getId()%>">
							<input class="delete" type="submit" value="削除">
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</table>
				<br>
			<div class="buttons">
				<form method="post" action="PostEditor.jsp">
					<input type="hidden" name="id" value="0">
					<input class="button" type="submit" value="新規追加">
				</form>
				<form method="post" action="EmployeeList.jsp">
					<input class="button" type="submit" value="社員一覧">
				</form>
			</div>
		</div>
	</body>
</html>