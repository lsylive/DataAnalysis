<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>

<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path+"/";
	String msg = (String)request.getAttribute("msg");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title></title>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.css" />
		<script src="<%=CONTEXT_PATH%>dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />
		<script src="<%=CONTEXT_PATH%>js/ctrl_util.js"></script>
		<script src="<%=CONTEXT_PATH%>js/date_validate.js"></script>
		<script language="javascript" src="<%= CONTEXT_PATH %>js/datapicker/WdatePicker.js"></script>
		
	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var hyperlink = contextpath + "blacklist/blacklistDeclarationAction.do";	

	function goCancel(){		
		parent.closedialog();
	}
	function goSubmit(){
		

		if(!checkString(getElement('record(decCode)'),"申报代码",120,true)) return;

    	document.forms[0].submit();
	}
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;padding-right:2px;}
</style>
</head>

<body onload="init()">
<script type="text/javascript">
var windows; 
function closeWindow(){	
	windows.window("win").close();
};



var msg = "<%= msg==null?"":msg%>";
if (msg!="")openAlert(msg);
function init(){
}
</script>
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/blacklist/blacklistDeclarationAction.do" >
  <html:hidden property="action" />
  <html:hidden property="record(id)" />
		<table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 <tr>
			  <td ><font color="red">*</font>申报代码：</td>
					<td >
					   <html:text property="record(decCode)" />
					</td>
			  <td >申报时间：</td>
					<td>
					  <html:text property="record(decTime)" styleClass="Wdate" onclick="WdatePicker({isShowWeek:true});" />
					</td>
			</tr>
			
			 <tr>
			  <td>名单报警起始时间:</td>
					<td >
					   <html:text property="record(startDate)" styleClass="Wdate" onclick="WdatePicker({isShowWeek:true});" />
					</td>
			  <td>名单报警结束时间:</td>
					<td>
					   <html:text property="record(endDate)" styleClass="Wdate" onclick="WdatePicker({isShowWeek:true});" />
					</td>
			</tr>
			<tr>
						<td>
							<font color="red">*</font>申报人：
						</td>
						<td colspan="3" class="sel">
							<html:select property="record(userId)">
								<html:optionsCollection property="codeSets(userIds)" label="codeName" value="value" />
							</html:select>
						</td>	
			</tr>
			 <tr>
						<td>
							对比指标：
						</td>
						<td colspan="3" class="sel">
						<html:select property="record(indCode)">
							<html:optionsCollection property="codeSets(indicator)" label="codeName" value="value" />
						</html:select>
						</td>
					</tr>
			<tr>
			   <td>名单（名单之间用半角分号隔开,每个名单的不同指标值用半角逗号隔开）：</td>
				 <td colspan="3">
					  <html:textarea rows="8" property="record(queryValue)" ></html:textarea>
				 </td>
			</tr>
			<tr>
			   <td>备注：</td>
				 <td colspan="3">
					  <html:textarea rows="2" property="record(remark)" ></html:textarea>
				 </td>
			</tr>
			<table>
			<tr height="40" valign="middle">
			   <td align="center">
			      <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
				    &nbsp;
			      <gw:button name="btnReset" onClick="goReset()">重置</gw:button>
			   </td>
			</tr>
			</table>
	  </table>
	 </table>
  </html:form>
  

</body>
</html>
