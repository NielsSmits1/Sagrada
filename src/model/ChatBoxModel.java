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
    	return database.Select("select username, message, chatline.time from player left join game on game_idgame = idgame right join chatline on player_idplayer = idplayer where idgame = " + gameId + " order  by chatline.time asc");
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
