<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/gwf.tld" prefix="gwf" %>

<% String CONTEXT_PATH = request.getContextPath(); %>
<html>
<head>
	<title><fmt:message key="app.longName"/></title>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH %>/ext-2.0/resources/css/ext-all-firefox.css"/>
	<!--[if lte ie 8]>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH %>/ext-2.0/resources/css/ext-all-ie.css" />
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH %>/style/column-tree.css" />
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH %>/style/gwf.css" />	
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH %>/style/tooltip.css"/>
</head>
<body style="overflow:hidden">

    <div id="loading-mask" style=""></div>
    <div id="loading">
    <div class="loading-indicator"><img src="<%= CONTEXT_PATH %>/common/images/extanim32.gif" width="32" height="32" style="margin-right:8px;" align="absmiddle"/>正在加载，请稍候...</div>
    </div>	

	<script type="text/javascript" src="<%= CONTEXT_PATH %>/common/js/tooltip.js"></script>		
	<script type="text/javascript" src="<%= CONTEXT_PATH %>/ext-2.0/adapter/ext/ext-base.js"></script>              
	
	<script type="text/javascript" src="<%= CONTEXT_PATH %>/ext-2.0/ext-all.js"></script>	 
	<script type="text/javascript" src="<%= CONTEXT_PATH %>/ext-2.0/RadioPrototype.js"></script>	 
	<script type="text/javascript" src="<%= CONTEXT_PATH %>/ext-2.0/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%= CONTEXT_PATH %>/ext-2.0/VTypes.js"></script>
	<script type="text/javascript" src="<%= CONTEXT_PATH %>/ext-2.0/DateTime.js"></script>
	<script type="text/javascript" src="<%= CONTEXT_PATH %>/ext-2.0/ColumnNodeUI.js"></script>
	
	<script type="text/javascript">
		Ext.BLANK_IMAGE_URL = "<%= CONTEXT_PATH %>/ext-2.0/resources/images/default/s.gif" ;		
		setTimeout(function(){
	        Ext.get('loading').remove();
	        Ext.get('loading-mask').fadeOut({remove:true});
	    }, 500);
	</script>

