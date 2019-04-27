package model;

import View.InlogPane;
import View.Menubar;
import View.MyScene;
import javafx.application.Platform;

public class MenubarModel {

	private MyScene scene;
	

	public MenubarModel(Menubar menu) {

	}

	public void Exit() {
		Platform.exit();
	}

	public void logout() {
		scene = new MyScene();
		scene.setRoot(new InlogPane(scene));
	}
}
