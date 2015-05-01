package hospital.address;

import java.io.IOException;

import hospital.address.QueueTimerAlt;
import hospital.address.model.Patient;
import hospital.address.model.Status;
import hospital.address.view.DoctorController;
import hospital.address.view.LoginController;
import hospital.address.view.NurseController;
import hospital.address.view.PatientViewController;
import hospital.address.view.ReceptionistController;
import hospital.address.view.ReceptonistSearchController;
import hospital.address.view.QueueController;
import hospital.address.view.WaitingController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	static QueueTimerAlt qTa = new QueueTimerAlt();
	static TheQueue tQ = new TheQueue();

	public static ObservableList<Patient> fxTreatmentList = FXCollections
			.observableArrayList();

	public static ObservableList<Patient> fxWaitingList = FXCollections
			.observableArrayList();

	public static ObservableList<Patient> fxOnCallList = FXCollections
			.observableArrayList();

	public static ObservableList<Status> fxStatusCode = FXCollections
			.observableArrayList();
	
	public static ObservableList<Patient> fxCallNextPatient = FXCollections
			.observableArrayList();
	
	public static ObservableList<Patient> fxCallOnCallPatient = FXCollections
			.observableArrayList();

	public static ObservableList<Patient> fxByeByePatient = FXCollections
			.observableArrayList();

	static Patient patient;

	private Stage primaryStage;
	public static BorderPane rootLayout;
	@SuppressWarnings("unused")
	private AnchorPane anchorpane;

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("PAS");
		initRootLayout();
		queueWindow();
		patientViewWaitingList();
		showLogin();

	}
 
	public void queueWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/QueueMaster.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();

			QueueController controller = loader.getController();
			controller.setMainApp(this);

			stage.setScene(new Scene(root, 1000, 800));
			stage.setTitle("Staff View");
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void patientViewWaitingList() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/WaitingMaster.fxml"));
			Parent root = loader.load();
			Stage stage = new Stage();

			
			WaitingController controller = loader.getController();
			controller.setMainApp(this);

			
			stage.setScene(new Scene(root, 1000, 400));
			stage.setTitle("Patient View");
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showLogin() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
			AnchorPane Login = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(Login);

			// Give the controller access to the main app.
			LoginController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showRecep() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/Receptionist.fxml"));
			AnchorPane recep = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(recep);

			// Give the controller access to the main app.
			ReceptionistController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showEdit() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/EditPatient.fxml"));
			AnchorPane edit = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(edit);

			// Give the controller access to the main app.
			ReceptionistController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showPatientView() {
		try {
		
			// Load person overview.
			FXMLLoader load = new FXMLLoader();
			load.setLocation(MainApp.class.getResource("view/PatientView.fxml"));
			AnchorPane PatientView = load.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(PatientView);

			PatientViewController controller = load.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showReceptionistSearch() {
		try {
			// Load person overview.
			FXMLLoader load = new FXMLLoader();
			load.setLocation(MainApp.class
					.getResource("view/ReceptionistSearch.fxml"));
			AnchorPane searchRecep = load.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(searchRecep);

			// Give the controller access to the main app.
			ReceptonistSearchController controller = load.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showDoc() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Doctor.fxml"));
			AnchorPane doc = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(doc);

			// Give the controller access to the main app.
			DoctorController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void showTriage() {

		try {
			// Load person overview.
			FXMLLoader load = new FXMLLoader();
			load.setLocation(MainApp.class
					.getResource("view/Triage.fxml"));
			AnchorPane searchRecep = load.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(searchRecep);

			// Give the controller access to the main app.
			ReceptonistSearchController controller = load.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showNurse() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Nurse.fxml"));
			AnchorPane nurse = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(nurse);

			// Give the controller access to the main app.
			NurseController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void clearFxTreatmentList() {
		fxTreatmentList.clear();
	}
	
	public static void copyFxTreatmentListAgain() {
		fxTreatmentList.addAll(QueueTimerAlt.forDisplay);
	}

	public static void clearFxWaitingList() {
		fxWaitingList.clear();
	}
	
	public static void copyFxWaitingListAgain() {
		fxWaitingList.addAll(QueueTimerAlt.waitingCopy);
	}

	public static void clearFxOnCallList() {
		fxOnCallList.clear();
	}
	
	public static void copyFxOnCallListAgain() {
		fxOnCallList.addAll(QueueTimerAlt.onCallCopy);
	}

	public static void clearFxStatus() {
		fxStatusCode.clear();
		
	}
	public static void copyFxStatusAgain() {
		fxStatusCode.addAll(QueueTimerAlt.statusCodeList);
	}
	
	public ObservableList<Patient> getFxTreatmentList() {
		fxTreatmentList.addAll(QueueTimerAlt.forDisplay);
		return fxTreatmentList;
	}

	public ObservableList<Patient> getFxWaitingList() {
		fxWaitingList.addAll(QueueTimerAlt.waitingCopy);
		return fxWaitingList;
	}

	public ObservableList<Patient> getFxOnCallList() {
		fxOnCallList.addAll(QueueTimerAlt.onCallCopy);
		return fxOnCallList;
	}

	public ObservableList<Status> getFxStatusCode() {
		fxStatusCode.addAll(QueueTimerAlt.statusCodeList);
		return fxStatusCode;
	}
	
	public ObservableList<Patient> getFxCallOnCallPatient() {
		return fxCallOnCallPatient;
	}
	
	public ObservableList<Patient> getFxCallNextPatient() {
		return fxCallNextPatient;
	}
	
	public ObservableList<Patient> getFxByeByePatient() {
		return fxByeByePatient;
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args){

		Starter start = new Starter();
		Thread t1 = new Thread(start);
		t1.start();

		launch(args);
	}

}
