package hospitalTest;

import static org.junit.Assert.*;
import hospital.address.model.Staff;

import org.junit.Before;
import org.junit.Test;

public class StaffTest {

	String TitleCorrect, TitleWrong, firstNameCorrect, firstNameWrong,
			middleNameCorrect, middleNameWrong, lastNameCorrect, lastNameWrong,
			dateOfBirthCorrect, dateOfBirthWrong, addressLineOneCorrect,
			addressLineOneWrong, addressLineTwoCorrect, addressLineTwoWrong,
			addressLineThreeCorrect, addressLineThreeWrong, cityCorrect,
			cityWrong, postcodeCorrect, postCodeWrong, passwordCorrect,
			passwordBlank, passwordShort;

	int loginIDCorrect, loginIDBoundaryLower, LoginIDBoundaryUpper,
			loginIDShort, loginIDLong, loginIDBlank, StaffIDCorrect,
			StaffIDLowerBoundary, StaffIDUpperBoundary, StaffIDShort,
			StaffIDLong, StaffIDBlank;

	long mobileNumberCorrect, mobileNumberLowerBoundary,
			mobileNumberUpperBoundary, mobileNumberShort, mobileNumberLong,
			mobileNumberWrong, mobileNumberBlank;

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
		passwordBlank = null;
		passwordShort = "pass";

		loginIDCorrect = 666666;
		loginIDBoundaryLower = 100000;
		LoginIDBoundaryUpper = 999999;
		loginIDShort = 6666;
		loginIDLong = 666666666;

		StaffIDCorrect = 666666;
		StaffIDShort = 6666;
		StaffIDLong = 6666666;
		StaffIDLowerBoundary = 100000;
		StaffIDUpperBoundary = 999999;

		mobileNumberCorrect = 447592010304L;
		mobileNumberLowerBoundary = 100000000000L;
		mobileNumberUpperBoundary = 999999999999L;
		mobileNumberShort = 44759201030L;
		mobileNumberLong = 4475920103040L;

	}

	@Test
	public void testStaff() {
		Staff staff = new Staff();
		assertNotNull(staff);
	}

	@Test
	public void testConstructorWithArgsCorrect() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertNotNull(staff);
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongStaffID()
			throws IllegalArgumentException, Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDShort, loginIDCorrect,
				passwordCorrect);
		assertEquals(StaffIDCorrect, staff.getStaffID());
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordShort);
		assertEquals(passwordCorrect, staff.getPassword());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDShort,
				passwordCorrect);

		assertEquals(loginIDCorrect, staff.getLoginID());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberShort, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(mobileNumberCorrect, staff.getContactNumber());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		Staff staff = new Staff(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(TitleCorrect, staff.getTitle());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		Staff staff = new Staff("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(firstNameCorrect, staff.getFirstName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(middleNameCorrect, staff.getMiddleName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(lastNameCorrect, staff.getLastName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(dateOfBirthCorrect, staff.getDateOfBirth());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineOne() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineOneCorrect, staff.getAddressLineOne());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineTwoCorrect, staff.getAddressLineTwo());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineThreeCorrect, staff.getAddressLineThree());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoCity() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(cityCorrect, staff.getCity());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoPostCode() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				mobileNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(postcodeCorrect, staff.getPostcode());

	}

	@Test
	public void testSetStaffID() throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDCorrect);
		assertEquals(StaffIDCorrect, staff.getStaffID());
	}

	@Test
	public void testSetStaffIDLowerBoundary() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDLowerBoundary);
		assertEquals(StaffIDLowerBoundary, staff.getStaffID());
	}

	@Test
	public void testSetStaffIDUpperBoundary() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDUpperBoundary);
		assertEquals(StaffIDUpperBoundary, staff.getStaffID());
	}

	@Test(expected = Exception.class)
	public void testSetStaffIDShort() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDShort);

	}

	@Test(expected = Exception.class)
	public void testSetStaffIDLong() throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDLong);

	}

	@Test
	public void testSetMobileNumber() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberCorrect);
		assertEquals(mobileNumberCorrect, staff.getContactNumber());
	}

	@Test
	public void testSetMobileNumberLowerBoundary()
			throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberLowerBoundary);
		assertEquals(mobileNumberLowerBoundary, staff.getContactNumber());
	}

	@Test
	public void testSetMobileNumberUpperBoundary()
			throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberUpperBoundary);
		assertEquals(mobileNumberUpperBoundary, staff.getContactNumber());
	}

	@Test(expected = Exception.class)
	public void testSetMobileNumberShort() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberShort);
	}

	@Test(expected = Exception.class)
	public void testSetMobileNumberLong() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberLong);
	}

	@Test
	public void testSetLoginID() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDCorrect);
		assertEquals(loginIDCorrect, staff.getLoginID());
	}

	@Test
	public void testSetLoginIDLowerBoundary() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDBoundaryLower);
		assertEquals(loginIDBoundaryLower, staff.getLoginID());
	}

	@Test
	public void testSetLoginIDUpperBoundary() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(LoginIDBoundaryUpper);
		assertEquals(LoginIDBoundaryUpper, staff.getLoginID());
	}

	@Test(expected = Exception.class)
	public void testSetLoginIDShort() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDShort);
	}

	@Test(expected = Exception.class)
	public void testSetLoginIDLong() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDLong);
	}

	@Test
	public void testSetPassword() throws Exception {
		Staff staff = new Staff();
		staff.setPassword(passwordCorrect);
		assertEquals(passwordCorrect, staff.getPassword());
	}

	@Test(expected = Exception.class)
	public void testSetPasswordShort() throws Exception {
		Staff staff = new Staff();
		staff.setPassword(passwordShort);

	}

	@Test(expected = Exception.class)
	public void testSetPasswordBlank() throws Exception {
		Staff staff = new Staff();
		staff.setPassword(passwordBlank);

	}

	@Test
	public void testToString() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		staff.toString();
	}
}
