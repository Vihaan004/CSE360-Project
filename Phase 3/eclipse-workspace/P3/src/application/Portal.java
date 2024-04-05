package application;

import javafx.stage.Stage;

public class Portal {
	// universal stage, width and height of all portal pages
	Stage stage;
	static int width, height;
	
	Portal(Stage stage) {
		this.stage = stage;
	}
	
	Portal(Stage stage, int w, int h) {
		this.stage = stage;
		width = w;
		height = h;
	}
	
}
