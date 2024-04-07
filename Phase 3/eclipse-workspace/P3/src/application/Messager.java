package application;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Messager {
	
	public HBox createMessageBox() {
		
		Text hello = new Text("hello");
		
		HBox messageBox = new HBox(10);
		messageBox.getChildren().addAll(hello);
		messageBox.setAlignment(Pos.CENTER);
		
		return messageBox;
	}
}
