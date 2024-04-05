package application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientPortal extends Portal {
	
	PatientPortal(Stage stage) {
		super(stage);
	}

	//	private Stage stage;
	private Scene patientScene;
	
	// navigate page - override user function
	public void openPortal() {
		patientScene = createPortalScene();
		stage.setScene(patientScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		Text h = new Text("hello");
		VBox hello = new VBox(h);
		Scene patientPortal = new Scene(hello, width, height);
		return patientPortal;
	}
	
	

}
