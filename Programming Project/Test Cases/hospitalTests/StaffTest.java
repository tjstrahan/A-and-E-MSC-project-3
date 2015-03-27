package hospitalTests;

import static org.junit.Assert.*;
import hospital.Staff;

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

		mobileNumberCorrect = (long) 447592010304.00;
		mobileNumberLowerBoundary = (long) 100000000000.00;
		mobileNumberUpperBoundary = (long) 999999999999.00;
		mobileNumberShort = (long) 44759201030.00;
		mobileNumberLong = (long) 4475920103040.00;

	}

	@Test
	public void testStaff() {
		Staff staff = new Staff();
		assertNotNull(staff);
	}

	@Test
	public void testConstructorWithArgsCorrect() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
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
				StaffIDShort, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(StaffIDCorrect, staff.getStaffID());
	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordShort);
		assertEquals(passwordCorrect, staff.getPassword());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDShort,
				passwordCorrect);

		assertEquals(loginIDCorrect, staff.getLoginID());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberShort, loginIDCorrect,
				passwordCorrect);
		assertEquals(mobileNumberCorrect, staff.getMobileNumber());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoTitle() {
		Staff staff = new Staff(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(TitleCorrect, staff.getTitle());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoFirstName() {
		Staff staff = new Staff("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(firstNameCorrect, staff.getFirstName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(middleNameCorrect, staff.getMiddleName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoLastName() {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(lastNameCorrect, staff.getLastName());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoDOB() {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(dateOfBirthCorrect, staff.getDateOfBirth());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineOne() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineOneCorrect, staff.getAddressLineOne());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineTwoCorrect, staff.getAddressLineTwo());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeWrong, cityCorrect, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineThreeCorrect, staff.getAddressLineThree());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoCity() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityWrong, postcodeCorrect,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(cityCorrect, staff.getCity());

	}

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoPostCode() {
		Staff staff = new Staff(TitleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postCodeWrong,
				StaffIDCorrect, mobileNumberCorrect, loginIDCorrect,
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
		staff.setMobileNumber(mobileNumberCorrect);
		assertEquals(mobileNumberCorrect, staff.getMobileNumber());
	}

	@Test
	public void testSetMobileNumberLowerBoundary()
			throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setMobileNumber(mobileNumberLowerBoundary);
		assertEquals(mobileNumberLowerBoundary, staff.getMobileNumber());
	}

	@Test
	public void testSetMobileNumberUpperBoundary()
			throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setMobileNumber(mobileNumberUpperBoundary);
		assertEquals(mobileNumberUpperBoundary, staff.getMobileNumber());
	}

	@Test(expected = Exception.class)
	public void testSetMobileNumberShort() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setMobileNumber(mobileNumberShort);
	}

	@Test(expected = Exception.class)
	public void testSetMobileNumberLong() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setMobileNumber(mobileNumberLong);
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
	public void testToString() {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, StaffIDCorrect, mobileNumberCorrect,
				loginIDCorrect, passwordCorrect);
		staff.toString();
	}
}
