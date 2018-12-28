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

	<h4>我收到的请求信息</h4>
	<table width="50%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>接收方信息</td>
			<td>请求金额</td>
			<td>请求时间</td>
			<td>请求状态</td>
			<td>留言</td>
			<td>接受请求</td>
			<td>拒绝请求</td>	
		<tr/>
		<c:forEach var="requestToMeRecord" items="${requestToMeRecords}">
			<tr>
				<td>${requestToMeRecord.username}</td>
				<td>${requestToMeRecord.amount}</td>
				<td>${requestToMeRecord.time}</td>
				<td>${requestToMeRecord.state==0?"正在处理":pageSendRecord.state==1?"处理完毕":"已经拒绝"}</td>
				<td>${requestToMeRecord.memo}</td>
				<td><form action="${pageContext.request.contextPath}/accept/${requestToMeRecord.id}" method="post">
		    	<input type="hidden" name="_method" value="put"> 
		   		<input type="submit" value="接受请求">
				</form></td>
				<td><form action="${pageContext.request.contextPath}/refuse/${requestToMeRecord.id}" method="post">
		    	<input type="hidden" name="_method" value="put"> 
		   		<input type="submit" value="拒绝请求">
				</form></td>
			<tr/>
		</c:forEach>
	</table>
	<br>
	<c:forEach var="i" begin="1" end="${pageNums}" step="1">
		<a href="${pageContext.request.contextPath}/getSendRecordByPage/${i}">${i}</a>
	</c:forEach><br><br>
	每页可显示条数：<select >
		<option value="10" >10</option>
		<option value="20">20</option>
		<option value="30">30</option>
	</select><br>
	总条数：${requestToMePage.total==null?0:requestToMePage.total}
	<br><br>
	<form action="${pageContext.request.contextPath}/requestMoney" method="get">
	<input type="submit" value="返回"><br>
	</form>

</body>
</html>