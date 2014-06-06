<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<script>
		if(parent.showPage){
			parent.showPage();
		}
		alert("<%=request.getAttribute("msg")%>");
		</script>	
	</head>
	<body >
		
</body>			
</html>	
	 