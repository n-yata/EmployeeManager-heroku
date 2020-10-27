<%@page import="dao.EmployeeDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "beans.Employee" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>社員データベース管理ページ</title>
		<link rel="stylesheet" href="css/style.css">
	</head>

	<body>
		<p>社員一覧：</p>
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
		<%
			boolean isSearchMode = false;
			ArrayList<Employee> employees = new ArrayList<>();
			if(request.getAttribute("searchedEmployees") == null){
				employees = new EmployeeDAO().selectAll();
			}else{
				employees = (ArrayList<Employee>) request.getAttribute("searchedEmployees");
				isSearchMode = true;
			}

			if(employees.isEmpty()){
		%>
				<p>登録されている社員がいません。</p>
		<%
			}else{
		%>
				<table class="list">
					<tr>
						<th>社員ID</th>
						<th>名前</th>
						<th></th>
						<th></th>
					</tr>
				<%
					for(Employee e : employees){
				%>
					<tr>
						<td><%=e.getEmpId()%></td>
						<td><%=e.getName()%></td>
						<td>
							<form method="post" action="EmployeeEditor.jsp">
								<input type="hidden" name="id" value="<%=e.getId()%>">
								<input class="edit" type="submit" value="編集">
							</form>
						</td>
						<td>
							<form method="post" action="DeleteEmployee">
								<input type="hidden" name="id" value="<%=e.getId()%>">
								<input class="delete" type="submit" value="削除">
							</form>
						</td>
					</tr>
				<%
					}
				%>
				</table>
				<br>
		<%
			}
		%>
				<div class="buttons">
					<form method="post" action="EmployeeEditor.jsp">
						<input type="hidden" name="id" value="0">
						<input class="button" type="submit" value="新規追加">
					</form>
					<form method="post" action="EmployeeSearch.jsp">
						<input class="button" type="submit" value="検索...">
					</form>
				<%
					if(!isSearchMode){
				%>
						<form method="post" action="ExportEmployee">
							<input class="button" type="submit" value="CSVファイルに出力">
						</form>
				<%
					}
				%>
				<%
					if(isSearchMode){
				%>
						<form method="post" action="EmployeeList.jsp">
							<input class="button" type="submit" value="社員一覧">
						</form>
				<%
					}
				%>
					<form method="post" action="PostList.jsp">
						<input class="button" type="submit" value="部署一覧">
					</form>
				</div>
		</div>
	</body>
</html>