package hospitalTest;

import static org.junit.Assert.*;
import hospital.address.model.Receptionist;
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

	long contactNumberCorrect, contactNumberWrong;

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

		contactNumberCorrect = (long) 447592010304L;
		contactNumberWrong = (long) 4475920103040L;
	}

	@Test
	public void testReceptionist() {
		Receptionist receptionist = new Receptionist();
		assertNotNull(receptionist);
	}

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
