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

public class UserLogin extends Portal{

	//	private Stage stage;
	private Scene startupScene;
	private Scene loginScene;
	private String userType;
	private TextField firstName;
	private TextField lastName;
	private DatePicker dob;
	
	UserLogin(Stage stage, int w, int h) {
		// setting universal vars
		super(stage, w, h);
	}
	
	// startup page
	public void startup() {
		startupScene = createStartupScene();
		stage.setScene(startupScene);
	    stage.setTitle("AutoPed");
	    stage.show();
	}
	
	// login page
	public void loginPage() {
		loginScene = createLoginScene();
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
	        userType = "patient";
	        navigate();
	    });

	    Button nurseButton = new Button("Nurse");
	    nurseButton.setMinSize(100, 50);
	    nurseButton.setOnAction(e -> {
	        userType = "nurse";
	        navigate();
	    });

	    Button doctorButton = new Button("Doctor");
	    doctorButton.setMinSize(100, 50);
	    doctorButton.setOnAction(e -> {
	        userType = "doctor";
	        navigate();
	    });

	    buttonBox.getChildren().addAll(patientButton, nurseButton, doctorButton);
	    // Adding buttons to the grid
	    grid.add(buttonBox, 3, 0);

	    // Scene setup
	    Scene startupScene = new Scene(grid, width, height);
		startupScene.getRoot().requestFocus();
		return startupScene;
	    
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
	    	login(alert, true); // login(alert, newAccount)
	    });
	    
	    Button loginButton = new Button("Sign In");
	    loginButton.setAlignment(Pos.CENTER_RIGHT);
	    // login functionality, authentication
	    loginButton.setOnAction(e -> {
	    	login(alert, false); // login(alert, newAccount)
	    });
	    
	   
//	    Region spacer = new Region();
//	    HBox.setHgrow(spacer, Priority.ALWAYS); 
	    
	    buttonBox.getChildren().addAll(backButton, signupButton, loginButton);
	    
	    VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));
	    
	    
	    // adding login form to the grid
	    loginBox.getChildren().addAll(firstNameBox, lastNameBox, dateBox, buttonBox, alert);
	    grid.add(loginBox, 2, 0);
		
	    // scene setup
        Scene loginScene = new Scene(grid, width, height);
	    loginScene.getRoot().requestFocus();
	    
	    return loginScene;
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
	
	private void navigate() {
		
		switch (userType) {
		case "patient" :
			loginPage();
			break;
		case "nurse" :
	        NursePortal nursePortal = new NursePortal(stage);
	        nursePortal.accessPortal();
		case "doctor" :
	        DoctorPortal doctorPortal = new DoctorPortal(stage);
	        doctorPortal.accessPortal();
		}
	}
	
	private void login(Text alert, boolean newAccount) {
		
		// check for exisiting account
		
		
		
		Patient patient = new Patient(firstName.getText(), lastName.getText(), dob.getValue());
		if (newAccount) { patient.saveAccount(); }
		patient.openPortal(stage);
		
	}
	
}
