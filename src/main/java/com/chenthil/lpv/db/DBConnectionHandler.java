package com.chenthil.lpv.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionHandler {
	
	
	public static void initializeDB() throws SQLException {
		
		Connection conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
		Statement stmt = conn.createStatement();
		stmt.execute("DROP TABLE IF EXISTS PARSER");
		stmt.execute("CREATE TABLE TEST(GUID VARCHAR(255) PRIMARY KEY, "
				+ " TIMEREQ VARCHAR(255), TIMERESP VARCHAR(255), URI VARCHAR(255),"
				+ "ACTION VARCHAR(500))");
		conn.close();
	}
	
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
		
	}
	
	
	
	public static void main(String args[]) throws SQLException {
		initializeDB();
	}

}
