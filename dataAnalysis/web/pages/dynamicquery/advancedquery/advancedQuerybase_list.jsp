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
<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css"/>

<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>	 
<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.form.js"></script>

<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../dynamicquery/advancedquery.do";	
      var fulllink = contextpath + "dynamicquery/advancedquery.do";		

  		

  		 function getqueryname(){
  		 	var url = contextpath + "query/commSubjectQuery.do?action1=SELPARAM";
  		    var queryname=$NAME("inpqueryname")[0];
  		    dhtmlxAjax.get(url,function(loader){
  			var options = loader.xmlDoc.responseXML.getElementsByTagName("option");
  			for ( var i = 0; i < options.length; i++) {
  				var name = options[i].childNodes[0].firstChild.nodeValue;
  				var value = options[i].childNodes[1].firstChild.nodeValue;
  				 var option=new Option(name,value);
  		         try{
  		        	 queryname.options.add(option);
  		         }catch(e){
  		           
  		         }
  		    }
  		});	
  		 }  		 
  	function checkInput(expressctl){
  		var ctrl=document.getElementsByTagName("input");
  		var ctrl1=document.getElementsByTagName("select");
  		var isInput=false;
  		var temp;
  		for(i=0;i<ctrl.length;i++){
  	  		//alert(ctrl[i].value+":"+ctrl[i].type); 
  	  		temp = (ctrl[i].value).replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
  			if((expressctl.indexOf(ctrl[i].id)==-1 || expressctl.indexOf(ctrl[i].name)==-1) && ''!=temp && ctrl[i].type!='hidden')
  			{
  	  			isInput=true;
  			}
  		}
  		for(i=0;i<ctrl1.length;i++){
  		    temp = (ctrl1[i].value).replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
  			if(expressctl.indexOf(ctrl1[i].name)==-1 && ''!=temp)
  				isInput=true;
  		}

  		if(!isInput){
  			alert("请至少需要输入一个不为空的查询条件或选择一个下拉框的选项");
  			return false;
  		}else
  			return true;
  	}
      
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
         if(checkInput("querymode,query.pageSize"))
 		{
           document.forms[0].submit();
 		}
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
function view(id) {
	var tableId = document.getElementsByName("query.parameters(tableId)")[0].value;
	openWindow("详细信息",contextpath+"query/commSubjectQuery.do" + "?action1=showdetail&uid="+id+"&tableId="+tableId,500,300);
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
	   var win2 = windows.createWindow(winName, 0, 0, 300, 150);
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
	if(checkInput("querymode,query.pageSize"))
		{
       		document.forms[0].submit();
		}		
}

function goAdvancedQuery(queryType){
	var tableid = findSelected("ID","查询");
	window.location.href = fulllink + "?action=BASEQUERY&queryType="+queryType+"&tableId=" + tableid;
}

function closedialog(ret){
	windows.window(winName).close();
}

</script>	
<style type="text/css">
   html, body {width:100%; height:100%;}
</style>   
</head>

<body  onload="showMessage('<bean:write name="advancedQueryForm" property="errorMessage" />')" > 
<div class="special-padding">
<html:form styleId="advancedQueryForm" action="/dynamicquery/advancedquery.do?action=BASEQUERY" method="post">
		<html:hidden property="query.parameters(tableId)"/>
		<html:hidden property="queryName" />
		<html:hidden property="tableId" />
		<html:hidden property="condId" />
		<input type="hidden" name="action1" value="saveparam" />
		<logic:notPresent name="is_spacetable">
		<table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                 	<tr>
                  	<td class="textL" valign="top">
                  	   <table cellpadding="0" cellspacing="0" style="position:relative;top:-1px;height:24px;" width="100%" border="0">
                  	   <tr><td class="textL" style="padding-left:5px;">
                  	   请填写/选择查询条件
                  	   	&nbsp;
                  	<input type="radio" name="querymode" value="base" onclick="toQuery('base')" checked="checked" />简单查询&nbsp;
                  	<input type="radio" name="querymode" value="batch" onclick="toQuery('batch')" />批量查询&nbsp;
                  	<input type="radio" name="querymode" value="advance" onclick="toQuery('simple');" />高级查询&nbsp;
                  	<input type="radio" name="querymode" value="advance" onclick="toQuery('advance');" />复杂高级查询&nbsp;
                  	</td>
                  	<td class="textR">	
                 	</td></tr>
                 	      </table>
                 	    </td>  
               		 </tr>	
             		 </table>
      </logic:notPresent>
      <table id="formTable" width="100%" cellpadding="0" cellspacing="0" class="formTable" >
      <tbody>
         <bean:write name="formStr" filter="false"/>
         
         <tr class="btnTr">
					<td class="textL" colspan="4">
						<font color=red>注意：使用‘%’进行模糊匹配查询，如输入‘国%’ 就是查询第一个字为国的，‘%国%’就是查询含有国字的</font>
					</td>
				</tr>
         <tr class="btnTr">
           <td class="textC" colspan="4">
           <gw:button styleClass="btnStyle" code="" onClick="goQuery()">查询</gw:button>
              &nbsp;
           <gw:button styleClass="btnStyle" code="" onClick="goReset()">重置</gw:button>
           </td>
         </tr>
        </tbody>
      </table>               	
</html:form>
</div>
<script language="javascript">
</script>
</body>
</html>