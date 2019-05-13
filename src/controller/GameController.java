package controller;

import java.util.ArrayList;

import View.BoardPane;
import View.DicePane;
import View.PatterncardSelect;
import View.RootPane;
import javafx.scene.Parent;
import model.Dice;
import model.Game;
import model.PatternCard;
import model.Round;

public class GameController {
	private Game game;
	private MyScene scene;
	private PatterncardSelect option;
	private RootPane rootpane;
	private BoardController boardcontroller;
	private Round round;

	public GameController(MyScene s) {
		scene = s;
		boardcontroller = new BoardController(this);
		game = new Game();
		game.setPlayableDices();
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
		boardcontroller.setPatternCard(id);
	}

	public void setRootpane() {
		rootpane = new RootPane(this);
		scene.setRoot(rootpane);
	}
	
	public BoardPane returnBoardPane() {
		return boardcontroller.returnBoardPane();
	}
}
