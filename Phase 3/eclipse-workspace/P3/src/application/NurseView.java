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

public class NurseView {
	
    private Stage stage;
    private Controller control;
    private int width, height;
    private Scene nurseScene;
    private Nurse nurse;
    private Text alert;
    
    private TextField weightField;
    private TextField heightField;
    private TextField tempField;
    private TextField BPField;

    private ListView<String> prescriptionList;
    private ListView<String> healthHistoryList;
    private ListView<String> messageList;
    private TextArea healthRecordArea;
    private TextArea messageArea;

    public NurseView(Stage stage, Controller control, int width, int height) {
        this.stage = stage;
        this.control = control;
        this.width = width;
        this.height = height;
        nurse = new Nurse();
        alert = new Text(" ");
        nurseScene = createPortalScene();
    }

    public void show() {
        stage.setScene(nurseScene);
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
        		if(nurse.findPatient(fNameField.getText(), lNameField.getText(), dobField.getValue())) {
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

    
    private HBox createVitalsBox() {
    	
    	Label weightLabel = new Label("Weight (lbs):");
    	weightField = new TextField();
    	weightField.setPromptText("Weight (lbs)");
    	weightField.setPrefWidth(150);
    	weightField.setEditable(false);
    	VBox weightBox = new VBox(5, weightLabel, weightField);

    	Label heightLabel = new Label("Height (cms):");
    	heightField = new TextField();
    	heightField.setPromptText("Height (cms)");
    	heightField.setPrefWidth(150);
    	heightField.setEditable(false);
    	VBox heightBox = new VBox(5, heightLabel, heightField);

    	Label temperatureLabel = new Label("Temperature (F):");
    	tempField = new TextField();
    	tempField.setPromptText("Temperature (F)");
    	tempField.setPrefWidth(150);
    	tempField.setEditable(false);
    	VBox temperatureBox = new VBox(5, temperatureLabel, tempField);

    	Label bloodPressureLabel = new Label("Blood Pressure:");
    	BPField = new TextField();
    	BPField.setPromptText("e.g., 120/80");
    	BPField.setPrefWidth(150);
    	BPField.setEditable(false);
    	VBox bloodPressureBox = new VBox(5, bloodPressureLabel, BPField);


        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            nurse.setPatientVitals(weightField.getText(), heightField.getText(), tempField.getText(), BPField.getText());
            alert.setText("Vitals saved");
            alert.setFill(Color.GREEN);
        });

        HBox vitalsBox = new HBox(10);
        vitalsBox.getChildren().addAll(weightBox, heightBox, temperatureBox, bloodPressureBox, saveButton);
        vitalsBox.setAlignment(Pos.CENTER);

        return vitalsBox;
    }




    private GridPane createDataGrid() {
    	Label prescriptionLabel = new Label("Prescriptions");
        prescriptionList = new ListView<>();
        prescriptionList.setPrefSize(300, 200);
		VBox prescriptionBox =  new VBox(prescriptionLabel, prescriptionList);        
		
		
		Label healthHistoryLabel = new Label("Health History");
        healthHistoryList = new ListView<>();
        healthHistoryList.setPrefSize(300, 200);
		VBox healthHistoryBox =  new VBox(healthHistoryLabel, healthHistoryList);   
		
		
		Label healthRecordLabel = new Label("New Health Record");
		healthRecordArea = new TextArea();
		healthRecordArea.setPrefSize(300, 200);
		healthRecordArea.setPromptText("Allergies, Immunization, Health issues/concerns");
		healthRecordArea.setEditable(false);

		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> {
        	nurse.saveHealthRecord(healthRecordArea.getText());
        	alert.setText("Record saved");
        	alert.setFill(Color.GREEN);
        });
		
		VBox healthRecordBox = new VBox(healthRecordLabel, healthRecordArea, saveButton);
		
		
		Label viewMessagesLabel = new Label("Messages");
        messageList = new ListView<>();
        messageList.setPrefSize(300, 200);
		VBox viewMessageBox =  new VBox(viewMessagesLabel, messageList); 
		
		
		Label sendMessageLabel = new Label("Send a message");
		messageArea = new TextArea();
		messageArea.setPrefSize(300, 200);
		messageArea.setPromptText("Enter your message here");
		messageArea.setEditable(false);
		Button sendButton = new Button("Send");
		sendButton.setOnAction(e -> {
			if(!messageArea.getText().isEmpty()) {
				nurse.saveMessage(messageArea.getText());	
				alert.setText("Message sent");
				alert.setFill(Color.GREEN);
			}
			else {
				alert.setText("Message cannot be empty");
				alert.setFill(Color.RED);
			}
        });
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
    
    
    private HBox createHeader() {
    	Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
		ImageView logoView = new ImageView(logo);
		logoView.setFitWidth(75);
	    logoView.setPreserveRatio(true);
    	
    	Text heading = new Text("Nurse Portal");
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
        layout.getChildren().addAll(createHeader(), createFinderBox(), alert, createVitalsBox(), createDataGrid());

        return new Scene(layout, width, height);
    }
    
    private void populateData() {
    	
    	weightField.setEditable(true);
    	heightField.setEditable(true);
    	tempField.setEditable(true);
    	BPField.setEditable(true);
    	healthRecordArea.setEditable(true);
    	messageArea.setEditable(true);
    	
        ListView<String> messagesListView = nurse.getMessageList();
        ListView<String> healthHistoriesListView = nurse.getHealthHistoryList();
        ListView<String> prescriptionsListView = nurse.getPrescriptionList();

        messageList.setItems(messagesListView.getItems());
        healthHistoryList.setItems(healthHistoriesListView.getItems());
        prescriptionList.setItems(prescriptionsListView.getItems());
        
        messageList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !messageList.getItems().get(0).equals("You have no messages")) {
		        String selectedMessage = messageList.getSelectionModel().getSelectedItem();
		        System.out.println(selectedMessage + " opened");
		        control.popup(stage, nurse.getMessage(selectedMessage), "Message");
		    }
		});
        
        healthHistoryList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !healthHistoryList.getItems().get(0).equals("You have no health history")) {
		        String selectedRecord = healthHistoryList.getSelectionModel().getSelectedItem();
		        System.out.println(selectedRecord + " opened");
		        control.popup(stage, nurse.getRecord(selectedRecord), "Record");
		    }
		});
        
        prescriptionList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !prescriptionList.getItems().get(0).equals("You have no prescriptions")) { 
		        String selectedPrescription = prescriptionList.getSelectionModel().getSelectedItem();
		        System.out.println(selectedPrescription + " opened");
		        control.popup(stage, nurse.getPrescription(selectedPrescription), "Prescription");
		    }
		});
    }



    private void logout() {
    	alert.setText("");
        control.appStart();
    }
}
