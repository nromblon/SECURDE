package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static Connection conn;
	
	private static String DB_URL = "jdbc:mysql://localhost:3306/shs_library_db";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = "";
	
	
	public static Connection getConnection(){
		if(conn==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
