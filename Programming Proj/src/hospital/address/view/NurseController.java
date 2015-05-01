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

	private Stage primaryStage;
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

	public static String treatRoomNoString;

	public static int treatRoomNoInt;
	
	public static MainApp mainApp;
	
	@FXML
	private void initialize() {
		
		prepareWindow();
		
		// Initialize the person table with the two columns.
		treatmentRoomField.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				treatRoomNoString = treatmentRoomField.getText();
				treatRoomNoInt = Integer.parseInt(treatRoomNoString);

			}
		});
	
	
		extendTreatment.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				DropShadow dropShadow = new DropShadow();

				message.setText("Extended....");
				message.setEffect(dropShadow);

				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(primaryStage);
				VBox dialogVbox = new VBox(20);
				dialogVbox.getChildren().add(new Text("Treatment Extended!"));
				Scene dialogScene = new Scene(dialogVbox, 150, 150);
				dialog.setScene(dialogScene);
				dialog.show();

				MedicalTeamOperations.extraTreatmentTime(treatRoomNoInt);
				
				
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