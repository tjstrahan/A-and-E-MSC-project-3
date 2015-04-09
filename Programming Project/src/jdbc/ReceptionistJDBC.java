/**
 * JDBC Code to Connect to Database from the Receptionist.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class that uses JDBC code to connect to the database table 'Patient'
 * 
 * @author James Maguire
 */
public class ReceptionistJDBC {
	
	/**
	 * Username for access to Database
	 */
	private static final String DB_USERNAME = "40038896";

	/**
	 * Use if Username is part of the Databases URL, otherwise leave blank
	 */
	private static final String URL_USERNAME = "40038896";
	
	/**
	 * Password for access to Database
	 */
	private static final String DB_PASSWORD = "SUA8746";
	
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
	 *  Instance Var for statements made into the database
	 */
	public static Statement stmt;
	
	/**
	 * Instance Var for the patient Name used in SQL Query
	 */
	public static String PATIENT_NAME;
	
	/**
	 * Instance Var for Patient Last Name used in SQL Query
	 */
	public static String PATIENT_LAST_NAME;
	
	/**
	 * Patient first name for args asked by scanner
	 */
	public static String first1;
	
	/**
	 * Patient last name for args asked by scanner
	 */
	public static String last1;

	public static Connection con () {

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
	} // Main Method Close

	/**
	 * Method for Inserting Patient Updates into the Database using table
	 * 'Patient'.
	 * 
	 * NHS_Number(Primary Key), Title, First_Name, Middle_Name, Last_Name,
	 * D.O.B, Sex, FirstLineofAddress, SecondLineofAddress, ThirdLineofAddress,
	 * City, Postcode, Contact Number, Alergies, Known Conditions, Blood Group,
	 * GPName, GPCode, Next of Kin.
	 * 
	 * @throws SQLException
	 */
	public static void admitPatient() throws SQLException {
		Statement stmt = con.createStatement();
		try {
			stmt.executeUpdate("INSERT INTO Patient VALUES('111121111','Miss','Lucinda','','Bow','1955-06-05','F','22','Adelaide St','','Belfast','BT2 8GD','442827537576','','','AB','Dr. Zeus','E1829','Long Bow')");
		} catch (SQLException sqle) {
			System.out.println("Could Not Insert Values Given To Database"
					+ sqle);
		}
	} // admitPatient Close

	/**
	 * Method for Looking up Patients in the table 'Patient'
	 * 
	 * @throws SQLException
	 */
	public static void lookUpPatient(String PATIENT_NAME,
			String PATIENT_LAST_NAME) throws SQLException {
		
		Connection con = con();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql;
		sql = "SELECT NHS_number, Title, First_Name, Middle_Name, Last_Name , DOB, Sex, First_line_of_Address, Second_line_of_Address, Third_line_of_Address, City, Postcode, Contact_Number, Allergies, Known_Condtions, Bloood_Group, Gp_Name, Gp_Code, Next_of_Kin FROM PATIENT WHERE First_Name = "
				+ PATIENT_NAME + "and Last_Name = " + PATIENT_LAST_NAME;
		ResultSet rs = stmt.executeQuery(sql);

		// Move cursor to the last row.
		rs.last();

		// STEP 5: Extract data from result set
		System.out.println("Displaying record...");
		// Retrieve by column name
		int NHS_number = rs.getInt("NHS_number");
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
		String Known_Condtions = rs.getString("Known_Condtions");
		String Bloood_Group = rs.getString("Bloood_Group");
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
				+ Known_Condtions);
		System.out.println("Blood Group: " + Bloood_Group);
		System.out.println("Next of Kin: " + Next_of_Kin);
		System.out
				.println("______________________________________________________________________");
	} // lookUpPatient Close

} // Class Close
