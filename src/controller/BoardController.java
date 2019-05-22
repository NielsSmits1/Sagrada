package controller;

import java.util.ArrayList;

import View.BoardPane;
import View.DicePane;
import model.Opponent;
import model.PatternCard;
import model.Space;

public class BoardController {
	private ArrayList<PatternCard> patternCardOptions;
	private PatternCard finalCard;
	private Opponent opponent;
	private BoardPane boardpane;
	private ArrayList<BoardPane> opponentBoard;
	private GameController gameController;

	public BoardController(GameController gameController) {
		this.gameController = gameController;
		opponentBoard = new ArrayList<>();
		patternCardOptions = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			patternCardOptions.add(new PatternCard());
		}
		checkDuplicatePatternCard();
		for (int i = 0; i < patternCardOptions.size(); i++) {
			patternCardOptions.get(i).addOptionToDB();
		}

	}
	/// *
	// Asks for the ArrayList of spaces.
	/// **
	
	public int getIdGame() {
		return gameController.getIdGame();
	}
	public void validateMove(int x, int y) {
		if(getSelected() != null && finalCard.validateMove(x, y, getSelected().getDieNumber(), getSelected().getColor())){
			boardpane.setSelected(getSelected(), x, y);
		}
	}
	
	public void validateToolcardTwo(int dieNumber, String color, int xPos, int yPos) {
		if(finalCard.validateMove(xPos, yPos, dieNumber,color)) {
			boardpane.moveDiceAccepted(dieNumber, color, xPos, yPos);
		}
	}
	public DicePane getSelected() {
		return gameController.getSelected();
	}
	public void setBoard() {
		boardpane = new BoardPane(this);
	}
	
	public void setOpponentBoard() {
		opponent = new Opponent(getIdGame(), getOwnId());
		for (int i = 0; i < getOpponentCords().size(); i++) {
			opponentBoard.add(new BoardPane(getOpponentCords().get(i)));
		}
		
	}

	public BoardPane returnBoardPane() {
		return boardpane;
	}

	public ArrayList<PatternCard> getPatternCardOptions() {
		return patternCardOptions;
	}

	public ArrayList<Space> getPatternCard() {
		return finalCard.getRandom();
	}

	public void setPatternCard(int id) {
		finalCard = new PatternCard(id, getIdGame(),getOwnId(), this);
		setBoard();
		setOpponentBoard();
		gameController.setRootpane();
	}


	public void checkDuplicatePatternCard() {
		//hoort hier eik niet maar kan nergens anders
		while (patternCardOptions.get(1).getPatternId() == patternCardOptions.get(0).getPatternId()
				|| patternCardOptions.get(1).getPatternId() == patternCardOptions.get(2).getPatternId()
				|| patternCardOptions.get(1).getPatternId() == patternCardOptions.get(3).getPatternId()) {
			patternCardOptions.get(1).randomNumber();
			patternCardOptions.get(1).changeField();
		}
		while (patternCardOptions.get(2).getPatternId() == patternCardOptions.get(0).getPatternId()
				|| patternCardOptions.get(2).getPatternId() == patternCardOptions.get(1).getPatternId()
				|| patternCardOptions.get(2).getPatternId() == patternCardOptions.get(3).getPatternId()) {
			patternCardOptions.get(2).randomNumber();
			patternCardOptions.get(2).changeField();
		}
		while (patternCardOptions.get(3).getPatternId() == patternCardOptions.get(0).getPatternId()
				|| patternCardOptions.get(3).getPatternId() == patternCardOptions.get(2).getPatternId()
				|| patternCardOptions.get(3).getPatternId() == patternCardOptions.get(1).getPatternId()) {
			patternCardOptions.get(3).randomNumber();
			patternCardOptions.get(3).changeField();
		}
	}

	public void getTurns() {
		gameController.getTurns();
	}
	
	public int getOwnId() {
		return gameController.getOwnId();
	}
	
	public ArrayList<ArrayList<Space>> getOpponentCords(){
		return opponent.getOpponents();
	}
	
	public ArrayList<BoardPane> getOpponentBoard(){
		return opponentBoard;
	}
	
	public void setAllowsMovement(int i) {
		boardpane.enableDiceMovement();
		boardpane.allowMovement();
		if(i == 2) {
			finalCard.setColorExamption();
		}
		if(i == 3) {
			finalCard.setNumberExamption();
		}
		if(i == 9) {
			finalCard.setNextToDiceExamption();
		}
	}
	
	public void disableMovement(int x, int y) {
		boardpane.disableMovement(x , y);
	}
	
	public void calculatePatterncardNeeded() {
		//TODO maak 3 var.
		int x = (3 + 1)*4;
			
	}
	


}
