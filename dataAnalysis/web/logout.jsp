<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<body>
<script type="text/javascript">
<%
   String timeout =	request.getParameter("timeout") ;   
   if("true".equalsIgnoreCase(timeout)){%>
   		alert('<fmt:message key="session.invalid" />') ;		
   <%}%>
   top.document.location="login.jsp";
</script>
</body>
</html>