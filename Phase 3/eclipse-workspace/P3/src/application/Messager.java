//package application;
//
//import javafx.geometry.Pos;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Text;
//
//public class Messager {
//	
//	Controller control;
//	Patient patient;
//	Nurse nurse;
//	Doctor doctor;
//	
//	Messager(Patient patient) {
//		control = new Controller();
//		this.patient = patient;
//	}
//	Messager(Nurse nurse) {
//		control = new Controller();
//		this.nurse = nurse;
//	}
//	Messager(Doctor doctor) {
//		control = new Controller();
//		this.doctor = doctor;
//	}
//	
//	
//	public HBox createMessageBox(Object obj) {
//		
//		Label visitLabel = new Label("Visits");
//		
//		ListView<String> messageList = obj.getVisitList();
//		
//		messageList.setOnMouseClicked(e -> {
//		    if (e.getClickCount() == 2 && !messageList.getItems().get(0).equals("You have no visits")) { // Double-click and ensure it's not the placeholder text
//		        String selectedVisit = messageList.getSelectionModel().getSelectedItem();
//		        control.popup(stage, obj.createVisitScene(selectedVisit), "Visit");
//		    }
//		});
//		
//		VBox visitBox = new VBox(visitLabel, obj);
//		
//		HBox messageBox = new HBox(10);
//		messageBox.getChildren().addAll(messageList);
//		messageBox.setAlignment(Pos.CENTER);
//		
//		return messageBox;
//	}
//}
