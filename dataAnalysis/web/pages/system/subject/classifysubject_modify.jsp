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
	 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/jquery.js"></script>	
	
	<script type="text/javascript">
	
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "querycfg/classifySubject.do";		

	function goCancel(){
		parent.closedialog(false);
	}
	
	function goSubmit(){
		if(!checkString(getElement('record(subName)'),"主题名",30,true)) return;
		if(!checkString(getElement('record(subCode)'),"主题代码",30,true)) return;
		document.forms[0].action=fulllink+"?action=UPDATE";
    	document.forms[0].submit();
	}

	
   function closedialog(ret){
	     dhxWins.window(winName).close();
	  }
	  function deleteTable(){
	  	
	  		deleteSelectedItem(window.document.forms[0],"tabId");
	  		getTableId();
	  
	  }
	   function init(){
   		showMessage('<bean:write name="subjectClassifyForm" property="errorMessage" />');
   		}
   		$("input:text").change(function() {
   			parent.modifytag=true;
		});
	 
	</script>
	<style type="text/css">
   html, body {width:100%; height:100%;}
</style>

</head>

<body onload="init()">
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/querycfg/classifySubject.do" >
  	<html:hidden property="record(id)" />
  	<html:hidden property="record(subType)" />
  	<html:hidden property="catagoryId" />
  
    <table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
		   
		   <tr height="*">
		      <td valign="top" align="center">




		<table cellpadding="0" cellspacing="0" width="98%" class="formTable">
			 
			 <tr >
				  <td width="15%" class="textR"><font color="red">*</font>主题名称：</td>
				  <td width="35%">
					   <html:text property="record(subName)" />
				  </td>
				  <td width="15%" class="textR"><font color="red">*</font>主题代码：</td>
				  
					<td width="35%">
					   <html:text property="record(subCode)" />
					</td>
			 </tr>
			
			 <tr>
				  <td class="textR" valign="top">主题说明：</td>
				  <td colspan="3">
				  <html:textarea rows="3" property="record(subDesc)" />
				  
			 </tr>
			<tr>
			   <td class="textR" valign="top">备注：</td>
				 <td colspan="3">
					  <html:textarea rows="5" property="record(remark)" ></html:textarea>
				 </td>
			</tr>
	 </table>
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
</body>

</html>
