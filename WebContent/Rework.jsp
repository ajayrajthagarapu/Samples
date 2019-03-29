<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>verify form</title>
</head>
<body bgcolor="Turquoise">
	   <form  action="UpdateAllTables" method="post" enctype="multipart/form-data">


			<!-- Displaying complete form details and DE can forward to verifier by changing status to verify -->
			<%

			Object obj=request.getAttribute("sendToReworkJsp");
			ArrayList<HashMap<String,String>> Reworkformdetails=(ArrayList<HashMap<String,String>>)obj;
			Iterator<HashMap<String, String>> itr=Reworkformdetails.iterator();
			
			while(itr.hasNext())
			{
				HashMap<String , String> hm=itr.next();
				String custid1=hm.get("customerId");
				String FIRSTNAME=hm.get("FIRSTNAME");
				String MIDDLENAME=hm.get("MIDDLENAME");
				String LASTNAME=hm.get("LASTNAME");
				String GENDER=hm.get("GENDER");
				String dob=hm.get("dob");
				String PANNO=hm.get("PANNO");
				String FATHERNAME=hm.get("FATHERNAME");
				String EMPLOYMENTTYPE=hm.get("EMPLOYMENTTYPE");
				String NAMEOFEMPLOYER=hm.get("NAMEOFEMPLOYER");
				String EXPERIENCE=hm.get("EXPERIENCE");
				String DESIGNATION=hm.get("DESIGNATION");
				String ANNUALINCOME=hm.get("ANNUALINCOME");
				String CORRESPONDENCEADDRESS=hm.get("CORRESPONDENCEADDRESS");
				String PERMANENTADDRESS=hm.get("PERMANENTADDRESS");
				String WORKPLACEADDRESS=hm.get("WORKPLACEADDRESS");
				
				String APPLICATIONNUMBER=hm.get("APPLICATIONNUMBER");
				String LOANAMOUNTREQUESTED=hm.get("LOANAMOUNTREQUESTED");
				String TENUREREQUESTED=hm.get("TENUREREQUESTED");
				String APPLICATIONSTATUS=hm.get("APPLICATIONSTATUS");
				
											
			%>
<center>
	<h1>LOAN APPLICATION FORM</h1>
</center>


		<table border="3" border color="black" align="center">
		
		
		<tr align="center">
				<td><b>Customer Id:</b></td>
				<td><input type="text" name="custid" readonly="read only"
					value="<%=custid1%>" size="40"></td>
			</tr>
			
			
		<tr align="center">
				<td><b>Application Number:</b></td>
				<td><input type="text" name="appno" readonly="read only"
					value="<%=APPLICATIONNUMBER%>"  maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>First Name:</b></td>
				<td><input type="text" name="firstname" placeholder="firstname"
					value="<%=FIRSTNAME%>"  maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Middle Name:</b></td>
				<td><input type="text" name="middlename"
					placeholder="middlename" value="<%=MIDDLENAME%>"  maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Last Name:</b></td>
				<td><input type="text" name="lastname"
				 value="<%=LASTNAME%>" 	size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Gender</b></td>
				<td>
				<input type="text" name="GENDER" value="<%=GENDER%>">
				</td>
			</tr>



			<tr align="center">
				<td><b>Date of Birth</b></td>
				<td><input type="date" value="<%=dob%>" name="dob"></td>
			</tr>

			<tr align="center">
				<td><b>PAN No</b></td>
				<td><input type="text" name="panno" placeholder="pan no" 
					value="<%=PANNO%>" maxlenth="15" size="40"></td>
			</tr>



			<tr align="center">
				<td><b>Fathers Name/Spouse Name/Guardian</b></td>
				<td><input type="text" name="fathername" 
					placeholder="Fathers Name/Spouse Name/Guardian" value="<%=FATHERNAME%>"
					maxlenth="100" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Employment Type</b></td>
				<td><input type="text" name="employment" 
					placeholder="Employment Type" value="<%=EMPLOYMENTTYPE%>" maxlenth="15" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Name of Employer</b></td>
				<td><input type="text" name="NameofEmployer" 
					placeholder="Name of Employer" value="<%=NAMEOFEMPLOYER%>" maxlenth="120" size="40"></td>
			</tr>
			
			<tr align="center">
				<td><b>Experience</b></td>
				<td><input type="number" name="experience" 
					placeholder="experience" value="<%=EXPERIENCE%>" maxlenth="120" size="40"></td>
		
			<tr align="center">
				<td><b>Designation</b></td>
				<td><input type="text" name="designation" 
					placeholder="designation" value="<%=DESIGNATION%>" maxlenth="60" size="40"></td>
			</tr>
			
				</tr>
			
				<tr align="center">
				<td><b>Annual Income</b></td>
				<td><input type="number" name="annualincome" 
					placeholder="annualincome" value="<%=ANNUALINCOME%>" maxlenth="12" size="40"></td>
			</tr>

			<tr align="center">
				<td colspan="2"><b>Address Details</b></td>
			</tr>

			<tr align="center">
				<td><b>Correspondence Address</b></td>
				<td><input type="text" name="correspondence" 
					placeholder="Correspondence Address" value="<%=CORRESPONDENCEADDRESS%>" maxlenth="500"
					size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Work Address</b></td>
				<td><input type="text" name="workaddress" 
					placeholder="Work Address" value="<%=WORKPLACEADDRESS%>" maxlenth="500" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Permanent Address</b></td>
				<td><input type="text" name="permanent" 
					placeholder="Permanent Address" value="<%=PERMANENTADDRESS%>" maxlenth="500" size="40"></td>
			</tr>
			
			<tr align="center">
				<td colspan="2"><b>Operational Parameters</b></td>
			</tr>
			
				

			<tr align="center">
				<td><b>Loan Amount Requested</b></td>
				<td><input type="number" name="loanamountrequested" 
					placeholder="Loan Amount Requested" value="<%=LOANAMOUNTREQUESTED%>" maxlenth="500"
					size="40"></td>
						
				</tr>
				
				
			
			<tr align="center">
				<td><b>Tenure Requested</b></td>
				<td><input type="number" name="tenurerequested" 
					placeholder="Tenure Requested" value="<%=TENUREREQUESTED%>" maxlenth="20" size="40"></td>
		</tr>
		
		
		
					
			<%-- <tr align="center">
				<td><b>Application Status</b></td>
				<td><input type="text" name="APPLICATIONSTATUS"  value="<%=APPLICATIONSTATUS%>"
					 maxlenth="20" size="40"></td> --%>
		</tr>
			
			<tr align="center">
					<td colspan="2"><h3><b>Document Information</h3></b></td>
					</tr>
					
				<tr align="center">
				<td><b>Application Form</b></td>
				
				<td><input type="file" name="applicationform" value="applicationform"><a href="DocumentDownloadCOntroller?submit=retreiveDocument&applicationnumber=<%=APPLICATIONNUMBER%>&form=addApplication">
				<b>Application Form</b></a>
				</td>
				</tr>
				
				<tr align="center">
				<td><b>Id Documents</b></td>
				<td><input type="file" name="iddocument" value="iddocument"><a href="DocumentDownloadCOntroller?submit=retreiveDocument&applicationnumber=<%=APPLICATIONNUMBER%>&form=ID Document">
				<b>Id Documents</b></a>
				</td>
				</tr>
				
						
				<tr align="center">
				<td><b>Support Documents</b></td>
				<td><input type="file" name="supportdocument" value="supportdocument"><a href="DocumentDownloadCOntroller?submit=retreiveDocument&applicationnumber=<%=APPLICATIONNUMBER%>&form=Support Document">
				<b>Support Documents</b></a>
				</td>
				</tr>
				
			
					
			
				
			  <tr align="center">
				<td colspan="3">
				<input type="submit" name="submit" value="verify">
				</td>
             </tr>
             </table>

	<%
}


%>




</form>
</body>
</html>