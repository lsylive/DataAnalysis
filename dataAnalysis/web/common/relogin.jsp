<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function relogin(){
		alert('操作超时,请重新登录!');
		var parent=window.parent;
		while(parent.parent!=parent){
		if(parent.id=='')
			break;
			parent=parent.parent;
	     }
		parent.location.href="../login.jsp";
	}
	</script>
  </head>
  
  <body onload="relogin()">
    
  </body>
</html>
