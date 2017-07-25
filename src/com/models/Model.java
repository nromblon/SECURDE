package com.models;

import java.sql.Connection;

import com.db.DBConnector;
import com.objects.DBObject;

public interface Model {
	
	public Connection con = DBConnector.getConnection();
	
}
