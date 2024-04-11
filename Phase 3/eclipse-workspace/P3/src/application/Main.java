package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	int width = 1200;
	int height = 800;
	
	public void start(Stage primaryStage) {
		Controller control = new Controller(primaryStage, width, height);
	    primaryStage.setTitle("AutoPed");
		control.appStart();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}