package model;

import java.util.ArrayList;

import java.util.Random;
import Database.db;
import controller.BoardController;

public class PatternCard {
	
	private ArrayList<Space> patternfield;
	private db database = new db();
	private ArrayList<ArrayList<Object>> p;
	private int patternId;
	private Random random;
	
	//	private BoardController controller;
	public PatternCard(int number) {
//		controller = c;
//		patternfield.clear();
		patternfield = new ArrayList<>();
		setPatternId(number);
		p = getSelect();
		setPatternField();
//		addCard();
	}
	
	public PatternCard() {
		random = new Random();
		patternfield = new ArrayList<>();
		setPatternId(random.nextInt(23)+1);
		p = getSelect();
		setPatternField();
	}
	
	public ArrayList<ArrayList<Object>> getSelect() {
        return database.Select("SELECT * FROM patterncardfield WHERE patterncard_idpatterncard = " + getPatternId() +";");
    }
	
	///*
		//Fills the ArrayList that contains all of the spaces.
		///**
	
	public void addCard() {
		for (int i = 0; i < patternfield.size(); i++) {
			addChosenCard(patternfield.get(i).getXPos(), patternfield.get(i).getYPos());
		}
	}
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
	
	public void changeField() {
		patternfield.clear();
		p = getSelect();
		setPatternField();
	}
	
	public void randomNumber() {
		patternId  = random.nextInt(23)+1;
	}
	
	public void addOptionToDB() {
//		database.CUD("DELETE FROM tjpmsalt_db2.patterncardoption WHERE player_idplayer = 1;");
//		database.CUD("insert into tjpmsalt_db2.patterncardoption (patterncard_idpatterncard,player_idplayer) VALUES (" + patternId + ",1);");
	}
	
	//TODO ADD THE CHOSEN PATTERNCARD TO PLAYERFRAMEFIELD
	
	public void addChosenCard(int xPos, int yPos) {
		database.CUD("insert into tjpmsalt_db2.playerframefield (player_idplayer, position_x,position_y) VALUES (1,"+ xPos + "," + yPos + ");");
	}
}
