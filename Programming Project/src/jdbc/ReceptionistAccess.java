/**
 * JDBC Code to Connect to Database from the Receptionist.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import hospital.Patient;
import hospital.Receptionist;

/**
 * Class that uses JDBC code to connect to the database table 'Patient'
 * 
 * @author James Maguire, Emma Scroggie, Tom McDonnell and Kieron Allsop
 */
public class ReceptionistAccess {

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

	/**
	 * Method to connect to the database
	 * 
	 * @return
	 */
	public static Connection con() {

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
			System.out.println("You are Connected to Database");

			/**
			 * 
			 */
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return con;
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
	 * Method for Looking up Patients and displaying details by NHS_number in the table 'Patient'
	 * 
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public void displayPatientByNHSNumber(int NHSNumber)
			throws SQLException, ParseException {
		
		Connection con = con();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql = "SELECT Title, First_Name, Middle_Name, Last_Name , DOB, Sex, First_line_of_Address, Second_line_of_Address, Third_line_of_Address, City, Postcode, Contact_Number, Allergies, Known_Conditions, Blood_Group, Gp_Name, Gp_Code, Next_of_Kin, Notes, Unix_Timestamp FROM PATIENT WHERE NHS_number = "
				+ NHSNumber;
		ResultSet rs = stmt.executeQuery(sql);

		// Move cursor to the last row.
		rs.next();

		// Retrieve by column name
		String title = rs.getString("Title");
		String firstName = rs.getString("First_Name");
		String middleName = rs.getString("Middle_Name");
		String lastName = rs.getString("Last_Name");
		String mysqlDOB = rs.getString("DOB");
		String sex = rs.getString("Sex");
		String firstLineOfAddress = rs.getString("First_line_of_Address");
		String secondLineOfAddress = rs.getString("Second_line_of_Address");
		String thirdLineOfAddress = rs.getString("Third_line_of_Address");
		String city = rs.getString("City");
		String postcode = rs.getString("Postcode");
		String contactNumber = rs.getString("Contact_Number");
		String allergies = rs.getString("Allergies");
		String knownConditions = rs.getString("Known_Conditions");
		String bloodGroup = rs.getString("Blood_Group");
		String GPName = rs.getString("Gp_Name");
		String GPCode = rs.getString("Gp_Code");
		String nextOfKin = rs.getString("Next_of_Kin");
		String rawNotes = rs.getString("Notes");
		long rawDischargeTime = rs.getLong("Unix_Timestamp");
	    
		String dischargeTime = "";
		String notes = "";
		
		if (rawDischargeTime == 0L) {
			dischargeTime = "n/a";
		} else {		
			dischargeTime = unixTimestampConversion(rawDischargeTime);		
		}
		
		if (rawNotes.isEmpty()) {
			notes = "n/a";
		} else {
			notes = rawNotes;
		}

		// Display values
		System.out.println("________________________________________________________________");
		System.out.println("Name: " + title + " " + firstName + " "+ middleName + " " + lastName);
		System.out.print("Date of Birth: " + correctUKDateFormat(mysqlDOB));
		System.out.println("\tSex : " + sex);
		System.out.println("NHS Number: " + NHSNumber);
		System.out.println("Address 1: " + firstLineOfAddress);
		System.out.println("Address 2: " + secondLineOfAddress);
		System.out.println("Address 3: " + thirdLineOfAddress);
		System.out.println("City:      " + city);
		System.out.println("Postcode:  " + postcode);
		System.out.println("Contact Number: +" + contactNumber);
		System.out.println("General Practitioner: " + GPName + "(" + GPCode + ")");
		System.out.println("Allergies: " + allergies);
		System.out.println("Conditions: "+ knownConditions);
		System.out.println("Blood Group: " + bloodGroup);
		System.out.println("Next of Kin: " + nextOfKin);
		System.out.println("Last A\'n\'E visit: " + dischargeTime);
		System.out.println("Notes from visit: " + notes);
		System.out.println("________________________________________________________________");
		con.close();
	}// lookUpPatient Close
	
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
	public void updateContactNumber(int NHSNumber, String phoneNumber)
			throws SQLException, IllegalArgumentException {

		Long contactNumber;
		
		contactNumber = Long.parseLong(phoneNumber);
		
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
	 * Method to add one patient manually to the queue having searched
	 * for them in the database using the nhs-number which is the primary 
	 * key of the patient table.
	 * 
	 * @param NHS_Number
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public static void admitPatient (int NHSNumber) throws IllegalArgumentException, Exception {
		
		try {

			Connection con = con();

			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String sql;

			sql = "SELECT * FROM PATIENT WHERE NHS_Number = " + NHSNumber;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Receptionist.patientsFromDB.addFirst(new Patient(
						rs.getString("Title"), 
						rs.getString("First_Name"), 
						rs.getString("Middle_Name"),
						rs.getString("Last_Name"),
						correctUKDateFormat(rs.getString("DOB")),
						rs.getString("First_line_of_Address"),
						rs.getString("Second_line_of_Address"), 
						rs.getString("Third_line_of_Address"), 
						rs.getString("City"),
						rs.getString("Postcode"),
						rs.getLong("Contact_Number"),
						rs.getInt("NHS_number"),
						rs.getString("Allergies"), 
						rs.getString("Known_Conditions"),
						rs.getString("Blood_Group"),
						rs.getString("Sex"),
						rs.getString("Next_of_Kin"),
						rs.getString("Gp_Name"),
						rs.getString("Gp_Code"),
						rs.getString("Notes")));
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
				Receptionist.patientsFromDB.add(new Patient(
						rs.getString("Title"), 
						rs.getString("First_Name"), 
						rs.getString("Middle_Name"),
						rs.getString("Last_Name"),
						correctUKDateFormat(rs.getString("DOB")),
						rs.getString("First_line_of_Address"),
						rs.getString("Second_line_of_Address"), 
						rs.getString("Third_line_of_Address"), 
						rs.getString("City"),
						rs.getString("Postcode"),
						rs.getLong("Contact_Number"),
						rs.getInt("NHS_number"),
						rs.getString("Allergies"), 
						rs.getString("Known_Conditions"),
						rs.getString("Blood_Group"),
						rs.getString("Sex"),
						rs.getString("Next_of_Kin"),
						rs.getString("Gp_Name"),
						rs.getString("Gp_Code"),
						rs.getString("Notes")));
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
	 * Method to convert a date String in format yyyy-MM-dd to format dd-MM-yyyy
	 * 
	 * @param mysqlDOB, date in format yyyy-MM-dd
	 * @return date in format dd-MM-yyyy
	 * @throws ParseException 
	 */
	public static String correctUKDateFormat(String mysqlDOB) throws ParseException{
		
		Date dateDOB = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(mysqlDOB);
		
		return new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(dateDOB);
	}

	/**
	 * Method to convert the seconds since the unix epoch (which is what unix_timestamp
	 * is stored as in mysql) into a human readable format.
	 * 
	 * @param rawDischargeTime
	 * @return
	 */
	public String unixTimestampConversion(long rawDischargeTime) {
		
		Date date = new Date(rawDischargeTime*1000L); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy"); 
		
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/London")); 
		
		return sdf.format(date);
	}
	
} // Class Close
