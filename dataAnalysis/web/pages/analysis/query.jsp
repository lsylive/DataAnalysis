<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%
   String tempId = (String)request.getParameter("tempId");
   String fileName = (String)request.getAttribute("fileName");

	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>¹㶫ʡхϢ̹£¨˽¾ޓ¦ԃϵͳ£©</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />

<style>
body { margin: 1px; overflow:hidden;text-align:center;background-color:#DCE9EF }
</style>
</head>

<body scroll="no">
  	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="query" width="100%" height="100%"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="<%=CONTEXT_PATH%>pages/analysis/flash/<%=fileName%>.swf" />
			<param name="quality" value="high" />
			<param name="wmode" value="transparent">
			<param name="bgcolor" value="#dce9ef" />
			<param name="FlashVars" value="url=<%=CONTEXT_PATH%>&templateId=<%=tempId%>" />
			<param name="allowScriptAccess" value="sameDomain" />
			<embed src="<%=CONTEXT_PATH%>pages/analysis/flash/<%=fileName%>.swf" quality="high" bgcolor="#dce9ef"
				width="100%" height="100%" name="query" align="middle"
				play="true"
				loop="false"
				quality="high"
				allowScriptAccess="sameDomain"
				type="application/x-shockwave-flash"
				pluginspage="http://www.adobe.com/go/getflashplayer">
			</embed>
	</object>
</body>
</html>
