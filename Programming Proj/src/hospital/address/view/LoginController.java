package hospital.address.view;

import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import hospital.address.MainApp;
import hospital.address.model.HospitalManager;
import hospital.address.model.Staff;

public class LoginController {

	private String staffType;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label message1;

	@FXML
	private Label RequiredPass;

	@FXML
	private Label RequireUser;
	
	@FXML
	private Label wrong;

	@FXML
	private Button loginButton;

	public static String user;
	public static String pass;

	private MainApp mainApp;

	public LoginController() {

	}

	@FXML
	private void initialize() {
		
		
		wrong.setText(" ");
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

			@SuppressWarnings("unused")
			@Override
			public void handle(ActionEvent arg0) {
				
				if (username.getText().isEmpty()){
					RequireUser.setText("*Required");
				}
				
				if (password.getText().isEmpty()){
					RequiredPass.setText("*Required");
				}

				Staff staff = new Staff();
			
				staffType = null;

				int number = Integer.parseInt(user);

				for (int loop = 0; loop < MainApp.staffList.size(); loop++) {
					if (number == (MainApp.staffList.get(loop).getLoginID())
							&& pass.equals(MainApp.staffList.get(loop).getPassword())) {
						if (number >= 100000 && number < 200000) {
							//System.out.println("doctor");
							staffType = "Doctor";
							mainApp.showDoc();
						} else if (number >= 300000 && number < 400000) {
							staffType = "Hospital Manager";
						} else if (number >= 500000 && number < 600000) {
							//System.out.println("receptionist");
							staffType = "Receptionist";
							mainApp.showRecep();						
						} else if (number >= 700000 && number < 800000) {
							//System.out.println("nurse");
							staffType = "Nurse";
							mainApp.showNurse();
						}
					}else {
						wrong.setText("Username/Password incorrect");
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

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	// public static void LoginProcess (String username, String password){

	// }

}
