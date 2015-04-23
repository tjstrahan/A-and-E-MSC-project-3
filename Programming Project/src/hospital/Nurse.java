package hospital;

public class Nurse extends Staff {

	/**
	 * number of medical team doctor belongs to
	 */
	private int medicalTeam;

	/**
	 * Default Constructor
	 */
	public Nurse() {
	}

	/**
	 * constructor with arguements
	 * 
	 * @param title
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param dateOfBirth
	 * @param addressLineOne
	 * @param addressLineTwo
	 * @param addressLineThree
	 * @param city
	 * @param postcode
	 * @param contactNumber
	 * @param staffID
	 * @param loginID
	 * @param password
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public Nurse(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, long contactNumber, int staffID, int loginID,
			String password, int medicalTeam)
			throws IllegalArgumentException, Exception {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, contactNumber, staffID, loginID, password);
		setMedicalTeam(medicalTeam);
	
	}

	/**
	 * Get number of nurses on medical team
	 * 
	 * @return
	 */
	public int getMedicalTeam() {
		return medicalTeam;
	}

	/**
	 * Set number of nurses on medical team
	 * 
	 * @param onCallTeam
	 */
	public void setMedicalTeam(int medicalTeam) {
		if (medicalTeam < 1 || medicalTeam > 5) {
			throw new IllegalArgumentException("Invalid number");
		} else {
			this.medicalTeam = medicalTeam;
		}

	}

	/**
	 * Method which once called will allow a nurse to extend the treatment time
	 * of a patient
	 * 
	 * @throws Exception
	 */
	public void extendTreatment() throws Exception {
		MedicalTeamOperations mA = new MedicalTeamOperations();
		mA.extendTreatment();
	}

	/**
	 * Method to allow nurse to access the queue searching methods in queue
	 * operations
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void QueueCommmands() throws IllegalArgumentException, Exception {
		QueueOperations qA = new QueueOperations();
		qA.QueueCommmands();
	}
}
