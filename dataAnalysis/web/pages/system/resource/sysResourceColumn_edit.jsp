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
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
	<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "system/ResourceColumn.do";		
	
	function goSubmit(){
		 //checkString参数：对象名、标题、长度、是否检测空值
		  if(!checkString(getElement('record(cnName)'),"中文姓名",60,true)) return;

		  if(!checkString(getElement('record(name)'),"英文姓名",30,true)) return;
		//checkNumeric参数：对象名、标题、长度、是否检测空值,是否检测大于0
		  //if(!checkNumeric(getElement('record(dataLength)'),"长度",6,true,true)) return;
		// if(!checkNumeric(getElement('record(dataPercise)'),"精度",6,true,true)) return;
		  if(!checkNumeric(getElement('record(dataLength)'),"长度",6,true,false)) return;
		  if(!checkNumeric(getElement('record(dataPercise)'),"精度",6,true,false)) return;
		  if(!checkNumeric(getElement('record(squenceNo)'),"顺序号",6,true,true)) return; 
		  if(!checkString(getElement('record(defaultValue)'),"默认值",30,false)) return;
		  if(!checkString(getElement('record(maxValue)'),"最大值",30,false)) return;
		  if(!checkString(getElement('record(minValue)'),"最小值",30,false)) return;
		  if(!checkString(getElement('record(remark)'),"备注",30,false)) return;
			document.forms[0].submit();
		//}   	
	}

	function setFormByDataMeta(){

		var indicator = document.getElementsByName("record(indicatorId)")[0];
		var datatype = document.getElementsByName("record(dataType)")[0];
		var datacode = document.getElementsByName("record(codesetId)")[0];
		
		var dataMeta = document.getElementsByName("record(datametaId)")[0];
		var dataMetaId = dataMeta.value;
		var url = fulllink+"?action=SETFORM&dataMetaId="+dataMetaId;
		dhtmlxAjax.get(url,function(loader){
			
		if(loader.xmlDoc.responseXML.getElementsByTagName("indicator")[0].firstChild!=null){
			var indicatorid = loader.xmlDoc.responseXML.getElementsByTagName("indicator")[0].firstChild.nodeValue;			
			for(var i=0;i<indicator.length;i++){
				if (indicator.options[i].value==indicatorid) {
						indicator.selectedIndex = i;
						break;
				}
			}
		}else{
				indicator.selectedIndex = 0;
		}
			
		if(loader.xmlDoc.responseXML.getElementsByTagName("dataType")[0].firstChild!=null){
			var datatypeid = loader.xmlDoc.responseXML.getElementsByTagName("dataType")[0].firstChild.nodeValue;
			for(var i=0;i<indicator.length;i++){
				if (datatype.options[i].value==datatypeid) {
					datatype.selectedIndex = i;
					break;
				}
			}
		}else{
			datatype.selectedIndex = 0;
		}

			if(loader.xmlDoc.responseXML.getElementsByTagName("dataCode")[0].firstChild!=null){
			var datacodeid = loader.xmlDoc.responseXML.getElementsByTagName("dataCode")[0].firstChild.nodeValue;
				for(var i=0;i<datacode.length;i++){
					if (datacode.options[i].value==datacodeid) {
						datacode.selectedIndex = i;
						break;
					}
				}
			}else{
				datacode.selectedIndex = 0;
			}
			
		});	
	}
function init(){
	showMessage('<bean:write name="resourceColumnForm" property="errorMessage" />');
}
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body onload="init()">
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/system/ResourceColumn.do?action=UPDATE">
  <html:hidden property="record(id)" />
  <html:hidden property="record(tableId)" />
			 <table id="tblForm" cellpadding="0" cellspacing="0" width="98%" class="formTable">
			  <tbody>
			 <tr>
			  <td width="20%"><font color="red">*</font>中文名称：</td>
					<td width="30%">
					   <html:text property="record(cnName)" />
					</td>
			  <td width="20%"><font color="red">*</font>英文名称：</td>
					<td width="30%">
					   <html:text property="record(name)" />
					</td>
			</tr>
			<tr>
			  <td >对应数据元：</td>
					<td >
					   <html:hidden property="record(datametaId)" />
					   <html:text property="record(datametaName)" onchange="setFormByDataMeta()" onclick="openWindow('选择数据元',280,300)"/>
					</td>
			  <td >长度：</td>
					<td >
					   <html:text property="record(dataLength)" />
					</td>
			</tr>
			<tr>
			  <td >精度：</td>
					<td >
					   <html:text property="record(dataPercise)" />
					</td>
			  <td >是否可空：</td>
					<td class="textL">
					   <html:checkbox property="record(isNull)" value="1" styleClass="checkbox"/>
					</td>
			</tr>
			<tr>
			  <td >默认值：</td>
					<td >
					   <html:text property="record(defaultValue)" />
					</td>
			  <td >最大值：</td>
					<td >
					   <html:text property="record(maxValue)" />
					</td>
			</tr>
			<tr>
			  <td >最小值：</td>
					<td >
					   <html:text property="record(minValue)" />
					</td>
			  <td >是否主键：</td>
					<td class="textL">
					  <html:checkbox property="record(isPrimary)" value="1" styleClass="checkbox"/>
					</td>
			</tr>
			<tr>
			  <td >是否外键：</td>
					<td class="textL">
					   <html:checkbox property="record(isForeign)" value="1" styleClass="checkbox"/>
					</td>
			  <td ><font color="red">*</font>顺序号：</td>
					<td >
					   <html:text property="record(squenceNo)" />
					</td>
			</tr>
			<tr>
			  <td ><font color="red">*</font>安全等级：</td>
					<td class="sel">
					   <html:select property="record(securityLevel)">
              	<html:optionsCollection property="codeSets(SECURITY_LEVEL)" label="codeName" value="value" />
					   </html:select>
					</td>
			  <td >采用代码集：</td>
					<td class="sel">
					   <html:select property="record(codesetId)">
              	<html:optionsCollection property="codeSets(codeset)" label="codeName" value="value" />
					   </html:select>
					</td>
			</tr>
			<tr>
			<td >类型：</td>
					<td class="sel">
					   <html:select property="record(dataType)">
              			<html:optionsCollection property="codeSets(DATAMETA_TYPE)" label="codeName" value="value" />
					</html:select>
					</td>
			<td >对应指标：</td>
					<td class="sel">
					   <html:select property="record(indicatorId)">
              	<html:optionsCollection property="codeSets(indicator)" label="codeName" value="value" />
					   </html:select>
					</td>
			  
			</tr>
			<tr>
			  <td >备注：</td>
					<td colspan="3">
					    <html:textarea rows="3" property="record(remark)" ></html:textarea>
					</td>
			</tr>
        </tbody>
	 </table>
  </html:form>
  </div>
  <%@include file="/common/resize.jsp" %>
</body>
<script>
var wins; 
var winName = "win";
var layout;
function openWindow(title,w,h){ 
	var url = "<%=CONTEXT_PATH%>system/ResourceColumn.do?action=SELECTDATAMETA";
	wins = new dhtmlXWindows();
	wins.enableAutoViewport(true);
	wins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
	wins.setSkin("dhx_skyblue");
	var win = wins.createWindow(winName,0,0,w,h);
	win.allowPark();
	win.setText(title);
	win.setModal(true);
	win.centerOnScreen();
	win.attachURL(url);
}

function closeWindow(){
	wins.window(winName).close();
}

function setDatameta(id,text){
	document.getElementsByName("record(datametaId)")[0].value = id;
	document.getElementsByName("record(datametaName)")[0].value = text;
}

function getSelectedId(){
	return document.getElementsByName("record(datametaId)")[0].value;
}

function goMove(){
	document.getElementsByName("record(datametaId)")[0].value = "";
	document.getElementsByName("record(datametaName)")[0].value = "";
}
</script>
</html>
