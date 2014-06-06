<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>	 
<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
 <script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.form.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>/js/Array.js"></script>
<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../dynamicquery/advancedquery.do";	
      var fulllink = contextpath + "dynamicquery/advancedquery.do";		
      var changetag=false;
      var fristload=true;
      var array=new Array();
      function gosearch()  {
    	  var rc = getElement("query.pageSize");
        if(rc!=null) {
    	     if(!checkNumeric(rc,"[每页记录数]",false,true)) return false;
        }
        document.forms[0].action = hyperlink + "?action=BASEQUERY";
        document.forms[0].target = "_self";
        document.forms[0].submit();
    } 
      function gosearchForadvance(action)  {
     	 var rc = getElement("query.pageSize");
         if(rc!=null) {
     	     if(!checkNumeric(rc,"[每页记录数]",false,true)) return false;
         }
         document.forms[0].action = hyperlink + "?action="+action;
         document.forms[0].target = "_self";
         document.forms[0].submit();

     };

     function queryBase(field)  {
         var order = getElement("query.order");
         var desc  = getElement("query.orderDirection");
     	var pn =  getElement("query.pageNumber");

         order.value = field;
         if(desc.value == "") desc.value = "asc";
         else if(desc.value == "asc") desc.value = "desc"; 
         else desc.value = "asc";
         pn.value="1";
         gosearchForadvance("BASEQUERY");
     };

     var ids='<bean:write name="idArr" />';
function view(id) {
	<logic:notPresent name="is_spacetable">
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	//window.open(contextpath+"query/commSubjectQuery.do" + "?action1=showdetail&uid="+id+"&tableId="+tableId+"&idArr="+ids, "测试", "height=400,width=700");
	openWindow("详细信息",contextpath+"query/commSubjectQuery.do" + "?action1=showdetail&uid="+id+"&tableId="+tableId+"&idArr="+ids,500,300);
	</logic:notPresent>
}

function toQuery(type){
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	var queryUrl ;
	if(type=='batch')
	{
		queryUrl =contextpath + "dynamicquery/advancedbatch.do?action=TOBASEPAGE&tableId=" + tableId;
		window.location.href=queryUrl;
	}else if(type=='advance'){
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId;
		window.location.href=queryUrl;
	}else if(type=='simple'){
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId+"&action1=listsimple";
		window.location.href=queryUrl;
	}	
}

function renew()  {
	  var order =   getElement("query.order");                  order.value="";
	  var desc =    getElement("query.orderDirection");         desc.value="";
	  var pn =    getElement("query.pageNumber");             pn.value="1";
	  var ps =    getElement("query.pageSize");               ps.value="10";
	  var v0 =    getElement("query.parameters(cityCode)");      v0.value="";
	  var v1 =    getElement("query.parameters(tradeId)");    v1.value="";
	  var v2 =    getElement("query.parameters(categoryId)");   v2.value="";
	  var v3 =    getElement("query.parameters(type)");   v2.value="";
    gosearch();
}

var windows; 
var winName = "wind";
function openWindow(title,url,w,h){ 
	windows = new dhtmlXWindows();
	windows.enableAutoViewport(true);
	windows.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
	windows.setSkin("dhx_skyblue");
	var win = windows.createWindow(winName,0,0,w,h);
	win.allowPark();
	win.setText(title);
	win.setModal(true);
	win.centerOnScreen();
	win.attachURL(url);
}

function openSingleWindow(title,divhtml){
	windows = new dhtmlXWindows();
	var win2 = windows.createWindow(winName, 0, 0, 300, 180);
    win2.setText(title);
    win2.keepInViewport(true);
    win2.setModal(true);
    win2.centerOnScreen();
    win2.button("minmax1").hide();
    win2.button("minmax2").hide();
    win2.button("park").hide();
    win2.attachHTMLString(divhtml);
    return win2;    
 }

function goQuery()  {
    document.forms[0].submit();	
}

function goAdvancedQuery(queryType){
	var tableid = findSelected("ID","查询");
	window.location.href = fulllink + "?action=BASEQUERY&queryType="+queryType+"&tableId=" + tableid;
}

function closedialog(ret){
	windows.window(winName).close();
}
function goBack(){
	var url = hyperlink+"?action=TOADVANCEDPAGE";
	document.forms[0].action=url;
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
function delFavorite(ids){
	if(array.contains(ids))
		array.remove(ids);		
}

function addFav(){
   	var id = findMultiSelected("ID","收藏");
	if(id == "") return;
	var res = confirm("是否真的要添加结果集到详细比较页面?");
	if(res == true) {
  		addFavorite($NAME("query.parameters(tableId)")[0].value,id);
	}
   }

function showSave(){
  	if($NAME("query.pageCount")[0].value=='0')
  	{
  		alertMsg("没有查到任何结果，无法保存");
  		return ;
  	}
  	var divhtmlimport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请填写表中文名:</td><td><input type="text" name="tablecname" title="中文名" /></td></tr><tr><td width="40%">表描述:</td><td><textarea name="tabledesc" style="width:150px;height:40px;" ></textarea></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=saveResult()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh"><strong>关闭</strong></a></td></tr></tbody></table>';       
  	openSingleWindow('保存查询结果到个人空间',divhtmlimport,300,300);
  }
  function saveResult(){
	if(confirm('确定要将查询结果保存到个人空间')){
		
  	if(checkIsNull("tablecname")){
  	  	if(checkCnName())return;
	  	var param =getParam($NAME('tablecname')[0].value,$NAME('tabledesc')[0].value);
  		var url=contextpath+"dynamicquery/advancedquery.do?action=SAVERESULT";
  		closedialog();
  		var loader=dhtmlxAjax.postSync(url,param);
  		var value = loader.xmlDoc.responseText;
  		if(value=='OK'){
  			alertMsg("保存查询数据成功!",120);
  		}
  		else if(value=='REQUIRED'){
  			alertMsg("您还未开通个人空间,请先开通才能使用!",120);
  		}else
  			alertMsg("保存查询数据失败",120);
  		//parent.saveResult($NAME('tableename')[0].value,$NAME('tablecname')[0].value,$NAME('tabledesc')[0].value,$NAME("pos")[0].value);
  		
  	}
  	
	  }
  }

  function checkCnName(){
	  var param ="cnName="+$NAME('tablecname')[0].value;
	  var url=contextpath+"dynamicquery/advancedquery.do?action=CHECKCNNAME";
	  var loader=dhtmlxAjax.postSync(url,param);
		var value = loader.xmlDoc.responseText;
		if(value=='false'){
			alert("该表名已经存在，请重新命名!");
			return true;
		}
		return false;

	  }
	function showExport(){
		var url=fulllink+'?action=SHOWEXPORTEXCEL'; 		
		openWindow('导出',url,350,120);
	}
  
  function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
			openSingleWindow('个人空间',alerttab,300,120);
			$$("closebtn").focus();
      }
  
   function alertMsg(msgtxt){
  	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
		openSingleWindow('个人空间',alerttab,300,120);
		$$("closebtn").focus();
  }	

function getParam(cname,desc){
	$NAME("action1")[0].value="saveresult"; 
		$NAME("tabcname")[0].value=cname; 
		$NAME("tabdesc")[0].value=desc;
		
		var param=$('#advancedQueryForm').formSerialize();
		return param;
}

function getTableId(){
	return $NAME("query.parameters(tableId)")[0].value;
}

function getParamStr(){
	return $NAME("query.parameters(pramaStr)")[0].value;
}
</script>	
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>   
</head>

<body onload="showMessage('<bean:write name="advancedQueryForm" property="errorMessage" />')" > 

<div id="div_main" style="position:absolute;top:0;left:0;width:100%;height:90%;background:white;border: 0px;"></div>

  	<div id="div_genaral" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;overflow: auto;">
  	 <div class="special-padding"> 
  	<html:form action="/dynamicquery/advancedquery.do?action=BASEQUERY" method="post" styleId="advancedQueryForm">
		<html:hidden property="query.parameters(tableId)"/>
		<html:hidden property="query.parameters(pramaStr)"/>
		<html:hidden property="action1" />
		<input type="hidden" name="tabename" />
		<input type="hidden" name="tabcname" />
		<input type="hidden" name="tabdesc" />
		<logic:notPresent name="is_spacetable">
        <table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
           <tr>
                            <td class="textL">
                            <gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="addFav()">添加到详细页</gw:button>
			     			 &nbsp;<gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="showExport()">导出</gw:button>
                 			</td>
          </tr>
        </table> 
       </logic:notPresent>
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" 
	       name="advancedQueryForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	       fixRows="true" rowDblClick="view('{ID}')">
    <header style="" height="27"  />
 	<rooter height="30" width="100%" showType="all" style="font-size:9pt;"  textStyle="text-align:center;" />      	
</gw:grid2>                 	
</html:form>

<html:form action="/dynamicquery/queryDetail" method="post" style="display:none">
<html:hidden property="action1" value="listbatch" />
<html:hidden property="tabId" />
<input type="hidden" name="idArr" />
</html:form>
</div>
</div>
  	<logic:notPresent name="is_spacetable">
	<div id="div_favor" style="position:absolute;top:0;left:0;width:100%;height:100%;background:white;padding:0px;border: 0px;">
  		<iframe id="favorfrm" name="favorfrm" align="top" frameborder="0" scrolling="auto" width="100%" height="100%" ></iframe>
  	</div>
  	</logic:notPresent>
	<div id="bottom_div" style="text-align:center;position:absolute;width:100%;bottom:10px;">
			      <gw:button name="btnAdd" onClick="goBack()">返回</gw:button>
	</div>


<script language="javascript">
var tabbar = new dhtmlXTabBar("div_main", "top");

tabbar.setSkin('dhx_skyblue');
tabbar.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxTabbar/codebase/imgs/");
//tabbar.enableScroll(true);
tabbar.enableAutoReSize();
tabbar.addTab("d1", "详细结果", "100px");

<logic:notPresent name="is_spacetable">
tabbar.addTab("favor","收藏详细页");
tabbar.setContent("favor","div_favor");
</logic:notPresent>
tabbar.setContent("d1", "div_genaral");
tabbar.setTabActive("d1");
//$NAME("action1")[0].value="SHOWQUERY";
//document.forms[0].target='resultfrm';
//document.forms[0].submit();

tabbar.attachEvent("onSelect",function(id,pid){
try{
if(id="favor"){
	showFavorite();
	return true;
}
}catch(e){

}finally{return true;}
});
</script>
</body>
</html>