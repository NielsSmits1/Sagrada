package model;

import java.util.ArrayList;

import Database.db;

public class Round {
	private db database = new db();
	private ArrayList<Turn> turnes = new ArrayList();
	
	public ArrayList<ArrayList<Object>> GetPlayerWithChallengedStatus(int gameId) {
		return database.Select("select count(username) from player where game_idgame = "+ gameId); //Change "Johan" To self.username 
	}	
		
	public ArrayList<ArrayList<Object>> GetPlayerWithChallengeeStatus(int gameId) {
		return database.Select("select * from player where game_idgame = "+ gameId +" AND playstatus_playstatus = 'Uitgedaagde'"); //Change "Teun" To self.username 
	}
	public ArrayList<ArrayList<Object>> getRemaines() {
		return database.Select("SELECT MAX(gd.round), p.username, p.seqnr FROM gamedie AS gd " + 
				"LEFT JOIN playerframefield AS pff ON gd.dienumber = pff.dienumber AND gd.diecolor = pff.diecolor LEFT JOIN player AS "+
				"p ON pff.player_idplayer = p.idplayer " + 
				"WHERE gd.idgame = 2 AND p.isCurrentPlayer = 1");
	}
	public void buildRemaningRounds() {
//		while(getRemaines);
	}
	public int calculateRounds(int gameId) {
		// dit sijn hoeveel turnes er nog in de ronde sijn die besig is
		return (int)getRemaines().get(0).get(3) - (int)GetPlayerWithChallengedStatus(gameId).get(0).get(0);
	}
	// Calculates the amount of turns in a round
	public int calculateTurns(int gameId) {
		// dit sijn hoeveel turns er in een game sijn.
		return (int)(GetPlayerWithChallengedStatus(gameId).get(0).get(0)) * 2;
		
	}
	public void buildTurnes(int gameId) {
		for(ArrayList<Object> a: database.Select("select idplayer, username from player where game_idgame = " + gameId +" and "
				+ "playstatus_playstatus != 'Uitgespeeld' order by idplayer asc")){
			turnes.add(new Turn((int)a.get(0),new Player((String) a.get(1))));
		}
		for(ArrayList<Object> a: database.Select("select idplayer, username from player where game_idgame = " + gameId +" and "
				+ "playstatus_playstatus != 'Uitgespeeld' order by idplayer desc")){
			turnes.add(new Turn((int)a.get(0),new Player((String) a.get(1))));
		}
	}
	
	// adds a number to a username 
	public void setUserNameId() {
		
	}
}
