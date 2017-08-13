package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;
import com.objects.Review;

public class ReviewModel implements Model{
	
	public static ArrayList<Review> getReviewsByPublication(int pubId) {
		ArrayList<Review> reviews = new ArrayList<Review>();
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * "
														+ "FROM reviews r "
														+ "INNER JOIN user u ON r.UserId = u.UserId "
														+ "WHERE r.PublicationId = ?");
			stmt.setInt(1, pubId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				reviews.add(new Review(rs.getString("Username"), rs.getString("Review")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reviews;
	}

	public static int insertReview(int pubId, int userId, String review) {
		int id = 0;
		try {
	        PreparedStatement insertReview = con.prepareStatement("INSERT INTO reviews (Review, UserId, PublicationId) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        insertReview.setString(1, review);
	        insertReview.setInt(2, userId);
	        insertReview.setInt(3, pubId);
	        
	        insertReview.executeUpdate();
	        ResultSet reviewId = insertReview.getGeneratedKeys();
	        reviewId.next();
	        id = reviewId.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
}
