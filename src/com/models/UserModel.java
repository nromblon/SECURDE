package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.constants.Status;
import com.mysql.jdbc.Statement;
import com.objects.Publication;
import com.objects.User;

public class UserModel implements Model{
		
//	public static User getUserWithId(int id) {
//		User user = null;
//		try {
//			PreparedStatement stmt = con.prepareStatement("SELECT * FROM user WHERE UserId = ?");
//			stmt.setInt(1, id);
//			ResultSet rs = stmt.executeQuery();
//			
//			rs.next();
//			
//			user = new User(id, rs.getString("FirstName"), rs.getString("LastName"), rs.getString("MiddleInitial"), rs.getString("Username"), rs.getString("FirstName"), rs.getString("FirstName"), rs.getString("FirstName"), rs.getBoolean("IsLocked"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return user;
//	}
	
	public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM user u "
													    + "INNER JOIN privilege p ON u.Privilege_PrivilegeId = p.PrivilegeId");

			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getInt("UserId"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("MiddleInitial"), rs.getString("Username"), rs.getString("Email"), rs.getString("IdentificationNumber"), rs.getString("Privilege"), rs.getBoolean("IsLocked")));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
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
				
				PublicationModel.setPubStatus(pubId, Status.RESERVED);
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
																   + "WHERE pt.UserId = ? AND pt.DateBorrowed IS NULL");
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
	
	public static ArrayList<Publication> getBorrowed(int userId) {
		ArrayList<Publication> reservedPubs = new ArrayList<Publication>();
		try {
			PreparedStatement getBorrowed = con.prepareStatement("SELECT * FROM publicationtransaction pt "
																   + "INNER JOIN publication p ON p.PublicationId = pt.PublicationId "
																   + "WHERE pt.UserId = ? AND pt.DateBorrowed IS NOT NULL");
			getBorrowed.setInt(1, userId);
			
			ResultSet rs = getBorrowed.executeQuery();
			
			while(rs.next()) {
				reservedPubs.add(new Publication(rs.getInt("PublicationId"), rs.getString("Publication")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reservedPubs;
	}
	
	public static boolean overrideReservation(int pubId) {
		boolean success = false;
		Date date = new Date();
		Timestamp dateToday = new Timestamp(date.getTime());
		
		try {
			PreparedStatement getReservation = con.prepareStatement("SELECT * FROM publicationtransaction "
																   + "WHERE PublicationId = ?");
			getReservation.setInt(1, pubId);
			
			ResultSet rs = getReservation.executeQuery();
			
			if(rs.next()) {
				PreparedStatement override = con.prepareStatement("UPDATE publicationtransaction "
																+ "SET DateBorrowed = ? "
																+ "WHERE PublicationTransactionId = ?");
				override.setTimestamp(1, dateToday);
				override.setInt(2, rs.getInt("PublicationTransactionId"));
				
				override.executeUpdate();
				
				PublicationModel.setPubStatus(pubId, Status.OUT);
				success = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return success;
	}
	
	public static boolean setLockedAccount(int userId, boolean isLocked) {
		boolean success = false;
		
		try {
			PreparedStatement setLocked = con.prepareStatement("UPDATE user SET IsLocked = ? "
														     + "WHERE UserId = ?");
			setLocked.setBoolean(1, isLocked);
			setLocked.setInt(2, userId);

			setLocked.executeUpdate();
			
			success = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return success;
	}
	
	public static boolean setLoginAttempts(int userId, int val) {
		boolean success = false;
		System.out.println(userId);
		try {
			PreparedStatement setLoginAttempts = con.prepareStatement("UPDATE user SET Login_Attempts = ? "
														     + "WHERE UserId = ?");
			setLoginAttempts.setInt(1, val);
			setLoginAttempts.setInt(2, userId);

			setLoginAttempts.executeUpdate();
			
			success = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return success;
	}
}
