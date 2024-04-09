package application;

import java.time.LocalDate;

import javafx.scene.control.ListView;

public class Doctor {
	private Patient patient;
	
	Doctor () {
		
	}
	
	public boolean findPatient(String fname, String lname, LocalDate dob) {
		patient = new Patient(fname, lname, dob);
		if(patient.accountExists()) { return true; } 
		else { return false; }
	}
	
//	public ListView<String> getVisitList() {
//		return patient.getVisitList();
//	}
	
	public String getContactInfo() {
		return patient.getContactInfo();
	}
	
	public String getPharmacyInfo() {
		return patient.getPharmacyInfo();
	}
	
	public String getInsuranceInfo() {
		return patient.getInsuranceInfo();
	}
	
	public ListView<String> getPrescriptionList() {
		return patient.getPrescriptionList();
	}
	
	public ListView<String> getMessageList() {
		return patient.getMessageList();
	}
	
	public ListView<String> getHealthHistoryList() {
		return patient.getHealthHistoryList();
	}
	
	public void saveMessage(String message) {
		patient.saveMessage("Doctor", message);
	}
	
	public void saveVisit(String summary) {
		patient.saveVisit(summary);
	}
	
	public void savePrescription(String prescription) {
		patient.savePrescription(prescription);
	}
	
	public String getPatientWeight() {
		return patient.getWeight();
	}
	
	public String getPatientHeight() {
		return patient.getHeight();
	}
	
	public String getPatientTemp() {
		return patient.getTemp();
	}
	
	public String getPatientBP() {
		return patient.getBP();
	}
	
	public String getPatientAge() {
		return patient.getAge();
	}
}

