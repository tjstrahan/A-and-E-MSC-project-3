package hospitalTest;
/**
 * Doctortest.java as part of the Team Internet Java Program. 
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import hospital.address.model.Doctor;
import hospital.address.model.Staff;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the Doctor.java file in the hospital.address.model package of
 * the program.
 * 
 * @author Team Internet
 *
 */
public class Doctortest {
	
	/**
	 * Variables Declared for Testing
	 * String, int, long
	 */
	String TitleCorrect, TitleWrong, firstNameCorrect, firstNameWrong,
			middleNameCorrect, middleNameWrong, lastNameCorrect, lastNameWrong,
			dateOfBirthCorrect, dateOfBirthWrong, addressLineOneCorrect,
			addressLineOneWrong, addressLineTwoCorrect, addressLineTwoWrong,
			addressLineThreeCorrect, addressLineThreeWrong, cityCorrect,
			cityWrong, postcodeCorrect, postCodeWrong, passwordCorrect,
			passwordWrong;

	int loginIDCorrect, loginIDWrong, StaffIDCorrect, StaffIDWrong,
			medicalTeamCorrect;

	long contactNumberCorrect, contactNumberWrong;

	/**
	 * Variables Declared for Testing Methods.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test to confirm Doctor() is NotNull.
	 */
	@Test
	public void testReceptionist() {
		Doctor doctor = new Doctor();
		assertNotNull(doctor);
	}

	/**
	 * Test to confirm that Staff() is NotNull.
	 */
	@Test
	public void testStaff() {
		Staff staff = new Staff();
		assertNotNull(staff);
	}

	/**
	 * Test constructor with Args is correct and NotNull.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testConstructorWithArgsCorrect() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertNotNull(doctor);
	}

	/**
	 * Test constructor with Args - with Staff ID not implemented
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongStaffID()
			throws IllegalArgumentException, Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDWrong, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(StaffIDCorrect, doctor.getStaffID());
	}

	/**
	 * Test constructor with Args - with correct password not implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordWrong, medicalTeamCorrect);
		assertEquals(passwordCorrect, doctor.getPassword());

	}

	/**
	 * Test constructor with Args - with incorrect Log in details.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDWrong,
				passwordCorrect, medicalTeamCorrect);

		assertEquals(loginIDCorrect, doctor.getLoginID());

	}

	/**
	 * Test constructor with Args - with incorrect mobile number.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberWrong, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(contactNumberCorrect, doctor.getContactNumber());

	}

	/**
	 * Test constructor with Args - with title not implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleWrong, firstNameWrong,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);

	}

	/**
	 * Test constructor with Args - with no FirstName implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);

	}

	/**
	 * Test constructor with Args - with no MiddleName implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);
		assertEquals(middleNameCorrect, doctor.getMiddleName());

	}

	/**
	 * Test constructor with Args - with lastName not implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);

	}

	/**
	 * Test constructor with Args - with DOB not implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect, medicalTeamCorrect);

	}

	/**
	 * Test constructor with Args - with AddressLineOne not Implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoAddressLineOne() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);

	}

	/**
	 * Test constructor with Args - with AddressLineTwo not Implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(addressLineTwoCorrect, doctor.getAddressLineTwo());

	}

	/**
	 * Test constructor with Args - with AddressLineThree not Implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() throws Exception {
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);
		assertEquals(addressLineThreeCorrect, doctor.getAddressLineThree());

	}

	/**
	 * Test constructor with Args - with no City Name implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoCity() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);

	}

	/**
	 * Test constructor with Args - with Post Code not Implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testConstructorWithArgsNoPostCode() throws Exception {
		@SuppressWarnings("unused")
		Doctor doctor = new Doctor(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect, medicalTeamCorrect);

	}

	/**
	 * The Method to amend record
	 */
	public static void amendRecord() {
	}

	/**
	 * The Method to extend treatment
	 */
	public static void extendTreatment() {
	}
} // Class End
