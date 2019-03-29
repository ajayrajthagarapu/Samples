<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Jsp</title>
</head>
<center>
	<h1>WELCOME TO SEARCH FORM</h1>
</center>
<body bgcolor="Turquoise">

<form name="searchform" action="SearchController" method="get">

<table align="center" border="1">
<tr align="center">
<td><b>Application number:</b></td><td><input type="text" name="appno" value=""></td>
</tr>

<tr>
<td><b>Customer ID:</b></td><td><input type="text" name="custid" value=""></td>
</tr>

<tr align="center">
<td><b>Application Status</b></td>
<td>
<select name="applicationstatus">
<option value="ALL">All</option>
<option value="NEW">New</option>
<option value="verify">Verify</option>
<option value="approve">Approve</option>
<option value="reject">Reject</option>
</select>
</td>


<tr  align="center">
<td colspan="2"><input type="submit" name="button" value="submit">
<input type="reset" name="reset" value="reset"></td>
</tr>


</table>
</form>


</body>
</html>