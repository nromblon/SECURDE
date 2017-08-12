package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.objects.Author;
import com.objects.Publication;
import com.objects.Publisher;

public class PublicationModel implements Model{
	
	public static Publication getPubWithId(int id) {
		Publication pub = null;
		try {
			//TODO: fix because query doesn't show pubs without authors
			PreparedStatement stmt = con.prepareStatement("SELECT * "
														+ "FROM publication p "
														+ "INNER JOIN author a ON p.AuthorId = a.AuthorId "
														+ "INNER JOIN publisher pub ON p.PublisherId = pub.PublisherId "
														+ "INNER JOIN publicationtype pubt ON p.PublicationTypeId = pubt.PublicationTypeId "
														+ "INNER JOIN status s ON p.StatusId = s.StatusId "
														+ "WHERE p.PublicationId=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			pub = new Publication(id, rs.getString("Publication"), new Author(rs.getInt("AuthorId"), rs.getString("AuthorFirstName"), rs.getString("AuthorLastName")), new Publisher(rs.getInt("PublisherId"), rs.getString("Publisher")), rs.getInt("PublicationTypeId"), rs.getInt("StatusId"), rs.getString("Location"), rs.getInt("Year"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pub;
	}
	
	public static ArrayList<Publication> searchPubBy(String searchTerm, String searchBy, String category) {
		if(searchTerm == "")
			return null;
		ArrayList<Publication> pubs = new ArrayList<Publication>();
		
		String condition1 = "";
    	String condition2 = (category.equals("View Entire Collection"))? "": "AND pubt.PublicationType = '" + category+"'";
    	
    	switch(searchBy) {
    	case "Publication": condition1 = "p.Publication LIKE '%"+searchTerm+"%'"; break;
    	case "Author": condition1 = "a.AuthorFirstName LIKE '%"+searchTerm+"%' OR a.AuthorLastName LIKE '%"+searchTerm+"%'"; break;
    	case "Publisher": condition1 = "pub.Publisher LIKE '%"+searchTerm+"%'"; break;
    	}
    	
		try {
			//TODO: fix because query doesn't show pubs without authors
			PreparedStatement getPubs = con.prepareStatement("SELECT * "
															+ "FROM publication p "
															+ "INNER JOIN author a ON p.AuthorId = a.AuthorId "
															+ "INNER JOIN publisher pub ON p.PublisherId = pub.PublisherId "
															+ "INNER JOIN publicationtype pubt ON p.PublicationTypeId = pubt.PublicationTypeId "
															+ "INNER JOIN status s ON p.StatusId = s.StatusId WHERE "+condition1+" "+condition2);
	    	ResultSet rs = getPubs.executeQuery();
			
	    	while(rs.next())
				pubs.add(new Publication(rs.getInt("PublicationId"), rs.getString("Publication"), new Author(rs.getInt("AuthorId"), rs.getString("AuthorFirstName"), rs.getString("AuthorLastName")), new Publisher(rs.getInt("PublisherId"), rs.getString("Publisher")), rs.getInt("PublicationTypeId"), rs.getInt("StatusId"), rs.getString("Location"), rs.getInt("Year")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pubs;
	}
	
	public static int insertPublication(String publication, int authorId, int publisherId, int pubTypeId, String location, int year) {
		int id = 0;
		try {
	        PreparedStatement insertPub = con.prepareStatement("INSERT INTO publication (Publication, AuthorId, PublisherId, PublicationTypeId, Location, Year) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        insertPub.setString(1, publication);
	        insertPub.setInt(2, authorId);
	        insertPub.setInt(3, publisherId);
	        insertPub.setInt(4, pubTypeId);
	        insertPub.setString(5, location);
	        insertPub.setInt(6, year);
	        
	        insertPub.executeUpdate();
	        ResultSet pubId = insertPub.getGeneratedKeys();
	        pubId.next();
	        id = pubId.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
}
