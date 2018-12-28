<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>更改用户信息</h4>
	<form:form action="${pageContext.request.contextPath}/updateUser" method="put" modelAttribute="loginUser">
		<!-- path 相当于普通 form 标签里 name 和 id 的结合体 -->
		<form:hidden path="id" />
		密  码：<form:input path="password"/><br><br>
		姓  名：<form:input path="name" /><br><br>
		S S N：<form:input path="ssn" /><br><br>
		邮  箱：<form:input path="email" /><br><br>
		手  机：<form:input path="phone" /><br><br>
		主账户：<form:select path="primaryAccount.id" itemValue="id" itemLabel="bankAccount" items="${userbankAccounts}"/>
		<br><br>
		<input type="submit" value="确认修改"><br><br>
	</form:form>
</body>
</html>