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
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/addreview")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewServlet() {
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
		String pubId = request.getParameter("pubId");
		String reviewText = request.getParameter("reviewText");
		
		try {	      
	    	//TODO: fix for security
	        String query = "INSERT INTO reviews (Review, UserId) VALUES ('" +reviewText+ "', " +request.getSession().getAttribute("UserId")+ ")";
	        PreparedStatement insertReview = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        insertReview.executeUpdate();
	        ResultSet reviewId = insertReview.getGeneratedKeys();
	        reviewId.next();
	        
	        query = "INSERT INTO publicationreviews (Publication_PublicationId, Reviews_ReviewId) VALUES ("+pubId+", " +reviewId.getInt(1)+ ")";
	        PreparedStatement insertCon = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	        insertCon.executeUpdate();
	        
	    } catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
		
		response.sendRedirect("publication/details?id="+pubId);
	}

}
