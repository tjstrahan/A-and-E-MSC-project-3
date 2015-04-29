package hospital.address.view;

import hospital.address.MainApp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TriageController implements Initializable {

	private Button back;
	private Button setCategory;
	private MainApp mainApp;
	private Stage primaryStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCategory.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(primaryStage);
				VBox dialogVbox = new VBox(20);
				dialogVbox.getChildren().add(new Text("Treatment Extended!"));
				Scene dialogScene = new Scene(dialogVbox, 150, 150);
				dialog.setScene(dialogScene);
				dialog.show();
				
			}
		});
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				mainApp.showNurse();
				
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
