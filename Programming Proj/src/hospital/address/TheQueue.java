package hospital.address;

import hospital.address.model.Patient;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class to represent the queueing system of both the treatment rooms and
 * waiting list in a hospital accident and emergency department. There is heavy
 * use of one line comments in this class so that others can follow what exactly
 * is happening.
 * 
 * @author Kieron
 * 
 */
public class TheQueue {

	/**
	 * This allows the system to be run faster than real time by the factor set.
	 * If set to 40 it runs forty times faster than real time. To set to real
	 * time set this variable to 1. Main purpose is testing and demonstrating.
	 */
	public static final int TIME_FACTOR = 16;

	/**
	 * Maximum length of Waiting List as described in specifications - Should
	 * this need to be increased at a later time the queueing system will still
	 * function
	 */
	public static final int MAX_WAITING_LIST_LENGTH = 10;

	/**
	 * Number of treatment rooms in Accident and Emergency - Should this need to
	 * be increased at a later time the queueing system will still function
	 */
	public static final int NUMBER_OF_TREATMENT_ROOMS = 5;

	/**
	 * Constant for int which refers to a specific email message in Email.java
	 */
	static final int PATIENT_TURNED_AWAY_EMAIL = 2;

	/**
	 * To ensure that patients moved from Treatment Room to the Waiting List
	 * remain in the correct order. It has a default value of zero and is
	 * decremented each time a patient is moved from a treatment room onto the
	 * waiting list. It is only limited by the lowest possible number of the int
	 * primitive type
	 */
	static int newPatNum = 0;

	/**
	 * Boolean to indicate if the On Call Team has been contacted by SMS.
	 * Default value is false.
	 */
	public static boolean onCallTeamContacted = false;

	/**
	 * Boolean to indicate if the On Call Team is on the site and treating
	 * patients. Default value is false.
	 */
	public static boolean onCallInSitu = false;

	/**
	 * Boolean to indicate that the treatment room ArrayList has been populated
	 * with null values when the class is first run. These values are then used
	 * as place holders to ensure that patients do not 'move' treatment rooms
	 * during the course of their stay.
	 */
	static boolean firstRun = true;

	/**
	 * Variable to be passed to sms class to select particular message.
	 */
	static final int SMS_TO_ON_CALL_TEAM = 2;

	/**
	 * LinkedList of patients to represent the Waiting List in the hospital
	 */
	public static LinkedList<Patient> WaitingList = new LinkedList<Patient>();

	/**
	 * ArrayList of patients to represent the five Treatment Rooms in the
	 * Accident and Emergency Department at the hospital. The memory space is
	 * allocated when created
	 */
	public static ArrayList<Patient> TreatmentRoom = new ArrayList<Patient>(
			NUMBER_OF_TREATMENT_ROOMS);

	/**
	 * LinkedList that will hold a patient when they are being treated by the on
	 * call team
	 */
	public static LinkedList<Patient> OnCallTeam = new LinkedList<Patient>();

	/**
	 * LinkedList that patients will move into once they have been treated. In
	 * future it could be used for both patient records and statistical
	 * purposes. Used in testing of the Queue, to ensure that through running
	 * the queue system all patients could be accounted for.
	 */
	public static LinkedList<Patient> Treated = new LinkedList<Patient>();

	/**
	 * LinkedList of patients that are turned away from the Accident and
	 * Emergency Department. Could be used in future for statistical and
	 * performance purposes. It was also to prove everything was working during
	 * testing
	 */
	public static LinkedList<Patient> SentElsewhere = new LinkedList<Patient>();

	/**
	 * Main Queue class - takes a Patient object and places them on the
	 * appropriate List dependant on Triage Category.
	 * 
	 * @param patient
	 *            object
	 */
	public void addToQueue(Patient patient) {

		// On first run assign null values to the five elements of the treatment
		// room ArrayList, this is to ensure that the list stays of a constant
		// size and that each treatment room has a place holder, that is when a
		// patient is not in a treatment room the room is reassigned as null so
		// that the list does not resize and the patients 'move' room. Ensure it
		// only happens once by setting a boolean value
		if (firstRun) {
			for (int loop = 0; loop < NUMBER_OF_TREATMENT_ROOMS; loop++) {
				TreatmentRoom.add(loop, null);
			}
			firstRun = false;
		}

		// Used during testing
		// System.out.println("New Patient arrives " + patient);

		// If waiting list size is less than the defined size
		if (WaitingList.size() < MAX_WAITING_LIST_LENGTH) {

			// Add patient initially to the waiting list
			WaitingList.add(patient);

			// Immediately start to try and fill the treatment rooms
			fillTreatmentRoom();

			// Sort waiting list so that the highest priority patient is at the
			// top of the list
			sortListByTriage(WaitingList);

			// If the waiting list has patients on it
			if (!WaitingList.isEmpty()) {

				// Check if the patient at the top of the waiting list is an
				// emergency patient
				if (WaitingList.get(0).getTriageNumber() == 1) {

					// Check to see if an emergency patient, triage category 1,
					// is on waiting list and swap them with the lowest priority
					// patient in the treatment room ArrayList
					moveEmergencyPatients();
				}

				// Ensure that patient moved from treatment room is put to the
				// top of the waiting list regardless of triage category
				keepPriorityPatientsAtTop();

				// Take patients of the same triage number and sort by the order
				// they joined the waiting list
				keepPatientsOfSameTriageInOrder();

			}

			// If waiting list of full
		} else if (WaitingList.size() == MAX_WAITING_LIST_LENGTH) {

			// If the On Call team has not be contacted
			if (!onCallTeamContacted) {

				// Send SMS to the On Call Team on new thread - this is done to
				// ensure that if there is any network delays it does not impact
				// on the running of the queueing system. Once sent the thread
				// terminates.
				Thread sms1 = new Thread(new SendSMS(SMS_TO_ON_CALL_TEAM));
				sms1.start();

			}

			// If the patient arriving from triage has a rating of 1, that is
			// they are an emergency patient
			if (patient.getTriageNumber() == 1) {

				// If the patient of least priority in the Treatment Rooms does
				// not have a triage rating of 1; achieved by using a bubble-
				// sort sorting technique on the treatment room ArrayList to
				// obtain the element of least priority
				if (TreatmentRoom.get(bubbleSortTreatmentRoom())
						.getTriageNumber() != 1) {

					// Used in testing
					// Message that a patient has been asked to leave
					// System.out.println("New Emergency patient arrives");
					// System.out.println("Patient "
					// + WaitingList.getLast().getAdmissionNumber()
					// + " has been asked to go elsewhere.");

					// Thread to send a message to the GUI so that the patient
					// being taken off the queue and sent elsewhere knows to
					// leave the department
					Thread else1 = new Thread(new CallPatientMessageGoHome(
							WaitingList.getLast()));
					else1.start();

					// Add last patient on waiting list to send elsewhere list -
					// this was used during testing and may be of use going
					// forward as a performance / statistical measure
					SentElsewhere.add(WaitingList.getLast());

					// Remove last patient from the waiting list
					WaitingList.removeLast();

					// Add the new patient to the waiting list
					WaitingList.add(patient);

					// Sort waiting list to move the new emergency patient to
					// the top
					sortListByTriage(WaitingList);

					// Move the new emergency patient into the treatment room
					// and lowest priority patient out of the treatment room
					moveEmergencyPatients();

					// Ensure that patient moved from treatment room is put to
					// the top of the waiting list regardless of triage category
					keepPriorityPatientsAtTop();

					// Take patients of the same triage number and sort by the
					// order they joined the waiting list
					keepPatientsOfSameTriageInOrder();

					// If the lowest priority patient in the Treatment rooms has
					// a triage rating of 1, ie is an Emergency Patient
				} else {

					// For testing
					// System.out.println("Emergency Patient sent elsewhere: "
					// + patient);

					// Add patient to send elsewhere list
					SentElsewhere.add(patient);

					// Thread to send email to hospital manager because an
					// Emergency patient has been turned away
					Thread email2 = new Thread(new Email(
							PATIENT_TURNED_AWAY_EMAIL));
					email2.start();

				}

				// If On Call Team not treating a patient
			} else if (!onCallInSitu) {

				// Set On Call boolean to true
				onCallInSitu = true;

				// Add patient to On Call LinkedList
				OnCallTeam.add(0, patient);

				// Used for testing - call patient
				// System.out.println("The On Call Team will attend to "
				// + OnCallTeam.get(0).getFirstName() + " "
				// + OnCallTeam.get(0).getLastName() + " now");

				// Thread to send a message to the GUI so that the patient
				// to be treated by the On Call Team is made aware
				Thread cOc1 = new Thread(new CallPatientMessageOnCallTeam(
						OnCallTeam.get(0)));
				cOc1.start();

				// Set flag in patient that they have been treated by the On
				// Call Team
				OnCallTeam.get(0).isTreatedByOnCallTeam();

				// Get a new instance of time
				Instant onCallStart = Instant.now();

				// Sets patients end wait time, which will have automatically
				// been started at triage
				OnCallTeam.get(0).setEndTimeWait(onCallStart.toEpochMilli());

				// Sets patients treatment start time
				OnCallTeam.get(0).setStartTimeTreat(onCallStart.toEpochMilli());

				// Ensure that patient moved from treatment room is put to
				// the top of the waiting list regardless of triage category
				keepPriorityPatientsAtTop();

				// Take patients of the same triage number and sort by the
				// order they joined the waiting list
				keepPatientsOfSameTriageInOrder();

				// Patient is sent elsewhere
			} else {

				// Ensure that patient moved from treatment room is put to
				// the top of the waiting list regardless of triage category
				keepPriorityPatientsAtTop();

				// Take patients of the same triage number and sort by the
				// order they joined the waiting list
				keepPatientsOfSameTriageInOrder();

				// Add last patient to send elsewhere list - used for testing
				// and possible future uses (see previous)
				SentElsewhere.add(patient);

			}
		}
	}

	/**
	 * Method to fill each treatment room if treatment room is empty.
	 */
	public synchronized void fillTreatmentRoom() {

		// Temporary ArrayList to allow for moving of patient objects around
		ArrayList<Patient> temp = new ArrayList<Patient>();

		// For loop to iterate through each of the treatment rooms
		for (int loop = 0; loop < NUMBER_OF_TREATMENT_ROOMS; loop++) {

			// If treatment is set empty and there is one or more patients on
			// the Waiting list
			if (TreatmentRoom.get(loop) == null && !WaitingList.isEmpty()) {

				// Remove null element from the Treatment Room
				TreatmentRoom.remove(loop);

				// Copy first patient in Waiting List into the temporary
				// ArrayList
				temp.add(WaitingList.get(0));

				// Reset this boolean as patient is no longer waiting
				temp.get(0).setWaitingMoreThan30(false);

				// This is so that patients who have already been on the waiting
				// list do not get their waiting end time changed upon re-entry
				// to the waiting list
				if (temp.get(0).getAdmissionNumber() > 0) {

					// Get a new instance of time
					Instant endWait = Instant.now();

					// Set end waiting time for patient using new instance of
					// time
					temp.get(0).setEndTimeWait(endWait.toEpochMilli());
				}

				// Remove the first element from the waiting list
				WaitingList.remove(0);

				// Copy the patient from the temporary ArrayList to the correct
				// treatment room as defined by the loop iteration
				TreatmentRoom.add(loop, temp.get(0));

				// Calling patient to treatment room - Testing purposes
				// System.out.println("Can "
				// + TreatmentRoom.get(loop).getFirstName() + " "
				// + TreatmentRoom.get(loop).getLastName()
				// + " go to Treatment Room " + (loop + 1) + "");

				// Assign set the Treatment Room array element in the patient
				// object,this is required when bubblesorting the treatment room
				// to find the patient of lowest priority
				TreatmentRoom.get(loop).setTreatmentRoomArrayElement(loop);

				// Assign the actual treatment room number of the patient - used
				// for display purposes in JavaFX
				TreatmentRoom.get(loop).setActualTreatmentRoom(loop + 1);

				// Thread to send a message to the GUI so that the patient knows
				// which treatment room to go to
				Thread cM1 = new Thread(new CallPatientMessageTreatmentRoom(
						TreatmentRoom.get(loop), loop));
				cM1.start();

				// Get a new instance of time
				Instant startTreat = Instant.now();

				// Set the start time for entry into the treatment room
				TreatmentRoom.get(loop).setStartTimeTreat(
						startTreat.toEpochMilli());

				// Clear temp ArrayList
				temp.clear();

			}
		}
	}

	/**
	 * Method to sort a LinkedList of Patient objects by triage category
	 * 
	 * @param patient
	 *            object
	 */
	public synchronized void sortListByTriage(LinkedList<Patient> patient) {
		Collections.sort(patient, new sortByTriage());
	}

	/**
	 * Method to sort a ArrayList of Patient objects by triage category
	 * 
	 * @param patient
	 *            object
	 */
	public synchronized void sortListByTriage(ArrayList<Patient> patient) {
		Collections.sort(patient, new sortByTriage());
	}

	/**
	 * Class that implements Comparator to sort Patient objects by triage number
	 * 
	 * @author Kieron
	 * @implements Comparator
	 */
	public class sortByTriage implements Comparator<Patient> {
		@Override
		public int compare(Patient p1, Patient p2) {
			if (p1.getTriageNumber() > p2.getTriageNumber()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Method to sort a ArrayList of Patient objects by admission number
	 * 
	 * @param patient
	 *            object
	 */
	public void sortListByAdmissionNumber(ArrayList<Patient> patient) {
		Collections.sort(patient, new sortByAdmissionNumber());
	}

	/**
	 * Method to sort a LinkedList of Patient objects by admission number
	 * 
	 * @param patient
	 *            object
	 */
	public void sortListByAdmissionNumber(LinkedList<Patient> patient) {
		Collections.sort(patient, new sortByAdmissionNumber());
	}

	/**
	 * Class that implements Comparator to sort Patient objects by admission
	 * number
	 * 
	 * @author Kieron
	 * @implements Comparator
	 */
	public class sortByAdmissionNumber implements Comparator<Patient> {
		@Override
		public int compare(Patient p1, Patient p2) {
			if (p1.getAdmissionNumber() > p2.getAdmissionNumber()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	/**
	 * Method to bubblesort a copy of the treatment room in order to obtain the
	 * array element of the patient with the lowest priority, that is the
	 * patient with the highest triage number and in the case of two patients
	 * with this triage number the patient who has been in the treatment room
	 * for the shortest period. The treatment room list itself is not
	 * bubblesorted as that would destroy the order of the list and 'move'
	 * patients around the different treatment rooms
	 * 
	 * @return an <code>int</code> which is the element number of the treatment
	 *         room ArrayList which contains the patient of the lowest priority
	 */
	public synchronized int bubbleSortTreatmentRoom() {

		// temporary ArrayList - copy of treatment room
		ArrayList<Patient> tempList = new ArrayList<Patient>();

		// For loop to iterate through each of the treatment rooms
		for (int loop = 0; loop < NUMBER_OF_TREATMENT_ROOMS; loop++) {

			// Populate the temporary ArrayList with all the elements from the
			// TreatmentRoom list
			tempList.add(loop, TreatmentRoom.get(loop));
		}

		// Initialise variable for the index of the element
		int indexOfElement = 0;

		// Sort the temporary ArrayList by triage number - lowest first
		sortListByTriage(tempList);

		// For loop to iterate through each of the treatment rooms - because you
		// are comparing elements you need it to loop one less time than normal,
		// otherwise the last compare throws an IndexOutOfBounds exception
		for (int loop = 0; loop < NUMBER_OF_TREATMENT_ROOMS - 1; loop++) {

			// If triage number of elements 0 and 1 are the same
			if (tempList.get(loop).getTriageNumber() == tempList.get(loop + 1)
					.getTriageNumber()) {

				// And if element 0 was added after element 1 to the queue, swap
				// them in the list
				if (tempList.get(loop).getAdmissionNumber() > tempList.get(
						loop + 1).getAdmissionNumber()) {
					Collections.swap(tempList, loop, loop + 1);

					// This will ensure that if two patients have negative
					// admission numbers (that is a patient has been taken out
					// of the treatment room they are reassigned a new negative
					// admission number for testing purposes) they will be
					// ordered correctly
					if (tempList.get(loop).getAdmissionNumber() < 0
							&& tempList.get(loop + 1).getAdmissionNumber() < 0) {
						Collections.swap(tempList, loop, loop + 1);
					}
				}
			}
		}

		// The index of the last element of the ArrayList is the size of the
		// list  minus 1
		int lastIndex = NUMBER_OF_TREATMENT_ROOMS - 1;

		// Get treatment room number of last element
		indexOfElement = tempList.get(lastIndex).getTreatmentRoomArrayElement();

		// Clear the temporary ArrayList for next use
		tempList.clear();

		// Return index of element containing patient of lowest priority
		return indexOfElement;
	}

	/**
	 * Method to move a newly added emergency patient, triage number 1, into the
	 * treatment room by removing the patient of the lowest priority
	 */
	public synchronized void moveEmergencyPatients() {

		// Temporary ArrayLists used in the moving process
		ArrayList<Patient> tempListFromTreatment = new ArrayList<Patient>();
		ArrayList<Patient> tempListFromWaiting = new ArrayList<Patient>();

		// Declare variable
		int index;

		// Obtain index which is the element of the treatment room ArrayList of
		// the lowest priority. This is obtained by calling the
		// bubblesortTreatmentList method
		index = bubbleSortTreatmentRoom();

		// If patient in the treatment room of the lowest priority is not of
		// triage rating 1 and the first element of the waiting list (the newly
		// arrived patient) is an emergency patient they must be swapped
		if ((TreatmentRoom.get(index).getTriageNumber() != 1)
				&& (WaitingList.get(0).getTriageNumber() == 1)) {

			// The patient being removed from the treatment room is set as a
			// priority patient and will be kept at the top of the waiting list
			TreatmentRoom.get(index).setPriorityPatient(true);

			// Reset the start treatment time to zero as their clock will start
			// at zero when they eventually re-enter the treatment room
			TreatmentRoom.get(index).setStartTimeTreat(0);

			// Ensure their end treatment time is also set to zero
			TreatmentRoom.get(index).setEndTimeTreat(0);

			// Set boolean to false for extended treatment - in case they had
			// had treatment extended whilst in treatment room
			TreatmentRoom.get(index).setExtraTime(false);

			// Copy patient in treatment room to be moved to a temporary
			// ArrayList
			tempListFromTreatment.add(TreatmentRoom.get(index));

			// Remove patient from Treatment Room
			TreatmentRoom.remove(index);

			// Decrement the new Admission number this is to make sorting of
			// patients that are a priority patient easier, such patients all
			// have a negative admission number
			newPatNum--;

			// Set new admission number for patient being moved out of treatment
			// room
			tempListFromTreatment.get(0).setAdmissionNumber(newPatNum);

			// Get a new instant of time
			Instant setTime = Instant.now();

			// Set end wait time for emergency patient moving to treatment room
			WaitingList.get(0).setEndTimeWait(setTime.toEpochMilli());

			// Set treatment start time for emergency patient moving to
			// treatment room
			WaitingList.get(0).setStartTimeTreat(setTime.toEpochMilli());

			// Add person from waiting list to temporary ArrayList
			tempListFromWaiting.add(WaitingList.get(0));

			// Remove first element of Waiting List
			WaitingList.remove(0);

			// For loop to iterate through each of the treatment rooms
			for (int loop = 0; loop < NUMBER_OF_TREATMENT_ROOMS; loop++) {

				if (loop == index) {

					// Add patient to treatment room
					TreatmentRoom.add(index, tempListFromWaiting.get(0));

					// Call patient - used for testing purposes
					// System.out.println("Can "
					//		+ TreatmentRoom.get(index).getFirstName() + " "
					//		+ TreatmentRoom.get(index).getLastName()
					//		+ " go to Treatment Room " + (index + 1) + "");

					// Set Treatment Room Array Element in patient
					TreatmentRoom.get(index)
							.setTreatmentRoomArrayElement(index);

					// Set actual treatment room
					TreatmentRoom.get(index).setActualTreatmentRoom(index + 1);

					// Thread to send a message to the GUI so that the patient
					// is made aware which treatment room to attend
					Thread cM1 = new Thread(
							new CallPatientMessageTreatmentRoom(
									TreatmentRoom.get(index), index));
					cM1.start();

				}
			}

			// for testing purposes
			// System.out.println("Swapping patients");

			// Reset treatment room of patient removed from treatment room to
			// the default value of -1
			tempListFromTreatment.get(0).setTreatmentRoomArrayElement(-1);
			tempListFromTreatment.get(0).setActualTreatmentRoom(-1);

			// Copy patient from temporary ArrayList to first element of waiting
			// list; this is a LinkedList and all the other elements will
			// shuffle up to make room at the start of the list
			WaitingList.addFirst(tempListFromTreatment.get(0));

			// Clear both temporary ArrayLists for next use
			tempListFromTreatment.clear();
			tempListFromWaiting.clear();

			// If all treatment room patients are emergency patients (triage
			// number of 1) and the newly added patient at the top of the
			// waiting list is an emergency patient and the On Call Team is not
			// treating anyone
		} else if ((TreatmentRoom.get(index).getTriageNumber() == 1)
				&& (WaitingList.getFirst().getTriageNumber() == 1)
				&& !onCallInSitu) {

			// Send SMS to the On Call Team on new thread
			Thread sms2 = new Thread(new SendSMS(SMS_TO_ON_CALL_TEAM));
			sms2.start();

			// Set boolean to true
			onCallInSitu = true;

			// Copy emergency patient from top of waiting list to a temporary
			// ArrayList
			tempListFromTreatment.add(WaitingList.get(0));

			// Remove patient from Waiting List
			WaitingList.remove(0);

			// Add patient to on call team list - patient removed from this list
			// after 15 minutes - there can only be one patient on this list
			OnCallTeam.add(0, tempListFromTreatment.get(0));

			// Call patient
			// System.out.println("The On Call Team will attend to "
			//		+ OnCallTeam.get(0).getFirstName() + " "
			//		+ OnCallTeam.get(0).getLastName() + " now");

			// Thread to send a message to the GUI so that the patient
			// to be treated by the On Call Team is made aware
			Thread cOc2 = new Thread(new CallPatientMessageOnCallTeam(
					OnCallTeam.get(0)));
			cOc2.start();

			// Set boolean for patient records
			OnCallTeam.get(0).isTreatedByOnCallTeam();

			// Get a new instant of time
			Instant onCallStart = Instant.now();

			// Sets patients end wait time, which will have automatically
			// been started at triage
			OnCallTeam.get(0).setEndTimeWait(onCallStart.toEpochMilli());

			// Set time that on call team started treating patient
			OnCallTeam.get(0).setStartTimeTreat(onCallStart.toEpochMilli());

		}
	}

	/**
	 * Method to ensure that those patients who have been granted priority
	 * status, that is they have been moved from the treatment room to make way
	 * for an emergency patient or they have been waiting for more than 25
	 * minutes, are kept at the top of the waiting list
	 */
	public synchronized void keepPriorityPatientsAtTop() {

		// Temporary ArrayList to hold patient whilst being sorted
		ArrayList<Patient> temp = new ArrayList<Patient>();

		// Loop through the waiting list
		for (int loop = 0; loop < WaitingList.size(); loop++) {

			// If patient has been granted priority status
			if (WaitingList.get(loop).isPriorityPatient()) {

				// Copy patient to the temporary ArrayList
				temp.add(WaitingList.get(loop));

				// Remove patient from current position in waiting list
				WaitingList.remove(loop);

				// Add patient from temporary ArrayList to the start of waiting
				// list
				WaitingList.addFirst(temp.get(0));
			}

			// Clear the temporary ArrayList for next use
			temp.clear();
		}
	}

	/**
	 * Method ensure that patients of the same priority are grouped together and
	 * sorted by time on waiting list, for instance if there are several
	 * patients with a triage number of 4 they will all be grouped together, but
	 * with the patient who joined the waiting list first at the top of the list
	 */
	public synchronized void keepPatientsOfSameTriageInOrder() {

		// If the waiting list has 10 or less patients (elements)
		if (WaitingList.size() <= 10) {

			// Make a temporary LinkedList of the waiting list
			LinkedList<Patient> copyWaitingList = new LinkedList<Patient>(
					WaitingList);

			// Instantiate the iterator
			Iterator<Patient> Patient = copyWaitingList.iterator();

			// Create five temporary ArrayLists to hold the patients of each
			// priority or triage number - note that triage1 should never be
			// used, it is included for completeness and its absence did cause
			// an exception on one occasion
			ArrayList<Patient> priority = new ArrayList<Patient>();
			ArrayList<Patient> triage1 = new ArrayList<Patient>();
			ArrayList<Patient> triage2 = new ArrayList<Patient>();
			ArrayList<Patient> triage3 = new ArrayList<Patient>();
			ArrayList<Patient> triage4 = new ArrayList<Patient>();

			// Second temporary LinkedList into which the waiting list will be
			// recontructed before being copied back to the actual waiting list
			LinkedList<Patient> orderedWaitingList = new LinkedList<Patient>();

			// Variable to allow for iteration of the Waiting List
			int element = 0;

			// While there are patients in the Waiting List do...
			while (Patient.hasNext()) {

				// If patient has been previously moved from the treatment room,
				// or has been waiting for more than 25 without treatment.
				if (Patient.next().isPriorityPatient()) {

					// Add patient to to 'priority' ArrayList
					priority.add(copyWaitingList.get(element));

					// Advance element number
					element++;

					// For those patients not afforded priority status
				} else {

					// Switch on triage priority
					switch (copyWaitingList.get(element).getTriageNumber()) {

					// Triage priority 1
					case 1:

						// Add patient to 'triage1' ArrayList
						triage1.add(copyWaitingList.get(element));

						// Advance element number
						element++;

						// Allow iterator to advance to next patient if any
						break;

					// Triage priority 2
					case 2:

						// Add patient to 'triage2' ArrayList
						triage2.add(copyWaitingList.get(element));

						// Advance element number
						element++;

						// Allow iterator to advance to next patient if any
						break;

					// Triage priority 3
					case 3:

						// Add patient to 'triage 3' ArrayList
						triage3.add(copyWaitingList.get(element));

						// Advance element number
						element++;

						// Allow iterator to advance to next patient if any
						break;

					// Triage priority 4
					case 4:

						// Add patient to 'triage 4' ArrayList
						triage4.add(copyWaitingList.get(element));

						// Advance element number
						element++;

						// Allow iterator to advance to next patient if any
						break;
					}
				}
			}

			// Sort each of these ArrayLists based on admission number, that is
			// the order in which the patients joined the waiting list. Priority
			// patients have been assigned a new negative admission number which
			// indicates the order in which they were awarded priority status
			// and therefore allows them to be sorted also
			sortListByAdmissionNumber(priority);
			sortListByAdmissionNumber(triage1);
			sortListByAdmissionNumber(triage2);
			sortListByAdmissionNumber(triage3);
			sortListByAdmissionNumber(triage4);

			// As the priority patients now have a decementing negative
			// admission number their temporary ArrayList must be reversed to
			// keep them in the correct order
			Collections.reverse(priority);

			// Add all the elements from each sorted ArrayList to the
			// orderedWaitingList temporary LinkedList in the correct order
			orderedWaitingList.addAll(priority);
			orderedWaitingList.addAll(triage1);
			orderedWaitingList.addAll(triage2);
			orderedWaitingList.addAll(triage3);
			orderedWaitingList.addAll(triage4);

			// Clear Waiting List
			WaitingList.clear();

			// Add all elements from orderedWaitingList temporary LinkedList to
			// the Waiting List, all patients are now in the correct order
			WaitingList.addAll(orderedWaitingList);

			// Clear temporary lists
			copyWaitingList.clear();
			orderedWaitingList.clear();

		}
	}
}