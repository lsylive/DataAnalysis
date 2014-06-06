<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld"
	prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html-el.tld" prefix="html-el" %> 
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw"%>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css">
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">

		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>

		<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/commSubjectQuery.do";	
      var fulllink = contextpath + "/query/commSubjectQuery.do";		
      function init(){
      	var hasnext=<bean:write name="hasnext" />
      	var hasprev=<bean:write name="hasprev" />
      	if(!hasprev)
      		$NAME('prev')[0].disabled=true;
      	if(!hasnext)
      		$NAME('next')[0].disabled=true;
      }
      function goDirect(oper){
      	$NAME("dir")[0].value=oper;
      	document.forms[0].submit();
      		
      }
	
   	function goCancel(){
		parent.closedialog(false);
	}
	</script>
		<style type="text/css">
   html, body {width:100%; height:100%;}
</style>

	</head>

	<body onload="init()">
		
		<div id="formDiv" class="formDiv">
		<html:form styleId="commSubjectQueryForm" action="/query/commSubjectQuery.do" method="post">
			<html:hidden property="id"/>
			<html:hidden property="uid"/>
			<html:hidden property="action1" />
			<html:hidden property="querySql" />
			<html:hidden property="tableId" />
			<html:hidden property="idArr" />
			<input type="hidden" name="dir" />
			 <table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                        <tr>
                            <td class="textL">
                            <gw:button name="prev" styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="goDirect('-')">上一页</gw:button>
                            &nbsp; <gw:button name="next" styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="goDirect('+')">下一页</gw:button>
			     
                 </td>
                     </tr>
                    </table>  
					<table id="tblForm" cellpadding="0" cellspacing="0" width="99%" class="formTable">
							<bean:write name="htmlcode" filter="false"/>
							<logic:present name="picURL">
								<tr >
								<td colspan="4" class="textC"> 
								<logic:iterate id="bean" name="picURL">
								<%
								if(((String)bean).endsWith("tif")||((String)bean).endsWith("TIF")){
								%>
								<img width="100%" src='<%=CONTEXT_PATH%>image/imageAction.do?fileName=pics/<bean:write name="tableName" filter="false"/>/<bean:write name="bean" filter="false"/>' /><BR> <BR>
								<%}else{ %>
								<img width="100%" src='<%=CONTEXT_PATH%>pics/<bean:write name="tableName" filter="false"/>/<bean:write name="bean" filter="false"/>' /><BR> <BR>
								<%} %>
								</logic:iterate>
								</td>
								</tr>
							</logic:present>
					</table>	
		</html:form>
	</div>
	<div id="btnDiv" class="btnDiv">
			     
			      <gw:button name="btnCancel" onClick="goCancel()">关闭</gw:button>
				   
</div>	  
<%@include file="/common/resize.jsp" %>
  
	</body>
</html>
