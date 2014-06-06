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
      var divhtmlimport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请填写查询名称:</td><td><input type="text" name="inpqueryname" /></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=importparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh"><strong>关闭</strong></a></td></tr></tbody></table>';       
      var divhtmlexport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请选择查询条件:</td><td><select id="inpqueryname" name="inpqueryname"></select></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=exportparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
	  
	  var selctrl;
	  var selrow;
	  var indname;
		
	 function insertRow(){
		R = $$("param").insertRow(-1);
		<logic:iterate id="cell" name="cellList" indexId="id">
		var C = R.insertCell(<bean:write name="id" />); 
		C.align='left';
		C.innerHTML='<bean:write name="cell" property="code" filter="false" />';
		</logic:iterate>	
   	}
   	function delRow()
	{
		//var rindex;
		//var e=ctrl;
		//while(typeof(e)=="undefined"||e.tagName!="TR")
   		//	e=e.parentElement||e.parentNode;
		//rindex=e.rowIndex;
		//if(rindex==0){
		//	alert('第一行不能删除');
		//	return ;
		//}
		//if(confirm("确定要删除该条条件?"))
		//	$$('param').deleteRow(rindex);

		var hasdelet = false;
		var table = document.getElementById("searchTable");
		var boxs = document.getElementsByName("deletebox");
		for ( var i = boxs.length-1; i >= 0 ; i--) {
			if (boxs[i].checked==true) {
				hasdelet = true; 
			}
		}
		if(hasdelet){
			if (confirm("确定要删除条件?")) {
				for ( var i = boxs.length-1; i >= 0 ; i--) {
					if (boxs[i].checked==true) {
						var row = boxs[i].parentNode.parentNode||boxs[i].parentElement.parentElement;
						//table.deleteRow(row.rowIndex); 
						$$('param').deleteRow(row.rowIndex);
					}
				} 
			}
		}
		else{
			alert("请选择要删除的条件！");
		}
	}
	function checkValid(){
		var colArrstr='<bean:write name="colArr" />';
		var colArr=colArrstr.split(',');
		var jsonstr="";
		var count=0;
		var totalcount=$NAME(colArr[0]).length;
		
		for(j=0;j<totalcount;j++){
			var hasparam=false;
			var resultstr='';
			for(i=0;i<colArr.length;i++){
				var resultArr=$NAME(colArr[i]);
				var result = '';
				if($NAME(colArr[i])[j]){
					result=($NAME(colArr[i])[j].value).replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
				}
				
				if(result!=''){
					hasparam=true;
					resultstr+=colArr[i]+":'"+result+"',";
				}
				else
					resultstr+=colArr[i]+":'',";
			}
			if(hasparam)
				jsonstr+=resultstr.substr(0,resultstr.length-1)+"},{";
		}
		if(jsonstr=='')
		{
			alert('至少要输入一组不为空的查询条件');
			return false;
		}
		jsonstr="[{"+jsonstr.substr(0,jsonstr.length-2)+"]";   
		//alert(jsonstr); 
		
		$NAME('record(paramjson)')[0].value=jsonstr;
		return true;
	}
		function goQuery(action){
		
		$NAME('action1')[0].value=action;
		//parent.openWindow('查询结果',850,500);
		//document.forms[0].target="content";
			if(checkValid() && checkIsNull("selTable"))
			{
				document.forms[0].submit();
			}	
		}
		//修改主窗口页面标题
		
		function showquerymode(mode){
	  	var id=$NAME("id")[0].value; 
	  	if(mode==1)
	  		window.location.href='<%=CONTEXT_PATH%>query/commSubjectQuery.do?action1=showquery&id='+id;
	  	else if(mode==2)
	  		window.location.href='<%=CONTEXT_PATH%>query/subjectBatchQuery.do?action1=listbatch&id='+id;
	  	
	  } 
	  function importparam(){
	 if(checkValid() && checkIsNull("inpqueryname"))
	  {	
  		$NAME("action1")[0].value="saveparam"; 
  		$NAME("queryName")[0].value=$NAME('inpqueryname')[0].value; 
  		$NAME("subjectId")[0].value=$NAME("id")[0].value;
  		var param=$('#subjectBatchQueryForm').formSerialize();
  		var url=contextpath+"dynamicquery/queryCondition.do";
  		var loader=dhtmlxAjax.postSync(url,param);
  		var value = loader.xmlDoc.responseText;
  		if(value=='OK'){
  			alert("保存查询条件成功!");
  			closedialog();
  		}else if(value=='REQUIRE'){
  			alert("您还未开通个人空间,请先开通才能使用!");
  			closedialog();
  		}
  		else
  			alert("保存查询条件失败");
	 	}
	 }
	 function exportparam(){
      	if(checkIsNull("inpqueryname")){
      		$NAME("condId")[0].value=$NAME("inpqueryname")[0].value;
      		$NAME("action1")[0].value="EXPORTPARAM";
      		document.forms[0].submit();
      	}
	 }
	 
	  
	  function showexport(){
	   	var url = contextpath + "dynamicquery/queryCondition.do";
	   	var param="action1=GETPARAM&condType=2&subjectId="+$NAME("id")[0].value;
	  	var loader=dhtmlxAjax.postSync(url,param);
  	   	var result=loader.xmlDoc.responseXML.getElementsByTagName("RESULT");
  	   	var msg=result[0].childNodes[0].nodeValue;
  	   	var msgtxt='';
  	   	var isok=true;
  	   	if(msg=='REQUIRED'){
  	   		msgtxt="您还未开通个人空间,请先开通才能使用!";
  	   		isok=false;
  	   	}else if(msg=='EMPTY'){
  	   		msgtxt="您还未对改主题或表保存过查询条件，请先保存再载入!";
  	   		isok=false;
  	   	}else if(msg=='ERR'){
  	   		msgtxt='系统出错,请稍后再试';
  	   		isok=false;
  	   	}
  	   	if(!isok){
  	   		alertMsg(msgtxt,120);
  	   		return ;
  	   	}
	 	openSingleWindow('导入查询条件',divhtmlexport);
	 	var options = loader.xmlDoc.responseXML.getElementsByTagName("COL");
		for ( var i = 0; i < options.length; i++) {
			var name = options[i].childNodes[1].firstChild.nodeValue;
			var value = options[i].childNodes[0].firstChild.nodeValue;
	         try{
	        	var optionstr="<option value='"+value+"'>"+name+"</option>"; 
		   		 $(optionstr).appendTo("#inpqueryname");
	        }catch(e){
	        }
	    }
	    	if($NAME('condId')[0].value!='')
	    		$NAME('inpqueryname')[0].value=$NAME('condId')[0].value;
	 }
	 function showimport(){
	 	openSingleWindow('保存查询条件',divhtmlimport);
	 	if($NAME('queryName')[0].value!='')
	 		$NAME('inpqueryname')[0].value=$NAME('queryName')[0].value;
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
 	  function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
			openSingleWindow('个人空间问题',alerttab,120);
			$$("closebtn").focus();
      }
	   function uploadfile(){
	 	
		var seltab=$NAME('selTable');
		var id=$NAME('id')[0].value;
		var seltabval='';
		for(i=0;i<seltab.length;i++){
			if(seltab[i].checked)
				seltabval+=seltab[i].value+",";
		}
		if(seltabval!='')
			seltabval=seltabval.substr(0,seltabval.length-1);
		var url='<%=CONTEXT_PATH%>query/subjectBatchQuery.do?action1=uploadfile&id='+id+'&seltab='+seltabval; 		
		
		openUploadWindow('导入查询条件',url);
	 }


	   function selectAll(){
			  $("input[name='selTable']").attr("checked", true); 
		  }
		  function diSelectAll(){
			  $("input[name='selTable']").attr("checked", false);
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
			<html:hidden property="tableId" />
			<html:hidden property="queryName" />
			<html:hidden property="condId" />
			<input type="hidden" name="subjectId" />
			
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
                  	   <gw:button styleClass="sbuBtnStyle" onClick="uploadfile()" title="导入对应的文本文件" icon="openIcon">导入文件</gw:button>
                 	      </td></tr>
                 	      </table>
                 	    </td>  
               	 </tr>	
             		 </table>
			<table id="param" class="searchTable" width="100%" cellSpacing="0" cellPadding="0">
				<tr style="" height="27">
				<th width="3%">
						删除
					</th>
					<logic:iterate id="column" name="queryParamList">
						<th width="<bean:write name='column' property='width' />%">
							<bean:write name="column" property="displayName" />
						</th>
					</logic:iterate>
					

				</tr>
				
				<bean:write name="columnhtml" filter="false" />
				
			</table>

			<table cellspacing="0" cellpadding="0" class="controlTable" >
				<tr>
					<td valign="top" align="left">
						<a href="javascript:void(0)" class="sbuBtnStyle" onclick="insertRow()"><strong><span
								class="addIcon">&nbsp;</span>增加</strong>
						</a>
						<a href="javascript:void(0)" class="sbuBtnStyle" onclick="delRow()"><strong><span class="addIcon">&nbsp;</span>删除</strong> </a>
					</td>
				</tr>
			</table>
			
			<table cellpadding="0" cellspacing="0" width="100%" class="formTable" >
					<tr>
						  <td class="textL">
							 <gw:button name="btnQuery" onClick="selectAll()">全选</gw:button>
							 &nbsp;
							 <gw:button name="btnQuery" onClick="diSelectAll()">全不选</gw:button>
							 &nbsp;
							 <font color=red>注意：批量查询是实行精确查询，输入‘%’不起作用</font>
						  </td>
						</tr>
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
					
					<gw:button name="btnQuery" onClick="goQuery('queryoverview')">查询</gw:button>
              &nbsp;
           			<gw:button name="btnQuery" onClick="goReset()">重置</gw:button>
					</td>
				</tr>
			</table>
		
			<div id="floatselect" class="spa" style="display:none;">
		 </div>
		 <iframe id="frm" name="frm" style="width: 0px;height: 0px;display: none;"></iframe>
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
    function openUploadWindow(title,url){
   	   var win2 = dhxWins.createWindow(winName, 0, 0, 400, 110);
       win2.setText(title);
       win2.keepInViewport(true);
       win2.setModal(true);
       win2.centerOnScreen();
       win2.button("minmax1").hide();
       win2.button("minmax2").hide();
       win2.button("park").hide();
       win2.attachURL(url);
       return win2;    
    }
    function closedialog(ret){
	     dhxWins.window(winName).close();
	   
	  }
   
</script> 
	</body>
</html>
