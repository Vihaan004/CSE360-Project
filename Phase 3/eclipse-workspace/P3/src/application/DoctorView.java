package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DoctorView {
	
    private Stage stage;
    private Controller control;
    private int width, height;
    private Scene doctorScene;
    private Doctor doctor;
    private Text alert;
    
    private Text weight;
    private Text patientHeight;
    private Text temp;
    private Text BP;
    private Text age;
    
    private ListView<String> prescriptionListView;
    private ListView<String> healthHistoryListView;
    private ListView<String> messageListView;
    private TextArea newPrescriptionArea;
    private TextArea messageArea;
    private TextArea summaryArea;
    
    private TextArea contactArea;
    private TextArea insuranceArea;
    private TextArea pharmacyArea;

    public DoctorView(Stage stage, Controller control, int width, int height) {
        this.stage = stage;
        this.control = control;
        this.width = width;
        this.height = height;
        doctor = new Doctor();
        alert = new Text(" ");
        doctorScene = createPortalScene();
    }

    public void show() {
        stage.setScene(doctorScene);
        stage.show();
    }
    
    private boolean validateFields(TextField fNameField, TextField lNameField, DatePicker dobField) {
	    String fname = fNameField.getText().trim();
	    String lname = lNameField.getText().trim();
	    LocalDate dob;
	    
	    if (fname.isEmpty() || lname.isEmpty()) {
	    	alert.setText("First name and last name required");
	    	alert.setFill(Color.RED);
	    	return false;
	    } 
	    
	    try {
	    	LocalDate.parse(dobField.getEditor().getText(), DateTimeFormatter.ofPattern("M/d/yyyy"));
	    	dob = dobField.getValue();	
	    } catch (Exception e) {
	    	alert.setText("Invalid date format. Use MM/DD/YYYY.");
	        alert.setFill(Color.RED);
	        return false;
	    }

	    if (dob == null || dob.isAfter(LocalDate.now())) {
	        alert.setText("Please enter a valid date of birth.");
	        alert.setFill(Color.RED);
	        return false;
	    }
	    return true;
	}
    
    private HBox createFinderBox() {
    	Label fNameLabel = new Label("First Name: ");
        TextField fNameField = new TextField();
        fNameField.setPrefWidth(200); // Set preferred width
        
        Label lNameLabel = new Label("Last Name: ");
        TextField lNameField = new TextField();
        lNameField.setPrefWidth(200); // Set preferred width
        
        // Label and DateField for date of birth
        Label dobLabel = new Label("Date of Birth: ");
        DatePicker dobField = new DatePicker();
        dobField.setPrefWidth(200); // Set preferred width
        dobField.setPromptText("MM/DD/YYYY");
        
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
        	
        	if(validateFields(fNameField, lNameField, dobField)) {
        		if(doctor.findPatient(fNameField.getText(), lNameField.getText(), dobField.getValue())) {
        			alert.setText("Patient Found");
        			alert.setFill(Color.GREEN);
        			populateData();
        		} else {
        			alert.setText("Patient not found, please check credentials or create a new account.");	
        			alert.setFill(Color.RED);
        		}
        	}
        	
        	
        });

        // Horizontal box for User ID label and VBox
        HBox finderBox = new HBox(10); // 10 is spacing between elements
        finderBox.getChildren().addAll(fNameLabel, fNameField, lNameLabel, lNameField, dobLabel, dobField, searchButton);
        finderBox.setAlignment(Pos.CENTER);
        
        return finderBox;
    }

    
    private HBox createPatientVitalsBox() {
    	
	    weight = new Text("Weight(lbs): ");
	    weight.setFont(Font.font("Verdana", 16));

	    patientHeight = new Text("Height(cm): ");
	    patientHeight.setFont(Font.font("Verdana", 16));

	    temp = new Text("Temperature(F): ");
	    temp.setFont(Font.font("Verdana", 16)); 

	    BP = new Text("Blood Pressure: ");
	    BP.setFont(Font.font("Verdana", 16));

	    age = new Text("Age: ");
	    age.setFont(Font.font("Verdana", 16));  


	    // Arrange info horizontally
	    HBox vitalsBox = new HBox(10); // 10 is spacing between elements
	    vitalsBox.setAlignment(Pos.CENTER); // Center alignment for HBox
	    vitalsBox.getChildren().addAll(weight, patientHeight, temp, BP, age);
        
	    return vitalsBox;
    }
    
	private VBox createInfoBox() {
		
		Label contactLabel = new Label("Contact");
		
		contactArea = new TextArea();
		contactArea.setPrefSize(300, 66);
		contactArea.setPromptText("Contact information");
		
		Label insuranceLabel = new Label("Insurance");
		
		insuranceArea = new TextArea();
		insuranceArea.setPrefSize(300, 66);
		insuranceArea.setPromptText("Insurance ID/information");
		
		Label pharmacyLabel = new Label("Pharmacy");
		
		pharmacyArea = new TextArea();
		pharmacyArea.setPrefSize(300, 66);
	    pharmacyArea.setPromptText("Pharmacy");
		
		VBox infoBox = new VBox(contactLabel, contactArea, insuranceLabel, insuranceArea, pharmacyLabel, pharmacyArea);

		return infoBox;
	}

    private GridPane createDataGrid() {
    	Label prescriptionLabel = new Label("Prescriptions");
        prescriptionListView = new ListView<>();
        prescriptionListView.setPrefSize(300, 200);
		VBox prescriptionBox =  new VBox(prescriptionLabel, prescriptionListView);        
		
		
		Label healthHistoryLabel = new Label("Health History");
        healthHistoryListView = new ListView<>();
        healthHistoryListView.setPrefSize(300, 200);
		VBox healthHistoryBox =  new VBox(healthHistoryLabel, healthHistoryListView);   
		
		
		Label newPrescriptionLabel = new Label("New Prescription");
		newPrescriptionArea = new TextArea();
		newPrescriptionArea.setPrefSize(300, 200);
		newPrescriptionArea.setPromptText("Prescribe Medications here");
		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> {
        	doctor.savePrescription(newPrescriptionArea.getText());
        	if(!newPrescriptionArea.getText().isEmpty()) {
				doctor.saveMessage(newPrescriptionArea.getText());	
				alert.setText("Prescription saved");
				alert.setFill(Color.GREEN);
			}
			else {
				alert.setText("Prescriptions cannot be empty");
				alert.setFill(Color.RED);
			}
        });
		
		VBox healthRecordBox = new VBox(newPrescriptionLabel, newPrescriptionArea, saveButton);
		
		
		Label viewMessagesLabel = new Label("Messages");
        messageListView = new ListView<>();
        messageListView.setPrefSize(300, 200);
		VBox viewMessageBox =  new VBox(viewMessagesLabel, messageListView); 
		
		
		Label sendMessageLabel = new Label("Send a message");
		messageArea = new TextArea();
		messageArea.setPrefSize(300, 200);
		messageArea.setPromptText("Enter your message here");
		Button sendButton = new Button("Send");
		sendButton.setOnAction(e -> {
			if(!messageArea.getText().isEmpty()) {
				doctor.saveMessage(messageArea.getText());	
				alert.setText("Message sent");
				alert.setFill(Color.GREEN);
			}
			else {
				alert.setText("Message cannot be empty");
				alert.setFill(Color.RED);
			}
        });
		VBox sendMessageBox = new VBox(sendMessageLabel, messageArea, sendButton);
		
		
		Label summaryLabel = new Label("Visit summary");
		summaryArea = new TextArea();
		summaryArea.setPrefSize(300, 200);
		summaryArea.setPromptText("Enter a summary of the medical examination");
		Button saveButton2 = new Button("Send");
		saveButton2.setOnAction(e -> {
        	if(!messageArea.getText().isEmpty()) {
        		doctor.saveVisit(summaryArea.getText());
				alert.setText("Visit summary saved");
				alert.setFill(Color.GREEN);
			}
			else {
				alert.setText("Visit summary cannot be empty");
				alert.setFill(Color.RED);
			}
        });
		VBox summaryBox = new VBox(summaryLabel, summaryArea, saveButton2);
		

        GridPane dataGrid = new GridPane();
        dataGrid.setHgap(20);
        dataGrid.setVgap(20);
        dataGrid.add(prescriptionBox, 0, 0);
        dataGrid.add(healthHistoryBox, 1, 0);
        dataGrid.add(healthRecordBox, 0, 1);
        dataGrid.add(summaryBox, 1, 1);
        dataGrid.add(sendMessageBox, 0, 2);
        dataGrid.add(viewMessageBox, 1, 2);
        dataGrid.add(createInfoBox(), 3, 0);
        dataGrid.setAlignment(Pos.CENTER);
        
        return dataGrid;
    }
    
    
    private HBox createHeader() {
    	Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
		ImageView logoView = new ImageView(logo);
		logoView.setFitWidth(75);
	    logoView.setPreserveRatio(true);
    	
    	Text heading = new Text("Doctor Portal");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 34));
        heading.setTextAlignment(TextAlignment.CENTER);
        
        Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(e -> {
			logout();
		});
		
		HBox header = new HBox(30);
		header.setAlignment(Pos.CENTER);
		header.getChildren().addAll(logoView, heading, logoutButton);	
		
		return header;
    }
    
    
    private Scene createPortalScene() {

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30, 30, 30, 30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(createHeader(), createFinderBox(), alert, createPatientVitalsBox(), createDataGrid());

        return new Scene(layout, width, height);
    }
    
    private void populateData() {
    	weight.setText("Weight(lbs): " + doctor.getPatientWeight());
    	patientHeight.setText("Height(cm): " + doctor.getPatientHeight());
    	temp.setText("Temperature(F): " + doctor.getPatientTemp());
    	BP.setText("Blood Pressure: " + doctor.getPatientBP());
    	age.setText("Age: " + doctor.getPatientAge());
    	
    	ListView<String> messagesListView = doctor.getMessageList();
        ListView<String> healthHistoriesListView = doctor.getHealthHistoryList();
        ListView<String> prescriptionsListView = doctor.getPrescriptionList();

        messageListView.setItems(messagesListView.getItems());
        healthHistoryListView.setItems(healthHistoriesListView.getItems());
        prescriptionListView.setItems(prescriptionsListView.getItems());

		contactArea.setText(doctor.getContactInfo());
		insuranceArea.setText(doctor.getInsuranceInfo());
		pharmacyArea.setText(doctor.getPharmacyInfo());
    }

    private void logout() {
    	alert.setText("");
        control.appStart();
    }
}
