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
	String tableurl = CONTEXT_PATH+"system/ResourceTable.do?action=EDIT&record(id)="+id;
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
  	<div id="div_genaral" style="position:relative;top:0;left:0;width:99%;height:100%;background:white;padding-left:2px;padding-right:2px;">
  	<iframe id="content" src="<%=tableurl%>" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>
  	</div>
    <div id="bottom_div" class="btnDiv">
		<gw:button styleClass="btnStyle" code="" onClick="goSubmit()">保存</gw:button>
		&nbsp;
		<gw:button styleClass="btnStyle" code="" onClick="goCancel()">关闭</gw:button>
    </div>

<script type="text/javascript">
var contextpath = "<%=CONTEXT_PATH%>";	
var fulllink = contextpath + "system/ResourceTable.do";		

var id = '<%=request.getAttribute("tableid")%>';
var urlforcolumn = contextpath+"/system/ResourceColumn.do"+"?action=LIST&tableid="+id;
var urlink=fulllink+'?action=EDIT&record(id)='+id;
var iframe = document.getElementById("content");
function toColumnTab(){		
	iframe.src=urlforcolumn;
}
function toTableTab(){		
	iframe.src= urlink;
}
    var tabbar = new dhtmlXTabBar("div_main", "top");
    tabbar.setSkin('dhx_skyblue');
    tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
    //tabbar.enableAutoSize(true,true);
    tabbar.enableAutoReSize();
    
    tabbar.addTab("a1", "基本信息", "100px");
    tabbar.addTab("a2", "表字段", "100px");
    tabbar.setContent("a1","div_genaral");
    tabbar.setTabActive("a1");	
    tabbar.attachEvent("onSelect",function(id,pid){
      try{
		if (id=="a1"&&pid=="a2") {
			
			//由a2跳到a1由于没有可以改变的项所以不需要判断
			//if (isFormChanged()==true) {
				//if (confirm("内容已经改变，确实不需要保存吗？")) {
					toTableTab();
					tabbar.setContent(id,"div_genaral");
					return true;
				//} else {
				//	return false;
				//}
			//}else{
			//	toTableTab();
				//tabbar.setContent(id,"div_genaral");
				//return true;
			//}
		
		} else if(id=="a2"&&pid=="a1"){
			if (isFormChanged()==true) {
				if (confirm("当前页面尚未保存，确定要离开吗？")) 
				{
					toColumnTab();
					tabbar.setContent(id,"div_genaral");
					return true;
				} 
				else 
				{	
					//goSubmit()这里弄个返回值，当需要验证的时候验证一下表单，通过则正常切换，不通过则不切换				
					//goSubmit();
					//toColumnTab();
					//tabbar.setContent(id,"div_genaral");
					return false;
				}
			}else{
				toColumnTab();
				tabbar.setContent(id,"div_genaral");
				return true;
			}
						
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
    	if (tabbar.getActiveTab()=="a1") {
    		var cityid = doc.getElementsByName("record(cityCode)")[0].value;
        	var categoryid = doc.getElementsByName("record(categoryId)")[0].value; 
        	var nodeid = cityid+"_"+categoryid;  	  		
        	iframe.contentWindow.goSubmit();
        	parent.setSelectTreeNode(nodeid);
		} else if(tabbar.getActiveTab()=="a2"){
			alert("当前页面没有可保存的项！");
		}
      }catch(e){
			alert("页面还没有加载完成！");
      }        
    } 

    function goCancel(){
        if (tabbar.getActiveTab()=='a1') {
            
        	if (isFormChanged()==true) {
    			if (confirm("内容已经改变，确定不需要保存吗?")) {
    				parent.closedialog(false);
    			}
    		}else{
    			parent.closedialog(false);
    		} 
		}
    	else{
			parent.closedialog(false);
		}   
   	 
   	}  

    function isFormChanged(){
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

    parent.setWindowsTitle("修改资源库");
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
