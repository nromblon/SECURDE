package com.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.objects.Tag;

public class TagModel implements Model{
	
	public static ArrayList<Tag> getAllTags() {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		
		try {
			PreparedStatement getTags = con.prepareStatement("SELECT * FROM tags");
			ResultSet rs = getTags.executeQuery();
			
			while(rs.next()) {
				tags.add(new Tag(rs.getInt("TagId"), rs.getString("Tag")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tags;
	}
	
	public static ArrayList<Tag> getTagsOfPub(int pubId) {
		ArrayList<Tag> tags = new ArrayList<Tag>();
		
		try {
			PreparedStatement getTags = con.prepareStatement("SELECT * FROM publicationtags pt "
														   + "INNER JOIN tags t ON pt.TagId = t.TagId "
														   + "WHERE pt.PublicationId = ?");
			getTags.setInt(1, pubId);
			ResultSet rs = getTags.executeQuery();
			
			while(rs.next()) {
				tags.add(new Tag(rs.getInt(2), rs.getString("Tag")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tags;
	}
	
	public static boolean deleteTagsofPub(int pubId) {
		boolean success = false;
		
		try {
			PreparedStatement deleteTags = con.prepareStatement("DELETE FROM publicationtags WHERE PublicationId = ?");
			deleteTags.setInt(1, pubId);
			deleteTags.executeUpdate();
			
			success = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public static boolean insertTagsofPub(int pubId, String[] tags) {
		boolean success = false;
		
		try {
			
			for (String tag : tags) {
				PreparedStatement insertTag = con.prepareStatement("INSERT INTO publicationtags (PublicationId, TagId) VALUES (?, ?)");
				insertTag.setInt(1, pubId);
				insertTag.setInt(2, Integer.valueOf(tag));
				insertTag.executeUpdate();
			}
			
			success = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

}
