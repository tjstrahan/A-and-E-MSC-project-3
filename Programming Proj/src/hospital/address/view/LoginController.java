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
import hospital.address.jdbc.ReceptionistAccess;
import hospital.address.model.HospitalManager;
import hospital.address.model.Staff;

public class LoginController {

	/**
	 * LinkedList of doctors and nurses working in medical team on site
	 */
	

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

			}
		});

		password.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				pass = password.getText();

			}
		});

		loginButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				ReceptionistAccess rac = new ReceptionistAccess();

				try {
					rac.getReceptionists();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
				System.out.println(rac.Receptionist);
				System.out.println("hi");

				int number = Integer.parseInt(user);
				for (int loop = 0; loop < staffs.size(); loop++) {
					if (number == (staffs.get(loop).getLoginID())
							&& pass.equals(staffs.get(loop).getPassword())) {
						if (number >= 100000 && number < 200000) {
							staffType = "Doctor";
							mainApp.showDoc();
						} else if (number >= 300000 && number < 400000) {
							staffType = "Hospital Manager";
						} else if (number >= 700000 && number < 800000) {
							staffType = "Nurse";
							mainApp.showNurse();
						}
					}
				}

				for (int loop = 0; loop < rac.Receptionist.size(); loop++) {
					if (number == (rac.Receptionist.get(loop).getLoginID())
							&& pass.equals(rac.Receptionist.get(loop).getPassword())) {
						
					if (number >= 500000 && number < 600000) {
						staffType = "Receptionist";
						mainApp.showRecep();
					}

				}
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
