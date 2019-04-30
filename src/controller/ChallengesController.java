package controller;

import model.Challenge;

public class ChallengesController {
	
	private Challenge challenge;

	
	public ChallengesController() {
		challenge = new Challenge();
		
		
		
		
		
	}
	
	public void acceptChallenge() {
		challenge.changePlayerStatusToAccepted();
	}

	public void declineChallenge() {
		challenge.changePlayerStatusToDeclined();
	}
	
	public void checkChallengedPlayer() {
		challenge.GetPlayerWithChallengedStatus();
		
		System.out.println(challenge.GetPlayerWithChallengedStatus());
	}

}
