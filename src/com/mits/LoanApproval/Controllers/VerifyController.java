package com.mits.LoanApproval.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mits.LoanApproval.Dao.WorkFlowDao;


@WebServlet("/VerifyController")
public class VerifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(VerifyController.class);    
    
    public VerifyController() 
    {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creating the WorkFlowDao object and retreiving the NEW and Verify Status codes using retreiveNewandVerifyStatusRecords() method 
		//forwarding the ayylist to VerifyStatus Jsp
		
		WorkFlowDao wkdao=new WorkFlowDao();
		ArrayList<HashMap<String, String>> retreiveNewandVerifyStatusRecords = wkdao.retreiveNewandVerifyStatusRecords();
		request.setAttribute("PartialRecords", retreiveNewandVerifyStatusRecords);
		RequestDispatcher rd = request.getRequestDispatcher("VerifyStatus.jsp");
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
