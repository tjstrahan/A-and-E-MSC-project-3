package hospital.address;

import hospital.address.model.Patient;

/**
 * Runnable class to allow for patients either arriving in the department or
 * those on the waiting list to be given notification about which treatment room
 * they are to attend. It can deal with several patients at one time by virtue
 * of the Treatment Room number being passed into the class. This ensures that
 * each patient has the message displyed for 30 seconds.
 * 
 * @author Kieron
 *
 */
public class CallPatientMessageTreatmentRoom implements Runnable {

	/**
	 * Private instance of Patient
	 */
	private Patient patient;

	/**
	 * Private instance var of Treatment Room
	 */
	private int tRoom;

	/**
	 * Constructor with arguments
	 * 
	 * @param patient
	 *            object
	 * @param tRoom
	 *            , an <code>int</code> representing treatment room element in
	 *            the TreatmentRoom ArrayList
	 */
	public CallPatientMessageTreatmentRoom(Patient patient, int tRoom) {
		this.patient = patient;
		this.tRoom = tRoom;
	}

	/**
	 * Runnable method. After a patient is added the and the 30 second sleep
	 * period, there is a for loop to ensure that the patient added by this
	 * thread is removed. This is done as there can be several messages
	 * displayed at one time
	 */
	@Override
	public void run() {

		MainApp.fxCallNextPatient.add(patient);
		try {
			Thread.sleep(30000 / TheQueue.TIME_FACTOR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int loop = 0; loop < MainApp.fxCallNextPatient.size(); loop++) {
			if (MainApp.fxCallNextPatient.get(loop)
					.getTreatmentRoomArrayElement() == tRoom) {
				MainApp.fxCallNextPatient.remove(loop);
			}
		}
	}
}
