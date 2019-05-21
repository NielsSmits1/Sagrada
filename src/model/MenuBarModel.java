package model;

import View.InlogPane;
import View.MyScene;
import controller.PlayerController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

public class MenuBarModel {
	
	private Pane pane;
	private Alert alert = new Alert(AlertType.INFORMATION);
	private MyScene scene;
	
	public MenuBarModel(MyScene scene) {
		this.scene = scene;
	}
	
	public void showStats(PlayerController self) {
		alert.setHeaderText(self.getStats());
		// test
		alert.showAndWait();
	}
	
	public void Exit() {
		Platform.exit();
	}
	
	public Pane logOut() {
		pane = new InlogPane(scene);
		return pane;
	}

}
