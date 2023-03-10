package com.promineotech.mwa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.promineotech.mwa.exception.*;

public class DbConnection {

	private static final String HOST = "localhost";
	private static final String PASSWORD = "projects";
	private static final int PORT = 3306;
	private static final String SCHEMA = "mage_warrior_archer";
	private static final String USER = "projects";
	
	public static Connection getConnection() {
		
		String uri = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s", 
				HOST, PORT, SCHEMA, USER, PASSWORD);
		
		try {
			Connection conn = DriverManager.getConnection(uri);
			System.out.println("Connection to schema " + SCHEMA + " complete.");
			return conn;
		} catch (SQLException e) {
			System.out.println("Unable to get connection at " + uri);
			throw new DbException("Unable to get connection at \" + uri");
		}
		
	}
	
}
