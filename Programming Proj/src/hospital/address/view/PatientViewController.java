package hospital.address.view;

import java.io.IOException;
import java.util.LinkedList;

import hospital.address.MainApp;
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

public class PatientViewController {

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
	private Label Street;

	@FXML
	private TextField SearchData;

	@FXML
	private Button Edit;

	@FXML
	private Button Admit;

	@FXML
	private Button Cancel;

	@FXML
	private Button Logout;

	private String AddressLineOneNew;

	private String AddressLineThreeNew;

	private String cityNew;

	private String AddressLineTwoNEw;

	private String PostcodeNew;

	private String StreetNew;

	private String ContactNumberNew;

	private String allergiesNew;

	private String ConditionsNew;

	private String nextOFKinNew;

	private String SearchNew;

	@SuppressWarnings("unused")
	private MainApp mainApp;
	
	public static LinkedList <Patient> Admittedpatients = new LinkedList<Patient>();

	@FXML
	public void initialize() {
		ReceptonistSearchController recs = new ReceptonistSearchController();

		String contactnum = String.valueOf(recs.PatientSearched.get(0)
				.getContactNumber());

		AddressLineOne.setText(recs.PatientSearched.get(0).getAddressLineOne());
		AddressLineThree.setText(recs.PatientSearched.get(0)
				.getAddressLineThree());
		;
		city.setText(recs.PatientSearched.get(0).getCity());
		AddressLineTwo.setText(recs.PatientSearched.get(0).getAddressLineTwo());
		Postcode.setText(recs.PatientSearched.get(0).getPostcode());
		;
		ContactNumber.setText(contactnum);
		allergies.setText(recs.PatientSearched.get(0).getAllergies());
		;
		Conditions.setText(recs.PatientSearched.get(0).getKnownConditions());
		nextOFKin.setText(recs.PatientSearched.get(0).getNextOfKin());

		Edit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							// Load person overview.
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(MainApp.class
									.getResource("view/EditPatient.fxml"));
							AnchorPane edit = loader.load();

							// Set person overview into the center of root
							// layout.
							MainApp.rootLayout.setCenter(edit);

							// Give the controller access to the main app.
							EditPatientController controller = loader
									.getController();
							controller.setMainApp(mainApp);

						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		Admit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Admittedpatients.clear();
				System.out.println(Admittedpatients);
				int NHS;
				NHS = ReceptonistSearchController.PatientSearched.get(0)
						.getNhsNumber();

				Admittedpatients.addAll(ReceptonistSearchController.PatientSearched);
				System.out.println(Admittedpatients);
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

		Logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							// Load person overview.
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(MainApp.class
									.getResource("view/Login.fxml"));
							AnchorPane edit = loader.load();

							// Set person overview into the center of root
							// layout.
							MainApp.rootLayout.setCenter(edit);

							// Give the controller access to the main app.
							LoginController controller = loader
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