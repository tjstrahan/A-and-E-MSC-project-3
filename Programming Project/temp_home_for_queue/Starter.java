package hospital;

/**
 * Starter class for the whole system
 * 
 * @author Kieron
 *
 */
public class Starter {

	/**
	 * Boolean to state if the queueing system is active
	 */
	public static boolean isAlive = false;

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Set boolean to true
		makeQueueAlive();

		// Get receptionist to start adding patients
		Receptionist.addPatientsFromDB();

		// Start TriageNurse thread to assign triage categories and add patients
		// into the queueing system
		TriageNurse triNurse = new TriageNurse();
		Thread t1 = new Thread(triNurse);
		t1.start();

		// Start the QueueTimeAlt class (only alt as it was an alternative
		// approach to the original)
		QueueTimerAlt qta = new QueueTimerAlt();
		Thread t2 = new Thread(qta);
		t2.start();

	}

	/**
	 * Set boolean isAlive to true
	 */
	public static void makeQueueAlive() {
		isAlive = true;
	}

	/**
	 * Set boolean isAlive to false
	 */
	public static void makeQueueDead() {
		isAlive = false;
	}
}
