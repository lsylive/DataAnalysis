<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>广东省信息所（数据应用系统）</title>

	<script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
	<script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
	<script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>

	<script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"/>

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css"/>
	<script src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.js"></script>

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css">
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">
	<script type="text/javascript" src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>

	<script  src="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css">

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>/css/main.css" />

	<script type="text/javascript">
		var contextpath = "<%= CONTEXT_PATH%>";
		var hyperlink = "../datastandard/standardCodeset.do";
		var fulllink = contextpath + "datastandard/standardCodeset.do";

		function addcodeset(categoryid) {
			editMode = "ADD";
			openWindow('添加代码集', fulllink+'?action=ADD&categoryid='+categoryid, 700, 400);
		}

		function modifycodeset() {
			var id = dhxLayout.cells("b")._frame.contentWindow.getModifyId();
			if(id == "") return;
			editMode = "EDIT";
			var urlcodeedit = contextpath+"datastandard/standardCode.do"+"?action=LIST&codesetid="+id;
			var rulcodesetedit=fulllink+'?action=EDIT&record(id)='+id;
			openEditWindow("修改代码集", rulcodesetedit, urlcodeedit, 800, 450);
		}

		function deletecodeset() {
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

		function view() {
			var id = dhxLayout.cells("b")._frame.contentWindow.getModifyId();
			if(id == "") return;
			var urllink = contextpath+"datastandard/standardCode.do"+"?action=LIST&codesetid="+id;
			editMode = "VIEW";
			openWindow('浏览代码',  urllink, 800, 450);
		}

		function viewCodesetGrid(id){
			var innerdoc;
			if (_isIE) {
				innerdoc = dhxLayout.cells("b")._frame.contentWindow.document;
        	} else {
        		innerdoc = dhxLayout.cells("b")._frame.contentDocument;
        	}
        	var ids = innerdoc.getElementsByName("query.parameters(treeid)");
        	if (ids.length != 0) {
				var value = ids[0].value=id;
				innerdoc.forms[0].submit();
			}
		}
	</script>
  </head>

<body>
	<div id="div_left" style="background-color:#e8f0f8;height:100%;width:100%;"></div>

	<%@include file="/common/dialog1.jsp" %>
	<script type="text/javascript">

		var dhxLayout = new dhtmlXLayoutObject(document.body, "2U");
    	dhxLayout.setSkin("dhx_skyblue");
    	dhxLayout.setImagePath("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/");
    	var leftPanel=dhxLayout.cells("a");
    	var rightPanel=dhxLayout.cells("b");
    	leftPanel.hideHeader();
    	leftPanel.attachObject("div_left");
    	leftPanel.setWidth(250);
    	rightPanel.hideHeader();
    	rightPanel.attachURL("<%= CONTEXT_PATH%>datastandard/standardCodeset.do");

		var toolbar = dhxLayout.attachToolbar();
		toolbar.setIconsPath("<%= CONTEXT_PATH%>images/icon/");
		toolbar.addButton("new_codeset", 0, "添加代码集", "home--plus.gif", "home--plus.gif");
		toolbar.addButton("modify_codeset", 1, "修改代码集", "home--pencil.gif", "home--pencil.gif");
		toolbar.addButton("del_codeset", 2, "删除代码集", "home--minus.gif", "home--minus.gif");
		toolbar.addButton("viewcodes", 3, "浏览代码", "door-open.gif", "door-open.gif");
		toolbar.attachEvent("onClick", function(id){
			if(id=="new_codeset") {
				var nodeId=tree.getSelectedItemId();
				addcodeset(nodeId);
			}
			else if(id=="modify_codeset") modifycodeset();
			else if(id=="del_codeset") deletecodeset();
			else if(id=="viewcodes") view();
		});

		var tree = new dhtmlXTreeObject("div_left","100%","100%",0);
		tree.setImagePath("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/imgs/");
		tree.attachEvent("onMouseIn", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,"<B style='color:red'>"+str+"</B>");
		});
		tree.attachEvent("onMouseOut", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,str);
		});
		tree.attachEvent("onClick", function(id){
			viewCodesetGrid(id);
		});
		tree.attachEvent("onXLE", function(tree,id){
			var ids = tree.getSubItems(id);
			if (ids == "")
				tree.setItemImage(id, "folderClosed.gif");
		});

		tree.setXMLAutoLoading("<%= CONTEXT_PATH%>datastandard/standardCodeset.do?action=getTree");
		tree.loadXML("<%= CONTEXT_PATH%>datastandard/standardCodeset.do?action=getTree");

		function openEditWindow(title,rulcodesetedit,urlcodeedit,w,h){
			var w = dhxWins.createWindow(winName, 0, 0, w, h);
			w.setText(title);
			w.keepInViewport(true);
			w.setModal(true);
			w.centerOnScreen();
			w.button("minmax1").hide();
			w.button("minmax2").hide();
			w.button("park").hide();
			w.button("close").attachEvent("onClick", function(){closedialog();});
			var tabbar = w.attachTabbar();
			tabbar.setSkin('dhx_skyblue');
			tabbar.setImagePath("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
			tabbar.addTab("a1", "代码集信息", "100px");
			tabbar.addTab("a2", "代码列表", "100px");
			tabbar.setHrefMode("iframes-on-demand");
			tabbar.setContentHref("a1", rulcodesetedit);
			tabbar.setContentHref("a2", urlcodeedit);
			tabbar.setTabActive("a1");
			return w;
		}

		function closedialog(){
			dhxWins.window(winName).close();
			var id = tree.getSelectedItemId();
			if (editMode != "VIEW")
				reSearch(id);
			editMode = "";
			return id;
		}

		function reSearch(id){
			dhxLayout.cells("b")._frame.contentWindow.renew(id);
		}
	</script>
</body>
</html>