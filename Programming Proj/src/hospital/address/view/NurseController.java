package hospital.address.view;

import hospital.address.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class NurseController {
	@FXML
	private Label message;
	
	@FXML
	private Button Button;

	
	@SuppressWarnings("unused")
	private MainApp mainApp;
	
	 @FXML
	    private void initialize() {
	    	// Initialize the person table with the two columns.
		Button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
						message.setText("Im a nurse nurse me back to health");
				
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
