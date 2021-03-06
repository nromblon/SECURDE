package com.servlets;

import java.io.IOException;
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
import com.utils.Log;
import com.utils.Logger;
import com.utils.Validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int privilege = -1;
		try{
			if(session.getAttribute("privilege")!=null)
    			privilege = Integer.parseInt( (String) session.getAttribute("privilege"));
    	}catch(NumberFormatException e){
    		//TODO: appropriate error message for invalid number syntax
    		e.printStackTrace();
    	}
		Logger.info(this.getServletName(), LogKey.AUTH_ATTEMPT, "Anonymous user attempted authorization", "From:" + request.getRemoteAddr());
		
		if(session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			
			if(privilege == Privilege.USER) {
				response.sendRedirect("search");
				
			} else if (privilege == Privilege.LIB_STAFF || privilege == Privilege.LIB_MANAGER) {
				response.sendRedirect("publication/add");
			} else if (privilege == Privilege.ADMIN){
				response.sendRedirect("admin/tools");
			} else{ //TODO: change to fitting error message (this only appears when privilege is not parsable to int)
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				Logger.info(this.getServletName(), LogKey.AUTH_SUCCESS, "User Authorization Successful", "From:" + request.getRemoteAddr(), "Username:"+username);
			}
		}else
			request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean flag = true;

		//input validation TODO: add escaping and checking for potential malicious inputs
		Validator validator = Validator.getInstance();
		
		if((username == "") || (password == "") || !(validator.isAlphaNumeric(username, 45))){
			System.out.println("input not valid");
			flag=false;
			request.setAttribute("error", "Invalid username or password!");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		Logger.info(this.getServletName(), LogKey.LOGIN_ATTEMPT, "Login attempt", "From:" + request.getRemoteAddr(), "Username:"+username);
		
		
		if(flag){
			Connection conn = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DBConnector.getConnection();
				String query = "SELECT * FROM user WHERE Username=?";

		        PreparedStatement sameUsername = conn.prepareStatement(query);
		        sameUsername.setString(1, username);
		        ResultSet rsU = sameUsername.executeQuery();
		        if(rsU.next()){
		        	PreparedStatement samePassword = conn.prepareStatement("SELECT * FROM user WHERE Username=? AND PasswordHash=PASSWORD(?)");
		        	samePassword.setString(1, rsU.getString("Username"));
		        	samePassword.setString(2, password);
		        	ResultSet rsP = samePassword.executeQuery();
			        
			        if(rsP.next()) {
			        	if(!rsP.getBoolean("IsLocked") && rsP.getInt("login_attempts") < 5) {
			        		UserModel.setLoginAttempts(rsP.getInt("UserId"), 0);
			        		
			        		HttpSession session= request.getSession();
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
							
//							Log.info(this.getServletName(), rsP.getInt("UserId"), LogKey.LOGIN_SUCCESS, "Username: "+username);
				        
				        	int privilege = -1;
				        	try{
				        		privilege = Integer.parseInt( (String) session.getAttribute("privilege"));
				        	}catch(NumberFormatException e){
				        		//TODO: appropriate error message for invalid number syntax
				        		e.printStackTrace();
				        	}
							System.out.println(privilege);
							if(privilege == (Privilege.USER)) {
//								System.out.println("user login");
//								String encodedURL = response.encodeRedirectURL("search.jsp");
								response.sendRedirect("search");
							} else if (privilege == (Privilege.LIB_MANAGER) || privilege == (Privilege.LIB_STAFF)) {
								System.out.println("libmanager");
//								String encodedURL = response.encodeRedirectURL("addpublication.jsp");
//								response.sendRedirect(encodedURL);
								response.sendRedirect("publication/add");
							} else if (privilege == (Privilege.ADMIN)){
								System.out.println("admin login");
//								String encodedURL = response.encodeRedirectURL("admintools.jsp");
//								response.sendRedirect(encodedURL);
								response.sendRedirect("admin/tools");
							} 
							Logger.info(this.getServletName(), LogKey.LOGIN_SUCCESS, "Successful Login", "From:" + request.getRemoteAddr(), "UserID:"+rsP.getInt("UserId"),"Username:"+username,
									"Privilege:"+privilege);
			        	} else {
			        		System.out.println("user is locked out");
					        
			        		Logger.info(this.getServletName(), LogKey.ACC_LOCKOUTS, "Account Locked", "From:" + request.getRemoteAddr(), "Username:"+username);
			        		
				        	request.setAttribute("error", "Your account is locked out! Please contact your admin.");
				        	request.getRequestDispatcher("login.jsp").forward(request, response);
			        	}
			        } else {
			        	System.out.println("no login");
			        	
			        	if(rsU.getInt("login_attempts") >= 5) {
			        		UserModel.setLockedAccount(rsU.getInt("UserId"), true);
			        	} else {
			        		Logger.info(this.getServletName(), LogKey.LOGIN_FAIL,"User attempted to login", "From:" + request.getRemoteAddr(), "Username:"+username);
			        		UserModel.setLoginAttempts(rsU.getInt("UserId"), rsU.getInt("login_attempts") + 1);
			        	} 
				        
			        	request.setAttribute("error", "Invalid username or password!");
			        	
			        	request.getRequestDispatcher("login.jsp").forward(request, response);
			        }
					
		        }
		        else {
		        	System.out.println("no login");
		        	Logger.info(this.getServletName(), LogKey.LOGIN_FAIL,"No username exists","From:" + request.getRemoteAddr(), "Username:"+username);
		        	request.setAttribute("error", "Invalid username or password!");
		        	request.getRequestDispatcher("login.jsp").forward(request, response);
		        }
		        
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
