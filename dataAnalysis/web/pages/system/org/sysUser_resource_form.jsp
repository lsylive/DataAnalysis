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
		parent.close();
	}

	function goSubmit(){		  
		  document.forms[0].action=fulllink+"?action=SAVERESOURCE";
    	  document.forms[0].submit();
    	  parent.close();
	}

  function closedialog(ret,str,str1){
     dhxWins.window(winName).close();
     if(ret==true) {
        $$('roleIds').value=str;
        $$('roleNames').value=str1;
     }	
  }	

	function initColLevel(){
		var strs = document.getElementsByName("record(colLevels)")[0].value;
		if (strs.indexOf(",")!=-1) {
			var str = strs.split(",");
			var levels = document.getElementsByName("columnLevel");
			for ( var i = 0; i < str.length; i++) {
				if (str[i]!="0"&&str[i]!="") {
					levels[i].value = str[i];
				}
			}
		}		
	}
  
  function init(){
	 initColLevel(); 
     showMessage('<bean:write name="sysUserForm" property="errorMessage" />');     
  }
	
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body onload="init()">
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/system/sysUser.do">
  	<html:hidden property="record(tableId)" />
  	<html:hidden property="record(userId)" />
	<html:hidden property="record(colLevels)"/>

		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 <tr >
				  <th width="40%">表名</th>
				  <th width="30%">表安全等级</th>
				  <th width="30%">用户分配等级</th>
			 </tr>
			 <tr >
				  <td >
				  	<bean:write name="table" property="cnName"/>&nbsp;
				  </td>
				  <td >
					<bean:write name="table" property="securityLevel"/>&nbsp;
				  </td>
				  <td class="sel">
					<html:select property="record(tableLevel)">
					<option value="0" >---请选择---</option>
              			<html:optionsCollection property="codeSets(SECURITY_LEVEL)" label="codeName" value="value" />
					</html:select>
				  </td>
			 </tr>
			 <tr >
				  <th width="40%">字段名</th>
				  <th width="30%">字段安全等级</th>
				  <th width="30%">用户分配等级</th>
			 </tr>
			 <logic:iterate id="column" name="columns">
			 <tr >
				  <td >
				  	<bean:write name="column" property="cnName"/>&nbsp;
				  </td>
				  <td >
					<bean:write name="column" property="securityLevel"/>&nbsp;
				  </td>
				  <td class="sel">
				  <input type="hidden" name="columnId" value='<bean:write name="column" property="id"/>'/>
					<html:select property="columnLevel">
					<option value="0" >---请选择---</option>
              			<html:optionsCollection property="codeSets(SECURITY_LEVEL)" label="codeName" value="value" />
					</html:select>
				  </td>
			 </tr>
			 </logic:iterate>
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
