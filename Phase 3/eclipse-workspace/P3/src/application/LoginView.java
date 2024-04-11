// CSE 360 Group Project
// Team 40 - Tuesday 1:30 pm

package application;

// Import necessary JavaFX and Java Time libraries
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

// Class definition for the login view of the application
public class LoginView {

    // Class variables
	private Stage stage;
	private int width, height;
	private Scene startupScene;
	private Scene loginScene;
	private Controller control;
	Text alert = new Text(""); // For displaying login or registration errors

	private String firstName;
    private String lastName;
    private LocalDate dob;
	
	// Constructor
	LoginView(Stage stage, Controller control, int width, int height) {
		this.stage = stage;
		this.control = control;
		this.width = width;
		this.height = height;
		// Initialize scenes for startup and login
		startupScene = createStartupScene();
		loginScene = createLoginScene();
	}
	
	// Display the startup scene
	public void show() {
		stage.setScene(startupScene);
	    stage.show();
	}
	
	// Switch to the login scene
	private void loginPage() {
		stage.setScene(loginScene);
	    stage.show();
	}
	
	// Creates the initial startup scene with user type selection
	private Scene createStartupScene() {
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    
	    // Create and add the logo box to the grid
	    VBox logoBox = createLogoBox("Please select your user type to begin");
	    grid.add(logoBox, 0, 0);
	    
	    // Separator for visual distinction
	    Separator separator = new Separator();
	    separator.setOrientation(Orientation.VERTICAL);
	    separator.setPrefHeight(200); // Set the separator's height
	    GridPane.setMargin(separator, new Insets(0, 50, 0, 50)); // Add margin around separator
	    grid.add(separator, 1, 0, 1, 3);

	    // Container for the buttons
	    VBox buttonBox = new VBox(30);

	    // Initialize buttons for different user types and add action listeners
	    Button patientButton = new Button("Patient");
	    patientButton.setMinSize(100, 50);
	    patientButton.setOnAction(e -> loginPage());

	    Button nurseButton = new Button("Nurse");
	    nurseButton.setMinSize(100, 50);
	    nurseButton.setOnAction(e -> control.showNurseView());

	    Button doctorButton = new Button("Doctor");
	    doctorButton.setMinSize(100, 50);
	    doctorButton.setOnAction(e -> control.showDoctorView());

	    // Add buttons to the container
	    buttonBox.getChildren().addAll(patientButton, nurseButton, doctorButton);
	    grid.add(buttonBox, 3, 0);

	    // Return the constructed scene
		return new Scene(grid, width, height);
	}

	// Creates the login scene where users can enter their credentials
	private Scene createLoginScene() {
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    
	    // Add the logo box with instructions for login
	    VBox logoBox = createLogoBox("Enter your name and date of birth");
	    grid.add(logoBox, 0, 0);
	    
	    // Repeat use of separator for visual distinction
	    Separator separator = new Separator();
	    separator.setOrientation(Orientation.VERTICAL);
	    separator.setPrefHeight(200); // Set height
	    GridPane.setMargin(separator, new Insets(0, 50, 0, 50)); // Add margin
	    grid.add(separator, 1, 0, 1, 3);
	    
	    // Login form container
	    VBox loginBox = new VBox(10);
	    
	    // First name input field
	    HBox firstNameBox = new HBox(21);
	    firstNameBox.setAlignment(Pos.CENTER_LEFT); 
	    Label firstNameLabel = new Label("First Name:");
	    TextField firstNameField = new TextField();
	    firstNameBox.getChildren().addAll(firstNameLabel, firstNameField);
	    
	    // Last name input field
	    HBox lastNameBox = new HBox(21);
	    lastNameBox.setAlignment(Pos.CENTER_LEFT);
	    Label lastNameLabel = new Label("Last Name:");
	    TextField lastNameField = new TextField();
	    lastNameBox.getChildren().addAll(lastNameLabel, lastNameField);
	    
	    // Date of birth input field
	    HBox dateBox = new HBox(10);
	    dateBox.setAlignment(Pos.CENTER_LEFT);
	    Label dobLabel = new Label("Date of Birth:");
	    DatePicker dobField = new DatePicker();
	    dobField.setPromptText("MM/DD/YYYY");
	    dateBox.getChildren().addAll(dobLabel, dobField);
	    
	    // Alert text setup
	    alert.setFont(Font.font("verdana", 12));
	    alert.setFill(Color.RED);
	    
	    // Button container for navigation and actions
	    HBox buttonBox = new HBox(10);
	    buttonBox.setAlignment(Pos.CENTER_LEFT);
	    Button backButton = new Button("Back");
	    backButton.setOnAction(e -> control.appStart());
	    Button registerButton = new Button("Register");
	    registerButton.setOnAction(e -> {
	    	if(validateFields(firstNameField, lastNameField, dobField)) { register(); }
	    });
	    Button loginButton = new Button("Login");
	    loginButton.setOnAction(e -> {
	    	if(validateFields(firstNameField, lastNameField, dobField)) { 
	    		login(firstNameField, lastNameField, dobField); 
	    	}
	    });
	    buttonBox.getChildren().addAll(backButton, registerButton, loginButton);
	    VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));
	    
	    // Compile components into the login form
	    loginBox.getChildren().addAll(firstNameBox, lastNameBox, dateBox, buttonBox, alert);
	    grid.add(loginBox, 2, 0);
	    
	    // Return the constructed scene
	    return new Scene(grid, width, height);
	}
	
	// Utility method to create a logo box that can be reused
	private VBox createLogoBox(String promptText) {
		VBox logoBox = new VBox(10);
	    logoBox.setAlignment(Pos.CENTER);
	    // Load and display the application logo
	    Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
	    ImageView logoView = new ImageView(logo);
	    logoView.setFitWidth(200);
	    logoView.setPreserveRatio(true);
	    
	    // Display a prompt text below the logo
	    Text prompt = new Text(promptText);
	    prompt.setFont(Font.font("verdana", 14)); // Set font size and family
	    
	    // Add the logo and prompt to the container
	    logoBox.getChildren().addAll(logoView, prompt);
	    return logoBox;
	}
	
	// Handle registration process
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
	
	// Handle login process
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
	
	// Validates the input fields for registration and login
	private boolean validateFields(TextField firstNameField, TextField lastNameField, DatePicker dobField) {
	    firstName = firstNameField.getText().trim();
	    lastName = lastNameField.getText().trim();
	    if (firstName.isEmpty() || lastName.isEmpty()) {
	    	alert.setText("First name and last name required");
	    	alert.setFill(Color.RED);
	    	return false;
	    } 
	    
	    // Attempt to parse the date of birth field to ensure it follows the correct format
	    try {
	    	LocalDate.parse(dobField.getEditor().getText(), DateTimeFormatter.ofPattern("M/d/yyyy"));
	    	dob = dobField.getValue();	
	    } catch (Exception e) {
	    	alert.setText("Invalid date format. Use MM/DD/YYYY.");
	        alert.setFill(Color.RED);
	        return false;
	    }

	    // Check for valid date of birth
	    if (dob == null || dob.isAfter(LocalDate.now())) {
	        alert.setText("Please enter a valid date of birth.");
	        alert.setFill(Color.RED);
	        return false;
	    }
	   
	    // Normalize the first and last name's capitalization
	    firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
	    lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
	    return true;
	}
}
