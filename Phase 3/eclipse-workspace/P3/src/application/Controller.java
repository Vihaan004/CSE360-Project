package application;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	
	Stage stage;
	int width, height;
	LoginView loginView;
	NurseView nurseView;
	DoctorView doctorView;
	PatientView patientView;
	
	Controller (Stage stage, int width, int height) {
		this.stage = stage;
		this.width = width;
		this.height = height;
	}
	
	public void appStart() {
		loginView = new LoginView(stage, this, width, height);
		loginView.show();
	}
	
	public void showNurseView() {
		nurseView = new NurseView(stage, this, width, height);
		nurseView.show();
	}
	
	public void showDoctorView() {
		doctorView = new DoctorView(stage, this, width, height);
		doctorView.show();
	}
	
	public void showPatientView(Patient patient) {
		patientView = new PatientView(stage, this, width, height);
		patientView.show(patient);
	}
	
	public void popup(Stage parentStage, Scene scene, String title) {
		Stage popup = new Stage();
		popup.initOwner(parentStage);
		popup.initModality(Modality.WINDOW_MODAL);
        popup.setTitle(title);	
        popup.setScene(scene);
        popup.show();
	}
}
