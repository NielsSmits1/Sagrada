package controller;

import java.util.ArrayList;


import View.HomePane;
import View.MyScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.util.Duration;
import model.Game;
import model.Player;

public class HomeController {
	private HomePane home;
	private SearchPlayerController sp;
	private ChallengerController cpp;
	private ChallengesController cp;
	private LeaderboardController lc;
	private MyScene scene;

	private MenubarController mbc;

	private Player player;
	private Player self;

	private Game game;
	private Game lastg;

	public HomeController(Player self, MenubarController mbc) {
		
		this.mbc = mbc;
		this.self = self;
		cpp = new ChallengerController(this);
		cp = new ChallengesController(this);
		lc = new LeaderboardController(this);
		sp = new SearchPlayerController(this, cpp);

		home = new HomePane(sp.getSearchPlayerPane(), cpp.getChallengerPane(), cp.getChallengesPane(),
				lc.getLeaderboardPane());
		
		
		home.getPlayers().setOnAction(e -> lc.setPlayers1());
		home.getPlayersPlayed().setOnAction(e -> lc.setPlayers2());
		home.getPlayersWins().setOnAction(e -> lc.setPlayers3());
		home.getAllGames().setOnAction(e -> lc.setGames1());
		home.getAllGamesDate().setOnAction(e -> lc.setGames2());
		
		
		
		openGames();
		startTimeline();

	}

	private void startTimeline() {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(timeline.INDEFINITE);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10000), e -> refresh()));

		timeline.play();
	}

	private void refresh() {
		try {
		cpp.refresh();
		cp.refresh();
		self.checkChallenger();}
		catch(Exception e) {
			
		}

	}
	
	private Game openGames() {
		// open the games that are being played, or are ready to be played
		for (Game g : self.getOpenGames()) {
			mbc.addGame(g);
			game = g;
			this.lastg = g;
			
		}
		return game;
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

	public MyScene getScene() {
		return scene;
	}

	public Game getGame() {
		return game;
	}
	
	

}
