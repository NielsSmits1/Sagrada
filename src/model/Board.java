package model;

import java.util.ArrayList;

import Database.db;
import controller.BoardController;

public class Board {
	
	private ArrayList<Space> patternfield;
	private db database = new db();
	private ArrayList<ArrayList<Object>> p;
	private int patternId;


	//	private BoardController controller;
	public Board() {
//		controller = c;
		patternfield = new ArrayList<>();
		p = getSelect();
		setPatternField();
	}
	
	public ArrayList<ArrayList<Object>> getSelect() {
        return database.Select("SELECT * FROM patterncardfield WHERE patterncard_idpatterncard = " + getPatternId() +";");
    }
	
	///*
		//Fills the ArrayList that contains all of the spaces.
		///**
	private void setPatternField() {
		for(int i = 0;i<20;i++) {
			patternfield.add(new Space());
			patternfield.get(i).setXPOS((int) p.get(i).get(1));
			patternfield.get(i).setYPOS((int) p.get(i).get(2));
			///*
			//The color might be null, in that case the color will be set the white.
			///**
			if((String)p.get(i).get(3) == null) {
				patternfield.get(i).setColor("WHITE");
			}else {
				///*
				//This switch is needed because all of the colors in the DB are in dutch.
				///**
				switch((String) p.get(i).get(3)) {
				case "blauw":
				patternfield.get(i).setColor("BLUE");
				break;
				case "rood":
					patternfield.get(i).setColor("RED");
					break;
				case "geel":
					patternfield.get(i).setColor("YELLOW");
					break;
				case "groen":
					patternfield.get(i).setColor("GREEN");
					break;
				case "paars":
					patternfield.get(i).setColor("PURPLE");
					break;
				default:
					patternfield.get(i).setColor("WHITE");
					break;
				}
			}
				
				
			if(p.get(i).get(4) != null) {
				patternfield.get(i).setEyes((int) p.get(i).get(4));
			}
		}
	}
	
	public ArrayList<Space> getPatternField(){
		return patternfield;
	}
	
	public int getPatternId() {
		return patternId;
	}
	
	public void setPatternId(int patternId) {
		this.patternId = patternId;
	}
}
