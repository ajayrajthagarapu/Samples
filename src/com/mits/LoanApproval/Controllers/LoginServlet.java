package com.mits.LoanApproval.Controllers;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.mits.LoanApproval.Beans.UserMasterVo;
import com.mits.LoanApproval.Beans.UserRoleVo;
import com.mits.LoanApproval.Dao.LoginDao;


public class LoginServlet extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(LoginServlet.class); 
	
	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		//getting Userid and password from login1 form
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		logger.debug(userid);
        
		//setting the values to UserMasterVo
		UserMasterVo bean = new UserMasterVo();
		bean.setUserid(userid);
		bean.setPassword(password);
		LoginDao dao = new LoginDao();
		
		boolean userValidate = dao.validate(bean);//calling validate method from dao object by passing bean obj

		if (userValidate) {//check whether the dataentry role or approver role

			UserMasterVo masterVo = dao.getUserRolename(userid);
			ArrayList<UserRoleVo> arrayList = masterVo.getRolelist();
			
			
			if (arrayList.size() > 0) {
				UserRoleVo userrolevo = (UserRoleVo) arrayList.get(0);
				String rolename = userrolevo.getRolename();
				if ("dataentry".equalsIgnoreCase(rolename)) {
                  //if dateentry role forward to Data_Entry_HomePage
					HttpSession session = request.getSession();
					session.setAttribute("name", userid);
					request.getRequestDispatcher("Data_Entry_HomePage.jsp").forward(request, response);

				} else if ("approver".equalsIgnoreCase(rolename)) {
                   //if approver role forward to Approver_HomePage
					HttpSession session = request.getSession();
					session.setAttribute("name", userid);
					request.getRequestDispatcher("Approver_HomePage.jsp").forward(request, response);
				}

			}

		} else {//if not a valid user show the below message and include Login1 page
			
			out.println("INVALID LOGIN CREDETINTIALS");
			request.getRequestDispatcher("Login1.jsp").include(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
