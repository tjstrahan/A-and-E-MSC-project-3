
package hospital.address.model;

/**
 * Abstract class for Person
 * 
 * @author James Maguire
 * @author Janine Kelly
 */

public abstract class Person {

	/**
	 * Instance Var to store the title. Title can only be
	 * Mr/Ms/Mrs/Miss/Dr/Rev/Prof
	 */
	private String title;

	/**
	 * Instance var to store the first Name.
	 */
	private String firstName;

	/**
	 * Instance var to store the middle Name. Can be NULL.
	 */
	private String middleName;

	/**
	 * Instance var to store the last name.
	 */
	private String lastName;

	/**
	 * Instance var to store the date of birth.
	 */
	private String dateOfBirth;

	/**
	 * Instance var to store Address Line One(1) Expected Format - YYYY/MM/DD
	 */
	private String addressLineOne;

	/**
	 * Instance var to store Address Line Two(2). Can be NULL
	 */
	private String addressLineTwo;

	/**
	 * Instance var to store Address Line Three(3). Can be NULL
	 */
	private String addressLineThree;

	/**
	 * Instance var to store the city Name.
	 */
	private String city;

	/**
	 * Instance var to store the postcode. Expected Format. e.g - AA1 1AA
	 */
	private String postcode;
	
	/**
	 * Contact telephone number fror the patient
	 */
	private long contactNumber;
	
	/**
	 * Constant for the minimum value of the mobile number
	 */
	static final long MOBILE_NUMBER_MIN_LENGTH = 100000000000L;

	/**
	 * Constant for the maximum value of the mobile number
	 */
	static final long MOBILE_NUMBER_MAX_LENGTH = 999999999999L;


	/**
	 * Default Constructor
	 */
	public Person() {

	}

	/**
	 * Constructor with arguments
	 * 
	 * @param string
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
	 * @throws Exception 
	 * @throws IllegalArgumentException
	 */
	public Person(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, long contactNumber) throws IllegalArgumentException,Exception  {

		setTitle(title);
		setFirstName(firstName);
		this.middleName = middleName;
		setLastName(lastName);
		setDateOfBirth(dateOfBirth);
		setAddressLineOne(addressLineOne);
		this.addressLineTwo = addressLineTwo;
		this.addressLineThree = addressLineThree;
		setContactNumber(contactNumber);
		setCity(city);
		setPostcode(postcode);
	}

	/**
	 * Get method for title
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set method for title
	 * 
	 * @param title
	 */
	public void setTitle(String title) throws Exception {
		if (title.equals("Mr") || title.equals("Mrs") || title.equals("Miss") || title.equals("Ms")
				|| title.equals("Dr") || title.equals("Rev") || title.equals("Prof")
				|| title.equals("Sir")) {
			this.title = title;
		} else {
			throw new Exception("Title is invalid.");
		}
	}
	
	/**
	 * Get method for firstName
	 * 
	 * @return firstName
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set method for firstName
	 * 
	 * @param firstName
	 */

	public void setFirstName(String firstName) throws Exception {
		if (!firstName.isEmpty()) {
			this.firstName = firstName;
		} else
			throw new Exception("First name cannot be left blank.");

	}

	/**
	 * Get method for middleName
	 * 
	 * @return middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Set method for middleName
	 * 
	 * @param middleName
	 */

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Get method for lastName
	 * 
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set method for lastName
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) throws Exception {
		if (!lastName.isEmpty()) {
			this.lastName = lastName;
		} else
			throw new Exception("Last name cannot be left blank.");

	}

	/**
	 * Get method for dateOfBirth
	 * 
	 * @return dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Set method for dateOfBirth
	 * 
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(String dateOfBirth) throws Exception {
		if (!dateOfBirth.isEmpty()) {
			this.dateOfBirth = dateOfBirth;
		} else
			throw new Exception("D.O.B. cannot be left blank.");

	}

	/**
	 * Get method for addressLineOne
	 * 
	 * @return addressLineOne
	 */
	public String getAddressLineOne() {
		return addressLineOne;
	}

	/**
	 * Set method for addressLineOne
	 * 
	 * @param addressLineOne
	 */
	public void setAddressLineOne(String addressLineOne) throws Exception {
		if (!addressLineOne.isEmpty()) {
			this.addressLineOne = addressLineOne;
		} else
			throw new Exception("An address must be entered.");

	}

	/**
	 * Get method for addressLineTwo
	 * 
	 * @return addressLineTwo
	 */
	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	/**
	 * Set method for addressLineTwo
	 * 
	 * @param addressLineTwo
	 */
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	/**
	 * Get method for addressLineThree
	 * 
	 * @return addressLineThree
	 */
	public String getAddressLineThree() {
		return addressLineThree;
	}

	/**
	 * Set method for addressLineThree
	 * 
	 * @param addressLineThree
	 */
	public void setAddressLineThree(String addressLineThree) {
		this.addressLineThree = addressLineThree;
	}

	/**
	 * Get method for city
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set method for city
	 * 
	 * @param city
	 */
	public void setCity(String city) throws Exception {
		if (!city.isEmpty()) {
			this.city = city;
		} else
			throw new Exception("A city must be entered.");
	}

	/**
	 * Get method for postcode
	 * 
	 * @return postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * Set method for postcode
	 * 
	 * @param postcode
	 */
	public void setPostcode(String postcode) throws Exception {
		if (!postcode.isEmpty()) {
			this.postcode = postcode;
		} else
			throw new Exception("A postcode must be entered.");
	}

	/**
	 * Get contact number of the patient
	 * 
	 * @return
	 */
	public long getContactNumber() {
		return contactNumber;
	}

	/**
	 * Set Contact number method which will take in the argument of the mobile
	 * phone number and assign it to the mobile number var with appropriate
	 * validation for exceptions in the event of an invalid field
	 * 
	 * @param contactNumber
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void setContactNumber(long contactNumber)
			throws IllegalArgumentException, Exception {

		// if the mobile number if 11 digits long set it to the mobile number
		// variable
		if (contactNumber >= MOBILE_NUMBER_MIN_LENGTH
				&& contactNumber <= MOBILE_NUMBER_MAX_LENGTH) {

			this.contactNumber = contactNumber;
			// if the mobile number is not 11 digits long
		} else if (contactNumber < MOBILE_NUMBER_MIN_LENGTH
				|| contactNumber > MOBILE_NUMBER_MAX_LENGTH) {
			// throw a new illegal argument exception with an appropriate
			// message
			throw new IllegalArgumentException(
					"Sorry your mobile number must be 11 numbers long");
			// if anything else
		} else {
			// throw a new illegal argument exception with an appropriate
			// message
			throw new Exception("Sorry what you typed is invalid");
		}
	}

} // Class close
