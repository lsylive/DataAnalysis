<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

	<script language="javascript">
		var fulllink = "<%= CONTEXT_PATH%>datastandard/dataMeta.do";
		var hyperlink = "../datastandard/dataMeta.do";

		function getModifyId() {
			var id = findSelected("ID","修改");
			if (id == "") return "";
			return id;
		}

		function getDeleteId() {
			var ids = findMultiSelected("ID","删除");
			if (ids == "") return "";
			return ids;
		}

		function renew(id) {
			var order = getElement("query.order");                  order.value="";
			var desc =  getElement("query.orderDirection");         desc.value="";
			var pn =    getElement("query.pageNumber");             pn.value="1";
			var ps =    getElement("query.pageSize");               ps.value="10";
			var v1 =    getElement("query.parameters(categoryid)"); v1.value=id;
			gosearch();
		}

		function view(id) {
			var url_link = fulllink+'?action=view&id='+id;
			parent.openWindow("查看数据元", url_link, 700, 400);	
		}
	</script>
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body onload="showMessage('<bean:write name="standardDataMetaForm" property="errorMessage" />')">
<div class="special-padding">   
<html:form action="/datastandard/dataMeta.do" method="post">
			<html:hidden  property="query.parameters(categoryid)" />
			<html:hidden property="query.order" />
			<html:hidden property="query.orderDirection" />
			<html:hidden property="query.pageNumber" />
			<html:hidden property="query.recordCount"/>
			<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
		name="standardDataMetaForm" property="query.recordSet" parameters="query" rowEventHandle="false"
		fixRows="true">
	<header style="" height="27"  />
	<column width="3%" itemType="checkbox" property="ID" selectAll="true"/>
	<column width="12%" name="所属分类" property="CATEGORYID" align="center" />
	<column width="13%" name="英文名" property="NAME" align="center"
		itemType="hyperlink" href="#" onClick="view('{ID}')" itemStyleClass="HLight" />
	<column width="20%" name="中文名" property="CNNAME" />
	<column width="10%" name="数据类型" property="DATATYPE" />
	<column width="12%" name="数据元代码" property="CODE" />
	<column width="30%" name="备注" property="REMARK" align="center" />
	<rooter height="30" width="50%" showType="all" />
</gw:grid2>
</html:form>
</div>
</body>
</html>