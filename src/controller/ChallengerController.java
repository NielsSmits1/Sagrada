package controller;

import java.util.ArrayList;
import View.ChallengerPane;
import View.ChallengesPlayerLinePane;
import model.Challenge;
import model.Player;

public class ChallengerController {
	private ChallengerPane challengerPane;
	private Challenge challenge;
	private ArrayList<ChallengesPlayerLinePane> challengesPL;

	public ChallengerController(HomeController homeController) {

		challengerPane = new ChallengerPane();
		challenge = new Challenge(homeController.getSelf());
		setChallengers();
	}

	public void setChallengers() {
		challengesPL = new ArrayList<ChallengesPlayerLinePane>();

		for (Player a : challenge.playersYouChallenged()) {
			ChallengesPlayerLinePane p = new ChallengesPlayerLinePane(a.getUsername(), a.getStatus(), a.getGameId());
			challengesPL.add(p);
		}
		challengerPane.setPlayerLines(challengesPL);

	}

	public void refresh() {
		this.setChallengers();
	}

	public ChallengerPane getChallengerPane() {
		ChallengerPane cp = challengerPane;
		return cp;
	}

}
