package model;

import View.Menubar;
import View.MyScene;
import controller.MenubarController;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MySceneModel {
	
	private MyScene scene;
	private Pane pane;
	
	private MenubarController mencon;
	private Menubar menu;
	private MenubarModel menumodel;
	
	public MySceneModel(MyScene scene) {
		this.scene = scene;
	}
	
	public Pane createscene(Pane pane) {
		menu = new Menubar();
//		menumodel = new MenubarModel(menu);
//		mencon = new MenubarController(menumodel, menu);
		
		this.pane = pane;
		pane = new VBox(menu, new VBox(pane));
		return pane;
	}
	
	public Parent switchPane(Pane pane) {
		this.pane = pane;
		createscene(pane);
		return pane;
	}

}
