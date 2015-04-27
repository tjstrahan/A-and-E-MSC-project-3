package hospital.address;

import java.util.Scanner;

public class MedicalTeamOperations {

	/**
	 * Static instantiation of the scanner class
	 */
	static Scanner scanner = new Scanner(System.in);

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
	
	public static void changeTriage(int NHSNumber) {
		
		 
	}
}
