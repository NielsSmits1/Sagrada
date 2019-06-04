package controller;

import java.util.ArrayList;

import View.BoardPane;
import View.DicePane;
import model.PatternCard;
import model.PatternCardOptions;
import model.Player;
import model.Space;

public class BoardController {
	private ArrayList<PatternCard> patternCardOptions;
	private PatternCard finalCard;
	private PatternCard checkPlacement;
	private GameController gameController;
	private PatternCardOptions allOptions;
	private ArrayList<BoardPane> boards;

	public BoardController(GameController gameController) {
		this.gameController = gameController;
		boards = new ArrayList<>();
		checkPlacement = new PatternCard(this, this.gameController.getOwnId(), this.gameController.getIdGame(), this.gameController.getOwnPatternId());
	}
	/// *
	// Asks for the ArrayList of spaces.
	/// **
	
	public void setOwnOptions() {
		patternCardOptions = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			patternCardOptions.add(new PatternCard(this.gameController.getOwnOptions().get(i)));
		}
	}
	
	public ArrayList<PatternCard> getOptions(){
		return patternCardOptions;
	}

	public int getIdGame() {
		return gameController.getIdGame();
	}

	public void validateMove(int x, int y) {
		if (gameController.getTurnPlayer().getSelf() && getSelected() != null && checkPlacement.validateMove(x, y, getSelected().getDieNumber(), getSelected().getColor())) {
				for(BoardPane bp : boards) {
					if(bp.getSelf()) {
						bp.setSelected(getSelected(), x, y);
					}
				}	
			}
	}

	public void validateToolcardTwo(int dieNumber, String color, int xPos, int yPos) {
		if (checkPlacement.validateMove(xPos, yPos, dieNumber, color)) {
			for(BoardPane bp : boards) {
				if(bp.getSelf()) {
					bp.moveDiceAccepted(dieNumber, color, xPos, yPos);
				}
			}
		}
	}

	public DicePane getSelected() {
		return gameController.getSelected();
	}

//	public void setBoard() {
////		players.add(new BoardPane(this, finalCard));
//		for (int i = 0; i < getGamemode(); i++) {
//			boards.add(new BoardPane(this, new PatternCard(gameController.getChosenIds().get(i))));
//		}
//	}


	public ArrayList<PatternCard> getPatternCardOptions() {
		return patternCardOptions;
	}
	
	public int getOwnPatternId() {
		return gameController.getOwnPatternId();
	}

	public void setPatternCard(int id) {
		finalCard = new PatternCard(id, getIdGame(), getOwnId(), this);
		//gameController.setRootpane();
	}

	public void setRandomCard() {
		finalCard = new PatternCard(getOwnId(), getIdGame(), this);
	}

	public ArrayList<Space> getPatternCard() {
		return finalCard.getPatternField();
	}

	public void getTurns() {
//		gameController.getTurns();
	}

	public int getOwnId() {
		return gameController.getOwnId();
	}

	public void setAllowsMovement(int i) {
		for(BoardPane bp : boards) {
			if(bp.getSelf()) {
				bp.enableDiceMovement();
				bp.allowMovement();
			}
		}
		
		if (i == 2) {
			checkPlacement.setColorExamption();
		}
		if (i == 3) {
			checkPlacement.setNumberExamption();
		}
		if (i == 9) {
			checkPlacement.setNextToDiceExamption();
		}
	}
	
	public BoardPane getOwnBoard() {
		for (BoardPane bp : boards) {
			if(bp.getSelf()) {
				return bp;
			}
		}
		return null;
	}

	public void disableMovement(int x, int y) {
		for(BoardPane bp : boards) {
			if(bp.getSelf()) {
				bp.disableMovement(x, y);
			}
		}
	}


	public int getDifficulty() {
		return finalCard.getDifficulty();
	}
	
	public void setOptions() {
		allOptions = new PatternCardOptions();
		allOptions.getAllPatternCards(getGamemode()*4);
		this.gameController.addOptions(allOptions.getOptions());
	}
	
	public int getGamemode() {
		return gameController.getGamemode();
	}
	
	public ArrayList<BoardPane> getBoards(){
		return boards;
	}
	
	public void addBoard(PatternCard pc, Player p) {
		boards.add(new BoardPane(this, pc, p));
	}
	public void emptyBoards() {
		this.boards = new ArrayList<BoardPane>();
	}

}
