<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/login" method="post">
		用户名:<input id="username" type="text" name="username" /> <br><br> 
		密　码:<input id="password" type="password" name="password" /> <br> <br>
		<input type="submit" value="登录"><br><br>
	</form>
	<form action="${pageContext.request.contextPath}/register" method="get">
		<input type="submit" value="注册"><br><br>
	</form>
</body>
</html>