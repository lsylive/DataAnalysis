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
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "system/sysUser.do";		

	function goCancel(){
		parent.closedialog(false);
	}
	
	function goSubmit(){
		  
		  var pwd=getElement('record(pwd)');
		  var compwd=getElement('record(confirmPwd)');
		  if(!checkString(pwd,"密码",30,true)) return;
		  if(!checkString(compwd,"确认密码",30,true)) return;
		  if(pwd.value!=compwd.value){
		     alert("密码和确认密码不相同！");
		     pwd.focus();
		     return;
		  }
		  if(pwd.value.length<8){
		     alert("密码长度不能小于8位！");
		     pwd.focus();
		     return;
		  }
		  		
		  document.forms[0].action=fulllink+"?action=SAVEPWD";
    	document.forms[0].submit();
	}
  
  function init(){
     showMessage('<bean:write name="sysUserForm" property="errorMessage" />')
  }
	
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body onload="init()">
<div id="formDiv" class="formDiv" style="text-align:left!important;">	
  <html:form method="post" action="/system/sysUser.do">
  	<html:hidden property="record(id)" />
		<table cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 <tr >
				  <td width="35%"><font color="red">*</font>密码：</td>
				  <td width="65%">
					   <html:password property="record(pwd)" />
				  </td>
			 </tr>
			 <tr>
				  <td ><font color="red">*</font>确认密码：</td>
				  <td >
					   <html:password property="record(confirmPwd)" />
				  </td>
			 </tr>
	 </table>
</html:form>
</div>			
<div id="btnDiv" class="btnDiv">
			      <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
</div>
<%@include file="/common/resize.jsp" %>

</body>
</html>
