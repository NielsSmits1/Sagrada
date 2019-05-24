package controller;

import java.util.ArrayList;

import View.ChallengesPane;
import View.ChallengesPlayerLinePane;
import model.Challenge;
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

		challengesPane = new ChallengesPane();
//		challengesPane.copyArraylist(challengesPL);
//		challengesPane.getAcceptButton().setOnAction(e -> acceptChallenge());
		Thread t1 = new multiThreads(this, 1000L);
		t1.start();
		refresh();

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
		/**
		 * challengerlinepane array =- new array
		 * clpa.bgetaccept9).setonaction()
		 * loop voorbij
		 * dan geef jij die aan je challengerpane
		 * en die challengerpane voegt em toe
		 * challengerpane.getchildren.addall
		 */
		challengesPL = new ArrayList<ChallengesPlayerLinePane>();
		
		for (Player a: challenge.playersChallengedYou()) {
			ChallengesPlayerLinePane p = new ChallengesPlayerLinePane(a.getUsername());

			p.getAccept().setOnAction(e -> setPlayerStatusToAccepted(a));
			p.getDecline().setOnAction(e -> setPlayerStatusToDeclined(a));
			challengesPL.add(p);
//			challengesPane.addChallengesLine(a.getUsername());
//			challengesPane.addChallengesLine(a.getUsername());
//			p.getAccept().setOnAction(arg0);
//			p.getDecline().setOnAction(arg0);
//			challenges.add(p);
			/*
			challengesPane.addChallengesLine(a);
			challengesPane.setButtonzooi()*/
		}
//		challengesPane.setLayout();
//		challengesPane.setPlayerLines(challenges);
//		challengesPane.setLayout();
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
		challengesPane.setLayout();
		
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


