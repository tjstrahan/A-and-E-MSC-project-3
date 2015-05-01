package hospital.address.view;

import hospital.address.MainApp;
import hospital.address.TheQueue;
import hospital.address.model.Receptionist;
import hospital.address.model.TriageNurse;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TriageController {

	/**
	 * Instantiate the Queue class
	 */
	static TheQueue hospQueue = new TheQueue();

	@FXML
	private Label message;
	@FXML
	private Label AddressLineOne;
	@FXML
	private Label AddressLineThree;
	@FXML
	private Label city;
	@FXML
	private Label AddressLineTwo;
	@FXML
	private Label Postcode;
	@FXML
	private Label ContactNumber;
	@FXML
	private Label allergies;
	@FXML
	private Label Conditions;
	@FXML
	private Label nextOFKin;
	@FXML
	private Label Search;

	@FXML
	private Button back;

	@FXML
	private Button setCategory;

	@FXML
	private TextField categoryToEnter;

	private String triageCatString;

	private MainApp mainApp;

	private Stage primaryStage;

	private TriageNurse triage;

	/**
	 * FXML Initialize method setting screen 'widgets' and fields, setting up
	 * respective handlers
	 */
	@FXML
	public void initialize() {

		// Sets up the back button to leave triage and return to generic Nurse
		// login
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainApp.showNurse();

			}
		});

		// Sets up textfield to take user input for triage category
		categoryToEnter.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				triageCatString = categoryToEnter.getText().toString();

			}
		});

		// Button enabling the triage category to be set for admitted patients
		setCategory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(categoryToEnter.getText().isEmpty()){
					message.setText("Please assign a Category");
				}
				
					// Ensures that patient is on admitted list before requesting
					// triage
					if (!PatientViewController.Admittedpatients.isEmpty()) {
						try {
							triage = new TriageNurse();
							int triageCatInt = Integer.parseInt(triageCatString);

							// Ensures correct category applied
							String triageCat = "";
							switch (triageCatInt) {
							case 1:
								triageCat = "Emergency";
								break;
							case 2:
								triageCat = "Urgent";
								break;
							case 3:
								triageCat = "Semi-urgent";
								break;
							case 4:
								triageCat = "Non-urgent";
								break;
							}
							PatientViewController.Admittedpatients.get(0)
									.setTriageNumber(triageCatInt);
							PatientViewController.Admittedpatients.get(0)
									.setTriageCategory(triageCat);
							Receptionist.patientsFromDB
									.addFirst(PatientViewController.Admittedpatients
											.get(0));
							// Reset the list
							PatientViewController.Admittedpatients.clear();

							// Displays confirmation of triage category amendment
							final Stage dialog = new Stage();
							dialog.initModality(Modality.APPLICATION_MODAL);
							dialog.initOwner(primaryStage);
							VBox dialogVbox = new VBox(20);
							dialogVbox.getChildren().add(
									new Text("Patient Triaged!"));
							Scene dialogScene = new Scene(dialogVbox, 150, 150);
							dialog.setScene(dialogScene);
							dialog.show();

							// Returns to nurse screen once triage set
							mainApp.showNurse();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				
		});

		// Displays the patients details from the admitted patients list
		if (!PatientViewController.Admittedpatients.isEmpty()) {
			getPatients();
		}

	}

	/**
	 * Method that displays the patients details for triage nurse form admitted patients list
	 * If the list is empty nothing will show
	 */
	private void getPatients() {

		AddressLineOne.setText(PatientViewController.Admittedpatients.get(0)
				.getAddressLineOne());
		AddressLineTwo.setText(PatientViewController.Admittedpatients.get(0)
				.getAddressLineTwo());
		AddressLineThree.setText(PatientViewController.Admittedpatients.get(0)
				.getAddressLineThree());
		city.setText(PatientViewController.Admittedpatients.get(0).getCity());
		Postcode.setText(PatientViewController.Admittedpatients.get(0)
				.getPostcode());
		;
		allergies.setText(PatientViewController.Admittedpatients.get(0)
				.getAllergies());
		;
		Conditions.setText(PatientViewController.Admittedpatients.get(0)
				.getKnownConditions());
		nextOFKin.setText(PatientViewController.Admittedpatients.get(0)
				.getNextOfKin());
		String contactnum = String
				.valueOf(PatientViewController.Admittedpatients.get(0)
						.getContactNumber());
		ContactNumber.setText(contactnum);
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
