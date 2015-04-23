/**
 * Program that reads the Hospital Managers GMAIL Email Inbox and outputs its contents.
 */
package hospital;

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

import jdbc.HospitalManagerAccess;

/**
 * @author James Maguire
 *
 */
public class HospitalManager {

	/**
	 * LinkedList of doctors and nurses working in medical team on site
	 */
	public static LinkedList<Staff> medicalTeam = new LinkedList<Staff>();

	/**
	 * LinkedList of doctors and nurses part of the current on call team
	 */
	public static LinkedList<Staff> onCallTeam = new LinkedList<Staff>();
	
	public static ArrayList<Long> onCallContactNumbers = new ArrayList<Long>(5);
	
	static HospitalManagerAccess hMa = new HospitalManagerAccess();
	static Scanner scanner = new Scanner(System.in);
	static int medicalTeamFour = 4;
	static int medicalTeamFive = 5;

	public void populateMedicalTeam() throws Exception {

		hMa.getMedicalTeamDocs();
		hMa.getMedicalTeamNurses();
		printMedicalTeam();

	}

	public void setOnCallTeam() throws Exception {

		int team = 0;
		System.out.println("Select medical team 1 or 2 for on call duties");
		team = scanner.nextInt();
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

	public static void main(String[] args) {

		String host = "pop.gmail.com";
		String mailStoreType = "pop3";
		String username = "tothehospitalmanager@gmail.com";
		String password = "javateers2015";

		fetch(host, mailStoreType, username, password);
	}

	public static void fetch(String pop3Host, String storeType, String user,
			String password) {
		try {
			// create properties field
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.pop3.host", pop3Host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3s");

			store.connect(pop3Host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				writePart(message);
				String line = reader.readLine();
				if ("YES".equals(line)) {
					message.writeTo(System.out);
				} else if ("QUIT".equals(line)) {
					break;
				}
			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that checks if content is of plain text - Else it says it's an
	 * unknown type.
	 * 
	 * @param p
	 * @throws Exception
	 */
	public static void writePart(Part p) throws Exception {
		if (p instanceof Message)
			// Call methos writeEnvelope
			writeEnvelope((Message) p);

		System.out.println("----------------------------");
		System.out.println("CONTENT-TYPE: " + p.getContentType());

		// check if the content is plain text
		if (p.isMimeType("text/plain")) {
			System.out.println("This is plain text");
			System.out.println("---------------------------");
			System.out.println((String) p.getContent());
		} else {
			System.out.println("This is an unknown type");
			System.out.println("---------------------------");
		}
	}

	/**
	 * Method that prints the FROM, TO AND SUBJECT of the email.
	 * 
	 * @param m
	 * @throws Exception
	 */
	public static void writeEnvelope(Message m) throws Exception {
		System.out.println("This is the message envelope");
		System.out.println("---------------------------");
		Address[] a;

		// FROM
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("FROM: " + a[j].toString());
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("TO: " + a[j].toString());
		}

		// SUBJECT
		if (m.getSubject() != null)
			System.out.println("SUBJECT: " + m.getSubject());
	}

} // Class close
