package model;

import java.util.ArrayList;

import Database.db;
import controller.GameController;

public class Game {

	private db database = new db();
	private ArrayList<ArrayList<Object>> p;
	private ArrayList<Dice> diceArray;
	private GameController controller;
	
	public Game(GameController controller) {
		diceArray = new ArrayList<>();
		database = new db();
		p = getSelect();
		setDiceArray();
		this.controller = controller;
	}
	
	public ArrayList<ArrayList<Object>> getSelect() {
        return database.Select("SELECT * FROM tjpmsalt_db2.gamedie;");
    }
	
	private void setDiceArray() {
		for(int i = 0;i<90;i++) {
			diceArray.add(new Dice());
			diceArray.get(i).setDieNumber((int)p.get(i).get(1));
			diceArray.get(i).setEyes((int)p.get(i).get(3));
			switch((String) p.get(i).get(2)) {
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
			System.out.println("" + diceArray.get(i).getDieNumber() + " " + diceArray.get(i).getDieColor() + " " + diceArray.get(i).getEyes());
		}
	}
	
	public ArrayList<Dice> getDiceArray(){
		return diceArray;
	}
	
}
