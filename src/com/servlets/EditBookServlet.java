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
import com.models.PublicationModel;
/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/editbook")
public class EditBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private String bookId;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/publication/details?id="+bookId).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		//TODO: change type and others
		String type = request.getParameter("type");
		String bookId = request.getParameter("id");
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String location = request.getParameter("location");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String[] tags = request.getParameterValues("tags[]");

		PublicationModel.editPublication(Integer.valueOf(bookId), Integer.valueOf(type), title, author, publisher, location, Integer.valueOf(year), tags);
	}

}
