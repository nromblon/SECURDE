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
import com.models.PublicationModel;
import com.utils.Logger;

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
		request.getRequestDispatcher("/addpublication.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String userId = (String) request.getSession().getAttribute("userId");
	    if(PublicationModel.deletePublication(Integer.valueOf(id))){
	    	Logger.info(this.getServletName(), LogKey.DELETE_PUB, "User deleted a publication"
					, "From:" + request.getRemoteAddr(), "UserId:"+userId, "PubId:"+id);
	    }
	}

}
