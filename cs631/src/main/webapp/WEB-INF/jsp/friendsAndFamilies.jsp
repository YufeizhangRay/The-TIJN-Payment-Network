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

<h4>显示所有朋友</h4>
	<table width="30%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>用户名</td>
			<td>用户Email</td>
			<td>用户电话</td>
			<td>操作</td>
		<tr />
		<c:forEach var="friend" items="${friends}">
			<tr>
				<td>${friend.username}</td>
				<td>${friend.email}</td>
				<td>${friend.phone}</td>
			<td><form action="${pageContext.request.contextPath}/deleteFriend/${friend.id}" method="post">
		    <input type="hidden" name="_method" value="put"> 
		    <input type="submit" value="删除">
			</form></td>
			<tr/>
		</c:forEach>
	</table>
	<br><br>
	<form action="${pageContext.request.contextPath}/addFriend" method="post">
		    <input type="hidden" name="_method" value="put"> 
		  添加好友：  <input id="username" type="text" name="username" />
		    <input type="submit" value="添加">
    </form>
	<br><br>
	<h4>显示所有家人</h4>
	<table width="30%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>用户名</td>
			<td>用户Email</td>
			<td>用户电话</td>
			<td>操作</td>
		<tr />
		<c:forEach var="family" items="${families}">
			<tr>
				<td>${family.username}</td>
				<td>${family.email}</td>
				<td>${family.phone}</td>
			<td><form action="${pageContext.request.contextPath}/deleteFamily/${family.id}" method="post">
		    <input type="hidden" name="_method" value="put"> 
		    <input type="submit" value="删除">
			</form></td>
			<tr/>
		</c:forEach>
	</table>
	<br><br>
	<form action="${pageContext.request.contextPath}/addFamily" method="post">
		    <input type="hidden" name="_method" value="put"> 
		  添加家人：  <input id="username" type="text" name="username" />
		    <input type="submit" value="添加">
    </form>
	<br><br>
</body>
</html>