<%@ page language="java" contentType="text/html;charset=UTF-8" %>


<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">

	<title>首页</title>
	
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	border:none;
	overflow: hidden;
}
body {
	background-color:#b3d1f2;
	width:100%;
	height:100%;
	left:0px;
	top:0px;
	margin:0px;
	border:0px;
}
.frm {
	width:100%;
	height:100%;
	left:0px;
	top:0px;
	margin: 0px;
	border:none;
}
</style>
</head>
<body scroll="no">
<iframe name="main" src="user/login.do" class="frm" frameborder="0" scrolling="auto"></iframe>
</body>
</html> 