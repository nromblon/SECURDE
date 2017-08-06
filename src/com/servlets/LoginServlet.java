package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
<<<<<<< HEAD
		HttpSession session = request.getSession();
		
		String privilege = (String) session.getAttribute("privilege") ;
		
		if(session.getAttribute("username") != null) {
			if(privilege == "1") {
				response.sendRedirect("/search");
			} else if (privilege == "3") {
				response.sendRedirect("/publication/add");
			} else if (privilege == "4"){
				response.sendRedirect("/admin/tools");
			}
		}
=======
		// TODO Auto-generated method stub
				HttpSession session = request.getSession();
				
				String privilege = (String) session.getAttribute("privilege") ;
				
				if(session.getAttribute("username") != null) {
					if(privilege == "1") {
						response.sendRedirect("/search");
					} else if (privilege == "3") {
						response.sendRedirect("/publication/add");
					} else if (privilege == "4"){
						response.sendRedirect("/admin/tools");
					} else {
						session.invalidate();			
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}	
				} else {
					request.getRequestDispatcher("/login.jsp").forward(request, response);		
				}
>>>>>>> parent of da92305... code cleaning
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				String query = "SELECT * FROM user WHERE Username=? AND PasswordHash=?";
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
//					session.setAttribute("password", rs.getString("PasswordHash"));
					session.setAttribute("username", rs.getString("Username"));
					session.setAttribute("privilege", rs.getString("Privilege_PrivilegeId"));
		        
		        	String privilege = (String) session.getAttribute("privilege");
					System.out.println(privilege);
					if(privilege.equals("1")) {
						System.out.println("user login");
						response.sendRedirect("search");
					} else if (privilege.equals("2")) {
						System.out.println("libmanager");
						response.sendRedirect("publication/add");
					} else if (privilege.equals("4")){
						System.out.println("admin login");
						response.sendRedirect("admin/tools");
					}
		        }
		        else {
		        	request.setAttribute("error", "<script type='text/javascript'> alert('Invalid username or password!'); </script>");
		        }
		        
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else{
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
