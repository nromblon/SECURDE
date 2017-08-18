package com.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.constants.LogKey;
import com.models.UserModel;
import com.utils.Logger;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/override")
public class OverrideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverrideServlet() {
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
		String pubId = request.getParameter("id");
		String userType = request.getParameter("userType");
		
		if(UserModel.overrideReservation(Integer.valueOf(pubId), userType)){
			Logger.info(this.getServletName(), LogKey.OVERRIDE_PUB, "Publication reservation overriden", "From:" + request.getRemoteAddr(),"UserId:"
					+request.getSession().getAttribute("userId"), "PubId:"+pubId);
		}
	}

}
