package hospitalTest;
/**
 * StaffTest.java as part of the Team Internet Java Program. 
 */
import static org.junit.Assert.*;
import hospital.address.model.Staff;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the Staff.java class in the hospital.address.model package.
 * 
 * @author Team Internet
 */
public class StaffTest {
	/**
	 * Initialising Variables for Method tests. String, int long
	 */
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

	/**
	 * Variables Declared for SetUp.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Staff() is NotNull
	 */
	@Test
	public void testStaff() {
		Staff staff = new Staff();
		assertNotNull(staff);
	}

	/**
	 * Test Constructor with Args - Clarification it is NotNull.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args - Wrong StaffID implemented
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args -Wrong Password Implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args - Wrong Log In implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args - Wrong Mobile Number implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args - No title implemented
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		Staff staff = new Staff(TitleWrong, firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(TitleCorrect, staff.getTitle());

	}

	/**
	 * Test Constructor with Args - No firstName implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		Staff staff = new Staff("Mr", firstNameWrong, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(firstNameCorrect, staff.getFirstName());

	}

	/**
	 * Test Constructor with Args - No middle name implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameWrong,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(middleNameCorrect, staff.getMiddleName());

	}

	/**
	 * Test Constructor with Args - No last name implemented.
	 * 
	 * @throws Exception
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameWrong, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(lastNameCorrect, staff.getLastName());

	}

	/**
	 * Test Constructor with Args - No DOB implemented.
	 */
	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthWrong, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect);
		assertEquals(dateOfBirthCorrect, staff.getDateOfBirth());

	}

	/**
	 * Test Constructor with Args - No addressLineOne implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args -No addressLineTwo implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args - No addressLineThree implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args - No city implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test Constructor with Args - No PostCode implemented.
	 * 
	 * @throws Exception
	 */
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

	/**
	 * Test StaffIDCorrect throws an IllegalArgumentException.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test
	public void testSetStaffID() throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDCorrect);
		assertEquals(StaffIDCorrect, staff.getStaffID());
	}

	/**
	 * Test the staff Id Lower Boundary.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test
	public void testSetStaffIDLowerBoundary() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDLowerBoundary);
		assertEquals(StaffIDLowerBoundary, staff.getStaffID());
	}

	/**
	 * Test the StaffID upper Boundary.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test
	public void testSetStaffIDUpperBoundary() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDUpperBoundary);
		assertEquals(StaffIDUpperBoundary, staff.getStaffID());
	}

	/**
	 * Test the setting of Short Staff Id
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetStaffIDShort() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDShort);

	}

	/**
	 * Test the exception of a Long Staff ID.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetStaffIDLong() throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setStaffID(StaffIDLong);

	}

	/**
	 * Throw Exception if mobile number is incorrect.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test
	public void testSetMobileNumber() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberCorrect);
		assertEquals(mobileNumberCorrect, staff.getContactNumber());
	}

	/**
	 * Throw exception if mobile number is less than the lower Boundary.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test
	public void testSetMobileNumberLowerBoundary()
			throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberLowerBoundary);
		assertEquals(mobileNumberLowerBoundary, staff.getContactNumber());
	}

	/**
	 * Throw exception if mobile number us high than the upper Boundary.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test
	public void testSetMobileNumberUpperBoundary()
			throws IllegalArgumentException, Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberUpperBoundary);
		assertEquals(mobileNumberUpperBoundary, staff.getContactNumber());
	}

	/**
	 * Throw Exception if mobile number is short.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetMobileNumberShort() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberShort);
	}

	/**
	 * Throw Exception if mobile number is too long.
	 * 
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetMobileNumberLong() throws IllegalArgumentException,
			Exception {
		Staff staff = new Staff();
		staff.setContactNumber(mobileNumberLong);
	}

	/**
	 * Test the LogIn Id.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetLoginID() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDCorrect);
		assertEquals(loginIDCorrect, staff.getLoginID());
	}

	/**
	 * Throw Exception if the LoginID is lower than the Boundary.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetLoginIDLowerBoundary() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDBoundaryLower);
		assertEquals(loginIDBoundaryLower, staff.getLoginID());
	}

	/**
	 * Throw Exception if the LoginID is greater than the Boundary.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetLoginIDUpperBoundary() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(LoginIDBoundaryUpper);
		assertEquals(LoginIDBoundaryUpper, staff.getLoginID());
	}

	/**
	 * Throw exception of the LoginID is too short.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetLoginIDShort() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDShort);
	}

	/**
	 * Throw exception if the Login ID is too long.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetLoginIDLong() throws Exception {
		Staff staff = new Staff();
		staff.setLoginID(loginIDLong);
	}

	/**
	 * Test password = correct.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetPassword() throws Exception {
		Staff staff = new Staff();
		staff.setPassword(passwordCorrect);
		assertEquals(passwordCorrect, staff.getPassword());
	}

	/**
	 * Throw exception if password is shorter than the set limit.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetPasswordShort() throws Exception {
		Staff staff = new Staff();
		staff.setPassword(passwordShort);

	}

	/**
	 * Throw Exception if the password is not entered.
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetPasswordBlank() throws Exception {
		Staff staff = new Staff();
		staff.setPassword(passwordBlank);

	}

	/**
	 * Test the implementation of the data to String for display
	 * 
	 * @throws Exception
	 */
	@Test
	public void testToString() throws Exception {
		Staff staff = new Staff("Mr", firstNameCorrect, middleNameCorrect,
				lastNameCorrect, dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, mobileNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect);
		staff.toString();
	}
} // Class close
