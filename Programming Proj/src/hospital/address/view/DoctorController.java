package hospital.address.view;

import java.sql.SQLException;
import java.text.ParseException;

import hospital.address.MainApp;
import hospital.address.MedicalTeamOperations;
import hospital.address.jdbc.DoctorAccess;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DoctorController {
	@FXML
	private Label messages;

	@FXML
	private Button notes;

	@FXML
	private TextField notesInput;

	@FXML
	private TextField nhsNumberInput;

	@FXML
	private TextField treatmentRoom;

	@FXML
	private Button search;

	@FXML
	private Button extendTreatmentButton;

	public static String NHSNumber;

	public static int NHSNumberInt;

	public static String treatRoomNoString;

	public static int treatRoomNoInt;

	DoctorAccess da = new DoctorAccess();

	MedicalTeamOperations mTo = new MedicalTeamOperations();

	@SuppressWarnings("unused")
	private MainApp mainApp;

	@FXML
	private void initialize() {

		nhsNumberInput.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				NHSNumber = nhsNumberInput.getText();

			}
		});

		extendTreatmentButton.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				treatRoomNoString = treatmentRoom.getText();
			}

		});

		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				NHSNumberInt = Integer.parseInt(NHSNumber);
			}
		});

		treatmentRoom.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				treatRoomNoInt = Integer.parseInt(treatRoomNoString);
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

		extendTreatmentButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {

				try {
					mTo.extraTreatmentTime(treatRoomNoInt);
				} catch (Exception e) {
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
