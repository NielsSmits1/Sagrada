package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import Database.db;

public class Challenge {
	private Player self;
	private Player challenger;
	private Game game;
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
	public Game getGame() {
		return this.game;
	}
	

	
	public void changePlayerStatusToAccepted() {
		database.CUD("UPDATE player SET playstatus_playstatus = 'Geaccepteerd' WHERE playstatus_playstatus = 'Uitgedaagde' and username = '" + self.getUsername() +"' and game_idgame in (select game_idgame from (select * FROM player) as playerr where username ='"+ challenger.getUsername() +"')"); // idplayer needs to be variabel
	}

	public void changePlayerStatusToDeclined() {
		database.CUD("UPDATE player SET playstatus_playstatus = 'Geweigerd' WHERE playstatus_playstatus = 'Uitgedaagde' and username = '" + self.getUsername() +"' and game_idgame in (select game_idgame from (select * FROM player) as playerr where username ='"+ challenger.getUsername() +"')"); // idplayer needs to be variabel

	}

	public ArrayList<ArrayList<Object>> GetPlayerWithChallengeeStatus() {
		return database.Select("select * from player where game_idgame in (select game_idgame from player where username = '" + self.getUsername() + "' and playstatus_playstatus = 'Uitgedaagde') AND playstatus_playstatus = 'Uitdager'");
	}	
		//  returns :niels
		//	         teun
	public ArrayList<ArrayList<Object>> GetPlayerWithChallengedStatus() {
		return database.Select("SELECT username, playstatus_playstatus, game_idgame FROM player where game_idgame in (select game_idgame from player where username = '" + self.getUsername() +"' AND playstatus_playstatus = 'Uitdager')");
		// returns :johan
		// 			teun
		//			niels
	}
	
	public ArrayList<Player> playersChallengedYou() {
		ArrayList<Player> challengedPlayerNames = new ArrayList<Player>();
		String u;

		for (ArrayList<Object> a : this.GetPlayerWithChallengeeStatus()) {
			u = (String) a.get(1);
			if (!self.getUsername().equals(u)) {
				Player p = new Player(u);
				challengedPlayerNames.add(p);
			} 
		}
		return challengedPlayerNames;
	}
	public ArrayList<Player> playersYouChallenged() {
		ArrayList<Player> players = new ArrayList<Player>();

		for (ArrayList<Object> a : this.GetPlayerWithChallengedStatus()) {
			Player p = new Player((String) a.get(0));
			p.setStatus((String) a.get(1));
			p.addGameId((int)a.get(2));
			players.add(p);
		}
		return players;
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
	public void addChallenger(Player self2) {
		this.self = self;
		
	}
	public void addChallengee(Player challenger) {
		this.challenger = challenger;
		
	}
	
	public void buildGame() {
		game = new Game();
		game.addPlayer(self,"Uitdager",game.getRandomColor());
		game.addPlayer(challenger, "Uitgedaagde",game.getRandomColor());
	}


}
