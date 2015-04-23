/**
 * Send email from a Gmail account.
 * NOTE: You must have switched on "Allow lesser applications to access account" on your Gmail.
 * NOTE: You must have the javamail jar file added as an external extension. 
 */
package hospital;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Class for Sending an email to the hospital manager using GMAIL
 * 
 * @author James Maguire
 */
public class Email implements Runnable {
	/**
	 * User_Name for gmail.com. NOTE: Use just information before @gmail.com as
	 * the username.
	 */
	private static String USER_NAME = "tothehospitalmanager";
	/**
	 * Password for your gmail account connected to username
	 */
	private static String PASSWORD = "javateers2015";
	/**
	 * Who you are sending the mail to.
	 */
	private static String RECIPIENT = "kallsop01@gmail.com";

	private int select;

	public Email(int select) {
		this.select = select;
	}

	@Override
	public void run() {
		switch (select) {
		case 1:
			emailWaitingExceeded();
			break;
		case 2:
			emailPatientTurnedAway();
			break;
		}

	}
	
	
	public static void emailWaitingExceeded() {
		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT };
		String subject = "To Hospital Manager";
		String body = "Here is your report. 2 patients have been waiting for 30 minutes.";

		sendFromGMail(from, pass, to, subject, body);
	}

	public static void emailPatientTurnedAway() {
		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT };
		String subject = "To Hospital Manager";
		String body = "Here is your report. A patient has been turned away from A and E.";

		sendFromGMail(from, pass, to, subject, body);
	}

	/**
	 * Method that sends an email from a gmail account
	 * 
	 * @param from
	 * @param pass
	 * @param to
	 * @param subject
	 * @param body
	 */
	private static void sendFromGMail(String from, String pass, String[] to,
			String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}
}