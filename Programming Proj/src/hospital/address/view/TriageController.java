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

	@FXML
	public void initialize() {

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainApp.showNurse();

			}
		});

		categoryToEnter.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				triageCatString = categoryToEnter.getText().toString();

			}
		});

		setCategory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					triage = new TriageNurse();
					int triageCatInt = Integer.parseInt(triageCatString);

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

						PatientViewController.Admittedpatients.clear();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		
			}
		});

		if (!PatientViewController.Admittedpatients.isEmpty()) {
			getPatients();
		}

	}

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
