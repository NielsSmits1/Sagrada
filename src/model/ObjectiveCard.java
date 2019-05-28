package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;

public class ObjectiveCard {

	//instance variables
	private Random random;
	private int card1;
	private int card2;
	private db database;
	
	
	//constructor
	public ObjectiveCard() {
		random = new Random();
		database = new db();
		generateRandomInts();

	}
	
	
	public ArrayList<ArrayList<Object>> getObjectivecardsFromDatabase() {
		String query = ("SELECT idtoolcard, description FROM tjpmsalt_db2.toolcard WHERE idtoolcard = " + card1
				+ " OR idtoolcard = " + card2 + "");
		return database.Select(query);
	}
	
	private void generateRandomInts() {
		card1 = random.nextInt(12) + 1;
		card2 = random.nextInt(12) + 1;

		while (card2 == card1) {
			card2 = random.nextInt(12) + 1;
		}
	}
}
