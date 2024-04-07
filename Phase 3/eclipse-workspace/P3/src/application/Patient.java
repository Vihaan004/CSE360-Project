package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private File patientDir;
    private File visitDir;
    private File prescriptionDir;
    
    public Patient(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
    
    public boolean accountExists() {
        return new File("Patients" + File.separator + this.firstName + "_" + this.lastName + "_" + this.dob).exists();
    }
    
    public boolean createAccount() {
        buildDir();
        if(patientDir.mkdirs()) {
        	try {
        		// create info file, directories for visits and prescriptions
        		if(new File(patientDir, "info.txt").createNewFile() 
        				&& visitDir.mkdir() 
        				&& prescriptionDir.mkdir()) {
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
    
    private void buildDir() {
    	String patientDirPath = "Patients" + File.separator + this.firstName + "_" + this.lastName + "_" + this.dob;
    	patientDir = new File(patientDirPath);
    	visitDir = new File(patientDirPath + File.separator + "visits");
    	prescriptionDir = new File(patientDirPath + File.separator + "prescriptions");
    }
    
    public String getName() {
    	return firstName + " " + lastName + " ";
    }
    
    public String getDOB() {
    	return dob.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }
    
    public File getVisitDir() {
    	return visitDir;
    }
    
    public File getPrescriptionDir() {
    	return prescriptionDir;
    }
    
}
