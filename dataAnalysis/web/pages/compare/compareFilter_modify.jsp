<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	String msg = (String)request.getAttribute("msg");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<script src="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
    <script src="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css"/>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
	<script src="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>
  	
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "/system/ResourceTable.do";		
	  var td='<textarea rows="3" name="record(paramvalue1)" type="text"></textarea><input type="hidden" name="record(paramvalue2)" />';		
      var timetd='<input name="paramvalue1" type="text" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowWeek:true});" /><input type="hidden" name="paramvalue2" />';		
      var timebetweentd='<table cellspacing="0" cellpadding="0" width="100%"><tr><td width="50%"><input name="paramvalue1" type="text" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowWeek:true});" /></td><td width="50%"><input name="paramvalue2" type="text" readonly="readonly" class="Wdate" onclick="WdatePicker({isShowWeek:true});" /></td></tr></table>';
	  var betweentd='<table cellspacing="0" cellpadding="0" width="100%" ><tr><td width="50%"><input name="record(paramvalue1)" type="text" /></td><td width="50%"><input name="record(paramvalue2)" type="text"  /></td></tr></table>';
	  
	function goCancel(){		
		parent.closedialog();
	}
	function goSubmit(){
		//checkString参数：对象名、标题、长度、是否检测空值\

		if(!checkString(getElement('record(columnId)'),"筛选列中文名",120,true)) return;

		if(!checkString(getElement('record(filterOperator)'),"筛选操作符",120,true)) return;
		
		if(!checkString(getElement('record(paramvalue1)'),"筛选值",1000,false)) return;
		//if(!checkString(getElement('record(code)'),"代码",30,true)) return;

    	document.forms[0].submit();
	}
	function changecolumn(){
	  	var tab=$$("tblForm");
		var oper=$NAME('record(filterOperator)')[0].value;
		
		 if(oper=='BT'){
			$$('valuetd').innerHTML=betweentd;
		}else
			$$('valuetd').innerHTML=td;
	  }

	</script>
	<script type="text/javascript">
var windows; 
function closeWindow(){	
	windows.window("win").close();
};
	function addTable(){
		var url=fulllink + "?action=ADDTAB";
		
		openWindow('选择表',fulllink + "?action=ADDTAB",450,400);
	}

function openAlert(message){
	windows = new dhtmlXWindows();
	windows.enableAutoViewport(true);
	windows.setImagePath("<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
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
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;padding-right:2px;}
</style>

</head>

<body>

<div id="formDiv" class="formDiv">
  <html:form method="post" action="/compare/compareFilter.do" >
  <html:hidden property="action" />
  <html:hidden property="record(compId)" />
  <html:hidden property="record(id)" />
  
		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 
			 <tr>
			  <td width="30%"><font color="red">*</font>选择筛选列：</td>
					<td >
					
				  <html:select property="record(columnId)">
				  	 <html:optionsCollection name="columnList" label="cnName" value="id" />
				  </html:select>
					   
					</td>
					
					 </tr>
			<tr>
			  <td ><font color="red">*</font>筛选条件：</td>
					<td>
					   <html:select property="record(filterOperator)" onchange="changecolumn(this)">
				  	 <html:optionsCollection property="codeSets(FILTER_OPER)" label="codeName" value="value" />
				  </html:select>
					</td>
					 
			</tr>
			<tr>
			  <td ><font color="red">*</font>筛选值：</td>
					<td id="valuetd">
					<logic:present name="compareFilterForm" property="record(filterOperator)">
						<logic:equal value="BT" name="compareFilterForm" property="record(filterOperator)">
							<table cellspacing="0" cellpadding="0" width="100%"><tr><td width="50%">
							<html:text property="record(paramvalue1)"></html:text></td>
							<td width="50%">
							<html:text property="record(paramvalue2)"></html:text>
							</td>
							</tr>
							</table>
						</logic:equal>
						<logic:notEqual value="BT" name="compareFilterForm" property="record(filterOperator)">
						<html:textarea rows="3" property="record(paramvalue1)"></html:textarea>
						<input type="hidden" name="record(paramvalue2)" />
						</logic:notEqual>
					</logic:present>
					<logic:notPresent name="compareFilterForm" property="record(filterOperator)">
					<textarea rows="3" name="record(paramvalue1)"></textarea>
					
					<input type="hidden" name="record(paramvalue2)" />
					</logic:notPresent>
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
<%@include file="/common/dialog1.jsp" %>
<%@include file="/common/resize.jsp" %>
</body>
</html>
