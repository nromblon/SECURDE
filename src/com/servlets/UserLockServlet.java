package com.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.LogKey;
import com.models.UserModel;
import com.utils.Logger;

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
        super();
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
		
		if(UserModel.setLockedAccount(userId, isLocked)){
			Logger.info(this.getServletName(), LogKey.ACC_UNLOCK, "Account lock status toggled", "From:" + request.getRemoteAddr()
			,"of UserId:"+userId,"to State:"+isLocked);
		}
	}

}
