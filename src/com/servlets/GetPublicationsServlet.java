package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;
import com.objects.Author;
import com.objects.Publication;
import com.objects.Publisher;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/get_pubs")
public class GetPublicationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPublicationsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Statement stmt = null;
		Connection con = DBConnector.getConnection();
		
		String searchTerm = request.getParameter("searchTerm");
		String searchBy = request.getParameter("searchBy");
		String category = request.getParameter("category");
		System.out.println(category);
	    try {
	    	String condition1 = "";
	    	String condition2 = (category.equals("View Entire Collection"))? "": "AND pubt.PublicationType = '" + category+"'";
	    	
	    	switch(searchBy) {
	    	case "Publication": condition1 = "p.Publication LIKE '%"+searchTerm+"%'"; break;
	    	case "Author": condition1 = "a.AuthorFirstName LIKE '%"+searchTerm+"%' OR a.AuthorLastName LIKE '%"+searchTerm+"%'"; break;
	    	case "Publisher": condition1 = "pub.Publisher LIKE '%"+searchTerm+"%'"; break;
	    	}
	    	System.out.println(condition1+" "+condition2);
	    	PreparedStatement getPubs = con.prepareStatement("SELECT * From publication p INNER JOIN author a ON p.AuthorId = a.AuthorId INNER JOIN publisher pub ON p.PublisherId = pub.PublisherId INNER JOIN publicationtype pubt ON p.PublicationTypeId = pubt.PublicationTypeId INNER JOIN status s ON p.StatusId = s.StatusId WHERE "+condition1+" "+condition2);
	    	ResultSet rs = getPubs.executeQuery();
	    	ArrayList<Publication> publications = new ArrayList<Publication>();

	        while(rs.next()) {
	        	publications.add(new Publication(rs.getInt("PublicationId"), rs.getString("Publication"), new Author(rs.getInt("AuthorId"), rs.getString("AuthorFirstName"), rs.getString("AuthorLastName")), 
	        			new Publisher(rs.getInt("PublisherId"), rs.getString("Publisher")), rs.getString("PublicationType"), rs.getString("Status"), rs.getString("Location"), rs.getInt("Year")));
	        }
	        
	        request.setAttribute("searchTerm", searchTerm);
	        request.setAttribute("publications", publications);
	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    
	    request.getRequestDispatcher("/search").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
