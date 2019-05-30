package controller;

import java.util.ArrayList;

import View.BoardPane;
import View.DicePane;
import model.Opponent;
import model.PatternCard;
import model.PatternCardOptions;
import model.Player;
import model.Space;

public class BoardController {
	private ArrayList<PatternCard> patternCardOptions;
	private PatternCard finalCard;
	private PatternCard checkPlacement;
	private Opponent opponent;
	private BoardPane boardpane = new BoardPane();
	private ArrayList<BoardPane> opponentBoard;
	private GameController gameController;
	private PatternCardOptions allOptions;
	private ArrayList<BoardPane> boards;

	public BoardController(GameController gameController) {
		this.gameController = gameController;
		boards = new ArrayList<>();
		opponentBoard = new ArrayList<>();
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
		if (getSelected() != null && checkPlacement.validateMove(x, y, getSelected().getDieNumber(), getSelected().getColor())) {
			if(gameController.getTurnPlayer().getSelf()) {
				for(BoardPane bp : boards) {
					if(bp.getSelf()) {
						bp.setSelected(getSelected(), x, y);
					}
				}	
			}
		}
	}

	public void validateToolcardTwo(int dieNumber, String color, int xPos, int yPos) {
		if (finalCard.validateMove(xPos, yPos, dieNumber, color)) {
			boardpane.moveDiceAccepted(dieNumber, color, xPos, yPos);
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

	public BoardPane returnBoardPane() {
		return boardpane;
	}

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
		updateToken();
		//gameController.setRootpane();
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

	public ArrayList<ArrayList<Space>> getOpponentCords() {
		return opponent.getOpponents();
	}

	public ArrayList<BoardPane> getOpponentBoard() {
		return opponentBoard;
	}

	public void setAllowsMovement(int i) {
		boardpane.enableDiceMovement();
		boardpane.allowMovement();
		if (i == 2) {
			finalCard.setColorExamption();
		}
		if (i == 3) {
			finalCard.setNumberExamption();
		}
		if (i == 9) {
			finalCard.setNextToDiceExamption();
		}
	}

	public void disableMovement(int x, int y) {
		boardpane.disableMovement(x, y);
	}


	public int getDifficulty() {
		return finalCard.getDifficulty();
	}

	public void updateToken() {
		gameController.updateTokens(getDifficulty());
	}
	
	public void setPlayerTokens(int minus) {
		boardpane.decreaseLabelValue(minus);
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

}
