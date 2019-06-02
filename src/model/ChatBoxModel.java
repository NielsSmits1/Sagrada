package model;

import java.util.ArrayList;

import Database.db;

public class ChatBoxModel {

	private db database;
	private int gameId;
	private int playerId;
	
	public ChatBoxModel() {
		database = new db();
		
		
	}
	
	public ArrayList<ArrayList<Object>> playerUserName(){
		System.out.println(gameId);
		System.out.println(playerId);
    	return database.Select("select username, chatline.message, chatline.time from player left join chatline on player.idplayer = chatline.player_idplayer where message is not null and game_idgame = "+gameId+";");
    }
	
	public void sendCUD(String input) {
		database.CUD("INSERT INTO chatline (player_idplayer, time, message) values(" + playerId +  " , now(),'" + input + "');");
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	

	
}
