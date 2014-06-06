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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<style type="text/css">
html, body {
	width:100%;
	height:100%;
}
html {
	padding:2px;
	overflow:auto;
}
</style>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../querycfg/commSubject.do";	
      var fulllink = contextpath + "/querycfg/commSubject.do";		
      
// 用于浏览该角色下的用户
function view(id) {
	 openWindow('',fulllink + "?action=viewUser&id=" + id,'700','300');
}


function goDel()  {
	var id = findMultiSelected("ID","删除");
	if(id == "") return;
	var res = confirm("是否真的要删除该主题?");
	if(res == true) {
      document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
      document.forms[0].target = "_self";
      document.forms[0].submit();
    }
}

function goModify()  {
	var id = findSelected("ID","修改");
	if(id == "") return;
    var urlink=fulllink+'?action=EDIT&id='+id;
    editMode="EDIT";
	openWindow('修改主题',urlink,800,450);
}



function goAdd()  {
	openWindow('添加主题',fulllink+'?action=ADD',800,450);
}

function renew()  {
	var order = getElement("query.order");                  order.value="";
	var desc =  getElement("query.orderDirection");         desc.value="";
	var pn =    getElement("query.pageNumber");             pn.value="1";
	var ps =    getElement("query.pageSize");               ps.value="10";
	var v0 =    getElement("query.parameters(roleCode)");   v0.value="";
	var v1 =    getElement("query.parameters(roleName)");   v1.value="";
	var v2 =    getElement("query.parameters(roleStatus)"); v2.value="";
    gosearch();
}
function goConfig(){
	var id = findSelected("ID","修改");
	if(id == "") return;
    var urllink='<%=CONTEXT_PATH%>/querycfg/subjectSynthesisCfg.do?subjectId='+id;
	openWindow('查询配置',urllink,800,450);
	//window.location.href=urlink;
}
function goQuery()  {
	var pn = getElement("query.pageNumber");             
	pn.value="1";
    gosearch();
}

function closedialog(ret){
	     dhxWins.window(winName).close();
	     if(ret=='true') {
       	 gosearch();
       }	
	  }
    function init(){
   		showMessage('<bean:write name="querySubjectForm" property="errorMessage" />');
   }
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>
<body onload="init()">
<div class="special-padding">
    <html:form action="/querycfg/commSubject.do" method="post" styleClass="special-form" >
        <table width="100%" cellpadding="0" cellspacing="0" class="formTable" >
            <tbody>
                <tr>
                    <td width=10%>主题名称：</td>
                    <td width=40%><html:text property="query.parameters(subName)" /></td>
                    <td width=10%>主题代码：</td>
                    <td width=40%><html:text property="query.parameters(subCode)" /></td>
                </tr>
                <tr class="btnTr">
                    <td class="textC" colspan="4">
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
                            <td class="textL">
                             <gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="goAdd()">增加</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="A9905-02" icon="subIcon" onClick="goModify()">修改</gw:button>&nbsp;
			      <gw:button styleClass="sbuBtnStyle" code="A9905-03" icon="delIcon" onClick="goDel()">删除</gw:button>&nbsp;
			       <gw:button styleClass="sbuBtnStyle" code="A9905-04" icon="assignIcon" onClick="goConfig()">查询配置</gw:button>&nbsp;
			       <gw:button styleClass="sbuBtnStyle" code="A9905-05" icon="refIcon" onClick="gosearch()">刷新</gw:button>
                 </td>
                     </tr>
                    </table>
                    <html:hidden property="query.order" />
                    <html:hidden property="query.orderDirection" />
                    <html:hidden property="query.pageNumber" />
                    <html:hidden property="query.recordCount"/>
                    <html:hidden property="query.pageCount" />
                    <gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" name="querySubjectForm" property="query.recordSet" parameters="query" rowEventHandle="false" fixRows="true" >
                        <header style="" height="27"  />
                        <column width="3%" itemType="checkbox" property="ID" />
                        <column width="25%" name="主题名称" property="SUBNAME" headerOnClick="query('subCode')" headerOnMouseOver="headerOver(this)" headerOnMouseOut="headerOut(this)" align="left" />
                        <column width="20%" name="主题编号" property="SUBCODE" headerOnClick="query('subName')" headerOnMouseOver="headerOver(this)" headerOnMouseOut="headerOut(this)" align="left" />
						<column name="主题说明" property="SUBDESC" headerOnClick="query('subName')" headerOnMouseOver="headerOver(this)" headerOnMouseOut="headerOut(this)" align="left" />
                        <rooter height="30" width="100%" showType="all"  />
                    </gw:grid2>
    </html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>