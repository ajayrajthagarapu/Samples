<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>REWORK STATUS JSP</title>
</head>
<body>
<!-- Getting the partial Rework status records -->
<table border="2" align="center">
				<tr>
				<td>APPLICATION NUMBER</td>
				<td>CUSTOMER ID</td>
				<td>FIRST NAME</td>
			    <td>LAST NAME</td>
				<td>PAN NUMBER</td>
				<td>APPLICATION STATUS</td>
 				</tr>
 				
 				<%
 				Object obj=request.getAttribute("ReworkRecords");
 				ArrayList<HashMap<String,String>> arr=(ArrayList<HashMap<String,String>>)obj;
 				Iterator<HashMap<String,String>> itr=arr.iterator();
 				while(itr.hasNext())
 				{
 					HashMap<String , String> hm=itr.next();
 					String panNo=hm.get("panNo");
 					String customerId=hm.get("customerId");
 				    String firstName=hm.get("firstName");
 				    String lastName=hm.get("lastName");
 				    String appStatus=hm.get("appStatus");
 				    String appNo=hm.get("appNo");
 				    
 				 				
 				
 				%>
 				<tr>
 				<td>
 				<a href="DisplayReworkDetailsController?appNo=<%=appNo%>"><%=appNo%></a>
				<td><%=customerId%></td>
	       		<td><%=firstName%></td>
	       		<td><%=lastName%></td>
	       		<td><%=panNo%></td>
	       		<td><%=appStatus%></td>
	       		</tr>
	       		
	       		<%
	       		
 				}
 	
	       		
	       		%>






</table>



</body>
</html>