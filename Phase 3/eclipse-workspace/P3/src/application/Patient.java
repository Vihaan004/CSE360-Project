package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Patient {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    
    public Patient(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
    
    public boolean accountExists() {
        return new File(buildPath()).exists();
    }
    
    public boolean createAccount() {
        File patientDir = new File(buildPath());
        if(patientDir.mkdirs()) {
        	try {
        		// create info file, directories for visits and prescriptions
        		if(new File(patientDir, "info.txt").createNewFile() 
        				&& new File(patientDir, "visits").mkdir() 
        				&& new File(patientDir, "prescriptions").mkdir()) {
        			return true;
        		} else {
        			// clean up if dir creation fails
        			patientDir.delete();
        			return false;
        		}
        	} catch (IOException e) {
        		patientDir.delete();
        		return false;
        	}
        } else {
        	patientDir.delete();
        	return false;
        }
    }
    
    private String buildPath() {
        return "Patients" + File.separator + this.firstName + "_" + this.lastName + "_" + this.dob;
    }
    
    public String getName() {
    	return firstName + " " + lastName;
    }
    
}
