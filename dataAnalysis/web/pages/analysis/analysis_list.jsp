<%@ page language="java" contentType="text/html;charset=GBK" %>
<%
  String ITEM_TYPE = (String)request.getAttribute("type");
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
<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	
<style type="text/css">
  html, body {
   	  width:100%; 
   	  height:100%; 
   	  margin:0px; 
   	  padding:0px; 
   	  overflow:hidden;
  }

 .ficon_item,.ficon_item_selected{
 	   border:1px solid lightgrey;
 	   width:100px;
 	   float:left;
 	   margin:5px; 
 	   cursor:default;
 	   vertical-align:middle;
 }
 .ficon_item_text{
 	   font-size:12px; 
 	   font-family: tahoma; 
 	   width:85px!important; 
 	   height:43px!important; 
 	   text-align:center!important;
 }
 .ficon_item_selected div{
 	   background-color:#3366ff; 
 	   color:white;
 }
</style>
</head>
<body>
  <div id="winVP" style="position:relative;top:0;left:0;width:100%;height:100%;z-index:100;"></div>
<script type="text/javascript">

//页面初始化代码
	  var dhxLayout = new dhtmlXLayoutObject(document.body, "1C");
	  dhxLayout.setSkin("dhx_skyblue");

	  var leftPanel=dhxLayout.cells("a");
	  leftPanel.hideHeader();
 
//    var toolbar = dhxLayout.attachToolbar();
//    toolbar.setIconsPath("<%=CONTEXT_PATH%>images/icon/");
//    toolbar.addButton("add", 0, "创建快捷方式", "home--plus.gif", "home--plus.gif");
//    toolbar.addButton("del", 1, "删除快捷方式", "home--minus.gif", "home--minus.gif");
//    toolbar.attachEvent("onClick", function(id){
//    	if(id=="add") addLink(id);
//    	else if(id=="del") delLink(id);
//    });

    var dhxFolders = leftPanel.attachFolders();
    dhxFolders.setItemType("ftiles");
    dhxFolders.enableSelection(2);

    dhxFolders.setUserData("icons_src_dir", "<%=CONTEXT_PATH%>images/analysis");
    dhxFolders.loadXML("<%=CONTEXT_PATH%>analysis/analysis.do?action=Folder&type=<%=ITEM_TYPE%>", "<%=CONTEXT_PATH%>pages/analysis/analysis.xsl");
    dhxFolders.attachEvent("ondblclick", doOnDblClick);

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

    function doOnDblClick(itemId) {
      var xmlNode = dhxFolders.getItem(itemId).data.dataObj;
      var tempId=xmlNode.getAttribute("tempId");
      var type=xmlNode.getAttribute("type");
      var tempType=xmlNode.getAttribute("tempType");
      
      window.location="<%=CONTEXT_PATH%>analysis/analysis.do?action=query&tempId="+tempId+"&type="+type+"&tempType="+tempType;
    }

</script>
</body>
</html>