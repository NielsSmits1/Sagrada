package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;

public class Game {
	private db database = new db();
	private ArrayList<ArrayList<Object>> diceData;
	private ArrayList<Dice> diceArray;
	private ArrayList<Dice> playableDices;
	private int idgame; 
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
