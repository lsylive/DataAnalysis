<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld"
	prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>

<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw"%>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String msg = (String) request.getAttribute("msg");
	if (msg == null)
		msg = "";
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
		<script src="<%=CONTEXT_PATH%>js/ctrl_util.js"></script>
		<script src="<%=CONTEXT_PATH%>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>
		
		<script language="javascript"> 
      var contextpath = "<%=CONTEXT_PATH%>";
      var hyperlink = contextpath + "blacklist/blacklistDeclarationAction.do";
      var fulllink = contextpath + "blacklist/blacklistDeclarationAction.do";
      
		 function goAdd()  {
			openWindow("添加黑名单",fulllink+'?action=ADD',700,380);
		}
		function goEdit() {
			var id = findSelected("ID","修改");
			if(id == "") return;
		    var url_link=fulllink+'?action=EDIT&record(id)='+id;
			openWindow("修改黑名单",url_link,700,400);
		}

    	function goStart(){
    		var id = findMultiSelected("ID","开始对比");
	  		if(id == "") return;
	  		
	  		var cType = getElement("query.pageNumber");    
	  		var res = confirm("是否真的要启用对比?手动对比将立即执行。");
	  		if(res == true) {
       			//goViewInfo();
       			document.forms[0].action = fulllink + "?action=startcompare&ids=" + id;
       			document.forms[0].target = "_self";
       			document.forms[0].submit();
       			
  		 	}
    	}
    	function goCancel(){
    		var id = findMultiSelected("ID","取消对比");
	  		if(id == "") return;
	  		//var res = confirm("是否真的要取消开始对比?");
	  		//if(res == true) {
       			document.forms[0].action = fulllink + "?action=stopcompare&ids=" + id;
       			document.forms[0].target = "_self";
       			document.forms[0].submit();
  		 	//}
    	}
    	
		function renew() {
			var order = getElement("query.order");                  order.value="";
			var desc =  getElement("query.orderDirection");         desc.value="";
			var pn =    getElement("query.pageNumber");             pn.value="1";
			var ps =    getElement("query.pageSize");               ps.value="10";
			var v0 =    getElement("query.parameters(memoTitle)");    v0.value="";
			var v1 =    getElement("query.parameters(createDateFrom)");    v1.value="";
			var v2 =    getElement("query.parameters(createDateTo)");    v2.value="";
    		gosearch();
		}

		function goQuery() {
			var pn = getElement("query.pageNumber");             
			pn.value="1";
			gosearch();
		}

		function closedialog(ret) {
			dhxWins.window(winName).close();
      		editMode="";
			if(ret=='true') {
				gosearch();
			}	
		}
		

		function goDel()  {
	  		var id = findMultiSelected("ID","删除");
	  		if(id == "") return;
	  		var res = confirm("是否真的要删除?");
	  		if(res == true) {
       			document.forms[0].action = hyperlink + "?action=DELETE&ids=" + id;
       			document.forms[0].target = "_self";
       			document.forms[0].submit();
    }
    }
    
    function goViewInfo()  {
	  		var id = findMultiSelected("ID","运行状态");
	  		if(id == "") return;
    		var url_link=hyperlink1 + "?action=list&compId=" + id;
			openWindow("查看运行状态",url_link,700,400);
    
}
		 function alertMsg(msgtxt,heigth){
      	var alerttab='<table cellpadding="0" cellspacing="0" width="100%" class="formTable"><tbody><tr><td width="60px" class="textR"><span><img src="'+contextpath+'common/images/info.gif"/></span></td><td class="textL"><span><b>'+msgtxt+'</b></span></td></tr><tr class="btnTr"><td class="textC" colspan="2"><a id="closebtn" href="javascript:onClick=closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a></td></tr></tbody></table>';       
			openSingleWindow('个人空间',alerttab,300,120);
			$$("closebtn").focus();
      }		

		var msg = "<%=msg%>";
		if (msg != "")
			alert(msg);
			

	</script>

	</head>

	<body>
	<div class="special-padding">   
		<html:form action="/blacklist/blacklistDeclarationAction.do" method="post">
			<table border="0" width="100%" cellpadding="0" cellspacing="0" align="center">
				<tr>
            		<td width="100%" valign="top">
						<table cellspacing="1" cellpadding="1" class="controlTable">
							<tr>
								<td valign="top" align="left">
									<gw:button styleClass="sbuBtnStyle" icon="addIcon" onClick="goAdd()">添加黑名单</gw:button>&nbsp;
									<gw:button styleClass="sbuBtnStyle" icon="subIcon" onClick="goEdit()">修改黑名单</gw:button>&nbsp;
									<gw:button styleClass="sbuBtnStyle" icon="delIcon" onClick="goDel()">删除黑名单</gw:button>&nbsp;
								</td>
							</tr>
						</table>
						<html:hidden property="query.order" />
						<html:hidden property="query.orderDirection" />
						<html:hidden property="query.pageNumber" />
						<html:hidden property="query.recordCount" />
						<html:hidden property="query.pageCount" />
						<html:hidden property="query.parameters(classId)" />
						
						<gw:grid2 cellPadding="0" cellSpacing="0" width="100%" styleClass="listTable"
	    					   name="blacklistDeclarationForm" property="query.recordSet" parameters="query" rowEventHandle="false" 
	      						 fixRows="false">
	      						 <header style="" height="27"  />
    										<column width="5%" itemType="checkbox" property="ID" align="center" selectAll="true"  headerAlign="center"/>
  											  <column width="20%" name="申报代码" property="DECCODE" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnClick="query('DECCODE')"
											  headerOnMouseOver="headerOver(this)"/>
											 <column width="10%" name="申报时间" property="DECTIME" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnClick="query('DECTIME')"
											  headerOnMouseOver="headerOver(this)"/>
											<column width="10%" name="申报人" property="USERNAME" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnMouseOver="headerOver(this)"/>
    										<column width="20%" name="名单报警起始时间" property="STARTDATE" align="center" 
    	    							      headerOnMouseOut="headerOut(this)"
											  headerOnMouseOver="headerOver(this)"/>
											<column width="20%" name="名单报警结束时间" property="ENDDATE" styleClass="td_LeftTop"  headerStyleClass="td_LeftTop"
    													headerOnClick="query('createDate')" 
    													headerOnMouseOver="headerOver(this)" headerStyle="color:#000077"
    													headerOnMouseOut="headerOut(this)"/>
    										<rooter height="30" width="100%" showType="all" />      
												</gw:grid2>
					</td>
				</tr>
			</table>
		</html:form>
		</div>
		<%@include file="/common/dialog1.jsp"%>
	</body>
</html>