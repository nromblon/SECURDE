package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.models.UserModel;
import com.utils.Validator;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/change")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/changepassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String oldPassword = request.getParameter("old-password");
		String password = request.getParameter("password");
		String confirmPass = request.getParameter("confirm-password");
		Validator v = Validator.getInstance();
		boolean flag = true;
		int userId = (int) session.getAttribute("userId");
		
		ArrayList<String> errors = new ArrayList<String>();
		
		if(oldPassword.equals(password)) {
			errors.add("New password cannot be equal to the old one!");
			request.setAttribute("errors", errors);
			doGet(request, response);
		} else if(UserModel.checkPasswordMatch(userId, oldPassword)) {
			if(!(v.isValidPassword(password))) {
				errors.add("Invalid Password! Password must contain: a digit, a lower case letter, an upper case letter, a special character and no white spaces. Must be at least 8 characters");
				flag = false;
			}
			if(!password.equals(confirmPass)) {
				errors.add("Confirm Password not equal to New Password");
				flag = false;
			}
			
			if(flag) {
				UserModel.changePassword(userId, password);
				response.sendRedirect("search");
			} else {
				request.setAttribute("errors", errors);
				doGet(request, response);
			}
		} else {
			errors.add("Old password is incorrect");
			request.setAttribute("errors", errors);
			doGet(request, response);
		}	
	}

}
