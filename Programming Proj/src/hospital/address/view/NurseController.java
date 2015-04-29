package hospital.address.view;

import java.sql.SQLException;
import java.text.ParseException;

import hospital.address.MainApp;
import hospital.address.MedicalTeamOperations;
import hospital.address.jdbc.GeneralAccess;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NurseController {

	@FXML
	private Label message;

	private Stage primaryStage;
	@FXML
	private TextField treatmentRoomField;

	@FXML
	private TextField searchNHSField;

	@FXML
	private TextArea patientDetails;

	@FXML
	private Button extendTreatment;

	@FXML
	private Button searchNHS;

	@FXML
	private Button triagePatient;

	public static String NHSNumber;

	public static int NHSNumberInt;

	public static String treatRoomNoString;

	public static int treatRoomNoInt;

	GeneralAccess ga = new GeneralAccess();

	@SuppressWarnings("unused")
	private MainApp mainApp;

	@FXML
	private void initialize() {
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

		searchNHSField.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				NHSNumber = searchNHSField.getText();

			}
		});

		searchNHS.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				DropShadow dropShadow = new DropShadow();
				
				message.setText("Searching....");
				message.setEffect(dropShadow);
				NHSNumberInt = Integer.parseInt(NHSNumber);

				try {
					ga.displayPatientByNHSNumber(NHSNumberInt);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
