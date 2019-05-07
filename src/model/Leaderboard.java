package model;

import java.util.ArrayList;

import Database.db;

public class Leaderboard {
	private Player self;
	private db database = new db();

	public Leaderboard() {

	}

	public ArrayList<ArrayList<Object>> getListOfUsernames() {
		return database.Select("Select * from account order by username ASC");
	}
	
	public ArrayList<ArrayList<Object>> getListOfUsernamesWithAmountOfGamesPlayed() {
		return database.Select("select distinct player.username, IFNULL(games_played.games_played, 0) as games_played from player left join games_played on player.username = games_played.username order by games_played.games_played DESC, username ASC");
	}
	
	public ArrayList<ArrayList<Object>> getListOfUsernamesWithAmountOfGamesWon() {
		return database.Select("select p1.username, IFNULL(games_won.games_won,0) as amount_won from player p1 LEFT JOIN games_won on p1.username = games_won.username group by p1.username order by amount_won DESC, p1.username ASC");
	}
	// selects and returns arraylist of usernames.
//	    public ArrayList<ArrayList<Object>> checkPlayerInGame(){
//	    	return database.Select("select username from player where game_idgame = (select game_idgame from player where username ='" + username + "') ");
//	   }

}
