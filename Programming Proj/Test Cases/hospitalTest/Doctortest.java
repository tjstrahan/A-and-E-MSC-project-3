package hospitalTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import hospital.address.model.Doctor;
import hospital.address.model.Staff;

import org.junit.Before;
import org.junit.Test;

public class Doctortest{

	String TitleCorrect, TitleWrong, firstNameCorrect, firstNameWrong,
			middleNameCorrect, middleNameWrong, lastNameCorrect, lastNameWrong,
			dateOfBirthCorrect, dateOfBirthWrong, addressLineOneCorrect,
			addressLineOneWrong, addressLineTwoCorrect, addressLineTwoWrong,
			addressLineThreeCorrect, addressLineThreeWrong, cityCorrect,
			cityWrong, postcodeCorrect, postCodeWrong, passwordCorrect,
			passwordWrong;

	int loginIDCorrect, loginIDWrong, StaffIDCorrect, StaffIDWrong;

	long contactNumberCorrect, contactNumberWrong;

	@Before
	public void setUp() throws Exception {

		TitleCorrect = "Dr";
		TitleWrong = null;
		firstNameCorrect = "Mavis";
		firstNameWrong = null;
		middleNameCorrect = "Ethel";
		middleNameWrong = null;
		lastNameCorrect = "Andrews";
		lastNameWrong = null;
		dateOfBirthCorrect = "1982/03/04";
		dateOfBirthWrong = null;
		addressLineOneCorrect = "22 Lavender Close";
		addressLineOneWrong = null;
		addressLineTwoCorrect = "Belfast";
		addressLineTwoWrong = null;
		addressLineThreeCorrect = "Antrim";
		addressLineThreeWrong = null;
		cityCorrect = "Belfast";
		cityWrong = null;
		postcodeCorrect = "BT13 5UY";
		postCodeWrong = null;
		passwordCorrect = "password";
		passwordWrong = "pass";

		loginIDCorrect = 555555;
		loginIDWrong = 5555555;

		StaffIDCorrect = 555555;
		StaffIDWrong = 5555;

		contactNumberCorrect = 447594567811L;
		contactNumberWrong = 4475920103040L;
	}

	@Test
	public void testReceptionist() {
		Doctor doctor = new Doctor();
		assertNotNull(doctor);
	}

	@Test
	public void testStaff() {
		Staff staff = new Staff();
		assertNotNull(staff);
	}

	@Test
	public void testConstructorWithArgsCorrect() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertNotNull(doctor);
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongStaffID()
			throws IllegalArgumentException, Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDWrong, loginIDCorrect,
				passwordCorrect);
		assertEquals(StaffIDCorrect, doctor.getStaffID());
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordWrong);
		assertEquals(passwordCorrect, doctor.getPassword());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDWrong,
				passwordCorrect);

		assertEquals(loginIDCorrect, doctor.getLoginID());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberWrong, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(contactNumberCorrect, doctor.getContactNumber());

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(middleNameCorrect, doctor.getMiddleName());

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoAddressLineOne() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineTwoCorrect, doctor.getAddressLineTwo());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineThreeCorrect, doctor.getAddressLineThree());

	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoCity() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
	
	}

	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoPostCode() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);

	}

	/**
	 * Method to amend record
	 */
	public static void amendRecord(){
		}
	/**
	 * Method to extend treatment
	 */
	public static void extendTreatment(){
	}
}
