package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Patient {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private File patientDir;
    private File infoFile;
    private File vitalsFile;
    private File insuranceFile;
    private File pharmacyFile;
    private File visitDir;
    private File prescriptionDir;
    private File messageDir;
    
    public Patient(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
    
    public boolean accountExists() {
    	buildDir();
    	return patientDir.exists();
    }
    
    public boolean createAccount() {
        buildDir();
        if(patientDir.mkdirs()) {
        	try {
        		// create patient files, directories for visits and prescriptions
        		infoFile = new File(patientDir, "info.txt");
        		vitalsFile = new File(patientDir, "vitals.txt");
        		insuranceFile = new File(patientDir, "insurance.txt");
        		pharmacyFile = new File(patientDir, "pharmacy.txt");
        		
        		if(infoFile.createNewFile()
        				&& vitalsFile.createNewFile()
        				&& insuranceFile.createNewFile()
        				&& pharmacyFile.createNewFile()
        				&& visitDir.mkdir() 
        				&& prescriptionDir.mkdir() 
        				&& messageDir.mkdir())
        		{
        			saveInfo();
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
    	messageDir = new File(patientDirPath + File.separator + "messages");
//    	System.out.println(visitDir.getPath());
    }
    
    
    private void saveInfo() {
        FileWriter writer = null;
        try {
            writer = new FileWriter(infoFile);
            writer.write("Name: " + getName() + "\n");
            writer.write("Date of Birth: " + getDOB() + "\n");
        } catch (IOException e) {
            System.out.println("SYSTEM ERROR (Patient->saveInfo)");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("SYSTEM ERROR (Patient->saveInfo)");
                }
            }
        }
    }
    
    
    public String getName() {
    	return firstName + " " + lastName + " ";
    }
    
    
    public String getDOB() {
    	return dob.format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }

    
    public ListView<String> getVisitList() {
    	
    	ListView<String> visitList = new ListView<>();
//    	System.out.println(new File("Patients\\E_E_2024-04-01\\visits").exists());
//    	System.out.println(visitDir.getPath());
    	
    	if (visitDir != null && visitDir.exists() && visitDir.isDirectory()) {
		    
			File[] visitFiles = visitDir.listFiles();
		    if (visitFiles != null && visitFiles.length > 0) {
		        for (File file : visitFiles) {
		            if (file.isFile()) {
		                visitList.getItems().add(file.getName());
		            }
		        }
		    } else {
		        // Directory exists but no files found
		        visitList.getItems().add("You have no visits");
		    }
		} else {
		    // Directory is null or does not exist
		    visitList.getItems().add("You have no visits");
		}
    	
    	return visitList;
    }
    
    
    public ListView<String> getPrescriptionList() {
    	
    	ListView<String> PrescriptionList = new ListView<>();
    	
    	if (prescriptionDir != null && prescriptionDir.exists() && prescriptionDir.isDirectory()) {
		    
			File[] prescriptionFiles = prescriptionDir.listFiles();
		    if (prescriptionFiles != null && prescriptionFiles.length > 0) {
		        for (File file : prescriptionFiles) {
		            if (file.isFile()) {
		                PrescriptionList.getItems().add(file.getName());
		            }
		        }
		    } else {
		        // Directory exists but no files found
		        PrescriptionList.getItems().add("You have no prescriptions");
		    }
		} else {
		    // Directory is null or does not exist
		    PrescriptionList.getItems().add("You have no prescriptions");
		}
    	
    	return PrescriptionList;
    }
    
    
    public ListView<String> getMessageList() {
    	
    	ListView<String> messageList = new ListView<>();
    	
    	if (messageDir != null && messageDir.exists() && messageDir.isDirectory()) {
		    
			File[] messageFiles = messageDir.listFiles();
		    if (messageFiles != null && messageFiles.length > 0) {
		        for (File file : messageFiles) {
		            if (file.isFile()) {
		                messageList.getItems().add(file.getName());
		            }
		        }
		    } else {
		        // Directory exists but no files found
		        messageList.getItems().add("You have no messages");
		    }
		} else {
		    // Directory is null or does not exist
		   messageList.getItems().add("You have no messages");
		}
    	
    	return messageList;
    }
    
    
    public Scene createVisitScene(String selectedVisit) {
        
//        StringBuilder contentBuilder = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(visitDir.getPath() + File.separator + selectedVisit))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                contentBuilder.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            System.out.println("SYSTEM ERROR (Patient->createVisitScene)");
//        }
//
//        Text content = new Text(contentBuilder.toString());
    	
    	Text content = fileRead(selectedVisit);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(content);

        return new Scene(layout, 500, 500);
	}
  
    
    
    
    public Scene createPrescriptionScene(String selectedPrescription) {
        
//        StringBuilder contentBuilder = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(prescriptionDir.getPath() + File.separator + selectedPrescription))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                contentBuilder.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            System.out.println("SYSTEM ERROR (Patient->createPrescriptionScene)");
//        }
//
//        Text content = new Text(contentBuilder.toString());
    	
    	Text content = fileRead(selectedPrescription);
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(content);

        return new Scene(layout, 500, 500);
	}
    
    
    public Scene createMessageScene(String selectedMessage) {
        
//        StringBuilder contentBuilder = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(visitDir.getPath() + File.separator + selectedMessage))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                contentBuilder.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            System.out.println("SYSTEM ERROR (Patient->createMessageScene)");
//        }
//
//        Text content = new Text(contentBuilder.toString());
    	
    	Text content = fileRead(selectedMessage);
    	
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(content);

        return new Scene(layout, 500, 500);
	}
    
    
    private Text fileRead(String filepath) {
    	StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(visitDir.getPath() + File.separator + filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("SYSTEM ERROR (Patient->createMessageScene)");
        }
        
        return new Text(contentBuilder.toString());
    }

    
    public void saveMessage(String message) {
    	LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm a_MM-dd-yyyy");
        String formattedDateTime = now.format(formatter);
        
        // Constructing the filename with the current date and time, replacing slashes with dashes
        String filename = formattedDateTime + ".txt";
        
        // Ensuring the message directory exists
        if (!messageDir.exists()) {
            messageDir.mkdirs();
        }

        // Creating the file under messageDir with the constructed filename
        File messageFile = new File(messageDir, filename);

        // Using try-with-resources to handle the FileWriter automatically
        try (FileWriter writer = new FileWriter(messageFile)) {
            writer.write(message);
        } catch (IOException e) {
            System.out.println("SYSTEM ERROR (Patient->saveMessage): " + e.getMessage());
        }
    }
    
    
//    LocalDateTime time = LocalDateTime.now();
//	String now = time.format(DateTimeFormatter.ofPattern("MM/dd/yyyy_HH-mm-ss"));
//	File messageFile = new File(messageDir + File.separator + now + ".txt");
//	FileWriter writer = null;
//    try {
//        writer = new FileWriter(messageFile);
//        writer.write(message + "\n");
//    } catch (IOException e) {
//        System.out.println("SYSTEM ERROR (Patient->saveMessage1)");
//    } finally {
//        if (writer != null) {
//            try {
//                writer.close();
//            } catch (IOException e) {
//                System.out.println("SYSTEM ERROR (Patient->saveMessage)");
//            }
//        }
//    }

}
