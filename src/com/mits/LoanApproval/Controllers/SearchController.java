package com.mits.LoanApproval.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mits.LoanApproval.Beans.SearchBean;
import com.mits.LoanApproval.Dao.SearchDao;


@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SearchController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//retreives the appno,custid or applicationstatus from search form
		//setting the values to SearchBean and Performing search Operation in SearchDao
		
		String appno = request.getParameter("appno");
		String custid = request.getParameter("custid");
		String applicationStatus = request.getParameter("applicationstatus");
		System.out.println("Application Status: "+applicationStatus);
		
		SearchBean sb=new SearchBean();
		sb.setApplicationNumber(appno);
		sb.setCustomerId(custid);
		sb.setApplicationStatus(applicationStatus);
		
		SearchDao searchDao=new SearchDao();
		ArrayList<HashMap<String, String>> search = searchDao.search(sb);
		request.setAttribute("searchList",search);
		request.getRequestDispatcher("SearchList.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
