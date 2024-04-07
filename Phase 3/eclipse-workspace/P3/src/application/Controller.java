package application;

import java.io.File;

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
}
