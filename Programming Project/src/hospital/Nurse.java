package hospital;

public class Nurse extends Staff implements iOnCall {

	/**
	 * Default Constructor
	 */
	public Nurse() {
		// TODO Auto-generated constructor stub
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
	 * @param staffID
	 * @param mobileNumber
	 * @param loginID
	 * @param password
	 */
	public Nurse(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int staffID, long mobileNumber, int loginID,
			String password) {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, staffID, mobileNumber, loginID, password);

	}

	/**
	 * Method which once called will allow the Nurse to amend Patients record
	 */
	public void amendRecord() {

	}

	/**
	 * Method which once called will allow a Nurse to extend the treatment time
	 * of a patient
	 */
	public void extendTreatment() {

	}

	/**
	 * Overridden method from the isOnCall interface to set if the nurse is out
	 * on call
	 */
	@Override
	public void isOnCall() {
		// TODO Auto-generated method stub

	}

}
