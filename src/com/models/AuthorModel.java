package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class AuthorModel implements Model{
	
	public static int insertAuthor(String firstName, String lastName) {
		int id = 0;
		try {
	        PreparedStatement insertAuthor = con.prepareStatement("INSERT INTO author (AuthorFirstName, AuthorLastName) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
	        insertAuthor.setString(1, firstName);
	        insertAuthor.setString(2, lastName);
	        
	        insertAuthor.executeUpdate();
	        ResultSet authorId = insertAuthor.getGeneratedKeys();
	        authorId.next();
	        id = authorId.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
}
