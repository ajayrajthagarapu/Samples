package com.mits.LoanApproval.Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.mits.LoanApproval.Beans.OperationalParametersBean;
import com.mits.LoanApproval.Dao.WorkFlowDao;


@WebServlet("/UpdateStatusController")
public class UpdateStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(UpdateStatusController.class);    
  
    public UpdateStatusController() 
    {
        super();
        
    }
//Approver after Filling the Remaining Detials and can change status to approve,reject or Rework 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   
		            HttpSession session = request.getSession(false);
		            String name = (String) session.getAttribute("name");
		            //Getting the details from approver end 
					System.out.println("in UpdateStatusController");
		            String appNo = request.getParameter("appno");
		            System.out.println(" IN UpdateStatusController appno "+appNo);
					String buttonValue = request.getParameter("submit");
				    System.out.println(" IN UpdateStatusController buttonValue "+buttonValue);
					String CREDITRATING = request.getParameter("CREDITRATING");
					System.out.println(" IN UpdateStatusController CREDITRATING "+CREDITRATING);
					String KYCCHECK = request.getParameter("KYCCHECK");
					System.out.println(" IN UpdateStatusController KYCCHECK "+KYCCHECK);
					String loang = request.getParameter("LOANAMOUNTGRANTED");
					int LOANAMOUNTGRANTED1 = Integer.parseInt(loang);
					System.out.println(" IN UpdateStatusController KYCCHECK "+LOANAMOUNTGRANTED1);
					String tenureapproved = request.getParameter("tenureapproved");
				    int tenureapproved1 = Integer.parseInt(tenureapproved);
				    System.out.println("loan amount in update method" +LOANAMOUNTGRANTED1);
					
				    //setting the details to OperationalParametersBean
					OperationalParametersBean opsbean=new OperationalParametersBean();
					opsbean.setCreditRating(CREDITRATING);
					opsbean.setKycCheck(KYCCHECK);
					opsbean.setLoanAmountGranted(LOANAMOUNTGRANTED1);
					opsbean.setTenureApproved(tenureapproved1);
					opsbean.setApplicationNumber(appNo);
					opsbean.setModifiedBy(name);
					
					
					//changing the status using buttonValue
					if(buttonValue.equals("rework"))
					{
						
						opsbean.setApplicationStatus("rework");
					}
					else if(buttonValue.equals("reject"))
					{
						opsbean.setApplicationStatus("reject");
					}
					else if(buttonValue.equals("approve"))
					{
						opsbean.setApplicationStatus("approve");
					}
					
					WorkFlowDao wfd=new WorkFlowDao();
					wfd.updateStatus(opsbean);
					
					
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
