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
	<title></title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "system/sysDept.do";		

	function goCancel(){
		parent.closedialog(false);
	}
	
	function goSubmit(){
		
		  if(!checkString(getElement('record(deptName)'),"部门名称",60,true)) return;
		  if(!checkString(getElement('record(deptCode)'),"部门编号",20,true)) return;
		  if(!checkString(getElement('record(deptAbbr)'),"部门简称",60,false)) return;
		  if(!checkNumeric(getElement('record(orderNo)'),"顺序号",6,true,true)) return;
		  if(!checkString(getElement('record(remark)'),"备注",200,false)) return;
		
		  document.forms[0].action=fulllink+"?action=SAVE";
    	document.forms[0].submit();
	}
  
  function init(){
     showMessage('<bean:write name="sysDeptForm" property="errorMessage" />')
  }
	
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body onload="init()">
<div id="formDiv" class="formDiv">		
  <html:form method="post" action="/system/sysDept.do">
  	<html:hidden property="record(orgId)" />
  	<html:hidden property="record(upDeptId)" />

		<table id="tblForm" cellpadding="0" cellspacing="0" width="98%" class="formTable">
			 <tr >
				  <td width="20%"><font color="red">*</font>部门名称：</td>
				  <td width="30%">
					   <html:text property="record(deptName)" />
				  </td>
				  <td width="20%"><font color="red">*</font>部门编号：</td>
					<td width="30%">
					   <html:text property="record(deptCode)" />
					</td>
			 </tr>
			 <tr>
				  <td >部门简称：</td>
				  <td >
					   <html:text property="record(deptAbbr)" />
				  </td>
				  <td ><font color="red">*</font>顺序号：</td>
				  <td>
					   <html:text property="record(orderNo)" />
				  </td>
			 </tr>
			<tr>
			   <td valign="top">备注：</td>
				 <td colspan="3">
					  <html:textarea rows="5" property="record(remark)" ></html:textarea>
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
