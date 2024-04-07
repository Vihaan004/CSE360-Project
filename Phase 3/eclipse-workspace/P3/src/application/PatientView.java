package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PatientView {
	
	private Stage stage;
    private Controller control;
	private int width, height;
	private Scene patientScene;
	private Patient patient;
	
	PatientView(Stage stage, Controller control, int width, int height) {
		this.stage = stage;
		this.control = control;
		this.width = width;
		this.height = height;
	}
	
	public void show(Patient patient) {
		this.patient = patient;
		patientScene = createPortalScene();
		stage.setScene(patientScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		
//		VBox hello = new VBox(10);
		
		GridPane dash = new GridPane();
		dash.setAlignment(Pos.CENTER);
		dash.setHgap(50);
		dash.setVgap(50);
		HBox header = createHeader();
		HBox infoBox = createInfoBox();
		
		dash.add(header, 0, 0);
		dash.add(infoBox, 0, 1);
		
		
		
		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(e -> {
			logout();
		});
		
//		hello.getChildren().addAll(identifier, logoutButton);
		
		return new Scene(dash, width, height);
	}
	
	
	// header section		
	private HBox createHeader() {
		
		Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
		ImageView logoView = new ImageView(logo);
		logoView.setFitWidth(75);
	    logoView.setPreserveRatio(true);
	    
		Text name = new Text(patient.getName());
		Text birthday = new Text(patient.getDOB());
		VBox id = new VBox(name, birthday);
		
		HBox header = new HBox(30);
		header.setAlignment(Pos.CENTER);
		header.getChildren().addAll(logoView, id);	
		
		return header;
	}
	
	private HBox createInfoBox() {
		HBox infoBox = new HBox(100);
		infoBox.setAlignment(Pos.CENTER);
		
		// visit section
		Label visitLabel = new Label("Visits");
		File visitDir = patient.getVisitDir();

		ListView<String> visitList = new ListView<>();
		if (visitDir != null && visitDir.exists() && visitDir.isDirectory()) {
		    File[] visitFiles = visitDir.listFiles();
		    if (visitFiles != null && visitFiles.length > 0) {
		        for (File file : visitFiles) {
		            if (file.isFile()) {
		                visitList.getItems().add(file.getName());
		            }
		        }
		    } else {
		        // Directory exists but no files found
		        visitList.getItems().add("You have no visits");
		    }
		} else {
		    // Directory is null or does not exist
		    visitList.getItems().add("You have no visits");
		}

		visitList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !visitList.getItems().get(0).equals("You have no visits")) { // Double-click and ensure it's not the placeholder text
		        String selectedVisit = visitList.getSelectionModel().getSelectedItem();
		        showVisit(new File(visitDir, selectedVisit), stage);
		    }
		});
		
		VBox visitBox = new VBox(visitLabel, visitList);

		
		// prescription section
		Label prescriptionLabel = new Label("Prescription");
		File prescriptionDir = patient.getPrescriptionDir();

		ListView<String> prescriptionList = new ListView<>();
		if (prescriptionDir != null && prescriptionDir.exists() && prescriptionDir.isDirectory()) {
		    File[] visitFiles = prescriptionDir.listFiles();
		    if (visitFiles != null && visitFiles.length > 0) {
		        for (File file : visitFiles) {
		            if (file.isFile()) {
		                prescriptionList.getItems().add(file.getName());
		            }
		        }
		    } else {
		        // Directory exists but no files found
		        prescriptionList.getItems().add("You have no visits");
		    }
		} else {
		    // Directory is null or does not exist
		    prescriptionList.getItems().add("You have no visits");
		}

		prescriptionList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !prescriptionList.getItems().get(0).equals("You have no visits")) { // Double-click and ensure it's not the placeholder text
		        String selectedVisit = prescriptionList.getSelectionModel().getSelectedItem();
		        showVisit(new File(visitDir, selectedVisit), stage);
		    }
		});
		
		VBox prescriptionBox = new VBox(prescriptionLabel, prescriptionList);
	
		
		infoBox.getChildren().addAll(visitBox, prescriptionBox);
		
		return infoBox;
	}
	
	private void showVisit(File visitDir, Stage parentStage) {
		Stage popup = new Stage();
		popup.initOwner(parentStage);
		popup.initModality(Modality.WINDOW_MODAL);
        popup.setTitle("Visits");	
        
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(visitDir))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Text content = new Text(contentBuilder.toString());
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(content);

        popup.setScene(new Scene(layout, 300, 200));
        popup.show();
	}
	
	private void logout() {
		control.appStart();
	}
	
	

}
