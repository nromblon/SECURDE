package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.objects.Publication;

public class PublicationModel implements Model{
	
	public static Publication getPubWithId(int id) {
		Publication pub = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM publication WHERE PublicationId = "+id);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
//			pub = new Publication(id, rs.getString("Publication"), rs.getString("LastName"), rs.getString("MiddleInitial"), rs.getString("Username"), rs.getString("FirstName"), rs.getString("FirstName"), rs.getString("FirstName"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pub;
	}
}
