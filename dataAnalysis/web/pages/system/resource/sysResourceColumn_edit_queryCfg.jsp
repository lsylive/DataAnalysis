<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String id = (String)request.getAttribute("record(id)");
	String tableid = (String)request.getAttribute("record(tableid)");
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String dataurl = CONTEXT_PATH + "system/ResourceColumn.do?action=EDIT&record(id)="+id+"&record(tableid)="+tableid;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css"/>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
<style type="text/css">
   html, body {width:100%; height:100%; overflow:hidden}
</style>
</head>

<body>	
  	<div id="div_main" style="position:relative;top:0;left:0;width:100%;height:92%;background:white;padding-left:2px;padding-right:2px;">
  	<div id="div_genaral" style="position:relative;top:0;left:0;width:98.5%;height:100%;background:white;padding-left:2px;padding-right:2px;">
  		<iframe id="content" src="<%=dataurl%>" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>
  	</div>
  	<div id="bottom_div" class="btnDiv">
		       <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
				    &nbsp;
			      <gw:button name="btnReset" onClick="goReset()">重置</gw:button>
		</div>	  
<script type="text/javascript">
		var contextpath = "<%=CONTEXT_PATH%>";
		var tableid = '<%=request.getAttribute("record(tableid)")%>';
		var id = '<%=request.getAttribute("record(id)")%>';
		var urlforquerycfg = 	contextpath + "querycfg/columnSynthesisCfg.do?action=MODIFY&columnId="+id+"&tableId="+tableid;
		var urlforedit = contextpath + "system/ResourceColumn.do?action=EDIT&record(id)="+id+"&record(tableid)="+tableid;
		var iframe = document.getElementById("content");
		function toEditColumn(){
			iframe.src = urlforedit;			
		}
		function toQueryCfg(){
			iframe.src = urlforquerycfg;
		}
		var tabbar = new dhtmlXTabBar("div_main", "top");
    tabbar.setSkin('dhx_skyblue');
    tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
    tabbar.enableAutoReSize();
    
    tabbar.addTab("a1", "基本信息", "100px");
    tabbar.addTab("a2", "查询配置", "100px");
    tabbar.setContent("a1","div_genaral");
    tabbar.setTabActive("a1");	
    tabbar.attachEvent("onSelect",function(id,pid){
      try{
				if (id=="a1"&&pid=="a2") {
						toEditColumn();
						tabbar.setContent(id,"div_genaral");
						return true;		
				} else if(id=="a2"&&pid=="a1"){			
						toQueryCfg();
						tabbar.setContent(id,"div_genaral");
						return true;			
				}else{
						toEditColumn();
						tabbar.setContent(id,"div_genaral");
						return true;
				}
		  }catch(e){
			  return true;
		  }
    });
    
    function goSubmit(){
      try{
    		var doc;
    		if (_isIE) {
    			doc = iframe.contentWindow.document;
        } else {
           doc = iframe.contentDocument;
        } 		 		
		    iframe.contentWindow.goSubmit();
		    goCancel();
      }catch(e){
				alert("页面还没有加载完成！");
      }        
    }
    
    function goReset(){
      try{
    		var doc;
    		if (_isIE) {
    			doc = iframe.contentWindow.document;
        } else {
           doc = iframe.contentDocument;
        } 		 		
		    iframe.contentWindow.goReset();		    
      }catch(e){
				alert("页面还没有加载完成！");
      }        
    }

    function goCancel(){        
			parent.closedialog(false);   	 
   	}
</script>
<script type="text/javascript">
	  var mainbody=window.document.body;
    if(window.addEventListener) {
 	     window.addEventListener("resize",goResize,false);
    }
    else {
 	     window.attachEvent('onresize',goResize);
    }
    
    var fDiv=window.document.getElementById('div_main');
    var bDiv=window.document.getElementById('bottom_div');
       fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*2+"px";
       bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*2+"px";
       fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*2+"px";
       bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*4+"px";

    
    function goResize(){
    	fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*2+"px";
        bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*2+"px";
        fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*2+"px";
        bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*4+"px";
    }

    function setTitle(title){
    	parent.setWindowsTitle(title);
    }
</script>
</body>
</html>
