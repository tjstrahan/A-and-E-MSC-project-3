package hospital.address.model;

import javafx.beans.property.*;
import hospital.address.TheQueue;

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
	private String sex;

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

	/**
	 * Notes that a doctor has added to patients record upon discharge from AnE
	 */
	private String notes;

	/**
	 * Instance var to store the triage category as a number
	 */
	private int triageNumber;

	private StringProperty triageCategory;
	
	/**
	 * Auto-incrementing number starting at zero used solely during sorting to
	 * ensure patients of same triage category are treated in admission order
	 */
	private int admissionNumber;

	/**
	 * Instance var to store the start of a patient's time on the Waiting List.
	 * The time is taken as the time in milliseconds since the 1970 epoch
	 */
	private long startTimeWait;

	/**
	 * Instance var to store the end of a patient's time on the Waiting List The
	 * time is taken as the time in milliseconds since the 1970 epoch
	 */
	private long endTimeWait;

	/**
	 * Instance var to store the start of a patient's time being treated, either
	 * in a Treatment Room or by the On Call Team. The time is taken as the time
	 * in milliseconds since the 1970 epoch
	 */
	private long startTimeTreat;

	/**
	 * Instance var to store the end of a patient's time being treated, either
	 * in a Treatment Room or by the On Call Team. The time is taken as the time
	 * in milliseconds since the 1970 epoch. This could also be considered as
	 * their discharge time.
	 */
	private long endTimeTreat;

	/**
	 * Instance var to hold the time which a patient is on the Waiting List and
	 * waiting for their first period of treatment to commence. It is calculated
	 * as the difference between the patient's starting time on the waiting list
	 * and a new instant of time. The difference in milliseconds is converted
	 * into minutes.
	 */
	private int timeOnWaitingList;

	/**
	 * Instance var to store the Treatment Room number that a particular patient
	 * has been put into. To avoid confusion between ArrayList elements (which
	 * start at zero) this is defaulted to -1. Therefore if a patient is not in
	 * a Treatment Room this var is -1.
	 */
	private int treatmentRoom;

	/**
	 * Instance var boolean to show that a patient was treate by the on call
	 * team, as this would not necessarily be obvious from treatment time alone
	 * in the case of a patient being treated normally and having their time in
	 * the treatment room extended
	 */
	private boolean treatedByOnCallTeam;

	/**
	 * Instance var boolean which is triggered to true when a patient has been
	 * waiting for treatment to commence for more than 30 minutes. It gets reset
	 * to false when a patient starts treatment.
	 */
	private boolean waitingMoreThan30;

	/**
	 * Instance var to store the boolean verifying whether the patient is to
	 * receive priority in the Waiting List - this can be because they have been
	 * moved out of a Treatment Room to make way for an Emergency Patient or
	 * that they have been waiting for treatment to commence for 25 minutes or
	 * more
	 */
	private boolean priorityPatient;

	/**
	 * Instance var to store the boolean verifying if a doctor has made a new
	 * note on a patients record during their treatment
	 */
	private boolean madeNewNote;

	/**
	 * Instance var to store the boolean verifying if a member of the medical
	 * team has requested an extra treatment time of 5 minutes
	 */
	private boolean extraTime;

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
	 * @param nhsNumber
	 * @param allergies
	 * @param knownConditions
	 * @param bloodGroup
	 * @param sex
	 * @param nextOfKin
	 * @param gpName
	 * @param gpCode
	 * @param notes
	 * @throws Exception
	 * @throws IllegalArgumentException
	 */
	public Patient(String string, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, long contactNumber, int nhsNumber,
			String allergies, String knownConditions, String bloodGroup,
			String sex, String nextOfKin, String gpName, String gpCode,
			String notes) throws IllegalArgumentException, Exception {

		// call to the superclass constructor
		super(string, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, contactNumber);

		setNhsNumber(nhsNumber);
		setAllergies(allergies);
		setKnownConditions(knownConditions);
		setBloodGroup(bloodGroup);
		setSex(sex);
		setNextOfKin(nextOfKin);
		setGpName(gpName);
		setGpCode(gpCode);
		this.notes = notes;

		// following are variables which have preset values when a patient
		// object if created, therefore are automatically set
		this.triageNumber = 0;
		this.triageCategory = new SimpleStringProperty();
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
		this.madeNewNote = false;
		this.extraTime = false;
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
	public String getSex() {
		return sex;
	}

	/**
	 * Set method for sex Must be either M or F
	 * 
	 * @param sex
	 */
	public void setSex(String sex) throws IllegalArgumentException {
		// If statement to check if the sex entered is valid - either M or F. If
		// not it will throw an exception.
		if (sex.equalsIgnoreCase("F") || sex.equalsIgnoreCase("M")) {
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
	 * Get method for notes added to patient record by doctor
	 * 
	 * @return
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Get method for notes made by a doctor
	 * 
	 * @param notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * Get method for triage number
	 * 
	 * @return triageNumber
	 */
	public int getTriageNumber() {
		return triageNumber;
	}

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

	/**
	 * ToString method to show information that will be displayed to people,
	 * including the public, who can view the Queue. It is basic none sensitive
	 * information. Users such as the Receptionist, Nurses and Doctors will be
	 * able to see more and will use a different method.
	 */
	@Override
	public String toString() {
		return "[" + getTitle() + " " + getFirstName() + " " + getLastName()
				+ ", Date of Birth: " + getDateOfBirth() + ", Triaged as: "
				+ getTriageCategory() + ", NHS number: " + this.nhsNumber
				+ ", Waiting Time: " + getTimeOnWaitingList() + " Ad: "
				+ getAdmissionNumber() + "]";
	}

	/**
	 * Get method for admission number
	 * 
	 * @return admissionNumber
	 */
	public int getAdmissionNumber() {
		return admissionNumber;
	}

	/**
	 * Set method for admission number
	 * 
	 * @param admissionNumber
	 */
	public void setAdmissionNumber(int admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	/**
	 * Get method for the start time for a patient on the waiting list
	 * 
	 * @return startTimeWait
	 */
	public long getStartTimeWait() {
		return startTimeWait;
	}

	/**
	 * Set method for the start time for a patient on the waiting list
	 * 
	 * @param startTimeWait
	 */
	public void setStartTimeWait(long startTimeWait) {
		this.startTimeWait = startTimeWait;
	}

	/**
	 * Get method for the end time for a patient on the waiting list
	 * 
	 * @return
	 */
	public long getEndTimeWait() {
		return endTimeWait;
	}

	/**
	 * Set method for the end time for a patient on the waiting list
	 * 
	 * @param endTimeWait
	 */
	public void setEndTimeWait(long endTimeWait) {
		this.endTimeWait = endTimeWait;
	}

	/**
	 * Method to get the treatment room number that a patient is currently in
	 * 
	 * @return treatmentRoom
	 */
	public int getTreatmentRoom() {
		return treatmentRoom;
	}

	/**
	 * Method to set the treatment room a patient is currently being treated in.
	 * 
	 * @param treatmentRoom
	 */
	public void setTreatmentRoom(int treatmentRoom) {
		this.treatmentRoom = treatmentRoom;
	}

	/**
	 * Method to get the start time for a patient being treated
	 * 
	 * @return startTimeTreat
	 */
	public long getStartTimeTreat() {
		return startTimeTreat;
	}

	/**
	 * Method to set the start time for a patient being treated
	 * 
	 * @param startTimeTreat
	 */
	public void setStartTimeTreat(long startTimeTreat) {
		this.startTimeTreat = startTimeTreat;
	}

	/**
	 * Method to get the end time for a patient being treated - can also be
	 * considered as their discharge time
	 * 
	 * @return endTimeTreat
	 */
	public long getEndTimeTreat() {
		return endTimeTreat;
	}

	/**
	 * Method to set the end time for a patient being treated - can also be
	 * considered as their discharge time
	 * 
	 * @param endTimeTreat
	 */
	public void setEndTimeTreat(long endTimeTreat) {
		this.endTimeTreat = endTimeTreat;
	}

	/**
	 * Method to get the boolean value which states if a patient is/was under
	 * the treatment of the On Call Team
	 * 
	 * @return treatedByOnCallTeam
	 */
	public boolean isTreatedByOnCallTeam() {
		return treatedByOnCallTeam;
	}

	/**
	 * Method to set the boolean value which states if a patient is/was under
	 * the treatment of the On Call Team
	 * 
	 * @param treatedByOnCallTeam
	 */
	public void setTreatedByOnCallTeam(boolean treatedByOnCallTeam) {
		this.treatedByOnCallTeam = treatedByOnCallTeam;
	}

	/**
	 * Method to get the boolean value which states if a patient actively
	 * waiting for treatment has been waiting for 30 minutes or more
	 * 
	 * @return waitingMoreThan30
	 */
	public boolean isWaitingMoreThan30() {
		return waitingMoreThan30;
	}

	/**
	 * Method to set the boolean value which states if a patient actively
	 * waiting for treatment has been waiting for 30 minutes or more
	 * 
	 * @param waitingMoreThan30
	 */
	public void setWaitingMoreThan30(boolean waitingMoreThan30) {
		this.waitingMoreThan30 = waitingMoreThan30;
	}

	/**
	 * Method to get the waiting time of a patient.
	 * 
	 * @return timeOnWaitingList
	 */
	public int getTimeOnWaitingList() {
		return timeOnWaitingList;
	}

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

	/**
	 * Method to get the madeNewNote boolean
	 * 
	 * @return
	 */
	public boolean isMadeNewNote() {
		return madeNewNote;
	}

	/**
	 * Method to set the madeNewNote boolean
	 * 
	 * @param madeNewNote
	 */
	public void setMadeNewNote(boolean madeNewNote) {
		this.madeNewNote = madeNewNote;
	}

	/**
	 * Method to get the extraTime boolean
	 * @return
	 */
	public boolean isExtraTime() {
		return extraTime;
	}

	/**
	 * Method to set the extraTime boolean
	 * @param extraTime
	 */
	public void setExtraTime(boolean extraTime) {
		this.extraTime = extraTime;
	}

	public String getTriageCategory() {
		return triageCategory.get();
	}

	public void setTriageCategory(String triageCategory) {
		this.triageCategory.set(triageCategory);
	}

	public StringProperty triageCategoryProperty() {
		return triageCategory;
	}

}
