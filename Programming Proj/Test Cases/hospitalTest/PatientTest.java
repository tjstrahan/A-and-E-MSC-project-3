package hospitalTest;

import static org.junit.Assert.*;
import hospital.address.model.Patient;

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
			incorrectGPCode, incorrectEmptyGPCode, incorrectGPCodeLong, female,
			male, incorrectSex, notes, invalidNotes, validTriageCategory, invalidTriageCategory;

	int nhsNumberLowerBoundary, nhsNumberUpperBoundary, nhsNumberCorrect,
			nhsNumberLowerBoundaryIncorrect, nhsNumberUpperBoundaryIncorrect,
			nhsNumberIncorrect, incorrectGPCodeNumber, triageNumberBoundary, triageNumberLowerBoundary,
			triageNumberUpperBoundary, triageNumberCorrect, triageNumberLowerBoundaryIncorrect, triageNumberUpperBoundaryIncorrect,
			triageNumberIncorrect;
			;
			
	boolean correctisPriorityPatient, incorrectEmptyisPriorityPatient, correctTreatedByOnCallTeam, incorrectTreatedByOnCallTeam,
	correctWaitingMoreThan30, incorrectWaitingMoreThan30, correctmadeNewNote, incorrectmadeNewNote, correctExtraTime, incorrectExtraTime;

	long contactNoCorrect;

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
		contactNoCorrect = 441234567899L;

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
		female = "F";
		male = "M";
		incorrectSex = "A";

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

		// Test data for notes

		notes = "";
		invalidNotes=null;
		
		//Test data for triage category
		
		validTriageCategory="";
		invalidTriageCategory=null;
		
		//test data for triage number
		triageNumberCorrect=3;
		triageNumberIncorrect=5;
		triageNumberLowerBoundary=1;
		triageNumberUpperBoundary=4;
		triageNumberLowerBoundaryIncorrect=0;
		triageNumberUpperBoundaryIncorrect=5;
		
		//test data for isPriortyPatient
		correctisPriorityPatient=true;
		incorrectEmptyisPriorityPatient=false;
		
		//test data for treated by on call team
		correctTreatedByOnCallTeam=true;
		incorrectTreatedByOnCallTeam=false;
		
		//test data for waiting more then 30
		correctWaitingMoreThan30=true;
		incorrectWaitingMoreThan30=false;
		
		//test data for made new note
		correctmadeNewNote=true;
		incorrectmadeNewNote=false;
		
		//test data for extra time
		correctExtraTime=true;
		incorrectExtraTime=false;
		

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
				contactNoCorrect, nhsNumberCorrect, allergiesCorrect,
				knownConditionsCorrect, aPositive, female, correctNextOfKin,
				correctGPName, correctGPCode, notes);
		
		
		assertNotNull(patient);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorArgumentsInCorrectNHSNumber()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNoCorrect, nhsNumberIncorrect, allergiesCorrect,
				knownConditionsCorrect, aPositive, female, correctNextOfKin,
				correctGPName, correctGPCode, notes);
		assertNotNull(patient);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testConstructorArgumentsInCorrectBloodGroup()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNoCorrect, nhsNumberCorrect, allergiesCorrect,
				knownConditionsCorrect, incorrectBloodType, female, correctNextOfKin,
				correctGPName, correctGPCode, notes );
		assertNotNull(patient);
	}
	
	public void testConstructorArgumentsInCorrectSex()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNoCorrect, nhsNumberCorrect, allergiesCorrect,
				knownConditionsCorrect, abNegative, incorrectSex, correctNextOfKin,
				correctGPName, correctGPCode, notes);
		assertNotNull(patient);
	}
	
	public void testConstructorArgumentsInCorrectGPName()
			throws IllegalArgumentException, Exception {
		Patient patient = new Patient(titleCorrect, firstNameCorrect,
				middleNameCorrect, lastNameCorrect, dateOfBirthCorrect,
				addressLineOneCorrect, addressLineTwoCorrect,
				addressLineThreeCorrect, cityCorrect, postcodeCorrect,
				contactNoCorrect, nhsNumberCorrect, allergiesCorrect,
				knownConditionsCorrect, abNegative, male, correctNextOfKin,
				incorrectGPName, correctGPCode, notes);
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
		patient.setSex(incorrectSex);
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
	@Test
	public void testGetSetTriageNumberCorrect() throws Exception {
		Patient patient = new Patient();
		patient.setTriageNumber(triageNumberCorrect);

}
	@Test(expected = Exception.class)
	public void testGetSetTriageNumberInCorrect() throws Exception {
		Patient patient = new Patient();
		patient.setTriageNumber(triageNumberIncorrect);
		
		
		
	}
	@Test
	public void testGetSetTriageNumberLowerBoundary() throws Exception {
		Patient patient = new Patient();
		patient.setTriageNumber(triageNumberLowerBoundary);
		
}
	@Test
	public void testGetSetTriageNumberUpperBoundary() throws Exception {
		Patient patient = new Patient();
		patient.setTriageNumber(triageNumberUpperBoundary);
	}
	@Test(expected = Exception.class)
	public void testGetSetTriageNumberLowerBoundaryInCorrect() throws Exception {
		Patient patient = new Patient();
		patient.setTriageNumber(triageNumberLowerBoundaryIncorrect);
	}
	@Test(expected = Exception.class)
	public void testGetSetTriageNumberUpperBoundaryInCorrect() throws Exception {
		Patient patient = new Patient();
		patient.setTriageNumber(triageNumberUpperBoundaryIncorrect);
	}
	@Test
	public void testGetSetNotes(){
		Patient patient = new Patient();
		patient.setNotes(notes);
		assertEquals(notes, patient.getNotes());
	}

	@Test(expected = Exception.class)
	public void testGetSetinvalidNotes(){
		Patient patient = new Patient();
		patient.setNotes(invalidNotes);
		assertEquals(invalidNotes, patient.getNotes());
}
	@Test
	public void testGetSetisPriorityPatient(){
		Patient patient = new Patient();
		patient.setPriorityPatient(correctisPriorityPatient);
		assertEquals(correctisPriorityPatient, patient.isPriorityPatient());
		
	}
	@Test(expected = Exception.class)
	public void testGetSetisincorrectEmptyPriorityPatient(){
		Patient patient = new Patient();
		patient.setPriorityPatient(incorrectEmptyisPriorityPatient);
		assertEquals (incorrectEmptyisPriorityPatient, patient.isPriorityPatient());
	
}
	@Test
	public void testGetSetAdmissionNumber(){
		Patient patient = new Patient();
		patient.setAdmissionNumber(100);
	}
	@Test
	public void testGetSetTimeWait(){
		Patient patient = new Patient();
		patient.setStartTimeWait(100);
	
}
	@Test
	public void testGetSetEndTimeWait(){
		Patient patient = new Patient();
		patient.setEndTimeWait(100);
	}
	@Test
	public void testGetSetTreatmentRoom(){
		Patient patient = new Patient();
		patient.setTreatmentRoom(100);
	}
	@Test
	public void testGetSetstartTimeTreat(){
		Patient patient = new Patient();
		patient.setStartTimeTreat(100);
	}
	@Test
	public void testGetSetendTimeTreat(){
		Patient patient = new Patient();
		patient.setEndTimeTreat(100);
	}
	@Test
	public void testGetSetcorrecttreatedByOnCallTeam(){
		Patient patient = new Patient();
		patient.setTreatedByOnCallTeam(correctTreatedByOnCallTeam);
		assertEquals(correctTreatedByOnCallTeam, patient.isTreatedByOnCallTeam());
	}
	@Test(expected = Exception.class)
	public void testGetSetincorrecttreatedByOnCallTeam(){
		Patient patient = new Patient();
		patient.setTreatedByOnCallTeam(incorrectTreatedByOnCallTeam);
		assertEquals(incorrectTreatedByOnCallTeam, patient.isTreatedByOnCallTeam());
	}
	@Test
	public void testGetSetcorrectwaitingmorethan30(){
		Patient patient = new Patient();
		patient.setWaitingMoreThan30(correctWaitingMoreThan30);
		assertEquals(correctTreatedByOnCallTeam, patient.isWaitingMoreThan30());
	}
	@Test(expected = Exception.class)
	public void testGetSetincorrectwaitingmorethan30(){
		Patient patient = new Patient();
		patient.setTreatedByOnCallTeam(incorrectWaitingMoreThan30);
		assertEquals(incorrectWaitingMoreThan30, patient.isWaitingMoreThan30());
	}
	@Test
	public void testGetSettreatmentRoom(){
		Patient patient = new Patient();
		patient.setTreatmentRoom(100);
	}
	@Test
	public void testGetSetcorrectmadeNewNote(){
		Patient patient = new Patient();
		patient.setMadeNewNote(correctmadeNewNote);
		assertEquals(correctmadeNewNote, patient.isMadeNewNote());
	}
	@Test(expected = Exception.class)
	public void testGetSetincorrectmadeNewNote(){
		Patient patient = new Patient();
		patient.setMadeNewNote(incorrectmadeNewNote);
		assertEquals(incorrectmadeNewNote, patient.isMadeNewNote());
	
}
	@Test
	public void testGetSetcorrectExtraTime(){
		Patient patient = new Patient();
		patient.setExtraTime(correctExtraTime);
		assertEquals(correctExtraTime, patient.isExtraTime());
	}
	@Test(expected = Exception.class)
	public void testGetSetincorrectExtraTime(){
		Patient patient = new Patient();
		patient.setExtraTime(incorrectExtraTime);
		assertEquals(incorrectExtraTime, patient.isExtraTime());
	}
	@Test
	public void testGetSettriageCategory(){
		Patient patient = new Patient();
		patient.setTriageCategory(validTriageCategory);
		assertEquals(validTriageCategory, patient.getTriageCategory());
		
	}
	@Test (expected = Exception.class)
	public void testGetSetinvalidtriageCategory(){
		Patient patient = new Patient();
		patient.setTriageCategory(invalidTriageCategory);
		assertEquals(invalidTriageCategory, patient.getTriageCategory());
	}
		
}
	
	