package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusModel implements Model{
	
	public static String getStatusWithId(int id) {
		String status = "";
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * "
														+ "FROM `status` "
														+ "WHERE StatusId = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			status = rs.getString("Status");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
}
