/**
 * Program that reads the Hospital Managers GMAIL Email Inbox and outputs its contents.
 */
package hospital.address.model;

import hospital.address.jdbc.HospitalManagerAccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

/**
 * Class to simulate what a hospital manager can do in the system
 *
 */
public class HospitalManager {

	/**
	 * LinkedList of doctors and nurses working in medical team on site
	 */
	public static LinkedList<Staff> medicalTeam = new LinkedList<Staff>();

	
	public static LinkedList<Staff> receptionistList = new LinkedList<Staff>();
	
	
	public static LinkedList<Staff> hospitalManagerList = new LinkedList<Staff>();

	/**
	 * LinkedList of doctors and nurses part of the current on call team
	 */
	public static LinkedList<Staff> onCallTeam = new LinkedList<Staff>();
	
	public static ArrayList<Long> onCallContactNumbers = new ArrayList<Long>(5);
	
	static HospitalManagerAccess hMa = new HospitalManagerAccess();
	static Scanner scanner = new Scanner(System.in);
	static int medicalTeamFour = 4;
	static int medicalTeamFive = 5;

	public void populateHospitalManagerList() throws Exception {
		hMa.getHospitalManager();
		printHospitalManager();
	}
	
	public void populateReceptionistList() throws Exception {
		
		hMa.getReceptionStaff();
		//printReceptionTeam();
	}
	
	public void populateMedicalTeam() throws Exception {

		hMa.getMedicalTeamDocs();
		hMa.getMedicalTeamNurses();
		//printMedicalTeam();

	}

	public void setOnCallTeam(int team) throws Exception {

		//int team = 0;
		//System.out.println("Select medical team 1 or 2 for on call duties");
		//team = scanner.nextInt();
		if (team == 1) {
			hMa.getOnCallTeamDocs(team);
			hMa.getOnCallTeamNurses(team);
			hMa.setOnCall(true, medicalTeamFour);
			hMa.setOnCall(false, medicalTeamFive);
			onCallContactNumbers.clear();
			getOnCallTeamMobileNumbers();
			printOnCallTeam();
		} else if (team == 2) {
			hMa.getOnCallTeamDocs(team);
			hMa.getOnCallTeamNurses(team);
			hMa.setOnCall(true, medicalTeamFive);
			hMa.setOnCall(false, medicalTeamFour);
			onCallContactNumbers.clear();
			getOnCallTeamMobileNumbers();
			printOnCallTeam();
		} else {
			throw new IllegalArgumentException("Invalid Team");
		}
	}
	
	public void getOnCallTeamMobileNumbers() {	
		
		for (int loop = 0 ; loop < onCallTeam.size() ; loop ++ ) {
			onCallContactNumbers.add(onCallTeam.get(loop).getContactNumber());
		}
		
	}
	
	public void setOnCallDB(int medicalTeam) throws SQLException {
		hMa.setOnCall(true, medicalTeam);
	}
	
	public void unSetOnCallDB(int medicalTeam) throws SQLException {
		hMa.setOnCall(false, medicalTeam);
	}
	
	public void printOnCallTeam() {
		Iterator<Staff> Staff = onCallTeam.iterator();
		System.out.println("Current On Call Team");
		System.out.println("====================");
		while (Staff.hasNext()) {
			System.out.println(Staff.next());
		}
	}
	
	public void printMedicalTeam() {
		Iterator<Staff> Staff = medicalTeam.iterator();
		System.out.println("Current Medical Team");
		System.out.println("====================");
		while (Staff.hasNext()) {
			System.out.println(Staff.next());
		}
	}

	public void printReceptionTeam() {
		Iterator<Staff> Receptionist = receptionistList.iterator();
		System.out.println("Current Reception Team");
		System.out.println("======================");
		while (Receptionist.hasNext()) {
			System.out.println(Receptionist.next());
		}
	}
	
	public void printHospitalManager() {
		Iterator<Staff> HospitalManager = hospitalManagerList.iterator();
		System.out.println("Current Hospital Manager");
		System.out.println("========================");
		while (HospitalManager.hasNext()) {
			System.out.println(HospitalManager.next());
		}
	}
} // Class close
