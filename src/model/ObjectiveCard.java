package model;

import java.util.ArrayList;
import java.util.Random;

import Database.Db;
import controller.CardController;

public class ObjectiveCard {

	//instance variables
	private Random random;
	private int card1;
	private int card2;
	private int card3;
	private CardController controller;
	
	//constructor
	public ObjectiveCard(CardController tc) {
		random = new Random();
		controller = tc;
	}
	
	public int getCard1() {
		return card1;
	}

	public int getCard2() {
		return card2;
	}
	
	public int getCard3() {
		return card3;
	}

	public ArrayList<ArrayList<Object>> getObjectivecardsFromDatabase() {
		String query = ("SELECT idpublic_objectivecard FROM public_objectivecard WHERE idpublic_objectivecard = " + card1
				+ " OR idpublic_objectivecard = " + card2 + " OR idpublic_objectivecard = " + card3+ ";");
		return Db.select(query);
	}
	
	public ArrayList<Integer> getIds(){
		ArrayList<Integer> Ids = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Ids.add((int)Db.select("SELECT idpublic_objectivecard FROM sharedpublic_objectivecard WHERE idgame = " + controller.getIdGame() + "").get(i).get(0));
		}
		return Ids;
		
	}
	
//	public void insertObjectivecard() {
//		generateRandomInts();
//		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card1 + "," + controller.getIdGame() + ");");
//		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card2 + "," + controller.getIdGame() + ");");
//		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card3 + "," + controller.getIdGame() + ");");
//	}
//	private void generateRandomInts() {
//		card1 = random.nextInt(10) + 1;
//		card2 = random.nextInt(10) + 1;
//		card3 = random.nextInt(10) + 1;
//		while(card2 == card1) {
//			card2 = random.nextInt(10) + 1;
//		}
//		while(card3 == card1 || card3 == card2) {
//			card3 = random.nextInt(10) + 1;
//		}
//		
//		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card1 + "," + controller.getIdGame() + ");");
//		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card2 + "," + controller.getIdGame() + ");");
//		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card3 + "," + controller.getIdGame() + ");");
//	}
}
