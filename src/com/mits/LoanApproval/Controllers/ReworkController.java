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


@WebServlet("/ReworkController")
public class ReworkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ReworkController() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Created workFlowDao Object and retreiving ArrayList(retreiveReworkStatusRecords)
		WorkFlowDao wfd=new WorkFlowDao();
		ArrayList<HashMap<String,String>> retreiveReworkStatusRecords = wfd.retreiveReworkStatusRecords();
		request.setAttribute("ReworkRecords", retreiveReworkStatusRecords);
		request.getRequestDispatcher("ReworkStatus.jsp").forward(request, response);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
