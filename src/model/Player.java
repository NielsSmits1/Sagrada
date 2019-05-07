package model;
import java.util.ArrayList;

import Database.db;

public class Player {
	private int idplayer;
	private String username;
	private String password;
	private db database = new db();
	private Board board;
	
	private String differendPlayer;

	public Player(String u, String p) {
		this.username = u;
		this.password = p;
	}
	
	public Player(String username) {
		this.username = username;
	}
	//selects and returns the username and password.
    public ArrayList<ArrayList<Object>> getSelect() {
        return database.Select("SELECT * FROM account WHERE username = '" + username + "' AND password = '" + password + "';");
    }
    
    public ArrayList<ArrayList<Object>> checkUsername(){
    	return database.Select("Select * from account where username = '" + username +"'");
    }
    //selects and returns arraylist of usernames.
    public ArrayList<ArrayList<Object>> checkPlayerInGame(){
    	return database.Select("select username from player where game_idgame = (select game_idgame from player where username ='" + username + "') ");
   }

    //adds new user to the database.
    public void addUser() {
        database.CUD("INSERT INTO account (username, password) VALUES ('" + username + "', '" + password + "');");
    }
    
    public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}

	public void challenge() {
		/*
		 *eerst bouw nieuw game
		 *dan bouw spelers jezelf
		 *dan bouw speler ander
		 *update game zet jezelf als startspeler 
		 * database.CUD("");
		 */
		
	}
	public void buildNewGame() {
		database.CUD("insert into game ()");
	}

	public int getTimesWon() {
		int amount = 0;
		for(ArrayList<Object> a: database.Select("select username from games_won")) {
			if(a.get(0).equals(this.username)) {
				amount +=1;
			}
		}
		return amount;
	}

	public int getTimesLost() {
		int amount = 0;
		for(ArrayList<Object> a: database.Select("select username from games_won")) {
			if(!a.get(0).equals(this.username)) {
				amount +=1;
			}
		}
		return amount;
	}

	public String getHighScore() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMostPlacedDiceColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getMostPlacedDiceEyes() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAmountOfUniquePlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public int getTimesWonPlayer() {
		int amount = 0;
		for(ArrayList<Object> a: database.Select("select username from games_won")) {
			if(a.get(0).equals(this.differendPlayer)) {
				amount +=1;
			}
		}
		return amount;
	}

	public int getTimesLostPlayer() {
		int amount = 0;
		for(ArrayList<Object> a: database.Select("select username from games_won")) {
			if(!a.get(0).equals(this.differendPlayer)) {
				amount +=1;
			}
		}
		return amount;
	}


	public void setDifferendPlayer(String differendPlayer) {
		this.differendPlayer = differendPlayer;
	}
	
	
	
	



}
