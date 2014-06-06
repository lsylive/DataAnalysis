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
      var fulllink = contextpath + "system/sysRole.do";		

	function goCancel(){
		 parent.closedialog(false);
	}
	
	function goSubmit(){
		  document.forms[0].rights.value=tree.getAllChecked();
		  document.forms[0].action=fulllink+"?action=saveRights";
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
  <html:form method="post" action="/system/sysRole.do">
  	<html:hidden property="record(id)" />
  	<html:hidden styleId="rights" property="record(rights)" />
  </html:form>		
<div id="formDiv" class="formDiv" style="border:1px solid #b1b1b1;background:#d6e8fe;text-align:left!important;">
</div>
<div id="btnDiv" class="btnDiv">
			      <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
    </div>
<script type="text/javascript">
	  var mainbody=window.document.body;
    if(window.addEventListener) {
 	     window.addEventListener("resize",goResize,false); 
    }
    else {
 	     window.attachEvent('onresize',goResize);
    }
    
    var fDiv=window.document.getElementById('formDiv');
    var bDiv=window.document.getElementById('btnDiv');
    
    function goResize(){
       fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*3+"px";
       bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*3+"px";
       fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*3+"px";
       bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*3+"px";
    }
    goResize();   
</script> 
<script type="text/javascript">

    var tree = new dhtmlXTreeObject("formDiv","100%","100%",0);
    tree.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/imgs/");
    tree.setSkin('dhx_blue');
    tree.enableCheckBoxes(true, true);
    tree.enableThreeStateCheckboxes(1);

<logic:iterate id="item" indexId="index" name="sysRoleForm" property="query.recordSet" scope="request">
    tree.insertNewChild(<logic:notEqual name="item" property="PARENTID" value="0">'</logic:notEqual><bean:write name="item" property="PARENTID" /><logic:notEqual name="item" property="PARENTID" value="0">'</logic:notEqual>
    ,'<bean:write name="item" property="ID" />','<bean:write name="item" property="NAME" />',0,0,0,0,"","1");
</logic:iterate>
    tree.openAllItems(0);
<logic:iterate id="rit" indexId="index" name="sysRoleForm" property="rights" scope="request">
<logic:notEqual name="rit" property="oper" value="3" >tree.setCheck('<bean:write name="rit" property="resId" />',1);</logic:notEqual>
</logic:iterate>
</script>  
</body>
</html>
