package controller;

import model.Player;

public class PlayerController {

	private Player player;

	public PlayerController(String username, String password) {
		player = new Player(username, password);
	}
	
	public PlayerController(String un) {
		player = new Player(un);
	}

	// checks if the username + password combination already exists
	public boolean hasRows() {
		if (player.getSelect().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	// adds new user to the database.
	public void newUser() {
		player.addUser();
	}

	//returns true if the account details are valid.
	public boolean validateAccountRequirement(String u,String pass) {
		if (usernameIsAccepted( u) && passwordIsAccepted(pass)) {
			return true;
		} else {
			return false;
		}
	}

	//returns true if the password length is greater than 3.
	private boolean passwordIsAccepted(String pass) {
		if (pass.toCharArray().length > 3) {
			return true;
		} else {
			return false;
		}
	}

	//returns true is the username length is greather than 3.
	private boolean usernameIsAccepted(String u) {
		if (u.toCharArray().length > 3) {
			return true;
		} else {
			return false;
		}
	}
	public Player getPlayer() {
		return this.player;
	}


	public void challenge(String u, PlayerController self) {
		this.player = new Player(u);
		player.challenge();
		
	}

	public String getStats() {
		String stats = "Aantal gewonnen en verloren potjes: " + player.getTimesWon() + " : " + player.getTimesLost()
				+ "\nHoogst behaalde score: " + player.getHighScore() 
				+ "\nMeest geplaatste dobbelsteenkleur: " + player.getMostPlacedDiceColor()
				+ "\nMeest geplaatste dobbelsteenwaarde: " + player.getMostPlacedDiceEyes()
				+ "\nAantal verschillende tegenstanders waartegen gespeeld is: " + player.getAmountOfUniquePlayers();
		return stats;
	}
//	
//	public String getStatsPlayer(String username) {
//		player.setDifferendPlayer(username);
//		String stats = "Aantal gewonnen en verloren potjes: " + player.getTimesWonPlayer() + " : " + player.getTimesLostPlayer()
//				+ "\nHoogst behaalde score: " + player.getHighScore() 
//				+ "\nMeest geplaatste dobbelsteenkleur: " + player.getMostPlacedDiceColor()
//				+ "\nMeest geplaatste dobbelsteenwaarde: " + player.getMostPlacedDiceEyes()
//				+ "\nAantal verschillende tegenstanders waartegen gespeeld is: " + player.getAmountOfUniquePlayers();
//		return stats;
//	}


}
