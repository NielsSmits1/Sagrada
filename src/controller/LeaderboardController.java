package controller;

import java.util.Map;

import View.LeaderboardPane;
import model.Leaderboard;

public class LeaderboardController {

	private Leaderboard leaderboard;
	private HomeController home;
	private LeaderboardPane leaderboardPane;

	public LeaderboardController(HomeController home) {
		this.home = home;
//		System.out.println(home.getUsername());
		leaderboard = new Leaderboard();
		leaderboardPane = new LeaderboardPane();
		this.setPlayers1();

	}

//	public ArrayList<String> getPlayers() {
//		ArrayList<String> playerNames = new ArrayList<String>();
//		String u;
//
//		for (ArrayList<Object> a : leaderboard.getListOfUsernames()) {
//			u = (String) a.get(0);
//			playerNames.add(u);
//		
//			} 
//		return playerNames;
//	}
	public void setPlayers3() {
		leaderboardPane.setPlayersName();
		for (Map.Entry playerGamesPlayedWon : leaderboard.getPlayersFilteredByAmountOfGamesWon().entrySet()) {
			leaderboardPane.addPlayerNameLineWithAmountOfGamesWon(playerGamesPlayedWon.getKey().toString(),
					(int) playerGamesPlayedWon.getValue());
			
		}
		leaderboardPane.setLayout();

	}
	public void setPlayers2() {
		leaderboardPane.setPlayersName();
		for (Map.Entry<String, String> playerGamesPlayed : leaderboard.getPlayersFilteredByAmountOfGames().entrySet()) {
			leaderboardPane.addPlayerNameLineWithAmountOfGamesPlayed(playerGamesPlayed.getKey().toString(),
					playerGamesPlayed.getValue().toString());
		}
//		leaderboardPane.setPlayersPlayed();
		leaderboardPane.setLayout();
		
	}

	public void setPlayers1() {
		leaderboardPane.setPlayersName();
		for (String playerName : leaderboard.getPlayers()) {
			leaderboardPane.addPlayerNameLine(playerName);
		}
		leaderboardPane.setLayout();
		System.out.println("at least this works");
	}
	public LeaderboardPane getLeaderboardPane() {
		LeaderboardPane lp = leaderboardPane;
		return lp;
	}
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
//	public LinkedHashMap<String, String> getPlayersFilteredByAmountOfGames() {
//		LinkedHashMap<String, String> PlayerNamesWithGames = new LinkedHashMap<String, String>();
//		String u;
//		String s;
//
//		for (ArrayList<Object> a : leaderboard.getListOfUsernamesWithAmountOfGamesPlayed()) {
//			u = (String) a.get(0);
//			s = String.valueOf(a.get(1));
////			System.out.println(u + s); klopt
//			PlayerNamesWithGames.put(u, s);
//		}
//		return PlayerNamesWithGames;
//	}
//	public LinkedHashMap<String, Integer> getPlayersFilteredByAmountOfGamesWon() {
//		LinkedHashMap<String, Integer> PlayerNamesWithGamesWon = new LinkedHashMap<String, Integer>();
//		String u;
//		int s;
//
//		for (ArrayList<Object> a : leaderboard.getListOfUsernamesWithAmountOfGamesWon()) {
//			u = (String) a.get(0);
//			s = (int)a.get(1);
//			s = ((Number)a.get(1)).intValue();
//			PlayerNamesWithGamesWon.put(u, s);
//		}
//		return PlayerNamesWithGamesWon;
//	}

