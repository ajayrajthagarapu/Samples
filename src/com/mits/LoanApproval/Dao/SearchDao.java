package com.mits.LoanApproval.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mits.LoanApproval.Beans.ApplicationBean;
import com.mits.LoanApproval.Beans.OperationalParametersBean;
import com.mits.LoanApproval.Beans.SearchBean;
import com.mits.LoanApproval.DbUtil.DbUtil;

public class SearchDao {

	
public ArrayList<HashMap<String,String>> search(SearchBean SearchBean)
{
	        //Getting  APPLICATIONNUMBER,CUSTOMERID,APPLICATIONSTATUS using application number or customerId or applicationStatus
	        //Getting the values to SearchBean class
	
	        Connection con=null;
	        PreparedStatement pst=null;
	        ResultSet rs =null;
	        ArrayList<HashMap<String,String>> ar=new ArrayList<HashMap<String,String>>();
	
				try
				{
				con= DbUtil.getConnection();
				
				String sql="SELECT APPLICATIONNUMBER,CUSTOMERID,APPLICATIONSTATUS FROM LOAN639_TBLOPERATIONALPARAM WHERE 1=1";
				StringBuilder sb=new StringBuilder();
				
				
				String applicationNumber = SearchBean.getApplicationNumber();
				System.out.println("Application Number: "+applicationNumber);
				String customerId = SearchBean.getCustomerId();
				System.out.println("Customer ID: "+customerId);
				String applicationStatus = SearchBean.getApplicationStatus();
				System.out.println("Application Status: "+applicationStatus);
				
				
				if(applicationNumber.length()>0 && applicationNumber!=null)
				{
					sb.append(" AND APPLICATIONNUMBER='"+applicationNumber+"'");
				}
				if(customerId.length()>0 && customerId!=null)
				{
					sb.append(" AND CUSTOMERID='"+customerId+"'");
				}
				if(applicationStatus.length()>0 && applicationStatus.equals("ALL"))
				{
					
				}
				else if(applicationStatus!=null && applicationStatus.trim().length()>0)
				{
					sb.append(" AND APPLICATIONSTATUS='"+applicationStatus+"'");
				}
				String searchQuery=sql+sb;
				pst = con.prepareStatement(searchQuery);
				rs = pst.executeQuery();
				
				
				while(rs.next())
				{
					String appNo = rs.getString("APPLICATIONNUMBER");
					String CustId = rs.getString("CUSTOMERID");
					String appStatus = rs.getString("APPLICATIONSTATUS");
					
					HashMap<String , String> hm=new HashMap<String , String>();
					hm.put("applicationNumber", appNo);
					hm.put("customerId",CustId);
					hm.put("applicationStatus",appStatus);
					
					ar.add(hm);
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
					try{
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
		
		return ar;		
}

					public ArrayList<HashMap<String ,String>> retreiveStatusRecordsInSearch(String appno)
					{
						
						//Retreiving all the records using appno and returning the arrayList
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
						   	int LOANAMOUNTREQUESTED = rs.getInt("LOANAMOUNTREQUESTED");
						    
						   
						   
						   String KYCCHECK = rs.getString("KYCCHECK");
						    String CREDITRATING = rs.getString("CREDITRATING");
						    int TENUREREQUESTED = rs.getInt("TENUREREQUESTED");
						    int TENUREAPPROVED = rs.getInt("TENUREAPPROVED");
						    System.out.println("tenure approved in search "+TENUREAPPROVED);
						    int LOANAMOUNTGRANTED25 = rs.getInt("LOANAMOUNTGRANTED");
						    System.out.println("in search method loanamount granted "+LOANAMOUNTGRANTED25);
						    
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
							    opsbean.setCreditRating(CREDITRATING);
							    opsbean.setLoanAmountRequested(LOANAMOUNTREQUESTED);
							    opsbean.setKycCheck(KYCCHECK);
							    opsbean.setLoanAmountGranted(LOANAMOUNTGRANTED25);
							    opsbean.setTenureRequested(TENUREREQUESTED);
							    opsbean.setTenureApproved(TENUREAPPROVED);
							    opsbean.setApplicationStatus(APPLICATIONSTATUS);
							  
							    
							    hm.put("APPLICATIONNUMBER", opsbean.getApplicationNumber());
					            hm.put("CREDITRATING", opsbean.getCreditRating());
							    int loanAmountRequested2 = opsbean.getLoanAmountRequested();
							    String la=""+loanAmountRequested2;
							    hm.put("LOANAMOUNTREQUESTED",la);
					            hm.put("KYCCHECK", opsbean.getKycCheck());
							    int loanAmountGranted2 = opsbean.getLoanAmountGranted();
						        String la2=""+loanAmountGranted2;
							    hm.put("LoanAmountGranted", la2);
							    int tenureRequested2 = opsbean.getTenureRequested();
							    String st=""+tenureRequested2;
							    hm.put("TENUREREQUESTED",st );
							    int tenureApproved2 = opsbean.getTenureApproved();
							    String ta=tenureApproved2+"";
							    hm.put("tenureApproved2", ta);
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
					

	
	
}
