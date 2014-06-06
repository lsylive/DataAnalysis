<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
	<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH%>js/ctrl_util.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH%>js/date_validate.js"></script>

	<script language="javascript">
		var contextpath = "<%=CONTEXT_PATH%>";
		var hyperlink = "../system/sysRole.do";
		var fulllink = contextpath + "system/sysRole.do";

		// 用于浏览该角色下的用户
		function view(id) {
			editMode="VIEW";
			openWindow("查看角色", fulllink + "?action=view&id=" + id, 600, 400);
		}
		//用于浏览配置给该角色的权限
		function viewRight(id) {
			editMode="RIGHT";
			openWindow("查看权限", fulllink + "?action=viewRight&id=" + id, 500, 300);
		}

   function goRights()  {
	    var id = findSelected("ID","分配");
	    if(id == "") return;
	    
	    var loader = dhtmlxAjax.postSync(fulllink+'?action=CheckRole&id='+id, "");
	    rights(loader,id);
   }

   function rights(loader,id)  {
   	  var str="";
   	  if (loader.xmlDoc.responseXML != null) str = loader.xmlDoc.responseText;
   	  if(str==""||str=="ERROR") {
   	  	 alert("系统出错。");
   	     return;
   	  }
   	  else if(str=="Y"){
   	  	 alert("系统角色不能分配权限。");
   	     return;
   	  }
      
      var url_link=fulllink+'?action=rights&id='+id;
      editMode="RIGHTS";
      openWindow("分配角色权限",url_link,300,450);	  
   }

   function verify(loader,id)  {
   	  var str="";
   	  if (loader.xmlDoc.responseXML != null) str = loader.xmlDoc.responseText;
   	  if(str==""||str=="ERROR") {
   	  	 alert("系统出错。");
   	     return;
   	  }
   	  else if(str=="Y"){
   	  	 alert("系统角色不能审核。");
   	     return;
   	  }
      
      var url_link=fulllink+'?action=verify&id='+id;
      editMode="VERIFY";
      openWindow("审核角色",url_link,600,450);	   
   }
   
   function goVerify()  {
	    var id = findSelected("ID","审核");
	    if(id == "") return;
      
	    var loader = dhtmlxAjax.postSync(fulllink+'?action=CheckRole&id='+id, "");
      verify(loader,id);
   }
   
		function goDel() {
			var id = findMultiSelected("ID","删除");
			if(id == "") return;
			var res = confirm("是否真的要删除?");
			if(res == true) {
				document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
				document.forms[0].target = "_self";
				document.forms[0].submit();
			}
		}

		function goModify() {
			var id = findSelected("ID","修改");
			if(id == "") return;
    		var urlink=fulllink+'?action=EDIT&id='+id;
    		editMode="EDIT";
			openWindow("修改角色", urlink, 500, 250);
		}

		function goStart() {
			var id = findMultiSelected("ID","启用");
			if(id == "") return;
	    	document.forms[0].action = hyperlink + "?action=start&ids=" + id;
	    	document.forms[0].target = "_self";
	    	document.forms[0].submit();
		}

		function goStop() {
			var id = findMultiSelected("ID","暂停");
			if(id == "") return;
	    	document.forms[0].action = hyperlink + "?action=stop&ids=" + id;
	    	document.forms[0].target = "_self";
	    	document.forms[0].submit();
		}

		function goAdd() {
			editMode="ADD";
			openWindow("添加角色", fulllink+'?action=ADD', 500, 250);
		}

		function renew() {
			var order = getElement("query.order");                  order.value="";
			var desc =  getElement("query.orderDirection");         desc.value="";
			var pn =    getElement("query.pageNumber");             pn.value="1";
			var ps =    getElement("query.pageSize");               ps.value="10";
			var v0 =    getElement("query.parameters(roleCode)");   v0.value="";
			var v1 =    getElement("query.parameters(roleName)");   v1.value="";
			var v2 =    getElement("query.parameters(roleStatus)"); v2.value="";
    		gosearch();
		}

		function goQuery() {
			var pn = getElement("query.pageNumber");             
			pn.value="1";
			gosearch();
		}

		function closedialog(ret) {
			dhxWins.window(winName).close();
			if(ret=='true') {
				if(editMode=="VIEW"||editMode=="RIGHT") {}
				else gosearch();
			}	
			editMode="";
		}
  
function init(){
   showMessage('<bean:write name="sysRoleForm" property="errorMessage" />')
}
	</script>

<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body onload="init()">
	<div class="special-padding">  
<html:form action="/system/sysRole.do" method="post">

	<table width="100%" cellpadding="0" cellspacing="0" class="formTable">
	<tbody>
		<tr>
			<td width=12% class="textR">角色标识：</td>
			<td width=18%>
				<html:text property="query.parameters(roleCode)" />
			</td>
			<td width=12% class="textR">角色名称：</td>
			<td width=28%>
				<html:text property="query.parameters(roleName)" />
			</td>
			<td width=12% class="textR">是否启用：</td>
			<td width=18% class="sel">
				<html:select property="query.parameters(roleStatus)">
					<option value=""></option>
					<html:optionsCollection property="codeSets(ROLE_STATUS)" label="codeName" value="value" />
				</html:select>
			</td>
		</tr>
		<tr class="btnTr">
			<td class="textC" colspan="6">
		          <gw:button name="btnQuery" onClick="goQuery()">查询</gw:button>
				      &nbsp;
			        <gw:button name="btnRefresh" onClick="renew()">重置</gw:button>
			</td>
		</tr>
	</tbody>
	</table>

	<div class="gap8">&nbsp;</div>  

<table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
	<tr>
		<td>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="addIcon" onClick="goAdd()">增加</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="subIcon" onClick="goModify()">修改</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="delIcon" onClick="goDel()">删除</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="stopIcon" onClick="goStop()">暂停</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="playIcon" onClick="goStart()">启用</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="assignIcon" onClick="goRights()">分配权限</gw:button>
			      <!--<gw:button styleClass="sbuBtnStyle" code="" icon="verifyIcon" onClick="goVerify()">审核</gw:button>-->
		</td>
	</tr>	
</table>
			<html:hidden property="query.order" />
			<html:hidden property="query.orderDirection" />
			<html:hidden property="query.pageNumber" />
			<html:hidden property="query.recordCount"/>
			<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
		name="sysRoleForm" property="query.recordSet" parameters="query" rowEventHandle="false"
		fixRows="true" >
	<header style="" height="27"  />
	<column width="3%" itemType="checkbox" property="ID" align="center" selectAll="true"  headerAlign="center" />
	<column width="15%" name="标识" property="ROLECODE" align="center"
		headerOnClick="query('roleCode')"
		headerOnMouseOver="headerOver(this)"
		headerOnMouseOut="headerOut(this)" />
	<column width="25%" name="名称" property="ROLENAME"  itemStyleClass="HLight"
		itemType="hyperlink" href="#" onClick="view('{ID}')"
		headerOnClick="query('roleName')"
		headerOnMouseOver="headerOver(this)"
		headerOnMouseOut="headerOut(this)"/>
	<column width="46%" name="描述" property="REMARK" />
	<column width="10%" name="是否起用" property="ROLESTATUS" align="center" />
	<rooter height="30" width="100%" showType="all" />
</gw:grid2>

</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
<script type="text/javascript">
</script>
</body>
</html>