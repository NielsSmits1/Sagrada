package controller;

import java.util.Map;

import View.ChallengerPane;
import model.Challenge;


public class ChallengerController {
	private ChallengerPane challengerPane;
	private Challenge challenge;
	
	public ChallengerController(HomeController homeController) {

		challengerPane = new ChallengerPane();
		challenge = new Challenge(homeController.getSelf());

		setChallengers();
	
	}
	
	
	public void setChallengers() {
		for (Map.Entry<String, String> playerNameAndStatus : challenge.playersYouChallenged().entrySet()) {
			challengerPane.addStatusLine(playerNameAndStatus.getKey().toString(),playerNameAndStatus.getValue().toString());
			System.out.println(playerNameAndStatus.getKey().toString() + playerNameAndStatus.getValue().toString());
		}
		challengerPane.setLayout();
	}
	
	

	public ChallengerPane getChallengerPane() {
		ChallengerPane cp = challengerPane;
		return cp;
	}

}
