<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收藏详细页</title>
    <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css">
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
	<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	<script language="javascript" src="<%= CONTEXT_PATH %>/js/jquery.js"></script>	
  	<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
  
    <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	
    <script type="text/javascript">
    	function init(){
    		
    		var idArr='<%=request.getParameter("idArr")%>';
    		var contextpath = "<%=CONTEXT_PATH%>";	
    		var url=contextpath+""
    	}
    	function delRow(ctrl,index){
    	var rindex;
		var e=ctrl;
		e=e.parentElement||e.parentNode;
		e=e.parentElement||e.parentNode;
		e=e.parentElement||e.parentNode;
		while(typeof(e)=="undefined"||e.tagName!="TR")
   			e=e.parentElement||e.parentNode;
		rindex=e.rowIndex;
		$$('maintab').deleteRow(rindex);
    	
    	}
    </script>
  <style type="text/css">
   html, body {width:100%; height:100%;}
</style>

	</head>

	<body>
		<logic:present name="resultList">
		<div id="formDiv" class="formDiv">
		
		 <table id="maintab"  cellpadding="0" cellspacing="0" width="99%">
		<logic:iterate id="result" name="resultList" indexId="resind">  
			  <tr>
			  <td>
			    <table cellpadding="0" cellspacing="0" width="99%" class="formTable">
			   <tr>
			   	<td class="textL">
			   		<b>表名：<bean:write name="result" property="tabName" /> </b><b>主键: <bean:write name="result" property="pid" /></b>   
			   		<a class="sbuBtnStyle" href="#" onclick="javascript:parent.delFavorite('<bean:write name="result" property="ids" />');delRow(this,'<bean:write name="resind" />')"><span class="btnCancel"><strong>删除</strong></span></a>
			   	</td>
			   </tr>
			   <tr>
			   <td>
					<table cellpadding="0" cellspacing="0" width="99%" class="formTable">
							<bean:write name="result" property="htmlcode" filter="false"/>
					</table>	
					</td>
				</tr>
				</table>
				</td>
				</tr>
				
	</logic:iterate>
	</table>
	</div>
	
  </logic:present>
  <logic:notPresent name="resultList">
  	<b>您还未选择对应的记录添加到收藏</b>
  </logic:notPresent>

	</body>
</html>

