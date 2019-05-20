package controller;

import java.util.ArrayList;

import View.ToolCardPane;
import model.Toolcard;

public class ToolcardController {
	private Toolcard toolcard;
	private GameController gamecontroller;
	private ArrayList<ToolCardPane> toolcardpanes;
	
	public ToolcardController(GameController gc) {
		gamecontroller = gc;
		toolcard = new Toolcard(this);
		toolcardpanes = new ArrayList<>();
		setToolcardsDescription();
	}
	
	public void setToolcardActive() {
		gamecontroller.setToolcardActive();
	}
	
	public void enableDiceMovement() {
		gamecontroller.enableDiceMovement();
	}
	
	private void setToolcardsDescription() {
		toolcardpanes.add(new ToolCardPane(toolcard.getCardOneId(), toolcard.getCardOneDescription(), this));
		toolcardpanes.add(new ToolCardPane(toolcard.getCardTwoId(), toolcard.getCardTwoDescription(), this));
		toolcardpanes.add(new ToolCardPane(toolcard.getCardThreeId(), toolcard.getCardThreeDescription(), this));
	}
	public ArrayList<ToolCardPane> getToolCards(){
		return toolcardpanes;
	}
	
	public void toolcardClicked(int id) {
		toolcard.activateToolcard(id);
	}

}
