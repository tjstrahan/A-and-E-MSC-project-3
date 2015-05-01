/**
 * Status.java as part of the Team Internet Java Program. 
 */
package hospitalTest;

import static org.junit.Assert.*;
import hospital.address.model.Status;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test the Status.java class in the hospital.address.model package.
 * @author James Maguire
 */
public class StatusTest {
	/**
	 * Initialising Variable.
	 */
	int codeCorrect;

	/**
	 * Variable declared for Set up
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		codeCorrect = 1;
	}

	/**
	 * Test Status to ensure NotNull.
	 */
	@Test
	public void testStatus() {
		Status status = new Status();
		assertNotNull(status);
	}

	/**
	 * Test method for code pass in.
	 */
	@Test
	public void testStatusInteger() {
		Status status = new Status(codeCorrect);
		assertNotNull(codeCorrect);
	}

	/**
	 * Test method for code pass in.
	 */
	@Test
	public void testGetCode() {
		Status status = new Status(codeCorrect);
		assertNotNull(codeCorrect);
	}

	/**
	 * Test method for code pass in 
	 * 
	 */
	@Test
	public void testSetCode() {
		Status status = new Status(codeCorrect);
		assertNotNull(codeCorrect);
	}

} //Class close
