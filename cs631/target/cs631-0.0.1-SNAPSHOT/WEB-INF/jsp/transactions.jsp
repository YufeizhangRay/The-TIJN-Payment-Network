<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="${pageContext.request.contextPath}/getPageSendByUserName">查看汇款记录</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/getPageCancelByUserName">查看取消记录</a>
    <br><br>
	<a href="${pageContext.request.contextPath}/getPageRequestByUserName">查看请求记录</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/getPageRefuseByUserName">查看被拒绝记录</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/getPageRequestToMe">查看我收到的请求</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/getSearchDIY">自定义查询</a>
    <br><br>
    <form action="${pageContext.request.contextPath}/main" method="get">
	<input type="submit" value="返回菜单"><br>
	</form>

</body>
</html>