package hospitaltests;

import static org.junit.Assert.*;
import hospital.Doctor;
import hospital.Staff;

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

	long mobileNumberCorrect, mobileNumberWrong;

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

		mobileNumberCorrect = (long) 44759456781.00;
		mobileNumberWrong = (long) 4475920103040.00;
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
	public void testConstructorWithArgsCorrect() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
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
				StaffIDWrong, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(StaffIDCorrect, doctor.getStaffID());
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordWrong);
		assertEquals(passwordCorrect, doctor.getPassword());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDWrong,
				passwordCorrect);

		assertEquals(loginIDCorrect, doctor.getLoginID());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberWrong, loginIDCorrect,
				passwordCorrect);
		assertEquals(mobileNumberCorrect, doctor.getMobileNumber());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoTitle() {
		Doctor doctor = new Doctor(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(TitleCorrect, doctor.getTitle());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoFirstName() {
		Doctor doctor = new Doctor("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(firstNameCorrect, doctor.getFirstName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() {
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(middleNameCorrect, doctor.getMiddleName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoLastName() {
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(lastNameCorrect, doctor.getLastName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoDOB() {
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(dateOfBirthCorrect, doctor.getDateOfBirth());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineOne() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineOneCorrect, doctor.getAddressLineOne());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineTwoCorrect, doctor.getAddressLineTwo());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineThreeCorrect, doctor.getAddressLineThree());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoCity() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(cityCorrect, doctor.getCity());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoPostCode() {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(postcodeCorrect, doctor.getPostcode());

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
