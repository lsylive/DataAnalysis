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

  <script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>	
    <script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>	
     <script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.form.js"></script>
     <script language="javascript" src="<%= CONTEXT_PATH %>/js/Array.js"></script>	
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>

<script>

var contextpath = "<%=CONTEXT_PATH%>";	
var hyperlink = "../query/subjectBatchQuery.do";	
var fulllink = contextpath + "query/subjectBatchQuery.do";
var changetag=false;
var fristload=true;
var array=new Array();

function coverPage(){
	//alert("调用显示方法");
	$("#showWaiting").css("display","block");	
}
function showPage(){
//alert("调用隐藏方法");
$("#showWaiting").css("display","none");
}
</script>

</head>
<body id="abody">
<div id="showWaiting" class="loading">
	<div id="showWaitingPic" class="loading-gif"></div>
</div>
<html:form styleId="subjectBatchQueryForm" action="/query/subjectBatchQuery.do" >
			<html:hidden property="id"/>
			<html:hidden property="action1" />
			<html:hidden property="tableId" />
			<html:hidden property="pos" />
			<logic:iterate id="tab" name="selTabList">
				<input type="hidden" name="selTable" value="<bean:write name='tab' property='id' />" />
			</logic:iterate>
			<html:hidden property="record(paramjson)" />
			<input type="hidden" name="ftype" />
			<input type="hidden" name="tabename" />
			<input type="hidden" name="tabcname" />
			<input type="hidden" name="tabdesc" />
			
</html:form>
<html:form action="/dynamicquery/queryDetail" method="post">
<html:hidden property="action1" value="listbatch" />
<html:hidden property="tabId" />
<input type="hidden" name="idArr" />
</html:form>
<div style="display: none;"><iframe id="downloadframe" name="downloadframe"></iframe></div>

<div id="div_main" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;border: 0px;"></div>

  	<div id="div_genaral" style="position:absolute;top:0;left:0;width:100%;height:90%;background:white;border: 0px;">
  	<iframe id="tablefrm" name="tablefrm" align="top" frameborder="0" scrolling="auto" width="100%" height="100%" ></iframe>
  	</div>
  	<!-- <div id="div_result" style="display:none;position:relative;top:0;left:0;width:100%;height:90%;background:white;padding:2px;">
  	<iframe id="resultfrm" name="resultfrm" align="top" frameborder="0" scrolling="auto" width="100%" height="100%"></iframe>
  	</div>-->
  	<logic:iterate id="tab" name="tableList">
  	<div id="div_result<bean:write name='tab' property='id' />" style="position:relative;top:0;left:0;width:100%;height:100%;background:white;display: none;border: 0px;">
  		<iframe id="resultfrm<bean:write name='tab' property='id' />" name="resultfrm<bean:write name='tab' property='id' />" align="top" frameborder="0" scrolling="no" width="100%" height="100%"></iframe>
  	</div>
  	</logic:iterate>
  	  <div id="div_favor" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  		<iframe id="favorfrm" name="favorfrm" align="top" frameborder="0" scrolling="auto" width="100%" height="100%" ></iframe>
  		</div>
	<div id="btnDiv" class="btnDiv">
			      <gw:button name="btnAdd" onClick="goBackQuery()">返回</gw:button>
    </div>
<script>     
        var tabbar = new dhtmlXTabBar("div_main", "top");
		//tabbar.enableScroll(false);
		tabbar.enableAutoReSize();
		tabbar.setSkin('dhx_skyblue');
		tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
		tabbar.addTab("d1", "记录汇总", "100px");
		
		var str;
		var len;
		<logic:iterate id="tab" name="tableList">
		str="<bean:write name='tab' property='cName' />";
		len=(str.length+2)*12+"px";
	     tabbar.addTab("a<bean:write name='tab' property='id' />", "<bean:write name='tab' property='cName' />", len);
         tabbar.setContent("a<bean:write name='tab' property='id' />", "div_result<bean:write name='tab' property='id' />");
        </logic:iterate>
      
  		tabbar.addTab("favor","收藏详细页");
        tabbar.setContent("favor","div_favor");
	    tabbar.setContent("d1", "div_genaral");
		tabbar.setTabActive("d1");
        
		tabbar.enableScroll(true);
		
		tabbar.attachEvent("onSelect",function(id,pid){
      try{
		if (id.indexOf("a")<0 && id!='favor') {
			return true;
		} else if(id!='favor'){
		    var selid=id.substr(1);
		    $NAME("tabId")[0].value=selid;
		    $NAME("tableId")[0].value=selid;
			$NAME("action1")[0].value="querycount";	
			//tabbar.setContent(id,"div_genaral");
			var frmid='resultfrm'+selid;
			var frmobj=$$(frmid);
			if(frmobj.contentWindow.document.body.innerHTML==''){
				coverPage();
				document.forms[0].target=frmid;
				document.forms[0].submit();
			}
			return true;			
		}else{
			showFavorite();
			return true;
		}
	  }catch(e){

	  }finally{return true;}
    });
    $NAME("tabId")[0].value="<bean:write name='fristtabId' />";
    $NAME("action1")[0].value="totalcount";	
    document.forms[0].target='tablefrm';
	document.forms[0].submit();


	function createDivWithIframe(){
		$("<div id='openDiv' style='position:relative;top:0;left:0;width:100%;height:100%;display: none;'><iframe id='content' name='content' align='top' frameborder='0' scrolling='yes' width='100%' height='100%' ></iframe></div>").appendTo("body"); 		
	}
		
    function showlist(id){		
      	tabbar.setTabActive("a"+id);
		var frmid='resultfrm'+id;
	    var frmobj=$$(frmid);
	    $NAME("action1")[0].value="querycount";
		$NAME("tableId")[0].value=id;
		if(frmobj.contentWindow.document.body.innerHTML==''){
			coverPage();
	   	 	document.forms[0].target=frmid;
			document.forms[0].submit();
		}	
	}  
	function showresult(pos){
		createDivWithIframe();
		$NAME("action1")[0].value="showresult";
		$NAME("pos")[0].value=pos;
		openWindowById('条件查询结果','openDiv',1000,450);
		var frmid='content';
	    var frmobj=$$(frmid);
	   
	   	document.forms[0].target=frmid;
		document.forms[0].submit();

		//document.forms[0].target='content';
		//document.forms[0].submit();
	}

	function goBackQuery(){
		$NAME("action1")[0].value="LISTBATCH";
		document.forms[0].target="_self";
		document.forms[0].submit();
	}
	function addFavorite(tabId,ids){
		
		var idArrctrl=$NAME("idArr")[0];
		var idArr=idArrctrl.value;
		var idsArray=ids.split(";");
		var tmpstr='';
		for(i=0;i<idsArray.length;i++){
			tmpstr=tabId+"-"+idsArray[i];
			array.append(tmpstr,true);
		}
		changetag=true;
	}
	function showFavorite(){
		var idArraystr=array.toString();
		$NAME("idArr")[0].value=idArraystr;
		if(changetag || fristload){
			document.forms[1].target='favorfrm';
			document.forms[1].submit();
			fristload=false;
			changetag=false;
		}
	}
	function showparamresult(tabId,pos){

		createDivWithIframe();
		
		$NAME("tabId")[0].value=tabId;
		$NAME("tableId")[0].value=tabId;
		$NAME("action1")[0].value="showresult";
		$NAME("pos")[0].value=pos;

		var frmid='content';
	    var frmobj=$$(frmid);
	    openWindowById('条件查询结果','openDiv',1000,450);
	    document.forms[0].target=frmid;
		document.forms[0].submit();
		
	}
	function delFavorite(ids){
    	if(array.contains(ids))
    		array.remove(ids);		
    }	

	function downloadExcel(ftype,pos){
		$NAME("pos")[0].value=pos;
    	$NAME("ftype")[0].value=ftype;
    	$NAME("action1")[0].value="EXPORTEXCEL";
    	document.forms[0].target='downloadframe';
		document.forms[0].submit();
    }

    function exportAllExcell(id){
       
    	 $NAME("tabId")[0].value=id;
		 $NAME("tableId")[0].value=id;
		 $NAME("action1")[0].value="EXPORTALLEXCEL";
        
    	document.forms[0].target='downloadframe';
		document.forms[0].submit();
    }
    function saveResult(cname,desc,pos){
    	//if(confirm('确定要将查询结果保存到个人空间')){
    	$NAME("pos")[0].value=pos;
    	$NAME("action1")[0].value="saveresult"; 
  		//$NAME("tabename")[0].value=ename;
  		$NAME("tabcname")[0].value=cname; 
  		$NAME("tabdesc")[0].value=desc;
  		var param=$('#subjectBatchQueryForm').formSerialize();
  		return param;
  		//alert(param);
  		//var url=contextpath+"query/subjectBatchQuery.do";
  		//var loader=dhtmlxAjax.postSync(url,param);
  		//var value = loader.xmlDoc.responseText;
  		//if(value=='OK'){
  		//	alertMsg("保存查询数据成功!",120);
  		//}
  		//else if(value=='REQUIRED'){
  		//	alertMsg("您还未开通个人空间,请先开通才能使用!",120);
  		//}else
  		//	alertMsg("保存查询数据失败",120);
       
  		//document.forms[0].targer="savefrm";
  		//document.forms[0].submit();
  		//}
    }
	 function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
			openSingleWindow('个人空间',alerttab,300,120);
			$$("closebtn").focus();
      }	
      var mainbody=window.document.body;
    if(window.addEventListener) {
 	     window.addEventListener("resize",goResize,false); 
    }
    else {
 	     window.attachEvent('onresize',goResize);
    }
     function closedialog(ret){
	     dhxWins.window(winName).close();
	   
	  }
    
    var bDiv=window.document.getElementById('btnDiv');
    //var btbl=window.document.getElementById('tblForm');
    var fDiv=window.document.getElementById('div_main');
    function goResize(){
       fDiv.style.width=mainbody.offsetWidth+"px";
       bDiv.style.width=mainbody.offsetWidth+"px";
       fDiv.style.height=mainbody.offsetHeight-bDiv.clientHeight+"px";
       bDiv.style.top=fDiv.offsetHeight+fDiv.offsetTop*2+"px";
    }
    goResize();   
      </script>	
       <%@include file="/common/dialog1.jsp" %>
 	
</body>
</html>