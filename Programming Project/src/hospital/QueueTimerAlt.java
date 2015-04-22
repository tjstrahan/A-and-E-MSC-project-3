package hospital;

// imports
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

import jdbc.QueueAccessAddDischargeTime;

/**
 * The class which allows several methods such as printing lists, checking
 * treatment times etc to be carried out at one minute intervals. Stops when
 * there are no more patients to treat.
 * 
 * @author Kieron
 *
 */
public class QueueTimerAlt implements Runnable {

	/**
	 * Constant for int which refers to a specific email message in Email.java
	 */
	public static final int WAITING_TIME_EXCEEDED_EMAIL = 1;
	
	/**
	 * ArrayList to hold a temporary copy of Treatment Room list.
	 */
	static ArrayList<Patient> treatmentCopy = new ArrayList<Patient>(5);

	/**
	 * LinkedList to hold a temporary copy of Waiting list.
	 */
	static LinkedList<Patient> waitingCopy = new LinkedList<Patient>();

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
			checkTreatmentRoom1();
			checkTreatmentRoom2();
			checkTreatmentRoom3();
			checkTreatmentRoom4();
			checkTreatmentRoom5();
			checkOnCallTeam();
			setWaitingTimes();
			checkPatientsWaitingLongTime();
			copyLists();
			printLists();
			getStatusCode();
			clearLists();
			orderPatients();

			// Pause loop for 1 minute
			try {
				Thread.sleep(numberOfMilliSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			checkIfAlive();
		}

	}

	/**
	 * Method which gets current time and checks if Treatment Room 1 is occupied
	 * then calls a another method to see if the patients treatment has
	 * finished.
	 */
	public void checkTreatmentRoom1() {

		// New instant of time
		Instant now = Instant.now();

		// Set current time as time since the 1970 epoch in milliseconds
		long currentTime = now.toEpochMilli();

		// If Treatment Room 1 is occupied
		if (TheQueue.TreatmentRoom.get(0) != null) {

			// Check to see if patient has finished their treatment
			endTreatmentRoomTreatment1(currentTime);
		}
	}

	/**
	 * Method to determine if the patient in Treatment Room 1 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public void endTreatmentRoomTreatment1(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise NHS number variable
		int NHSNumber = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(0).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + MedicalTeamOperations.extraTime1)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(0).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.TreatmentRoom.get(0).getNhsNumber();

			// Timestamp patients database record - run on separate thread in
			// case network traffic slows down queue timer execution
			Thread tR1 = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			tR1.start();

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(0));

			// If patient had their treatment extended reset treatment room time
			// to standard
			if (MedicalTeamOperations.treatmentRoom1extended) {
				MedicalTeamOperations.extraTime1 = 0;
				MedicalTeamOperations.treatmentRoom1extended = false;
			}

			// Let everyone know the room is now empty
			System.out.println("Treatment Room 1 ready for next patient");

			// Remove patient from the Treatment Room ArrayList
			TheQueue.TreatmentRoom.remove(0);

			// Set Treatment Room 1 (element 0) to null, that is empty
			TheQueue.TreatmentRoom.add(0, null);

			// Reset treatmentTime to zero
			treatmentTime = 0;

			// Run the Fill Treatment Room method in TheQueue
			theQueue.fillTreatmentRoom();
		}

	}

	/**
	 * Method which gets current time and checks if Treatment Room 2 is occupied
	 * then calls a another method to see if the patients treatment has
	 * finished.
	 */
	public void checkTreatmentRoom2() {

		// New instant of time
		Instant now = Instant.now();

		// Set current time as time since the 1970 epoch in milliseconds
		long currentTime = now.toEpochMilli();

		// If Treatment Room 2 is occupied
		if (TheQueue.TreatmentRoom.get(1) != null) {

			// Check to see if patient has finished their treatment
			endTreatmentRoomTreatment2(currentTime);
		}
	}

	/**
	 * Method to determine if the patient in Treatment Room 2 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public void endTreatmentRoomTreatment2(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise NHS number variable
		int NHSNumber = 0;
		
		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(1).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + MedicalTeamOperations.extraTime2)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(1).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.TreatmentRoom.get(1).getNhsNumber();

			// Timestamp patients database record - run on separate thread in
			// case network traffic slows down queue timer execution
			Thread tR2 = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			tR2.start();
			
			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(1));

			// If patient had their treatment extended reset treatment room time
			// to standard
			if (MedicalTeamOperations.treatmentRoom2extended) {
				MedicalTeamOperations.extraTime2 = 0;
				MedicalTeamOperations.treatmentRoom2extended = false;
			}

			// Let everyone know the room is now empty
			System.out.println("Treatment Room 2 ready for next patient");

			// Remove patient from the Treatment Room ArrayList
			TheQueue.TreatmentRoom.remove(1);

			// Set Treatment Room 2 (element 1) to null, that is empty
			TheQueue.TreatmentRoom.add(1, null);

			// Reset treatmentTime to zero
			treatmentTime = 0;

			// Run the Fill Treatment Room method in TheQueue
			theQueue.fillTreatmentRoom();
		}
	}

	/**
	 * Method which gets current time and checks if Treatment Room 3 is occupied
	 * then calls a another method to see if the patients treatment has
	 * finished.
	 */
	public void checkTreatmentRoom3() {

		// New instant of time
		Instant now = Instant.now();

		// Set current time as time since the 1970 epoch in milliseconds
		long currentTime = now.toEpochMilli();

		// If Treatment Room 3 is occupied
		if (TheQueue.TreatmentRoom.get(2) != null) {

			// Check to see if patient has finished their treatment
			endTreatmentRoomTreatment3(currentTime);
		}

	}

	/**
	 * Method to determine if the patient in Treatment Room 3 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public void endTreatmentRoomTreatment3(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise NHS number variable
		int NHSNumber = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(2).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + MedicalTeamOperations.extraTime3)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(2).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.TreatmentRoom.get(2).getNhsNumber();

			// Timestamp patients database record - run on separate thread in
			// case network traffic slows down queue timer execution
			Thread tR3 = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			tR3.start();

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(2));

			// If patient had their treatment extended reset treatment room time
			// to standard
			if (MedicalTeamOperations.treatmentRoom3extended) {
				MedicalTeamOperations.extraTime3 = 0;
				MedicalTeamOperations.treatmentRoom3extended = false;
			}

			// Let everyone know the room is now empty
			System.out.println("Treatment Room 3 ready for next patient");

			// Remove patient from the Treatment Room ArrayList
			TheQueue.TreatmentRoom.remove(2);

			// Set Treatment Room 3 (element 2) to null, that is empty
			TheQueue.TreatmentRoom.add(2, null);

			// Reset treatmentTime to zero
			treatmentTime = 0;

			// Run the Fill Treatment Room method in TheQueue
			theQueue.fillTreatmentRoom();
		}

	}

	/**
	 * Method which gets current time and checks if Treatment Room 4 is occupied
	 * then calls a another method to see if the patients treatment has
	 * finished.
	 */
	public void checkTreatmentRoom4() {

		// New instant of time
		Instant now = Instant.now();

		// Set current time as time since the 1970 epoch in milliseconds
		long currentTime = now.toEpochMilli();

		// If Treatment Room 4 is occupied
		if (TheQueue.TreatmentRoom.get(3) != null) {

			// Check to see if patient has finished their treatment
			endTreatmentRoomTreatment4(currentTime);
		}
	}

	/**
	 * Method to determine if the patient in Treatment Room 4 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public void endTreatmentRoomTreatment4(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise NHS number variable
		int NHSNumber = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(3).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + MedicalTeamOperations.extraTime4)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(3).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.TreatmentRoom.get(3).getNhsNumber();

			// Timestamp patients database record - run on separate thread in
			// case network traffic slows down queue timer execution
			Thread tR4 = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			tR4.start();

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(3));

			// If patient had their treatment extended reset treatment room time
			// to standard
			if (MedicalTeamOperations.treatmentRoom4extended) {
				MedicalTeamOperations.extraTime4 = 0;
				MedicalTeamOperations.treatmentRoom4extended = false;
			}

			// Let everyone know the room is now empty
			System.out.println("Treatment Room 4 ready for next patient");

			// Remove patient from the Treatment Room ArrayList
			TheQueue.TreatmentRoom.remove(3);

			// Set Treatment Room 4 (element 3) to null, that is empty
			TheQueue.TreatmentRoom.add(3, null);

			// Reset treatmentTime to zero
			treatmentTime = 0;

			// Run the Fill Treatment Room method in TheQueue
			theQueue.fillTreatmentRoom();
		}

	}

	/**
	 * Method which gets current time and checks if Treatment Room 5 is occupied
	 * then calls a another method to see if the patients treatment has
	 * finished.
	 */
	public void checkTreatmentRoom5() {

		// New instant of time
		Instant now = Instant.now();

		// Set current time as time since the 1970 epoch in milliseconds
		long currentTime = now.toEpochMilli();

		// If Treatment Room 5 is occupied
		if (TheQueue.TreatmentRoom.get(4) != null) {

			// Check to see if patient has finished their treatment
			endTreatmentRoomTreatment5(currentTime);
		}

	}

	/**
	 * Method to determine if the patient in Treatment Room 5 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public void endTreatmentRoomTreatment5(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise NHS number variable
		int NHSNumber = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(4).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + MedicalTeamOperations.extraTime5)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(4).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.TreatmentRoom.get(4).getNhsNumber();

			// Timestamp patients database record - run on separate thread in
			// case network traffic slows down queue timer execution
			Thread tR5 = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			tR5.start();

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(4));

			// If patient had their treatment extended reset treatment room time
			// to standard
			if (MedicalTeamOperations.treatmentRoom5extended) {
				MedicalTeamOperations.extraTime5 = 0;
				MedicalTeamOperations.treatmentRoom5extended = false;
			}

			// Let everyone know the room is now empty
			System.out.println("Treatment Room 5 ready for next patient");

			// Remove patient from the Treatment Room ArrayList
			TheQueue.TreatmentRoom.remove(4);

			// Set Treatment Room 5 (element 4) to null, that is empty
			TheQueue.TreatmentRoom.add(4, null);

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

			// Let everyone one the On Call team has finished
			System.out.println("On Call Team finished treating patient.");

			// Set the patients end treatment time
			TheQueue.OnCallTeam.get(0).setEndTimeTreat(currentTime);

			// Get NHS number of patient
			NHSNumber = TheQueue.OnCallTeam.get(0).getNhsNumber();
			
			// Timestamp patients database record - run on separate thread in
			// case network traffic slows down queue timer execution
			Thread oCT = new Thread(new QueueAccessAddDischargeTime(NHSNumber));
			oCT.start();
			
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
	 * and Turned Away Patients. In the final application the later two will no
	 * longer be required.
	 */
	public void printTime() {

		// Get a new instant of time
		//Instant now = Instant.now();
		LocalDateTime dateTime = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
			    .withLocale(new Locale("de"));
		String germanDateTime = dateTime.format(formatter);
		// Print time to screen - some work possibly needed here to make
		// this more user friendly rather than the standard ISO-8601
		// format
		//System.out.println(now.toString());
		System.out.println(germanDateTime);

	}

	/**
	 * Method to make copies of all the lists
	 */
	public void copyLists() {

		// Make a copy of all the ArrayLists and LinkedLists in TheQueue
		// - this is done as it reduces the likelihood of
		// ConCurrentModificationException errors which can occur if a
		// list is being Iterated at the same time as its contents are
		// being sorted/reordered. It just does not like it!
		treatmentCopy.addAll(TheQueue.TreatmentRoom);
		waitingCopy.addAll(TheQueue.WaitingList);
		onCallCopy.addAll(TheQueue.OnCallTeam);
		//treatedCopy.addAll(TheQueue.Treated);
		//turnedAwayCopy.addAll(TheQueue.SentElsewhere);

	}

	/**
	 * Method to print out the content of the lists
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

		//System.out.println("Treated Patients");
		//System.out.println("================");

		// Call method which uses iterator to print list contents
		//printTreated();

		//System.out.println("Turned Away Patients");
		//System.out.println("====================");

		// Call method which uses iterator to print list contents
		//printTurnedAway();

	}

	/**
	 * Method to print out the status code of the Accident and Emergency
	 * department
	 */
	public void getStatusCode() {

		// Call the status method which determines the status code based
		// on queue length and average waiting times
		System.out.println("STATUS CODE: " + status());
		System.out
				.println("-----------------------------------------------------------------");

	}

	/**
	 * Method to determine the status of the Accident and Emergency Department
	 * based on the average waiting time and queue length of the department.
	 * 
	 * @return an <code>int</code> in the range 1 to 4 which is the Status code
	 *         for the Accident and Emergency department
	 */
	public int status() {

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
		System.out.println("Patients waiting to be treated = " + count);

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
			System.out.println("Average wait time is " + averageWaitTime);

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

			// SendSMS sendSMS = new SendSMS();
			// Thread sms = new Thread(sendSMS);
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
		waitingCopy.clear();
		onCallCopy.clear();
		//treatedCopy.clear();
		//turnedAwayCopy.clear();

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
	 * TreatmentRoom ArrayList.
	 */
	public static void printTreatment() {
		Iterator<Patient> Patient = treatmentCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method which uses the iterator to iterate through a copy of the
	 * WaitingList LinkedList.
	 */
	public static void printWaiting() {
		Iterator<Patient> Patient = waitingCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method which uses the iterator to iterate through a copy of the
	 * OnCallTeam LinkedList.
	 */
	public static void printOnCall() {
		Iterator<Patient> Patient = onCallCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	/**
	 * Method which uses the iterator to iterate through a copy of the Treated
	 * LinkedList.
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

		if (TheQueue.TreatmentRoom.get(0) == null
				&& TheQueue.TreatmentRoom.get(1) == null
				&& TheQueue.TreatmentRoom.get(2) == null
				&& TheQueue.TreatmentRoom.get(3) == null
				&& TheQueue.TreatmentRoom.get(4) == null
				&& TheQueue.WaitingList.isEmpty()
				&& TheQueue.OnCallTeam.isEmpty()) {

			// If all above then call method to set boolean to false
			Starter.makeQueueDead();
		}
	}

}
