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
<title>批量查</title>
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>

	<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/subjectBatchQuery.do";	
      var fulllink = contextpath + "query/subjectBatchQuery.do";		
      var ids='<bean:write name="idArr" />';
	  function showdetail(id){
	  	var url_link=contextpath+'query/commSubjectQuery.do?action1=showdetail&uid='+id+'&tableId='+$NAME("tableId")[0].value+"&idArr="+ids;
	  	 openWindow('详细结果',url_link,600,350);
	  }
	  function goQuery(){
	  
	  }
	    function showSave(){
	  	if($NAME("query.pageCount")[0].value=='0')
	  	{
	  		alertMsg("没有查到任何结果，无法保存");
	  		return ;
	  	}
	  	var divhtmlimport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请填写表中文名:</td><td><input type="text" name="tablecname" title="中文名" /></td></tr><tr><td width="40%">表描述:</td><td><textarea name="tabledesc" style="width:150px;height:40px;" ></textarea></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=saveResult()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh"><strong>关闭</strong></a></td></tr></tbody></table>';       
      	openSingleWindow('保存查询结果到个人空间',divhtmlimport,300,180);
	  }

	    function showExport(){
	    	if($NAME("query.pageCount")[0].value=='0')
		  	{
		  		alertMsg("没有查到任何结果，无法导出");
		  		return ;
		  	}
		   	var divexport='<table cellpadding="0" cellspacing="0" width="280px" class="formTable"><tbody><tr><td width="40%">请选择文件类型:</td><td class="textC"><input type="radio" class="checkbox" name="ftype" value="0" checked="checked" />Excel&nbsp;<input type="radio" class="checkbox" name="ftype" value="1" />CSV </td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=downloadExcel()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=parent.closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
	      	openSingleWindow('保存查询结果到个人空间',divexport,300,180);
		}
	    
	   function downloadExcel(){
		  	var ftypes = document.getElementsByName("ftype");
			var ftype;
			
			if(ftypes[0].checked=="checked"||ftypes[0].checked==true){ftype="0";}else{ftype="1";}
	    	parent.downloadExcel(ftype,$NAME("pos")[0].value);
	    } 
	  function saveResult(){
		if(confirm('确定要将查询结果保存到个人空间')){
			
	  	if(checkIsNull("tablecname")){
	  		if(checkCnName())return;
		  	var param =parent.saveResult($NAME('tablecname')[0].value,$NAME('tabledesc')[0].value,$NAME("pos")[0].value);
	  		var url=contextpath+"query/subjectBatchQuery.do";
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
   	 function closedialog(ret){
	     dhxWins.window("win").close();
	   
	  }
   	 function addFavorite(){
	   	var id = findMultiSelected("id","收藏");
		if(id == "") return;
		var res = confirm("是否真的要添加结果集到详细比较页面?");
		if(res == true) {
      		parent.addFavorite($NAME("tableId")[0].value,id);
    	}
	   }

   	function showPage(){
		parent.showPage();
	}
	</script>	
   <style type="text/css">
html {
        width: 100%;
        height: 100%;
        margin: 0px;
        
         
}
 body {
 overflow-x: hidden;
        overflow-y: auto;margin: 0px;
}
</style>

</head>

<body topmargin="0" leftmargin="0" >
<div class="specail-padding">
<html:form action="/query/subjectBatchQuery.do" method="post">
<html:hidden property="tableId" />
<html:hidden property="id" />
<html:hidden property="action1" />
<html:hidden property="query.order" />
<html:hidden property="query.orderDirection" />
<html:hidden property="query.pageNumber" />
<html:hidden property="query.recordCount"/>
<html:hidden property="query.pageCount" />
<html:hidden property="record(paramjson)" />
<html:hidden property="pos" />

 <table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                        <tr>
                            <td class="textL">
                            <gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="addFavorite()">添加到详细页</gw:button>
			     			&nbsp;<gw:button styleClass="sbuBtnStyle" code="A9905-01" icon="addIcon" onClick="showExport()">导出</gw:button>
                 </td>
                     </tr>
                    </table>            
<gw:grid2 border="0" cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable" secondRowStyle="background:#e3effe"
	       name="subjectBatchQueryForm" property="query.recordSet" parameters="query" rowEventHandle="false" schema="common" 
	       fixRows="true" rowDblClick="showdetail('{id}')" >
    				<header height="27"  />

   					<rooter height="0" width="100%" showType="all" />      
</gw:grid2>		





</html:form>
</div>
<%@include file="/common/dialog1.jsp" %>
</body>
</html>