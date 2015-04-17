package hospital;

import java.util.LinkedList;
import java.util.List;

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
	 * To allow for a patient in Treatment Room 1 be treated for a determined
	 * extra period of time.
	 */
	public static long extraTime1 = 0L;

	/**
	 * To allow for a patient in Treatment Room 2 be treated for a determined
	 * extra period of time.
	 */
	public static long extraTime2 = 0L;

	/**
	 * To allow for a patient in Treatment Room 3 be treated for a determined
	 * extra period of time.
	 */
	public static long extraTime3 = 0L;

	/**
	 * To allow for a patient in Treatment Room 4 be treated for a determined
	 * extra period of time.
	 */
	public static long extraTime4 = 0L;

	/**
	 * To allow for a patient in Treatment Room 5 be treated for a determined
	 * extra period of time.
	 */
	public static long extraTime5 = 0L;

	public static boolean treatmentRoom1extended = false;
	public static boolean treatmentRoom2extended = false;
	public static boolean treatmentRoom3extended = false;
	public static boolean treatmentRoom4extended = false;
	public static boolean treatmentRoom5extended = false;

	/**
	 * Method which can be called by a doctor to increase a patients period of
	 * treatment in one of the five treatment rooms.
	 * 
	 * @param extend
	 *            , a <code>boolean</code> to say if a patient is having their
	 *            treatment extended or not
	 * @param treatmentRoom
	 *            , an <code>int</code> for the treatment room number
	 * @throws IllegalArgumentException
	 *             , thrown if an invalid Treatment Room number is passed to the
	 *             method
	 */
	public static void extraTreatmentTime(boolean extend, int treatmentRoom)
			throws IllegalArgumentException {

		// Declare and initialise variable
		int minutes = 0;

		// If extend is true
		if (extend) {

			// Treatment will be extended by five minutes
			minutes = 5;
		}

		// If treatment room variable is less than 1
		if (treatmentRoom < 1) {
			throw new IllegalArgumentException("Invalid Treatment Rooom");

		} else {
			// Switch on the Treatment Room number, conversion is included to
			// convert the minutes into milliseconds. If an invalid Treatment
			// room number is passed to the method an exception is throw. Set a
			// boolean to show that the treatment room time for that treatment
			// room has been extended. This allows it to be reset when a patient
			// leaves that particular treatment room.
			switch (treatmentRoom) {
			case 1:
				if (treatmentRoom1extended) {
					throw new IllegalArgumentException(
							"Treatment already extended");
				} else {
					extraTime1 = (minutes * 10000) / TheQueue.TIME_FACTOR;
					treatmentRoom1extended = true;
				}
				break;
			case 2:
				if (treatmentRoom2extended) {
					throw new IllegalArgumentException(
							"Treatment already extended");
				} else {
					extraTime2 = (minutes * 10000) / TheQueue.TIME_FACTOR;
					treatmentRoom2extended = true;
				}
				break;
			case 3:
				if (treatmentRoom3extended) {
					throw new IllegalArgumentException(
							"Treatment already extended");
				} else {
					extraTime3 = (minutes * 10000) / TheQueue.TIME_FACTOR;
					treatmentRoom3extended = true;
				}
				break;
			case 4:
				if (treatmentRoom4extended) {
					throw new IllegalArgumentException(
							"Treatment already extended");
				} else {
					extraTime4 = (minutes * 10000) / TheQueue.TIME_FACTOR;
					treatmentRoom4extended = true;
				}
				break;
			case 5:
				if (treatmentRoom5extended) {
					throw new IllegalArgumentException(
							"Treatment already extended");
				} else {
					extraTime5 = (minutes * 10000) / TheQueue.TIME_FACTOR;
					treatmentRoom5extended = true;
				}
				break;
			default:
				throw new IllegalArgumentException("Invalid Treatment Room");
			}
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
	 * Method to search a list of patients by NHS number and display to screen
	 * 
	 * @param patient
	 *            , a <code>List</code> of Patient objects
	 * @param numberBeingSearchedFor
	 *            , an <code>int</code>, the NHS number being searched for
	 * @return a <code>Patient</code>, the patient who match the
	 *         search criteria
	 */
	public static Patient searchByNHSNumber(List<Patient> patient,
			int numberBeingSearchedFor) {

		// LinkedList to hold the results
		Patient matchedPatient = new Patient();

		// To allow for display of "No Matches" dialog if no patients meet the
		// searched for criteria
		boolean matches = false;

		// Check number being searched for is a valid NHS number
		if (numberBeingSearchedFor < NHS_NUMBER_MIN
				|| numberBeingSearchedFor > NHS_NUMBER_MAX) {
			throw new IllegalArgumentException("NHS number is 9 digits long");

		} else {
			// Enhanced For Loop to search for patient
			for (Patient patientWaiting : patient) {

				// If the NHS number matches print out patient
				if (patientWaiting.getNhsNumber() == numberBeingSearchedFor) {
					matchedPatient.equals(patientWaiting);
					matches = true;
				}
			}
		}

		// If no matches
		if (!matches) {
			System.out.println("No Matches");
			matchedPatient = null;
		}
		
		return matchedPatient;
	}
	
	public static void changeTriageCategory() {
		
		
		
	}
}
