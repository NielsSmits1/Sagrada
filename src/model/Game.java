package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;

public class Game {
	private db database = new db();
	private ArrayList<ArrayList<Object>> diceData;
	private ArrayList<Dice> diceArray;
	private ArrayList<Dice> playableDices;
	private ArrayList<Round> rounds;
	private int idgame;
	private int yourself;
	private Random r;
	public Game() {
		r = new Random();
		diceArray = new ArrayList<>();
		database = new db();
		idgame = (int)createNewGameId();
		insertDicesIntoDatabase();
		diceData = getSelect();
		setDiceArray();
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
				.Select("SELECT (idgame+1) AS newGameId FROM tjpmsalt_db2.game ORDER BY idgame DESC LIMIT 1;").get(0)
				.get(0);
	}
	
	public void setOwnId(int chosenPatternId) {
		yourself = (int) getNewId();
		database.CUD("INSERT INTO tjpmsalt_db2.player (idplayer, username, game_idgame, playstatus_playstatus, isCurrentPlayer, private_objectivecard_color, patterncard_idpatterncard ) "
				+ "VALUES("+ yourself+ ",'niels'," + idgame + ", 'geaccepteerd', 0, 'blauw' , " + chosenPatternId + ")");
	}
	
	public long getNewId() {
		return (long) database.Select("SELECT (idplayer+1) AS newPlayerId FROM tjpmsalt_db2.player ORDER BY game_idgame DESC LIMIT 1;").get(0).get(0);
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

	//returns the arraylist with dices.
	public ArrayList<Dice> getDiceArray() {
		return diceArray;
	}


	// inserts standard values for dices for a new game.
	public void insertDicesIntoDatabase() {
		database.CUD("insert into game(idgame, creationdate) VALUES("+ idgame +", CURRENT_TIME());");
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
			int randomDie = r.nextInt(17)+1;
			String[] colors = {"blauw", "groen", "geel","rood", "paars"};
			String color = colors[r.nextInt(5)];
			for (int j = 0; j < diceArray.size(); j++) {
				if(diceArray.get(j).getDieNumber() == randomDie && diceArray.get(j).getDieColor() == color) {
					playableDices.add(diceArray.get(j));
					diceArray.remove(j);
				}
			}
//				if(database.Select("SELECT FROM gamedie WHERE dieumber = " + randomDie + " AND diecolor = '" + color + "'"))
//			}
//			System.out.println("" + diceArray.get(randomDie).getDieNumber());
			
		}
	}


	
	public int getIdGame() {
		return idgame;
	}
	
	public int getOwnId() {
		return yourself;
	}
	
}
