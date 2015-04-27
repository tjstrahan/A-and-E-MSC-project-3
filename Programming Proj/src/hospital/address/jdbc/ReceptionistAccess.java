/**
 * JDBC Code to Connect to Database from the Receptionist.
 */
package hospital.address.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import hospital.address.model.HospitalManager;
import hospital.address.model.Nurse;
import hospital.address.model.Patient;
import hospital.address.model.Receptionist;
import hospital.address.model.Staff;
import hospital.address.view.LoginController;

/**
 * Class that uses JDBC code to connect to the database table 'Patient'
 * 
 * @author James Maguire, Emma Scroggie, Tom McDonnell and Kieron Allsop
 */
public class ReceptionistAccess {
	/**
	 * Instantiation of DatabaseSettings class
	 */
	static DatabaseSettings db = new DatabaseSettings();
	/**
	 * Instance Var for Connection to database
	 */
	public static Connection con = null;

	/**
	 * Instance Var for statements made into the database
	 */
	public static Statement stmt;

	/**
	 * Prepared Statement
	 */
	public static PreparedStatement pstmt;

	/**
	 * Statement used to select all patients from database - set as such to
	 * exclude 1 patient who will be used to demonstrate other functions of the
	 * system
	 */
	public static final String SELECT_ALL_STATEMENT = "SELECT * FROM PATIENT WHERE NHS_number > 200000000";

	/**
	 * Instance Var for patients first name used in SQL Query
	 */
	public static String PATIENT_FIRST_NAME;

	/**
	 * Instance Var for patients Last Name used in SQL Query
	 */
	public static String PATIENT_LAST_NAME;

	/**
	 * Instance Var for patients DOB used in SQL Query
	 */
	public static String DOB;

	/**
	 * First line of address
	 */
	public static String firstLineOfAddress;

	/**
	 * Second line of address
	 */
	public static String secondLineOfAddress;

	/**
	 * Third line of address
	 */
	public static String thirdLineOfAddress;

	/**
	 * City
	 */
	public static String city;

	/**
	 * Patients postcode
	 */
	public static String Postcode;

	/**
	 * Patients primary contact numbe
	 */
	public static long contactNumber;

	/**
	 * Allergies the patient may have
	 */
	public static String allergies;

	/**
	 * Conditions the patient may have
	 */
	public static String knownConditions;

	/**
	 * Patients next of kin
	 */
	public static String nextOfKin;

	/**
	 * Constant for the minimum value of the mobile number
	 */
	static final long MOBILE_NUMBER_MIN_LENGTH = 100000000000L;

	/**
	 * Constant for the maximum value of the mobile number
	 */
	static final long MOBILE_NUMBER_MAX_LENGTH = 999999999999L;

	/**
	 * Static instantiation of the Patient class
	 */
	static Patient patient;
	
	public static LinkedList<Staff> Receptionist = new LinkedList<Staff>();

	/**
	 * Method to connect to the database
	 * 
	 * @return
	 */
	public static Connection con() {

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
			System.out.println("You are Connected to Database");

			/**
			 * 
			 */
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return con;
	}

public void getReceptionists() throws Exception {
		
		try {

			Connection con = con();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT * FROM staff NATURAL JOIN staffaccess WHERE Role = \"Receptionist\";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Receptionist.add(new Receptionist(
						rs.getString("Title"),
						rs.getString("First_Name"), 
						rs.getString("Middle_Name"),
						rs.getString("Last_Name"),
						GeneralAccess.correctUKDateFormat(rs.getString("DOB")),
						rs.getString("First_line_of_Address"),
						rs.getString("Second_line_of_Address"), 
						rs.getString("Third_line_of_Address"), 
						rs.getString("City"),
						rs.getString("Postcode"),
						rs.getInt("StaffID"),
						rs.getLong("Contact_Number"),
						rs.getInt("LoginID"), 
						rs.getString("Password")));
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
	

	/**
	 * Method for finding a patients NHS number in the database
	 * 
	 * @throws SQLException
	 */
	public static int lookUpPatientNHSNumber(String PATIENT_NAME,
			String PATIENT_LAST_NAME, String DOB) throws SQLException {

		Connection con = con();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql = "SELECT NHS_number FROM PATIENT WHERE First_Name = \""
				+ PATIENT_NAME + "\" and Last_Name = \"" + PATIENT_LAST_NAME
				+ "\" and DOB = \"" + DOB + "\";";
		ResultSet rs = stmt.executeQuery(sql);

		// Move cursor to the last row.
		rs.last();

		// Retrieve by column name
		int NHS_number = rs.getInt("NHS_number");

		// close statement object
		stmt.close();
		// close connection
		con.close();

		// Return NHS number of the patient
		return NHS_number;
	}

	/**
	 * Method to update the first line of the address
	 * 
	 * @param NHSNumber
	 * @param firstLineOfAddress
	 */
	public void updateFirstLineOfAddress(int NHSNumber,
			String firstLineOfAddress) throws SQLException {

		if (firstLineOfAddress.isEmpty()) {
			throw new IllegalArgumentException("An address must be entered");
		} else {

			Connection con = con();
			try {
				pstmt = con
						.prepareStatement("UPDATE Patient SET  First_Line_Of_Address = ? WHERE NHS_number = ?");
				pstmt.setString(1, firstLineOfAddress);
				pstmt.setInt(2, NHSNumber);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				System.err.println("Failed to update \'1st Address Line\'");
			}
		}
	}

	/**
	 * Method to update the second line of the address
	 * 
	 * @param NHS_Number
	 * @param secondLineOfAddress
	 * @throws SQLException
	 */
	public void updateSecondLineOfAddress(int NHSNumber,
			String secondLineOfAddress) throws SQLException {

		Connection con = con();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET   Second_Line_Of_Address = ? WHERE NHS_number = ?");
			pstmt.setString(1, secondLineOfAddress);
			pstmt.setInt(2, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'2nd Address Line\'");
		}
	}

	/**
	 * Method to update the third line of the address
	 * 
	 * @param NHS_Number
	 * @param thirdLineOfAddress
	 * @throws SQLException
	 */
	public void updateThirdLineOfAddress(int NHSNumber,
			String thirdLineOfAddress) throws SQLException {

		Connection con = con();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Third_Line_Of_Address  = ? WHERE NHS_number = ?");
			pstmt.setString(1, thirdLineOfAddress);
			pstmt.setInt(2, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'3rd Address Line\'");
		}
	}

	/**
	 * Method to update the city a patient lives in
	 * 
	 * @param NHS_Number
	 * @param city
	 * @throws SQLException
	 */
	public void updateCity(int NHSNumber, String city) throws SQLException {

		if (city.isEmpty()) {
			throw new IllegalArgumentException("An address must be entered");
		} else {

			Connection con = con();
			try {
				pstmt = con
						.prepareStatement("UPDATE Patient SET City  = ? WHERE NHS_number = ?");
				pstmt.setString(1, city);
				pstmt.setInt(2, NHSNumber);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				System.err.println("Failed to update \'City\'");
			}
		}
	}

	/**
	 * Method to update a patients postcode
	 * 
	 * @param NHS_Number
	 * @param postcode
	 * @throws SQLException
	 */
	public void updatePostcode(int NHSNumber, String postcode)
			throws SQLException {

		if (postcode.isEmpty()) {
			throw new IllegalArgumentException("An address must be entered");
		} else {

			Connection con = con();
			try {
				pstmt = con
						.prepareStatement("UPDATE Patient SET Postcode  = ? WHERE NHS_number = ?");
				pstmt.setString(1, postcode);
				pstmt.setInt(2, NHSNumber);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				System.err.println("Failed to update \'Postcode\'");
			}
		}
	}

	/**
	 * Method to update patients primary contact number
	 * 
	 * @param NHS_Number
	 * @param contactNumber
	 * @throws SQLException
	 */
	public void updateContactNumber(int NHSNumber, long contactNumber2)
			throws SQLException, IllegalArgumentException {

		Long contactNumber;

		contactNumber = contactNumber2;

		if (contactNumber < MOBILE_NUMBER_MIN_LENGTH
				|| contactNumber > MOBILE_NUMBER_MAX_LENGTH) {
			throw new IllegalArgumentException(
					"Contact number must be 11 numbers long");
		} else {

			Connection con = con();
			try {
				pstmt = con
						.prepareStatement("UPDATE Patient SET Contact_Number  = ? WHERE NHS_number = ?");
				pstmt.setLong(1, contactNumber);
				pstmt.setInt(2, NHSNumber);
				pstmt.executeUpdate();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				System.err.println("Failed to update \'Contact Number\'");
			}
		}
	}

	/**
	 * Method to add a patients first allergy
	 * 
	 * @param NHS_Number
	 * @param allergies
	 * @throws SQLException
	 */
	public void addFirstAllergies(int NHSNumber, String allergies)
			throws SQLException {

		Connection con = con();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Allergies = ? WHERE NHS_number = ?");
			pstmt.setString(1, allergies);
			pstmt.setInt(2, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Allergies\'");
		}
	}

	/**
	 * Method to Append to list of allergies a patient has
	 * 
	 * @param NHS_Number
	 * @param allergies
	 * @throws SQLException
	 */
	public void addMoreAllergies(int NHSNumber, String allergies)
			throws SQLException {

		Connection con = con();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Allergies = CONCAT(Allergies, ', ', ?) WHERE NHS_number = ?");
			pstmt.setString(1, allergies);
			pstmt.setInt(2, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Allergies\'");
		}
	}

	/**
	 * Method to add a patients first known condition
	 * 
	 * @param NHS_Number
	 * @param knownCondtions
	 * @throws SQLException
	 */
	public void addFirstKnownConditions(int NHSNumber, String knownCondtions)
			throws SQLException {

		Connection con = con();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Known_Conditions = ? WHERE NHS_number = ?");
			pstmt.setString(1, knownCondtions);
			pstmt.setInt(2, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Known Condtions\'");
		}
	}

	/**
	 * Method to Append to list of known conditions a patient has
	 * 
	 * @param NHS_Number
	 * @param knownCondtions
	 * @throws SQLException
	 */
	public void addMoreKnownConditions(int NHSNumber, String knownCondtions)
			throws SQLException {

		Connection con = con();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Known_Conditions = CONCAT(Known_Conditions, ', ', ?) WHERE NHS_number = ?");
			pstmt.setString(1, knownCondtions);
			pstmt.setInt(2, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Known Condtions\'");
		}
	}

	/**
	 * Method to update a patients next of kin
	 * 
	 * @param NHS_Number
	 * @param nextOfKin
	 * @throws SQLException
	 */
	public void updateNextOfKin(int NHSNumber, String nextOfKin)
			throws SQLException {

		Connection con = con();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Next_Of_Kin  = ? WHERE NHS_number = ?");
			pstmt.setString(1, nextOfKin);
			pstmt.setInt(2, NHSNumber);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("Failed to update \'Next of Kin\'");
		}
	}

	/**
	 * Method to add one patient manually to the queue having searched for them
	 * in the database using the nhs-number which is the primary key of the
	 * patient table.
	 * 
	 * @param NHS_Number
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static void admitPatient(int NHSNumber)
			throws IllegalArgumentException, Exception {

		try {

			Connection con = con();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT * FROM PATIENT WHERE NHS_Number = " + NHSNumber;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Receptionist.patientsFromDB.addFirst(new Patient(rs
						.getString("Title"), rs.getString("First_Name"), rs
						.getString("Middle_Name"), rs.getString("Last_Name"),
						GeneralAccess.correctUKDateFormat(rs.getString("DOB")),
						rs.getString("First_line_of_Address"), rs
								.getString("Second_line_of_Address"), rs
								.getString("Third_line_of_Address"), rs
								.getString("City"), rs.getString("Postcode"),
						rs.getLong("Contact_Number"), rs.getInt("NHS_number"),
						rs.getString("Allergies"), rs
								.getString("Known_Conditions"), rs
								.getString("Blood_Group"), rs.getString("Sex"),
						rs.getString("Next_of_Kin"), rs.getString("Gp_Name"),
						rs.getString("Gp_Code"), rs.getString("Notes")));
			}

			// close statement object
			stmt.close();
			// close connection
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	/**
	 * Method to get all patients from the database
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static void getAllPatients() throws IllegalArgumentException,
			Exception {

		try {

			Connection con = con();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = SELECT_ALL_STATEMENT;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Receptionist.patientsFromDB.add(new Patient(rs
						.getString("Title"), rs.getString("First_Name"), rs
						.getString("Middle_Name"), rs.getString("Last_Name"),
						GeneralAccess.correctUKDateFormat(rs.getString("DOB")),
						rs.getString("First_line_of_Address"), rs
								.getString("Second_line_of_Address"), rs
								.getString("Third_line_of_Address"), rs
								.getString("City"), rs.getString("Postcode"),
						rs.getLong("Contact_Number"), rs.getInt("NHS_number"),
						rs.getString("Allergies"), rs
								.getString("Known_Conditions"), rs
								.getString("Blood_Group"), rs.getString("Sex"),
						rs.getString("Next_of_Kin"), rs.getString("Gp_Name"),
						rs.getString("Gp_Code"), rs.getString("Notes")));
			}

			// close statement object
			stmt.close();
			// close connection
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

	}

} // Class Close
