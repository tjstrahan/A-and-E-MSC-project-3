package hospital.address.view;

import hospital.address.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

public class ReceptionistController {

	private MainApp mainApp;
	
	@FXML
	private javafx.scene.control.Button Button;
	
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
