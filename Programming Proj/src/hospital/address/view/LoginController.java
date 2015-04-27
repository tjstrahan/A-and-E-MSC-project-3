package hospital.address.view;

import java.util.LinkedList;

import javax.swing.event.EventListenerList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import hospital.address.MainApp;
import hospital.address.jdbc.HospitalManagerAccess;
import hospital.address.model.HospitalManager;
import hospital.address.model.Staff;

public class LoginController {

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label message1;

	@FXML
	private Label message2;

	@FXML
	private Label message;

	@FXML
	private Button loginButton;

	public static String user;
	public static String pass;

	private MainApp mainApp;

	public LoginController() {

	}

	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		username.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				user = username.getText();
				message1.setText(user);

			}
		});

		password.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				pass = password.getText();
				message2.setText(pass);

			}
		});

		loginButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				HospitalManager hispo = new HospitalManager();
				
				try {
					hispo.populateMedicalTeam();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Staff staff = new Staff();
				String staffType;

				LinkedList<Staff> staffs = new LinkedList<Staff>();

				staffs.addAll(HospitalManager.medicalTeam);
				System.out.println(staffs);
				
				int number = Integer.parseInt(user);
				for (int loop = 0; loop < staffs.size(); loop++) {
					if (number == (staffs.get(loop).getLoginID())
							&& pass.equals(
									staffs.get(loop).getPassword())) {
						if (number >= 100000 && number < 200000){
						staffType = "Doctor";
						System.out.println("Hi everybody");
						mainApp.showDoc();
						} else if (number >= 300000
								&& number < 400000) {
							staffType = "Hospital Manager";
						} else if (number >= 500000
								&& number < 600000) {
							staffType = "Receptionist";
						} else if (number >= 700000
								&& number < 800000) {
							staffType = "Nurse";
							mainApp.showNurse();
						}

					}
				
							if (username.getText().equalsIgnoreCase("tom")
						&& password.getText().equalsIgnoreCase("password")) {
					try {
						mainApp.showRecep();
					} catch (Exception eve) {
						eve.printStackTrace();
					}
				}
				// else LoginProcess(user, pass);
			}
			}
		});

		}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	// public static void LoginProcess (String username, String password){

	// }

}
