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
		var hyperlink = "../datastandard/indicator.do";
		var fulllink = contextpath + "datastandard/indicator.do";

		function addindicator(categoryid)  {
			if (categoryid == "root") categoryid = "";
			editMode = "ADD";
			openWindow('添加指标', fulllink+'?action=ADD&categoryid='+categoryid, 700, 400);
		}

		function modifyindicator()  {
			var id = dhxLayout.cells("b")._frame.contentWindow.getModifyId();
			if(id == "") return;
			editMode = "EDIT";
			openWindow("修改指标", fulllink+'?action=EDIT&id='+id, 700, 400);
		}

		function deleteindicator()  {
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
    	leftPanel.setWidth(200);
    	rightPanel.hideHeader();
    	rightPanel.attachURL("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlx/imgs/blank.html");

		var toolbar = dhxLayout.attachToolbar();
		toolbar.setIconsPath("<%= CONTEXT_PATH%>images/icon/");
		toolbar.setSkin("dhx_skyblue");
		toolbar.addButton("new_indicator", 0, "添加指标", "build_all_1.gif", "build_all_1.gif");
		toolbar.addButton("modify_indicator", 1, "修改指标", "build_all_2.gif", "build_all_2.gif");
		toolbar.addButton("del_indicator", 2, "删除指标", "build_all_3.gif", "build_all_3.gif");
		toolbar.disableItem("modify_indicator");
		toolbar.disableItem("del_indicator");
		toolbar.attachEvent("onClick", function(id){
			if(id=="new_indicator") {
				var nodeId=tree.getSelectedItemId();
				addindicator(nodeId);
			}
			else if(id=="modify_indicator") modifyindicator();
			else if(id=="del_indicator") deleteindicator();
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

		tree.setXMLAutoLoading("<%= CONTEXT_PATH%>datastandard/indicator.do?action=getTree");
		tree.loadXML("<%= CONTEXT_PATH%>datastandard/indicator.do?action=getTree");


		function closedialog(){
			dhxWins.window(winName).close();
			var id = tree.getSelectedItemId();
			reSearch(id);
			editMode = "";
			return id;
		}

		function reSearch(id){
			dhxLayout.cells("b")._frame.contentWindow.renew(id);
		}
		
		function viewDataMeta(id) {
			toolbar.enableItem("modify_indicator");
			toolbar.enableItem("del_indicator");
			dhxLayout.cells("b").attachURL(fulllink+"?indicatorId="+id+"&action=LIST");			
		}
	</script>
</body>
</html>