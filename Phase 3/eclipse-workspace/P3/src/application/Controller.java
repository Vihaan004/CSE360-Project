// CSE 360 Group Project
// Team 40 - Tuesday 1:30pm
package application;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

// Manages the application's navigation and window management.
public class Controller {
    
    Stage stage; // Primary application window.
    int width, height; // Window dimensions.
    
    // View components of the application.
    LoginView loginView;
    NurseView nurseView;
    DoctorView doctorView;
    PatientView patientView;
    
    // Initializes controller with stage and dimensions.
    Controller(Stage stage, int width, int height) {
        this.stage = stage;
        this.width = width;
        this.height = height;
    }
    
    // Starts the application by showing the login view.
    public void appStart() {
        loginView = new LoginView(stage, this, width, height);
        loginView.show();
    }
    
    // Displays the nurse view.
    public void showNurseView() {
        nurseView = new NurseView(stage, this, width, height);
        nurseView.show();
    }
    
    // Displays the doctor view.
    public void showDoctorView() {
        doctorView = new DoctorView(stage, this, width, height);
        doctorView.show();
    }
    
    // Displays the patient view for a specific patient.
    public void showPatientView(Patient patient) {
        patientView = new PatientView(stage, this, width, height);
        patientView.show(patient);
    }
    
    // Shows a modal popup with the specified scene and title.
    public void popup(Stage parentStage, Scene scene, String title) {
        Stage popup = new Stage();
        popup.initOwner(parentStage);
        popup.initModality(Modality.WINDOW_MODAL);
        popup.setTitle(title);
        popup.setScene(scene);
        popup.show();
    }
}
