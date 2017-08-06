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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String searchTerm = request.getParameter("searchTerm");
		String searchBy = request.getParameter("searchBy");
		String category = request.getParameter("category");
	    
	    request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("publications", PublicationModel.searchPubBy(searchTerm, searchBy, category));
	    
	    request.getRequestDispatcher("/search").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
