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
import javafx.scene.paint.Color;
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
	private Text alert;
	
	PatientView(Stage stage, Controller control, int width, int height) {
		this.stage = stage;
		this.control = control;
		this.width = width;
		this.height = height;
		alert = new Text("");
	}
	
	public void show(Patient patient) {
		this.patient = patient;
		patientScene = createPortalScene();
		stage.setScene(patientScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		
		GridPane dash = new GridPane();
		dash.setAlignment(Pos.CENTER);
		dash.setHgap(30);
		dash.setVgap(30);
		dash.setPadding(new Insets(20,20,20,20));
		
		dash.add(createHeader(), 0, 0);
		dash.add(createVisitBox(), 0, 1);
		dash.add(createPrescriptionBox(), 1, 1);
		dash.add(createInfoBox(), 2, 1, 1, 2);
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
		header.getChildren().addAll(logoView, idBox, logoutButton, alert);	
		
		return header;
	}
	
	
	private VBox createVisitBox() {
		Label visitLabel = new Label("Visits");
		
		ListView<String> visitList = patient.getVisitList();
		
		visitList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !visitList.getItems().get(0).equals("You have no visits")) { // Double-click and ensure it's not the placeholder text
		        String selectedVisit = visitList.getSelectionModel().getSelectedItem();
		        System.out.println(selectedVisit + " opened");
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
		        System.out.println(selectedPrescription + " opened");
		        control.popup(stage, patient.createPrescriptionScene(selectedPrescription), "Prescription");
		    }
		});
		
		return new VBox(prescriptionLabel, prescriptionList);
	}

	
	private VBox createInfoBox() {
		
		Label contactLabel = new Label("Contact");
		
		TextArea contactArea = new TextArea();
		contactArea.setText(patient.getContactInfo());
		contactArea.setPrefSize(200, 300);
		contactArea.setPromptText("Contact information");
		
		Label insuranceLabel = new Label("Insurance");
		
		TextArea insuranceArea = new TextArea();
		insuranceArea.setText(patient.getInsuranceInfo());
		insuranceArea.setPrefSize(200, 300);
		insuranceArea.setPromptText("Insurance ID/information");
		
		Label pharmacyLabel = new Label("Pharmacy");
		
		TextArea pharmacyArea = new TextArea();
		pharmacyArea.setText(patient.getPharmacyInfo());
		pharmacyArea.setPrefSize(200, 300);
	    pharmacyArea.setPromptText("Pharmacy");
		
		Button editButton = new Button("Edit Info");
		editButton.setOnAction(e -> {
			patient.editInfo(contactArea.getText(), insuranceArea.getText(), pharmacyArea.getText());
			alert.setText("Info saved");
			alert.setFill(Color.GREEN);
//			else {
//				alert.setText("Missing information");
//				alert.setFill(Color.RED);
//			}
		});
		
		VBox infoBox = new VBox(20);
		infoBox.getChildren().addAll(contactLabel, contactArea, insuranceLabel, insuranceArea, pharmacyLabel, pharmacyArea, editButton);

		return infoBox;
	}
	
	
	private VBox createViewMessageBox() {
		Label viewMessageLabel = new Label("Messages");
		
		ListView<String> messageList = patient.getMessageList();
		
		messageList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !messageList.getItems().get(0).equals("You have no messages")) { // Double-click and ensure it's not the placeholder text
		        String selectedMessage = messageList.getSelectionModel().getSelectedItem();
		        System.out.println(selectedMessage + " opened");
		        control.popup(stage, patient.createMessageScene(selectedMessage), "Message");
		    }
		});
		
		return new VBox(viewMessageLabel, messageList);
	}
	
	
	private VBox createSendMessageBox() {
		Label sendMessageLabel = new Label("Send a message");
		
		TextArea messageArea = new TextArea();
		messageArea.setPrefWidth(100);
		messageArea.setPrefHeight(300);
		messageArea.setPromptText("Enter your message here");
		
		Button sendButton = new Button("Send");
		sendButton.setOnAction(e -> {
			if(!messageArea.getText().isEmpty()) {
				patient.saveMessage(patient.getName(), messageArea.getText());	
				alert.setText("Message sent");
				alert.setFill(Color.GREEN);
			}
			else {
				alert.setText("Message cannot be empty");
				alert.setFill(Color.RED);
			}
		});
		
		VBox messageBox = new VBox(20);
		messageBox.getChildren().addAll(sendMessageLabel, messageArea, sendButton);
		return messageBox;
	}
	
	
	private void logout() {
		alert.setText("");
		control.appStart();
	}
	
	

}
