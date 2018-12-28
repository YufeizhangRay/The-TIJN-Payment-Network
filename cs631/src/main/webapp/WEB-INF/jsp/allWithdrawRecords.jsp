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
<h4>所有提现信息</h4>
	<table width="30%" border="1" cellpadding="1" cellspacing="0">
		<tr>
			<td>提现额度</td>
			<td>提现账户</td>
			<td>提现银行</td>
			<td>提现时间</td>
		<tr />
		<c:forEach var="pageWithdrawRecord" items="${pageWithdrawRecords}">
			<tr>
				<td>${pageWithdrawRecord.amount}</td>
				<td>${pageWithdrawRecord.account}</td>
				<td>${pageWithdrawRecord.bankName}</td>
				<td>${pageWithdrawRecord.withdrawTime}</td>
			<tr/>
		</c:forEach>
	</table>
	<br>
	<c:forEach var="i" begin="1" end="${pageNums}" step="1">
		<a href="${pageContext.request.contextPath}/getWithdrawRecordByPage/${i}">${i}</a>
	</c:forEach><br><br>
	每页可显示条数：<select >
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
	</select><br>
	总条数：${WithdrawPage.total}
	<br><br>
	<form action="${pageContext.request.contextPath}/account" method="get">
	<input type="submit" value="返回用户"><br>
	</form>
</body>
</html>