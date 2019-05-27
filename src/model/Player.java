package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.db;

public class Player {
	private int idplayer;
	private String username;
	private String password;
	private db database = new db();
	private PatternCard board;
	private int score;
	private int seqnr;
	private int lastgame;
	private int gameId;
	private String status;
	private Boolean self = false;
	private String objective_color;
	private int patternCardId;

	// private String differendPlayer;

	public void setSelf(Boolean s) {
		this.self = s;
	}

	public void setPatternCardId(int i) {
		this.patternCardId = i;

	}

	public void setObjective_color(String o) {
		this.objective_color = o;
	}

	public Player(String u, String p) {
		this.username = u;
		this.password = p;
	}

	public void setStatus(String s) {
		this.status = s;
	}

	public String getStatus() {
		return this.status;
	}

	public Player(String username) {
		this.username = username;
	}

	// selects and returns the username and password.
	public ArrayList<ArrayList<Object>> getSelect() {
		return database
				.Select("SELECT * FROM account WHERE username = '" + username + "' AND password = '" + password + "';");
	}

	public ArrayList<ArrayList<Object>> checkUsername() {
		return database.Select("Select * from account where username = '" + username + "'");
	}

	// selects and returns arraylist of usernames.
	public ArrayList<ArrayList<Object>> checkPlayerInGame() {
		return database.Select(
				"select username from player where game_idgame in (select game_idgame from player where username ='"
						+ username + "') ");
	}

	public ArrayList<ArrayList<Object>> playerWonList() {
		return database.Select(
				"SELECT p1.username,count(p1.username) as games_won FROM player p1 LEFT JOIN player p2 ON p1.game_idgame = p2.game_idgame AND p1.score < p2.score where p2.score is null AND p1.playstatus_playstatus = 'Uitgespeeld' group by p1.username");
	}

	public ArrayList<ArrayList<Object>> playerPlayedList() {
		return database.Select(
				"select username, count(game_idgame) as played_games from player where playstatus_playstatus = 'uitgespeeld' group by username");
	}

	public ArrayList<ArrayList<Object>> maxPlayerScore() {
		return database.Select("select username , max(score) as max from player group by username");
	}

	public ArrayList<ArrayList<Object>> maxColor() {
		return database.Select(
				"select username, diecolor , count(diecolor) as amount_color from player join playerframefield on player.idplayer = playerframefield.player_idplayer where username = '"
						+ username + "' group by username,diecolor order by amount_color DESC limit 1");
	}
	public ArrayList<ArrayList<Object>> maxDieNumber() {
		return database.Select("SELECT username, COUNT(g.eyes) amount_eyes, g.eyes FROM player p JOIN playerframefield pf ON p.idplayer = pf.player_idplayer JOIN (SELECT eyes, dienumber FROM gamedie WHERE round is not null) as g ON g.dienumber = pf.dienumber WHERE username = '"+ username +"' GROUP BY p.username, g.eyes ORDER BY amount_eyes desc LIMIT 1");

	}
	

	public ArrayList<ArrayList<Object>> maxPlayedAgainst() {
		return database.Select(
				"select username, diecolor , count(diecolor) as amount_color from player join playerframefield on player.idplayer = playerframefield.player_idplayer where username = '"
						+ username + "' group by username,diecolor order by amount_color DESC limit 1");
	}

	public ArrayList<ArrayList<Object>> maxUniquePlayersPlayed() {
		return database.Select(
				"select *, count(distinct(username)) as amount_played_against from player where game_idgame in (select game_idgame from player where username = '"
						+ username + "') and username != '" + username
						+ "' and playstatus_playstatus = 'Uitgespeeld' or 'Afgebroken' group by username");
	}

	public ArrayList<ArrayList<Object>> lastGameMade() {
		return database.Select("select max(idgame)from game");
	}

	public ArrayList<ArrayList<Object>> lastGamePlayers() {
		return database.Select("SELECT username FROM player where game_idgame = '" + getLastGame() + "'");
	}

	public ArrayList<ArrayList<Object>> getPlayedGames() {
		return database.Select(
				"SELECT COUNT(p1.playstatus_playstatus), p1.game_idgame FROM player as p1 WHERE p1.playstatus_playstatus = 'Geaccepteerd' and p1.game_idgame IN (SELECT game_idgame FROM player WHERE  username = '"
						+ this.username + "') GROUP BY p1.game_idgame");
	}

	// adds new user to the database.
	public void addUser() {
		database.CUD("INSERT INTO account (username, password) VALUES ('" + username + "', '" + password + "');");
	}

	public boolean checkLogin() {
		if (getSelect().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkUsernameExists() {
		if (this.checkUsername().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int getTimesWon() {
		int w = 0;
		for (ArrayList<Object> a : playerWonList()) {
			if (a.get(0).equals(this.username)) {
				w = ((Number) a.get(1)).intValue();
			}
		}
		return w;
	}

	public int getTimesPlayed() {
		int w = 0;
		for (ArrayList<Object> a : playerPlayedList()) {
			if (a.get(0).equals(this.username)) {
				w = ((Number) a.get(1)).intValue();
			}
		}
		return w;
	}

	public int getTimesLost() {
		int w = 0;
		getTimesPlayed();
		getTimesWon();
		w = getTimesPlayed() - getTimesWon();

		return w;
	}

	public int getHighScore() {
		int w = 0;
		for (ArrayList<Object> a : maxPlayerScore()) {
			if (a.get(0).equals(this.username)) {
				w = ((Number) a.get(1)).intValue();
			}
		}
		return w;
	}

	public String getMostPlacedDiceColor() {
		String w = "";
		for (ArrayList<Object> a : maxColor()) {
			if (a.get(0).equals(this.username)) {
				w = (String) a.get(1);
			}
		}
		return w;

	}

	public int getMostPlacedDiceEyes() {
		int w = 0;
		for (ArrayList<Object> a : maxDieNumber()) {
			if (a.get(0).equals(this.username)) {
				w = (int) a.get(2);
			}
		}
		return w;

	}

	public int getAmountOfUniquePlayers() {
		int w = maxUniquePlayersPlayed().size();
		return w;
	}

	public int getLastGame() {
		int w = 0;
		for (ArrayList<Object> a : lastGameMade()) {
			w = ((Number) a.get(0)).intValue();
		}
		lastgame = w;
		return w;
	}

	public boolean checkGameSize() {
		if (lastGamePlayers().size() == 4) {
			return false;
		}
		return true;
	}

	public boolean checkSelf() { // returns true if ur already in the idgame
		for (ArrayList<Object> a : lastGamePlayers()) {
			if (a.get(0).equals(this.username)) {
				return true;
			}
		}
		return false;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(int idplayer2) {
		this.idplayer = idplayer2;

	}

	public void setScore(int score) {
		this.score = score;

	}

	public void setSeqnr(int seqnr) {
		this.seqnr = seqnr;

	}

	public int getSeqnr() {
		return this.seqnr;
	}

	public void changeSeqNr() {
		switch (seqnr) {
		case 1:
			seqnr = 8;
			break;
		case 2:
			seqnr = 7;
			break;
		case 3:
			seqnr = 6;
			break;
		case 4:
			seqnr = 5;
			break;
		case 5:
			seqnr = 4;
			break;
		case 6:
			seqnr = 3;
			break;
		case 7:
			seqnr = 2;
			break;
		case 8:
			seqnr = 1;
			break;
		}
		updateSeqNr();
	}

	private void updateSeqNr() {
		database.Select("update player set seqnr = " + this.seqnr + " where idplayer = " + this.idplayer);
	}

	public boolean checkIfGame(String username) {
		for (ArrayList<Object> a : this.checkPlayerInGame()) {
			String u = (String) a.get(0);
			if (u.equals(username)) {
				return true;
			}
		}
		return false;
	}

	public void addGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getGameId() {
		return this.gameId;
	}

	public ArrayList<Game> getOpenGames() {
		ArrayList<Game> games = new ArrayList<Game>();
		for (ArrayList<Object> a : this.getPlayedGames()) {
			if ((long) a.get(0) == (countPlayersGame((int) a.get(1)) - 1)) {
				setChallengerToAccepted();
			}
			if ((long) a.get(0) == countPlayersGame((int) a.get(1))) { // if all players accepted
				Game g = new Game();
				g.setGameId((int) a.get(1));
				g.insertPlayers(buildPlayersForGame(g.getPlayersInGame()));
				games.add(g);
			}
		}
		return games;
	}

	private ArrayList<Player> buildPlayersForGame(ArrayList<ArrayList<Object>> players) {
		ArrayList<Player> P = new ArrayList<Player>();
		for (ArrayList<Object> pl : players) {
			Player pop = new Player((String) pl.get(1));
			pop.setId((int) pl.get(0));
			if (pl.get(2) != null) {
				pop.setSeqnr((int) pl.get(2));
			} else {
				pop.setSeqnr(0);
			}
			if (pl.get(4) == null) {
				pop.setScore(0);
			} else {
				pop.setScore((int) pl.get(4));
			}
			pop.setObjective_color((String) pl.get(3));
			if (pl.get(5) == null) {
				pop.setPatternCardId(0);
			} else {
				pop.setPatternCardId((int) pl.get(5));
			}

			if (pl.get(1) == this.username) {
				pop.setSelf(true);
			}
			P.add(pop);
		}
		return P;

	}

	private long countPlayersGame(int gameId) {
		return (long) database.Select("select count(username) from player where game_idgame = " + gameId).get(0).get(0);
	}

	public void setChallengerToAccepted() {
		database.CUD(
				"UPDATE player SET playstatus_playstatus = 'Geaccepteerd' WHERE playstatus_playstatus = 'Uitdager' and game_idgame in (select game_idgame from (select * FROM player) as playerr )"); // idplayer
																																																		// needs
																																																		// to
																																																		// be
																																																		// variabel
	}
	
	public boolean usedInvalidCharacters() {
		Pattern pattern = Pattern.compile("[:?!@#$%^&*()]");
		Matcher m1 = pattern.matcher(username);
		Matcher m2 = pattern.matcher(password);

		if (m1.find() || m2.find()) {
			return true;
		}
		return false;
	}
}
