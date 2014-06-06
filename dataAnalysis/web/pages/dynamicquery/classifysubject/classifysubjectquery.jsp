<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html-el.tld" prefix="html-el" %> 
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw"%>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css"/>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css"/>
		
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.form.js"></script>
		
		

		<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/classifySubjectQuery.do";	
      var fulllink = contextpath + "/query/classifySubjectQuery.do";		
      var divhtmlexport='<table cellpadding="1" cellspacing="1" width="98%" class="formTable"><tbody><tr><td width="20%">请填写查询名称:</td><td><input type="text" name="queryname" /></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=importparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh"><strong>关闭</strong></a></td></tr></tbody></table>';       
      var divhtmlimport='<table cellpadding="1" cellspacing="1" width="98%" class="formTable"><tbody><tr><td width="20%">请选择查询条件:</td><td><select name="queryname"></select></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=exportparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
      
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
	  function goQuery(){
	 	 document.getElementsByName("action")[0].value="overview";
	  	if(checkIfInput("selTable,id,action,querySql,tabId") && checkIsNull("selTable"))
		{
			//parent.openWindow('查询结果',850,500);
			//document.forms[0].target="content";
			//document.forms[0].action='commSubjectQuery.do';
			document.forms[0].submit();
		}
	  }
	  function showquerymode(mode){
	  	var id=$NAME("id")[0].value; 
	  	if(mode==1)
	  		window.location.href='<%=CONTEXT_PATH%>query/commSubjectQuery.do?action=showquery&id='+id;
	  	else if(mode==2)
	  		window.location.href='<%=CONTEXT_PATH%>query/subjectBatchQuery.do?action=listcomm&id='+id;
	  	
	  }
	   function showimport(){
	 	openSingleWindow('导入查询条件',divhtmlimport);
	 }
	 function showexport(){
	 	openSingleWindow('导出查询条件',divhtmlexport);
	 }
	 function importparam(){
	 	if(checkIsNull("queryname"))
	 	{
	 		document.forms[0].target="frm";
	 		$NAME("action")[0].value="saveparam";
	 		document.forms[0].submit();
	 	}
	 }
	 function exportparam(){
	 	var url = contextpath + "query/commSubjectQuery.do?action=EXPPARAM&name="+escape($NAME("queryname")[0].value);
	    var queryname=$NAME("queryname")[0];
	    dhtmlxAjax.get(url,function(loader){
		var options = loader.xmlDoc.responseXML.getElementsByTagName("PARAM");
		for ( var i = 0; i < options.length; i++) {
			var name = options[i].childNodes[0].firstChild.nodeValue;
			var value = options[i].childNodes[1].firstChild.nodeValue;
	         try{
	        	 $NAME(name)[0].value=value;
	         }catch(e){
	           
	         }
	    }
	});	
	  }
   
	</script>
	<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>
	<style type="text/css">
   html, body {width:100%; height:100%;}
   
</style>
	</head>

	<body topmargin="0" leftmargin="0">
	
	<div class="special-padding">
		
		<html:form action="/query/classifySubjectQuery.do" method="post">
			<html:hidden name="classifySubjectQueryForm" property="id" />
			<html:hidden property="action" />
			<html:hidden property="querySql" />
			
			<html:hidden property="tabId" />
					<table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                 	<tr>
                  	<td class="textL" valign="top">
                  	   <table cellpadding="0" cellspacing="0" style="position:relative;top:-1px;height:24px;" width="100%" border="0">
                  	   <tr><td class="textL" style="padding-left:5px;">
                  	   	请填写/选择查询条件
                  	   	&nbsp;
                  	   	<input type="radio" name="querymode" value="1" onclick="showquerymode(1)" checked="checked" />简单查询
                  	   	&nbsp;
                  	   	<input type="radio" name="querymode" value="2" onclick="showquerymode(2)" />批量查询
                 	      </td>
                  	   <td class="textR">
                  	   	<gw:button styleClass="sbuBtnStyle" onClick="showimport()" title="从个人空间中导入查询条件" icon="subIcon">打开</gw:button>
                  	   	<gw:button styleClass="sbuBtnStyle" onClick="showexport()" title="保存当前查询条件到个人空间中" icon="subIcon">保存</gw:button>
                 	      </td></tr>
                 	      </table>
                 	    </td>  
               	 </tr>	
             		 </table>
					<table cellpadding="0" cellspacing="0" width="98%" class="formTable">
						<tbody>
							<bean:write name="htmlcode" filter="false"/>
						
						<tr>
						 <td colspan="4" >
							<table cellpadding="0" cellspacing="0" width="100%">
								
								<tr>
								<td  style="text-align: left;">
								选择数据库表：
								</td>
								</tr>
								<tr>
								<td class="textL">
									 <table cellpadding="0" cellspacing="0" border="0">
								   	<bean:write name="seltablestr" filter="false"/>
								   </table>
								</td>
								</tr>
								
							</table>
							</td>
							</tr>
							
						<tr class="btnTr">
           			<td class="textC" colspan="4">
              	<gw:button name="btnQuery" onClick="goQuery()">查询</gw:button>
              &nbsp;
           			<gw:button name="btnQuery" onClick="goReset()">重置</gw:button>
           			 
           			</td>
         			</tr>
         			</tbody>
						</table>	
				
				<div class="gap8">&nbsp;</div>      
			
			</td>
			</tr>
			</table>
	
		</html:form>
		<iframe id="frm" name="frm" style="width: 0px;height: 0px;display: none;"></iframe>
		</div>
		

	</body>
</html>
