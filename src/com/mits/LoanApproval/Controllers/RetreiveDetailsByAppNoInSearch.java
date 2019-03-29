package com.mits.LoanApproval.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mits.LoanApproval.Dao.SearchDao;



@WebServlet("/RetreiveDetailsByAppNoInSearch")
public class RetreiveDetailsByAppNoInSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RetreiveDetailsByAppNoInSearch() {
        super();
        
    }

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Getting the application NUmber and passing it as parameter to retreiveStatusRecordsInSearch method
	    //It forwards to DisplayForm Jsp
		System.out.println("*******************");
		String appNo = request.getParameter("appNo");
		SearchDao sd=new SearchDao();
		ArrayList<HashMap<String, String>> retreiveStatusRecordsInSearch = sd.retreiveStatusRecordsInSearch(appNo);
        request.setAttribute("SendToFinalSearch", retreiveStatusRecordsInSearch);
        request.getRequestDispatcher("DisplayForm.jsp").forward(request, response);

		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
