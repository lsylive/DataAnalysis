<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
  
	
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
  	 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
   <style type="text/css">
   html, body {width:100%; height:100%;}
  .special-padding{padding: 2px;}
  .special-form{margin: 0;}
</style>
  <body>
   
	<script type="text/javascript">
	var dhxLayout = new dhtmlXLayoutObject(document.body, "1C");
	
	var tabbar = dhxLayout.cells("a").attachTabbar(); //attachObject("a_tabbar");
	//var tabbar = new dhtmlXTabBar("a_tabbar", "top");
	//tabbar.enableScroll(false);
	tabbar.enableAutoReSize();
tabbar.setSkin('dhx_skyblue');
tabbar.setHrefMode("iframes-on-demand");
tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
tabbar.addTab("a1", "公共主题配置", "100px");
tabbar.addTab("a2", "分类主题配置", "100px");
tabbar.setContentHref("a1", "<%=CONTEXT_PATH%>querycfg/commSubject.do");
tabbar.setContentHref("a2", "<%=CONTEXT_PATH%>querycfg/classifySubject.do");

tabbar.setTabActive("a1");
	</script>


  </body>
</html>
