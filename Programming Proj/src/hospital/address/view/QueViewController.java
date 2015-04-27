package hospital.address.view;

import hospital.address.MainApp;
import hospital.address.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QueViewController {

	@FXML
	private Label patient1;
	
	@FXML
	private Label patient2;
	
	@FXML
	private Label patient3;
	
	@FXML
	private Label patient4;
	
	@FXML
	private Label patient5;
	
	@FXML
	private Label patient6;
	
	@FXML
	private Label patient7;
	
	@FXML
	private Label patient8;
	
	@FXML
	private Label patient9;
	
	@FXML
	private Label patient10;
	
	@FXML
	private Label Room1;
	
	@FXML
	private Label Room2;
	
	@FXML
	private Label Room3;
	
	@FXML
	private Label Room4;
	
	@FXML
	private Label Room5;
	
	private MainApp mainApp;
	
	public QueViewController(){
		
		
	}
	 /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
    	if (person != null) {
    		// Fill the labels with info from the person object.
    		patient1.setText(person.getFirstName() + " " + person.getLastName());
    		patient2.setText(person.getFirstName() + " " + person.getLastName());
    		patient3.setText(person.getFirstName() + " " + person.getLastName());
    		patient4.setText(person.getFirstName() + " " + person.getLastName());
    		patient5.setText(person.getFirstName() + " " + person.getLastName());
    		patient6.setText(person.getFirstName() + " " + person.getLastName());
    		patient7.setText(person.getFirstName() + " " + person.getLastName());
    		patient8.setText(person.getFirstName() + " " + person.getLastName());
    		patient9.setText(person.getFirstName() + " " + person.getLastName());
    		patient10.setText(person.getFirstName() + " " + person.getLastName());
    		Room1.setText(person.getFirstName() + " " + person.getLastName());
    		Room2.setText(person.getFirstName() + " " + person.getLastName());
    		Room3.setText(person.getFirstName() + " " + person.getLastName());
    		Room4.setText(person.getFirstName() + " " + person.getLastName());
    		Room5.setText(person.getFirstName() + " " + person.getLastName());
    		
    		
    	} else {
    		// Person is null, remove all the text.
    		patient1.setText(" ");
    		patient2.setText(" ");
    		patient3.setText(" ");
    		patient4.setText(" ");
    		patient5.setText(" ");
    		patient6.setText(" ");
    		patient7.setText(" ");
    		patient8.setText(" ");
    		patient9.setText(" ");
    		patient10.setText(" ");
    		Room1.setText(" ");
    		Room2.setText(" ");
    		Room3.setText(" ");
    		Room4.setText(" ");
    		Room5.setText(" ");
    	}
    }
    
    
}
