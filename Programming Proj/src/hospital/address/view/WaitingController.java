package hospital.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import hospital.address.MainApp;
import hospital.address.model.Patient;

public class WaitingController {

	@FXML
	private TableView<Patient> callPatient;
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
	private TableColumn<Patient, Integer> nhsColumn;

	@FXML
	private TableView<Patient> onCallPatient;
	@FXML
	private TableColumn<Patient, Integer> treatmentColumn1;
	@FXML
	private TableColumn<Patient, String> titleColumn1;
	@FXML
	private TableColumn<Patient, String> forenameColumn1;
	@FXML
	private TableColumn<Patient, String> surnameColumn1;
	@FXML
	private TableColumn<Patient, String> dobColumn1;
	@FXML
	private TableColumn<Patient, Integer> nhsColumn1;

	@FXML
	private TableView<Patient> byeByePatient;
	@FXML
	private TableColumn<Patient, Integer> treatmentColumn2;
	@FXML
	private TableColumn<Patient, String> titleColumn2;
	@FXML
	private TableColumn<Patient, String> forenameColumn2;
	@FXML
	private TableColumn<Patient, String> surnameColumn2;
	@FXML
	private TableColumn<Patient, String> dobColumn2;
	@FXML
	private TableColumn<Patient, Integer> nhsColumn2;

	@SuppressWarnings("unused")
	private MainApp mainApp;

	public WaitingController() {
	}

	@FXML
	private void initialize() {

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
		nhsColumn
				.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
						"nhsNumber"));

		titleColumn1
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"title"));
		forenameColumn1
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"firstName"));
		surnameColumn1
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"lastName"));
		dobColumn1
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"dateOfBirth"));
		nhsColumn1
				.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
						"nhsNumber"));

		titleColumn2
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"title"));
		forenameColumn2
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"firstName"));
		surnameColumn2
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"lastName"));
		dobColumn2
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"dateOfBirth"));
		nhsColumn2
				.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
						"nhsNumber"));

	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		callPatient.setItems(mainApp.getFxCallNextPatient());
		onCallPatient.setItems(mainApp.getFxCallOnCallPatient());
		byeByePatient.setItems(mainApp.getFxByeByePatient());
	}

}
