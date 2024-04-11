// CSE 360 Group Project
// Team 40 - Tuesday 1:30pm
package application;
import java.time.LocalDate;

import javafx.scene.Scene;
import javafx.scene.control.ListView;

// Represents a Doctor in the system, capable of accessing and modifying patient information.
public class Doctor {
    private Patient patient; // Current patient being accessed or modified
    
    Doctor() {
        // Constructor for Doctor
    }
    
    // Attempts to find a patient based on their name and date of birth.
    // Returns true if the patient exists, false otherwise.
    public boolean findPatient(String fname, String lname, LocalDate dob) {
        patient = new Patient(fname, lname, dob);
        return patient.accountExists();
    }
    
    // Retrieves patient's contact information.
    public String getContactInfo() {
        return patient.getContactInfo();
    }
    
    // Retrieves patient's pharmacy information.
    public String getPharmacyInfo() {
        return patient.getPharmacyInfo();
    }
    
    // Retrieves patient's insurance information.
    public String getInsuranceInfo() {
        return patient.getInsuranceInfo();
    }
    
    // Returns a list of prescriptions associated with the patient.
    public ListView<String> getPrescriptionList() {
        return patient.getPrescriptionList();
    }
    
    // Returns a list of messages associated with the patient.
    public ListView<String> getMessageList() {
        return patient.getMessageList();
    }
    
    // Returns a list detailing the patient's health history.
    public ListView<String> getHealthHistoryList() {
        return patient.getHealthHistoryList();
    }
    
    // Saves a message from the doctor to the patient's record.
    public void saveMessage(String message) {
        patient.saveMessage("Doctor", message);
    }
    
    // Records a summary of a visit to the patient's history.
    public void saveVisit(String summary) {
        patient.saveVisit(summary);
    }
    
    // Adds a prescription to the patient's list of medications.
    public void savePrescription(String prescription) {
        patient.savePrescription(prescription);
    }
    
    // Generates a scene to display a selected message from the patient's message list.
    public Scene getMessage(String selectedMessage) {
        return patient.createMessageScene(selectedMessage);
    }

    // Generates a scene to display a selected health record from the patient's health history.
    public Scene getRecord(String selectedRecord) {
        return patient.createRecordScene(selectedRecord);
    }

    // Generates a scene to display a selected prescription from the patient's prescription list.
    public Scene getPrescription(String selectedPrescription) {
        return patient.createPrescriptionScene(selectedPrescription);
    }
    
    // Retrieves patient's weight.
    public String getPatientWeight() {
        return patient.getWeight();
    }
    
    // Retrieves patient's height.
    public String getPatientHeight() {
        return patient.getHeight();
    }
    
    // Retrieves patient's temperature.
    public String getPatientTemp() {
        return patient.getTemp();
    }
    
    // Retrieves patient's blood pressure.
    public String getPatientBP() {
        return patient.getBP();
    }
    
    // Calculates and returns patient's age.
    public String getPatientAge() {
        return patient.getAge();
    }
}
