package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	int width = 1400;
	int height = 900;
	
	public void start(Stage primaryStage) {
		Controller control = new Controller(primaryStage, width, height);
	    primaryStage.setTitle("AutoPed");
		control.appStart();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}