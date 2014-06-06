<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String fullurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分配资源</title>
<link rel="stylesheet" type="text/css" href="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css" href="<%=fullurl%>/css/main.css" />
<script>

var contextpath = "<%=fullurl%>";	
var hyperlink = "../system/sysUser.do";	
var fulllink = contextpath + "/system/sysUser.do";
function tonclick(id){
	var userId = document.getElementsByName("record(userId)")[0].value;
		var value = id;
		if(value.indexOf("cator_")==-1){					
		dhxLayout.cells("b").attachURL(fulllink+"?tableId="+id+"&action=GETRESOURCEFORM&userId="+userId);
		}else{
			dhxLayout.cells("b").attachURL("<%=fullurl%>/dhtmlxSuite/dhtmlx/imgs/blank.html");
		}
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

function close(){
	parent.close();
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
<html:form method="post" action="/system/sysUser.do">
  	<html:hidden property="record(userId)" />  	
</html:form>
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
		tree.setXMLAutoLoading("<%=fullurl%>/system/sysUser.do?action=GETRESOURCETREE");
		tree.loadXML("<%=fullurl%>/system/sysUser.do?id=0&action=GETRESOURCETREE"); 

	
		var wins = dhxLayout.dhxWins;
		var winName="win";  
	    var editMode;      
	    wins.enableAutoViewport(true);
	    wins.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");

	    function openWindow(title,urlink,w,h){
	       var w = wins.createWindow(winName, 0, 0, w, h);
	       w.setText(title);
	       w.keepInViewport(true);
	      // w.denyResize();
	       w.setModal(true);
	       w.centerOnScreen();
	       w.button("minmax1").hide();
	       w.button("minmax2").hide();
	       w.button("park").hide();
	       w.button("close").attachEvent("onClick", function(){closedialog();});
	       w.attachURL(urlink);
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

		function setWindowsTitle(title){
			wins.window(winName).setText(title);
		}
</script>

</body>
</html>