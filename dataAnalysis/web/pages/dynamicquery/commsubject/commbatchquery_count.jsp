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
        overflow: hidden;
}
</style>

</head>

<body topmargin="0" leftmargin="0" onload="showPage()">
<div class="special-padding">
<html:form action="/query/subjectBatchQuery.do" method="post">

             
<gw:grid2 border="0" cellPadding="1" cellSpacing="1" width="100%" styleClass="listTable" secondRowStyle="background:#e3effe"
	       name="subjectBatchQueryForm" property="query.recordSet" parameters="query" rowEventHandle="false" schema="common" 
	       fixRows="true" rowDblClick="parent.showresult('{id}')">
    				<header height="27"  />
    
    			
    	         
   							<rooter height="0" width="100%" showType="all" />      
							</gw:grid2>		



</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>