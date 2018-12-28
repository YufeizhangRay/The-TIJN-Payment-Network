<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>注册新用户</h4>
	<c:if test="${not empty requestScope.note}">
		<span style="color: red; font-weight: bolder;">${note}</span>
	</c:if>
	<form:form action="${pageContext.request.contextPath}/register" method="post" modelAttribute="user">
		<!-- path 相当于普通 form 标签里 name 和 id 的结合体 -->
		用户名：<form:input path="username" /><form:errors path="username"/><br><br>
		密  码：<form:input path="password"/><form:errors path="password"/><br><br>
		姓  名：<form:input path="name" /><br><br>
		S S N：<form:input path="ssn" /><form:errors path="ssn"/><br><br>
		邮  箱：<form:input path="email" /><form:errors path="email"/><br><br>
		手  机：<form:input path="phone" /><form:errors path="phone"/><br><br>
		<form:hidden path="emailState" />
		<form:hidden path="phoneState" />
		<form:hidden path="balance" />
		<form:hidden path="state" />
		<%-- 所属部门：<form:select path="dpt.id" itemValue="id" itemLabel="dptName" items="${dpts}"/>
				<form:errors path="dpt"/><br><br> --%>
		<input type="submit" value="确认添加"><br><br>
	</form:form>
</body>
</html>