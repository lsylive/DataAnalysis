<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="x-ua-compatible" content="ie=7" />
	<title>广东省信息所(数据应用系统)</title>

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH%>/js/date_validate.js"></script>	
	<script language="javascript" src="<%= CONTEXT_PATH%>/js/ctrl_util.js"></script>

	<script type="text/javascript">
		function goReturn(){
			parent.closedialog(false);
		}
	</script>

	<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body onload="showMessage('<bean:write name="standardDataMetaForm" property="errorMessage" />')">
<div id="formDiv" class="formDiv">
<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
	<tr>
		<td width="15%">行业分类：</td>
		<td width="35%" class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(categoryName)" /></span>&nbsp;
		</td>
		<td width="15%">数据指标：</td>
		<td width="35%" class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(indicatorName)" /></span>&nbsp;
		</td>
	</tr>
	<tr>
		<td>英文名：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(name)" /></span>&nbsp;
		</td>
		<td>英文缩略语：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(abbreviations)" /></span>&nbsp;
		</td>
	</tr>
	<tr>
		<td>数据元代码：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(code)" /></span>&nbsp;
		</td>
		<td>中文名：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(cnName)" /></span>&nbsp;
		</td>
	</tr>
	<tr>
		<td>同义词：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(synonyms)" /></span>&nbsp;
		</td>
		<td>表示词：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(expression)" /></span>&nbsp;
		</td>
	</tr>
	<tr>
		<td>数据类型：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(dataTypeName)" /></span>&nbsp;
		</td>
		<td>数据格式：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(dataFormat)" /></span>&nbsp;
		</td>
	</tr>			
	<tr>
		<td>值域：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(dataRange)" /></span>&nbsp;
		</td>
		<td>计量单位：</td>
		<td class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(measurementUnit)" /></span>&nbsp;
		</td>
	</tr>
	<tr>
		<td>所属代码集：</td>
		<td colspan="3" class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(codesetNo)" /></span>&nbsp;
		</td>
	</tr>
	<tr>
		<td >备注：</td>
		<td colspan="3" class="textL">
			<span><bean:write name="standardDataMetaForm" property="record(remark)" /></span>&nbsp;
		</td>
	</tr>
</table>
</div>
<div id="btnDiv" class="btnDiv">
				<gw:button name="btnReturn" onClick="goReturn()">返回</gw:button>
</div>
<%@include file="/common/resize.jsp" %>
</body>
</html>