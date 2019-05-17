package controller;

import java.util.ArrayList;

import View.BoardPane;
import View.DicePane;
import View.MyScene;
import View.PatterncardSelect;
import View.RootPane;
import View.ToolCardPane;
import javafx.scene.Parent;
import model.Dice;
import model.Game;
import model.PatternCard;
//import model.Round;
import model.Round;
import model.Space;

public class GameController {
	private Game game;


	private MyScene scene;
	private PatterncardSelect option;
	private RootPane rootpane;
	private BoardController boardcontroller;
	private ToolcardController toolcardcontroller;
	private Round round;

	public GameController(MyScene s) {
		scene = s;
		game = new Game();
		game.setPlayableDices();
		boardcontroller = new BoardController(this);
		toolcardcontroller = new ToolcardController(this);

	}
	

	public Parent showOptions() {
		option = new PatterncardSelect(this);
		return option;
	}

	public ArrayList<PatternCard> getPatternCardOptions() {
		return boardcontroller.getPatternCardOptions();
	}

	/// *
	// Gets all dices available in the game.
	/// **
	public ArrayList<Dice> getPlayableDices() {
		return game.getPlayableDices();
	}
	
	public DicePane getSelected() {
		return rootpane.getSelected();
	}
	
	public int getIdGame() {
		return game.getIdGame();
	}
	
	public int getTurns() {
		return round.calculateTurns(getIdGame());
	}

	// public ArrayList<Space> getPatternCard(){
	// return boardcontroller.getPatternCard();
	// }

	public void setPatternCard(int id) {
		game.setOwnId(id);
		boardcontroller.setPatternCard(id);
	}

	public void setRootpane() {
		rootpane = new RootPane(this);
		scene.setRoot(rootpane);
	}
	
	public BoardPane returnBoardPane() {
		return boardcontroller.returnBoardPane();
	}
	
	public int getOwnId() {
		return game.getOwnId();
	}
	
	public ArrayList<BoardPane> getOpponentBoard(){
		return boardcontroller.getOpponentBoard();
	}
	
	public ArrayList<ToolCardPane> getToolCards(){
		return toolcardcontroller.getToolCards();
	}
	
	
}
