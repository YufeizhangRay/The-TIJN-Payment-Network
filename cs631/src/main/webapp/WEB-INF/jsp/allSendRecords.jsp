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
	
	<h4>所有汇款信息</h4>
	<table width="50%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>汇款来源</td>
			<td>接收方信息</td>
			<td>汇款金额</td>
			<td>汇款时间</td>
			<td>汇款状态</td>
			<td>留言</td>
			<td>取消</td>	
		<tr/>
		<c:forEach var="pageSendRecord" items="${pageSendRecords}">
			<tr>
				<td>${pageSendRecord.resource}</td>
				<td>${pageSendRecord.info}</td>
				<td>${pageSendRecord.amount}</td>
				<td>${pageSendRecord.time}</td>
				<td>${pageSendRecord.state==0?"正在处理":pageSendRecord.state==1?"处理完毕":"已经取消"}</td>
				<td>${pageSendRecord.memo}</td>
				<td><form action="${pageContext.request.contextPath}/cancelSend/${pageSendRecord.id}" method="post">
		    	<input type="hidden" name="_method" value="put"> 
		   		<input type="submit" value="取消汇款">
				</form></td>
			<tr/>
		</c:forEach>
	</table>
	<br>
	<c:forEach var="i" begin="1" end="${pageNums}" step="1">
		<a href="${pageContext.request.contextPath}/getSendRecordByPage/${i}">${i}</a>
	</c:forEach><br><br>
	<br>
	每周取消额度：$299.99
	<br>
	本周已取消：$${cancelNow==null?"0.0":cancelNow}
	<br><br>
	每页可显示条数：<select >
		<option value="10" >10</option>
		<option value="20">20</option>
		<option value="30">30</option>
	</select><br>
	总条数：${sendPage.total==null?0:sendPage.total}
	<br><br>
	<form action="${pageContext.request.contextPath}/sendMoney" method="get">
	<input type="submit" value="返回"><br>
	</form>

</body>
</html>