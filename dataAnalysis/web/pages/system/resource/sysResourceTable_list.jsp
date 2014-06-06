<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String urlforcolumn = CONTEXT_PATH+"system/ResourceColumn.do";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 
	 <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
	 <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>

<script language="javascript">  
var contextpath = "<%=CONTEXT_PATH%>";	
var hyperlink = "../system/ResourceTable.do";	
var fulllink = contextpath + "system/ResourceTable.do";		
      
function getDelIds()  {
	  var ids = findMultiSelected("ID","删除");
	  if(ids == "") return "";
	  return ids;
}

function getModifyId()  {
	var id = findSelected("ID","修改");
	if(id == "") return "";
	return id;
}

function renew(treeid)  {
	  var order = getElement("query.order");                  order.value="";
	  var desc =  getElement("query.orderDirection");         desc.value="";
	  var pn =    getElement("query.pageNumber");             pn.value="1";
	  var ps =    getElement("query.pageSize");               ps.value="10";
	  var v1 =    getElement("query.parameters(treeid)");     v1.value=treeid;
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

<body onload="javascript:showMessage('<bean:write name="resourceTableForm" property="errorMessage" />');">
<div class="special-padding">  
<html:form action="/system/ResourceTable.do" method="post">
<table cellpadding="0" cellspacing="0" class="formTable" >
      	    <tbody>
         <tr>
           <td width=20% class="textR">表名：</td>
           <td width=30%>
             	<html:text property="query.parameters(cnName)" />
           </td>
           <td width=20% class="textR">安全等级：</td>
           <td width=30% class="sel">
              <html:select styleId="selectStatus" property="query.parameters(securityLevel)" >
              	<option value=""></option>
              	<html:optionsCollection property="codeSets(SECURITY_LEVEL)" label="codeName" value="value" />
              </html:select>
           </td>
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
      
              <html:hidden property="query.parameters(treeid)" />            
              <html:hidden property="query.order" />
              <html:hidden property="query.orderDirection" />
              <html:hidden property="query.pageNumber" />
              <html:hidden property="query.recordCount"/>
              <html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
	       name="resourceTableForm" property="query.recordSet" parameters="query" rowEventHandle="false"
	       fixRows="true">
    <header style="" height="27"  />
    <column width="3%" itemType="checkbox" property="ID" align="center" selectAll="true"  headerAlign="center"/>
    <column width="22%" name="资源库" property="CN_NAME" align="center" 
    	          headerOnMouseOut="headerOut(this)"
				  headerOnClick="query('CN_NAME')"
				  headerOnMouseOver="headerOver(this)"/>
    <column width="20%" name="所属分类" property="CATEGORY_NAME" align="center" 
    	        headerOnMouseOut="headerOut(this)"
				headerOnClick="query('CATEGORY_NAME')"
				headerOnMouseOver="headerOver(this)"/>
    <column width="15%" name="安全等级" property="SECURITY_LEVEL" align="center" 
				headerOnClick="query('SECURITY_LEVEL')"
				headerOnMouseOut="headerOut(this)"
				headerOnMouseOver="headerOver(this)"/>
    <column width="40%" name="说明" property="REMARK" align="center" 
    	        headerOnClick="query('remark')"   
				headerOnMouseOut="headerOut(this)"
				headerOnMouseOver="headerOver(this)"/>
    <rooter height="30" width="100%" showType="all" />      
</gw:grid2>		
</html:form>
</div>
</body>
</html>