<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>	
   <script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "querycfg/columnSynthesisCfg.do";		
	
	function goSubmit(){
		if(!checkNumeric(getElement('record(seqNo)'),"字段排序号",10,true,true)) return;
		document.forms[0].action=fulllink+"?action=UPDATE";
		document.forms[0].submit(); 	
	}

	function check(){
		
		
	}
	</script> 
	   <style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body>
  <div id="formDiv" class="formDiv">
  <html:form method="post" action="/querycfg/columnSynthesisCfg.do" >
  <html:hidden name="columnSynthesisConfigForm" property="columnId"/>
  <html:hidden name="columnSynthesisConfigForm" property="tableId"/>
  <html:hidden property="record(columnId)" />
  <html:hidden property="record(tableId)" />
  <html:hidden property="record(colcfgId)" />
  <html:hidden property="record(paramcfgId)" />
		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">			 
			 <tr>
			 <td width="20%" class="textR">中文名称：</td>
					<td width="30%" class="textL">
					<bean:write name="columnName" />
			 </td>
			 <td class="textR"><font color="red">*</font>字段排序：</td>
					<td >
					    <html:text property="record(seqNo)"></html:text>
					</td> 
			</tr>
			<tr>
			  <td class="textR">是否概要字段：</td>
					<td class="textL">
					    <html:checkbox property="record(isSubject)" value="1" styleClass="checkbox" />
					</td>
			  <td class="textR">可排序字段：</td>
					<td class="textL">
					  <html:checkbox property="record(isSortable)" value="1" styleClass="checkbox" />
					</td>
			</tr>
			<tr>
			  <td class="textR">是否查询字段：</td>
					<td class="textL">
					    <html:checkbox property="record(isFilter)" value="1" styleClass="checkbox" />
					</td>
			  <td class="textR">查询运算符：</td>
				<td class="sel">
					 <html:select property="record(filterOperator)">
				  	 <html:optionsCollection property="codeSets(FILTER_OPER)" label="codeName" value="value" />
				  </html:select>
					</td>
			</tr>
			<tr>
			  <td width="20%" class="textR">是否显示：</td>
					<td width="30%" class="textL">
					    <html:checkbox property="record(isShown)" value="1" styleClass="checkbox" />
					</td>
			  <td colspan="2">&nbsp;</td>
			</tr>
			
			
			<tr>
			  <td class="textR">支持批量查询：</td>
					<td class="textL">
					 <html:checkbox property="record(batchQuery)" value="1" styleClass="checkbox"/>
					</td>
			  <td class="textR">支持简繁查询：</td>
					<td class="textL">
					 <html:checkbox property="record(stQuery)" value="1" styleClass="checkbox" />
					 
					</td>
			</tr>
			<tr>
			  <td class="textR">支持同音查询：</td>
					<td class="textL">
					   <html:checkbox property="record(homonymQuery)" value="1" styleClass="checkbox" />
					</td>
			  <td class="textR">支持模糊查询：</td>
					<td class="textL">
					   <html:checkbox property="record(fuzzyQuery)" value="1" styleClass="checkbox" />
					</td>
			</tr>
			
			
	 </table>
				
  </html:form>
  </div>  
    <%@include file="/common/resize.jsp" %>
</body>
</html>
