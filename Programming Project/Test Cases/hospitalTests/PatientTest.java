package hospitalTests;

import static org.junit.Assert.*;
import hospital.Patient;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * NO TESTS WERE WRITTEN FOR ADMISSION TIME, TOP OF LIST OR TRIAGE CATEGORY. I
 * WAS ALSO SLIGHTLY UNCLEAR ABOUT TRIAGE CATEGORY HOWEVER HAVE STATED THAT IT
 * MUST BE SET TO ZERO AS PER A PREVIOUS CONVERSATION WITH KIERON.
 * 
 * @author Janine
 *
 */
public class PatientTest {

	String titleCorrect, titleWrong, firstNameCorrect, firstNameWrong,
			middleNameCorrect, middleNameWrong, lastNameCorrect, lastNameWrong,
			dateOfBirthCorrect, dateOfBirthWrong, addressLineOneCorrect,
			addressLineOneWrong, addressLineTwoCorrect,
			addressLineThreeCorrect, cityCorrect, cityWrong, postcodeCorrect,
			postCodeWrong, allergiesCorrect, knownConditionsCorrect, aPositive,
			aNegative, bPositive, bNegative, abPositive, abNegative, oPositive,
			oNegative, incorrectBloodType, incorrectBloodTypeLong,
			correctNextOfKin, correctGPName, incorrectGPName, correctGPCode,
			incorrectGPCode, incorrectEmptyGPCode, incorrectGPCodeLong;

	int nhsNumberLowerBoundary, nhsNumberUpperBoundary, nhsNumberCorrect,
			nhsNumberLowerBoundaryIncorrect, nhsNumberUpperBoundaryIncorrect,
			nhsNumberIncorrect, incorrectGPCodeNumber, correctTriageCategory;

	char female, male, incorrectSexChar;

	double correctAdmissionTime;

	boolean correctTopOfList;

	@Before
	public void setUp() throws Exception {

		// Test data for superclass
		titleCorrect = "Miss";
		titleWrong = null;
		firstNameCorrect = "Janine";
		firstNameWrong = null;
		middleNameCorrect = "Helen";
		lastNameCorrect = "Kelly";
		lastNameWrong = null;
		dateOfBirthCorrect = "13/11/91";
		dateOfBirthWrong = null;
		addressLineOneCorrect = "2 Big Road";
		addressLineOneWrong = null;
		addressLineTwoCorrect = "Glenavy";
		addressLineThreeCorrect = "Antrim";
		cityCorrect = "Belfast";
		cityWrong = null;
		postcodeCorrect = "BT33 5LJ";
		postCodeWrong = null;

		// Test data for NHS number
		nhsNumberCorrect = 123456789;
		nhsNumberIncorrect = 1234567899;
		nhsNumberLowerBoundary = 100000000;
		nhsNumberUpperBoundary = 999999999;
		nhsNumberLowerBoundaryIncorrect = 99999999;
		nhsNumberUpperBoundaryIncorrect = 1000000000;

		// Test data for allergies
		allergiesCorrect = "Correct allergy";

		// Test data for known conditions
		knownConditionsCorrect = "Known condition correct";

		// Test data for blood group
		aPositive = "A+";
		aNegative = "A-";
		bPositive = "B+";
		bNegative = "B-";
		abPositive = "AB+";
		abNegative = "AB-";
		oPositive = "O+";
		oNegative = "O-";
		incorrectBloodType = "A";
		incorrectBloodTypeLong = "Blood group A";

		// Test data for sex
		female = 'F';
		male = 'M';
		incorrectSexChar = 'A';

		// Test data for next of kin
		correctNextOfKin = "Rory McCaul";

		// Test data for GP name
		correctGPName = "Dr Correct Doctor";
		incorrectGPName = null;

		// Test data for GP code
		correctGPCode = "E1234";
		incorrectGPCode = "F1234";
		incorrectGPCodeLong = "E12345";
		incorrectEmptyGPCode = null;

		// Triage category
		correctTriageCategory = 0;

		// Admission
		correctAdmissionTime = 12.00;

		// Top of list

		correctTopOfList = false;

	}

	@Test
	public void testDefaultConstructor() {
		Patient patient = new Patient();
		assertNotNull(patient);
	}

	@Test
	public void testConstructorArgumentsCorrect()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				nhsNumberCorrect, allergiesCorrect, knownConditionsCorrect,
				aPositive, female, correctNextOfKin, correctGPName,
				correctGPCode, correctTriageCategory, correctAdmissionTime,
				correctTopOfList);
		assertNotNull(patient);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorArgumentsIncorrectNHSNumber()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				nhsNumberIncorrect, allergiesCorrect, knownConditionsCorrect,
				aPositive, female, correctNextOfKin, correctGPName,
				correctGPCode, correctTriageCategory, correctAdmissionTime,
				correctTopOfList);
		assertNotNull(patient);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorArgumentsIncorrectBloodGroup()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				nhsNumberCorrect, allergiesCorrect, knownConditionsCorrect,
				incorrectBloodType, female, correctNextOfKin, correctGPName,
				correctGPCode, correctTriageCategory, correctAdmissionTime,
				correctTopOfList);
		assertNotNull(patient);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorArgumentsIncorrectSex()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				nhsNumberCorrect, allergiesCorrect, knownConditionsCorrect,
				aPositive, incorrectSexChar, correctNextOfKin, correctGPName,
				correctGPCode, correctTriageCategory, correctAdmissionTime,
				correctTopOfList);
		assertNotNull(patient);
	}
	
	@Test(expected = NullPointerException.class)
	public void testConstructorArgumentsIncorrectGPName()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				nhsNumberCorrect, allergiesCorrect, knownConditionsCorrect,
				aPositive, female, correctNextOfKin, incorrectGPName,
				correctGPCode, correctTriageCategory, correctAdmissionTime,
				correctTopOfList);
		assertNotNull(patient);
	}

	@Test
	public void testSetCorrectNHSNumber() {
		Patient patient = new Patient();
		patient.setNhsNumber(nhsNumberCorrect);
		assertEquals(nhsNumberCorrect, patient.getNhsNumber());
	}

	@Test
	public void testSetCorrectNHSNumberLower() {
		Patient patient = new Patient();
		patient.setNhsNumber(nhsNumberLowerBoundary);
		assertEquals(nhsNumberLowerBoundary, patient.getNhsNumber());
	}

	@Test
	public void testSetCorrectNHSNumberUpper() {
		Patient patient = new Patient();
		patient.setNhsNumber(nhsNumberUpperBoundary);
		assertEquals(nhsNumberUpperBoundary, patient.getNhsNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIncorrectNHSNumber() {
		Patient patient = new Patient();
		patient.setNhsNumber(nhsNumberIncorrect);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIncorrectNHSNumberLower() {
		Patient patient = new Patient();
		patient.setNhsNumber(nhsNumberLowerBoundaryIncorrect);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIncorrectNHSNumberUpper() {
		Patient patient = new Patient();
		patient.setNhsNumber(nhsNumberUpperBoundaryIncorrect);
	}

	@Test
	public void testSetAllergiesCorrect() {
		Patient patient = new Patient();
		patient.setAllergies(allergiesCorrect);
		assertEquals(allergiesCorrect, patient.getAllergies());
	}

	@Test
	public void testSetKnownConditionsCorrect() {
		Patient patient = new Patient();
		patient.setKnownConditions(knownConditionsCorrect);
		assertEquals(knownConditionsCorrect, patient.getKnownConditions());
	}

	@Test
	public void testSetBloodGroupAPositive() {
		Patient patient = new Patient();
		patient.setBloodGroup(aPositive);
		assertEquals(aPositive, patient.getBloodGroup());
	}

	@Test
	public void testSetBloodGroupANegative() {
		Patient patient = new Patient();
		patient.setBloodGroup(aNegative);
		assertEquals(aNegative, patient.getBloodGroup());
	}

	@Test
	public void testSetBloodGroupABPositive() {
		Patient patient = new Patient();
		patient.setBloodGroup(abPositive);
		assertEquals(abPositive, patient.getBloodGroup());
	}

	@Test
	public void testSetBloodGroupABNegative() {
		Patient patient = new Patient();
		patient.setBloodGroup(abNegative);
		assertEquals(abNegative, patient.getBloodGroup());
	}

	@Test
	public void testSetBloodGroupOPositive() {
		Patient patient = new Patient();
		patient.setBloodGroup(oPositive);
		assertEquals(oPositive, patient.getBloodGroup());
	}

	@Test
	public void testSetBloodGroupONegative() {
		Patient patient = new Patient();
		patient.setBloodGroup(oNegative);
		assertEquals(oNegative, patient.getBloodGroup());
	}

	@Test
	public void testSetBloodGroupBPositive() {
		Patient patient = new Patient();
		patient.setBloodGroup(bPositive);
		assertEquals(bPositive, patient.getBloodGroup());
	}

	@Test
	public void testSetBloodGroupBNegative() {
		Patient patient = new Patient();
		patient.setBloodGroup(bNegative);
		assertEquals(bNegative, patient.getBloodGroup());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIncorrectBloodType() {
		Patient patient = new Patient();
		patient.setBloodGroup(incorrectBloodType);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetIncorrectBloodTypeLong() {
		Patient patient = new Patient();
		patient.setBloodGroup(incorrectBloodTypeLong);
	}

	@Test
	public void testSetSexFemale() {
		Patient patient = new Patient();
		patient.setSex(female);
		assertEquals(female, patient.getSex());
	}

	@Test
	public void testSetSexMale() {
		Patient patient = new Patient();
		patient.setSex(male);
		assertEquals(male, patient.getSex());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSexIncorrect() {
		Patient patient = new Patient();
		patient.setSex(incorrectSexChar);
	}

	@Test
	public void testSetNextOfKin() {
		Patient patient = new Patient();
		patient.setNextOfKin(correctNextOfKin);
		assertEquals(correctNextOfKin, patient.getNextOfKin());
	}

	@Test
	public void testSetGpNameCorrect() throws Exception {
		Patient patient = new Patient();
		patient.setGpName(correctGPName);
		assertEquals(correctGPName, patient.getGpName());
	}

	@Test(expected = Exception.class)
	public void testSetGpNameIncorrect() throws Exception {
		Patient patient = new Patient();
		patient.setGpName(incorrectGPName);
	}

	@Test
	public void testSetGpCodeCorrect() throws Exception {
		Patient patient = new Patient();
		patient.setGpCode(correctGPCode);
		assertEquals(correctGPCode, patient.getGpCode());
	}

	@Test(expected = Exception.class)
	public void testSetGpCodeIncorrect() throws Exception {
		Patient patient = new Patient();
		patient.setGpCode(incorrectGPCode);
	}

	@Test(expected = Exception.class)
	public void testSetGpCodeEmpty() throws Exception {
		Patient patient = new Patient();
		patient.setGpCode(incorrectEmptyGPCode);
	}

	@Test(expected = Exception.class)
	public void testSetGpCodeTooLong() throws Exception {
		Patient patient = new Patient();
		patient.setGpCode(incorrectGPCodeLong);
	}

}
