/**
 * 
 */
package networking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class ConnectToDatabase {

	/**
	 * Username for access to Database
	 */
	private static final String DB_USERNAME = "";

	/**
	 * Use if Username is part of the Databases URL, otherwise leave blank
	 */
	private static final String URL_USERNAME = "";

	/**
	 * Password for access to Database
	 */
	private static final String DB_PASSWORD = "";

	/**
	 * Main body of URL of Database, eg //web2.eeecs.qub.ac.uk/ or
	 * thin:@db.yale.edu:univdb
	 */
	public static final String WEB_ADDRESS = "";

	/**
	 * Type of database, eg mysql: or oracle: etc
	 */
	public static final String DB_PROTOCOL = "mysql:";

	/**
	 * name of Database driver to be called, for mysql com.mysql.jdbc.Driver or
	 * for oracle oracle.jdbc.driver.OracleDriver
	 */
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	public static void main(String args[]) {

		String url = "jdbc:" + DB_PROTOCOL + WEB_ADDRESS + URL_USERNAME;
		Connection con = null;
		

		/*
		 * Loading the driver
		 */
		try {

			// Driver
			Class.forName(DB_DRIVER);

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		/*
		 * Making the connection
		 */
		try {
			con = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);

			System.out.println("connected");
			// jdbc stuff goes here
			
			
			
			
			
			
			
			

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		try {
			con.close();
			System.out.println("close");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
