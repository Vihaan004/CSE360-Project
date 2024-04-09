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
    private TextArea healthRecordArea;
    private TextArea messageArea;

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

    
    private HBox createPatientInfoBox() {
    	
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
	    HBox patientInfo = new HBox(10); // 10 is spacing between elements
	    patientInfo.setAlignment(Pos.CENTER); // Center alignment for HBox
	    patientInfo.getChildren().addAll(weight, patientHeight, temp, BP, age);
        
	    return patientInfo;
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
		
		
		Label healthRecordLabel = new Label("New Health Record");
		healthRecordArea = new TextArea();
		healthRecordArea.setPrefSize(300, 200);
		healthRecordArea.setPromptText("Allergies, Immunization, Health issues/concerns");
		Button saveButton = new Button("Send");
		VBox healthRecordBox = new VBox(healthRecordLabel, healthRecordArea, saveButton);
		
		
		Label viewMessagesLabel = new Label("Messages");
        messageListView = new ListView<>();
        messageListView.setPrefSize(300, 200);
		VBox viewMessageBox =  new VBox(viewMessagesLabel, messageListView); 
		
		
		Label sendMessageLabel = new Label("Send a message");
		messageArea = new TextArea();
		messageArea.setPrefSize(300, 200);
		messageArea.setPromptText("Enter your message here");
		Button sendButton = new Button("Send");
		VBox sendMessageBox = new VBox(sendMessageLabel, messageArea, sendButton);
		

        GridPane dataGrid = new GridPane();
        dataGrid.setHgap(20);
        dataGrid.setVgap(20);
        dataGrid.add(prescriptionBox, 0, 0);
        dataGrid.add(healthHistoryBox, 1, 0);
        dataGrid.add(healthRecordBox, 0, 1);
        dataGrid.add(sendMessageBox, 1, 1);
        dataGrid.add(viewMessageBox, 0, 2, 2, 1);
        dataGrid.setAlignment(Pos.CENTER);
        
        return dataGrid;
    }
    
    
    private HBox createFooter() {

        // Create a new Save button
        Button save = new Button("Save");
        save.setOnAction(e -> {
            // Add action logic for the Save button
        });

        // Horizontal box for Back and Save buttons
        HBox footer = new HBox(10); // 10 is spacing between buttons
        footer.getChildren().addAll(save);
        footer.setAlignment(Pos.CENTER);
        
        return footer;
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
        layout.getChildren().addAll(createHeader(), createFinderBox(), alert, createPatientInfoBox(), createDataGrid(), createFooter());

        return new Scene(layout, width, height);
    }
    
    private void populateData() {
    	weight.setText("Weight(lbs): " + doctor.getPatientWeight());
    	patientHeight.setText("Height(cm): " + doctor.getPatientHeight());
    	temp.setText("Temperature(F): " + doctor.getPatientTemp());
    	BP.setText("Blood Pressure: " + doctor.getPatientBP());
    	age.setText("Age: " + doctor.getPatientAge());
    	messageListView = doctor.getMessageList();
    	healthHistoryListView = doctor.getHealthHistoryList();
    	prescriptionListView = doctor.getPrescriptionList();
    }

    private void logout() {
    	alert.setText("");
        control.appStart();
    }
}
