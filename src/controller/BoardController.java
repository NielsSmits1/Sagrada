package controller;

import java.util.ArrayList;


import View.BoardPane;
import model.Board;
import model.Space;

public class BoardController {
	private Board field;
//	private BoardPane b;
	public BoardController() {
//		b = bp;
//		field = new Board(this);
	}
	///*
		//Asks for the ArrayList of spaces.
		///**
	public ArrayList<Space> getPatternField() {
		return field.getPatternField();
	}
	
	///*
		//this id is used for the query that gets the windowPattern out of the DB.
		///**
	public int getPatternId() {
		return field.getPatternId();
	}
	
	public void setPatternId(int id) {
		field = new Board();
		field.setPatternId(id);
	}
}
