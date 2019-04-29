package controller;

import java.util.ArrayList;

import View.RootPane;
import model.Dice;
import model.Game;

public class GameController {
	private Game game;
	public GameController() {
		
	}
	
	///*
		//Gets all dices available in the game.
		///**
	public ArrayList<Dice> getDiceArray(){
		game = new Game();
		return game.getDiceArray();
	}
}
