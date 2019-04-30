package model;

import Database.db;

public class Challenge {
	private Player player1;
	private Player player2;
	private int gameId;
	private String playerStatus;
	private db database = new db();
	
	public Challenge() {
		// TODO Auto-generated constructor stub
	}
	
	public void changePlayerStatusToAccepted() {
		database.CUD("UPDATE player SET playstatus = 'geaccepteerd' WHERE idplayer = 1 "); // idplayer needs to be variabel
		
	}

	public void changePlayerStatusToDeclined() {
		database.CUD("UPDATE player SET playstatus = 'afgewezen' WHERE idplayer = 1 "); // idplayer needs to be variabel

		
	}

}
