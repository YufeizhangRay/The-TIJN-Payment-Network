<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>单独请求</h4>
   <form action="${pageContext.request.contextPath}/requestMoney" method="post">
		    <input type="hidden" name="_method" value="put"> 
		  请输入请求接收方邮箱或者电话：<br>  
		  <input id="info" type="text" name="info" /><br><br>
		  请输入请求金额：  <br>
		  <input id="amount" type="text" name="amount" /><br><br>
		  留言：  <br>
		  <input id="amount" type="text" name="memo" /><br><br>
		    <input type="submit" value="确认发送">
    </form>
    <h4>分单请求</h4>
   <form action="${pageContext.request.contextPath}/requestMoneySplit" method="post">
		    <input type="hidden" name="_method" value="put"> 
		  请输入请求总金额：  <br>
		  <input id="amount" type="text" name="amount" /><br><br>
		  请输入请求接收方邮箱或者电话：<br>  
		  <input id="info1" type="text" name="info1" /><br><br>
		  请输入请求百分比：  <br>
		  <input id="percent" type="text" name="percent" /><br><br>
		  留言：  <br>
		  <input id="memo1" type="text" name="memo1" /><br><br>
		  请输入请求接收方邮箱或者电话：<br>  
		  <input id="info2" type="text" name="info2" /><br><br>
		  留言：  <br>
		  <input id="memo2" type="text" name="memo2" /><br><br>
		    <input type="submit" value="确认发送">
    </form>
    <h4>向家人好友请求</h4>
   <form action="${pageContext.request.contextPath}/requestMoneyFromFrendsAndFamilies" method="post">
		    <input type="hidden" name="_method" value="put"> 
		  请输入请求接收方邮箱或者电话：<br>  
		  <input id="info" type="text" name="info" /><br><br>
		  请输入请求金额：  <br>
		  <input id="amount" type="text" name="amount" /><br><br>
		  留言：  <br>
		  <input id="amount" type="text" name="memo" /><br><br>
		    <input type="submit" value="确认发送">
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/getPageRequestByUserName">查看请求记录</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/getPageRefuseByUserName">查看被拒绝记录</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/getPageRequestToMe">查看我收到的请求</a>
    <br><br>
	<form action="${pageContext.request.contextPath}/main" method="get">
	<input type="submit" value="返回菜单"><br>
	</form>
</body>
</html>