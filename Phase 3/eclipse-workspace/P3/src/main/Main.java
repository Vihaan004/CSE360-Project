package main;
	
import application.UserLogin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import view.LoginView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	int width = 1000;
	int height = 800;
	
	public void start(Stage primaryStage) {
		StartView loginPage = new LoginView(primaryStage, width, height);
		loginPage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}