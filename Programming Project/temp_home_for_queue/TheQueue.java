package hospital;

// imports
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

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
	static boolean onCallTeamContacted = false;

	/**
	 * Boolean to indicate if the On Call Team is on the site an treating
	 * patients. Default value is false.
	 */
	static boolean onCallInSitu = false;

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

		System.out.println("New Patient" + patient);
		System.out.println();

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
				keepMovedPatientsAtTop();

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
					keepMovedPatientsAtTop();

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

				// Set flag in patient that they have been treated by the On
				// Call Team
				OnCallTeam.get(0).isTreatedByOnCallTeam();

				// Get a new instance of time
				Instant onCallStart = Instant.now();

				// Sets patients treatment start time
				OnCallTeam.get(0).setStartTimeTreat(onCallStart.toEpochMilli());

			} else {

				// Patient is sent elsewhere
				System.out.println("go elsewhere");

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

				// Get a new instance of time
				Instant endWait = Instant.now();

				// Set end waiting time for patient using new instance of date
				temp.get(0).setEndTimeWait(endWait.toEpochMilli());

				// Remove the first element from the waiting list
				WaitingList.remove(0);

				// Copy the patient from the temporary ArrayList to the correct
				// treatment room as defined by the loop iteration
				TreatmentRoom.add(loop, temp.get(0));

				// Assign set he Treatment Room number in the patient object,
				// this is required when bubblesorting the treatment room to
				// find the patient of lowest priority
				TreatmentRoom.get(loop).setTreatmentRoom(loop);

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

	public int highestTriageTreatmentList(ArrayList<Patient> patient) {

		int highestTriageValue = TreatmentRoom.get(0).getTriageNumber();
		int index = 0;

		for (int loop = 1; loop < TreatmentRoom.size(); loop++) {
			if (TreatmentRoom.get(loop).getTriageNumber() > highestTriageValue) {
				highestTriageValue = TreatmentRoom.get(loop).getTriageNumber();
				index = loop;
			}
		}
		return index;
	}

	public synchronized int bubbleSortTreatmentRoom() {

		// I was gonna try a TreeMap, but you can only sort properly by key,
		// rather than value, then thought about maybe adding in treatment room
		// to the patient - i have changed the treatment room java threads to do
		// that the moment the patient enters the room.

		// now i think we can probably use the bubblesort method from the other
		// class - i was a gonna create a temp arraylist, do the bubblesort
		// based on, triage, then time and the last element of the arraylist
		// will be the one we need to remove, so get treatment room number from
		// that and thats the one we need to swap with waiting list.

		// temp arraylist - copy of treatment room
		ArrayList<Patient> tempList = new ArrayList<Patient>();
		tempList.add(0, TreatmentRoom.get(0));
		tempList.add(1, TreatmentRoom.get(1));
		tempList.add(2, TreatmentRoom.get(2));
		tempList.add(3, TreatmentRoom.get(3));
		tempList.add(4, TreatmentRoom.get(4));

		// initialise variable
		int indexOfElement = 0;

		// sort templist by triagenumber - lowest first
		sortListByTriage(tempList);

		// if triage number of elements 0 and 1 are the same
		if (tempList.get(0).getTriageNumber() == tempList.get(1)
				.getTriageNumber()) {

			// and if element 0 was added after element 1 to the queue, swap
			// them in the list
			if (tempList.get(0).getAdmissionNumber() > tempList.get(1)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 0, 1);
			}
		}

		// if triage number of elements 1 and 2 are the same
		if (tempList.get(1).getTriageNumber() == tempList.get(2)
				.getTriageNumber()) {

			// and if element 1 was added after element 2 to the queue, swap
			// them in the list
			if (tempList.get(1).getAdmissionNumber() > tempList.get(2)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 1, 2);
			}
		}

		// if triage number of elements 2 and 3 are the same
		if (tempList.get(2).getTriageNumber() == tempList.get(3)
				.getTriageNumber()) {

			// and if element 2 was added after element 3 to the queue, swap
			// them in the list
			if (tempList.get(2).getAdmissionNumber() > tempList.get(3)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 2, 3);
			}
		}

		// if triage number of elements 3 and 4 are the same
		if (tempList.get(3).getTriageNumber() == tempList.get(4)
				.getTriageNumber()) {

			// and if element 3 was added after element 4 to the queue, swap
			// them in the list
			if (tempList.get(3).getAdmissionNumber() > tempList.get(4)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 3, 4);
				System.out.println(tempList.get(3).getAdmissionNumber());
				System.out.println(tempList.get(4).getAdmissionNumber());

			//	if (tempList.get(4).getAdmissionNumber() < 0 && tempList.get(3).getAdmissionNumber() < 0) {
			//		Collections.swap(tempList, 4, 3);
			//	}
			}
		}

		// get treatment room number of last element
		indexOfElement = tempList.get(4).getTreatmentRoom();

		// clear temp arraylist
		tempList.clear();

		System.out.println(indexOfElement);

		// return index of element to be swapped out of treatment room
		return indexOfElement;
	}

	public synchronized void moveEmergencyPatients() {

		// temp arraylist
		ArrayList<Patient> tempListFromTreatment = new ArrayList<Patient>();
		ArrayList<Patient> tempListFromWaiting = new ArrayList<Patient>();

		int index;

		index = bubbleSortTreatmentRoom();

		// do this while last patient in treatment room list when it was sorted
		// in the bubblesort method does not have a
		// triage category of 1 (ie is not an emergency) and the first patient
		// on the waiting list does have a triage category of 1 (is an emergency
		// patient)
		if ((TreatmentRoom.get(index).getTriageNumber() != 1)
				&& (WaitingList.get(0).getTriageNumber() == 1)) {

			TreatmentRoom.get(index).setMovedFromTreatRoom(true);
			TreatmentRoom.get(index).setStartTimeTreat(0);
			TreatmentRoom.get(index).setEndTimeTreat(0);

			// copy patient in treatment room to be moved list to temp arraylist
			tempListFromTreatment.add(TreatmentRoom.get(index));

			TreatmentRoom.remove(index);

			newPatNum--;

			tempListFromTreatment.get(0).setAdmissionNumber(newPatNum);

			Instant setWait = Instant.now();
			tempListFromTreatment.get(0).setStartTimeWait(
					setWait.toEpochMilli());

			Instant setTreat = Instant.now();
			WaitingList.get(0).setStartTimeTreat(setTreat.toEpochMilli());
			tempListFromWaiting.add(WaitingList.get(0));
			WaitingList.remove(0);

			switch (index) {
			case 0:
				TreatmentRoom.add(index, tempListFromWaiting.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				break;
			case 1:
				TreatmentRoom.add(index, tempListFromWaiting.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				break;
			case 2:
				TreatmentRoom.add(index, tempListFromWaiting.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				break;
			case 3:
				TreatmentRoom.add(index, tempListFromWaiting.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				break;
			case 4:
				TreatmentRoom.add(index, tempListFromWaiting.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				break;
			}

			System.out.println("Swapping patients");

			// reset treatment room of patient removed from threatment room to
			// the default value
			tempListFromTreatment.get(0).setTreatmentRoom(-1);

			// copy patient from temp arraylist to first element of waiting list
			WaitingList.addFirst(tempListFromTreatment.get(0));

			// clear temp arraylist
			tempListFromTreatment.clear();
			tempListFromWaiting.clear();

		} else if ((TreatmentRoom.get(index).getTriageNumber() == 1)
				&& (WaitingList.getFirst().getTriageNumber() == 1)
				&& !onCallInSitu) {

			// set boolean to true
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

			// set boolean to true
			onCallInSitu = true;

			tempListFromTreatment.add(WaitingList.get(0));

			WaitingList.remove(0);

			// add patient to on call team list - patient removed from this list
			// after 15 minutes - there can only be one patient on this list
			OnCallTeam.add(0, tempListFromTreatment.get(0));
			OnCallTeam.get(0).isTreatedByOnCallTeam();

			Instant onCallStart = Instant.now();
			OnCallTeam.get(0).setStartTimeTreat(onCallStart.toEpochMilli());
		}

	}

	// moving patients who have been in the treatment room and moved out to the
	// top of the waiting list
	public synchronized void keepMovedPatientsAtTop() {

		// temp arraylist
		ArrayList<Patient> temp = new ArrayList<Patient>();

		// for loop
		for (int loop = 0; loop < WaitingList.size(); loop++) {

			// if true
			if (WaitingList.get(loop).isMovedFromTreatRoom()) {

				// copy patient to temp arraylist
				temp.add(WaitingList.get(loop));

				// remove patient from current position in waiting list
				WaitingList.remove(loop);

				// add patient from temp arraylist to start of waiting list
				WaitingList.addFirst(temp.get(0));

			}

			// empty temp array list
			temp.clear();

		}
	}

	// method to sort patients in waiting list that have not been moved from the
	// treatment room by patient number (in otherwords to keep them in the order
	// they should be in relative to when they joined the queue)
	public synchronized void keepPatientsOfSameTriageInOrder() {

		if (WaitingList.size() <= 10) {
			LinkedList<Patient> copyWaitingList = new LinkedList<Patient>(
					WaitingList);

			// instantiate the iterator
			Iterator<Patient> Patient = copyWaitingList.iterator();

			// temporary arraylists to hold the different types of patient when
			// they
			// are copied from the Waiting List Linked List
			ArrayList<Patient> moved = new ArrayList<Patient>();
			ArrayList<Patient> triage1 = new ArrayList<Patient>();
			ArrayList<Patient> triage2 = new ArrayList<Patient>();
			ArrayList<Patient> triage3 = new ArrayList<Patient>();
			ArrayList<Patient> triage4 = new ArrayList<Patient>();
			LinkedList<Patient> orderedWaitingList = new LinkedList<Patient>();

			// into to allow for iteration of the Waiting List
			int element = 0;

			// while there are patients in the Waiting List do...
			while (Patient.hasNext()) {

				// if patient has been previously moved from the treatment room,
				// remember that these patients have already been sorted
				if (Patient.next().isMovedFromTreatRoom()) {

					// add patient to to 'moved' arraylist
					moved.add(copyWaitingList.get(element));

					// advance element number
					element++;

					// for those patients not moved from the treatment room
				} else {

					// switch on triage priority
					switch (copyWaitingList.get(element).getTriageNumber()) {

					// if triage priority equals 1
					case 1:

						// add patient to 'triage1' arraylist
						triage1.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;

					// if triage priority equals 2
					case 2:

						// add patient to 'triage2' arraylist
						triage2.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;

					// if triage priority equals 3
					case 3:

						// add patient to 'triage 3' arraylist
						triage3.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;

					// if triage priority equals 4
					case 4:

						// add patient to 'triage 4' arraylist
						triage4.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;
					}
				}
			}

			// sort each of these array lists based on patient number, which is
			// being used in this file in place of time on queue - patient
			// number
			// gives incorrect results for patients that have been moved because
			// in
			// the actual system when they are moved from the treatment room to
			// the
			// waiting list their treatment room thread will cease and a new
			// waiting
			// list thread will start, therefore i have assigned new patient
			// numbers
			// to mimic this
			sortListByAdmissionNumber(moved);
			Collections.reverse(moved);
			sortListByAdmissionNumber(triage1);
			sortListByAdmissionNumber(triage2);
			sortListByAdmissionNumber(triage3);
			sortListByAdmissionNumber(triage4);

			// addAll elements from each sorted arraylist to the end of the
			// WaitingList linked list
			orderedWaitingList.addAll(moved);
			orderedWaitingList.addAll(triage1);
			orderedWaitingList.addAll(triage2);
			orderedWaitingList.addAll(triage3);
			orderedWaitingList.addAll(triage4);

			copyWaitingList.clear();
			WaitingList.clear();
			WaitingList.addAll(orderedWaitingList);
		}

	}

}