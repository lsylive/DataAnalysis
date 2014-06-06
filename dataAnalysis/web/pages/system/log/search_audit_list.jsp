<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String urlforcolumn = CONTEXT_PATH+"query/searchAudit.do";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 <script language="javascript" src="<%= CONTEXT_PATH %>/js/datapicker/WdatePicker.js"></script>
	 <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
	 <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>

<script language="javascript">  
var contextpath = "<%=CONTEXT_PATH%>";	
var hyperlink = "../query/searchAudit.do";	
var fulllink = contextpath + "query/searchAudit.do";		
      

function renew()  {
	  var order = getElement("query.order");                  order.value="";
	  var desc =  getElement("query.orderDirection");         desc.value="";
	  var pn =    getElement("query.pageNumber");             pn.value="1";
	  var ps =    getElement("query.pageSize");               ps.value="10";
    gosearch();
}

function goQuery()  {
	  var pn = getElement("query.pageNumber");             
	  pn.value="1";
      gosearch();
};
</script>	
   
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body onload="javascript:showMessage('<bean:write name="searchAuditForm" property="errorMessage" />');">
<div class="special-padding">  
<html:form action="/query/searchAudit.do" method="post">
<table cellpadding="0" cellspacing="0" class="formTable" >
      	    <tbody>
         <tr>
           <td width=10% class="textR">用户名：</td>
           <td width=23%>
             	<html:text property="query.parameters(username)" />
           </td>
           <td width=10% class="textR">单位：</td>
           <td width=23% class="sel">
              <html:select styleId="selectStatus" property="query.parameters(depid)" >
              	<html:optionsCollection property="codeSets(depts)" label="codeName" value="value" />
              </html:select>
           </td>
           <td width=10% class="textR">时间：</td>
           <td width=23%>
           <table cellpadding="0" cellspacing="0" class="formTable noBorder">
           <tr>
           <td><html:text property="query.parameters(starttime)" styleClass="Wdate" onfocus="WdatePicker({isShowWeek:true})" readonly="true"/></td>
           <td><html:text property="query.parameters(endtime)" styleClass="Wdate" onfocus="WdatePicker({isShowWeek:true})" readonly="true"/></td> 
           </tr>
           </table>
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
              <html:hidden property="query.order" />
              <html:hidden property="query.orderDirection" />
              <html:hidden property="query.pageNumber" />
              <html:hidden property="query.recordCount"/>
              <html:hidden property="query.pageCount" />
              
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
	       name="searchAuditForm" property="query.recordSet" parameters="query" rowEventHandle="false"
	       fixRows="true">
    <header style="" height="27"  />
    <column width="3%" itemType="checkbox" property="ID" align="center" selectAll="true"  headerAlign="center"/>
    <column width="22%" name="用户名" property="USERNAME" align="center" 
    	          headerOnMouseOut="headerOut(this)"
				  headerOnClick="query('USERNAME')"
				  headerOnMouseOver="headerOver(this)"/>
    <column width="20%" name="单位" property="DEPTNAME" align="center" 
    	        headerOnMouseOut="headerOut(this)"
				headerOnClick="query('DEPTNAME')"
				headerOnMouseOver="headerOver(this)"/>
    <column width="15%" name="时间" property="OPTTIME" align="center" 
				headerOnClick="query('OPTTIME')"
				headerOnMouseOut="headerOut(this)"
				headerOnMouseOver="headerOver(this)"/>
    <column width="40%" name="查询条件" property="CONDITION" align="center" 
    	        headerOnClick="query('CONDITION')"   
				headerOnMouseOut="headerOut(this)"
				headerOnMouseOver="headerOver(this)"/>
    <rooter height="30" width="100%" showType="all" />      
</gw:grid2>		
</html:form>
</div>
</body>
</html>