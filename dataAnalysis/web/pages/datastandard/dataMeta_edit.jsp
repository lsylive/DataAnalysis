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
		function goCancel(){
			parent.closedialog();
		}

		function goSubmit(){
			if(!checkString(getElement('record(cnName)'),"中文名",30,true)) return;

			  if(!checkString(getElement('record(name)'),"英文名",30,true)) return;
			  if(!checkString(getElement('record(abbreviations)'),"英文缩略语",16,true)) return;
			  if(!checkString(getElement('record(code)'),"代码",30,true)) return;
			  if(!checkString(getElement('record(synonyms)'),"同义词",120,false)) return;
			  if(!checkString(getElement('record(expression)'),"表示词",30,false)) return;
			  if(!checkString(getElement('record(codesetNo)'),"代码集编号",60,false)) return;
			  if(!checkString(getElement('record(dataFormat)'),"数据格式",120,false)) return;
			  if(!checkString(getElement('record(dataRange)'),"值域",120,false)) return;
			  if(!checkString(getElement('record(measurementUnit)'),"计量单位",14,false)) return;
			  if(!checkString(getElement('record(indicatorId)'),"数据指标",30,false)) return;
			  if(!checkString(getElement('record(remark)'),"备注",250,false)) return;
			document.forms[0].submit(); 	
		}

		function init(){
			var alt='<bean:write name="standardDataMetaForm" property="errorMessage" />';
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
<div id="formDiv" class="formDiv">
	<html:form method="post" action="/datastandard/dataMeta.do?action=UPDATE" >
	<html:hidden property="record(id)" />
	
<table cellpadding="0" cellspacing="0" width="98%" class="formTable">
	<tr>
		<td width="15%" class="textR"><font color="red">*</font>所属分类：</td>
		<td width="35%">
			<html:text property="categoryName" disabled="true"/>
		</td>
		<td width="15%" class="textR"><font color="red">*</font>中文名：</td>
		<td width="35%" >
			<html:text property="record(cnName)" />
		</td>
	</tr>
	<tr>
		<td class="textR"><font color="red">*</font>英文名：</td>
		<td>
			<html:text property="record(name)" />
		</td>
		<td class="textR"><font color="red">*</font>英文缩略语：</td>
		<td>
			<html:text property="record(abbreviations)" />
		</td>
	</tr>
	<tr>
		<td class="textR"><font color="red">*</font>数据元代码：</td>
		<td>
			<html:text property="record(code)" />
		</td>
		<td class="textR"><font color="red">*</font>数据类型：</td>
		<td class="sel">
			<html:select property="record(dataType)">
				<html:optionsCollection property="codeSets(DATAMETA_TYPE)" label="codeName" value="value" />
			</html:select>
		</td>
	</tr>
	<tr>
		<td class="textR">同义词：</td>
		<td>
			<html:text property="record(synonyms)" />
		</td>
		<td class="textR">表示词：</td>
		<td>
			<html:text property="record(expression)" />
		</td>
	</tr>
	<tr>
		<td  class="textR">代码集编号：</td>
		<td class="sel">
			<html:select property="record(codesetNo)">
				<html:optionsCollection property="codeSets(codesets)" label="codeName" value="value" />
			</html:select>
		</td>
		<td class="textR">数据格式：</td>
		<td>
			<html:text property="record(dataFormat)" />
		</td>
	</tr>			
	<tr>
		<td class="textR">值域：</td>
		<td>
			<html:text property="record(dataRange)" />
		</td>
		<td class="textR">计量单位：</td>
		<td>
			<html:text property="record(measurementUnit)" />
		</td>
	</tr>
	<tr>
		<td class="textR">数据指标：</td>
		<td class="sel">
		<html:select property="record(indicatorId)">
				<html:optionsCollection property="codeSets(indicators)" label="codeName" value="value" />
			</html:select>
			
		</td>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td class="textR">备注：</td>
		<td colspan="3">
			<html:textarea rows="5" property="record(remark)" />
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