package hospital;



/**
 * Class to extend staff class details to class doctor and to implement iOnCall interface
 * @author James
 *
 */

public class Doctor extends Staff implements iOnCall {
	
	/**
	 * default constructor for doctor
	 */
	
	public Doctor (){}
	
	/**
	 * Constructor with args for doctor including a call to the super class staff constructor
	 */
	public Doctor (String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode,long contactNumber, int staffID, int loginID,
			String password) throws IllegalArgumentException, Exception {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, contactNumber, staffID, loginID, password);
	}
	
/**
 * Method to amend record
 */
public static void amendRecord(){
	}
/**
 * Method which once called will allow a Doctor to extend the treatment time
 * of a patient
 */
public void extendTreatment(boolean extend, int treatmentRoom) {
	QueueOperations.extraTreatmentTime(extend, treatmentRoom);
}

/**
 * Overridden method from the isOnCall interface to set if the doctor is out
 * on call
 */
@Override
public void isOnCall() {
	
	this.isOnCallToString();

}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */

public String isOnCallToString() {
	return "Doctor " + getTitle() + " " + getFirstName()
			+ " " + getMiddleName() + " " + getLastName()
			+ "is on call";
}



}


