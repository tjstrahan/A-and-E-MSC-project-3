package hospital;

// imports
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

import jdbc.QueueAccessClearDischargeNotes;
import jdbc.QueueAccessClearDischargeTime;

/**
 * Class to represent the queueing system of both the treatment rooms and
 * waiting list in a hospital accident and emergency department
 * 
 * @author Kieron
 * 
 */
public class TheQueue {

	/**
	 * This allows the system to be run faster than real time by the factor set.
	 * If set to 40 it runs forty times faster than real time. To set to real
	 * time set this variable to 1.
	 */
	public static final int TIME_FACTOR = 30;

	/**
	 * Maximum length of Waiting List as described in specifications
	 */
	public static final int MAX_WAITING_LIST_LENGTH = 10;

	/**
	 * Number of treatment rooms in Accident and Emergency
	 */
	public static final int NUMBER_OF_TREATMENT_ROOMS = 5;

	/**
	 * To ensure that patients moved from Treatment Room to the Waiting List
	 * remain in the correct order. It has a default value of zero and is
	 * decremented each time a patient is moved from a treatment room onto the
	 * waiting list.
	 */
	static int newPatNum = 0;

	/**
	 * Boolean to indicate if the On Call Team has been contacted by SMS.
	 * Default value is false.
	 */
	public static boolean onCallTeamContacted = false;

	/**
	 * Boolean to indicate if the On Call Team is on the site an treating
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
	 * LinkedList of patients to represent the Waiting List in the hospital it
	 * is limited to 10 patients at any given time
	 */
	public static LinkedList<Patient> WaitingList = new LinkedList<Patient>();

	/**
	 * ArrayList of patients to represent the five Treatment Rooms in the
	 * Accident and Emergency Department at the hospital. There are 5 treatment
	 * rooms so the ArrayList is allocated an initial size of 5 so that the
	 * memory space is allocated. It will never change in size
	 */
	public static ArrayList<Patient> TreatmentRoom = new ArrayList<Patient>(5);

	/**
	 * LinkedList that will hold a patient when they are being treated by the on
	 * call team
	 */
	public static LinkedList<Patient> OnCallTeam = new LinkedList<Patient>();

	/**
	 * LinkedList that patients will move into once they have been treated. It
	 * will be used as an exit point for patient objects from which pertinent
	 * details will be written back to the database
	 */
	public static LinkedList<Patient> Treated = new LinkedList<Patient>();

	/**
	 * LinkedList of patients that are turned away from the Accident and
	 * Emergency Department. Created for statistical reasons. Also to prove
	 * everything is working during testing
	 */
	public static LinkedList<Patient> SentElsewhere = new LinkedList<Patient>();

	/**
	 * Main Queue class
	 * 
	 * @param patient
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

		System.out.println("New Patient arrives " + patient);

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

		} else if (WaitingList.size() == MAX_WAITING_LIST_LENGTH) {

			// If the On Call team has not be contacted
			if (!onCallTeamContacted) {

				// Set boolean to true
				onCallTeamContacted = true;

				// Send SMS to the On Call Team, do this by instantiating SMS
				// class and starting it as a new thread, the SMS class will
				// check back to TheQueue to see if the boolean value
				// onCallTeamContacted is true. Then if true it will extract the
				// mobile numbers of the 2 doctors and 3 nurses that are flagged
				// as on call from the database into an array and send them an
				// sms message, once message is sent the thread will end, this
				// structure means that the queue can continue without delay

				// SendSMS sendSMS = new SendSMS();
				// Thread sms = new Thread(sms);
				// sms.start();

				// for demo purposes
				System.out.println("On-call team called - SMS");
			}

			// If the patient arriving from triage has a rating of 1, that is
			// they are an emergency patient
			if (patient.getTriageNumber() == 1) {

				// If the patient of least priority in the Treatment Rooms does
				// not have a triage rating of 1; achieved by using a bubble
				// sort sorting technique on the treatment room ArrayList to
				// obtain the element of least priority
				if (TreatmentRoom.get(bubbleSortTreatmentRoom())
						.getTriageNumber() != 1) {

					// Message that a patient has been asked to leave
					System.out.println("New Emergency patient arrives");
					System.out.println("Patient "
							+ WaitingList.getLast().getAdmissionNumber()
							+ " has been asked to go elsewhere.");

					// Add last patient on waiting list to send elsewhere list
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

					// alert to hospital manager

					// SendSMS sendSMS = new SendSMS();
					// Thread sms = new Thread(sendSMS);
					// sms.start();

					// SendEmail sendEmail = new SendEmail();
					// Thread email = new Thread(sendEmail);
					// email.start();

					// for demo purposes
					System.out
							.println("Alert hospital manager - SMS and Email");
				}

			} else if (!onCallInSitu) {

				// Set On Call boolean to true
				onCallInSitu = true;

				// Add patient to On Call LinkedList
				OnCallTeam.add(0, patient);

				// Call patient
				System.out.println("The On Call Team will attend to "
						+ OnCallTeam.get(0).getFirstName() + " "
						+ OnCallTeam.get(0).getLastName() + " now");

				// Set flag in patient that they have been treated by the On
				// Call Team
				OnCallTeam.get(0).isTreatedByOnCallTeam();

				// Get a new instance of time
				Instant onCallStart = Instant.now();

				// Sets patients end wait time, which will have automatically
				// been started at triage
				OnCallTeam.get(0).setEndTimeWait(onCallStart.toEpochMilli());

				// Reset timestamp on patients record - run on separate thread
				// in case network traffic slows down queue execution
				Thread oCT1TimeClear = new Thread(new QueueAccessClearDischargeTime(
						OnCallTeam.get(0).getNhsNumber()));
				oCT1TimeClear.start();

				// Clear Notes (if any) from previous visit (if any) - run on
				// separate thread in case network traffic slows down queue
				// execution
				Thread oCT1NotesClear = new Thread(new QueueAccessClearDischargeNotes(
						OnCallTeam.get(0).getNhsNumber()));
				oCT1NotesClear.start();
				
				// Sets patients treatment start time
				OnCallTeam.get(0).setStartTimeTreat(onCallStart.toEpochMilli());

				// Ensure that patient moved from treatment room is put to
				// the top of the waiting list regardless of triage category
				keepPriorityPatientsAtTop();

				// Take patients of the same triage number and sort by the
				// order they joined the waiting list
				keepPatientsOfSameTriageInOrder();

			} else {

				// Patient is sent elsewhere
				System.out.println("go elsewhere");

				// Ensure that patient moved from treatment room is put to
				// the top of the waiting list regardless of triage category
				keepPriorityPatientsAtTop();

				// Take patients of the same triage number and sort by the
				// order they joined the waiting list
				keepPatientsOfSameTriageInOrder();

				// Add last patient to send elsewhere list
				SentElsewhere.add(patient);

				// alert to hospital manager

				// SendSMS sendSMS = new SendSMS();
				// Thread sms = new Thread(sendSMS);
				// sms.start();

				// SendEmail sendEmail = new SendEmail();
				// Thread email = new Thread(sendEmail);
				// email.start();

				// for demo purposes
				System.out.println("Alert hospital manager - SMS and Email");

			}

		}

	}

	/**
	 * Method to fill each treatment room if treatment room contains null
	 */
	public synchronized void fillTreatmentRoom() {

		// Temporary ArrayList to allow for moving of patient objects around
		ArrayList<Patient> temp = new ArrayList<Patient>();

		// For loop to iterate through each of the 5 treatment rooms
		for (int loop = 0; loop < NUMBER_OF_TREATMENT_ROOMS; loop++) {

			// If treatment is set to null, in otherwords the room is empty, and
			// the waiting list is not empty, that is patients have been through
			// triage
			if (TreatmentRoom.get(loop) == null && !WaitingList.isEmpty()) {

				// Remove element the null element from the Treatment Room
				TreatmentRoom.remove(loop);

				// Copy first patient in Waiting List into the temporary
				// ArrayList
				temp.add(WaitingList.get(0));

				// Reset this boolean as patient is no longer on the Waiting
				// List
				temp.get(0).setWaitingMoreThan30(false);

				// This is so that patient who have already been on the waiting
				// list do not get their waiting end time changed upon re-entry
				// to the waiting list
				if (temp.get(0).getAdmissionNumber() > 0) {

					// Get a new instance of time
					Instant endWait = Instant.now();

					// Set end waiting time for patient using new instance of
					// date
					temp.get(0).setEndTimeWait(endWait.toEpochMilli());
				}

				// Remove the first element from the waiting list
				WaitingList.remove(0);

				// Copy the patient from the temporary ArrayList to the correct
				// treatment room as defined by the loop iteration
				TreatmentRoom.add(loop, temp.get(0));

				// Calling patient to treatment room
				System.out.println("Can "
						+ TreatmentRoom.get(loop).getFirstName() + " "
						+ TreatmentRoom.get(loop).getLastName()
						+ " go to Treatment Room " + (loop + 1) + "");

				// Assign set he Treatment Room number in the patient object,
				// this is required when bubblesorting the treatment room to
				// find the patient of lowest priority
				TreatmentRoom.get(loop).setTreatmentRoom(loop);

				// Reset timestamp on patients record - run on separate thread
				// in case network traffic slows down queue execution
				Thread tRTimeClear = new Thread(new QueueAccessClearDischargeTime(
						TreatmentRoom.get(loop).getNhsNumber()));
				tRTimeClear.start();

				// Clear Notes (if any) from previous visit (if any) - run on
				// separate thread in case network traffic slows down queue
				// execution
				Thread tRNotesClear = new Thread(new QueueAccessClearDischargeNotes(
						TreatmentRoom.get(loop).getNhsNumber()));
				tRNotesClear.start();

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
	 * room number of the patient with the lowest priority, that is the patient
	 * with the highest triage number and in the case of two patients with this
	 * triage number the patient who has been in the treatment room for the
	 * shortest period. The treatment room list itself is not bubblesorted as
	 * that would destroy the order of the list and 'move' patients around the
	 * different treatment rooms
	 * 
	 * @return an <code>int</code> which is the element number of the treatment
	 *         room ArrayList which contains the patient of the lowest priority
	 */
	public synchronized int bubbleSortTreatmentRoom() {

		// temporary ArrayList - copy of treatment room
		ArrayList<Patient> tempList = new ArrayList<Patient>();

		// Populate the temporary ArrayList with all the elements from the
		// TreatmentRoom list
		tempList.add(0, TreatmentRoom.get(0));
		tempList.add(1, TreatmentRoom.get(1));
		tempList.add(2, TreatmentRoom.get(2));
		tempList.add(3, TreatmentRoom.get(3));
		tempList.add(4, TreatmentRoom.get(4));

		// Initialise variable for the index of the element
		int indexOfElement = 0;

		// Sort the temporary ArrayList by triage number - lowest first
		sortListByTriage(tempList);

		// If triage number of elements 0 and 1 are the same
		if (tempList.get(0).getTriageNumber() == tempList.get(1)
				.getTriageNumber()) {

			// And if element 0 was added after element 1 to the queue, swap
			// them in the list
			if (tempList.get(0).getAdmissionNumber() > tempList.get(1)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 0, 1);

				// This will ensure that if two patients have negative admission
				// numbers they will be ordered correctly
				if (tempList.get(0).getAdmissionNumber() < 0
						&& tempList.get(1).getAdmissionNumber() < 0) {
					Collections.swap(tempList, 0, 1);
				}
			}
		}

		// if triage number of elements 1 and 2 are the same
		if (tempList.get(1).getTriageNumber() == tempList.get(2)
				.getTriageNumber()) {

			// And if element 1 was added after element 2 to the queue, swap
			// them in the list
			if (tempList.get(1).getAdmissionNumber() > tempList.get(2)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 1, 2);

				// This will ensure that if two patients have negative admission
				// numbers they will be ordered correctly
				if (tempList.get(1).getAdmissionNumber() < 0
						&& tempList.get(2).getAdmissionNumber() < 0) {
					Collections.swap(tempList, 1, 2);
				}
			}
		}

		// if triage number of elements 2 and 3 are the same
		if (tempList.get(2).getTriageNumber() == tempList.get(3)
				.getTriageNumber()) {

			// And if element 2 was added after element 3 to the queue, swap
			// them in the list
			if (tempList.get(2).getAdmissionNumber() > tempList.get(3)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 2, 3);

				// This will ensure that if two patients have negative admission
				// numbers they will be ordered correctly
				if (tempList.get(2).getAdmissionNumber() < 0
						&& tempList.get(3).getAdmissionNumber() < 0) {
					Collections.swap(tempList, 2, 3);
				}
			}
		}

		// if triage number of elements 3 and 4 are the same
		if (tempList.get(3).getTriageNumber() == tempList.get(4)
				.getTriageNumber()) {

			// And if element 3 was added after element 4 to the queue, swap
			// them in the list
			if (tempList.get(3).getAdmissionNumber() > tempList.get(4)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 3, 4);

				// This will ensure that if two patients have negative admission
				// numbers they will be ordered correctly
				if (tempList.get(3).getAdmissionNumber() < 0
						&& tempList.get(4).getAdmissionNumber() < 0) {
					Collections.swap(tempList, 3, 4);

				}
			}
		}

		// Get treatment room number of last element
		indexOfElement = tempList.get(4).getTreatmentRoom();

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

			// Switch on index - element corresponding to each treatment room -
			// elements in an ArrayList start at zero, therefore an index of
			// zero refers to treatment room 1 and so on
			switch (index) {

			// Treatment Room 1
			case 0:

				// Add patient to treatment room 1
				TreatmentRoom.add(index, tempListFromWaiting.get(0));

				// Call patient
				System.out.println("Can "
						+ TreatmentRoom.get(index).getFirstName() + " "
						+ TreatmentRoom.get(index).getLastName()
						+ " go to Treatment Room " + (index + 1) + "");

				// Set Treatment Room in patient
				TreatmentRoom.get(index).setTreatmentRoom(index);
				
				// Reset timestamp on patients record - run on separate thread
				// in case network traffic slows down queue execution
				Thread tR1TimeClear = new Thread(new QueueAccessClearDischargeTime(
						TreatmentRoom.get(index).getNhsNumber()));
				tR1TimeClear.start();

				// Clear Notes (if any) from previous visit (if any) - run on
				// separate thread in case network traffic slows down queue
				// execution
				Thread tR1NotesClear = new Thread(new QueueAccessClearDischargeNotes(
						TreatmentRoom.get(index).getNhsNumber()));
				tR1NotesClear.start();

				// Leave switch statement
				break;

			// Treatment Room 2
			case 1:

				// Add patient to treatment room 2
				TreatmentRoom.add(index, tempListFromWaiting.get(0));

				// Call patient
				System.out.println("Can "
						+ TreatmentRoom.get(index).getFirstName() + " "
						+ TreatmentRoom.get(index).getLastName()
						+ " go to Treatment Room " + (index + 1) + "");

				// Set Treatment Room in patient
				TreatmentRoom.get(index).setTreatmentRoom(index);

				// Reset timestamp on patients record - run on separate thread
				// in case network traffic slows down queue execution
				Thread tR2TimeClear = new Thread(new QueueAccessClearDischargeTime(
						TreatmentRoom.get(index).getNhsNumber()));
				tR2TimeClear.start();

				// Clear Notes (if any) from previous visit (if any) - run on
				// separate thread in case network traffic slows down queue
				// execution
				Thread tR2NotesClear = new Thread(new QueueAccessClearDischargeNotes(
						TreatmentRoom.get(index).getNhsNumber()));
				tR2NotesClear.start();

				// Leave switch statement
				break;

			// Treatment Room 3
			case 2:

				// Add patient to treatment room 3
				TreatmentRoom.add(index, tempListFromWaiting.get(0));

				// Call patient
				System.out.println("Can "
						+ TreatmentRoom.get(index).getFirstName() + " "
						+ TreatmentRoom.get(index).getLastName()
						+ " go to Treatment Room " + (index + 1) + "");

				// Set Treatment Room in patient
				TreatmentRoom.get(index).setTreatmentRoom(index);

				// Reset timestamp on patients record - run on separate thread
				// in case network traffic slows down queue execution
				Thread tR3TimeClear = new Thread(new QueueAccessClearDischargeTime(
						TreatmentRoom.get(index).getNhsNumber()));
				tR3TimeClear.start();

				// Clear Notes (if any) from previous visit (if any) - run on
				// separate thread in case network traffic slows down queue
				// execution
				Thread tR3NotesClear = new Thread(new QueueAccessClearDischargeNotes(
						TreatmentRoom.get(index).getNhsNumber()));
				tR3NotesClear.start();

				// Leave switch statement
				break;

			// Treatment Room 4
			case 3:

				// Add patient to treatment room 4
				TreatmentRoom.add(index, tempListFromWaiting.get(0));

				// Call patient
				System.out.println("Can "
						+ TreatmentRoom.get(index).getFirstName() + " "
						+ TreatmentRoom.get(index).getLastName()
						+ " go to Treatment Room " + (index + 1) + "");

				// Set Treatment Room in patient
				TreatmentRoom.get(index).setTreatmentRoom(index);

				// Reset timestamp on patients record - run on separate thread
				// in case network traffic slows down queue execution
				Thread tR4TimeClear = new Thread(new QueueAccessClearDischargeTime(
						TreatmentRoom.get(index).getNhsNumber()));
				tR4TimeClear.start();

				// Clear Notes (if any) from previous visit (if any) - run on
				// separate thread in case network traffic slows down queue
				// execution
				Thread tR4NotesClear = new Thread(new QueueAccessClearDischargeNotes(
						TreatmentRoom.get(index).getNhsNumber()));
				tR4NotesClear.start();

				// Leave switch statement
				break;

			// Treatment Room 5
			case 4:

				// Add patient to treatment room 5
				TreatmentRoom.add(index, tempListFromWaiting.get(0));

				// Call patient
				System.out.println("Can "
						+ TreatmentRoom.get(index).getFirstName() + " "
						+ TreatmentRoom.get(index).getLastName()
						+ " go to Treatment Room " + (index + 1) + "");

				// Set Treatment Room in patient
				TreatmentRoom.get(index).setTreatmentRoom(index);

				// Reset timestamp on patients record - run on separate thread
				// in case network traffic slows down queue execution
				Thread tR5TimeClear = new Thread(new QueueAccessClearDischargeTime(
						TreatmentRoom.get(index).getNhsNumber()));
				tR5TimeClear.start();

				// Clear Notes (if any) from previous visit (if any) - run on
				// separate thread in case network traffic slows down queue
				// execution
				Thread tR5NotesClear = new Thread(new QueueAccessClearDischargeNotes(
						TreatmentRoom.get(index).getNhsNumber()));
				tR5NotesClear.start();

				// Leave switch statement
				break;
			}

			// for testing purposes
			System.out.println("Swapping patients");

			// Reset treatment room of patient removed from treatment room to
			// the default value
			tempListFromTreatment.get(0).setTreatmentRoom(-1);

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

			// Set boolean to true
			onCallTeamContacted = true;

			// Send SMS to the On Call Team, do this by instantiating SMS
			// class and starting it as a new thread, the SMS class will
			// check back to TheQueue to see if the boolean value
			// onCallTeamContacted is true. Then if true it will extract the
			// mobile numbers of the 2 doctors and 3 nurses that are flagged
			// as on call from the database into an array and send them an
			// sms message, once message is sent the thread will end, this
			// structure means that the queue can continue without delay

			// SendSMS sendSMS = new SendSMS();
			// Thread sms = new Thread(sms);
			// sms.start();

			// for demo purposes
			System.out.println("On-call team called - SMS");

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
			System.out.println("The On Call Team will attend to "
					+ OnCallTeam.get(0).getFirstName() + " "
					+ OnCallTeam.get(0).getLastName() + " now");

			// Set boolean for patient records
			OnCallTeam.get(0).isTreatedByOnCallTeam();

			// Get a new instant of time
			Instant onCallStart = Instant.now();

			// Sets patients end wait time, which will have automatically
			// been started at triage
			OnCallTeam.get(0).setEndTimeWait(onCallStart.toEpochMilli());

			// Set time that on call team started treating patient
			OnCallTeam.get(0).setStartTimeTreat(onCallStart.toEpochMilli());
			
			// Reset timestamp on patients record - run on separate thread
			// in case network traffic slows down queue execution
			Thread oCT2TimeClear = new Thread(new QueueAccessClearDischargeTime(
					OnCallTeam.get(0).getNhsNumber()));
			oCT2TimeClear.start();

			// Clear Notes (if any) from previous visit (if any) - run on
			// separate thread in case network traffic slows down queue
			// execution
			Thread oCT2NotesClear = new Thread(new QueueAccessClearDischargeNotes(
					OnCallTeam.get(0).getNhsNumber()));
			oCT2NotesClear.start();

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
				// or
				// has been waiting for more than 25 without treatment.
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