package hospital;

/**
 * This is the class for Patient which extends the person abstract class as its
 * super class
 * 
 * @author Janine
 *
 */

public class Patient extends Person {

	// Set all instance vars to private
	/**
	 * Instance var to store the NHS number - must be 9 digits
	 */
	private int nhsNumber;

	/**
	 * Instance var to store the allergies
	 */
	private String allergies;

	/**
	 * Instance var to store the known conditions
	 */
	private String knownConditions;

	/**
	 * Instance var to store the blood group Must be one of - A+, A-, B+, B-,
	 * AB+, AB-, O+, O-
	 */
	private String bloodGroup;

	/**
	 * Instance var to store the sex Must be either M or F
	 */
	private char sex;

	/**
	 * Instance var to store the next of kin
	 */
	private String nextOfKin;

	/**
	 * Instance var to store the gp name
	 */
	private String gpName;

	/**
	 * Instance var to store the GP code
	 */
	private String gpCode;

	// Amended this to triageNumber as I have introduced a method triageCategory
	// to represent the triageNumber as a String for display purposes
	/**
	 * Instance var to store the triage category as a number
	 */
	private int triageNumber;

	// New instance var
	/**
	 * Auto-incrementing number starting at zero used solely during sorting to
	 * ensure patients of same triage category are treated in admission order
	 */
	private int admissionNumber;

	// Amended var type and name
	/**
	 * Instance var to store the start of a patient's time on the Waiting List.
	 * The time is taken as the time in milliseconds since the 1970 epoch
	 */
	private long startTimeWait;

	// New
	/**
	 * Instance var to store the end of a patient's time on the Waiting List The
	 * time is taken as the time in milliseconds since the 1970 epoch
	 */
	private long endTimeWait;

	// New
	/**
	 * Instance var to store the start of a patient's time being treated, either
	 * in a Treatment Room or by the On Call Team. The time is taken as the time
	 * in milliseconds since the 1970 epoch
	 */
	private long startTimeTreat;

	// New
	/**
	 * Instance var to store the end of a patient's time being treated, either
	 * in a Treatment Room or by the On Call Team. The time is taken as the time
	 * in milliseconds since the 1970 epoch. This could also be considered as
	 * their discharge time.
	 */
	private long endTimeTreat;

	// New
	/**
	 * Instance var to hold the time which a patient is on the Waiting List and
	 * waiting for their first period of treatment to commence. It is calculated
	 * as the difference between the patient's starting time on the waiting list
	 * and a new instant of time. The difference in milliseconds is converted
	 * into minutes.
	 */
	private int timeOnWaitingList;

	// New
	/**
	 * Instance var to store the Treatment Room number that a particular patient
	 * has been put into. To avoid confusion between ArrayList elements (which
	 * start at zero) this is defaulted to -1. Therefore if a patient is not in
	 * a Treatment Room this var is -1.
	 */
	private int treatmentRoom;

	// New
	/**
	 * Instance var boolean to show that a patient was treate by the on call
	 * team, as this would not necessarily be obvious from treatment time alone
	 * in the case of a patient being treated normally and having their time in
	 * the treatment room extended
	 */
	private boolean treatedByOnCallTeam;

	// New
	/**
	 * Instance var boolean which is triggered to true when a patient has been
	 * waiting for treatment to commence for more than 30 minutes. It gets reset
	 * to false when a patient starts treatment.
	 */
	private boolean waitingMoreThan30;

	// Amended var name
	/**
	 * Instance var to store the boolean verifying whether the patient is to
	 * receive priority in the Waiting List - this can be because they have been
	 * moved out of a Treatment Room to make way for an Emergency Patient or
	 * that they have been waiting for treatment to commence for 25 minutes or
	 * more
	 */
	private boolean priorityPatient;

	/**
	 * Constant for NHS number maximum value
	 */
	private static final int NHS_NUMBER_MAX = 999999999;

	/**
	 * Constant for NHS number minimum value
	 */
	private static final int NHS_NUMBER_MIN = 100000000;

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
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public Patient(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int nhsNumber, String allergies,
			String knownConditions, String bloodGroup, char sex,
			String nextOfKin, String gpName, String gpCode)
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

		// following are variables which have preset values when a patient
		// object if created, therefore are automatically set
		this.triageNumber = 0;
		this.priorityPatient = false;
		this.admissionNumber = 0;
		this.startTimeWait = 0;
		this.startTimeTreat = 0;
		this.endTimeWait = 0;
		this.endTimeTreat = 0;
		this.treatmentRoom = -1;
		this.timeOnWaitingList = 0;
		this.treatedByOnCallTeam = false;
		this.waitingMoreThan30 = false;

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
	public void setNhsNumber(int nhsNumber) throws IllegalArgumentException
	// , Exception
	{
		// Check that the NHS number is nine figures long
		if (nhsNumber >= NHS_NUMBER_MIN && nhsNumber <= NHS_NUMBER_MAX) {
			// If the NHS number is the appropriate length then set the NHS
			// number
			this.nhsNumber = nhsNumber;

			// If the NHS number falls below the appropriate values throw an
			// exception with an appropriate message
		}
		if (nhsNumber < NHS_NUMBER_MIN || nhsNumber > NHS_NUMBER_MAX) {
			throw new IllegalArgumentException(
					"Sorry. NHS number must be 9 digits long.");
		}

		// Anything other than a nine digit value will throw an exception
		// with an appropriate message
		/*
		 * } else { throw new Exception("Your entry is invalid."); }
		 */
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
	 * 
	 * @throws Exception
	 */
	public void setGpCode(String gpCode) throws Exception {
		// If statement to check that the gp code is not empty, starts with the
		// letter E and is 5 characters long. If it is, it sets it.
		if ((!gpCode.isEmpty()) && (gpCode.startsWith("E"))
				&& (gpCode.length() == 5)) {
			this.gpCode = gpCode;
			// If the the gp code does not fulfil the requirements of the if
			// statement then an exception is thrown.
		} else {
			throw new Exception("This GP Code is invalid.");
		}
	}

	/**
	 * Get method for triage number
	 * 
	 * @return triageNumber
	 */
	public int getTriageNumber() {
		return triageNumber;
	}

	// I had to amend this - yes the triageNumber starts out as zero (initial
	// value is set above) but the set method will be called by the triagenurse,
	// therefore must be able to set a value of 1, 2, 3 or 4. Will probably need
	// new test cases. I could argue that you need the ability to set it to zero
	// as well, for instance if a patient is sent elsewhere or they are
	// discharged and re-admitted their triageNumber would need to be reset to
	// zero before they entered triage again in case there was a mix up, so
	// perhaps it should be greater than or equal to zero and less than 5.
	/**
	 * Set method for triage number
	 * 
	 * @param triageNumber
	 */
	public void setTriageNumber(int triageNumber) throws Exception {
		if (triageNumber > 0 && triageNumber < 5) {
			this.triageNumber = triageNumber;
		} else {
			throw new Exception("Triage category must be 1 to 4.");
		}
	}

	/**
	 * Get method for priority Patient boolean
	 * 
	 * @return priorityPatient
	 */
	public boolean isPriorityPatient() {
		return priorityPatient;
	}

	/**
	 * Set method for priority Patient boolean
	 * 
	 * @param topOfList
	 */
	public void setPriorityPatient(boolean priorityPatient) {
		this.priorityPatient = priorityPatient;
	}

	/*
	 * The toString will need to be modified to display only those details which
	 * we will display in the queue and that will be displayed publicly to those
	 * patients actually in the queue. The main use of the toString method will
	 * be in the iteration of the various lists which is carried out every
	 * minute so it needs to be quick and easy.
	 * 
	 * I suggest firstName, lastName, NHS number, triageCategory, possibly DOB,
	 * 
	 * The toString I have been using for developmental and testing purposes is
	 * 
	 * 
	 * @Override public String toString() { 
	 * return "[" + this.firstName + ", T"
	 * + this.triageNumber + ", " +triageCategory()+ ", " + this.priorityPatient
	 * + ", No " + this.admissionNumber + ", Wait Start:" + (this.startTimeWait)
	 * + ", Wait End:" + (this.endTimeWait) + ", Treat Start:" +
	 * (this.startTimeTreat) + ", Treat End:" + (this.endTimeTreat) + ", Wait:"
	 * + (getTimeOnWaitingList()) +"mins, Wait: "+this.waitingMoreThan30+" ]";
	 * }
	 * 
	 * Almost everything else held in patient is private/not of relevance to the
	 * patient.
	 * 
	 * Nurses and doctors should be able to bring up more information - they
	 * should be able to search the queue for patient name, nhs number of triage
	 * category
	 * 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Patient [nhsNumber=" + nhsNumber + ", allergies=" + allergies
				+ ", knownConditions=" + knownConditions + ", bloodGroup="
				+ bloodGroup + ", sex" + sex + ", nextOfKin" + nextOfKin
				+ ", gpName" + gpName + ", gpCode" + gpCode
				+ ", triageCategory" + triageCategory() + ", title=" + title
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", addressLineOne=" + addressLineOne + ", addressLineTwo="
				+ addressLineTwo + ", addressLineThree=" + addressLineThree
				+ ", city=" + city + ", postcode=" + postcode + "]";
	}

	// New
	/**
	 * Get method for admission number
	 * 
	 * @return admissionNumber
	 */
	public int getAdmissionNumber() {
		return admissionNumber;
	}

	// New - this is automatically set by TheQueue class and can be any positive
	// or negative integer - there is no user input to this number whatsoever
	// therefore no validation is required
	/**
	 * Set method for admission number
	 * 
	 * @param admissionNumber
	 */
	public void setAdmissionNumber(int admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	// New
	/**
	 * Get method for the start time for a patient on the waiting list
	 * 
	 * @return startTimeWait
	 */
	public long getStartTimeWait() {
		return startTimeWait;
	}

	// New - see no need for validation here, not sure what you would validate -
	// the number is a long in the order of 1428357527133 - and as stated above
	// is the time in milliseconds since the 1970 epoch
	/**
	 * Set method for the start time for a patient on the waiting list
	 * 
	 * @param startTimeWait
	 */
	public void setStartTimeWait(long startTimeWait) {
		this.startTimeWait = startTimeWait;
	}

	// New
	/**
	 * Get method for the end time for a patient on the waiting list
	 * 
	 * @return
	 */
	public long getEndTimeWait() {
		return endTimeWait;
	}

	// New - see no need for validation here, not sure what you would validate -
	// see above
	/**
	 * Set method for the end time for a patient on the waiting list
	 * 
	 * @param endTimeWait
	 */
	public void setEndTimeWait(long endTimeWait) {
		this.endTimeWait = endTimeWait;
	}

	// New
	/**
	 * Method to get the treatment room number that a patient is currently in
	 * 
	 * @return treatmentRoom
	 */
	public int getTreatmentRoom() {
		return treatmentRoom;
	}

	// New - TheQueue can only set valid treatment room numbers or -1 as a
	// default
	/**
	 * Method to set the treatment room a patient is currently being treated in.
	 * 
	 * @param treatmentRoom
	 */
	public void setTreatmentRoom(int treatmentRoom) {
		this.treatmentRoom = treatmentRoom;
	}

	// New
	/**
	 * Method to get the start time for a patient being treated
	 * 
	 * @return startTimeTreat
	 */
	public long getStartTimeTreat() {
		return startTimeTreat;
	}

	// New - see no need for validation here, not sure what you would validate -
	// see above
	/**
	 * Method to set the start time for a patient being treated
	 * 
	 * @param startTimeTreat
	 */
	public void setStartTimeTreat(long startTimeTreat) {
		this.startTimeTreat = startTimeTreat;
	}

	// New
	/**
	 * Method to get the end time for a patient being treated - can also be
	 * considered as their discharge time
	 * 
	 * @return endTimeTreat
	 */
	public long getEndTimeTreat() {
		return endTimeTreat;
	}

	// New - see no need for validation here, not sure what you would validate -
	// see above
	/**
	 * Method to set the end time for a patient being treated - can also be
	 * considered as their discharge time
	 * 
	 * @param endTimeTreat
	 */
	public void setEndTimeTreat(long endTimeTreat) {
		this.endTimeTreat = endTimeTreat;
	}

	// New
	/**
	 * Method to get the boolean value which states if a patient is/was under
	 * the treatment of the On Call Team
	 * 
	 * @return treatedByOnCallTeam
	 */
	public boolean isTreatedByOnCallTeam() {
		return treatedByOnCallTeam;
	}

	// New
	/**
	 * Method to set the boolean value which states if a patient is/was under
	 * the treatment of the On Call Team
	 * 
	 * @param treatedByOnCallTeam
	 */
	public void setTreatedByOnCallTeam(boolean treatedByOnCallTeam) {
		this.treatedByOnCallTeam = treatedByOnCallTeam;
	}

	// New
	/**
	 * Method to get the boolean value which states if a patient actively
	 * waiting for treatment has been waiting for 30 minutes or more
	 * 
	 * @return waitingMoreThan30
	 */
	public boolean isWaitingMoreThan30() {
		return waitingMoreThan30;
	}

	// New
	/**
	 * Method to set the boolean value which states if a patient actively
	 * waiting for treatment has been waiting for 30 minutes or more
	 * 
	 * @param waitingMoreThan30
	 */
	public void setWaitingMoreThan30(boolean waitingMoreThan30) {
		this.waitingMoreThan30 = waitingMoreThan30;
	}

	// New
	/**
	 * Method to get the waiting time of a patient.
	 * 
	 * @return timeOnWaitingList
	 */
	public int getTimeOnWaitingList() {
		return timeOnWaitingList;
	}

	// New - to test this class you would need to firstly change the bit which
	// says TheQueue.TIME_FACTOR to an integer, I have it set to 16 normally so
	// the queue runs 16 times faster than realtime, and also you would need to
	// pass in the currentTime. This can be obtained by doing
	//
	// Instant now = Instant.now();
	// long currentTime = now.toEpochmilli();
	/**
	 * Method to set the waiting time of a patient. It is calculated as the
	 * difference between the patient's starting time on the waiting list and a
	 * new instant of time. The difference in milliseconds is converted into
	 * minutes. As part of the conversion process the TIME_FACTOR as defined in
	 * TheQueue.class is used. This is used for demo purposes and is the factor
	 * by which the system is sped up. A value of one here indicates real time.
	 * 
	 * @param currentTime
	 */
	public void setTimeOnWaitingList(long currentTime) {

		if (this.startTimeWait == this.endTimeWait) {
			this.timeOnWaitingList = 0;
		} else if (this.endTimeWait == 0) {
			this.timeOnWaitingList = (int) ((currentTime - this.startTimeWait) / (60000 / TheQueue.TIME_FACTOR));
		} else {
			this.timeOnWaitingList = (int) ((this.endTimeWait - this.startTimeWait) / (60000 / TheQueue.TIME_FACTOR));
		}
		if (this.timeOnWaitingList > 24) {
			this.setPriorityPatient(true);
		}
		if (this.timeOnWaitingList > 29) {
			this.setWaitingMoreThan30(true);
		}
	}

	// New
	/**
	 * Method to display the names of the triage categories
	 * 
	 * @return String
	 */
	public String triageCategory() {
		String triage = "";
		switch (this.triageNumber) {
		case 1:
			triage = "Emergency";
			break;
		case 2:
			triage = "Urgent";
			break;
		case 3:
			triage = "Semi-urgent";
			break;
		case 4:
			triage = "Non-urgent";
			break;
		default:
			triage = "";
			break;
		}
		return triage;
	}
}