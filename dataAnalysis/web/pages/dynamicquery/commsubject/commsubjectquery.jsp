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
Boolean commName=(Boolean)request.getAttribute("commName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css">
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css">
		
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/jquery.form.js"></script>
		
		<script language="javascript">  
      var contextpath = "<%=CONTEXT_PATH%>";
      
      var hyperlink = "../query/commSubjectQuery.do";	
      var fulllink = contextpath + "/query/commSubjectQuery.do";		
      var divhtmlimport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请填写查询名称:</td><td><input type="text" name="inpqueryname" /></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=importparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh"><strong>关闭</strong></a></td></tr></tbody></table>';       
      var divhtmlexport='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="40%">请选择查询条件:</td><td><select id="inpqueryname" name="inpqueryname"></select></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a href="javascript:onClick=exportparam()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;<a href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
          
function renew()  {
	document.forms[0].reset();
	var order = getElement("query.order");                  order.value="";
	var desc =  getElement("query.orderDirection");         desc.value="";
	var pn =    getElement("query.pageNumber");             pn.value="1";
	var ps =    getElement("query.pageSize");               ps.value="10";
	var v0 =    getElement("query.parameters(roleCode)");   v0.value="";
	var v1 =    getElement("query.parameters(roleName)");   v1.value="";
	var v2 =    getElement("query.parameters(roleStatus)"); v2.value="";
}

	  function check(){
	  	
	  }
	  function goQuery(){
	  	//document.forms[0].target="_self";
	  	document.getElementsByName("action1")[0].value="overview";
	  	
	  	if(checkIfInput("selTable,id,action,querySql,tabId") && checkIsNull("selTable"))
		{
			//var url=fulllink+"?"+$('#commSubjectQueryForm').formSerialize();
			//parent.openWindow('查询结果',850,500);
			//document.forms[0].target="content";
			var isSpa = <%=commName==null?"false":commName%>;
			if (isSpa) {
				var vals = $(".spa")[0];
				//alert(vals[0].type);				
				//return;
				//var vals = document.getElementsByTagName("input");
				var flag = false;
				if (checkNumeric(vals,"电话号码",false,false,true)) {
					var val = vals.value.replace(/(^\s*)|(\s*$)/g, ""); //去除输入的空格
					if (val!=""&&val.length<6) {
						flag = true;
					}
				}else{
					return;
				}
				if(flag){
					alert("电话号码不能小于6位数！");
					return;
				}				
			}
			document.forms[0].submit();
		}
	  }
	 function showimport(){
	 //检查是否存在个人空间
	 //var url = contextpath + "dynamicquery/queryCondition.do";
	 //param="action1=CHECKSPACE&subjectId="+$NAME("id")[0].value;
	 //var loader=dhtmlxAjax.postSync(url,param);
	 //var result=loader.xmlDoc.responseText;
	 //if(result=='REQUIRED'){
	 //	alertMsg('您还未开通个人空间,请先开通才能使用',120);
	 //}else
	 	openSingleWindow('保存查询条件',divhtmlimport);
	 	if($NAME('queryName')[0].value!='')
	 		$NAME('inpqueryname')[0].value=$NAME('queryName')[0].value;
	 }
	
	 function importparam(){
	 
	 if(checkIsNull("inpqueryname"))
	  {	
  		$NAME("action1")[0].value="saveparam"; 
  		$NAME("queryName")[0].value=$NAME('inpqueryname')[0].value; 
  		$NAME("subjectId")[0].value=$NAME("id")[0].value;
  		var param=$('#commSubjectQueryForm').formSerialize();
  		
  		var url=contextpath+"dynamicquery/queryCondition.do";
  		var loader=dhtmlxAjax.postSync(url,param);
  		var value = loader.xmlDoc.responseText;
  		closedialog();
  		if(value=='OK'){
  			alertMsg("保存查询条件成功!",120);
  		}
  		else if(value=='REQUIRED'){
  			alertMsg("您还未开通个人空间,请先开通才能使用!",120);
  		}else
  			alertMsg("保存查询条件失败",120);
	 	}
	 }
	
	 function showexport(){
	 	var url = contextpath + "dynamicquery/queryCondition.do";
	    var param="action1=GETPARAM&condType=1&subjectId="+$NAME("id")[0].value;
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
			//alert(name+" "+value);
	         try{
	        	var optionstr="<option value='"+value+"'>"+name+"</option>"; 
		   		 $(optionstr).appendTo("#inpqueryname");
	         }catch(e){
	         }
	    }
  	   	if($NAME('condId')[0].value!='')
	    		$NAME('inpqueryname')[0].value=$NAME('condId')[0].value;
	 }
	 function exportparam(){
	 	
      	if(checkIsNull("inpqueryname")){
      		$NAME("condId")[0].value=$NAME("inpqueryname")[0].value;
      		$NAME("action1")[0].value="EXPORTPARAM";
      		document.forms[0].submit();
      	}
	 }
	 function getqueryname(){
	 	var url = contextpath + "query/commSubjectQuery.do?action1=SELPARAM";
	    var queryname=$NAME("queryname")[0];
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
	 //修改主窗口页面标题
     
      function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
			openSingleWindow('个人空间问题',alerttab,120);
			$$("closebtn").focus();
      }
	  
	  function showquerymode(mode){
	  	var id=$NAME("id")[0].value; 
	  	if(mode==1)
	  		window.location.href='<%=CONTEXT_PATH%>query/commSubjectQuery.do?action1=showquery&id='+id;
	  	else if(mode==2)
	  		window.location.href='<%=CONTEXT_PATH%>query/subjectBatchQuery.do?action1=listbatch&id='+id;
	  	
	  }

	  function selectAll(){
		  $("input[type='checkbox']").attr("checked", true); 
	  }
	  function diSelectAll(){
		  $("input[type='checkbox']").attr("checked", false);
	  }
	</script>

	<style type="text/css">
   html, body {width:100%; height:100%;}
</style>
<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>
	
	</head>

	<body>   
<div class="special-padding">
			
		<html:form styleId="commSubjectQueryForm" action="/query/commSubjectQuery.do" method="post" styleClass="special-form">
		<html:hidden property="id"/>
			<html:hidden property="action1" />
			<html:hidden property="querySql" />
			<html:hidden property="queryName" />
			<html:hidden property="tableId" />
			<html:hidden property="condId" />
			<input type="hidden" name="subjectId" />
            		<table width="100%" cellspacing="1" cellpadding="1" class="controlTable">
                 	<tr>
                  	<td class="textL" valign="top">
                  	   <table cellpadding="0" cellspacing="0" style="position:relative;top:-1px;height:24px;" width="100%" border="0">
                  	   <tr><td class="textL" style="padding-left:5px;">
                  	   	请填写/选择查询条件
                  	   	&nbsp;
                  	   	<%
                  	   	   if(commName==null||!commName){
                  	   	%>
                  	   	<input type="radio" name="querymode" value="1" checked="checked" />简单查询
                  	   	&nbsp;
                  	   	<input type="radio" name="querymode" value="2" onclick="showquerymode(2)" />批量查询
                 	      <%}%>
                 	      </td>
                  	   <td class="textR">
                 	      </td></tr>
                 	      </table>
                 	    </td>  
               	 </tr>	
             		 </table>
             
           
					<table cellpadding="0" cellspacing="0" width="98%" class="formTable">
						<tbody>
						 <%
                  	   	   	if(commName!=null&&commName){
                  	   	%>
						<tr><td width="10%">电话号码类型</td>
						<td colspan="3" class="textL" style="padding-left:5px;">
						<html:radio property="record(telNoType)" value="1" styleClass="widthreset">主叫</html:radio>
						
                  	   	&nbsp;
                  	   	<html:radio property="record(telNoType)" value="2" styleClass="widthreset">被叫</html:radio>
                  	   	
                  	   	&nbsp;
                  	   	<html:radio property="record(telNoType)" value="3" styleClass="widthreset">全部</html:radio>
                  	   
						</td> 
						</tr>
						 <%}%>
						
						<bean:write name="htmlcode" filter="false"/>
						<tr>
						  <td colspan="4" class="textL">
							 <gw:button name="btnQuery" onClick="selectAll()">全选</gw:button>
							 &nbsp;
							 <gw:button name="btnQuery" onClick="diSelectAll()">全不选</gw:button>
							 &nbsp;
							 <%
                  	   	   		if(commName==null||!commName){
                  	   		 %>
							 <font color=red>注意：使用‘%’进行模糊匹配查询，如输入‘国%’ 就是查询第一个字为国的，‘%国%’就是查询含有国字的</font>
						   <%}else{%>
						   <font color=red>注意：请输入最少6位号码</font>
						   <%}%>
						  </td>
						</tr>
						<tr>
						  <td colspan="4" style="padding-left:8px;padding-bottom:5px;">
								   <fieldset class="formFiledset"><legend class="formLegend">选择数据库表</legend><div class="fieldsetBorder">
									<table cellpadding="0" cellspacing="0">
								   	    <bean:write name="seltablestr" filter="false"/>
								    </table></div>
								   </fieldset>
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
		<div id="output1" style="display: none;"></div>
		 <!-- <div id="openDiv" style="display: none;">
  <iframe id="content" name="content" style="width: 100%;height: 100%;display: none;"></iframe>
  </div> -->
  <iframe id="frm" name="frm" style="width: 0px;height: 0px;display: none;"></iframe>
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
    function openSingleWindow(title,divhtml,height){
       if(height==null || height=='')
       		height=100;
   	   var win2 = dhxWins.createWindow(winName, 0, 0, 300, height);
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
