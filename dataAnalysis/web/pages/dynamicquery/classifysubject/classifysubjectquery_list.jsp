<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源目录查看</title>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css">
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	
    <script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>	
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>

<script>

var contextpath = "<%=CONTEXT_PATH%>";	
var hyperlink = "../query/commSubjectQuery.do";	
var fulllink = contextpath + "query/commSubjectQuery.do";

</script>

</head>
<body>

<html:form action="/query/classifySubjectQuery" >
			<html:hidden property="id"/>
			<html:hidden property="action" />
			<html:hidden property="tabId" />
			<logic:iterate id="tab" name="selTabList">
				<input type="hidden" name="selTable" value="<bean:write name='tab' property='id' />" />
			</logic:iterate>
			<logic:iterate id="param" name="paramList">
			<input type="hidden" name="<bean:write name='param' property='key' />" value="<bean:write name='param' property='value' />" />
			</logic:iterate>
</html:form>


<div id="div_main" style="position:relative;top:0;left:0;width:100%;height:100%;background:white"></div>
  	<div id="div_genaral" style="position:relative;top:0;left:0;width:100%;height:100%;background:white;padding:2px;">
  	<iframe id="totalfrm" name="totalfrm" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>
  	<logic:iterate id="tab" name="tableList">
  	<div id="div_result<bean:write name='tab' property='id' />" style="position:relative;top:0;left:0;width:100%;height:90%;background:white;padding:2px;display: none;">
  		<iframe id="resultfrm<bean:write name='tab' property='id' />" name="resultfrm<bean:write name='tab' property='id' />" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>
  	</logic:iterate>
  	
	 <div id="btnDiv" class="btnDiv" style="text-align:center;position:relative;width:100%;bottom:30px;">
			      <gw:button name="btnAdd" onClick="goBackQuery()">返回</gw:button>
    </div>
<script>     
        var tabbar = new dhtmlXTabBar("div_main", "top");
		tabbar.enableScroll(false);
		tabbar.setSkin('dhx_skyblue');
		tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
		tabbar.enableScroll(true);
		tabbar.addTab("d1", "记录汇总", "100px");
		
		<logic:iterate id="tab" name="tableList">
		var str="<bean:write name='tab' property='cName' />";
		var len=(str.length+2)*12+"px";
	     tabbar.addTab("a<bean:write name='tab' property='id' />", "<bean:write name='tab' property='cName' />", len);
         tabbar.setContent("a<bean:write name='tab' property='id' />", "div_result<bean:write name='tab' property='id' />");
        </logic:iterate>
		tabbar.setContent("d1", "div_genaral");
		tabbar.setTabActive("d1");
		
		tabbar.attachEvent("onSelect",function(id,pid){
      try{
		if (id=="d1") {
			//$NAME("action")[0].value="querycount";	
			//tabbar.setContent(id,"div_genaral");
			//document.forms[0].target='resultfrm';
			//document.forms[0].submit();
			return true;
		
		} else {
		    var selid=id.substr(1);
		    $NAME("tabId")[0].value=selid;
			$NAME("action")[0].value="showlist";
			var frmid='resultfrm'+selid;
			var frmobj=$$(frmid);
			if(frmobj.contentWindow.document.body.innerHTML==''){
				document.forms[0].target=frmid;
				document.forms[0].submit();
			}
			return true;			
		}
	  }catch(e){

	  }finally{return true;}
    });
    $NAME("action")[0].value="querycount";	
    document.forms[0].target='totalfrm';
	document.forms[0].submit();
		
    function showlist(id){	
		
		tabbar.setTabActive("a"+id);
		var frmid='resultfrm'+id;
	    var frmobj=$$(frmid);
	    $NAME("action")[0].value="showlist";
		$NAME("tabId")[0].value=id;
		if(frmobj.contentWindow.document.body.innerHTML==''){
	   	 	document.forms[0].target=frmid;
			document.forms[0].submit();
		}
	}   
	function goBackQuery(){
		$NAME("action")[0].value="SHOWQUERY";
		document.forms[0].target="_self";
		document.forms[0].submit();
	}
		
				
</script>
</body>
</html>