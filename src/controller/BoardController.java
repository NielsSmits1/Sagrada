package controller;

import java.util.ArrayList;


import View.BoardPane;
import model.PatternCard;
import model.Space;

public class BoardController {
//	private PatternCard field1;
//	private PatternCard field2;
//	private PatternCard field3;
//	private PatternCard field4;
	private ArrayList<PatternCard> options;
//	private BoardPane b;
	public BoardController() {
//		b = bp;
//		setPatternId(1);
		options = new ArrayList<>();
		for(int i = 0; i<4;i++) {
			options.add(new PatternCard());
		}
		checkDuplicatePatternCard();
//		options.add(new PatternCard());
//		field1 = new PatternCard();
//		field2 = new PatternCard();
//		field3 = new PatternCard();
//		field4 = new PatternCard();
	}
	///*
		//Asks for the ArrayList of spaces.
		///**
	public ArrayList<PatternCard> getPatternCard() {
		return options;
	}
	
	public void checkDuplicatePatternCard() {
		while(options.get(1).getPatternId() == options.get(0).getPatternId() || options.get(1).getPatternId() == options.get(2).getPatternId() || options.get(1).getPatternId() == options.get(3).getPatternId()) {
			options.get(1).randomNumber();
			options.get(1).changeField();
		}
		while(options.get(2).getPatternId() == options.get(0).getPatternId() || options.get(2).getPatternId() == options.get(1).getPatternId() || options.get(2).getPatternId() == options.get(3).getPatternId()) {
			options.get(2).randomNumber();
			options.get(2).changeField();
		}
		while(options.get(3).getPatternId() == options.get(0).getPatternId() || options.get(3).getPatternId() == options.get(2).getPatternId() || options.get(3).getPatternId() == options.get(1).getPatternId()) {
			options.get(3).randomNumber();
			options.get(3).changeField();
		}
	}
	
	///*
		//this id is used for the query that gets the windowPattern out of the DB.
		///**

	
//	public void setPatternId(int id) {
//		field = new PatternCard(id);
//	}
	

}
