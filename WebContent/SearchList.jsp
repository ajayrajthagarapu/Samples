<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SearchList Form</title>
</head>
<body bgcolor="Turquoise">
<form name="search list" method="get" >
<table border="1">

<tr align="center">
	<td><b>APPLICATIONNUMBER</b></td>
	<td><b>CUSTOMERID</b></td>
	<td><b>APPLICATIONSTATUS</b></td>
</tr>

<%
Object obj=request.getAttribute("searchList");
ArrayList<HashMap<String,String>> arr=(ArrayList<HashMap<String,String>>)obj;
Iterator<HashMap<String,String>> itr=arr.iterator();
while(itr.hasNext())
{
	HashMap<String,String> hm=itr.next();
	String appNo=hm.get("applicationNumber");
	String custId=hm.get("customerId");
	String appStatus=hm.get("applicationStatus");
	
%>
	<tr>
		<td>		
			<b><a href="RetreiveDetailsByAppNoInSearch?appNo=<%=appNo%>"><%=appNo%></a></b>
		</td>
		<td>
			<b><%=custId%></b>
		</td>
		<td>
			<b><%=appStatus%></b>
		</td>
	</tr>
	
<% } %>
</table>
</body>
</html>