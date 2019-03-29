<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VERIFY PAGE</title>
</head>
<body>

<!-- showing only partial records of verify and new Status -->

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
			Object obj=request.getAttribute("PartialRecords");
			ArrayList<HashMap<String, String>> arr=(ArrayList<HashMap<String, String>>)obj;
			Iterator<HashMap<String, String>> itr=arr.iterator();
			while(itr.hasNext())
			{
				HashMap<String , String> hm=itr.next();
				String appno=hm.get("APPLICATIONNUMBER");
				String custId=hm.get("CUSTOMERID");
				String firstName=hm.get("FIRSTNAME");
				String lastName=hm.get("LASTNAME");
				String panno=hm.get("PANNO");
				String appstatus=hm.get("APPLICATIONSTATUS");

            %>
       		<tr>
       		<td><a href="DisplayFormDetailsController?appno=<%=appno%>"><%=appno%></a></td>
       		<td><%=custId%></td>
       		<td><%=firstName%></td>
       		<td><%=lastName%></td>
       		<td><%=panno%></td>
       		<td><%=appstatus%></td>
       
       		</tr>

          <%
          
			}
          
          %>

</table>




</body>
</html>