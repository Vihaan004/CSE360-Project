package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NurseView {
    private Stage stage;
    private Controller control;
    private int width, height;
    private Scene nurseScene;
    
    // Constructor
    public NurseView(Stage stage, Controller control, int width, int height) {
        this.stage = stage;
        this.control = control;
        this.width = width;
        this.height = height;
        nurseScene = createPortalScene();
        
    }
    public void show() {
		stage.setScene(nurseScene);
		stage.show();
	}
	
	private Scene createPortalScene() {
		Text h = new Text("nurse");
		Button back = new Button("Back");
		back.setOnAction(e -> {
			backToLogin();
		});
		
		VBox hello = new VBox();
		hello.getChildren().addAll(h, back);
		
		return new Scene(hello, width, height);
	}
	
	private void backToLogin() {
		control.appStart();
	}
}
