package application;

import java.time.LocalDate;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class User {
	String firstName, lastName;
	LocalDate dateOfBirth;
	Stage stage;
	
	User(String firstName, String lastName, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public void saveAccount() {
		
	}
	
	public void openPortal(Stage stage) {
		
	}
}
