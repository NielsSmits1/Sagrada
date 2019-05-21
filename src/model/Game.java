package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;

public class Game {
	private db database = new db();
	private ArrayList<ArrayList<Object>> diceData;
	private ArrayList<Round> rounds;
	private ArrayList<ArrayList<Object>> forwardPlayer;
	private ArrayList<ArrayList<Object>> backwardPlayer;
	private ArrayList<ArrayList<Object>> normalTurnes;
	private ArrayList<Dice> diceArray;
	private ArrayList<Dice> playableDices;
	private int idgame; 
	private int yourself;
	private Random r;
	private Player self;
	public Game(Player self) {

	}

	
	// private Random r;

	public Game() {
		r = new Random();
		diceArray = new ArrayList<>();
		database = new db();
		idgame = (int) createNewGameId();
		insertDicesIntoDatabase();
		diceData = getSelect();
		setDiceArray();
		buildGameTurns();
		checkIfGameHasStarted();
		this.self = self;
	}
//	private int getLastRound() {
//		ArrayList<ArrayList<Object>> maxRound = database.Select("select round, roundtrack from gamedie where idgame = " + this.idgame + " and round = (select max(round) from gamedie"
//				+ " where idgame = " + this.idgame +")");
//		if(maxRound.isEmpty()) {
//			// nog geen rondes geweest
//			return 0;
//		}else {
//			// check of de laatst gespeelde ronde voorbij is
//			if(maxRound.get(maxRound.size()).get(1) == maxRound.get(0).get(0)) {
//				// laatste ronde gespeeld
//				return (int)maxRound.get(0).get(0) + 1;
//			}else {
//				return (int)maxRound.get(0).get(0);
//			}
//			
//		}
//	}
//	private void buildRounds() {
//		// als lastround() 6 is moet ie nog 5 rondes spelen
//		for(int i = getLastRound(); i < 11; i++) {
//			Round r = new Round(i);
//			r.buildTurnes(self);
//		}
//	}

	private void buildGameTurns() {
		forwardPlayer = database.Select("select idplayer, username, seqnr from player where game_idgame = " + this.idgame);
		for(ArrayList<Object> a : database.Select("select idplayer, username, seqnr from player where game_idgame = " + this.idgame + " order by idplayer desc")) {
			forwardPlayer.add(a);
		}
		
		for(int i = 0; i < forwardPlayer.size(); i++) {
			
		}
	}
	private void checkIfGameHasStarted() {
		// check met select of game begonnen 
		// als dat niet so is build de game 
		if(gameStarted()) {
			
		}else {
			
		}
	}
	
	private ArrayList<ArrayList<Object>> getLastRound() {
		return database.Select("SELECT MAX(gd.round), p.username, p.seqnr FROM gamedie AS gd " + 
				"LEFT JOIN playerframefield AS pff ON gd.dienumber = pff.dienumber AND gd.diecolor = pff.diecolor LEFT JOIN player AS "+
				"p ON pff.player_idplayer = p.idplayer " + 
				"WHERE gd.idgame = " + this.idgame +" AND p.isCurrentPlayer = 1");
	}

	private ArrayList<ArrayList<Object>> getPlayersInGame() {
		return database.Select("select username from player where game_idgame = " + this.idgame);
	}
	public void checkofso() {
		ArrayList<ArrayList<Object>> pl = this.getPlayersInGame();
		ArrayList<ArrayList<Object>> la = this.getLastRound();
		ArrayList<Object> rn = new ArrayList<Object>();
		boolean check = false;
		for(ArrayList<Object> a: pl) {
			if(a.get(0)==la.get(0).get(1)) {
				check = true;
			}if(check) {
				rn.add(a);
			}
		}
	}
	
	private boolean gameStarted() {
		if(getLastRound().get(0).get(0) == null) {
			return false;
		}else {
			return true;
		}
	}
//	private void buildRounds() {
//		for(int x = 0; x < 10; x++) {
//			Round r = new Round();
//			r.buildTurnes(this.idgame);
//			rounds.add(r);
//		}
//	}
	//creates a new gameId based on the highest current gameId + 1.

	private long createNewGameId() {
		return (long) database
				.Select("SELECT (idgame+1) AS newGameId FROM tjpmsalt_db2.game ORDER BY idgame DESC LIMIT 1;").get(0)
				.get(0);
	}

	public void setOwnId(int chosenPatternId) {
		yourself = (int) getNewId();
		database.CUD(
				"INSERT INTO tjpmsalt_db2.player (idplayer, username, game_idgame, playstatus_playstatus, isCurrentPlayer, private_objectivecard_color, patterncard_idpatterncard ) "
						+ "VALUES(" + yourself + ",'niels'," + idgame + ", 'geaccepteerd', 0, 'blauw' , "
						+ chosenPatternId + ")");
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
		}
	}

	// returns the arraylist with dices.
	public ArrayList<Dice> getDiceArray() {
		return diceArray;
	}

	// inserts standard values for dices for a new game.
	public void insertDicesIntoDatabase() {
		database.CUD("insert into game(idgame, creationdate) VALUES(" + idgame + ", CURRENT_TIME());");
		database.CUD(
				"INSERT INTO gamedie (idgame, dienumber, diecolor) SELECT " + idgame + ", number, color FROM die;");
	}

	public void updateEyes(int eyes, int dienumber, String color) {
		
		database.CUD("UPDATE gamedie SET eyes = " + eyes + " WHERE idgame = " + idgame + " AND dienumber = " + dienumber
				+ " AND diecolor = '" + color + "';");
	}

	public ArrayList<Dice> getPlayableDices() {

		return playableDices;
	}


	public void setPlayableDices() {
		playableDices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomDie = r.nextInt(17) + 1;
			String[] colors = { "blauw", "groen", "geel", "rood", "paars" };
			String color = colors[r.nextInt(5)];
			for (int j = 0; j < diceArray.size(); j++) {
				if (diceArray.get(j).getDieNumber() == randomDie && diceArray.get(j).getDieColor() == color) {
					playableDices.add(diceArray.get(j));
					diceArray.remove(j);
				}
			}
			// if(database.Select("SELECT FROM gamedie WHERE dieumber = " + randomDie + "
			// AND diecolor = '" + color + "'"))
			// }
			// System.out.println("" + diceArray.get(randomDie).getDieNumber());

		}
	}
	
	public int getIdGame() {
		return idgame;
	}

	public int getOwnId() {
		return yourself;
	}

}
