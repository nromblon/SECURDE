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

import com.constants.LogKey;
import com.db.DBConnector;
import com.models.ReviewModel;
import com.models.UserModel;
import com.mysql.jdbc.Statement;
import com.utils.Logger;
import com.utils.Validator;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addpublication.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = (int) request.getSession().getAttribute("userId");
		String pubId = request.getParameter("pubId");
		String reviewText = request.getParameter("reviewText");

		Boolean flag = true;
        
        Validator validator = Validator.getInstance();

		if(!(validator.isAlphaNumericHasSpace(reviewText, 140))) {
			request.setAttribute("error", "Invalid Review Text!");
			flag = false;
		}
		System.out.println(flag);
		
		if(flag && !UserModel.getBorrowed(userId).isEmpty()){
			int revId = ReviewModel.insertReview(Integer.valueOf(pubId), (int)request.getSession().getAttribute("userId"), reviewText);
			if(revId > -1)
				Logger.info(this.getServletName(), LogKey.ADD_REVIEW, "User added review", "From:" + request.getRemoteAddr(),
					"UserId:"+userId,"PubId:"+pubId,"ReviewId:"+revId);
			
		}
		
		response.sendRedirect("publication/details?id="+pubId);
//		else
//			request.getRequestDispatcher("/addreview").forward(request, response); 
		
	}

}
