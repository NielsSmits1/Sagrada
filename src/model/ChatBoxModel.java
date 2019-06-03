package model;

import java.util.ArrayList;

import Database.Db;

public class ChatBoxModel {

	private Db database;
	private int gameId;
	private int playerId;
	
	public ChatBoxModel() {
		database = new Db();
		
		
	}
	
	public ArrayList<ArrayList<Object>> playerUserName(){
    	return database.select("select username, chatline.message, chatline.time from player left join chatline on player.idplayer = chatline.player_idplayer where message is not null and game_idgame = "+gameId+";");
    }
	
	public void sendCUD(String input) {
		database.cud("INSERT INTO chatline (player_idplayer, time, message) values(" + playerId +  " , now(),'" + input + "');");
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
