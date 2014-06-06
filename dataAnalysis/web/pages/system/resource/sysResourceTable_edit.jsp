<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String fullurl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	String msg = (String)request.getAttribute("msg");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script src="<%=fullurl%>/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
    <script src="<%=fullurl%>/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css"/>
	<link rel="stylesheet" type="text/css" href="<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
	<script src="<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=fullurl%>/css/main.css" />

  <script language="javascript" src="<%= fullurl %>/js/date_validate.js"></script>
  	
  <script language="javascript" src="<%= fullurl %>/js/ctrl_util.js"></script>
	<script type="text/javascript">
      var contextpath = "<%=fullurl%>";	
      var fulllink = contextpath + "/system/ResourceTable.do";		

	function goCancel(){		
		parent.closedialog();
	}
	function goSubmit(){
		//checkString参数：对象名、标题、长度、是否检测空值\

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
	}
function init(){
	var title = document.getElementsByName("record(cnName)")[0].value;
	parent.setTitle("修改表("+title+")");
	showMessage('<bean:write name="resourceTableForm" property="errorMessage" />');
}
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;padding-right:2px;}
</style>
</head>

<body onload="init()">
<script type="text/javascript">
var windows; 
function closeWindow(){	
	windows.window("win").close();
};

function openAlert(message){
	windows = new dhtmlXWindows();
	windows.enableAutoViewport(true);
	windows.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
	windows.setSkin("dhx_skyblue");
	var win = windows.createWindow("win",0,0,250,125);
	win.denyPark();
	win.denyMove();
	win.denyResize();
	win.button("minmax1").hide();
	win.button("park").hide();
	win.setText("确认");
	win.setModal(true);
	win.center();
	var div = "<div ><p align='center'>"+message+"</P><p align='center'><input type=button onclick='closeAlert()' value='确定'/></p></div>";
	win.attachHTMLString(div);
}

function closeAlert(){
	windows.window("win").close();
}
var msg = "<%= msg==null?"":msg%>";
if (msg!="")openAlert(msg);
</script>
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/system/ResourceTable.do?action=UPDATE" >
  <html:hidden property="record(id)" />
		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 <tr >
				  <td width="20%"><font color="red">*</font>所属地市：</td>
				  <td width="30%" class="sel">
					<html:select property="record(cityCode)">
              			<html:optionsCollection property="codeSets(City)" label="codeName" value="value" />
					</html:select>
				  </td>
				  <td width="20%"><font color="red">*</font>所属分类：</td>
				  <td width="30%" class="sel">
					   <html:select property="record(categoryId)" >
              	<html:optionsCollection property="codeSets(Category)" label="codeName" value="value" />
					   </html:select>
				  </td>
				 
			 </tr>
			 <tr>
			  <td ><font color="red">*</font>中文名称：</td>
					<td >
					   <html:text property="record(cnName)" />
					</td>
			  <td ><font color="red">*</font>英文名称：</td>
					<td>
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
  </div>
  <%@include file="/common/resize.jsp" %>
</body>
</html>
