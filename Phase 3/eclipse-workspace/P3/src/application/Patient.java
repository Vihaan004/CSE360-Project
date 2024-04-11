package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Patient {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String age;
    
    private File infoFile;
    private File contactFile;
    private File vitalsFile;
    private File insuranceFile;
    private File pharmacyFile;
    
    private File healthHistoryDir;
    private File patientDir;
    private File visitDir;
    private File prescriptionDir;
    private File messageDir;
    
    public Patient(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.age = Integer.toString(Period.between(dob, LocalDate.now()).getYears());
		buildDirs();
		buildFiles();		
    }
    
    // account management functions
    public boolean accountExists() {
    	return patientDir.exists();
    }
    
    public boolean createAccount() {
        if(patientDir.mkdirs()) {
        	try {
        		if(infoFile.createNewFile() 
        				&& contactFile.createNewFile()
        				&& vitalsFile.createNewFile()
        				&& insuranceFile.createNewFile()
        				&& pharmacyFile.createNewFile() 
        				&& healthHistoryDir.mkdir()
        				&& visitDir.mkdir() 
        				&& prescriptionDir.mkdir() 
        				&& messageDir.mkdir())
        		{
        			fileWrite(infoFile.getName(), patientDir, getName() + "\n" + getDOB() + " \n" + getAge() + " \n");
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

    private void buildDirs() {
    	String patientDirPath = "Patients" + File.separator + this.firstName + "_" + this.lastName + "_" + this.dob;
    	patientDir = new File(patientDirPath);
    	visitDir = new File(patientDirPath + File.separator + "visits");
    	prescriptionDir = new File(patientDirPath + File.separator + "prescriptions");
    	messageDir = new File(patientDirPath + File.separator + "messages");
    	healthHistoryDir = new File(patientDirPath + File.separator + "health history");
    }
    
    private void buildFiles() {
    	infoFile = new File(patientDir, "info.txt");
		contactFile = new File(patientDir, "contact.txt");
		vitalsFile = new File(patientDir, "vitals.txt");
		insuranceFile = new File(patientDir, "insurance.txt");
		pharmacyFile = new File(patientDir, "pharmacy.txt");
    }

   
    // get functions
    public String getName() {
    	return firstName + " " + lastName;
    }
    
    public String getDOB() {
    	return dob.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }
    
    public String getAge() {
    	return age;
    }
    
    public String getWeight() {
        String[] lines = fileRead("vitals.txt", patientDir).split("\n");
        return lines.length > 0 ? lines[0] : "N/A";
    }

    public String getHeight() {
        String[] lines = fileRead("vitals.txt", patientDir).split("\n");
        return lines.length > 1 ? lines[1] : "N/A";
    }

    public String getTemp() {
        String[] lines = fileRead("vitals.txt", patientDir).split("\n");
        return lines.length > 2 ? lines[2] : "N/A";
    }

    public String getBP() {
        String[] lines = fileRead("vitals.txt", patientDir).split("\n");
        return lines.length > 3 ? lines[3] : "N/A";
    }

    
    
    
    public String getContactInfo() {
    	return fileRead(contactFile.getName(), patientDir);
    }
    
    public String getInsuranceInfo() {
    	return fileRead(insuranceFile.getName(), patientDir);
    }
    
    public String getPharmacyInfo() {
    	return fileRead(pharmacyFile.getName(), patientDir);
    }

    
    // fetch data functions
    public ListView<String> getVisitList() {
    	return getFiles(visitDir);
    }
    
    public ListView<String> getPrescriptionList() {
    	return getFiles(prescriptionDir);
    }
    
    public ListView<String> getMessageList() {
    	 return getFiles(messageDir);
    }

    public ListView<String> getHealthHistoryList() {
    	return getFiles(healthHistoryDir);
    }

    
    public ListView<String> getFiles(File dir) {
    	
    	ListView<String> fileList = new ListView<>();
    	
    	if (dir != null && dir.exists() && dir.isDirectory()) {
		    
			File[] files = dir.listFiles();
		    if (files != null && files.length > 0) {
		        for (File file : files) {
		            if (file.isFile()) {
		                fileList.getItems().add(file.getName());
		            }
		        }
		    } else {
		        // Directory exists but no files found
		        fileList.getItems().add("You have no " + dir.getName());
		    }
		} else {
		    // Directory is null or does not exist
		   fileList.getItems().add("You have no " + dir.getName());
		}
    	
    	return fileList;
    }
    
    
    
    public Scene createVisitScene(String selectedVisit) {
    	
    	String content = fileRead(selectedVisit, visitDir);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(new Text(content));

        return new Scene(layout, 500, 500);
	}
     
    
    public Scene createPrescriptionScene(String selectedPrescription) {
    	
    	String content = fileRead(selectedPrescription, prescriptionDir);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(new Text(content));

        return new Scene(layout, 500, 500);
	}
    
    
    public Scene createMessageScene(String selectedMessage) {
    	
    	String content = fileRead(selectedMessage, messageDir);
    	
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(new Text(content));

        return new Scene(layout, 500, 500);
	}
    
    

    public Scene createRecordScene(String selectedRecord) {
    	
    	String content = fileRead(selectedRecord, healthHistoryDir);
    	
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(new Text(content));

        return new Scene(layout, 500, 500);
	}
    
    
    
    // edit database
    public void editInfo(String contact, String insurance, String pharmacy) {
    	fileWrite(contactFile.getName(), patientDir, contact);
    	fileWrite(insuranceFile.getName(), patientDir, insurance);
    	fileWrite(pharmacyFile.getName(), patientDir, pharmacy);
    }
    
    public void setVitals(String weight, String height, String temp, String BP) {
    	String content = weight +"\n"+ height +"\n"+ temp +"\n"+ BP +"\n"+ getAge()+"\n";
    	fileWrite("vitals.txt", patientDir, content);
    }
    
    public void saveMessage(String sender, String message) {
    	LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm a_MM-dd-yyyy");
        String formattedDateTime = now.format(formatter);
        
        String filename = sender + " " + formattedDateTime + ".txt";
        
        fileWrite(filename, messageDir, message);
    }
    
    public void saveHealthRecord(String record) {
    	LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm a_MM-dd-yyyy");
    	String formattedDateTime = now.format(formatter);
    	
    	String filename = "Record " + formattedDateTime + ".txt";
    }

    
    public void savePrescription(String prescription) {
    	LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm a_MM-dd-yyyy");
        String formattedDateTime = now.format(formatter);
        
        String filename = "Prescription " + formattedDateTime + ".txt";

        fileWrite(filename, prescriptionDir, prescription);
    }
    
    public void saveVisit(String summary) {
    	LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm a_MM-dd-yyyy");
        String formattedDateTime = now.format(formatter);
        
        String filename = "Visit " + formattedDateTime + ".txt";

        fileWrite(filename, visitDir, summary);
    }
    
    
    
    // function to read file data
    private String fileRead(String filename, File dir) {
    	
    	StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(dir.getPath() + File.separator + filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("SYSTEM ERROR: Patient->fileRead : " + filename);
        }
        
        return contentBuilder.toString();
    }

    // function to write file data
    private void fileWrite(String filename, File dir, String content) {
    	FileWriter writer = null;
        try {
            writer = new FileWriter(new File(dir.getPath() + File.separator + filename));
            writer.write(content + "\n");
            System.out.println("Data Written in file: " + filename);
        } catch (IOException e) {
            System.out.println("SYSTEM ERROR:Patient->fileWrite : " + filename);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("SYSTEM ERROR: Patient->saveWrite : " + filename);
                }
            }
        }
    }
    
    private void fileAppend(String filename, File dir, String content) {
    	FileWriter writer = null;
    	try {
            // Open the file in append mode
            writer = new FileWriter(new File(dir.getPath() + File.separator + filename), true);
            // Append the content to the file
            writer.write(content + "\n");
            System.out.println("Data Appended to file: " + filename);
        } catch (IOException e) {
            System.out.println("SYSTEM ERROR: Patient->fileAppend : " + filename);
        } finally {
        	if (writer != null) {
        		try {
        			writer.close();
        		} catch (IOException e) {
        			System.out.println("SYSTEM ERROR: Patient->fileAppend : " + filename);
            }
        }
    }
}
    

}
