package controller;

import View.Menubar;
import View.MyScene;
import View.ChatBox;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import model.MenuBarModel;

public class MenubarController {

	private Menubar menu;
	private MenuBarModel menuModel;

	private PlayerController self;

	private Pane pane;
	private MyScene scene;
	private InlogController inlogController;
	private PlayerController controller;

	private GameController game;
	
	

	public MenubarController(MyScene scene, InlogController controller, PlayerController player) {

		this.scene = scene;
		this.inlogController = controller;
		this.controller = player;
		
		game = new GameController(scene);
		menu = new Menubar(scene, this.controller);

		menu.getExit().setOnAction(e -> exit());
		menu.getLogout().setOnAction(e -> logOut());
		menu.getHelp().setOnAction(e -> menu.getRules().createStage1());
//		menu.getHelp().setOnAction(e -> game.builtAlertbox());
//		inlogController.getHome().getHome().getGameTab().setOnAction(e -> menu.creatNewTabs());
//		inlogController.getHome().getHome().getGameTab().setOnAction(e ->game.builtGameStage());
		
	}

	public Menubar getMenubar() {

		return menu;

	}

	public void exit() {
		Platform.exit();
	}

	public Pane logOut() {
		scene.setRoot(inlogController.getInlog());
		return pane;
	}

}
