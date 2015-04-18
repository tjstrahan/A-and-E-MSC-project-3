package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueueAccessClearDischargeTime implements Runnable{

	/**
	 * Username for access to Database
	 */
	private static final String DB_USERNAME = "40142115";

	/**
	 * Use if Username is part of the Databases URL, otherwise leave blank
	 */
	private static final String URL_USERNAME = "40142115";

	/**
	 * Password for access to Database
	 */
	private static final String DB_PASSWORD = "YCR2335";

	/**
	 * Main body of URL of Database, eg //web2.eeecs.qub.ac.uk/ or
	 * thin:@db.yale.edu:univdb
	 */
	public static final String WEB_ADDRESS = "//web2.eeecs.qub.ac.uk/";

	/**
	 * Type of database, eg mysql: or oracle: etc
	 */
	public static final String DB_PROTOCOL = "mysql:";

	/**
	 * name of Database driver to be called, for mysql com.mysql.jdbc.Driver or
	 * for oracle oracle.jdbc.driver.OracleDriver
	 */
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * Instance Var for Connection to database
	 */
	public static Connection con = null;

	/**
	 * Instance of prepared statment
	 */
	public static PreparedStatement pstmt;

	/**
	 * Instance Var for the nhs number
	 */
	private int NHSNumber;

	/**
	 * open DB connection method throws SQLException
	 * 
	 * @returns connection
	 */
	public static Connection getConnection() {
		String url = "jdbc:" + DB_PROTOCOL + WEB_ADDRESS + URL_USERNAME;
		// Load Driver
		try {
			// Driver
			Class.forName(DB_DRIVER);
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		// Make Connection
		try {
			con = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return con;
	}

	public QueueAccessClearDischargeTime(int NHSNumber) {
		this.NHSNumber = NHSNumber;
	}
	
	@Override
	public void run() {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Unix_Timestamp = 0 WHERE NHS_number = ?");
			pstmt.setInt(1, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Discharge Time\'");
		}

	}

}
