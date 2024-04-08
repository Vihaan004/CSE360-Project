package application;

import java.time.LocalDate;

public class Nurse {
	private Patient patient;
	
	Nurse () {
		
	}
	
	public boolean findPatient(String fname, String lname, LocalDate dob) {
		patient = new Patient(fname, lname, dob);
		if(patient.accountExists()) { return true; } 
		else { return false; }
	}
}

