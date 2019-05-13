package model;

import java.util.ArrayList;

import Database.db;

public class Challenge {
	private Player self;
	private Player challenger;
	private int gameId;
	private String playerStatus;
	private db database = new db();

	public Challenge() {
		
	}
	public Challenge(Player self) {
		this.self = self;
	}
	public Challenge(Player self, Player challenger) {
		this.self = self;
		this.challenger = challenger;
	}

	public void changePlayerStatusToAccepted() {
		database.CUD("UPDATE player SET playstatus_playstatus = 'Geaccepteerd' WHERE playstatus_playstatus = 'Uitgedaagde' and username = '" + self.getUsername() +"' and game_idgame in (select game_idgame from player where username ='"+ challenger.getUsername() +"')"); // idplayer needs to be variabel
		System.out.println("PlayerAccepted");
	}

	public void changePlayerStatusToDeclined() {

		System.out.println("PlayerDeclined");
		database.CUD("UPDATE player SET playstatus_playstatus = 'Geweigerd' WHERE playstatus_playstatus = 'Uitgedaagde' and username = '" + self.getUsername() +"' and game_idgame in (select game_idgame from player where username ='"+ challenger.getUsername() +"')"); // idplayer needs to be variabel

	}

	public ArrayList<ArrayList<Object>> GetPlayerWithChallengedStatus() {
		return database.Select("select username from player where game_idgame in (select game_idgame from player where username = '" + self.getUsername() +"') AND playstatus_playstatus = 'Uitdager'"); //Change "Johan" To self.username 
		
	}	
		//  returns :niels
		//	         teun
	public ArrayList<ArrayList<Object>> GetPlayerWithChallengeeStatus() {
		return database.Select("select * from player where game_idgame in (select game_idgame from player where username = '" + self.getUsername() +"') AND playstatus_playstatus = 'Uitgedaagde'"); //Change "Teun" To self.username 
		
		// returns :johan
		// 			teun
		//			niels
	}
	public Player getSelf() {
		return self;
	}
	public Player getChallenger() {
		return challenger;
	}
	public void setSelf(Player self) {
		this.self = self;
	}
	public void setChallenger(Player challenger) {
		this.challenger = challenger;
	}
	public void setChallengerUsername(String challengerUsername){
		challenger.setUsername(challengerUsername);
		
	}


}
