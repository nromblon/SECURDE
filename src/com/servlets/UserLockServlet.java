package com.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.UserModel;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/userlock")
public class UserLockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLockServlet() {
        super();//TODO: fix error pages to be less descriptive
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/addpublication.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.valueOf(request.getParameter("userId"));
		int locked = Integer.valueOf(request.getParameter("isLocked"));
		boolean isLocked = (locked == 1)? true: false;
		
		UserModel.setLockedAccount(userId, isLocked);
	}

}
