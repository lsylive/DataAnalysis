<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fullurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
   <script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 
<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>

<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../system/ResourceColumnView.do";	
      var fulllink = contextpath + "system/ResourceColumnView.do";		

function goModify()  {
	var id = findSelected("ID","修改");
	if(id == "") return;
    var urlink=fulllink+'?action=EDIT&record(id)='+id;
    openWindow("修改字段",urlink,450,350);
}

function goAdd()  {
	var tableid = getElement("query.parameters(tableid)");
	openWindow("添加字段",fulllink+'?action=ADD&tableid='+tableid.value,450,350);
}

function renew()  {
	  var order = getElement("query.order");                  order.value="";
	  var desc =  getElement("query.orderDirection");         desc.value="";
	  var pn =    getElement("query.pageNumber");             pn.value="1";
	  var ps =    getElement("query.pageSize");               ps.value="10";
    gosearch();
}

function goQuery()  {
	  var pn = getElement("query.pageNumber");             
	  pn.value="1";
    	gosearch();
}

function closedialog(ret){
	closeWindow();
	gosearch();
	  }

var windows; 
var winName = "win";
function openWindow(title,url,w,h){ 
	windows = new dhtmlXWindows();
	windows.enableAutoViewport(true);
	windows.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
	windows.setSkin("dhx_skyblue");
	var win = windows.createWindow(winName,0,0,w,h);
	win.allowPark();
	win.setText(title);
	win.setModal(true);
	win.centerOnScreen();
	win.attachURL(url);
}
function closeWindow(){
	
	windows.window(winName).close();
};

   </script>	
   <style type="text/css">
   html, body {width:100%; height:100%;}
</style>

</head>

<body onload="showMessage('<bean:write name="resourceColumnForm" property="errorMessage" />');">
<div class="special-padding"> 
<html:form action="/system/ResourceColumnView.do" method="post">
              <html:hidden  property="query.parameters(tableid)" />            
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
	       name="resourceColumnForm" property="query.recordSet" parameters="query" rowEventHandle="false" rowEventHandle="false" 
	       fixRows="false" >
    <header style="" height="27"  />
    <column width="22%" name="名称" property="CN_NAME" align="center" 
    	          headerOnClick="query('CN_NAME')"/>
    <column width="20%" name="说明" property="REMARK" align="center" 
    	        headerOnClick="query('REMARK')"/>
    <column width="15%" name="数据类型" property="DATA_TYPE" align="center" 
				  headerOnClick="query('DATA_TYPE')"/>
    <column width="20%" name="默认值" property="DEFAULT_VALUE" align="center" 
    	         headerOnClick="query('DEFAULT_VALUE')"  />
    <column width="20%" name="安全等级" property="SECURITY_LEVEL" align="center" 
    	         headerOnClick="query('SECURITY_LEVEL')"  />
    <rooter height="30" width="100%" showType="all"  />      
</gw:grid2>		
</html:form>
</div>
</body>
</html>