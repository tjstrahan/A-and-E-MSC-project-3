package hospital;

import java.util.List;

public class QueueOperations {

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

		// Switch on the Treatment Room number, conversion is included to
		// convert the minutes into milliseconds. If an invalid Treatment room
		// number is passed to the method an exception is throw
		switch (treatmentRoom) {
		case 1:
			extraTime1 = (minutes * 10000) / TheQueue.TIME_FACTOR;
			break;
		case 2:
			extraTime2 = (minutes * 10000) / TheQueue.TIME_FACTOR;
			break;
		case 3:
			extraTime3 = (minutes * 10000) / TheQueue.TIME_FACTOR;
			break;
		case 4:
			extraTime4 = (minutes * 10000) / TheQueue.TIME_FACTOR;
			break;
		case 5:
			extraTime5 = (minutes * 10000) / TheQueue.TIME_FACTOR;
			break;
		default:
			throw new IllegalArgumentException("Invalid Treatment Room");
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
	 * @throws IllegalArgumentException
	 *             , thrown if an invalid triage category is passed to this
	 *             method
	 */
	public static void searchForTriageCategory(List<Patient> patient,
			String category) throws IllegalArgumentException {

		// To allow for display of "No Matches" dialog if no patients meet the
		// searched for criteria
		boolean matches = false;

		// Check to see if the triage category that is being searched for is
		// valid
		if (!category.equalsIgnoreCase("Emergency")
				|| !category.equalsIgnoreCase("Urgent")
				|| !category.equalsIgnoreCase("Semi-Urgent")
				|| !category.equalsIgnoreCase("Non-Urgent")) {

			// If it is invalid throw and exception
			throw new IllegalArgumentException("Invalid Triage Category");

			// If valid, ie not invalid
		} else {

			// Enhanced For Loop to search for patient
			for (Patient patientWaiting : patient) {

				// If categories match
				if (patientWaiting.triageCategory().equals(category)) {
					System.out.println(patientWaiting);
					matches = true;
				} 
			}
			
			// If no matches
			if (!matches) {
				System.out.println("No Matches");
			}
		}

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
	 */
	public static void searchForPatientByName(List<Patient> patient,
			String forename, String surname) {

		// To allow for display of "No Matches" dialog if no patients meet the
		// searched for criteria
		boolean matches = false;
		
		// Enhanced For Loop to search for patient
		for (Patient patientWaiting : patient) {

			// If the forename and surname match print out patient
			if (patientWaiting.getFirstName().equalsIgnoreCase(forename)
					&& patientWaiting.getLastName().equalsIgnoreCase(surname)) {
				System.out.println(patientWaiting);
				matches = true;
			} 
		}
		
		// If no matches
		if (!matches) {
			System.out.println("No Matches");
		}

	}

	/**
	 * Method to search a list of patients by NHS number and display to screen
	 * 
	 * @param patient
	 *            , a <code>List</code> of Patient objects
	 * @param numberBeingSearchedFor
	 *            , an <code>int</code>, the NHS number being searched for
	 */
	public static void searchByNHSNumber(List<Patient> patient,
			int numberBeingSearchedFor) {

		// To allow for display of "No Matches" dialog if no patients meet the
		// searched for criteria
		boolean matches = false;
		
		// Enhanced For Loop to search for patient
		for (Patient patientWaiting : patient) {

			// If the NHS number matches print out patient
			if (patientWaiting.getNhsNumber() == numberBeingSearchedFor) {
				System.out.println(patientWaiting);
			} 
		}
		
		// If no matches
		if (!matches) {
			System.out.println("No Matches");
		}

	}
}
