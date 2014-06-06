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
	  	 openWindow('主题查询',urllink,800,450);
	  }
	  function goQuery(){
	  
	  }
	  function showPage(){
			parent.showPage();
		}

		function exportAllExcell(id){
			parent.exportAllExcell(id);
		}
	</script>	
   <style type="text/css">
html {
        width: 100%;
        height: 100%;
        margin: 0px;
        overflow-y: auto;
}
body {
         width: 100%;
        margin: 0px;
      
}
</style>

</head>

<body topmargin="0" leftmargin="0" onload="showPage()">
<div class="special-padding">
<html:form action="/query/subjectBatchQuery.do" method="post">


  <table cellpadding="0" cellspacing="0" width="99%" class="formTable">
<logic:iterate id="result" name="resultList" indexId="resind">
  		<tr>
			   	<td class="textL">
			   		<div style="float:left;margin-top:3px"><b>表名：<bean:write name="result" property="cName" /> </b>&nbsp;<b>来源: <bean:write name="result" property="cityName" /></b>&nbsp;<b>分类: <bean:write name="result" property="catagoryName" /></b> 
			   		&nbsp;<b> 总数: <bean:write name="result" property="totalcount" /> </b></div>
			   		<b style="float:right;">
			   		<a  class="sbuBtnStyle" href='javascript:exportAllExcell(<bean:write name="result" property="id" />);' ><strong><span class="addIcon">&nbsp;</span>导出</strong></a>
			   		<!-- 
			   		<gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="exportAllExcell(<bean:write name='result' property='id' />)">导出</gw:button>
			   		 -->
			   		</b>  
			   		
			   	</td>
			   </tr>
<logic:present name="result" property="valueList">
			   <tr>
			   <td style="padding:0;">
			   
					<table cellpadding="0" cellspacing="0" width="100%" class="listTable">
						<tr height="27px">
<logic:iterate id="column" name="result" property="columnList" >
							<th width="15%" align="center">
								<bean:write name="column" property="columnName" />
							</th>
</logic:iterate>
						<th  align="center">
							数量
						</th>
						</tr>
					
<logic:iterate id="vallist" name="result" property="valueList" >
						<tr  class="trBg">
						
<logic:iterate id="valArr" name="vallist" property="valArr">
							<td style="background:#e3effe;text-align:center;" width="15%">
<logic:present name="valArr" property="showurl">
								<a href='javascript:parent.showparamresult(<bean:write name="result" property="id" />,<bean:write name="vallist" property="colind" />)'  >

</logic:present>
								<bean:write name="valArr" property="value" />
<logic:present name="valArr" property="showurl">
								</a>
</logic:present>
								</td>
</logic:iterate>
						<td style="background:#e3effe;text-align:center;" align="center" >
							<bean:write name="vallist" property="count" />
						</td>
						</tr>
</logic:iterate>
					
					</table>	
					</td>
				</tr>
				</logic:present>
						
</logic:iterate>
				</table>







</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>