<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
   <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
   <script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	
	 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 <script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
	 <script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

<script language="javascript">  
var contextpath = "<%=CONTEXT_PATH%>";	
var hyperlink = "../system/sysUser.do";	
var fulllink = contextpath + "system/sysUser.do";		
      
function view(id) {
    var url_link=fulllink+'?action=view&id='+id;
    editMode="VIEW";
    openWindow("查看用户",url_link,700,370);	  
}

function goDel()  {
	  var id = findMultiSelected("id","删除");
	  if(id == "") return;
	  var res = confirm("是否真的要删除?");
	  if(res == true) {
      document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
      document.forms[0].target = "_self";
      document.forms[0].submit();
    }
}

function goChangePwd()  {
	  var id = findSelected("ID","重置密码");
	  if(id == "") return;
    var url_link=fulllink+'?action=PWD&id='+id;
    editMode="PWD";
    openWindow("修改密码",url_link,300,150);	  
}

function goRights()  {
	  var id = findSelected("ID","分配");
	  if(id == "") return;
	  var loader = dhtmlxAjax.postSync(fulllink+'?action=CheckUser&id='+id, "");
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
   	  	 alert("系统用户不能分配权限。");
   	     return;
   	  }
      
      var url_link=fulllink+'?action=rights&id='+id;
      editMode="RIGHTS";
      openWindow("分配用户权限",url_link,300,500);	  
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
      openWindow("审核用户",url_link,600,450);	 
   }
   
function goVerify()  {
	  var id = findSelected("ID","审核");
	  if(id == "") return;

	  var loader = dhtmlxAjax.postSync(fulllink+'?action=CheckUser&id='+id, "");
    verify(loader,id);
}

function goModify()  {
	  var id = findSelected("ID","修改");
	  if(id == "") return;
    var url_link=fulllink+'?action=edit&id='+id;
    editMode="EDIT";
    openWindow("修改用户",url_link,700,370);	  
}

function goStart()  {
	  var id = findMultiSelected("id","启用");
	  if(id == "") return;
    document.forms[0].action = hyperlink + "?action=start&ids=" + id;
    document.forms[0].target = "_self";
    document.forms[0].submit();
}

function goStop()  {
	  var id = findMultiSelected("id","暂停");
	  if(id == "") return;
    document.forms[0].action = hyperlink + "?action=stop&ids=" + id;
    document.forms[0].target = "_self";
    document.forms[0].submit();
}

function goAdd()  {
   editMode="ADD";
   var url_link=fulllink+'?action=add';
   openWindow("添加用户",url_link,700,400);	 
}
function goResource(){
	var id = findSelected("ID","分配资源");
	if(id == "") return;
	var url_link=fulllink+'?action=resource&userId='+id;
	openWindow("选择资源",url_link,700,400);
}
function renew()  {
	  var order = getElement("query.order");                   order.value="";
	  var desc =  getElement("query.orderDirection");          desc.value="";
	  var pn =    getElement("query.pageNumber");              pn.value="1";
	  var ps =    getElement("query.pageSize");                ps.value="10";
	  var v0 =    getElement("query.parameters(userAccount)"); v0.value="";
	  var v1 =    getElement("query.parameters(userName)");    v1.value="";
	  var v2 =    getElement("query.parameters(deptId)");      v2.value="";
	  var v3 =    getElement("query.parameters(userStatus)");  v3.value="";
    gosearch();
}

function goQuery()  {
	  var pn = getElement("query.pageNumber");             
	  pn.value="1";
    gosearch();
}
function close(){
	dhxWins.window(winName).close();
}

function closedialog(ret){
   dhxWins.window(winName).close();
   if(ret=='true') {
   	  if(editMode=="PWD") alert("密码已经被修改。");
   	  else if(editMode=="RIGHTS"||editMode=="VERIFY") {}
      else gosearch();
   }	
   editMode="";
}
  
function init(){
   showMessage('<bean:write name="sysUserForm" property="errorMessage" />');
}

</script>

<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body onload="init()">
<div class="special-padding">   
<html:form action="/system/sysUser.do" method="post">
      <table cellpadding="0" cellspacing="0" class="formTable" >
      	    <tbody>
         <tr>
           <td width=10% class="textR">账号：</td>
           <td width=15%>
             	<html:text property="query.parameters(userAccount)" />
           </td>
           <td width=10% class="textR">用户名：</td>
           <td width=15%>
             	<html:text property="query.parameters(userName)" />
           </td>
           <td width=10% class="textR">部门：</td>
           <td width=20% class="sel">
              <html:select styleId="selectOrgId" property="query.parameters(deptId)" >
              	<html:optionsCollection property="codeSets(depts)" label="codeName" value="value" />
              </html:select>
           </td>
           <td width=10% class="textR">状态：</td>
           <td width=10% class="sel">
              <html:select styleId="selectStatus" property="query.parameters(userStatus)" >
              	<option value=""></option>
              	<html:optionsCollection property="codeSets(USER_STATUS)" label="codeName" value="value" />
              </html:select>
           </td>
         </tr>
         <tr class="btnTr">
           <td class="textC" colspan="8">
		          <gw:button name="btnQuery" onClick="goQuery()">查询</gw:button>
				      &nbsp;
			      <gw:button name="btnRefresh" onClick="renew()">重置</gw:button>
           </td>
         </tr>
             </tbody>
      </table>
      
      <div class="gap8">&nbsp;</div>  
          
<table  cellspacing="1" cellpadding="1"  class="controlTable">
    <tr>
        <td>
			      <gw:button styleClass="sbuBtnStyle" code="A9903-01" icon="addIcon" onClick="goAdd()">增加</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="A9903-02" icon="subIcon" onClick="goModify()">修改</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="A9903-03" icon="delIcon" onClick="goDel()">删除</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="A9903-04" icon="resetIcon" onClick="goChangePwd()">重置密码</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="A9903-05" icon="stopIcon" onClick="goStop()">暂停</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="A9903-06" icon="playIcon" onClick="goStart()">启用</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="A9903-07" icon="assigningPermissions" onClick="goRights()">分配权限</gw:button>
			     <!-- <gw:button styleClass="sbuBtnStyle" code="A9903-08" icon="checkIcon" onClick="goVerify()">审核</gw:button>-->

			      <gw:button styleClass="sbuBtnStyle" code="A9903-12" icon="assignIcon" onClick="goResource()">分配资源</gw:button>
        </td>	
    </tr>
</table>              
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" 
	       name="sysUserForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	       fixRows="true" >
    <header height="27"  />
    <column width="3%" itemType="checkbox" property="ID" align="center" selectAll="true"  headerAlign="center" />
    <column width="10%" name="姓名" property="USERNAME" itemStyleClass="HLight" 
                 itemType="hyperlink" href="#" onClick="view('{ID}')" 
    	         headerOnMouseOut="headerOut(this)"  
    	         headerOnClick="query('userName')" 
    	         headerOnMouseOver="headerOver(this)" />
    <column width="10%" name="帐号" property="USERACCOUNT"    itemStyleClass="HLight" 
                 itemType="hyperlink" href="#"  onClick="view('{ID}')"
    	         headerOnMouseOut="headerOut(this)"  
    	         headerOnClick="query('userAccount')" 
    	         headerOnMouseOver="headerOver(this)" />
    <column width="22%" name="单位" property="ORGID" href="#" 
    	         headerOnMouseOut="headerOut(this)" 
    	         headerOnClick="query('orgId')" 
    	         headerOnMouseOver="headerOver(this)" />
    <column width="15%" name="部门" property="DEPTID" href="#" 
    	         headerOnMouseOut="headerOut(this)"   />
    <column width="7%" name="状态" property="USERSTATUS" align="center"   />
    <column width="33%" name="说明" property="REMARK" />
    <rooter height="30" width="100%" showType="all"  />      
</gw:grid2  >		
</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
<script type="text/javascript">
	
</script>	
</body>
</html>