package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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
        String password = request.getParameter("password");
        String cpassword= request.getParameter("cpassword");
        String email= request.getParameter("email");
        int idnumber= Integer.parseInt(request.getParameter("idnumber"));
        Date birthday= Date.valueOf(request.getParameter("calendar"));
        String secretQuestion= request.getParameter("secretQuestion");
        String answer= request.getParameter("answer");
        int type = Integer.parseInt(request.getParameter("privilege"));
        Connection conn = null;
        
        try{
        
	        //loading drivers for mysql
	        Class.forName("com.mysql.jdbc.Driver");
			conn = DBConnector.getConnection();
	
			PreparedStatement stmt = 
	        		conn.prepareStatement("INSERT INTO user (FirstName,LastName,MiddleInitial,Username,PasswordHash,Email,IdentificationNumber,SecurityQuestionId,AnswerHash,Privilege_PrivilegeId) VALUES (?,?,?,?,?,?,?,?,?,?)");
	
	        stmt.setString(1, firstname);
	        stmt.setString(2, lastname);
	        stmt.setString(3, midinitial);
	        stmt.setString(4, username);
	        stmt.setString(5, "password");
	        stmt.setString(6, email);
	        stmt.setInt(7, idnumber);
	        stmt.setInt(8, 1);
	        stmt.setString(9, answer);
	        stmt.setInt(10, type);
	        
	        
	        int i = stmt.executeUpdate();
	        
			if(i>0)
			{
				System.out.println("success");
				out.println("You are sucessfully registered");
			}
	        
		}
        catch(Exception se)
        {
            se.printStackTrace();
        }
	}

}
