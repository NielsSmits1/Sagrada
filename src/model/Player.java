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
	private int lastgame;

	
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
    public ArrayList<ArrayList<Object>> maxPlayerScore(){
    	return database.Select("select username , max(score) as max from player group by username");
    }
    public ArrayList<ArrayList<Object>> maxColor(){
    	return database.Select("select username, diecolor , count(diecolor) as amount_color from player join playerframefield on player.idplayer = playerframefield.player_idplayer where username = '" + username + "' group by username,diecolor order by amount_color DESC limit 1");
    }
    public ArrayList<ArrayList<Object>> maxPlayedAgainst(){
    	return database.Select("select username, diecolor , count(diecolor) as amount_color from player join playerframefield on player.idplayer = playerframefield.player_idplayer where username = '" + username + "' group by username,diecolor order by amount_color DESC limit 1");
    }
    public ArrayList<ArrayList<Object>> maxUniquePlayersPlayed(){
    	return database.Select("select *, count(distinct(username)) as amount_played_against from player where game_idgame in (select game_idgame from player where username = '" + username + "') and username != '" + username + "' and playstatus_playstatus = 'Uitgespeeld' or 'Afgebroken' group by username");
    }
    public ArrayList<ArrayList<Object>> lastGameMade(){
    	return database.Select("select max(idgame)from game");
    }
    public ArrayList<ArrayList<Object>> lastGamePlayers(){
    	return database.Select("SELECT username FROM player where game_idgame = '"+ getLastGame() +"'");
    }
   
    
    public void createNewGame() {
    	database.CUD("INSERT INTO GAME(creationdate) VALUES (now())");
    }
   
    
    public void addSelf() {
    	database.CUD("INSERT INTO PLAYER(username,game_idgame,playstatus_playstatus,isCurrentPlayer,private_objectivecard_color) VALUES ('" + username +"', " + getLastGame() + " , 'Uitdager', 0, 'rood')"); // rood has to be variable between all colors
    }
    
    
    public void addChallenger() {
    	database.CUD("INSERT INTO PLAYER(username,game_idgame,playstatus_playstatus,isCurrentPlayer,private_objectivecard_color) VALUES ('" + username +"', " + getLastGame() + " , 'Uitgedaagde', 0, 'rood')");  // rood has to be variable between all colors
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

	public int getHighScore() {
		int w = 0;
		for(ArrayList<Object> a: maxPlayerScore()) {
			if(a.get(0).equals(this.username)) {
			w = ((Number)a.get(1)).intValue();
			}
		}
		return w;
	}

	public String getMostPlacedDiceColor() {
		String w = "";
		for(ArrayList<Object> a: maxColor()) {
			if(a.get(0).equals(this.username)) {
			w = (String) a.get(1);
			}
		}
		return w;

	}

	public int getMostPlacedDiceEyes() {
		int w = 0;
		return w;
	}

	public int getAmountOfUniquePlayers() {
		int w = maxUniquePlayersPlayed().size();
		return w;
	}
	
	public int getLastGame() {
		int w = 0;
		for(ArrayList<Object> a: lastGameMade()) {
			w = ((Number)a.get(0)).intValue();
		}
		lastgame = w;
		return w;
	}

//		int w = (int) lastGameMade().get(0);
////		int w = ((Number)lastGameMade().get(0)).intValue();
//		
//		return w;
//  }
	public boolean checkGameSize() {
		System.out.println(lastGamePlayers().size());
		if(lastGamePlayers().size() >= 4) {
			return false;
		}
		return true;
	}
	public boolean checkSelf() {                            // returns true if ur already in the idgame
			for(ArrayList<Object> a: lastGamePlayers()) {
				if(a.get(0).equals(this.username)) {
					return true;
				}
			}
			return false;
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
