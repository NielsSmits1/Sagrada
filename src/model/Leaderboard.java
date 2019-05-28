package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Database.db;
import View.ChallengesPane;
import View.LeaderboardPane;

public class Leaderboard {
	private Player self;
	private db database = new db();

	public Leaderboard() {

	}

	public ArrayList<ArrayList<Object>> getListOfUsernames() {
		return database.Select("Select * from account order by username ASC");
	}

	public ArrayList<ArrayList<Object>> getListOfUsernamesWithAmountOfGamesPlayed() {

		return database.Select(
				"select username, count(game_idgame) as played_games from player where playstatus_playstatus = 'Uitgespeeld' group by username");
	}

	public ArrayList<ArrayList<Object>> getListOfUsernamesWithAmountOfGamesWon() {
		return database.Select(
				"SELECT p1.username,count(p1.username) as games_won FROM player p1 LEFT JOIN player p2 ON p1.game_idgame = p2.game_idgame AND p1.score < p2.score where p2.score is null AND p1.playstatus_playstatus = 'Uitgespeeld' group by p1.username");

	}

	public ArrayList<String> getPlayers() {
		ArrayList<String> playerNames = new ArrayList<String>();
		String u;

		for (ArrayList<Object> a : this.getListOfUsernames()) {
			u = (String) a.get(0);
			playerNames.add(u);
		}
		return playerNames;
	}

	public ArrayList<Integer> getGames() {
		ArrayList<Integer> games = new ArrayList<Integer>();
		int u;

		for (ArrayList<Object> a : this.getAllGames()) {
			u = (int) a.get(0);
			games.add(u);

		}
		return games;
	}

	public ArrayList<Integer> getGamesDate() {
		ArrayList<Integer> games = new ArrayList<Integer>();
		int u;

		for (ArrayList<Object> a : this.getAllGamesDate()) {
			u = (int) a.get(0);
			games.add(u);

		}
		return games;
	}

	public ArrayList<ArrayList<Object>> getSelfGames() {
		return database.Select("SELECT game_idgame FROM player WHERE username = '" + self.getUsername() + "'");
	}

	public ArrayList<ArrayList<Object>> getAllGames() {
		return database.Select("SELECT * FROM GAME");
	}

	public ArrayList<ArrayList<Object>> getAllGamesDate() {
		return database.Select("SELECT * FROM GAME ORDER BY creationdate ASC");
	}

	
	public LinkedHashMap<Integer, Boolean> allGames() {
		ArrayList<Integer> gameList = new ArrayList<Integer>();
		ArrayList<Integer> selfList = new ArrayList<Integer>();
		LinkedHashMap<Integer, Boolean> highlightGames = new LinkedHashMap<Integer, Boolean>();
		int gameId = 0;
		
		for (ArrayList<Object> a : getAllGames()) {
			gameId = (int) a.get(0);
			gameList.add(gameId);

		}
		int selfId = 0;
		for (ArrayList<Object> b : getSelfGames()) {
			selfId = (int) b.get(0);
			selfList.add(selfId);

		}
	
		for (int i = 0; i < gameList.size(); i++) {
			boolean selfBoolean = false;
			if (selfList.contains(gameList.get(i))) {
				selfBoolean = true;
				}
			highlightGames.put(gameList.get(i), selfBoolean);
			}
		

		return highlightGames;
	}
	
	public LinkedHashMap<Integer, Boolean> allGamesDate() {
		ArrayList<Integer> gameList = new ArrayList<Integer>();
		ArrayList<Integer> selfList = new ArrayList<Integer>();
		LinkedHashMap<Integer, Boolean> highlightGames = new LinkedHashMap<Integer, Boolean>();
		int gameId = 0;
		
		for (ArrayList<Object> a : getAllGamesDate()) {
			gameId = (int) a.get(0);
			gameList.add(gameId);

		}
		int selfId = 0;
		for (ArrayList<Object> b : getSelfGames()) {
			selfId = (int) b.get(0);
			selfList.add(selfId);

		}
	
		for (int i = 0; i < gameList.size(); i++) {
			boolean selfBoolean = false;
			if (selfList.contains(gameList.get(i))) {
				selfBoolean = true;
				}
			highlightGames.put(gameList.get(i), selfBoolean);
			}
		

		return highlightGames;
	}

	public LinkedHashMap<String, String> getPlayersFilteredByAmountOfGames() {
		LinkedHashMap<String, String> PlayerNamesWithGames = new LinkedHashMap<String, String>();
		String u;
		String s;

		for (ArrayList<Object> a : this.getListOfUsernamesWithAmountOfGamesPlayed()) {
			u = (String) a.get(0);
			if (a.get(1) == null) {
				s = String.valueOf(0);
			}
			s = String.valueOf(a.get(1));
			PlayerNamesWithGames.put(u, s);
		}
		return PlayerNamesWithGames;
	}

	public LinkedHashMap<String, Integer> getPlayersFilteredByAmountOfGamesWon() {
		LinkedHashMap<String, Integer> PlayerNamesWithGamesWon = new LinkedHashMap<String, Integer>();
		String u;
		int s;

		for (ArrayList<Object> a : this.getListOfUsernamesWithAmountOfGamesWon()) {
			u = (String) a.get(0);
			if (a.get(1) == null) {
				s = 0;
			}
			s = ((Number) a.get(1)).intValue();
			PlayerNamesWithGamesWon.put(u, s);
		}
		return PlayerNamesWithGamesWon;
	}

	public Player getSelf() {
		return self;
	}

	public void setSelf(Player self) {
		this.self = self;
	}

}
