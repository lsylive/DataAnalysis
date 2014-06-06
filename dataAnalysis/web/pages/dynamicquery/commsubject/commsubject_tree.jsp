<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源目录查看</title>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css">
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
		
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
		
<script>
var win;
var contextpath = "<%=CONTEXT_PATH%>";	
var hyperlink = "../query/commSubjectQuery.do";	
var fulllink = contextpath + "/query/commSubjectQuery.do";
var classifylink = contextpath + "/query/classifySubjectQuery.do";
var tablelink = contextpath + "/dynamicquery/advancedquery.do";
			
function showquery(id){	
	
    var iscomm=id.indexOf('comm');
    var isclass=id.indexOf('buss');
    var isall = id.indexOf('all_');
    var isspacetable = id.indexOf("space_");
    switch (id) {
	case "spacetable":case "this_is_commons":case "this_is_categorys":case "this_is_alltables":
		return;
	default:
		break;
	}
	
	if(iscomm!=-1){
		id=id.substr(4);
		dhxLayout.cells("b").attachURL(fulllink+"?action1=showquery&id="+id);
	}else if(isclass!=-1){
		id=id.substr(4);
		dhxLayout.cells("b").attachURL(fulllink+"?action1=showquery&cataId="+id);
	}
	else {
		if(isspacetable!=-1){
			id=id.substr(6);
		}else if(isall!=-1){
			id=id.substr(4);
		}
		var urlink = tablelink + "?action=TOADVANCEDPAGE&queryType=base&tableId=" + id;
		//alert(urlink);
		dhxLayout.cells("b").attachURL(urlink);
	}
}


</script>
<style type="text/css">
html, body {
        width: 100%;
        height: 100%;
        margin: 0px;
        overflow: hidden;
}
</style>
</head>
<body>
<div id="treepanel" style="background-color:#e8f0f8;height:100%;"></div>
<div id="openDiv" style="position:relative;top:0;left:0;width:100%;height:100%;display: none;">
  <iframe id="content" frameborder="0" name="content" style="width: 100%;height: 100%;"></iframe>
  </div>
<script>     
		
        var dhxLayout = new dhtmlXLayoutObject(document.body, "2U");
       	dhxLayout.setSkin("dhx_skyblue");
       	dhxLayout.setImagePath("<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxToolbar/codebase/imgs/dhxlayout_dhx_blue/");
        dhxLayout.cells("a").setText("分类");
        dhxLayout.cells("a").setWidth(200);
        dhxLayout.cells("a").hideHeader();
        dhxLayout.cells("a").attachObject("treepanel");
        dhxLayout.cells("b").hideHeader();
        

       
        
        //树配置
        var tree=new dhtmlXTreeObject("treepanel","100%","100%",0);
		tree.setImagePath("<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlx/imgs/");
		tree.setSkin('dhx_blue');
		tree.enableCheckBoxes(0);
		tree.setOnClickHandler(showquery);
		tree.attachEvent("onMouseIn", function(id){
	    	 var str=tree.getUserData(id,"name");
	    	 tree.setItemText(id,"<font color='#FF4422'>"+str+"</font>");
	    });
	    tree.attachEvent("onMouseOut", function(id){
	    	 var str=tree.getUserData(id,"name");
	    	 tree.setItemText(id,str);
	    });
	 	
		tree.setXMLAutoLoading("<%=CONTEXT_PATH%>query/commSubjectQuery.do?action1=showtree");
		tree.loadXML("<%=CONTEXT_PATH%>/query/commSubjectQuery.do?action1=showtree&tree=<%=request.getAttribute("tree")%>&id=0"); 

	    //tabbar
	   
	    
		var wins = dhxLayout.dhxWins;
		var winName="win";  //打开窗口名称
	    var editMode;       //页面编辑状态：ADD,EDIT,DELETE
	    wins.enableAutoViewport(true);
	    wins.setImagePath("<%=CONTEXT_PATH%>/dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
		
	    function openWindow(title,w,h){
	       if(!win){
	       win = wins.createWindow(winName, 0, 0, w, h);
	       win.setText(title);
	       win.keepInViewport(true);
	       win.setModal(true);
	       win.centerOnScreen();
	       win.button("minmax1").hide();
	       win.button("minmax2").hide();
	       win.button("park").hide();
	        //win.button("close").hide();
	        win.button("close").attachEvent("onClick", closedialog);
	       
	       
	       win.attachObject("openDiv");
	     
	       }else{
	       win.keepInViewport(true);
	       win.setModal(true);
	       win.centerOnScreen();
	       	wins.window(winName).show();
	       	}
	       return win;    
	    }

	    
	    function closedialog(){
	    	if(document.frames)
	         	document.frames["content"].document.body.innerHTML='';
	         else
	          	document.getElementById('content').contentDocument.body.innerHTML='';
	         wins.window(winName).setModal(false);
		     wins.window(winName).hide();
		    
		     
	    }

	   
</script>

</body>
</html>