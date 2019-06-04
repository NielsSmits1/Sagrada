package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Database.Db;

public class Game {
	private ArrayList<ArrayList<Object>> diceData;
	private ArrayList<Dice> diceArray;
	private ArrayList<Dice> playableDices;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int idgame;
	private int yourself;
	private Random r;
	private Player self;
	private ArrayList<Gamefavortoken> token;
	private int roundNumber;
	private int turnNumber;
	private Player turnPlayer;

	public void addPlayer(Player param, String status, String color, long senr, int cplayer) {
		insertPlayer(param, status, color, senr, cplayer);

	}

	// public ArrayList<Player> getPlayers() {
	// return this.players;
	//
	// }

	public Game() {
		r = new Random();
		diceArray = new ArrayList<>();
		token = new ArrayList<Gamefavortoken>();

	}
	
	public int getPlayerId(String username) {
		return (int)Db.select("select idplayer from player where username = '" + username +"' AND game_idgame = " + idgame +"").get(0).get(0);
	}

	public void startGame() {
		insertDicesIntoDatabase();
		diceData = getselect();
		setDiceArray();
		fillTokenArrayList();
	}

	public int getTurn() {
		return this.turnNumber;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public Player getTurnPlayer() {
		return turnPlayer;
	}

	public void buildTurns() {

		turnNumber = getTurnNumber();
		turnPlayer = setWhoseTurnItIs();
	}

	public void buildRounds() {
		roundNumber = getLastRound();

	}

	public void refreshCurrentPlayer() {
		roundNumber = getLastRound();
		turnNumber = getTurnNumber();
		turnPlayer = setWhoseTurnItIs();

	}

	public Player setWhoseTurnItIs() {
		String turnplayer = (String) Db
				.select("select username from player where isCurrentPlayer = 1 and game_idgame = " + this.idgame).get(0)
				.get(0);
		for (Player p : players) {
			if (p.getUsername().equals(turnplayer)) {
				this.addTurnPlayer(p);
				return p;
			}
		}
		return null;
	}

	private void updateCurrentPlayer() {
		Db.cud("update player set isCurrentPlayer = 0 where game_idgame = " + this.idgame + " and username = '"
				+ turnPlayer.getUsername() + "'");
	}

	private void addToTrack() {
		ArrayList<ArrayList<Object>> leftoverDices = Db.select(
				"select g.dienumber, g.diecolor FROM gameDie g LEFT JOIN playerframefield p ON g.idgame = p.idgame AND g.dienumber = p.dienumber AND g.diecolor = p.diecolor WHERE g.idgame = "
						+ idgame + " AND g.roundtrack IS NULL AND g.round = " + roundNumber
						+ " AND player_idplayer IS NULL;");
		for (int i = 0; i < leftoverDices.size(); i++) {
			Db.cud("UPDATE gameDie g SET roundtrack = " + roundNumber + " WHERE g.dienumber = "
					+ leftoverDices.get(i).get(0) + " AND g.diecolor = '" + leftoverDices.get(i).get(1)
					+ "' AND idgame = " + idgame + ";");
		}
	}
	
	private void setNewCurrentRoundBeginner() {
		updateSeNumber();
		updateCurrentPlayer();
		Db.cud("update player set isCurrentPlayer = 1 where seqnr = 1 and game_idgame = "
				+ this.idgame);
		turnPlayer = setWhoseTurnItIs();
	}

	public void setNewCurrentPlayer() {
		updateSeNumber();

		int numberOfPlayers = players.size();
		if (turnNumber == numberOfPlayers * 2) {
			setNewCurrentRoundBeginner();
			newRound();
			
			turnNumber = getTurnNumber();


		} else {
			updateSeNumber();
			setNewCurrentPlayerDB();
		}
	}
	private void updateSe(int num) {
		Db.cud("update player set seqnr = " + num + " where game_idgame = " + this.idgame + " and username = '" + this.turnPlayer.getUsername() + "'");
	}
	private void updateSeNumber() {
		int curn = (int)Db.select("select seqnr from player where game_idgame = " + this.idgame + " and username = '" + this.turnPlayer.getUsername() + "'").get(0).get(0);
		switch(curn) {
		case 1: 
			updateSe(players.size() * 2);
			break;
		
		case 2: 
			if(players.size() == 2) {
				updateSe(3);
			}else if(players.size() == 3) {
				updateSe(5);
			}else {
				updateSe(7);
			}break;
		case 3:
			if(players.size() == 2) {
				updateSe(1);
			}else if(players.size() == 3) {
				updateSe(4);
			}else {
				updateSe(6);
			}
			break;
		case 4: 
			if(players.size() == 4) {
				updateSe(5);
			}else {
				updateSe(2);
			}
			break;
		case 5: 
			if(players.size() == 3) {
				updateSe(1);
			}else {
				updateSe(3);
			}
			break;
		case 6: 
			if(players.size() == 3) {
				updateSe(3);
			}else {
				updateSe(2);
			}
			break;
		case 7: 
			updateSe(1);
			break;
		case 8: 
			updateSe(4);
		}
	}
	private void forwardSeqNr() {
		int maxNumber = 1;
		Db.cud("Update player set isCurrentPlayer = 0 where game_idgame = " + this.idgame);
		for (ArrayList<Object> a : Db.select(
				"select username, seqnr from player where game_idgame = " + this.idgame + " order by seqnr desc")) {
			if ((int) a.get(1) == players.size() * 2) {
				Db.cud("update player set seqnr = " + players.size() + " where game_idgame = " + this.idgame
						+ " and username = '" + (String) a.get(0) + "'");
			} else {
				Db.cud("update player set seqnr = " + maxNumber + " where game_idgame = " + this.idgame
						+ " and username = '" + (String) a.get(0) + "'");
				maxNumber += 1;
			}
		}
		// update player set senr = 1 where senr = 7
		// updte player set senr = 2 where senr = 6
		// updt pl set se nr = 3 whe sen = 5
		// updt pl set se = 4 where se 8
		Db.cud("update player set isCurrentPlayer = 1 where game_idgame = " + this.idgame + " and seqnr = 1");
	}

	private void backwartsSeqNr() {

		int maxNumber = turnNumber;
		for (ArrayList<Object> a : Db
				.select("select username from player where game_idgame = " + this.idgame + " order by seqnr desc")) {
			Db.cud("update player set seqnr = " + (maxNumber + 1) + " where game_idgame = " + this.idgame
					+ " and username = '" + (String) a.get(0) + "'");
			maxNumber += 1;
		}
		setNewCurrentPlayerDB();
	}

	private void setNewCurrentPlayerDB() {
		updateSeNumber();
		updateCurrentPlayer();
		Db.cud("update player set isCurrentPlayer = 1 where seqnr = " + (turnNumber + 1) + " and game_idgame = "
				+ this.idgame);
		turnPlayer = setWhoseTurnItIs();
	}

	private void newRound() {
		if (roundNumber > 10) {
			showWinnerScreen();
		} else {
			addToTrack();
			this.roundNumber = getLastRound();
		}
	}

	private int getTurnNumber() {
		return (int) Db
				.select("select seqnr from player where isCurrentPlayer = 1 and game_idgame = " + this.idgame).get(0)
				.get(0);
	}

	private int getLastRound() {
		ArrayList<ArrayList<Object>> round = Db
				.select("select max(roundtrack) from gamedie where idgame = " + this.idgame);
		if (round.get(0).get(0) == null) {
			// als null - geen rondes: begin bij ronde 1
			return 1;
		} else if ((int) round.get(0).get(0) == 10) {
			return 10;
		} else {
			return (int) round.get(0).get(0) + 1;
			// anders + 1 is de ronde waar ze in zitten
		}
	}

	public ArrayList<ArrayList<Object>> showWinnerScreen() {
		Db.cud("update player set playstatus_playstatus = 'Uitgespeeld' where game_idgame = " + this.idgame);
		return Db.select(
				"select p.username, p.score, idplayer seqnr from player p where game_idgame = " + idgame + " order by p.score desc");
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}

	public boolean alreadyInGame(Player player) {
		for (ArrayList<Object> a : getPlayersInGame()) {
			String s = (String) a.get(1);
			if (s.equals(player.getUsername())) {
				return true;
			}
		}
		return false;

	}

	public void insertPlayer(Player p, String status, String color, long senr, int cplayer) {
		Db.cud(
				"INSERT INTO PLAYER(username,game_idgame,playstatus_playstatus,isCurrentPlayer,private_objectivecard_color, seqnr) VALUES ('"
						+ p.getUsername() + "', " + this.idgame + " , '" + status + "', " + cplayer + ", '" + color
						+ "', " + senr + ")");
	}

	public ArrayList<ArrayList<Object>> getColorsFromGame(int idgame) {
		return Db.select("select private_objectivecard_color FROM player WHERE game_idgame ='" + idgame + "'");
	}

	public String getRandomColor() {
		return checkColor().get(0);
	}

	public ArrayList<String> checkColor() {
		ArrayList<String> allColors = new ArrayList<String>();
		allColors.add("rood");
		allColors.add("blauw");
		allColors.add("groen");
		allColors.add("paars");
		allColors.add("geel");

		ArrayList<String> takenColors = new ArrayList<String>();
		String colors;
		for (ArrayList<Object> a : getColorsFromGame(idgame)) {
			colors = (String) a.get(0);
			takenColors.add(colors);

		}
		allColors.removeAll(takenColors);
		Collections.shuffle(allColors);
		return allColors;
	}

	public ArrayList<ArrayList<Object>> getPlayersInGame() {
		return Db.select(
				"select idplayer, username, seqnr, private_objectivecard_color, score, patterncard_idpatterncard from player where game_idgame = "
						+ this.idgame);

	}

	public long getNewId() {
		return (long) Db.select(
				"select (idplayer+1) AS newPlayerId FROM tjpmsalt_db2.player ORDER BY game_idgame DESC LIMIT 1;").get(0)
				.get(0);
	}

	// gets all die information where id game equals the new game.
	public ArrayList<ArrayList<Object>> getselect() {
		return Db.select("select * FROM gamedie WHERE idgame =" + idgame + " ORDER BY dienumber;");
	}

	// adds all dices to a dicearraylist.
	private void setDiceArray() {
		for (int i = 0; i < diceData.size(); i++) {
			diceArray.add(new Dice());
			diceArray.get(i).setDieNumber((int) diceData.get(i).get(1));
			switch ((String) diceData.get(i).get(2)) {
			case "blauw":
				diceArray.get(i).setDieColor("blauw");
				break;
			case "rood":
				diceArray.get(i).setDieColor("rood");
				break;
			case "geel":
				diceArray.get(i).setDieColor("geel");
				break;
			case "groen":
				diceArray.get(i).setDieColor("groen");
				break;
			case "paars":
				diceArray.get(i).setDieColor("paars");
				break;
			}
			updateEyes(diceArray.get(i).getEyes(), diceArray.get(i).getDieNumber(), diceArray.get(i).getDieColor());
			// diceArray = null;
		}
	}

	// returns the arraylist with dices.
	public ArrayList<Dice> getDiceArray() {
		return diceArray;
	}

	// inserts standard values for dices for a new game.
	public void insertDicesIntoDatabase() {
		Db.cud(
				"INSERT INTO gamedie (idgame, dienumber, diecolor) select " + idgame + ", number, color FROM die;");
	}

	public void updateEyes(int eyes, int dienumber, String color) {
		Db.cud("UPDATE gamedie SET eyes = " + eyes + " WHERE idgame = " + idgame + " AND dienumber = " + dienumber
				+ " AND diecolor = '" + color + "';");
	}

	public ArrayList<Dice> getPlayableDices() {

		return playableDices;
	}

	public void getDiceWithChosenValue(int dienumber, String color, int chosenvalue) {
		for (int i = 0; i < playableDices.size(); i++) {
			if (playableDices.get(i).getDieNumber() == dienumber && playableDices.get(i).getDieColor().equals(color)) {
				playableDices.remove(i);
			}
		}
		Db.cud("Update gamedie SET ROUND = null WHERE dienumber = " + dienumber + " AND diecolor = '" + color
				+ "' AND idgame = " + idgame + "");
		Db.cud("update gamedie SET Round = " + roundNumber + " WHERE eyes = " + chosenvalue + " AND idgame = "
				+ idgame + " AND ROUND IS NULL ORDER BY RAND() LIMIT 1");
	}

	public void reDraw() {
		ArrayList<ArrayList<Object>> leftoverDices = Db.select(
				"select g.dienumber, g.diecolor, g.eyes FROM gameDie g LEFT JOIN playerframefield p ON g.idgame = p.idgame AND g.dienumber = p.dienumber AND g.diecolor = p.diecolor WHERE g.idgame = "
						+ idgame + " AND g.roundtrack IS NULL AND g.round = " + roundNumber
						+ " AND player_idplayer IS NULL;");
		int amountToBeDrawed = leftoverDices.size();
		Db.cud(
				"Update gamedie g LEFT JOIN playerframefield p ON g.idgame = p.idgame AND g.dienumber = p.dienumber AND g.diecolor = p.diecolor SET ROUND = null WHERE g.idgame = "
						+ idgame + " AND g.roundtrack IS NULL AND g.round = " + roundNumber
						+ " AND player_idplayer IS NULL;");
		Db.cud("Update gamedie set round = " + roundNumber + " where idgame = " + idgame
				+ " AND round IS NULL ORDER BY RAND() LIMIT " + amountToBeDrawed + "");
	}

	public void setPlayableDices() {
		playableDices = new ArrayList<>();
		ArrayList<ArrayList<Object>> leftoverDices = Db.select(
				"select g.dienumber, g.diecolor, g.eyes FROM gameDie g LEFT JOIN playerframefield p ON g.idgame = p.idgame AND g.dienumber = p.dienumber AND g.diecolor = p.diecolor WHERE g.idgame = "
						+ idgame + " AND g.roundtrack IS NULL AND g.round = " + roundNumber
						+ " AND player_idplayer IS NULL;");
		if (!leftoverDices.isEmpty()) {
			for (int i = 0; i < leftoverDices.size(); i++) {
				playableDices.add(new Dice((int) leftoverDices.get(i).get(0), (String) leftoverDices.get(i).get(1),
						(int) leftoverDices.get(i).get(2)));
			}
		} else {
			ArrayList<ArrayList<Object>> randomDice = Db
					.select("select dienumber, diecolor, eyes from gamedie where idgame = " + idgame
							+ " AND round IS NULL ORDER BY RAND() LIMIT " + ((players.size() * 2) + 1) + "");
			for (int i = 0; i < randomDice.size(); i++) {
				playableDices.add(new Dice((int) randomDice.get(i).get(0), (String) randomDice.get(i).get(1),(int) randomDice.get(i).get(2)));
				Db.cud("UPDATE gamedie SET round = " + roundNumber + " WHERE idgame = " + idgame
						+ " AND dienumber = " + playableDices.get(i).getDieNumber() + " AND diecolor = '"
						+ playableDices.get(i).getDieColor() + "'");
			}

		}
	}

	public int getIdGame() {
		return idgame;
	}

	public int getOwnId() {
		for (Player p : players) {
			if (p.getSelf()) {
				yourself = p.getPlayerId();
			}
		}
		return yourself;
	}

	public ArrayList<ArrayList<Object>> getAvailableGames(String u) {
		return Db.select(
				"select game_idgame, COUNT(game_idgame) AS amountPlayers FROM player WHERE game_idgame IN (select game_idgame FROM player where username = '"
						+ u
						+ "' AND playstatus_playstatus = 'Uitdager') GROUP BY game_idgame HAVING amountPlayers < 4");
	}

	public ArrayList<ArrayList<Object>> getRejectedGames(String u) {
		return Db.select(
				"select game_idgame FROM player WHERE game_idgame in (select game_idgame FROM player where username = '"
						+ u + "' AND playstatus_playstatus = 'Uitdager') AND playstatus_playstatus = 'geweigerd'");
	}

	public ArrayList<Integer> availableGames(String u) {
		ArrayList<Integer> availableGames = new ArrayList<Integer>();
		ArrayList<Integer> rejectedGames = new ArrayList<Integer>();
		int gameid = 0;
		for (ArrayList<Object> a : getAvailableGames(u)) {
			gameid = (int) a.get(0);
			availableGames.add(gameid);

		}
		int rejectid = 0;
		for (ArrayList<Object> b : getRejectedGames(u)) {
			rejectid = (int) b.get(0);
			rejectedGames.add(rejectid);

		}
		availableGames.removeAll(rejectedGames);

		return availableGames;
	}

	public void setGameId(int gid) {
		this.idgame = gid;

	}

	public void insertPlayers(ArrayList<Player> players) {
		this.players = players;

	}

	public void fillTokenArrayList() {
		for (int i = 1; i < 25; i++) {
			token.add(new Gamefavortoken(i));
			Db.cud("INSERT INTO gamefavortoken (idfavortoken, idgame) VALUES (" + i + "," + idgame + ")");
		}
	}

	public void assignTokensToPlayer() {
		for (Player p : players) {
			if (p.getSelf()) {
				p.setTokenAmount(p.getPc().getDifficulty());
				Db.cud("UPDATE gamefavortoken SET idplayer = " + p.getPlayerId()
						+ " WHERE idplayer is null AND idgame = " + idgame + " LIMIT " + p.getPc().getDifficulty()
						+ ";");
			}
		}
	}

	public void updateTokenArrayList(int difficulty) {
		for (int i = 1; i <= difficulty; i++) {
			Db.cud("UPDATE gamefavortoken SET idplayer = " + yourself + " WHERE idFavortoken = " + i
					+ " AND idgame = " + idgame + ";");
		}
	}

	public void addTokensToGametoolcard(int tokensGone, int toolcardid, int playerid) {
		Db.cud("UPDATE gamefavortoken SET gametoolcard = " + getGametoolcard(toolcardid) + ", round = "
				+ roundNumber + " WHERE idgame = " + idgame + " AND idplayer = " + playerid
				+ " AND gametoolcard is null LIMIT " + tokensGone + "");
	}

	public int getLeftoverTokens() {
		return (int) Db.select("select count(*) FROM gamefavortoken WHERE idgame = " + idgame + " AND idplayer = "
				+ yourself + " AND round is null;").get(0).get(0);
	}

	public int getGametoolcard(int toolcardid) {
		return (int) Db.select("select gametoolcard from gametoolcard WHERE idgame = " + idgame
				+ " AND idtoolcard = " + toolcardid + "").get(0).get(0);
	}

	public int getGamemode() {
		return players.size();
	}

	public void addOptionsToDB(ArrayList<Integer> randomIDS) {
		for (int i = 0; i < randomIDS.size(); i++) {
			if (i >= 12) {
				Db.cud("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES ("
						+ randomIDS.get(i) + ", " + players.get(3).getPlayerId() + ")");
				continue;
			}
			if (i >= 8) {
				Db.cud("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES ("
						+ randomIDS.get(i) + ", " + players.get(2).getPlayerId() + ")");
				continue;
			}
			if (i >= 4) {
				Db.cud("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES ("
						+ randomIDS.get(i) + ", " + players.get(1).getPlayerId() + ")");
				continue;
			}
			if (i >= 0) {
				Db.cud("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES ("
						+ randomIDS.get(i) + ", " + players.get(0).getPlayerId() + ")");
				continue;
			}
			// System.out.println("waarde: " + randomIDS.get(i));
			// database.cud("INSERT INTO patterncardoption (patterncard_idpatterncard,
			// player_idplayer) VALUES (" + randomIDS.get(i) + ", " +
			// players.get(j).getPlayerId() + ")");

		}
	}

	public void setSelf() {
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getSelf()) {
				self = players.get(i);
			}
		}
	}

	public ArrayList<Integer> getOwnOptions() {
		ArrayList<Integer> ownOptions = new ArrayList<>();
		for (Player p : players) {
			if (p.getSelf()) {
				for (int i = 0; i < 4; i++) {
					ownOptions.add((int) Db
							.select("select patterncard_idpatterncard FROM patterncardoption WHERE player_idplayer = "
									+ p.getPlayerId() + ";")
							.get(i).get(0));
				}
			}
		}
		return ownOptions;
	}

	public boolean hasChosen() {
		for (Player p : players) {
			if (p.getPatternId() == 0) {

				return false;
			}
		}
		return true;
	}

	public ArrayList<Integer> getChosenIds() {
		ArrayList<Integer> chosenId = new ArrayList<Integer>();
		for (int i = 0; i < players.size(); i++) {
			chosenId.add((int) Db
					.select("select patterncard_idpatterncard FROM player WHERE game_idgame = " + idgame + ";").get(i)
					.get(0));
		}
		return chosenId;
	}

	public boolean checkIfSelf() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkIfIPickedPatternCard(String username) {
		for (Player p : players) {
			if (p.getUsername().equals(username)) {
				if (p.getPatternId() != 0) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public Player getSelf() {
		return self;
	}

	public boolean checkIfFilled() {
		if (Db.select(
				"select patterncardoption.patterncard_idpatterncard FROM patterncardoption LEFT JOIN player ON player_idplayer = idplayer WHERE game_idgame = "
						+ idgame + ";")
				.isEmpty()) {
			return false;
		}
		return true;
	}

	public void createNewGame() {
		Db.cud("insert into game(creationdate) values (now())");
		this.idgame = (int) Db.select("select max(idgame) from game").get(0).get(0);

	}

	public void insertChosenID(int id) {
		for (Player p : players) {
			if (p.getSelf()) {
				p.setPatternCardId(id);
				p.setPc();
				Db.cud("UPDATE player SET patterncard_idpatterncard = " + id + " WHERE idplayer = "
						+ p.getPlayerId() + ";");
			}
		}

	}

	public void insertChosenID() {
		for (Player p : players) {
			if (p.getSelf()) {
				p.setPatternCardId(getHighestId());
				p.setPc();
				Db.cud("UPDATE player SET patterncard_idpatterncard = " + getHighestId() + " WHERE idplayer = "
						+ p.getPlayerId() + ";");
			}
		}

	}

	private int getHighestId() {
		return (int) Db.select("select idpatterncard FROM patterncard order by idpatterncard DESC LIMIT 1;")
				.get(0).get(0);
	}

	public int getOwnPatternId() {
		for (Player p : players) {
			if (p.getSelf()) {
				return p.getPatternId();
			}
		}

		return 0;
	}

	public long getHighestSeNumber() {
		return (long) Db.select("select max(seqnr) + 1 from player where game_idgame = " + this.idgame).get(0)
				.get(0);
	}


	public ArrayList<ArrayList<Dice>> getLeftovers() {
		ArrayList<ArrayList<Dice>> dicePerRound = new ArrayList<>();
		
		for (int j = 1; j < 11; j++) {
			ArrayList<Dice> dices = new ArrayList<Dice>();
			for (int i = 0; i < getRoundDice(j).size(); i++) {
				dices.add(new Dice((int) getRoundDice(j).get(i).get(0), (String) getRoundDice(j).get(i).get(1),
						(int) getRoundDice(j).get(i).get(2)));
			}
			dicePerRound.add(dices);
		}
		 return dicePerRound;
	}

	public ArrayList<ArrayList<Object>> getRoundDice(int j) {
        return Db.select("select dienumber,diecolor,eyes from gamedie where idgame = "+ idgame +" and roundtrack = "+ j);
	}

	public void addTurnPlayer(Player self2) {
		Db.cud("update game set turn_idplayer = (select idplayer from player where username = '"
				+ self2.getUsername() + "' and game_idgame = " + this.idgame + ") where idgame = " + this.idgame);

	}

}
