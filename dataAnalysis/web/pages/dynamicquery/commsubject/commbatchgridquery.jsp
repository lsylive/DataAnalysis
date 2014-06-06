<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld"
	prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlxgrid_form.js"></script>
		<script language="javascript" src="<%=CONTEXT_PATH%>js/date_validate.js"></script>
		<script language="javascript" src="<%=CONTEXT_PATH%>js/ctrl_util.js"></script>
		<script language="javascript" src="<%=CONTEXT_PATH%>js/jquery.js"></script>
		<script language="javascript" src="<%=CONTEXT_PATH%>js/jquery.form.js"></script>
		
		<style type="text/css">
   html, body {width:100%; height:100%;}
   .spa { border:1px solid #cdcac5;padding:4px 0 0 0;text-align:center;position:absolute; background:#fff;margin:0 0 0 0;width:250px;height:190px;*height:200px;z-index:100;}
   .spa .select {width:245px;background-color:#FFFF99;border: 0px;}
}
</style>
		<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/commSubjectQuery.do";	
      var fulllink = contextpath + "/query/commSubjectQuery.do";		
      var divhtmlexport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请填写查询名称:</td><td><input type="text" name="queryname" /></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=importparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh"><strong>关闭</strong></a></td></tr></tbody></table>';       
      var divhtmlimport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请选择查询条件:</td><td><select name="queryname"></select></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=exportparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
	  
	  var selctrl;
	  var selrow;
	  var indname;
		
	
	
		//修改主窗口页面标题
		
		function showquerymode(mode){
	  	var id=$NAME("id")[0].value; 
	  	if(mode==1)
	  		window.location.href='<%=CONTEXT_PATH%>query/commSubjectQuery.do?action1=showquery&id='+id;
	  	else if(mode==2)
	  		window.location.href='<%=CONTEXT_PATH%>query/subjectBatchQuery.do?action1=listbatch&id='+id;
	  	
	  } 
	  function showimport(){
	 	openSingleWindow('导入查询条件',divhtmlimport);
	 }
	 function showexport(){
	 	openSingleWindow('导出查询条件',divhtmlexport);
	 }
	 function showdict(codeSetId,ctrl,indid){
	 	var div=$$('floatselect');
	 	selctrl=ctrl;
	 	
	 	var rindex;
		var e=ctrl;
		while(typeof(e)=="undefined"||e.tagName!="TR")
   			e=e.parentElement||e.parentNode;
		rindex=e.rowIndex;
		indname="col"+indid;
		
		selrow=rindex;
	 	div.innerHTML=eval("code"+codeSetId);
	 	
	 	div.style.display='';
	 	var r=getAbsolutePos(selctrl);
		div.style.left = r.x+document.body.scrollLeft+"px";   
		div.style.top = r.y+document.body.scrollTop+20+"px";  
		var value1=$NAME(indname)[rindex-1].value;
		if(value1!='')
			$NAME('dictsel')[0].value=value1; 
	 }
	 function getselectvalue(){
	 	var div=$$('floatselect');
	 	var ctrl1=$NAME('dictsel')[0];
	 	var rindex=ctrl1.selectedIndex;
	 	var value=ctrl1.options[rindex].value;
	 	var txt=ctrl1.options[rindex].text;
	 	selctrl.value=txt;
	 	//alert(indname);
	 	$NAME(indname)[selrow-1].value=value;
	 	div.style.display='none';
	 }
	 function   getAbsolutePos(el)   {   
  		var   r   =   {   x:   el.offsetLeft,   y:   el.offsetTop   };   
  		if   (el.offsetParent)   {   
  		var   tmp   =   getAbsolutePos(el.offsetParent);   
 		 r.x   +=   tmp.x;   
  		r.y   +=   tmp.y;   
 		 }   
 		 return   r;   
 	 }   
 	 function hidefloat(){
 	 	$$('floatselect').style.display='none';
 	 }
	 
	</script>

	<script language="javascript" src="<%=CONTEXT_PATH%>js/datapicker/WdatePicker.js"></script>
	</head>
    
	<body >
		<div class="special-padding">
		<html:form styleId="subjectBatchQueryForm" action="/query/subjectBatchQuery.do" method="post">
			<html:hidden property="id" />
			<html:hidden property="record(paramjson)" />
			<html:hidden property="action1" />
			<html:hidden property="querySql" />
			<html:hidden property="tabId" />

			<html:hidden property="tabId" />
			
            		<table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                 	<tr>
                  	<td class="textL" valign="top">
                  	   <table cellpadding="0" cellspacing="0" style="position:relative;top:-1px;height:24px;" width="100%" border="0">
                  	   <tr><td class="textL" style="padding-left:5px;">
                  	   	请填写/选择查询条件
                  	   	&nbsp;
                  	   	<input type="radio" name="querymode" value="1" onclick="showquerymode(1)"  />简单查询
                  	   	&nbsp;
                  	   	<input type="radio" name="querymode" value="2" checked="checked" />批量查询
                 	      </td>
                  	   <td class="textR">
                 	      </td></tr>
                 	      </table>
                 	    </td>  
               	 </tr>	
             		 </table>
             		 <table cellspacing="0" cellpadding="0" class="controlTable" >
				<tr>
					<td valign="top" align="left">
						<gw:button styleClass="sbuBtnStyle" onClick="insertRow()" title="增加条件" icon="addIcon">增加条件</gw:button>
						&nbsp;
						<gw:button styleClass="sbuBtnStyle" onClick="delRow()" title="删除条件" icon="addIcon">删除条件</gw:button>
					</td>
				</tr>
			</table>
			<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td>
				<div id="gridbox" style="width:100%;height:400px;overflow:hidden"></div>

				</td>
	
				</tr>
			</table>		
			<table cellpadding="0" cellspacing="0" width="100%" class="formTable" >
				
				<tr>
					<td style="padding-left:8px;padding-bottom:5px;">
						    <fieldset class="formFiledset"><legend class="formLegend">选择数据库表</legend><div class="fieldsetBorder">
										 <table cellpadding="0" cellspacing="0" border="0">
								   	    <bean:write name="seltablestr" filter="false"/>
								       </table></div>
								   </fieldset>
					</td>
				</tr>
				<tr class="btnTr">
					<td class="textC">
					
						<gw:button name="btnQuery" onClick="goQuery()">查询</gw:button>
              &nbsp;
           			<gw:button name="btnQuery" onClick="goReset()">重置</gw:button>
					</td>
				</tr>
			</table>
		
			<div id="floatselect" class="spa" style="display:none;">
		 </div>
		</html:form>
		
		</div>
		 
		<script type="text/javascript">
    var dhxWins = new dhtmlXWindows();
    dhxWins.enableAutoViewport(true);
    dhxWins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
    var winName="win";	
    var editMode;       
    var win1;
    var win2;
    
    var mygrid = new dhtmlXGridObject('gridbox');
	mygrid.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxGrid/codebase/imgs/");
	mygrid.setColumnIds("<bean:write name='columnIds' />");
	mygrid.setHeader("<bean:write name='columnNames' />");
	mygrid.setInitWidths("<bean:write name='columnWidths' />");
	mygrid.setColAlign("<bean:write name='alignModes' />");
	mygrid.setColTypes("<bean:write name='colTypes' />");
	mygrid.setSkin("dhx_skyblue");
	mygrid.enableAlterCss("even", "uneven");
	
	<logic:present name="codeSetList">
	<logic:iterate id="code" name="codeSetList">
	mygrid.getCombo(<bean:write name="code" property="pos" />).put('<bean:write name="code" property="codeValue" />','<bean:write name="code" property="codeNo" />');
	</logic:iterate>
	</logic:present>
	
	mygrid.enableMultiselect(true); 
	mygrid.enableAutoHeight(true,350);
	mygrid.selMultiRows = true;
	mygrid.submitAddedRows(true);
	mygrid.init();
    mygrid.addRow(mygrid.uid(),[<bean:write name="blankstr" filter="false" />]);
    function goQuery(){		
		//$NAME('record(paramjson)')[0].value=myXmlStr;
		$NAME('action1')[0].value="queryoverview";
		//Sending form by direct submit call
		mygrid.parentFormOnSubmit();
		alert($$('gridbox').innerHTML);
		if(checkIsNull("selTable"))
		{
			document.forms[0].submit();
		}
			
		}
    function insertRow(){
		mygrid.addRow(mygrid.uid(),[<bean:write name="blankstr" filter="false" />]);
   	}
   	function delRow()
	{
		if(confirm("确定要删除该条条件?"))
		{
			mygrid.deleteSelectedRows();
		}
	}
  
    function openWindow(title,url){
       //var htmlcontent='<div id="openDiv"><iframe id="frm" name="frm" style="width: 100%;height: 100%;display: none;"></iframe></div>';
   	   if(win1!=null){
   	   win1 = dhxWins.createWindow(winName, 0, 0, 850, 500);
       win1.setText(title);
       win1.keepInViewport(true);
       win1.setModal(true);
       win1.centerOnScreen();
       win1.button("minmax1").hide();
       win1.button("minmax2").hide();
       win1.button("park").hide();
       win1.button("close").hide();
        win1.attachObject("openDiv");
    	}else
    	win1.show();
    	document.forms[0].target="content";
    	document.forms[0].submit();
    	return win1;    
    }
    function openSingleWindow(title,divhtml){
   	   var win2 = dhxWins.createWindow(winName, 0, 0, 300, 100);
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
    function closedialog(ret){
	     dhxWins.window(winName).close();
	   
	  }
   
</script> 
	</body>
</html>
