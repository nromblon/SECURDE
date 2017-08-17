package com.servlets;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;
import com.models.PubTypeModel;
import com.models.PublicationModel;
import com.mysql.jdbc.Statement;
import com.utils.Validator;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();//TODO: fix error pages to be less descriptive
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
		String type = request.getParameter("type");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String year = request.getParameter("year");
		String location = request.getParameter("location");
		String[] tags = request.getParameterValues("tags");
		
		Boolean flag = true;
        
        Validator validator = Validator.getInstance();
		
		if(!(validator.isAlphaNumericHasSpace(type, 45))) {
			request.setAttribute("error", "Invalid Type!");
			flag = false;
		}
		if(!(validator.isAlphaNumericHasSpace(title, 45))) {
			request.setAttribute("error", "Invalid Title!");
			flag = false;
		}
		if(!(validator.isAlphaNumericHasSpace(author, 45))) {
			request.setAttribute("error", "Invalid Author!");
			flag = false;
		}
		if(!(validator.isNumeric(year))) {
			request.setAttribute("error", "Invalid Year!");
			flag = false;
		}
		if(!(validator.isAlphaNumericHasSpace(location, 45))) {
			request.setAttribute("error", "Invalid Location!");
			flag = false;
		}
		
//		int authorId = AuthorModel.insertAuthor("", author);
//		int publisherId = PublisherModel.insertPublisher(publisher);
		if(flag){
			int pubId = PublicationModel.insertPublication(Integer.valueOf(type), title, author, publisher, location, Integer.valueOf(year), tags);
			response.sendRedirect("publication/details?id="+pubId);
		}
		else
        	request.getRequestDispatcher("/addbook").forward(request, response); 
		
	}

}
