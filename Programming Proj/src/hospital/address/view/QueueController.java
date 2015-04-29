package hospital.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import hospital.address.MainApp;
import hospital.address.QueueTimerAlt;
import hospital.address.TheQueue;
import hospital.address.model.Patient;
import hospital.address.model.Status;

public class QueueController {

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
	private TableView<Patient> waitingList;
	@FXML
	private TableColumn<Patient, String> titleColumn1;
	@FXML
	private TableColumn<Patient, String> forenameColumn1;
	@FXML
	private TableColumn<Patient, String> surnameColumn1;
	@FXML
	private TableColumn<Patient, String> dobColumn1;
	@FXML
	private TableColumn<Patient, String> triageColumn1;
	@FXML
	private TableColumn<Patient, Integer> nhsColumn1;

	@FXML
	private TableView<Patient> onCallTeam;
	@FXML
	private TableColumn<Patient, String> titleColumn2;
	@FXML
	private TableColumn<Patient, String> forenameColumn2;
	@FXML
	private TableColumn<Patient, String> surnameColumn2;
	@FXML
	private TableColumn<Patient, String> dobColumn2;
	@FXML
	private TableColumn<Patient, String> triageColumn2;
	@FXML
	private TableColumn<Patient, Integer> nhsColumn2;

	@FXML
	private TableView<Status> statusCodeShow;
	@FXML
	private TableColumn<Status, Integer> codeColumn;

	@SuppressWarnings("unused")
	private MainApp mainApp;

	public QueueController() {
	}

	@FXML
	private void initialize() {

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

		waitingList.setEditable(false);
		waitingList.setOnMouseClicked(null);

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
		triageColumn1
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"triageCategory"));
		nhsColumn1
				.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
						"nhsNumber"));

		onCallTeam.setEditable(false);
		onCallTeam.setOnMouseClicked(null);

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
		triageColumn2
				.setCellValueFactory(new PropertyValueFactory<Patient, String>(
						"triageCategory"));
		nhsColumn2
				.setCellValueFactory(new PropertyValueFactory<Patient, Integer>(
						"nhsNumber"));

		codeColumn
				.setCellValueFactory(new PropertyValueFactory<Status, Integer>(
						"code"));
		statusCodeShow.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		codeColumn.setStyle( "-fx-alignment: CENTER; -fx-font-weight: bold; -fx-stroke: green;");
		
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		treatmentTable.setItems(mainApp.getFxTreatmentList());
		waitingList.setItems(mainApp.getFxWaitingList());
		onCallTeam.setItems(mainApp.getFxOnCallList());
		statusCodeShow.setItems(mainApp.getFxStatusCode());

	}

}
