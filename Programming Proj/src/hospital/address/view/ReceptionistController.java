package hospital.address.view;

import hospital.address.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReceptionistController {

	private MainApp mainApp;
	
	@FXML
	private javafx.scene.control.Button Button;
	
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
