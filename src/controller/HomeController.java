package controller;

import View.*;
import java.util.ArrayList;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Player;

public class HomeController {
	private HomePane home;
	private SearchPlayerController sp;
	private ChallengerController cpp;
	private ChallengesController cp;
	private Player player;
	private Player self;

	private Alert alert = new Alert(AlertType.INFORMATION);

	public HomeController(MyScene scene, Player self) {
		this.self = self;

		sp = new SearchPlayerController();
		cpp = new ChallengerController();
		cp = new ChallengesController(this);

		home = new HomePane(sp.getSearchPlayerPane(this), cpp.getChallengerPane(), cp.getChallengesPane());

		home.getSearch().getStats().setOnAction(e -> showStatsPlayer());

	}

	public HomeController(PlayerController self2) {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return self.getUsername();
	}

	public void buildPlayer(String u) {
		player = new Player(u);
	}

	public void buildPlayer(String u, String pw) {
		player = new Player(u, pw);
	}

	public boolean usernameExist(String u) {
		buildPlayer(u);
		if (player.checkUsername().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isInGame(String username, PlayerController self) {
		String u;
		this.player = new Player(username);
		for (ArrayList<Object> a : self.getPlayer().checkPlayerInGame()) {
			u = (String) a.get(0);
			if (u.equals(username)) {
				return true;
			}
		}
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getSelf() {
		return this.self;
	}

	public String getStats() {
		String stats = "Aantal gewonnen en verloren potjes: " + self.getTimesWon() + " : " + self.getTimesLost()
				+ "\nHoogst behaalde score: " + self.getHighScore() + "\nMeest geplaatste dobbelsteenkleur: "
				+ self.getMostPlacedDiceColor() + "\nMeest geplaatste dobbelsteenwaarde: "
				+ self.getMostPlacedDiceEyes() + "\nAantal verschillende tegenstanders waartegen gespeeld is: "
				+ self.getAmountOfUniquePlayers();
		return stats;
	}

	public String getStatsPlayer() {
//		player.setDifferendPlayer(username);
		String stats = "Aantal gewonnen en verloren potjes: " + player.getTimesWon() + " : " + player.getTimesLost()
				+ "\nHoogst behaalde score: " + player.getHighScore() + "\nMeest geplaatste dobbelsteenkleur: "
				+ player.getMostPlacedDiceColor() + "\nMeest geplaatste dobbelsteenwaarde: "
				+ player.getMostPlacedDiceEyes() + "\nAantal verschillende tegenstanders waartegen gespeeld is: "
				+ player.getAmountOfUniquePlayers();
		return stats;
	}

	public void showStatsPlayer() {
		alert.setHeaderText(getStatsPlayer());

		alert.showAndWait();
	}

	public Parent showHome() {

		return home;
	}

	public HomePane getHome() {
		return home;
	}

}
