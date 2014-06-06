<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html"%>
<%
	String path = request.getContextPath();
	String fullurl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	String msg = (String)request.getAttribute("msg");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.css" />
   <script src="<%=fullurl%>/dhtmlxSuite/dhtmlx/dhtmlx.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=fullurl%>/css/main.css" />

  <script language="javascript" src="<%= fullurl %>/js/date_validate.js"></script>	
  <script language="javascript" src="<%= fullurl %>/js/ctrl_util.js"></script>
	<script type="text/javascript">
      var contextpath = "<%=fullurl%>";	
      var fulllink = contextpath + "system/ResourceTableView.do";		

	function goCancel(){		
		
    	parent.closedialog();
	}
	
	function goSubmit(){
    	
    	var cityid = getElement("record(cityCode)").value;
		var categoryid = getElement("record(categoryId)").value;
		var nodeid;
		if (categoryid!="") {
			nodeid = cityid+"_"+categoryid;
		}else {
			nodeid = cityid;
		}		
		document.forms[0].submit();
    	parent.setSelectTreeNode(nodeid);
	}

</script>
<style type="text/css">
   html, body {width:100%; height:100%; margin:0px; padding:0px; overflow:hidden;}
</style>
</head>

<body>
<div id="formDiv" class="formDiv">
  <html:form method="post" action="/system/ResourceTableView.do">
  <html:hidden property="record(id)" />
		    <table id="tblForm" cellpadding="0" cellspacing="0" width="100%" class="formTable">
			 <tr >
				  <td width="20%">所属地市</td>
				  <td class="textL">
				  <bean:write name="resourceTableForm" property="record(cityName)"/>&nbsp;
				  </td>
				 
			 </tr>
			 <tr >
				  <td width="20%">所属分类</td>
				  <td class="textL">
				  <bean:write name="resourceTableForm" property="record(categoryName)"/>&nbsp;		   
				  </td>
				 
			 </tr>
			 <tr>
			  <td >资源库名称</td>
					<td class="textL">
						 <bean:write name="resourceTableForm" property="record(cnName)"/>
					  &nbsp;
					</td>
			</tr>
			<tr>
			  <td >资源库英文名</td>
					<td class="textL">
					<bean:write name="resourceTableForm" property="record(name)"/>
					   &nbsp;
					</td>
			</tr>
			<tr>
			  <td >代码</td>
					<td class="textL">
					<bean:write name="resourceTableForm" property="record(code)"/>
					   &nbsp;
					</td>
			</tr>
			<tr>
			  <td >安全等级</td>
					<td class="textL">
					<bean:write name="resourceTableForm" property="record(securityLevel)"/>
					  &nbsp;
					</td>
			</tr>
			<tr>
			   <td>备注</td>
				 <td class="textL">
				<bean:write name="resourceTableForm" property="record(remark)"/>
				&nbsp;
				 </td>
			</tr>
	 </table>
  </html:form>
  </div>
  <div id="btnDiv" class="btnDiv">
</div>
<%@include file="/common/resize.jsp" %>
</body>
</html>
