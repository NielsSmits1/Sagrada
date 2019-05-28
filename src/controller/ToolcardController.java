package controller;

import java.util.ArrayList;

import View.ObjectiveCardPane;
import View.ToolCardPane;
import model.ObjectiveCard;
import model.Toolcard;

public class ToolcardController {
	private Toolcard toolcard;
	private GameController gamecontroller;
	private ArrayList<ToolCardPane> toolcardpanes;
	private ArrayList<ObjectiveCardPane> objectiveCards;
	private ObjectiveCard objectiveCard;

	public ToolcardController(GameController gc) {
		gamecontroller = gc;
		toolcard = new Toolcard(this);
		objectiveCard = new ObjectiveCard();
		toolcardpanes = new ArrayList<>();
		objectiveCards = new ArrayList<>();
		setToolcardsDescription();
		setObjectiveCards();
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

	private void setToolcardsDescription() {
		toolcardpanes.add(new ToolCardPane(toolcard.getCardOneId(), toolcard.getCardOneDescription(), this));
		toolcardpanes.add(new ToolCardPane(toolcard.getCardTwoId(), toolcard.getCardTwoDescription(), this));
		toolcardpanes.add(new ToolCardPane(toolcard.getCardThreeId(), toolcard.getCardThreeDescription(), this));
		setGameCards();
	}
	
	public ArrayList<ToolCardPane> getToolCards() {
		return toolcardpanes;
	}
	
	private void setObjectiveCards() {
		objectiveCards.add(new ObjectiveCardPane(objectiveCard.getCard1()));
		objectiveCards.add(new ObjectiveCardPane(objectiveCard.getCard2()));
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
	
	public void setGameCards() {
		for (int i = 0; i < getToolcards().size(); i++) {
			gamecontroller.setGameCard(getToolcards().get(i));
		}
	}
}
