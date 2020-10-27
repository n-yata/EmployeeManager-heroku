<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>
			<%
				if(request.getAttribute("succeed").equals("Success")){
			%>
					成功
			<%
				}else{
			%>
					失敗
			<%
				}
			%>
		</title>
		<link rel="stylesheet" href="css/style.css">
	</head>
	<body>
		<%
			if(request.getAttribute("succeed").equals("Success")){
		%>
				<p class="success">データベースへの登録に成功しました。</p>
		<%
			}else{
		%>
				<p class="error">データベースへの登録に失敗しました。</p>
		<%
			}
		%>
		<form method="post" action=<%=(request.getAttribute("action").equals("emp") ? "EmployeeList.jsp" : "PostList.jsp") %>>
			<input class="button" type="submit" value="トップページへ">
		</form>
	</body>
</html>