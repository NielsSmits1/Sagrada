package controller;

import java.util.ArrayList;

import View.HomePane;
import View.MyScene;
import javafx.scene.Parent;
import model.Player;

public class HomeController {
	private HomePane home;
	private SearchPlayerController sp;
	private ChallengerController cpp;
	private ChallengesController cp;
	private LeaderboardController lc;
	private PlayerController pc;
	private MyScene scene;

	private Player player;
	private Player self;

	public HomeController(MyScene scene, Player self) {
		this.self = self;
		this.scene = scene;
		pc = new PlayerController(self.getUsername());
		sp = new SearchPlayerController();
		cpp = new ChallengerController(this);
		cp = new ChallengesController(this);
		lc = new LeaderboardController(this);
		
		home = new HomePane(sp.getSearchPlayerPane(this), cpp.getChallengerPane(), cp.getChallengesPane(), lc.getLeaderboardPane());
		
		
		home.getPlayers().setOnAction(e -> lc.setPlayers1());
		home.getPlayersPlayed().setOnAction(e -> lc.setPlayers2());
		home.getPlayersWins().setOnAction(e -> lc.setPlayers3());
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
		if(player.checkUsername().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isInGame(String username, PlayerController self) {
		String u;
		this.player = new Player(username);
		for(ArrayList<Object> a: self.getPlayer().checkPlayerInGame()) {
			u = (String) a.get(0);
			if(u.equals(username)) {
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
				+ "\nHoogst behaalde score: " + self.getHighScore() 
				+ "\nMeest geplaatste dobbelsteenkleur: " + self.getMostPlacedDiceColor()
				+ "\nMeest geplaatste dobbelsteenwaarde: " + self.getMostPlacedDiceEyes()
				+ "\nAantal verschillende tegenstanders waartegen gespeeld is: " + self.getAmountOfUniquePlayers();
		return stats;
	}
	
	public String getStatsPlayer() {
//		player.setDifferendPlayer(username);
		String stats = "Aantal gewonnen en verloren potjes: " + player.getTimesWon() + " : " + player.getTimesLost()
				+ "\nHoogst behaalde score: " + player.getHighScore() 
				+ "\nMeest geplaatste dobbelsteenkleur: " + player.getMostPlacedDiceColor()
				+ "\nMeest geplaatste dobbelsteenwaarde: " + player.getMostPlacedDiceEyes()
				+ "\nAantal verschillende tegenstanders waartegen gespeeld is: " + player.getAmountOfUniquePlayers();
		return stats;
	}


	public Parent showHome() {
		return home;
	}
//	public void showHomePane() {
//		home.createHomePane(pc, scene);
//	}

}
