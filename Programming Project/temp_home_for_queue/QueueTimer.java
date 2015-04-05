package hospital;

// imports
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class to allow several thread based methods to be run on the different lists
 * within TheQueue class at scheduled times. This has been achieved by using the
 * ScheduledExecutorService and many many hours of head scratching!
 * 
 * @author Kieron
 *
 */
public class QueueTimer {

	/**
	 * ArrayList to hold a temporary copy of Treatment Room list.
	 */
	static ArrayList<Patient> treatmentCopy = new ArrayList<Patient>(5);

	/**
	 * LinkedList to hold first temporary copy of Waiting list.
	 */
	static LinkedList<Patient> waitingCopy1 = new LinkedList<Patient>();

	/**
	 * LinkedList to hold second temporary copy of Waiting list.
	 */
	static LinkedList<Patient> waitingCopy2 = new LinkedList<Patient>();

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
	 * To allow for a patient in Treatment Room 1 be treated for a determined
	 * extra period of time.
	 */
	static long extraTime1 = 0;

	/**
	 * To allow for a patient in Treatment Room 2 be treated for a determined
	 * extra period of time.
	 */
	static long extraTime2 = 0;

	/**
	 * To allow for a patient in Treatment Room 3 be treated for a determined
	 * extra period of time.
	 */
	static long extraTime3 = 0;

	/**
	 * To allow for a patient in Treatment Room 4 be treated for a determined
	 * extra period of time.
	 */
	static long extraTime4 = 0;

	/**
	 * To allow for a patient in Treatment Room 5 be treated for a determined
	 * extra period of time.
	 */
	static long extraTime5 = 0;

	/**
	 * Main method of class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service1 = Executors
				.newSingleThreadScheduledExecutor();
		service1.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Thread which prints the patients from all lists held within the
			 * queue, that is the Treatment Rooms, Waiting List, On Call Team,
			 * Treated Patients and Turned Away Patients. In the final
			 * application the later two will no longer be required.
			 */
			@Override
			public void run() {

				// Get a new instant of time
				Instant now = Instant.now();

				// Print time to screen - some work possibly needed here to make
				// this more user friendly rather than the standard ISO-8601
				// format
				System.out.println(now.toString());

				// Make a copy of all the ArrayLists and LinkedLists in TheQueue
				// - this is done as it reduces the likelihood of
				// ConCurrentModificationException errors which can occur if a
				// list is being Iterated at the same time as its contents are
				// being sorted/reordered. It just does not like it!
				treatmentCopy.addAll(TheQueue.TreatmentRoom);
				waitingCopy1.addAll(TheQueue.WaitingList);
				onCallCopy.addAll(TheQueue.OnCallTeam);
				treatedCopy.addAll(TheQueue.Treated);
				turnedAwayCopy.addAll(TheQueue.SentElsewhere);

				// This method will print out all the lists to screen
				printAll();

				// Once lists have been printed out, they are all cleared in
				// preparation for the next time
				treatmentCopy.clear();
				waitingCopy1.clear();
				onCallCopy.clear();
				treatedCopy.clear();
				turnedAwayCopy.clear();

			} // end of runnable

			// This is how often this thread is run.
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service2 = Executors
				.newSingleThreadScheduledExecutor();
		service2.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Thread which gets the current time and if the On Call Team is
			 * treating a patient passes the time to a method to see if the
			 * patient's treatment has finished.
			 */
			@Override
			public void run() {

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

			} // end of runnable
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service3 = Executors
				.newSingleThreadScheduledExecutor();
		service3.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Thread which gets the current time and passes it to a method to
			 * see if treatment for the patient in Treatment Room 1 has
			 * finished.
			 */
			@Override
			public void run() {

				// New instant of time
				Instant now = Instant.now();

				// Set current time as time since the 1970 epoch in milliseconds
				long currentTime = now.toEpochMilli();

				// If Treatment Room 1 is occupied
				if (TheQueue.TreatmentRoom.get(0) != null) {

					// Check to see if patient has finished their treatment
					endTreatmentRoomTreatment1(currentTime);
				}

			} // end of runnable

			// 20 millisecond delay to stop concurrency issues
		}, 20, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service4 = Executors
				.newSingleThreadScheduledExecutor();
		service4.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Thread which gets the current time and passes it to a method to
			 * see if treatment for the patient in Treatment Room 2 has
			 * finished.
			 */
			@Override
			public void run() {

				// New instant of time
				Instant now = Instant.now();

				// Set current time as time since the 1970 epoch in milliseconds
				long currentTime = now.toEpochMilli();

				// If Treatment Room 2 is occupied
				if (TheQueue.TreatmentRoom.get(1) != null) {

					// Check to see if patient has finished their treatment
					endTreatmentRoomTreatment2(currentTime);
				}

			} // end of runnable

			// 40 millisecond delay to stop concurrency issues
		}, 40, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service5 = Executors
				.newSingleThreadScheduledExecutor();
		service5.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Thread which gets the current time and passes it to a method to
			 * see if treatment for the patient in Treatment Room 3 has
			 * finished.
			 */
			@Override
			public void run() {

				// New instant of time
				Instant now = Instant.now();

				// Set current time as time since the 1970 epoch in milliseconds
				long currentTime = now.toEpochMilli();

				// If Treatment Room 3 is occupied
				if (TheQueue.TreatmentRoom.get(2) != null) {

					// Check to see if patient has finished their treatment
					endTreatmentRoomTreatment3(currentTime);
				}

			} // end of runnable

			// 60 millisecond delay to stop concurrency issues
		}, 60, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service6 = Executors
				.newSingleThreadScheduledExecutor();
		service6.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Thread which gets the current time and passes it to a method to
			 * see if treatment for the patient in Treatment Room 4 has
			 * finished.
			 */
			@Override
			public void run() {

				// New instant of time
				Instant now = Instant.now();

				// Set current time as time since the 1970 epoch in milliseconds
				long currentTime = now.toEpochMilli();

				// If Treatment Room 4 is occupied
				if (TheQueue.TreatmentRoom.get(3) != null) {

					// Check to see if patient has finished their treatment
					endTreatmentRoomTreatment4(currentTime);
				}

			} // end of runnable

			// 80 millisecond delay to stop concurrency issues
		}, 80, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service7 = Executors
				.newSingleThreadScheduledExecutor();
		service7.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Thread which gets the current time and passes it to a method to
			 * see if treatment for the patient in Treatment Room 5 has
			 * finished.
			 */
			@Override
			public void run() {

				// New instant of time
				Instant now = Instant.now();

				// Set current time as time since the 1970 epoch in milliseconds
				long currentTime = now.toEpochMilli();

				// If Treatment Room 5 is occupied
				if (TheQueue.TreatmentRoom.get(4) != null) {

					// Check to see if patient has finished their treatment
					endTreatmentRoomTreatment5(currentTime);
				}

			} // end of runnable

			// Introduced a slight 100 millisecond delay to stop concurrency
			// issues
		}, 100, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service8 = Executors
				.newSingleThreadScheduledExecutor();
		service8.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Run these methods to ensure Waiting List remains sorted correctly
			 * even when no new patients are arriving
			 */
			@Override
			public void run() {

				// Keeps all Priority Patients at the top of the Waiting List
				theQueue.keepPriorityPatientsAtTop();

				// Ensures patients of same priority are ordered correctly
				theQueue.keepPatientsOfSameTriageInOrder();

			} // end of runnable

		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service9 = Executors
				.newSingleThreadScheduledExecutor();
		service9.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Method to set waiting time of patients on the Waiting List
			 */
			@Override
			public void run() {

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

			} // end of runnable

			// 100 milliseconds delay to reduce concurrency issues
		}, 100, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service10 = Executors
				.newSingleThreadScheduledExecutor();
		service10.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Method to print out the status code of the Accident and Emergency
			 * department
			 */
			@Override
			public void run() {

				// Make a temporary copy of the Waiting List
				waitingCopy2.addAll(TheQueue.WaitingList);

				// Call the status method which determines the status code based
				// on queue length and average waiting times
				System.out.println("STATUS CODE: " + status());
				System.out
						.println("-----------------------------------------------------------------");

				// Clear temporary copy of the Waiting List for reuse
				waitingCopy2.clear();

			} // end of runnable

			// 400 millisecond delay to reduce concurrency issues
		}, 400, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		/**
		 * ScheduledExectorService which implements a single thread on a
		 * schedule of once every minute.
		 */
		final ScheduledExecutorService service11 = Executors
				.newSingleThreadScheduledExecutor();
		service11.scheduleWithFixedDelay(new Runnable() {

			/**
			 * Method to contact hospital manager if more than two patients are
			 * waiting for more than 30 minutes for their treatment to commence
			 */
			@Override
			public void run() {

				// Call the checkIfTwoPatientsMoreThan30 method which returns a
				// boolean value, if true contact the hospital manager
				if (checkIfTwoPatientsMoreThan30()) {
					System.out
							.println("More than 2 patients are waiting 30 mins - contact hosp manager");

					// alert to hospital manager

					// SendSMS sendSMS = new SendSMS();
					// Thread sms = new Thread(sendSMS);
					// sms.start();

					// SendEmail sendEmail = new SendEmail();
					// Thread email = new Thread(sendEmail);
					// email.start();

				}

			} // end of runnable

			// 500 millisecond delay to reduce concurrency issues
		}, 500, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

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
	public static void endTreatmentRoomTreatment1(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(0).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + extraTime1)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(0).setEndTimeTreat(currentTime);

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(0));

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
	 * Method to determine if the patient in Treatment Room 2 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public static void endTreatmentRoomTreatment2(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(1).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + extraTime2)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(1).setEndTimeTreat(currentTime);

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(1));

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
	 * Method to determine if the patient in Treatment Room 3 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public static void endTreatmentRoomTreatment3(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(2).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + extraTime3)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(2).setEndTimeTreat(currentTime);

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(2));

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
	 * Method to determine if the patient in Treatment Room 4 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public static void endTreatmentRoomTreatment4(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(3).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + extraTime4)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(3).setEndTimeTreat(currentTime);

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(3));

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
	 * Method to determine if the patient in Treatment Room 5 has completed
	 * their treatment which is standardly 10 minutes, however this can be
	 * extended at the request of a doctor in increments of 5 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public static void endTreatmentRoomTreatment5(long currentTime) {

		// Declare and initialise treatmentTime variable
		long treatmentTime = 0;

		// Declare and initialise startTime variable
		long startTime = TheQueue.TreatmentRoom.get(4).getStartTimeTreat();

		// How treatmentTime is calculated
		treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time, consisting of the
		// standard treatment time and any extra time requested
		if (treatmentTime > (TREATMENT_ROOM_TIME + extraTime5)) {

			// Set treatment room end time to the currentTime
			TheQueue.TreatmentRoom.get(4).setEndTimeTreat(currentTime);

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.TreatmentRoom.get(4));

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
	 * Method to determine if the patient in being treated by the On Call Team
	 * has had their treatment completed, which is standardly 15 minutes.
	 * 
	 * @param currentTime
	 *            , a <code>long</code> which is time in milliseconds since the
	 *            1970 epoch
	 */
	public static void endOnCallTreatment(long currentTime) {

		// Declare and initialise startTime variable
		long startTime = TheQueue.OnCallTeam.get(0).getStartTimeTreat();

		// Declare and initialise treatmentTime
		long treatmentTime = currentTime - startTime;

		// If treatmentTime is greater than the allowed time
		if (treatmentTime > ON_CALL_TREATMENT_TIME) {

			// Let everyone one the On Call team has finished
			System.out.println("On Call Team finished treating patient.");

			// Set the patients end treatment time
			TheQueue.OnCallTeam.get(0).setEndTimeTreat(currentTime);

			// Add patient to the Treated LinkedList
			TheQueue.Treated.add(TheQueue.OnCallTeam.get(0));

			// Clear the OnCallTeam LinkedList for next patient
			TheQueue.OnCallTeam.clear();

			// Set the onCallInSitu boolean to false, thereby allowing the On
			// Call Tean to treat another patient
			TheQueue.onCallInSitu = false;
		}
	}

	/**
	 * Method format the printing of the Treatment Rooms, Waiting List, On Call
	 * Team and when necessary the Treated and SentElsewhere lists as well.
	 */
	public static void printAll() {

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

		System.out.println("Treated Patients");
		System.out.println("================");

		// Call method which uses iterator to print list contents
		printTreated();

		System.out.println("Turned Away Patients");
		System.out.println("====================");

		// Call method which uses iterator to print list contents
		printTurnedAway();

		System.out
				.println("-----------------------------------------------------------------");
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
		Iterator<Patient> Patient = waitingCopy1.iterator();
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
	 * Method which can be called by a doctor to increase a patients period of
	 * treatment in one of the five treatment rooms.
	 * 
	 * @param extend
	 *            , a <code>boolean</code> to say if a patient is having their
	 *            treatment extended or not
	 * @param treatmentRoom
	 *            , an <code>int</code> for the treatment room number
	 */
	public static void extraTreatmentTime(boolean extend, int treatmentRoom) {

		// Declare and initialise variable
		int minutes = 0;

		// If extend is true
		if (extend) {

			// Treatment will be extended by five minutes
			minutes = 5;
		}

		// Switch on the Treatment Room number, conversion is included to
		// convert the minutes into milliseconds
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
		}
	}

	/**
	 * To check if two or more patients currently waiting for treatment have
	 * been waiting for more than 30 minutes.
	 * 
	 * @return a <code>boolean</code>
	 */
	public static boolean checkIfTwoPatientsMoreThan30() {

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
		for (int loop = 0; loop < waitingCopy2.size(); loop++) {
			if (waitingCopy2.get(loop).getEndTimeWait() == 0) {
				count++;
			}
		}

		// For testing purposes
		System.out.println("Patients waiting to be treated = " + count);

		// If the WaitingList is longer than 1 patient
		if (waitingCopy2.size() > 0) {

			// For loop to go through the waiting list and add up the waiting
			// times of those patients whi have not yet received any treatment
			for (int loop = 0; loop < waitingCopy2.size(); loop++) {
				if (waitingCopy2.get(loop).getEndTimeWait() == 0) {
					sumOfWaitTime += waitingCopy2.get(loop)
							.getTimeOnWaitingList();
				}
			}

			// Calculate average waiting time
			averageWaitTime = sumOfWaitTime / count;

			// For testing purposes
			System.out.println("Average wait time is " + averageWaitTime);

			// Follow business rules to set aNEStatus
			if (averageWaitTime > 9 && averageWaitTime < 20) {
				aNEStatus = 2;
			}

			if (averageWaitTime > 19) {
				aNEStatus = 3;
			}

			if (waitingCopy2.size() == 10 && TheQueue.onCallInSitu) {
				aNEStatus = 4;
			}
		}

		// Return status to calling method
		return aNEStatus;
	}
}