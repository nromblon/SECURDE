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
import com.models.PublicationModel;
import com.models.UserModel;
import com.objects.Publication;

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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String searchTerm = request.getParameter("searchTerm");
		String searchBy = request.getParameter("searchBy");
		String category = request.getParameter("category");
		ArrayList<Publication> results = PublicationModel.searchPubBy(searchTerm, searchBy, category);
		String message = "";
		
		//TODO: add security checks for input
		if(searchTerm == "")
			message = message.concat("Input a search term to search!<br>");
		else if(results.isEmpty())
			message = message.concat("There seems to be nothing here...<br>");
	    
	    request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("publications", results);
        request.setAttribute("message", message);
    
	    request.getRequestDispatcher("/search").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
