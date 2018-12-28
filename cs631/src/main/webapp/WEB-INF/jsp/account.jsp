<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>用户信息</h4>
	<table width="20%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>用户名</td>
			<td>${loginUser.username}</td>
		<tr/>
		<tr>
			<td>姓 名</td>
			<td>${loginUser.name}</td>
		<tr/>
		<tr>
			<td>S S N</td>
			<td>${loginUser.ssn}</td>
		<tr/>
		<tr>
			<td>邮 箱</td>
			<td>${loginUser.email}</td>
		<tr/>
		<tr>
			<td>验 证</td>
			<td>${loginUser.emailState==1?"已验证":"未验证"}</td>
		<tr/>
		<tr>
			<td>手 机</td>
			<td>${loginUser.phone}</td>
		<tr/>
		<tr>
			<td>验 证</td>
			<td>${loginUser.phoneState==1?"已验证":"未验证"}</td>
		<tr/>
		<tr>
			<td>主账户</td>
			<td>${loginUser.primaryAccount.bankAccount}</td>
		<tr/>
		<tr>
			<td>主账户银行名称</td>
			<td>${loginUser.primaryAccount.bank.bankName}</td>
		<tr/>
		<tr>
			<td><a href="${pageContext.request.contextPath}/allAccounts" >查看所有账户</a></td>
		<tr/>
	</table>
	<br>
	<form action="${pageContext.request.contextPath}/updateUser" method="get">
		<input type="submit" value="修改用户信息"><br>
	</form>
	<br>
	<table width="20%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>余 额</td>
			<td>$${loginUser.balance}</td>
		<tr/>	
	</table>
	<br>
			<form action="${pageContext.request.contextPath}/topUp" method="post">
		    <input type="hidden" name="_method" value="put"> 
		    <input id="balance" type="text" name="balance" />
		    <input type="submit" value="氪金">
			</form>
			<br>
			<form action="${pageContext.request.contextPath}/withdraw" method="post">
		    <input type="hidden" name="_method" value="put"> 
		    <input id="balance" type="text" name="balance" />
		    <input type="submit" value="提现">
			</form>
	<br>
	单笔提现额度：${loginUser.phoneState==1||loginUser.emailState==1?"$9,999.99":"$499.99"}
	<br>
	每周提现额度：${loginUser.phoneState==1||loginUser.emailState==1?"$19,999.99":"$999.99"}
	<br>
	本周已提现：$${totalWithdraw==null?"0.0":totalWithdraw}
	<br><br>
	<a href="${pageContext.request.contextPath}/getPageRecordByUserName">查看氪金记录</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/getPageWithdrawByUserName">查看提现记录</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/friendsAndFamilies">好友与家人</a>
	<br><br>
	<form action="${pageContext.request.contextPath}/main" method="get">
		<input type="submit" value="返回"><br>
	</form>
	<br>
	<br>
</body>
</html>