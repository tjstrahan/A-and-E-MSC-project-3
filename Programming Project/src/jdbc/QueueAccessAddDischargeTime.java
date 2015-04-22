package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class that uses JDBC code to connect to the database table 'Patient' to add a
 * discharge time
 */
public class QueueAccessAddDischargeTime implements Runnable {

	/**
	 * Instantiation of DatabaseSettings class
	 */
	static DatabaseSettings db = new DatabaseSettings();
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
		String url = "jdbc:" + db.getDB_PROTOCOL() + db.getWEB_ADDRESS()
				+ db.getURL_USERNAME();
		// Load Driver
		try {
			// Driver
			Class.forName(db.getDB_DRIVER());
		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		// Make Connection
		try {
			con = DriverManager.getConnection(url, db.getDB_USERNAME(),
					db.getDB_PASSWORD());
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return con;
	}

	public QueueAccessAddDischargeTime(int NHSNumber) {
		this.NHSNumber = NHSNumber;
	}

	@Override
	public void run() {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Unix_Timestamp = unix_timestamp() WHERE NHS_number = ?");
			pstmt.setInt(1, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Discharge Time\'");
		}

	}
}