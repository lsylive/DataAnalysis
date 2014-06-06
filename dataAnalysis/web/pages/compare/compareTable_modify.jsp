<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld"
	prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	String msg = (String) request.getAttribute("msg");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<script
			src="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
		<script
			src="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
		<link rel="stylesheet" type="text/css"
			href="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css" />
		<script
			src="<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
		<script src="<%=CONTEXT_PATH%>/js/json2.js"></script>

		<link rel="stylesheet" type="text/css"
			href="<%=CONTEXT_PATH%>/css/main.css" />

		<script language="javascript"
			src="<%=CONTEXT_PATH%>/js/date_validate.js"></script>

		<script language="javascript" src="<%=CONTEXT_PATH%>/js/ctrl_util.js"></script>
		<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "/compare/compareTable.do";		

	function goCancel(){		
		parent.closedialog();
	}
	function goSubmit(){
		//checkString参数：对象名、标题、长度、是否检测空值\

		if(!checkString(getElement('record(mainTabName)'),"主资源表名称",120,true)) return;

		if(!checkString(getElement('record(mainTabFieldNames)'),"主资源表对比字段列表",5000,true)) return;
		
		if(!checkString(getElement('record(slaveTabNames)'),"对比资源表名称",5000,true)) return;
		
		var slaveTabFieldIds = document.getElementsByName("record(slaveTabFieldIds)")[0].value;
	
		if (slaveTabFieldIds.indexOf('-1')>-1) 
		{
			alert('还有未选择的对比字段');
			getElement('record(mainTabName)').focus();
			return;
		}
		
    	document.forms[0].submit();
	}

var windows; 
var tableFieldsCount = 0;
var slaveTabFieldIdArray;

function closeWindow(){	
	windows.window("win").close();
};
	function addMTable(){
		var url=fulllink + "?action=ADDTAB";
		var id=$NAME("record(compId)")[0].value;
		var tableId = document.getElementsByName("record(mainTabId)")[0].value;
		openWindow('选择资源库资源表',fulllink + "?action=ADDRTABLE&mutilSelect=true&type=table&id="+id + "&tableId="+ tableId,450,300);
	}
function addSTable(){
		var mainTabType = document.getElementsByName("record(mainTabType)")[0].value;
		if (mainTabType==1)
		{
			var url=fulllink + "?action=ADDTAB";
			var id=$NAME("record(compId)")[0].value;
			openWindow('选择资源库资源表',fulllink + "?action=ADDRTABLE&mutilSelect=false&type=table&id="+id,450,300);
		}
		else
		{
			var url=contextpath + "/personal/personalTable.do";
			var id=$NAME("record(compId)")[0].value;
			openWindow('选择个人空间资源表',fulllink + "?action=ADDPTABLE&mutilSelect=false&type=table&id="+id,450,300);
		}
	}
function addSField(){
		var mainTabType = document.getElementsByName("record(mainTabType)")[0].value;
		var url=fulllink + "?action=ADDField";
		var id=$NAME("record(compId)")[0].value;
		var tableId = document.getElementsByName("record(mainTabId)")[0].value;
		openWindow('选择字段',fulllink + "?action=ADDSFIELD&tableId="+tableId+"&mainTabType=" + mainTabType,450,300);
	}
function addMField(tableId,cellId){
		var url=fulllink + "?action=ADDField";
		openWindow('选择字段',fulllink + "?action=ADDMFIELD&tableId="+tableId+"&cellId=" + cellId+"&mainTabType=1",450,300);
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
function addTableArrToList(tabIdArr,tabNameArr,self_call){
		if (self_call=='0') 
		{
			closedialog();
   			document.getElementsByName("record(slaveTabNames)")[0].value=tabNameArr;
   			document.getElementsByName("record(slaveTabIds)")[0].value=tabIdArr;
   		}
   		
   		var matchTable = document.getElementsByName("matchTable")[0];
   		
   		var rowNum=matchTable.rows.length;
     	for (i=1;i<rowNum;i++)
     	{
         	matchTable.deleteRow(i);
         	rowNum=rowNum-1;
         	i=i-1;
     	}
     	
   		var rows=matchTable.rows;
   		var tabIdArray = tabIdArr.substring(0,tabIdArr.length-1).split(",");
   		var tabNameArray = tabNameArr.substring(0,tabNameArr.length-1).split(",");
   		slaveTabFieldIdArray = new Array(tabIdArray.length);
   		var cellId;
   		for(j=0; j < tabIdArray.length; j ++)
   		{
   			var newRow=matchTable.insertRow(rows.length);
   			var cells=newRow.cells;
   			var newCell=newRow.insertCell(cells.length);
   			newCell.innerHTML="<td valign='top'>"+tabNameArray[j]+"</td>";
   			slaveTabFieldIdArray[j] = new Array(tableFieldsCount);
   			for(i=0; i<tableFieldsCount; i ++){
   				newCell=newRow.insertCell(cells.length);
   				//cellId = "cell" + j +"" + i;
   				cellId =  (j+1) + "" + (i+1);
   				newCell.innerHTML="<td valign='top'><a name='btnAddField'  href='javascript:addMField("+ tabIdArray[j]+"," + cellId + ");'><strong>选择对比字段</strong></a></td>";
   				slaveTabFieldIdArray[j][i]='-1';
   			}
   		}
   		if (self_call=='0') {
   			document.getElementsByName("record(slaveTabFieldIds)")[0].value=slaveTabFieldIdArray;
   		}
   		

   }
   
   function addTableToMain(tabId,tabName,self_call){
		if (self_call=='0') closedialog();
   		document.getElementsByName("record(mainTabName)")[0].value=tabName;
   		document.getElementsByName("record(mainTabId)")[0].value=tabId;
   		
   		var matchTable = document.getElementsByName("matchTable")[0];
   		
   		var rowNum=matchTable.rows.length;
     	for (i=0;i<rowNum;i++)
     	{
         	matchTable.deleteRow(i);
         	rowNum=rowNum-1;
         	i=i-1;
     	}
   		var rows=matchTable.rows;
   		var newRow=matchTable.insertRow(rows.length);
   		var cells=newRow.cells;
   		var newCell=rows(newRow.rowIndex).insertCell(cells.length);
   		
   		newCell.innerHTML="<td valign='top'>"+tabName+"</td>"
   }
   
   function addTableFieldsToMain(fieldIds,fieldNames,self_call){
		if (self_call=='0') closedialog();
   		document.getElementsByName("record(mainTabFieldIds)")[0].value=fieldIds;
   		document.getElementsByName("record(mainTabFieldNames)")[0].value=fieldNames;
   		
   		var matchTable = document.getElementsByName("matchTable")[0];
   		
   		var rowNum=matchTable.rows.length;
     	for (i=1;i<rowNum;i++)
     	{
         	matchTable.deleteRow(i);
         	rowNum=rowNum-1;
         	i=i-1;
     	}
   		var rows=matchTable.rows;
   		var firstRow = rows[0];
   		var cells=firstRow.cells;
   		var cellLength = cells.length;
   		for(i=1; i<cellLength; i++)
   		{
   			firstRow.deleteCell(i);
   			cellLength--;
   			i--;
   		}
   		var fieldIdArray = fieldIds.substring(0,fieldIds.length-1).split(",");
   		var fieldNameArray = fieldNames.substring(0,fieldNames.length-1).split(",");
   		tableFieldsCount = fieldIdArray.length;
   		for(i=0; i<tableFieldsCount; i ++){
   			var newCell=firstRow.insertCell(cells.length);
   			newCell.innerHTML="<td valign='top'>"+fieldNameArray[i]+"</td>"
   		}
   }
   
   function addMTableFieldsToMain(tableId,cellId,fieldId,fieldName,self_call)
   {
   		if (self_call=='0') closedialog();
   		if (cellId.length == 1) cellId = "0" + cellId;
   		var rowNum = cellId.substring(0,1), colNum = cellId.substring(1,2);
   		var matchTable = document.getElementsByName("matchTable")[0];
   		var targetCell = matchTable.rows[rowNum].cells[colNum];
   		targetCell.innerHTML = "<td valign='top'><a name='btnAddField' href='javascript:addMField("+ tableId+"," + cellId + ");'>"+fieldName+"</a></td>";
   		
   		slaveTabFieldIdArray[rowNum-1][colNum-1] =fieldId;
   		document.getElementsByName("record(slaveTabFieldIds)")[0].value = slaveTabFieldIdArray;
   		
   }
   
function closeAlert(){
	windows.window("win").close();
}
function closedialog(ret){
	     dhxWins.window(winName).close();
	  }
var msg = "<%=msg == null ? "" : msg%>";
if (msg!="")openAlert(msg);
	
	
function init(){
	if($NAME('record(mainTabId)')[0].value!='' && $NAME('record(mainTabName)')[0].value!='')
	{
		addTableToMain($NAME('record(mainTabId)')[0].value,$NAME('record(mainTabName)')[0].value,'1');	
	}
	if ($NAME('record(mainTabFieldIds)')[0].value!='' && $NAME('record(mainTabFieldNames)')[0].value!='')
	{
		addTableFieldsToMain($NAME('record(mainTabFieldIds)')[0].value,$NAME('record(mainTabFieldNames)')[0].value,'1');
	}
	if ($NAME('record(slaveTabIds)')[0].value!='' && $NAME('record(slaveTabNames)')[0].value!='')
	{
		addTableArrToList($NAME('record(slaveTabIds)')[0].value,$NAME('record(slaveTabNames)')[0].value,'1');
	}
	if ($NAME('record(slaveTabFieldNames)')[0].value!='[]' && $NAME('record(slaveTabFieldIds)')[0].value!='[]');
	{
		var jsonFieldName = $NAME('record(slaveTabFieldNames)')[0].value;
		var jsonFieldId = $NAME('record(slaveTabFieldIds)')[0].value;
	
		var slaveTabFieldNameArr = JSON.parse(jsonFieldName);
		var slaveTabFieldIdArr = JSON.parse(jsonFieldId);
		if (slaveTabFieldNameArr=='') return;
		
		var slaveTabIdArr = document.getElementsByName("record(slaveTabIds)")[0].value;
   		var slaveTabIdArray = slaveTabIdArr.substring(0,slaveTabIdArr.length-1).split(",");
   		var tableId,cellId;
   		
   		for(i=0; i < slaveTabIdArray.length; i ++){
			tableId = slaveTabIdArray[i];
			
			if (slaveTabFieldNameArr[i]==null)
			{
				continue;
			}
			for(j=0; j < slaveTabFieldNameArr[i].length; j ++)
			{
				var cellId =  (i+1) + "" + (j+1);
				addMTableFieldsToMain(tableId,cellId,slaveTabFieldIdArr[i][j],slaveTabFieldNameArr[i][j],'1');
				
			}
		}
		
	}
}
	</script>
		<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
	margin: 0px;
	padding: 0px;
	overflow: hidden;
	padding-right: 2px;
}
</style>
	</head>

	<body onload="init()">
		<div id="formDiv" class="formDiv">
			<html:form method="post" action="/compare/compareTable.do">
				<html:hidden property="action" value="savebase" />
				<html:hidden property="record(compId)" />
				<html:hidden property="record(mainTabId)" />
				<html:hidden property="record(slaveTabIds)" />
				<html:hidden property="record(indIds)" />
				<html:hidden property="record(mainTabFieldIds)" />
				<html:hidden property="record(slaveTabFieldIds)" />
				<html:hidden property="record(slaveTabFieldNames)" />

				<table id="tblForm" cellpadding="0" cellspacing="0" width="100%"
					class="formTable">
					<tr>
						<td width="30%">
							<font color="red">*</font>主资源表类型：
						</td>
						<td colspan="2">
							<html:select property="record(mainTabType)">
								<!--<html:option value="0">个人空间资源表</html:option>-->
								<html:option value="1">资源库资源表</html:option>
							</html:select>
						</td>
						
					</tr>
					<tr>
						<td width="30%">
							<font color="red">*</font>主资源表名称：
						</td>
						<td width="60%">
							<html:text property="record(mainTabName)" disabled="true"/>
						</td>
						<td width="10%">
							<gw:button name="btnAddTab" onClick="addSTable()">编辑</gw:button>
						</td>
					</tr>
					<tr>
						<td width="30%">
							<font color="red">*</font>主资源表对比字段列表：
						</td>
						<td width="60%">
							<html:text property="record(mainTabFieldNames)" disabled="true"/>
						</td>
						<td width="10%">
							<gw:button name="btnAddField" onClick="addSField()">编辑</gw:button>
						</td>
					</tr>
					<tr>
						<td width="30%">
							<font color="red">*</font>对比资源表名称：
						</td>
						<td width="60%">
							<html:text property="record(slaveTabNames)" disabled="true"/>
						</td>
						<td width="10%">
							<gw:button name="btnAddTab" onClick="addMTable()">编辑</gw:button>
						</td>
					</tr>
				</table>
				<table id="matchTable" cellpadding="0" cellspacing="0" width="100%"
					class="formTable">
					<tr></tr>
				</table>
			</html:form>
		</div>
		<div id="btnDiv" class="btnDiv">
		</div>
		<%@include file="/common/dialog1.jsp"%>
		<%@include file="/common/resize.jsp"%>
	</body>
</html>
