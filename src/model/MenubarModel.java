package model;

import View.InlogPane;
import View.Menubar;
import controller.MenubarController;
import controller.MyScene;
import javafx.application.Platform;

public class MenubarModel {
	private MenubarController controller;

	private MyScene scene;

	
	/*
	 * view mag NOOIT direct communceren met t model
	 * 
	 */

	public MenubarModel() {
		/*
		 * view als ingangspunt
		 * begin = view
		 * bij die view pakken wij een instantie van de controller
		 * en dan vanaf die controller regelen we alles zaken met t model
		 */
	}

	public void Exit() {
		Platform.exit();
	}

	public void logout() {
		scene = new MyScene();
		scene.setRoot(new InlogPane(scene));
	}
}
