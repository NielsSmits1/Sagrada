package controller;

import java.util.ArrayList;

import model.Player;

public class PlayerController {
	private Player player;
	private String username;
	private String password;

	public PlayerController(String u, String p) {
		this.username = u;
		this.password = p;
		player = new Player(username, password);
	}
	
	public PlayerController(String un) {
		username = un;
		player = new Player(username);
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
	public boolean validateAccountRequirement() {
		if (usernameIsAccepted() && passwordIsAccepted()) {
			return true;
		} else {
			return false;
		}
	}

	//returns true if the password length is greater than 3.
	private boolean passwordIsAccepted() {
		if (password.toCharArray().length > 3) {
			return true;
		} else {
			return false;
		}
	}

	//returns true is the username length is greather than 3.
	private boolean usernameIsAccepted() {
		if (username.toCharArray().length > 3) {
			return true;
		} else {
			return false;
		}
	}
	public Player getPlayer() {
		return this.player;
	}
	
	public boolean usernameExist() {
		if(player.checkUsername().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	public boolean isInGame(String username, PlayerController self) {
		String u;
		this.player = new Player(username);
		for(ArrayList<Object> a: self.getPlayer().checkPlayerInGame()) {
			u = (String) a.get(0);
			if(u.equals(username)) {
				return true;
			}
		}
		return false;
	}

	public void challenge(String u, PlayerController self) {
		this.player = new Player(username);
		player.challenge();
		
	}

}
