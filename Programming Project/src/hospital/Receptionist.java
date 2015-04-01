package hospital;

import java.sql.SQLException;
import java.util.Scanner;

import JDBC.ConnectToDatabase;

public class Receptionist extends Staff {

	/**
	 * default constructor for Receptionist
	 */
	public Receptionist() {

	}

	/**
	 * constructor with arguemnts
	 * 
	 * @param title
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param dateOfBirth
	 * @param addressLineOne
	 * @param addressLineTwo
	 * @param addressLineThree
	 * @param city
	 * @param postcode
	 * @param staffID
	 * @param mobileNumber
	 * @param loginID
	 * @param password
	 */
	public Receptionist(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int staffID, long mobileNumber, int loginID,
			String password) {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, staffID, mobileNumber, loginID, password);

	}

	/**
	 * method which once called will have the ability to look up a patient in
	 * the database
	 */
	@SuppressWarnings("static-access")
	public void lookUpPatient() {
		
		JDBC.ConnectToDatabase lookup = new ConnectToDatabase();
		
		System.out.println("Look up Patient");
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter Patient Name. First and Last");
		String first1 = scanner.next();
		String last1 = scanner.next();
		 
		try {
			lookup.lookUpPatient("'" + first1 + "'", "'" + last1 + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to allow the receptionist to admit a patient
	 */
	public void admitPatient() {
		
		

	}
}
