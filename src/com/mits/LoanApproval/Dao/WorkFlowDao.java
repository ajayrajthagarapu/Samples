package com.mits.LoanApproval.Dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;
import com.mits.LoanApproval.Beans.ApplicationBean;
import com.mits.LoanApproval.Beans.DocumentBean;
import com.mits.LoanApproval.Beans.OperationalParametersBean;
import com.mits.LoanApproval.DbUtil.DbUtil;

public class WorkFlowDao {
	Connection con=null;
	ResultSet rs=null;
	ResultSet rs1=null;
	String custID=null;
	PreparedStatement pst8;
	
	static final Logger logger = Logger.getLogger(WorkFlowDao.class); 
	
	
	
	public void insertOperation(OperationalParametersBean operationsParameterBean)//inserting into 4 tables using this method
	{
		ApplicationBean applicationBean = operationsParameterBean.getApplicationBean();//getting the application bean
			try 
			{
		    
			//getting the connection from DbUtil
			con = DbUtil.getConnection();
			con.setAutoCommit(false);
			
			//insert into LOAN639_TBLBASICCUSTOMERINFO table
			String insertForm="insert into LOAN639_TBLBASICCUSTOMERINFO values(customerID_sequence639.NEXTVAL,?,?,?,?,?,?,?)";
			PreparedStatement pst1 = con.prepareStatement(insertForm);
			
			pst1.setString(1, applicationBean.getFirstName());
			pst1.setString(2, applicationBean.getMiddleName());
			pst1.setString(3, applicationBean.getLastName());
			pst1.setString(4, applicationBean.getGender());
			pst1.setDate(5, applicationBean.getDateOfBirth());
			pst1.setString(6, applicationBean.getPanNo());
			pst1.setString(7, applicationBean.getFatherName());
			pst1.executeUpdate();
		    
			//getting the cutomerId using PANNO
			String getCustId="select CUSTOMERID from LOAN639_TBLBASICCUSTOMERINFO where PANNO=?";
			PreparedStatement pst2 = con.prepareStatement(getCustId);
			pst2.setString(1, applicationBean.getPanNo());
			ResultSet rs = pst2.executeQuery();
			
			
			if(rs.next())
			{
			   custID = rs.getString("CUSTOMERID");
			   logger.debug("inside while loop of custumer id"+custID);
			   
			}
			logger.debug("outside while loop"+custID);
			
			//insert into LOAN639_TBLEMPLOYEMENTINFO table
			String EmpInfoQuery="insert into LOAN639_TBLEMPLOYEMENTINFO values(?,?,?,?,?,?)";
			PreparedStatement pst3 = con.prepareStatement(EmpInfoQuery);
			pst3.setString(1, custID);
			pst3.setString(2,applicationBean.getEmployementType());
			pst3.setString(3, applicationBean.getNameOfEmployer());
			pst3.setInt(4,applicationBean.getExperience());
			pst3.setString(5, applicationBean.getDesignation());
			pst3.setInt(6, applicationBean.getAnnualIncome());
			pst3.executeUpdate();
			
			//inserting into LOAN639_TBLADDRESSINFO table
			String AddressInfo="insert into LOAN639_TBLADDRESSINFO values(?,?,?,?)";
			PreparedStatement pst4 = con.prepareStatement(AddressInfo);
			pst4.setString(1, custID);
			pst4.setString(2, applicationBean.getCorrespondenceAddress());
			pst4.setString(3,applicationBean.getWorkplaceAddress());
			pst4.setString(4, applicationBean.getPermanentAddress());
			pst4.executeUpdate();
			
			String status = "NEW";
			//inserting into LOAN639_TBLOPERATIONALPARAM table
			String opssql="insert into LOAN639_TBLOPERATIONALPARAM(APPLICATIONNUMBER, CUSTOMERID, LOANAMOUNTREQUESTED, TENUREREQUESTED, APPLICATIONSTATUS,CREATEDBY,CREATEDDATE) values(applicationNumber_sequence639.NEXTVAL,?,?,?,?,?,SYSDATE)";
			PreparedStatement pst5 = con.prepareStatement(opssql);
			pst5.setString(1, custID);
			pst5.setInt(2, operationsParameterBean.getLoanAmountRequested());
			pst5.setInt(3, operationsParameterBean.getTenureRequested());
			pst5.setString(4, status);
			pst5.setString(5, operationsParameterBean.getCreatedBy());
			pst5.executeUpdate();	
			
			//getting APPLICATIONNUMBER using customerId
			String getApplicationNumber="select APPLICATIONNUMBER from LOAN639_TBLOPERATIONALPARAM where CUSTOMERID="+custID;
			PreparedStatement pst6= con.prepareStatement(getApplicationNumber);
			ResultSet rs3 = pst6.executeQuery();
			String applicationnumber = null;
			if(rs3.next())
			{
				applicationnumber = rs3.getString("APPLICATIONNUMBER");
			}
			
			
			
			ArrayList<DocumentBean> documentBean = operationsParameterBean.getDocumentBean();
			
			for(DocumentBean obj:documentBean)
			{
				String insertDocs= "insert into LOAN639_TBLDOCUMENTTYPE values (?,?,?,?,?,?)";
				PreparedStatement pst7 = con.prepareStatement(insertDocs);
				pst7.setString(1, applicationnumber);
				pst7.setString(2, obj.getDocumentType());
				pst7.setString(3, obj.getMimeType());
				pst7.setString(4, obj.getFileName());
				pst7.setLong(5, obj.getFileSize());
				pst7.setBlob(6, obj.getDocumentInputStreamObj());
				pst7.executeQuery();
				System.out.println("in ps7");
			}
			
		    		
			con.commit();		
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				
				try {
					con.rollback();
				    }
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
			
			finally 
			{
				try {
					if (rs != null) {
						rs.close();
					}
						
					if (rs1 != null) {
						rs.close();
					}
								
				    con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
	}
	
		public ArrayList<HashMap<String ,String>> retreiveNewandVerifyStatusRecords()
				{
				//getting new and verify status Records
				ResultSet rs4=null;
				ArrayList<HashMap<String , String>> arraylist=new ArrayList<HashMap<String,String>>();
				Connection con = DbUtil.getConnection();
				String getVerifydetails="select bc.PANNO,bc.CUSTOMERID,bc.FIRSTNAME,bc.LASTNAME,op.APPLICATIONSTATUS,op.APPLICATIONNUMBER  FROM\r\n" + 
						"LOAN639_TBLBASICCUSTOMERINFO bc,LOAN639_TBLOPERATIONALPARAM op where bc.CUSTOMERID=op.CUSTOMERID and (op.APPLICATIONSTATUS='NEW' or op.APPLICATIONSTATUS='verify')";
	
	
				ApplicationBean appbean=new ApplicationBean();
				OperationalParametersBean opsbean = new OperationalParametersBean();
	
				try {
					pst8 = con.prepareStatement(getVerifydetails);
				
					rs4= pst8.executeQuery();
					while(rs4.next())
					{
				
				String appno = rs4.getString("APPLICATIONNUMBER");
				String firstname = rs4.getString("FIRSTNAME");
				String lastname = rs4.getString("LASTNAME");
				String panno = rs4.getString("PANNO");
				String custId = rs4.getString("CUSTOMERID");
				String appstatus = rs4.getString("APPLICATIONSTATUS");
				
				appbean.setFirstName(firstname);
				appbean.setLastName(lastname);
				appbean.setCustomerId(custId);
				appbean.setPanNo(panno);
				opsbean.setApplicationStatus(appstatus);
				opsbean.setApplicationNumber(appno);
				
				HashMap<String, String> hm=new HashMap<String, String>();
				hm.put("APPLICATIONNUMBER", opsbean.getApplicationNumber());
				hm.put("APPLICATIONSTATUS", opsbean.getApplicationStatus());
				hm.put("FIRSTNAME",appbean.getFirstName());
				hm.put("LASTNAME", appbean.getLastName());
				hm.put("CUSTOMERID", appbean.getCustomerId());
				hm.put("PANNO", appbean.getPanNo());
				
				 arraylist.add(hm);
				 
			
					}
						
						
						
					} 
				catch (SQLException e) 
				{
		
					e.printStackTrace();
				}
	
				finally
				{
					try 
						{
				if(pst8!=null)
				{
					pst8.close();
				}
				if(con!=null)
				{
					con.close();
				}
				if(rs4!=null)
				{
					rs4.close();
				}
			}
			
			
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return arraylist;
		}
		
		public ArrayList<HashMap<String ,String>> retreiveCompleteRecordsUsingAppno(String appno)
		{
			
			//retreiving complete records by using application Number
			
			ResultSet rs=null;
			
			ArrayList<HashMap<String , String>> arraylist=new ArrayList<HashMap<String,String>>();
			try {
			
			
			Connection con = DbUtil.getConnection();
			

			ApplicationBean appbean=new ApplicationBean();
			OperationalParametersBean opsbean = new OperationalParametersBean();
			
			
			String retreiveVerifyDetailsQuery="select bc.CUSTOMERID,bc.FIRSTNAME,bc.MIDDLENAME,bc.LASTNAME,bc.GENDER,bc.DATEOFBIRTH,bc.PANNO,bc.FATHERNAME,ad.CORRESPONDENCEADDRESS,\r\n" + 
					"ad.PERMANENTADDRESS,ad.WORKPLACEADDRESS,emp.EMPLOYMENTTYPE,emp.NAMEOFEMPLOYER,emp.EXPERIENCE,emp.DESIGNATION,emp.ANNUALINCOME,\r\n" + 
					"op.APPLICATIONNUMBER,op.CREDITRATING,op.LOANAMOUNTREQUESTED,op.KYCCHECK,op.LOANAMOUNTGRANTED,op.TENUREREQUESTED,op.TENUREAPPROVED,\r\n" + 
					"op.APPLICATIONSTATUS from LOAN639_TBLBASICCUSTOMERINFO bc,LOAN639_TBLADDRESSINFO ad,LOAN639_TBLEMPLOYEMENTINFO emp,LOAN639_TBLOPERATIONALPARAM op \r\n" + 
					"where ad.CUSTOMERID=bc.CUSTOMERID and bc.CUSTOMERID=emp.CUSTOMERID and bc.CUSTOMERID=op.CUSTOMERID and op.APPLICATIONNUMBER=?";
			
			PreparedStatement pst = con.prepareStatement(retreiveVerifyDetailsQuery);
			pst.setString(1, appno);
			rs = pst.executeQuery();
			while(rs.next())
			{
				String customerId = rs.getString("CUSTOMERID");
				String FIRSTNAME = rs.getString("FIRSTNAME");
				String MIDDLENAME = rs.getString("MIDDLENAME");
				String LASTNAME = rs.getString("LASTNAME");
			    String GENDER = rs.getString("GENDER");	
			    System.out.println("IN retreiveVerifyStatusRecords "+GENDER);
			    Date DATEOFBIRTH = rs.getDate("DATEOFBIRTH");
			    String PANNO = rs.getString("PANNO");
			    String FATHERNAME = rs.getString("FATHERNAME");
			    
			    String EMPLOYMENTTYPE = rs.getString("EMPLOYMENTTYPE");
			    String NAMEOFEMPLOYER = rs.getString("NAMEOFEMPLOYER");
			    int EXPERIENCE = rs.getInt("EXPERIENCE");
			    String DESIGNATION = rs.getString("DESIGNATION");
			    int ANNUALINCOME = rs.getInt("ANNUALINCOME");
			    
			    String CORRESPONDENCEADDRESS = rs.getString("CORRESPONDENCEADDRESS");
			    String PERMANENTADDRESS = rs.getString("PERMANENTADDRESS");
			    String WORKPLACEADDRESS = rs.getString("WORKPLACEADDRESS");
			    
			    String APPLICATIONNUMBER = rs.getString("APPLICATIONNUMBER");
			  //  String CREDITRATING = rs.getString("CREDITRATING");
			    int LOANAMOUNTREQUESTED = rs.getInt("LOANAMOUNTREQUESTED");
			    //String KYCCHECK = rs.getString("KYCCHECK");
			   // String LOANAMOUNTGRANTED = rs.getString("LOANAMOUNTGRANTED");
			  //  int LOANAMOUNTGRANTED1 = Integer.parseInt(LOANAMOUNTGRANTED);
			    int TENUREREQUESTED = rs.getInt("TENUREREQUESTED");
			  //  int TENUREAPPROVED = rs.getInt("TENUREAPPROVED");
			    String APPLICATIONSTATUS = rs.getString("APPLICATIONSTATUS");
			    
			    appbean.setCustomerId(customerId);
			    appbean.setFirstName(FIRSTNAME);
			    appbean.setMiddleName(MIDDLENAME);
			    appbean.setLastName(LASTNAME);
			    appbean.setGender(GENDER);
			    appbean.setDateOfBirth(DATEOFBIRTH);
			    appbean.setPanNo(PANNO);
			    appbean.setFatherName(FATHERNAME);
			    appbean.setEmployementType(EMPLOYMENTTYPE);
			    appbean.setNameOfEmployer(NAMEOFEMPLOYER);
			    appbean.setExperience(EXPERIENCE);
			    appbean.setDesignation(DESIGNATION);
			    appbean.setAnnualIncome(ANNUALINCOME);
			    appbean.setCorrespondenceAddress(CORRESPONDENCEADDRESS);
			    appbean.setPermanentAddress(PERMANENTADDRESS);
			    appbean.setWorkplaceAddress(WORKPLACEADDRESS);
			  
			    HashMap<String, String> hm=new HashMap<String, String>();
				hm.put("customerId", appbean.getCustomerId());
				hm.put("FIRSTNAME", appbean.getFirstName());
				hm.put("MIDDLENAME", appbean.getMiddleName());
				hm.put("LASTNAME", appbean.getLastName());
				hm.put("GENDER",appbean.getGender());
				Date dob1 = appbean.getDateOfBirth();
				String dob2=""+dob1;
				hm.put("dob",dob2);
				hm.put("PANNO",appbean.getPanNo());
				hm.put("FATHERNAME", appbean.getFatherName());
				hm.put("EMPLOYMENTTYPE", appbean.getEmployementType());
				hm.put("NAMEOFEMPLOYER", appbean.getNameOfEmployer());
				int experience2 = appbean.getExperience();
				String exp=experience2+"";
				hm.put("EXPERIENCE",exp);
				hm.put("DESIGNATION", appbean.getDesignation());
				int annual = appbean.getAnnualIncome();
				String annual1=""+annual;
				hm.put("ANNUALINCOME", annual1);
				hm.put("CORRESPONDENCEADDRESS",appbean.getCorrespondenceAddress());
				hm.put("PERMANENTADDRESS", appbean.getPermanentAddress());
				hm.put("WORKPLACEADDRESS", appbean.getWorkplaceAddress());
				
				
				 	opsbean.setApplicationNumber(APPLICATIONNUMBER);
				    //opsbean.setCreditRating(CREDITRATING);
				    opsbean.setLoanAmountRequested(LOANAMOUNTREQUESTED);
				 //   opsbean.setKycCheck(KYCCHECK);
				  //  opsbean.setLoanAmountGranted(LOANAMOUNTGRANTED1);
				    opsbean.setTenureRequested(TENUREREQUESTED);
				//    opsbean.setTenureApproved(TENUREAPPROVED);
				    opsbean.setApplicationStatus(APPLICATIONSTATUS);
				    /*opsbean.setLoanAmountGranted(LOANAMOUNTGRANTED1);*/
				    
				    hm.put("APPLICATIONNUMBER", opsbean.getApplicationNumber());
				 //   hm.put("CREDITRATING", opsbean.getCreditRating());
				    int loanAmountRequested2 = opsbean.getLoanAmountRequested();
				    String la=""+loanAmountRequested2;
				    hm.put("LOANAMOUNTREQUESTED",la);
				 //   hm.put("KYCCHECK", opsbean.getKycCheck());
				  //  int loanAmountGranted2 = opsbean.getLoanAmountGranted();
				   // String la2=""+loanAmountGranted2;
				   // hm.put("LoanAmountGranted", la2);
				    int tenureRequested2 = opsbean.getTenureRequested();
				    String st=""+tenureRequested2;
				    hm.put("TENUREREQUESTED",st );
				    hm.put("APPLICATIONSTATUS", opsbean.getApplicationStatus());
				  	    
				
				arraylist.add(hm);
			   
			}
			
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			
			
			
			return arraylist;
		}
		
		
		public void updateStatus(OperationalParametersBean opsbean)
		
		{
			//updating the status and filling the remaining details
			//opsbean as some fields filled by approver(Fields are set in Controller and in this mehtod we are getting values)
			
			Connection con=null;
			PreparedStatement pst=null;
			
			
		try
		{
			con= DbUtil.getConnection();
			String sql11="UPDATE LOAN639_TBLOPERATIONALPARAM SET CREDITRATING=?, KYCCHECK=?, LOANAMOUNTGRANTED=?, TENUREAPPROVED=?, APPLICATIONSTATUS=?,MODIFIEDBY=?,MODIFIEDDATE=SYSDATE WHERE APPLICATIONNUMBER=?";
			System.out.println("IN updateStatus method");
			pst = con.prepareStatement(sql11);
			pst.setString(1, opsbean.getCreditRating());
			System.out.println("CREDITRATING"+opsbean.getCreditRating());
			pst.setString(2, opsbean.getKycCheck());
			System.out.println("KYCCHECK"+opsbean.getKycCheck());
			pst.setInt(3, opsbean.getLoanAmountGranted());
			System.out.println("LOANAMOUNTGRANTED"+opsbean.getLoanAmountGranted());
			pst.setInt(4, opsbean.getTenureApproved());
			System.out.println("TENUREAPPROVED"+opsbean.getTenureApproved());
			pst.setString(5, opsbean.getApplicationStatus());
			System.out.println("APPLICATIONSTATUS"+opsbean.getApplicationStatus());
			pst.setString(6, opsbean.getModifiedBy());
			pst.setString(7, opsbean.getApplicationNumber());
			System.out.println("APPLICATIONNUMBER"+opsbean.getApplicationNumber());
			pst.executeUpdate();
			
			
		} 
		catch (SQLException e) 
			{
				e.printStackTrace();			
		   }
							
	   finally 
		  {    
		       try
		       {
			 	if(con!=null)
			 	{
			 		con.close();
			 	}
		       }
		       catch(SQLException e)
		       {
		    	   e.printStackTrace();
		       }
		       try
		       {
			 	if(pst!=null)
			 	{
			 		pst.close();
			 	}
		       }
		       catch(SQLException e)
		       {
		    	   e.printStackTrace();
		       }
		  }
				
	
	}
			
		
				public DocumentBean getDocuments(String APPLICATIONNUMBER,String form)
				{
					
					//Getting the documents by application number and Form
					PreparedStatement pst=null;
					Connection con=null;
					DocumentBean docbean=new DocumentBean();
					
				try 
				{
					con = DbUtil.getConnection();
					String sql="SELECT dyl.DOCUMENTTYPE,dyl.MIMETYPE,dyl.FILENAME,dyl.FILESIZE,dyl.DOCUMENTOBJECT " + 
								"FROM LOAN639_TBLDOCUMENTTYPE dyl, LOAN639_TBLOPERATIONALPARAM opl " + 
								"where opl.APPLICATIONNUMBER=dyl.APPLICATIONNUMBER " + 
								"and opl.APPLICATIONNUMBER=? and dyl.DOCUMENTTYPE=?";
					pst = con.prepareStatement(sql);
					
					
					pst.setString(1,APPLICATIONNUMBER);
					pst.setString(2,form);
					
					
					ResultSet rs=pst.executeQuery();
						while(rs.next())
					{
						String DOCUMENTTYPE = rs.getString("DOCUMENTTYPE");
						String MIMETYPE = rs.getString("MIMETYPE");
						String FILENAME = rs.getString("FILENAME");
						Long FILESIZE = rs.getLong("FILESIZE");
						Blob blob = rs.getBlob("DOCUMENTOBJECT");
						InputStream inputStream = blob.getBinaryStream();
						
						docbean.setDocumentType(DOCUMENTTYPE);
						docbean.setMimeType(MIMETYPE);
						docbean.setFileName(FILENAME);
						docbean.setFileSize(FILESIZE);
						docbean.setDocumentInputStreamObj(inputStream);
							
					}
					
		
				}
		
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				finally 
				{
					
					 try
				       {
					 	if(con!=null)
					 	{
					 		con.close();
					 	}
				       }
				       catch(SQLException e)
				       {
				    	   e.printStackTrace();
				       }
				       try
				       {
					 	if(pst!=null)
					 	{
					 		pst.close();
					 	}
				       }
				       catch(SQLException e)
				       {
				    	   e.printStackTrace();
				       }
				       try
				       {
					 	if(rs!=null)
					 	{
					 		rs.close();
					 	}
				       }
				       catch(SQLException e)
				       {
				    	   e.printStackTrace();
				       }
				}
					
					
					
				return docbean;
				}
				
			public ArrayList<HashMap<String,String>> retreiveReworkStatusRecords()
			{
				
				//Retreiving Rework Status Records
				Connection con=null;
				PreparedStatement pst =null;
				ArrayList<HashMap<String,String>> arraylist=new ArrayList<HashMap<String,String>>();
				
				try 
				{
					
				con = DbUtil.getConnection();
				String retreiveReworkStatusRecords="select bc.PANNO,bc.CUSTOMERID,bc.FIRSTNAME,bc.LASTNAME,op.APPLICATIONSTATUS,op.APPLICATIONNUMBER  FROM \r\n" + 
						"					LOAN639_TBLBASICCUSTOMERINFO bc,LOAN639_TBLOPERATIONALPARAM op where bc.CUSTOMERID=op.CUSTOMERID and APPLICATIONSTATUS='rework'";
				pst = con.prepareStatement(retreiveReworkStatusRecords);
				ResultSet rs = pst.executeQuery();
				ApplicationBean appBean=new ApplicationBean();
				OperationalParametersBean opsBean=new OperationalParametersBean();
				
				while(rs.next())
				{
					String panNo = rs.getString("PANNO");
					String customerId = rs.getString("CUSTOMERID");
					String firstName = rs.getString("FIRSTNAME");
					String lastName = rs.getString("LASTNAME");
					String appStatus = rs.getString("APPLICATIONSTATUS");
					String appNo = rs.getString("APPLICATIONNUMBER");
										
					appBean.setPanNo(panNo);
					appBean.setCustomerId(customerId);
					appBean.setFirstName(firstName);
					appBean.setLastName(lastName);
					
					opsBean.setApplicationStatus(appStatus);
					opsBean.setApplicationNumber(appNo);
					
					HashMap<String,String> hm=new HashMap<String,String>();
					hm.put("panNo", appBean.getPanNo());
					hm.put("customerId",appBean.getCustomerId());
					hm.put("firstName",appBean.getFirstName());
					hm.put("lastName", appBean.getLastName());
					hm.put("appStatus",opsBean.getApplicationStatus());
					hm.put("appNo", opsBean.getApplicationNumber());
					
					
					arraylist.add(hm);
															
				}
					
				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					 try
				       {
					 	if(con!=null)
					 	{
					 		con.close();
					 	}
				       }
				       catch(SQLException e)
				       {
				    	   e.printStackTrace();
				       }
				       try
				       {
					 	if(pst!=null)
					 	{
					 		pst.close();
					 	}
				       }
				       catch(SQLException e)
				       {
				    	   e.printStackTrace();
				       }
				       try
				       {
					 	if(rs!=null)
					 	{
					 		rs.close();
					 	}
				       }
				       catch(SQLException e)
				       {
				    	   e.printStackTrace();
				       }
				}
				
				
				
				
				return arraylist;
			}
				
			public void updateStatusReworkToVerify(OperationalParametersBean operationsParameterBean)
			{
				//DE operator updates the Rework Status Records to Verify Status
				Connection con=null;
				ResultSet rs=null;
				String custID=null;
				
				ApplicationBean applicationBean = operationsParameterBean.getApplicationBean();
				try 
				{
				con = DbUtil.getConnection();
				con.setAutoCommit(false);
				String updateBasicCustInfo="UPDATE LOAN639_TBLBASICCUSTOMERINFO SET FIRSTNAME=?,MIDDLENAME=?,LASTNAME=?,GENDER=?,DATEOFBIRTH=?,PANNO=?,FATHERNAME=? WHERE CUSTOMERID=?";
				PreparedStatement pst1 = con.prepareStatement(updateBasicCustInfo);
				
				pst1.setString(1, applicationBean.getFirstName());
				System.out.println(applicationBean.getFirstName());
				pst1.setString(2, applicationBean.getMiddleName());
				pst1.setString(3, applicationBean.getLastName());
				pst1.setString(4, applicationBean.getGender());
				System.out.println("after verify updating by getting the gender "+applicationBean.getGender());
				pst1.setDate(5, applicationBean.getDateOfBirth());
				pst1.setString(6, applicationBean.getPanNo());
				pst1.setString(7, applicationBean.getFatherName());
				pst1.setString(8, applicationBean.getCustomerId());
				System.out.println(applicationBean.getCustomerId());
				
				pst1.executeUpdate();
			
				String getCustId="select CUSTOMERID from LOAN639_TBLBASICCUSTOMERINFO where PANNO=?";
				PreparedStatement pst2 = con.prepareStatement(getCustId);
				pst2.setString(1, applicationBean.getPanNo());
				rs = pst2.executeQuery();
				
				
				if(rs.next())
				{
				   custID = rs.getString("CUSTOMERID");
				   logger.debug("inside while loop of custumer id"+custID);
				   
				}
				logger.debug("outside while loop"+custID);
				String updateEmpInfo="UPDATE LOAN639_TBLEMPLOYEMENTINFO SET EMPLOYMENTTYPE=?,NAMEOFEMPLOYER=?,EXPERIENCE=?,DESIGNATION=?,ANNUALINCOME=? WHERE CUSTOMERID=? ";
				PreparedStatement pst3 = con.prepareStatement(updateEmpInfo);
			
				pst3.setString(1,applicationBean.getEmployementType());
				pst3.setString(2, applicationBean.getNameOfEmployer());
				pst3.setInt(3,applicationBean.getExperience());
				pst3.setString(4, applicationBean.getDesignation());
				pst3.setInt(5, applicationBean.getAnnualIncome());
				pst3.setString(6, custID);
				pst3.executeUpdate();
				
				String updateAddInfo="UPDATE LOAN639_TBLADDRESSINFO SET CORRESPONDENCEADDRESS=?,WORKPLACEADDRESS=?,PERMANENTADDRESS=? WHERE CUSTOMERID=?";
				PreparedStatement pst4 = con.prepareStatement(updateAddInfo);
				
				pst4.setString(1, applicationBean.getCorrespondenceAddress());
				pst4.setString(2,applicationBean.getWorkplaceAddress());
				pst4.setString(3, applicationBean.getPermanentAddress());
				pst4.setString(4, custID);
				pst4.executeUpdate();
				
				String status = "verify";
				String updateOpsInfo="UPDATE LOAN639_TBLOPERATIONALPARAM SET LOANAMOUNTREQUESTED=?,TENUREREQUESTED=?,APPLICATIONSTATUS=?,MODIFIEDBY=?,MODIFIEDDATE=SYSDATE, WHERE CUSTOMERID=?";
				PreparedStatement pst5 = con.prepareStatement(updateOpsInfo);
				
				pst5.setInt(1, operationsParameterBean.getLoanAmountRequested());
				pst5.setInt(2, operationsParameterBean.getTenureRequested());
				pst5.setString(3, status);
				pst5.setString(4, operationsParameterBean.getModifiedBy());
				pst5.setString(5, custID);
				pst5.executeUpdate();	
				System.out.println("status in update method is "+status);
				
				String getApplicationNumber="select APPLICATIONNUMBER from LOAN639_TBLOPERATIONALPARAM where CUSTOMERID="+custID;
				PreparedStatement pst6= con.prepareStatement(getApplicationNumber);
				ResultSet rs3 = pst6.executeQuery();
				String applicationnumber = null;
				if(rs3.next())
				{
					applicationnumber = rs3.getString("APPLICATIONNUMBER");
				}
				System.out.println("appNo in update method is "+applicationnumber);
				
			
				
			ArrayList<DocumentBean> documentBean = operationsParameterBean.getDocumentBean();
				
			
			
			if(documentBean!=null && !documentBean.isEmpty())
			{
				for(DocumentBean obj:documentBean)
				{
					if(obj.getFileSize()>0 && obj.getDocumentInputStreamObj()!=null)
					{
					String insertDocs= "UPDATE LOAN639_TBLDOCUMENTTYPE SET DOCUMENTTYPE=?,MIMETYPE=?,FILENAME=?,FILESIZE=?,DOCUMENTOBJECT=? where APPLICATIONNUMBER=? and DOCUMENTTYPE=?";
					PreparedStatement pst7 = con.prepareStatement(insertDocs);
					System.out.println("********************");
					pst7.setString(1, obj.getDocumentType());
					pst7.setString(2, obj.getMimeType());
					pst7.setString(3, obj.getFileName());
					pst7.setLong(4, obj.getFileSize());
					pst7.setBlob(5, obj.getDocumentInputStreamObj());
					pst7.setString(6, applicationnumber);
					pst7.setString(7, obj.getDocumentType());
					pst7.executeQuery();
					System.out.println("from update all controller");
					}
				}
			}
				
			    		
				con.commit();		
				}
				catch(Exception e)
				{	
					try {
					con.rollback();
						} 
					catch (SQLException e1)
						{
					e1.printStackTrace();
						}
				}
				finally
				{
					try { 
						if(con!=null)
						{
					 con.close();
						}
						}
					 catch(SQLException e)
					    {
						e.printStackTrace();
					    }
				}
					
				
			}
			
			
				
	}














































