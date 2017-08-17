package com.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;
import com.utils.Validator;

import java.sql.Connection;

/**
 * Servlet implementation class RoomsServlet
 */
@WebServlet("/rooms")
public class RoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/rooms.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rmid = Integer.parseInt(request.getParameter("rmid"));
		int slotid = Integer.parseInt(request.getParameter("slotid"));
		Date date = Date.valueOf(request.getParameter("date"));
		if(request.getSession().getAttribute("userId")==null)
			return;
		int userId = (int) request.getSession().getAttribute("userId");
		Connection conn = null;

		Boolean flag = true;
        
        Validator validator = Validator.getInstance();
		
		if(!(validator.isDate(date.toString()))) {
			request.setAttribute("error", "Invalid Date!");
			flag = false;
		}
		
		if(flag){
			try{
				conn = DBConnector.getConnection();
				String line = "INSERT INTO `roomtransaction` (User_UserId,Room_RoomId,RoomSlotId,RoomReserveDate) VALUES (?,?,?,?)";
				PreparedStatement stmt = conn.prepareStatement(line);
				stmt.setInt(1, userId);
				stmt.setInt(2, rmid);
				stmt.setInt(3, slotid);
				stmt.setDate(4, date);
				
				if(stmt.executeUpdate()>0){
					response.getWriter().write("pass");
				}
				else
					response.getWriter().write("fail");
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

}
