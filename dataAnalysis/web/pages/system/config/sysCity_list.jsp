<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String msg = (String)request.getAttribute("msg");
	if (msg == null) msg = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css"/>
	<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

	<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

	<script language="javascript"> 
      var contextpath = "<%=CONTEXT_PATH%>";
      var hyperlink = "../system/sysCity.do";
      var fulllink = contextpath + "system/sysCity.do";

		function view(id) {
			editMode = "VIEW";
			openWindow("查看地市详情", fulllink + "?action=view&id=" + id, 700, 300);
		}

		function goDel() {
			var id = findMultiSelected("id","删除");
			if(id == "") return;
			var res = confirm("是否真的要删除?");
			if(res == true) {
				document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
				document.forms[0].target = "_self";
				document.forms[0].submit();
			}
		}

		function goModify() {
			var id = findSelected("ID","修改");
			if(id == "") return;
		    var urlink=fulllink+'?action=EDIT&id='+id;
		    editMode = "EDIT";
			openWindow("修改地市", urlink, 600, 300);
		}

		function goAdd() {
			editMode = "ADD"
			openWindow("添加地市", fulllink+'?action=ADD', 600,300);
		}

		function renew() {
			var order = getElement("query.order");                  order.value="";
			var desc =  getElement("query.orderDirection");         desc.value="";
			var pn =    getElement("query.pageNumber");             pn.value="1";
			var ps =    getElement("query.pageSize");               ps.value="10";
			var v0 =    getElement("query.parameters(code)");       v0.value="";
			var v1 =    getElement("query.parameters(name)");       v1.value="";
    		gosearch();
		}

		function goQuery() {
			var pn = getElement("query.pageNumber");             
			pn.value="1";
			gosearch();
		}

		function closedialog(ret) {
			dhxWins.window(winName).close();
			if(ret=='true') {
				if(editMode=="VIEW") {}
				else gosearch();
			}
			editMode="";
		}
		var msg = "<%= msg%>";
		if (msg != "")
			alert(msg);
	</script>	
   
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body onload="showMessage('<bean:write name="sysCityForm" property="errorMessage" />')">
<div class="special-padding"> 
<html:form action="/system/sysCity.do" method="post" >
<table class="controlTable" cellspacing="1" cellpadding="1">
	<tr>
		<td valign="top" align="left">
			<gw:button styleClass="sbuBtnStyle" icon="addIcon" onClick="goAdd()">增加</gw:button>
			<gw:button styleClass="sbuBtnStyle" icon="subIcon" onClick="goModify()">修改</gw:button>
			<gw:button styleClass="sbuBtnStyle" icon="delIcon" onClick="goDel()">删除</gw:button>
			<gw:button styleClass="sbuBtnStyle" icon="refIcon" onClick="gosearch()">刷新</gw:button>
		</td>
	</tr>
</table>
				<html:hidden property="query.order" />
				<html:hidden property="query.orderDirection" />
				<html:hidden property="query.pageNumber" />
				<html:hidden property="query.recordCount"/>
				<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
		name="sysCityForm" property="query.recordSet" parameters="query" rowEventHandle="false"
		fixRows="true">
    <header style="" height="27" />
    <column width="3%" itemType="checkbox" property="ID" selectAll="true"/>
    <column width="6%" name="排序号" property="SQUENCE_NO" align="center"
    			headerOnClick="query('squence_No')"
    			headerOnMouseOver="headerOver(this)"
    			headerOnMouseOut="headerOut(this)" />
    <column width="15%" name="地市代码" property="CODE" align="center" 
				headerOnClick="query('code')"
				headerOnMouseOver="headerOver(this)"
				headerOnMouseOut="headerOut(this)" />
    <column width="25%" name="地市名" property="NAME"
				headerOnMouseOut="headerOut(this)"
				headerOnClick="query('name')"
				headerOnMouseOver="headerOver(this)" />
    <column width="50%" name="备注" property="REMARK" />
    <rooter height="30" width="100%" showType="all" />      
</gw:grid2>		
</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>