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
      var hyperlink = "..//compare/compareFilter.do";	
      var fulllink = contextpath + "/compare/compareFilter.do";		
      
// 用于浏览该角色下的用户
function view(id) {
	 openWindow('',fulllink + "?action=viewUser&id=" + id,'700','300');
}


function goDel()  {
	var id = findMultiSelected("ID","删除");
	if(id == "") return;
	var res = confirm("是否真的要删除该条件?");
	if(res == true) {
      document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
      document.forms[0].target = "_self";
      document.forms[0].submit();
    }
}

function goModify()  {
	var id = findSelected("ID","修改");
	if(id == "") return;
	var compId=$NAME('record(compId)')[0].value;
    var urlink=fulllink+'?action=EDIT&id='+id+"&record(compId)="+compId;
    editMode="EDIT";
	openWindow('修改筛选条件配置',urlink,550,250);
}



function goAdd()  {
	var compId=$NAME('record(compId)')[0].value;
	var mainTabId = $NAME('record(mainTabId)')[0].value;
	if (mainTabId=='')
	{
		alert('请先进行对比表及字段设置');
		return;
	}
	openWindow('添加筛选条件配置',fulllink+'?action=ADD&record(compId)='+compId +'&mainTabId=' + mainTabId,550,250);
}

function renew()  {
	var order = getElement("query.order");                  order.value="";
	var desc =  getElement("query.orderDirection");         desc.value="";
	var pn =    getElement("query.pageNumber");             pn.value="1";
	var ps =    getElement("query.pageSize");               ps.value="20";
    gosearch();
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
   		showMessage('<bean:write name="compareFilterForm" property="errorMessage" />');
   }
	</script>	
    <style type="text/css">
   html, body {width:100%; height:100%;}
  .special-padding{padding: 2px;}
  .special-form{margin: 0;}
</style>

</head>

<body onload="init()" >
<div class="special-padding">  
<html:form action="/compare/compareFilter.do" method="post" styleClass="special-form">
<html:hidden property="record(compId)" />
<html:hidden property="record(mainTabId)" />
     
      <table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
          <tr>
            <td width="100%" valign="top">
          
             <table  cellspacing="0" cellpadding="0"  class="controlTable">
                <tr >
                  <td valign="top" align="left">
                   <gw:button styleClass="sbuBtnStyle" code="" icon="addIcon" onClick="goAdd()">增加</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="subIcon" onClick="goModify()">修改</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="delIcon" onClick="goDel()">删除</gw:button>
			       <gw:button styleClass="sbuBtnStyle" code="" icon="refIcon" onClick="renew()">刷新</gw:button>
                 
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
        
<gw:grid2 border="0" cellPadding="2" cellSpacing="0" width="100%" styleClass="listTable" secondRowStyle="background:#e3effe"
	       name="compareFilterForm" property="query.recordSet" parameters="query" rowEventHandle="false" schema="common" 
	       fixRows="true">
    <header style="" height="27"  />
    <column width="5%" itemType="checkbox" property="ID"  styleClass="td_LeftTop"  headerStyleClass="td_LeftTop" />
    
	<column width="30%" name="筛选列中文名" property="COLUMNNAME" align="center" 
    	         headerOnClick="query('COLUMNNAME')"  styleClass="td_LeftTop"  headerStyleClass="td_LeftTop"
    	         headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)" />
	
    <column width="20%" name="筛选操作符" property="OPER"  
    	         headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)"/>
    <column name="筛选值" property="FILTERVALUE" 
    	         headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)"/>
   <rooter height="30" width="100%" showType="all" />          
</gw:grid2>		


</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>