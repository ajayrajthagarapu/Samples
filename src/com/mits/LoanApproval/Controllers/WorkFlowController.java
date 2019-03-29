package com.mits.LoanApproval.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
//import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import com.mits.LoanApproval.Beans.ApplicationBean;
import com.mits.LoanApproval.Beans.DocumentBean;
import com.mits.LoanApproval.Beans.OperationalParametersBean;
import com.mits.LoanApproval.Dao.WorkFlowDao;



public class WorkFlowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(WorkFlowController.class);
       
  
    public WorkFlowController() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
	    
	    //retreiving all the add form details using request object			
		String firstName = request.getParameter("firstname");
		String middleName = request.getParameter("middlename");
		String lastName = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		Date date = Date.valueOf(dob);
		String panno = request.getParameter("panno");
		String fatherName = request.getParameter("fathername");
		String employmentType = request.getParameter("employment");
		String NameOfEmployer = request.getParameter("NameofEmployer");
		String experience = request.getParameter("experience");
		String designation = request.getParameter("designation");
		String correspondenceAddress = request.getParameter("correspondence");
		String workAddress = request.getParameter("workaddress");
		String permanent = request.getParameter("permanent");
		String loanAmountRequested = request.getParameter("loanamountrequested");
		String tenureRequested = request.getParameter("tenurerequested");
        String annualIncome = request.getParameter("annualincome");
	   
        //parsing from String to Int
		int experience1 = Integer.parseInt(experience);
		int annual = Integer.parseInt(annualIncome);
		
		//Setting the form retreived data to corresponding ApplicationBean
		ApplicationBean applicationBean=new ApplicationBean();
		applicationBean.setFirstName(firstName);
		applicationBean.setMiddleName(middleName);
		applicationBean.setLastName(lastName);
		applicationBean.setGender(gender);
		applicationBean.setDateOfBirth(date);
		applicationBean.setPanNo(panno);
		applicationBean.setFatherName(fatherName);
		applicationBean.setEmployementType(employmentType);
		applicationBean.setNameOfEmployer(NameOfEmployer);
		applicationBean.setExperience(experience1);
		applicationBean.setDesignation(designation);
		applicationBean.setCorrespondenceAddress(correspondenceAddress);
		applicationBean.setWorkplaceAddress(workAddress);
		applicationBean.setPermanentAddress(permanent);
		applicationBean.setAnnualIncome(annual);
	

		int loanamountrequested1 = Integer.parseInt(loanAmountRequested);
		int tenureRequested1 = Integer.parseInt(tenureRequested);
		
		//Setting the form retreived data to corresponding OperationalParametersBean
		OperationalParametersBean opsbean=new OperationalParametersBean();
		opsbean.setLoanAmountRequested(loanamountrequested1);
		opsbean.setTenureRequested(tenureRequested1);
		opsbean.setApplicationBean(applicationBean);
		opsbean.setCreatedBy((String) session.getAttribute("name"));
		
	  ArrayList<DocumentBean> docbean=new ArrayList<DocumentBean>();//creating arraylist of docbean
      
	  
	 //getting the images by using getPartMethod
      Part part1 = request.getPart("applicationform");
      
      if (part1!=null) 
      {
    	  String documenttype="addApplication";
          String appContentType = part1.getContentType();
          InputStream appInputStream = part1.getInputStream();
          String appName = part1.getName();
          long appSize = part1.getSize();
          
          DocumentBean documentBean = new DocumentBean();
          documentBean.setDocumentType(documenttype);
          documentBean.setMimeType(appContentType);
          documentBean.setFileName(appName);
          documentBean.setFileSize(appSize);
          documentBean.setDocumentInputStreamObj(appInputStream);
          docbean.add(documentBean);
          System.out.println("1st file "+appName);
      }
      
      Part part2 = request.getPart("iddocument");      
      if(part2!=null)
      {
    
      String documenttype1="ID Document";
      String idContentType = part2.getContentType();
      InputStream idInputStream = part2.getInputStream();
      String idName = part2.getName();
      long idSize = part2.getSize();
      
      DocumentBean documentBean2 = new DocumentBean();
      documentBean2.setDocumentType(documenttype1);
      documentBean2.setMimeType(idContentType);
      documentBean2.setFileName(idName);
      documentBean2.setFileSize(idSize);
      documentBean2.setDocumentInputStreamObj(idInputStream);
      docbean.add(documentBean2);
      System.out.println("2nd st file "+idName);
      
      
      }
      Part part3 = request.getPart("supportdocument");
      if(part3!=null)
      {
      String documenttype2="Support Document";
      String supportContentType = part3.getContentType();
      InputStream supportInputStream = part3.getInputStream();
      String supportName = part3.getName();
      long supportSize = part3.getSize();
      System.out.println("third file "+supportName);
      DocumentBean documentBean3=new DocumentBean();
      documentBean3.setDocumentType(documenttype2);
      documentBean3.setMimeType(supportContentType);
      documentBean3.setFileName(supportName);
      documentBean3.setFileSize(supportSize);
      documentBean3.setDocumentInputStreamObj(supportInputStream);
      docbean.add(documentBean3);
      
      }
	  
      opsbean.setDocumentBean(docbean);
      WorkFlowDao wfdao=new WorkFlowDao();
      //passing the opsbean as parameters to insertOperation method
      //opsbean contains applicationbean and document ArrayList
	  wfdao.insertOperation(opsbean);
	  out.println("Record Inserted Successfully");
   
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}


/*Enumeration parameterNames = request.getParameterNames();
		
while(parameterNames.hasMoreElements())
{
	Object objOri=parameterNames.nextElement();
	String param=(String)objOri;
	String value=request.getParameter(param);
	out.println("Parameter Name is '"+param+"' and Parameter Value is '"+value+"'");

}
*/
