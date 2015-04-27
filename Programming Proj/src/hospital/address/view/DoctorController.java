package hospital.address.view;

import hospital.address.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DoctorController {
	@FXML
	private Label messages;
	
	@FXML
	private Button Button;

	
	private MainApp mainApp;
	
	 @FXML
	    private void initialize() {
	    	// Initialize the person table with the two columns.
		Button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					
					
						messages.setText("Im a doctor doctorise me");
				
					//else LoginProcess(user, pass);
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
