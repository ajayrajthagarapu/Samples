package com.mits.LoanApproval.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mits.LoanApproval.Dao.WorkFlowDao;


@WebServlet("/DisplayFormDetailsController")
public class DisplayFormDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DisplayFormDetailsController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
		         
		
		              //Getting the application Number through UrlRewiting
						String appno = request.getParameter("appno");
						WorkFlowDao wfdao = new WorkFlowDao();
						//Passing the application number as parameter into a method
				        ArrayList<HashMap<String,String>> retreiveCompleteRecordsUsingAppno = wfdao.retreiveCompleteRecordsUsingAppno(appno);
				        request.setAttribute("verifyform", retreiveCompleteRecordsUsingAppno);
				        request.getRequestDispatcher("verifyDetailsByAppNum.jsp").forward(request, response);
				        						
		
		
	
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
