package model;

public class Turn {
	private Player player;
	private int playerId;
	private int senumber;
	
	Turn(int playerId, Player param){
		this.player = param;
	}
	
public void setPlayerId(int param) {
	this.playerId = param;
	}
public void setSeNumber(int param) {
	this.senumber = param;
}
public int getPlayerId() {
	return this.playerId;
}
public int getseNumber() {
	return this.senumber;
}
}
