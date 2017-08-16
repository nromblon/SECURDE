package com.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.models.PubTypeModel;
import com.models.UserModel;
import com.objects.PubType;
import com.utils.Validator;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String category = (request.getParameter("category") == null)? "View Entire Collection": request.getParameter("category");
		String searchBy = (request.getParameter("searchBy") == null)? "Title": request.getParameter("searchBy");
		ArrayList<PubType> pubTypes = PubTypeModel.getPubTypes();
		
		String categorySelect = "<option>View Entire Collection</option>";
		for (PubType pubType : pubTypes) {
			if(pubType.getName().equals(category))
				categorySelect = categorySelect.concat("<option value = "+pubType.getName()+" selected>"+pubType.getName()+"</option>");
			else
				categorySelect = categorySelect.concat("<option value = "+pubType.getName()+">"+pubType.getName()+"</option>");
		}
		
		String searchBySelect = "<option value = 'Publication' "+((searchBy.equals("Publication"))? "selected":"")+">Title</option>"
								+ "<option value = 'Author' "+((searchBy.equals("Author"))? "selected":"")+">Author</option>"
								+ "<option value = 'Publisher' "+((searchBy.equals("Publisher"))? "selected":"")+">Publisher</option>";
		
		request.setAttribute("categorySelect", categorySelect);
		request.setAttribute("searchBySelect", searchBySelect);
		
		if(request.getSession().getAttribute("username") != null) {
			int userId = (int) request.getSession().getAttribute("userId");
			request.setAttribute("reservedPubs", UserModel.getReservations(userId));
			request.setAttribute("borrowedPubs", UserModel.getBorrowed(userId));
		}
		
		request.getRequestDispatcher("/search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
