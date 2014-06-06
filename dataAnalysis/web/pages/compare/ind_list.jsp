<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
  	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
<script type="text/javascript" src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
  <script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "querycfg/commSubject.do";		
	  var mutilSelect=<%=request.getParameter("mutilSelect")%>
	function goCancel(){
		parent.closedialog(false);
	}
	function addTable(){
		
	}
	
	</script>
	 <style type="text/css">
   html, body {width:100%; height:100%;}
</style>

</head>

<body>
  <html:form method="post" action="/querycfg/commSubject.do" styleClass="special-form">
  	<html:hidden property="selectedItemId" />
  	<html:hidden name="querySubjectForm" property="id" />
      
  	</html:form>
  <div id="formDiv" class="formDiv" style="border:1px solid #b1b1b1;background:#d6e8fe;text-align:left!important;">
</div>
   <div id="btnDiv" style="position: absolute;left: 2px;height: 25px;text-align: center;">
			      <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
    </div>
	  	
	  <script type="text/javascript">
var tree = new dhtmlXTreeObject("formDiv","100%","100%",0);
tree.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/imgs/");
if(mutilSelect){
	tree.enableCheckBoxes(true,false);
	tree.enableThreeStateCheckboxes(true);
}
tree.attachEvent("onCheck", function(id,state){
		enableSubmit(id);
	});
	tree.attachEvent("onClick", function(id,state){
		enableSubmit(id);
	});
	<logic:iterate id="tree" name="treeList">
	  	tree.insertNewChild(<logic:equal value="0" name="tree" property="parentId"><bean:write name="tree" property="parentId" /></logic:equal><logic:notEqual value="0" name="tree" property="parentId">'<bean:write name="tree" property="parentId" />'</logic:notEqual>,'<bean:write name="tree" property="id" />','<bean:write name="tree" property="label" />',<bean:write name="tree" property="action" />,<bean:write name="tree" property="imageClose" />,<bean:write name="tree" property="imageOpen" />,<bean:write name="tree" property="imageLeaf" />,'<logic:equal value="1" name="tree" property="hasChild">CHILD</logic:equal><logic:equal value="1" name="tree" property="isCheck">,CHECKED</logic:equal>');
	  </logic:iterate>
	  tree.openAllItems();
//tree.attachEvent("onSelect", function(id){
//    	 enableSubmit(id);
//    });
	
    function enableSubmit(id){
    	if(mutilSelect){
			var selids=tree.getAllChecked();
			var ids=selids.split(',');
			var cansubmit=false;
			for(i=0;i<ids.length;i++)
			{
				if(ids[i].indexOf('ind')>-1){
					cansubmit=true;
					break;
				}
			}
		}else
		{
			var selid=tree.getSelectedItemId();
			if(selid!='')
				cansubmit=true;
		}
		if(cansubmit){
			disableAnchor($NAME("btnAdd")[0],false);
			//document.getElementsByName("btnAdd")[0].disabled="";
		}else{
			disableAnchor($NAME("btnAdd")[0],true);
			//document.getElementsByName("btnAdd")[0].disabled="disabled";
		}
	}
    function goSubmit(){
    	if(mutilSelect){
    		var selIds=tree.getAllChecked();
    		var selids='';
    		if(selIds!=''){
    			var selTxts="";
    		var IdArr=selIds.split(",");
    		for(i=0;i<IdArr.length;i++){
    			var txt=tree.getItemText(IdArr[i]);
    			if(IdArr[i].indexOf('cata')==-1){
    				selids+=IdArr[i]+",";
    				selTxts+=tree.getItemText(IdArr[i])+",";
    			}
    		}
    		//alert(selids);
    		//alert(selTxts);
    		parent.addIndArrToList(selids,selTxts);
		 	    
    		}else{
    			alert('请选择至少一个数据表');
    		}
    	}else{
    		var selid=tree.getSelectedItemId();
    		var seltxt=tree.getSelectedItemText(); 
    		if(selid!=undefined && selid!=''){
    			parent.addIndArrToList(selid,seltxt);
    			
    		}else{
    			alert('请选择至少一个数据表');
    		}
    	}
	}
	function disableAnchor(obj, disable){
  if(disable){
    var href = obj.getAttribute("href");
    if(href && href != "" && href != null){
       obj.setAttribute('href_bak', href);
    }
    obj.removeAttribute('href');
    obj.style.color="gray";
  }
  else{
    obj.setAttribute('href', obj.attributes['href_bak'].nodeValue);
    obj.style.color="#000";
  }
}
	//初始化有表就可以提交
	enableSubmit('1');
</script>
<script type="text/javascript">
	  var mainbody=window.document.body;
    if(window.addEventListener) {
 	     window.addEventListener("resize",goResize,false); 
    }
    else {
 	     window.attachEvent('onresize',goResize);
    }
    
    var fDiv=window.document.getElementById('formDiv');
    var bDiv=window.document.getElementById('btnDiv');
    
    function goResize(){
       fDiv.style.width=mainbody.offsetWidth-fDiv.offsetLeft*3+"px";
       bDiv.style.width=mainbody.offsetWidth-bDiv.offsetLeft*3+"px";
       fDiv.style.height=mainbody.offsetHeight-bDiv.offsetHeight-fDiv.offsetTop*3+"px";
       bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*3+"px";
    }
    goResize();   
</script> 
</body>
</html>
