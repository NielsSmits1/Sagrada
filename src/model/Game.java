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
	private ArrayList<Player> players = new ArrayList<Player>();
	private int idgame; 
	private int yourself;
	private Random r;

	
	// private Random r;

	public void addPlayer(Player param) {
		insertPlayer(param);
		
	}
	public void addPlayer(Player param, String status) {
		insertPlayer(param, status);
		
	}
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	public Game() {
		r = new Random();
		diceArray = new ArrayList<>();
		database = new db();
		idgame = (int) createNewGameId();
		insertDicesIntoDatabase();
		diceData = getSelect();
		setDiceArray();
		//buildGameTurns();
		checkIfGameHasStarted();
		
	}
	public boolean alreadyInGame(Player player) {
		for(ArrayList<Object>  a : getPlayersInGame()) {
			String s = (String)a.get(0);
			if(s.equals(player.getUsername())) {
				return true;
			}
		}
		return false;
			 
	}
	public void createNewGame() {
	    database.CUD("INSERT INTO GAME(creationdate) VALUES (now())");
	    idgame = (int) createNewGameId();
	}
	public void insertPlayer(Player p) {
    	database.CUD("INSERT INTO PLAYER(username,game_idgame,playstatus_playstatus,isCurrentPlayer,private_objectivecard_color) VALUES ('" + p.getUsername() +"', " + this.idgame + " , 'Uitgedaagde', 0, 'rood')");  // rood has to be variable between all colors
    }
	public void insertPlayer(Player p, String status) {
    	database.CUD("INSERT INTO PLAYER(username,game_idgame,playstatus_playstatus,isCurrentPlayer,private_objectivecard_color) VALUES ('" + p.getUsername() +"', " + this.idgame + " , '" + status + "', 0, 'rood')");  // rood has to be variable between all colors
        System.out.println(this.idgame);
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

	private long createNewGameId() {
		return (int) database
				.Select("SELECT (idgame) AS newGameId FROM tjpmsalt_db2.game ORDER BY idgame DESC LIMIT 1;").get(0)
				.get(0);
	}

	public void setOwnId(int chosenPatternId) {
		yourself = (int) getNewId();
		database.CUD(
				"INSERT INTO tjpmsalt_db2.player (idplayer, username, game_idgame, playstatus_playstatus, isCurrentPlayer, private_objectivecard_color, patterncard_idpatterncard ) "
						+ "VALUES(" + yourself + ",'niels'," + idgame + ", 'geaccepteerd', 0, 'blauw' , "
						+ chosenPatternId + ")");
	}
	
	public void setOwnId() {
		yourself = (int) getNewId();
		database.CUD(
				"INSERT INTO tjpmsalt_db2.player (idplayer, username, game_idgame, playstatus_playstatus, isCurrentPlayer, private_objectivecard_color) "
						+ "VALUES(" + yourself + ",'niels'," + idgame + ", 'geaccepteerd', 0, 'blauw')");
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
	
	public void getDiceWithChosenValue(int dienumber, String color, int value, int chosenvalue) {
		diceArray.add(new Dice(dienumber, color, value));
		for (int i = 0; i < playableDices.size(); i++) {
			if(playableDices.get(i).getDieNumber() == dienumber && playableDices.get(i).getDieColor().equals(color)) {
				playableDices.remove(i);
			}
		}
		int index = r.nextInt(diceArray.size());
		Dice newDice = diceArray.get(index);
		while(newDice.getEyes() != chosenvalue) {
			index = r.nextInt(diceArray.size());
			newDice = diceArray.get(index);
		}
		diceArray.remove(index);
		playableDices.add(newDice);
	}


	public void setPlayableDices() {
		playableDices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int randomDie = r.nextInt(18) + 1;
			String[] colors = { "blauw", "groen", "geel", "rood", "paars" };
			String color = colors[r.nextInt(5)];
			for (int j = 0; j < diceArray.size(); j++) {
				//TODO Make sure the same combination can't be added multiple times, a solution might be the random function in SQL, and also update the round.
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
	public ArrayList<ArrayList<Object>> countOpenChallenges(String u) {
		return database.Select("SELECT game_idgame, count(game_idgame) as amountPlayers FROM player where game_idgame in (select game_idgame from player where username ='" + u +"') AND playstatus_playstatus = 'Uitgedaagde' group by game_idgame having amountPlayers < 4");
	}
	public void setGameId(int gid) {
		this.idgame = gid;
		
	}

}
