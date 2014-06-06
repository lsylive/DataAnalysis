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
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css">
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 
<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../dynamicquery/advancedquery.do";	
      var fulllink = contextpath + "dynamicquery/advancedquery.do";		
      
function view(id) {
	 dialog_open(fulllink + "?action=view&id=" + id,'700','300');
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

function goQuery()  {
	  var pn = getElement("query.pageNumber");             
	  pn.value="1";
    	gosearch();
}

function goAdvancedQuery(queryType){
	var tableid = findSelected("ID","查询");
	var urlink = fulllink + "?action=BASEQUERY&queryType="+queryType+"&tableId=" + tableid;
	if (tableid!=""&&tableid!=null) {
		window.location.href=urlink;
		//openWindow("高级查询",urlink,1024,768);
	}	
}

function closedialog(ret){
       dialog_close();
       if(ret=='true') {
       	 gosearch();
       }	
}
function getCategorys(){
	//var trade = document.getElementsByName("query.parameters(tradeId)")[0];
	//var tradeId = trade.options[trade.selectedIndex].value;
	//var category = document.getElementsByName("query.parameters(categoryId)")[0];

	//category.length=1;
	//var url = fulllink+"?action=SELECT&tradeId="+tradeId;
	//dhtmlxAjax.get(url,function(loader){
		//var options = loader.xmlDoc.responseXML.getElementsByTagName("option");
		//for ( var i = 0; i < options.length; i++) {
			//var name = options[i].childNodes[0].firstChild.nodeValue;
			//var value = options[i].childNodes[1].firstChild.nodeValue;
			// var option=new Option(name,value);
	        // try{
	        	// category.options.add(option);
	         //}catch(e){
	         	//alert(e);
	        // }
	    //}
	//});	

	goQuery();
}


var windows; 
var winName = "win";
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
</script>	
   
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>  
</head>

<body onload="showMessage('<bean:write name="advancedQueryForm" property="errorMessage" />')" >
<div class="special-padding">   
<html:form action="/dynamicquery/advancedquery.do" method="post">
<html:hidden property="query.parameters(cityCode)"/>
<html:hidden property="query.parameters(tradeId)"/>
<html:hidden property="query.parameters(categoryId)"/>
       	  	  <table  cellspacing="1" cellpadding="1"  class="controlTable">
                <tr >
                  <td>
                  <gw:button styleClass="sbuBtnStyle" code="" icon="refIcon" onClick="goAdvancedQuery('base')">查询</gw:button>
                  </td>
                </tr>               
				</table>
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" 
	       name="advancedQueryForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	       fixRows="true">
    <header height="27"  />
    <column width="3%" itemType="checkbox" property="ID" />
    <column width="15%" name="名称" property="CN_NAME" align="center" 
				 headerOnClick="query('CN_NAME')"
				 headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)" 
    />
    <column width="15%" name="地市" property="CITYNAME" 
    	         headerOnClick="query('cityname')"
				 headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)" 
    />
    <column width="15%" name="行业" property="TRADENAME" 
    	         headerOnClick="query('parentId')" 
                 headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)" 
    />
   	<column width="15%" name="分类" property="CATEGORYNAME" 
    	         headerOnClick="query('categoryname')" 
				 headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)" 
    />
   	<column width="15%" name="类型" property="TYPE" 
    	         headerOnClick="query('type')"
				 headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)" 
    />
    <column width="22%" name="说明" property="REMARK" align="center" 
				 headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)" 
	/>
    <rooter height="30" width="100%" showType="all" style="font-size:9pt;"  textStyle="text-align:center;" />      	
</gw:grid2>
</html:form>
</div>
</body>
</html>