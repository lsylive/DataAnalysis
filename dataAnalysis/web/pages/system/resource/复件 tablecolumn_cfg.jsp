<%@page import="com.liusy.datapp.util.ColumnCfgPageVO"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>js/date_validate.js"></script>	
   <script language="javascript" src="<%= CONTEXT_PATH %>js/ctrl_util.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "querycfg/columnSynthesisCfg.do";		

	function goCancel(){
		parent.closeWindow();
	}
	function goSubmit(){
		
		//document.forms[0].action=fulllink+"?action=UPDATE";
		document.forms[0].submit(); 	
	}

	function check(){
		
		
	}
	</script> 
	   <style type="text/css">
   html, body {width:100%; height:100%;}
</style>
</head>

<body>
  <div id="formDiv" class="formDiv">
  <html:form method="post" action="/querycfg/columnSynthesisCfg.do" >
  <html:hidden name="columnSynthesisConfigForm" property="tableId"/>
  <html:hidden property="record(columnId)" />
  <html:hidden property="record(tableId)" />
  <input type="hidden" name="action" value="SAVEINITCFG" />
		<table class="listTable" width="90%" cellSpacing="0" cellPadding="0">
   			<tr style="" height="50">
			 <th width="15%" align="center">
              字段名
             </th>
             <th width="14%" align="center">
              中文名
             </th>
              <th width="4%" align="center">
             是否查询条件
             </th>
              <th width="10%" align="center">
              查询运算符
             </th>
              <th width="3%" align="center">
              概要字段
             </th>
             <th width="3%" align="center">
              可排序字段
             </th>
            
             <th width="3%" align="center">
              是否显示
             </th>
              <th width="5%" align="center">
              顺序号
             </th>
             <th width="3%" align="center">
              模糊查
             </th>
             <th width="3%" align="center">
              批量查
             </th>
             <th width="3%" align="center">
              同音查
             </th>
             <th width="3%" align="center">
              简繁查
             </th>
			 </tr>
			 
			<logic:iterate id="columncfg" name="columnSynthesisConfigForm" property="parameters" indexId="index" >
			<%ColumnCfgPageVO vo = (ColumnCfgPageVO)columncfg; %>
			<tr <%if(index.intValue() %2==0) {%>class="trBg"<% }%>>
				<td align="center">
				<input type="hidden" name="parameters[<bean:write name='index' />].columnId" value="<bean:write name='columncfg' property='columnId' />" />
				<bean:write name="columncfg" property="name" />
				</td>
				<td align="center">
				<bean:write name="columncfg" property="cname" />
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].isFilter"  <%=vo.getIsFilter()!=null&&vo.getIsFilter().equalsIgnoreCase("1")?"checked='checked'":"" %> value="1" />
				</td>
				<td>
					<select name="parameters[<bean:write name='index' />].filterOperator"  value="<bean:write name="columncfg" property="filterOperator" />">
					
						<option value="="   <%=vo.getFilterOperator().equalsIgnoreCase("=")?"selected='selected'":"" %>>=</option>
						<option value="&gt;"   <%=vo.getFilterOperator().equalsIgnoreCase(">")?"selected='selected'":"" %>>&gt;</option>
						<option value="&gt;="   <%=vo.getFilterOperator().equalsIgnoreCase(">=")?"selected='selected'":"" %>>&gt;=</option>
						<option value="&lt;"   <%=vo.getFilterOperator().equalsIgnoreCase("<")?"selected='selected'":"" %>>&lt;</option>
						<option value="&lt;="   <%=vo.getFilterOperator().equalsIgnoreCase("<=")?"selected='selected'":"" %>>&lt;=</option>
						<option value="!="   <%=vo.getFilterOperator().equalsIgnoreCase("!=")?"selected='selected'":"" %>>!=</option>
						<option value="LK"   <%=vo.getFilterOperator().equalsIgnoreCase("lk")?"selected='selected'":"" %>>LIKE</option>
						<option value="BT"   <%=vo.getFilterOperator().equalsIgnoreCase("bt")?"selected='selected'":"" %>>BETWEEN</option><
					</select>
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].isSubject" <%=vo.getIsSubject()!=null&&vo.getIsSubject().equalsIgnoreCase("1")?"checked='checked'":"" %>  value="1" />
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].isSortable" <%=vo.getIsSortable()!=null&&vo.getIsSortable().equalsIgnoreCase("1")?"checked='checked'":"" %> value="1" />
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].isShown" <%=vo.getIsShown()!=null&&vo.getIsShown().equalsIgnoreCase("1")?"checked='checked'":"" %> value="1" />
				</td>
				<td align="center">
				<input type="text" name="parameters[<bean:write name='index' />].seqNo" value="<%=index.intValue()+1 %>" size="2" />
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].fuzzyQuery" <%=vo.getFuzzyQuery()!=null&&vo.getFuzzyQuery().equalsIgnoreCase("1")?"checked='checked'":"" %> value="1" />
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].batchQuery" <%=vo.getBatchQuery()!=null&&vo.getBatchQuery().equalsIgnoreCase("1")?"checked='checked'":"" %> value="1" />
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].homonymQuery" <%=vo.getHomonymQuery()!=null&&vo.getHomonymQuery().equalsIgnoreCase("1")?"checked='checked'":"" %> value="1" />
				</td>
				<td align="center">
				<input type="checkbox" name="parameters[<bean:write name='index' />].stQuery" <%=vo.getStQuery()!=null&&vo.getStQuery().equalsIgnoreCase("1")?"checked='checked'":"" %> value="1" />
				</td>
			</tr>
			
			</logic:iterate>
			
	 </table>
				
  </html:form>
  </div>
   <div id="btnDiv" class="btnDiv">
		       <gw:button name="btnAdd" onClick="goSubmit()">保存</gw:button>
				    &nbsp;
			      <gw:button name="btnCancel" onClick="goCancel()">取消</gw:button>
				    &nbsp;
			      <gw:button name="btnReset" onClick="goReset()">重置</gw:button>
</div>	  
    <%@include file="/common/resize.jsp" %>
</body>
</html>
