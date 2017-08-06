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
/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/editbook")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String bookId;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/publication/details?id="+bookId).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = DBConnector.getConnection();
		
		bookId = request.getParameter("id");
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String location = request.getParameter("location");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		
	    try {	        	
	        PreparedStatement getPub = con.prepareStatement("SELECT * From publication p INNER JOIN author a ON p.AuthorId = a.AuthorId WHERE p.PublicationId = "+bookId);
	        ResultSet rs = getPub.executeQuery();
	        
	        rs.next();
	        
	        PreparedStatement updateAuthor = con.prepareStatement("UPDATE author SET AuthorFirstName = '', AuthorLastName = '" +request.getParameter("author")+ "' WHERE AuthorId = "+rs.getInt("AuthorId"));
	        updateAuthor.executeUpdate();
	        
	        PreparedStatement updatePub = con.prepareStatement("UPDATE publication SET Publication = '" +title+ "', Location = '" +location+ "', Year = "+year+" WHERE PublicationId = "+bookId);
	        updatePub.executeUpdate();

	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    
//	    doGet(request, response);
	    
	}

}
