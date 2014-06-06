<%@ page language="java" contentType="text/html;charset=UTF-8" import="java.io.*" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>

<% String CONTEXT_PATH = request.getContextPath(); %>
<html>
<head>
<style type="text/css">
<!--
body {
	font: 100% 宋体, 新宋体;
	background: #f2f2f2;
	margin: 0; 
	padding: 0;
	text-align: center; 
	color: #000000;
}
.oneColFixCtr #container {
	width: 400px;  
	height:250px;
	background: #FFFFFF;
	margin: 10px auto; 
	border: 1px solid #000000;
	text-align: left;
}
.oneColFixCtr #mainContent {
	padding: 5px; 
}
#mainContent h1{
	background:url(<%= CONTEXT_PATH%>/common/images/warning.gif) no-repeat left center;
	height:48px;
	text-indent:2em;
	line-height:48px;
	
	}
#mainContent p{
	border:1px dashed #bcccda;
	padding:10px;
	background:#fcfcfc;
	line-height:1.5em;
	
	}
-->
</style>
</head>

<body class="oneColFixCtr">

<div id="container">
  <div id="mainContent">
    <p>
您没有访问本功能的权限。
    </p>
	<!-- end #mainContent --></div>
<!-- end #container --></div>
</body>
</html>