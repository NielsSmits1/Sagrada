package model;
import java.util.ArrayList;

import Database.db;

public class Player {
	private int idplayer;
	private String username;
	private String password;
	private db database = new db();
	private PatternCard board;
	private int score;
	private int seqnr;

	
//	private String differendPlayer;

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
    
    public ArrayList<ArrayList<Object>> playerWonList(){
    	return database.Select("SELECT p1.username,count(p1.username) as games_won FROM player p1 LEFT JOIN player p2 ON p1.game_idgame = p2.game_idgame AND p1.score < p2.score where p2.score is null AND p1.playstatus_playstatus = 'Uitgespeeld' group by p1.username");
    }
    public ArrayList<ArrayList<Object>> playerPlayedList(){
    	return database.Select("select username, count(game_idgame) as played_games from player where playstatus_playstatus = 'uitgespeeld' group by username");
    }
    //adds new user to the database.
    public void addUser() {
        database.CUD("INSERT INTO account (username, password) VALUES ('" + username + "', '" + password + "');");
    }
    
    public boolean checkLogin() {
    	if(getSelect().isEmpty()) {
    		return false;
    	}else {
    		return true;
    	}
    }
    
    public boolean checkUsernameExists() {
    	if(this.checkUsername().isEmpty()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    String getPassword() {
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
		int w = 0;
		for(ArrayList<Object> a: playerWonList()) {
			if(a.get(0).equals(this.username)) {
			w = ((Number)a.get(1)).intValue() ;
			}
		}
		return w;
	}
	public int getTimesPlayed() {
		int w = 0;
		for(ArrayList<Object> a: playerPlayedList()) {
			if(a.get(0).equals(this.username)) {
			w = ((Number)a.get(1)).intValue();
			}
		}
		return w;
	}

	public int getTimesLost() {
		int w = 0;
		getTimesPlayed();
		getTimesWon();
		w = getTimesPlayed()-getTimesWon();

			
		
		return w;
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

	public void setId(int idplayer2) {
		this.idplayer = idplayer2;
		
	}

	public void setScore(int score) {
		this.score = score;
		
	}

	public void setSeqnr(int seqnr) {
		this.seqnr = seqnr;
		
	}
	public int getSeqnr() {
		return this.seqnr;
	}
	public void changeSeqNr() {
		switch(seqnr) {
			case 1:
				seqnr = 8;
				break;
			case 2:
				seqnr = 7;
				break;
			case 3: 
				seqnr = 6;
				break;
			case 4: 
				seqnr = 5;
				break;
			case 5:
				seqnr = 4;
				break;
			case 6:
				seqnr = 3;
				break;
			case 7:
				seqnr = 2;
				break;
			case 8:
				seqnr = 1;
				break;
		}
		updateSeqNr();
	}

	private void updateSeqNr() {
		database.Select("update player set seqnr = " + this.seqnr + " where idplayer = " + this.idplayer);
	}
	
	
//	public int getTimesWonPlayer() {
//		int amount = 0;
//		for(ArrayList<Object> a: database.Select("select username from games_won")) {
//			if(a.get(0).equals(this.username)) {
//				amount +=1;
//			}
//		}
//		return amount;
//	}
//	
//
//	public int getTimesLostPlayer() {
//		int amount = 0;
//		for(ArrayList<Object> a: database.Select("select username from games_won")) {
//			if(!a.get(0).equals(this.username)) {
//				amount +=1;
//			}
//		}
//		return amount;
//	}

//
//	public void setDifferendPlayer(String differendPlayer) {
//		this.differendPlayer = differendPlayer;
//	}
	
	
	
	



}
