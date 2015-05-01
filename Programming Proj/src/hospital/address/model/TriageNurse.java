package hospital.address.model;

// Imports
import hospital.address.TheQueue;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

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

	static Scanner scanner = new Scanner(System.in);

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

			if (Receptionist.patientsFromDB.get(0).getTriageNumber() == 0) {
				// Set triage rating
				try {
					assignTriageNumber(randomTriageNumber());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				Thread.sleep(59900 / TheQueue.TIME_FACTOR);
				// Thread.sleep((random.nextInt(60000) + 60000)
				// / TheQueue.TIME_FACTOR);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// While there are patients in the LinkedList from database
		} while (Receptionist.patientsFromDB.size() != 0);

	}

	public int randomTriageNumber() {
		return random.nextInt(5);
	}

	public void manualTriageNumber() throws Exception {
		int input = 0;
		System.out.println("Enter triage number");
		input = scanner.nextInt();
		assignTriageNumber(input);
	}

	/**
	 * Method to randomly assign triage number
	 * 
	 * @return
	 * @throws Exception
	 */
	public void assignTriageNumber(int selection) throws Exception {
		int triageRating = 0;
		String triageCat = "";
		switch (selection) {
		case 0:
			triageRating = 1;
			triageCat = "Emergency";
			break;
		case 1:
			triageRating = 1;
			triageCat = "Emergency";
			break;
		case 2:
			triageRating = 2;
			triageCat = "Urgent";
			break;
		case 3:
			triageRating = 3;
			triageCat = "Semi-urgent";
			break;
		case 4:
			triageRating = 4;
			triageCat = "Non-urgent";
			break;

		}

		Receptionist.patientsFromDB.get(0).setTriageNumber(triageRating);
		Receptionist.patientsFromDB.get(0).setTriageCategory(triageCat);

	}
}
