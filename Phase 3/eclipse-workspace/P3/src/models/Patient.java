package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient {
	private String firstName;
	private String lastName;
	private LocalDate birthday;
	private List<Visit> visits = new ArrayList<>();
	
	// constructor
}
