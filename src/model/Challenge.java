package model;

import java.util.ArrayList;

import Database.db;

public class Challenge {
	private Player player;
	private Player player2;
	private int gameId;
	private String playerStatus;
	private db database = new db();

	public Challenge() {
		// TODO Auto-generated constructor stub
	}

	public void changePlayerStatusToAccepted() {
//		database.CUD("UPDATE player SET playstatus = 'geaccepteerd' WHERE idplayer = 1 "); // idplayer needs to be variabel
		System.out.println("PlayerAccepted");
	}

	public void changePlayerStatusToDeclined() {

		System.out.println("PlayerDeclined");
//		database.CUD("UPDATE player SET playstatus = 'afgewezen' WHERE idplayer = 1 "); // idplayer needs to be variabel

	}

	public ArrayList<ArrayList<Object>> GetPlayerWithChallengedStatus() {
		return database.Select("select username from player where game_idgame = (select game_idgame from player where username ='" + "Johan" + "' AND playstatus_playstatus = 'Uitdager') "); //Change "Johan" To self.username 
		
		
		//  returns :niels
		//	         teun
	
	}

}
