package model;

import java.util.ArrayList;
import java.util.Collections;

import Database.db;

public class Round {
	private db database = new db();
	private ArrayList<Turn> turnes = new ArrayList();
	private int turnNumber;
	/*
	 * als game wordt aangemaakt build alle turnes anders haal die shit op
	 * als game begint build die shit 
	 */
	
	public Round(int tn) {
		this.turnNumber = tn;
	}
	public int getTurn() {
		return this.turnNumber;
	}
	
	public void buildTurnes(Player self, int gameId) {
		ArrayList<ArrayList<Object>> playersInGame = database.Select("select idplayer, username , seqnr, score from player where game_idgame = " + gameId + " order by seqnr asc");
		for(ArrayList<Object> p : playersInGame) {
			Turn t = new Turn();
			t.buildTurnPlayer((int)p.get(0), (String)p.get(1), (int)p.get(2), (int)p.get(3));
			turnes.add(t);
		}

		for(int i = turnes.size(); i > 0; i--) {
			turnes.add(turnes.get(i));
		}
		
	}
	public void buildHalfTurn() {
		
	}
	
	public void loopTurns() {
		for(Turn t : turnes) {
			// loopt door elke turn heen van deze ronde
		}
	}

	public ArrayList<ArrayList<Object>> GetPlayerWithChallengedStatus(int gameId) {
		return database.Select("select count(username) from player where game_idgame = "+ gameId); //Change "Johan" To self.username 
	}	
		
	public ArrayList<ArrayList<Object>> GetPlayerWithChallengeeStatus(int gameId) {
		return database.Select("select * from player where game_idgame = "+ gameId +" AND playstatus_playstatus = 'Uitgedaagde'"); //Change "Teun" To self.username 
	}


//	public int calculateRounds(int gameId) {
//		// dit sijn hoeveel turnes er nog in de ronde sijn die besig is
//		return (int)getRemaines().get(0).get(2) - (int)GetPlayerWithChallengedStatus(gameId).get(0).get(0);
//	}

	public ArrayList<ArrayList<Object>> getRemaines() {
		return database.Select("SELECT MAX(gd.round), p.username, p.seqnr FROM gamedie AS gd " + 
				"LEFT JOIN playerframefield AS pff ON gd.dienumber = pff.dienumber AND gd.diecolor = pff.diecolor LEFT JOIN player AS "+
				"p ON pff.player_idplayer = p.idplayer " + 
				"WHERE gd.idgame = 2 AND p.isCurrentPlayer = 1");
	}

	// public void buildRemaningRounds() {
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
//	private void buildTurnesForward(int gameId) {
//		for(ArrayList<Object> a: database.Select("select idplayer, username from player where game_idgame = " + gameId +" and "
//				+ "playstatus_playstatus != 'Uitgespeeld' order by idplayer asc")){
//			turnes.add(new Turn((int)a.get(0),new Player((String) a.get(1)), 0));
//		}
//	}
//	private void buiildTurnesBackward(int gameId) {
//		for(ArrayList<Object> a: database.Select("select idplayer, username from player where game_idgame = " + gameId +" and "
//				+ "playstatus_playstatus != 'Uitgespeeld' order by idplayer desc")){
//			turnes.add(new Turn((int)a.get(0),new Player((String) a.get(1)), 1));
//		}
//	}	
	// adds a number to a username 
	public void setUserNameId() {
		
	}
}
