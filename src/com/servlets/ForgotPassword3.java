package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.LogKey;
import com.constants.Privilege;
import com.db.DBConnector;
import com.models.UserModel;
import com.objects.User;
import com.utils.Logger;
import com.utils.Validator;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgot/step3")
public class ForgotPassword3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private User user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPassword3() {
        super();
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
			System.out.println("init user");
			user = UserModel.getUserWithId(Integer.valueOf(request.getParameter("userId")));
			request.getRequestDispatcher("/forgotpassword3.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("../forgot/step1").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		Validator v = Validator.getInstance();
		boolean flag = true;
		
		Connection conn = null;
		conn = DBConnector.getConnection();
		
		if(!(v.isValidPassword(password))) {
			request.setAttribute("error", "Invalid Password! Password must contain: a digit, a lower case letter, an upper case letter, a special character and no white spaces. Must be at least 8 characters");
			flag = false;
		}
		
		if(flag) {
			if(UserModel.changePassword(user.getId(), password)){
				Logger.info(this.getServletName(), LogKey.CHANGE_PASS, "User changed password"
						, "From:" + request.getRemoteAddr(), "UserId:"+user.getId());
			
			}
			
			try {
				PreparedStatement loginUser = conn.prepareStatement("SELECT * FROM user WHERE UserId = ?");
	        	loginUser.setInt(1, user.getId());
	        	ResultSet rsP = loginUser.executeQuery();
	        	
	        	if(rsP.next()) {
					session.setAttribute("userId", rsP.getInt("UserId"));
					session.setAttribute("lastName", rsP.getString("LastName"));
					session.setAttribute("middleName", rsP.getString("MiddleInitial"));
					session.setAttribute("firstName", rsP.getString("FirstName"));
					session.setAttribute("username", rsP.getString("Username"));
					session.setAttribute("privilege", rsP.getString("Privilege_PrivilegeId"));
					session.setAttribute("usertypeid", rsP.getInt("UserTypeId"));
					
					Cookie userId = new Cookie("user",rsP.getString("UserId"));
					//setting cookie to expiry in 30 mins
					userId.setMaxAge(30*60);
					response.addCookie(userId);
	        	}
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
//			Log.info(this.getServletName(), rsP.getInt("UserId"), LogKey.LOGIN_SUCCESS, "Username: "+username);
        
        	int privilege = -1;
        	try{
        		privilege = Integer.parseInt( (String) session.getAttribute("privilege"));
        	}catch(NumberFormatException e){
        		//TODO: appropriate error message for invalid number syntax
        		e.printStackTrace();
        	}
			System.out.println(privilege);
			if(privilege == (Privilege.USER)) {
//				System.out.println("user login");
//				String encodedURL = response.encodeRedirectURL("search.jsp");
				response.sendRedirect("../search");
			} else if (privilege == (Privilege.LIB_MANAGER) || privilege == (Privilege.LIB_STAFF)) {
				System.out.println("../libmanager");
//				String encodedURL = response.encodeRedirectURL("addpublication.jsp");
//				response.sendRedirect(encodedURL);
				response.sendRedirect("../publication/add");
			} else if (privilege == (Privilege.ADMIN)){
				System.out.println("admin login");
//				String encodedURL = response.encodeRedirectURL("admintools.jsp");
//				response.sendRedirect(encodedURL);
				response.sendRedirect("../admin/tools");
			} 
		} else {
			request.getRequestDispatcher("/forgotpassword3.jsp").forward(request, response);
		}
	}

}
