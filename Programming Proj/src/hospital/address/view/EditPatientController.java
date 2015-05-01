package hospital.address.view;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import hospital.address.MainApp;
import hospital.address.jdbc.ReceptionistAccess;
import hospital.address.model.Receptionist;

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
	
	@FXML 
	private Button ok;
	
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
	
	private String AddressLineOneOld;

	private String AddressLineThreeOld;

	private String cityOld;

	private String AddressLineTwoOld;

	private String PostcodeOld;

	private String StreetOld;

	private String ContactNumberOld;

	private String allergiesOld;

	private String ConditionsOld;

	private String nextOFKinOld;
	
	@FXML
	private Label Saved;
	
	private MainApp mainApp;
	
	@FXML
	private void initialize() {
		ReceptonistSearchController recs = new ReceptonistSearchController();

		String contactnum = String.valueOf(recs.PatientSearched.get(0)
				.getContactNumber());
		
		AddressLineOneOld = recs.PatientSearched.get(0).getAddressLineOne();

		AddressLineThreeOld =  recs.PatientSearched.get(0).getAddressLineThree();

		cityOld = recs.PatientSearched.get(0).getCity();

		AddressLineTwoOld = recs.PatientSearched.get(0).getAddressLineTwo();

		PostcodeOld = recs.PatientSearched.get(0).getPostcode();

		allergiesOld = recs.PatientSearched.get(0).getAllergies();

		ConditionsOld = recs.PatientSearched.get(0).getKnownConditions();

		nextOFKinOld = recs.PatientSearched.get(0).getNextOfKin();
		
		NewAddressLineOne.setText(AddressLineOneOld);
		NewAddressLineThree.setText(AddressLineThreeOld);
		Newcity.setText(cityOld);
		NewAddressLineTwo.setText(AddressLineTwoOld);
		NewPostcode.setText(PostcodeOld);
		NewContactNumber.setText(contactnum);
		Newallergies.setText(allergiesOld);
		NewConditions.setText(ConditionsOld);
		NewNextOFKin.setText(nextOFKinOld);
		
		
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
				
				int NHS = recs.PatientSearched.get(0).getNhsNumber();
				long contactnums = Long.parseLong(ContactNumberNew) ;
				
				try {
						rec.updateFirstLineOfAddress(NHS, AddressLineOneNew);
						rec.updateCity(NHS, cityNew);
						rec.updateContactNumber(NHS, contactnums);
						rec.updateNextOfKin(NHS, nextOFKinNew);
						rec.updatePostcode(NHS, PostcodeNew);
						rec.updateSecondLineOfAddress(NHS, AddressLineTwoNEw);
						rec.updateThirdLineOfAddress(NHS, AddressLineThreeNew);
						rec.addMoreAllergies(NHS, allergiesNew);
						rec.addMoreKnownConditions(NHS, ConditionsNew);
						Saved.setText("Saved");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				
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
