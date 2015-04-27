package hospitalTest;

import static org.junit.Assert.*;
import hospital.address.model.Patient;
import hospital.address.model.Person;
import hospital.address.model.Staff;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing basic instance vars for abstract class person, implemented through patient and
 * staff subclasses and get/sets tested.
 * Each specialisation is tested in its own right to ensure abstract gets/sets function as desired.
 * @author naveedagahi
 *
 */
public class PersonTest {

	// test data
	String title, firstName, middleName, lastName, dateOfBirth, addressLineOne,
			addressLineTwo, addressLineThree, city, postcode;

	@Before
	public void setUp() throws Exception {

	// test data initialised
		
		title = "Ms";
		firstName = "Tess";
		middleName = "Tee";
		lastName = "McCode";
		dateOfBirth = "1990/01/01";
		addressLineOne = "30";
		addressLineTwo = "Main Street";
		addressLineThree = "Finaghy";
		city = "Belfast";
		postcode = "BT1 1BT";

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
	 * Testing title get/set methods for patient instance
	 * @throws Exception 
	 */
	@Test
	public void testGetSetTitlePatient() throws Exception {
		Person person = new Patient();
		person.setTitle(title);
		assertEquals(title, person.getTitle());
	}

	/**
	 * Testing title get/set methods for staff instance
	 * @throws Exception 
	 */
	@Test
	public void testGetSetTitleStaff() throws Exception {
		Person person = new Staff();
		person.setTitle(title);
		assertEquals(title, person.getTitle());
	}

	/**
	 * Testing first name get/set methods for patient instance
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
	 * @throws Exception 
	 */
	@Test
	public void testSetSetFirstNameStaff() throws Exception {
		Person person = new Staff();
		person.setFirstName(firstName);
		assertEquals(firstName, person.getFirstName());
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
	 * @throws Exception 
	 */
	@Test
	public void testGetSetLastNameStaff() throws Exception {
		Person person = new Staff();
		person.setLastName(lastName);
		assertEquals(lastName, person.getLastName());
	}

	/**
	 * Testing date of birth get/set methods for patient instance
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
	 * @throws Exception 
	 */
	@Test
	public void testGetSetDateOfBirthStaff() throws Exception {
		Person person = new Staff();
		person.setDateOfBirth(dateOfBirth);
		assertEquals(dateOfBirth, person.getDateOfBirth());

	}

	/**
	 * Testing address line one get/set methods for patient instance
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
	 * @throws Exception 
	 */
	@Test
	public void testGetSetAddressLineOneStaff() throws Exception {
		Person person = new Staff();
		person.setAddressLineOne(addressLineOne);
		assertEquals(addressLineOne, person.getAddressLineOne());
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
	 * @throws Exception 
	 */
	@Test
	public void testGetSetCityStaff() throws Exception {
		Person person = new Staff();
		person.setCity(city);
		assertEquals(city, person.getCity());
	}

	/**
	 * Testing postcode get/set methods for patient instance
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
	 * @throws Exception 
	 */
	@Test
	public void testGetSetPostcodeStaff() throws Exception {
		Person person = new Staff();
		person.setPostcode(postcode);
		assertEquals(postcode, person.getPostcode());
	}

} // end of test
