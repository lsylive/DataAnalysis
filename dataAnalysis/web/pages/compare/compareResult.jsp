<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>广东省信息所（数据应用系统）</title>

			<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
	<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>/css/main.css" />

		<script type="text/javascript">
		var contextpath = "<%=CONTEXT_PATH%>";
		var hyperlink = "../compare/compareResultAction.do";
		var fulllink = contextpath + "compare/compareResultAction.do";

		function addClass()  {
			openWindow('添加分类', fulllink+'?action=ADD', 700, 220);
		}

		function modifyClass(id)  {
			if(id == "") return;
			openWindow("修改分类", fulllink+'?action=EDIT&id='+id, 700, 220);
		}

		function viewMainRecord(id) {
			var innerDoc;
			if (_isIE) {
				innerDoc = dhxLayout.cells("b")._frame.contentWindow.document;
			}else{
				innerDoc = dhxLayout.cells("b")._frame.contentDocument;
			}
			if (id.indexOf(":")>-1)
			{
				
				var runIdAndTableName = id.split(":");
				innerDoc.getElementsByName("viewType")[0].value="0";
				innerDoc.getElementsByName("query.parameters(tableId)")[0].value=runIdAndTableName[0];
				innerDoc.getElementsByName("runId")[0].value=runIdAndTableName[1];
				innerDoc.getElementsByName("tableEnName")[0].value=runIdAndTableName[2];
				innerDoc.forms[0].submit();
				
				rightDownPanel.attachURL("<%=CONTEXT_PATH%>/compare/compareResultRecord.do?action=BASEQUERY");
			}
			
		}
		
		function showMenuItems(id){
			if (id == "root")
			{
				toolbar.hideItem("del_runinfo");
				toolbar.hideItem("del_result");
			}
			else
			{
				if (id.indexOf("RUN") > -1)
				{
					toolbar.showItem("del_runinfo");
					toolbar.hideItem("del_result");
				}
				if (id.indexOf(":") > -1)
				{
					toolbar.hideItem("del_runinfo");
					toolbar.showItem("del_result");
				}
				
			}
		
		}
		
		function deleteItem(id)  {
				var runIdAndTableName = "";
				var url_link = "";
				if (id.indexOf("RUN") > -1)
				{
					var res=confirm("删除运行记录会一并删除该运行记录下所有的对比结果，真的要删除该运行记录？");
					if(res==false) return;
					var runId = id.substring(3);
					url_link="<%=CONTEXT_PATH%>/compare/compareResult.do?action=DELETERUN&runId="+runId;
				}
				else if (id.indexOf(":") > -1)
				{
					var res=confirm("真的要删除该对比结果？");
					if(res==false) return;
					runIdAndTableName = id.split(":");
					url_link="<%=CONTEXT_PATH%>/compare/compareResult.do?action=DELETERESULT&runId="+runIdAndTableName[1]+"&tableEnName="+runIdAndTableName[2];
				}
				
				var loader = dhtmlxAjax.postSync(url_link,"");
				var ret=loader.xmlDoc.responseText;
				var obj = eval('(' + ret + ')');
				if(obj.exit=="0"){
				   tree.deleteItem(id);
				   tree.refreshItem(0);
				   rightTopPanel.attachURL("<%=CONTEXT_PATH%>/compare/compareResultRecord.do?action=BASEQUERY");
				   rightDownPanel.attachURL("<%=CONTEXT_PATH%>/compare/compareResultRecord.do?action=BASEQUERY");
				}
				else{
				   showMessage(obj.message);
				} 	    		
		}
		
				function showMessage(msg){
			var win = dhxWins.createWindow(winName,0,0,200,120);
			win.denyPark();
			win.denyMove();
			win.denyResize();
			win.button("minmax1").hide();
			win.button("park").hide();
			win.setText("确定");
			win.setModal(true);
			win.denyMove();
			win.denyResize();
			win.centerOnScreen();
			var div = "<div><p align='center'>" + msg + "</p><p align='center'><input type=button onclick='closeMessage()' value='确定'/></p></div>";
			win.attachHTMLString(div);
		}

		function closeMessage(){
			dhxWins.window(winName).close();
		}
		

		
		function viewSlave(runId,mainPkId,slaveTableEnName)
		{
			var innerDoc;
			if (_isIE) {
				innerDoc = dhxLayout.cells("c")._frame.contentWindow.document;
			}else{
				innerDoc = dhxLayout.cells("c")._frame.contentDocument;
			}
			innerDoc.getElementsByName("viewType")[0].value="1";
			innerDoc.getElementsByName("mainPkId")[0].value = mainPkId;
    		innerDoc.getElementsByName("runId")[0].value = runId;
			innerDoc.getElementsByName("tableEnName")[0].value = slaveTableEnName;
			innerDoc.getElementsByName("query.parameters(tableId)")[0].value="";
			innerDoc.forms[0].submit();
		}
		


	</script>
	</head>

	<body>
		<div id="div_tree"
			style="background-color: #e8f0f8; height: 100%; width: 100%;"></div>

		<script type="text/javascript">

		var dhxLayout = new dhtmlXLayoutObject(document.body, "3L");
    	dhxLayout.setSkin("dhx_skyblue");
    	dhxLayout.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/");
    	var leftPanel=dhxLayout.cells("a");
    	var rightTopPanel=dhxLayout.cells("b");
    	var rightDownPanel = dhxLayout.cells("c");
    	leftPanel.hideHeader();
    	leftPanel.attachObject("div_tree");
    	leftPanel.setWidth(250);
    	rightTopPanel.hideHeader();
    	rightTopPanel.attachURL("<%=CONTEXT_PATH%>/compare/compareResultRecord.do?action=BASEQUERY");
    	
		rightDownPanel.hideHeader();
		rightDownPanel.attachURL("<%=CONTEXT_PATH%>/compare/compareResultRecord.do?action=BASEQUERY");
		
		var toolbar = dhxLayout.attachToolbar();
		toolbar.setIconsPath("<%=CONTEXT_PATH%>images/icon/");
		toolbar.addButton("del_runinfo", 0, "删除运行记录", "folder_3.gif", "folder_3.gif");
		toolbar.addButton("del_result", 0, "删除对比结果", "folder_3.gif", "folder_3.gif");
		toolbar.hideItem("del_runinfo");
		toolbar.hideItem("del_result");
		toolbar.attachEvent("onClick", function(id){
			var nodeId=tree.getSelectedItemId();
			deleteItem(tree.getSelectedItemId());
    	});

		var tree = new dhtmlXTreeObject("div_tree","100%","100%",0);
		tree.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/imgs/");
		
		tree.attachEvent("onSelect", function(id){
    	 showMenuItems(id);
    });
		tree.attachEvent("onMouseIn", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,"<B style='color:red'>"+str+"</B>");
		});
		tree.attachEvent("onMouseOut", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,str);
		});
		tree.attachEvent("onClick", function(id){
			viewMainRecord(id);
		});
		

		tree.setXMLAutoLoading("<%=CONTEXT_PATH%>compare/compareResult.do?action=getTree");
		tree.loadXML("<%=CONTEXT_PATH%>compare/compareResult.do?action=getTree");

		var dhxWins = dhxLayout.dhxWins;
		var winName="winCodeset";   //打开窗口名称
	    var editMode;       		//页面编辑状态：ADD,EDIT,DELETE
		dhxWins.enableAutoViewport(true);
		dhxWins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");

		function openWindow(title,urllink,w,h){
			var w = dhxWins.createWindow(winName, 0, 0, w, h);
			w.setText(title);
			w.keepInViewport(true);
			w.setModal(true);
			w.centerOnScreen();
			w.button("minmax1").hide();
			w.button("minmax2").hide();
			w.button("park").hide();
			w.attachURL(urllink);
			return w;    
	    }

		function closedialog(){
			dhxWins.window(winName).close();
			tree.refreshItem(0);
		}

	</script>
	</body>
</html>