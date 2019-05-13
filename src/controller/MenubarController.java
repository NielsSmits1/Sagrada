package controller;


import View.Menubar;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class MenubarController {

	private Menubar menu;
	private Pane pane;
	private MyScene scene;
	private InlogController inlogController;

	public MenubarController(MyScene scene, InlogController controller) {
		this.scene = scene;
		this.inlogController = controller;
		menu = new Menubar();
		menu.getExit().setOnAction(e -> exit());
		menu.getLogout().setOnAction(e -> logOut());
	}

	public Menubar getMenubar() {

		return menu;

	}
	
	 public void exit(){
		 Platform.exit();
	 }
	 
	 public Pane logOut() {
		 scene.setRoot(inlogController.getInlog());
		 return pane;
	 }
	

}
