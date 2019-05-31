package controller;

import java.util.ArrayList;

import View.ObjectiveCardPane;
import View.ToolCardPane;
import model.ObjectiveCard;
import model.Toolcard;

public class CardController {
	private Toolcard toolcard;
	private GameController gamecontroller;
	private ArrayList<ToolCardPane> toolcardpanes;
	private ArrayList<ObjectiveCardPane> objectiveCards;
	private ObjectiveCard objectiveCard;

	public CardController(GameController gc) {
		gamecontroller = gc;
		toolcard = new Toolcard(this);
		objectiveCard = new ObjectiveCard(this);
		toolcardpanes = new ArrayList<>();
		objectiveCards = new ArrayList<>();
	}

	public void setToolcardOneActive() {
		gamecontroller.setToolcardOneActive();
	}

	public void setToolcardSixActive() {
		gamecontroller.setToolcardSixActive();
	}

	public void setToolcardTenActive() {
		gamecontroller.setToolcardTenActive();
	}

	public void setToolcardElevenActive() {
		gamecontroller.setToolcardElevenActive();

	}

	public void enableDiceMovement(int i) {
		gamecontroller.enableDiceMovement(i);
	}

	public void setToolcards() {
		toolcardpanes.add(new ToolCardPane(toolcard.getIds().get(0), this));
		toolcardpanes.add(new ToolCardPane(toolcard.getIds().get(1), this));
		toolcardpanes.add(new ToolCardPane(toolcard.getIds().get(2), this));
	}
	
	public ArrayList<ToolCardPane> getToolCards() {
		return toolcardpanes;
	}
	
	public void setObjectiveCards() {
		objectiveCards.add(new ObjectiveCardPane(objectiveCard.getIds().get(0)));
		objectiveCards.add(new ObjectiveCardPane(objectiveCard.getIds().get(1)));
		objectiveCards.add(new ObjectiveCardPane(objectiveCard.getIds().get(2)));
	}

	public ArrayList<ObjectiveCardPane> getObjectiveCards() {
		return objectiveCards;
	}


	public void toolcardClicked(int id) {
		toolcard.activateToolcard(id);
	}

	public void setPlayerTokens(int minus) {
		gamecontroller.setPlayerTokens(minus);
	}
	
	public ArrayList<Integer> getToolcards(){
		return toolcard.getToolCards();
	}
	
//	public void setGameCards() {
//		for (int i = 0; i < getToolcards().size(); i++) {
//			gamecontroller.setGameCard(getToolcards().get(i));
//		}
//	}
	
	public void insertCards() {
		toolcard.insertToolcards();
		objectiveCard.insertObjectivecard();
	}
	
	public int getIdGame() {
		return gamecontroller.getIdGame();
	}
}
