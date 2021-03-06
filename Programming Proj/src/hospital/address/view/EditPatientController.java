package hospital.address.view;

import java.io.IOException;
import java.sql.SQLException;

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
import hospital.address.MainApp;
import hospital.address.jdbc.ReceptionistAccess;

public class EditPatientController {

	// FOR WHEN YOU COME BACK MAKE FXML LOOK LIKE THE PERSON VIEW WITH DETAILS
	// ON LEFT THEN TEXTFIELD BESIDE MAYBE A SAVE BUTTON WHICH ITERATES THROUGH
	// IF NOT EMPTY THEN UPDATE METHOD OR JUST SAVE BUTTONS
	
	@FXML
	private TextField NewAddressLineOne;
	@FXML
	private TextField NewAddressLineThree;
	@FXML
	private TextField Newcity;
	@FXML
	private TextField NewAddressLineTwo;
	@FXML
	private TextField NewPostcode;
	@FXML
	private TextField NewContactNumber;
	@FXML
	private TextField Newallergies;
	@FXML
	private TextField NewConditions;
	@FXML
	private TextField NewNextOFKin;
	
	@FXML
	private Button save;
	
	long test = 123451234512L;
	
	@FXML 
	private Button ok;
	
	private String AddressLineOneNew;

	private String AddressLineThreeNew;

	private String cityNew;

	private String AddressLineTwoNEw;

	private String PostcodeNew;

	private String ContactNumberNew;

	private String allergiesNew;

	private String ConditionsNew;

	private String nextOFKinNew;
	
	private String AddressLineOneOld;

	private String AddressLineThreeOld;

	private String cityOld;

	private String AddressLineTwoOld;

	private String PostcodeOld;

	private String ContactNumberOld;

	private String allergiesOld;

	private String ConditionsOld;

	private String nextOFKinOld;
	
	@FXML
	private Label Saved;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		//ReceptonistSearchController recs = new ReceptonistSearchController();

		ContactNumberOld = String.valueOf(ReceptonistSearchController.PatientSearched.get(0)
				.getContactNumber());
		
		
		AddressLineOneOld = ReceptonistSearchController.PatientSearched.get(0).getAddressLineOne();

		AddressLineThreeOld =  ReceptonistSearchController.PatientSearched.get(0).getAddressLineThree();

		cityOld = ReceptonistSearchController.PatientSearched.get(0).getCity();

		AddressLineTwoOld = ReceptonistSearchController.PatientSearched.get(0).getAddressLineTwo();

		PostcodeOld = ReceptonistSearchController.PatientSearched.get(0).getPostcode();

		allergiesOld = ReceptonistSearchController.PatientSearched.get(0).getAllergies();

		ConditionsOld = ReceptonistSearchController.PatientSearched.get(0).getKnownConditions();

		nextOFKinOld = ReceptonistSearchController.PatientSearched.get(0).getNextOfKin();
		
		NewAddressLineOne.setText(AddressLineOneOld);
		NewAddressLineThree.setText(AddressLineThreeOld);
		Newcity.setText(cityOld);
		NewAddressLineTwo.setText(AddressLineTwoOld);
		NewPostcode.setText(PostcodeOld);
		NewContactNumber.setText(ContactNumberOld);
		Newallergies.setText(allergiesOld);
		NewConditions.setText(ConditionsOld);
		NewNextOFKin.setText(nextOFKinOld);
		
		AddressLineOneNew = AddressLineOneOld;
		AddressLineThreeNew = AddressLineThreeOld;
		AddressLineTwoNEw = AddressLineTwoOld;
		cityNew = cityOld;
		PostcodeNew = PostcodeOld;
		ContactNumberNew = ContactNumberOld;
		allergiesNew = allergiesOld;
		ConditionsNew = ConditionsOld;
		nextOFKinNew = nextOFKinOld;
		
		
		
		NewAddressLineOne.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				AddressLineOneNew = NewAddressLineOne.getText();
				System.out.println(AddressLineOneNew);
			}
		});
		
		NewAddressLineTwo.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				AddressLineTwoNEw = NewAddressLineTwo.getText();
				System.out.println(AddressLineTwoNEw);
			}
		});
		
		NewAddressLineThree.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				AddressLineThreeNew = NewAddressLineThree.getText();
				
			}
		});
		
		Newcity.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				cityNew = Newcity.getText();
				
			}
		});
		
		NewPostcode.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				PostcodeNew = NewPostcode.getText();
				
			}
		});
		
		NewContactNumber.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				ContactNumberNew = NewContactNumber.getText();
				
			}
		});
		
		NewNextOFKin.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				nextOFKinNew = NewNextOFKin.getText();
				
			}
		});
		
		NewConditions.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				ConditionsNew = NewConditions.getText();
				
			}
		});
		
		Newallergies.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				allergiesNew = Newallergies.getText();
				
			}
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			
			ReceptionistAccess rec = new ReceptionistAccess();

			@Override
			public void handle(ActionEvent event) {
				
				int NHS = ReceptonistSearchController.PatientSearched.get(0).getNhsNumber();
				long contactnums = Long.parseLong(ContactNumberNew) ;
				
				try {
						rec.updateFirstLineOfAddress(NHS, AddressLineOneNew);
						ReceptonistSearchController.PatientSearched.get(0).setAddressLineOne(AddressLineOneNew);
						rec.updateCity(NHS, cityNew);
						ReceptonistSearchController.PatientSearched.get(0).setCity(cityNew);
						rec.updateContactNumber(NHS, contactnums);
						ReceptonistSearchController.PatientSearched.get(0).setContactNumber(contactnums);
						rec.updateNextOfKin(NHS, nextOFKinNew);
						ReceptonistSearchController.PatientSearched.get(0).setNextOfKin(nextOFKinNew);
						rec.updatePostcode(NHS, PostcodeNew);
						ReceptonistSearchController.PatientSearched.get(0).setPostcode(PostcodeNew);
						rec.updateSecondLineOfAddress(NHS, AddressLineTwoNEw);
						ReceptonistSearchController.PatientSearched.get(0).setAddressLineTwo(AddressLineTwoNEw);
						rec.updateThirdLineOfAddress(NHS, AddressLineThreeNew);
						ReceptonistSearchController.PatientSearched.get(0).setAddressLineThree(AddressLineThreeNew);
						rec.addFirstAllergies(NHS, allergiesNew);
						ReceptonistSearchController.PatientSearched.get(0).setAllergies(allergiesNew);
						rec.addFirstKnownConditions(NHS, ConditionsNew);
						ReceptonistSearchController.PatientSearched.get(0).setKnownConditions(ConditionsNew);
						Saved.setText("Saved");
					} catch (Exception e) {
						e.printStackTrace();
					};
					Platform.runLater(new Runnable() {
						  @Override public void run() {
							  try {
									
									// Load person overview.
									FXMLLoader load = new FXMLLoader();
									load.setLocation(MainApp.class.getResource("view/PatientView.fxml"));
									AnchorPane PatientView = load.load();

									// Set person overview into the center of root layout.
									MainApp.rootLayout.setCenter(PatientView);

									PatientViewController controller = load.getController();
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
