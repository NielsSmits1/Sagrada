package controller;

import java.util.ArrayList;

import View.ChallengesPane;
import View.ChallengesPlayerLinePane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Challenge;
import model.Game;
import model.Player;

public class ChallengesController {
	private ChallengesPane challengesPane;
	private ArrayList<ChallengesPlayerLinePane> challengesPL;

	private Challenge challenge;
	private HomeController home;
//	private multiThreads multi;

	public ChallengesController(HomeController home) {
		this.home = home;
		
		challenge = new Challenge(home.getSelf());

		Timeline timeline = new Timeline();
		 //timeline.setCycleCount(8);
		 //timeline.setAutoReverse(true);
		 timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), check()));
		 timeline.play();
		challengesPane = new ChallengesPane();
//		challengesPane.copyArraylist(challengesPL);
//		challengesPane.getAcceptButton().setOnAction(e -> acceptChallenge());
		//Thread t1 = new multiThreads(this, 1000L);
		//t1.start();
		refresh();

	}
	private KeyValue check() {
	 System.out.println("uyfghujmjbm");
	return null;
}
	public ChallengesController() {
		challenge = new Challenge(home.getSelf());
		challengesPane = new ChallengesPane();
		setChallengers();
//		challengesPane.copyArraylist(challengesPL);
		challengesPane.setPlayerLine(challengesPL);
	}
//
//	public void acceptChallenge(String differentPlayer) {
//		System.out.println("Accept");
//		challenge.setChallengerUsername(differentPlayer);
//	}
//	public void acceptChallenge() {
//		challengesPane.getPlayerName();
//		System.out.println("Accept");
//		System.out.println(challengesPane.getPlayerName());
////		challenge.changePlayerStatusToAccepted();
//		
//	}
	public void declineChallenge(String differentPlayer) {
		challenge.setChallengerUsername(differentPlayer);
		challenge.changePlayerStatusToDeclined();
	}
//	public void setChallenges() {
//		home.buildPlayer("Teun");
//	}
	public void setChallengers() {
		challengesPL = new ArrayList<ChallengesPlayerLinePane>();
		
		for (Player a: challenge.playersChallengedYou()) {
			ChallengesPlayerLinePane p = new ChallengesPlayerLinePane(a.getUsername());
			p.getAccept().setOnAction(e -> setPlayerStatusToAccepted(a));
			p.getDecline().setOnAction(e -> setPlayerStatusToDeclined(a));
			challengesPL.add(p);
		}
		challengesPane.showPlayerLines(challengesPL);
	}
	
	
	public ChallengesPane getChallengesPane() {
		ChallengesPane cp = challengesPane;
		return cp;
	}
	public void setPlayerStatus(Player challenger) {
		challenge.setChallenger(challenger);
	}
	public void setPlayerStatusToAccepted(Player challenger) {
		setPlayerStatus(challenger);
		challenge.changePlayerStatusToAccepted();
		refresh();
	}
	public void setPlayerStatusToDeclined(Player challenger) {
		setPlayerStatus(challenger);
		challenge.changePlayerStatusToDeclined();
		refresh();
	}
	public void refresh() {
		setChallengers();
		challengesPane.setPlayerLine(challengesPL);
	}
	
	
}
	
	
//				leaderboardPane.setPlayersName();
//				for (Map.Entry<String, String> playerGamesPlayed : leaderboard.getPlayersFilteredByAmountOfGames().entrySet()) {
//					leaderboardPane.addPlayerNameLineWithAmountOfGamesPlayed(playerGamesPlayed.getKey().toString(),
//							playerGamesPlayed.getValue().toString());
//				}
////				leaderboardPane.setPlayersPlayed();
//				leaderboardPane.setLayout();

//	public ArrayList<String> getChallengers() {
//		ArrayList<String> challengedPlayerNames = new ArrayList<String>();
//		String u;
//
//		for (ArrayList<Object> a : challenge.GetPlayerWithChallengedStatus()) {
//			u = (String) a.get(0);
//			System.out.println(u);
//			if (!home.getSelf().getUsername().equals(u)) {
//				challengedPlayerNames.add(u);
//			} 
//		}
//		return challengedPlayerNames;
//	}
//	public HashMap<String, String> getChallenged() {
//		HashMap<String, String> challengedPlayerNames = new HashMap<String, String>();
//		String u;
//		String s;
//
//		for (ArrayList<Object> a : challenge.GetPlayerWithChallengeeStatus()) {
//			u = (String) a.get(1);
//			s = (String) a.get(3);
//			if (!home.getSelf().getUsername().equals(u)) {
//				challengedPlayerNames.put(u, s);
//			} 
//		}
//		return challengedPlayerNames;
//	}


