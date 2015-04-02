package hospital;

import java.time.Instant;

// mock up of patient class to test the queue
public class Patient {

	// vars
	private String firstName;
	private String lastName;
	private int age;
	private int triageNumber;
	private boolean movedFromTreatRoom;
	private boolean treatedByOnCallTeam;
	private int admissionNumber;
	private long startTimeWait;
	private long endTimeWait;
	private long startTimeTreat;
	private long endTimeTreat;
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
		this.treatedByOnCallTeam = false;
		this.admissionNumber = 0;
		this.triageNumber = triageNumber;
		this.startTimeWait = 0;
		this.startTimeTreat = 0;
		this.endTimeWait = 0;
		this.endTimeTreat = 0;
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
				+ ", Wait Start:" + (this.startTimeWait)
				+ ", Wait End:" + (this.endTimeWait)
				+ ", Treat Start:" + (this.startTimeTreat)
				+ ", Treat End:" + (this.endTimeTreat)
				+ ", Wait Time:" + waitTime(this.startTimeWait, this.endTimeWait)+"mins ]";

		// , Waiting Time " + calcWaitingTime()

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
	
	public int waitTime(long start, long end){
		Instant now = Instant.now();
		long currentTime = now.toEpochMilli();
		int minutes = 0;
		if (start == end) {
			minutes = 0;
		} else if (end < start) {
			minutes = 0;
		
		} else if (end == 0) {
			minutes = (int) ((currentTime - start)  / 10000) ;
		} else {
			minutes = (int) ((end - start)  / 10000);
		}
		
		return minutes;
	}
}
