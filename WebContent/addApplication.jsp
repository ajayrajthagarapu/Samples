<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>add application form</title>
</head>
<center>
	<h1>LOAN APPLICATION FORM</h1>
</center>
<body bgcolor="Turquoise">

	<form name="addapplication" method="post" action="WorkFlowController" onsubmit="return validate()" enctype="multipart/form-data">
		<table border="3"  border color="black" align="center">

			<tr align="center">
				<td><b>First Name:</b></td>
				<td><input type="text" name="firstname"  placeholder="firstname"
					value=""  maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Middle Name:</b></td>
				<td><input type="text" name="middlename"
					placeholder="middlename" value="" maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Last Name:</b></td>
				<td><input type="text" name="lastname" placeholder="lastname"
					maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Gender</b></td>
				<td>
					<input type="radio" name="gender" value="M" checked>Male 
					<input type="radio" name="gender" value="F">Female
				</td>
			</tr>



			<tr align="center">
				<td><b>Date of Birth</b></td>
				<td><input type="date" name="dob"></td>
			</tr>

			<tr align="center">
				<td><b>PAN No</b></td>
				<td><input type="text" name="panno" placeholder="pan no"
					value="" maxlenth="15" size="40"></td>
			</tr>



			<tr align="center">
				<td><b>Fathers Name/Spouse Name/Guardian</b></td>
				<td><input type="text" name="fathername"
					placeholder="Fathers Name/Spouse Name/Guardian" value=""
					maxlenth="100" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Employment Type</b></td>
				<td><input type="text" name="employment"
					placeholder="Employment Type" value="" maxlenth="15" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Name of Employer</b></td>
				<td><input type="text" name="NameofEmployer"
					placeholder="Name of Employer" value="" maxlenth="120" size="40"></td>
			</tr>
			
			<tr align="center">
				<td><b>Experience</b></td>
				<td><input type="number" name="experience"
					placeholder="experience" value="" maxlenth="120" size="40"></td>
		
			<tr align="center">
				<td><b>Designation</b></td>
				<td><input type="text" name="designation"
					placeholder="designation" value="" maxlenth="60" size="40"></td>
			</tr>
			
				</tr>
			
				<tr align="center">
				<td><b>Annual Income</b></td>
				<td><input type="number" name="annualincome"
					placeholder="annualincome" value="" maxlenth="12" size="40"></td>
			</tr>

			<tr align="center">
				<td colspan="2"><b>Address Details</b></td>
			</tr>

			<tr align="center">
				<td><b>Correspondence Address</b></td>
				<td><input type="text" name="correspondence"
					placeholder="Correspondence Address" value="" maxlenth="500"
					size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Work Address</b></td>
				<td><input type="text" name="workaddress"
					placeholder="Work Address" value="" maxlenth="500" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Permanent Address</b></td>
				<td><input type="text" name="permanent"
					placeholder="Permanent Address" value="" maxlenth="500" size="40"></td>
			</tr>
			<tr align="center">
				<td colspan="2"><b>Operational Parameters</b></td>
			</tr>

			<tr align="center">
				<td><b>Loan Amount Requested</b></td>
				<td><input type="number" name="loanamountrequested"
					placeholder="Loan Amount Requested" value="" maxlenth="20"
					size="40"></td>
			<tr align="center">
				<td><b>Tenure Requested</b></td>
				<td><input type="number" name="tenurerequested"
					placeholder="Tenure Requested" value="" maxlenth="20" size="40"></td>
		
					<tr align="center">
					<td colspan="2"><h3><b>Document Information</h3></b></td>
					</tr>
					
							<tr align="center">
				<td><b>Application Form</b></td>
				<td><input type="file" name="applicationform" value="applicationform"></td>
					</tr>
					
							<tr align="center">
				<td><b>Id Documents</b></td>
				<td><input type="file" name="iddocument" value="iddocument"></td>
					</tr>
					
							<tr align="center">
				<td><b>Support Documents</b></td>
				<td><input type="file" name="supportdocument" value="supportdocument"></td>
					</tr>
					
			
				
			<tr align="center">
				<td colspan="2"><input type="submit" name="submit"
					value="submit"><input type="reset" name="reset"
					value="reset"></td>

			</tr>

		</table>
	</form>
</body>
</html>