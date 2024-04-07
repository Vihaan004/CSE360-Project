// CSE 360 Group Project
// Team 40 - Tuesday 1:30 pm

package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginView {

	private Stage stage;
	private int width, height;
	private Scene startupScene;
	private Scene loginScene;
	private Controller control;
	
	Text alert = new Text("");

	private String firstName;
    private String lastName;
    private LocalDate dob;
	
	LoginView(Stage stage, Controller control, int width, int height) {
		this.stage = stage;
		this.control = control;
		this.width = width;
		this.height = height;
		startupScene = createStartupScene();
		loginScene = createLoginScene();
	}
	
	// startup page
	public void show() {
		stage.setScene(startupScene);
	    stage.show();
	}
	
	// login page
	private void loginPage() {
		stage.setScene(loginScene);
	    stage.show();
	}
	
	// create and set startup scene to current stage
	private Scene createStartupScene() {
		
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
//	    grid.setPadding(new Insets(15, 15, 15, 15));
	    
	    VBox logoBox = createLogoBox("Please select your user type to begin");
	    grid.add(logoBox, 0, 0);
	    
	    // Separator line
	    Separator separator = new Separator();
	    separator.setOrientation(Orientation.VERTICAL);
	    grid.add(separator, 1, 0, 1, 3);

	    separator.setPrefHeight(200); // line height
	    GridPane.setMargin(separator, new Insets(0, 50, 0, 50)); // margin around the separator
	    
	    
	    VBox buttonBox = new VBox(30);

	    // Buttons by user type
	    Button patientButton = new Button("Patient");
	    patientButton.setMinSize(100, 50);
	    patientButton.setOnAction(e -> {
	        loginPage();
	    });

	    Button nurseButton = new Button("Nurse");
	    nurseButton.setMinSize(100, 50);
	    nurseButton.setOnAction(e -> {
	        control.showNurseView();
	    });

	    Button doctorButton = new Button("Doctor");
	    doctorButton.setMinSize(100, 50);
	    doctorButton.setOnAction(e -> {
	        control.showDoctorView();
	    });

	    buttonBox.getChildren().addAll(patientButton, nurseButton, doctorButton);
	    // Adding buttons to the grid
	    grid.add(buttonBox, 3, 0);

	    // Scene setup
//	    Scene startupScene = new Scene(grid, width, height);
//		startupScene.getRoot().requestFocus();
		return new Scene(grid, width, height);
	    
	}

	// create and set login scene to current stage
	private Scene createLoginScene() {
	
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    
	    VBox logoBox = createLogoBox("Enter your name and date of birth");
	    grid.add(logoBox, 0, 0);
	    
	    // Separator line
	    Separator separator = new Separator();
	    separator.setOrientation(Orientation.VERTICAL);
	    grid.add(separator, 1, 0, 1, 3);

	    separator.setPrefHeight(200); // line height
	    GridPane.setMargin(separator, new Insets(0, 50, 0, 50)); // margin around the separator
	    
	    VBox loginBox = new VBox(10);
//	    loginBox.setAlignment(Pos.CENTER);
	    
	    HBox firstNameBox = new HBox(21);
	    firstNameBox.setAlignment(Pos.CENTER_LEFT); 
	    Label firstNameLabel = new Label("First Name:");
	    TextField firstNameField = new TextField(); 							// class var
//	    firstName.setPromptText("");
	    firstNameBox.getChildren().addAll(firstNameLabel, firstNameField); 
	    
	    
	    HBox lastNameBox = new HBox(21);
	    lastNameBox.setAlignment(Pos.CENTER_LEFT);
	    Label lastNameLabel = new Label("Last Name:");
	    TextField lastNameField = new TextField(); 							// class var
//	    lastName.setPromptText("last name");
	    lastNameBox.getChildren().addAll(lastNameLabel, lastNameField); 
	    
	    
	    HBox dateBox = new HBox(10);
	    dateBox.setAlignment(Pos.CENTER_LEFT);
	    Label dobLabel = new Label("Date of Birth:");
	    DatePicker dobField = new DatePicker();									// class var
	    dobField.setPromptText("MM/DD/YYYY");
	    dateBox.getChildren().addAll(dobLabel, dobField);
	    
	    alert.setFont(Font.font("verdana", 12));
	    alert.setFill(Color.RED);
	    
	    HBox buttonBox = new HBox(10);
	    buttonBox.setAlignment(Pos.CENTER_LEFT);
	    
	    Button backButton = new Button("Back");
	    backButton.setOnAction(e -> {
	    	control.appStart();
	    });
	    
	    Button registerButton = new Button("Register");
	    registerButton.setOnAction(e -> {
	    	if(validateFields(firstNameField, lastNameField, dobField)) { register(); }
	    });
	    
	    Button loginButton = new Button("Login");
	    loginButton.setAlignment(Pos.CENTER_RIGHT);
	    loginButton.setOnAction(e -> {
	    	if(validateFields(firstNameField, lastNameField, dobField)) { 
	    		login(firstNameField, lastNameField, dobField); 
	    	}
	    });
	    
	   
//	    Region spacer = new Region();
//	    HBox.setHgrow(spacer, Priority.ALWAYS); 
	    
	    buttonBox.getChildren().addAll(backButton, registerButton, loginButton);
	    
	    VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));
	    
	    
	    // adding login form to the grid
	    loginBox.getChildren().addAll(firstNameBox, lastNameBox, dateBox, buttonBox, alert);
	    grid.add(loginBox, 2, 0);
		
	    // scene setup
//        Scene loginScene = new Scene(grid, width, height);
//	    loginScene.getRoot().requestFocus();
	    
	    return new Scene(grid, width, height);
	}
	
	// create common logo section with specified prompt
	private VBox createLogoBox(String promptText) {
		VBox logoBox = new VBox(10);
	    logoBox.setAlignment(Pos.CENTER);
	    // Application logo 
	    Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
	    ImageView logoView = new ImageView(logo);
	    logoView.setFitWidth(200);
	    logoView.setPreserveRatio(true);

//	    Text header = new Text("Welcome to AutoPed");
//	    header.setFont(Font.font("verdana", 20)); // Set font name and size
	    
	    // prompt
	    Text prompt = new Text(promptText);
	    prompt.setFont(Font.font("verdana", 14)); // Set font name and size
	    
	    logoBox.getChildren().addAll(logoView, prompt); // add header?
	    return logoBox;
	}
	
	private void register() {
		
		Patient patient = new Patient(firstName, lastName, dob);
		if(patient.accountExists()) { 
			alert.setText("Account already exists, please login");
			alert.setFill(Color.GREEN);
		}
		else if (patient.createAccount()){ 
			alert.setText("Account created, please login");
			alert.setFill(Color.GREEN);
		} else {
			alert.setText("SYSTEM ERROR");
			alert.setFill(Color.RED);
		}
		
	}
	
	private void login(TextField fname, TextField lname, DatePicker date) {
		
		Patient patient = new Patient(firstName, lastName, dob);
		if(!patient.accountExists()) { 
			alert.setText("Account doesn't exist, please register or check credentials");
			alert.setFill(Color.RED);
		} else {
			fname.clear();
			lname.clear();
			date.setValue(null);
			alert.setText("");
			control.showPatientView(patient);
		}
		
		
	}
	
	private boolean validateFields(TextField firstNameField, TextField lastNameField, DatePicker dobField) {
	    firstName = firstNameField.getText().trim();
	    lastName = lastNameField.getText().trim();
	    if (firstName.isEmpty() || lastName.isEmpty()) {
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
	   
	    firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
	    lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
	    return true;
	}
	
}
