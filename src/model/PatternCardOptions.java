package model;

import java.util.ArrayList;

import Database.db;

public class PatternCardOptions {
	private db database;
	private ArrayList<Integer> idpatterncards;
	
	public PatternCardOptions() {
		database = new db();
		idpatterncards = new ArrayList<>();
	}
	
	public void getAllPatternCards(int amount) {
		System.out.println("a " +amount);
		ArrayList<ArrayList<Object>> ids = database.Select("SELECT idpatterncard FROM patterncard ORDER BY RAND() LIMIT "+ amount);
		System.out.println(ids.size());
		for (int i = 0; i < ids.size(); i++) {
			idpatterncards.add((int) ids.get(i).get(0));
		}
		
	}
	
	public ArrayList<Integer> getOptions(){
		return idpatterncards;
	}
	
	
}
