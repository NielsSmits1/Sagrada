package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import View.ChallengesPane;
import View.ChallengesPlayerLinePane;
import javafx.scene.Node;
import javafx.scene.Parent;
import model.Challenge;

public class ChallengesController {
	private ChallengesPane challengesPane;

	private Challenge challenge;
	private HomeController home;

	public ChallengesController(HomeController home) {
		this.home = home;
		challenge = new Challenge(home.getSelf());
		challengesPane = new ChallengesPane();
		setChallengers();

		challengesPane.getAcceptButton().setOnAction(e -> acceptChallenge());

		
	}
	public ChallengesController() {
		challenge = new Challenge(home.getSelf());
		challengesPane = new ChallengesPane();
		setChallengers();
	}

//
//	public void acceptChallenge(String differentPlayer) {
//		System.out.println("Accept");
//		challenge.setChallengerUsername(differentPlayer);
//	}


	public void acceptChallenge() {
		challengesPane.getPlayerName();
		System.out.println("Accept");
		System.out.println(challengesPane.getPlayerName());
//		challenge.changePlayerStatusToAccepted();
		
	}

	public void declineChallenge(String differentPlayer) {
		challenge.setChallengerUsername(differentPlayer);
		challenge.changePlayerStatusToDeclined();
	}

//	public void setChallenges() {
//		home.buildPlayer("Teun");
//	}
	
	public void setChallengers() {
		for (String a: challenge.playersChallengedYou()) {
			challengesPane.addChallengesLine(a);
		}
		challengesPane.setLayout();
	}
	
	public ChallengesPane getChallengesPane() {
		ChallengesPane cp = challengesPane;
		return cp;
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


