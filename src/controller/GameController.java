package controller;

import java.util.ArrayList;

import View.PatterncardSelect;
import View.RootPane;
import javafx.scene.Parent;
import model.Dice;
import model.Game;
import model.PatternCard;
import model.Space;

public class GameController {
	private Game game;
	private MyScene scene;
	private PatterncardSelect option;
	private BoardController boardcontroller;
	public GameController(MyScene s) {
		scene = s;
		boardcontroller = new BoardController();
	}
	
	public Parent showOptions() {
		option = new PatterncardSelect(getPatternCard());
		return option;
	}
	
	public ArrayList<PatternCard> getPatternCard() {
		return boardcontroller.getPatternCard();
	}
	
	///*
		//Gets all dices available in the game.
		///**
	public ArrayList<Dice> getDiceArray(){
		game = new Game();
		return game.getDiceArray();
	}
}
