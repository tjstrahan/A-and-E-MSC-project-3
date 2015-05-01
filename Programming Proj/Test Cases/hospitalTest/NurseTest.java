package hospitalTest;

import static org.junit.Assert.*;
import hospital.address.model.Nurse;

import org.junit.Before;
import org.junit.Test;

public class NurseTest {
	
	/**
	 * Test class for nurse
	 */

	String TitleCorrect, TitleWrong, firstNameCorrect, firstNameWrong,
			middleNameCorrect, middleNameWrong, lastNameCorrect, lastNameWrong,
			dateOfBirthCorrect, dateOfBirthWrong, addressLineOneCorrect,
			addressLineOneWrong, addressLineTwoCorrect, addressLineTwoWrong,
			addressLineThreeCorrect, addressLineThreeWrong, cityCorrect,
			cityWrong, postcodeCorrect, postCodeWrong, passwordCorrect,
			passwordWrong;

	int loginIDCorrect, loginIDWrong, StaffIDCorrect, StaffIDWrong, medicalTeamCorrect;

	long mobileNumberCorrect, mobileNumberWrong;

	@Before
	public void setUp() throws Exception {
		
		/**
		 * Test data for nurse
		 */
		
		TitleCorrect = "Mr";
		TitleWrong = null;
		firstNameCorrect = "Thomas";
		firstNameWrong = null;
		middleNameCorrect = "Bob";
		middleNameWrong = null;
		lastNameCorrect = "McDonnell";
		lastNameWrong = null;
		dateOfBirthCorrect = "20/3/90";
		dateOfBirthWrong = null;
		addressLineOneCorrect = "2 Big Road";
		addressLineOneWrong = null;
		addressLineTwoCorrect = "Glenavy";
		addressLineTwoWrong = null;
		addressLineThreeCorrect = "Antrim";
		addressLineThreeWrong = null;
		cityCorrect = "Belfast";
		cityWrong = null;
		postcodeCorrect = "BT33 5LJ";
		postCodeWrong = null;
		passwordCorrect = "password";
		passwordWrong = "pass";

		loginIDCorrect = 666666;
		loginIDWrong = 66666666;

		StaffIDCorrect = 666666;
		StaffIDWrong = 6666;

		mobileNumberCorrect = 447592010304L;
		mobileNumberWrong = 4475920103040L;
	}
	/**
	 * Testing the nurse default constructor
	 */
	@Test
	public void testNurseDefaultConstructor() {
		Nurse nurse = new Nurse();
		assertNotNull(nurse);
	}
	/**
	 * Testing the nurse non default constructor with all correct arguments
	 * @throws Exception
	 */

	@Test
	public void testConstructorWithArgsCorrect() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertNotNull(nurse);
	}
	/**
	 * Testing the nurse non default constructor with the wrong staff id
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongStaffID()
			throws IllegalArgumentException, Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDWrong, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(StaffIDCorrect, nurse.getStaffID());
	}
	/**
	 * Testing the nurse non default constructor with the wrong password
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordWrong, medicalTeamCorrect);
		assertEquals(passwordCorrect, nurse.getPassword());

	}
	/**
	 * Testing the nurse non default constructor with the wrong login
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDWrong,
				passwordCorrect, medicalTeamCorrect);

		assertEquals(loginIDCorrect, nurse.getLoginID());

	}
	/**
	 * Testing the nurse non default constructor with the wrong mobile number
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberWrong, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(mobileNumberCorrect, nurse.getContactNumber());

	}
	/**
	 * Testing the nurse non default constructor with the wrong title
	 * @throws Exception
	 */

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		@SuppressWarnings("unused")
		Nurse nurse = new Nurse(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);

	}
	/**
	 * Testing the nurse non default constructor with the wrong first name
	 * @throws Exception
	 */

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		@SuppressWarnings("unused")
		Nurse nurse = new Nurse("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);
	
	}
	/**
	 * Testing the nurse non default constructor with the wrong middle name
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Nurse nurse = new Nurse("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);
		assertEquals(middleNameCorrect, nurse.getMiddleName());

	}
	/**
	 * Testing the nurse non default constructor with the wrong last name
	 * @throws Exception
	 */

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		@SuppressWarnings("unused")
		Nurse nurse = new Nurse("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);
	
	}
	/**
	 * Testing the nurse non default constructor with the wrong date of birth
	 * @throws Exception
	 */

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		@SuppressWarnings("unused")
		Nurse nurse = new Nurse("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);

	}
	/**
	 * Testing the nurse non default constructor with the wrong addres line one
	 */

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoAddressLineOne() throws Exception {
		@SuppressWarnings("unused")
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
	
	}
	/**
	 * Testing the nurse non default constructor with the wrong address line two
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(addressLineTwoCorrect, nurse.getAddressLineTwo());

	}
	/**
	 * Testing the nurse non default constructor with the wrong address line three
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(addressLineThreeCorrect, nurse.getAddressLineThree());

	}
	/**
	 * Testing the nurse non default constructor with the wrong city
	 * @throws Exception
	 */

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoCity() throws Exception {
		@SuppressWarnings("unused")
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
	
	}
	/**
	 * Testing the nurse non default constructor with the wrong postcode
	 * @throws Exception
	 */

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoPostCode() throws Exception {
		@SuppressWarnings("unused")
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);

	}
	/**
	 * Test stub for amend record method
	 */

	@Test   
	public void testAmendRecord() {
		fail("Not yet implemented");
	}
	/**
	 * Test stub for extend treatment method
	 */

	@Test
	public void testExtendTreatment() {
		fail("Not yet implemented");
	}
	/**
	 * Test stub for is on call method
	 */
	@Test
	public void testIsOnCall() {
		fail("Not yet implemented");
	}

}
