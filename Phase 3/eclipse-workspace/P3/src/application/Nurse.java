package application;

import java.time.LocalDate;

import javafx.scene.control.ListView;

public class Nurse {
	private Patient patient;
	
	Nurse () {
		
	}
	
	public boolean findPatient(String fname, String lname, LocalDate dob) {
		patient = new Patient(fname, lname, dob);
		if(patient.accountExists()) { return true; } 
		else { return false; }
	}
//	
//	public ListView<String> getVisitList() {
//		return patient.getVisitList();
//	}
	
	public ListView<String> getPatientPrescriptionList() {
		return patient.getPrescriptionList();
	}
	
	public ListView<String> getPatientMessageList() {
		return patient.getMessageList();
	}
	
	public ListView<String> getPatientHealthHistoryList() {
		return patient.getHealthHistoryList();
	}
	
	public void setPatientVitals(int weight, int height, int temp, String BP) {
		patient.setVitals(weight, height, temp, BP);
	}
}

