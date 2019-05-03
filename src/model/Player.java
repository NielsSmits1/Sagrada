package model;
import java.util.ArrayList;

import Database.db;

public class Player {
	private String username;
	private String password;
	private db database = new db();

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



}
