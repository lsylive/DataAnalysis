<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String id = (String)request.getAttribute("tableid");
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String columnurl = CONTEXT_PATH+"system/ResourceColumn.do?action=LIST&tableid="+id;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
		<script src="<%=CONTEXT_PATH%>js/ctrl_util.js"></script>
		<script src="<%=CONTEXT_PATH%>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>
		
	<script type="text/javascript">
	var contextpath = "<%=CONTEXT_PATH%>";	
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; overflow:hidden}
</style>
</head>

<body>	
	<html:form action="/compare/compareInfo.do" method="post">
	<html:hidden property="action" />
	<html:hidden property="record(id)" />
	</html:form>
	<div id="div_main" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;border: 0px;"></div>
  	
  	<div id="div_table" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  	<iframe id="frmtable" name="frmtable" src="" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>
  	
  	
  	<div id="div_filter" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  	<iframe id="frmfilter" name="frmfilter" src="" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>
  	
  	<div id="div_condition" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  	<iframe id="frmcondition" name="frmfilter" src="" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>
  	
  	
    <div id="bottom_div" class="btnDiv">
		<gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
		&nbsp;
		<gw:button name="btnCancel" onClick="goCancel()">关闭</gw:button>
    </div>

<script type="text/javascript"><!--

    var tabbar = new dhtmlXTabBar("div_main", "top");
    tabbar.setSkin('dhx_skyblue');
    tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
    //tabbar.enableAutoSize(true,true);
    tabbar.enableAutoReSize();
 
    tabbar.addTab("a1", "基本信息", "100px");
    tabbar.addTab("a2", "对比表及字段设置", "140px");
     tabbar.addTab("a3", "筛选条件设置", "140px");
  
    tabbar.setContent("a1","div_table");
    tabbar.setContent("a2","div_filter");
    tabbar.setContent("a3","div_condition");
  
 
    tabbar.setTabActive("a1");	
    tabbar.attachEvent("onSelect",function(id,pid){
      try{ 
		if (id=="a1") {
			return true;
		} else if(id=="a2"){
		    var compId=$NAME("record(id)")[0].value;
		    var frmobj=$$('frmfilter');
		    if(frmobj.contentWindow.document.body.innerHTML=='')
		    	frmobj.contentWindow.location.href='<%=CONTEXT_PATH%>compare/compareTable.do?action=BASE&record(compId)='+compId;
			return true;	
		
		} else if(id=="a3"){
		    var compId=$NAME("record(id)")[0].value;
		    var frmobj=$$('frmcondition');
		    var frmobj1=$$('frmfilter');
		   
		    if(frmobj.contentWindow.document.body.innerHTML==''){
		    	if (frmobj1.contentWindow.document.body.innerHTML!='')
		    	{
		    		var mainTabId = frmobj1.contentWindow.document.getElementsByName("record(mainTabId)")[0].value;
		    		frmobj.contentWindow.location.href='<%=CONTEXT_PATH%>compare/compareFilter.do?action=LIST&record(compId)='+compId +'&mainTabId=' + mainTabId;
		    	}
		    	else
		    	{
					frmobj.contentWindow.location.href='<%=CONTEXT_PATH%>compare/compareFilter.do?action=LIST&record(compId)='+compId;	    
		    	}
		    }
			return true;	
		}
	  }catch(e){
		  return true;
	  }
    });
   //$NAME("action")[0].value='BASE';
    document.forms[0].target="frmtable";
    document.forms[0].submit();
   
   
    function goSubmit(){
      try{
    	var doc;
    	var compId=$NAME("record(id)")[0].value;
    	
    	if (compId!='') {
    		var iframe = $$("frmfilter");
    		if(isFormChanged(iframe))
    			iframe.contentWindow.goSubmit();
		}
		 
		var iframe1 = $$("frmtable");
    		iframe1.contentWindow.goSubmit();
      }catch(e){
		//alert("页面还没有加载完成！");
      }        
    } 

    function goCancel(){
		parent.closedialog(false);
   	}  
   	function closedialog(ret){
   		parent.closedialog(ret);
   	}
	function isFormChanged(iframe){
      try{
    	var doc;
    	if (_isIE) {
    		doc = iframe.contentWindow.document;
        } else {
            doc = iframe.contentDocument;
        }
        var isChanged = false;
        var form = doc.forms[0];
        for (var i = 0; i < form.elements.length; i++) {
            var element = form.elements[i];
            var type = element.type;
            if (type == "text" || type == "hidden" || type == "textarea" || type == "button") {
                if (element.value != element.defaultValue) {
                    isChanged = true;
                    break;
                }
            } else if (type == "radio" || type == "checkbox") {
                if (element.checked != element.defaultChecked) {
                    isChanged = true;
                    break;
                }
            } else if (type == "select-one"|| type == "select-multiple") {
                for (var j = 0; j < element.options.length; j++) {
                    if (element.options[j].selected != element.options[j].defaultSelected) {
                        isChanged = true;
                        break;
                    }
                }
            }     
        }    
        return isChanged;
      }catch(e){return false;}
    }; 
   
--></script>  
<script type="text/javascript">
	  var mainbody=window.document.body;
    if(window.addEventListener) {
 	     window.addEventListener("resize",goResize,false); 
    }
    else {
 	     window.attachEvent('onresize',goResize);
    }
    
    var fDiv=window.document.getElementById('div_main');
    //var gDiv=window.document.getElementById('div_genaral');
    var bDiv=window.document.getElementById('bottom_div');
       fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*2+"px";
      // gDiv.style.width=mainbody.offsetWidth-gDiv.offsetLeft*2+"px";
       bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*2+"px";
       fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*2+"px";
      // gDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-gDiv.offsetTop*2+"px";
       bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*4+"px";

    
    function goResize(){
    	fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*2+"px";
       // gDiv.style.width=mainbody.offsetWidth-gDiv.offsetLeft*2+"px";
        bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*2+"px";
        fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*2+"px";
       // gDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-gDiv.offsetTop*2+"px";
        bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*4+"px";
    }

    function setTitle(title){
    	parent.setWindowsTitle(title);
    }
</script>
</body>
</html>
