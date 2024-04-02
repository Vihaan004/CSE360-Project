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
import javafx.scene.layout.VBox;
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
	
	UserLogin(Stage stage, int w, int h) {
		this.stage = stage;
		width = w;
		height = h;
	}
	
//	private VBox createLogoBox(String prompt) {
//		
//	}

	public void showStartupPage() {
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
//	    grid.setPadding(new Insets(15, 15, 15, 15));
	    
	    VBox logoBox = new VBox(10);
	    logoBox.setAlignment(Pos.CENTER);
	    // Application logo 
	    Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
	    ImageView logoView = new ImageView(logo);
	    logoView.setFitWidth(200);
	    logoView.setPreserveRatio(true);
//	    grid.add(logoView, 0, 0);

	    Text header = new Text("Welcome to AutoPed");
	    header.setFont(Font.font("verdana", 20)); // Set font name and size
//	    grid.add(header, 0, 1);
	    
	    Text prompt = new Text("Please select your user type to begin.");
	    prompt.setFont(Font.font("verdana", 14)); // Set font name and size
//	    grid.add(sub, 0, 2); 
	    
	    logoBox.getChildren().addAll(logoView, prompt); // add header?
	    
	    
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
//	    GridPane.setConstraints(patientButton, 2, 0);
	    patientButton.setOnAction(e -> {
	        userType = "patient";
	        showUserLoginPage();
	    });

	    Button nurseButton = new Button("Nurse");
	    nurseButton.setMinSize(100, 50);
//	    GridPane.setConstraints(nurseButton, 2, 1); 
	    nurseButton.setOnAction(e -> {
	        userType = "nurse";
	        showUserLoginPage();
	    });

	    Button doctorButton = new Button("Doctor");
	    doctorButton.setMinSize(100, 50);
//	    GridPane.setConstraints(doctorButton, 2, 2);
	    doctorButton.setOnAction(e -> {
	        userType = "doctor";
	        showUserLoginPage();
	    });

	    // Adding buttons to the grid
	    buttonBox.getChildren().addAll(patientButton, nurseButton, doctorButton);
	    
	    grid.add(logoBox, 0, 0);
	    grid.add(buttonBox, 3, 0);

	    // Scene setup
	    startupScene = new Scene(grid, width, height);
	    stage.setScene(startupScene);
	    stage.setTitle("AutoPed");
	    stage.show();
	}

	
	
	private void showUserLoginPage() {
		
	    GridPane grid = new GridPane();
		
        loginScene = new Scene(grid, width, height);
        stage.setScene(loginScene);
        stage.show();
	}
	
}
