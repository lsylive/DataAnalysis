<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String fullurl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高级查询</title>
<script src="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css" href="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.css">

<link rel="stylesheet" type="text/css" href="<%=fullurl%>/css/main.css" />
<script>

var contextpath = "<%=fullurl%>";	
var hyperlink = "../dynamicquery/advancedquery.do";	
var fulllink = contextpath + "/dynamicquery/advancedquery.do";	
function tonclick(id){
		var value = id;
		if(value!="province")					
		dhxLayout.cells("b").attachURL(fulllink+"?treeId="+id+"&action="+"LIST");
};
function tonopen(id,mode){
	return confirm("Do you want to "+(mode>0?"close":"open")+" node "+tree.getItemText(id)+"?");
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
        dhxLayout.cells("a").setText("分类");
        dhxLayout.cells("a").setWidth(200);
        dhxLayout.cells("a").hideHeader();
        dhxLayout.cells("a").attachObject("treepanel");
        dhxLayout.cells("b").hideHeader();
        dhxLayout.cells("b").attachURL(fulllink);

        var tree=new dhtmlXTreeObject("treepanel","100%","100%",0);
		//tree.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlxTree/codebase/imgs/");
		tree.setImagePath("<%=fullurl%>/dhtmlxSuite/dhtmlx/imgs/");
		tree.setSkin('dhx_blue');
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
		tree.loadXML(fulllink+"?action=TREE"); 


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