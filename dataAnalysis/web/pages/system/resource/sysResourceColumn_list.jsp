<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String fullurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
   <script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 
<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>

<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../system/ResourceColumn.do";	
      var fulllink = contextpath + "system/ResourceColumn.do";		

function goDel()  {
	  var id = findMultiSelected("ID","删除");
	  if(id == "") return;
	  var res = confirm("是否真的要删除?");
	  if(res == true) {
      document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
      document.forms[0].target = "_self";
      document.forms[0].submit();
    }
}

function moveUp(){
	var id = findSelected("ID","移动");
	if(id == "") return;
    document.forms[0].action = hyperlink + "?action=moveUp&record(id)=" + id;
    document.forms[0].target = "_self";
    document.forms[0].submit();
}

function moveDown(){
	var id = findSelected("ID","移动");
	if(id == "") return;
    document.forms[0].action = hyperlink + "?action=moveDown&record(id)=" + id;
    document.forms[0].target = "_self";
    document.forms[0].submit();
}

function goModify()  {
	var id = findSelected("ID","修改");
	var tableId = document.getElementsByName("query.parameters(tableid)")[0].value;
	if(id == "") return;
    var urlink=fulllink+'?action=TOEDIT&record(id)='+id+'&record(tableid)='+tableId;
    openWindow("修改字段",urlink,580,400);
}

function goAdd()  {
	var tableid = getElement("query.parameters(tableid)");
	openWindow("添加字段",fulllink+'?action=ADD&tableid='+tableid.value,580,400);
}

function goInitQueryCfg()  {
	var tableId = getElement("query.parameters(tableid)");
	var urlink=contextpath+'querycfg/columnSynthesisCfg.do?action=INITCFG&tableId='+tableId.value;
	openWindow("初始查询配置",urlink,700,350);
}

function renew()  {
	  var order = getElement("query.order");                  order.value="";
	  var desc =  getElement("query.orderDirection");         desc.value="";
	  var pn =    getElement("query.pageNumber");             pn.value="1";
	  var ps =    getElement("query.pageSize");               ps.value="10";
    gosearch();
}

function goQuery()  {
	  var pn = getElement("query.pageNumber");             
	  pn.value="1";
    	gosearch();
}

function closedialog(ret){
	closeWindow();
	gosearch();
	  }

var windows; 
var winName = "win";
function openWindow(title,url,w,h){ 
	windows = new dhtmlXWindows();
	windows.enableAutoViewport(true);
	windows.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
	windows.setSkin("dhx_skyblue");
	var win = windows.createWindow(winName,0,0,w,h);
	win.allowPark();
	win.setText(title);
	win.setModal(true);
	win.centerOnScreen();
	win.attachURL(url);
}
function closeWindow(){	
	windows.window(winName).close();
};

function init(){
	var value="<%= request.getAttribute("moveID")%>";
	var checkbox = document.getElementsByName("ID");
	for ( var i = 0; i < checkbox.length; i++) {
		if (checkbox[i].value==value) {
			checkbox[i].checked=true;
		}
	}
	showMessage('<bean:write name="resourceColumnForm" property="errorMessage" />');
}
</script>	
<style type="text/css">
   html, body {width:100%; height:100%; overflow:hidden;}
   
    .sbuBtnStyle strong span.upIcon {/* 增加 */
	background:url(../images/icon/arrow-up.gif) no-repeat;
	}
	.sbuBtnStyle strong span.downIcon {/* 增加 */
	background:url(../images/icon/arrow-down.gif) no-repeat;
	}
</style>  
</head>

<body onload="init()">
<div class="special-padding"> 
<html:form action="/system/ResourceColumn.do" method="post">
 				<table  cellspacing="1" cellpadding="1"  class="controlTable">
                <tr >
                  <td>
                  <gw:button styleClass="sbuBtnStyle" code="" icon="addIcon" onClick="goAdd()">增加</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="subIcon" onClick="goModify()">修改</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="delIcon" onClick="goDel()">删除</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="refIcon" onClick="gosearch()">刷新</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="openIcon" onClick="goInitQueryCfg()" >初始查询配置</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="upIcon" onClick="moveUp()" align="right" >上移</gw:button>
			      <gw:button styleClass="sbuBtnStyle" code="" icon="downIcon" onClick="moveDown()" align="right" >下移</gw:button>
                  </td>
                </tr>               
				</table>
              <!-- 这里是查询条件，资源库列表传过来 -->
              <html:hidden  property="query.parameters(tableid)" />            
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" 
	       name="resourceColumnForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	       fixRows="true" >
    <header style="" height="27"  />
    <column width="3%" itemType="checkbox" property="ID" selectAll="true" styleClass="td_LeftTop"  headerStyleClass="td_LeftTop" />
    <column width="22%" name="名称" property="CN_NAME" align="center" 
 			headerOnMouseOut="headerOut(this)"
			headerOnClick="query('CN_NAME')"
			headerOnMouseOver="headerOver(this)"/>
    <column width="20%" name="英文名" property="NAME" align="center" 
			headerOnMouseOut="headerOut(this)"
			headerOnClick="query('NAME')"
			headerOnMouseOver="headerOver(this)"/>
    <column width="15%" name="数据类型" property="DATA_TYPE" align="center" />
    <column width="20%" name="默认值" property="DEFAULT_VALUE" align="center" 
    	         />
    <column width="10%" name="安全等级" property="SECURITY_LEVEL" align="center" 
    	    headerOnMouseOut="headerOut(this)"
			headerOnClick="query('NAME')"
			headerOnMouseOver="headerOver(this)"/>
	<column width="10%" name="顺序号" property="SQUENCE_NO" align="center" 
    	    headerOnMouseOut="headerOut(this)"
			headerOnClick="query('SQUENCE_NO')"
			headerOnMouseOver="headerOver(this)"/>
    <rooter height="30" width="100%" showType="all" />      
</gw:grid2>		
</html:form>
</div>
</body>
</html>