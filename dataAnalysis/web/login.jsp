<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%
	String path = request.getContextPath();
	String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广州市国家安全局数据综合分析平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Page-Enter" content="blendTrans(Duration=1.0)" />
<meta http-equiv="Page-Exit" content="blendTrans(Duration=0.5)" />

<style type="text/css">
body, div, ul, li, h1, h2, h3, pre, form, fieldset, input, textarea, p, blockquote, th, td {
	margin:0;
	padding:0;
	font-size:14px;
}
table {
	border-collapse:collapse;
	border-spacing:0;
}
fieldset, img {
	border:0;
}
address, caption, cite, code, dfn, em, strong, th, var {
	font-style:normal;
	font-weight:normal;
}
h1 {
	font-size:22px;
	color:#000;
}
/* ---------------------------------UP Reset------------------------------------------- */
body {
	font:100% Arial, sans-serif;
	text-align:center;
	background:#06416b url(<%=CONTEXT_PATH%>images/main_bg.jpg) repeat-x center top;
	overflow-x:hidden;
}
div#loginWrap {
	text-align:left;
	margin:0 auto;
	width:1001px;
}
div#loginWrap h1.top-1 {
	background: url(<%=CONTEXT_PATH%>images/top_11.jpg) no-repeat center top;
	height:96px;
	text-indent:-9999px;
}
div#loginWrap div.top-2 {
	background: url(<%=CONTEXT_PATH%>images/top_21.jpg) no-repeat center top;
	height:90px;
}
div#loginWrap div.top-3 {
	background: url(<%=CONTEXT_PATH%>images/top_11.jpg) no-repeat center top;
	font-size:48px;
	color:white;
	height:88px;
}
div#loginWrap div.top-4 {
	background: url(<%=CONTEXT_PATH%>images/top_21.jpg) no-repeat center top;
	height:63px;
}
div#login {
	margin: 0 auto;
	height:105px;
	background: url(<%=CONTEXT_PATH%>images/form_left_bg1.jpg) no-repeat left top;
	text-align:center;
	position:relative;
	z-index:3;
}
div#footer{
	background: url(<%=CONTEXT_PATH%>images/footer_bg.jpg) no-repeat center top;
	height:114px;
}
div#footer p{
	color:#FFF;
	font-size:12px;
	margin:3px auto;
	padding-top:10px;
	text-align:center;
}
div#login div.wrap {
	position:absolute;
	top:0px;
	left:360px;
	padding:0;
	width:324px;
	height:105px;
	background: url(<%=CONTEXT_PATH%>images/form_bg.jpg) repeat-x center top;
}
div#login div.wrap div.wrap-text{
	width:320px;
	height:105px;
	margin:0 2px;
	background: url(<%=CONTEXT_PATH%>images/form_text_bg.jpg) repeat-x center top;
}
div#login div.wrap div.wrap-text .user-text{
	display:inline-block;
	height:35px;
	margin-top:10px;
	width:95%;
}
span.inputContent {
	float:left;
	width:75px;
	height:24px;
	text-align:right;
	color:#07678f;
	font-size:16px;
	font-family:"黑体";
	margin-top:5px;
}
.inputA,.inputA-hover,.inputB,.inputB-hover{/* --输入框样式-- */
	width:212px;
	height:28px;
	background:url(<%=CONTEXT_PATH%>images/text_bg.gif) left top;
	float:left;
	border:1px solid #b0b4b7;
	line-height:28px;
	text-indent:25px;
}
.inputB{/* --输入框样式-- */
	background-position: left -56px;
}
.inputA-hover{/* --输入框样式-- */
	background:#93f1fb url(<%=CONTEXT_PATH%>images/text_bg.gif) left -28px;
}
.inputB-hover{/* --输入框样式-- */
	background:#93f1fb url(<%=CONTEXT_PATH%>images/text_bg.gif) left -84px;
}

div#login div.btn-area {
	position:absolute;
	top:0px;
	left:705px;
	padding:0;
	width:344px;
	height:105px;
}
.butStyle,.butStyle-hover,.resStyle,.resStyle-hover,.cvStyle,.cvStyle-hover {/* --按钮-- */
	width:81px;
	height:79px;
	display:block;
	float:left;
	z-index:5;
	border:none;
	text-indent:-9999px;
	cursor:pointer;
	margin-top:12px;
	margin-right:16px;
	background:url(<%=CONTEXT_PATH%>images/btn.jpg) left top;
}
.butStyle-hover {
	background-position: -81px top;
}
.resStyle {
	background-position: -162px top;
}
.resStyle-hover {
	background-position: -243px top;
}
.cvStyle {
	background-position: -324px top;
}
.cvStyle-hover {
	background-position: -405px top;
}
</style>
   
<script language="javascript" type="text/javascript">  
var msg="";

function checkLength(o,n,min,max) {
   if(o.value.length > max || o.value.length < min) {
	    msg=msg+n+("长度必须介于"+min+"和"+max+"之间。\r\n");
			return false;
	 } 
	 else {
      return true;
   }
}

function checkRegexp(o,regexp,n) {
   if(regexp.test(o.value)) {
      return true;
   } 
   else {
	    msg=msg+n+"\r\n";
      return false;
	 }
}

function checkInput(userName,password){
   var bValid = true;

   bValid = bValid & checkLength(userName,"用户名",4,30);
   bValid = bValid & checkRegexp(userName,/^[a-z]([0-9a-z_])+$/i,"用户名必须是字母或数字。");
   bValid = bValid & checkLength(password,"密码",8,30);
					
   return bValid;
}
           
function goValidate(){
   var uName = document.getElementById("userName");
   var password =document.getElementById("password");

   if(checkInput(uName,password)){
      login();
   }
   else {
      alert(msg);
      msg="";
   }
}

function login(){
   document.forms[0].submit();
}

function showmsg(){
	var msg='<%=request.getAttribute("errMsg") %>';
	if(msg!='null')
		alert(msg);
}
            
function goSafeLogin(){
	window.location.reload('https://222.29.40.20:446/dataApp/readCACookie.jsp');
	//document.getElementById("safeIFrame").src="https://222.29.40.20:448/dataApp/readCACookie.jsp";
}

</script>  
</head>
<body onload="showmsg()">
	<div id="loginWrap">
	
	<h1 class="top-1">数据综合分析平台</h1>
      <div class="top-2"></div>
      <div class="top-3">数据综合分析系统</div>
      <div class="top-4"></div>
    <div id="login">
        <form id="loginForm" action="<%=CONTEXT_PATH%>user/login.do?action=login" method="post">
           	<div class="wrap">
            	<div class="wrap-text">       
					 <div class="user-text"><span class="inputContent" id="nm">用户名：</span>
                		 <input type="text" name="userName" id="userName" class="inputA" maxlength="23" value="admin" onmouseover="this.className='inputA-hover'"  onmouseout="this.className='inputA'"/>
					 </div>                        
                     <div class="user-text"><span class="inputContent" id="pw">密  &nbsp;码：</span>
                		<input type="password" name="password" id="password" class="inputB" value="12345678"  maxlength="23" onmouseover="this.className='inputB-hover'"  onmouseout="this.className='inputB'" />
                     </div>
                </div>
        	</div>
            <div class="btn-area">
            <input  type="button" onclick="goValidate()" value="登录" name="login" class="butStyle"   onmouseover="this.className='butStyle'"  onmouseout="this.className='butStyle-hover'"  />
<!--            
       		<input  type="button" onclick="goSafeLogin()" value="ca登录"  class="cvStyle"    onmouseover="this.className='cvStyle-hover'"  onmouseout="this.className='cvStyle'" />
-->
       		 </div>
        </form>
    </div>
    
</div>
   
</body>
</html>