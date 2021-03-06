package hospital.address.view;

import java.io.IOException;

import hospital.address.MainApp;
import hospital.address.MedicalTeamOperations;
import hospital.address.jdbc.GeneralAccess;
import hospital.address.model.Patient;
import hospital.address.model.Status;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NurseController {

	@FXML
	private TableView<Patient> treatmentTable;
	@FXML
	private TableColumn<Patient, Integer> treatmentColumn;
	@FXML
	private TableColumn<Patient, String> titleColumn;
	@FXML
	private TableColumn<Patient, String> forenameColumn;
	@FXML
	private TableColumn<Patient, String> surnameColumn;
	@FXML
	private TableColumn<Patient, String> dobColumn;
	@FXML
	private TableColumn<Patient, String> triageColumn;
	@FXML
	private TableColumn<Patient, Integer> nhsColumn;

	@FXML
	private TableView<Status> statusCodeShow;
	@FXML
	private TableColumn<Status, Integer> codeColumn;

	@FXML
	private Label message;
	@FXML
	private TextField treatmentRoomField;

	@FXML
	private TextArea patientDetails;

	@FXML
	private Button extendTreatment;

	@FXML
	private Button triagePatient;

	@FXML
	private Button logout;


	private Stage primaryStage;
	
	public static String treatRoomNoString;

	public static int treatRoomNoInt;
	
	public static MainApp mainApp;
	
	/**
	 * Initializes FX with screen fields and controls
	 */
	@FXML
	private void initialize() {
		
		// Dynamically populates queue 'widget' with up to date information
		prepareWindow();
		
		// Initializes treatment room field to enter room to extend
		treatmentRoomField.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				treatRoomNoString = treatmentRoomField.getText();
				treatRoomNoInt = Integer.parseInt(treatRoomNoString);

			}
		});
	
		// Sets up button for extension of treatments
		extendTreatment.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				
				if(treatmentRoomField.getText().isEmpty()){
					message.setText("Please assign a Category");
				}
				else{
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);
					VBox dialogVbox = new VBox(20);
					dialogVbox.getChildren().add(new Text("Treatment Extended!"));
					Scene dialogScene = new Scene(dialogVbox, 150, 150);
					dialog.setScene(dialogScene);
					dialog.show();
					message.setText("Treatment Extended");
					MedicalTeamOperations.extraTreatmentTime(treatRoomNoInt);
				}

				
				
				
			}
		});
		
		logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoginController log = new LoginController();
				Platform.runLater(new Runnable() {
					  
					@Override public void run() {
						  try {
							  LoginController.user = null;
							  LoginController.pass = null;
							  	log.setStaffType(null);
								// Load person overview.
								FXMLLoader load = new FXMLLoader();
								load.setLocation(MainApp.class.getResource("view/Login.fxml"));
								AnchorPane Login = load.load();

								// Set person overview into the center of root layout.
								MainApp.rootLayout.setCenter(Login);

								LoginController controller = load.getController();
								controller.setMainApp(mainApp);
								
							} catch (IOException e) {
								e.printStackTrace();
							}
					  }
					});
			}
		});
		
		triagePatient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
				Platform.runLater(new Runnable() {
					  @Override public void run() {
						  try {
								
								FXMLLoader load = new FXMLLoader();
								load.setLocation(MainApp.class.getResource("view/Triage.fxml"));
								AnchorPane triage = load.load();

								// Set person overview into the center of root layout.
								MainApp.rootLayout.setCenter(triage);

								// Give the controller access to the main app.
								TriageController controller = load.getController();
								controller.setMainApp(mainApp);

							} catch (IOException e) {
								e.printStackTrace();
							}
					  }
			
				
				});
		
		}
			
		});
	}
		

	private void prepareWindow() {
		
		
		treatmentTable.setEditable(false);
		treatmentTable.setOnMouseClicked(null);

		treatmentColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
						"actualTreatmentRoom"));
		titleColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"title"));
		forenameColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"firstName"));
		surnameColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"lastName"));
		dobColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"dateOfBirth"));
		triageColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"triageCategory"));
		nhsColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
						"nhsNumber"));

	}
	
	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		treatmentTable.setItems(mainApp.getFxTreatmentList());
	
		
	}
	}