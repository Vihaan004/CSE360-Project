package application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NurseView {
	
	private Stage stage;
	private int width, height;
	private Scene nurseScene;
	
	NurseView(Stage stage, int width, int height) {
		this.stage = stage;
		this.width = width;
		this.height = height;
	}
	
	public void view() {
		nurseScene = createPortalScene();
		stage.setScene(nurseScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		Text h = new Text("nurse");
		VBox hello = new VBox(h);
		Scene patientPortal = new Scene(hello, width, height);
		return patientPortal;
	}

}
