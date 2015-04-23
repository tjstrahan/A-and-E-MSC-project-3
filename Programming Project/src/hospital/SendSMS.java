package hospital;

import java.util.ArrayList;
import java.util.List;

// import ClockWork library from JAR file - website is http://www.clockworksms.com/
import com.clockworksms.*;

// send SMS method
public class SendSMS implements Runnable {

	/**
	 * Unique API key supplied by Clockwork
	 */
	private static String API_KEY = "5a8e44a3b1b208755656af3088e1cb9381d90678";

	// from field
	static String FROM = "A&E";

	// message to on call team members
	static String ONCALL_MESSAGE = "On Call Team please report for duty";

	// message to hospital manager
	static String HOSPMANAGER_MESSAGE = "Waiting time guidelines exceeded!";

	/**
	 * Used for selection of method to run in thread
	 */
	private int select;

	/**
	 * Constructor with args for the Run method, allows user to select method to
	 * run from the class
	 * 
	 * @param select
	 */
	public SendSMS(int select) {
		this.select = select;
	}

	/**
	 * The Run method
	 */
	@Override
	public void run() {
		switch (select) {
		case 1:
			sendSMSHospManager();
			break;
		case 2:
			sendSMSOnCallTeam();
			break;
		case 3:
			checkSMSCredit();
			break;
		}

	}

	/**
	 * Method to show credit left in the account
	 */
	public static void checkSMSCredit() {
		try {
			ClockWorkSmsService clockWorkSmsService = new ClockWorkSmsService(
					API_KEY);
			Long credit = clockWorkSmsService.checkCredit();
			System.out.println("Credits = " + credit);
		} catch (ClockworkException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to send a single SMS message to the Hospital Manager
	 */
	public static void sendSMSHospManager() {

		try {
			ClockWorkSmsService clockWorkSmsService = new ClockWorkSmsService(
					API_KEY);

			// number is in international format without the +
			SMS sms = new SMS("447557648697", HOSPMANAGER_MESSAGE, FROM);

			ClockworkSmsResult result = clockWorkSmsService.send(sms);

			if (result.isSuccess()) {
				// System.out.println("Sent with ID: " + result.getId());
			} else {
				// System.out.println("Error: " + result.getErrorMessage());
			}
		} catch (ClockworkException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to send multiple SMS message at once to the members of the On Call
	 * team
	 */
	public static void sendSMSOnCallTeam() {
		try {
			List<SMS> messages = new ArrayList<SMS>();
			messages.add(new SMS(""
					+ HospitalManager.onCallContactNumbers.get(0) + "",
					ONCALL_MESSAGE, FROM));
			// messages.add(new
			// SMS(""+HospitalManager.onCallContactNumbers.get(0)+"",
			// ONCALL_MESSAGE, FROM));
			// messages.add(new
			// SMS(""+HospitalManager.onCallContactNumbers.get(0)+"",
			// ONCALL_MESSAGE, FROM));
			// messages.add(new
			// SMS(""+HospitalManager.onCallContactNumbers.get(0)+"",
			// ONCALL_MESSAGE, FROM));
			// messages.add(new
			// SMS(""+HospitalManager.onCallContactNumbers.get(0)+"",
			// ONCALL_MESSAGE, FROM));

			ClockWorkSmsService clockWorkSmsService = new ClockWorkSmsService(
					API_KEY);
			List<ClockworkSmsResult> results = clockWorkSmsService
					.send(messages);
			
			for (ClockworkSmsResult result : results) {
				if (result.isSuccess()) {
					TheQueue.onCallTeamContacted = true;
				} else {
					// System.out.println("Error: " + result.getErrorMessage());
				}
			}
		} catch (ClockworkException e) {
			e.printStackTrace();
		}
	}
}
