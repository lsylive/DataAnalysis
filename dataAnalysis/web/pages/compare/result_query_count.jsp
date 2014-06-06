<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>	 
<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/Array.js"></script>
<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../compare/compareResultRecord.do";	
      var fulllink = contextpath + "compare/compareResultRecord.do";		
      var changetag=false;
      var fristload=true;
      var array=new Array();
      function gosearch()  {
    	  var rc = getElement("query.pageSize");
        if(rc!=null) {
    	     if(!checkNumeric(rc,"[每页记录数]",false,true)) return false;
        }
        document.forms[0].action = hyperlink + "?action=BASEQUERY";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    } 
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

     var ids='<bean:write name="idArr" />';
function view(id) {
	var isPersonalTable = document.getElementsByName("query.parameters(isPersonalTable)")[0].value;
	if (isPersonalTable == "1") return;
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	openWindow("详细信息",contextpath+"query/commSubjectQuery.do" + "?action1=showdetail&uid="+id+"&tableId="+tableId+"&idArr="+ids,500,300);
	//viewSlave(id);
}

function viewSlave(id) {
	if(id == "") return;
	
    var runId =	document.getElementsByName("runId")[0].value;
	var tableEnName=document.getElementsByName("tableEnName")[0].value;
	
	parent.viewSlave(runId,id,tableEnName);
}
function showView(id)
{
var a =document.getElementsByName('viewType')[0].value;
	if(a!=0)
	view(id);
	else
	viewSlave(id);
}
function toQuery(type){
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	var queryUrl ;
	if(type=='batch')
	{
		queryUrl =contextpath + "dynamicquery/advancedbatch.do?action=TOBASEPAGE&tableId=" + tableId;
		window.location.href=queryUrl;
	}else if(type=='advance'){
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId;
		window.location.href=queryUrl;
	}else if(type=='simple'){
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId+"&action1=listsimple";
		window.location.href=queryUrl;
	}	
}

function renew()  {
	  var order =   getElement("query.order");                  order.value="";
	  var desc =    getElement("query.orderDirection");         desc.value="";
	  var pn =    getElement("query.pageNumber");             pn.value="1";
	  var ps =    getElement("query.pageSize");               ps.value="10";
	  var v0 =    getElement("query.parameters(cityCode)");      v0.value="";
	  var v1 =    getElement("query.parameters(tradeId)");    v1.value="";
	  var v2 =    getElement("query.parameters(categoryId)");   v2.value="";
	  var v3 =    getElement("query.parameters(type)");   v2.value="";
    gosearch();
}

var windows; 
var winName = "wind";
function openWindow(title,url,w,h){ 
	windows = new dhtmlXWindows();
	windows.enableAutoViewport(true);
	windows.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
	windows.setSkin("dhx_skyblue");
	var win = windows.createWindow(winName,0,0,w,h);
	win.allowPark();
	win.setText(title);
	win.setModal(true);
	win.centerOnScreen();
	win.attachURL(url);
}

function openSingleWindow(title,divhtml){
	windows = new dhtmlXWindows();
	   var win2 = windows.createWindow(winName, 0, 0, 300, 100);
    win2.setText(title);
    win2.keepInViewport(true);
    win2.setModal(true);
    win2.centerOnScreen();
    win2.button("minmax1").hide();
    win2.button("minmax2").hide();
    win2.button("park").hide();
    win2.attachHTMLString(divhtml);
    return win2;    
 }

function goQuery()  {
    document.forms[0].submit();	
}


function closedialog(ret){
	windows.window(winName).close();
}


</script>	
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>   
</head>

<body onload="showMessage('<bean:write name="compareResultRecordForm" property="errorMessage" />')" > 

<div id="div_main" style="position:absolute;top:0;left:0;width:100%;height:90%;background:white;border: 0px;" scrolling="auto"></div>

  	<div id="div_genaral" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  	 <div class="special-padding"> 


  	<html:form action="/compare/compareResultRecord.do?action=BASEQUERY" method="post">
		<html:hidden property="runId" />
		<html:hidden property="tableEnName"/>
		<html:hidden property="query.parameters(tableId)"/>
		<html:hidden property="query.parameters(pramaStr)"/>
		<html:hidden property="mainPkId"/>
		<html:hidden property="viewType"/>
		<html:hidden property="query.parameters(isPersonalTable)"/>
		            <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
        <!-- 
		<html:text property="query.parameters(tableCnName)" readonly="ture"></html:text>
		 -->
		 <% 
		 if (request.getAttribute("tableCnName")!=null) out.print(request.getAttribute("tableCnName"));
		 %>
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" 
	       name="compareResultRecordForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	       fixRows="true" rowDblClick="showView('{ID}')" >
    <header style="" height="27"  />
 	<rooter height="30" width="100%" showType="all" style="font-size:9pt;"  textStyle="text-align:center;" />      	
</gw:grid2>                 	
</html:form>

<html:form action="/dynamicquery/queryDetail" method="post" style="display:none">
<html:hidden property="action1" value="listbatch" />
<html:hidden property="tabId" />
<input type="hidden" name="idArr" />
</html:form>


</div>
</div>
  	


</body>
</html>