<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>广东省信息所（数据应用系统）</title>
	<meta http-equiv="x-ua-compatible" content="ie=7" /> 

	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
   <script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
   
   <script language="javascript" src="<%= CONTEXT_PATH%>js/date_validate.js"></script>	
	<script language="javascript" src="<%= CONTEXT_PATH%>js/ctrl_util.js"></script>

	<link rel="stylesheet" type="text/css" href="<%= CONTEXT_PATH%>/css/main.css" />

	<style type="text/css">
		html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
	</style>
  </head>
  
  <body>
	<div id="div_main" style="position:relative;top:0;left:0;width:100%;height:100%;"></div>
	<div id="div_left" style="position:relative;background-color:#e8f0f8;top:0;left:0;width:100%;height:100%"></div>
	<div id="div_right" style="position:relative;top:0;left:0;width:100%;height:100%;">
		<div id="div_grid" style="position:relative;top:0;left:0;width:100%;height:99%;overflow:auto;"></div>
	</div>

<%@include file="/common/dialog1.jsp" %>
	<script type="text/javascript">

		function closedialog(ret){
			dhxWins.window(winName).close();
			if(ret=='true') {
				var curId = tree.getSelectedItemId();
				if(editMode=="ADD") {
					tree.refreshItem(curId);
				}
				else if(editMode=="EDIT"){
					var parentId = tree.getParentId(curId);
					tree.refreshItem(parentId);
					tree.selectItem(curId,false,"");
				}
				else if(editMode=="DELETE"){
					var parentId = tree.getParentId(curId);
					tree.refreshItem(parentId);
				}
			}
			editMode="";	
		}

		function addCategory(nodeId){
			if (nodeId!=""){
				editMode="ADD";
				var url_link="<%= CONTEXT_PATH%>datastandard/standardCategory.do?action="+editMode+"&pid="+nodeId;
				openWindow("增加分类",url_link,700,400);
			}
		}

		function modifyCategory(nodeId){
			if (nodeId!="root") {
				if (nodeId==""||nodeId==null) {
					alert("请选择分类！");
					return;
				}
				editMode="EDIT";
				var url_link="<%= CONTEXT_PATH%>datastandard/standardCategory.do?action="+editMode+"&id="+nodeId;
				openWindow("修改分类",url_link,700,400);
			}
		}

		function deleteCategory(nodeId){
			if (nodeId!="root") {
				var res=confirm("真的要删除该分类？");
				if(res==false) return;
				editMode="DELETE";
				var url_link="<%= CONTEXT_PATH%>datastandard/standardCategory.do?action="+editMode+"&id="+nodeId;
				var loader = dhtmlxAjax.postSync(url_link,"");
				var ret=loader.xmlDoc.responseText;
				var obj = eval('(' + ret + ')');
				if(obj.exit=="0"){
				   tree.deleteItem(nodeId);
				}
				else{
				   showMessage(obj.message);
				} 
	    		editMode="";
	    	}
		}

		function setButtonVisible(id){
			toolbar.disableItem("new_Category");
			toolbar.disableItem("modify_Category");
			toolbar.disableItem("del_Category");
			if (id=="root") {
				toolbar.enableItem("new_Category");
				toolbar.disableItem("modify_Category");
				toolbar.disableItem("del_Category");
			}
			else if (id!="") {
				toolbar.enableItem("new_Category");
				toolbar.enableItem("modify_Category");
				toolbar.enableItem("del_Category");
			}
		}

		var dhxLayout = new dhtmlXLayoutObject("div_main", "1C");
		var toolbar = dhxLayout.attachToolbar();
		toolbar.setIconsPath("<%= CONTEXT_PATH%>images/icon/");
		toolbar.addButton("new_Category", 0, "添加分类", "home--plus.gif", "home--plus.gif");
		toolbar.addButton("modify_Category", 1, "修改分类", "home--pencil.gif", "home--pencil.gif");
		toolbar.addButton("del_Category", 2, "删除分类", "home--minus.gif", "home--minus.gif");
		toolbar.attachEvent("onClick", function(id){
			var nodeId=tree.getSelectedItemId();
			if(id=="new_Category") addCategory(nodeId);
			else if(id=="modify_Category") modifyCategory(nodeId);
			else if(id=="del_Category") deleteCategory(nodeId);
    	});
    	//dhxLayout.hideToolbar();
    	toolbar.disableItem("new_Category");
    	toolbar.disableItem("modify_Category");
    	toolbar.disableItem("del_Category");
    	
    	dhxLayout.setSkin("dhx_skyblue");
    	var leftPanel=dhxLayout.cells("a");
//    	var rightPanel=dhxLayout.cells("b");
    	leftPanel.hideHeader();
    	leftPanel.attachObject("div_left");
    	leftPanel.setWidth(250);
//    	rightPanel.hideHeader();
//    	rightPanel.attachObject("div_right");

		var tree = new dhtmlXTreeObject("div_left","100%","100%",0);
		tree.setImagePath("<%= CONTEXT_PATH%>dhtmlxSuite/dhtmlx/imgs/csh_bluefolders/");
		tree.setSkin("dhx_blue");
		tree.attachEvent("onSelect", function(id){
			setButtonVisible(id);
		});
		tree.attachEvent("onDblClick", function(id){
			modifyCategory(id);
		});
		tree.attachEvent("onMouseIn", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,"<font color='#FF4422'>"+str+"</font>");
		});
		tree.attachEvent("onMouseOut", function(id){
			var str=tree.getUserData(id,"name");
			tree.setItemText(id,str);
		});

		tree.setXMLAutoLoading("<%= CONTEXT_PATH%>datastandard/standardCategory.do?action=getTree");
		tree.loadXML("<%= CONTEXT_PATH%>datastandard/standardCategory.do?action=getTree");

		function refreshItem(id){
			tree.refreshItem(0);
		}

		function showMessage(msg){
			var win = dhxWins.createWindow(winName,0,0,200,120);
			win.centerOnScreen();
			win.denyPark();
			win.denyMove();
			win.denyResize();
			win.button("minmax1").hide();
			win.button("park").hide();
			win.setText("确定");
			win.setModal(true);
			win.denyMove();
			win.denyResize();
			var div = "<div><p align='center'>" + msg + "</p><p align='center'><input type=button onclick='closeMessage()' value='确定'/></p></div>";
			win.attachHTMLString(div);
		}

		function closeMessage(){
			dhxWins.window(winName).close();
		}
	</script>
  </body>
</html>