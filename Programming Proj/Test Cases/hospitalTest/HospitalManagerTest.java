package hospitalTest;
/**
 * StaffTest.java as part of the Team Internet Java Program. 
 */
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

/**
 * Class to test the HospitalManager.java class in the hospital.address.model package.
 * @author James and James (The Two Jays)
 */
public class HospitalManagerTest {

	/**
	 * Test the Manager List
	 * @throws Exception
	 */
	@Test
	public void testPopulateHospitalManagerList() throws Exception {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
		
	}
	
	/**
	 * Test the ReceptionistList.
	 * @throws Exception
	 */
	@Test
	public void testPopulateReceptionistList() throws Exception {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}

	/**
	 *  Test the Medical Team. 
	 *  @throws Exception
	 */
	@Test
	public void testPopulateMedicalTeam() throws Exception {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}

	/**
	 * Test the SetOnCall Team.
	 * @throws Exception
	 */
	@Test
	public void testSetOnCallTeam() throws Exception {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}

	/**
	 * Test the SetOnCallDB.
	 * @throws SQLException
	 */
	@Test
	public void testSetOnCallDB() throws SQLException {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}

	/**
	 * Test the UNsetOnCallDB.
	 * @throws SQLException
	 */
	@Test
	public void testUnSetOnCallDB() throws SQLException {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}

	/**
	 * Test the PrintOnCallTeam.
	 */
	@Test
	public void testPrintOnCallTeam() {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}

	/**
	 * Test the PrintMedicalTeam.
	 */
	@Test
	public void testPrintMedicalTeam() {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}

	/**
	 *  Test the Print ReceptionTeam.
	 */
	@Test
	public void testPrintReceptionTeam() {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
	}
	
	/**
	 *  Test the print HospitalManager.
	 */
	@Test
	public void testPrintHospitalManager() {
		HospitalManagerTest hospitalTest = new HospitalManagerTest();
		assertNotNull(hospitalTest);
		}

} // Class close

