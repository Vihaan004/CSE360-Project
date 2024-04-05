package application;

import java.time.LocalDate;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class User {
	String firstName, lastName;
	LocalDate dateOfBirth;
//	Stage stage;
	
	User(String firstName, String lastName, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	
	// add account to db
	public void saveAccount() {
		
	}
	
	
	// navigate page
	public void openPortal(Stage stage) {
		
	}
}
