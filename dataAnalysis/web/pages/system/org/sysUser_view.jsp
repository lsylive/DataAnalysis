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
	<link rel="stylesheet" type="text/css" href="<%=CONTEXT_PATH%>css/main.css" />

  <script language="javascript" src="<%= CONTEXT_PATH %>/js/date_validate.js"></script>	

	<script type="text/javascript">
      var contextpath = "<%=CONTEXT_PATH%>";	
      var fulllink = contextpath + "system/sysUser.do";		

	function goCancel(){
		parent.closedialog(false);
	}
	</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body>
<div id="formDiv" class="formDiv">	
  <html:form method="post" action="/system/sysUser.do">

		<table cellpadding="0" cellspacing="0" width="98%" class="formTable">
			 <tr >
				  <td width="15%">姓名：</td>
				  <td width="35%" class="textL">
					   &nbsp;<bean:write name="sysUserForm" property="record(userName)" />
				  </td>
				  <td width="15%">顺序号：</td>
					<td width="35%" class="textL">
					   &nbsp;<bean:write name="sysUserForm" property="record(orderNo)" />
					</td>
			 </tr>
			 <tr>
				  <td >账号：</td>
				  <td  class="textL">
					   &nbsp;<bean:write name="sysUserForm" property="record(userAccount)" />
				  </td>
				  <td >部门：</td>
				  <td class="textL">
					   &nbsp;<bean:write name="sysUserForm" property="record(deptName)" />
				  </td>
			 </tr>
			 <tr>
				  <td >行政等级：</td>
				  <td class="textL">
					   &nbsp;<bean:write name="sysUserForm" property="record(adminLevelName)" />
				  </td>
				  <td>用户状态：</td>
					<td class="textL">
					   &nbsp;<bean:write name="sysUserForm" property="record(userStatusName)" />
					</td>
			</tr>
			 <tr>
				  <td >角色：</td>
				  <td colspan="3"  class="textL">
					   &nbsp;<bean:write name="sysUserForm" property="record(roleNames)" />
			    </td>      
			</tr>			
			<tr >
			   <td vAlign="top">备注：</td>
				 <td colspan="3" class="textL" style="white-space:wrap;height:100px;" vAlign="top">
					  &nbsp;<bean:write name="sysUserForm" property="record(remark)" filter="false" />
				 </td>
			</tr>
	 </table>

 </html:form>	
</div>
<div id="btnDiv" class="btnDiv">
			<gw:button name="btnCancel" onClick="goCancel()">返回</gw:button>
</div>
<%@include file="/common/resize.jsp" %>  
</body>
</html>
