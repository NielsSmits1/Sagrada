package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Leaderboard;

public class LeaderboardController {

	private Leaderboard leaderboard;
	private HomeController home;

	public LeaderboardController(HomeController home) {
		this.home = home;
		leaderboard = new Leaderboard();
	}

	public ArrayList<String> getPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>();
		String u;

		for (ArrayList<Object> a : leaderboard.getListOfUsernames()) {
			u = (String) a.get(0);
			playerNames.add(u);
		
			} 
		return playerNames;
	}
//	public HashMap<String, String> getPlayersFilteredByWon() {
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
	public HashMap<String, String> getPlayersFilteredByAmountOfGames() {
		HashMap<String, String> PlayerNamesWithGames = new HashMap<String, String>();
		String u;
		String s;

		for (ArrayList<Object> a : leaderboard.getListOfUsernamesWithAmountOfGamesPlayed()) {
			u = (String) a.get(0);
			s = String.valueOf(a.get(1));
			PlayerNamesWithGames.put(u, s);
		}
		return PlayerNamesWithGames;
	}
	public HashMap<String, Integer> getPlayersFilteredByAmountOfGamesWon() {
		HashMap<String, Integer> PlayerNamesWithGamesWon = new HashMap<String, Integer>();
		String u;
		String s1;
		int s;

		for (ArrayList<Object> a : leaderboard.getListOfUsernamesWithAmountOfGamesWon()) {
			u = (String) a.get(0);
			s1 = String.valueOf(a.get(1));
			s = Integer.parseInt(s1);
			PlayerNamesWithGamesWon.put(u, s);
		}
		return PlayerNamesWithGamesWon;
	}
}
