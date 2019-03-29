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

@WebServlet("/DisplayReworkDetailsController")
public class DisplayReworkDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DisplayReworkDetailsController() {
        super();
           }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Getting the application NUmber and passing it as parameter to retreiveCompleteRecordsUsingAppno method
		//It forwards to Rework Jsp
		
		
		String appNo = request.getParameter("appNo");
		WorkFlowDao wfdao = new WorkFlowDao();
        ArrayList<HashMap<String,String>> retreiveCompleteRecordsUsingAppno = wfdao.retreiveCompleteRecordsUsingAppno(appNo);
        request.setAttribute("sendToReworkJsp", retreiveCompleteRecordsUsingAppno);
        request.getRequestDispatcher("Rework.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
