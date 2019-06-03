package controller;

import java.sql.SQLException;
import java.util.HashMap;

import View.Menubar;
import View.MyScene;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Game;
import model.Player;

public class MenubarController {

	private Menubar menu;
	private MyScene scene;
	private InlogController inlog;
	private Player self;
	private Alert alert = new Alert(AlertType.INFORMATION);
	private GameController gc;
	private Stage stageChat;
	private Scene sceneChat;
	private ChatBoxController chat;
	private HashMap<RadioMenuItem, GameController> gamepanes = new HashMap<>();

	public MenubarController(MyScene scene, InlogController controller, Player player) {

		this.scene = scene;
		this.inlog = controller;
		this.self = player;
		menu = new Menubar(scene);

		menu.getExit().setOnAction(e -> {
			exit();
			try {
				gc.getGame().getDatabase().getCon().close();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		});

		menu.getLogout().setOnAction(e -> {
			logOut();
			try {
				gc.getGame().getDatabase().getCon().close();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		});

		menu.getHelp().setOnAction(e -> menu.getRules().createStage1());
		menu.getHome().setOnAction(e -> controller.buildHome());
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
		gc = new GameController(g);
		chat = gc.getChatBox();
		g.buildRounds();
		g.buildTurns();

		Menu m = new Menu("Gamenummer : " + gc.getIdGame());

		MenuItem c = new MenuItem("chatbox");
		RadioMenuItem mi = new RadioMenuItem("open game");

		m.getItems().addAll(mi, c);
		menu.addGameItem(m);
		gamepanes.put(mi, gc);

		mi.setOnAction(e -> setRoot(mi));
		c.setOnAction(e -> {
			gc.getGamePane().setGameId(gc.getIdGame());
			gc.getChatBox().getModel().setGameId(gc.getGamePane().getGameId());
			gc.getChatBox().getModel().setPlayerId(gc.getOwnId());
			builtChatBox();

		});

	}

	public void builtChatBox() {
		stageChat = new Stage();
		sceneChat = new Scene(new Pane(gc.getChatBox().getScreen()));
		builtScene();

	}

	public void builtScene() {
		stageChat.setScene(sceneChat);
		stageChat.show();
		stageChat.show();
	}

	public void setRoot(RadioMenuItem mi) {
		gc = gamepanes.get(mi);
		if (!mi.isDisable())
			if (!gc.getGame().hasChosen()) {
				if (gc.getGame().checkIfIPickedPatternCard(self.getUsername())) {
					this.showWait();
				} else {
					// Set selectPatterncardpane
					scene.setRoot(new VBox(this.getMenubar(), gc.buildPatterncardoptions()));
				}
			} else {
				gc.buildGame();
				scene.setRoot(new VBox(this.getMenubar(), gamepanes.get(mi).getGamePane()));
				for (RadioMenuItem r : gamepanes.keySet()) {
					r.setDisable(false);
				}
				mi.setDisable(true);

			}
		else {
			gc.buildGame();
			scene.setRoot(new VBox(this.getMenubar(), gamepanes.get(mi).getGamePane()));

		}
	}

	public void showWait() {
		alert.setHeaderText("Er worden nog patroonkaarten gekozen");
		alert.showAndWait();
	}

	public void goHome() {

	}
}
