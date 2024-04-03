// CSE 360 Group Project
// Team 40 - Tuesday 1:30 pm

package application;

import java.io.File;

import javafx.geometry.HPos;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class UserLogin {
	private int width, height;
	private Stage stage;
	private Scene startupScene;
	private Scene loginScene;
	private String userType;
	private TextField firstName;
	private TextField lastName;
	private DatePicker dob;
	
	UserLogin(Stage stage, int w, int h) {
		this.stage = stage;
		width = w;
		height = h;
	}
	
	// startup page
	public void startup() {
		createStartupScene();
		stage.setScene(startupScene);
	    stage.setTitle("AutoPed");
	    stage.show();
	}
	
	// login page
	public void loginPage() {
		createLoginScene();
		stage.setScene(loginScene);
	    stage.show();
	}
	
	// create and set startup scene to current stage
	private void createStartupScene() {
		
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
	        userType = "patient";
	        loginPage();
	    });

	    Button nurseButton = new Button("Nurse");
	    nurseButton.setMinSize(100, 50);
	    nurseButton.setOnAction(e -> {
	        userType = "nurse";
	        loginPage();
	    });

	    Button doctorButton = new Button("Doctor");
	    doctorButton.setMinSize(100, 50);
	    doctorButton.setOnAction(e -> {
	        userType = "doctor";
	        loginPage();
	    });

	    buttonBox.getChildren().addAll(patientButton, nurseButton, doctorButton);
	    // Adding buttons to the grid
	    grid.add(buttonBox, 3, 0);

	    // Scene setup
	    startupScene = new Scene(grid, width, height);
		startupScene.getRoot().requestFocus();
	    
	}

	// create and set login scene to current stage
	private void createLoginScene() {
	
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
	    firstName = new TextField(); 							// class var
//	    firstName.setPromptText("");
	    firstNameBox.getChildren().addAll(firstNameLabel, firstName); 
	    
	    
	    HBox lastNameBox = new HBox(21);
	    lastNameBox.setAlignment(Pos.CENTER_LEFT);
	    Label lastNameLabel = new Label("Last Name:");
	    lastName = new TextField(); 							// class var
//	    lastName.setPromptText("last name");
	    lastNameBox.getChildren().addAll(lastNameLabel, lastName); 
	    
	    
	    HBox dateBox = new HBox(10);
	    dateBox.setAlignment(Pos.CENTER_LEFT);
	    Label dobLabel = new Label("Date of Birth:");
	    dob = new DatePicker();									// class var
	    dob.setPromptText("MM/DD/YYYY");
	    dateBox.getChildren().addAll(dobLabel, dob);
	    
	    Text alert = new Text(userType);
	    alert.setFont(Font.font("verdana", 12));
	    alert.setFill(Color.RED);
	    
	    HBox buttonBox = new HBox(10);
	    buttonBox.setAlignment(Pos.CENTER_LEFT);
	    
	    Button backButton = new Button("Back");
	    backButton.setOnAction(e -> {
	    	startup();
	    });
	    
	    Button signupButton = new Button("Sign Up");
	    signupButton.setOnAction(e -> {
	    	login(alert, userType, false);
	    });
	    
	    Button loginButton = new Button("Sign In");
	    loginButton.setAlignment(Pos.CENTER_RIGHT);
	    // login functionality, authentication
	    loginButton.setOnAction(e -> {
	    	login(alert, userType, true);
	    });
	    
	   
//	    Region spacer = new Region();
//	    HBox.setHgrow(spacer, Priority.ALWAYS); 
	    
	    buttonBox.getChildren().addAll(backButton, signupButton, loginButton);
	    
	    VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));
	    
	    
	    
	    // adding login form to the grid
	    loginBox.getChildren().addAll(firstNameBox, lastNameBox, dateBox, buttonBox, alert);
	    grid.add(loginBox, 2, 0);
		
	    // scene setup
        loginScene = new Scene(grid, width, height);
	    loginScene.getRoot().requestFocus();
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
	
	private void login(Text alert, String userType, boolean accExists) {
		
		// check for exisiting account using User class
		
		
		switch (userType) {
		case "patient":
			Patient patient = new Patient(firstName.getText(), lastName.getText(), dob.getValue());
			if (!accExists) { patient.saveAccount(); }
			patient.openPortal(stage);
			break;
		case "nurse":
			Nurse nurse = new Nurse(firstName.getText(), lastName.getText(), dob.getValue());
			if (!accExists) { nurse.saveAccount(); }
			nurse.openPortal(stage);
			break;
		case "doctor":
			Doctor doctor = new Doctor(firstName.getText(), lastName.getText(), dob.getValue());
			if (!accExists) { doctor.saveAccount(); }
			doctor.openPortal(stage);
			break;
		default:
			System.out.println("SysErr");
			alert.setText("SYSTEM ERROR");
		}
	}
	
}
