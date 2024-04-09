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
	
	public ListView<String> getPrescriptionList() {
		return patient.getPrescriptionList();
	}
	
	public ListView<String> getMessageList() {
		return patient.getMessageList();
	}
	
	public ListView<String> getHealthHistoryList() {
		return patient.getHealthHistoryList();
	}
	
	public void setPatientVitals(String weight, String height,String temp, String BP) {
		patient.setVitals(weight, height, temp, BP);
	}
	
	public void saveHealthRecord(String record) {
		patient.saveHealthRecord(record);
	}
	
	public void saveMessage(String message) {
		patient.saveMessage("Nurse", message);
	}
}

