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
<script type="text/javascript">
	function uploadfile(){
		if($NAME('filename').value!=''){
			document.forms[0].submit();
		}else
			alert('请选择上传的文件');
	}
</script>
</head>
  
  <body>
   <html:form action="query/subjectBatchQuery.do" enctype="multipart/form-data" target="_parent">
   			<html:hidden property="id" />
			<html:hidden property="action1" value="importfile" />
			<html:hidden property="tableId" />
			<logic:present name="selTabList">
			<logic:iterate id="tab" name="selTabList">
				<input type="hidden" name="selTable" value="<bean:write name='tab' property='id' />" />
			</logic:iterate>
			</logic:present>
   <table cellpadding="0" cellspacing="0" width="100%" class="formTable">
   	<tbody>
   	<tr>
   		<td width="40%">请选择文件:</td>
   		<td><html:file property="filename"></html:file>  </td>
   	</tr>
   	<tr class="btnTr">
   		<td class="textC" colspan="2"><a href="javascript:onClick=uploadfile()" class="btnStyle" name="btnQuery" ><strong>确定</strong></a>&nbsp;
   			<a href="javascript:onClick=parent.closedialog()" class="btnStyle" name="btnRefresh" ><strong>关闭</strong></a>
   		</td>
   	</tr>
   	</tbody>
   	</table>
   </html:form>
  </body>
</html>
