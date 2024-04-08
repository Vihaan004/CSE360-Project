package application;

import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;  // Import HBox


public class DoctorView {

	private Stage stage;
	private Controller control;
	private int width, height;
	private Scene doctorScene;

	DoctorView(Stage stage, Controller control, int width, int height) {
		this.stage = stage;
		this.control = control;
		this.width = width;
		this.height = height;
		doctorScene = createPortalScene();
	}

	public void show() {
		stage.setScene(doctorScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
	    // Heading
	    Text heading = new Text("Doctor View");
	    heading.setFont(Font.font("Arial", FontWeight.BOLD, 34)); // Set font and size
	    heading.setTextAlignment(TextAlignment.CENTER); // Center alignment
	    
	 // Label and TextField for User ID
        Label userIdLabel = new Label("User ID: ");
        TextField userIdField = new TextField();
        userIdField.setPrefWidth(200); // Set preferred width

        // New label for the format
        Label formatLabel = new Label("format: firstName_lastName_dd/mm/yy");

        // Vertical box for TextField and format label
        VBox userIdAndFormatBox = new VBox(5); // 5 is spacing between elements
        userIdAndFormatBox.getChildren().addAll(userIdField, formatLabel);

        // Horizontal box for User ID label and VBox
        HBox userIdBox = new HBox(10); // 10 is spacing between elements
        userIdBox.getChildren().addAll(userIdLabel, userIdAndFormatBox);
        userIdBox.setAlignment(Pos.CENTER);

	 // Patient info Text views with increased font size
	    Text weight = new Text("Weight(lbs): xx");
	    weight.setFont(Font.font("Arial", 16));  // Set font size to 16

	    Text height2 = new Text("Height(cm): xx");
	    height2.setFont(Font.font("Arial", 16));  // Set font size to 16

	    Text temperature = new Text("Temperature(F): xx");
	    temperature.setFont(Font.font("Arial", 16));  // Set font size to 16

	    Text bloodPressure = new Text("Blood Pressure: xx/yy");
	    bloodPressure.setFont(Font.font("Arial", 16));  // Set font size to 16

	    Text age = new Text("Age: xx");
	    age.setFont(Font.font("Arial", 16));  // Set font size to 16


	    // Arrange info horizontally
	    HBox patientInfo = new HBox(10); // 10 is spacing between elements
	    patientInfo.setAlignment(Pos.CENTER); // Center alignment for HBox
	    patientInfo.getChildren().addAll(weight, height2, temperature, bloodPressure, age);

	    

	 // GridPane for TextAreas
	    GridPane textAreasGrid = new GridPane();
	    textAreasGrid.setVgap(10); // Vertical gap
	    textAreasGrid.setHgap(10); // Horizontal gap
	    textAreasGrid.setAlignment(Pos.CENTER);

	    // Creating TextAreas
	    TextArea textArea1 = new TextArea();
	    textArea1.setPromptText("Allergies");
        textArea1.setPrefSize(400, 80); // Set preferred size
	    TextArea textArea2 = new TextArea();
	    textArea2.setPromptText("Previous Health issues");
        textArea2.setPrefSize(400, 80); // Set preferred size
	    TextArea textArea3 = new TextArea();
	    textArea3.setPromptText("Previous Prescription");
        textArea3.setPrefSize(400, 80); // Set preferred size
	    TextArea textArea4 = new TextArea();
	    textArea4.setPromptText("Immunization History");
        textArea4.setPrefSize(400, 80); // Set preferred size

	    // Adding TextAreas to the GridPane
	    textAreasGrid.add(textArea1, 0, 0); // Column 0, Row 0
	    textAreasGrid.add(textArea2, 1, 0); // Column 1, Row 0
	    textAreasGrid.add(textArea3, 0, 1); // Column 0, Row 1
	    textAreasGrid.add(textArea4, 1, 1); // Column 1, Row 1
	    
	 // Existing elements
	    Button back = new Button("Back");
	    back.setOnAction(e -> {
	        backToLogin();
	    });
	    
	 // Create a new Save button
        Button save = new Button("Save");
        save.setOnAction(e -> {
            // Add action logic for the Save button
        });

        // Horizontal box for Back and Save buttons
        HBox buttonBox = new HBox(10); // 10 is spacing between buttons
        buttonBox.getChildren().addAll(back, save);
        buttonBox.setAlignment(Pos.CENTER);

	    // VBox and other UI elements
	    VBox layout = new VBox(10); 
	    layout.setAlignment(Pos.CENTER); 
	    layout.getChildren().addAll(heading, userIdBox, patientInfo, textAreasGrid, buttonBox);

	    return new Scene(layout, width, height);
	}
	
	private void backToLogin() {
		control.appStart();
	}


}
