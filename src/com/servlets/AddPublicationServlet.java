package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.constants.LogKey;
import com.constants.Privilege;
import com.models.PubTypeModel;
import com.models.TagModel;
import com.utils.Logger;

/**
 * Servlet implementation class AddPublicationServlet
 */
@WebServlet("/publication/add")
public class AddPublicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPublicationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int privilege = -1;
		try{
			if(session.getAttribute("privilege")!=null)
    			privilege = Integer.parseInt( (String) session.getAttribute("privilege"));
    	}catch(NumberFormatException e){
    		//TODO: appropriate error message for invalid number syntax
    		e.printStackTrace();
    	}
		Logger.info(this.getServletName(), LogKey.AUTH_ATTEMPT, "User attempted authorization", "From:" + request.getRemoteAddr());
		
		if(session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			if (privilege == Privilege.LIB_STAFF || privilege == Privilege.LIB_MANAGER) {
				response.sendRedirect("publication/add");
				request.setAttribute("allTags", TagModel.getAllTags());
				request.setAttribute("pubTypes", PubTypeModel.getPubTypes());
				
				Logger.info(this.getServletName(), LogKey.AUTH_SUCCESS, "User Authorization Successful", "From:" + request.getRemoteAddr(), "Username:"+username);
				
				request.getRequestDispatcher("/addpublication.jsp").forward(request, response);
				
			}
		}else{
			response.sendRedirect("../login"); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
