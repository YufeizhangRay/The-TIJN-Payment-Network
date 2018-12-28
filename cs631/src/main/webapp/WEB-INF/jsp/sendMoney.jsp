<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>汇款</h4>
   <form action="${pageContext.request.contextPath}/sendMoney" method="post">
		    <input type="hidden" name="_method" value="put"> 
		  请输入接收方邮箱或者电话：<br>  
		  <input id="info" type="text" name="info" /><br><br>
		  请输入汇款金额：  <br>
		  <input id="amount" type="text" name="amount" /><br><br>
		  留言：  <br>
		  <input id="memo" type="text" name="memo" /><br><br>
		    <input type="submit" value="确认发送">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/getPageSendByUserName">查看汇款记录</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/getPageCancelByUserName">查看取消记录</a>
    <br><br>
	<form action="${pageContext.request.contextPath}/main" method="get">
	<input type="submit" value="返回菜单"><br>
	</form>

</body>
</html>