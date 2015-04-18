package hospital;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

import hospital.Patient;
import jdbc.ReceptionistAccess;

public class Receptionist extends Staff {

	public static LinkedList<Patient> patientsFromDB = new LinkedList<Patient>();

	/**
	 * default constructor for Receptionist
	 */
	public Receptionist() {

	}

	/**
	 * constructor with arguments
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
	 * @param contactNumber
	 * @param loginID
	 * @param password
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Receptionist(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int staffID, int contactNumber, int loginID,
			String password) throws IllegalArgumentException, Exception {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, staffID, contactNumber, loginID, password);

	}

	/**
	 * method which once called will have the ability to look up a patient in
	 * the database
	 */
	public void lookUpPatient() {

		ReceptionistAccess rA = new ReceptionistAccess();

		// PRint out look up patient
		System.out.println("Look up Patient");
		// declare the scanner
		Scanner scanner = new Scanner(System.in);

		try {
		// ask the user to type in their first and last name of the person they
		// seek
		System.out.println("Enter Patient Foreame.");
		String foreName = scanner.next();
		System.out.println("Enter Patient Surname.");
		String surName = scanner.next();
		System.out.println("Enter Patient DOB in the format dd-MM-yyyy.");
		String rawDOB = scanner.next();
		scanner.close();

		// surrounded by a try catch block, send to the loopUpPatient class the
		// first and last names typed in
		
			rA.displayPatientByNHSNumber(ReceptionistAccess
					.lookUpPatientNHSNumber(foreName, surName,
							correctMYSQLDateFormat(rawDOB)));

			// if this fails catch the SQLException and print the stack trace
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to allow the Receptionist to admit a patient
	 */
	public void admitPatient(int NHSNumber) {

		try {
			ReceptionistAccess.admitPatient(NHSNumber);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to admit all patients in the database - method solely for
	 * demonstrating the queue
	 */
	public void admitAllPatients() {

		try {
			ReceptionistAccess.getAllPatients();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to convert a date String in format dd-MM-yyyy to format yyyy-MM-dd
	 * 
	 * @param inputDOB
	 *            , date in format dd-MM-yyyy
	 * @return date in format yyyy-MM-dd
	 * @throws ParseException
	 */
	public String correctMYSQLDateFormat(String inputDOB) throws ParseException {

		Date dateDOB = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
				.parse(inputDOB);

		return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
				.format(dateDOB);
	}

}
