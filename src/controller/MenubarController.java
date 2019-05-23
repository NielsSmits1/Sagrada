package controller;


import View.Menubar;
import View.MyScene;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import model.Game;
import model.MenuBarModel;
import model.Player;


public class MenubarController {
	
	private Menubar menu;
	//private MenuBarModel menuModel;


	private Pane pane;
	private MyScene scene;
	private InlogController inlogController;
	private HomeController home;
	private Player self;

	private GameController game;

	public MenubarController(MyScene scene, InlogController controller, Player player) {

		this.scene = scene;
		this.inlogController = controller;
		this.self = player;
		game = new GameController(scene);
		menu = new Menubar(scene);

		menu.getExit().setOnAction(e -> exit());
		menu.getLogout().setOnAction(e -> logOut());
		menu.getHelp().setOnAction(e -> menu.getRules().createStage1());
//		menu.getHelp().setOnAction(e -> game.builtAlertbox());
//		inlogController.getHome().getHome().getGameTab().setOnAction(e -> menu.creatNewTabs());
		//inlogController.getHome().getHome().getGameTab().setOnAction(e ->game.builtGameStage());
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

	public void addGame(Game g) {
		GameController gc = new GameController(g);
		gc.buildGame();
		menu.addGameItem(gc.getGameStage(), gc.getIdGame());
		
	}

}
