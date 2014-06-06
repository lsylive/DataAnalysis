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
      var hyperlink = "..//compare/compareRunInfo.do";	
      var fulllink = contextpath + "/compare/compareRunInfo.do";		
      
//setInterval("renew()",1000);

function goDel()  {
	var id = findMultiSelected("ID","删除");
	if(id == "") return;
	var res = confirm("删除运行记录会一并删除该运行记录下所有的对比结果，真的要删除该运行记录？");
	if(res == true) {
      document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
      document.forms[0].target = "_self";
      document.forms[0].submit();
    }
}

function stop()  {
	var id = findMultiSelected("ID","暂停运行");
	if(id == "") return;
	var res = confirm("确实要暂停正在运行该对比查询的所有线程吗?");
	if(res == true) {
      document.forms[0].action = hyperlink + "?action=STOP&ids=" + id;
      document.forms[0].target = "_self";
      document.forms[0].submit();
    }
}

function resume()  {
	var id = findMultiSelected("ID","继续运行");
	if(id == "") return;
	document.forms[0].action = hyperlink + "?action=RESUME&ids=" + id;
    document.forms[0].target = "_self";
    document.forms[0].submit();
    
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

function goCancel(){
		parent.closedialog(false);
	}

 function init(){
   		showMessage('<bean:write name="compareRunInfoForm" property="errorMessage" />');
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
<html:form action="/compare/compareRunInfo.do" method="post" styleClass="special-form">
<html:hidden property="record(compId)" />
     
      <table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
          <tr>
            <td width="100%" valign="top">
          
             <table  cellspacing="0" cellpadding="0"  class="controlTable">
                <tr >
                  <td valign="top" align="left">
			      <gw:button styleClass="sbuBtnStyle" code="" icon="delIcon" onClick="goDel()">删除</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="refIcon" onClick="renew()">刷新</gw:button>
			      <!-- 
			      <gw:button styleClass="sbuBtnStyle" code="" icon="stopIcon" onClick="stop()">暂停</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="playIcon" onClick="resume()">继续</gw:button>
			       -->
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
	       name="compareRunInfoForm" property="query.recordSet" parameters="query" rowEventHandle="false" schema="common" 
	       fixRows="true">
    <header style="" height="27"  />
    <column width="5%" itemType="checkbox" property="ID"  styleClass="td_LeftTop"  headerStyleClass="td_LeftTop" />
    
	<column width="30%" name="开始运行时间" property="STARTTIME" align="center" headerOnClick="query('startTime')" 
				  styleClass="td_LeftTop"  headerStyleClass="td_LeftTop"
    	         headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)" />
	
    <column width="20%" name="结束运行时间" property="FINISHTIME"  headerOnClick="query('finishTime')" 
    	         headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)"/>
    <column name="运行状态" property="STATUSNAME"  headerOnClick="query('status')" 
    	         headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)"/>
 	<column name="匹配记录数" property="PMATCHCOUNT"  headerOnClick="query('pMatchCount')" 
    	         headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)"/>
   <rooter height="30" width="100%" showType="all" />          
</gw:grid2>		

</html:form>
</div>
  <div id="btnDiv" class="btnDiv" align="center">
			      <gw:button name="btnClose" onClick="goCancel()">关闭</gw:button>
</div>	

<%@include file="/common/dialog1.jsp" %>
</body>
</html>