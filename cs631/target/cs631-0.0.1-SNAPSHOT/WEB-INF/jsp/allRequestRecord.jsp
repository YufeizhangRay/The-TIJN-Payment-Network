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

<h4>所有请求信息</h4>
	<table width="50%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>接收方信息</td>
			<td>请求金额</td>
			<td>请求时间</td>
			<td>请求状态</td>
			<td>留言</td>	
			<td>用户是否注册</td>	
			<td>操作</td>
		<tr/>
		<c:forEach var="pageRequestRecord" items="${pageRequestRecords}">
			<tr>
				<td>${pageRequestRecord.info}</td>
				<td>${pageRequestRecord.amount}</td>
				<td>${pageRequestRecord.time}</td>
				<td>${pageRequestRecord.state==0?"正在处理":pageRequestRecord.state==1?"处理完毕":"已经拒绝"}</td>
				<td>${pageRequestRecord.memo}</td>
				<td>${pageRequestRecord.isExit==0?"未注册":"已注册"}</td>
				<td><form action="${pageContext.request.contextPath}/cancelRequset/${pageRequestRecord.id}" method="post">
		    <input type="hidden" name="_method" value="put"> 
		    <input type="submit" value="撤销请求">
			</form></td>
			<tr/>
		</c:forEach>
	</table>
	<br>
	<c:forEach var="i" begin="1" end="${pageNums}" step="1">
		<a href="${pageContext.request.contextPath}/getRequestRecordByPage/${i}">${i}</a>
	</c:forEach><br><br>
	<br>
	每页可显示条数：<select >
		<option value="10" >10</option>
		<option value="20">20</option>
		<option value="30">30</option>
	</select><br>
	总条数：${requestPage.total==null?0:requestPage.total}
	<br><br>
	<form action="${pageContext.request.contextPath}/requestMoney" method="get">
	<input type="submit" value="返回"><br>
	</form>
	
</body>
</html>