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
import java.util.List;

import javafx.collections.FXCollections;

public class NurseView {
	
    private Stage stage;
    private Controller control;
    private int width, height;
    private Scene nurseScene;
    private Nurse nurse;
    private Text alert;
    
    private ComboBox<String> weightDropdown;
    private ComboBox<String> heightDropdown;
    private ComboBox<String> temperatureDropdown;
    private ComboBox<String> bloodPressureDropdown;

    private ListView<String> prescriptionListView;
    private ListView<String> healthHistoryListView;
    private ListView<String> messageListView;
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

    
    private HBox createDropdownMenus() {
    	// Dropdown menu for Weight
        ComboBox<String> weightDropdown = new ComboBox<>();
        for (int i = 50; i <= 500; i=i+1) {
            weightDropdown.getItems().add(i + " lbs");
        }
        weightDropdown.setPromptText("Weight (lbs)");

        // Dropdown menu for Height with options from 110 to 210cm
        ComboBox<String> heightDropdown = new ComboBox<>();
        for (int i = 50; i <= 220; i=i+5) {
            heightDropdown.getItems().add(i + " cm");
        }
        heightDropdown.setPromptText("Height (cms)");

        // Dropdown menu for Temperature with options from 90 to 110F
        ComboBox<String> temperatureDropdown = new ComboBox<>();
        for (int i = 90; i <= 115; i++) {
            temperatureDropdown.getItems().add(i + " F");
        }
        temperatureDropdown.setPromptText("Temperature (F)");

        // Dropdown menu for Blood Pressure with 10 more options
        ComboBox<String> bloodPressureDropdown = new ComboBox<>(FXCollections.observableArrayList(
            "120/80", "130/85", "140/90", "150/95", "160/100", 
            "170/105", "180/110", "190/115", "200/120", "210/125", "220/130"));
        bloodPressureDropdown.setPromptText("Blood Pressure");
        
        
//
//        // Dropdown menu for Age with options from 12 to 80
//        ComboBox<String> ageDropdown = new ComboBox<>();
//        for (int i = 12; i <= 80; i++) {
//            ageDropdown.getItems().add(Integer.toString(i));
//        }
//        ageDropdown.setPromptText("Age");


        // Set preferred width for dropdowns
        weightDropdown.setPrefWidth(200);
        heightDropdown.setPrefWidth(200);
        temperatureDropdown.setPrefWidth(200);
        bloodPressureDropdown.setPrefWidth(200);
//        ageDropdown.setPrefWidth(200);

        // Horizontal box for dropdown menus
        HBox dropdownMenus = new HBox(20);
        dropdownMenus.getChildren().addAll(weightDropdown, heightDropdown, temperatureDropdown, bloodPressureDropdown /*, ageDropdown*/);
        dropdownMenus.setAlignment(Pos.CENTER); 
        
        return dropdownMenus;
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
    	
        Button save = new Button("Save");
        save.setOnAction(e -> {
        	int weight = Integer.parseInt(weightDropdown.getValue().replaceAll("\\D+", ""));
            int height = Integer.parseInt(heightDropdown.getValue().replaceAll("\\D+", ""));
            int temp = Integer.parseInt(temperatureDropdown.getValue().replaceAll("\\D+", ""));
            String BP = bloodPressureDropdown.getValue();

            nurse.setPatientVitals(weight, height, temp, BP);
        });

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
        layout.getChildren().addAll(createHeader(), createFinderBox(), alert, createDropdownMenus(), createDataGrid(), createFooter());

        return new Scene(layout, width, height);
    }
    
    private void populateData() {
    	
        ListView<String> messagesListView = nurse.getPatientMessageList();
        ListView<String> healthHistoriesListView = nurse.getPatientHealthHistoryList();
        ListView<String> prescriptionsListView = nurse.getPatientPrescriptionList();

        messageListView.setItems(messagesListView.getItems());
        healthHistoryListView.setItems(healthHistoriesListView.getItems());
        prescriptionListView.setItems(prescriptionsListView.getItems());
    }



    private void logout() {
    	alert.setText("");
        control.appStart();
    }
}
