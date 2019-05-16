package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;

public class Toolcard {
	private Random random;
	private int card1;
	private int card2;
	private int card3;
	private db database;
	
	public Toolcard() {
		random = new Random();
		database = new db();
		generateRandomInts();
	}
	
	public ArrayList<ArrayList<Object>> getToolcardsFromDatabase() {
		String query = ("SELECT idtoolcard, description FROM tjpmsalt_db2.toolcard WHERE idtoolcard = "+card1+" OR idtoolcard = "+card2+" OR idtoolcard = "+card3 +"");
		return database.Select(query);
		
	}
	
	private void generateRandomInts() {
		card1 = random.nextInt(12)+1;		
		card2 = random.nextInt(12)+1;
		card3 = random.nextInt(12)+1;
	
		while(card2 == card1) {
			card2 = random.nextInt(12)+1;
		}
		
		while(card3 == card1 || card3 == card2) {
			card3 = random.nextInt(12)+1;
		}
		
	}
}
