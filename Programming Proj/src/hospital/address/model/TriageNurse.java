package hospital.address.model;

// Imports
import hospital.address.TheQueue;

import java.time.Instant;
import java.util.Random;

/**
 * Triage Nurse class - this represents the triage nurse and is where patients
 * are assigned their triage category and is the entry point to the queue.
 * 
 * @author Kieron
 *
 */
public class TriageNurse implements Runnable {

	/**
	 * Instantiate the Queue class
	 */
	static TheQueue hospQueue = new TheQueue();
	
	/**
	 * Instantiate the Random class
	 */
	static Random random = new Random();

	/**
	 * Main run method of the class where patients are brought in from the
	 * receptionist class, assigned a triage number and their waiting time
	 * started
	 */
	@Override
	public synchronized void run() {

		// Declare and initialise variable
		int admissionNumber = 0;

		do {

			// Increment patient number
			admissionNumber++;

			// Set triage rating
			try {
				Receptionist.patientsFromDB.get(0).setTriageNumber(
						presetTriageNumber());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Set patient's admission number
			Receptionist.patientsFromDB.get(0).setAdmissionNumber(
					admissionNumber);
			
			// Get new instant of time
			Instant startWait = Instant.now();

			// Set time patient added to waiting list in milliseconds since the
			// 1970 epoch
			Receptionist.patientsFromDB.get(0).setStartTimeWait(
					startWait.toEpochMilli());

			// Add patient to Queue
			hospQueue.addToQueue(Receptionist.patientsFromDB.get(0));

			// Remove first patient from the LinkedList of patients imported
			// from the database
			Receptionist.patientsFromDB.removeFirst();

			// Pause to represent gaps between patients - set at 1 to 2 minutes
			try {
				Thread.sleep((random.nextInt(60000) + 60000)
						/ TheQueue.TIME_FACTOR);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// While there are patients in the LinkedList from database
		} while (Receptionist.patientsFromDB.size() != 0);

	}

	/**
	 * Method to randomly assign triage number
	 * @return
	 */
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
