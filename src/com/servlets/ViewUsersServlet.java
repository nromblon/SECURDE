package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.LogKey;
import com.constants.Privilege;
import com.models.UserModel;
import com.utils.Logger;

/**
 * Servlet implementation class ViewUsersServlet
 */
@WebServlet("/admin/users")
public class ViewUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUsersServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int privilege = -1;
		Logger.info(this.getServletName(), LogKey.AUTH_ATTEMPT, "User attempted authorization", "From:" + request.getRemoteAddr());

		try{
			if(session.getAttribute("privilege")!=null)
    			privilege = Integer.parseInt( (String) session.getAttribute("privilege"));
    	}catch(NumberFormatException e){
    		//TODO: appropriate error message for invalid number syntax
    		e.printStackTrace();
    	}
		
		if(session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			if (privilege == Privilege.ADMIN) {
				request.setAttribute("users", UserModel.getUsers());
				request.getRequestDispatcher("/viewusers.jsp").forward(request, response);
				Logger.info(this.getServletName(), LogKey.AUTH_SUCCESS, "Admin Authorization Successful", "From:" + request.getRemoteAddr(), "Username:"+username);
			}
		}else{
			response.sendRedirect("../login"); 
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
