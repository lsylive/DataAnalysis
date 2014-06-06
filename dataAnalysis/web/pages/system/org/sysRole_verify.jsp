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
		  document.forms[0].action=fulllink+"?action=saveVerify";
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
  </html:form>
  	<div id="formDiv" class="formDiv" style="text-align:left!important;">  		
  	<div id="div_main" style="position:relative;top:0px;left:0px;width:100%;height:100%;background:white"></div>
  	<div id="div_genaral" style="position:relative;top:0;left:0;width:100%;background:white;padding-top:3px;text-align:center">
		<table cellpadding="0" cellspacing="0" width="100%" class="formTable" style="width:99%;">
			 <tr >
				  <td width="20%">角色标识：</td>
				  <td width="30%" class="textL">
					   <bean:write name="sysRoleForm" property="record(roleCode)" />
				  </td>
				  <td width="20%">状态：</td>
					<td width="30%" class="textL">
					   <bean:write name="sysRoleForm" property="record(roleStatus)" />
					</td>
			 </tr>
			 <tr>
				  <td >角色名称：</td>
				  <td  colspan="3" class="textL">
					   <bean:write name="sysRoleForm" property="record(roleName)" />
				  </td>
			 </tr>
			<tr>
			   <td>备注：</td>
				 <td colspan="3" class="textL">
					  <bean:write name="sysRoleForm" property="record(remark)" filter="false" />
				 </td>
			</tr>
	 </table>
  	</div>
  	<div id="div_rights" style="position:relative;top:0;left:0;width:100%;height:99%;padding-top:3px;text-align:left;">
  	</div>
  	</div>
    <div id="btnDiv" class="btnDiv">
			      <gw:button name="btnAdd" onClick="goSubmit()">通过</gw:button>
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
//    var divRights=window.document.getElementById('div_rights');
//    var divR=window.document.getElementById('div_rightsMiddle');
//    var divRoles=window.document.getElementById('div_roles');
//    var divRm=window.document.getElementById('div_rolesMiddle');
       fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*2+"px";
       bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*2+"px";
       fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*2+"px";
       bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*4+"px";

    
    function goResize(){
       fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*2+"px";
       bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*2+"px";
       fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*2+"px";
       bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*4+"px";

//       divR.style.height=divRights.offsetHeight-8+"px";
//       divRm.style.height=divRoles.offsetHeight-8+"px";
    }
</script>
<script type="text/javascript">
    var tabbar = new dhtmlXTabBar("div_main", "top");
    tabbar.setSkin('dhx_skyblue');

    tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
    tabbar.addTab("a1", "基本信息", "100px");
    tabbar.addTab("a2", "权限", "100px");
    tabbar.setContent("a1", "div_genaral");
    tabbar.setContent("a2", "div_rights");
    tabbar.setTabActive("a1");	

    var tree = new dhtmlXTreeObject("div_rights","100%","100%",0);
    tree.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/imgs/");
    tree.setSkin('dhx_blue');
//    tree.enableCheckBoxes(true, true);
//    tree.enableThreeStateCheckboxes(1);

<logic:iterate id="item" indexId="index" name="sysRoleForm" property="query.recordSet" scope="request">
    tree.insertNewChild(<logic:notEqual name="item" property="PARENTID" value="0">'</logic:notEqual><bean:write name="item" property="PARENTID" /><logic:notEqual name="item" property="PARENTID" value="0">'</logic:notEqual>
    ,'<bean:write name="item" property="ID" />','<bean:write name="item" property="NAME" />',0,0,0,0,"","1");
</logic:iterate>
    tree.openAllItems(0);
    var txt;
<logic:iterate id="rit" indexId="index" name="sysRoleForm" property="rights" scope="request">
   <logic:equal name="rit" property="oper" value="3" >
      txt=tree.getItemText('<bean:write name="rit" property="resId" />')+"(被删除)";
      tree.setItemText('<bean:write name="rit" property="resId" />',txt,"");
      tree.setItemColor('<bean:write name="rit" property="resId" />','red','red');
   </logic:equal>
   <logic:equal name="rit" property="oper" value="1" >
      txt=tree.getItemText('<bean:write name="rit" property="resId" />')+"(新增)";
      tree.setItemText('<bean:write name="rit" property="resId" />',txt,"");
      tree.setItemColor('<bean:write name="rit" property="resId" />','blue','blue');
   </logic:equal>
</logic:iterate>
</script>  
</body>
</html>
