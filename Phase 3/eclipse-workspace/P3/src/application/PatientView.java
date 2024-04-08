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
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
		dash.setHgap(30);
		dash.setVgap(30);
		dash.setPadding(new Insets(20,20,20,20));
		
		dash.add(createHeader(), 0, 0);
		dash.add(createVisitBox(), 0, 1);
		dash.add(createPrescriptionBox(), 1, 1);
		dash.add(createViewMessageBox(), 0, 2);
		dash.add(createSendMessageBox(), 1, 2);
		
		
		return new Scene(dash, width, height);
	}
	
	
	// header section		
	private HBox createHeader() {
		
		Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
		ImageView logoView = new ImageView(logo);
		logoView.setFitWidth(75);
	    logoView.setPreserveRatio(true);
	    
		Text name = new Text(patient.getName());
		name.setFont(Font.font("verdana", 16));
		Text birthday = new Text(patient.getDOB());
		birthday.setFont(Font.font("verdana", 16));
		VBox idBox = new VBox(name, birthday);
		idBox.setAlignment(Pos.CENTER);
		
		Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(e -> {
			logout();
		});
//		logoutButton.setAlignment(Pos.CENTER_RIGHT);
		
		HBox header = new HBox(30);
		header.setAlignment(Pos.CENTER);
		header.getChildren().addAll(logoView, idBox, logoutButton);	
		
		return header;
	}
	
	
	private VBox createVisitBox() {
		Label visitLabel = new Label("Visits");
		
		ListView<String> visitList = patient.getVisitList();
		
		visitList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !visitList.getItems().get(0).equals("You have no visits")) { // Double-click and ensure it's not the placeholder text
		        String selectedVisit = visitList.getSelectionModel().getSelectedItem();
		        control.popup(stage, patient.createVisitScene(selectedVisit), "Visit");
		    }
		});
		
		return new VBox(visitLabel, visitList);
	}
	
	
	private VBox createPrescriptionBox() {
		Label prescriptionLabel = new Label("Prescription");

		ListView<String> prescriptionList = patient.getPrescriptionList();

		prescriptionList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !prescriptionList.getItems().get(0).equals("You have no prescriptions")) { // Double-click and ensure it's not the placeholder text
		        String selectedPrescription = prescriptionList.getSelectionModel().getSelectedItem();
		        control.popup(stage, patient.createPrescriptionScene(selectedPrescription), "Prescription");
		    }
		});
		
		return new VBox(prescriptionLabel, prescriptionList);
	}

	
	private VBox createViewMessageBox() {
		Label viewMessageLabel = new Label("Messages");
		
		ListView<String> messageList = patient.getVisitList();
		
		messageList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !messageList.getItems().get(0).equals("You have no visits")) { // Double-click and ensure it's not the placeholder text
		        String selectedMessage = messageList.getSelectionModel().getSelectedItem();
		        control.popup(stage, patient.createVisitScene(selectedMessage), "Visit");
		    }
		});
		
		return new VBox(viewMessageLabel, messageList);
	}
	
	
	private VBox createSendMessageBox() {
		Label sendMessageLabel = new Label("Send a message");
		
		TextArea messageArea = new TextArea();
		messageArea.setPrefWidth(100);
		messageArea.setPrefHeight(270);
		messageArea.setPromptText("Enter your message here");
		
		Button sendButton = new Button("Send");
		
		return new VBox(sendMessageLabel, messageArea, sendButton);
	}
	
	
	private void logout() {
		control.appStart();
	}
	
	

}
