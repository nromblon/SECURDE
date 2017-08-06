package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;

import sun.security.pkcs11.Secmod.DbMode;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/deletebook")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/addpublication.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = DBConnector.getConnection();
		String id = request.getParameter("id");
		
	    try {	   
	    	PreparedStatement deletePubTags = con.prepareStatement("DELETE FROM publicationtags WHERE PublicationId = "+id);
	    	deletePubTags.executeUpdate();
	        
	        PreparedStatement deletePub = con.prepareStatement("DELETE FROM publication WHERE PublicationId = "+id);
	        deletePub.executeUpdate();

	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    
//	    response.sendRedirect("search");
	}

}
