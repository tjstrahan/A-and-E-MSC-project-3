package hospital.address.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import hospital.address.MainApp;

public class ReceptionistController {

	@FXML
	private Label message;

	@FXML
	private Label Name;

	@FXML
	private Button Button;

	@FXML
	private Button AdmitOneButton;
	@FXML
	private Button LookUpPatientButton;
	@FXML
	private Button EditPatientButton;

	@SuppressWarnings("unused")
	private MainApp mainApp;

	@FXML
	private void initialize() {

		int number = Integer.parseInt(LoginController.user);
		String name = null;
		for (int loop = 0; loop < LoginController.receptionists.size(); loop++) {
			if (number == (LoginController.receptionists.get(loop).getLoginID())) {
				name = LoginController.receptionists.get(loop).getFirstName();
			}

			Name.setText(name);

			Button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub

					message.setText("Sorry something wasnt right");

					// else LoginProcess(user, pass);
				}

			});
		}
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
