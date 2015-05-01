package hospital.address.view;

import java.io.IOException;

import hospital.address.MainApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReceptionistController {

	private MainApp mainApp;
	
	@FXML
	private javafx.scene.control.Button Button;
	
	@FXML
	private javafx.scene.control.Button Logout;
	
	@FXML
	private Label name;
	
	@FXML
	private void initialize() {
		
		Button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mainApp.showReceptionistSearch();
				
			}
		});
		
		Logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LoginController log = new LoginController();
				log.receptionists.clear();
				log.staffList.clear();
				Platform.runLater(new Runnable() {
					  @Override public void run() {
						  try {
								
								// Load person overview.
								FXMLLoader load = new FXMLLoader();
								load.setLocation(MainApp.class.getResource("view/Login.fxml"));
								AnchorPane Login = load.load();

								// Set person overview into the center of root layout.
								MainApp.rootLayout.setCenter(Login);

								PatientViewController controller = load.getController();
								controller.setMainApp(mainApp);

							} catch (IOException e) {
								e.printStackTrace();
							}
					  }
					});
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
