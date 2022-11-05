/*
 * Singleton for the database connection
 */
package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

final class DBConnect {
	private final static String CREATE_ACCOUNTS = "CREATE TABLE IF NOT EXISTS \"accounts\" (\r\n"
			+ "	\"id\"	INTEGER,\r\n"
			+ "	\"username\"	TEXT NOT NULL UNIQUE,\r\n"
			+ "	\"password\"	TEXT,\r\n"
			+ "	\"securityQuestion\"	TEXT,\r\n"
			+ "	PRIMARY KEY(\"id\")\r\n"
			+ ")";
	
	private final static String CREATE_COURSES = "CREATE TABLE IF NOT EXISTS \"courses\" (\r\n"
			+ "	\"id\"	INTEGER,\r\n"
			+ "	\"name\"	TEXT,\r\n"
			+ "	\"accountID\"	NUMERIC,\r\n"
			+ "	PRIMARY KEY(\"id\"),\r\n"
			+ "	FOREIGN KEY(\"accountID\") REFERENCES \"cards\"(\"id\")\r\n"
			+ ")";
	
	private final static String CREATE_CARDS = "CREATE TABLE IF NOT EXISTS \"cards\" (\r\n"
			+ "	\"id\"	INTEGER,\r\n"
			+ "	\"upperText\"	TEXT,\r\n"
			+ "	\"lowerText\"	TEXT,\r\n"
			+ "	\"learned\"	INTEGER,\r\n"
			+ "	\"courseID\"	INTEGER,\r\n"
			+ "	FOREIGN KEY(\"courseID\") REFERENCES \"courses\"(\"id\"),\r\n"
			+ "	PRIMARY KEY(\"id\")\r\n"
			+ ")";
	
	private DBConnect() {}
	
	private static Connection con = null; // holds the single database connection for the program
	
	/**
	 * Generates a connection to the database once, then serves that connection when needed
	 * @return connection to the database
	 */
	static Connection getConnection() {
		if(con == null) {
			try {
				// Sets up the connection
				con = DriverManager.getConnection("jdbc:sqlite:test.db"); // change name/location?
				
				// Finds the number of tables in the DB
				Statement statement = con.createStatement();
				int tableNum = statement.executeQuery("SELECT * FROM sqlite_master WHERE type = 'table'").getInt(1);

				// writes the schema if the DB is empty
				if(tableNum == 0) {
					statement.execute(CREATE_ACCOUNTS);
					statement.execute(CREATE_COURSES);
					statement.execute(CREATE_CARDS);
				}
			}catch (SQLException e) {
		        System.out.println(e.getMessage());
		    }
		}
		return con;
	}
}
