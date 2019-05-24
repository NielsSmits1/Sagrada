package model;

import java.util.ArrayList;

import Database.db;

public class ChatBoxModel {

	private db database;
	private String[] playerUsernameChat;
	private int gameId = 1;
	private int playerId = 1;
	
	public ChatBoxModel() {
		database = new db();
		playerUsernameChat = new String[100];
		
		
	}
	
	public ArrayList<ArrayList<Object>> playerUserName(){
    	return database.Select("SELECT username,message,chatline.time FROM player LEFT join chatline  ON idplayer = player_idplayer where game_idgame = " + gameId +" order by time asc;");
    }
	
	public void sendCUD(String input) {
		database.CUD("INSERT INTO chatline (player_idplayer, time, message) values(" + playerId +  " , now(),'" + input + "');");
	}
	public void playerUsername() {
		for (int x = 0 ; x < playerUserName().size(); x++) {
			if( playerUsernameChat[x] == null) {
				playerUsernameChat[x] = playerUserName().get(x).toString();
			}
		}
	}

	public String getPlayerGameId() {
		String y = "leeg";
		for(int x = 0 ; x < playerUsernameChat.length; x++) {
			if(playerUsernameChat[x] != null) {
				return playerUsernameChat[x];
			}
		}
		return y;
	}

	public String[] getPlayerUsernameChat() {
		return playerUsernameChat;
	}
	
	
	
	
	
}
