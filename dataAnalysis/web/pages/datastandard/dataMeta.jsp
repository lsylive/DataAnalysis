<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	
	<title>广东省信息所（数据应用系统）</title>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
   <script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>/css/main.css" />
<script language="javascript" src="<%= CONTEXT_PATH%>js/date_validate.js"></script>	
	<script language="javascript" src="<%= CONTEXT_PATH%>js/ctrl_util.js"></script>

	<script type="text/javascript">
		var contextpath = "<%= CONTEXT_PATH%>";
		var hyperlink = "../datastandard/dataMeta.do";
		var fulllink = contextpath + "datastandard/dataMeta.do";

		function adddatameta(categoryid) {
			if (categoryid == "root") categoryid = "";
			openWindow('添加数据元', fulllink+'?action=ADD&categoryid='+categoryid, 700, 400);
		}

		function modifydatameta() {
			var id = dhxLayout.cells("b")._frame.contentWindow.getModifyId();
			if(id == "") return;
			openWindow("修改数据元", fulllink+'?action=EDIT&id='+id, 700, 400);
		}

		function deletedatameta() {
			var id = dhxLayout.cells("b")._frame.contentWindow.getDeleteId();
			if(id == "") return;
			var res = confirm("是否真的要删除?");
			if(res == true) {
				var innerDoc;
				if (_isIE) {
					innerDoc = dhxLayout.cells("b")._frame.contentWindow.document;
				} else {
					innerDoc = dhxLayout.cells("b")._frame.contentDocument;
				}
				innerDoc.forms[0].action = hyperlink + "?action=delete&ids=" + id;
				innerDoc.forms[0].target = "_self";
				innerDoc.forms[0].submit();
			}
		}

		function viewDataMeta(id) {
			dhxLayout.cells("b").attachURL(fulllink+"?categoryid="+id+"&action=LIST");
			toolbar.enableItem("modify_datameta");
			toolbar.enableItem("del_datameta");
		}
	</script>
	<style type="text/css">
<!--
html, body {
        width: 100%;
        height: 100%;
        margin: 0px;
        overflow: hidden;
}

-->
</style>
  </head>
<body>
	<div id="div_tree" style="background-color:#e8f0f8;height:100%;width:100%;"></div>

	<%@include file="/common/dialog1.jsp" %>
	<script type="text/javascript">

		var dhxLayout = new dhtmlXLayoutObject(document.body, "2U");
    	dhxLayout.setSkin("dhx_skyblue");
    	dhxLayout.setImagePath("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/");
    	var leftPanel=dhxLayout.cells("a");
    	var rightPanel=dhxLayout.cells("b");
    	leftPanel.hideHeader();
    	leftPanel.attachObject("div_tree");
    	leftPanel.setWidth(160);
    	rightPanel.hideHeader();
    	rightPanel.attachURL("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlx/imgs/blank.html");

		var toolbar = dhxLayout.attachToolbar();
		toolbar.setIconsPath("<%= CONTEXT_PATH%>images/icon/");
		toolbar.setSkin("dhx_skyblue");
		toolbar.addButton("new_datameta", 0, "添加数据元", "build_child_1.gif", "build_child_1.gif");
		toolbar.addButton("modify_datameta", 1, "修改数据元", "build_child_2.gif", "build_child_2.gif");
		toolbar.addButton("del_datameta", 2, "删除数据元", "build_child_3.gif", "build_child_3.gif");
		toolbar.disableItem("modify_datameta");
		toolbar.disableItem("del_datameta");
		toolbar.attachEvent("onClick", function(id){
			if(id=="new_datameta") {
				var nodeId=tree.getSelectedItemId();
				adddatameta(nodeId);
			}
			else if(id=="modify_datameta") modifydatameta();
			else if(id=="del_datameta") deletedatameta();
    	});

		var tree = new dhtmlXTreeObject("div_tree","100%","100%",0);
		tree.setImagePath("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlx/imgs/csh_bluefolders/");
		tree.setSkin("dhx_blue");
		tree.attachEvent("onMouseIn", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,"<font color='#FF4422'>"+str+"</font>");
		});
		tree.attachEvent("onMouseOut", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,str);
		});
		tree.attachEvent("onClick", function(id){
			viewDataMeta(id);
		});

		tree.setXMLAutoLoading("<%= CONTEXT_PATH%>datastandard/dataMeta.do?action=getTree");
		tree.loadXML("<%= CONTEXT_PATH%>datastandard/dataMeta.do?action=getTree");


		function closedialog(){
			dhxWins.window(winName).close();
			var id = tree.getSelectedItemId();
			reSearch(id);
			return id;
		}

		function reSearch(id){
			dhxLayout.cells("b")._frame.contentWindow.renew(id);
		}
	</script>
</body>
</html>