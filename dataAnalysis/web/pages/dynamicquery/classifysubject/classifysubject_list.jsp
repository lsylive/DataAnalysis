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
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	<script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>/js/ctrl_util.js"></script>
	<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/classifySubjectQuery.do";	
      var fulllink = contextpath + "/query/classifySubjectQuery.do";		
      


function goDel()  {
	var id = findMultiSelected("ID","删除");
	if(id == "") return;
	var res = confirm("是否真的要删除该主题?");
	if(res == true) {
      document.forms[0].action = hyperlink + "?action=delete&ids=" + id;
      document.forms[0].target = "_self";
      document.forms[0].submit();
    }
}

function goModify()  {
	var id = findSelected("ID","修改");
	if(id == "") return;
    var urlink=fulllink+'?action=EDIT&id='+id;
    editMode="EDIT";
	openWindow('修改主题',urlink,800,450);
}



function goAdd()  {
	openWindow('添加主题',fulllink+'?action=ADD',800,450);
}

function renew()  {
	document.forms[0].reset();
	var order = getElement("query.order");                  order.value="";
	var desc =  getElement("query.orderDirection");         desc.value="";
	var pn =    getElement("query.pageNumber");             pn.value="1";
	var ps =    getElement("query.pageSize");               ps.value="10";
	var v0 =    getElement("query.parameters(roleCode)");   v0.value="";
	var v1 =    getElement("query.parameters(roleName)");   v1.value="";
	var v2 =    getElement("query.parameters(roleStatus)"); v2.value="";
    //gosearch();
}

function goQuery()  {
	var pn = getElement("query.pageNumber");             
	pn.value="1";
    gosearch();
}

function closedialog(ret){
	     dhxWins.window(winName).close();
	     if(ret=='true') {
       	 gosearch();
       }	
	  }
	  function showquery(id){
	     if(id==null || id == "") {
	     	id = findSelected("ID","简单查"); 	
			if(id == "") return;
	     }
	  	 var urllink='<%=CONTEXT_PATH%>/query/classifySubjectQuery.do?action=SHOWQUERY&id='+id;
	  	 window.location.href=urllink;
	  	 //openWindow('主题查询',urllink,800,450);
	  }
	   function showbatch(id){
	    if(id==null || id == "") {
	     	id = findSelected("ID","批量查");
			if(id == "") return;
	     }
	  	 var urllink='<%=CONTEXT_PATH%>/query/subjectBatchQuery.do?action=listclassify&id='+id;
	  	 window.location.href=urllink;
	  	 //openWindow('主题查询',urllink,800,450);
	  }
	   function init(){
   		showMessage('<bean:write name="classifySubjectQueryForm" property="errorMessage" />');
   }
   	function getCategorys(){
	var trade = $NAME("query.parameters(tradeId)")[0];
	var tradeId = trade.options[trade.selectedIndex].value;
	var category = $NAME("query.parameters(categoryId)")[0];

	category.length=1;
	var url = contextpath + "dynamicquery/advancedquery.do?action=SELECT&tradeId="+tradeId;
	dhtmlxAjax.get(url,function(loader){
		var options = loader.xmlDoc.responseXML.getElementsByTagName("option");
		for ( var i = 0; i < options.length; i++) {
			var name = options[i].childNodes[0].firstChild.nodeValue;
			var value = options[i].childNodes[1].firstChild.nodeValue;
			 var option=new Option(name,value);
	         try{
	        	 category.options.add(option);
	         }catch(e){
	           
	         }
	    }
	});	
	}
	</script>	
   

</head>

<body onload="init()">
<html:form action="/query/classifySubjectQuery.do" method="post">

<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%" >
  <tr height="*">
    <td align="center" valign="top">
      <table width="100%" cellpadding="1" cellspacing="1" class="formTable" >
         <tr>
           <td width=20%>行业：</td>
           
           <td width=30%>
             	<html:select property="query.parameters(tradeId)" onchange="getCategorys()">
             	<option value="">全部</option>
             		<html:optionsCollection property="codeSets(Trade)" label="codeName" value="value"/>
             	</html:select>
           </td>
          
           <td width=20%>分类：</td>
           <td width=30%>
             	<html:select property="query.parameters(categoryId)">
             	<option value="">全部</option>
             		<html:optionsCollection property="codeSets(Categorys)" label="codeName" value="value"/>
             	</html:select>
           </td>
          
         </tr>
         
       
         <tr class="btnTr">
           <td class="textC" colspan="4">
              	<a href="#" class="btnStyle" name="btnQuery" onclick="goQuery()"><strong>查询</strong></a>
              &nbsp;
           	<a href="#" class="btnStyle" name="btnRefresh" onclick="renew()"><strong>重置</strong></a>
         
           </td>
         </tr>
           
      </table>
      <table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
          <tr>
            <td width="100%" valign="top">
       	  	 <table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                <tr >
                  <td>
        			<a href="#" class="sbuBtnStyle" onclick="showquery('')"><strong><span class="addIcon">&nbsp;</span>简单查</strong></a>
        			<a href="#" class="sbuBtnStyle" onclick="showbatch('')"><strong><span class="addIcon">&nbsp;</span>批量查</strong></a>
                  </td>
                </tr>	
              </table>
             
                    <html:hidden property="query.order" />
                  	<html:hidden property="query.orderDirection" />
                  	<html:hidden property="query.pageNumber" />
                  	<html:hidden property="query.recordCount"/>
                  	<html:hidden property="query.pageCount" />
                  	
        
<gw:grid2 border="0" cellPadding="1" cellSpacing="1" width="100%" styleClass="listTable" secondRowStyle="background:#e3effe"
	       name="classifySubjectQueryForm" property="query.recordSet" parameters="query" rowEventHandle="false" schema="common" 
	       fixRows="true" >
    <header style="" height="27"  />
    <column width="3%" itemType="checkbox" property="ID" />
    <column width="25%" name="主题名称" itemType="hyperlink" href="#"  onClick="showquery('{ID}')" property="SUBNAME"  align="center" 
    	         headerOnClick="query('subCode')" headerOnMouseOver="headerOver(this)" 
    	         headerOnMouseOut="headerOut(this)" />
	
    <column width="30%" name="主题说明" property="SUBDESC"   
                 itemType="hyperlink" headerOnClick="query('subName')" headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    	         headerOnMouseOut="headerOut(this)"/>
     <column width="17%" name="创建时间" property="CREATETIME"   
    	         headerOnClick="query('subName')" headerOnMouseOver="headerOver(this)" headerOnMouseOut="headerOut(this)"/>
    <column width="10%" name="简单查" value="查询" itemType="hyperlink" href="#"  onClick="showquery('{ID}')" />
   <column width="10%" name="批量查" value="查询" itemType="hyperlink" href="#"  onClick="showbatch('{ID}')" />
    	         
   <rooter height="30" width="100%" showType="all" />      
</gw:grid2>		


             </td>
           </tr>
       </table>
    </td>
  </tr>
</table>


</html:form>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>