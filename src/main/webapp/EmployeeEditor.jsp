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
	<%
		String str = request.getParameter("id");
		if(str == null || str.equals("")){
			str = "0";
		}
		int id = Integer.parseInt(str);
		Employee emp;
		if(id == 0){
			emp = new Employee();
		}else{
			emp = new EmployeeDAO().selectById(id);
		}
	%>
		<title>社員データの<%=id == 0 ? "新規作成" : "修正" %></title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<h1>社員データを<%=id == 0 ? "新規作成" : "修正" %>します</h1>
		<div class="contents">
			<form method="post" action="CommitEmployee" enctype="multipart/form-data">
				<p>社員ID：<input type="text" name="empId" value="<%=emp.getEmpId()%>" placeholder="例)emp0000"></p>
			<%
				if(request.getAttribute("errorEmpId") != null){
			%>
					<p class="error"><%=request.getAttribute("errorEmpId") %></p>
			<%
				}
			%>

				<p>名前：<input type="text" name="name" value="<%=emp.getName()%>"></p>
			<%
				if(request.getAttribute("errorName") != null){
			%>
					<p class="error"><%=request.getAttribute("errorName") %></p>
			<%
				}
			%>

				<p>年齢：<input type="text" name="age" value="<%=(emp.getAge() == 0) ? "" : emp.getAge()%>"></p>
			<%
				if(request.getAttribute("errorAge") != null){
			%>
					<p class="error"><%=request.getAttribute("errorAge") %></p>
			<%
				}
			%>

				<p>性別：
			<%
				for(Gender gender : Gender.values()){
			%>
					<label><input type="radio" name="gender" value="<%=gender.getCode()%>"
						<%=(gender.getCode().equals(emp.getGender())) ? "checked" : "" %>>
					<%=gender.getText()%></label>
			<%
				}
			%>
				</p>

			<%
				if(request.getAttribute("errorGender") != null){
			%>
					<p class="error"><%=request.getAttribute("errorGender") %></p>
			<%
				}
			%>

				<p>写真：<input type="file" name="photo" value="ファイルを選択"></p>
				<input type="hidden" name="photoId" value="<%=emp.getPhotoId() %>">
			<%
				if(!(emp.getPhotoId() == 0)){
			%>
					<img src="ReadPhoto?id=<%=emp.getPhotoId() %>">
			<%
				}
			%>
			<%
				if(request.getAttribute("errorPhotoId") != null){
			%>
					<p class="error"><%=request.getAttribute("errorPhotoId") %></p>
			<%
				}
			%>

				<p>郵便番号：<input type="text" name="zip" value="<%=emp.getZip()%>" placeholder="例)000-0000"></p>
			<%
				if(request.getAttribute("errorZip") != null){
			%>
					<p class="error"><%=request.getAttribute("errorZip") %></p>
			<%
				}
			%>

				<p>都道府県：<select name="pref">
					<option value="0" <%=(emp.getPref() == 0) ? "selected" : ""%>>選択してください</option>
				<%
					for(Prefecture pref : Prefecture.values()){
				%>
					<option value="<%=pref.getCode()%>" <%=(pref.getCode() == emp.getPref()) ? "selected" : "" %>>
					<%=pref.getFullText()%></option>
				<%
					}
				%>
				</select></p>
			<%
				if(request.getAttribute("errorPref") != null){
			%>
					<p class="error"><%=request.getAttribute("errorPref") %></p>
			<%
				}
			%>

				<p>住所：<input type="text" name="address" value="<%=emp.getAddress()%>"></p>
			<%
				if(request.getAttribute("errorAddress") != null){
			%>
					<p class="error"><%=request.getAttribute("errorAddress") %></p>
			<%
				}
			%>

				<p>所属部署：<select name="postId">
					<option value="0" <%=(emp.getPostId() == 0) ? "selected" : ""%>>選択してください</option>
				<%
					ArrayList<Post> posts = new PostDAO().selectAll();
					for(Post p : posts){
				%>
					<option value="<%=p.getId()%>" <%=(p.getId() == emp.getPostId()) ? "selected" : "" %>>
					<%=p.getName()%></option>

				<%
					}
				%>
				</select></p>
			<%
				if(request.getAttribute("errorPostId") != null){
			%>
					<p class="error"><%=request.getAttribute("errorPostId") %></p>
			<%
				}
			%>

				<p>入社日：<input type="text" name="entDate" value="<%=(emp.getEntDate() == null) ? "" : emp.getEntDate()%>" placeholder="例)2000-01-01"></p>
			<%
				if(request.getAttribute("errorEntDate") != null){
			%>
					<p class="error"><%=request.getAttribute("errorEntDate") %></p>
			<%
				}
			%>

				<p>退社日：<input type="text" name="retDate" value="<%=(emp.getRetDate() == null) ? "" : emp.getRetDate()%>" placeholder="例)2000-01-01"></p>
			<%
				if(request.getAttribute("errorRetDate") != null){
			%>
					<p class="error"><%=request.getAttribute("errorRetDate") %></p>
			<%
				}
			%>

				<input type="hidden" name="id" value="<%=emp.getId()%>">
				<input class="confing" type="submit" value="設定">
			</form>
			<form method="post" action="EmployeeList.jsp">
				<input class="cancel" type="submit" value="キャンセル">
			</form>
		</div>
	</body>
</html>