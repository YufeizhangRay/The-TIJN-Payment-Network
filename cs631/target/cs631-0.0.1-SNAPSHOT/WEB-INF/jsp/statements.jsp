<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>Statements</h4>
	11月汇款总额：$${totalSendNov==null?0.0:totalSendNov}
	<br><br>
	11月收款总额：$${totalRecevieNov==null?0.0:totalRecevieNov}
	<br><br><br><br>
	12月汇款总额：$${totalSendDec==null?0.0:totalSendDec}
	<br><br>
	12月收款总额：$${totalRecevieDec==null?0.0:totalRecevieDec}
	<br><br>
	<form action="${pageContext.request.contextPath}/main" method="get">
	<input type="submit" value="返回主菜单"><br>
	</form>
</body>
</html>