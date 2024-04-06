package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	int width = 1000;
	int height = 800;
	
	public void start(Stage primaryStage) {
		Controller control = new Controller(primaryStage, width, height);
		control.appStart();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}