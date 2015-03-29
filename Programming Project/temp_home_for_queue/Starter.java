package hospital;

public class Starter {
	
	public static void main(String[] args) {

		Receptionist.addPatientsFromDB();

		TriageNurse triNurse = new TriageNurse();
		Thread t1 = new Thread(triNurse);
		t1.start();
		
		QueueTimer.main(args);

	}

}
