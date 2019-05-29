package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;
import controller.CardController;

public class ObjectiveCard {

	//instance variables
	private Random random;
	private int card1;
	private int card2;
	private db database;
	private CardController controller;
	
	//constructor
	public ObjectiveCard(CardController tc) {
		random = new Random();
		database = new db();
		controller = tc;
		generateRandomInts();
	}
	
	public int getCard1() {
		return card1;
	}

	public int getCard2() {
		return card2;
	}

	public ArrayList<ArrayList<Object>> getObjectivecardsFromDatabase() {
		String query = ("SELECT idpublic_objectivecard FROM public_objectivecard WHERE idpublic_objectivecard = " + card1
				+ " OR idpublic_objectivecard = " + card2 + "");
		return database.Select(query);
	}
	
	public ArrayList<Integer> getIds(){
		ArrayList<Integer> Ids = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			Ids.add((int)database.Select("SELECT idpublic_objectivecard FROM sharedpublic_objectivecard WHERE idgame = " + controller.getIdGame() + "").get(i).get(0));
		}
		return Ids;
		
	}
	
	public void insertObjectivecard() {
		generateRandomInts();
		database.CUD("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card1 + "," + controller.getIdGame() + ");");
		database.CUD("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card2 + "," + controller.getIdGame() + ");");
	}
	private void generateRandomInts() {
		card1 = random.nextInt(10) + 1;
		card2 = random.nextInt(10) + 1;

		while (card2 == card1) {
			card2 = random.nextInt(10) + 1;
		}
	}
}
