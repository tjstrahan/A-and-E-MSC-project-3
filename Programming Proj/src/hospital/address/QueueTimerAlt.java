package hospital.address;

// imports
import hospital.address.model.Patient;
import hospital.address.model.Status;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

import hospital.address.TheQueue;
import hospital.address.MainApp;
import hospital.address.jdbc.QueueAccessAddDischargeTime;
import hospital.address.jdbc.QueueAccessClearDischargeNotes;

/**
 * The class which allows several methods such as printing lists, checking
 * treatment times etc to be carried out at one minute intervals. The list
 * printing facility is for testing purposes as the lists are displayed
 * graphically using a JavaFX GUI
 * 
 * @author Kieron
 *
 */
public class QueueTimerAlt implements Runnable {

	/**
	 * Constant for int which refers to a specific email message in Email.java
	 */
	static final int WAITING_TIME_EXCEEDED_EMAIL = 1;

	/**
	 * Variable to be passed to sms method to select particular message.
	 */
	static final int SMS_TO_HOSP_MANAGER = 1;

	/**
	 * ArrayList to hold a temporary copy of Treatment Room list. 
	 */
	static ArrayList<Patient> treatmentCopy = new ArrayList<Patient>();

	/**
	 * LinkedList to hold a temporary copy of Waiting list.
	 */
	static LinkedList<Patient> waitingCopy = new LinkedList<Patient>();

	/**
	 * LinkedList copy of the temporary copy of Treatment Room list used for
	 * display purposes in JavaFX
	 */
	public static LinkedList<Patient> forDisplay = new LinkedList<Patient>();

	/**
	 * LinkedList to hold a status object which stores the status code for
	 * Accident and Emergency, use for display purposes by Java FX
	 */
	public static LinkedList<Status> statusCodeList = new LinkedList<Status>();

	/**
	 * LinkedList to hold a temporary copy of who the On Call Team is treating.
	 */
	static LinkedList<Patient> onCallCopy = new LinkedList<Patient>();

	/**
	 * LinkedList to hold a temporary copy of the list of successfully treated
	 * patients.
	 */
	static LinkedList<Patient> treatedCopy = new LinkedList<Patient>();

	/**
	 * LinkedList to hold a temporary copy of the list of patients who have been
	 * told to attend a different hospital.
	 */
	static LinkedList<Patient> turnedAwayCopy = new LinkedList<Patient>();

	/**
	 * Static to determine how long the On Call team takes to treat a patient.
	 * It is preset as 15 minutes and will change proportionately based on the
	 * TIME_FACTOR constant defined in the queue itself. It is defined in
	 * milliseconds.
	 */
	static final long ON_CALL_TREATMENT_TIME = 900000 / TheQueue.TIME_FACTOR;

	/**
	 * Static to determine how long the a patient is in a treatment room. It is
	 * preset as 10 minutes and will change proportionately based on the
	 * TIME_FACTOR constant defined in the queue itself. It is defined in
	 * milliseconds.
	 */
	static final long TREATMENT_ROOM_TIME = 600000 / TheQueue.TIME_FACTOR;

	/**
	 * Static to determine the interval between each iteration / pulse of the
	 * methods within this class. It is set as 1 minute and for consistency with
	 * all other time units is defined in milliseconds.
	 */
	static final long numberOfMilliSeconds = 60000 / TheQueue.TIME_FACTOR;

	/**
	 * Static to state the extra time which can be allocated to a patients
	 * treatment in a treatment room should a member of the medical team extend
	 * their treatment
	 */
	static final long FIVE_MINUTES = 300000 / TheQueue.TIME_FACTOR;

	/**
	 * Create an instance of the TheQueue class so it can be used throughout the
	 * class.
	 */
	static TheQueue theQueue = new TheQueue();

	/**
	 * Create an instance of the Patient class so it can be used throughout the
	 * class.
	 */
	static Patient patient = new Patient();

	/**
	 * Run method of class
	 */
	@Override
	public void run() {

		// While alive
		while (Starter.isAlive) {

			printTime();
			checkTreatmentRooms();
			checkOnCallTeam();
			setWaitingTimes();
			checkPatientsWaitingLongTime();
			MainApp.clearFxTreatmentList();
			MainApp.clearFxWaitingList();
			MainApp.clearFxOnCallList();
			MainApp.clearFxStatus();
			copyLists();
			makeForDisplay();
			getStatusCode();
			MainApp.copyFxTreatmentListAgain();
			MainApp.copyFxWaitingListAgain();
			MainApp.copyFxOnCallListAgain();
			MainApp.copyFxStatusAgain();
			// printLists(); was used for testing purposes
			orderPatients();

			// Pause loop for 1 minute
			try {
				Thread.sleep(numberOfMilliSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			checkIfAlive();
			clearLists();
		}

	}

	/**
	 * Method to remove null values from the treatment room ArrayList and add to
	 * a linkedlist for display purposes
	 */
	public void makeForDisplay() {

		for (int loop = 0; loop < treatmentCopy.size(); loop++) {

			if (treatmentCopy.get(loop) != null) {
				forDisplay.add(treatmentCopy.get(loop));
			}
		}
	}

	/**
	 * Method which gets current time and checks if each Treatment Room is
	 * occupied then calls a another method to see if the patients treatment has
	 * finished.
	 */
	public void checkTreatmentRooms() {

		// New instant of time
		Instant now = Instant.now();

		// Set current time as time since the 1970 epoch in milliseconds
		long currentTime = now.toEpochMilli();

		// For loop to iterate through each of the treatment rooms
		for (int loop = 0; loop < TheQueue.NUMBER_OF_TREATMENT_ROOMS; loop++) {

			// If Treatment Room is occupied
			if (TheQueue.TreatmentRoom.get(loop) != null) {

				// Check to see if patient has finished their treatment
				endTreatmentRoomTreatment(loop, currentTime);
			}
		}
	}

	/**
	 * Method to determine if the patient in a Treatment Room has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor by 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public void endTreatmentRoomTreatment(int loop, long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0L;

		// Declare and initialise extraTime variable
		long extraTime = 0L;

		// Declare and initialise NHS number variable
		int NHSNumber = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(loop).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// Check if patient has been given extra treatment time
		if (TheQueue.TreatmentRoom.get(loop).isExtraTime()) {
			extraTime = FIVE_MINUTES;
		}

		// If treatmentTime is greater than the allowed time, consisting of
		// the standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + extraTime)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(loop).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.TreatmentRoom.get(loop).getNhsNumber();

			// Timestamp patients database record - run on separate thread
			// in case network traffic slows down queue timer execution - will
			// overwrite any previous entry
			Thread tR1 = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			tR1.start();

			// Clear Notes if doctor has not made any new notes - i.e. clear
			// notes from previous visit - run on a separate thread in case
			// network traffic would caue a delay in queue timer operation
			if (!TheQueue.TreatmentRoom.get(loop).isMadeNewNote()) {
				Thread tR1NotesClear = new Thread(
						new QueueAccessClearDischargeNotes(NHSNumber));
				tR1NotesClear.start();
			}

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(loop));

			// Used for testing purposes
			// Let everyone know the room is now empty
			// System.out.println("Treatment Room " + (loop + 1)
			// + " ready for next patient");

			// Remove patient from the Treatment Room ArrayList
			TheQueue.TreatmentRoom.remove(loop);

			// Set Treatment Room 1 (element 0) to null, that is empty
			TheQueue.TreatmentRoom.add(loop, null);

			// Reset treatmentTime to zero
			treatmentTime = 0;

			// Run the Fill Treatment Room method in TheQueue
			theQueue.fillTreatmentRoom();
		}

	}

	/**
	 * Method which gets current time and checks if On Call Team is busy then
	 * calls a another method to see if the patients treatment has finished.
	 */
	public void checkOnCallTeam() {

		// New instant of time
		Instant now = Instant.now();

		// Set current time as time since the 1970 epoch in milliseconds
		long currentTime = now.toEpochMilli();

		// Check if the On Call team is in situ
		if (TheQueue.onCallInSitu) {

			// Check to see if the on call team has finished their
			// treatment
			endOnCallTreatment(currentTime);
		}
	}

	/**
	 * Method to determine if the patient in being treated by the On Call Team
	 * has had their treatment completed, which is standardly 15 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public void endOnCallTreatment(long currentTime) {

		// Declare and initialise startTime variable
		long startTime = TheQueue.OnCallTeam.get(0).getStartTimeTreat();

		// Declare and initialise NHS number variable
		int NHSNumber = 0;

		// Declare and initialise treatmentTime
		long treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time
		if (treatmentTime > ON_CALL_TREATMENT_TIME) {

			// Let everyone one the On Call team has finished - testing
			// System.out.println("On Call Team finished treating patient.");

			// Set the patients end treatment time
			TheQueue.OnCallTeam.get(0).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.OnCallTeam.get(0).getNhsNumber();

			// Timestamp patients database record - run on separate thread in
			// case network traffic slows down queue timer execution - will
			// overwrite any previous entry
			Thread oCT = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			oCT.start();

			// Clear Notes if doctor has not made any new notes - ie clear notes
			// from previous visit
			if (!TheQueue.OnCallTeam.get(0).isMadeNewNote()) {
				Thread oCTNotesClear = new Thread(
						new QueueAccessClearDischargeNotes(NHSNumber));
				oCTNotesClear.start();
			}

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.OnCallTeam.get(0));

			// Clear the OnCallTeam LinkedList for next patient
			TheQueue.OnCallTeam.clear();

			// Set the onCallInSitu boolean to false, thereby allowing the On
			// Call Team to treat another patient
			TheQueue.onCallInSitu = false;
		}
	}

	/**
	 * Method to set waiting time of patients on the Waiting List
	 */
	public void setWaitingTimes() {

		// New instant of time
		Instant now = Instant.now();

		// If Waiting List is NOT empty
		if (!TheQueue.WaitingList.isEmpty()) {

			// For loop to iterate through the Waiting List
			for (int loop = 0; loop < TheQueue.WaitingList.size(); loop++) {

				// Set the time spent waiting for each patient, achieved
				// by sending the number of milliseconds since the 1970
				// epoch to the setTimeOnWaitingList method in the
				// Patient class
				TheQueue.WaitingList.get(loop).setTimeOnWaitingList(
						now.toEpochMilli());
			}
		}
	}

	/**
	 * Method which prints the patients from all lists held within the queue,
	 * that is the Treatment Rooms, Waiting List, On Call Team, Treated Patients
	 * and Turned Away Patients. In the final application this method will be
	 * redundant. Used for testing purposes
	 */
	public void printTime() {

		// Get a new instant of time
		// Instant now = Instant.now();
		LocalDateTime dateTime = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(
				FormatStyle.SHORT).withLocale(new Locale("de"));
		String germanDateTime = dateTime.format(formatter);
		// Print time to screen - some work possibly needed here to make
		// this more user friendly rather than the standard ISO-8601
		// format
		// System.out.println(now.toString());
		// System.out.println(germanDateTime);

	}

	/**
	 * Method to make copies of all the lists
	 */
	public void copyLists() {

		// Make a copy of all the ArrayLists and LinkedLists in TheQueue
		// - this is done as it reduces the likelihood of
		// ConCurrentModificationException errors which can occur if a
		// list is being Iterated at the same time as its contents are
		// being sorted/reordered.
		treatmentCopy.addAll(TheQueue.TreatmentRoom);
		waitingCopy.addAll(TheQueue.WaitingList);
		onCallCopy.addAll(TheQueue.OnCallTeam);

		// These used for testing purposes
		// treatedCopy.addAll(TheQueue.Treated);
		// turnedAwayCopy.addAll(TheQueue.SentElsewhere);

	}

	/**
	 * Method to print out the content of the lists - this is not used in the
	 * final application and is here for testing/debugging purposes
	 */
	public void printLists() {

		System.out.println("Treatment Room");
		System.out.println("==============");

		// Call method which uses iterator to print list contents
		printTreatment();

		System.out.println("Waiting List");
		System.out.println("============");

		// Call method which uses iterator to print list contents
		printWaiting();

		System.out.println("On Call Team Treating");
		System.out.println("=====================");

		// Call method which uses iterator to print list contents
		printOnCall();

		// Below wee used during testing
		// System.out.println("Treated Patients");
		// System.out.println("================");

		// Call method which uses iterator to print list contents
		// printTreated();

		// System.out.println("Turned Away Patients");
		// System.out.println("====================");

		// Call method which uses iterator to print list contents
		// printTurnedAway();

	}

	/**
	 * Method to add status code to linked list which is displayed in GUI of the
	 * Accident and Emergency department
	 */
	public static void getStatusCode() {

		// used for testing
		// System.out.println("STATUS CODE: " + status());

		// Call the status method which determines the status code based
		// on queue length and average waiting times and create new Status
		// object
		statusCodeList.add(new Status(status()));
	}

	/**
	 * Method to determine the status of the Accident and Emergency Department
	 * based on the average waiting time and queue length of the department.
	 * 
	 * @return an <code>int</code> in the range 1 to 4 which is the Status code
	 *         for the Accident and Emergency department
	 */
	public static int status() {

		// Declare and initialise variable
		int sumOfWaitTime = 0;
		int averageWaitTime = 0;
		int aNEStatus = 1;
		int count = 0;

		// For loop to count how many patients on the WaitingList (a copy is
		// used as it reduces the likelihood of currency problems that can occur
		// when constantly accessing the 'live' list) are actively waiting for
		// treatment, ie their endWaitTime will be equal to zero
		for (int loop = 0; loop < waitingCopy.size(); loop++) {
			if (waitingCopy.get(loop).getEndTimeWait() == 0) {
				count++;
			}
		}

		// For testing purposes
		// System.out.println("Patients waiting to be treated = " + count);

		// If the WaitingList is longer than 1 patient
		if (waitingCopy.size() > 0) {

			// For loop to go through the waiting list and add up the waiting
			// times of those patients whi have not yet received any treatment
			for (int loop = 0; loop < waitingCopy.size(); loop++) {
				if (waitingCopy.get(loop).getEndTimeWait() == 0) {
					sumOfWaitTime += waitingCopy.get(loop)
							.getTimeOnWaitingList();
				}
			}

			// If all patients on the Waiting List have been moved from a
			// Treatment Room the count will be zero. This is to prevent a
			// divide by zero error
			if (count == 0) {

				averageWaitTime = 0;
			} else {

				// Calculate average waiting time
				averageWaitTime = sumOfWaitTime / count;
			}

			// For testing purposes
			// System.out.println("Average wait time is " + averageWaitTime);

			// Follow business rules to set aNEStatus
			if (averageWaitTime > 9 && averageWaitTime < 20) {
				aNEStatus = 2;
			}

			if (averageWaitTime > 19) {
				aNEStatus = 3;
			}

			if (waitingCopy.size() == 10 && TheQueue.onCallInSitu) {
				aNEStatus = 4;
			}
		}

		// Return status to calling method
		return aNEStatus;
	}

	/**
	 * Method to contact hospital manager if more than two patients are waiting
	 * for more than 30 minutes for their treatment to commence
	 */
	public void checkPatientsWaitingLongTime() {

		// Call the checkIfTwoPatientsMoreThan30 method which returns a
		// boolean value, if true contact the hospital manager
		if (checkIfTwoPatientsMoreThan30()) {

			// Send SMS to the On Call Team on new thread
			// Thread sms = new Thread(new SendSMS(SMS_TO_HOSP_MANAGER));
			// sms.start();

			// Thread to send email to hospital manager
			Thread email1 = new Thread(new Email(WAITING_TIME_EXCEEDED_EMAIL));
			email1.start();
		}
	}

	/**
	 * To check if two or more patients currently waiting for treatment have
	 * been waiting for more than 30 minutes.
	 * 
	 * @return a <code>boolean</code>
	 */
	public boolean checkIfTwoPatientsMoreThan30() {

		// Declare and initialise variables
		int count = 0;
		boolean twoPatientsMoreThan30 = false;

		// A for loop to iterate through the WaitingList to count how many
		// patients have the isWaitingForMoreThan30 boolean set to true
		for (int loop = 0; loop < TheQueue.WaitingList.size(); loop++) {
			if (TheQueue.WaitingList.get(loop).isWaitingMoreThan30()) {
				count++;
			}
		}

		// Switch on the count value obtained from the above loop to see if the
		// condition has been set for the twoPatientsMoreThan30 to be set at
		// true
		switch (count) {
		case 1:
			twoPatientsMoreThan30 = false;
			break;
		case 2:
			twoPatientsMoreThan30 = true;
			break;
		}

		// Return true or false
		return twoPatientsMoreThan30;

	}

	/**
	 * Method to clear the temporary lists in preparation for re-use
	 */
	public void clearLists() {

		// Once lists have been printed out, they are all cleared in
		// preparation for the next time
		treatmentCopy.clear();
		forDisplay.clear();
		waitingCopy.clear();
		onCallCopy.clear();
		statusCodeList.clear();
		// treatedCopy.clear();
		// turnedAwayCopy.clear();

	}

	/**
	 * Method to ensure that patients remain ordered even when no new patient
	 * has arrived
	 */
	public void orderPatients() {

		// Keeps all Priority Patients at the top of the Waiting List
		theQueue.keepPriorityPatientsAtTop();

		// Ensures patients of same priority are ordered correctly
		theQueue.keepPatientsOfSameTriageInOrder();

	}

	/**
	 * Method which uses the iterator to iterate through a copy of the
	 * TreatmentRoom ArrayList. - testing purposes
	 */
	public static void printTreatment() {
		Iterator<Patient> Patient = treatmentCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method which uses the iterator to iterate through a copy of the
	 * WaitingList LinkedList. - testing purposes
	 */
	public static void printWaiting() {
		Iterator<Patient> Patient = waitingCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method which uses the iterator to iterate through a copy of the
	 * OnCallTeam LinkedList. - testing purposes
	 */
	public static void printOnCall() {
		Iterator<Patient> Patient = onCallCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method which uses the iterator to iterate through a copy of the Treated
	 * LinkedList. - testing purposes
	 */
	public static void printTreated() {
		Iterator<Patient> Patient = treatedCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method which uses the iterator to iterate through a copy of the
	 * SentElsewhere LinkedList.
	 */
	public static void printTurnedAway() {
		Iterator<Patient> Patient = turnedAwayCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method to check if there are any patients being treated or waiting. If no
	 * patients, ie department closed, the while above will stop
	 */
	public void checkIfAlive() {

		int countNulls = 0;
		boolean checkForNull = false;

		// For loop to iterate through each of the treatment rooms
		for (int loop = 0; loop < TheQueue.NUMBER_OF_TREATMENT_ROOMS; loop++) {
			if (TheQueue.TreatmentRoom.get(loop) == null) {
				countNulls++;
			}
		}

		if (countNulls == TheQueue.NUMBER_OF_TREATMENT_ROOMS) {
			checkForNull = true;
		}

		if (checkForNull && TheQueue.WaitingList.isEmpty()
				&& TheQueue.OnCallTeam.isEmpty()) {

			// If all above then call method to set boolean to false
			Starter.makeQueueDead();
		}
	}

}
