package hospital;

public class Nurse extends Staff implements iOnCall {

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
			String postcode,long contactNumber, int staffID, int loginID,
			String password) throws IllegalArgumentException, Exception {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, contactNumber, staffID, loginID, password);
	}

	/**
	 * Method which once called will allow a nurse to extend the treatment time
	 * of a patient
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
	
	/**
	 * Overridden method from the isOnCall interface to set if the nurse is out
	 * on call
	 */
	@Override
	public void isOnCall() {
		
		this.isOnCallToString();

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String isOnCallToString() {
		return "Nurse " + getTitle() + " " + getFirstName()
				+ " " + getMiddleName() + " " + getLastName()
				+ "is on call";
	}
	
	

}
