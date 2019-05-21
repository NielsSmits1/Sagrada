package model;

import java.util.ArrayList;
import java.util.Random;

import Database.db;
import controller.ToolcardController;

public class Toolcard {
	private Random random;
	private int card1;
	private int card2;
	private int card3;
	private db database;
	private ToolcardController toolcardController;

	public Toolcard(ToolcardController toolcardController) {
		this.toolcardController = toolcardController;
		random = new Random();
		database = new db();
		generateRandomInts();
	}

	public ArrayList<ArrayList<Object>> getToolcardsFromDatabase() {
		String query = ("SELECT idtoolcard, description FROM tjpmsalt_db2.toolcard WHERE idtoolcard = " + card1
				+ " OR idtoolcard = " + card2 + " OR idtoolcard = " + card3 + "");
		return database.Select(query);
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

	}
	
	public void activateToolcard(int id) {
		switch (id) {
		case 1: activateToolCardOne();
			
			break;
		case 2: activateToolCardTwo();
			
			break;
		case 3: activateToolCardThree();
			
			break;
		case 4:
			
			break;
		case 5:
			
			break;
		case 6:
			
			break;
		case 7:
			
			break;
		case 8:
			
			break;
		case 9:
			
			break;
		case 10:
			
			break;
		case 11:
			
			break;
		case 12:
			
			break;

		default:
			break;
		}
	}

	private void activateToolCardOne() {
		toolcardController.setToolcardActive();
		
	}
	
	private void activateToolCardTwo() {
		toolcardController.enableDiceMovement(2);
		
		
	}
	
	private void activateToolCardThree() {
		toolcardController.enableDiceMovement(3);

		
	}
	
	private void activateToolCardFour() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardFive() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardSix() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardSeven() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardEight() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardNine() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardTen() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardEleven() {
		// TODO Auto-generated method stub
		
	}
	
	private void activateToolCardTwelve() {
		// TODO Auto-generated method stub
		
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
}
