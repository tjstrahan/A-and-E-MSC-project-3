package hospital.address.view;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;

import hospital.address.MainApp;
import hospital.address.jdbc.GeneralAccess;
import hospital.address.jdbc.ReceptionistAccess;
import hospital.address.model.Patient;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.concurrent.*;

public class ReceptonistSearchController {

	@FXML
	private Label FirstName;

	@FXML
	private Label LastName;

	@FXML
	private Label DOB;

	@FXML
	private Label incorrectFName;

	@FXML
	private Label incorrectLname;

	@FXML
	private Label incorrectDOB;

	@FXML
	private Label noRecord;

	@FXML
	private TextField FNameInput;

	@FXML
	private TextField LNameInput;

	@FXML
	private TextField DOBInput;

	@FXML
	private Button Search;

	@FXML
	private Button Cancel;

	@SuppressWarnings("unused")
	private MainApp mainApp;

	public static String fname;
	public static String lname;
	public static String dob;

	public static LinkedList<Patient> PatientSearched = new LinkedList<Patient>();

	@FXML
	private void initialize() {

		FNameInput.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				fname = FNameInput.getText();
				System.out.println(fname);
			}

		});

		LNameInput.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				lname = LNameInput.getText();
				System.out.println(lname);

			}

		});

		DOBInput.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				dob = DOBInput.getText();
				System.out.println(dob);

			}

		});

		Search.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				PatientSearched.clear();

				if (fname.isEmpty()) {
					incorrectFName.setText("Field Required");
				}

				if (lname.isEmpty()) {
					incorrectLname.setText("Field Required");
				}

				if (dob.isEmpty()) {
					incorrectDOB.setText("Field Required");
				}

				try {
					ReceptionistAccess.lookUpPatient(fname, lname, dob);

					// if this fails catch the SQLException and print the stack
					// trace
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println(PatientSearched);

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {

							// Load person overview.
							FXMLLoader load = new FXMLLoader();
							load.setLocation(MainApp.class
									.getResource("view/PatientView.fxml"));
							AnchorPane PatientView = load.load();

							// Set person overview into the center of root
							// layout.
							MainApp.rootLayout.setCenter(PatientView);

							PatientViewController controller = load
									.getController();
							controller.setMainApp(mainApp);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});

				if (PatientSearched.isEmpty()) {
					noRecord.setText("No Existing Record");
				}
			}

		});

		Cancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							// Load person overview.
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(MainApp.class
									.getResource("view/Receptionist.fxml"));
							AnchorPane edit = loader.load();

							// Set person overview into the center of root
							// layout.
							MainApp.rootLayout.setCenter(edit);

							// Give the controller access to the main app.
							ReceptionistController controller = loader
									.getController();
							controller.setMainApp(mainApp);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
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

}
