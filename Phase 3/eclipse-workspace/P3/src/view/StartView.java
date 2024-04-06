package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartView extends ViewBase{
	
	public StartView(Stage stage, int width, int height) {
		super(stage, width, height);
		initView();
	}

	protected void initView() {
		
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
	
	// create common logo section with specified prompt
		private VBox createLogoBox(String promptText) {
			VBox logoBox = new VBox(10);
		    logoBox.setAlignment(Pos.CENTER);
		    // Application logo 
		    Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
		    ImageView logoView = new ImageView(logo);
		    logoView.setFitWidth(200);
		    logoView.setPreserveRatio(true);

//		    Text header = new Text("Welcome to AutoPed");
//		    header.setFont(Font.font("verdana", 20)); // Set font name and size
		    
		    // prompt
		    Text prompt = new Text(promptText);
		    prompt.setFont(Font.font("verdana", 14)); // Set font name and size
		    
		    logoBox.getChildren().addAll(logoView, prompt); // add header?
		    return logoBox;
		}
		
}
