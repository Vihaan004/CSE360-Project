package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class NurseView {
    private Stage stage;
    private Controller control;
    private int width, height;
    private Scene nurseScene;

    public NurseView(Stage stage, Controller control, int width, int height) {
        this.stage = stage;
        this.control = control;
        this.width = width;
        this.height = height;
        nurseScene = createPortalScene();
    }

    public void show() {
        stage.setScene(nurseScene);
        stage.show();
    }

    private Scene createPortalScene() {
        Text heading = new Text("Nurse View");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 34));
        heading.setTextAlignment(TextAlignment.CENTER);
        
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
        
     // Dropdown menu for Weight with options from 80 to 200lbs
        ComboBox<String> weightDropdown = new ComboBox<>();
        for (int i = 50; i <= 260; i=i+5) {
            weightDropdown.getItems().add(i + " lbs");
        }
        weightDropdown.setPromptText("Weight(lbs)");

        // Dropdown menu for Height with options from 110 to 210cm
        ComboBox<String> heightDropdown = new ComboBox<>();
        for (int i = 80; i <= 220; i=i+5) {
            heightDropdown.getItems().add(i + " cm");
        }
        heightDropdown.setPromptText("Height(cms)");

        // Dropdown menu for Temperature with options from 90 to 110F
        ComboBox<String> temperatureDropdown = new ComboBox<>();
        for (int i = 90; i <= 110; i++) {
            temperatureDropdown.getItems().add(i + " F");
        }
        temperatureDropdown.setPromptText("Temperature(F)");

        // Dropdown menu for Blood Pressure with 10 more options
        ComboBox<String> bloodPressureDropdown = new ComboBox<>(FXCollections.observableArrayList(
            "120/80", "130/85", "140/90", "150/95", "160/100", 
            "170/105", "180/110", "190/115", "200/120", "210/125", "220/130"));
        bloodPressureDropdown.setPromptText("Blood Pressure");

        // Dropdown menu for Age with options from 12 to 80
        ComboBox<String> ageDropdown = new ComboBox<>();
        for (int i = 12; i <= 80; i++) {
            ageDropdown.getItems().add(Integer.toString(i));
        }
        ageDropdown.setPromptText("Age");


        // Set preferred width for dropdowns
        weightDropdown.setPrefWidth(200);
        heightDropdown.setPrefWidth(200);
        temperatureDropdown.setPrefWidth(200);
        bloodPressureDropdown.setPrefWidth(200);
        ageDropdown.setPrefWidth(200);

        // Horizontal box for dropdown menus
        HBox dropdownMenus = new HBox(20);
        dropdownMenus.getChildren().addAll(weightDropdown, heightDropdown, temperatureDropdown, bloodPressureDropdown, ageDropdown);
        dropdownMenus.setAlignment(Pos.CENTER); 

     // Text areas for medical history with increased size
        TextArea allergiesField = new TextArea();
        allergiesField.setPromptText("Allergies");
        allergiesField.setPrefSize(400, 80); // Set preferred size

        TextArea previousHealthIssueField = new TextArea();
        previousHealthIssueField.setPromptText("Previous Health Issue");
        previousHealthIssueField.setPrefSize(400, 80);

        TextArea previousPrescriptionField = new TextArea();
        previousPrescriptionField.setPromptText("Previous Prescription");
        previousPrescriptionField.setPrefSize(400, 80);

        TextArea immunizationHistoryField = new TextArea();
        immunizationHistoryField.setPromptText("Immunization History");
        immunizationHistoryField.setPrefSize(400, 80);


        // GridPane for 2x2 text fields
        GridPane textFieldGrid = new GridPane();
        textFieldGrid.setHgap(20);
        textFieldGrid.setVgap(20);
        textFieldGrid.add(allergiesField, 0, 0);
        textFieldGrid.add(previousHealthIssueField, 1, 0);
        textFieldGrid.add(previousPrescriptionField, 0, 1);
        textFieldGrid.add(immunizationHistoryField, 1, 1);
        textFieldGrid.setAlignment(Pos.CENTER);

        Button back = new Button("Back");
        back.setOnAction(e -> backToLogin());

        // Create a new Save button
        Button save = new Button("Save");
        save.setOnAction(e -> {
            // Add action logic for the Save button
        });

        // Horizontal box for Back and Save buttons
        HBox buttonBox = new HBox(10); // 10 is spacing between buttons
        buttonBox.getChildren().addAll(back, save);
        buttonBox.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30, 30, 30, 30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(heading, userIdBox, dropdownMenus, textFieldGrid, buttonBox); // Replace 'back' with 'buttonBox'

        return new Scene(layout, width, height);
    }

    private void backToLogin() {
        control.appStart();
    }
}
