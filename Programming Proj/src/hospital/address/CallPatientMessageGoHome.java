package hospital.address;

import hospital.address.model.Patient;

/**
 * Runnable class used to display message to patients that have been removed
 * from the Waiting List due to the arrival of the a new Emergency Patient. Adds
 * patient to an OberservableList in the Main JavaFX class for a period of 30
 * seconds. Patient is asked to speak to the Receptionist about having to go
 * elsewhere to help reduce possible arguments amongst patients
 * 
 * @author Kieron
 *
 */
public class CallPatientMessageGoHome implements Runnable {

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
	public CallPatientMessageGoHome(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Run method - patient is added to the List and then removed after 30
	 * seconds have elapsed.
	 */
	@Override
	public void run() {

		MainApp.fxByeByePatient.add(patient);
		try {
			Thread.sleep(30000 / TheQueue.TIME_FACTOR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainApp.fxByeByePatient.remove(0);
	}
}
