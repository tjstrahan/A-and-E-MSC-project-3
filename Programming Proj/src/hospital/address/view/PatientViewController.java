package hospital.address.view;

import java.io.IOException;

import hospital.address.MainApp;
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
					  @Override public void run() {
						  try {
								// Load person overview.
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(MainApp.class
										.getResource("view/EditPatient.fxml"));
								AnchorPane edit = loader.load();

								// Set person overview into the center of root layout.
								MainApp.rootLayout.setCenter(edit);

								// Give the controller access to the main app.
								ReceptionistController controller = loader.getController();
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