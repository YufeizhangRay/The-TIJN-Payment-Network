<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有用户信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jq/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		//$(".delete")为拿到删除键
		$(".delete").click(function() {
			//var href =this.href 为点击后得到对应的 href
			var href =this.href;// jQuary写法：$(this).attr("href");
			//$("#delform")为拿到下面对应的表单
			//attr("action",href)使表单中的action对应我们得到的href
			//submit()提交
			$("#delform").attr("action",href).submit();
			return false;//让<a>标签失去原来的效果
		});
	});
</script>
</head>
<body>

<form id="delform" action="" method="post">
		<input type="hidden" name="_method" value="delete">
	</form>
	
<h4>显示所有用户信息</h4>
	<table width="80%" border="1" cellpadding="1" cellspacing="0">
		<tr> 
			<td>用户编号</td>
			<td>用户名</td>
			<td>姓 名</td>
			<td>S S N</td>
			<td>邮 箱</td>
			<td>验 证</td>
			<td>手 机</td>
			<td>验 证</td>
			<td>余 额</td>
			<td>状 态</td>
		<tr/>
		<c:forEach var="user" items="${users}">
		<tr> 
			<td>${user.id}</td>
			<td>${user.username}</td>
			<td>${user.name}</td>
			<td>${user.ssn}</td>
			<td>${user.email}</td>
			<td>${user.emailState}</td>
			<td>${user.phone}</td>
			<td>${user.phoneState}</td>
			<td>${user.balance}</td>
			<td>${user.state}</td>
			<td><a href="${pageContext.request.contextPath}/changeUserState/${user.id}">修改</a>  <a class="delete" href="${pageContext.request.contextPath}/deleteUser/${user.id}">删除</a></td>
		<tr/>
		</c:forEach>
	</table>
	<br><br>
	<a href="${pageContext.request.contextPath}/logout">Sign Out.</a>
</body>
</html>