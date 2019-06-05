package model;

import java.util.ArrayList;
import java.util.Random;

import Database.Db;

public class Challenge {
	private Player self;
	private Player challenger;
	private Game game;
	private Random random;

	public Challenge() {
		random = new Random();
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
		Db.cud("UPDATE player SET playstatus_playstatus = 'Geaccepteerd' WHERE playstatus_playstatus = 'Uitgedaagde' and username = '"
				+ self.getUsername()
				+ "' and game_idgame in (select game_idgame from (select * FROM player) as playerr where username ='"
				+ challenger.getUsername() + "')"); // idplayer needs to be variabel

	}

	public void changePlayerStatusToDeclined() {
		Db.cud("UPDATE player SET playstatus_playstatus = 'Geweigerd' WHERE playstatus_playstatus = 'Uitgedaagde' and username = '"
				+ self.getUsername()
				+ "' and game_idgame in (select game_idgame from (select * FROM player) as playerr where username ='"
				+ challenger.getUsername() + "')"); // idplayer needs to be variabel

	}

	public ArrayList<ArrayList<Object>> getPlayerWithChallengeeStatus() {

		return Db.select("select * from player where game_idgame in (select game_idgame from player where username = '"
				+ self.getUsername()
				+ "' and playstatus_playstatus = 'Uitgedaagde') AND playstatus_playstatus = 'Uitdager'");
	}

	// returns :niels
	// teun
	public ArrayList<ArrayList<Object>> getPlayerWithChallengedStatus() {

		return Db.select(
				"SELECT username, playstatus_playstatus, game_idgame FROM player where game_idgame in (select game_idgame from player where username = '"
						+ self.getUsername() + "' AND playstatus_playstatus = 'Uitdager')");
		// returns :johan
		// teun
		// niels
	}

	public ArrayList<Player> playersChallengedYou() {
		ArrayList<Player> challengedPlayerNames = new ArrayList<Player>();
		String u;

		for (ArrayList<Object> a : this.getPlayerWithChallengeeStatus()) {
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
		for (ArrayList<Object> a : this.getPlayerWithChallengedStatus()) {
			Player p = new Player((String) a.get(0));
			p.setStatus((String) a.get(1));
			p.addGameId((int) a.get(2));
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

	public void setChallengerUsername(String challengerUsername) {
		challenger.setUsername(challengerUsername);

	}


	public void addChallengee(Player challenger) {
		this.challenger = challenger;

	}

	public void generateRandomToolcards(int idgame) {
		int card1 = random.nextInt(12) + 1;
		int card2 = random.nextInt(12) + 1;
		int card3 = random.nextInt(12) + 1;

		while (card2 == card1) {
			card2 = random.nextInt(12) + 1;
		}

		while (card3 == card1 || card3 == card2) {
			card3 = random.nextInt(12) + 1;
		}

		Db.cud("INSERT INTO gametoolcard (idtoolcard, idgame) VALUES (" + card1 + "," + idgame + ");");
		Db.cud("INSERT INTO gametoolcard (idtoolcard, idgame) VALUES (" + card2 + "," + idgame + ");");
		Db.cud("INSERT INTO gametoolcard (idtoolcard, idgame) VALUES (" + card3 + "," + idgame + ");");

	}

	public void generateRandomObjectcard(int idgame) {
		int card1 = random.nextInt(10) + 1;
		int card2 = random.nextInt(10) + 1;
		int card3 = random.nextInt(10) + 1;
		while (card2 == card1) {
			card2 = random.nextInt(10) + 1;
		}
		while (card3 == card1 || card3 == card2) {
			card3 = random.nextInt(10) + 1;
		}

		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card1 + "," + idgame
				+ ");");
		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card2 + "," + idgame
				+ ");");
		Db.cud("INSERT INTO sharedpublic_objectivecard (idpublic_objectivecard, idgame) VALUES (" + card3 + "," + idgame
				+ ");");
	}

	public void addOptions(int idplayer, int idgame) {
		// Db.cud("INSERT INTO patterncardoption (patterncard_idpatterncard,
		// player_idplayer) (SELECT idpatterncard, " + idplayer +" FROM patterncard
		// ORDER BY RAND() LIMIT 4)");
		boolean unique = true;
		ArrayList<ArrayList<Object>> alreadyChosen = Db.select(
				"Select patterncardoption.patterncard_idpatterncard from patterncardoption join player on player_idplayer = idplayer WHERE game_idgame = "
						+ idgame + "");
		ArrayList<ArrayList<Object>> newlyGenerated = Db
				.select("Select idpatterncard from patterncard ORDER BY RAND() LIMIT 4;");
		while (unique) {
			unique = false;
			for (int i = 0; i < alreadyChosen.size(); i++) {
				for (int j = 0; j < newlyGenerated.size(); j++) {
					if (newlyGenerated.get(j).get(0) == alreadyChosen.get(i).get(0)) {
						unique = true;
						newlyGenerated = Db.select("Select idpatterncard from patterncard ORDER BY RAND() LIMIT 4;");
						break;
					}
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			Db.cud("INSERT INTO patterncardoption (patterncard_idpatterncard, player_idplayer) VALUES ("
					+ (int) newlyGenerated.get(i).get(0) + ", " + idplayer + ");");
		}

	}
	
	public void addCard(int playerid, int idgame) {
		for (int x = 1; x <= 5; x++) {
			for (int y = 1; y <= 4; y++) {
				addChosenCard(x, y, playerid, idgame);
			}
		}
	}
	
	public void addChosenCard(int xPos, int yPos, int yourself, int idgame) {
		Db.cud(
				"insert into playerframefield (player_idplayer, position_x,position_y, idgame) VALUES ("
						+ yourself + "," + xPos + "," + yPos + "," + idgame + ");");
	}

}
