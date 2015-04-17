package hospital;

/**
 * Class to extend staff class details to class doctor and to implement iOnCall
 * interface
 * 
 * @author James
 *
 */

public class Doctor extends Staff implements iOnCall {

	/**
	 * default constructor for doctor
	 */
	public Doctor() {
	}

	/**
	 * Constructor with args for doctor including a call to the super class
	 * staff constructor
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
	public Doctor(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, long contactNumber, int staffID, int loginID,
			String password) throws IllegalArgumentException, Exception {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, contactNumber, staffID, loginID, password);

	}

	/**
	 * Method to amend record
	 */
	public static void amendRecord() {
	}

	/**
	 * Method to extend treatment
	 */
	public static void extendTreatment() {
	}

	@Override
	public void isOnCall() {
		// TODO Auto-generated method stub

	}
}
