package hospital.address.jdbc;

import hospital.address.TheQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Class that uses JDBC code to connect to the database table 'Patient' and
 * allows a Doctor to make notes to a patients record during treatment in a
 * treatment room
 */

public class DoctorAccess {

	/**
	 * Instantiation of DatabaseSettings class
	 */
	static DatabaseSettings db = new DatabaseSettings();

	/**
	 * Instance Var for Connection to database
	 */
	public static Connection con = null;

	/**
	 * Prepared statement
	 */
	public static PreparedStatement pstmt;

	/**
	 * open DB connection method throws SQLException
	 * 
	 * @returns connection
	 */
	public static Connection getConnection() throws SQLException {
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

	/**
	 * Method for Looking up Patients and displaying details by NHS_number in
	 * the table 'Patient'
	 * 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void displayPatientByNHSNumber(int NHSNumber) throws SQLException,
			ParseException {
		GeneralAccess gA = new GeneralAccess();
		gA.displayPatientByNHSNumber(NHSNumber);
	}

	/**
	 * Method to get the NHS number of a patient in a treatment room
	 * 
	 * @param treatmentRoom
	 * @return
	 */
	public static int getNHSNumberOfTreatmentRoomPatient(int treatmentRoom) {

		int treatmentRoomElement = treatmentRoom - 1;

		if (TheQueue.TreatmentRoom.get(treatmentRoomElement) == null) {
			throw new IllegalArgumentException("Treatment Room Empty");
		} else {

			return TheQueue.TreatmentRoom.get(treatmentRoomElement)
					.getNhsNumber();
		}
	}

	/**
	 * Method to Append to list of general notes a patient has
	 * 
	 * @param NHS_Number
	 * @param notes
	 * @throws SQLException
	 */
	public void writeNotesOnPatientRecord(int NHS_Number, String notes)
			throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Notes = ? WHERE NHS_number = ?");
			pstmt.setString(1, notes);
			pstmt.setInt(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Notes\'");
		}
	}

}// end of class

