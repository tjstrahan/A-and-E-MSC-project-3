package hospital;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

import hospital.Patient;
import jdbc.ReceptionistJDBC;

public class Receptionist extends Staff {

	/**
	 * LinkedList to hold patients that are being sent to triage
	 */
	public static LinkedList<Patient> patientsFromDB = new LinkedList<Patient>();
	
	
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
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public Receptionist(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int staffID, long mobileNumber, int loginID,
			String password) throws IllegalArgumentException, Exception  {
		
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, staffID, mobileNumber, loginID, password);

	}

	/**
	 * method which once called will have the ability to look up a patient in
	 * the database
	 */
	public void lookUpPatient() {

		
		// PRint out look up patient
		System.out.println("Look up Patient");
		// declare the scanner
		Scanner scanner = new Scanner(System.in);

		// ask the user to type in their first and last name of the person they
		// seek
		System.out.println("Enter Patient Name. First and Last");
		String first1 = scanner.next();
		String last1 = scanner.next();
		scanner.close();
		// surrounded by a try catch block, send to the loopUpPatient class the
		// first and last names typed in
		try {
			ReceptionistJDBC.lookUpPatient("'" + first1 + "'", "'" + last1 + "'");
			
			// if this fails catch the SQLException and print the stack trace
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * Method to allow the receptionist to admit a patient
	 */
	public void admitPatient() {

		// call the method surrounded by a try catch block and catch the SQLException if it is thrown
		try {
			ReceptionistJDBC.admitPatient();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Method to admit all patients in the database - method solely for demonstrating the queue
	 */
	public void admitAllPatients() {
		
		try {
			ReceptionistJDBC.getAllPatients();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
