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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>	 
<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/Array.js"></script>
<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../dynamicquery/advancedbatch.do";	
      var fulllink = contextpath + "dynamicquery/advancedbatch.do";	
      var changetag=false;
      var fristload=true;
      var array=new Array();
function view(id) {
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	openWindow("详细信息",contextpath+"query/commSubjectQuery.do" + "?action1=showdetail&uid="+id+"&tableId="+tableId,800,600);
}

function toQuery(type){
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	var queryUrl ;
	if (type=="base") 
	{
		
	} 
	else if(type=="batch")
	{
		queryUrl =contextpath + "dynamicquery/advancedbatch.do?action=TOBASEPAGE&tableId=" + tableId;
	}else if(type=="advance"){
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId;
	}
	
	
	window.location.href=queryUrl;
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
	win.denyPark();
	win.button("park").hide();
	win.button("minmax1").hide();
	win.setText(title);
	win.setModal(true);
	win.centerOnScreen();
	win.maximize();
	win.denyResize();
	//win.attachObject("iframe");
	//document.frames[0].src=url;
	win.attachURL(url);
}

function queryCondition(value)  {
	document.getElementsByName("query.parameters(pramaStr)")[0].value=escape(value);
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	var url = fulllink + "?action=SEARCHCONDITION&tableId="+tableId+"&pramaStr="+value;
	url = encodeURI(url);
	url = encodeURI(url);
	//openWindow("详细记录",url,800,600);
	window.frames[1].location.href=url;
	tabbar.setTabActive("d2");  	
}

function goAdvancedQuery(queryType){
	var tableid = findSelected("ID","查询");
	window.location.href = fulllink + "?action=TOADVANCEDPAGE&queryType="+queryType+"&tableId=" + tableid;
}

function closedialog(ret){
	windows.window(winName).close();
}

function goBack(){
	var url = hyperlink+"?action=TOBASEPAGE";
	document.forms[0].action=url;
	document.forms[0].submit();
}

function addFavorite(tabId,ids){
	
	var idArrctrl=$NAME("idArr")[0];
	var idArr=idArrctrl.value;
	var idsArray=ids.split(";");
	var tmpstr='';
	for(i=0;i<idsArray.length;i++){
		tmpstr=tabId+"-"+idsArray[i];
		array.append(tmpstr,true);
	}
	changetag=true;
}
function showFavorite(){
	var idArraystr=array.toString();
	//alert(idArraystr);
	$NAME("idArr")[0].value=idArraystr;
	if(changetag || fristload){
		document.forms[1].target='favorfrm';
		document.forms[1].submit();
		fristload=false;
		changetag=false;
	}
}
function delFavorite(ids){
	if(array.contains(ids))
		array.remove(ids);		
}	

function exportAllExcell(){
	document.forms[0].action="../dynamicquery/advancedbatch.do?action=EXPORTALLEXCELL";
	document.forms[0].target='downloadframe';
	document.forms[0].submit();
}
</script>	
<style type="text/css">
   html {
        width: 100%;
        height: 100%;
        margin: 0px;
        overflow-y: auto;
}
body {
        width: 100%;
        height: 100%;
        margin: 0px;
      
}
</style>   
</head>

<body onload="showMessage('<bean:write name="advancedQueryForm" property="errorMessage" />')" > 
<div style="display: none;"><iframe id="downloadframe" name="downloadframe"></iframe></div>
<div id="div_main" style="position:absolute;top:0;left:0;width:100%;height:90%;background:white;border: 0px;"></div>
<div id="div_genaral" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  <div class="special-padding">
  <html:form action="/dynamicquery/advancedbatch.do?action=SEARCHCONDITION" styleId="#advancedQueryForm" method="post" >
		<html:hidden property="query.parameters(tableId)"/>
		<html:hidden property="query.parameters(pramaStr)"/>
		<html:hidden property="query.parameters(allParams)"/>
		
			
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
<table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
     <tr>
       <td class="textR">
       <div style="float:left;margin-top:3px"><b>表名：<bean:write name="tableName" /> </b>&nbsp; 
			   		&nbsp;<b> 总数: <bean:write name="totalCount" /> </b></div>
	<b style="float:right;">
			   		<a  class="sbuBtnStyle" href='javascript:exportAllExcell();' ><strong><span class="addIcon">&nbsp;</span>导出</strong></a>
			   		</b> 
       </td>
     </tr>
 </table>
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" 
	       name="advancedQueryForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	       fixRows="true">
    <header style="" height="27"  />
<rooter height="30" width="100%" showType="none" style="font-size:9pt;"  textStyle="text-align:center;" />      	
</gw:grid2>
</html:form>
<html:form action="/dynamicquery/queryDetail" method="post" style="display:none">
<html:hidden property="action1" value="listbatch" />
<html:hidden property="tabId" />
<input type="hidden" name="idArr" />
</html:form>	
</div>
</div>

<div id="detail_div" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:2px;display: none;">
  	<iframe id="detail_frame" name="detail_frame" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
</div>
<div id="div_favor" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  	<iframe id="favorfrm" name="favorfrm" align="top" frameborder="0" scrolling="auto" width="100%" height="100%" ></iframe>
</div>

<div id="bottom_div" style="text-align:center;position:absolute;width:100%;bottom:10px;">
			      <gw:button name="btnAdd" onClick="goBack()">返回</gw:button>
</div>
<script>
var tabbar = new dhtmlXTabBar("div_main", "top");

tabbar.setSkin('dhx_skyblue');
tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
//tabbar.enableScroll(true);
tabbar.enableAutoReSize();
tabbar.addTab("d1", "记录汇总", "100px");
tabbar.setContent("d1", "div_genaral");
tabbar.addTab("d2", "详细");
tabbar.setContent("d2", "detail_div");
tabbar.addTab("favor","收藏详细页");
tabbar.setContent("favor","div_favor");
tabbar.setTabActive("d1");

tabbar.attachEvent("onSelect",function(id,pid){
    try{
		if(id="favor"){
			showFavorite();
			return true;
		}
	  }catch(e){
	  }finally{return true;}
  });
</script>

</body>
</html>