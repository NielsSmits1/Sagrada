package controller;

import java.util.ArrayList;
import java.util.Map;

import View.LeaderboardPane;
import View.LeaderboardPlayerLinePane;
import model.Leaderboard;

public class LeaderboardController {

	private Leaderboard leaderboard;
	private HomeController home;
	private LeaderboardPane leaderboardPane;
	private ArrayList<LeaderboardPlayerLinePane> lplp;

	public LeaderboardController(HomeController home) {
		this.home = home;
		leaderboard = new Leaderboard();
		leaderboard.setSelf(this.home.getSelf());
		leaderboardPane = new LeaderboardPane();
		this.setPlayers1();

	}

	public void setPlayers3() {
		lplp = new ArrayList<LeaderboardPlayerLinePane>();

		for (Map.Entry playerGamesPlayedWon : leaderboard.getPlayersFilteredByAmountOfGamesWon().entrySet()) {
			LeaderboardPlayerLinePane p = new LeaderboardPlayerLinePane((String) playerGamesPlayedWon.getKey(),
					(Integer) playerGamesPlayedWon.getValue());
			lplp.add(p);
		}
		leaderboardPane.setPlayersName(lplp);

	}

	public void setPlayers2() {
		lplp = new ArrayList<LeaderboardPlayerLinePane>();

		for (Map.Entry<String, String> playerGamesPlayed : leaderboard.getPlayersFilteredByAmountOfGames().entrySet()) {
			LeaderboardPlayerLinePane p = new LeaderboardPlayerLinePane((String) playerGamesPlayed.getKey(),
					(String) playerGamesPlayed.getValue());
			lplp.add(p);
		}
		leaderboardPane.setPlayersName(lplp);

	}

	public void setPlayers1() {
		lplp = new ArrayList<LeaderboardPlayerLinePane>();

		for (String playerName : leaderboard.getPlayers()) {
			LeaderboardPlayerLinePane p = new LeaderboardPlayerLinePane(playerName);
			lplp.add(p);
		}
		leaderboardPane.setPlayersName(lplp);

	}

	public void setGames3() {
		lplp = new ArrayList<LeaderboardPlayerLinePane>();

		for (Integer games : leaderboard.getGames()) {
			LeaderboardPlayerLinePane p = new LeaderboardPlayerLinePane(games);
			lplp.add(p);

			// leaderboardPane.addPlayerNameLine(games);
		}
		leaderboardPane.setPlayersName(lplp);
	}

	public void setGames1() {
		lplp = new ArrayList<LeaderboardPlayerLinePane>();

		for (Map.Entry games : leaderboard.allGames().entrySet()) {
			LeaderboardPlayerLinePane p = new LeaderboardPlayerLinePane((int) games.getKey(),
					(boolean) games.getValue());
			lplp.add(p);

		}
		leaderboardPane.setPlayersName(lplp);
	}

	public void setGames2() {
		lplp = new ArrayList<LeaderboardPlayerLinePane>();

		for (Map.Entry games : leaderboard.allGamesDate().entrySet()) {
			LeaderboardPlayerLinePane p = new LeaderboardPlayerLinePane((int) games.getKey(),
					(boolean) games.getValue());
			lplp.add(p);

		}
		leaderboardPane.setPlayersName(lplp);
	}

	public LeaderboardPane getLeaderboardPane() {
		LeaderboardPane lp = leaderboardPane;
		return lp;
	}
}
