package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.UserModel;
import com.objects.User;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgot/step1")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/forgotpassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = UserModel.getUserWithUsername(username);

		if(user != null) {
			request.getSession().setAttribute("forgot-userId", user.getId());
			response.sendRedirect("../forgot/step2?userId="+user.getId());
		} else {
			request.setAttribute("error", "Invalid username");		
			doGet(request, response);
		}
	}

}
