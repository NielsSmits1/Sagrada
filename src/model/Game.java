package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Database.db;
import controller.GameController;

public class Game {
	private db database = new db();
	private ArrayList<ArrayList<Object>> diceData;
	private ArrayList<Round> rounds;
	private ArrayList<Dice> diceArray;
	private ArrayList<Dice> playableDices;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int idgame;
	private int yourself;
	private Random r;
	private Player self;
	private ArrayList<Gamefavortoken> token;
	private GameController controller;
	private int roundNumber;
	private int turnNumber;
	private Player turnPlayer;
	


	

	public void addPlayer(Player param, String status, String color, long senr, int cplayer) {
		insertPlayer(param, status, color, senr, cplayer);

	}

//	public ArrayList<Player> getPlayers() {
//		return this.players;
//
//	}
	

	public Game() {
		r = new Random();
		diceArray = new ArrayList<>();
		token = new ArrayList<Gamefavortoken>();
		database = new db();
		

	}
	
	public void startGame() {
		insertDicesIntoDatabase();
		diceData = getSelect();
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
		String turnplayer = (String)database.Select("select username from player where isCurrentPlayer = 1 and game_idgame = " +this.idgame).get(0).get(0);
		for(Player p: players) {
			if(p.getUsername().equals(turnplayer)) {
				return p;
			}
		}
		return null;
	}
	
	private void updateCurrentPlayer() {
		database.CUD("update player set isCurrentPlayer = 0 where game_idgame = " + this.idgame + " and username = '" + turnPlayer.getUsername() + "'");
	}
	
	public void setNewCurrentPlayer() {
		int numberOfPlayers = players.size();
		if(turnNumber == numberOfPlayers ) {// 2-3-4
			// dan is de eerste loop voorbij
			backwartsSeqNr();
			setNewCurrentPlayerDB();
			
		}else if(turnNumber == numberOfPlayers * 2) {// 4-6-8
			// dan is een ronde voorbij
			newRound();
		}else {
			setNewCurrentPlayerDB();
		}
	}
	private void forwardSeqNr() {
		int maxNumber = 1;
		for(ArrayList<Object> a : database.Select("select username from player where game_idgame = " + this.idgame)) {// get players in game, DEZE QUERY BESTAAT AL IN GAME 
			database.CUD("update player set seqnr = " + maxNumber+ " where game_idgame = " + this.idgame + " and username = '" + (String)a.get(0) + "'");
			maxNumber+=1;
		}
		database.CUD("update player set isCurrentPlayer = 1 where game_idgame = " + this.idgame + " and seqnr = 1");
	}

	private void backwartsSeqNr() {
		
		int maxNumber = turnNumber;
		for(ArrayList<Object> a : database.Select("select username from player where game_idgame = " + this.idgame + " order by idplayer desc")) {// get players in game, DEZE QUERY BESTAAT AL IN GAME 
			database.CUD("update player set seqnr = " + (maxNumber + 1) + " where game_idgame = " + this.idgame + " and username = '" + (String)a.get(0) + "'");
			System.out.println(a.get(0) + " " + maxNumber);
			maxNumber+=1;
		}
		setNewCurrentPlayerDB();
	}

	private void setNewCurrentPlayerDB() {
		updateCurrentPlayer();
		database.CUD("update player set isCurrentPlayer = 1 where seqnr = " + (turnNumber + 1) + " and game_idgame = " + this.idgame);
		
	}
	private void newRound() {
		// doe iets met de overgebleven dice(s)
		// en ook iets met roundtrack
		// this.addRoundTrack(gamePane.getRemainingDices());
		forwardSeqNr();
		if(roundNumber>9) {
			
		}else {
			this.roundNumber +=1;
		}
	}
	
	

	private int getTurnNumber() {
		return (int)database.Select("select seqnr from player where isCurrentPlayer = 1 and game_idgame = " + this.idgame).get(0).get(0);
	}

	
	private int getLastRound(){
		ArrayList<ArrayList<Object>> round = database.Select("Select max(roundtrack) from gamedie where idgame = " + this.idgame);
		if(round.get(0).get(0)==null) {
			// als null - geen rondes: begin bij ronde 1
			return 1;
		}else if((int)round.get(0).get(0) == 10) {
			// als 10 game voorbij duuuh
			// hoezo opent hij dit scherm
			return 10;
		}else {
			return (int)round.get(0).get(0) + 1;
			// anders + 1 is de ronde waar ze in zitten
		}
	}


	// public void addPlayer(Player param) {
	// 	insertPlayer(param);

	// }o

	// public void addPlayer(Player param, String status) {
	// 	insertPlayer(param, status);

	// }

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
		database.CUD(
				"INSERT INTO PLAYER(username,game_idgame,playstatus_playstatus,isCurrentPlayer,private_objectivecard_color, seqnr) VALUES ('"
						+ p.getUsername() + "', " + this.idgame + " , '" + status + "', " + cplayer + ", '"+ color +"', " + senr + ")");
	}

	public ArrayList<ArrayList<Object>> getColorsFromGame(int idgame) {
		return database.Select("SELECT private_objectivecard_color FROM player WHERE game_idgame ='"+ idgame + "'");
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
		return database.Select("select idplayer, username, seqnr, private_objectivecard_color, score, patterncard_idpatterncard from player where game_idgame = " + this.idgame);

	}

	public long getNewId() {
		return (long) database.Select(
				"SELECT (idplayer+1) AS newPlayerId FROM tjpmsalt_db2.player ORDER BY game_idgame DESC LIMIT 1;").get(0)
				.get(0);
	}

	// gets all die information where id game equals the new game.
	public ArrayList<ArrayList<Object>> getSelect() {
		return database.Select("SELECT * FROM tjpmsalt_db2.gamedie WHERE idgame =" + idgame + " ORDER BY dienumber;");
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
//			diceArray = null;
		}
	}

	// returns the arraylist with dices.
	public ArrayList<Dice> getDiceArray() {
		return diceArray;
	}

	// inserts standard values for dices for a new game.
	public void insertDicesIntoDatabase() {
		database.CUD("INSERT INTO gamedie (idgame, dienumber, diecolor) SELECT " + idgame + ", number, color FROM die;");
	}

	public void updateEyes(int eyes, int dienumber, String color) {

		database.CUD("UPDATE gamedie SET eyes = " + eyes + " WHERE idgame = " + idgame + " AND dienumber = " + dienumber
				+ " AND diecolor = '" + color + "';");
	}

	public ArrayList<Dice> getPlayableDices() {

		return playableDices;
	}

	public void getDiceWithChosenValue(int dienumber, String color, int value, int chosenvalue) {
		diceArray.add(new Dice(dienumber, color, value));
		for (int i = 0; i < playableDices.size(); i++) {
			if (playableDices.get(i).getDieNumber() == dienumber && playableDices.get(i).getDieColor().equals(color)) {
				playableDices.remove(i);
			}
		}
		int index = r.nextInt(diceArray.size());
		Dice newDice = diceArray.get(index);
		while (newDice.getEyes() != chosenvalue) {
			index = r.nextInt(diceArray.size());
			newDice = diceArray.get(index);
		}
		diceArray.remove(index);
		playableDices.add(newDice);
	}

	public void setPlayableDices() {
		playableDices = new ArrayList<>();
		ArrayList<ArrayList<Object>> leftoverDices = database.Select("SELECT g.dienumber, g.diecolor, g.eyes FROM gameDie g LEFT JOIN playerframefield p ON g.idgame = p.idgame AND g.dienumber = p.dienumber AND g.diecolor = p.diecolor WHERE g.idgame = " + idgame +" AND g.roundtrack IS NULL AND g.round = " + 1 +" AND player_idplayer IS NULL;");
		if(!leftoverDices.isEmpty()) {
			for (int i = 0; i < leftoverDices.size(); i++) {
				playableDices.add(new Dice((int)leftoverDices.get(i).get(0), (String)leftoverDices.get(i).get(1), (int)leftoverDices.get(i).get(2)));
			}
		}
		else {
		ArrayList<ArrayList<Object>> randomDice = database.Select("select dienumber, diecolor, eyes from gamedie where idgame = " + idgame + " AND round IS NULL ORDER BY RAND() LIMIT " + ((players.size()*2)+1) +"");
		for (int i = 0; i < randomDice.size(); i++) {
			playableDices.add(new Dice((int)randomDice.get(i).get(0), (String)randomDice.get(i).get(1), (int)randomDice.get(i).get(2)));
			database.CUD("UPDATE gamedie SET round = 1 WHERE idgame = " + idgame + " AND dienumber = " +  playableDices.get(i).getDieNumber() + " AND diecolor = '" + playableDices.get(i).getDieColor() +"'");
		}

	}

	public int getIdGame() {
		return idgame;
	}

	public int getOwnId() {
		for(Player p : players) {
			if(p.getSelf()) {
				yourself = p.getPlayerId();
			}
		}
		return yourself;
	}


	public ArrayList<ArrayList<Object>> getAvailableGames(String u) {
		return database.Select(
				"SELECT game_idgame, COUNT(game_idgame) AS amountPlayers FROM player WHERE game_idgame IN (SELECT game_idgame FROM player where username = '"
						+ u	+ "' AND playstatus_playstatus = 'Uitdager') GROUP BY game_idgame HAVING amountPlayers < 4");
	}

	public ArrayList<ArrayList<Object>> getRejectedGames(String u) {
		return database
				.Select("SELECT game_idgame FROM player WHERE game_idgame in (SELECT game_idgame FROM player where username = '"
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
		System.out.println(idgame);
		for (int i = 1; i < 25; i++) {
			token.add(new Gamefavortoken(i));
			database.CUD("INSERT INTO gamefavortoken (idfavortoken, idgame) VALUES (" + i + "," + idgame + ")");
		}
	}
	
	public void assignTokensToPlayer() {
		for(Player p : players) {
			if(p.getSelf()) {
					p.setTokenAmount(p.getPc().getDifficulty());
					database.CUD("UPDATE gamefavortoken SET idplayer = " + p.getPlayerId() + " WHERE idplayer is null AND idgame = " + idgame +" LIMIT " + p.getPc().getDifficulty() + ";");
			}
		}
	}
	
	public void updateTokenArrayList(int difficulty) {
		for (int i = 1; i <= difficulty; i++) {
			database.CUD("UPDATE gamefavortoken SET idplayer = " + yourself + " WHERE idFavortoken = " + i +" AND idgame = " + idgame + ";");
		}
	}
	
	public void updatePlayedTokens(int amountPlayed) {
		
	}
	
	public void addTokensToGametoolcard(int amount, int toolcardid) {
		if(getLeftoverTokens() >= amount) {
			database.CUD("UPDATE gamefavortoken SET gametoolcard = " + getGametoolcard(toolcardid) + " WHERE idgame = " + idgame +"");
		}
	}
	
	public int getLeftoverTokens() {
		return (int)database.Select("SELECT count(*) FROM gamefavortoken WHERE idgame = " + idgame + " AND idplayer = " + yourself + " AND round is null;").get(0).get(0);
	}
	
	public int getGametoolcard(int toolcardid) {
		return (int)database.Select("SELECT gametoolcard from gametoolcard WHERE idgame = " + idgame +" AND idtoolcard = " + toolcardid + "").get(0).get(0);
	}
	
	
	public int getGamemode() {
		return players.size();
	}
	
	public void addOptionsToDB(ArrayList<Integer> randomIDS) {
		for (int i = 0; i < randomIDS.size(); i++) {
				if(i >= 12) {
					database.CUD("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES (" + randomIDS.get(i) + ", " + players.get(3).getPlayerId() + ")");
					continue;
				}
				if(i >= 8) {
					database.CUD("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES (" + randomIDS.get(i) + ", " + players.get(2).getPlayerId() + ")");
					continue;
				}
				if(i >= 4) {
					System.out.println("Speler 2");
					database.CUD("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES (" + randomIDS.get(i) + ", " + players.get(1).getPlayerId() + ")");
					continue;
				}
				if(i >= 0) {
					System.out.println("Speler 1");
					database.CUD("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES (" + randomIDS.get(i) + ", " + players.get(0).getPlayerId() + ")");
					continue;
				}
//				System.out.println("waarde: " + randomIDS.get(i));
//				database.CUD("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES (" + randomIDS.get(i) + ", " + players.get(j).getPlayerId() + ")");
			
		}
	}
	
	public void setSelf() {
		for (int i = 0; i < players.size(); i++) {
			if(players.get(i).getSelf()) {
				self = players.get(i);
			}
		}
	}
	
	public ArrayList<Integer> getOwnOptions(){
		ArrayList<Integer> ownOptions = new ArrayList<>();
		for (Player p : players) {
		if(p.getSelf()) {
			for (int i = 0; i < 4; i++) {
				ownOptions.add((int)database.Select("SELECT patterncard_idpatterncard FROM patterncardoption WHERE player_idplayer = " + p.getPlayerId() +";").get(i).get(0));
				}
			}
		}
		return ownOptions;
	}
	
	public boolean hasChosen() {
		for (Player p : players) {
			if(p.getPatternId() == 0) {
				
				return false;
			}
		}
		return true;
	}
	
	
	public ArrayList<Integer> getChosenIds(){
		ArrayList<Integer> chosenId = new ArrayList<Integer>();
		for (int i = 0; i < players.size(); i++) {
			chosenId.add((int)database.Select("SELECT patterncard_idpatterncard FROM player WHERE game_idgame = " + idgame + ";").get(i).get(0));
		}
		return chosenId;
	}

	public boolean checkIfSelf() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean checkIfIPickedPatternCard(String username) {
		for(Player p : players) {
			if(p.getUsername().equals(username)) {
				if(p.getPatternId()!=0) {
					return true;
				}else {
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
		if(database.Select("SELECT patterncardoption.patterncard_idpatterncard FROM patterncardoption LEFT JOIN player ON player_idplayer = idplayer WHERE game_idgame = " + idgame + ";").isEmpty()) {
			return false;
		}
		return true;
	}

	public void createNewGame() {
		database.CUD("insert into game(creationdate) values (now())");
		this.idgame = (int)database.Select("select max(idgame) from game").get(0).get(0);
		
	}
	
	public void insertChosenID(int id) {
		for (Player p : players) {
			if(p.getSelf()) {
				p.setPatternCardId(id);
				p.setPc();
				database.CUD("UPDATE player SET patterncard_idpatterncard = " + id + " WHERE idplayer = " + p.getPlayerId() +";");
			}
		}
		
	}
	
	public int getOwnPatternId() {
		for (Player p : players) {
			if(p.getSelf()) {
				return p.getPatternId();
			}
		}
		
		return 0;
	}

	public long getHighestSeNumber() {
		return (long)database.Select("select max(seqnr) + 1 from player where game_idgame = " + this.idgame).get(0).get(0);
	}
	
}
	
	

