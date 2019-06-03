package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;
import controller.CardController;

public class Toolcard {
	private Random random;
	private int card1;
	private int card2;
	private int card3;
	private db database;
	private CardController cardController;

	public Toolcard(CardController cc) {
		this.cardController = cc;
		random = new Random();
		database = new db();
		
	}
	
	public void insertToolcards() {
		generateRandomInts();
	}

	public ArrayList<ArrayList<Object>> getToolcardsFromDatabase() {
		String query = ("SELECT idtoolcard, description FROM toolcard WHERE idtoolcard = " + card1
				+ " OR idtoolcard = " + card2 + " OR idtoolcard = " + card3 + "");
		return database.Select(query);
	}
	
	public ArrayList<Integer> getIds(){
		ArrayList<Integer> Ids = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Ids.add((int)database.Select("SELECT idtoolcard FROM gametoolcard WHERE idgame = " + cardController.getIdGame() + "").get(i).get(0));
		}
		return Ids;
		
	}

	private void generateRandomInts() {
		card1 = random.nextInt(12) + 1;
		card2 = random.nextInt(12) + 1;
		card3 = random.nextInt(12) + 1;

		while (card2 == card1) {
			card2 = random.nextInt(12) + 1;
		}

		while (card3 == card1 || card3 == card2) {
			card3 = random.nextInt(12) + 1;
		}
		
		database.CUD("INSERT INTO gametoolcard (idtoolcard, idgame) VALUES (" + card1 + "," + cardController.getIdGame() + ");");
		database.CUD("INSERT INTO gametoolcard (idtoolcard, idgame) VALUES (" + card2 + "," + cardController.getIdGame() + ");");
		database.CUD("INSERT INTO gametoolcard (idtoolcard, idgame) VALUES (" + card3 + "," + cardController.getIdGame() + ");");

	}
	
	public int alreadyBought(int idgame, int idtoolcard) {
		if(database.Select("SELECT gametoolcard.gametoolcard FROM gametoolcard LEFT JOIN gamefavortoken ON gametoolcard.gametoolcard = gamefavortoken.gametoolcard WHERE gametoolcard.idgame = " + idgame +" AND idtoolcard = " + idtoolcard +" AND gamefavortoken.gametoolcard is NOT NULL;").isEmpty()) {
			return 1;
		}
		return 2;
	}

	public void activateToolcard(int id) {
		switch (id) {
		case 1:
			activateToolCardOne();

			break;
		case 2:
			activateToolCardTwo();

			break;
		case 3:
			activateToolCardThree();

			break;
		case 4:

			break;
		case 5:

			break;
		case 6:
			activateToolCardSix();

			break;
		case 7:
			activateToolCardSeven();
			break;
		case 8:

			break;
		case 9:
			activateToolCardNine();
			break;
		case 10:
			activateToolCardTen();
			break;
		case 11:
			activateToolCardEleven();

			break;
		case 12:

			break;

		default:
			break;
		}
	}

	private void activateToolCardOne() {
		cardController.setToolcardOneActive();

	}

	private void activateToolCardTwo() {
		cardController.enableDiceMovement(2);

	}

	private void activateToolCardThree() {
		cardController.enableDiceMovement(3);

	}

	private void activateToolCardFour() {
		// TODO Auto-generated method stub

	}

	private void activateToolCardFive() {
		// TODO Auto-generated method stub

	}

	private void activateToolCardSix() {
		cardController.setToolcardSixActive();

	}

	private void activateToolCardSeven() {
		// TODO Auto-generated method stub
		cardController.setToolcardSevenActive();
	}

	private void activateToolCardEight() {
		// TODO Auto-generated method stub

	}

	private void activateToolCardNine() {
		// TODO Auto-generated method stub
		cardController.enableDiceMovement(9);
	}

	private void activateToolCardTen() {
		cardController.setToolcardTenActive();

	}

	private void activateToolCardEleven() {
		cardController.setToolcardElevenActive();

	}

	private void activateToolCardTwelve() {
		// TODO Auto-generated method stub

	}
	
	public long getTokensPlaced(int id) {
		int gametoolcard =  (int)database.Select("SELECT gametoolcard.gametoolcard FROM gametoolcard  WHERE gametoolcard.idgame = " + cardController.getIdGame() +" AND idtoolcard = " + id +"").get(0).get(0);
		return (long)database.Select("select count(*) from gamefavortoken where idgame = " + cardController.getIdGame() + " AND gametoolcard = " + gametoolcard + "").get(0).get(0);
	}

	public int getCardOneId() {
		return card1;
	}

	public int getCardTwoId() {
		return card2;
	}

	public int getCardThreeId() {
		return card3;
	}

	public String getCardOneDescription() {
		return (String) getToolcardsFromDatabase().get(0).get(1);
	}

	public String getCardTwoDescription() {
		return (String) getToolcardsFromDatabase().get(1).get(1);
	}

	public String getCardThreeDescription() {
		return (String) getToolcardsFromDatabase().get(2).get(1);
	}
	
	public ArrayList<Integer> getToolCards(){
		ArrayList<Integer> toolcards = new ArrayList<>();
		toolcards.add(card1);
		toolcards.add(card2);
		toolcards.add(card3);
		return toolcards;
	}
}
