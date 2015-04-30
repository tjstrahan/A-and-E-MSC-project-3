package hospital.address.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import hospital.address.MainApp;

public class TransferClass {
	public static MainApp mainApp;
	
	public void showReceptionistSearchReturn() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class
					.getResource("view/ReceptionistSearch.fxml"));
			AnchorPane searchRecepReturn = loader.load();

			// Set person overview into the center of root layout.
			MainApp.rootLayout.setCenter(searchRecepReturn);

			// Give the controller access to the main app.
			ReceptonistSearchController controller = loader.getController();
			controller.setMainApp(mainApp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
