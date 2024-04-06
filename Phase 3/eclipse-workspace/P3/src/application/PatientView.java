package application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientView {
	
	private Stage stage;
	private int width, height;
	private Scene patientScene;
	
	PatientView(Stage stage, int width, int height) {
		this.stage = stage;
		this.width = width;
		this.height = height;
	}
	
	public void view() {
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
