<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>自定义查询</h4>
	输入样式：2018-11-11
	<br>
	<br> 查询汇款
	<form action="${pageContext.request.contextPath}/getTotalSendByTime"
		method="get">
		请输入开始时间：<br> <input id="begin" type="text" name="begin" />
		<br> 请输入结束时间：<br> 
		<input id="end" type="text" name="end" /><br>
		<br> <input type="submit" value="查询">
	</form>
	<br>
	<br> 查询收款
	<form action="${pageContext.request.contextPath}/getTotalRecevieByTime"
		method="get">
		请输入开始时间：<br> <input id="begin" type="text" name="begin" />
		<br> 请输入结束时间：<br> 
		<input id="end" type="text" name="end" /><br><br> 
		<br> <input type="submit" value="查询">
	</form>
	<br>
	<br>
	<form action="${pageContext.request.contextPath}/transactions"
		method="get">
		<input type="submit" value="返回"><br>
	</form>
</body>
</html>