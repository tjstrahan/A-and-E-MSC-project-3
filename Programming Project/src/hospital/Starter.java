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
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		// Instantiate new Receptionist object
		Receptionist receptionist = new Receptionist();

		HospitalManager hM = new HospitalManager();
		hM.populateMedicalTeam();
		hM.setOnCallTeam();

		// Set boolean to true
		makeQueueAlive();

		
		try {

			// Get receptionist to start adding patients
			receptionist.admitAllPatients();

		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// For testing purposes
		System.out.println(Receptionist.patientsFromDB.size());

		// Start TriageNurse thread to assign triage categories and add patients
		// into the queueing system
		TriageNurse triNurse = new TriageNurse();
		Thread t1 = new Thread(triNurse);
		t1.start();
		
		// Start the QueueTimeAlt class (named Alt as it was an alternative
		// approach to the original which had used a thread pool approach)
		QueueTimerAlt qta = new QueueTimerAlt();
		Thread t2 = new Thread(qta);
		t2.start();


		
		// manually add patient
		receptionist.admitPatient(111121111);
		
		
		
	}

	/**
	 * Set boolean isAlive to true - while true the queue timer class will run
	 */
	public static void makeQueueAlive() {
		isAlive = true;
	}

	/**
	 * Set boolean isAlive to false - when false the queue timer class will stop
	 */
	public static void makeQueueDead() {
		isAlive = false;
	}
}
