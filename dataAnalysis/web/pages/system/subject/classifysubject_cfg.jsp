<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>广东省信息所（数据应用系统）</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />

		<link rel="stylesheet" type="text/css"
			href="<%=CONTEXT_PATH%>css/main.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
	</head>
	<body>
			<!--  <div id="div_main" style="position:relative;top:0;left:0;width:100%;height:100%;"></div>-->
			<div id="treepanel" style="background-color:#e8f0f8;height:100%;"></div>
		</div>

		<script type="text/javascript">

        var modifytag=false;
    	function showMenuItems(id){
    	
    	if(id=="buss0"){
    		toolbar.disableItem("config_classify");
           toolbar.disableItem("config_indicate");
          }
    	else if(id!='0'){
    	 //dhxLayout.showToolbar();
         toolbar.enableItem("config_classify");
         toolbar.enableItem("config_indicate");
         }
		}
		//页面初始化代码
		//var dhxLayout = new dhtmlXLayoutObject("div_main", "1C");
		var dhxLayout = new dhtmlXLayoutObject(document.body, "2U");
    	var toolbar = dhxLayout.attachToolbar();
    	toolbar.setIconsPath("<%=CONTEXT_PATH%>images/icon/");
   
    	toolbar.addButton("config_classify", 0, "修改分类主题", "home--pencil.gif", "home--pencil.gif");
  		toolbar.addSeparator("sep1", 1);
   		toolbar.addButton("config_indicate", 2, "分类主题配置", "home--pencil.gif", "home--pencil.gif");
   		 //默认隐藏菜单
    	toolbar.disableItem("config_classify");
  		toolbar.disableItem("config_indicate");
    	toolbar.attachEvent("onClick", function(id){
    		//如果修改过
    		if(modifytag)
    		{
    			if(confirm('分类基本信息已修改，是否保存?'))
    			{
    				dhxLayout.cells("b")._frame.contentWindow.document.forms[0].submit();
    			}
    		}
    		var nodeId=tree.getSelectedItemId();
    		if(id=="config_classify") configclassify(nodeId);
    		else if(id=="config_indicate") configindicate(nodeId)
    	});
    	//dhxLayout.hideToolbar();
      
	  dhxLayout.setSkin("dhx_skyblue");
	  var leftPanel=dhxLayout.cells("a");
	 

	  leftPanel.hideHeader();
	  leftPanel.setWidth(200);
	  leftPanel.attachObject("treepanel");
	  var rightPanel=dhxLayout.cells("b");
	  rightPanel.hideHeader();
	 
	 
    var tree = new dhtmlXTreeObject("treepanel","100%","100%",0);
    tree.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/imgs/");
    tree.setSkin('dhx_blue');
    tree.attachEvent("onSelect", function(id){
    	 showMenuItems(id);
    });
    tree.attachEvent("onMouseIn", function(id){
	    	 var str=tree.getUserData(id,"name");
	    	 tree.setItemText(id,"<font color='#FF4422'>"+str+"</font>");
	    });
	    tree.attachEvent("onMouseOut", function(id){
	    	 var str=tree.getUserData(id,"name");
	    	 tree.setItemText(id,str);
	    });


		tree.setXMLAutoLoading("<%=CONTEXT_PATH%>querycfg/classifySubject.do?action=getTree");
		tree.loadXML("<%=CONTEXT_PATH%>querycfg/classifySubject.do?action=getTree&id=0");
		showMenuItems("0");

    var dhxWins = new dhtmlXWindows();
    dhxWins.enableAutoViewport(true);
    dhxWins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
    
    var winName="win";  //打开窗口名称
    var editMode;       //页面编辑状态：ADD,EDIT,DELETE
    

    function refreshItem(id){
	     tree.refreshItem(0);
    }
   

    function configclassify(id){
    	editMode="MODIFY";
    	var url_link="<%=CONTEXT_PATH%>querycfg/classifySubject.do?action="+editMode+"&catagoryId="+id.substring(4);
    	openWindow("修改分类主题",url_link,700,450);
    	//dhxLayout.cells("b").attachURL(url_link);
    }	
	function configindicate(id){
    	editMode="LIST";
    	var url_link="<%=CONTEXT_PATH%>querycfg/classifySynthesisCfg.do?action="+editMode+"&catagoryId="+id.substring(4);
    	//openWindow("分类主题配置",url_link,800,500);
    	dhxLayout.cells("b").attachURL(url_link);
    }
    function closedialog(ret){
	     dhxWins.window(winName).close();
	  }	
   
    </script>

<%@include file="/common/dialog1.jsp" %>
	</body>
</html>
