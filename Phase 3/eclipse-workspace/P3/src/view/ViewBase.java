package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewBase {
	// template for stage, width and height of all pages
	protected Stage stage;
	protected int width, height;
	protected Scene scene;
	
	ViewBase (Stage stage, int width, int height) {
		this.stage = stage;
		this.width = width;
		this.height = height;
	}
	
	public void show() {
		stage.setScene(scene);
		stage.setWidth(width);
		stage.setHeight(height);
		stage.show();
	}
	
	
}
