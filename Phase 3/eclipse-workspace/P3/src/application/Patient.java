package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class Patient extends User {
	
	PatientPortal patientPortal = new PatientPortal();
	
	Patient(String firstName, String lastName, LocalDate dob) {
		super(firstName, lastName, dob);
	}
	
	public void openPortal() {
		patientPortal.openPortal();
	}
	
	
	
}
