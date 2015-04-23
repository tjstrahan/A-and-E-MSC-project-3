package hospital;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class QueueOperations {

	/**
	 * Constant for NHS number maximum value
	 */
	private static final int NHS_NUMBER_MAX = 999999999;

	/**
	 * Constant for NHS number minimum value
	 */
	private static final int NHS_NUMBER_MIN = 100000000;

	/**
	 * Static instantiation of the scanner class
	 */
	static Scanner scanner = new Scanner(System.in);

	/**
	 * Front end of Queue Operations class - menu system to launch the varioues
	 * search methods
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void QueueCommmands() throws IllegalArgumentException, Exception {

		int input = 0;

		boolean showMenu = true;

		do {
			
			System.out.println("Searches available");
			System.out
					.println("1.  Display NHS Number of a patient in Treatment Room");
			System.out
					.println("2.  Display NHS Number of patient being treated by On Call Team");
			System.out
					.println("3.  Search Treatment Rooms for Specific Triage Category");
			System.out
					.println("4.  Search Waiting List for Specific Triage Category");
			System.out.println("5.  Search Treatment Room by Patient Name");
			System.out.println("6.  Search Waiting List by Patient Name ");
			System.out.println("7.  Search Treatment Room by NHS Number");
			System.out.println("8.  Search Waiting List by NHS Number");
			System.out.println("9.  Exit");
			
			input = scanner.nextInt();
		
			switch (input) {
	
			case 1:
				getNHSNumberOfTreatmentRoomPatient();
				break;
			case 2:
				getNHSNumberOfOnCallPatient();
				break;
			case 3:
				searchTreatmentRoomsForTriageCategory();
				break;
			case 4:
				searchWaitingListForTriageCategory();
				break;
			case 5:
				searchTreatmentRoomsByName();
				break;
			case 6:
				searchWaitingListByName();
				break;
			case 7:
				searchTreatmentRoomByNHSNumber();
				break;
			case 8:
				searchWaitingListByNHSNumber();
				break;
			case 9:
				showMenu = false;
				break;
			default:
				throw new IllegalArgumentException("Invalid input");
			}
			
		} while (showMenu);

	}

	/**
	 * Method to get the NHS number of a patient in a treatment room
	 * 
	 * 
	 * @return
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public int getNHSNumberOfTreatmentRoomPatient()
			throws IllegalArgumentException, Exception {

		int NHSNumber = 0;
		int treatmentRoom = 0;
		System.out.println("Enter Treatment Room");
		treatmentRoom = scanner.nextInt();

		if (treatmentRoom > 0 && treatmentRoom <= TheQueue.NUMBER_OF_TREATMENT_ROOMS) {
			int treatmentRoomElement = treatmentRoom - 1;

			if (TheQueue.TreatmentRoom.get(treatmentRoomElement) == null) {
				throw new IllegalArgumentException("Treatment Room Empty");
			} else {

				NHSNumber = TheQueue.TreatmentRoom.get(treatmentRoomElement)
						.getNhsNumber();
			}
		} else {
			throw new Exception("Invalid Treatment Room");
		}
		return NHSNumber;
	}

	/**
	 * Method to get the NHS number of a patient in a treatment room
	 * 
	 * @param treatmentRoom
	 * @return
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public int getNHSNumberOfTreatmentRoomPatient(int treatmentRoom)
			throws IllegalArgumentException, Exception {

		int NHSNumber = 0;
		
		if (treatmentRoom > 0 && treatmentRoom <= TheQueue.NUMBER_OF_TREATMENT_ROOMS) {
			int treatmentRoomElement = treatmentRoom - 1;

			if (TheQueue.TreatmentRoom.get(treatmentRoomElement) == null) {
				throw new IllegalArgumentException("Treatment Room Empty");
			} else {

				NHSNumber = TheQueue.TreatmentRoom.get(treatmentRoomElement)
						.getNhsNumber();
			}
		} else {
			throw new Exception("Invalid Treatment Room");
		}
		return NHSNumber;
	}

	
	/**
	 * Method to get the NHS number of a patient being treated by on call team
	 * 
	 * @param treatmentRoom
	 * @return
	 * @throws Exception
	 */
	public int getNHSNumberOfOnCallPatient() throws Exception {

		int NHSNumber = 0;
		if (TheQueue.onCallInSitu) {
			NHSNumber = TheQueue.OnCallTeam.get(0).getNhsNumber();
		} else {
			throw new Exception("On Call Team not treating anyone");
		}
		return NHSNumber;
	}

	/**
	 * Method to take input from user and search the Treatment Rooms for
	 * patients of a particular triage category
	 * 
	 * @throws Exception
	 */
	public void searchTreatmentRoomsForTriageCategory() throws Exception {

		int category = 0;
		System.out.println("Enter Triage Category");
		category = scanner.nextInt();

		if (category > 0 && category < 5) {
			System.out.println(searchForTriageCategory(TheQueue.TreatmentRoom,
					category));
		} else {
			throw new Exception("Invalid Triage Category");
		}
	}

	/**
	 * Method to take input from user and search the Waiting List for patients
	 * of a particular triage category
	 * 
	 * @throws Exception
	 */
	public void searchWaitingListForTriageCategory() throws Exception {

		int category = 0;
		System.out.println("Enter Triage Category");
		category = scanner.nextInt();

		if (category > 0 && category < 5) {
			System.out.println(searchForTriageCategory(TheQueue.WaitingList,
					category));
		} else {
			throw new Exception("Invalid Triage Category");
		}
	}

	/**
	 * Method to search a list of patients for a specific Triage category and
	 * display to screen
	 * 
	 * @param patient
	 *            , a <code>List</code> of Patient objects
	 * @param category
	 *            , a <code>String</code> stating the Triage Category to be
	 *            searched for
	 * @return a <code>LinkedList</code> containing patients who match the
	 *         search criteria
	 * @throws IllegalArgumentException
	 *             , thrown if an invalid triage category is passed to this
	 *             method
	 */
	public static LinkedList<Patient> searchForTriageCategory(
			List<Patient> patient, int category)
			throws IllegalArgumentException {

		// LinkedList to hold the results
		LinkedList<Patient> matchedPatients = new LinkedList<Patient>();

		// To allow for display of "No Matches" dialog if no patients meet the
		// searched for criteria
		boolean matches = false;

		// Check to see if the triage category that is being searched for is
		// valid
		if (category < 1 || category > 4) {

			// If it is invalid throw and exception
			throw new IllegalArgumentException("Invalid Triage Category");

			// If valid, ie not invalid
		} else {

			// Enhanced For Loop to search for patient
			for (Patient patientWaiting : patient) {

				// If categories match
				if (patientWaiting.getTriageNumber() == category) {
					matchedPatients.add(patientWaiting);
					matches = true;
				}
			}

			// If no matches
			if (!matches) {
				System.out.println("No Matches");
			}
		}
		return matchedPatients;

	}

	/**
	 * Method to take user input for patient forename and surname and search the
	 * treatment rooms
	 */
	public void searchTreatmentRoomsByName() {

		String forename = "";
		String surname = "";
		System.out.println("Please enter forename");
		forename = scanner.next();
		System.out.println("Please enter surname");
		surname = scanner.next();

		System.out.println(searchForPatientByName(TheQueue.TreatmentRoom,
				forename, surname));
	}

	/**
	 * Method to take user input for patient forename and surname and search the
	 * waiting list
	 */
	public void searchWaitingListByName() {

		String forename = "";
		String surname = "";
		System.out.println("Please enter forename");
		forename = scanner.next();
		System.out.println("Please enter surname");
		surname = scanner.next();

		System.out.println(searchForPatientByName(TheQueue.WaitingList,
				forename, surname));
	}

	/**
	 * Method to search a list of patients by patient forename and surname and
	 * display to screen
	 * 
	 * @param patient
	 *            , a <code>List</code> of Patient objects
	 * @param forename
	 *            , a <code>String</code> stating the forename to be searched
	 *            for in the list
	 * @param surname
	 *            , a <code>String</code> stating the surname to be searched for
	 *            in the list
	 * @return a <code>LinkedList</code> containing patients who match the
	 *         search criteria - done in this manner as it is possible for two
	 *         people to have the same name
	 * 
	 */
	public static LinkedList<Patient> searchForPatientByName(
			List<Patient> patient, String forename, String surname) {

		// LinkedList to hold the results
		LinkedList<Patient> matchedPatients = new LinkedList<Patient>();

		// To allow for display of "No Matches" dialog if no patients meet the
		// searched for criteria
		boolean matches = false;

		// Enhanced For Loop to search for patient
		for (Patient patientWaiting : patient) {

			// If the forename and surname ad patient to result set
			if (patientWaiting.getFirstName().equalsIgnoreCase(forename)
					&& patientWaiting.getLastName().equalsIgnoreCase(surname)) {
				matchedPatients.add(patientWaiting);
				matches = true;
			}
		}

		// If no matches
		if (!matches) {
			System.out.println("No Matches");
		}
		return matchedPatients;
	}

	/**
	 * Method to take input from the user and search Treatment Room by NHS
	 * number
	 * 
	 * @throws Exception
	 */
	public void searchTreatmentRoomByNHSNumber() throws Exception {

		int NHSNumber = 0;
		System.out.println("Enter NHS Number");
		NHSNumber = scanner.nextInt();
		if (NHSNumber >= NHS_NUMBER_MIN && NHSNumber <= NHS_NUMBER_MAX) {
			System.out.println(searchByNHSNumber(TheQueue.TreatmentRoom,
					NHSNumber));
		} else {
			throw new Exception();
		}
	}

	/**
	 * Method to take input from the user and search Waiting List by NHS number
	 * 
	 * @throws Exception
	 */
	public void searchWaitingListByNHSNumber() throws Exception {

		int NHSNumber = 0;
		System.out.println("Enter NHS Number");
		NHSNumber = scanner.nextInt();
		if (NHSNumber >= NHS_NUMBER_MIN && NHSNumber <= NHS_NUMBER_MAX) {
			System.out.println(searchByNHSNumber(TheQueue.WaitingList,
					NHSNumber));
		} else {
			throw new Exception();
		}
	}

	/**
	 * Method to search a list of patients by NHS number and display to screen
	 * 
	 * @param patient
	 *            , a <code>List</code> of Patient objects
	 * @param numberBeingSearchedFor
	 *            , an <code>int</code>, the NHS number being searched for
	 * @return a <code>Patient</code>, the patient who match the search criteria
	 */
	public static LinkedList<Patient> searchByNHSNumber(List<Patient> patient,
			int numberBeingSearchedFor) {

		// LinkedList to hold the results
		LinkedList<Patient> matchedPatient = new LinkedList<Patient>();

		// To allow for display of "No Matches" dialog if no patients meet the
		// searched for criteria
		boolean matches = false;

		for (Patient patientWaiting : patient) {

			// If the NHS number matches print out patient
			if (patientWaiting.getNhsNumber() == numberBeingSearchedFor) {
				matchedPatient.add(patientWaiting);
				matches = true;
			}
		}

		// If no matches
		if (!matches) {
			System.out.println("No Matches");
		}

		return matchedPatient;
	}

	public static void changeTriageCategory() {

	}
}
