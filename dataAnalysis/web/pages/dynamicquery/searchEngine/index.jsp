<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/config/tag/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/config/tag/gw-tag.tld" prefix="gw" %>
<%
String path = request.getContextPath();
String CONTEXT_PATH = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<style type="text/css">
		html{ background:url(<%=CONTEXT_PATH%>pages/dynamicquery/searchEngine/body_bg.jpg) bottom right no-repeat #fff; border:1px solid #f7f7f7; padding:0; margin:0; text-align:center; height:100%; overflow:hidden;}
		p{ line-height:32px;}
		h1{ text-indent:-9999px; height:80px; background:url(<%=CONTEXT_PATH%>pages/dynamicquery/searchEngine/h1_bg.jpg) no-repeat center;}
		.text{background: none repeat scroll 0% 0% #fff; border-width: 1px; border-style: solid; border-color: #CCCCCC #999999 #999999 #CCCCCC; color: #000; font: 18px arial,sans-serif bold; height: 23px; margin: 0pt; padding: 3px 8px 0pt 6px; vertical-align: middle; }
		.widthtext{width:340px;}
		.widthtext1{width:40px; text-align:center;}
		.btn{ height:30px; padding:0 12px; line-height:28px; font-size:15px; outline:none; border-color:#CCCCCC #999999 #999999 #CCCCCC; border-width:1px; border-style:solid; background:url(<%=CONTEXT_PATH%>pages/dynamicquery/searchEngine/btn_bg.jpg) repeat-x; font-weight:bold;}
	</style>
</head>

<body>
	<h1>全文搜索查询</h1>
	<html:form  action="/dynamicquery/searchEngine.do?action=RESULT" method="post">
		<p style="padding-left:100px;">请输入关键词:&nbsp;
		<html:text property="record(queryStr)" size="44" styleClass="text widthtext"></html:text>
	  	&nbsp;&nbsp;显示记录数:&nbsp;<html:text property="record(maxresults)" value="100" styleClass="text widthtext1"></html:text>
		</p>
		<p>
			<input type="submit" value="搜索" class="btn"/>
		</p>
        </html:form>
</body>
</html>