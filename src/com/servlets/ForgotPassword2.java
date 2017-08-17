package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.models.UserModel;
import com.objects.User;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgot/step2")
public class ForgotPassword2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private User user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int sessionUserId = 0;
		int userId = 0;
		
		try {
			sessionUserId = (int) session.getAttribute("forgot-userId");
			userId = Integer.valueOf(request.getParameter("userId"));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if(sessionUserId == userId) {
			user = UserModel.getUserWithId(Integer.valueOf(request.getParameter("userId")));
			request.setAttribute("securityQuestion", UserModel.getSecurityQuestion(user.getId()));
			request.getRequestDispatcher("/forgotpassword2.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("../forgot/step1").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String answer = request.getParameter("security-question");
		
		if(UserModel.checkAnswer(user.getId(), answer)) {
			request.getSession().setAttribute("forgot-userId", user.getId());
			response.sendRedirect("../forgot/step3?userId="+user.getId());
		} else {
			request.setAttribute("error", "Invalid answer");
			request.setAttribute("securityQuestion", UserModel.getSecurityQuestion(user.getId()));
			request.getRequestDispatcher("/forgotpassword2.jsp").forward(request, response);
//			response.sendRedirect("../forgot/step2?userId="+user.getId());
		}
		
	}

}
