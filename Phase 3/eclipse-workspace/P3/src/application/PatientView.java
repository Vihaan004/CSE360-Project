package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientView {
	
	private Stage stage;
    private Controller control;
	private int width, height;
	private Scene patientScene;
	private Patient patient;
	
	PatientView(Stage stage, Controller control, int width, int height) {
		this.stage = stage;
		this.control = control;
		this.width = width;
		this.height = height;
	}
	
	public void show(Patient patient) {
		this.patient = patient;
		patientScene = createPortalScene();
		stage.setScene(patientScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		Text h = new Text(patient.getName());
		Button back = new Button("Back");
		back.setOnAction(e -> {
			backToLogin();
		});
		
		VBox hello = new VBox();
		hello.getChildren().addAll(h, back);
		
		return new Scene(hello, width, height);
	}
	
	private void backToLogin() {
		control.appStart();
	}
	
	

}
