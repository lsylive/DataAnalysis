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
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
   <script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH%>js/ctrl_util.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH%>js/date_validate.js"></script>

	<script language="javascript">  
		var contextpath = "<%= CONTEXT_PATH%>";	
		var hyperlink = "../datastandard/standardCode.do";	
		var fulllink = contextpath + "datastandard/standardCode.do";		

		function goAdd()  {
			var codesetid = getElement("query.parameters(codesetid)");
			openWindow("添加字段",fulllink+'?action=ADD&codesetid='+codesetid.value,400,300);
		}

		function goModify()  {
			var id = findSelected("ID", "修改");
			if(id == "") return;
			var urlink = fulllink+'?action=EDIT&id='+id;
			openWindow("修改代码", urlink, 400, 300);
		}

		function goDel()  {
			var id = findMultiSelected("ID", "删除");
			if(id == "") return;
			var res = confirm("是否真的要删除?");
			if(res == true) {
				document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
				document.forms[0].target = "_self";
				document.forms[0].submit();
			}
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
			dhxWins.window(winName).close();
			gosearch();
		}

   </script>	
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>

</head>

<body>
<html:form action="/datastandard/standardCode.do" method="post">


<table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td width="100%" valign="top">
			<p align="center" valign="middle">
				<font color=red style="font-size:12pt">
					<bean:write name="standardCodeForm" property="errorMessage" />
				</font>
			</p>
<table  cellspacing="1" cellpadding="1" class="controlTable">
	<tr>
       <td>
       		<gw:button styleClass="sbuBtnStyle" icon="addIcon" onClick="goAdd()">增加</gw:button>
			<gw:button styleClass="sbuBtnStyle" icon="subIcon" onClick="goModify()">修改</gw:button>
			<gw:button styleClass="sbuBtnStyle" icon="delIcon" onClick="goDel()">删除</gw:button>
			<gw:button styleClass="sbuBtnStyle" icon="refIcon" onClick="gosearch()">刷新</gw:button>
		</td>
	</tr>
</table>
			<html:hidden property="query.parameters(codesetid)" />
			<html:hidden property="query.order" />
			<html:hidden property="query.orderDirection" />
			<html:hidden property="query.pageNumber" />
			<html:hidden property="query.recordCount"/>
			<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
		name="standardCodeForm" property="query.recordSet" parameters="query" rowEventHandle="false"
		fixRows="true">
	<header style="" height="27"  />
	<column width="3%" itemType="checkbox" property="ID" />
	<column width="25%" name="代码名" property="NAME" align="center" />
	<column width="25%" name="代码值" property="VALUE" align="center" />
	<column width="47%" name="备注" property="REMARK" align="center" />
	<rooter height="30" width="100%" showType="all" />
</gw:grid2>		


		</td>
	</tr>
</table>
</html:form>
	<%@include file="/common/dialog1.jsp" %>
</body>
</html>