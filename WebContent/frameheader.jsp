<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="Turquoise">
<%

String userid=(String)session.getAttribute("name");

%>
<img src="images/java_logo.jpg"/><br/>
 <%=new java.util.Date() %> 
  <%=userid %> 
  <div align="right">
  <a href="logout.jsp" target="_top">logout</a>
  </div>
 <table align="left"><tr></tr></table> 
</body>
</html>