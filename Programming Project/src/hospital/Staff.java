package hospital;

/**
 * This is the class for Staff which extends the person abstract class as its
 * super class
 * 
 * @author Thomas
 *
 */
public class Staff extends Person {

	/**
	 * privately declaring the variable for the staffID as int
	 */
	private int staffID;

	/**
	 * privately declaring the variable for the mobileNumber as long
	 * 
	 */
	private long mobileNumber;

	/**
	 * privately declaring the variable for the loginID as String
	 */
	private int loginID;

	/**
	 * privately declaring the variable for the password as String
	 */
	private String password;

	/**
	 * Constant for the minimum value of the mobile number
	 */
	static final long MOBILE_NUMBER_MIN_LENGTH = (long) 100000000000.00;

	/**
	 * Constant for the maximum value of the mobile number
	 */
	static final long MOBILE_NUMBER_MAX_LENGTH = (long) 999999999999.00;

	/**
	 * Constant for the minimum value of the staff ID
	 */
	static final int STAFF_ID_MIN_LENGTH = 100000;

	/**
	 * Constant for the minimum value of the staff ID
	 */
	static final int STAFF_ID_MAX_LENGTH = 999999;

	/**
	 * Default constructor in place in the event no data is passed in
	 */
	public Staff() {

	}

	/**
	 * Constructor with arguements including those from the superclass
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
	 * @param LoginID
	 * @param password
	 */
	public Staff(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int staffID, long mobileNumber, int loginID,
			String password) {

		// call to the super class constructor
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode);

		// including the get methods from the variables declared in this class
		this.staffID = staffID;
		this.mobileNumber = mobileNumber;
		this.loginID = loginID;
		this.password = password;
	}

	/**
	 * Get method for the Staff ID
	 * 
	 * @return staffID
	 */
	public int getStaffID() {
		return staffID;
	}

	/**
	 * Set the staff ID with validation in place in this method in the event of
	 * an illegal field
	 * 
	 * @param staffID
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void setStaffID(int staffID) throws IllegalArgumentException,
			Exception {

		// check that the staff id must be at least six figure long and not
		// exceed this either
		if (staffID >= STAFF_ID_MIN_LENGTH && staffID <= STAFF_ID_MAX_LENGTH) {
			// if so set the staffID
			this.staffID = staffID;

			// or else if the staff ID is above or below the 6 figures
		} else if (staffID <= STAFF_ID_MIN_LENGTH) {
			// throw an illegal arguement exception displaying an appropriate
			// message
			throw new IllegalArgumentException(
					"Sorry your Staff ID must be at least 6 numbers long");
			// anything else other than the 6 digit figure (for example a
			// String) will throw an exception with an appropriate message
		} else if (staffID >= STAFF_ID_MAX_LENGTH) {
			// throw an illegal arguement exception displaying an appropriate
			// message
			throw new IllegalArgumentException(
					"Sorry your Staff ID must be at least 6 numbers long");
		} else {
			throw new Exception("Sorry what you typed is invalid");
		}
	}

	/**
	 * Get method which will return the mobile number
	 * 
	 * @return
	 */
	public long getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * set Mobile number method which will take in the arguement of the mobile
	 * phone number and assign it to the mobile number var with appropriate
	 * validation for exceptions in the event of an invalid field
	 * 
	 * @param mobileNumber
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void setMobileNumber(long mobileNumber)
			throws IllegalArgumentException, Exception {

		// if the mobile number if 11 digits long set it to the mobile numer
		// variable
		if (mobileNumber >= MOBILE_NUMBER_MIN_LENGTH
				&& mobileNumber <= MOBILE_NUMBER_MAX_LENGTH) {

			this.mobileNumber = mobileNumber;
			// if the mobile number is not 11 digits long
		} else if (mobileNumber <= MOBILE_NUMBER_MIN_LENGTH
				|| mobileNumber >= MOBILE_NUMBER_MAX_LENGTH) {
			// throw a new illegal arguement exception with an appropriate
			// message
			throw new IllegalArgumentException(
					"Sorry your mobile number must be at least 11 numbers long");
			// if anything else
		} else {
			// throw a new illegal arguement exception with an appropriate
			// message
			throw new Exception("Sorry what you typed is invalid");
		}
	}

	/**
	 * Get login ID method
	 * 
	 * @return
	 */
	public int getLoginID() {
		return loginID;
	}

	/**
	 * Set login ID method which will throw an exception if the format is not
	 * correct
	 * 
	 * @param loginID
	 * @throws Exception
	 */
	public void setLoginID(int loginID) throws Exception {
		// loginID is the same as staff ID so boundaries replicated that the
		// staff id must be at least six figure long and not
		// exceed this either
		if (loginID >= STAFF_ID_MIN_LENGTH && loginID <= STAFF_ID_MAX_LENGTH) {
			// if so set the loginID
			this.loginID = loginID;

			// or else if the loginID is above or below the 6 figures
		} else if (loginID <= STAFF_ID_MIN_LENGTH
				|| loginID >= STAFF_ID_MAX_LENGTH) {
			// throw an illegal arguement exception displaying an appropriate
			// message
			throw new IllegalArgumentException(
					"Sorry your login ID must be at least 6 numbers long");
			// anything else other than the 6 digit figure (for example a
			// String) will throw an exception with an appropriate message
		} else {
			throw new Exception("Sorry what you typed is invalid");
		}
	}

	/**
	 * Get password method which will return the password
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * set Password method which will set the passed in arguement password t the
	 * var password if it is of correct format
	 * 
	 * @param password
	 * @throws Exception
	 */
	public void setPassword(String password) throws Exception {
		// check if the password passed in is empty
		if (password.isEmpty()) {
			// if so throw an exception with the approriate message displayed
			throw new Exception("Password can not be left blank");
		}
		// if the password is not 6 letters long at least
		if (password.length() < 6) {
			// if so throw an exception with the approriate message displayed
			throw new Exception("Password must be at least 6 characters");
			// or else set the passed in arguement to the password variable
		} else {
			this.password = password;
		}
	}

	/*
	 * Generate toString method to display all the details of the given staff
	 * but will be overriden when calling particular staff type
	 */
	@Override
	public String toString() {
		return "Staff [staffID=" + staffID + ", mobileNumber=" + mobileNumber
				+ ", loginID=" + loginID + ", password=" + password
				+ ", title=" + title + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", addressLineOne="
				+ addressLineOne + ", addressLineTwo=" + addressLineTwo
				+ ", addressLineThree=" + addressLineThree + ", city=" + city
				+ ", postcode=" + postcode + "]";
	}

}
