package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class that uses JDBC code to connect to the database table 'Patient'
 * update the rows in patient table 
 * inserts records into a table with has the date and an autoincremented id
 * Emma
 */

public class DoctorAccess {
	/**
	 * Username for access to Database
	 */
	private static final String DB_USERNAME = "9611177";

	/**
	 * Use if Username is part of the Databases URL, otherwise leave blank
	 */
	private static final String URL_USERNAME = "";
	/**
	 * Password for access to Database
	 */
	private static final String DB_PASSWORD = "SZN9396";
	/**
	 * Main body of URL of Database, eg //web2.eeecs.qub.ac.uk/ or
	 * thin:@db.yale.edu:univdb
	 */
	public static final String WEB_ADDRESS = "//web2.eeecs.qub.ac.uk/9611177";
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
     * 
     */
	public static PreparedStatement pstmt;
	/**
	 * Instance Var for the nhs number used in SQL Query
	 */
	public static long NHS_Number;
	/**
	 * Instance Var for the patient Name to be updated in sql query
	 */
	public static String firstName;
	/**
     * 
     */
	public static String middleName;
	/**
     * 
     */
	public static String lastName;
	/**
     * 
     */
	public static String firstLineOfAddress;
	/**
     * 
     */
	public static String secondLineOfAddress;
	/**
     * 
     */
	public static String thirdLineOfAddress;
	/**
    *  
    */
	public static String city;
	/**
     * 
     */
	public static String Postcode;
	/**
     * 
     */
	public static int contactNumber;
	/**
     * 
     */
	public static String allergies;
	/**
	 * 
	 */
	public static String knownConditions;
	/**
     * 
     */
	public static String gpName;
	/**
     * 
     */
	public static String gpCode;
	/**
     * 
     */
	public static String nextOfKin;
	/**
	 * 
	 */
	public static String symptoms;
	
	

	public static void mainMethod(){
		/**try{
		
		/**
	        // searching by nhs number
			System.out.println("Look up Patient");
			System.out.println("Enter NHS number");
			Scanner scanner = new Scanner(System.in);
			NHS_Number = scanner.nextLong();
			scanner.close();
			searchPatientByNHS_Number(NHS_Number);
				

			
		//edit the details of a patient
			System.out.println("Look up Patient");
			System.out.println("Enter NHS number");
			Scanner scanner = new Scanner(System.in);
			NHS_Number = scanner.nextLong();
			System.out.println("Enter First Name to edit");
			firstName = scanner.next();
			scanner.close();
		
		
			// enter nhs number and create row for the 
			System.out.println("Enter NHS number");
			Scanner scanner = new Scanner(System.in);
			NHS_Number = scanner.nextLong();
			System.out.println("Enter symptoms");
			symptoms = scanner.next();
			scanner.close();	
			insertNewRecord(NHS_Number, symptoms);
			
			
			 
			} catch (SQLException e) {
					e.printStackTrace();
			}*/
		
	} // Main Method Close

	/**
	 * open DB connection method
	 * throws SQLException
	 * @returns connection
	 */
	public static Connection getConnection() throws SQLException {
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

	/**
	 * Method for Looking up Patients and displaying details by NHS_number in the table 'Patient'
	 * 
	 * @throws SQLException
	 */
	public static void searchPatientAndDisplayByNHS_Number(long NHS_Number)
			throws SQLException {
		
		Connection con = getConnection();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql = "SELECT NHS_number, Title, First_Name, Middle_Name, Last_Name , DOB, Sex, First_line_of_Address, Second_line_of_Address, Third_line_of_Address, City, Postcode, Contact_Number, Allergies, Known_Conditions, Blood_Group, Gp_Name, Gp_Code, Next_of_Kin FROM PATIENT WHERE NHS_number = "
				+ NHS_Number;
		ResultSet rs = stmt.executeQuery(sql);

		// Move cursor to the last row.
		rs.next();

		// STEP 5: Extract data from result set
		System.out.println("Displaying record...");
		// Retrieve by column name
		long NHS_number = rs.getLong("NHS_number");
		String Gp_Code = rs.getString("Gp_Code");
		String Title = rs.getString("Title");
		String First_Name = rs.getString("First_Name");
		String Middle_Name = rs.getString("Middle_Name");
		String Last_Name = rs.getString("Last_Name");
		String DOB = rs.getString("DOB");
		String Sex = rs.getString("Sex");
		String First_line_of_Address = rs.getString("First_line_of_Address");
		String Second_line_of_Address = rs.getString("Second_line_of_Address");
		String Third_line_of_Address = rs.getString("Third_line_of_Address");
		String City = rs.getString("City");
		String Postcode = rs.getString("Postcode");
		String Contact_Number = rs.getString("Contact_Number");
		String Allergies = rs.getString("Allergies");
		String Known_Conditions = rs.getString("Known_Conditions");
		String Blood_Group = rs.getString("Blood_Group");
		String Gp_Name = rs.getString("Gp_Name");
		String Next_of_Kin = rs.getString("Next_of_Kin");

		// Display values
		System.out
				.println("________________________________________________________________");
		System.out.print("NHS Number: " + NHS_number);
		System.out.println("\nSex : " + Sex);
		System.out.println("Name: " + Title + " " + First_Name + " "
				+ Middle_Name + " " + Last_Name);
		System.out.println("Address " + First_line_of_Address + " "
				+ Second_line_of_Address + " " + Third_line_of_Address);
		System.out.println("        " + City);
		System.out.println("        " + Postcode);
		System.out.println("Contact Number: " + Contact_Number);
		System.out.println("GP : " + Gp_Name + "(" + Gp_Code + ")");
		System.out.println("Birth " + DOB);
		System.out.println("Allergies/Conditions: " + Allergies + " "
				+ Known_Conditions);
		System.out.println("Blood Group: " + Blood_Group);
		System.out.println("Next of Kin: " + Next_of_Kin);
		System.out
				.println("______________________________________________________________________");
		con.close();
	}// lookUpPatient Close

	/**
	 * prepared statement for updating the first name with a valid NHS_Number
	 * 
	 * @param NHS_Number
	 * @param firstName
	 */
	public static void updateFirstName(long NHS_Number, String firstName) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con.prepareStatement("UPDATE Patient SET First_Name = ? WHERE NHS_number = ?");
			pstmt.setString(1, firstName);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("error in the update first name method");
		}
	}

	/*
	 * public static void updateFirstName(int NHS_Number, String firstName)
	 * throws SQLException {
	 * 
	 * stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY); String sql; sql =
	 * "UPDATE Patient SET First_Name ='" + firstName+
	 * "'WHERE NHS_number = "+NHS_Number;
	 * 
	 * stmt.executeUpdate(sql); }
	 */
	/**
	 * Update the Middle name where the NHS_Number is valid
	 * 
	 * @param NHS_Number
	 * @param middleName
	 */
	public void updateMiddleName(int NHS_Number, String middleName) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Middle_Name = ? WHERE NHS_number = ?");
			pstmt.setString(1, middleName);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(" update middle name error");
		}
	}

	/*
	 * public static void updateMiddleName(int NHS_Number, String middleName)
	 * throws SQLException {
	 * 
	 * stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY); String sql; sql =
	 * "UPDATE Patient SET Middle_Name ='" + middleName +
	 * "'WHERE NHS_number = "+NHS_Number;
	 * 
	 * stmt.executeUpdate(sql); }
	 */

	/**
	 * method to update the last name 
	 * @param NHS_Number
	 * @param lastName
	 * @throws SQLException
	 */
	public void updateLastName(long NHS_Number, String lastName) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Last_Name = ? WHERE NHS_number = ?");
			pstmt.setString(1, lastName);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update last name method");
		}
	}

	/*
	 * public static void updateLastName(int NHS_Number, String lastName) throws
	 * SQLException {
	 * 
	 * stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY); String sql; sql =
	 * "UPDATE Patient SET Last_Name ='" + lastName+
	 * "'WHERE NHS_number = "+NHS_Number;
	 * 
	 * stmt.executeUpdate(sql); }
	 */
	
	
	/**
	 * update the first line of the address
	 * @param NHS_Number
	 * @param firstLineOfAddress
	 */
	public void updateFirstLineOfAddress(long NHS_Number,
			String firstLineOfAddress) throws SQLException{

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET  First_Line_Of_Address = ? WHERE NHS_number = ?");
			pstmt.setString(1, firstLineOfAddress);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update first line of address");
		}
	}

	/*
	 * public static void updateFirstLineOfAddress(int NHS_Number, String
	 * firstLineOfAddress) throws SQLException {
	 * 
	 * stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY); String sql; sql =
	 * "UPDATE Patient SET First_Line_Of_Address ='" + firstLineOfAddress+
	 * "'WHERE NHS_number = "+NHS_Number;
	 * 
	 * stmt.executeUpdate(sql); }
	 */
	
	/**
	 * Method to update the 2nd line of address
	 * @param NHS_Number
	 * @param secondLineOfAddress
	 * @throws SQLException
	 */
	public void updateSecondLineOfAddress(long NHS_Number,
			String secondLineOfAddress) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET   Second_Line_Of_Address = ? WHERE NHS_number = ?");
			pstmt.setString(1, secondLineOfAddress);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update second line of address");
		}
	}

	/*
	public static void updateSecondLineOfAddress(int NHS_Number,
			String secondLineOfAddress) throws SQLException {

		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql = "UPDATE Patient SET Second_Line_Of_Address ='"
				+ secondLineOfAddress + "'WHERE NHS_number = " + NHS_Number;

		stmt.executeUpdate(sql);
	}
*/
	/**
	 * method to update 3rd line address
	 * @param NHS_Number
	 * @param thirdLineOfAddress
	 * @throws SQLException
	 */
	public void updateThirdLineOfAddress(long NHS_Number,
			String thirdLineOfAddress) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Third_Line_Of_Address  = ? WHERE NHS_number = ?");
			pstmt.setString(1, thirdLineOfAddress);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update third line of address");
		}
	}
	
	/*
	public static void updateThirdLineOfAddress(int NHS_Number,
			String thirdLineOfAddress) throws SQLException {

		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql = "UPDATE Patient SET Third_Line_Of_Address ='"
				+ thirdLineOfAddress + "'WHERE NHS_number = " + NHS_Number;

		stmt.executeUpdate(sql);
	}
*/
	/**
	 * Method to update the city
	 * @param NHS_Number
	 * @param city
	 * @throws SQLException
	 */
	public void updateCity(long NHS_Number, String city)
			throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET City  = ? WHERE NHS_number = ?");
			pstmt.setString(1, city);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update city");
		}
	}
	
	/*
	public static void updateCity(int NHS_Number, String city)
			throws SQLException {

		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql = "UPDATE Patient SET City ='" + city + "'WHERE NHS_number = "
				+ NHS_Number;

		stmt.executeUpdate(sql);
	}
*/
	
	/**
	 * Method to update the postcode
	 * @param NHS_Number
	 * @param postcode
	 * @throws SQLException
	 */
	public void updatePostcode(long NHS_Number, String postcode)
			throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Postcode  = ? WHERE NHS_number = ?");
			pstmt.setString(1, postcode);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update postcode");
		}
	}
	
	
	/*
	public static void updatePostcode(int NHS_Number, String postcode)
			throws SQLException {
		// create statement
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		// Write statement
		sql = "UPDATE Patient SET Postcode ='" + postcode
				+ "'WHERE NHS_number = " + NHS_Number;
		// Execute update statement
		stmt.executeUpdate(sql);
	}
*/
	/**
	 * method to update the Contact number
	 * @param NHS_Number
	 * @param contactNumber
	 * @throws SQLException
	 */
	public void updateContactNumber(long NHS_Number, int contactNumber) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Contact_Number  = ? WHERE NHS_number = ?");
			pstmt.setInt(1, contactNumber);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update contact number");
		}
	}
	

	/*
	public static void updateContactNumber(int NHS_Number, int contactNumber)
			throws SQLException {
		// create statement
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		// Write statement
		sql = "UPDATE Patient SET Contact_Number = " + contactNumber
				+ " WHERE NHS_number = " + NHS_Number;
		// Execute update statement
		stmt.executeUpdate(sql);
	}
*/
	/**
	 * method to update patient Allergies
	 * @param NHS_Number
	 * @param allergies
	 * @throws SQLException
	 */
	public void updateAllergies(long NHS_Number, String allergies) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Allergies  = ? WHERE NHS_number = ?");
			pstmt.setString(1, allergies);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update allergies");
		}
	}
	
	
	/*
	public static void updateAllergies(int NHS_Number, String allergies)
			throws SQLException {
		// create statement
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		// Write statement
		sql = "UPDATE Patient SET Allergies ='" + allergies
				+ "'WHERE NHS_number = " + NHS_Number;
		// Execute update statement
		stmt.executeUpdate(sql);
	}
*/
	
	/**
	 * Method to update the known conditions
	 * @param NHS_Number
	 * @param knownCondtions
	 * @throws SQLException
	 */
	public void updateKnownConditions(long NHS_Number,
			String knownCondtions) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Known_Conditions  = ? WHERE NHS_number = ?");
			pstmt.setString(1, knownCondtions);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update known Condtions");
		}
	}
	
	/*
	public static void updateKnownConditions(int NHS_Number,
			String knownCondtions) throws SQLException {
		// create statement
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		// Write statement
		sql = "UPDATE Patient SET Known_Conditions ='" + knownConditions
				+ "'WHERE NHS_number = " + NHS_Number;
		// Execute update statement
		stmt.executeUpdate(sql);
	}
*/
	/**
	 * method to update the gp name 
	 * @param NHS_Number
	 * @param gpName
	 * @throws SQLException
	 */
	public void updateGpName(long NHS_Number, String gpName) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Gp_Name  = ? WHERE NHS_number = ?");
			pstmt.setString(1, gpName);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update known Condtions");
		}
	}
	/*
	
	public static void updateGpName(int NHS_Number, String gpName)
			throws SQLException {
		// create statement
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		// Write statement
		sql = "UPDATE Patient SET Gp_Name ='" + gpName + "'WHERE NHS_number = "
				+ NHS_Number;
		// Execute update statement
		stmt.executeUpdate(sql);
	}
*/
	
	/**
	 * method to update the gpcode
	 * @param NHS_Number
	 * @param gpCode
	 * @throws SQLException
	 */
	public void updateGpCode(long NHS_Number, String gpCode) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Gp_Code  = ? WHERE NHS_number = ?");
			pstmt.setString(1, gpCode);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update known Condtions");
		}
	}
	
	/*
	public static void updateGpCode(int NHS_Number, String gpCode)
			throws SQLException {
		// create statement
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		// Write statement
		sql = "UPDATE Patient SET Gp_Code ='" + gpCode + "'WHERE NHS_number = "
				+ NHS_Number;
		// Execute update statement
		stmt.executeUpdate(sql);
	}
*/
	/**
	 * method to update the next of kin
	 * @param NHS_Number
	 * @param nextOfKin
	 * @throws SQLException
	 */
	public void updateNextOfKin(long NHS_Number, String nextOfKin) throws SQLException {

		Connection con = getConnection();
		try {
			pstmt = con
					.prepareStatement("UPDATE Patient SET Next_Of_Kin  = ? WHERE NHS_number = ?");
			pstmt.setString(1, nextOfKin);
			pstmt.setLong(2, NHS_Number);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("update known Condtions");
		}
	}
	
	/*
	public static void updateNextOfKin(int NHS_Number, String nextOfKin)
			throws SQLException {
		// create statement
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		// Write statement
		sql = "UPDATE Patient SET Next_Of_Kin ='" + nextOfKin
				+ "'WHERE NHS_number = " + NHS_Number;
		// Execute update statement
		stmt.executeUpdate(sql);
	}
*/
	
	/**
	 * Methdod that inserts a row in a table which will document the symptoms of the patient,
	 * The id auto increments and the date and time is taken at the statements creation. 
	 * @param NHS_Number
	 * @param symptoms
	 * @throws SQLException
	 */
public static void insertNewRecord(long NHS_Number, String symptoms) throws SQLException{
	Connection con = getConnection();
	try{
		
		pstmt= con.prepareStatement("INSERT INTO AandE_Record values (default,?,?,default)");
		pstmt.setLong(1, NHS_Number);
		pstmt.setString(2, symptoms);	
		pstmt.executeUpdate();
		pstmt.close();
		con.close();
		
	}catch( SQLException e){
		e.printStackTrace();
		
	}
		
	}
	
	
}// end of class


