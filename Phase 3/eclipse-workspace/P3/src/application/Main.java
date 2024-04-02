package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	int width = 1000;
	int height = 800;
	
	public void start(Stage primaryStage) {
		UserLogin app = new UserLogin(primaryStage, width, height);
		app.startup();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}