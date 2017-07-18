package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;
import com.objects.Review;
/**
 * Servlet implementation class PublicationDetailsServlet
 */
@WebServlet("/publication/details")
public class PublicationDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicationDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = DBConnector.getConnection();
		String id = request.getParameter("id");
		
		try {
	    	PreparedStatement getPub = con.prepareStatement("SELECT * From publication p INNER JOIN author a ON p.AuthorId = a.AuthorId INNER JOIN publisher pub ON p.PublisherId = pub.PublisherId INNER JOIN publicationtype pubt ON p.PublicationTypeId = pubt.PublicationTypeId INNER JOIN status s ON p.StatusId = s.StatusId WHERE p.PublicationId = "+id);
	    	ResultSet rs = getPub.executeQuery();
	    	
	    	rs.next();
	    	
	    	PreparedStatement getPubReviews = con.prepareStatement("SELECT * FROM reviews r INNER JOIN publicationreviews pr ON pr.Reviews_ReviewId = r.ReviewId INNER JOIN publication p ON pr.Publication_PublicationId = p.PublicationId INNER JOIN user u ON r.UserId = u.UserId");
	    	ResultSet reviews = getPubReviews.executeQuery();
	    	ArrayList<Review> reviewObjs = new ArrayList<Review>();
	    	
	    	
	    	while(reviews.next()) {
	    		reviewObjs.add(new Review(reviews.getString("Username"), reviews.getString("Review")));
	    	}
	    	
	    	request.setAttribute("title", rs.getString("Publication"));
	    	request.setAttribute("type", rs.getString("PublicationType"));
	    	request.setAttribute("author", rs.getString("AuthorLastName") + ", " + rs.getString("AuthorFirstName"));
	    	request.setAttribute("publisher", rs.getString("Publisher"));
	    	request.setAttribute("year", rs.getString("Year"));
	    	request.setAttribute("location", rs.getString("Location"));
	    	request.setAttribute("reviews", reviewObjs);
	    	
	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
		request.getRequestDispatcher("/publicationdetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
