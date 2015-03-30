package hospital;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class QueueTimer {

	static ArrayList<Patient> treatmentCopy = new ArrayList<Patient>(5);
	static LinkedList<Patient> waitingCopy = new LinkedList<Patient>();
	static LinkedList<Patient> onCallCopy = new LinkedList<Patient>();
	static LinkedList<Patient> treatedCopy = new LinkedList<Patient>();

	static final int ON_CALL_TREATMENT_TIME = 900000 / TheQueue.TIME_FACTOR;

	static final int TREATMENT_ROOM_TIME = 600000 / TheQueue.TIME_FACTOR;

	static final int numberOfMilliSeconds = 60000 / TheQueue.TIME_FACTOR;

	static TheQueue theQueue = new TheQueue();

	public static void main(String[] args) {

		final ScheduledExecutorService service1 = Executors
				.newSingleThreadScheduledExecutor();
		service1.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				System.out.println(new Date() + "\n");

				treatmentCopy.addAll(TheQueue.TreatmentRoom);
				waitingCopy.addAll(TheQueue.WaitingList);
				onCallCopy.addAll(TheQueue.OnCallTeam);
				treatedCopy.addAll(TheQueue.Treated);

				printAll();

				treatmentCopy.clear();
				waitingCopy.clear();
				onCallCopy.clear();
				treatedCopy.clear();

			}
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		final ScheduledExecutorService service2 = Executors
				.newSingleThreadScheduledExecutor();
		service2.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				long currentTime = new Date().getTime();

				if (TheQueue.onCallInSitu) {

					endOnCallTreatment(currentTime);
				}

			}
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		final ScheduledExecutorService service3 = Executors
				.newSingleThreadScheduledExecutor();
		service3.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				long currentTime = new Date().getTime();
				
				if (TheQueue.TreatmentRoom.get(0) != null) {
					endTreatmentRoomTreatment1(currentTime);
				}
				
			}
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);
		
		final ScheduledExecutorService service4 = Executors
				.newSingleThreadScheduledExecutor();
		service4.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				long currentTime = new Date().getTime();

				if (TheQueue.TreatmentRoom.get(1) != null) {
					endTreatmentRoomTreatment2(currentTime);
				}
				
			}
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);

		final ScheduledExecutorService service5 = Executors
				.newSingleThreadScheduledExecutor();
		service5.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				long currentTime = new Date().getTime();

				if (TheQueue.TreatmentRoom.get(2) != null) {
					endTreatmentRoomTreatment3(currentTime);
				}
				
			}
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);
		
		final ScheduledExecutorService service6 = Executors
				.newSingleThreadScheduledExecutor();
		service6.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				long currentTime = new Date().getTime();

				if (TheQueue.TreatmentRoom.get(3) != null) {
					endTreatmentRoomTreatment4(currentTime);
				}
				
			}
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);
		
		final ScheduledExecutorService service7 = Executors
				.newSingleThreadScheduledExecutor();
		service7.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {

				long currentTime = new Date().getTime();

				if (TheQueue.TreatmentRoom.get(4) != null) {
					endTreatmentRoomTreatment5(currentTime);
				}
				
			}
		}, 0, numberOfMilliSeconds, TimeUnit.MILLISECONDS);
	}

	public static void endTreatmentRoomTreatment1(long currentTime) {

		long treatmentTime1 = 0;
		long treatmentTime2 = 0;

		if (!TheQueue.TreatmentRoom.get(0).isMovedFromTreatRoom()) {

			long startTime1 = TheQueue.TreatmentRoom.get(0)
					.getStartTimeTreat1();
			treatmentTime1 = currentTime - startTime1;
			if (treatmentTime1 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(0).setEndTimeTreat1(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(0));
				System.out.println("Treatment Room 1 ready for next patient");
				TheQueue.TreatmentRoom.remove(0);
				TheQueue.TreatmentRoom.add(0, null);
				treatmentTime1 = 0;
				theQueue.fillTreatmentRoom();
			}

		} else {

			long startTime2 = TheQueue.TreatmentRoom.get(0)
					.getStartTimeTreat2();
			treatmentTime2 = currentTime - startTime2;

			if (treatmentTime2 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(0).setEndTimeTreat2(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(0));
				System.out.println("Treatment Room 1 ready for next patient");
				TheQueue.TreatmentRoom.remove(0);
				TheQueue.TreatmentRoom.add(0, null);
				treatmentTime2 = 0;
				theQueue.fillTreatmentRoom();
			}
		}
	}
	
	public static void endTreatmentRoomTreatment2(long currentTime) {

		long treatmentTime1 = 0;
		long treatmentTime2 = 0;

		if (!TheQueue.TreatmentRoom.get(1).isMovedFromTreatRoom()) {

			long startTime1 = TheQueue.TreatmentRoom.get(1)
					.getStartTimeTreat1();
			treatmentTime1 = currentTime - startTime1;

			if (treatmentTime1 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(1).setEndTimeTreat1(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(1));
				System.out.println("Treatment Room 2 ready for next patient");
				TheQueue.TreatmentRoom.remove(1);
				TheQueue.TreatmentRoom.add(1, null);
				treatmentTime1 = 0;
				theQueue.fillTreatmentRoom();
			}

		} else {

			long startTime2 = TheQueue.TreatmentRoom.get(1)
					.getStartTimeTreat2();
			treatmentTime2 = currentTime - startTime2;

			if (treatmentTime2 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(1).setEndTimeTreat2(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(1));
				System.out.println("Treatment Room 2 ready for next patient");
				TheQueue.TreatmentRoom.remove(1);
				TheQueue.TreatmentRoom.add(1, null);
				treatmentTime2 = 0;
				theQueue.fillTreatmentRoom();
			}
		}
	}
	
	public static void endTreatmentRoomTreatment3(long currentTime) {

		long treatmentTime1 = 0;
		long treatmentTime2 = 0;

		if (!TheQueue.TreatmentRoom.get(2).isMovedFromTreatRoom()) {

			long startTime1 = TheQueue.TreatmentRoom.get(2)
					.getStartTimeTreat1();
			treatmentTime1 = currentTime - startTime1;

			if (treatmentTime1 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(2).setEndTimeTreat1(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(2));
				System.out.println("Treatment Room 3 ready for next patient");
				TheQueue.TreatmentRoom.remove(2);
				TheQueue.TreatmentRoom.add(2, null);
				treatmentTime1 = 0;
				theQueue.fillTreatmentRoom();
			}

		} else {

			long startTime2 = TheQueue.TreatmentRoom.get(2)
					.getStartTimeTreat2();
			treatmentTime2 = currentTime - startTime2;

			if (treatmentTime2 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(2).setEndTimeTreat2(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(2));
				System.out.println("Treatment Room 3 ready for next patient");
				TheQueue.TreatmentRoom.remove(2);
				TheQueue.TreatmentRoom.add(2, null);
				treatmentTime2 = 0;
				theQueue.fillTreatmentRoom();
			}
		}
	}
	
	public static void endTreatmentRoomTreatment4(long currentTime) {

		long treatmentTime1 = 0;
		long treatmentTime2 = 0;

		if (!TheQueue.TreatmentRoom.get(3).isMovedFromTreatRoom()) {

			long startTime1 = TheQueue.TreatmentRoom.get(3)
					.getStartTimeTreat1();
			treatmentTime1 = currentTime - startTime1;

			if (treatmentTime1 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(3).setEndTimeTreat1(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(3));
				System.out.println("Treatment Room 4 ready for next patient");
				TheQueue.TreatmentRoom.remove(3);
				TheQueue.TreatmentRoom.add(3, null);
				treatmentTime1 = 0;
				theQueue.fillTreatmentRoom();
			}

		} else {

			long startTime2 = TheQueue.TreatmentRoom.get(3)
					.getStartTimeTreat2();
			treatmentTime2 = currentTime - startTime2;

			if (treatmentTime2 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(3).setEndTimeTreat2(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(3));
				System.out.println("Treatment Room 4 ready for next patient");
				TheQueue.TreatmentRoom.remove(3);
				TheQueue.TreatmentRoom.add(3, null);
				treatmentTime2 = 0;
				theQueue.fillTreatmentRoom();
			}
		}
	}

	public static void endTreatmentRoomTreatment5(long currentTime) {

		long treatmentTime1 = 0;
		long treatmentTime2 = 0;

		if (!TheQueue.TreatmentRoom.get(4).isMovedFromTreatRoom()) {

			long startTime1 = TheQueue.TreatmentRoom.get(4)
					.getStartTimeTreat1();
			treatmentTime1 = currentTime - startTime1;

			if (treatmentTime1 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(4).setEndTimeTreat1(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(4));
				System.out.println("Treatment Room 5 ready for next patient");
				TheQueue.TreatmentRoom.remove(4);
				TheQueue.TreatmentRoom.add(4, null);
				treatmentTime1 = 0;
				theQueue.fillTreatmentRoom();
			}

		} else {

			long startTime2 = TheQueue.TreatmentRoom.get(4)
					.getStartTimeTreat2();
			treatmentTime2 = currentTime - startTime2;

			if (treatmentTime2 > TREATMENT_ROOM_TIME) {

				TheQueue.TreatmentRoom.get(4).setEndTimeTreat2(currentTime);
				TheQueue.Treated.add(TheQueue.TreatmentRoom.get(4));
				System.out.println("Treatment Room 5 ready for next patient");
				TheQueue.TreatmentRoom.remove(4);
				TheQueue.TreatmentRoom.add(4, null);
				treatmentTime2 = 0;
				theQueue.fillTreatmentRoom();
			}
		}
	}

	
	public static void endOnCallTreatment(long currentTime) {

		long startTime = TheQueue.OnCallTeam.get(0).getStartTimeTreat1();
		long treatmentTime = currentTime - startTime;
		if (treatmentTime > ON_CALL_TREATMENT_TIME) {
			System.out.println("On Call Team finished treating patient.");
			TheQueue.OnCallTeam.get(0).setEndTimeTreat1(currentTime);
			TheQueue.Treated.add(TheQueue.OnCallTeam.get(0));
			TheQueue.OnCallTeam.clear();
			TheQueue.onCallInSitu = false;
		}
	}

	public static void printAll() {

		System.out.println("Treatment Room");
		System.out.println("==============");
		printTreatment();
		System.out.println("Waiting List");
		System.out.println("============");
		printWaiting();
		System.out.println("On Call Team Treating");
		System.out.println("=====================");
		printOnCall();
		System.out.println("Treated Patients");
		System.out.println("================");
		printTreated();
		System.out
				.println("-----------------------------------------------------------------");
	}

	public static void printTreatment() {
		Iterator<Patient> Patient = treatmentCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	public static void printWaiting() {
		Iterator<Patient> Patient = waitingCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	public static void printOnCall() {
		Iterator<Patient> Patient = onCallCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

	public static void printTreated() {
		Iterator<Patient> Patient = treatedCopy.iterator();
		while (Patient.hasNext()) {
			System.out.println(Patient.next());
		}
	}

}