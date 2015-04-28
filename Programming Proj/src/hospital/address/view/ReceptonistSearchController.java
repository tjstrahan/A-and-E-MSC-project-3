package hospital.address.view;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;

import hospital.address.MainApp;
import hospital.address.jdbc.GeneralAccess;
import hospital.address.jdbc.ReceptionistAccess;
import hospital.address.model.Patient;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReceptonistSearchController {
	
	@FXML
	private Label FirstName;
	
	@FXML
	private Label LastName;
	
	@FXML
	private Label DOB;
	
	@FXML 
	private TextField FNameInput;
	
	@FXML 
	private TextField LNameInput;
	
	@FXML 
	private TextField DOBInput;
	
	@FXML
	private Button Search;
	
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
			ReceptionistAccess ra = new ReceptionistAccess();
				try {
					ra.lookUpPatient(fname, lname, dob);

					// if this fails catch the SQLException and print the stack trace
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(PatientSearched);
				mainApp.showPatientView();
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
