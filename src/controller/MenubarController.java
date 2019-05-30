package controller;

import java.util.HashMap;

import View.Menubar;
import View.MyScene;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

	private HashMap<RadioMenuItem, GameController> gamepanes = new HashMap<>();

	public MenubarController(MyScene scene, InlogController controller, Player player) {

		this.scene = scene;
		this.inlog = controller;
		this.self = player;
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
		g.buildRounds();
//		g.buildTurns();
		Menu m = new Menu("Gamenummer : " + gc.getIdGame());
		RadioMenuItem mi = new RadioMenuItem("open game");
		m.getItems().add(mi);
		menu.addGameItem(m);
		gamepanes.put(mi, gc);
		mi.setOnAction(e -> setRoot(mi));
		

	}

	public void setRoot(RadioMenuItem mi) {
		gc = gamepanes.get(mi);
		if (!mi.isDisable())
			if (!gc.getGame().hasChosen()) {
				if (gc.getGame().checkIfIPickedPatternCard(self.getUsername())) {
					this.showWait();
				} else {
					// Set selectPatterncardpane
					scene.setRoot(gc.buildPatterncardoptions());
				}
			} else {
				gc.buildGame();
				scene.setRoot(new VBox(this.getMenubar(), gamepanes.get(mi).getGamepane()));
				for(RadioMenuItem r : gamepanes.keySet()) {
					r.setDisable(false);
				}
				mi.setDisable(true);
				
			
		}else {
			gc.buildGame();
			scene.setRoot(new VBox(this.getMenubar(),gamepanes.get(mi).getGamepane()));
			
		}
	}

	public void showWait() {
		alert.setHeaderText("Er worden nog patroonkaarten gekozen");
		alert.showAndWait();
	}
}
