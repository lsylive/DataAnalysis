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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>
<body onload="init()">
  <div id="left_div" style="overflow:hidden;">
  </div>
<script type="text/javascript">
	
	  function init(){
	  
	  }

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
    	openWindow("增加单位",url_link,600,250);
    }	

    function modifyOrg(id){
    	editMode="EDIT";
    	var url_link="<%=CONTEXT_PATH%>system/sysOrg.do?action="+editMode+"&id="+id.substring(3);
    	openWindow("修改单位",url_link,600,250);
    }	

    function deleteOrg(id){
    	var res=confirm("将要删除该单位下属的所有单位和部门\r\n        是否真的要删除该单位？");
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
    	openWindow("增加部门",url_link,600,250);
    }	

    function modifyDept(id){
    	editMode="EDIT";
    	var url_link="<%=CONTEXT_PATH%>system/sysDept.do?action="+editMode+"&id="+id.substring(4);
    	openWindow("修改部门",url_link,600,250);
    }	

    function deleteDept(id){
    	var res=confirm("将要删除该部门下属的所有部门\r\n        是否真的要删除该部门？");
    	if(res==false) return;
    	editMode="DELETE";
    	var url_link="<%=CONTEXT_PATH%>system/sysDept.do?action="+editMode+"&id="+id.substring(4);
			var loader = dhtmlxAjax.postSync(url_link,"");
			eval("var ret="+loader.xmlDoc.responseText);
			if(ret.exit=="0") tree.deleteItem(id,true); else alert(ret.message);
    	editMode="";
    }	

		function showMenuItems(id){
         toolbar.disableItem("new_org");
         toolbar.disableItem("new_dept");
         toolbar.hideItem("modify_org");
         toolbar.hideItem("modify_dept");
         toolbar.hideItem("del_org");
         toolbar.hideItem("del_dept");
         toolbar.hideItem("sep2");
         toolbar.hideItem("sep3");
    	if(id.indexOf("org")>-1){
         toolbar.enableItem("new_org");
         toolbar.enableItem("new_dept");
         toolbar.showItem("modify_org");
         toolbar.showItem("del_org");
         toolbar.showItem("sep1");
         toolbar.showItem("sep2");
         toolbar.showItem("sep3");
    	}
    	else if(id.indexOf("dept")>-1){
         toolbar.disableItem("new_org");
         toolbar.enableItem("new_dept");
         toolbar.showItem("modify_dept");
         toolbar.showItem("del_dept");
         toolbar.showItem("sep2");
         toolbar.showItem("sep3");
		  }
		}


//页面初始化代码
	  var dhxLayout = new dhtmlXLayoutObject(document.body, "1C");
//	  var dhxLayout = new dhtmlXLayoutObject("div_main", "2U");
	  dhxLayout.setSkin("dhx_skyblue");

	  var leftPanel=dhxLayout.cells("a");
	  leftPanel.hideHeader();
	  leftPanel.attachObject("left_div");

//	  var rightPanel=dhxLayout.cells("b");
//	  leftPanel.setWidth(250);
//	  rightPanel.hideHeader();
//	  rightPanel.attachObject("right_div");

    var toolbar = dhxLayout.attachToolbar();
    toolbar.setIconsPath("<%=CONTEXT_PATH%>images/icon/");
    toolbar.setSkin("dhx_skyblue");
    toolbar.addButton("new_org", 0, "添加子单位", "house_big_1.gif", "house_big_1.gif");
    toolbar.addSeparator("sep1", 1);
    toolbar.addButton("new_dept", 2, "添加子部门", "people_1.gif", "people_1.gif");
    toolbar.addSeparator("sep2", 3);
    toolbar.addButton("modify_org", 4, "修改", "house_big_2.gif", "house_big_2.gif");
    toolbar.addButton("modify_dept", 5, "修改", "people_2.gif", "people_2.gif");
    toolbar.addSeparator("sep3", 6);
    toolbar.addButton("del_org", 7, "删除", "house_big_3.gif", "house_big_3.gif");
    toolbar.addButton("del_dept", 8, "删除", "people_3.gif", "people_3.gif");
    toolbar.attachEvent("onClick", function(id){
    	var nodeId=tree.getSelectedItemId();
    	if(id=="new_org") addOrg(nodeId);
    	else if(id=="modify_org") modifyOrg(nodeId);
    	else if(id=="new_dept") addDept(nodeId);
    	else if(id=="modify_dept") modifyDept(nodeId);
    	else if(id=="del_org") deleteOrg(nodeId);
    	else if(id=="del_dept") deleteDept(nodeId);
    });
	 
    var tree = new dhtmlXTreeObject("left_div","100%","100%",0);
    tree.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/imgs/");
    tree.setSkin('dhx_blue');


    tree.attachEvent("onSelect", function(id){
    	 showMenuItems(id);
    });
    tree.attachEvent("onDblClick", function(id){
       if(id.indexOf("org")>-1) modifyOrg(id);
       if(id.indexOf("dept")>-1) modifyDept(id);
    });
    tree.attachEvent("onMouseIn", function(id){
    	 var str=tree.getUserData(id,"name");
    	 tree.setItemText(id,"<font color='#FF4422'>"+str+"</font>");
    });
    tree.attachEvent("onMouseOut", function(id){
    	 var str=tree.getUserData(id,"name");
    	 tree.setItemText(id,str);
    });
		tree.setXMLAutoLoading("<%=CONTEXT_PATH%>system/sysOrg.do?action=getTree");
		tree.loadXML("<%=CONTEXT_PATH%>system/sysOrg.do?action=getTree&id=0");
    showMenuItems("0");
    
    function refreshItem(id){
	     tree.refreshItem(0);
    }
</script>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>