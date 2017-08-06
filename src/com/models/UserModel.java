package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.objects.User;

public class UserModel implements Model{
		
	public static User getUserWithId(int id) {
		User user = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE UserId = "+id);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			user = new User(id, rs.getString("FirstName"), rs.getString("LastName"), rs.getString("MiddleInitial"), rs.getString("Username"), rs.getString("FirstName"), rs.getString("FirstName"), rs.getString("FirstName"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
