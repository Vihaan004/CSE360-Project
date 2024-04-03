package application;

import java.time.LocalDate;

public class Doctor extends User {
	
	Doctor(String firstName, String lastName, LocalDate dob) {
		super(firstName, lastName, dob);
	}
}
