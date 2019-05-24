package model;

import java.util.ArrayList;

import Database.db;

public class PatternCardOptions {
	private db database;
	private ArrayList<Integer> idpatterncards;
	
	public PatternCardOptions() {
		idpatterncards = new ArrayList<>();
	}
	
	public void getAllPatternCards(int amount) {
		ArrayList<ArrayList<Object>> ids = database.Select("SELECT idpatterncard FROM tjpmsalt_db2.patterncard ORDER BY RAND() LIMIT "+ amount);
		for (int i = 0; i < ids.size(); i++) {
			idpatterncards.add((int) ids.get(i).get(0));
		}
	}
	
	public ArrayList<Integer> getOptions(){
		return idpatterncards;
	}
	
	
}
