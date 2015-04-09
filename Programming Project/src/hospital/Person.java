package hospital;

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
	 * Default Constructor
	 */
	public Person() {

	}

	/**
	 * Constructor with arguments
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
	 */
	public Person(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode) {

		this.title = title;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.addressLineThree = addressLineThree;
		this.city = city;
		this.postcode = postcode;
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
	public void setTitle(String title) {

		this.title = title;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
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
	public void setCity(String city) {
		this.city = city;
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
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

} // Class close
