package com.chenthil.vpt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionHandler {
	
	static {
		try {
			initializeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void initializeDB() throws SQLException {
		
		Connection conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
		Statement stmt = conn.createStatement();
		stmt.execute("DROP TABLE IF EXISTS PARSER");
		stmt.execute("CREATE TABLE PARSER(GUID VARCHAR(255) PRIMARY KEY, "
				+ " TIMEREQ VARCHAR(255), TIMERESP VARCHAR(255), URI VARCHAR(255),"
				+ "ACTION VARCHAR(500))");
		conn.close();
	}
	
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
		
	}
	
	
	

}
