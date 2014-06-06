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
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>

	<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/commSubjectQuery.do";	
      var fulllink = contextpath + "/query/commSubjectQuery.do";		
      
	  function showdetail(id){
	  	 var urllink='<%=CONTEXT_PATH%>/query/commSubjectQuery.do?action1=SHOWQUERY&id='+id;
	  	 openWindow('查询结果',urllink,800,450);
	  }
	  function goQuery(){
	  
	  }

	  function showPage(){
		  parent.showPage();
	}
   
	</script>	
   
<style type="text/css">
html, body {
        width: 100%;
        height: 100%;
        margin: 0px;
        }
</style>
</head>

<body onload="showPage()">
<div class="special-padding" style="overflow-x:hidden;
overflow-y:auto;">


<html:form action="/query/commSubjectQuery.do" method="post">
<html:hidden name="commSubjectQueryForm" property="record" />



<gw:grid2 border="0" cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" secondRowStyle="background:#e3effe"
	       name="commSubjectQueryForm" property="query.recordSet" parameters="query" rowEventHandle="false" schema="common" 
	       fixRows="true" >
    				<header height="27"  />
    
    						<column width="30%" name="表名称" itemType="hyperlink" href="javascript:void(0)"  onClick="parent.showlist('{id}')" property="cName" align="center" />

							<column width="20%" name="来源" property="cityName" align="center" />
							<column width="20%" name="分类" property="catagoryName" align="center" />
							<column width="20%" name="安全等级" property="securityLevel" align="center" />
	
    						 <column width="10%" name="结果数量" property="count" />
    	         
   							<rooter height="0" width="100%" showType="none" />      
							</gw:grid2>		



</html:form>
</div>

<%@include file="/common/dialog1.jsp" %>
</body>
</html>