package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;

/**
 * Servlet implementation class AdminCreateServlet
 */
@WebServlet("/admin/create")
public class AdminCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("admincreate.jsp").forward(request, response);
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
        String type = request.getParameter("privilege");
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
	        stmt.setString(5, "password");
	        stmt.setString(6, email);
	        stmt.setString(7, idnumber);
	        stmt.setString(8, birthday);
	        stmt.setString(9, secretQuestion);
	        stmt.setString(10, answer);
	        stmt.setString(11, type);
	        
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
