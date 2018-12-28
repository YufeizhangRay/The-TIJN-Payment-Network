<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h4>主菜单</h4>
	<a href="${pageContext.request.contextPath}/account">Account</a><br><br>
	<a href="${pageContext.request.contextPath}/sendMoney">Send money (send money to a registered or new user)</a><br><br>
	<a href="${pageContext.request.contextPath}/requestMoney">Request money (request money from registered or new users)</a><br><br>
	<a href="${pageContext.request.contextPath}/statements">Statements (total amount sent and received per month)</a><br><br>
	<a href="${pageContext.request.contextPath}/transactions">Search Transactions (search for an account, transaction, given two dates return the total amount sent/received per month etc.)</a><br><br>
	<a href="${pageContext.request.contextPath}/logout">Sign Out.</a>
	
</body>
</html>