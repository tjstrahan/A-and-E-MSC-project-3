package hospital.address;

import hospital.address.QueueOperations;
import hospital.address.TheQueue;

import java.util.Scanner;

public class MedicalTeamOperations {

	/**
	 * Static instantiation of the scanner class
	 */
	static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Constant for NHS number maximum value
	 */
	public static final int NHS_NUMBER_MAX = 999999999;

	/**
	 * Constant for NHS number minimum value
	 */
	public static final int NHS_NUMBER_MIN = 100000000;

	/**
	 * Method which takes input and passes it to the extraTreatmentTime method -
	 * placed here so all relevant classes can call it and reduce duplication of
	 * code
	 * 
	 * @throws Exception
	 */
	public void extendTreatment() throws Exception {

		int treatmentRoom = 0;
		System.out.println("Enter Treatment Room you wish to extend");
		treatmentRoom = scanner.nextInt();
		
		if (treatmentRoom > 0
				&& treatmentRoom <= TheQueue.NUMBER_OF_TREATMENT_ROOMS) {
			extraTreatmentTime(treatmentRoom);
		} else {
			throw new Exception("Invalid Treatment Room");
		}
	}

	/**
	 * Method which can be called by a doctor to increase a patients period of
	 * treatment in one of the five treatment rooms.
	 * 
	 * @param treatmentRoom
	 *            , an <code>int</code> for the treatment room number
	 * @throws IllegalArgumentException
	 *             , thrown if patient's treatment time already extended
	 */
	public static void extraTreatmentTime(int treatmentRoom)
			throws IllegalArgumentException {

		// The index of the ArrayList for Treatment room is one less than the
		// treatment room number
		int index = treatmentRoom - 1;

		// If patient in treatment room has not had their time extended
		if (!TheQueue.TreatmentRoom.get(index).isExtraTime()) {

			// Set boolean to true
			TheQueue.TreatmentRoom.get(index).setExtraTime(true);

		} else {
			
			throw new IllegalArgumentException ("Treatment already extended.");
		}
	}
	
	/**
	 * Method to change the triage category of a patient of the Waiting List
	 * @param NHSNumber
	 * @throws Exception
	 */
	public void changeTriage() throws Exception {

		int NHSNumber = 0;
		int triageNumber = 0;
		int waitingListIndex = 0;
		
		System.out.println("Enter patient's NHS Number");
		NHSNumber = scanner.nextInt();
		
		if (NHSNumber < NHS_NUMBER_MIN || NHSNumber > NHS_NUMBER_MAX) {
			throw new IllegalArgumentException(
					"Sorry. NHS number must be 9 digits long.");
		}
		
		System.out.println("Please enter new Triage Category");
		triageNumber = scanner.nextInt();

		if (triageNumber > 0 || triageNumber < 5) {

			// Get index of waiting list that the patient of the entered
			// NHSNumber occupies at that precise time
			waitingListIndex = TheQueue.WaitingList.indexOf(QueueOperations
					.searchByNHSNumber(TheQueue.WaitingList, NHSNumber));
			
			TheQueue.WaitingList.get(waitingListIndex).setTriageNumber(
					triageNumber);

		} else {
			throw new IllegalArgumentException("Invalid Triage Category");
		}
	}
}
