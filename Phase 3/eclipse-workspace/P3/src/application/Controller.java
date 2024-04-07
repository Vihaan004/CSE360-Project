package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	LoginView loginView;
	NurseView nurseView;
	DoctorView doctorView;
	PatientView patientView;
	
	Controller (Stage stage, int width, int height) {
		loginView = new LoginView(stage, this, width, height);
		nurseView = new NurseView(stage, this, width, height);
		doctorView = new DoctorView(stage, this, width, height);
		patientView = new PatientView(stage, this, width, height);
	}
	
	Controller () {
		
	}
	
	public void appStart() {
		loginView.show();
	}
	
	public void showNurseView() {
		nurseView.show();
	}
	
	public void showDoctorView() {
		doctorView.show();
	}
	
	public void showPatientView(Patient patient) {
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
