package hospital;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class TheQueue {

	// system runs 20 times faster than in real life for demo purpose, set this
	// value to 1 to run in real time
	// to be able to demo some of the stuff
	public static final int TIME_FACTOR = 60;

	// this ensure that patients moved from Treatment Room to the Waiting List
	// remain in the correct order
	static int newPatNum = 0;

	static boolean onCallTeamContacted = false;
	static boolean onCallInSitu = false;
	static boolean firstRun = true;

	static Date date;

	public static LinkedList<Patient> WaitingList = new LinkedList<Patient>();
	public static ArrayList<Patient> TreatmentRoom = new ArrayList<Patient>(5);
	public static LinkedList<Patient> OnCallTeam = new LinkedList<Patient>();
	public static LinkedList<Patient> Treated = new LinkedList<Patient>();
	public static LinkedList<Patient> SentElsewhere = new LinkedList<Patient>();

	public void addToQueue(Patient patient) {

		// on first run assign null values to the five elements of the treatment
		// room arraylist, this is to ensure that the list stays of a constant
		// size and that each treatment room has a place holder, ie when a
		// patient is not in a treatment room the room is reassigned as null so
		// that the list does not resize and the patients 'move' room. ensure it
		// only happens once by setting a boolean value
		if (firstRun) {
			TreatmentRoom.add(0, null);
			TreatmentRoom.add(1, null);
			TreatmentRoom.add(2, null);
			TreatmentRoom.add(3, null);
			TreatmentRoom.add(4, null);
			firstRun = false;
		}

		// does the on call team need contacted
		// needToContactOnCallQuery();

		// if waiting list size is less than 10
		if (WaitingList.size() < 10) {

			// add patient initially to the waiting list
			WaitingList.add(patient);

			// immediately start to try and fill the treatment rooms
			fillTreatmentRoom();

			// sort waiting list so that the highest priority patient is at the
			// top of the list
			sortListByTriage(WaitingList);

			// if the waiting list has patients on it
			if (!WaitingList.isEmpty()) {

				// check if the patient at the top of the waiting list is an
				// emergency patient
				if (WaitingList.get(0).getTriageNumber() == 1) {

					// check to see if an emergency patient, triage category 1,
					// is on waiting list and swap them with the lowest priority
					// patient in the treatment room list
					moveEmergencyPatients();
				}

				// ensure that patient moved from treatment room is put to the
				// top of the waiting list regardless of triage category
				keepMovedPatientsAtTop();

				// take patients of the same triage number and sort by the order
				// they joined the waiting list
				keepPatientsOfSameTriageInOrder();

			}

		} // new

		if (WaitingList.size() == 10) {

			if (!onCallTeamContacted) {

				onCallTeamContacted = true;

				// sendSMSToOnCallTeam(){}

				// for demo purposes
				System.out.println("******** On-call team called - SMS");
			}

			// new start

			if (patient.getTriageNumber() == 1) {

				System.out.println("herhrerherhehrehrherher");

				/*
				 * 
				 * &&
				 * TreatmentRoom.get(bubbleSortTreatmentRoom()).getTriageNumber
				 * () != 1) } // message that a patient has been asked to leave
				 * System.out.println("******** New Emergency patient arrives");
				 * System.out .println(
				 * "******** Not all treatment room patients are Emergency patients"
				 * ); System.out.println("******** Patient " +
				 * WaitingList.getLast().getAdmissionNumber() +
				 * " has been asked to go elsewhere."); // remove last patient
				 * from waitinglist WaitingList.removeLast();
				 * 
				 * // add the new patient to the waiting list
				 * WaitingList.add(patient);
				 * 
				 * // sort waiting list to move the new emergency patient to the
				 * top sortListByTriage(WaitingList);
				 * 
				 * // move the new emergency patient into the treatment room and
				 * lowest // priority patient out of the treatment room
				 * moveEmergencyPatients();
				 * 
				 * // ensure that patient moved from treatment room is put to
				 * the top // of the waiting list regardless of triage category
				 * keepMovedPatientsAtTop();
				 * 
				 * // take patients of the same triage number and sort by the
				 * order // they joined the waiting list
				 * keepPatientsOfSameTriageInOrder(); // alert to hospital
				 * manager System.out .println(
				 * "******** Alert hospital manager - SMS and Email ?? doesnt say\n"
				 * );
				 */
			}
			if (onCallInSitu) {

				System.out.println("go elsewhere");

			} else {

				// set boolean to true
				// onCallTeamContacted = true;

				OnCallTeam.add(0, patient);
				// System.out.println(OnCallTeam);
				onCallInSitu = true;
				// System.out.println(onCallInSitu);
				OnCallTeam.get(0).isTreatedByOnCallTeam();
				OnCallTeam.get(0).setStartTimeTreat1(new Date().getTime());
				// System.out.println(OnCallTeam.get(0).getStartTimeTreat1());
			}

		}
		// } new
	}

	public synchronized void fillTreatmentRoom() {

		ArrayList<Patient> temp = new ArrayList<Patient>();

		if (TreatmentRoom.get(0) == null && !WaitingList.isEmpty()) {
			TreatmentRoom.remove(0);
			temp.add(WaitingList.get(0));
			if (!temp.get(0).isSecondTimeOnWaitingList()) {
				temp.get(0).setEndTimeWait1(new Date().getTime());
			} else {
				temp.get(0).setEndTimeWait2(new Date().getTime());
			}
			WaitingList.remove(0);
			TreatmentRoom.add(0, temp.get(0));
			TreatmentRoom.get(0).setTreatmentRoom(0);
			if (TreatmentRoom.get(0).isMovedFromTreatRoom()) {
				TreatmentRoom.get(0).setStartTimeTreat2(new Date().getTime());
			} else {
				TreatmentRoom.get(0).setStartTimeTreat1(new Date().getTime());
			}
			temp.clear();

		} else if (TreatmentRoom.get(1) == null && !WaitingList.isEmpty()) {
			TreatmentRoom.remove(1);
			temp.add(WaitingList.get(0));
			if (!temp.get(0).isSecondTimeOnWaitingList()) {
				temp.get(0).setEndTimeWait1(new Date().getTime());
			} else {
				temp.get(0).setEndTimeWait2(new Date().getTime());
			}
			WaitingList.remove(0);
			TreatmentRoom.add(1, temp.get(0));
			TreatmentRoom.get(1).setTreatmentRoom(1);
			if (TreatmentRoom.get(1).isMovedFromTreatRoom()) {
				TreatmentRoom.get(1).setStartTimeTreat2(new Date().getTime());
			} else {
				TreatmentRoom.get(1).setStartTimeTreat1(new Date().getTime());
			}
			temp.clear();

		} else if (TreatmentRoom.get(2) == null && !WaitingList.isEmpty()) {
			TreatmentRoom.remove(2);
			temp.add(WaitingList.get(0));
			if (!temp.get(0).isSecondTimeOnWaitingList()) {
				temp.get(0).setEndTimeWait1(new Date().getTime());
			} else {
				temp.get(0).setEndTimeWait2(new Date().getTime());
			}
			WaitingList.remove(0);
			TreatmentRoom.add(2, temp.get(0));
			TreatmentRoom.get(2).setTreatmentRoom(2);
			if (TreatmentRoom.get(2).isMovedFromTreatRoom()) {
				TreatmentRoom.get(2).setStartTimeTreat2(new Date().getTime());
			} else {
				TreatmentRoom.get(2).setStartTimeTreat1(new Date().getTime());
			}
			temp.clear();

		} else if (TreatmentRoom.get(3) == null && !WaitingList.isEmpty()) {
			TreatmentRoom.remove(3);
			temp.add(WaitingList.get(0));
			if (!temp.get(0).isSecondTimeOnWaitingList()) {
				temp.get(0).setEndTimeWait1(new Date().getTime());
			} else {
				temp.get(0).setEndTimeWait2(new Date().getTime());
			}
			WaitingList.remove(0);
			TreatmentRoom.add(3, temp.get(0));
			TreatmentRoom.get(3).setTreatmentRoom(3);
			if (TreatmentRoom.get(3).isMovedFromTreatRoom()) {
				TreatmentRoom.get(3).setStartTimeTreat2(new Date().getTime());
			} else {
				TreatmentRoom.get(3).setStartTimeTreat1(new Date().getTime());
			}
			temp.clear();

		} else if (TreatmentRoom.get(4) == null && !WaitingList.isEmpty()) {
			TreatmentRoom.remove(4);
			temp.add(WaitingList.get(0));
			if (!temp.get(0).isSecondTimeOnWaitingList()) {
				temp.get(0).setEndTimeWait1(new Date().getTime());
			} else {
				temp.get(0).setEndTimeWait2(new Date().getTime());
			}
			WaitingList.remove(0);
			TreatmentRoom.add(4, temp.get(0));
			TreatmentRoom.get(4).setTreatmentRoom(4);
			if (TreatmentRoom.get(4).isMovedFromTreatRoom()) {
				TreatmentRoom.get(4).setStartTimeTreat2(new Date().getTime());
			} else {
				TreatmentRoom.get(4).setStartTimeTreat1(new Date().getTime());
			}
			temp.clear();

		}
	}

	// method to contact on call team according to business rules, that is when
	// waiting list is full
	public synchronized void needToContactOnCallQuery() {

		// on call team to be put on standby if waiting list is full
		if (WaitingList.size() == 10) {

			// set boolean to true
			onCallTeamContacted = true;

			// for demo purposes
			System.out.println("******** On-call team called - SMS");

		}

		/*
		 * 
		 * // waiting list is no longer full set onCallOnStandby boolean back to
		 * // false and tell team to stand down if (WaitingList.size() < 9 &&
		 * onCallTeamContacted) {
		 * 
		 * // set boolean to false onCallTeamContacted = false;
		 * 
		 * // for demo purposes
		 * System.out.println("******** On-call team stand down - SMS");
		 * 
		 * }
		 */
	}

	// list sorting method by triage category
	public synchronized void sortListByTriage(LinkedList<Patient> patient) {
		Collections.sort(patient, new sortByTriage());
	}

	// list sorting method by triage category
	public synchronized void sortListByTriage(ArrayList<Patient> patient) {
		Collections.sort(patient, new sortByTriage());
	}

	// Triage category sorting algorithm
	public class sortByTriage implements Comparator<Patient> {
		@Override
		public int compare(Patient p1, Patient p2) {
			if (p1.getTriageNumber() > p2.getTriageNumber()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	// list sorting method by patient number - ie order patients based on when
	// they joined the queue
	public void sortListByAdmissionNumber(ArrayList<Patient> patient) {
		Collections.sort(patient, new sortByAdmissionNumber());
	}

	// list sorting method by patient number - ie order patients based on when
	// they joined the queue
	public void sortListByAdmissionNumber(LinkedList<Patient> patient) {
		Collections.sort(patient, new sortByAdmissionNumber());
	}

	// Patient number sorting algorithm
	public class sortByAdmissionNumber implements Comparator<Patient> {
		@Override
		public int compare(Patient p1, Patient p2) {
			if (p1.getAdmissionNumber() > p2.getAdmissionNumber()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	public int highestTriageTreatmentList(ArrayList<Patient> patient) {

		int highestTriageValue = TreatmentRoom.get(0).getTriageNumber();
		int index = 0;

		for (int loop = 1; loop < TreatmentRoom.size(); loop++) {
			if (TreatmentRoom.get(loop).getTriageNumber() > highestTriageValue) {
				highestTriageValue = TreatmentRoom.get(loop).getTriageNumber();
				index = loop;
			}
		}
		return index;
	}

	/*
	 * public int lastIn(int index) {
	 * 
	 * long highestStartTime = TreatmentRoom.get(index).getStartTime();
	 * 
	 * int accurateIndex = 0;
	 * 
	 * for (int loop = 0; loop < TreatmentRoom.size(); loop++) { if
	 * ((TreatmentRoom.get(index).getTriageNumber() == TreatmentRoom
	 * .get(loop).getTriageNumber())) { if
	 * (TreatmentRoom.get(loop).getStartTime() < highestStartTime) {
	 * accurateIndex = loop; } } } return accurateIndex; }
	 */

	public synchronized int bubbleSortTreatmentRoom() {

		// I was gonna try a TreeMap, but you can only sort properly by key,
		// rather than value, then thought about maybe adding in treatment room
		// to the patient - i have changed the treatment room java threads to do
		// that the moment the patient enters the room.

		// now i think we can probably use the bubblesort method from the other
		// class - i was a gonna create a temp arraylist, do the bubblesort
		// based on, triage, then time and the last element of the arraylist
		// will be the one we need to remove, so get treatment room number from
		// that and thats the one we need to swap with waiting list.

		// temp arraylist - copy of treatment room
		ArrayList<Patient> tempList = new ArrayList<Patient>();
		tempList.add(0, TreatmentRoom.get(0));
		tempList.add(1, TreatmentRoom.get(1));
		tempList.add(2, TreatmentRoom.get(2));
		tempList.add(3, TreatmentRoom.get(3));
		tempList.add(4, TreatmentRoom.get(4));

		// initialise variable
		int indexOfElement = 0;

		// sort templist by triagenumber - lowest first
		sortListByTriage(tempList);

		// if triage number of elements 0 and 1 are the same
		if (tempList.get(0).getTriageNumber() == tempList.get(1)
				.getTriageNumber()) {

			// and if element 0 was added after element 1 to the queue, swap
			// them in the list
			if (tempList.get(0).getAdmissionNumber() > tempList.get(1)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 0, 1);
			}
		}

		// if triage number of elements 1 and 2 are the same
		if (tempList.get(1).getTriageNumber() == tempList.get(2)
				.getTriageNumber()) {

			// and if element 1 was added after element 2 to the queue, swap
			// them in the list
			if (tempList.get(1).getAdmissionNumber() > tempList.get(2)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 1, 2);
			}
		}

		// if triage number of elements 2 and 3 are the same
		if (tempList.get(2).getTriageNumber() == tempList.get(3)
				.getTriageNumber()) {

			// and if element 2 was added after element 3 to the queue, swap
			// them in the list
			if (tempList.get(2).getAdmissionNumber() > tempList.get(3)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 2, 3);
			}
		}

		// if triage number of elements 3 and 4 are the same
		if (tempList.get(3).getTriageNumber() == tempList.get(4)
				.getTriageNumber()) {

			// and if element 3 was added after element 4 to the queue, swap
			// them in the list
			if (tempList.get(3).getAdmissionNumber() > tempList.get(4)
					.getAdmissionNumber()) {
				Collections.swap(tempList, 3, 4);
			}
		}

		// was trying something - awaiting reply from Aidan
		/*
		 * if (tempList.get(4).isMovedFromTreatRoom()) { indexOfElement =
		 * tempList.get(3).getTreatmentRoom();
		 * 
		 * if (tempList.get(3).isMovedFromTreatRoom()) { indexOfElement =
		 * tempList.get(2).getTreatmentRoom();
		 * 
		 * if (tempList.get(2).isMovedFromTreatRoom()) { indexOfElement =
		 * tempList.get(1).getTreatmentRoom();
		 * 
		 * if (tempList.get(1).isMovedFromTreatRoom()) { indexOfElement =
		 * tempList.get(0).getTreatmentRoom(); } } } }
		 */

		// get treatment room number of last element
		indexOfElement = tempList.get(4).getTreatmentRoom();

		// clear temp arraylist
		tempList.clear();

		System.out.println(indexOfElement);

		// return index of element to be swapped out of treatment room
		return indexOfElement;
	}

	public synchronized void moveEmergencyPatients() {

		// temp arraylist
		ArrayList<Patient> tempList = new ArrayList<Patient>();

		int index;

		index = bubbleSortTreatmentRoom();

		// do this while last patient in treatment room list when it was sorted
		// in the bubblesort method does not have a
		// triage category of 1 (ie is not an emergency) and the first patient
		// on the waiting list does have a triage category of 1 (is an emergency
		// patient)
		if ((TreatmentRoom.get(index).getTriageNumber() != 1)
				&& (WaitingList.get(0).getTriageNumber() == 1)) {

			System.out.println("ine here");

			// if (TreatmentRoom.get(index).getTriageNumber() != 1
			// && !TreatmentRoom.get(index).getTopOfList()) {

			// set the topOfList boolean to true to enable them to be put to the
			// top
			// of the waiting list
			TreatmentRoom.get(index).setMovedFromTreatRoom(true);
			TreatmentRoom.get(index).setEndTimeTreat1(new Date().getTime());

			if (TreatmentRoom.get(index).getEndTimeWait1() != 0) {
				TreatmentRoom.get(index).setSecondTimeOnWaitingList(true);
			}
			
			// copy patient in treatment room to be moved list to temp arraylist
			tempList.add(TreatmentRoom.get(index));

			TreatmentRoom.remove(index);

			newPatNum--;

			tempList.get(0).setAdmissionNumber(newPatNum);
			/*
			 * if (tempList.get(0).isSecondTimeOnWaitingList()) {
			 * tempList.get(0).setStartTimeWait2(new Date().getTime()); } else {
			 * tempList.get(0).setStartTimeWait1(new Date().getTime()); }
			 */
			if (WaitingList.get(0).isMovedFromTreatRoom()) {
				WaitingList.get(0).setStartTimeTreat2(new Date().getTime());
			} else {
				WaitingList.get(0).setStartTimeTreat1(new Date().getTime());
			}

			if (tempList.get(0).isSecondTimeOnWaitingList()) {
				tempList.get(0).setStartTimeWait2(new Date().getTime());
			} else {
				tempList.get(0).setStartTimeWait1(new Date().getTime());
			}
			/*
			 * if (WaitingList.get(0).isSecondTimeOnWaitingList()) {
			 * WaitingList.get(0).setEndTimeWait2(new Date().getTime()); } else
			 * { WaitingList.get(0).setEndTimeWait1(new Date().getTime()); }
			 */

			switch (index) {
			case 0:
				TreatmentRoom.add(index, WaitingList.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				WaitingList.remove(0);
				break;
			case 1:
				TreatmentRoom.add(index, WaitingList.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				WaitingList.remove(0);
				break;
			case 2:
				TreatmentRoom.add(index, WaitingList.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				WaitingList.remove(0);
				break;
			case 3:
				TreatmentRoom.add(index, WaitingList.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				WaitingList.remove(0);
				break;
			case 4:
				TreatmentRoom.add(index, WaitingList.get(0));
				TreatmentRoom.get(index).setTreatmentRoom(index);
				WaitingList.remove(0);
				break;
			}

			System.out.println("Swapping patients");

			// reset treatment room of patient removed from threatment room to
			// the default value
			tempList.get(0).setTreatmentRoom(-1);

			/*
			 * if (tempList.get(0).isSecondTimeOnWaitingList()) {
			 * tempList.get(0).setStartTimeWait2(new Date().getTime()); } else {
			 * tempList.get(0).setStartTimeWait1(new Date().getTime()); }
			 */
			// copy patient from temp arraylist to first element of waiting list
			WaitingList.addFirst(tempList.get(0));

			// clear temp arraylist
			tempList.clear();

		} else if ((TreatmentRoom.get(index).getTriageNumber() == 1)
				&& (WaitingList.getFirst().getTriageNumber() == 1)
				&& !onCallInSitu) {

			// set boolean to true
			onCallTeamContacted = true;

			// sendSMSToOnCallTeam(){}

			// for demo purposes
			System.out.println("******** On-call team called - SMS");

			// set boolean to true
			onCallInSitu = true;

			tempList.add(WaitingList.get(0));

			WaitingList.remove(0);

			// add patient to on call team list - patient removed from this list
			// after 15 minutes - there can only be one patient on this list
			OnCallTeam.add(0, tempList.get(0));
			OnCallTeam.get(0).isTreatedByOnCallTeam();
			OnCallTeam.get(0).setStartTimeTreat1(new Date().getTime());
		}

	}

	// moving patients who have been in the treatment room and moved out to the
	// top of the waiting list
	public synchronized void keepMovedPatientsAtTop() {

		// temp arraylist
		ArrayList<Patient> temp = new ArrayList<Patient>();

		// for loop
		for (int loop = 0; loop < WaitingList.size(); loop++) {

			// if true
			if (WaitingList.get(loop).isMovedFromTreatRoom()) {

				// copy patient to temp arraylist
				temp.add(WaitingList.get(loop));

				// remove patient from current position in waiting list
				WaitingList.remove(loop);

				// add patient from temp arraylist to start of waiting list
				WaitingList.addFirst(temp.get(0));

			}

			// empty temp array list
			temp.clear();

		}
	}

	// method to sort patients in waiting list that have not been moved from the
	// treatment room by patient number (in otherwords to keep them in the order
	// they should be in relative to when they joined the queue)
	public synchronized void keepPatientsOfSameTriageInOrder() {

		if (WaitingList.size() < 10) {
			LinkedList<Patient> copyWaitingList = new LinkedList<Patient>(
					WaitingList);

			// instantiate the iterator
			Iterator<Patient> Patient = copyWaitingList.iterator();

			// temporary arraylists to hold the different types of patient when
			// they
			// are copied from the Waiting List Linked List
			ArrayList<Patient> moved = new ArrayList<Patient>();
			ArrayList<Patient> triage1 = new ArrayList<Patient>();
			ArrayList<Patient> triage2 = new ArrayList<Patient>();
			ArrayList<Patient> triage3 = new ArrayList<Patient>();
			ArrayList<Patient> triage4 = new ArrayList<Patient>();
			LinkedList<Patient> orderedWaitingList = new LinkedList<Patient>();

			// into to allow for iteration of the Waiting List
			int element = 0;

			// while there are patients in the Waiting List do...
			while (Patient.hasNext()) {

				// if patient has been previously moved from the treatment room,
				// remember that these patients have already been sorted
				if (Patient.next().isMovedFromTreatRoom()) {

					// add patient to to 'moved' arraylist
					moved.add(copyWaitingList.get(element));

					// advance element number
					element++;

					// for those patients not moved from the treatment room
				} else {

					// switch on triage priority
					switch (copyWaitingList.get(element).getTriageNumber()) {

					// if triage priority equals 1
					case 1:

						// add patient to 'triage1' arraylist
						triage1.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;

					// if triage priority equals 2
					case 2:

						// add patient to 'triage2' arraylist
						triage2.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;

					// if triage priority equals 3
					case 3:

						// add patient to 'triage 3' arraylist
						triage3.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;

					// if triage priority equals 4
					case 4:

						// add patient to 'triage 4' arraylist
						triage4.add(copyWaitingList.get(element));

						// advance element number
						element++;

						// allow iterator to advance to next patient if any
						break;
					}
				}
			}

			// sort each of these array lists based on patient number, which is
			// being used in this file in place of time on queue - patient
			// number
			// gives incorrect results for patients that have been moved because
			// in
			// the actual system when they are moved from the treatment room to
			// the
			// waiting list their treatment room thread will cease and a new
			// waiting
			// list thread will start, therefore i have assigned new patient
			// numbers
			// to mimic this
			sortListByAdmissionNumber(moved);
			Collections.reverse(moved);
			sortListByAdmissionNumber(triage1);
			sortListByAdmissionNumber(triage2);
			sortListByAdmissionNumber(triage3);
			sortListByAdmissionNumber(triage4);

			// delete all elements from WaitingList
			// .clear();

			// addAll elements from each sorted arraylist to the end of the
			// WaitingList linked list
			orderedWaitingList.addAll(moved);
			orderedWaitingList.addAll(triage1);
			orderedWaitingList.addAll(triage2);
			orderedWaitingList.addAll(triage3);
			orderedWaitingList.addAll(triage4);

			copyWaitingList.clear();
			WaitingList.clear();
			WaitingList.addAll(orderedWaitingList);
		}

	}

}