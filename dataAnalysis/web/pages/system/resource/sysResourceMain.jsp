<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String fullurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源目录管理</title>
<link rel="stylesheet" type="text/css" href="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css" href="<%=fullurl%>/css/main.css" />
<script>

var contextpath = "<%=fullurl%>";	
var hyperlink = "../system/ResourceTable.do";	
var fulllink = contextpath + "/system/ResourceTable.do";
function tonclick(id){
		var value = id;
		if(value!="province"){					
		dhxLayout.cells("b").attachURL(fulllink+"?treeid="+id+"&action="+"LIST");
		}else{
			dhxLayout.cells("b").attachURL("<%=fullurl%>/dhtmlxSuite/dhtmlx/imgs/blank.html");
		}
		showMenuItems(id);
};
function tonopen(id,mode){
	return confirm("Do you want to "+(mode>0?"close":"open")+" node "+tree.getItemText(id)+"?");
};
function goAdd()  {
	var nodeId = tree.getSelectedItemId();
	openWindow("添加资源库",fulllink+'?action=ADD&nodeId='+nodeId,800,500);
}
function goModify(){
var id = dhxLayout.cells("b")._frame.contentWindow.getModifyId();
if (id=="") {
return;
}
var urlink=fulllink+'?action=TOEDIT&tableid='+id;
openWindow("修改资源库",urlink,800,540);
};

function goDelete(){
var ids = dhxLayout.cells("b")._frame.contentWindow.getDelIds();
if (ids=="") {
return;
}	
var res = confirm("是否真的要删除?");
if(res == true) {
var innerdoc;
if (_isIE) {
	innerdoc = dhxLayout.cells("b")._frame.contentWindow.document;
} else {
	innerdoc = dhxLayout.cells("b")._frame.contentDocument;
}
innerdoc.forms[0].action = hyperlink + "?action=delete&ids=" + ids;
innerdoc.forms[0].target = "_self";
innerdoc.forms[0].submit();
}
};


function showMenuItems(id){
	if(id=="province"){
		toolbar.disableItem("new_table");
	    toolbar.disableItem("modify_table");
	    toolbar.disableItem("del_tabel");
	}
	else if(id.indexOf("_")>-1){
		toolbar.enableItem("new_table");
		toolbar.enableItem("modify_table");
		toolbar.enableItem("del_tabel");
	}
	else{
	 	toolbar.disableItem("new_table");
	 	toolbar.enableItem("modify_table");
	 	toolbar.enableItem("del_tabel");
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
<div id="treepanel" style="background-color:#e8f0f8;height:100%;"></div>
<script>     
        var dhxLayout = new dhtmlXLayoutObject(document.body, "2U");
       	dhxLayout.setSkin("dhx_skyblue");
       	dhxLayout.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/");
        dhxLayout.cells("a").setText("资源目录");
        dhxLayout.cells("a").setWidth(150);
        dhxLayout.cells("a").hideHeader();
        dhxLayout.cells("a").attachObject("treepanel");
        dhxLayout.cells("b").hideHeader();
        dhxLayout.cells("b").attachURL("<%=fullurl%>/dhtmlxSuite/dhtmlx/imgs/blank.html");//.attachURL("<%=fullurl%>/system/ResourceTable.do");

        var toolbar = dhxLayout.attachToolbar();
        toolbar.setIconsPath("<%=fullurl%>/images/icon/");
        toolbar.setSkin("dhx_skyblue");
        toolbar.addButton("new_table", 0, "添加表", "database--plus.gif", "databases--plus.gif");
        toolbar.setItemToolTip("new_table", "在分类下面添加资源库表");
        toolbar.addSeparator("sep1", 1);	 
        toolbar.addButton("modify_table", 2, "修改表", "database--pencil.gif", "database--pencil.gif");
        toolbar.setItemToolTip("modify_table", "修改资源库表信息和该表下对应的字段信息");
        toolbar.addSeparator("sep3", 3);
        toolbar.addButton("del_tabel", 4, "删除表", "databases--minus.gif", "databases--minus.gif");
        toolbar.setItemToolTip("del_tabel", "删除资源库表");
        //toolbar.addButton("viwe_theme", 5, "查看公共目录", "door-open.png", "door-open.png");
        //toolbar.setItemToolTip("viwe_theme", "查看公共目录");
        toolbar.disableItem("new_table");
        toolbar.disableItem("modify_table");
        toolbar.disableItem("del_tabel");
        toolbar.attachEvent("onClick", function(id){
        	if(id=="new_table"){
            	goAdd();
            }
        	else if(id=="modify_table") {
            	goModify();
            }
        	else if(id=="del_tabel") {
            	goDelete();
        	}
        	else if(id=="viwe_theme") {
        		openWindow("查看公共目录","",800,450);
        	}
        });
        
        
        var tree=new dhtmlXTreeObject("treepanel","100%","100%",0);
		tree.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlx/imgs/");
		tree.setSkin("dhx_blue");
		tree.enableCheckBoxes(0);
		tree.setOnClickHandler(tonclick);
		tree.attachEvent("onMouseIn", function(id){
	    	 var str=tree.getUserData(id,"name");
	    	 tree.setItemText(id,"<font color='#FF4422'>"+str+"</font>");
	    });
	    tree.attachEvent("onMouseOut", function(id){
	    	 var str=tree.getUserData(id,"name");
	    	 tree.setItemText(id,str);
	    });
		//tree.setXMLAutoLoading("<%=fullurl%>/system/ResourceTable.do");
		tree.loadXML("<%=fullurl%>/system/ResourceTable.do?action=TREE"); 

	
		//var wins = dhxLayout.dhxWins;
		var wins = new dhtmlXWindows();
		var winName="win";  
	    var editMode;      
	    wins.enableAutoViewport(true);
	    wins.setImagePath("<%=fullurl%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");

	    function openWindow(title,urlink,width,height){
	       var w = wins.createWindow(winName, 0, 0, width,height);
	       w.attachURL(urlink);
	       w.setText(title);
	       w.keepInViewport(true);
	      // w.denyResize();
	       w.setModal(true);
	       w.centerOnScreen();
	       w.button("minmax1").hide();
	       w.button("minmax2").hide();
	       w.button("park").hide();
	       w.button("close").attachEvent("onClick", function(){closedialog();});
	    }
	    
	    function closedialog(){
		     wins.window(winName).close();
		     var treeid = tree.getSelectedItemId();
		     reSearch(treeid);
		     return treeid;
	    }

	    function closedialognofresh(){
	    	wins.window(winName).close();
		}
	    function reSearch(treeid){
	    	dhxLayout.cells("b")._frame.contentWindow.gosearch();
			//dhxLayout.cells("b")._frame.contentWindow.renew(treeid);
		}

		function setSelectTreeNode(nodeid){			
			tree.selectItem(nodeid);
		}

		function setWindowsTitle(title){
			wins.window(winName).setText(title);
		}
</script>

</body>
</html>