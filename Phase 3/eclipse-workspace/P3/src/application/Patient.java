package application;

import java.io.File;
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
        String path = buildFilePath();
        File file = new File(path);
        return file.exists();
    }
    
    public void saveAccount() {
        if (!accountExists()) {
            String path = buildFilePath();
            new File(path).mkdirs();
            // TODO: Create and initialize info.txt, visits.txt, prescriptions.txt
        }
        // Else, handle the case where the account already exists (e.g., show an error message)
    }
    
    private String buildFilePath() {
        return "Patients" + File.separator + this.firstName + "_" + this.lastName + "_" + this.dob;
    }
    
    // Other patient-related methods, such as opening the patient portal
}
