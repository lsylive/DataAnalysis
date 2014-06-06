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
 <script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.form.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>

<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../dynamicquery/advancedbatch.do";	
      var fulllink = contextpath + "/dynamicquery/advancedbatch.do";		
      
      function gosearchForadvance(action)  {
     	 var rc = getElement("query.pageSize");
         if(rc!=null) {
     	     if(!checkNumeric(rc,"[姣忛〉璁板綍鏁癩",false,true)) return false;
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
         gosearchForadvance("SEARCHCONDITION");
     };
     var ids='<bean:write name="idArr" />';
function view(id) {
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	openWindow("璇︾粏淇℃伅",contextpath+"query/commSubjectQuery.do" + "?action1=showdetail&uid="+id+"&tableId="+tableId+"&idArr="+ids,500,350);
}

function toQuery(type){
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	var queryUrl ;
	if (type=="base") 
	{
		
	} 
	else if(type=="batch")
	{
		queryUrl =contextpath + "dynamicquery/advancedbatch.do?action=TOBASEPAGE&tableId=" + tableId;
	}else if(type=="advance"){
		queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId;
	}
	
	
	window.location.href=queryUrl;
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

function goQuery()  {
	document.forms[0].submit();		
}

function goAdvancedQuery(queryType){
	var tableid = findSelected("ID","鏌ヨ");
	window.location.href = fulllink + "?action=TOADVANCEDPAGE&queryType="+queryType+"&tableId=" + tableid;
}


function showSave(){
  	if($NAME("query.pageCount")[0].value=='0')
  	{
  		alertMsg("娌℃湁鏌ュ埌浠讳綍缁撴灉锛屾棤娉曚繚瀛�);
  		return ;
  	}
  	var divhtmlimport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">璇峰～鍐欒〃涓枃鍚�</td><td><input type="text" name="tablecname" title="涓枃鍚� /></td></tr><tr><td width="40%">琛ㄦ弿杩�</td><td><textarea name="tabledesc" style="width:150px;height:40px;" ></textarea></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=saveResult()" class="btnStyle" name="btnQuery" ><strong>纭畾</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh"><strong>鍏抽棴</strong></a></td></tr></tbody></table>';       
  	openSingleWindow('淇濆瓨鏌ヨ缁撴灉鍒颁釜浜虹┖闂�,divhtmlimport,300,200);
  }
  function saveResult(){
	if(confirm('纭畾瑕佸皢鏌ヨ缁撴灉淇濆瓨鍒颁釜浜虹┖闂�)){
		
  	if(checkIsNull("tablecname")){
  		if(checkCnName())return;
	  	var param =getParam($NAME('tablecname')[0].value,$NAME('tabledesc')[0].value);
  		var url=contextpath+"dynamicquery/advancedquery.do?action=SAVERESULT";
  		closedialog();
  		var loader=dhtmlxAjax.postSync(url,param);
  		var value = loader.xmlDoc.responseText;
  		if(value=='OK'){
  			alertMsg("淇濆瓨鏌ヨ鏁版嵁鎴愬姛!",120);
  		}
  		else if(value=='REQUIRED'){
  			alertMsg("鎮ㄨ繕鏈紑閫氫釜浜虹┖闂�璇峰厛寮��鎵嶈兘浣跨敤!",120);
  		}else
  			alertMsg("淇濆瓨鏌ヨ鏁版嵁澶辫触",120);
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
			alert("璇ヨ〃鍚嶅凡缁忓瓨鍦紝璇烽噸鏂板懡鍚�");
			return true;
		}
		return false;
}
  function closedialog(ret){
	  dhxWins.window("win").close();
  }
  function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>鍏抽棴</strong></a></td></tr></tbody></table>';       
			openSingleWindow('涓汉绌洪棿',alerttab,300,120);
			$$("closebtn").focus();
      }
  
   function alertMsg(msgtxt){
  	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>鍏抽棴</strong></a></td></tr></tbody></table>';       
		openSingleWindow('涓汉绌洪棿',alerttab,300,120);
		$$("closebtn").focus();
  }	

   function getParam(cname,desc){
		//if(confirm('纭畾瑕佸皢鏌ヨ缁撴灉淇濆瓨鍒颁釜浜虹┖闂�)){
		//$NAME("pos")[0].value=pos;
		$NAME("action1")[0].value="saveresult"; 
			
			$NAME("tabcname")[0].value=cname; 
			$NAME("tabdesc")[0].value=desc;
			
			var param=$('#advancedQueryForm').formSerialize();
			//alert(param);
			return param;
			//alert(param);
			//var url=contextpath+"query/subjectBatchQuery.do";
			//var loader=dhtmlxAjax.postSync(url,param);
			//var value = loader.xmlDoc.responseText;
			//if(value=='OK'){
			//	alertMsg("淇濆瓨鏌ヨ鏁版嵁鎴愬姛!",120);
			//}
			//else if(value=='REQUIRED'){
			//	alertMsg("鎮ㄨ繕鏈紑閫氫釜浜虹┖闂�璇峰厛寮��鎵嶈兘浣跨敤!",120);
			//}else
			//	alertMsg("淇濆瓨鏌ヨ鏁版嵁澶辫触",120);
	   
			//document.forms[0].targer="savefrm";
			//document.forms[0].submit();
			//}
	}

   function showExport(){
		var url='<%= CONTEXT_PATH%>dynamicquery/advancedquery.do?action=SHOWEXPORTEXCEL'; 		
		openWindow('瀵煎嚭',url,350,120);
	}
   function getTableId(){
		return $NAME("query.parameters(tableId)")[0].value;
	}

	function getParamStr(){
		return $NAME("query.parameters(pramaStr)")[0].value;
	}
function addFavorite(){
   	var id = findMultiSelected("ID","鏀惰棌");
	if(id == "") return;
	var res = confirm("鏄惁鐪熺殑瑕佹坊鍔犵粨鏋滈泦鍒拌缁嗘瘮杈冮〉闈�");
	if(res == true) {
  		parent.addFavorite($NAME("query.parameters(tableId)")[0].value,id);
	}
   }


</script>	
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>   
</head>

<body onload="showMessage('<bean:write name="advancedQueryForm" property="errorMessage" />')" > 
  <table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                        <tr>
                            <td class="textL">
                            <gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="addFavorite()">娣诲姞鍒拌缁嗛〉</gw:button>
                &nbsp;<gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="showExport()">瀵煎嚭</gw:button>
                 </td>
                     </tr>
                    </table> 
<div class="special-padding">
<html:form action="/dynamicquery/advancedbatch.do?action=SEARCHCONDITION" method="post" styleId="advancedQueryForm">
		<html:hidden property="query.parameters(tableId)"/>
		<html:hidden property="query.parameters(pramaStr)"/>
		<html:hidden property="action1" />
		<input type="hidden" name="tabename" />
		<input type="hidden" name="tabcname" />
		<input type="hidden" name="tabdesc" />
		
		<html:hidden property="query.parameters(type)" value="SEARCHCONDITION"/>
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
</div>
  <%@include file="/common/dialog1.jsp" %>
<script language="javascript">
</script>
</body>
</html>