package controller;

import java.util.ArrayList;

import View.PatterncardSelect;
import View.RootPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import model.Dice;
import model.Game;
import model.PatternCard;
import model.Space;

public class GameController {
	private Game game;
	private MyScene scene;
	private PatterncardSelect option;
	private RootPane rootpane;
	private BoardController boardcontroller;
	public GameController(MyScene s) {
		scene = s;
		boardcontroller = new BoardController();
	}
	
	public Parent showOptions() {
		option = new PatterncardSelect(this);
		return option;
	}
	
//	private EventHandler<ActionEvent>confirm(){
//		if(option != null && option.getChosenId() != 0) {
//			option.getChosenId();
//			System.out.println("You've picked number" + option.getChosenId());
//		}
//		return null;
//	}
	
	public ArrayList<PatternCard> getPatternCardOptions() {
		return boardcontroller.getPatternCardOptions();
	}
	
	///*
		//Gets all dices available in the game.
		///**
	public ArrayList<Dice> getDiceArray(){
		game = new Game();
		return game.getDiceArray();
	}
	
//	public ArrayList<Space> getPatternCard(){
//		return boardcontroller.getPatternCard();
//	}
	
	public void setPatternCard(int id) {
		boardcontroller.setPatternCard(id);
	}
	
//	public void showRootPane() {
//		rootpane = new RootPane();
//	}
}
