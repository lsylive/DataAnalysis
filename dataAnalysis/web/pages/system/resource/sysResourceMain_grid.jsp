<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广东省信息所（数据应用系统）</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="x-ua-compatible" content="ie=7" />

<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css" ></link>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxGrid/codebase/dhtmlxgrid.css" ></link>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css" ></link>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxTreeGrid/codebase/dhtmlxtreegrid.js"></script>  


<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css">
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>

<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>
<body>
  <div id="div_main" style="position:relative;top:0;left:0;width:100%;height:100%;"></div>
  <div id="div_panel" style="position:relative;top:0;left:0;width:100%;height:100%;overflow:hidden;">
  <div id="panel" style="position:relative;top:0;left:0;width:100%;height:100%;"></div>
  </div>
  <div id="winVP" style="position:relative;top:0;left:0;width:100%;height:100%;z-index:100;"></div>
<script type="text/javascript">

    function closedialog(ret){
	     dhxWins.window(winName).close();
	     if(ret=='true') {
	       var nodeId = tree.getSelectedItemId();
	     	 if(editMode=="ADD") {
	          tree.refreshItem(nodeId);
	       }
	       else if(editMode=="EDIT"){
            var parentId = tree.getParentId(nodeId);
	          tree.refreshItem(parentId);
	          tree.selectItem(nodeId,false,"");
	       }
	       else if(editMode=="DELETE"){
            var parentId = tree.getParentId(nodeId);
	          tree.refreshItem(parentId);
	       }
	     }
	     editMode="";	
    }
	
    function addOrg(id){
    	editMode="ADD";
    	var url_link="<%=CONTEXT_PATH%>system/sysOrg.do?action="+editMode+"&pid="+id.substring(3);
    	openWindow("增加单位",url_link,700,400);
    }	

    function modifyOrg(id){
    	editMode="EDIT";
    	var url_link="<%=CONTEXT_PATH%>system/sysOrg.do?action="+editMode+"&id="+id.substring(3);
    	openWindow("修改单位",url_link,700,400);
    }	

    function deleteOrg(id){
    	var res=confirm("真的要删除该单位？");
    	if(res==false) return;
    	editMode="DELETE";
    	var url_link="<%=CONTEXT_PATH%>system/sysOrg.do?action="+editMode+"&id="+id.substring(3);
			var loader = dhtmlxAjax.postSync(url_link,"");
			eval("var ret="+loader.xmlDoc.responseText);
			if(ret.exit=="0") tree.deleteItem(id); else alert(ret.message);
    	editMode="";
    }	

    function addDept(id){
    	editMode="ADD";
    	var url_link;
    	if(id.indexOf("org")>-1){
    	   url_link="<%=CONTEXT_PATH%>system/sysDept.do?action="+editMode+"&orgId="+id.substring(3);
      }
      else {
      	 var orgId=tree.getUserData(id,"orgId");
    	   url_link="<%=CONTEXT_PATH%>system/sysDept.do?action="+editMode+"&pid="+id.substring(4)+"&orgId="+orgId.substring(3);
      }
    	openWindow("增加部门",url_link,700,400);
    }	

    function modifyDept(id){
    	editMode="EDIT";
    	var url_link="<%=CONTEXT_PATH%>system/sysDept.do?action="+editMode+"&id="+id.substring(4);
    	openWindow("修改部门",url_link,700,400);
    }	

    function deleteDept(id){
    	var res=confirm("真的要删除该部门？");
    	if(res==false) return;
    	editMode="DELETE";
    	var url_link="<%=CONTEXT_PATH%>system/sysDept.do?action="+editMode+"&id="+id.substring(4);
			var loader = dhtmlxAjax.postSync(url_link,"");
			eval("var ret="+loader.xmlDoc.responseText);
			if(ret.exit=="0") tree.deleteItem(id,true); else alert(ret.message);
    	editMode="";
    }	

		function showMenuItems(id){
         toolbar.hideItem("new_org");
         toolbar.hideItem("new_dept");
         toolbar.hideItem("modify_org");
         toolbar.hideItem("modify_dept");
         toolbar.hideItem("del_org");
         toolbar.hideItem("del_dept");
         toolbar.hideItem("sep1");
         toolbar.hideItem("sep2");
         toolbar.hideItem("sep3");
    	if(id.indexOf("org")>-1){
         toolbar.showItem("new_org");
         toolbar.showItem("new_dept");
         toolbar.showItem("modify_org");
         toolbar.showItem("del_org");
         toolbar.showItem("sep1");
         toolbar.showItem("sep2");
         toolbar.showItem("sep3");
    	}
    	else if(id.indexOf("dept")>-1){
         toolbar.showItem("new_dept");
         toolbar.showItem("modify_dept");
         toolbar.showItem("del_dept");
         toolbar.showItem("sep2");
         toolbar.showItem("sep3");
		  }
		}


//页面初始化代码
	  var dhxLayout = new dhtmlXLayoutObject("div_main", "1C");
	  dhxLayout.setSkin("dhx_skyblue");

	  var panel=dhxLayout.cells("a");
	  panel.hideHeader();
	  panel.attachObject("div_panel");

    var toolbar = dhxLayout.attachToolbar();
    toolbar.setIconsPath("<%=CONTEXT_PATH%>images/icon/");
    toolbar.addButton("new_org", 0, "添加子单位", "home--plus.png", "home--plus.png");
    toolbar.addSeparator("sep1", 1);
    toolbar.addButton("new_dept", 2, "添加子部门", "users--plus.png", "users--plus.png");
    toolbar.addSeparator("sep2", 3);
    toolbar.addButton("modify_org", 4, "修改", "home--pencil.png", "home--pencil.png");
    toolbar.addButton("modify_dept", 5, "修改", "users--pencil.png", "users--pencil.png");
    toolbar.addSeparator("sep3", 6);
    toolbar.addButton("del_org", 7, "删除", "home--minus.png", "home--minus.png");
    toolbar.addButton("del_dept", 8, "删除", "users--minus.png", "users--minus.png");
    toolbar.attachEvent("onClick", function(id){
    	var nodeId=tree.getSelectedItemId();
    	if(id=="new_org") addOrg(nodeId);
    	else if(id=="modify_org") modifyOrg(nodeId);
    	else if(id=="new_dept") addDept(nodeId);
    	else if(id=="modify_dept") modifyDept(nodeId);
    	else if(id=="del_org") deleteOrg(nodeId);
    	else if(id=="del_dept") deleteDept(nodeId);
    });
    

	 
var mygrid = new D('panel');
mygrid.selMultiRows = true;
mygrid.eg="<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxGrid/codebase/imgs/icons_greenfolders/";
mygrid.setHeader("Tree,Plain Text,Long Text,Color,Checkbox, ");
mygrid.setInitWidths("150,100,100,100,100,*");
mygrid.setColAlign("left,left,left,left,center");
mygrid.setColTypes("tree,ed,txt,ch,ch");
mygrid.setColSorting("str,str,str,na,str");
mygrid.init();
mygrid.setSkin("dhx_skyblue");
mygrid.loadXML("<%=CONTEXT_PATH%>dhtmlxSuite/products/dhtmlxTreeGrid/samples/common/test_list_1.xml"); 

		showMenuItems("0");

    var dhxWins = new dhtmlXWindows();
    dhxWins.enableAutoViewport(true);
    dhxWins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
    
    var winName="win";  //打开窗口名称
    var editMode;       //页面编辑状态：ADD,EDIT,DELETE
    
    function openWindow(title,urlink,w,h){
    	 var w = dhxWins.createWindow(winName, 0, 0, w, h);
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

    function refreshItem(id){
	     tree.refreshItem(0);
    }
</script>

</body>
</html>