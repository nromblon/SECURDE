package com.servlets;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
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
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String location = request.getParameter("location");
		String type = request.getParameter("type");
		
		ResultSet pubId = null;
		
	    try {	      
	    	//TODO: fix for security
	        String query = "INSERT INTO author (AuthorFirstName, AuthorLastName) VALUES ('', '" +author+ "')";
	        PreparedStatement insertAuthor = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        insertAuthor.executeUpdate();
	        ResultSet authorId = insertAuthor.getGeneratedKeys();
	        authorId.next();
	        
	        query = "INSERT INTO publisher (Publisher) VALUES ('"+publisher+"')";
	        PreparedStatement insertPublisher = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        insertPublisher.executeUpdate();
	        ResultSet publisherId = insertPublisher.getGeneratedKeys();
	        publisherId.next();
	        		
	        System.out.println(authorId);
	        query = "INSERT INTO publication (Publication, AuthorId, PublisherId, PublicationTypeId, Location, Year) VALUES ('"+title+"', " +authorId.getInt(1)+ ", " +publisherId.getInt(1)+ ", " +type+ ", '"+location+"', " +year+ ")";
	        PreparedStatement insertPub = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        insertPub.executeUpdate();
	        pubId = insertPub.getGeneratedKeys();
	        pubId.next();

	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    try {
			response.sendRedirect("publication/details?id="+pubId.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
