package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;
import com.utils.Validator;

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
		
		Connection conn = DBConnector.getConnection();
		Statement query;
		
		try {
			query = conn.createStatement();
		
			String line1 = "SELECT * FROM securityquestion";
			ResultSet res1 = query.executeQuery(line1);
			
			String options1="";			
			while(res1.next()){
				int key = res1.getInt("SecurityQuestionId");
				String question = res1.getString("SecurityQuestion");
				options1+="<option value=\""+key+"\">"+question+"</option>\n";
			}
			request.setAttribute("secQuestionOptions", options1);
			request.getRequestDispatcher("/admincreate.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
        String email= request.getParameter("email");
        int idnumber= Integer.parseInt(request.getParameter("idnumber"));
        Date birthday= Date.valueOf(request.getParameter("calendar"));
        //to remove 
        int secretQuestion= Integer.parseInt(request.getParameter("secretQuestion"));
        String answer= request.getParameter("answer");
        
        int type = -1;
        if(request.getParameter("privilege").equals("libman"))
        	type = 2;
        else if(request.getParameter("privilege").equals("libstaff"))
        	type = 3;
        	
        Connection conn = null;
        
        if(type == -1)
        	return;
        
        Boolean flag = true;
        
        Validator validator = Validator.getInstance();
		
		if(!(validator.isAlphaNumericHasSpace(firstname, 45))) {
			request.setAttribute("error", "Invalid First Name!");
			flag = false;
		}
		if(!(validator.isAlphaNumericHasSpace(lastname,45))) {
			request.setAttribute("error", "Invalid Last Name!");
			flag = false;
		}
		if(!(validator.isAlphaNumeric(midinitial,2))) {
			request.setAttribute("error", "Invalid Middle Initial!");
			flag = false;
		}
		if(!(validator.isAlphaNumeric(username,45))) {
			request.setAttribute("error", "Invalid Username!");
			flag = false;
		}						
		if(!(validator.validateEmail(email))) {
			request.setAttribute("error", "Invalid Email!");
			flag = false;
		}
		if(!(validator.isAlphaNumericHasSpace(answer,45))) {
			request.setAttribute("error", "Invalid Answer for secret question!");
			flag = false;
		}
        
		if(flag){
			try{
				conn = DBConnector.getConnection();
				String line = "INSERT INTO user (FirstName,LastName,MiddleInitial,Username,PasswordHash,Email,Birthday,IdentificationNumber,SecurityQuestionId,AnswerHash,Privilege_PrivilegeId,isTemporary)"
						+ " VALUES (?,?,?,?,PASSWORD(?),?,?,?,?,PASSWORD(?),?,?)";
				PreparedStatement stmt = conn.prepareStatement(line);
		
		        stmt.setString(1, firstname);
		        stmt.setString(2, lastname);
		        stmt.setString(3, midinitial);
		        stmt.setString(4, username);
		        stmt.setString(5, "password");
		        stmt.setString(6, email);
		        stmt.setDate(7, birthday);
		        stmt.setInt(8, idnumber);
		        stmt.setInt(9, secretQuestion);
		        stmt.setString(10, answer);
		        stmt.setInt(11, type);
		        stmt.setBoolean(12, true);
		        
		        
		        int i = stmt.executeUpdate();
		        
				if(i>0)
				{
					response.sendRedirect("tools");
				}
		        
			}
	        catch(Exception se)
	        {
	            se.printStackTrace();
	        }
		}
		else
			request.getRequestDispatcher("/admincreate.jsp").forward(request, response);  
        	
	}

}
