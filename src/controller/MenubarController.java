package controller;

import View.Menubar;
import View.MyScene;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.Game;
import model.MenuBarModel;
import model.Player;

public class MenubarController {

	private Menubar menu;
	private MenuBarModel menuModel;
	private Pane pane;
	private MyScene scene;
	private InlogController inlog;
	private Player self;
	private Alert alert = new Alert(AlertType.INFORMATION);

	public MenubarController(MyScene scene, InlogController ic, Player player) {

		this.scene = scene;
		this.self = player;
		this.inlog = ic;
		menu = new Menubar();
		this.inlogController = controller;


		menu.getExit().setOnAction(e -> exit());
		menu.getLogout().setOnAction(e -> logOut());
		menu.getHelp().setOnAction(e -> menu.getRules().createStage1());
		menu.getStats().setOnAction(e -> showStats());
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
		GameController gc = new GameController(g);
		gc.buildGame();
		menu.addGameItem(gc.getGameStage(), gc.getIdGame());
	}

}
