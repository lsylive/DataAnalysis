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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>

	<script type="text/javascript">
		var contextpath = "<%=CONTEXT_PATH%>";
		var fulllink = contextpath + "system/sysRole.do";

		function goCancel() {
			parent.closedialog(false);
		}

		function goSubmit() {
		  if(!checkString(getElement('record(roleCode)'),"角色标识",30,true)) return;
		  if(!checkString(getElement('record(roleName)'),"角色名称",30,true)) return;
		  if(!checkString(getElement('record(remark)'),"备注",200,false)) return;			
			
			document.forms[0].action=fulllink+"?action=SAVE";
    	document.forms[0].submit();
		}
		
  function init(){
     showMessage('<bean:write name="sysRoleForm" property="errorMessage" />')
  }
	
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body onload="init()">
	<div id="formDiv" class="formDiv">
  <html:form method="post" action="/system/sysRole.do">


			<table cellpadding="0" cellspacing="0" width="99%" class="formTable">
				<tr>
					<td width="20%" class="textR"><font color="red">*</font>角色标识：</td>
				  	<td width="30%">
						<html:text property="record(roleCode)" />
				  	</td>
					<td width="20%" class="textR"><font color="red">*</font>状态：</td>
					<td width="30%" class="sel">
						<html:select property="record(roleStatus)">
							<html:optionsCollection property="codeSets(ROLE_STATUS)" label="codeName" value="value" />
						</html:select>
					</td>
				</tr>
				<tr>
				  	<td class="textR"><font color="red">*</font>角色名称：</td>
					<td colspan="3">
						<html:text property="record(roleName)" />
					</td>
			 	</tr>
				<tr>
			    	<td class="textR">描述：</td>
				 	<td colspan="3" valign="top">
						<html:textarea property="record(remark)" ></html:textarea>
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
<%@include file="/common/dialog1.jsp" %>
<%@include file="/common/resize.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>