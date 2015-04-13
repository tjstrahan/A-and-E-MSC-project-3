package hospitalTests;

import static org.junit.Assert.*;
import hospital.Nurse;

import org.junit.Before;
import org.junit.Test;

public class NurseTest {

	String TitleCorrect, TitleWrong, firstNameCorrect, firstNameWrong,
			middleNameCorrect, middleNameWrong, lastNameCorrect, lastNameWrong,
			dateOfBirthCorrect, dateOfBirthWrong, addressLineOneCorrect,
			addressLineOneWrong, addressLineTwoCorrect, addressLineTwoWrong,
			addressLineThreeCorrect, addressLineThreeWrong, cityCorrect,
			cityWrong, postcodeCorrect, postCodeWrong, passwordCorrect,
			passwordWrong;

	int loginIDCorrect, loginIDWrong, StaffIDCorrect, StaffIDWrong;

	long mobileNumberCorrect, mobileNumberWrong;

	@Before
	public void setUp() throws Exception {
		
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

		mobileNumberCorrect = (long) 447592010304.00;
		mobileNumberWrong = (long) 4475920103040.00;
	}

	@Test
	public void testNurseDefaultConstructor() {
		Nurse nurse = new Nurse();
		assertNotNull(nurse);
	}

	@Test
	public void testConstructorWithArgsCorrect() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertNotNull(nurse);
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongStaffID()
			throws IllegalArgumentException, Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDWrong, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(StaffIDCorrect, nurse.getStaffID());
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordWrong);
		assertEquals(passwordCorrect, nurse.getPassword());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDWrong,
				passwordCorrect);

		assertEquals(loginIDCorrect, nurse.getLoginID());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberWrong, loginIDCorrect,
				passwordCorrect);
		assertEquals(mobileNumberCorrect, nurse.getMobileNumber());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		Nurse nurse = new Nurse(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(TitleCorrect, nurse.getTitle());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		Nurse nurse = new Nurse("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(firstNameCorrect, nurse.getFirstName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Nurse nurse = new Nurse("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(middleNameCorrect, nurse.getMiddleName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		Nurse nurse = new Nurse("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(lastNameCorrect, nurse.getLastName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		Nurse nurse = new Nurse("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(dateOfBirthCorrect, nurse.getDateOfBirth());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineOne() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineOneCorrect,nurse.getAddressLineOne());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineTwoCorrect, nurse.getAddressLineTwo());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineThreeCorrect, nurse.getAddressLineThree());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoCity() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(cityCorrect, nurse.getCity());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoPostCode() throws Exception {
		Nurse nurse = new Nurse(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(postcodeCorrect, nurse.getPostcode());

	}

	@Test
	public void testAmendRecord() {
		fail("Not yet implemented");
	}

	@Test
	public void testExtendTreatment() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOnCall() {
		fail("Not yet implemented");
	}

}
