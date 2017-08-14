package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.objects.Author;
import com.objects.Publication;
import com.objects.Publisher;
import com.objects.User;

public class UserModel implements Model{
		
	public static User getUserWithId(int id) {
		User user = null;
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE UserId = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			user = new User(id, rs.getString("FirstName"), rs.getString("LastName"), rs.getString("MiddleInitial"), rs.getString("Username"), rs.getString("FirstName"), rs.getString("FirstName"), rs.getString("FirstName"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static boolean checkExistingReservation(int userId, int pubId) {
		try {
			PreparedStatement checkExisting = con.prepareStatement("SELECT * FROM publicationtransaction "
																 + "WHERE UserId = ? AND PublicationId = ?");
			checkExisting.setInt(1, userId);
			checkExisting.setInt(2, pubId);
			
			ResultSet rs = checkExisting.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean checkExistingReservation(int pubId) {
		try {
			PreparedStatement checkExisting = con.prepareStatement("SELECT * FROM publicationtransaction "
																 + "WHERE PublicationId = ?");
			checkExisting.setInt(1, pubId);
			
			ResultSet rs = checkExisting.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static int reservePublication(int userId, int pubId) {
		int transactionId = 0;
		try {
			if(checkExistingReservation(userId, pubId)) {
				System.out.println("Book already reserved");
				transactionId = -1;
			} else {
				PreparedStatement insertTransaction = con.prepareStatement("INSERT INTO publicationtransaction (UserId, PublicationId) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
				insertTransaction.setInt(1, userId);
				insertTransaction.setInt(2, pubId);
				
				insertTransaction.executeUpdate();
				ResultSet rs = insertTransaction.getGeneratedKeys();
				rs.next();
				transactionId = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return transactionId;
	}
	
	public static ArrayList<Publication> getReservations(int userId) {
		ArrayList<Publication> reservedPubs = new ArrayList<Publication>();
		try {
			PreparedStatement getReservations = con.prepareStatement("SELECT * FROM publicationtransaction pt "
																   + "INNER JOIN publication p ON p.PublicationId = pt.PublicationId "
																   + "WHERE pt.UserId = ?");
			getReservations.setInt(1, userId);
			
			ResultSet rs = getReservations.executeQuery();
			
			while(rs.next()) {
				reservedPubs.add(new Publication(rs.getInt("PublicationId"), rs.getString("Publication")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reservedPubs;
	}
}
