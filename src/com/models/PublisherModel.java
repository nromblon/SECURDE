package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class PublisherModel implements Model{
	
	public static int insertPublisher(String publisher) {
		int id = 0;
		try {
	        PreparedStatement insertPublisher = con.prepareStatement("INSERT INTO publisher (Publisher) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
	        insertPublisher.setString(1, publisher);
	        
	        insertPublisher.executeUpdate();
	        ResultSet publisherId = insertPublisher.getGeneratedKeys();
	        publisherId.next();
	        id = publisherId.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
}
