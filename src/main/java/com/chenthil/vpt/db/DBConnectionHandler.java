package com.chenthil.vpt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * DB Utility that prepares the tool for persistence into H2 Embeded database 
 * and returns the connection to the DAO when requested.
 * 
 * @author Chenthil Natarajan
 *
 */
public class DBConnectionHandler {
	
	static {
		try {
			initializeDB();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prepares the program for persistence
	 * 
	 * @throws SQLException
	 */
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
	
	
	/**
	 * Returns the connection back to the DAO
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		
		return DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
		
	}
	
	
	

}
