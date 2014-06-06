<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
	String msg = (String)request.getAttribute("msg");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
    <script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css"/>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
	<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

  <script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
	 <script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>
	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "compare/compareInfo.do";		

	function goCancel(){		
		parent.closedialog();
	}
	function goSubmit(){
		//checkString参数：对象名、标题、长度、是否检测空值\

		if(!checkString(getElement('record(compareName)'),"对比名称",120,true)) return;

		//if(!checkString(getElement('record(startTime)'),"对比开始时间",120,true)) return;
		
		if(!checkString(getElement('record(remark)'),"备注",250,false)) return;
		//if(!checkString(getElement('record(code)'),"代码",30,true)) return;

    	document.forms[0].submit();
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
	windows.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
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


function setTypeControls(){
		var typeSelected = $NAME('record(compareType)')[0];
		var cycleSelected = $NAME('record(runCycle)')[0];
	  	var weekSelect=$NAME('record(runDay)')[0];
	  	var dateText = $NAME('record(runDate)')[0];
	  	var hourText = $NAME('record(runHour)')[0];
	  	
	  	if (typeSelected.value=="1")
	  	{ 
	  		cycleSelected.disabled="true";
	  		weekSelect.disabled="true";
	  		dateText.disabled="true";
	  		hourText.disabled="true";
	  	}
	  	else{
	  		cycleSelected.disabled="";
	  		hourText.disabled="";
	  		setCycleControls();
	  	}
	 }
	 
function setCycleControls(){
		var cycleSelected = $NAME('record(runCycle)')[0];
	  	var weekSelect=$NAME('record(runDay)')[0];
	  	var dateText = $NAME('record(runDate)')[0];
	  	var hourText = $NAME('record(runHour)')[0];

	  	if (cycleSelected.value=="0")
	  	{
	  		weekSelect.disabled="true";
	  		dateText.disabled="";
	  	}
	  	else if(cycleSelected.value=="1")
	  	{
	  		weekSelect.disabled="";
	  		dateText.disabled="true";
	  	}
	  	else if(cycleSelected.value=="2" || cycleSelected.value=="3")
	  	{
	  		weekSelect.disabled="true";
	  		dateText.disabled="true";
	  	}
}


var msg = "<%= msg==null?"":msg%>";
if (msg!="")openAlert(msg);

function init(){
	setTypeControls();
}
</script>
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/compare/compareInfo.do" >
  <html:hidden property="action" />
  <html:hidden property="record(id)" />
  
		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 
			 <tr>
			  <td ><font color="red">*</font>对比名称：</td>
					<td colspan="3">
					   <html:text property="record(compareName)" />
					</td>
			<!-- 
			  <td ><font color="red">*</font>对比类型：</td>
					<td>
					   <html:select property="record(compareType)" onchange="setTypeControls(this)" disabled="false">
					   		<html:option value="1">手动对比</html:option>
					   		<html:option value="0">自动对比</html:option>
					   </html:select>
					</td>
			 -->
			</tr>
			<!-- 
			 <tr>
			  <td ><font color="red">*</font>对比开始时间:</td>
					<td >
					   <html:text property="record(startTime)" styleClass="Wdate" onclick="WdatePicker({isShowWeek:true});" />
					</td>
			  <td>对比结束时间:</td>
					<td>
					   <html:text property="record(endTime)" styleClass="Wdate" onclick="WdatePicker({isShowWeek:true});" />
					</td>
			</tr>
			 -->
			<tr>
			  <td><font color="red">*</font>运行周期:</td>
					<td >
					   <html:select property="record(runCycle)" onchange="setCycleControls(this)">
					   		<html:option value="0">每月</html:option>
					   		<html:option value="1">每周</html:option>
					   		<html:option value="2">每日</html:option>
					   		<html:option value="3">仅一次</html:option>
					   </html:select>
					</td>
			 <td>时间点配置:</td>
					<td>
					<div id="daydiv">
						
						<table cellpadding="0" cellspacing="0">
							<tr>
							<td width="50%">
							周:<html:select property="record(runDay)">
								<html:option value="0">日</html:option>
								<html:option value="1">一</html:option>
								<html:option value="2">二</html:option>
								<html:option value="3">三</html:option>
								<html:option value="4">四</html:option>
								<html:option value="5">五</html:option>
								<html:option value="6">六</html:option>
							</html:select>
							</td>
							<td> 日:<html:text property="record(runDate)"/>
							</td>
							<td> 时:<html:text property="record(runHour)"></html:text>
							<td> 分:<html:text property="record(runMinute)"></html:text>
							
							</td>
							</tr>
						</table>
					   </div>
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
		      <!--  <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
				    &nbsp;
			      <gw:button name="btnReset" onClick="goReset()">重置</gw:button> -->
</div>	  
<%@include file="/common/dialog1.jsp" %>
<%@include file="/common/resize.jsp" %>
</body>
</html>
