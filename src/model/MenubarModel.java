package model;

import View.InlogPane;
import View.MyScene;

public class MenuBarModel {


	private MyScene scene;

	public MenuBarModel() {
	
	}

	

	public void logout() {
		scene = new MyScene();
		scene.setRoot(new InlogPane(scene));
	}
	
	public void showStats() {
		
	}
}
