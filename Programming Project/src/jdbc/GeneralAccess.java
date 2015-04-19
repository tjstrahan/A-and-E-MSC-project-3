package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class GeneralAccess {

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
}
