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

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
 <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "querycfg/subjectSynthesisCfg.do";		

	function goCancel(){
		parent.closedialog(false);
	}
	
	function goSubmit(){
		if(!checkString(getElement('record(name)'),"字段名",30,true)) return;
		if(!checkString(getElement('record(cnName)'),"中文名",30,true)) return;
		if(!checkString(getElement('record(dataType)'),"字段类型",2,true)) return;
		if(!checkNumeric(getElement('record(orderNo)'),"查询排序号",10,true,true)) return;
		if(!checkString(getElement('record(indId)'),"指标名称",10,true)) return;
		 document.forms[0].action=fulllink+"?action=SAVE";
    	document.forms[0].submit();
    	
	}
	
	function init(){
   		showMessage('<bean:write name="subjectSythesisCfgForm" property="errorMessage" />');
   		}
   
	  
	</script>
	   <style type="text/css">
   html, body {width:100%; height:100%;}
</style>

</head>

<body onload="init()">
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/querycfg/subjectSynthesisCfg.do" styleClass="special-form">
	<html:hidden property="record(subjectId)"/>
 


<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			  <tr >
				  <td width="20%" class="textR"><font color="red">*</font>字段英文名：</td>
				  <td width="30%">
				 	<html:text property="record(name)" title="字段英文名" />
					 
				  </td>
				  <td width="20%" class="textR"><font color="red">*</font>字段中文名：</td>
				  
					<td width="30%">
					 <html:text property="record(cnName)" title="字段中文名" />
					</td>
			 </tr>
			  <tr>
				 <td class="textR"><font color="red">*</font>字段类型：</td>
					<td class="sel">
					<html:select property="record(dataType)">
				  	 <html:optionsCollection property="codeSets(DATAMETA_TYPE)" label="codeName" value="value" />
				  </html:select> 
					</td>
				  <td class="textR"><font color="red">*</font>查询排序号：</td>
					<td class="textL">
					<html:text property="record(orderNo)" styleClass="checkbox" />
					</td>	
			 </tr>
			 <tr >
				  <td width="15%" class="textR"><font color="red">*</font>指标名称：</td>
				  <td width="35%" class="sel">
				  <html:select property="record(indId)" title="指标名称">
				  	 <html:options property="ID" labelProperty="NAME"  collection="indList" />
				  </html:select>
					 
				  </td>
				  <td width="20%" class="textR">使用代码集：</td>
				  
					<td width="25%" class="sel">
					 <html:select property="record(codeSetNo)">
					 <option value="">--请选择--</option>
				  	 <html:options property="codesetNo" labelProperty="name"  collection="codeSetList" />
				  </html:select>
					</td>
			 </tr>
			
			 <tr >
			 <td width="20%" class="textR">是否查询条件：</td>
				  
					<td width="25%" class="textL">
					<html:checkbox property="record(isFilter)" value="1" styleClass="checkbox"/>
					</td>
				  <td width="15%" class="textR">运算符：</td>
				  <td width="35%" class="sel">
				  <html:select property="record(filterOperator)">
				  	 <html:optionsCollection property="codeSets(FILTER_OPER)" label="codeName" value="value" />
				  </html:select>
					 
				  </td>
				 
			 </tr>
			 
			<tr>
				
				  <td class="textR">支持批量查：</td>
				  
					<td class="textL">
					<html:checkbox property="record(batchQuery)" value="1" styleClass="checkbox" />
					
					</td>
					 <td>支持同音查：</td>
				  
					<td class="textL">
					<html:checkbox property="record(homonymQuery)" value="1" styleClass="checkbox" />
					 
					</td>
			 </tr>
			 <tr>
				
				  <td class="textR">支持简繁查：</td>
				  
					<td class="textL">
					<html:checkbox property="record(stQuery)" value="1" styleClass="checkbox" />
					</td>
					 <td class="textR">支持模糊查：</td>
				  
					<td class="textL">
					<html:checkbox property="record(fuzzyQuery)" value="1" styleClass="checkbox" />
					</td>
			 </tr>
			 
	 </table>
			
  </html:form>
  </div>
   <div id="btnDiv" class="btnDiv">
		       <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
				    &nbsp;
			      <gw:button name="btnReset" onClick="goReset()">重置</gw:button>
</div>	  
    <%@include file="/common/resize.jsp" %>
</body>
</html>
