package controller;

import java.util.ArrayList;

import View.HomePane;
import View.MyScene;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import model.Player;

public class HomeController {
	private HomePane home;
	private SearchPlayerController sp;
	private ChallengerController cpp;
	private ChallengesController cp;
	private LeaderboardController lc;
	private MyScene scene;



	private Player player;
	private Player self;


	private Alert alert = new Alert(AlertType.INFORMATION);

	public HomeController(Player self) {
		this.self = self;
		//pc = new PlayerController(self.getUsername());
		cpp = new ChallengerController(this);
		cp = new ChallengesController(this);
		lc = new LeaderboardController(this);
		sp = new SearchPlayerController(this, cpp);
		
		home = new HomePane(sp.getSearchPlayerPane(), cpp.getChallengerPane(), cp.getChallengesPane(), lc.getLeaderboardPane());
		
		
		home.getPlayers().setOnAction(e -> lc.setPlayers1());
		home.getPlayersPlayed().setOnAction(e -> lc.setPlayers2());
		home.getPlayersWins().setOnAction(e -> lc.setPlayers3());

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

	public boolean isInGame(String username, Player self) {
		String u;
		this.player = new Player(username);
		for (ArrayList<Object> a : self.checkPlayerInGame()) {
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



	public Parent showHome() {

		return home;
	}
//	public void showHomePane() {
//		home.createHomePane(pc, scene);
//	}

	public HomePane getHome() {
		return home;
	}
	
	

}
