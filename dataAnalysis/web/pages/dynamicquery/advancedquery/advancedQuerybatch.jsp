<%@ page language="java" contentType="text/html;charset=UTF-8"%>
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
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Map"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
<link rel="stylesheet" type="text/css"
	href="<%=CONTEXT_PATH%>css/main.css" />
<script language="javascript"
	src="<%=CONTEXT_PATH%>js/datapicker/WdatePicker.js"></script>
<script language="javascript" src="<%=CONTEXT_PATH%>js/ctrl_util.js"></script>
<script language="javascript"
	src="<%=CONTEXT_PATH%>js/date_validate.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.form.js"></script>
<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../dynamicquery/advancedbatch.do";	
      var fulllink = contextpath + "dynamicquery/advancedbatch.do";		




      
    function gosearchForadvance(action)  {
    	var rc = getElement("query.pageSize");
        if(rc!=null) {
    	     if(!checkNumeric(rc,"[每页记录数]",false,true)) return false;
        }
        document.forms[0].action = hyperlink + "?action="+action;
        document.forms[0].target = "_self";
        document.forms[0].submit();
    };

    function queryBase(field)  {
        var order = getElement("query.order");
        var desc  = getElement("query.orderDirection");
    	var pn =  getElement("query.pageNumber");

        order.value = field;
        if(desc.value == "") desc.value = "asc";
        else if(desc.value == "asc") desc.value = "desc"; 
        else desc.value = "asc";
        pn.value="1";
        gosearchForadvance("BASEQUERY");
    };

    function checkInput(expressctl){
  		var ctrl=document.getElementsByTagName("input");
  		var ctrl1=document.getElementsByTagName("select");
  		var isInput=false;
  		var temp;
  		for(i=0;i<ctrl.length;i++){
  	  		//alert(ctrl[i].value+":"+ctrl[i].type);
  	  		temp = (ctrl[i].value).replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
  	  		  	  		
  			if((expressctl.indexOf(ctrl[i].id)==-1 || expressctl.indexOf(ctrl[i].name)==-1) 
  				&& ''!=temp && ctrl[i].type!='hidden')
  			{
  	  			isInput=true;
  			}
  			
  		}
  		for(i=0;i<ctrl1.length;i++){
  			temp = (ctrl[i].value).replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
  			
  			if(expressctl.indexOf(ctrl1[i].name)==-1 && ''!=temp)
  				isInput=true;
  		}

  		if(!isInput){
  			alert("请至少需要输入一个不为空的查询条件或选择一个下拉框的选项");
  			return false;
  		}else
  			return true;
  	} ;

function renew()  {
	  var order = getElement("query.order");                  order.value="";
	  var desc =  getElement("query.orderDirection");         desc.value="";
	  var pn =    getElement("query.pageNumber");             pn.value="1";
	  var ps =    getElement("query.pageSize");               ps.value="10";
	  var v0 =    getElement("query.parameters(cityCode)");      v0.value="";
	  var v1 =    getElement("query.parameters(tradeId)");    v1.value="";
	  var v2 =    getElement("query.parameters(categoryId)");   v2.value="";
	  var v3 =    getElement("query.parameters(type)");   v2.value="";
    gosearch();
};

function toQuery(type){
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	if (type=="base") 
	{
		var queryUrl =contextpath + "/dynamicquery/advancedquery.do?action=TOADVANCEDPAGE&queryType=base&tableId=" + tableId;		
		window.location.href=queryUrl;	
		} 
	else if(type=="advance")
	{
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId;
		window.location.href=queryUrl;	
	}else if(type=='simple'){
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId+"&action1=listsimple";
		window.location.href=queryUrl;
	}	
};
function insertRow(){
	var i = 0;
	var table = document.getElementById("searchTable");
	var row  = table.rows[0];	
	var cells = row.cells;
	var arow = table.insertRow(-1);	
	<logic:iterate id="oneTd" name="script" >
		arow.insertCell(i).innerHTML = '<bean:write name="oneTd" filter="false"/>';
		i++;
	</logic:iterate>
};

function deletRow(){	
		var hasdelet = false;
		var table = document.getElementById("searchTable");
		var boxs = document.getElementsByName("deletebox");
		for ( var i = boxs.length-1; i >= 0 ; i--) {
			if (boxs[i].checked==true) {
				hasdelet = true; 
			}
		}
		if(hasdelet){
			if (confirm("确定要删除条件?")) {
				for ( var i = boxs.length-1; i >= 0 ; i--) {
					if (boxs[i].checked==true) {
						var row = boxs[i].parentNode.parentNode||boxs[i].parentElement.parentElement;
						table.deleteRow(row.rowIndex); 
					}
				} 
			}
		}
		else{
			alert("请选择要删除的条件！");
		}
		
};
function goQuery(){
	var count = document.getElementById("searchTable").rows.length;
	var paramCount = document.getElementsByName("query.parameters(paramCount)")[0];
	
	paramCount.value = eval(count-1);
	 if(checkInput("querymode,deletebox"))
	{
	   document.forms[0].submit();
	}
};
var windows; 
var winName = "wind";
function openSingleWindow(title,divhtml){
	windows = new dhtmlXWindows();
	   var win2 = windows.createWindow(winName, 0, 0, 300, 150);
    win2.setText(title);
    win2.keepInViewport(true);
    win2.setModal(true);
    win2.centerOnScreen();
    win2.button("minmax1").hide();
    win2.button("minmax2").hide();
    win2.button("park").hide();
    win2.attachHTMLString(divhtml);
    return win2;    
 };
function closedialog(ret){
	windows.window(winName).close();
};
</script>
<style type="text/css">
html,body {
	width: 100%;
	height: 100%;
}

.spa {
	border: 1px solid #cdcac5;
	padding: 4px 0 0 0;
	text-align: center;
	position: absolute;
	background: #fff;
	margin: 0 0 0 0;
	width: 250px;
	height: 190px; *
	height: 200px;
	z-index: 100;
}

.spa .select {
	width: 245px;
	background-color: #FFFF99;
	border: 0px;
}
}
</style>
</head>

<body
	onload="showMessage('<bean:write name="advancedQueryForm" property="errorMessage" />')">
<div class="special-padding"><html:form styleId="batchQueryForm" action="/dynamicquery/advancedbatch.do?action=LIST" method="post">
	<html:hidden property="query.parameters(tableId)" />
	<html:hidden property="query.parameters(paramCount)" />
	<html:hidden property="queryName" />
	<html:hidden property="tableId" />
	<html:hidden property="condId" />
	<input type="hidden" name="action1" value="saveparam" />
	<input type="hidden" name="condType" value="4" />
	<table width="100%" cellspacing="1" cellpadding="1"
		class="controlTable">
		<tr>
			<td class="textL" valign="top">
			<table cellpadding="0" cellspacing="0"
				style="position: relative; top: -1px; height: 24px;" width="100%"
				border="0">
				<tr>
					<td class="textL" style="padding-left: 5px;">请填写/选择查询条件 &nbsp;
					<input type="radio" name="querymode" value="base"
						onclick="toQuery('base')" />简单查询&nbsp; <input type="radio"
						name="querymode" value="batch" checked="checked" />批量查询&nbsp; 
						<input type="radio" name="querymode" value="advance" onclick="toQuery('simple');" />高级查询&nbsp;
						<input type="radio" name="querymode" value="advance" onclick="toQuery('advance');" />复杂高级查询&nbsp;</td>
					<td class="textR">
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<table id="searchTable" width="100%" cellpadding="0" cellspacing="0"
		class="searchTable">
		<tbody>
			<bean:write name="formStr" filter="false" />
		</tbody>
	</table>
	<table cellspacing="0" cellpadding="0" class="controlTable">
		<tr>
			<td valign="top" align="left"><a href="javascript:void(0)"
				class="sbuBtnStyle" onclick="insertRow()"><strong><span
				class="addIcon">&nbsp;</span>增加</strong> </a> <a href="javascript:void(0)"
				class="sbuBtnStyle" onclick="deletRow()"><strong><span
				class="addIcon">&nbsp;</span>删除</strong> </a></td>
		</tr>
	</table>
	<table cellpadding="0" cellspacing="0" width="100%" class="formTable">
	     <tr class="btnTr">
					<td class="textL">
						<font color=red>注意：批量查询是实行精确查询，输入‘%’不起作用</font>
					</td>
		 </tr>
		<tr class="btnTr">
			<td class="textC">
			<gw:button styleClass="btnStyle" code="" onClick="goQuery()">查询</gw:button> 
			&nbsp; 
			<gw:button styleClass="btnStyle" code="" onClick="goReset()">重置</gw:button>
			</td>
		</tr>
	</table>
</html:form></div>
</body>
</html>