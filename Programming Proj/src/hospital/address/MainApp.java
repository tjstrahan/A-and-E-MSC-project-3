package hospital.address;

import java.io.IOException;

import hospital.address.view.DoctorController;
import hospital.address.view.LoginController;
import hospital.address.view.NurseController;
import hospital.address.view.ReceptionistController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane anchorpane;

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("PAS");
        initRootLayout();
        showLogin();
		
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
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
            loader.setLocation(MainApp.class.getResource("view/Receptionist.fxml"));
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
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	public static void main(String[] args) throws InterruptedException {
		
		Starter start = new Starter();
		Thread t1 = new Thread(start);
		t1.start();

		launch(args);
	}
	
	
}
