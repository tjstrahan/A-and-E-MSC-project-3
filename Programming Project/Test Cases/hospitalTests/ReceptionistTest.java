package hospitalTests;

import static org.junit.Assert.*;
import hospital.Receptionist;

import org.junit.Before;
import org.junit.Test;

public class ReceptionistTest {

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
	public void testReceptionist() {
		Receptionist receptionist = new Receptionist();
		assertNotNull(receptionist);
	}

	@Test
	public void testConstructorWithArgsCorrect() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertNotNull(receptionist);
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongStaffID()
			throws IllegalArgumentException, Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDWrong, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(StaffIDCorrect, receptionist.getStaffID());
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordWrong);
		assertEquals(passwordCorrect, receptionist.getPassword());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDWrong,
				passwordCorrect);

		assertEquals(loginIDCorrect, receptionist.getLoginID());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberWrong, loginIDCorrect,
				passwordCorrect);
		assertEquals(mobileNumberCorrect, receptionist.getMobileNumber());

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		@SuppressWarnings("unused")
		Receptionist receptionist = new Receptionist(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		@SuppressWarnings("unused")
		Receptionist receptionist = new Receptionist("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Receptionist receptionist = new Receptionist("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(middleNameCorrect, receptionist.getMiddleName());

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		@SuppressWarnings("unused")
		Receptionist receptionist = new Receptionist("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
	
	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		@SuppressWarnings("unused")
		Receptionist receptionist = new Receptionist("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
	
	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoAddressLineOne() throws Exception {
		@SuppressWarnings("unused")
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineTwoCorrect, receptionist.getAddressLineTwo());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineThreeCorrect, receptionist.getAddressLineThree());

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoCity() throws Exception {
		@SuppressWarnings("unused")
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoPostCode() throws Exception {
		@SuppressWarnings("unused")
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
	
	}

	//@Test
	//public void testLookUpPatient() {
	//	Receptionist recep = new Receptionist();
	//	recep.lookUpPatient();
	//}

	//@Test
	//public void testAdmitPatient() {
	//	Receptionist recep = new Receptionist();
	//	recep.admitPatient();
	//}

}
