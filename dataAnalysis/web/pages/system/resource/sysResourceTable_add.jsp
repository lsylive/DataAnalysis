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
	
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css"/>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>	
<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "system/ResourceTable.do";		

	function goCancel(){
		parent.closedialognofresh();
	}

	function goSubmit(){
		//checkString参数：对象名、标题、长度、是否检测空值
		
		  if(!checkString(getElement('record(cnName)'),"中文名称",120,true)) return;

		  if(!checkString(getElement('record(name)'),"英文名称",120,true)) return;
		  if(!checkNumeric(getElement('record(orde)'),"顺序",6,false,false)) return;
		  if(!checkString(getElement('record(remark)'),"备注",250,false)) return;
		  //if(!checkString(getElement('record(code)'),"代码",30,true)) return;
		var cityid = getElement("record(cityCode)").value;
		var categoryid = getElement("record(categoryId)").value;
		var nodeid;
		if (categoryid!="") {
			nodeid = cityid+"_"+categoryid;
		}else {
			nodeid = cityid;
		}		
    	document.forms[0].submit();
    	parent.setSelectTreeNode(nodeid);
	}
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body onload="showMessage('<bean:write name="resourceTableForm" property="errorMessage"/>');">
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/system/ResourceTable.do?action=SAVE">
 	<html:hidden property="record(nodeId)"/>
		 <table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 <tr >
				  <td width="20%"><font color="red">*</font>所属地市：</td>
				  <td width="30%" class="sel">
				<html:select property="record(cityCode)">
              	<html:optionsCollection property="codeSets(City)" label="codeName" value="value" />
				</html:select>
				  </td>
				  <td width="20%"><font color="red">*</font>所属行业：</td>
				  <td width="30%" class="sel">
					   <html:select property="record(categoryId)" >
              	<html:optionsCollection property="codeSets(Category)" label="codeName" value="value" filter="false"/>
					   </html:select>
				  </td>
			 </tr>
			 <tr>
			  <td ><font color="red">*</font>中文名称：</td>
					<td >
					   <html:text property="record(cnName)"/>
					</td>
			  <td ><font color="red">*</font>英文名称：</td>
					<td >
					   <html:text property="record(name)" />
					</td>
			</tr>
			<tr>
			  <td >代码：</td>
					<td >
					   <html:text property="record(code)" />
					</td>
			  <td ><font color="red">*</font>安全等级：</td>
					<td class="sel">
					  <html:select property="record(securityLevel)">
              	<html:optionsCollection property="codeSets(SECURITY_LEVEL)" label="codeName" value="value" />
					   </html:select>
					</td>
			</tr>
			<tr>
			<td >顺序：</td>
					<td >
					   <html:text property="record(orde)" />
					</td>
				<td ><font color="red">*</font>资源类型：</td>
					<td class="sel">
					  <html:select property="record(type)">
              				<html:optionsCollection property="codeSets(DT_TYPE)" label="codeName" value="value" />
					   </html:select>
				</td>
			</tr>
			<tr>
			   <td>备注：</td>
				 <td colspan="3">
					  <html:textarea rows="8" property="record(remark)" ></html:textarea>
				 </td>
			</tr>
          </table>      		
  </html:form>
</div>
	
<div id="btnDiv" class="btnDiv">
	<gw:button styleClass="btnStyle" code="" onClick="goSubmit()">提交</gw:button>
              &nbsp;
    <gw:button styleClass="btnStyle" code="" onClick="goCancel()">取消</gw:button>
              &nbsp;
    <gw:button styleClass="btnStyle" code="" onClick="goReset()">重置</gw:button>
</div>
<%@include file="/common/resize.jsp" %>
</body>
</html>
