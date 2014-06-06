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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css"/>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "system/sysUser.do";		

	function goRoles(){
		var roles=$$("roleIds").value;
    var url_link=fulllink+'?action=GETROLES';
    url_link=url_link+'&roleIds='+roles;
    
    openWindow("选择角色",url_link,200,300);	  
	}

	function goCancel(){
		parent.closedialog(false);
	}

	function goSubmit(){
		  //checkString参数：对象名、标题、长度、是否检测空值
		  if(!checkString(getElement('record(userName)'),"姓名",30,true)) return;

		  if(!checkString(getElement('record(userAccount)'),"帐号",30,true)) return;

		  //checkNumeric参数：对象名、标题、长度、是否检测空值,是否检测大于0
		  if(!checkNumeric(getElement('record(orderNo)'),"顺序号",6,true,true)) return;

		  //checkEmpty参数：对象名、标题
		  if(!checkEmpty(getElement('record(deptId)'),"部门")) return;

		  if(!checkEmpty(getElement('record(adminLevel)'),"行政等级")) return;

		  if(!checkString(getElement('record(remark)'),"备注",200,false)) return;		
		  
		  document.forms[0].action=fulllink+"?action=UPDATE";
    	document.forms[0].submit();
	}

  function closedialog(ret,str,str1){
     dhxWins.window(winName).close();
     if(ret==true) {
        $$('roleIds').value=str;
        $$('roleNames').value=str1;
     }	
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
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/system/sysUser.do">
  	<html:hidden property="record(orgId)" />
  	<html:hidden styleId="userId" property="record(id)" />

		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 <tr >
				  <td width="15%"><font color="red">*</font>姓名：</td>
				  <td width="35%">
					   <html:text property="record(userName)" />
				  </td>
				  <td width="15%"><font color="red">*</font>顺序号：</td>
					<td width="35%">
					   <html:text property="record(orderNo)" />
					</td>
			 </tr>
			 <tr>
				  <td ><font color="red">*</font>账号：</td>
				  <td >
					   <html:text property="record(userAccount)" />
				  </td>
				  <td ><font color="red">*</font>部门：</td>
				  <td class="sel">
				  	<html:select property="record(deptId)" >
              	<html:optionsCollection property="codeSets(depts)" label="codeName" value="value" />
					   </html:select>
				  </td>
			 </tr>
			 <tr>
				  <td ><font color="red">*</font>行政等级：</td>
				  <td class="sel">
					   <html:select property="record(adminLevel)" >
              	<option value=""></option>
              	<html:optionsCollection property="codeSets(ADMIN_LEVEL)" label="codeName" value="value" />
					   </html:select>
				  </td>
				  <td><font color="red">*</font>用户状态：</td>
					<td class="sel">
					   <html:select property="record(userStatus)">
              	<option value=""></option>
              	<html:optionsCollection property="codeSets(USER_STATUS)" label="codeName" value="value" />
					   </html:select>
					</td>
			</tr>
			 <tr>
				  <td >角色：</td>
				  <td colspan="3" class="sel">
				  	 <table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
				  	 	  <tr>
		   	           <td width="*">
		   	           	   <html:text styleId="roleNames" readonly="true" property="record(roleNames)" />
  	                   <html:hidden styleId="roleIds" property="record(roleIds)" />
		   	           	</td>
		   	           <td width="67px"><gw:button name="btnRoles" onClick="goRoles()">角色</gw:button></td>
			          </tr>
			       </table>
			    </td>      
			</tr>
			<tr>
			   <td vAlign="top">备注：</td>
				 <td colspan="3">
					  <html:textarea rows="10" property="record(remark)" ></html:textarea>
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
