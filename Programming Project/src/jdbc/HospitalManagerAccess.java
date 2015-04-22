package jdbc;

import hospital.Doctor;
import hospital.HospitalManager;
import hospital.Nurse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HospitalManagerAccess {
	
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
	 * Instance Var for statements made into the database
	 */
	public static Statement stmt;

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
	 * Method to set the on call status of doctors or nurses in database
	 * 
	 * @param onCall
	 * @param staffID
	 * @throws SQLException
	 */
	public void setOnCall(int onCall, int staffID) throws SQLException {
		Connection con = getConnection();

		try {
			pstmt = con
					.prepareStatement("UPDATE Staff SET on_Call_Status = ? WHERE staffID = ?");
			pstmt.setInt(1, onCall);
			pstmt.setInt(2, staffID);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'On Call\'");
		}
	}

	public void getMedicalTeamDocs() throws Exception {

		try {

			Connection con = getConnection();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT * FROM staff NATURAL JOIN staffaccess WHERE Role = \"Doctor\" AND Medical_Team IN (1,2,3);";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				HospitalManager.medicalTeam.add(new Doctor(rs
						.getString("Title"), rs.getString("First_Name"), rs
						.getString("Middle_Name"), rs.getString("Last_Name"),
						GeneralAccess.correctUKDateFormat(rs.getString("DOB")),
						rs.getString("First_line_of_Address"), rs
								.getString("Second_line_of_Address"), rs
								.getString("Third_line_of_Address"), rs
								.getString("City"), rs.getString("Postcode"),
						rs.getLong("Contact_Number"), rs.getInt("StaffID"), rs
								.getInt("StaffID"), rs.getString("Password"),
						rs.getInt("Medical_Team")));
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	public void getMedicalTeamNurses() throws Exception {

		try {

			Connection con = getConnection();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT * FROM staff NATURAL JOIN staffaccess WHERE Role = \"Nurse\" AND Medical_Team IN (1,2,3);";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				HospitalManager.medicalTeam.add(new Nurse(
						rs.getString("Title"), rs.getString("First_Name"), rs
								.getString("Middle_Name"), rs
								.getString("Last_Name"), GeneralAccess
								.correctUKDateFormat(rs.getString("DOB")), rs
								.getString("First_line_of_Address"), rs
								.getString("Second_line_of_Address"), rs
								.getString("Third_line_of_Address"), rs
								.getString("City"), rs.getString("Postcode"),
						rs.getLong("Contact_Number"), rs.getInt("StaffID"), rs
								.getInt("StaffID"), rs.getString("Password"),
						rs.getInt("Medical_Team")));
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	public void getOnCallTeamDocs(int medicalTeam) throws Exception {

		try {

			Connection con = getConnection();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT * FROM staff NATURAL JOIN staffaccess WHERE Role = \"Doctor\" AND Medical_Team = "
					+ medicalTeam + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				HospitalManager.onCallTeam.add(new Doctor(
						rs.getString("Title"), rs.getString("First_Name"), rs
								.getString("Middle_Name"), rs
								.getString("Last_Name"), GeneralAccess
								.correctUKDateFormat(rs.getString("DOB")), rs
								.getString("First_line_of_Address"), rs
								.getString("Second_line_of_Address"), rs
								.getString("Third_line_of_Address"), rs
								.getString("City"), rs.getString("Postcode"),
						rs.getLong("Contact_Number"), rs.getInt("StaffID"), rs
								.getInt("StaffID"), rs.getString("Password"),
						rs.getInt("Medical_Team")));
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	public void getOnCallTeamNurses(int medicalTeam) throws Exception {

		try {

			Connection con = getConnection();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT * FROM staff NATURAL JOIN staffaccess WHERE Role = \"Nurse\" AND Medical_Team = "
					+ medicalTeam + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				HospitalManager.onCallTeam.add(new Nurse(rs.getString("Title"),
						rs.getString("First_Name"),
						rs.getString("Middle_Name"), rs.getString("Last_Name"),
						GeneralAccess.correctUKDateFormat(rs.getString("DOB")),
						rs.getString("First_line_of_Address"), rs
								.getString("Second_line_of_Address"), rs
								.getString("Third_line_of_Address"), rs
								.getString("City"), rs.getString("Postcode"),
						rs.getLong("Contact_Number"), rs.getInt("StaffID"), rs
								.getInt("StaffID"), rs.getString("Password"),
						rs.getInt("Medical_Team")));
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}
