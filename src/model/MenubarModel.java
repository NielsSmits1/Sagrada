package model;

import View.InlogPane;
import View.MyScene;
import controller.MenubarController;
import javafx.application.Platform;

public class MenubarModel {
	private MenubarController controller;

	private MyScene scene;
	

	public MenubarModel() {

	}

	public void Exit() {
		Platform.exit();
	}

	public void logout() {
		scene = new MyScene();
		scene.setRoot(new InlogPane(scene));
	}
}
