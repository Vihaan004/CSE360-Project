package application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DoctorPortal extends Portal{

	//	private Stage stage;
	private Scene doctorScene;

	DoctorPortal(Stage stage) {
		super(stage);
	}

	public void accessPortal() {
		doctorScene = createPortalScene();
		stage.setScene(doctorScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		Text h = new Text("hello nurse");
		VBox hello = new VBox(h);
		Scene patientPortal = new Scene(hello, width, height);
		return patientPortal;
	}


}
