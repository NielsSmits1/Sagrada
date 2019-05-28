package controller;

import java.util.ArrayList;

import Database.db;
import model.Player;
import model.Round;
import model.Turn;
public class TurnsController_MoetMetMergeWeggegooidWorden {
	/*
	 * @Niels
	 * 		Gooi dit weg als er gemergd wordt, 
	 * 		ik doe dit nu in een aparte klasse zodat er geen merge conflicten onstaan.
	 * 		Dus gewoon in de GameController/Game-model douwen
	 */
	private db database = new db();
	ArrayList<Round> rounds = new ArrayList<Round>();
	private int gameId = 3;//test enzo
	private int roundNumber;
	private int turnNumber;
	private Player turnPlayer;
	private ArrayList<Player> players = new ArrayList<Player>(); // dit is de lijst met spelers in game-model
	
	
	public TurnsController_MoetMetMergeWeggegooidWorden() {//constructor
		roundNumber = getLastRound();
		turnNumber = getTurnNumber();
		turnPlayer = setWhoseTurnItIs();
		
		// test data moet er straks uit
		Player rowin = new Player("rowin");
		Player stef = new Player("stef");
		Player johan = new Player("johan");
		players.add(rowin);
		players.add(stef);
		players.add(johan);
		
		// als de game gestart wordt 
			/*worden roundNumber, turnNumber turnPlayer geinitialiseerd
			 * aan deze object kun je kijken welke ronde je ziet(roundNumber), de hoeveelste beurt in deze rounde(turnNumber) en wie er aan de beurt is (turnPlayer)
			 */ 
		
		// als de game bezig
			/*Heb je vooral turnPlayer nodig, deze heeft een boolean self, om te kijken of jij het bent
			 */
		
		// na elke beurt, wanneer er dus op passen of op beurt opslaan (ofzo) gedrukt is
			/* doe je setNewCurrentPlayer() : deze update seqnr en isCurrentPlayer
			 * gevolgd door updateCurrentPlayer() : deze veranderdt de isCurrentPlayer in 0
			 * daarna doe je turnPlayer = setWhoseTurnItIs(); 
			 * en roundNumber = getLastRound();
			 * en turnNumber = getTurnNumber();
			 */

		
	}

	private Player setWhoseTurnItIs() {
		String turnplayer = (String)database.Select("select username from player where isCurrentPlayer = 1 and game_idgame = " +this.gameId).get(0).get(0);
		for(Player p: players) {
			if(p.getUsername().equals(turnplayer)) {
				return p;
			}
		}
		return null;
	}
	
	private void updateCurrentPlayer() {
		database.CUD("update player set isCurrentPlayer = 0 where game_idgame = " + this.gameId + " and username = " + turnPlayer.getUsername());
	}
	
	private void setNewCurrentPlayer() {
		int numberOfPlayers = players.size();
		if(turnNumber == numberOfPlayers ) {// 2-3-4
			// dan is de eerste loop voorbij
			backwartsSeqNr();
			setNewCurrentPlayerDB();
			
		}else if(turnNumber == numberOfPlayers * 2) {// 4-6-8
			// dan is een ronde voorbij
			newRound();
		}else {
			setNewCurrentPlayerDB();
		}
	}

	private void newRound() {
		// doe iets met de overgebleven dice(s)
		// en ook iets met roundtrack
		// this.addRoundTrack(gamePane.getRemainingDices());
		forwardSeqNr();
	}
	
	private void forwardSeqNr() {
		int maxNumber = 1;
		for(ArrayList<Object> a : database.Select("select username from player where game_idgame = " + this.gameId)) {// get players in game, DEZE QUERY BESTAAT AL IN GAME 
			database.CUD("update player set seqnr = " + maxNumber+ " where game_idgame = " + this.gameId + " and username = '" + (String)a.get(0) + "'");
			maxNumber+=1;
		}
		database.CUD("update player set isCurrentPlayer = 1 where game_idgame = " + this.gameId + " and seqnr = 1");
	}

	private void backwartsSeqNr() {
		int maxNumber = roundNumber;
		for(ArrayList<Object> a : database.Select("select username from player where game_idgame = " + this.gameId + " order by idplayer desc")) {// get players in game, DEZE QUERY BESTAAT AL IN GAME 
			database.CUD("update player set seqnr = " + (maxNumber + 1) + " where game_idgame = " + this.gameId + " and username = '" + (String)a.get(0) + "'");
			maxNumber+=1;
		}
	}

	private void setNewCurrentPlayerDB() {
		database.CUD("update player set isCurrentPlayer = 1 where seqnr = " + (turnNumber + 1) + " and game_idgame = 2");
	}

	private int getTurnNumber() {
		return (int)database.Select("select seqnr from player where isCurrentPlayer = 1 and game_idgame = " + this.gameId).get(0).get(0);
	}

	
	private int getLastRound(){
		ArrayList<ArrayList<Object>> round = database.Select("Select max(roundtrack) from gamedie where idgame = " + this.gameId);
		if(round.get(0).get(0)==null) {
			// als null - geen rondes: begin bij ronde 1
			return 1;
		}else if((long)round.get(0).get(0) == 10) {
			// als 10 game voorbij duuuh
			// hoezo opent hij dit scherm
			return 10;
		}else {
			return (int)round.get(0).get(0) + 1;
			// anders + 1 is de ronde waar ze in zitten
		}
	}

}
