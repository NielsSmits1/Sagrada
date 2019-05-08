package model;

import View.MyScene;
import controller.MenubarController;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MySceneModel {
	
	private MyScene scene;
	private Pane pane;
	
	private MenubarController menucon;	
	
	public MySceneModel(MyScene scene) {
		this.scene = scene;
		menucon = new MenubarController();
	}
	
	public Pane createscene(Pane pane) {
		
		this.pane = pane;
		pane = new VBox(menucon.getMenu(), new VBox(pane));
		return pane;
	}
	
	public Parent switchPane(Pane pane) {
		this.pane = pane;
		createscene(pane);
		return pane;
	}

}
