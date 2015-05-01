package hospital.address;

import hospital.address.model.Patient;

/**
 * Runnable class used to display message to patients that will be seen by the
 * On Call team. Adds patient to an ObservableList in the Main JavaFX class for
 * a period of 30 seconds
 * 
 * @author Kieron
 *
 */
public class CallPatientMessageOnCallTeam implements Runnable {

	/**
	 * Private instance of Patient - done to allow a Patient Object to be passed
	 * into this class
	 */
	private Patient patient;

	/**
	 * Constructor with arguments
	 * 
	 * @param patient
	 *            object
	 */
	public CallPatientMessageOnCallTeam(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Run method - patient is added to the List and then removed after 30
	 * seconds have elapsed.
	 */
	@Override
	public void run() {

		MainApp.fxCallOnCallPatient.add(patient);
		try {
			Thread.sleep(30000 / TheQueue.TIME_FACTOR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainApp.fxCallOnCallPatient.remove(0);
	}
}
