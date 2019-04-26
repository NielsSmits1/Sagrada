package controller;

import java.util.ArrayList;

import View.RootPane;
import model.Dice;
import model.Game;

public class GameController {
	private RootPane rootPane;
	private Game game;
	public GameController(RootPane rp) {
		rootPane = rp;
		game = new Game(this);
	}
	
	///*
		//Gets all dices available in the game.
		///**
	public ArrayList<Dice> getDiceArray(){
		return game.getDiceArray();
	}
}
