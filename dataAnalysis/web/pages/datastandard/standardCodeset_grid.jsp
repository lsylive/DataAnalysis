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
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

	<script language="javascript">
		var contextpath = "<%=CONTEXT_PATH%>";
		var hyperlink = "../datastandard/standardCodeset.do";

		function getModifyId() {
			var id = findSelected("ID","修改");
			if (id == "") return "";
			return id;
		}
		
		function getId() {
			var id = findSelected("ID","浏览代码");
			if (id == "") return "";
			return id;
		}

		function getDeleteId() {
			var ids = findMultiSelected("ID","删除");
			if (ids == "") return "";
			return ids;
		}

		function viewCode(id) {
			var urllink = contextpath+"datastandard/standardCode.do"+"?action=LIST&codesetid="+id;
			parent.openWindow('浏览代码',  urllink, 800, 450);
		}

		function renew(id)  {
			var order = getElement("query.order");                  order.value="";
			var desc =  getElement("query.orderDirection");         desc.value="";
			var pn =    getElement("query.pageNumber");             pn.value="1";
			var ps =    getElement("query.pageSize");               ps.value="10";
			var v1 =    getElement("query.parameters(treeid)");      v1.value=id;
			gosearch();
		}
	</script>
	<style type="text/css">
   html, body {width:100%; height:100%;}
   html { padding:2px; overflow:auto;}
</style>
</head>

<body>
<html:form action="/datastandard/standardCodeset.do" method="post">


<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td width="100%" valign="top">
			<p align="center" valign="middle">
				<font color=red style="font-size:12pt">
					<bean:write name="standardCodesetForm" property="errorMessage" />
				</font>
			</p>

			<html:hidden  property="query.parameters(treeid)" />
			<html:hidden property="query.order" />
			<html:hidden property="query.orderDirection" />
			<html:hidden property="query.pageNumber" />
			<html:hidden property="query.recordCount"/>
			<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="1" cellSpacing="1" width="100%" styleClass="listTable"
		name="standardCodesetForm" property="query.recordSet" parameters="query" rowEventHandle="false"
		fixRows="true">
	<header style="" height="27"  />
	<column width="3%" itemType="checkbox" property="ID" />
	<column width="16%" name="所属分类" property="CATEGORYNAME" align="center" />
	<column width="20%" name="名称" property="NAME" itemStyleClass="HLight"
		itemType="hyperlink" href="#" onClick="viewCode('{ID}')" />
	<column width="15%" name="类型" property="TYPE" />
	<column width="16%" name="编号" property="CODESETNO" />
	<column width="30%" name="备注" property="REMARK" align="center" />
	<rooter height="30" width="50%" showType="all" />
</gw:grid2>		


    </td>
  </tr>
</table>


</html:form>
</body>
</html>