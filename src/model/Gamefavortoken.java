package model;

public class Gamefavortoken {

	private int tokenID;
	private int playerID;
	
	public Gamefavortoken() {
		tokenID = 0;
		playerID = 0;
	}

	public int getTokenID() {
		return tokenID;
	}

	public void setTokenID(int tokenID) {
		this.tokenID = tokenID;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
}
