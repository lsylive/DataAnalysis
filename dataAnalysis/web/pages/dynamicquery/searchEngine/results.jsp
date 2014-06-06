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

<%@page import="java.util.List"%><html><head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<title>查询结果 </title>
<style type="text/css">
.highlight{
background:yellow;
color:#CC0033;
}
		html{ background:url(<%=CONTEXT_PATH%>pages/dynamicquery/searchEngine/body_bg.jpg) bottom right no-repeat #fff; border:1px solid #f7f7f7; padding:0; margin:0; text-align:center; height:100%; }
		p{ line-height:32px;}
		h1{ text-indent:-9999px; height:80px; background:url(<%=CONTEXT_PATH%>pages/dynamicquery/searchEngine/h1_bg.jpg) no-repeat center;}
		.text{background: none repeat scroll 0% 0% #fff; border-width: 1px; border-style: solid; border-color: #CCCCCC #999999 #999999 #CCCCCC; color: #000; font: 18px arial,sans-serif bold; height: 23px; margin: 0pt; padding: 3px 8px 0pt 6px; vertical-align: middle; }
		.widthtext{width:340px;}
		.widthtext1{width:40px; text-align:center;}
		.btn{ height:30px; padding:0 12px; line-height:28px; font-size:15px; outline:none; border-color:#CCCCCC #999999 #999999 #CCCCCC; border-width:1px; border-style:solid; background:url(<%=CONTEXT_PATH%>pages/dynamicquery/searchEngine/btn_bg.jpg) repeat-x; font-weight:bold;vertical-align:middle;}
		a{ color:#1111CC; font-size:16px; }
		table{ text-align:left;}
		.fontStyle{ padding:3px; display:block; font-size:13px;}
		.box{width:100%; height:16px; background:#D9E1F7; font-size:12px; text-align:left; text-indent:20px; margin:10px auto; }
</style>


<script type="text/javascript">

function openFile(path){
		//alert(path);
		//document.getElementById("openFileForm").action=path;
		//document.getElementsByName("record(openFilePath)")[0].value=path;
		//document.getElementById("openFileForm").submit();		
		//var pathtemp = encodeURI(path);
		//pathtemp = encodeURI(pathtemp)
		var url = "<%=CONTEXT_PATH%>dynamicquery/searchEngine.do?action=OPENFILE&record(openFilePath)="+pathtemp;
		alert(url);
		window.open(url);
		//document.location.href=url;
	}

</script>
</head>

<body>
<div style="display: none;"><iframe id="downloadframe" name="downloadframe"></iframe></div>
<html:form styleId="openFileForm" action="/dynamicquery/searchEngine.do?action=OPENFILE" method="post" target="downloadframe">
<html:hidden property="record(openFilePath)"/>
</html:form>
<h1>全文搜索查询</h1>
<html:form  action="/dynamicquery/searchEngine.do?action=RESULT" method="post">
		<p style="padding-left:200px;">请输入关键词:&nbsp;
		<html:text property="record(queryStr)" size="44" styleClass="text widthtext"></html:text>
			&nbsp;&nbsp;显示记录数:&nbsp;&nbsp;&nbsp;<html:text property="record(maxresults)" styleClass="text widthtext1"></html:text> &nbsp;<input type="submit" value="搜索" class="btn"/>
		</p>
</html:form>
<logic:empty name="results">
没有找到您要查询的内容！
</logic:empty>
<logic:notEmpty name="results">
<div class="box">温馨提示:共搜索到&nbsp;<strong><%=((List)request.getAttribute("results")).size() %></strong>&nbsp;条记录</div>
<logic:iterate id="map" name="results">
<table border="0" cellpadding="0" cellspacing="0" width="93%">
<tr>
<td >

<a  href='<%=CONTEXT_PATH%>dynamicquery/searchEngine.do?action=OPENFILE&record(openFilePath)=<bean:write name="map" property="path"/>' target="_blank">
<!-- <a  href='javascript:onClick=openFile("<bean:write name="map" property="path"/>")'> -->
<font><bean:write name="map" property="title"/></font>
</a>

<font class="fontStyle"><bean:write name="map" property="contents" filter="false"/>
<br>
</font>
</td></tr></table>
<br>
</logic:iterate>
</logic:notEmpty>
</body>
</html>   
