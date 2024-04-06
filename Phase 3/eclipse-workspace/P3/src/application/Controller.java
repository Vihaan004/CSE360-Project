package application;

import java.io.File;

import javafx.stage.Stage;

public class Controller {
	LoginView loginView;
	NurseView nurseView;
	DoctorView doctorView;
	PatientView patientView;
	
	Controller (Stage stage, int width, int height) {
		loginView = new LoginView(stage, width, height);
		nurseView = new NurseView(stage, width, height);
		doctorView = new DoctorView(stage, width, height);
		patientView = new PatientView(stage, width, height);
	}
	
	public void appStart() {
		File dir = new File("Patients");
		if(!dir.exists()) {
			dir.mkdirs();
		}
		loginView.view();
	}
	
	public void showNurseView() {
		nurseView.view();
	}
	
	public void showDoctorView() {
		doctorView.view();
	}
	
	public void showPatientView() {
		patientView.view();
	}
}
