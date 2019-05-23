package controller;

import java.util.ArrayList;

import View.ChallengesPane;
import View.ChallengesPlayerLinePane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Challenge;
import model.Game;
import model.Player;

public class ChallengesController{
	private ChallengesPane challengesPane;
	private ArrayList<ChallengesPlayerLinePane> challengesPL;

	private Challenge challenge;
	private HomeController home;
	public ChallengesController(HomeController home) {
		this.home = home;
		challenge = new Challenge(home.getSelf());
		challengesPane = new ChallengesPane();
		refresh();

	}

	public ChallengesController() {
		challenge = new Challenge(home.getSelf());
		challengesPane = new ChallengesPane();
		setChallengers();
		challengesPane.setPlayerLine(challengesPL);
	}

	public void declineChallenge(String differentPlayer) {
		challenge.setChallengerUsername(differentPlayer);
		challenge.changePlayerStatusToDeclined();
	}
	public void setChallengers() {
		challengesPL = new ArrayList<ChallengesPlayerLinePane>();
		
		for (Player a: challenge.playersChallengedYou()) {
			ChallengesPlayerLinePane p = new ChallengesPlayerLinePane(a.getUsername());
			p.getAccept().setOnAction(e -> setPlayerStatusToAccepted(a));
			p.getDecline().setOnAction(e -> setPlayerStatusToDeclined(a));
			challengesPL.add(p);
		}
		challengesPane.showPlayerLines(challengesPL);
	}
	
	
	public ChallengesPane getChallengesPane() {
		ChallengesPane cp = challengesPane;
		return cp;
	}
	public void setPlayerStatus(Player challenger) {
		challenge.setChallenger(challenger);
	}
	public void setPlayerStatusToAccepted(Player challenger) {
		setPlayerStatus(challenger);
		challenge.changePlayerStatusToAccepted();
		refresh();
	}
	public void setPlayerStatusToDeclined(Player challenger) {
		setPlayerStatus(challenger);
		challenge.changePlayerStatusToDeclined();
		refresh();
	}
	public void refresh() {
		System.out.println("=)");
		setChallengers();
		challengesPane.setPlayerLine(challengesPL);
	}
	
	
}

