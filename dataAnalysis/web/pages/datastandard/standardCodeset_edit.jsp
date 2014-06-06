<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String msg = (String)request.getAttribute("msg");
	if (msg == null) msg = "";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<title>广东省信息所(数据应用系统)</title>
	<script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
    <script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css">
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">
	<script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH%>js/date_validate.js"></script>	
	<script language="javascript" src="<%= CONTEXT_PATH%>js/ctrl_util.js"></script>
	<script type="text/javascript">
		var contextpath = "<%= CONTEXT_PATH%>";	
		var fulllink = contextpath + "datastandard/standardCodeset.do";		

		function goCancel(){
			parent.closedialog();
		}

		function goSubmit(){
			var categoryid = getElement("record(categoryId)").value;
			document.forms[0].submit();
    		parent.setSelectTreeNode(categoryid);
		}
function init(){
		var alt="<bean:write name="standardCodesetForm" property="errorMessage" />";
  		if(""!=alt){
  			alert(alt);
  		}
  }
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>
<body onload="init()">
	<script type="text/javascript">
		var windows; 
		function openMessage(msg){
			windows = new dhtmlXWindows();
			windows.enableAutoViewport(true);
			windows.setImagePath("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
			windows.setSkin("dhx_skyblue");
			var win = windows.createWindow("win",0,0,200,120);
			win.denyPark();
			win.denyMove();
			win.denyResize();
			win.button("minmax1").hide();
			win.button("park").hide();
			win.setText("确定");
			win.setModal(true);
			win.denyMove();
			win.denyResize();
			win.centerOnScreen();
			var div = "<div><p align='center'>" + msg + "</p><p align='center'><input type=button onclick='closeMessage()' value='确定'/></p></div>";
			win.attachHTMLString(div);
		}

		function closeMessage(){
			windows.window("win").close();
		}

		var msg = "<%= msg%>";
		if (msg!="") openMessage(msg);
	</script>
	<div id="formDiv" class="formDiv">
	<html:form method="post" action="/datastandard/standardCodeset.do?action=UPDATE">
	<html:hidden property="record(id)" />
	<html:hidden property="record(categoryId)" />
	
<table cellpadding="0" cellspacing="0" width="98%" class="formTable">
	<tr>
		<td width="15%" class="textR">所属分类：</td>
		<td width="35%">
			<html:text property="categoryName" disabled="true"/>
		</td>
		<td width="15%" class="textR">编号：</td   >
		<td width="35%">
			<html:text property="record(codesetNo)" />
		</td>
	</tr>
	 <tr>
		<td class="textR">名称：</td>
		<td>
			<html:text property="record(name)" />
		</td>
		<td class="textR">表示：</td>
		<td>
			<html:text property="record(codeExpression)"></html:text>
		</td>
	</tr>
	<tr>
		<td class="textR">编码规则：</td>
		<td>
			<html:text property="record(encodeRule)"></html:text>
		</td>
		<td class="textR">类型：</td>
		<td>
			<html:text property="record(type)" />
		</td>
	</tr>
	<tr>
		<td class="textR">遵循标准：</td>
		<td colspan="3">
			<html:text property="record(standardCode)"></html:text>
		</td>
	</tr>
	<tr>
		<td class="textR">备注：</td>
		<td colspan="3">
			<html:textarea rows="8" property="record(remark)" />
		</td>
	</tr>
</table>


	</html:form>
	</div>
 <div id="btnDiv" class="btnDiv">
			      <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
				    &nbsp;
			      <gw:button name="btnReset" onClick="goReset()">重置</gw:button>
</div>	  
<%@include file="/common/resize.jsp" %>
</body>
</html>