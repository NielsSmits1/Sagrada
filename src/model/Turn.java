package model;

import java.util.ArrayList;

public class Turn {
	private Player player;

	public void addPlayer(Player self) {
		player = self;
		
	}
	
	public Turn() {
		
	}
	public void buildTurnPlayer(int idplayer, int seqnr,  int score) {
		
	}
	public void buildTurnPlayer(int idplayer, String username, int seqnr, int score) {
		this.player = new Player(username);
		player.setId(idplayer);
		player.setScore(score);
		player.setSeqnr(seqnr);
	}
	public Player getPlayer() {
		return this.player;
	}
	

}
