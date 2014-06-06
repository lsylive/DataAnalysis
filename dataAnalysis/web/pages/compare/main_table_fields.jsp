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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

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
		  var ids=$NAME('fieldId');
		  var str="";
		  var str1="";
		  for(var i=0;i<ids.length;i++){
		  	 if(ids[i].checked) str+=ids[i].value+",";
		  	 if(ids[i].checked) str1+=ids[i].title+",";
		  }
		  parent.addTableFieldsToMain(str,str1,'0');
	}
	
		function goSubmit1(){
		  var cellId = document.getElementsByName("cellId")[0].value;
		  var tableId = document.getElementsByName("tableId")[0].value;
		  var ids=$NAME('fieldId');
		  var str="";
		  var str1="";
		  for(var i=0;i<ids.length;i++){
		  	 if(ids[i].checked) 
		  	 {
		  	 	str=ids[i].value;
		  	 	str1=ids[i].title;
		  	 	break;
		  }
		  }
		  parent.addMTableFieldsToMain(tableId,cellId,str,str1,'0');
	}

	
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body>

<div id="formDiv" class="formDiv" style="text-align:left!important;background:#d6e8fe;">
  <html:form method="post" action="/system/sysUser.do">
  <input type="hidden" name="cellId" value="<%=request.getAttribute("cellId")%>" />
  <input type="hidden" name="tableId" value="<%=request.getAttribute("tableId")%>" />
  
  <logic:equal value="1" name="isMajor" scope="request">
  	<html:hidden property="record(ids)" />
	<logic:iterate id="item" indexId="index" name="fields" scope="request">
	   <input type="checkbox"  name="fieldId" value="<bean:write name="item" property="id" />" 
	   	title="<bean:write name="item" property="cnName" />"><img src="<%=CONTEXT_PATH%>images/icon/class_2.gif"> <bean:write name="item" property="cnName" /><br>
	</logic:iterate>
</logic:equal>

  <logic:equal value="0" name="isMajor" scope="request">
	<logic:iterate id="item" indexId="index" name="fields" scope="request">
	   <input type="radio"  name="fieldId" value="<bean:write name="item" property="id" />" 
	   	title="<bean:write name="item" property="cnName" />"><img src="<%=CONTEXT_PATH%>images/icon/class_2.gif"> <bean:write name="item" property="cnName" /><br>
	</logic:iterate>
</logic:equal>
	  		
  </html:form>		
</div>
<div id="btnDiv" class="btnDiv">
<logic:equal value="1" name="isMajor" scope="request">
			      <gw:button name="btnAdd" onClick="goSubmit()">确定</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
</logic:equal>
<logic:equal value="0" name="isMajor" scope="request">
			      <gw:button name="btnAdd" onClick="goSubmit1()">确定</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
</logic:equal>

</div>
<%@include file="/common/resize.jsp" %>
<script type="text/javascript">
</script>  
</body>
</html>
