package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String midinitial = request.getParameter("midinitial");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpassword= request.getParameter("cpassword");
        String email= request.getParameter("email");
        String idnumber= request.getParameter("idnumber");
        String birthday= request.getParameter("calendar");
        String secretQuestion= request.getParameter("secretQuestion");
        String answer= request.getParameter("answer");
        Connection conn = null;
        
        try{
        
	        //loading drivers for mysql
	        Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnector.getConnection();
	
	        PreparedStatement stmt = 
	        		conn.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?,?)");
	
	        stmt.setString(2, firstname);
	        stmt.setString(3, lastname);
	        stmt.setString(4, midinitial);
	        stmt.setString(5, password);
	        stmt.setString(6, email);
	        stmt.setString(7, idnumber);
	        stmt.setString(8, birthday);
	        stmt.setString(9, secretQuestion);
	        stmt.setString(10, answer);
	        stmt.setInt(11, 1);
	        
	        int i = stmt.executeUpdate();
	        
			if(i>0)
			{
				out.println("You are sucessfully registered");
			}
	        
		}
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
	}

}
