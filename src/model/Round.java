package model;

import java.util.ArrayList;

import Database.db;

public class Round {
	private db database = new db();
	
	public ArrayList<ArrayList<Object>> GetPlayerWithChallengedStatus(int gameId) {
		return database.Select("select username from player where game_idgame = "+ gameId +"  AND playstatus_playstatus = 'Uitdager'"); //Change "Johan" To self.username 
		
	}	
		
	public ArrayList<ArrayList<Object>> GetPlayerWithChallengeeStatus(int gameId) {
		return database.Select("select * from player where game_idgame = "+ gameId +" AND playstatus_playstatus = 'Uitgedaagde'"); //Change "Teun" To self.username 
		
	}
	
	// Calculates the amount of turns in a round
	public int calculateTurns(int gameId) {
		int turns;
		return turns = (GetPlayerWithChallengedStatus(gameId).size() + GetPlayerWithChallengeeStatus(gameId).size()) * 2;
		
	}
	
	// adds a number to a username 
	public void setUserNameId() {
		
	}
}
