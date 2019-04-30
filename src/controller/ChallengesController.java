package controller;

import model.Challenge;

public class ChallengesController {
	
	private Challenge challenge;

	
	public ChallengesController() {
		
		
		
		
		
	}
	
	public void acceptChallenge() {
		challenge.changePlayerStatusToAccepted();
	}

	public void declineChallenge() {
		challenge.changePlayerStatusToDeclined();
	}

}
