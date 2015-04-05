package hospital;

import java.util.LinkedList;

public class Receptionist {

	static LinkedList<Patient> patientsFromDB = new LinkedList<Patient>();
	static Patient patient;

	// a load of patients created and added to a linkedlist mimicking having
	// come from db - a triage number of zero has been assigned as this is how
	// the patient would come from the database
	public static void addPatientsFromDB() {

		patientsFromDB.add(new Patient("Kieron", "Allsop", 42, 0));
		patientsFromDB.add(new Patient("Jeff", "Hanneman", 24, 0));
		patientsFromDB.add(new Patient("Tom", "Araya", 34, 0));
		patientsFromDB.add(new Patient("Kerry", "King", 21, 0));
		patientsFromDB.add(new Patient("Lars", "Ulrich", 53, 0));
		patientsFromDB.add(new Patient("James", "Hetfield", 47, 0));
		patientsFromDB.add(new Patient("Cliff", "Burton", 23, 0));
		patientsFromDB.add(new Patient("Kirk", "Hammett", 56, 0));
		patientsFromDB.add(new Patient("Jason", "Newsted", 32, 0));
		patientsFromDB.add(new Patient("Joey", "Belladonna", 31, 0));
		patientsFromDB.add(new Patient("Dave", "Lombardo", 21, 0));
		patientsFromDB.add(new Patient("Kurt", "Cobain", 24, 0));
		patientsFromDB.add(new Patient("Tony", "Romo", 32, 0));
		patientsFromDB.add(new Patient("Dez", "Bryant", 27, 0));
		patientsFromDB.add(new Patient("DeMarco", "Murray", 27, 0));
		patientsFromDB.add(new Patient("Jason", "Witten", 30, 0));
		patientsFromDB.add(new Patient("Zak", "Martin", 30, 0));
		patientsFromDB.add(new Patient("Jeremy", "Clarkson", 60, 0));
		patientsFromDB.add(new Patient("Darren", "McFadden", 30, 0));
		patientsFromDB.add(new Patient("Jerry", "Jones", 30, 0));
		patientsFromDB.add(new Patient("Tyler", "Cluttz", 30, 0));
		patientsFromDB.add(new Patient("Tom", "McDonnell", 24, 0));
		patientsFromDB.add(new Patient("Janine", "Kelly", 34, 0));
		patientsFromDB.add(new Patient("Emma", "Scroggie", 21, 0));
		patientsFromDB.add(new Patient("James", "Maguire", 53, 0));
		patientsFromDB.add(new Patient("Naveed", "Agahi", 47, 0));
		patientsFromDB.add(new Patient("Aidan", "McGowan", 23, 0));
		patientsFromDB.add(new Patient("Hannah", "McDaid", 56, 0));
		patientsFromDB.add(new Patient("Catherine", "Geddis", 32, 0));
		patientsFromDB.add(new Patient("James", "Strahan", 31, 0));
		patientsFromDB.add(new Patient("Ian", "Williams", 21, 0));
		patientsFromDB.add(new Patient("Peter", "Lyness", 24, 0));
		patientsFromDB.add(new Patient("Payton", "Manning", 32, 0));
		patientsFromDB.add(new Patient("Greg", "Hardy", 27, 0));
		patientsFromDB.add(new Patient("Josh", "Brent", 27, 0));
		patientsFromDB.add(new Patient("Frank", "Gore", 30, 0));
		patientsFromDB.add(new Patient("Robert", "GriffinIII", 30, 0));
		patientsFromDB.add(new Patient("Brain", "Broaddus", 60, 0));
		patientsFromDB.add(new Patient("Syndey", "Durso", 30, 0));
		patientsFromDB.add(new Patient("Kelsey", "Charles", 30, 0));
		patientsFromDB.add(new Patient("Mickey", "Spagnola", 30, 0));
		patientsFromDB.add(new Patient("Kieron", "Allsop", 42, 0));
		patientsFromDB.add(new Patient("Jeff", "Hanneman", 24, 0));
		patientsFromDB.add(new Patient("Tom", "Araya", 34, 0));
		patientsFromDB.add(new Patient("Kerry", "King", 21, 0));
		patientsFromDB.add(new Patient("Lars", "Ulrich", 53, 0));
		patientsFromDB.add(new Patient("James", "Hetfield", 47, 0));
		patientsFromDB.add(new Patient("Cliff", "Burton", 23, 0));
		patientsFromDB.add(new Patient("Kirk", "Hammett", 56, 0));
		patientsFromDB.add(new Patient("Jason", "Newsted", 32, 0));
		patientsFromDB.add(new Patient("Joey", "Belladonna", 31, 0));
		patientsFromDB.add(new Patient("Dave", "Lombardo", 21, 0));
		patientsFromDB.add(new Patient("Kurt", "Cobain", 24, 0));
		patientsFromDB.add(new Patient("Tony", "Romo", 32, 0));
		patientsFromDB.add(new Patient("Dez", "Bryant", 27, 0));
		patientsFromDB.add(new Patient("DeMarco", "Murray", 27, 0));
		patientsFromDB.add(new Patient("Jason", "Witten", 30, 0));
		patientsFromDB.add(new Patient("Zak", "Martin", 30, 0));
		patientsFromDB.add(new Patient("Jeremy", "Clarkson", 60, 0));
		patientsFromDB.add(new Patient("Darren", "McFadden", 30, 0));
		patientsFromDB.add(new Patient("Jerry", "Jones", 30, 0));
		patientsFromDB.add(new Patient("Tyler", "Cluttz", 30, 0));
		patientsFromDB.add(new Patient("Tom", "McDonnell", 24, 0));
		patientsFromDB.add(new Patient("Janine", "Kelly", 34, 0));
		patientsFromDB.add(new Patient("Emma", "Scroggie", 21, 0));
		patientsFromDB.add(new Patient("James", "Maguire", 53, 0));
		patientsFromDB.add(new Patient("Naveed", "Agahi", 47, 0));
		patientsFromDB.add(new Patient("Aidan", "McGowan", 23, 0));
		patientsFromDB.add(new Patient("Hannah", "McDaid", 56, 0));
		patientsFromDB.add(new Patient("Catherine", "Geddis", 32, 0));
		patientsFromDB.add(new Patient("James", "Strahan", 31, 0));
		patientsFromDB.add(new Patient("Ian", "Williams", 21, 0));
		patientsFromDB.add(new Patient("Peter", "Lyness", 24, 0));
		patientsFromDB.add(new Patient("Payton", "Manning", 32, 0));
		patientsFromDB.add(new Patient("Greg", "Hardy", 27, 0));
		patientsFromDB.add(new Patient("Josh", "Brent", 27, 0));
		patientsFromDB.add(new Patient("Frank", "Gore", 30, 0));
		patientsFromDB.add(new Patient("Robert", "GriffinIII", 30, 0));
		patientsFromDB.add(new Patient("Brain", "Broaddus", 60, 0));
		patientsFromDB.add(new Patient("Syndey", "Durso", 30, 0));
		patientsFromDB.add(new Patient("Kelsey", "Charles", 30, 0));
		patientsFromDB.add(new Patient("Mickey", "Spagnola", 30, 0));
	}
}
