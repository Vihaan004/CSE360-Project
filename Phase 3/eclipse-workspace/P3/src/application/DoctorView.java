package application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DoctorView {

	private Stage stage;
	private int width, height;
	private Scene doctorScene;

	DoctorView(Stage stage, int width, int height) {
		this.stage = stage;
		this.width = width;
		this.height = height;
	}

	public void view() {
		doctorScene = createPortalScene();
		stage.setScene(doctorScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		Text h = new Text("doctor");
		VBox hello = new VBox(h);
		Scene patientPortal = new Scene(hello, width, height);
		return patientPortal;
	}


}
