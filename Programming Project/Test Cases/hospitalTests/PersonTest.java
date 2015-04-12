package hospitalTests;

import static org.junit.Assert.*;
import hospital.Patient;
import hospital.Person;
import hospital.Staff;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing basic instance vars for abstract class person, implemented
 * through patient and staff subclasses and get/sets tested. Each specialisation
 * is tested in its own right to ensure abstract gets/sets function as desired.
 * 
 * @author naveedagahi
 * @author also extended by Janine Kelly
 *
 */
public class PersonTest {

	// test data
	String titleMr, titleMrs, titleMs, titleMiss, titleRev, titleProf, titleDr,
			titleEmpty, titleWrong, firstName, firstNameEmpty, middleName,
			lastName, lastNameEmpty, dateOfBirth, addressLineOne,
			addressLineTwo, addressLineThree, city, postcode, wrongPostcode,
			dateOfBirthEmpty, addressLineOneEmpty, cityEmpty;

	@Before
	public void setUp() throws Exception {

		// test data initialised
		// title test data
		titleMr = "Mr";
		titleMrs = "Mrs";
		titleMs = "Ms";
		titleMiss = "Miss";
		titleRev = "Rev";
		titleProf = "Prof";
		titleDr = "Dr";
		titleEmpty = null;
		titleWrong = "Mister";

		firstName = "Tess";
		firstNameEmpty = null;

		middleName = "Tee";

		lastName = "McCode";
		lastNameEmpty = null;

		dateOfBirth = "1990/01/01";
		dateOfBirthEmpty = null;

		addressLineOne = "30";
		addressLineOneEmpty = null;

		addressLineTwo = "Main Street";
		addressLineThree = "Finaghy";

		city = "Belfast";
		cityEmpty = null;

		postcode = "BT1 1BT";
		wrongPostcode = null;

	}

	/**
	 * Default constructor patient inheritance
	 */
	@Test
	public void testPersonDefaultConstructorPatient() {
		Person person = new Patient();
		assertNotNull(person);
	}

	/**
	 * Default constructor staff inheritance
	 */
	@Test
	public void testPersonDefaultConstructorStaff() {
		Person person = new Staff();
		assertNotNull(person);
	}

	/**
	 * Testing title Mr get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMrPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleMr);
		assertEquals(titleMr, person.getTitle());
	}

	/**
	 * Testing title Mr get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMrStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleMr);
		assertEquals(titleMr, person.getTitle());
	}

	/**
	 * Testing title Rev get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleRevPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleRev);
		assertEquals(titleRev, person.getTitle());
	}

	/**
	 * Testing title Rev get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleRevStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleRev);
		assertEquals(titleRev, person.getTitle());
	}

	/**
	 * Testing title Dr get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleDrPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleDr);
		assertEquals(titleDr, person.getTitle());
	}

	/**
	 * Testing title Dr get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleDrStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleDr);
		assertEquals(titleDr, person.getTitle());
	}

	/**
	 * Testing title Prof get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleProfPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleProf);
		assertEquals(titleProf, person.getTitle());
	}

	/**
	 * Testing title Prof get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleProfStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleProf);
		assertEquals(titleProf, person.getTitle());
	}

	/**
	 * Testing title Ms get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMsPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleMs);
		assertEquals(titleMs, person.getTitle());
	}

	/**
	 * Testing title Ms get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMsStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleMs);
		assertEquals(titleMs, person.getTitle());
	}

	/**
	 * Testing title Miss get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMissPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleMiss);
		assertEquals(titleMiss, person.getTitle());
	}

	/**
	 * Testing title Miss get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMissStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleMiss);
		assertEquals(titleMiss, person.getTitle());
	}

	/**
	 * Testing title Mrs get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMrsPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleMrs);
		assertEquals(titleMrs, person.getTitle());
	}

	/**
	 * Testing title Mrs get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetTitleMrsStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleMrs);
		assertEquals(titleMrs, person.getTitle());
	}

	/**
	 * Testing title wrong get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetTitleWrongPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleWrong);

	}

	/**
	 * Testing title Mrs get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetTitleWrongStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleWrong);
	}

	/**
	 * Testing title empty get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetTitleEmptyPatient() throws Exception {
		Person person = new Patient();
		person.setTitle(titleEmpty);

	}

	/**
	 * Testing title empty get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetTitleEmptyStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(titleEmpty);
	}

	/**
	 * Testing first name get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetFirstNamePatient() throws Exception {
		Person person = new Patient();
		person.setFirstName(firstName);
		assertEquals(firstName, person.getFirstName());
	}

	/**
	 * Testing first name get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetSetFirstNameStaff() throws Exception {
		Person person = new Staff();
		person.setFirstName(firstName);
		assertEquals(firstName, person.getFirstName());
	}

	/**
	 * Testing empty first name get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetFirstNameEmptyPatient() throws Exception {
		Person person = new Patient();
		person.setFirstName(firstNameEmpty);

	}

	/**
	 * Testing empty first name get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetSetFirstNameEmptyStaff() throws Exception {
		Person person = new Staff();
		person.setFirstName(firstNameEmpty);

	}

	/**
	 * Testing middle name get/set methods for patient instance
	 */
	@Test
	public void testGetSetMiddleNamePatient() {
		Person person = new Patient();
		person.setMiddleName(middleName);
		assertEquals(middleName, person.getMiddleName());
	}

	/**
	 * Testing middle name get/set methods for staff instance
	 */
	@Test
	public void testGetSetMiddleNameStaff() {
		Person person = new Staff();
		person.setMiddleName(middleName);
		assertEquals(middleName, person.getMiddleName());
	}

	/**
	 * Testing last name get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetLastNamePatient() throws Exception {
		Person person = new Patient();
		person.setLastName(lastName);
		assertEquals(lastName, person.getLastName());
	}

	/**
	 * Testing last name get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetLastNameStaff() throws Exception {
		Person person = new Staff();
		person.setLastName(lastName);
		assertEquals(lastName, person.getLastName());
	}

	/**
	 * Testing empty last name get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetLastNameEmptyPatient() throws Exception {
		Person person = new Patient();
		person.setFirstName(lastNameEmpty);

	}

	/**
	 * Testing empty last name get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetSetLastNameEmptyStaff() throws Exception {
		Person person = new Staff();
		person.setFirstName(lastNameEmpty);

	}

	/**
	 * Testing date of birth get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetDateOfBirthPatient() throws Exception {
		Person person = new Patient();
		person.setDateOfBirth(dateOfBirth);
		assertEquals(dateOfBirth, person.getDateOfBirth());
	}

	/**
	 * Testing date of birth get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetDateOfBirthStaff() throws Exception {
		Person person = new Staff();
		person.setDateOfBirth(dateOfBirth);
		assertEquals(dateOfBirth, person.getDateOfBirth());

	}

	/**
	 * Testing empty DOB get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetFirstNameEmptyDOB() throws Exception {
		Person person = new Patient();
		person.setFirstName(dateOfBirthEmpty);

	}

	/**
	 * Testing empty DOB get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetSetFirstNameEmptyDOB() throws Exception {
		Person person = new Staff();
		person.setFirstName(dateOfBirthEmpty);

	}

	/**
	 * Testing address line one get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetAddressLineOnePatient() throws Exception {
		Person person = new Patient();
		person.setAddressLineOne(addressLineOne);
		assertEquals(addressLineOne, person.getAddressLineOne());
	}

	/**
	 * Testing address line one get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetAddressLineOneStaff() throws Exception {
		Person person = new Staff();
		person.setAddressLineOne(addressLineOne);
		assertEquals(addressLineOne, person.getAddressLineOne());
	}

	/**
	 * Testing empty address line one get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetAddressLineOneEmptyPatient() throws Exception {
		Person person = new Patient();
		person.setFirstName(addressLineOneEmpty);

	}

	/**
	 * Testing empty address line one get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetAddressLineOneEmptyStaff() throws Exception {
		Person person = new Staff();
		person.setFirstName(addressLineOneEmpty);

	}

	/**
	 * Testing address line two get/set methods for patient instance
	 */
	@Test
	public void testGetSetAddressLineTwoPatient() {
		Person person = new Patient();
		person.setAddressLineTwo(addressLineTwo);
		assertEquals(addressLineTwo, person.getAddressLineTwo());

	}

	/**
	 * Testing address line two get/set methods for staff instance
	 */
	@Test
	public void testGetSetAddressLineTwoStaff() {
		Person person = new Staff();
		person.setAddressLineTwo(addressLineTwo);
		assertEquals(addressLineTwo, person.getAddressLineTwo());
	}

	/**
	 * Testing address line three get/set methods for patient instance
	 */
	@Test
	public void testGetSetAddressLineThreePatient() {
		Person person = new Patient();
		person.setAddressLineThree(addressLineThree);
		assertEquals(addressLineThree, person.getAddressLineThree());
	}

	/**
	 * Testing address line three get/set methods for staff instance
	 */
	@Test
	public void testGetSetAddressLineThreeStaff() {
		Person person = new Staff();
		person.setAddressLineThree(addressLineThree);
		assertEquals(addressLineThree, person.getAddressLineThree());
	}

	/**
	 * Testing city get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetCityPatient() throws Exception {
		Person person = new Patient();
		person.setCity(city);
		assertEquals(city, person.getCity());

	}

	/**
	 * Testing city get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetCityStaff() throws Exception {
		Person person = new Staff();
		person.setCity(city);
		assertEquals(city, person.getCity());
	}

	/**
	 * Testing empty city get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetCityEmptyPatient() throws Exception {
		Person person = new Patient();
		person.setFirstName(cityEmpty);

	}

	/**
	 * Testing empty city get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testSetSetCityEmptyStaff() throws Exception {
		Person person = new Staff();
		person.setFirstName(cityEmpty);

	}

	/**
	 * Testing postcode get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetPostcodePatient() throws Exception {
		Person person = new Patient();
		person.setPostcode(postcode);
		assertEquals(postcode, person.getPostcode());
	}

	/**
	 * Testing postcode get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetSetPostcodeStaff() throws Exception {
		Person person = new Staff();
		person.setPostcode(postcode);
		assertEquals(postcode, person.getPostcode());
	}

	/**
	 * Testing wrong postcode get/set methods for patient instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetWrongPostcodePatient() throws Exception {
		Person person = new Patient();
		person.setPostcode(wrongPostcode);
	}

	/**
	 * Testing postcode get/set methods for staff instance
	 * 
	 * @throws Exception
	 */
	@Test(expected = Exception.class)
	public void testGetSetWrongPostcodeStaff() throws Exception {
		Person person = new Staff();
		person.setPostcode(wrongPostcode);

	}

} // end of test
