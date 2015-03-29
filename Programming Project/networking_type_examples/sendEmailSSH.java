package networking;

// imports including those from mail.JAR a new library thats needs referenced
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// method using SSH to send the email
public class sendEmailSSH {


	
	protected static String GMAIL_USERNAME = "AnE.Dept.Hosp@gmail.com";
	protected static String GMAIL_PASSWORD = "JavaTeers2015";

	
	public static void main(String[] args) {

		// instantiate properties class
		Properties props = new Properties();

		// setting SMTP server - specific to Gmail
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// gmail authentication - using account I just made for testing purposes
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(GMAIL_USERNAME,
								GMAIL_PASSWORD);
					}
				});

		try {

			// email message parameters
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(GMAIL_USERNAME));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("kallsop01@qub.ac.uk"));
			message.setSubject("Email from Eclipse");
			message.setText("Hi,"
					+ "\n\n Sent from Java program in Eclipse. Via GMail.... \n\n\n Kieron");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}