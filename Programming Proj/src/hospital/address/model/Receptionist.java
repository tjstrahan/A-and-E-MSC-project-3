package hospital.address.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

import javafx.beans.property.StringProperty;
import hospital.address.model.Patient;
import hospital.address.jdbc.GeneralAccess;
import hospital.address.jdbc.ReceptionistAccess;

public class Receptionist extends Staff {

	public static LinkedList<Patient> patientsFromDB = new LinkedList<Patient>();
	static Scanner scanner = new Scanner(System.in);
	static ReceptionistAccess rA = new ReceptionistAccess();
	
	private static final int NHS_NUMBER_MAX = 999999999;

	/**
	 * Constant for NHS number minimum value
	 */
	private static final int NHS_NUMBER_MIN = 100000000;

	/**
	 * default constructor for Receptionist
	 */
	

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
			String postcode, int staffID, long contactNumber, int loginID,
			String password) throws IllegalArgumentException, Exception {
		super();

	}

	public Receptionist() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Main menu method for recep that can either be kepy or taken away
	 * dependant on UI but works to test methods
	 */
	public void RecepCommands() {

		// declared int var to hold input from user
		int input;
		System.out.println("Please make a selection");
		System.out.println("1.  Change address line one");
		System.out.println("2.  Change address line two");
		System.out.println("3.  Change address line three");
		System.out.println("4.  Update city");
		System.out.println("5.  Update Postcode");
		System.out.println("6.  Update Contact number");
		System.out.println("7.  Update allergies");
		System.out.println("8.  Add allergies");
		System.out.println("9.  Update conditions");
		System.out.println("10. Add conditions");
		System.out.println("11. Update next of kin");
		System.out.println("12. Look up patient");

		input = scanner.nextInt();

		// switch statement dependant on input to load correct method
		switch (input) {
		case 1:
			updateFirstLinePatientAddress();
			break;
		case 2:
			updateSecondLineOfPatientAddress();
			break;
		case 3:
			updateThirdLineOfPatientAddress();
			break;
		case 4:
			updatePatientCity();
			break;
		case 5:
			updatePatientPostcode();
			break;
		case 6:
			updatePatientContactNumber();
			break;
		case 7:
			addPatientFirstAllergies();
			break;
		case 8:
			addPatientMoreAllergies();
			break;
		case 9:
			addFirstKnownPatientConditions();
			break;
		case 10:
			addMoreKnownPatientConditions();
			break;
		case 11:
			updateNextOfKin();
			break;
		case 12 :
			lookUpPatient();

		}

	}

	/**
	 * method which once called will have the ability to look up a patient in
	 * the database
	 * 
	 * @param lastName
	 * @param firstName
	 * @param dob
	 */
	public void lookUpPatient() {

		GeneralAccess gA = new GeneralAccess();

		String firstName, lastName, dob;
		// PRint out look up patient
		System.out.println("Look up Patient");

		do {
			System.out.print("Please enter first name : ");
			firstName = scanner.next();
		} while (firstName == null);

		do {
			System.out.print("Please enter last name : ");
			lastName = scanner.next();
		} while (lastName == null);

		System.out.println("Enter Patient DOB in the format dd-MM-yyyy");
		dob = scanner.next();

		// surrounded by a try catch block, send to the loopUpPatient class the
		// first and last names typed in
		try {
			gA.displayPatientByNHSNumber(ReceptionistAccess
					.lookUpPatientNHSNumber(firstName, lastName,
							correctMYSQLDateFormat(dob)));

			// if this fails catch the SQLException and print the stack trace
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to allow the Receptionist to admit a patient
	 */
	public void admitPatient(int nhsNumber) {
		//do {
			//System.out.print("Please enter nhsNumber : ");
			//nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		//} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);

		try {
			ReceptionistAccess.admitPatient(nhsNumber);
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

	public static void updateFirstLinePatientAddress() {
		
		int nhsNumber;
		String addressLineOne;

		System.out.print("Please enter address line one : ");
		addressLineOne = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);

		try {
			rA.updateFirstLineOfAddress(nhsNumber, addressLineOne);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateSecondLineOfPatientAddress() {
		
		int nhsNumber;
		String addressLineTwo;

		System.out.print("Please enter address line two : ");
		addressLineTwo = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);

		try {
			rA.updateSecondLineOfAddress(nhsNumber, addressLineTwo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateThirdLineOfPatientAddress() {
		
		int nhsNumber;
		String addressLineThree;

		System.out.print("Please enter address line three : ");
		addressLineThree = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);

		try {
			rA.updateThirdLineOfAddress(nhsNumber, addressLineThree);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updatePatientCity() {
		
		int nhsNumber;
		String city;

		System.out.print("Please enter city : ");
		city = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);

		try {
			rA.updateCity(nhsNumber, city);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updatePatientPostcode() {
		
		int nhsNumber;
		String postCode;

		System.out.print("Please enter postcode : ");
		postCode = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);
		try {
			rA.updatePostcode(nhsNumber, postCode);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updatePatientContactNumber() {
		
		int nhsNumber;
		long contactNumber = 0;

		//Long contact = contactNumber;

		System.out.print("Please enter contact number : ");
		contactNumber = scanner.nextLong();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);

		System.out.print("Please enter contact number : ");
		contactNumber = scanner.nextLong();

		try {
			rA.updateContactNumber(nhsNumber, contactNumber);
		} catch (IllegalArgumentException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addPatientFirstAllergies() {
		
		int nhsNumber;
		String allergies;

		System.out.print("Please enter allergies : ");
		allergies = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);
		try {
			rA.addFirstAllergies(nhsNumber, allergies);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addPatientMoreAllergies() {
		
		int nhsNumber;
		String allergies;

		System.out.print("Please enter allergies : ");
		allergies = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);
		try {
			rA.addMoreAllergies(nhsNumber, allergies);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addFirstKnownPatientConditions() {
		
		int nhsNumber;
		String knownConditions;

		System.out.print("Please enter any known conditions : ");
		knownConditions = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);
		try {
			rA.addFirstKnownConditions(nhsNumber, knownConditions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addMoreKnownPatientConditions() {
		
		int nhsNumber;
		String knownConditions;

		System.out.print("Please enter any known conditions : ");
		knownConditions = scanner.next();

		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);
		try {
			rA.addMoreKnownConditions(nhsNumber, knownConditions);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateNextOfKin() {
		
		int nhsNumber;
		String nextOfKin = null;

		System.out.print("Please enter next of kin : ");
		nextOfKin = scanner.next();
		do {
			System.out.print("Please enter nhsNumber : ");
			nhsNumber = scanner.nextInt();
			// Check that the NHS number is nine figures long
		} while (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX);
		try {
			rA.updateNextOfKin(nhsNumber, nextOfKin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
