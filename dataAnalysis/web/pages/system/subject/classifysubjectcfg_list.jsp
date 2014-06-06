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
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

	<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../querycfg/classifySynthesisCfg.do";	
      var fulllink = contextpath + "/querycfg/classifySynthesisCfg.do";		
      
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
	openWindow('修改分类主题配置',urlink,650,300);
}



function goAdd()  {
	openWindow('添加分类主题配置',fulllink+'?action=ADD&subjectId=<bean:write name="subjectSythesisCfgForm" property="subjectId" scope="request"/>',650,300);
}

function renew()  {
	var order = getElement("query.order");                  order.value="";
	var desc =  getElement("query.orderDirection");         desc.value="";
	var pn =    getElement("query.pageNumber");             pn.value="1";
	var ps =    getElement("query.pageSize");               ps.value="20";
	var v0 =    getElement("query.parameters(roleCode)");   v0.value="";
	var v1 =    getElement("query.parameters(roleName)");   v1.value="";
	var v2 =    getElement("query.parameters(roleStatus)"); v2.value="";
    gosearchcfg();
}

function goQuery()  {
	var pn = getElement("query.pageNumber");             
	pn.value="1";
    gosearchcfg();
}

function closedialog(ret){
	     dhxWins.window(winName).close();
	     if(ret=='true') {
       	 gosearchcfg();
       }	
	  }
   function gosearchcfg()  {
	  var rc = getElement("query.pageSize");
    if(rc!=null) {
	     if(!checkNumeric(rc,"[每页记录数]",false,true)) return false;
    }
    document.forms[0].action = hyperlink + '?action=list&catagoryId=<bean:write name="subjectSythesisCfgForm" property="catagoryId" scope="request"/>';
    document.forms[0].target = "_self";
    document.forms[0].submit();
}
 function init(){
   		showMessage('<bean:write name="subjectSythesisCfgForm" property="errorMessage" />');
   }

	</script>	
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body onload="init()">
<div class="special-padding"> 
<html:form action="/querycfg/classifySynthesisCfg.do" method="post" >
 <html:hidden property="catagoryId"/>
      
      <table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
          <tr>
            <td width="100%" valign="top">
           
       	  	 
               <table  cellspacing="1" cellpadding="1"  class="controlTable">
                <tr >
                  <td valign="top" align="left">
                  <gw:button styleClass="sbuBtnStyle" code="" icon="addIcon" onClick="goAdd()">增加</gw:button>
                  <gw:button styleClass="sbuBtnStyle" code="" icon="subIcon" onClick="goModify()">修改</gw:button>&nbsp;
			      <gw:button styleClass="sbuBtnStyle" code="" icon="delIcon" onClick="goDel()">删除</gw:button>&nbsp;
			       <gw:button styleClass="sbuBtnStyle" code="" icon="refIcon" onClick="gosearchcfg()">刷新</gw:button>
                  
                  </td>
                </tr>	
              </table>
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
                 </td>
</tr>
</table> 	
        
<gw:grid2 border="0" cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
	       name="subjectSythesisCfgForm" property="query.recordSet" parameters="query" rowEventHandle="false" schema="common" 
	       fixRows="true" >
    <header style="" height="27"  />
    <column width="3%" itemType="checkbox" property="ID" />
    <column width="30%" name="条件名称" property="CNNAME" align="left" 
    	         headerOnClick="query('cnName')"
    	         headerOnMouseOver="headerOver(this)"
    	         headerOnMouseOut="headerOut(this)" />
    <column width="30%" name="指标名称" property="INDNAME" align="left" 
    	         headerOnClick="query('indName')"
    	         headerOnMouseOver="headerOver(this)"
    	         headerOnMouseOut="headerOut(this)" />
	
    <column width="20%" name="是否过滤条件" property="ISFILTER" align="left"  
    	         headerOnMouseOver="headerOver(this)"
    	         headerOnMouseOut="headerOut(this)"/>
    <column name="查询操作符" property="OPER" align="left"   
    	         headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)"/>
   <rooter height="30" width="100%" showType="all"  />      
</gw:grid2>		
</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>