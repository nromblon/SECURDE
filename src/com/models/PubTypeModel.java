package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.objects.PubType;

public class PubTypeModel implements Model{
	
	public static String getPubTypeWithId(int id) {
		String pubType = "";
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * "
														+ "FROM `publicationtype` "
														+ "WHERE PublicationTypeId = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			pubType = rs.getString("PublicationType");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pubType;
	}
	
	public static ArrayList<PubType> getPubTypes() {
		ArrayList<PubType> pubTypes = new ArrayList<PubType>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * "
														+ "FROM publicationtype ");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				pubTypes.add(new PubType(rs.getInt("PublicationTypeId"), rs.getString("PublicationType")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pubTypes;
	}
}
