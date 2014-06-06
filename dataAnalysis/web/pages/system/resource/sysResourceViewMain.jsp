<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String fullurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源目录查看</title>
<link rel="stylesheet" type="text/css" href="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.css"/>
<script src="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

<link rel="stylesheet" type="text/css" href="<%=fullurl%>/css/main.css" />

<script>

var contextpath = "<%=fullurl%>";	
var hyperlink = "../system/ResourceTableView.do";	
var fulllink = contextpath + "/system/ResourceTableView.do";
function tonclick(id){	
	var value = id;
	if(value!="province"){					
	dhxLayout.cells("b").attachURL(fulllink+"?treeid="+id+"&action="+"LIST");
	}else{
		dhxLayout.cells("b").attachURL("<%=fullurl%>/dhtmlxSuite/dhtmlx/imgs/blank.html");
	}
	disableButton(id);
};
			
function tonopen(id,mode){
	return confirm("Do you want to "+(mode>0?"close":"open")+" node "+tree.getItemText(id)+"?");
};
function goView(){
	var id = dhxLayout.cells("b")._frame.contentWindow.getModifyId();
	if (id=="") {
		return;
	}
	var urlforcolumn = contextpath+"/system/ResourceColumnView.do"+"?action=LIST&tableid="+id;
    var urlink=fulllink+'?action=VIEW&record(id)='+id;
    openWindowForEdit("查看资源库",urlink,urlforcolumn,800,450);
};

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
       	dhxLayout.setSkin("dhx_skyblue");;
       	dhxLayout.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/");
        dhxLayout.cells("a").setText("分类");
        dhxLayout.cells("a").setWidth(150);
        dhxLayout.cells("a").hideHeader();
        dhxLayout.cells("a").attachObject("treepanel");
        dhxLayout.cells("b").hideHeader();
        dhxLayout.cells("b").attachURL("<%=fullurl%>/dhtmlxSuite/dhtmlx/imgs/blank.html");

        var toolbar = dhxLayout.attachToolbar();
        toolbar.setIconsPath("<%=fullurl%>/images/icon/");
        toolbar.setSkin("dhx_skyblue");
        toolbar.addButton("view_table", 2, "查看资源库", "door-open.gif", "door-open.gif");
        toolbar.disableItem("view_table");
        toolbar.setItemToolTip("view_table", "查看资源库表属性和字段属性");
        toolbar.attachEvent("onClick", function(id){
        	if(id=="view_table") {
            	goView();
            }
        });
        
        //树配置
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
		tree.setXMLAutoLoading("<%=fullurl%>/system/ResourceTableView.do");
		tree.loadXML("<%=fullurl%>/system/ResourceTableView.do?id=0");

		var wins = dhxLayout.dhxWins;
		var winName="win";  //打开窗口名称
	    var editMode;       //页面编辑状态：ADD,EDIT,DELETE
	    wins.enableAutoViewport(true);
	    wins.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");

	    function disableButton(id){
		    if(id=='province'){
		    	toolbar.disableItem("view_table");
		    }else{
		    	toolbar.enableItem("view_table");
			}
	    	 
		}
	    function openWindow(title,urlink,w,h){
	       var w = wins.createWindow(winName, 0, 0, w, h);
	       w.setText(title);
	       w.keepInViewport(true);
	       w.setModal(true);
	       w.centerOnScreen();
	       w.button("minmax1").hide();
	       w.button("minmax2").hide();
	       w.button("park").hide();
	       w.attachURL(urlink);
	       return w;    
	    }

	    function openWindowForEdit(title,tableurlink,columnurlink,w,h){
	    	var w = wins.createWindow(winName, 0, 0, w, h);
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
				tabbar.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
				tabbar.addTab("a1", "查看资源库信息", "100px");
				tabbar.addTab("a2", "资源库表字段", "100px");
				tabbar.setHrefMode("iframes-on-demand");
				tabbar.setContentHref("a1", tableurlink);
				tabbar.setContentHref("a2", columnurlink);
				tabbar.setTabActive("a1");
		       return w;
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

			dhxLayout.cells("b")._frame.contentWindow.renew(treeid);
		}

		function setSelectTreeNode(nodeid){
			tree.selectItem(nodeid);
		}
</script>

</body>
</html>