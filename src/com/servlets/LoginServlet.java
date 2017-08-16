package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.Privilege;
import com.db.DBConnector;
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
		//TODO: assign redirects after login for each user type
		if(session.getAttribute("username") != null) {
			if(privilege == Privilege.USER) {
				response.sendRedirect("search");
			} else if (privilege == Privilege.LIB_STAFF || privilege == Privilege.LIB_MANAGER) {
				response.sendRedirect("publication/add");
			} else if (privilege == Privilege.ADMIN){
				response.sendRedirect("admin/tools");
			} else //TODO: change to fitting error message (this only appears when privilege is not parsable to int)
				request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else
			request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean flag;
		
		if(username != "")
			request.setAttribute("username", username);
		if(password != "")
			request.setAttribute("password", password);
		
		//input validation TODO: add escaping and checking for potential malicious inputs
		if((username != "") && (password != ""))
			flag=true;
		else
			flag=false;

		if(flag){
			Connection conn = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DBConnector.getConnection();
				String query = "SELECT * FROM user WHERE Username=? AND PasswordHash=PASSWORD(?)";

		        PreparedStatement stmt = conn.prepareStatement(query);
		        stmt.setString(1, username);
		        stmt.setString(2, password);
		        ResultSet rs = stmt.executeQuery();
		        if(rs.next()){
					HttpSession session= request.getSession();
					session.setAttribute("userId", rs.getInt("UserId"));
					session.setAttribute("lastName", rs.getString("LastName"));
					session.setAttribute("middleName", rs.getString("MiddleInitial"));
					session.setAttribute("firstName", rs.getString("FirstName"));
					session.setAttribute("username", rs.getString("Username"));
					session.setAttribute("privilege", rs.getString("Privilege_PrivilegeId"));
		        
		        	int privilege = -1;
		        	try{
		        		privilege = Integer.parseInt( (String) session.getAttribute("privilege"));
		        	}catch(NumberFormatException e){
		        		//TODO: appropriate error message for invalid number syntax
		        		e.printStackTrace();
		        	}
					System.out.println(privilege);
					if(privilege == (Privilege.USER)) {
						System.out.println("user login");
						response.sendRedirect("search");
					} else if (privilege == (Privilege.LIB_MANAGER) || privilege == (Privilege.LIB_STAFF)) {
						System.out.println("libmanager");
						response.sendRedirect("publication/add");
					} else if (privilege == (Privilege.ADMIN)){
						System.out.println("admin login");
						response.sendRedirect("admin/tools");
					} 
		        }
		        else {
		        	System.out.println("no login");
		        
		        	request.setAttribute("error", "Invalid username or password!");
		        	request.getRequestDispatcher("login.jsp").forward(request, response);
		        }
		        
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else{//TODO: add error label instead because invalid input shit
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		}
		
	}

}
