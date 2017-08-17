package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.Privilege;
import com.db.DBConnector;
import com.utils.Validator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			
			String line2 = "SELECT * FROM usertype";
			ResultSet res2 = query.executeQuery(line2);
			
			String options2="";
			while(res2.next()){
				int key = res2.getInt("UserTypeId");
				String type = res2.getString("UserType");
				options2+="<option value=\""+key+"\">"+type+"</option>\n";
			}
			request.setAttribute("userTypeOptions", options2);
			
			
			request.getRequestDispatcher("/registration.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//TODO validate this as text/number
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String midinitial = request.getParameter("midinitial");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpassword= request.getParameter("cpassword"); //TODO: compare with password
        String email= request.getParameter("email");     //TODO: special validation
        Date birthday= Date.valueOf(request.getParameter("calendar"));
        String answer= request.getParameter("answer");
        
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
		if(!(validator.isAlphaNumeric(password,45))) {
			request.setAttribute("error", "Invalid Password!");
			flag = false;
		}								
		if(!(validator.validateEmail(email))) {
			request.setAttribute("error", "Invalid Email!");
			flag = false;
		}													
		if(!(validator.isDate(birthday.toString()))) {
			request.setAttribute("error", "Invalid Date!");
			flag = false;
		}	
		if(!(validator.isAlphaNumericHasSpace(answer,45))) {
			request.setAttribute("error", "Invalid Answer for secret question!");
			flag = false;
		}
        
        if(flag){
        	try{ //TODO validate this as numbers only
    	        int idnumber= Integer.parseInt(request.getParameter("idnumber"));
    	        int secretQuestion= Integer.parseInt(request.getParameter("secretQuestion"));
    	        int userType = Integer.parseInt(request.getParameter("userType"));
    	        
    	        Connection conn = null;
    	        
    	        try{
    				conn = DBConnector.getConnection();
    		
    				String updateQuery = "INSERT INTO user (FirstName,LastName,MiddleInitial,Username,PasswordHash,Email,Birthday,IdentificationNumber,SecurityQuestionId,AnswerHash,Privilege_PrivilegeId,UserTypeId)"
    						+ " VALUES (?,?,?,?,PASSWORD(?),?,?,?,?,PASSWORD(?),?,?)";
    		        PreparedStatement stmt = conn.prepareStatement(updateQuery,Statement.RETURN_GENERATED_KEYS);
    		        stmt.setString(1, firstname);
    		        stmt.setString(2, lastname);
    		        stmt.setString(3, midinitial);
    		        stmt.setString(4, username);
    		        stmt.setString(5, password);
    		        stmt.setString(6, email);
    		       	stmt.setDate(7, birthday);
    		        stmt.setInt(8, idnumber);
    		        stmt.setInt(9, secretQuestion); 
    		        stmt.setString(10, answer);
    		        stmt.setInt(11, 1);
    		        stmt.setInt(12, userType);
    				if(stmt.executeUpdate()>0)
    				{
    					ResultSet key = stmt.getGeneratedKeys();
    					if(key.next()){
    						HttpSession session= request.getSession();
    						session.setAttribute("userId", key.getInt(1));
    						session.setAttribute("lastName", lastname);
    						session.setAttribute("middleName", midinitial);
    						session.setAttribute("firstName", firstname);
    						session.setAttribute("username", username);
    						session.setAttribute("privilege", Integer.toString(Privilege.USER));

    			        	response.sendRedirect("search");
    					}else{
    						throw new SQLException("Creating user failed, no ID obtained.");
    					}
    				}
    		        
    			}
    	        catch(Exception se)
    	        {
    	            se.printStackTrace();
    	        }
            } catch(NumberFormatException e){
            	//TODO: add error message for invalid input
            	e.printStackTrace();
            }
        }
        else
        	request.getRequestDispatcher("/registration.jsp").forward(request, response);  
	
	}

}
