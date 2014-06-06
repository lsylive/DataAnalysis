<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String CONTEXT_PATH = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
    Cookie[] cookies = request.getCookies();
    if(cookies == null){
        cookies = new Cookie[0];    
    }

    String user_account = "";
    for(int i = 0; i < cookies.length; i ++)
    {
        Cookie cookie = cookies[i];
        if("KOAL_CERT_CN".equals(cookie.getName()))
        {
            user_account = cookie.getValue();
        }      
    }
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<body> 
<div style="display:none;">
        <form id="loginForm" name="loginForm" action="<%=CONTEXT_PATH%>user/login.do?action=login&SSLLogin=FrSSLLogin" method="post">
              <input type="text" name="userName" id="userName" maxlength="23" value="<%=user_account %>" />
              <input type="password" name="password" id="password" maxlength="23" value="" />
        </form>
</div>     
</body>
</html>

<script language="javascript" type="text/javascript"> 
var user_account ='<%=user_account %>';

//alert("in the readcookie.jsp, user_account: " + user_account);
//alert("CONTEXT_PATH: " + '<%=CONTEXT_PATH%>');

  if(user_account.length>0){ 
      //alert("safe_check pass");
      document.loginForm.submit();
  }else{
      alert("CA认证没有通过"); 
      window.location.reload('<%=CONTEXT_PATH%>login.jsp');
  }

</script>