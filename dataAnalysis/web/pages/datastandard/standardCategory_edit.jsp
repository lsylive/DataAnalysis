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
	<title></title>
	<meta http-equiv="x-ua-compatible" content="ie=7" />

	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	

	<script type="text/javascript">
		var contextpath = "<%=CONTEXT_PATH%>";	
		var fulllink = contextpath + "datastandard/standardCategory.do";		

		function goCancel(){
			parent.closedialog(false);
		}

		function goSubmit(){
			  if(!checkString(getElement('record(name)'),"名称",30,true)) return;
			  if(!checkString(getElement('record(code)'),"代码",30,false)) return;
			  if(!checkNumeric(getElement('record(squenceNo)'),"顺序号",6,true,true)) return;
			  if(!checkString(getElement('record(remark)'),"备注",250,false)) return;
			  
			document.forms[0].action=fulllink+"?action=UPDATE";
			document.forms[0].submit();
		}
		function init(){
			var alt='<bean:write name="standardCategoryForm" property="errorMessage" />';
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
  <html:form method="post" action="/datastandard/standardCategory.do">
	<html:hidden property="record(id)" />
		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">

			<tr>
				<td width="20%" class="textR"><font color="red">*</font>名称：</td>
				<td width="80%">
					<html:text property="record(name)" />
				</td>
			</tr>
			<tr>
				<td class="textR">代码：</td>
				<td>
					<html:text property="record(code)" />
				</td>
			</tr>
			<tr>
				<td class="textR"><font color="red">*</font>顺序号：</td>
				<td>
					<html:text property="record(squenceNo)" />
				</td>
			</tr>
			<tr>
				<td class="textR">备注：</td>
				<td>
					<html:textarea rows="8" property="record(remark)" ></html:textarea>
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