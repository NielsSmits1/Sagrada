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
	private Random r;
	private Player self;
	public Game(Player self) {
		r = new Random();
		diceArray = new ArrayList<>();
		database = new db();
		idgame = (int)createNewGameId();
		insertDicesIntoDatabase();
		diceData = getSelect();
		setDiceArray();
		buildGameTurns();
		checkIfGameHasStarted();
		this.self = self;
	}
	private int getLastRound() {
		ArrayList<ArrayList<Object>> maxRound = database.Select("select round, roundtrack from gamedie where idgame = " + this.idgame + " and round = (select max(round) from gamedie"
				+ " where idgame = " + this.idgame +")");
		if(maxRound.isEmpty()) {
			// nog geen rondes geweest
			return 0;
		}else {
			// check of de laatst gespeelde ronde voorbij is
			if(maxRound.get(maxRound.size()).get(1) == maxRound.get(0).get(0)) {
				// laatste ronde gespeeld
				return (int)maxRound.get(0).get(0) + 1;
			}else {
				return (int)maxRound.get(0).get(0);
			}
			
		}
	}
	private void buildRounds() {
		// als lastround() 6 is moet ie nog 5 rondes spelen
		for(int i = getLastRound(); i < 11; i++) {
			Round r = new Round(i);
			r.buildTurnes(self);
		}
	}

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
	private void buildRounds() {
		for(int x = 0; x < 10; x++) {
			Round r = new Round();
			r.buildTurnes(this.idgame);
			rounds.add(r);
		}
	}
	//creates a new gameId based on the highest current gameId + 1.
	private long createNewGameId() {
		return (long) database
				.Select("SELECT (idgame+1) AS newGameId FROM tjpmsalt_db2.gamedie ORDER BY idgame DESC LIMIT 1;").get(0)
				.get(0);
	}

	// gets all die information where id game equals the new game.
	public ArrayList<ArrayList<Object>> getSelect() {
		return database.Select("SELECT * FROM tjpmsalt_db2.gamedie WHERE idgame =" + idgame + " ORDER BY dienumber;");
	}

	//adds all dices to a dicearraylist.
	private void setDiceArray() {
		for (int i = 0; i < diceData.size(); i++) {
			diceArray.add(new Dice());
			diceArray.get(i).setDieNumber((int) diceData.get(i).get(1));
			switch ((String) diceData.get(i).get(2)) {
			case "blauw":
				diceArray.get(i).setDieColor("BLUE");
				break;
			case "rood":
				diceArray.get(i).setDieColor("RED");
				break;
			case "geel":
				diceArray.get(i).setDieColor("YELLOW");
				break;
			case "groen":
				diceArray.get(i).setDieColor("GREEN");
				break;
			case "paars":
				diceArray.get(i).setDieColor("PURPLE");
				break;
			default:
				diceArray.get(i).setDieColor("WHITE");
				break;
			}
			updateEyes(diceArray.get(i).getEyes(), diceArray.get(i).getDieNumber(), (String)diceData.get(i).get(2));
		}
	}

	//returns the arraylist with dices.
	public ArrayList<Dice> getDiceArray() {
		return diceArray;
	}


	// inserts standard values for dices for a new game.
	public void insertDicesIntoDatabase() {
		database.CUD("insert into game(idgame) VALUES("+ idgame + ");");
		database.CUD(
				"INSERT INTO gamedie (idgame, dienumber, diecolor) SELECT " + idgame + ", number, color FROM die;");
	}
	
	public void updateEyes(int eyes, int dienumber, String color) {
		database.CUD("UPDATE gamedie SET eyes = " + eyes + " WHERE idgame = " + idgame + " AND dienumber = "+ dienumber +" AND diecolor = '"+ color + "';");
	}
	
	public ArrayList<Dice> getPlayableDices(){
		
		return playableDices;
	}
	
	public void setPlayableDices() {
		playableDices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomDie = r.nextInt(diceArray.size());
			playableDices.add(diceArray.get(randomDie));
			System.out.println("" + diceArray.get(randomDie).getDieNumber());
			diceArray.remove(randomDie);
		}
	}
	
	public int getIdGame() {
		return idgame;
	}
	
}
