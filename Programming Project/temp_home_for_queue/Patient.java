package hospital;

// mock up of patient class to test the queue
public class Patient {

	// vars
	private String firstName;
	private String lastName;
	private int age;
	private int triageNumber;
	private boolean movedFromTreatRoom;
	private boolean secondTimeOnWaitingList;
	private boolean treatedByOnCallTeam;
	private int admissionNumber;
	private long startTimeWait1;
	private long startTimeWait2;
	private long endTimeWait1;
	private long endTimeWait2;
	private long startTimeTreat1;
	private long startTimeTreat2;
	private long endTimeTreat1;
	private long endTimeTreat2;
	private int treatmentRoom;

	// default constructor
	public Patient() {
	}

	// arg constructor - setting topOfList to false
	public Patient(String firstName, String lastName, int age, int triageNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.movedFromTreatRoom = false;
		this.secondTimeOnWaitingList = false;
		this.treatedByOnCallTeam = false;
		this.admissionNumber = 0;
		this.triageNumber = triageNumber;
		this.startTimeWait1 = 0;
		this.startTimeWait2 = 0;
		this.startTimeTreat1 = 0;
		this.startTimeTreat2 = 0;
		this.endTimeWait1 = 0;
		this.endTimeWait2 = 0;
		this.endTimeTreat1 = 0;
		this.endTimeTreat2 = 0;
		this.treatmentRoom = -1;
	}

	// getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getTriageNumber() {
		return triageNumber;
	}

	public void setTriageNumber(int triageNumber) {
		this.triageNumber = triageNumber;
	}

	public boolean isMovedFromTreatRoom() {
		return movedFromTreatRoom;
	}

	public void setMovedFromTreatRoom(boolean movedFromTreatRoom) {
		this.movedFromTreatRoom = movedFromTreatRoom;
	}

	public int getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(int admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	// minimal to string for demo purposes
	@Override
	public String toString() {
		return "[" + this.firstName + ", T" + this.triageNumber + ", "
				+ this.movedFromTreatRoom + ", No " + this.admissionNumber
				+ ", ws1:" + (this.startTimeWait1)
				+ ", we1:" + (this.endTimeWait1)
				+ ", ws2:" + (this.startTimeWait2)
				+ ", we2:" + (this.endTimeWait2)
				+ ", ts1:" + (this.startTimeTreat1)
				+ ", te1:" + (this.endTimeTreat1)
				+ ", ts2:" + (this.startTimeTreat2)
				+ ", te2:" + (this.endTimeTreat2)
				+ " ]";

		// , Waiting Time " + calcWaitingTime()

	}

	public long getStartTimeWait1() {
		return startTimeWait1;
	}

	public void setStartTimeWait1(long startTimeWait1) {
		this.startTimeWait1 = startTimeWait1;
	}

	public long getEndTimeWait1() {
		return endTimeWait1;
	}

	public void setEndTimeWait1(long endTimeWait1) {
		this.endTimeWait1 = endTimeWait1;
	}

	public int getTreatmentRoom() {
		return treatmentRoom;
	}

	public void setTreatmentRoom(int treatmentRoom) {
		this.treatmentRoom = treatmentRoom;
	}

	public long getStartTimeWait2() {
		return startTimeWait2;
	}

	public void setStartTimeWait2(long startTimeWait2) {
		this.startTimeWait2 = startTimeWait2;
	}

	public long getEndTimeWait2() {
		return endTimeWait2;
	}

	public void setEndTimeWait2(long endTimeWait2) {
		this.endTimeWait2 = endTimeWait2;
	}

	public long getStartTimeTreat1() {
		return startTimeTreat1;
	}

	public void setStartTimeTreat1(long startTimeTreat1) {
		this.startTimeTreat1 = startTimeTreat1;
	}

	public long getEndTimeTreat1() {
		return endTimeTreat1;
	}

	public void setEndTimeTreat1(long endTimeTreat1) {
		this.endTimeTreat1 = endTimeTreat1;
	}

	public long getEndTimeTreat2() {
		return endTimeTreat2;
	}

	public void setEndTimeTreat2(long endTimeTreat2) {
		this.endTimeTreat2 = endTimeTreat2;
	}

	public long getStartTimeTreat2() {
		return startTimeTreat2;
	}

	public void setStartTimeTreat2(long startTimeTreat2) {
		this.startTimeTreat2 = startTimeTreat2;
	}

	public boolean isSecondTimeOnWaitingList() {
		return secondTimeOnWaitingList;
	}

	public void setSecondTimeOnWaitingList(boolean secondTimeOnWaitingList) {
		this.secondTimeOnWaitingList = secondTimeOnWaitingList;
	}

	public boolean isTreatedByOnCallTeam() {
		return treatedByOnCallTeam;
	}

	public void setTreatedByOnCallTeam(boolean treatedByOnCallTeam) {
		this.treatedByOnCallTeam = treatedByOnCallTeam;
	}
}
