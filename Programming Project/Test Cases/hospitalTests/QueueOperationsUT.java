package hospitalTests;

import static org.junit.Assert.*;
import hospital.Patient;
import hospital.TheQueue;
import hospital.QueueOperations;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class QueueOperationsUT {

	int triageEmergency, triageInvalidLow, triageInvalidHigh, TRoom1, TRoom2,
			TRoom3, TRoom4, TRoom5, TRoomHigh, TRoomInvalid;
	long extraTime, noExtraTime;

	// Test data
	public static LinkedList<Patient> testPatients = new LinkedList<Patient>();
	public Patient patient;
	public TheQueue theQueue;

	@Before
	public void setUp() throws Exception {

		triageEmergency = 1;
		triageInvalidHigh = 5;
		triageInvalidLow = 0;
		
		TRoom1 = 1;
		TRoom2 = 2;
		TRoom3 = 3;
		TRoom4 = 4; 
		TRoom5 = 5;
		TRoomHigh = TheQueue.NUMBER_OF_TREATMENT_ROOMS + 1;
		TRoomInvalid = 0;

		extraTime = (5 * 10000) / TheQueue.TIME_FACTOR;
		noExtraTime = 0;

		testPatients.add(new Patient("Mr", "FirstName1", "MiddleName1",
				"LastName1", "01012001", "1 Test House", "Area", "Town",
				"City", "BT1 1BT", 111111111, "Allergy", "Condition", "O-",
				"M", "Mother", "Dr Doctor", "E0999"));
		testPatients.add(new Patient("Mr", "FirstName2", "MiddleName2",
				"LastName2", "01012001", "1 Test House", "Area", "Town",
				"City", "BT1 1BT", 222222222, "Allergy", "Condition", "O-",
				"M", "Mother", "Dr Doctor", "E0999"));
		testPatients.add(new Patient("Mr", "FirstName3", "MiddleName3",
				"LastName3", "01012001", "1 Test House", "Area", "Town",
				"City", "BT1 1BT", 333333333, "Allergy", "Condition", "O-",
				"M", "Mother", "Dr Doctor", "E0999"));
		testPatients.add(new Patient("Mr", "FirstName4", "MiddleName4",
				"LastName4", "01012001", "1 Test House", "Area", "Town",
				"City", "BT1 1BT", 444444444, "Allergy", "Condition", "O-",
				"M", "Mother", "Dr Doctor", "E0999"));
	}

	/**
	 * Test extra time method for Treatment Room 1 where extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeValidTreatRoom1() {
		QueueOperations.extraTreatmentTime(true, TRoom1);
		assertEquals(extraTime, QueueOperations.extraTime1);
	}

	/**
	 * Test extra time method for Treatment Room 1 where NO extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeNoExtraTimeTreatRoom1() {
		QueueOperations.treatmentRoom1extended = false;
		QueueOperations.extraTreatmentTime(false, TRoom1);
		assertEquals(noExtraTime, QueueOperations.extraTime1);
	}

	/**
	 * Test extra time method for Treatment Room 1 where extra time is set
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExtraTreatmentTimeTreatRoom1Prev() {
		QueueOperations.treatmentRoom1extended = true;
		QueueOperations.extraTreatmentTime(true, TRoom1);
	}
	
	/**
	 * Test extra time method for Treatment Room 2 where extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeValidTreatRoom2() {
		QueueOperations.extraTreatmentTime(true, TRoom2);
		assertEquals(extraTime, QueueOperations.extraTime2);
	}

	/**
	 * Test extra time method for Treatment Room 2 where NO extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeNoExtraTimeTreatRoom2() {
		QueueOperations.treatmentRoom2extended = false;
		QueueOperations.extraTreatmentTime(false, TRoom2);
		assertEquals(noExtraTime, QueueOperations.extraTime2);
	}

	/**
	 * Test extra time method for Treatment Room 2 where extra time is set
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExtraTreatmentTimeTreatRoom2Prev() {
		QueueOperations.treatmentRoom2extended = true;
		QueueOperations.extraTreatmentTime(true, TRoom2);
	}

	/**
	 * Test extra time method for Treatment Room 3 where extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeValidTreatRoom3() {
		QueueOperations.extraTreatmentTime(true, TRoom3);
		assertEquals(extraTime, QueueOperations.extraTime3);
	}

	/**
	 * Test extra time method for Treatment Room 3 where NO extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeNoExtraTimeTreatRoom3() {
		QueueOperations.treatmentRoom3extended = false;
		QueueOperations.extraTreatmentTime(false, TRoom3);
		assertEquals(noExtraTime, QueueOperations.extraTime3);
	}

	/**
	 * Test extra time method for Treatment Room 3 where extra time is set
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExtraTreatmentTimeTreatRoom3Prev() {
		QueueOperations.treatmentRoom3extended = true;
		QueueOperations.extraTreatmentTime(true, TRoom3);
	}
	
	/**
	 * Test extra time method for Treatment Room 4 where extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeValidTreatRoom4() {
		QueueOperations.extraTreatmentTime(true, TRoom4);
		assertEquals(extraTime, QueueOperations.extraTime4);
	}

	/**
	 * Test extra time method for Treatment Room 4 where NO extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeNoExtraTimeTreatRoom4() {
		QueueOperations.treatmentRoom4extended = false;
		QueueOperations.extraTreatmentTime(false, TRoom4);
		assertEquals(noExtraTime, QueueOperations.extraTime4);
	}

	/**
	 * Test extra time method for Treatment Room 4 where extra time is set
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExtraTreatmentTimeTreatRoom4Prev() {
		QueueOperations.treatmentRoom4extended = true;
		QueueOperations.extraTreatmentTime(true, TRoom4);
	}
	
	/**
	 * Test extra time method for Treatment Room 5 where extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeValidTreatRoom5() {
		QueueOperations.extraTreatmentTime(true, TRoom5);
		assertEquals(extraTime, QueueOperations.extraTime5);
	}

	/**
	 * Test extra time method for Treatment Room 5 where NO extra time is set
	 */
	@Test
	public void testExtraTreatmentTimeNoExtraTimeTreatRoom5() {
		QueueOperations.treatmentRoom5extended = false;
		QueueOperations.extraTreatmentTime(false, TRoom5);
		assertEquals(noExtraTime, QueueOperations.extraTime5);
	}

	/**
	 * Test extra time method for Treatment Room 5 where extra time is set
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExtraTreatmentTimeTreatRoom5Prev() {
		QueueOperations.treatmentRoom5extended = true;
		QueueOperations.extraTreatmentTime(true, TRoom5);
	}

	/**
	 * Test extra time method for Treatment Room where invalid treatment room
	 * number is passed
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExtraTreatmentTimeInvalidTreatRoomLow() {
		QueueOperations.extraTreatmentTime(false, TRoomInvalid);
	}
	
	/**
	 * Test extra time method for Treatment Room where invalid treatment room
	 * number is passed
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testExtraTreatmentTimeInvalidTreatRoomHigh() {
		QueueOperations.extraTreatmentTime(false, TRoomHigh);
	}

	/**
	 * Test to ensure that search for triage category is functional - searching
	 * for a triage number in the valid range
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchForTriageCategoryValid() throws Exception {

		// Temp LinkedList for expected patients
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>(
				testPatients);

		// Test patients set triage number
		actualPatients.get(0).setTriageNumber(1);
		actualPatients.get(1).setTriageNumber(1);
		actualPatients.get(2).setTriageNumber(3);
		actualPatients.get(3).setTriageNumber(4);

		// Two patients with a triage category of "Emergency", that is number 1
		expectedPatients.add(actualPatients.get(0));
		expectedPatients.add(actualPatients.get(1));

		// Assert that the expected is the same as the actual.
		assertEquals(expectedPatients, QueueOperations.searchForTriageCategory(
				actualPatients, triageEmergency));
	}

	/**
	 * Test to ensure that search for triage category is functional - searching
	 * for a triage number above the valid range
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSearchForTriageCategoryInvalidHigh() throws Exception {

		// Temp LinkedList
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>(
				testPatients);

		// Test patients set triage number
		actualPatients.get(0).setTriageNumber(1);
		actualPatients.get(1).setTriageNumber(1);
		actualPatients.get(2).setTriageNumber(3);
		actualPatients.get(3).setTriageNumber(4);

		// Two patients with a triage category of "Emergency", that is number 1
		expectedPatients.add(actualPatients.get(0));
		expectedPatients.add(actualPatients.get(1));

		// Assert that the expected is the same as the actual - putting
		// incorrect value for Triage number in throws exception
		assertEquals(expectedPatients, QueueOperations.searchForTriageCategory(
				actualPatients, triageInvalidHigh));
	}

	/**
	 * Test to ensure that search for triage category is functional - searching
	 * for a triage number below the valid range
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSearchForTriageCategoryInvalidLow() throws Exception {

		// Temp LinkedList
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>(
				testPatients);

		// Test patients set triage number
		actualPatients.get(0).setTriageNumber(1);
		actualPatients.get(1).setTriageNumber(1);
		actualPatients.get(2).setTriageNumber(3);
		actualPatients.get(3).setTriageNumber(4);

		// Two patients with a triage category of "Emergency", that is number 1
		expectedPatients.add(actualPatients.get(0));
		expectedPatients.add(actualPatients.get(1));

		// Assert that the expected is the same as the actual - putting
		// incorrect value for Triage number in throws exception
		assertEquals(expectedPatients, QueueOperations.searchForTriageCategory(
				actualPatients, triageInvalidLow));
	}

	/**
	 * Test to ensure that the search by patient name method is functional - in
	 * that if there is one patient matching the query that one patient is
	 * successfully returned
	 */
	@Test
	public void testSearchForPatientByNameValid() {

		// Temp LinkedList for expected patients in resultset
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>();

		// Add patients to list
		actualPatients.add(testPatients.get(0));
		actualPatients.add(testPatients.get(1));
		actualPatients.add(testPatients.get(2));
		actualPatients.add(testPatients.get(3));

		// Temp LinkedList to hold search results
		LinkedList<Patient> results = new LinkedList<Patient>();

		// Searching for FirstName1 LastName1, therefore
		expectedPatients.add(actualPatients.get(0));

		// Results
		results.addAll(QueueOperations.searchForPatientByName(actualPatients,
				"FirstName1", "LastName1"));

		// Check firstnames match
		assertEquals(expectedPatients.get(0).getFirstName(), results.get(0)
				.getFirstName());

		// Check lastnames match
		assertEquals(expectedPatients.get(0).getLastName(), results.get(0)
				.getLastName());

		// Check number of results match
		assertEquals(expectedPatients.size(), results.size());
	}

	/**
	 * Test to ensure that the search by patient name method is functional - to
	 * see if when more than one patient has the same details that both are
	 * returned in a search
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchForPatientByNameValidDuplicates() throws Exception {

		// Temp LinkedList for expected patients in resultset
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>();

		// Add patients to list, including 2 of the first patient
		actualPatients.add(testPatients.get(0));
		actualPatients.add(testPatients.get(0));
		actualPatients.add(testPatients.get(1));
		actualPatients.add(testPatients.get(2));
		actualPatients.add(testPatients.get(3));

		// Temp LinkedList to hold search results
		LinkedList<Patient> results = new LinkedList<Patient>();

		// Searching for FirstName1 LastName1, therefore
		expectedPatients.add(actualPatients.get(0));

		// Add second patient with same details
		expectedPatients.add(actualPatients.get(0));

		// Results
		results.addAll(QueueOperations.searchForPatientByName(actualPatients,
				"FirstName1", "LastName1"));

		// Check 1st firstnames match
		assertEquals(expectedPatients.get(0).getFirstName(), results.get(0)
				.getFirstName());

		// Check 2nd firstnames match
		assertEquals(expectedPatients.get(1).getFirstName(), results.get(1)
				.getFirstName());

		// Check 1st lastnames match
		assertEquals(expectedPatients.get(0).getLastName(), results.get(0)
				.getLastName());

		// Check 2nd lastnames match
		assertEquals(expectedPatients.get(1).getLastName(), results.get(1)
				.getLastName());

		// Check number of results match
		assertEquals(expectedPatients.size(), results.size());
	}

	/**
	 * Test to ensure that the search by patient name method is functional - to
	 * check what happens when there is no match - that is there is no match,
	 * therefore the results LinkedList will be empty which will throw the
	 * expected exception
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSearchForPatientByNameNoMatch() {

		// Temp LinkedList
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>();

		// Add patients to list
		actualPatients.add(testPatients.get(0));
		actualPatients.add(testPatients.get(1));
		actualPatients.add(testPatients.get(2));
		actualPatients.add(testPatients.get(3));

		// Temp LinkedList to hold search results
		LinkedList<Patient> results = new LinkedList<Patient>();

		// Searching for FirstName1 LastName1, therefore
		expectedPatients.add(actualPatients.get(0));

		// Results
		results.addAll(QueueOperations.searchForPatientByName(actualPatients,
				"FirstName1", "LastName2"));

		// Check firstnames match
		assertEquals(expectedPatients.get(0).getFirstName(), results.get(0)
				.getFirstName());

		// Check lastnames match
		assertEquals(expectedPatients.get(0).getLastName(), results.get(0)
				.getLastName());

		// Check number of results match
		assertEquals(expectedPatients.size(), results.size());

		// Check if there are no results
		assertNull(results);
	}

	/**
	 * Test to ensure that the search by NHSNumber method works using valid data
	 */
	@Test
	public void testSearchByNHSNumberValid() {

		// Temp LinkedList
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>();

		// Add patients to list
		actualPatients.add(testPatients.get(0));
		actualPatients.add(testPatients.get(1));
		actualPatients.add(testPatients.get(2));
		actualPatients.add(testPatients.get(3));

		// Add expected patient to expectedPatients list
		expectedPatients.add(testPatients.get(1));

		// Temp LinkedList to hold search results
		LinkedList<Patient> results = new LinkedList<Patient>();

		// Perform search and add to results LinkedList
		results.addAll(QueueOperations.searchByNHSNumber(actualPatients,
				222222222));

		// Check patients match
		assertEquals(expectedPatients, results);
	}

	/**
	 * Test to ensure that the search by NHSNumber method works using invalid
	 * data
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSearchByNHSNumberInvalid() {

		// Temp LinkedList
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>();

		// Add patients to list
		actualPatients.add(testPatients.get(0));
		actualPatients.add(testPatients.get(1));
		actualPatients.add(testPatients.get(2));
		actualPatients.add(testPatients.get(3));

		// Add expected patient to expectedPatients list
		expectedPatients.add(testPatients.get(1));

		// Temp LinkedList to hold search results
		LinkedList<Patient> results = new LinkedList<Patient>();

		// Perform search and add to results LinkedList - invalid NHS number - 8
		// digits being searched for
		results.addAll(QueueOperations.searchByNHSNumber(actualPatients,
				22222222));

		// Check patients match
		assertEquals(expectedPatients, results);
	}

	/**
	 * Test to ensure that the search by NHSNumber method works when no match is
	 * found in the data
	 */
	@Test
	public void testSearchByNHSNumberNoMatch() {

		// Temp LinkedList
		LinkedList<Patient> expectedPatients = new LinkedList<Patient>();

		// Temp LindedList for actual patients
		LinkedList<Patient> actualPatients = new LinkedList<Patient>();

		// Add patients to list
		actualPatients.add(testPatients.get(0));
		actualPatients.add(testPatients.get(1));
		actualPatients.add(testPatients.get(2));
		actualPatients.add(testPatients.get(3));

		// Add expected patient to expectedPatients list
		expectedPatients.add(testPatients.get(1));

		// Temp LinkedList to hold search results
		LinkedList<Patient> results = new LinkedList<Patient>();

		// Perform search and add to results LinkedList
		results.addAll(QueueOperations.searchByNHSNumber(actualPatients,
				555555555));

		// Check patients don't match
		assertNotEquals(expectedPatients, results);
	}
}
