package hospitalTest;

import static org.junit.Assert.*;
import hospital.address.model.Receptionist;
import org.junit.Before;
import org.junit.Test;

public class ReceptionistTest {
	
	/**
	 * Test class to test receptionist
	 */

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
		
		/**
		 * Test data to test receptionist
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

		contactNumberCorrect = (long) 447592010304L;
		contactNumberWrong = (long) 4475920103040L;
	}
	/**
	 * Testing the default constructor
	 */

	@Test
	public void testReceptionist() {
		Receptionist receptionist = new Receptionist();
		assertNotNull(receptionist);
	}
	/**
	 * Testing the non default constructor
	 * @throws Exception
	 */

	@Test
	public void testConstructorWithArgsCorrect() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect, firstNameCorrect, 
				middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordCorrect);
		assertNotNull(receptionist);
	}
	/**
	 * Testing the non default constructor with the wrong staff id
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongStaffID()
			throws IllegalArgumentException, Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberWrong, StaffIDWrong,
				loginIDCorrect, passwordCorrect);
		assertEquals(StaffIDCorrect, receptionist.getStaffID());
	}
	/**
	 * Testing the non default constructor with the wrong password 
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongPassword() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect,
				loginIDCorrect, passwordWrong);
		assertEquals(passwordCorrect, receptionist.getPassword());

	}
	/**
	 * Testing the non default constructor with the wrong login
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongLogIn() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect,contactNumberCorrect, StaffIDCorrect, 
				loginIDWrong, passwordCorrect);

		assertEquals(loginIDCorrect, receptionist.getLoginID());

	}
	/**
	 * Testing the non default constructor with the wrong mobile number
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsWrongMobileNumber() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect,contactNumberWrong, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(contactNumberCorrect, receptionist.getContactNumber());

	}
	/**
	 * Testing the non default constructor with the wrong title
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoTitle() throws Exception {
		Receptionist receptionist = new Receptionist(TitleWrong,
				firstNameWrong, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postcodeCorrect,contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(TitleCorrect, receptionist.getTitle());
	}
	/**
	 * Testing the non default constructor with the wrong first name
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoFirstName() throws Exception {
		Receptionist receptionist = new Receptionist("Mr", firstNameWrong,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect,postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect,  loginIDCorrect,
				passwordCorrect);
		assertEquals(firstNameCorrect, receptionist.getFirstName());
	}
	/**
	 * Testing the non default constructor with the wrong middle name
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoMiddleName() throws Exception {
		Receptionist receptionist = new Receptionist("Mr", firstNameCorrect,
				middleNameWrong, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect,  loginIDCorrect,
				passwordCorrect);
		assertEquals(middleNameCorrect, receptionist.getMiddleName());

	}
	/**
	 * Testing the non default constructor with the wrong last name
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoLastName() throws Exception {
		Receptionist receptionist = new Receptionist("Mr", firstNameCorrect,
				middleNameCorrect, lastNameWrong, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect,  loginIDCorrect,
				passwordCorrect);
		assertEquals(lastNameCorrect, receptionist.getLastName());

	}
	/**
	 * Testing the non default constructor with the wrong dob
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoDOB() throws Exception {
		Receptionist receptionist = new Receptionist("Mr", firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthWrong,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect,  loginIDCorrect,
				passwordCorrect);
		assertEquals(dateOfBirthCorrect, receptionist.getDateOfBirth());
	}
	/**
	 * Testing the non default constructor with the wrong address line one
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineOne() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneWrong, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect, loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineOneCorrect, receptionist.getAddressLineOne());
	}
	/**
	 * Testing the non default constructor with the wrong address line two
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineTwo() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect, addressLineTwoWrong,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNumberCorrect, StaffIDCorrect,  loginIDCorrect,
				passwordCorrect);
		assertEquals(addressLineTwoCorrect, receptionist.getAddressLineTwo());

	}
	/**
	 * Testing the non default constructor with the wrong address line three
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoAddressLineThree() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeWrong, cityCorrect,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(addressLineThreeCorrect,
				receptionist.getAddressLineThree());

	}
	/**
	 * Testing the non default constructor with the wrong city
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoCity() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityWrong,
				postcodeCorrect, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(cityCorrect,
				receptionist.getCity());
	}
	/**
	 * Testing the non default constructor with the wrong postcode
	 * @throws Exception
	 */

	@Test(expected = AssertionError.class)
	public void testConstructorWithArgsNoPostCode() throws Exception {
		Receptionist receptionist = new Receptionist(TitleCorrect,
				firstNameCorrect, middleNameCorrect, lastNameCorrect,
				dateOfBirthCorrect, addressLineOneCorrect,
				addressLineTwoCorrect, addressLineThreeCorrect, cityCorrect,
				postCodeWrong, contactNumberCorrect, StaffIDCorrect, 
				loginIDCorrect, passwordCorrect);
		assertEquals(postcodeCorrect,
				receptionist.getPostcode());
	}
}
