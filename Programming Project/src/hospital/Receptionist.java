package hospital;

public class Receptionist extends Staff {

	public Receptionist() {
		
	}

	public Receptionist(String title, String firstName, String middleName,
			String lastName, String dateOfBirth, String addressLineOne,
			String addressLineTwo, String addressLineThree, String city,
			String postcode, int staffID, long mobileNumber, int loginID,
			String password) {
		super(title, firstName, middleName, lastName, dateOfBirth,
				addressLineOne, addressLineTwo, addressLineThree, city,
				postcode, staffID, mobileNumber, loginID, password);
		
	}


	public void lookUpPatient(){
		
	}
	
	public void admitPatient(){
		
	}
}
