package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.Privilege;
import com.constants.UserType;
import com.models.PubTypeModel;
import com.models.PublicationModel;
import com.models.ReviewModel;
import com.models.TagModel;
import com.models.UserModel;
import com.objects.Publication;
import com.objects.Review;
import com.objects.User;
import com.utils.Utils;
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pubId = Integer.valueOf(request.getParameter("id"));
		HttpSession session = request.getSession();
		int userId = (session.getAttribute("userId") == null)? 0: (int)session.getAttribute("userId");

		Publication pub = PublicationModel.getPubWithId(pubId);
		ArrayList<Review> reviews = ReviewModel.getReviewsByPublication(pubId);
		
		if(session.getAttribute("username") != null) {
			int privilege = Integer.valueOf((String)session.getAttribute("privilege"));
			if(privilege == Privilege.USER) {
//				int userId = (int) session.getAttribute("userId");
				boolean reservedByMe = UserModel.checkExistingReservation(userId, pubId);
				request.setAttribute("reservedByMe", reservedByMe);
				
				boolean alreadyReserved = PublicationModel.checkExistingReservation(pubId);
				request.setAttribute("alreadyReserved", alreadyReserved);
				
				if(pub.getStatus().equals("Available")) {
					request.setAttribute("dateLabel", "Anticipated Return Date");
					if((int) session.getAttribute("usertypeid") == UserType.STUDENT) {
						request.setAttribute("date", Utils.getDatePlusWeek());
					} else {
						request.setAttribute("date", Utils.getDatePlusMonth());
					}
				} else if(pub.getStatus().equals("Out")) {
					request.setAttribute("dateLabel", "Date of Availability");
					request.setAttribute("date", Utils.dateToString(pub.getBorrowedUntil()));
				}
			} else if(privilege == Privilege.LIB_MANAGER) {
				User user = PublicationModel.getUserWhoReserved(pubId);
				boolean isBorrowed = PublicationModel.checkIfBorrowed(pubId);
				
				request.setAttribute("userWhoReserved", user);
				request.setAttribute("alreadyBorrowed", isBorrowed);
			}
			
			
		}
		
		request.setAttribute("userHasBorrowed", !UserModel.getBorrowed(userId).isEmpty());
		request.setAttribute("pubTypes", PubTypeModel.getPubTypes());
		request.setAttribute("allTags", TagModel.getAllTags());
		request.setAttribute("pubTags", TagModel.getTagsOfPub(pubId));
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
