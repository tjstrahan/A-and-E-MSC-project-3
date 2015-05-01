package hospital.address.view;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;

import hospital.address.MainApp;
import hospital.address.MedicalTeamOperations;
import hospital.address.jdbc.DoctorAccess;
import hospital.address.model.Patient;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DoctorController {

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
	private Button extendTreatment;

	@FXML
	private TextField treatmentRoomField;

	@FXML
	private Label messages;

	@FXML
	private Button notes;

	@FXML
	private TextField notesInput;

	@FXML
	private TextField nhsNumberInput;

	@FXML
	private Button search;
	
	@FXML
	private Button logout;
	
	@FXML
	private Label displayNotes;
	
	public static String NHSNumber;

	public static int NHSNumberInt;

	public static String treatRoomNoString;

	public static int treatRoomNoInt;
	
	private Stage primaryStage;
	
	public static LinkedList<String> notesPatient = new LinkedList<String>();

	DoctorAccess da = new DoctorAccess();

	MedicalTeamOperations mTo = new MedicalTeamOperations();

	@SuppressWarnings("unused")
	private MainApp mainApp;

	@FXML
	private void initialize() {

		prepareWindow();

		treatmentRoomField.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				treatRoomNoString = treatmentRoomField.getText().toString();
			}
		});

		extendTreatment.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				

				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(primaryStage);
				VBox dialogVbox = new VBox(20);
				dialogVbox.getChildren().add(new Text("Treatment Extended!"));
				Scene dialogScene = new Scene(dialogVbox, 150, 150);
				dialog.setScene(dialogScene);
				dialog.show();
				
				
				treatRoomNoInt = Integer.parseInt(treatRoomNoString);
				MedicalTeamOperations.extraTreatmentTime(treatRoomNoInt);
			}
		});

		nhsNumberInput.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				NHSNumber = nhsNumberInput.getText();

			}
		});


		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				NHSNumberInt = Integer.parseInt(NHSNumber);
			
				try {
					System.out.println("here");
					DoctorAccess da = new DoctorAccess();
					String previousNotes;
					previousNotes = da.viewNotesOnPatientRecord(NHSNumberInt);
					displayNotes.setText(notesPatient.get(0));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		});

		notes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				String notesToWrite = notesInput.getText().toString();

				try {
					da.writeNotesOnPatientRecord(NHSNumberInt, notesToWrite);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
