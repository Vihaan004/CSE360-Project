package application;

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
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
		alert = new Text(" ");
	}
	
	public void show(Patient patient) {
		this.patient = patient;
		patientScene = createPortalScene();
		stage.setScene(patientScene);
		stage.show();
	}
	
	private GridPane createDataGrid() {
		
		
		GridPane dataGrid = new GridPane();
		dataGrid.setHgap(20);
        dataGrid.setVgap(20);
        dataGrid.add(createViewMessageBox(), 0, 0);
        dataGrid.add(createPrescriptionBox(), 1, 0);
        dataGrid.add(createVisitBox(), 2, 0);
        dataGrid.add(createSendMessageBox(), 0, 1);
        dataGrid.add(createHealthHistoryBox(), 1, 1);
        dataGrid.add(createInfoBox(), 2, 1);
        dataGrid.setAlignment(Pos.CENTER);
        
        return dataGrid;
	}
	
	private Scene createPortalScene() {

		VBox layout = new VBox(20);
		layout.setPadding(new Insets(30, 30, 30, 30));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(createHeader(), alert, createDataGrid());
		
		return new Scene(layout, width, height);
	}
	
	
	// header section		
	private HBox createHeader() {	
		
		Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
		ImageView logoView = new ImageView(logo);
		logoView.setFitWidth(75);
	    logoView.setPreserveRatio(true);
    	
	    Text heading = new Text(patient.getName() + " " + patient.getDOB());
        heading.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        heading.setTextAlignment(TextAlignment.CENTER);
        
        Button logoutButton = new Button("Logout");
		logoutButton.setOnAction(e -> {
			logout();
		});
		
		HBox header = new HBox(30);
		header.setAlignment(Pos.CENTER);
		header.getChildren().addAll(logoView, heading, logoutButton);	
		
		return header;
	}
	
	
	private VBox createVisitBox() {
		Label visitLabel = new Label("Visits");
		
		ListView<String> visitList = patient.getVisitList();
        visitList.setPrefSize(300, 200);
		
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
		prescriptionList.setPrefSize(300, 200);
		
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
		contactArea.setPrefSize(300, 55);
		contactArea.setPromptText("Contact information");
		
		Label insuranceLabel = new Label("Insurance");
		
		TextArea insuranceArea = new TextArea();
		insuranceArea.setText(patient.getInsuranceInfo());
		insuranceArea.setPrefSize(300, 55);
		insuranceArea.setPromptText("Insurance ID/information");
		
		Label pharmacyLabel = new Label("Pharmacy");
		
		TextArea pharmacyArea = new TextArea();
		pharmacyArea.setText(patient.getPharmacyInfo());
		pharmacyArea.setPrefSize(300, 55);
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
		
		VBox infoBox = new VBox(contactLabel, contactArea, insuranceLabel, insuranceArea, pharmacyLabel, pharmacyArea, editButton);

		return infoBox;
	}
	
	
	private VBox createHealthHistoryBox() {
		Label healthHistoryLabel = new Label("Health History");
		
		ListView<String> healthHistoryList = patient.getHealthHistoryList();
		healthHistoryList.setPrefSize(300, 200);
		
		healthHistoryList.setOnMouseClicked(e -> {
		    if (e.getClickCount() == 2 && !healthHistoryList.getItems().get(0).equals("You have no health history")) { // Double-click and ensure it's not the placeholder text
		        String selectedHistory = healthHistoryList.getSelectionModel().getSelectedItem();
		        System.out.println(selectedHistory + " opened");
		        control.popup(stage, patient.createVisitScene(selectedHistory), "Record");
		    }
		});
		
		return new VBox(healthHistoryLabel, healthHistoryList);
	}
	
	
	private VBox createViewMessageBox() {
		Label viewMessageLabel = new Label("Messages");
		
		ListView<String> messageList = patient.getMessageList();
		messageList.setPrefSize(300, 200);
		
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
		messageArea.setPrefSize(300,200);
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
		
		VBox messageBox = new VBox(sendMessageLabel, messageArea, sendButton);
		return messageBox;
	}
	
	
	private void logout() {
		alert.setText("");
		control.appStart();
	}
	
	

}
