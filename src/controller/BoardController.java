package controller;

import java.util.ArrayList;

import View.BoardPane;
import View.DicePane;
import model.PatternCard;
import model.Space;

public class BoardController {
	private ArrayList<PatternCard> patternCardOptions;
	private PatternCard finalCard;
	private BoardPane boardpane;
	private GameController gameController;

	public BoardController(GameController gameController) {
		this.gameController = gameController;

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
	public DicePane getSelected() {
		return gameController.getSelected();
	}
	public void setBoard() {
		boardpane = new BoardPane(this);
	}

	public BoardPane returnBoardPane() {
		return boardpane;
	}

	public ArrayList<PatternCard> getPatternCardOptions() {
		return patternCardOptions;
	}

	public ArrayList<Space> getPatternCard() {
		return finalCard.getPatternField();
	}

	public void setPatternCard(int id) {
		finalCard = new PatternCard(id, getIdGame());
		setBoard();
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


}
