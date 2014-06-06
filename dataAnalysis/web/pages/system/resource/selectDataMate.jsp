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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css"/>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
  <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	

<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "system/ResourceColumn.do";		

	function goCancel(){
		 parent.closeWindow();
	}

	function setSelectedItem(){
		if(parent.getSelectedId()!=""){	
			tree.selectItem(parent.getSelectedId());
		}
	}

</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body >
<div id="formDiv" class="formDiv" style="text-align:left!important;background:#d6e8fe;">
<div id="treepanel" style="background-color:#e8f0f8;height:100%;"></div>		
</div>
<div id="btnDiv" class="btnDiv">
			      <gw:button name="btnAdd" onClick="goSubmit()">确定</gw:button>
				    &nbsp;
				    <gw:button name="btnCancel" onClick="goMove()">清空</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>

</div>

<%@include file="/common/resize.jsp" %>
<script type="text/javascript">
var tree=new dhtmlXTreeObject("treepanel","100%","100%",0);
tree.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTree/codebase/imgs/");
tree.setSkin("dhx_blue");
tree.loadXML("<%=CONTEXT_PATH%>system/ResourceColumn.do?action=TREE",setSelectedItem);
function goSubmit(){
	var id = tree.getSelectedItemId();
	var text = tree.getSelectedItemText();
	if(id==null||id==""||id=="0"){
		alert("请选择一个合适的数据元！");
		return;
	}else if(id.indexOf("category_")>-1){
		alert("分类不能作为数据元，请选择一个合适的数据元！");
		return;
	}else{
		parent.setDatameta(id,text);
		parent.setFormByDataMeta();
		parent.closeWindow();
	}
}

function goMove(){
	parent.goMove();
	parent.closeWindow();
}
</script>  
</body>
</html>
