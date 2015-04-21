package hospital;

import java.util.Scanner;

public class MedicalTeamOperations {

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
	 * To show if treatment time has already been extended
	 */
	public static boolean treatmentRoom1extended = false;

	/**
	 * To show if treatment time has already been extended
	 */
	public static boolean treatmentRoom2extended = false;

	/**
	 * To show if treatment time has already been extended
	 */
	public static boolean treatmentRoom3extended = false;

	/**
	 * To show if treatment time has already been extended
	 */
	public static boolean treatmentRoom4extended = false;

	/**
	 * To show if treatment time has already been extended
	 */
	public static boolean treatmentRoom5extended = false;
	
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

		boolean extend = false;
		int treatmentRoom = 0;
		System.out.println("Enter Treatment Room you wish to extend");
		treatmentRoom = scanner.nextInt();
		System.out.println("Confirm you wish to extend treatment"); // could be
																	// tick box
		extend = scanner.nextBoolean();
		if (treatmentRoom > 0 && treatmentRoom < 5) {
			extraTreatmentTime(extend, treatmentRoom);
		} else {
			throw new Exception("Invalid Treatment Room");
		}
	}

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
}
