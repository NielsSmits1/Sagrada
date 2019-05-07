package controller;

import java.util.ArrayList;

import View.RootPane;
import model.Dice;
import model.Game;

public class GameController {
	private Game game;
	public GameController() {
		game = new Game();
	}
	
	///*
		//Gets all dices available in the game.
		///**
	public ArrayList<Dice> getDiceArray(){
		return game.getDiceArray();
	}
	
	public void removeNumber(int number) {
		game.removeDice(number);
	}
	
	
}
