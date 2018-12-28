<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

	<h4>显示所有账户信息</h4>
	<table width="30%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>账户编号</td>
			<td>账户所属银行</td>
			<td>银行ID</td>
			<td>操作</td>
		<tr />
		<c:forEach var="account" items="${accounts}">
			<tr>
				<td>${account.bankAccount}</td>
				<td>${account.bank.bankName}</td>
				<td>${account.bank.id}</td>
			<td><form action="${pageContext.request.contextPath}/deleteAccount/${account.id}" method="post">
		    <input type="hidden" name="_method" value="put"> 
		    <input type="submit" value="解除绑定">
			</form></td>
			<tr/>
		</c:forEach>
	</table>
	<br><br>
	<table width="30%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>账户编号</td>
			<td>是否验证</td>
		<tr />
		<c:forEach var="userVer" items="${userVers}">
			<tr>
				<td>${userVer.bankAccountNum}</td>
				<td>${userVer.state==1?"已验证":"未验证"}</td>
		</c:forEach>
	</table>
	<br><br>
	<form action="${pageContext.request.contextPath}/allAccounts" method="post">
		    <input type="hidden" name="_method" value="put"> 
		  请输入账户：  <input id="bankAccount" type="text" name="bankAccount" />
		    <input type="submit" value="添加账户">
    </form>
    <br><br>
	<form action="${pageContext.request.contextPath}/account" method="get">
	<input type="submit" value="返回用户"><br>
	</form>

</body>
</html>