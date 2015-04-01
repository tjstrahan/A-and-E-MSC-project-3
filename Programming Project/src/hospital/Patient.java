package hospital;

/**
 * This is the class for Patient which extends the person abstract class as its
 * super class
 * 
 * @author Janine
 *
 */

public class Patient extends Person {
	/**
	 * Instance var to store the NHS number - must be 9 digits
	 */
	int nhsNumber;
	/**
	 * Instance var to store the allergies
	 */
	String allergies;
	/**
	 * Instance var to store the known conditions
	 */
	String knownConditions;
	/**
	 * Instance var to store the blood group Must be one of - A+, A-, B+, B-,
	 * AB+, AB-, O+, O-
	 */
	String bloodGroup;
	/**
	 * Instance var to store the sex Must be either M or F
	 */
	char sex;
	/**
	 * Instance var to store the next of kin
	 */
	String nextOfKin;
	/**
	 * Instance var to store the gp name
	 */
	String gpName;
	/**
	 * Instance var to store the GP code
	 */
	String gpCode;
	/**
	 * Instance var to store the triage category Set to 0
	 */
	int triageCategory = 0;
	/**
	 * Instance var to store the admission time
	 */
	double admissionTime;
	/**
	 * Instance var to store the boolean verifying whether the patient is top of
	 * the list
	 */
	boolean topOfList = false;
	/**
	 * Constant for NHS number maximum value
	 */
	static final int NHS_NUMBER_MAX = 999999999;
	/**
	 * Constant for NHS number minimum value
	 */
	static final int NHS_NUMBER_MIN = 100000000;

	/**
	 * Default constructor
	 */
	public Patient() {

	}

	/**
	 * Constructor with arguments including those from the superclass
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
	 * @param nhsNumber
	 * @param allergies
	 * @param knownConditions
	 * @param bloodGroup
	 * @param sex
	 * @param nextOfKin
	 * @param gpName
	 * @param gpCode
	 * @param triageCategory
	 * @param admissionTime
	 * @param topOfList
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public Patient(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int nhsNumber, String allergies,
			String knownConditions, String bloodGroup, char sex,
			String nextOfKin, String gpName, String gpCode, int triageCategory,
			double admissionTime, boolean topOfList)
			throws IllegalArgumentException, Exception {

		// call to the superclass constructor
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode);

		setNhsNumber(nhsNumber);
		setAllergies(allergies);
		setKnownConditions(knownConditions);
		setBloodGroup(bloodGroup);
		setSex(sex);
		setNextOfKin(nextOfKin);
		setGpName(gpName);
		setGpCode(gpCode);
		setTriageCategory(triageCategory);
		setAdmissionTime(admissionTime);
		setTopOfList(topOfList);
	}

	/**
	 * Get method for nhs Number
	 * 
	 * @return nhsNumber
	 */
	public int getNhsNumber() {
		return nhsNumber;
	}

	/**
	 * Set method for nhs Number
	 * 
	 * @param nhsNumber
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public void setNhsNumber(int nhsNumber) throws IllegalArgumentException,
			Exception {
		// Check that the NHS number is nine figures long
		if (nhsNumber > NHS_NUMBER_MIN && nhsNumber < NHS_NUMBER_MAX) {
			// If the NHS number is the appropriate length then set the NHS
			// number
			this.nhsNumber = nhsNumber;

			// If the NHS number falls below the appropriate values throw an
			// exception with an appropriate message
		} else if (nhsNumber < NHS_NUMBER_MIN || nhsNumber > NHS_NUMBER_MAX) {
			throw new IllegalArgumentException(
					"Sorry. NHS number must be 9 digits long.");

			// Anything other than a nine digit value will throw an exception
			// with an appropriate message
		} else {
			throw new Exception("Your entry is invalid.");
		}
	}

	/**
	 * Get method for allergies
	 * 
	 * @return allergies
	 */
	public String getAllergies() {
		return allergies;
	}

	/**
	 * Set method for allergies
	 * 
	 * @param allergies
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	/**
	 * Get method for known conditions
	 * 
	 * @return knownConditions
	 */
	public String getKnownConditions() {
		return knownConditions;
	}

	/**
	 * Set method for known conditions
	 * 
	 * @param knownConditions
	 */
	public void setKnownConditions(String knownConditions) {
		this.knownConditions = knownConditions;
	}

	/**
	 * Get method for blood group
	 * 
	 * @return bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * Set method for blood group
	 * 
	 * @param bloodGroup
	 */
	public void setBloodGroup(String bloodGroup)
			throws IllegalArgumentException {
		// Switch statement to ensure the blood group selected is an appropriate
		// one. Default will throw an exception if the blood group is not one of
		// those listed.
		switch (bloodGroup) {
		case "A+":
			this.bloodGroup = bloodGroup;
			break;
		case "A-":
			this.bloodGroup = bloodGroup;
			break;
		case "B+":
			this.bloodGroup = bloodGroup;
			break;
		case "B-":
			this.bloodGroup = bloodGroup;
			break;
		case "AB+":
			this.bloodGroup = bloodGroup;
			break;
		case "AB-":
			this.bloodGroup = bloodGroup;
			break;
		case "O+":
			this.bloodGroup = bloodGroup;
			break;
		case "O-":
			this.bloodGroup = bloodGroup;
			break;
		default:
			throw new IllegalArgumentException(
					"This is an invalid blood group.");
		}

	}

	/**
	 * Get method for sex
	 * 
	 * @return sex
	 */
	public char getSex() {
		return sex;
	}

	/**
	 * Set method for sex Must be either M or F
	 * 
	 * @param sex
	 */
	public void setSex(char sex) throws IllegalArgumentException {
		// If statement to check if the sex entered is valid - either M or F. If
		// not it will throw an exception.
		if (sex == 'F' || sex == 'M') {
			this.sex = sex;
		} else {
			throw new IllegalArgumentException(
					"Invalid entry. Sex must either be M or F");
		}
	}

	/**
	 * Get for next of kin
	 * 
	 * @return nextOfKin
	 */
	public String getNextOfKin() {
		return nextOfKin;
	}

	/**
	 * Set method for next of kin
	 * 
	 * @param nextOfKin
	 */
	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}

	/**
	 * Get method for gp name
	 * 
	 * @return gpName
	 */
	public String getGpName() {
		return gpName;
	}

	/**
	 * Set method for gp name
	 * 
	 * @param gpName
	 * @throws Exception
	 */
	public void setGpName(String gpName) throws Exception {
		if (gpName.isEmpty()) {
			throw new Exception("GP Name cannot be left blank.");
		} else {
			this.gpName = gpName;
		}
	}

	/**
	 * Get method for GP Code
	 * 
	 * @return gpCode
	 */
	public String getGpCode() {
		return gpCode;
	}

	/**
	 * Set method for GP code
	 */
	public void setGpCode(String gpCode) throws Exception {

		// If statement to check whether the GP code is left blank - if it is
		// then throw an exception
		if (gpCode.isEmpty()) {
			throw new Exception("GP Code cannot be left blank.");
			// Else if to check if the GP code beings with E - if it doesn't
			// then throw an exception
		} else if (!gpCode.startsWith("E")) {
			throw new Exception("GP Code must start with E.");
			// Else to set the GP code
		} else {
			this.gpCode = gpCode;
		}
	}

	/**
	 * Get method for triage category
	 * 
	 * @return triageCategory
	 */
	public int getTriageCategory() {
		return triageCategory;
	}

	/**
	 * Set method for triage category
	 * 
	 * @param triageCategory
	 */
	public void setTriageCategory(int triageCategory) throws Exception {
		if (triageCategory == 0) {
			this.triageCategory = triageCategory;
		} else {
			throw new Exception("Triage category must be 0.");
		}
	}

	/**
	 * Get method for admission time
	 * 
	 * @return admissionTime
	 */
	public double getAdmissionTime() {
		return admissionTime;
	}

	/**
	 * Set method for admission time
	 * 
	 * @param admissionTime
	 */
	public void setAdmissionTime(double admissionTime) {
		this.admissionTime = admissionTime;
	}

	/**
	 * Get method for top of list boolean
	 * 
	 * @return topOfList
	 */
	public boolean isTopOfList() {
		return topOfList;
	}

	/**
	 * Set method for top of list boolean
	 * 
	 * @param topOfList
	 */
	public void setTopOfList(boolean topOfList) {
		this.topOfList = topOfList;
	}

	@Override
	public String toString() {
		return "Patient [nhsNumber=" + nhsNumber + ", allergies=" + allergies
				+ ", knownConditions=" + knownConditions + ", bloodGroup="
				+ bloodGroup + ", sex" + sex + ", nextOfKin" + nextOfKin
				+ ", gpName" + gpName + ", gpCode" + gpCode
				+ ", triageCategory" + triageCategory + ", admissionTime"
				+ admissionTime + ", topOfList" + topOfList + ", title="
				+ title + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", addressLineOne=" + addressLineOne
				+ ", addressLineTwo=" + addressLineTwo + ", addressLineThree="
				+ addressLineThree + ", city=" + city + ", postcode="
				+ postcode + "]";
	}

}
