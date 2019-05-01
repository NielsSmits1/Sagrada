package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Challenge;

public class ChallengesController {

	private Challenge challenge;
	private HomeController home;

	public ChallengesController(HomeController home) {
		this.home = home;
		challenge = new Challenge(home.getSelf());
	}

	public void acceptChallenge() {
		challenge.changePlayerStatusToAccepted();
	}

	public void declineChallenge() {
		challenge.changePlayerStatusToDeclined();
	}

	public void setChallenges() {
		home.buildPlayer("Teun");
	}

	public ArrayList<String> getChallengers() {
		ArrayList<String> challengedPlayerNames = new ArrayList<String>();
		String u;

		for (ArrayList<Object> a : challenge.GetPlayerWithChallengedStatus()) {
			u = (String) a.get(0);
			if (!home.getSelf().getUsername().equals(u)) {
				challengedPlayerNames.add(u);
			} 
		}
		return challengedPlayerNames;
	}
	public HashMap<String, String> getChallenged() {
		HashMap<String, String> challengedPlayerNames = new HashMap<String, String>();
		String u;
		String s;

		for (ArrayList<Object> a : challenge.GetPlayerWithChallengeeStatus()) {
			u = (String) a.get(1);
			s = (String) a.get(3);
			if (!home.getSelf().getUsername().equals(u)) {
				challengedPlayerNames.put(u, s);
			} 
		}
		return challengedPlayerNames;
	}
}
