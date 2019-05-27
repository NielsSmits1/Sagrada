package controller;

import java.util.HashMap;

import View.GamePane;
import View.Menubar;
import View.MyScene;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import model.Game;
import model.MenuBarModel;
import model.Player;

public class MenubarController {

	private Menubar menu;
	private MenuBarModel menuModel;


	private Pane pane;
	private MyScene scene;
	private InlogController inlogController;
	private HomeController home;
	private Player self;

	private GameController game;
	private ChatBoxController chat;
	
	private GameController gc;
	
	
	
	private HashMap <Menu, GamePane> gamepanes = new HashMap<>();

	public MenubarController(MyScene scene, InlogController controller, Player player) {

		this.scene = scene;
		this.inlogController = controller;
		
		game = new GameController(scene);
		menu = new Menubar(scene);

		menu.getExit().setOnAction(e -> exit());
		menu.getLogout().setOnAction(e -> logOut());
		menu.getHelp().setOnAction(e -> menu.getRules().createStage1());
//		menu.getHelp().setOnAction(e -> game.builtAlertbox());
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

	public void addGame(Game g) {
		gc = new GameController(g); 
		Menu m = new Menu("Gamenummer : " + gc.getIdGame());
		menu.addGameItem(m);
		gamepanes.put(m, gc.getGameStage());
		m.setOnShowing(e-> setRoot(m));

	
	}
	
	public void setRoot(Menu m) {
		scene.setRoot(gamepanes.get(m));
	}
	
	
	

}
