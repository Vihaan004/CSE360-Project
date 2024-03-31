package application;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

	public void showStartupPage() {
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(50);
		grid.setVgap(10);
		
		// application logo
		Image logo = new Image(getClass().getResourceAsStream("/images/logo.jpg"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(300);
        logoView.setFitHeight(300);
        logoView.setPreserveRatio(true);
		grid.add(logoView, 0, 0);
		
		// user type buttons
		Button patient = new Button("Patient");
		patient.setMinSize(100, 50);
		grid.add(patient, 1, 0);
		patient.setOnAction(e -> {
			userType = "patient";
			showUserLoginPage();
		});
		
		Button nurse = new Button("Nurse");
		nurse.setMinSize(100, 50);
		grid.add(nurse, 1, 1);
		nurse.setOnAction(e -> {
			userType = "nurse";
			showUserLoginPage();
		});
		
		Button doctor = new Button("Doctor");
		doctor.setMinSize(100, 50);
		grid.add(doctor, 1, 2);
		doctor.setOnAction(e -> {
			userType = "doctor";
			showUserLoginPage();
		});
		
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
