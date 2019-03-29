<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN PAGE</title>
</head>
<center>
	<h1>LOGIN PAGE</h1>
</center>
<body bgcolor="Turquoise">
	<form name="loginform" action="LoginServlet" method="get">
		<table align="center">
			<tr>
				<td>USERID</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>PASSWORD</td>
				<td><input type="password" name="password"></td>
			</tr>

			<tr>
				<td><input type="submit" name="button" value="submit"></td>
				<td><input type="reset" name="reset" value="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>
