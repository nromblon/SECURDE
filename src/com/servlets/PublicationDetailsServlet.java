package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.PublicationModel;
import com.models.ReviewModel;
import com.models.UserModel;
import com.objects.Publication;
import com.objects.Review;
/**
 * Servlet implementation class PublicationDetailsServlet
 */
//TODO: vulnerable to indirect object references?
@WebServlet("/publication/details")
public class PublicationDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicationDetailsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pubId = Integer.valueOf(request.getParameter("id"));
//		int userId = (request.getSession().getAttribute("userId") == null)? 0: Integer.valueOf((String) request.getSession().getAttribute("userId"));
		boolean alreadyReserved = false;
		
		Publication pub = PublicationModel.getPubWithId(pubId);
		ArrayList<Review> reviews = ReviewModel.getReviewsByPublication(pubId);
		
		if(request.getSession().getAttribute("username") != null) {
			int userId = (int) request.getSession().getAttribute("userId");
			alreadyReserved = UserModel.checkExistingReservation(userId, pubId);
			request.setAttribute("alreadyReserved", alreadyReserved);
		}
		
		request.setAttribute("details", pub);
		request.setAttribute("reviews", reviews);
		request.getRequestDispatcher("/publicationdetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
