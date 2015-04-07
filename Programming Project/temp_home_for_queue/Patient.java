package hospital;


// mock up of patient class to test the queue
public class Patient {

	// vars
	private String firstName;
	private String lastName;
	private int age;
	private int triageNumber;
	private boolean priorityPatient;
	private boolean treatedByOnCallTeam;
	private boolean waitingMoreThan30;
	private int admissionNumber;
	private long startTimeWait;
	private long endTimeWait;
	private long startTimeTreat;
	private long endTimeTreat;
	private int timeOnWaitingList;
	private int treatmentRoom;

	// default constructor
	public Patient() {
	}

	// arg constructor - setting topOfList to false
	public Patient(String firstName, String lastName, int age, int triageNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.priorityPatient = false;
		this.treatedByOnCallTeam = false;
		this.admissionNumber = 0;
		this.triageNumber = triageNumber;
		this.startTimeWait = 0;
		this.startTimeTreat = 0;
		this.endTimeWait = 0;
		this.endTimeTreat = 0;
		this.treatmentRoom = -1;
		this.timeOnWaitingList = 0;
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

	public boolean isPriorityPatient() {
		return priorityPatient;
	}

	public void setPriorityPatient(boolean priorityPatient) {
		this.priorityPatient = priorityPatient;
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
		return "[" + this.firstName + ", T" + this.triageNumber + ", " +triageCategory()+ ", "
				+ this.priorityPatient + ", No " + this.admissionNumber
				+ ", Wait Start:" + (this.startTimeWait)
				+ ", Wait End:" + (this.endTimeWait)
				+ ", Treat Start:" + (this.startTimeTreat)
				+ ", Treat End:" + (this.endTimeTreat)
				+ ", Wait:" + (getTimeOnWaitingList()) +"mins, Wait: "+this.waitingMoreThan30+" ]";

	}

	public long getStartTimeWait() {
		return startTimeWait;
	}

	public void setStartTimeWait(long startTimeWait) {
		this.startTimeWait = startTimeWait;
	}

	public long getEndTimeWait() {
		return endTimeWait;
	}

	public void setEndTimeWait(long endTimeWait) {
		this.endTimeWait = endTimeWait;
	}

	public int getTreatmentRoom() {
		return treatmentRoom;
	}

	public void setTreatmentRoom(int treatmentRoom) {
		this.treatmentRoom = treatmentRoom;
	}

	public long getStartTimeTreat() {
		return startTimeTreat;
	}

	public void setStartTimeTreat(long startTimeTreat) {
		this.startTimeTreat = startTimeTreat;
	}

	public long getEndTimeTreat() {
		return endTimeTreat;
	}

	public void setEndTimeTreat(long endTimeTreat) {
		this.endTimeTreat = endTimeTreat;
	}

	public boolean isTreatedByOnCallTeam() {
		return treatedByOnCallTeam;
	}

	public void setTreatedByOnCallTeam(boolean treatedByOnCallTeam) {
		this.treatedByOnCallTeam = treatedByOnCallTeam;
	}

	public boolean isWaitingMoreThan30() {
		return waitingMoreThan30;
	}

	public void setWaitingMoreThan30(boolean waitingMoreThan30) {
		this.waitingMoreThan30 = waitingMoreThan30;
	}

	public int getTimeOnWaitingList() {
		return timeOnWaitingList;
	}

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
	
	public String triageCategory() {
		String triage = "";
		switch (this.triageNumber) {
		case 1 : triage = "Emergency";
		break;
		case 2 : triage = "Urgent";
		break;
		case 3 : triage = "Semi-urgent";
		break;
		case 4 : triage = "Non-urgent";
		break;
		default : triage = "";
		break;
		}
		return triage;
	}
	
}
