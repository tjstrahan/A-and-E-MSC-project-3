package hospital;

import java.util.Date;
import java.util.Random;

public class TriageNurse implements Runnable {

	static TheQueue hospQueue = new TheQueue();
	static Random random = new Random();
	static Date date;
	

	@Override
	public synchronized void run() {

		// to add a patient number for demo
		int admissionNumber = 0;

		do {

			// increment patient number
			admissionNumber++;

			// set triage rating
			Receptionist.patientsFromDB.get(0).setTriageNumber(
					presetTriageNumber());

			// set patient number - demo reasons
			Receptionist.patientsFromDB.get(0).setAdmissionNumber(
					admissionNumber);

			// set time patient added to treatment room or waiting list - this
			// needs to be inside the Queue itself as it will need to be reset
			// depending on if patient is waiting or being treated - put here
			// initially to show that it works
			Receptionist.patientsFromDB.get(0).setStartTimeWait1(new Date().getTime());

			// add patient to Queue
			hospQueue.addToQueue(Receptionist.patientsFromDB.get(0));

			// print Queue
			// hospQueue.printLists();

			// remove first patient from the LinkedList of patients imported
			// from the DB
			// Receptionist.patientsFromDB.addLast(Receptionist.patientsFromDB.getFirst());
			Receptionist.patientsFromDB.removeFirst();

			// pause to represent gaps between patients - set at 2.5 minutes
			try {
				Thread.sleep(73000 / TheQueue.TIME_FACTOR);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// while there are patients in the LinkedList from DB
		} while (Receptionist.patientsFromDB.size() != 0);

		cleanUp();
	}

	public synchronized void cleanUp() {
		for (int loop = 0; loop == 20; loop++) {
			hospQueue.addToQueue(null);
			try {
				Thread.sleep(1200000 / TheQueue.TIME_FACTOR);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// method to randomly assign triage number
	public int presetTriageNumber() {
		int triageRating = 0;
		int triageGenerator = random.nextInt(4);
		switch (triageGenerator) {
		case 0:
			triageRating = 1;
			break;
		case 1:
			triageRating = 2;
			break;
		case 2:
			triageRating = 3;
			break;
		case 3:
			triageRating = 4;
			break;
		}

		return triageRating;
	}
}
