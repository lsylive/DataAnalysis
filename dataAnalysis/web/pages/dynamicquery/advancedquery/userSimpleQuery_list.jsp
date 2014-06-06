<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
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
		<script language="javascript" src="<%=CONTEXT_PATH%>/js/date_validate.js"></script>
		<script language="javascript" src="<%=CONTEXT_PATH%>/js/ctrl_util.js"></script>
		<script language="javascript" src="<%=CONTEXT_PATH%>/js/jquery.js"></script>
		<script language="javascript" src="<%=CONTEXT_PATH%>/js/jquery.form.js"></script>
	<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
		<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = "../query/userDefinedQuery.do";	
      var fulllink = contextpath + "query/userDefinedQuery.do";
      var td='<input name="paramvalue1" type="text" /><input type="hidden" name="paramvalue2" />';		
      var timetd='<input name="paramvalue1" type="text" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowWeek:true});" /><input type="hidden" name="paramvalue2" />';		
      var timebetweentd='<table cellspacing="0" cellpadding="0" width="100%"><tr><td width="50%"><input name="paramvalue1" type="text" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowWeek:true});" /></td><td width="50%"><input name="paramvalue2" type="text" readonly="readonly" class="Wdate" onclick="WdatePicker({isShowWeek:true});" /></td></tr></table>';
	  var betweentd='<table cellspacing="0" cellpadding="0" width="100%" ><tr><td width="50%"><input name="paramvalue1" type="text" /></td><td width="50%"><input name="paramvalue2" type="text"  /></td></tr></table>';

	  <logic:present name="codeSetList">
	  <logic:iterate id="codeSet" name="codeSetList">
	  var code<bean:write name="codeSet" property="id" />='<bean:write name="codeSet" property="htmlcode" filter="false" />';
	  </logic:iterate>
	  </logic:present>
	  var codesetArr='<bean:write name="codeColArr" />';
	  var datetimeArr='<bean:write name="dateColArr" />';
	function closedialog(ret){
	     dhxWins.window(winName).close();
	     if(ret=='true') {
       	 
       }	
	  }
	  function changecolumn(ctrl){
	  	var tab=$$("param");
	  	var columnid=ctrl.value;
	  	
	  	var rindex;
		var e=ctrl;
		while(typeof(e)=="undefined"||e.tagName!="TR")
   			e=e.parentElement||e.parentNode;
		rindex=e.rowIndex;
		
		var oper=$NAME('oper')[rindex-1].value;
		if(codesetArr.indexOf(columnid)!=-1){
			tab.rows[rindex].cells[4].innerHTML=eval('code'+columnid)+'<input type="hidden" name="paramvalue2" />';
		}else if(datetimeArr.indexOf(columnid)!=-1){
			if(oper=='BT'){
				tab.rows[rindex].cells[4].innerHTML=timebetweentd;
			}else{
				tab.rows[rindex].cells[4].innerHTML=timetd;
			}
		}else if(oper=='BT'){
			tab.rows[rindex].cells[4].innerHTML=betweentd;
		}else
			tab.rows[rindex].cells[4].innerHTML=td;
	  }
	  function changeoper(ctrl){
	  	var tab=$$("param");
	  	var oper=ctrl.value;
	  	
	  	var rindex;
		var e=ctrl;
		while(typeof(e)=="undefined"||e.tagName!="TR")
   			e=e.parentElement||e.parentNode;
		rindex=e.rowIndex;
		var columnid=$NAME('colid')[rindex-1].value;
		if(oper=='BT'){
			if(codesetArr.indexOf(columnid)!=-1){
				tab.rows[rindex].cells[3].innerHTML=betweentd;
				//tab.rows[rindex].cells[3].innerHTML=eval('code'+columnid)+'<input type="hidden" name="paramvalue2" />';
			}
			else if(datetimeArr.indexOf(columnid)!=-1){
				tab.rows[rindex].cells[3].innerHTML=timebetweentd;
			}else{
				tab.rows[rindex].cells[3].innerHTML=betweentd;
			}
		}else{
			if(codesetArr.indexOf(columnid)!=-1){
				tab.rows[rindex].cells[3].innerHTML=eval('code'+columnid)+'<input type="hidden" name="paramvalue2" />';
			}else if(datetimeArr.indexOf(columnid)!=-1){
				tab.rows[rindex].cells[3].innerHTML=timetd;
			}else{
				tab.rows[rindex].cells[3].innerHTML=td;
			}
		}
	  }
	 
	  function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closeSdialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
			openSingleWindow('个人空间问题',alerttab,120);
			$$("closebtn").focus();
      }
	 function insertRow(){
	   	var pos=0;
	   	  <logic:notEmpty name="columnList">  
	   	<logic:iterate id="column" name="columnList">
	   	
		var oper='<bean:write name="column" property="oper" />';
		var paramvalue1='<bean:write name="column" property="paramvalue1" />';
		var paramvalue2='<bean:write name="column" property="paramvalue2" />';
	   	var linkoper='<bean:write name="column" property="linkoper" />';
		var colid='<bean:write name="column" property="colid" />';
		var colname='<bean:write name="column" property="columnName" />';
		var colename='<bean:write name="column" property="columnEName" />';			  
		insertPart(oper,paramvalue1,paramvalue2,linkoper,colid,colname,colename,pos);
		pos++;
		</logic:iterate>
		</logic:notEmpty> 
   	}
   	function insertPart(oper,paramvalue1,paramvalue2,linkoper,colid,colname,colename,pos){
   		R = $$("param").insertRow(-1);
   		var C = R.insertCell(0);
   		if(pos!=0) {
			C.innerHTML='<select name="linkoper"><option value="and">和</option><option value="or">或者</option></select>';
			if(linkoper=='')
				document.getElementsByName("linkoper")[pos-1].selectedIndex=0;
			else
				document.getElementsByName("linkoper")[pos-1].value=linkoper;
		}
		C=R.insertCell(1);
		C.innerHTML='<input type="hidden" name="colid" value="'+colid+'" /><b>'+colname+'</b>';
		C=R.insertCell(2);
		C.innerHTML='<select name="oper" onchange="changeoper(this)"><logic:iterate id="op" name="userDefinedQueryForm" property="codeSets(FILTER_OPER)" ><option value=\'<bean:write name="op" property="value" />\'><bean:write name="op" property="codeName" /></option></logic:iterate></select>';
		
		if(oper=='')
			document.getElementsByName("oper")[pos].selectedIndex=0;
		else
			document.getElementsByName("oper")[pos].value=oper;
		
		
		C=R.insertCell(3);
		if(codesetArr.indexOf(colid)!=-1){
			C.innerHTML=eval('code'+colid)+'<input type="hidden" name="paramvalue2" />';
		}else if(oper=='BT'){
			if(datetimeArr.indexOf(colid)!=-1){
				C.innerHTML='<table cellspacing="0" cellpadding="0" width="100%"><tr><td width="50%"><input name="paramvalue1" type="text" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowWeek:true});" value="'+paramvalue1+'" /></td><td width="50%"><input name="paramvalue2" type="text" readonly="readonly" class="Wdate" onclick="WdatePicker({isShowWeek:true});" value="'+paramvalue2+'" /></td></tr></table>';;
			}else{
				C.innerHTML='<table cellspacing="0" cellpadding="0" width="100%" ><tr><td width="50%"><input name="paramvalue1" type="text" value="'+paramvalue1+'" /></td><td width="50%"><input name="paramvalue2" type="text" value="'+paramvalue2+'"  /></td></tr></table>';
			}
		}else {
			if(datetimeArr.indexOf(colid)!=-1){
				C.innerHTML='<input name="paramvalue1" type="text" class="Wdate" readonly="readonly" onclick="WdatePicker({isShowWeek:true});" value="'+paramvalue1+'" /><input type="hidden" name="paramvalue2" value="'+paramvalue2+'" />';		
			}else
				C.innerHTML='<input type="text" name="paramvalue1" value="'+paramvalue1+'" /><input type="hidden" name="paramvalue2" value="'+paramvalue2+'" />';
		}
		
   	}
   	function delRow(ctrl)
	{
		var rindex;
		var e=ctrl;
		while(typeof(e)=="undefined"||e.tagName!="TR")
   			e=e.parentElement||e.parentNode;
		rindex=e.rowIndex;
		if(rindex==0){
			alert('第一行不能删除');
			return ;
		}
		if(confirm("确定要删除该条条件?"))
			$$('param').deleteRow(rindex);
	}
	
		function toQuery(type){
		var tableId = document.getElementsByName("tableId")[0].value;
		var queryUrl ;
		if (type=="base") 
		{
      		queryUrl =contextpath + "/dynamicquery/advancedquery.do?action=TOADVANCEDPAGE&queryType=base&tableId=" + tableId;		
		} 
		else if(type=="batch")
		{
			queryUrl =contextpath + "dynamicquery/advancedbatch.do?action=TOBASEPAGE&tableId=" + tableId;
		}else if(type=="advance"){
			queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId;
		}else if(type=='simple'){
			queryUrl =contextpath + "query/userDefinedQuery.do?tableId=" + tableId+"&action1=listsimple";
	   }	
	window.location.href=queryUrl;
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
		alert(idArraystr);
		$NAME("idArr")[0].value=idArraystr;
		if(changetag || fristload){
			document.forms[1].target='favorfrm';
			document.forms[1].submit();
			fristload=false;
			changetag=false;
		}
	}	
		 
	</script>
<script language="javascript" src="<%=CONTEXT_PATH%>js/datapicker/WdatePicker.js"></script>

	</head>

	<body onload="insertRow()">
		
		<div  class="special-padding">
		<html:form styleId="theForm" action="/query/userDefinedQuery.do" method="post">
			<html:hidden property="tableId" />
			<html:hidden property="condId" />
			<input type="hidden" name="action1" />
			<html:hidden property="queryName" />
			<input type="hidden" name="querytype" value="ADVANCE" />
			<input type="hidden" name="condType" value="5" />
			<table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                 	<tr>
                  	<td class="textL" valign="top">
                  	   <table cellpadding="0" cellspacing="0" style="position:relative;top:-1px;height:24px;" width="100%" border="0">
                  	   <tr><td class="textL" style="padding-left:5px;">
                  	   	请填写/选择查询条件
                  	   	&nbsp;
                  	   	<input type="radio" name="querymode" value="base" onclick="toQuery('base')"  />简单查询&nbsp;
                  	<input type="radio" name="querymode" value="batch" onclick="toQuery('batch')" />批量查询&nbsp;
                  	<input type="radio" name="querymode" value="advance" checked="checked" />高级查询&nbsp;
                  	<input type="radio" name="querymode" value="advance" onclick="toQuery('advance');" />复杂高级查询&nbsp;
                 	      </td>
                  	   <td class="textR">
                 	      </td></tr>
                 	      </table>
                 	    </td>  
               	 </tr>	
             		 </table>
			<table id="param" class="searchTable" width="100%" cellSpacing="0" cellPadding="0">
				<tr style="" height="27">
						<th width="15%">
							操作符号
						</th>
						<th width="15%">
							字段名
						</th>
						<th width="15%">
							运算符
						</th>
						<th width="*">
							查询值
						</th>
				</tr>
				
			</table>
			
			

			<table cellpadding="0" cellspacing="0" width="100%" class="formTable">

				<tr class="btnTr">
					<td class="textL">
						<font color=red>注意：使用‘%’进行模糊匹配查询，如输入‘国%’ 就是查询第一个字为国的，‘%国%’就是查询含有国字的</font>
					</td>
				</tr>
				<tr class="btnTr">
					<td class="textC">
						<gw:button name="btnQuery" onClick="goQuery()">查询</gw:button> &nbsp;
						<gw:button name="btnQuery" onClick="goReset()">重置</gw:button>
					</td>
				</tr>
			</table>

		</html:form>
		</div>
		<div id="openDiv" style="position:relative;top:0;left:0;width:100%;height:100%;display: none;">
  <iframe id="content" frameborder="0" name="content" style="width: 100%;height: 100%;"></iframe>
  </div>
		<script type="text/javascript">
		var win;
    	 var dhxWins = new dhtmlXWindows();
    	dhxWins.enableAutoViewport(true);
    	dhxWins.setImagePath("<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlxWindows/codebase/imgs/");
   
		var winName="win";  //打开窗口名称
	    var editMode;       //页面编辑状态：ADD,EDIT,DELETE
	    var winName1="sWin";
 
   				
     function goQuery(){
     		var cansubmit=false;
     		var url=fulllink;
     		$NAME("action1")[0].value='VALIDSIMPLE';
     		var param=$('#theForm').formSerialize();   		
     		var loader=dhtmlxAjax.postSync(url,param);
			 //dhtmlxAjax.post(url,param,function(loader){
				var value = loader.xmlDoc.responseXML.getElementsByTagName("result");
				var msgval=loader.xmlDoc.responseXML.getElementsByTagName("msg");
				var msg=msgval.item(0).text;
				var result=value.item(0).text;
				
				if(!result)
					result=value[0].childNodes[0].nodeValue;
				if(!msg)
					msg=msgval[0].childNodes[0].nodeValue;
							
				if(result=='OK')
				{
					$NAME("action1")[0].value='SIMPLEOVERVIEW';
					//openWindow('查询结果',700,500);
					//document.forms[0].target="content";
					document.forms[0].submit();
				}else
				{
					alert("查询条件问题: " + msg);
					//var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msg+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closeSdialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
					//openSingleWindow('查询条件问题',alerttab,120);
					//$$("closebtn").focus();
				}
			//});	
			} 
  
      function openWindow(title,w,h){
	       if(!win){
	       win = dhxWins.createWindow(winName, 0, 0, w, h);
	       win.setText(title);
	       win.keepInViewport(true);
	       win.setModal(true);
	       win.centerOnScreen();
	       win.button("minmax1").hide();
	       win.button("minmax2").hide();
	       win.button("park").hide();
	        //win.button("close").hide();
	        win.button("close").attachEvent("onClick", closedialog);
	       
	       
	       win.attachObject("openDiv");
	     
	       }else{
	       win.keepInViewport(true);
	       win.setModal(true);
	       win.centerOnScreen();
	       	dhxWins.window(winName).show();
	       	}
	       return win;    
	    }

	    function openSingleWindow(title,divhtml,selheight){
	    	var height=selheight;
	    	if(!height)
	    		height=100;
   	   		var win2 = dhxWins.createWindow(winName1, 0, 0, 300, height);
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
	    function closedialog(){
	    	if(document.frames)
	         	document.frames["content"].document.body.innerHTML='';
	         else
	          	document.getElementById('content').contentDocument.body.innerHTML='';
	         dhxWins.window(winName).setModal(false);
		     dhxWins.window(winName).hide();
		     //var treeid = tree.getSelectedItemId();
		     
	    }
	    function closeSdialog(){
	    	
		     dhxWins.window(winName1).close();
		     //var treeid = tree.getSelectedItemId();
		     
	    }
  </script>

	</body>
</html>
