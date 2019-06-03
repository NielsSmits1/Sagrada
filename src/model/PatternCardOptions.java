package model;

import java.util.ArrayList;

import Database.Db;

public class PatternCardOptions {
	private Db database;
	private ArrayList<Integer> idpatterncards;
	
	public PatternCardOptions() {
		database = new Db();
		idpatterncards = new ArrayList<>();
	}
	
	public void getAllPatternCards(int amount) {
		ArrayList<ArrayList<Object>> ids = database.select("SELECT idpatterncard FROM patterncard ORDER BY RAND() LIMIT "+ amount);
		for (int i = 0; i < ids.size(); i++) {
			idpatterncards.add((int) ids.get(i).get(0));
		}
		
	}
	
	public ArrayList<Integer> getOptions(){
		return idpatterncards;
	}
	
	
}
