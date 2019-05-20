package controller;

import View.Menubar;
import View.MyScene;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

public class MenubarController {

	private Menubar menu;
	private Pane pane;
	private MyScene scene;
	private InlogController inlogController;
	private PlayerController controller;

	private GameProgress game = new GameProgress();

	public MenubarController(MyScene scene, InlogController controller, PlayerController player) {

		this.scene = scene;
		this.inlogController = controller;
		this.controller = player;
		menu = new Menubar(scene, this.controller);

		menu.getExit().setOnAction(e -> exit());
		menu.getLogout().setOnAction(e -> logOut());
		menu.getHelp().setOnAction(e -> menu.getRules().createStage1());
//		menu.getHelp().setOnAction(e -> game.builtAlertbox());
//		inlogController.getHome().getHome().getGameTab().setOnAction(e -> menu.creatNewTabs());
		inlogController.getHome().getHome().getGameTab().setOnAction(e -> game.builtGameStage());
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
