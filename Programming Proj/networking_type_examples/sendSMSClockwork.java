package networking;

import java.util.ArrayList;
import java.util.List;

// import ClockWork library from JAR file - website is http://www.clockworksms.com/
import com.clockworksms.*;

// send SMS method
public class sendSMSClockwork {

	// unique API_Key supplied when account verified - account gets credited
	// with 50p upon verification - basically this is 10 free texts - then
	// either pay 5p per text using Paypal or setup a new account and get a new
	// API_KEY, possibly the best route to go down as so long as we have 5 for
	// the demo that is all we need
	private static String API_KEY = "5a8e44a3b1b208755656af3088e1cb9381d90678";

	// from field
	static String FROM = "A&E";

	// message
	static String MESSAGE = "On-call team please report for duty";

	// method to show what credit we have left on the account being used
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

	// this is he bit that sends a single text
	public static void sendSingleSMS() {

		try {
			ClockWorkSmsService clockWorkSmsService = new ClockWorkSmsService(
					API_KEY);

			// number is in international format without the +
			SMS sms = new SMS("447845896938", MESSAGE, FROM);

			ClockworkSmsResult result = clockWorkSmsService.send(sms);

			if (result.isSuccess()) {
				System.out.println("Sent with ID: " + result.getId());
			} else {
				System.out.println("Error: " + result.getErrorMessage());
			}
		} catch (ClockworkException e) {
			e.printStackTrace();
		}

	}

	// sends multiple texts - we could populate numbers from another arraylist
	public static void sendMultipleSMS() {
		try {
			List<SMS> messages = new ArrayList<SMS>();
			messages.add(new SMS("447557648697", MESSAGE, FROM));
			messages.add(new SMS("447557648697", MESSAGE, FROM));

			ClockWorkSmsService clockWorkSmsService = new ClockWorkSmsService(
					API_KEY);
			List<ClockworkSmsResult> results = clockWorkSmsService
					.send(messages);
			
			for (ClockworkSmsResult result : results) {
				if (result.isSuccess()) {
					System.out.println("Sent with ID: " + result.getId());
				} else {
					System.out.println("Error: " + result.getErrorMessage());
				}
			}
		} catch (ClockworkException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		// proves it works - need new API key to send many more texts, mine almost done
		checkSMSCredit();

		
		// sendSingleSMS();
		// sendMultipleSMS();
	}

}
