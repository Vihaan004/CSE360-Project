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
    private Messager messager;
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
		
		dash.add(createHeader(), 0, 0);
		dash.add(createInfoBox(), 0, 1);
		dash.add(messager.createMessageBox(), 0, 3);
		
		
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
		
		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(e -> {
			logout();
		});
		
		HBox header = new HBox(30);
		header.setAlignment(Pos.CENTER);
		header.getChildren().addAll(logoView, id, logoutButton);	
		
		return header;
	}
	
	private HBox createInfoBox() {
		HBox infoBox = new HBox(100);
		infoBox.setAlignment(Pos.CENTER);
		
		// visit section
		Label visitLabel = new Label("Visits");
		
		ListView<String> visitList = patient.getVisitList();
		
		visitList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !visitList.getItems().get(0).equals("You have no visits")) { // Double-click and ensure it's not the placeholder text
		        String selectedVisit = visitList.getSelectionModel().getSelectedItem();
		        control.popup(stage, patient.createVisitScene(selectedVisit), "Visit");
		    }
		});
		
		VBox visitBox = new VBox(visitLabel, visitList);

		
		// prescription section
		Label prescriptionLabel = new Label("Prescription");

		ListView<String> prescriptionList = patient.getPrescriptionList();

		prescriptionList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !prescriptionList.getItems().get(0).equals("You have no prescriptions")) { // Double-click and ensure it's not the placeholder text
		        String selectedPrescription = prescriptionList.getSelectionModel().getSelectedItem();
		        control.popup(stage, patient.createPrescriptionScene(selectedPrescription), "Prescription");
		    }
		});
		
		VBox prescriptionBox = new VBox(prescriptionLabel, prescriptionList);
	
		// combining sections
		infoBox.getChildren().addAll(visitBox, prescriptionBox);
		
		return infoBox;
	}
	
	
	private void logout() {
		control.appStart();
	}
	
	

}
