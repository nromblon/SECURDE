package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.DBConnector;

/**
 * Servlet implementation class UpdateRoomReservationServlet
 */
@WebServlet("/rooms/update")
public class UpdateRoomReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoomReservationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = Date.valueOf(request.getParameter("date"));
		
		String json = "[";
		
		Connection conn = null;
		try{
			conn = DBConnector.getConnection();
			String query = "SELECT * FROM `roomtransaction` WHERE RoomReserveDate=?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setDate(1, date);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				int rmid = rs.getInt("Room_RoomId");
				int slotid = rs.getInt("RoomSlotId");
				
				json+= "{\"rmid\":"+rmid+",\"slotid\":"+slotid+"},";
			}
			json = json.substring(0, (json.length()-1));
			json+="]";
			response.getWriter().write(json);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rmid = Integer.parseInt(request.getParameter("rmid"));
		int slotid = Integer.parseInt(request.getParameter("slotid"));
		Date date = Date.valueOf(request.getParameter("date"));
		
		Connection conn = null;
		try{
			conn = DBConnector.getConnection();
			
			String line = "DELETE FROM `roomtransaction` WHERE Room_RoomId=? AND RoomSlotId=? AND RoomReserveDate=?";
			PreparedStatement stmt = conn.prepareStatement(line);
			stmt.setInt(1, rmid);
			stmt.setInt(2, slotid);
			stmt.setDate(3, date);
			
			if(stmt.executeUpdate()>0){
				response.getWriter().write("pass");
			}
			else{
				System.out.println("nochange");
				response.getWriter().write("nochange");
			}
		}catch(Exception e){
			e.printStackTrace();
			response.getWriter().write("error");
		}
	}

}
