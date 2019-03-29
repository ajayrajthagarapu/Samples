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
	   <form name="approverform" method="get" >
           <table border="3" border color="black" align="center">
			<%

			Object obj=request.getAttribute("SendToFinalSearch");
			ArrayList<HashMap<String,String>> verifyformdetails=(ArrayList<HashMap<String,String>>)obj;
			Iterator<HashMap<String, String>> itr=verifyformdetails.iterator();
			
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
				String CREDITRATING=hm.get("CREDITRATING");
				String LoanAmountGranted=hm.get("LoanAmountGranted");
				String tenureApproved2=hm.get("tenureApproved2");
				String KYCCHECK=hm.get("KYCCHECK");
				
											
			%>
<center>
	<h1>LOAN APPLICATION FORM</h1>
</center>


		<table border="3" border color="black" align="center">
		
		
		<tr align="center">
				<td><b>Customer Id:</b></td>
				<td><input type="text" name="custid" 
					value="<%=custid1%>" readonly="readonly" size="40"></td>
			</tr>
			
			
		<tr align="center">
				<td><b>Application Number:</b></td>
				<td><input type="text" name="appno"
					value="<%=APPLICATIONNUMBER%>" readonly="readonly" maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>First Name:</b></td>
				<td><input type="text" name="firstname" placeholder="firstname"
					value="<%=FIRSTNAME%>" readonly="readonly" maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Middle Name:</b></td>
				<td><input type="text" name="middlename"
					placeholder="middlename" value="<%=MIDDLENAME%>" readonly="readonly" maxlenth="40" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Last Name:</b></td>
				<td><input type="text" name="lastname"
				 value="<%=LASTNAME%>" readonly="readonly"	size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Gender</b></td>
				<td>
				<input type="text" value="<%=GENDER%>" readonly="readonly">
				</td>
			</tr>



			<tr align="center">
				<td><b>Date of Birth</b></td>
				<td><input type="date" value="<%=dob%>" readonly="readonly" name="dob"></td>
			</tr>

			<tr align="center">
				<td><b>PAN No</b></td>
				<td><input type="text" name="panno" placeholder="pan no" readonly="readonly"
					value="<%=PANNO%>" maxlenth="15" size="40"></td>
			</tr>



			<tr align="center">
				<td><b>Fathers Name/Spouse Name/Guardian</b></td>
				<td><input type="text" name="fathername" readonly="readonly"
					placeholder="Fathers Name/Spouse Name/Guardian" value="<%=FATHERNAME%>"
					maxlenth="100" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Employment Type</b></td>
				<td><input type="text" name="employment" readonly="readonly"
					placeholder="Employment Type" value="<%=EMPLOYMENTTYPE%>" maxlenth="15" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Name of Employer</b></td>
				<td><input type="text" name="NameofEmployer" readonly="readonly"
					placeholder="Name of Employer" value="<%=NAMEOFEMPLOYER%>" maxlenth="120" size="40"></td>
			</tr>
			
			<tr align="center">
				<td><b>Experience</b></td>
				<td><input type="number" name="experience" readonly="readonly"
					placeholder="experience" value="<%=EXPERIENCE%>" maxlenth="120" size="40"></td>
		
			<tr align="center">
				<td><b>Designation</b></td>
				<td><input type="text" name="designation" readonly="readonly"
					placeholder="designation" value="<%=DESIGNATION%>" maxlenth="60" size="40"></td>
			</tr>
			
				</tr>
			
				<tr align="center">
				<td><b>Annual Income</b></td>
				<td><input type="number" name="annualincome" readonly="readonly"
					placeholder="annualincome" value="<%=ANNUALINCOME%>" maxlenth="12" size="40"></td>
			</tr>

			<tr align="center">
				<td colspan="2"><b>Address Details</b></td>
			</tr>

			<tr align="center">
				<td><b>Correspondence Address</b></td>
				<td><input type="text" name="correspondence" readonly="readonly"
					placeholder="Correspondence Address" value="<%=CORRESPONDENCEADDRESS%>" maxlenth="500"
					size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Work Address</b></td>
				<td><input type="text" name="workaddress" readonly="readonly"
					placeholder="Work Address" value="<%=WORKPLACEADDRESS%>" maxlenth="500" size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Permanent Address</b></td>
				<td><input type="text" name="permanent" readonly="readonly"
					placeholder="Permanent Address" value="<%=PERMANENTADDRESS%>" maxlenth="500" size="40"></td>
			</tr>
			
			
			<tr align="center">
				<td colspan="2"><b>Operational Parameters</b></td>
			</tr>
			
				<tr align="center">
				<td><b>Credit Rating</b></td>
				<td><input type="text" name="CREDITRATING" value=<%=CREDITRATING%> readonly="readonly"
					placeholder="Credit Rating" maxlenth="20"
					size="40"></td>
			</tr>

			<tr align="center">
				<td><b>Loan Amount Requested</b></td>
				<td><input type="number" name="loanamountrequested" readonly="readonly"
					placeholder="Loan Amount Requested" value="<%=LOANAMOUNTREQUESTED%>" maxlenth="500"
					size="40"></td>
						
				</tr>
				
				<tr align="center">
				<td><b>KYC Check</b></td>
				<td><input type="text" name="KYCCHECK"  readonly="readonly"
				placeholder="Kyc Check" maxlenth="20" value="<%=KYCCHECK%>" size="40"></td>
					
				</tr>
				
				
				
				<tr align="center">
				<td><b>Loan Amount Granted</b></td>
				<td><input type="number" name="LOANAMOUNTGRANTED" value=<%=LoanAmountGranted%> readonly="readonly"
				placeholder="LOANAMOUNTGRANTED" maxlenth="20" size="40"></td>
					
				</tr>
			
			<tr align="center">
				<td><b>Tenure Requested</b></td>
				<td><input type="number" name="tenurerequested" readonly="readonly"
					placeholder="Tenure Requested" value="<%=TENUREREQUESTED%>" maxlenth="20" size="40"></td>
		</tr>
		
		
		<tr align="center">
				<td><b>Tenure Approved</b></td>
				<td><input type="number" name="tenureapproved" value=<%=tenureApproved2%> readonly="readonly"
					placeholder="Tenure Approved"  maxlenth="20" size="500"></td>
		</tr>
				
					
		<%-- 	<tr align="center">
				<td><b>Application Status</b></td>
				<td><input type="text" name="APPLICATIONSTATUS"  value="<%=APPLICATIONSTATUS%>"
					 maxlenth="20" size="40"></td>
		</tr> --%>
			
			<tr align="center">
					<td colspan="2"><h3><b>Document Information</h3></b></td>
					</tr>
					
				<tr align="center">
				<td><b>Application Form</b></td>
				
				<td><a href="DocumentDownloadCOntroller?submit=retreiveDocument&applicationnumber=<%=APPLICATIONNUMBER%>&form=addApplication">
				<b>Application Form</b></a>
				</td>
				</tr>
				
				<tr align="center">
				<td><b>Id Documents</b></td>
				<td><a href="DocumentDownloadCOntroller?submit=retreiveDocument&applicationnumber=<%=APPLICATIONNUMBER%>&form=ID Document">
				<b>Id Documents</b></a>
				</td>
				</tr>
				
						
				<tr align="center">
				<td><b>Support Documents</b></td>
				<td><a href="DocumentDownloadCOntroller?submit=retreiveDocument&applicationnumber=<%=APPLICATIONNUMBER%>&form=Support Document">
				<b>Support Documents</b></a>
				</td>
				</tr>
				
			
					
			
				
			 <!--  <tr align="center">
				<td colspan="3">
				<input type="submit" name="submit" value="approve">
				<input type="submit" name="submit" value="reject">
				<input type="submit" name="submit" value="rework"></td>
             </tr> -->
</form>
	<%
}


%>





</body>
</html>