package controller;

import java.util.HashMap;

import View.GamePane;
import View.Menubar;
import View.MyScene;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import model.Game;
import model.Player;

public class MenubarController {

	private Menubar menu;
	private Pane pane;
	private MyScene scene;
	private InlogController inlog;
	private Player self;
	private Alert alert = new Alert(AlertType.INFORMATION);
	private ChatBoxController chat;
	private GameController gc;
	private Menu m;
	private MenuItem mi;

	private HashMap<Menu, GamePane> gamepanes = new HashMap<>();

	public MenubarController(MyScene scene, InlogController controller, Player player) {

		this.scene = scene;
		this.inlog = controller;

//		game = new GameController(scene);
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

	public void showStats() {
		alert.setHeaderText(getStatsSelf());
		alert.showAndWait();
	}

	public String getStatsSelf() {
		String stats = "Aantal gewonnen en verloren potjes: " + self.getTimesWon() + " : " + self.getTimesLost()
				+ "\nHoogst behaalde score: " + self.getHighScore() + "\nMeest geplaatste dobbelsteenkleur: "
				+ self.getMostPlacedDiceColor() + "\nMeest geplaatste dobbelsteenwaarde: "
				+ self.getMostPlacedDiceEyes() + "\nAantal verschillende tegenstanders waartegen gespeeld is: "
				+ self.getAmountOfUniquePlayers();
		return stats;

	}

	public void exit() {
		Platform.exit();
	}

	public void logOut() {
		scene.setRoot(inlog.getInlog());
	}

	public void addGame(Game g) {
		gc = new GameController(g);
		this.m = new Menu("Gamenummer : " + gc.getIdGame());
		this.mi = new MenuItem("open");
		m.getItems().add(mi);
		menu.addGameItem(m);
		refresh(g);

	}

	public void refresh(Game g) {
		if (gamepanes.containsKey(m)) {
			buildMenuItem();

		} else {
	
		addGame(g);
		}
	}
	
	public void buildMenuItem() {
		gc.buildGame();
		gamepanes.put(m, gc.getGamepane());
		mi.setOnAction(e -> setRoot(m, gc.getGamepane()));
	}

	public void setRoot(Menu m, GamePane gp) {
		System.out.println("Game geopend");
		scene.setRoot(gp);
	}

}
